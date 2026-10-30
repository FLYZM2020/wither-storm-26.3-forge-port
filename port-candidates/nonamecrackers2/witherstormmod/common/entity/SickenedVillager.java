/*     */ package nonamecrackers2.witherstormmod.common.entity;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.logging.LogUtils;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.core.Holder;
/*     */ import net.minecraft.core.registries.BuiltInRegistries;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.NbtOps;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.network.syncher.EntityDataSerializers;
/*     */ import net.minecraft.network.syncher.SynchedEntityData;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.MobSpawnType;
/*     */ import net.minecraft.world.entity.ReputationEventHandler;
/*     */ import net.minecraft.world.entity.SpawnGroupData;
/*     */ import net.minecraft.world.entity.ai.village.ReputationEventType;
/*     */ import net.minecraft.world.entity.monster.zombie.ZombieVillager;
/*     */ import net.minecraft.world.entity.npc.villager.Villager;
/*     */ import net.minecraft.world.entity.npc.VillagerData;
/*     */ import net.minecraft.world.entity.npc.VillagerDataHolder;
/*     */ import net.minecraft.world.entity.npc.VillagerProfession;
/*     */ import net.minecraft.world.entity.npc.VillagerTrades;
/*     */ import net.minecraft.world.entity.npc.VillagerType;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.trading.MerchantOffers;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.ServerLevelAccessor;
/*     */ import net.minecraftforge.common.BasicItemListing;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*     */ import nonamecrackers2.witherstormmod.mixin.IMixinZombieVillager;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ import org.slf4j.Logger;
/*     */ 
/*     */ public class SickenedVillager extends SickenedZombie implements VillagerDataHolder {
/*  52 */   static { SICKENED_TRADES = (Map<VillagerProfession, VillagerTrades.ItemListing[]>)Util.make(Maps.newHashMap(), map -> {
/*     */           for (VillagerProfession profession : ForgeRegistries.VILLAGER_PROFESSIONS.getValues()) {
/*     */             if (profession != VillagerProfession.NONE && profession != VillagerProfession.NITWIT) {
/*     */               if (profession == VillagerProfession.CLERIC) {
/*     */                 map.put(profession, makeDefaultWitheredItems(new VillagerTrades.ItemListing[] { (VillagerTrades.ItemListing)new BasicItemListing(1, new ItemStack((ItemLike)WitherStormModItems.TAINTED_DUST.get()), 12, 2) })); continue;
/*     */               } 
/*     */               if (profession == VillagerProfession.FARMER) {
/*     */                 map.put(profession, makeDefaultWitheredItems(new VillagerTrades.ItemListing[] { (VillagerTrades.ItemListing)new BasicItemListing(16, new ItemStack((ItemLike)WitherStormModItems.GOLDEN_APPLE_STEW.get()), 1, 30) }));
/*     */                 continue;
/*     */               } 
/*     */               if (profession == VillagerProfession.MASON) {
/*     */                 map.put(profession, makeDefaultMobStatues());
/*     */                 continue;
/*     */               } 
/*     */               map.put(profession, makeDefaultWitheredItems());
/*     */             } 
/*     */           } 
/*  69 */         }); } private static final Logger LOGGER = LogUtils.getLogger(); public static final Map<VillagerProfession, VillagerTrades.ItemListing[]> SICKENED_TRADES;
/*  70 */   private static final EntityDataAccessor<VillagerData> VILLAGER_DATA = SynchedEntityData.defineId(SickenedVillager.class, EntityDataSerializers.VILLAGER_DATA); @Nullable
/*     */   private Tag gossips;
/*     */   private CompoundTag tradeOffers;
/*     */   private int villagerXp;
/*  74 */   private final SickenedData data = new SickenedData();
/*     */ 
/*     */ 
/*     */   
/*     */   public SickenedVillager(EntityType<? extends SickenedVillager> type, Level world) {
/*  79 */     super((EntityType)type, world);
/*  80 */     BuiltInRegistries.VILLAGER_PROFESSION.getRandom(this.random).ifPresent(prof -> setVillagerData(getVillagerData().setProfession((VillagerProfession)prof.value())));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static VillagerTrades.ItemListing[] makeDefaultWitheredItems() {
/*  87 */     return new VillagerTrades.ItemListing[] { (VillagerTrades.ItemListing)new BasicItemListing(1, new ItemStack((ItemLike)WitherStormModItems.WITHERED_FLESH.get()), 16, 5), (VillagerTrades.ItemListing)new BasicItemListing(1, new ItemStack((ItemLike)WitherStormModItems.WITHERED_BONE.get()), 16, 5), (VillagerTrades.ItemListing)new BasicItemListing(1, new ItemStack((ItemLike)WitherStormModItems.WITHERED_SPIDER_EYE.get()), 16, 5) };
/*     */   }
/*     */ 
/*     */   
/*     */   private static VillagerTrades.ItemListing[] makeDefaultWitheredItems(VillagerTrades.ItemListing... and) {
/*  92 */     return (VillagerTrades.ItemListing[])ArrayUtils.addAll((Object[])makeDefaultWitheredItems(), (Object[])and);
/*     */   }
/*     */ 
/*     */   
/*     */   private static VillagerTrades.ItemListing[] makeDefaultMobStatues() {
/*  97 */     return new VillagerTrades.ItemListing[] { (VillagerTrades.ItemListing)new BasicItemListing(2, new ItemStack((ItemLike)WitherStormModItems.TAINTED_ZOMBIE_SITTING
/*  98 */             .get()), 12, 20), (VillagerTrades.ItemListing)new BasicItemListing(2, new ItemStack((ItemLike)WitherStormModItems.TAINTED_ZOMBIE_WALL
/*  99 */             .get()), 12, 20), (VillagerTrades.ItemListing)new BasicItemListing(2, new ItemStack((ItemLike)WitherStormModItems.TAINTED_ZOMBIE_LYING
/* 100 */             .get()), 12, 20), (VillagerTrades.ItemListing)new BasicItemListing(2, new ItemStack((ItemLike)WitherStormModItems.TAINTED_BONE_PILE
/* 101 */             .get()), 12, 20), (VillagerTrades.ItemListing)new BasicItemListing(2, new ItemStack((ItemLike)WitherStormModItems.TAINTED_SKELETON_WALL
/* 102 */             .get()), 12, 20), (VillagerTrades.ItemListing)new BasicItemListing(2, new ItemStack((ItemLike)WitherStormModItems.TAINTED_SKULL_CEILING
/* 103 */             .get()), 12, 20) };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void defineSynchedData() {
/* 110 */     super.defineSynchedData();
/* 111 */     this.entityData.define(VILLAGER_DATA, new VillagerData(VillagerType.PLAINS, VillagerProfession.NONE, 1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAdditionalSaveData(CompoundTag compound) {
/* 117 */     super.addAdditionalSaveData(compound);
/* 118 */     Objects.requireNonNull(LOGGER); VillagerData.CODEC.encodeStart((DynamicOps)NbtOps.INSTANCE, getVillagerData()).resultOrPartial(LOGGER::error).ifPresent(tag -> compound.put("VillagerData", tag));
/*     */ 
/*     */     
/* 121 */     if (this.tradeOffers != null)
/* 122 */       compound.put("Offers", (Tag)this.tradeOffers); 
/* 123 */     if (this.gossips != null)
/* 124 */       compound.put("Gossips", this.gossips); 
/* 125 */     compound.putInt("Xp", this.villagerXp);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditionalSaveData(CompoundTag compound) {
/* 131 */     super.readAdditionalSaveData(compound);
/* 132 */     if (compound.contains("VillagerData", 10)) {
/*     */       
/* 134 */       DataResult<VillagerData> result = VillagerData.CODEC.parse(new Dynamic((DynamicOps)NbtOps.INSTANCE, compound.get("VillagerData")));
/* 135 */       Objects.requireNonNull(LOGGER); result.resultOrPartial(LOGGER::error).ifPresent(this::setVillagerData);
/*     */     } 
/* 137 */     if (compound.contains("Offers", 10))
/* 138 */       this.tradeOffers = compound.getCompound("Offers"); 
/* 139 */     if (compound.contains("Gossips", 10))
/* 140 */       this.gossips = (Tag)compound.getList("Gossips", 10); 
/* 141 */     if (compound.contains("Xp", 3)) {
/* 142 */       this.villagerXp = compound.getInt("Xp");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void doExtraHandling(Mob mob) {
/* 148 */     Villager villager = (Villager)mob;
/* 149 */     villager.setVillagerData(getVillagerData());
/* 150 */     if (this.gossips != null) {
/* 151 */       villager.setGossips(this.gossips);
/*     */     }
/* 153 */     if (this.tradeOffers != null) {
/* 154 */       villager.setOffers(new MerchantOffers(this.tradeOffers));
/*     */     }
/* 156 */     villager.setVillagerXp(this.villagerXp);
/*     */     
/* 158 */     if (getData().getConversionStarter() != null) {
/*     */       
/* 160 */       Player player = level().getPlayerByUUID(getData().getConversionStarter());
/* 161 */       ((ServerLevel)level()).onReputationEvent(ReputationEventType.ZOMBIE_VILLAGER_CURED, (Entity)player, (ReputationEventHandler)villager);
/*     */     } 
/*     */     
/* 164 */     VillagerTrades.ItemListing[] listings = SICKENED_TRADES.get(villager.getVillagerData().getProfession());
/* 165 */     if (listings != null)
/*     */     {
/* 167 */       for (VillagerTrades.ItemListing listing : listings) {
/*     */         
/* 169 */         MerchantOffers offers = villager.getOffers();
/* 170 */         offers.add(listing.getOffer((Entity)villager, villager.getRandom()));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void convertFrom(Mob mob) {
/* 178 */     if (mob instanceof VillagerDataHolder) { VillagerDataHolder holder = (VillagerDataHolder)mob;
/* 179 */       setVillagerData(holder.getVillagerData()); }
/* 180 */      if (mob instanceof Villager) { Villager villager = (Villager)mob;
/*     */       
/* 182 */       setGossips((Tag)villager.getGossips().store((DynamicOps)NbtOps.INSTANCE));
/* 183 */       setTradeOffers(villager.getOffers().createTag());
/* 184 */       setVillagerXp(villager.getVillagerXp()); }
/*     */     
/* 186 */     else if (mob instanceof ZombieVillager) { ZombieVillager villager = (ZombieVillager)mob;
/*     */       
/* 188 */       IMixinZombieVillager mixinVillager = (IMixinZombieVillager)villager;
/* 189 */       setGossips(mixinVillager.getGossips());
/* 190 */       setTradeOffers(mixinVillager.getTradeOffers());
/* 191 */       setVillagerXp(villager.getVillagerXp()); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SoundEvent getAmbientSound() {
/* 198 */     return SoundEvents.ZOMBIE_VILLAGER_AMBIENT;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SoundEvent getHurtSound(DamageSource source) {
/* 204 */     return SoundEvents.ZOMBIE_VILLAGER_HURT;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SoundEvent getDeathSound() {
/* 210 */     return SoundEvents.ZOMBIE_VILLAGER_DEATH;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SoundEvent getStepSound() {
/* 216 */     return SoundEvents.ZOMBIE_VILLAGER_STEP;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTradeOffers(CompoundTag tag) {
/* 221 */     this.tradeOffers = tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGossips(Tag tag) {
/* 226 */     this.gossips = tag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance instance, MobSpawnType reason, SpawnGroupData data, CompoundTag compound) {
/* 232 */     setVillagerData(getVillagerData().setType(VillagerType.byBiome(world.getBiome(blockPosition()))));
/* 233 */     return super.finalizeSpawn(world, instance, reason, data, compound);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VillagerData getVillagerData() {
/* 239 */     return (VillagerData)this.entityData.get(VILLAGER_DATA);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVillagerData(VillagerData data) {
/* 245 */     VillagerData villagerdata = getVillagerData();
/* 246 */     if (villagerdata.getProfession() != data.getProfession())
/* 247 */       this.tradeOffers = null; 
/* 248 */     this.entityData.set(VILLAGER_DATA, data);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getVillagerXp() {
/* 253 */     return this.villagerXp;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVillagerXp(int xp) {
/* 258 */     this.villagerXp = xp;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WitherSickened.Data getData() {
/* 264 */     return this.data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeWhenFarAway(double dist) {
/* 271 */     return (!isConverting() && this.villagerXp == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static class SickenedData
/*     */     extends WitherSickened.Data
/*     */   {
/*     */     public EntityType<?> getStoredOriginalType() {
/* 279 */       return EntityType.VILLAGER;
/*     */     }
/*     */     
/*     */     public void setOriginal(EntityType<?> originalType, CompoundTag originalData) {}
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SickenedVillager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
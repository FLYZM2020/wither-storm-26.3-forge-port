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
/*     */ import net.minecraft.Util;
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
/*     */ import net.minecraft.world.entity.monster.ZombieVillager;
/*     */ import net.minecraft.world.entity.npc.Villager;
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
/*  52 */   static { SICKENED_TRADES = (Map<VillagerProfession, VillagerTrades.ItemListing[]>)Util.m_137469_(Maps.newHashMap(), map -> {
/*     */           for (VillagerProfession profession : ForgeRegistries.VILLAGER_PROFESSIONS.getValues()) {
/*     */             if (profession != VillagerProfession.f_35585_ && profession != VillagerProfession.f_35596_) {
/*     */               if (profession == VillagerProfession.f_35589_) {
/*     */                 map.put(profession, makeDefaultWitheredItems(new VillagerTrades.ItemListing[] { (VillagerTrades.ItemListing)new BasicItemListing(1, new ItemStack((ItemLike)WitherStormModItems.TAINTED_DUST.get()), 12, 2) })); continue;
/*     */               } 
/*     */               if (profession == VillagerProfession.f_35590_) {
/*     */                 map.put(profession, makeDefaultWitheredItems(new VillagerTrades.ItemListing[] { (VillagerTrades.ItemListing)new BasicItemListing(16, new ItemStack((ItemLike)WitherStormModItems.GOLDEN_APPLE_STEW.get()), 1, 30) }));
/*     */                 continue;
/*     */               } 
/*     */               if (profession == VillagerProfession.f_35595_) {
/*     */                 map.put(profession, makeDefaultMobStatues());
/*     */                 continue;
/*     */               } 
/*     */               map.put(profession, makeDefaultWitheredItems());
/*     */             } 
/*     */           } 
/*  69 */         }); } private static final Logger LOGGER = LogUtils.getLogger(); public static final Map<VillagerProfession, VillagerTrades.ItemListing[]> SICKENED_TRADES;
/*  70 */   private static final EntityDataAccessor<VillagerData> VILLAGER_DATA = SynchedEntityData.m_135353_(SickenedVillager.class, EntityDataSerializers.f_135043_); @Nullable
/*     */   private Tag gossips;
/*     */   private CompoundTag tradeOffers;
/*     */   private int villagerXp;
/*  74 */   private final SickenedData data = new SickenedData();
/*     */ 
/*     */ 
/*     */   
/*     */   public SickenedVillager(EntityType<? extends SickenedVillager> type, Level world) {
/*  79 */     super((EntityType)type, world);
/*  80 */     BuiltInRegistries.f_256735_.m_213642_(this.f_19796_).ifPresent(prof -> m_34375_(m_7141_().m_35565_((VillagerProfession)prof.m_203334_())));
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
/*     */   protected void m_8097_() {
/* 110 */     super.m_8097_();
/* 111 */     this.f_19804_.m_135372_(VILLAGER_DATA, new VillagerData(VillagerType.f_35821_, VillagerProfession.f_35585_, 1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7380_(CompoundTag compound) {
/* 117 */     super.m_7380_(compound);
/* 118 */     Objects.requireNonNull(LOGGER); VillagerData.f_35550_.encodeStart((DynamicOps)NbtOps.f_128958_, m_7141_()).resultOrPartial(LOGGER::error).ifPresent(tag -> compound.m_128365_("VillagerData", tag));
/*     */ 
/*     */     
/* 121 */     if (this.tradeOffers != null)
/* 122 */       compound.m_128365_("Offers", (Tag)this.tradeOffers); 
/* 123 */     if (this.gossips != null)
/* 124 */       compound.m_128365_("Gossips", this.gossips); 
/* 125 */     compound.m_128405_("Xp", this.villagerXp);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7378_(CompoundTag compound) {
/* 131 */     super.m_7378_(compound);
/* 132 */     if (compound.m_128425_("VillagerData", 10)) {
/*     */       
/* 134 */       DataResult<VillagerData> result = VillagerData.f_35550_.parse(new Dynamic((DynamicOps)NbtOps.f_128958_, compound.m_128423_("VillagerData")));
/* 135 */       Objects.requireNonNull(LOGGER); result.resultOrPartial(LOGGER::error).ifPresent(this::m_34375_);
/*     */     } 
/* 137 */     if (compound.m_128425_("Offers", 10))
/* 138 */       this.tradeOffers = compound.m_128469_("Offers"); 
/* 139 */     if (compound.m_128425_("Gossips", 10))
/* 140 */       this.gossips = (Tag)compound.m_128437_("Gossips", 10); 
/* 141 */     if (compound.m_128425_("Xp", 3)) {
/* 142 */       this.villagerXp = compound.m_128451_("Xp");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void doExtraHandling(Mob mob) {
/* 148 */     Villager villager = (Villager)mob;
/* 149 */     villager.m_34375_(m_7141_());
/* 150 */     if (this.gossips != null) {
/* 151 */       villager.m_35455_(this.gossips);
/*     */     }
/* 153 */     if (this.tradeOffers != null) {
/* 154 */       villager.m_35476_(new MerchantOffers(this.tradeOffers));
/*     */     }
/* 156 */     villager.m_35546_(this.villagerXp);
/*     */     
/* 158 */     if (getData().getConversionStarter() != null) {
/*     */       
/* 160 */       Player player = m_9236_().m_46003_(getData().getConversionStarter());
/* 161 */       ((ServerLevel)m_9236_()).m_8670_(ReputationEventType.f_26985_, (Entity)player, (ReputationEventHandler)villager);
/*     */     } 
/*     */     
/* 164 */     VillagerTrades.ItemListing[] listings = SICKENED_TRADES.get(villager.m_7141_().m_35571_());
/* 165 */     if (listings != null)
/*     */     {
/* 167 */       for (VillagerTrades.ItemListing listing : listings) {
/*     */         
/* 169 */         MerchantOffers offers = villager.m_6616_();
/* 170 */         offers.add(listing.m_213663_((Entity)villager, villager.m_217043_()));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void convertFrom(Mob mob) {
/* 178 */     if (mob instanceof VillagerDataHolder) { VillagerDataHolder holder = (VillagerDataHolder)mob;
/* 179 */       m_34375_(holder.m_7141_()); }
/* 180 */      if (mob instanceof Villager) { Villager villager = (Villager)mob;
/*     */       
/* 182 */       setGossips((Tag)villager.m_35517_().m_262795_((DynamicOps)NbtOps.f_128958_));
/* 183 */       setTradeOffers(villager.m_6616_().m_45388_());
/* 184 */       setVillagerXp(villager.m_7809_()); }
/*     */     
/* 186 */     else if (mob instanceof ZombieVillager) { ZombieVillager villager = (ZombieVillager)mob;
/*     */       
/* 188 */       IMixinZombieVillager mixinVillager = (IMixinZombieVillager)villager;
/* 189 */       setGossips(mixinVillager.getGossips());
/* 190 */       setTradeOffers(mixinVillager.getTradeOffers());
/* 191 */       setVillagerXp(villager.m_149889_()); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SoundEvent m_7515_() {
/* 198 */     return SoundEvents.f_12615_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SoundEvent m_7975_(DamageSource source) {
/* 204 */     return SoundEvents.f_12646_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SoundEvent m_5592_() {
/* 210 */     return SoundEvents.f_12645_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SoundEvent m_7660_() {
/* 216 */     return SoundEvents.f_12647_;
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
/*     */   public SpawnGroupData m_6518_(ServerLevelAccessor world, DifficultyInstance instance, MobSpawnType reason, SpawnGroupData data, CompoundTag compound) {
/* 232 */     m_34375_(m_7141_().m_35567_(VillagerType.m_204073_(world.m_204166_(m_20183_()))));
/* 233 */     return super.m_6518_(world, instance, reason, data, compound);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VillagerData m_7141_() {
/* 239 */     return (VillagerData)this.f_19804_.m_135370_(VILLAGER_DATA);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_34375_(VillagerData data) {
/* 245 */     VillagerData villagerdata = m_7141_();
/* 246 */     if (villagerdata.m_35571_() != data.m_35571_())
/* 247 */       this.tradeOffers = null; 
/* 248 */     this.f_19804_.m_135381_(VILLAGER_DATA, data);
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
/*     */   public boolean m_6785_(double dist) {
/* 271 */     return (!isConverting() && this.villagerXp == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static class SickenedData
/*     */     extends WitherSickened.Data
/*     */   {
/*     */     public EntityType<?> getStoredOriginalType() {
/* 279 */       return EntityType.f_20492_;
/*     */     }
/*     */     
/*     */     public void setOriginal(EntityType<?> originalType, CompoundTag originalData) {}
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SickenedVillager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
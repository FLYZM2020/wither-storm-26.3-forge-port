/*     */ package nonamecrackers2.witherstormmod.common.blockentity;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.advancements.CriteriaTriggers;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.core.NonNullList;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.ListTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.tags.BlockTags;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.Container;
/*     */ import net.minecraft.world.ContainerHelper;
/*     */ import net.minecraft.world.WorldlyContainer;
/*     */ import net.minecraft.world.effect.MobEffect;
/*     */ import net.minecraft.world.effect.MobEffectInstance;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.MobSpawnType;
/*     */ import net.minecraft.world.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.world.entity.item.ItemEntity;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*     */ import net.minecraft.world.inventory.ContainerLevelAccess;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.crafting.RecipeType;
/*     */ import net.minecraft.world.level.GameRules;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.Vec2;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.network.PacketDistributor;
/*     */ import nonamecrackers2.witherstormmod.common.blockentity.inventory.SuperBeaconMenu;
/*     */ import nonamecrackers2.witherstormmod.common.capability.PlayerWitherStormData;
/*     */ import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModAttributes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCriteriaTriggers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ import nonamecrackers2.witherstormmod.common.item.crafting.ItemCraftSuperBeaconRecipe;
/*     */ import nonamecrackers2.witherstormmod.common.item.crafting.ResummonSuperBeaconRecipe;
/*     */ import nonamecrackers2.witherstormmod.common.packet.GlobalSoundMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
/*     */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
/*     */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*     */ 
/*     */ public class SuperBeaconBlockEntity extends AbstractSuperBeaconBlockEntity implements WorldlyContainer {
/*     */   public static final int MAX_ITEMS = 16;
/*     */   public static final int RESUMMON_START = 60;
/*  77 */   private static final int[] COLOR = new int[] { 14, 62, 232 }; public static final int RESUMMON_TIME = 372; public static final int EFFECT_RADIUS = 128;
/*  78 */   private final Map<AbstractSuperBeaconBlockEntity.Color, BlockPos> connected = Maps.newHashMap();
/*  79 */   private NonNullList<ItemStack> items = NonNullList.m_122779_(); private int resummonTicks; private boolean isDoingResummoning;
/*     */   @Nullable
/*     */   private EntityType<?> resummoningEntity;
/*     */   @Nullable
/*     */   private CompoundTag resummonNbt;
/*  84 */   public Vec2 shake = Vec2.f_82462_;
/*  85 */   public Vec2 shakeO = Vec2.f_82462_;
/*  86 */   private List<BlockClusterEntity> clusters = Lists.newArrayList();
/*     */ 
/*     */   
/*     */   public SuperBeaconBlockEntity(BlockPos pos, BlockState state) {
/*  90 */     super((BlockEntityType<? extends AbstractSuperBeaconBlockEntity>)WitherStormModBlockEntityTypes.SUPER_BEACON.get(), pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  96 */     super.tick();
/*     */     
/*  98 */     int prevLevel = this.beaconLevel;
/*  99 */     this.beaconLevel = getBeaconLevel();
/* 100 */     if (prevLevel != this.beaconLevel) {
/*     */       
/* 102 */       if (this.beaconLevel > 0) {
/*     */         
/* 104 */         this.isActive = true;
/* 105 */         if (prevLevel == 0) {
/* 106 */           activate();
/*     */         }
/*     */       } else {
/*     */         
/* 110 */         this.isActive = false;
/* 111 */         deactivate();
/*     */       } 
/* 113 */       markUpdated();
/*     */     } 
/*     */     
/* 116 */     findNearbySupportBeacons();
/*     */     
/* 118 */     if (!this.f_58857_.f_46443_)
/*     */     {
/* 120 */       if (!isDoingResummonAnimation()) {
/*     */         
/* 122 */         List<ResummonSuperBeaconRecipe> recipes = this.f_58857_.m_7465_().m_44056_((RecipeType)WitherStormModRecipeTypes.SUPER_BEACON_RESUMMON.get(), (Container)this, this.f_58857_);
/* 123 */         if (!recipes.isEmpty()) {
/*     */           
/* 125 */           ResummonSuperBeaconRecipe recipe = recipes.get(0);
/* 126 */           if (recipe.getCondition().canCraft(this)) {
/*     */             
/* 128 */             this.resummoningEntity = recipe.getResummonEntity();
/* 129 */             this.resummonNbt = recipe.getResummonEntityNBT();
/* 130 */             activateResummonAnimation();
/* 131 */             this.isDoingResummoning = true;
/* 132 */             markUpdated();
/*     */           } 
/*     */         } 
/* 135 */         List<ItemCraftSuperBeaconRecipe> craftingRecipes = this.f_58857_.m_7465_().m_44056_((RecipeType)WitherStormModRecipeTypes.SUPER_BEACON_ITEM.get(), (Container)this, this.f_58857_);
/* 136 */         if (!craftingRecipes.isEmpty()) {
/*     */           
/* 138 */           ItemCraftSuperBeaconRecipe recipe = craftingRecipes.get(0);
/* 139 */           if (recipe.getCondition().canCraft(this)) {
/*     */             
/* 141 */             Vec3 pos = Vec3.m_82512_((Vec3i)m_58899_());
/* 142 */             ServerLevel level = (ServerLevel)this.f_58857_;
/* 143 */             level.m_8767_((ParticleOptions)ParticleTypes.f_123755_, pos.f_82479_, pos.f_82480_ + 2.0D, pos.f_82481_, 20, 1.0D, 1.0D, 1.0D, 0.01D);
/* 144 */             level.m_8767_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), pos.f_82479_, pos.f_82480_ + 2.0D, pos.f_82481_, 50, 1.0D, 1.0D, 1.0D, 0.015D);
/* 145 */             this.f_58857_.m_5594_(null, m_58899_(), (SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_ACTIVATES.get(), SoundSource.BLOCKS, 10.0F, 1.0F);
/* 146 */             ItemStack stack = recipe.assemble(this, this.f_58857_.m_9598_());
/* 147 */             ItemEntity item = new ItemEntity(this.f_58857_, pos.f_82479_, pos.f_82480_ + 2.0D, pos.f_82481_, stack);
/* 148 */             item.m_146915_(true);
/* 149 */             this.f_58857_.m_7967_((Entity)item);
/* 150 */             this.items.clear();
/* 151 */             markUpdated();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 176 */     if (this.isDoingResummoning) {
/*     */       
/* 178 */       this.resummonTicks++;
/* 179 */       BlockPos commandBlockPos = m_58899_().m_6630_(3);
/* 180 */       if (getResummonTicks() > 60) {
/*     */         
/* 182 */         Vec3 cmdBlockVec = Vec3.m_82512_((Vec3i)commandBlockPos);
/* 183 */         double x = cmdBlockVec.f_82479_ + this.random.nextGaussian();
/* 184 */         double y = cmdBlockVec.f_82480_ + this.random.nextGaussian();
/* 185 */         double z = cmdBlockVec.f_82481_ + this.random.nextGaussian();
/* 186 */         Vec3 delta = cmdBlockVec.m_82492_(x, y, z).m_82541_().m_82490_(0.1D);
/* 187 */         this.f_58857_.m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, delta.f_82479_, delta.f_82480_, delta.f_82481_);
/*     */       } 
/*     */       
/* 190 */       if (!this.f_58857_.f_46443_) {
/*     */         
/* 192 */         Vec3 pos = Vec3.m_82512_((Vec3i)m_58899_());
/*     */ 
/*     */         
/* 195 */         if (getResummonTicks() == 60) {
/*     */           
/* 197 */           this.items.clear();
/* 198 */           Level level = this.f_58857_; if (level instanceof ServerLevel) { ServerLevel serverLevel = (ServerLevel)level;
/*     */             
/* 200 */             serverLevel.m_8767_((ParticleOptions)ParticleTypes.f_123755_, pos.f_82479_, pos.f_82480_, pos.f_82481_, 20, 1.0D, 1.0D, 1.0D, 0.01D);
/* 201 */             serverLevel.m_8767_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), pos.f_82479_, pos.f_82480_ + 3.0D, pos.f_82481_, 50, 1.0D, 1.0D, 1.0D, 0.015D);
/* 202 */             this.f_58857_.m_5594_(null, commandBlockPos, (SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_ACTIVATES.get(), SoundSource.BLOCKS, 10.0F, 1.0F);
/* 203 */             boolean flag = (this.resummoningEntity == WitherStormModEntityTypes.WITHER_STORM.get());
/* 204 */             if (flag)
/* 205 */               this.f_58857_.m_5594_(null, commandBlockPos, (SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_BUILD.get(), SoundSource.BLOCKS, 10.0F, 1.0F); 
/* 206 */             if (this.resummoningEntity != null && this.resummonNbt != null && !flag) {
/*     */               
/* 208 */               Entity entity = this.resummoningEntity.m_262496_(serverLevel, commandBlockPos, MobSpawnType.TRIGGERED);
/* 209 */               for (ServerPlayer player : this.f_58857_.m_45976_(ServerPlayer.class, (new AABB(m_58899_())).m_82400_(100.0D)))
/* 210 */                 WitherStormModCriteriaTriggers.SUMMON_MOB_SUPER_BEACON.trigger(player, entity); 
/* 211 */               if (!this.resummonNbt.m_128456_()) {
/*     */                 
/* 213 */                 CompoundTag current = entity.m_20240_(new CompoundTag());
/* 214 */                 current.m_128391_(this.resummonNbt);
/* 215 */                 entity.m_20258_(current);
/*     */               } 
/* 217 */               this.resummoningEntity = null;
/* 218 */               this.resummonNbt = null;
/* 219 */               this.resummonTicks = 0;
/* 220 */               this.isDoingResummoning = false;
/*     */             }  }
/*     */           
/* 223 */           markUpdated();
/*     */         } 
/*     */         
/* 226 */         if (this.resummoningEntity == WitherStormModEntityTypes.WITHER_STORM.get()) {
/*     */ 
/*     */           
/* 229 */           if (getResummonTicks() > 60) {
/*     */             
/* 231 */             if (getResummonTicks() % 40 == 0) {
/*     */               
/* 233 */               playSound((SoundEvent)WitherStormModSoundEvents.BOWELS_LOUD_HURT.get(), 10.0F, 1.0F);
/* 234 */               WitherStormModPacketHandlers.MAIN.send(PacketDistributor.NEAR.with(PacketDistributor.TargetPoint.p(pos.f_82479_, pos.f_82480_, pos.f_82481_, 20.0D, this.f_58857_.m_46472_())), new ShakeScreenMessage(80.0F, 4.0F));
/*     */             } 
/*     */             
/* 237 */             int interval = Math.max(1, 372 / getResummonTicks());
/* 238 */             if (getResummonTicks() % interval == 0 && getResummonTicks() < 352 && this.f_58857_.m_46469_().m_46207_(GameRules.f_46132_))
/*     */             {
/* 240 */               for (int b = 0; b < 2; b++) {
/*     */                 
/* 242 */                 int x = this.random.nextInt(97) - 48 + m_58899_().m_123341_();
/* 243 */                 int z = this.random.nextInt(97) - 48 + m_58899_().m_123343_();
/* 244 */                 BlockPos currentPos = new BlockPos(x, m_58899_().m_123342_() + 3, z);
/* 245 */                 for (int i = 0; i < 30 && this.f_58857_.m_8055_(currentPos.m_7495_()).m_60795_(); i++)
/* 246 */                   currentPos = currentPos.m_7495_(); 
/* 247 */                 currentPos = currentPos.m_7495_();
/* 248 */                 BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(this.f_58857_);
/* 249 */                 cluster.populateWithRadius(currentPos, 1.0F, state -> {
/*     */                       if (!state.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST)) {
/*     */                         for (AbstractSuperBeaconBlockEntity.Color color : AbstractSuperBeaconBlockEntity.Color.values()) {
/*     */                           if (color.isValidBaseBlock(state)) {
/*     */                             return false;
/*     */                           }
/*     */                         } 
/*     */ 
/*     */                         
/* 258 */                         return (!state.m_204336_(WitherStormModBlockTags.WITHERED_BEACON_BASE) && !state.m_204336_(BlockTags.f_13079_) && !state.m_204336_(WitherStormModBlockTags.BEACONS));
/*     */                       } 
/*     */ 
/*     */                       
/*     */                       return false;
/*     */                     });
/*     */                 
/* 265 */                 cluster.setFadePos(m_58899_());
/* 266 */                 cluster.setTime(200);
/* 267 */                 if (getResummonTicks() % 40 == 0)
/* 268 */                   cluster.m_5496_((SoundEvent)WitherStormModSoundEvents.BLOCK_CLUSTER_SHAKE.get(), 2.0F, 1.0F); 
/* 269 */                 cluster.setRotationDelta(new Vec2(this.random.nextInt(20) * 0.1F / 2.0F, this.random.nextInt(20) * 0.1F / 2.0F));
/* 270 */                 cluster.m_20242_(true);
/* 271 */                 cluster.setPhysics(false);
/* 272 */                 this.f_58857_.m_7967_((Entity)cluster);
/* 273 */                 this.clusters.add(cluster);
/*     */               } 
/*     */             }
/*     */             
/* 277 */             Iterator<BlockClusterEntity> iterator = this.clusters.iterator();
/* 278 */             while (iterator.hasNext()) {
/*     */               
/* 280 */               BlockClusterEntity cluster = iterator.next();
/* 281 */               if (cluster.m_6084_()) {
/*     */                 
/* 283 */                 if (cluster.getShakeTime() <= 0) {
/*     */                   
/* 285 */                   Vec3 cmdPos = pos.m_82520_(0.0D, 3.0D, 0.0D);
/*     */ 
/*     */                   
/* 288 */                   Vec3 delta = cmdPos.m_82546_(cluster.m_20182_()).m_82541_().m_82490_(0.5D);
/* 289 */                   cluster.m_20256_(delta);
/* 290 */                   if ((new AABB(cluster.m_20183_())).m_82390_(cmdPos)) {
/* 291 */                     cluster.m_146870_();
/*     */                   }
/*     */                 } 
/*     */                 continue;
/*     */               } 
/* 296 */               iterator.remove();
/*     */             } 
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 302 */           if (getResummonTicks() > 372) {
/*     */             
/* 304 */             this.resummonTicks = 0;
/* 305 */             this.isDoingResummoning = false;
/* 306 */             markUpdated();
/* 307 */             for (BlockClusterEntity cluster : this.clusters)
/* 308 */               cluster.m_146870_(); 
/* 309 */             this.f_58857_.m_7471_(m_58899_(), false);
/* 310 */             for (BlockPos connected : getConnected().values())
/* 311 */               this.f_58857_.m_7471_(connected, false); 
/* 312 */             this.f_58857_.m_254849_(null, pos.f_82479_, pos.f_82480_, pos.f_82481_, 8.0F, Level.ExplosionInteraction.BLOCK);
/* 313 */             WitherStormEntity storm = (WitherStormEntity)((EntityType)WitherStormModEntityTypes.WITHER_STORM.get()).m_20615_(this.f_58857_);
/* 314 */             storm.m_21051_((Attribute)WitherStormModAttributes.EVOLUTION_SPEED.get()).m_22125_(new AttributeModifier("resummonedModifier", -0.5D, AttributeModifier.Operation.ADDITION));
/* 315 */             storm.setPhase(((Integer)WitherStormModConfig.SERVER.resummonedPhase.get()).intValue());
/* 316 */             storm.m_20219_(pos);
/* 317 */             storm.playSoundToEveryone((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_EVOLVES.get(), 1.0F, 1.0F);
/* 318 */             storm.getPlayDeadManager().setRecentlyRevived(true);
/* 319 */             storm.setResummoned(true);
/* 320 */             for (ServerPlayer player : this.f_58857_.m_45976_(ServerPlayer.class, (new AABB(m_58899_())).m_82400_(100.0D)))
/* 321 */               CriteriaTriggers.f_10580_.m_68256_(player, (Entity)storm); 
/* 322 */             this.f_58857_.m_7967_((Entity)storm);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 328 */     this.shakeO = this.shake;
/* 329 */     if (isDoingResummonAnimation()) {
/*     */       
/* 331 */       float x = Mth.m_14089_(this.resummonTicks * 4.0F) * 0.1F + (this.random.nextFloat() - 0.5F) * 0.05F;
/* 332 */       float z = Mth.m_14089_(this.resummonTicks * 3.0F) * 0.1F + (this.random.nextFloat() - 0.5F) * 0.05F;
/* 333 */       this.shake = new Vec2(x, z);
/*     */     } 
/*     */     
/* 336 */     if (!this.f_58857_.f_46443_ && getResummonTicks() == getResummonThreshold()) {
/*     */       
/* 338 */       playSound((SoundEvent)WitherStormModSoundEvents.WITHERED_BEACON_ACTIVATE.get(), 1.0F, 1.0F);
/* 339 */       playSound((SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), 10.0F, 1.0F);
/* 340 */       Vec3 pos = Vec3.m_82512_((Vec3i)m_58899_());
/* 341 */       WitherStormModPacketHandlers.MAIN.send(PacketDistributor.NEAR.with(PacketDistributor.TargetPoint.p(pos.f_82479_, pos.f_82480_, pos.f_82481_, 20.0D, this.f_58857_.m_46472_())), new ShakeScreenMessage(80.0F, 10.0F));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7651_() {
/* 348 */     super.m_7651_();
/* 349 */     this.clusters.forEach(c -> {
/*     */           c.m_20242_(false);
/*     */           c.setPhysics(true);
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_142466_(CompoundTag tag) {
/* 358 */     super.m_142466_(tag);
/* 359 */     NonNullList<ItemStack> items = NonNullList.m_122779_();
/* 360 */     ListTag list = tag.m_128437_("ResummonItems", 10);
/* 361 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/* 363 */       CompoundTag item = list.m_128728_(i);
/* 364 */       items.add(ItemStack.m_41712_(item));
/*     */     } 
/* 366 */     this.items = items;
/* 367 */     this.resummonTicks = tag.m_128451_("ResummonTicks");
/* 368 */     this.isDoingResummoning = tag.m_128471_("Resummoning");
/* 369 */     if (tag.m_128441_("ResummoningEntity")) {
/*     */       
/* 371 */       String rawId = tag.m_128461_("ResummoningEntity");
/* 372 */       this.resummoningEntity = EntityType.m_20632_(rawId).orElse(null);
/*     */     } 
/* 374 */     if (tag.m_128441_("ResummonNBT")) {
/* 375 */       this.resummonNbt = tag.m_128469_("ResummonNBT");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_183515_(CompoundTag tag) {
/* 381 */     super.m_183515_(tag);
/* 382 */     ListTag list = new ListTag();
/* 383 */     for (ItemStack stack : this.items) {
/*     */       
/* 385 */       CompoundTag item = new CompoundTag();
/* 386 */       list.add(stack.m_41739_(item));
/*     */     } 
/* 388 */     tag.m_128365_("ResummonItems", (Tag)list);
/* 389 */     tag.m_128405_("ResummonTicks", this.resummonTicks);
/* 390 */     tag.m_128379_("Resummoning", this.isDoingResummoning);
/* 391 */     if (this.resummoningEntity != null) {
/*     */       
/* 393 */       ResourceLocation id = EntityType.m_20613_(this.resummoningEntity);
/* 394 */       tag.m_128359_("ResummoningEntity", id.toString());
/*     */     } 
/* 396 */     if (this.resummonNbt != null) {
/* 397 */       tag.m_128365_("ResummonNBT", (Tag)this.resummonNbt);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEffect(ServerLevel level) {
/* 403 */     AABB box = (new AABB(m_58899_())).m_82400_(128.0D).m_82363_(0.0D, level.m_141928_(), 0.0D);
/* 404 */     level.m_45976_(Player.class, box).forEach(player -> {
/*     */           if (Math.sqrt(player.m_20238_(Vec3.m_82512_((Vec3i)m_58899_()))) <= 128.0D) {
/*     */             player.m_7292_(new MobEffectInstance(this.effect, 505, this.beaconLevel - 1, true, true));
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private void findNearbySupportBeacons() {
/* 413 */     AABB box = (new AABB(m_58899_())).m_82400_(5.0D);
/* 414 */     List<BlockEntity> entities = WorldUtil.getBlockEntitiesInAABB(this.f_58857_, box);
/*     */     
/* 416 */     Iterator<BlockPos> iterator = this.connected.values().iterator();
/* 417 */     while (iterator.hasNext()) {
/*     */       
/* 419 */       BlockPos pos = iterator.next();
/* 420 */       BlockEntity entity = this.f_58857_.m_7702_(pos);
/* 421 */       if (!this.isValidSupportBeacon.test(entity)) {
/* 422 */         iterator.remove();
/*     */       }
/*     */     } 
/* 425 */     for (BlockEntity entity : entities) {
/*     */       
/* 427 */       if (this.isValidSupportBeacon.test(entity) && Math.sqrt(entity.m_58899_().m_123331_((Vec3i)m_58899_())) <= 5.0D) {
/*     */         
/* 429 */         AbstractSuperBeaconBlockEntity.Color color = ((SuperSupportBeaconBlockEntity)entity).getColor();
/* 430 */         this.connected.putIfAbsent(color, entity.m_58899_());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int getBeaconLevel() {
/* 437 */     BlockPos pos = m_58899_();
/* 438 */     int level = 0;
/*     */     
/* 440 */     for (int i = 1; i <= 4; level = i++) {
/*     */       
/* 442 */       int y = pos.m_123342_() - i;
/* 443 */       if (i < this.f_58857_.m_141937_()) {
/*     */         break;
/*     */       }
/* 446 */       boolean flag = true;
/*     */       
/* 448 */       for (int x = pos.m_123341_() - i; x <= pos.m_123341_() + i && flag; x++) {
/*     */         
/* 450 */         for (int z = pos.m_123343_() - i; z <= pos.m_123343_() + i; z++) {
/*     */           
/* 452 */           BlockState state = this.f_58857_.m_8055_(new BlockPos(x, y, z));
/* 453 */           if ((i == 1) ? !state.m_204336_(WitherStormModBlockTags.WITHERED_BEACON_BASE) : !state.m_204336_(BlockTags.f_13079_)) {
/*     */             
/* 455 */             flag = false;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 461 */       if (!flag) {
/*     */         break;
/*     */       }
/*     */     } 
/* 465 */     return level;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConnected(BlockPos pos) {
/* 470 */     return this.connected.containsValue(pos);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getThickness() {
/* 476 */     return 0.25F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getOuterThickness() {
/* 482 */     return 0.45F;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<AbstractSuperBeaconBlockEntity.Color, BlockPos> getConnected() {
/* 487 */     return this.connected;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doPowerUp(ServerPlayer player) {
/* 493 */     super.doPowerUp(player);
/* 494 */     player.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(data -> {
/*     */           if (!data.hasActivatedSuperBeacon() && this.connected.size() >= (AbstractSuperBeaconBlockEntity.Color.values()).length) {
/*     */             this.poweringUpAnimation = 80;
/*     */             WitherStormModPacketHandlers.MAIN.send(PacketDistributor.ALL.noArg(), new GlobalSoundMessage((SoundEvent)WitherStormModSoundEvents.WITHERED_BEACON_POWER_UP.get(), 1.0F, 1.0F));
/*     */             this.f_58857_.m_45976_(ServerPlayer.class, (new AABB(m_58899_())).m_82400_(64.0D)).forEach(());
/*     */             for (BlockPos pos : this.connected.values()) {
/*     */               BlockEntity entity = this.f_58857_.m_7702_(pos);
/*     */               if (entity instanceof AbstractSuperBeaconBlockEntity) {
/*     */                 AbstractSuperBeaconBlockEntity superBeacon = (AbstractSuperBeaconBlockEntity)entity;
/*     */                 superBeacon.poweringUpAnimation = 80;
/*     */                 superBeacon.markUpdated();
/*     */                 superBeacon.doActivationSequence();
/*     */                 superBeacon.activateAnim = 0.0F;
/*     */               } 
/*     */             } 
/*     */             markUpdated();
/*     */             doActivationSequence();
/*     */             this.activateAnim = 0.0F;
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doPoweringUpAnimation() {
/* 524 */     if (!this.f_58857_.f_46443_)
/*     */     {
/* 526 */       if (this.poweringUpAnimation == 40) {
/*     */         
/* 528 */         WitherStormModPacketHandlers.MAIN.send(PacketDistributor.ALL.noArg(), new ShakeScreenMessage(120.0F, 12.0F));
/* 529 */         Level level = this.f_58857_; if (level instanceof ServerLevel) { ServerLevel serverLevel = (ServerLevel)level;
/*     */           
/* 531 */           Vec3 pos = Vec3.m_82512_((Vec3i)m_58899_());
/* 532 */           serverLevel.m_8767_((ParticleOptions)ParticleTypes.f_123799_, pos.f_82479_, pos.f_82480_, pos.f_82481_, 200, this.random.nextGaussian(), this.random.nextGaussian(), this.random.nextGaussian(), 0.2D); }
/*     */         
/* 534 */         this.f_58857_.m_45976_(ServerPlayer.class, (new AABB(m_58899_())).m_82400_(64.0D)).forEach(p -> {
/*     */               p.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(());
/*     */               WitherStormModCriteriaTriggers.ACTIVATE_SUPER_BEACON.trigger(p, this.connected.size());
/*     */             });
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<MobEffect> getValidEffects() {
/* 546 */     return VALID_EFFECTS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractContainerMenu m_7208_(int id, Inventory inventory, Player player) {
/* 552 */     return BaseContainerBlockEntity.m_58629_(player, this.lockKey, m_5446_()) ? (AbstractContainerMenu)new SuperBeaconMenu(id, (Container)inventory, this.data, ContainerLevelAccess.m_39289_(this.f_58857_, m_58899_()), this::doPowerUp, getValidEffects()) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getBeamColor() {
/* 558 */     return COLOR;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(ItemStack stack) {
/* 563 */     m_6836_(m_6643_(), stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack takeItem() {
/* 568 */     return m_7407_(m_6643_() - 1, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getResummonTicks() {
/* 573 */     return this.resummonTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec2 getShake(float partialTicks) {
/* 578 */     float x = Mth.m_14179_(partialTicks, this.shakeO.f_82470_, this.shake.f_82470_);
/* 579 */     float z = Mth.m_14179_(partialTicks, this.shakeO.f_82471_, this.shake.f_82471_);
/* 580 */     return new Vec2(x, z);
/*     */   }
/*     */ 
/*     */   
/*     */   private void activateResummonAnimation() {
/* 585 */     playSound((SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), 10.0F, 1.0F);
/* 586 */     playSound((SoundEvent)WitherStormModSoundEvents.BOWELS_LOUD_HURT.get(), 10.0F, 1.0F);
/* 587 */     Vec3 pos = Vec3.m_82512_((Vec3i)m_58899_());
/* 588 */     WitherStormModPacketHandlers.MAIN.send(PacketDistributor.NEAR.with(PacketDistributor.TargetPoint.p(pos.f_82479_, pos.f_82480_, pos.f_82481_, 20.0D, this.f_58857_.m_46472_())), new ShakeScreenMessage(80.0F, 10.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDoingResummonAnimation() {
/* 593 */     return this.isDoingResummoning;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isActive() {
/* 599 */     return (super.isActive() && !isDoingResummonAnimation());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean shouldDoActivatedAnim() {
/* 605 */     return (super.shouldDoActivatedAnim() || getResummonTicks() > getResummonThreshold());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_6211_() {
/* 611 */     this.items.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int m_6643_() {
/* 617 */     return this.items.size();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_7983_() {
/* 623 */     for (ItemStack itemstack : this.items) {
/*     */       
/* 625 */       if (!itemstack.m_41619_())
/* 626 */         return false; 
/*     */     } 
/* 628 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack m_8020_(int slot) {
/* 634 */     return (slot >= 0 && slot < this.items.size()) ? (ItemStack)this.items.get(slot) : ItemStack.f_41583_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack m_7407_(int slot, int amount) {
/* 640 */     if (!this.items.isEmpty()) {
/*     */       
/* 642 */       ItemStack stack = ((ItemStack)this.items.get(slot)).m_41620_(amount);
/* 643 */       if (((ItemStack)this.items.get(slot)).m_41619_())
/* 644 */         this.items.remove(slot); 
/* 645 */       markUpdated();
/* 646 */       return stack;
/*     */     } 
/*     */ 
/*     */     
/* 650 */     return ItemStack.f_41583_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack m_8016_(int slot) {
/* 657 */     return ContainerHelper.m_18966_((List)this.items, slot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_6836_(int slot, ItemStack stack) {
/* 663 */     if (slot >= 0 && slot <= 16) {
/*     */       
/* 665 */       this.items.add(slot, stack);
/* 666 */       markUpdated();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6542_(Player player) {
/* 673 */     return Container.m_272074_(this, player);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlayerUseItems(Player player) {
/* 678 */     return BaseContainerBlockEntity.m_58629_(player, this.lockKey, m_5446_());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] m_7071_(Direction directions) {
/* 684 */     return new int[] { m_6643_() };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_7155_(int slot, ItemStack stack, Direction direction) {
/* 690 */     return (direction != Direction.DOWN && slot < 16 && !isDoingResummonAnimation());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_7157_(int slot, ItemStack stack, Direction direction) {
/* 696 */     return false;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EntityType<?> getResummonEntity() {
/* 701 */     return this.resummoningEntity;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\blockentity\SuperBeaconBlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
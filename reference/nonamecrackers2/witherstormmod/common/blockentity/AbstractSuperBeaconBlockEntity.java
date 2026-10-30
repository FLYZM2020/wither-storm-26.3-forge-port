/*     */ package nonamecrackers2.witherstormmod.common.blockentity;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.util.Objects;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.ClientGamePacketListener;
/*     */ import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.LockCode;
/*     */ import net.minecraft.world.MenuProvider;
/*     */ import net.minecraft.world.effect.MobEffect;
/*     */ import net.minecraft.world.effect.MobEffects;
/*     */ import net.minecraft.world.inventory.ContainerData;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraftforge.common.extensions.IForgeBlockEntity;
/*     */ import net.minecraftforge.network.PacketDistributor;
/*     */ import nonamecrackers2.witherstormmod.common.capability.ChunkLoadingBlockEntities;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ import nonamecrackers2.witherstormmod.common.packet.RemoveDistantSuperBeaconMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.UpdateDistantSuperBeaconMessage;
/*     */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
/*     */ 
/*     */ public abstract class AbstractSuperBeaconBlockEntity
/*     */   extends BlockEntity
/*     */   implements MenuProvider {
/*  42 */   public static final Set<MobEffect> VALID_EFFECTS = (Set<MobEffect>)ImmutableSet.of(MobEffects.f_19600_, MobEffects.f_19606_, MobEffects.f_19598_, MobEffects.f_19603_, MobEffects.f_19596_, MobEffects.f_19611_, (Object[])new MobEffect[] { MobEffects.f_19605_, MobEffects.f_19618_ });
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int POWER_UP_ANIM_TIME = 80;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int POWER_UP_CLIMAX = 40;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int CONTAINER_DATA_SIZE = 4;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int SCAN_DIST = 5;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int COOLDOWN = 200;
/*     */ 
/*     */ 
/*     */   
/*     */   protected final Predicate<BlockEntity> isValidSupportBeacon;
/*     */ 
/*     */ 
/*     */   
/*     */   protected final Random random;
/*     */ 
/*     */ 
/*     */   
/*     */   protected int ticks;
/*     */ 
/*     */ 
/*     */   
/*     */   protected int activationTime;
/*     */ 
/*     */ 
/*     */   
/*     */   protected int beamHeight;
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected MobEffect effect;
/*     */ 
/*     */   
/*     */   protected boolean isActive;
/*     */ 
/*     */   
/*     */   protected float activateAnim;
/*     */ 
/*     */   
/*     */   protected float activateAnimO;
/*     */ 
/*     */   
/*     */   protected LockCode lockKey;
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected Component name;
/*     */ 
/*     */   
/*     */   public int beaconLevel;
/*     */ 
/*     */   
/*     */   protected int randomOffset;
/*     */ 
/*     */   
/*     */   protected final ContainerData data;
/*     */ 
/*     */   
/*     */   protected int poweringUpAnimation;
/*     */ 
/*     */   
/*     */   protected boolean showWorkingArea;
/*     */ 
/*     */   
/*     */   protected int effectSetCooldown;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractSuperBeaconBlockEntity(BlockEntityType<? extends AbstractSuperBeaconBlockEntity> type, BlockPos pos, BlockState state) {
/* 126 */     super(type, pos, state); this.isValidSupportBeacon = (entity -> { if (isActive() && entity != null && entity instanceof SuperSupportBeaconBlockEntity) { SuperSupportBeaconBlockEntity support = (SuperSupportBeaconBlockEntity)entity; if (!entity.m_58901_() && support.getColor() != null); }  return false; }); this.random = new Random(); this.lockKey = LockCode.f_19102_; this.data = new ContainerData() { public void m_8050_(int id, int value) { MobEffect effect; switch (id) { case 0: AbstractSuperBeaconBlockEntity.this.beaconLevel = value; break;case 1: effect = MobEffect.m_19453_(value); if (effect == null || AbstractSuperBeaconBlockEntity.this.getValidEffects().contains(effect)) AbstractSuperBeaconBlockEntity.this.effect = effect;  break;case 2: AbstractSuperBeaconBlockEntity.this.setShowWorkingArea((value == 1)); break;case 3: AbstractSuperBeaconBlockEntity.this.effectSetCooldown = value; break; }  } public int m_6499_() { return 4; } public int m_6413_(int id) { switch (id) { case 0: return AbstractSuperBeaconBlockEntity.this.beaconLevel;case 1: return MobEffect.m_19459_(AbstractSuperBeaconBlockEntity.this.effect);case 2: return AbstractSuperBeaconBlockEntity.this.showWorkingArea() ? 1 : 0;case 3: return AbstractSuperBeaconBlockEntity.this.effectSetCooldown; }  return 0; } }
/* 127 */       ; this.randomOffset = this.random.nextInt(100);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/* 132 */     this.ticks++;
/*     */     
/* 134 */     if (!this.f_58857_.f_46443_) {
/* 135 */       Objects.requireNonNull(this.f_58857_); WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(this.f_58857_::m_46472_), new UpdateDistantSuperBeaconMessage(m_58899_(), getBeamColor(), isActive(), getBeamHeight(), getThickness(), getOuterThickness()));
/*     */     } 
/* 137 */     tickActivationAnimation();
/*     */     
/* 139 */     this.activateAnimO = this.activateAnim;
/* 140 */     if (shouldDoActivatedAnim()) {
/* 141 */       this.activateAnim += (1.0F - this.activateAnim) / 8.0F;
/*     */     } else {
/* 143 */       this.activateAnim += (0.0F - this.activateAnim) / 8.0F;
/*     */     } 
/* 145 */     if (isActive() && !isPoweringUp() && (this.ticks + this.randomOffset) % 80 == 0) {
/* 146 */       this.f_58857_.m_5594_(null, m_58899_(), (SoundEvent)WitherStormModSoundEvents.WITHERED_BEACON_AMBIENT.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
/*     */     }
/* 148 */     if (this.poweringUpAnimation > 0) {
/*     */       
/* 150 */       this.poweringUpAnimation--;
/* 151 */       doPoweringUpAnimation();
/*     */     } 
/*     */     
/* 154 */     if (!this.f_58857_.f_46443_ && this.effect != null && isActive()) {
/* 155 */       applyEffect((ServerLevel)this.f_58857_);
/*     */     }
/* 157 */     if (!this.f_58857_.f_46443_ && this.effectSetCooldown > 0) {
/* 158 */       this.effectSetCooldown--;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doActivationSequence() {
/* 165 */     this.beamHeight = 0;
/* 166 */     this.activationTime = 0;
/* 167 */     markUpdated();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void tickActivationAnimation() {
/* 172 */     if (hasReachedPowerUpClimax()) {
/*     */       
/* 174 */       this.activationTime++;
/*     */       
/* 176 */       if (this.beamHeight < 1024) {
/* 177 */         this.beamHeight += this.activationTime / 2;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void doPoweringUpAnimation() {}
/*     */   
/*     */   public int getTicks() {
/* 185 */     return this.ticks;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AABB getRenderBoundingBox() {
/* 191 */     return IForgeBlockEntity.INFINITE_EXTENT_AABB;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBeamHeight() {
/* 196 */     return this.beamHeight;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isActive() {
/* 201 */     return this.isActive;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getActivateAnimation(float partialTicks) {
/* 206 */     return Mth.m_14179_(partialTicks, this.activateAnimO, this.activateAnim);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void activate() {
/* 211 */     playSound((SoundEvent)WitherStormModSoundEvents.WITHERED_BEACON_ACTIVATE.get(), 1.0F, 1.0F);
/* 212 */     doActivationSequence();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void deactivate() {
/* 217 */     playSound((SoundEvent)WitherStormModSoundEvents.WITHERED_BEACON_DEACTIVATE.get(), 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void playSound(SoundEvent event, float volume, float pitch) {
/* 222 */     this.f_58857_.m_5594_(null, m_58899_(), event, SoundSource.BLOCKS, volume, pitch + (this.random.nextFloat() - 0.5F) * 0.35F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7651_() {
/* 228 */     super.m_7651_();
/* 229 */     if (isActive())
/* 230 */       deactivate(); 
/* 231 */     if (!this.f_58857_.f_46443_) {
/* 232 */       Objects.requireNonNull(this.f_58857_); WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(this.f_58857_::m_46472_), new RemoveDistantSuperBeaconMessage(m_58899_()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet<ClientGamePacketListener> m_58483_() {
/* 242 */     return (Packet<ClientGamePacketListener>)ClientboundBlockEntityDataPacket.m_195640_(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundTag m_5995_() {
/* 248 */     return m_187482_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLoad() {
/* 254 */     super.onLoad();
/* 255 */     this.f_58857_.getCapability(WitherStormModCapabilities.CHUNK_LOADING_BLOCK_ENTITIES).ifPresent(cap -> cap.add(m_58899_()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_142466_(CompoundTag tag) {
/* 263 */     super.m_142466_(tag);
/* 264 */     this.activationTime = tag.m_128451_("ActivationTime");
/* 265 */     this.beamHeight = tag.m_128451_("BeamHeight");
/* 266 */     this.isActive = tag.m_128471_("IsActive");
/* 267 */     if (tag.m_128425_("CustomName", 8))
/* 268 */       this.name = (Component)Component.Serializer.m_130701_(tag.m_128461_("CustomName")); 
/* 269 */     this.poweringUpAnimation = tag.m_128451_("PowerUpTime");
/* 270 */     this.activateAnim = tag.m_128457_("ActivationAnim");
/* 271 */     this.effect = MobEffect.m_19453_(tag.m_128451_("Primary"));
/* 272 */     this.showWorkingArea = tag.m_128471_("ShowWorkingArea");
/* 273 */     this.effectSetCooldown = tag.m_128451_("Cooldown");
/* 274 */     this.lockKey = LockCode.m_19111_(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_183515_(CompoundTag tag) {
/* 280 */     super.m_183515_(tag);
/* 281 */     tag.m_128405_("ActivationTime", this.activationTime);
/* 282 */     tag.m_128405_("BeamHeight", this.beamHeight);
/* 283 */     tag.m_128379_("IsActive", this.isActive);
/* 284 */     if (this.name != null)
/* 285 */       tag.m_128359_("CustomName", Component.Serializer.m_130703_(this.name)); 
/* 286 */     tag.m_128405_("PowerUpTime", this.poweringUpAnimation);
/* 287 */     tag.m_128350_("ActivationAnim", this.activateAnim);
/* 288 */     tag.m_128405_("Primary", MobEffect.m_19459_(this.effect));
/* 289 */     tag.m_128379_("ShowWorkingArea", this.showWorkingArea);
/* 290 */     tag.m_128405_("Cooldown", this.effectSetCooldown);
/* 291 */     this.lockKey.m_19109_(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void markUpdated() {
/* 296 */     m_6596_();
/* 297 */     this.f_58857_.m_7260_(m_58899_(), m_58900_(), m_58900_(), 3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomName(@Nullable Component component) {
/* 302 */     this.name = component;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Component m_5446_() {
/* 308 */     return (this.name != null) ? this.name : (Component)Component.m_237115_("container.witherstormmod.withered_beacon");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPoweringUp() {
/* 313 */     return (this.poweringUpAnimation > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasReachedPowerUpClimax() {
/* 318 */     return (this.poweringUpAnimation <= 40);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doPowerUp(ServerPlayer player) {
/* 325 */     playSound((SoundEvent)WitherStormModSoundEvents.WITHERED_BEACON_ACTIVATE.get(), 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShowWorkingArea(boolean flag) {
/* 330 */     this.showWorkingArea = flag;
/* 331 */     markUpdated();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean showWorkingArea() {
/* 336 */     return this.showWorkingArea;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCooldown() {
/* 343 */     return this.effectSetCooldown;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCooldown(int cooldown) {
/* 348 */     this.effectSetCooldown = cooldown;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean shouldDoActivatedAnim() {
/* 353 */     return (isActive() && hasReachedPowerUpClimax());
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getResummonThreshold() {
/* 358 */     return 60 + (Color.values()).length * 40;
/*     */   } protected abstract void applyEffect(ServerLevel paramServerLevel); public abstract float getThickness(); public abstract float getOuterThickness(); public abstract Set<MobEffect> getValidEffects();
/*     */   public abstract int[] getBeamColor();
/*     */   public enum Color { AQUA, GREEN, GRAY, RED; private final Set<MobEffect> validEffects; private final Predicate<BlockState> block; private final int r; private final int g; private final int b;
/*     */     static {
/* 363 */       AQUA = new Color("AQUA", 0, (Set<MobEffect>)ImmutableSet.of(MobEffects.f_19611_, MobEffects.f_19608_, MobEffects.f_19598_), block -> block.m_204336_(WitherStormModBlockTags.AQUA_SUPPORT_BASE), 5, 255, 255);
/* 364 */       GREEN = new Color("GREEN", 1, (Set<MobEffect>)ImmutableSet.of(MobEffects.f_19596_, MobEffects.f_19593_, MobEffects.f_19603_), block -> block.m_204336_(WitherStormModBlockTags.GREEN_SUPPORT_BASE), 26, 255, 0);
/* 365 */       GRAY = new Color("GRAY", 2, (Set<MobEffect>)ImmutableSet.of(MobEffects.f_19600_, MobEffects.f_19609_, MobEffects.f_19607_), block -> block.m_204336_(WitherStormModBlockTags.GRAY_SUPPORT_BASE), 255, 255, 255);
/* 366 */       RED = new Color("RED", 3, (Set<MobEffect>)ImmutableSet.of(MobEffects.f_19606_, MobEffects.f_19605_, MobEffects.f_19618_), block -> block.m_204336_(WitherStormModBlockTags.RED_SUPPORT_BASE), 240, 39, 7);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Color(Set<MobEffect> effects, Predicate<BlockState> block, int r, int g, int b) {
/* 376 */       this.validEffects = effects;
/* 377 */       this.block = block;
/* 378 */       this.r = r;
/* 379 */       this.g = g;
/* 380 */       this.b = b;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isValidBaseBlock(BlockState block) {
/* 385 */       return this.block.test(block);
/*     */     }
/*     */ 
/*     */     
/*     */     public int getRed() {
/* 390 */       return this.r;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getGreen() {
/* 395 */       return this.g;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getBlue() {
/* 400 */       return this.b;
/*     */     }
/*     */ 
/*     */     
/*     */     public Set<MobEffect> getValidEffects() {
/* 405 */       return this.validEffects;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\blockentity\AbstractSuperBeaconBlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
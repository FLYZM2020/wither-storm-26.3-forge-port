/*     */ package nonamecrackers2.witherstormmod.common.entity;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.ClientGamePacketListener;
/*     */ import net.minecraft.network.syncher.EntityDataAccessor;
/*     */ import net.minecraft.network.syncher.EntityDataSerializers;
/*     */ import net.minecraft.network.syncher.SynchedEntityData;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.item.PrimedTnt;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import net.minecraftforge.network.NetworkHooks;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModDamageTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class SuperTNTEntity
/*     */   extends PrimedTnt {
/*  26 */   private static final EntityDataAccessor<Integer> START_FUSE = SynchedEntityData.defineId(SuperTNTEntity.class, EntityDataSerializers.INT);
/*     */   @Nullable
/*     */   private LivingEntity owner;
/*     */   private int startLife;
/*     */   
/*     */   public SuperTNTEntity(EntityType<? extends SuperTNTEntity> type, Level world) {
/*  32 */     super(type, world);
/*  33 */     setFuse(320);
/*     */   }
/*     */ 
/*     */   
/*     */   public SuperTNTEntity(Level world, double x, double y, double z, @Nullable LivingEntity owner) {
/*  38 */     this((EntityType<? extends SuperTNTEntity>)WitherStormModEntityTypes.SUPER_TNT.get(), world);
/*  39 */     setPos(x, y, z);
/*  40 */     double d0 = world.random.nextDouble() * 6.2831854820251465D;
/*  41 */     setDeltaMovement(-Math.sin(d0) * 0.02D, 0.2D, -Math.cos(d0) * 0.02D);
/*  42 */     setFuse(320);
/*  43 */     this.xo = x;
/*  44 */     this.yo = y;
/*  45 */     this.zo = z;
/*  46 */     this.owner = owner;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void defineSynchedData() {
/*  52 */     super.defineSynchedData();
/*  53 */     this.entityData.define(START_FUSE, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readAdditionalSaveData(@NotNull CompoundTag compound) {
/*  59 */     super.readAdditionalSaveData(compound);
/*  60 */     if (compound.contains("StartFuse")) {
/*  61 */       setStartFuse(compound.getInt("StartFuse"));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addAdditionalSaveData(@NotNull CompoundTag compound) {
/*  67 */     super.addAdditionalSaveData(compound);
/*  68 */     compound.putInt("StartFuse", getStartLife());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getOwner() {
/*  75 */     return this.owner;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void explode() {
/*  81 */     level().explode((Entity)this, WitherStormModDamageTypes.superTntExplosion(level()), null, getX(), getY(0.0625D), getZ(), 32.0F, false, Level.ExplosionInteraction.TNT);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFuse(int fuse) {
/*  87 */     super.setFuse(fuse);
/*  88 */     setStartFuse(fuse);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStartFuse(int fuse) {
/*  93 */     this.entityData.set(START_FUSE, Integer.valueOf(fuse));
/*  94 */     this.startLife = fuse;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStartLife() {
/*  99 */     return this.startLife;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getPickedResult(HitResult target) {
/* 105 */     return new ItemStack((ItemLike)WitherStormModItems.SUPER_TNT.get());
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Packet<ClientGamePacketListener> getAddEntityPacket() {
/* 111 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SuperTNTEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
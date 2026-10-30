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
/*  26 */   private static final EntityDataAccessor<Integer> START_FUSE = SynchedEntityData.m_135353_(SuperTNTEntity.class, EntityDataSerializers.f_135028_);
/*     */   @Nullable
/*     */   private LivingEntity owner;
/*     */   private int startLife;
/*     */   
/*     */   public SuperTNTEntity(EntityType<? extends SuperTNTEntity> type, Level world) {
/*  32 */     super(type, world);
/*  33 */     m_32085_(320);
/*     */   }
/*     */ 
/*     */   
/*     */   public SuperTNTEntity(Level world, double x, double y, double z, @Nullable LivingEntity owner) {
/*  38 */     this((EntityType<? extends SuperTNTEntity>)WitherStormModEntityTypes.SUPER_TNT.get(), world);
/*  39 */     m_6034_(x, y, z);
/*  40 */     double d0 = world.f_46441_.m_188500_() * 6.2831854820251465D;
/*  41 */     m_20334_(-Math.sin(d0) * 0.02D, 0.2D, -Math.cos(d0) * 0.02D);
/*  42 */     m_32085_(320);
/*  43 */     this.f_19854_ = x;
/*  44 */     this.f_19855_ = y;
/*  45 */     this.f_19856_ = z;
/*  46 */     this.owner = owner;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8097_() {
/*  52 */     super.m_8097_();
/*  53 */     this.f_19804_.m_135372_(START_FUSE, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_7378_(@NotNull CompoundTag compound) {
/*  59 */     super.m_7378_(compound);
/*  60 */     if (compound.m_128441_("StartFuse")) {
/*  61 */       setStartFuse(compound.m_128451_("StartFuse"));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_7380_(@NotNull CompoundTag compound) {
/*  67 */     super.m_7380_(compound);
/*  68 */     compound.m_128405_("StartFuse", getStartLife());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity m_19749_() {
/*  75 */     return this.owner;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_32103_() {
/*  81 */     m_9236_().m_254877_((Entity)this, WitherStormModDamageTypes.superTntExplosion(m_9236_()), null, m_20185_(), m_20227_(0.0625D), m_20189_(), 32.0F, false, Level.ExplosionInteraction.TNT);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_32085_(int fuse) {
/*  87 */     super.m_32085_(fuse);
/*  88 */     setStartFuse(fuse);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStartFuse(int fuse) {
/*  93 */     this.f_19804_.m_135381_(START_FUSE, Integer.valueOf(fuse));
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
/*     */   public Packet<ClientGamePacketListener> m_5654_() {
/* 111 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SuperTNTEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
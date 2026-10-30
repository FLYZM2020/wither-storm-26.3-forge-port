/*     */ package nonamecrackers2.witherstormmod.common.blockentity;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.util.IFormidibomb;
/*     */ 
/*     */ public class FormidibombBlockEntity extends BlockEntity implements IFormidibomb {
/*  24 */   private int fuse = 1200; @Nullable
/*  25 */   private LivingEntity owner; private int startFuse = this.fuse;
/*     */ 
/*     */   
/*     */   public FormidibombBlockEntity(BlockPos pos, BlockState state) {
/*  29 */     super((BlockEntityType)WitherStormModBlockEntityTypes.FORMIDIBOMB.get(), pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_183515_(CompoundTag tag) {
/*  35 */     super.m_183515_(tag);
/*  36 */     tag.m_128405_("Fuse", getFuseLife());
/*  37 */     tag.m_128405_("StartFuse", getStartFuse());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_142466_(CompoundTag compound) {
/*  43 */     super.m_142466_(compound);
/*  44 */     if (compound.m_128441_("Fuse"))
/*  45 */       setLifeFuse(compound.m_128451_("Fuse")); 
/*  46 */     if (compound.m_128441_("StartFuse")) {
/*  47 */       setStartFuse(compound.m_128451_("StartFuse"));
/*     */     }
/*     */   }
/*     */   
/*     */   public static void tick(Level level, BlockPos pos, BlockState state, FormidibombBlockEntity blockEntity) {
/*  52 */     if (blockEntity.getStartFuse() > 0) {
/*     */       
/*  54 */       if (((Boolean)WitherStormModConfig.SERVER.formidibombFuseEnabled.get()).booleanValue()) {
/*  55 */         blockEntity.fuse--;
/*     */       }
/*  57 */       if (blockEntity.getFuseLife() <= 0) {
/*     */         
/*  59 */         level.m_7731_(blockEntity.m_58899_(), Blocks.f_50016_.m_49966_(), 3);
/*  60 */         level.m_46747_(blockEntity.m_58899_());
/*     */       }
/*  62 */       else if (blockEntity.getFuseLife() <= blockEntity.getStartFuse() / 4) {
/*     */         
/*  64 */         FormidibombEntity entity = new FormidibombEntity(level, blockEntity.m_58899_().m_123341_() + 0.5D, blockEntity.m_58899_().m_123342_(), blockEntity.m_58899_().m_123343_() + 0.5D, blockEntity.getFormidibombOwner(), blockEntity, blockEntity.m_58900_());
/*  65 */         if (level.m_7967_((Entity)entity)) {
/*     */           
/*  67 */           level.m_7731_(blockEntity.m_58899_(), Blocks.f_50016_.m_49966_(), 3);
/*  68 */           level.m_46747_(blockEntity.m_58899_());
/*  69 */           level.m_6263_(null, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), SoundEvents.f_12512_, SoundSource.BLOCKS, 1.0F, 1.0F);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStillAlive() {
/*  78 */     return !m_58901_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ClientboundBlockEntityDataPacket getUpdatePacket() {
/*  84 */     return ClientboundBlockEntityDataPacket.m_195640_(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundTag m_5995_() {
/*  90 */     return m_187482_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFuseLife() {
/*  96 */     return this.fuse;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLifeFuse(int fuse) {
/* 102 */     this.fuse = fuse;
/* 103 */     setStartFuse(fuse);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStartFuse() {
/* 109 */     return this.startFuse;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartFuse(int fuse) {
/* 115 */     this.startFuse = fuse;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getFormidibombOwner() {
/* 121 */     return this.owner;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormidibombOwner(@Nullable LivingEntity entity) {
/* 127 */     this.owner = entity;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getPosition() {
/* 133 */     return new Vec3(m_58899_().m_123341_(), m_58899_().m_123342_(), m_58899_().m_123343_());
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\blockentity\FormidibombBlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
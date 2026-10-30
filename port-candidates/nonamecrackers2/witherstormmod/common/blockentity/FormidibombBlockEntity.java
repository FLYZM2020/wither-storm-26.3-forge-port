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
/*     */   protected void saveAdditional(CompoundTag tag) {
/*  35 */     super.saveAdditional(tag);
/*  36 */     tag.putInt("Fuse", getFuseLife());
/*  37 */     tag.putInt("StartFuse", getStartFuse());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void load(CompoundTag compound) {
/*  43 */     super.load(compound);
/*  44 */     if (compound.contains("Fuse"))
/*  45 */       setLifeFuse(compound.getInt("Fuse")); 
/*  46 */     if (compound.contains("StartFuse")) {
/*  47 */       setStartFuse(compound.getInt("StartFuse"));
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
/*  59 */         level.setBlock(blockEntity.getBlockPos(), Blocks.AIR.defaultBlockState(), 3);
/*  60 */         level.removeBlockEntity(blockEntity.getBlockPos());
/*     */       }
/*  62 */       else if (blockEntity.getFuseLife() <= blockEntity.getStartFuse() / 4) {
/*     */         
/*  64 */         FormidibombEntity entity = new FormidibombEntity(level, blockEntity.getBlockPos().getX() + 0.5D, blockEntity.getBlockPos().getY(), blockEntity.getBlockPos().getZ() + 0.5D, blockEntity.getFormidibombOwner(), blockEntity, blockEntity.getBlockState());
/*  65 */         if (level.addFreshEntity((Entity)entity)) {
/*     */           
/*  67 */           level.setBlock(blockEntity.getBlockPos(), Blocks.AIR.defaultBlockState(), 3);
/*  68 */           level.removeBlockEntity(blockEntity.getBlockPos());
/*  69 */           level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStillAlive() {
/*  78 */     return !isRemoved();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ClientboundBlockEntityDataPacket getUpdatePacket() {
/*  84 */     return ClientboundBlockEntityDataPacket.create(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundTag getUpdateTag() {
/*  90 */     return saveWithoutMetadata();
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
/* 133 */     return new Vec3(getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ());
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\blockentity\FormidibombBlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
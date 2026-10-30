/*     */ package nonamecrackers2.witherstormmod.common.entity.goal;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.world.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import nonamecrackers2.witherstormmod.common.entity.SickenedBee;
/*     */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
/*     */ import nonamecrackers2.witherstormmod.common.world.tainting.WorldTainting;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SickenedBeeConversionGoal
/*     */   extends Goal
/*     */ {
/*     */   private static final int COOLDOWN = 200;
/*     */   private final SickenedBee bee;
/*     */   private BlockPos targetPos;
/*     */   private int conversionTimer;
/*     */   private int conversionCooldown;
/*     */   
/*     */   public SickenedBeeConversionGoal(SickenedBee bee) {
/*  24 */     this.bee = bee;
/*  25 */     setFlags(EnumSet.of(Goal.Flag.MOVE));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canUse() {
/*  31 */     return (findConvertibleBlock() && this.conversionCooldown <= 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canContinueToUse() {
/*  37 */     return (this.targetPos != null && this.bee.distanceToSqr(this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ()) < 6.0D && this.conversionTimer > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() {
/*  43 */     this.bee.getNavigation().moveTo(this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ(), 0.75D);
/*  44 */     this.conversionTimer = 20;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() {
/*  50 */     this.targetPos = null;
/*  51 */     this.conversionTimer = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  57 */     if (this.conversionCooldown > 0)
/*     */     {
/*  59 */       this.conversionCooldown--;
/*     */     }
/*     */     
/*  62 */     if (this.targetPos != null) {
/*     */       
/*  64 */       BlockState targetBlockState = this.bee.level().getBlockState(this.targetPos);
/*  65 */       if (targetBlockState.is(WitherStormModBlockTags.SICKENED_BEE_CAN_CONVERT) && this.bee.distanceToSqr(this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ()) <= 6.0D) {
/*     */         
/*  67 */         this.conversionTimer--;
/*  68 */         if (this.conversionTimer <= 0)
/*     */         {
/*  70 */           taintBlock();
/*  71 */           stop();
/*  72 */           this.conversionCooldown = 200;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/*  77 */         stop();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean findConvertibleBlock() {
/*  86 */     Level level = this.bee.level();
/*  87 */     BlockPos blockPos = this.bee.blockPosition();
/*  88 */     for (int x = -6; x <= 6; x++) {
/*     */       
/*  90 */       for (int y = -6; y <= 6; y++) {
/*     */         
/*  92 */         for (int z = -6; z <= 6; z++) {
/*     */           
/*  94 */           BlockPos pos = blockPos.offset(x, y, z);
/*  95 */           BlockState state = level.getBlockState(pos);
/*  96 */           if (state.is(WitherStormModBlockTags.SICKENED_BEE_CAN_CONVERT)) {
/*     */             
/*  98 */             this.targetPos = pos;
/*  99 */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 104 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void taintBlock() {
/* 109 */     if (this.targetPos != null)
/* 110 */       WorldTainting.getInstance().convertBlock(this.targetPos, this.bee.level()); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\goal\SickenedBeeConversionGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
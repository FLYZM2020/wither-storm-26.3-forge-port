/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.ai.goal.SwellGoal;
/*    */ import net.minecraft.world.entity.monster.Creeper;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({SwellGoal.class})
/*    */ public abstract class MixinSwellGoal
/*    */ {
/*    */   @Final
/*    */   @Shadow
/*    */   private Creeper creeper;
/*    */   @Shadow
/*    */   @Nullable
/*    */   private LivingEntity target;
/*    */   
/*    */   @Inject(method = {"canUse"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Creeper;getTarget()Lnet/minecraft/world/entity/LivingEntity;", shift = At.Shift.BY, by = 2)}, cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
/*    */   public void canUseInvoke(CallbackInfoReturnable<Boolean> ci, LivingEntity livingentity) {
/* 42 */     Vec3 headPos = getHeadPosOfTractorBeamCreeperIsIn(livingentity);
/* 43 */     if (headPos != null && Math.sqrt(this.creeper.distanceToSqr(headPos)) < 12.0D && this.creeper.getRandom().nextInt(3) == 0) {
/* 44 */       ci.setReturnValue(Boolean.valueOf(true));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"tick"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void tickInvoke(CallbackInfo ci) {
/* 54 */     Vec3 headPos = getHeadPosOfTractorBeamCreeperIsIn(this.target);
/* 55 */     if (headPos != null) {
/*    */       
/* 57 */       if (this.creeper.getEyePosition().distanceTo(headPos) > 16.0D) {
/* 58 */         this.creeper.setSwellDir(-1);
/*    */       } else {
/* 60 */         this.creeper.setSwellDir(1);
/* 61 */       }  ci.cancel();
/*    */     } 
/*    */   }
/*    */   
/*    */   @Unique
/*    */   @Nullable
/*    */   private Vec3 getHeadPosOfTractorBeamCreeperIsIn(@Nullable LivingEntity target) {
/* 68 */     if (target instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)target;
/*    */       
/* 70 */       Pair<Boolean, Integer> pair = TractorBeamHelper.isInsideTractorBeam((Entity)this.creeper, (LivingEntity)storm, 4.0D);
/* 71 */       if (((Boolean)pair.getFirst()).booleanValue())
/* 72 */         return storm.getHeadPos(((Integer)pair.getSecond()).intValue());  }
/*    */     
/* 74 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinSwellGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
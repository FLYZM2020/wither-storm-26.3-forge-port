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
/*    */   private Creeper f_25916_;
/*    */   @Shadow
/*    */   @Nullable
/*    */   private LivingEntity f_25917_;
/*    */   
/*    */   @Inject(method = {"canUse"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Creeper;getTarget()Lnet/minecraft/world/entity/LivingEntity;", shift = At.Shift.BY, by = 2)}, cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
/*    */   public void canUseInvoke(CallbackInfoReturnable<Boolean> ci, LivingEntity livingentity) {
/* 42 */     Vec3 headPos = getHeadPosOfTractorBeamCreeperIsIn(livingentity);
/* 43 */     if (headPos != null && Math.sqrt(this.f_25916_.m_20238_(headPos)) < 12.0D && this.f_25916_.m_217043_().m_188503_(3) == 0) {
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
/* 54 */     Vec3 headPos = getHeadPosOfTractorBeamCreeperIsIn(this.f_25917_);
/* 55 */     if (headPos != null) {
/*    */       
/* 57 */       if (this.f_25916_.m_146892_().m_82554_(headPos) > 16.0D) {
/* 58 */         this.f_25916_.m_32283_(-1);
/*    */       } else {
/* 60 */         this.f_25916_.m_32283_(1);
/* 61 */       }  ci.cancel();
/*    */     } 
/*    */   }
/*    */   
/*    */   @Unique
/*    */   @Nullable
/*    */   private Vec3 getHeadPosOfTractorBeamCreeperIsIn(@Nullable LivingEntity target) {
/* 68 */     if (target instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)target;
/*    */       
/* 70 */       Pair<Boolean, Integer> pair = TractorBeamHelper.isInsideTractorBeam((Entity)this.f_25916_, (LivingEntity)storm, 4.0D);
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
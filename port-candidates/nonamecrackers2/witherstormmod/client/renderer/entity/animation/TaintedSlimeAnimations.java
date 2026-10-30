/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.animation;
/*    */ 
/*    */ import net.minecraft.client.animation.AnimationChannel;
/*    */ import net.minecraft.client.animation.AnimationDefinition;
/*    */ import net.minecraft.client.animation.Keyframe;
/*    */ import net.minecraft.client.animation.KeyframeAnimations;
/*    */ 
/*    */ public class TaintedSlimeAnimations
/*    */ {
/* 10 */   public static final AnimationDefinition MODEL_IDLE = AnimationDefinition.Builder.withLength(8.125F).looping()
/* 11 */     .addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe[] {
/*    */           
/* 13 */           new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(2.0F, 
/*    */             
/* 15 */             KeyframeAnimations.degreeVec(15.0F, 90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(4.0F, 
/*    */             
/* 17 */             KeyframeAnimations.degreeVec(30.0F, 180.0F, 30.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(6.0F, 
/*    */             
/* 19 */             KeyframeAnimations.degreeVec(0.0F, 270.0F, 15.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(8.0F, 
/*    */             
/* 21 */             KeyframeAnimations.degreeVec(0.0F, 360.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
/*    */         
/* 23 */         })).addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe[] {
/*    */           
/* 25 */           new Keyframe(2.0F, KeyframeAnimations.scaleVec(0.949999988079071D, 0.949999988079071D, 0.949999988079071D), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(4.0F, 
/*    */             
/* 27 */             KeyframeAnimations.scaleVec(0.8500000238418579D, 0.8500000238418579D, 0.8500000238418579D), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(6.0F, 
/*    */             
/* 29 */             KeyframeAnimations.scaleVec(0.949999988079071D, 0.949999988079071D, 0.949999988079071D), AnimationChannel.Interpolations.CATMULLROM)
/* 30 */         })).build();
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\animation\TaintedSlimeAnimations.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
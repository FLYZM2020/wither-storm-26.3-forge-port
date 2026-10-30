/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.LivingEntityRenderer;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({LivingEntityRenderer.class})
/*    */ public class MixinLivingEntityRenderer
/*    */ {
/*    */   @Inject(method = {"isEntityUpsideDown"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private static void isEntityUpsideDownHead(LivingEntity entity, CallbackInfoReturnable<Boolean> ci) {
/* 22 */     if (entity instanceof nonamecrackers2.witherstormmod.common.entity.WitherStormEntity)
/* 23 */       ci.setReturnValue(Boolean.valueOf(false)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinLivingEntityRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
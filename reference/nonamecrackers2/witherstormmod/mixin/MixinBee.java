/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.world.entity.animal.Animal;
/*    */ import net.minecraft.world.entity.animal.Bee;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedBee;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ 
/*    */ @Mixin({Bee.class})
/*    */ public abstract class MixinBee
/*    */   extends Animal
/*    */ {
/*    */   private MixinBee() {
/* 18 */     super(null, null);
/* 19 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   
/*    */   @Inject(method = {"isFlowerValid"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void witherstormmod$checkIfFlowerValidForSickenedBee_isFlowerValid(BlockPos pos, CallbackInfoReturnable<Boolean> ci) {
/* 25 */     if ((Bee)this instanceof SickenedBee)
/* 26 */       ci.setReturnValue(Boolean.valueOf(SickenedBee.TAINTABLE.test(m_9236_().m_8055_(pos)))); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinBee.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
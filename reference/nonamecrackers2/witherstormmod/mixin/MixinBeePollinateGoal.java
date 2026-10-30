/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.world.entity.animal.Bee;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedBee;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin(targets = {"net.minecraft.world.entity.animal.Bee$BeePollinateGoal"})
/*    */ public abstract class MixinBeePollinateGoal
/*    */ {
/*    */   @Unique
/*    */   @Final
/*    */   Bee this$0;
/*    */   
/*    */   @Inject(method = {"findNearbyFlower"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void witherstormmod$findSickenedBeeTaintableBlock_findNearbyFlower(CallbackInfoReturnable<Optional<BlockPos>> ci) {
/* 28 */     if (this.this$0 instanceof SickenedBee)
/* 29 */       ci.setReturnValue(m_28075_(SickenedBee.TAINTABLE, 5.0D)); 
/*    */   }
/*    */   
/*    */   @Shadow
/*    */   protected abstract Optional<BlockPos> m_28075_(Predicate<BlockState> paramPredicate, double paramDouble);
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinBeePollinateGoal.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.Redirect;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({Player.class})
/*    */ public class MixinPlayer
/*    */ {
/*    */   @Inject(method = {"wantsToStopRiding"}, at = {@At("HEAD")}, cancellable = true)
/*    */   protected void wantsToStopRiding(CallbackInfoReturnable<Boolean> callback) {
/* 26 */     if (((Player)this).m_20202_() instanceof nonamecrackers2.witherstormmod.common.entity.TentacleEntity && ((Boolean)WitherStormModConfig.COMMON.playerCannotDismountTentacles.get()).booleanValue()) {
/* 27 */       callback.setReturnValue(Boolean.valueOf(false));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Redirect(method = {"isScoping"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
/*    */   public boolean redirectIsIsScoping(ItemStack stack, Item item) {
/* 36 */     return (stack.m_150930_(item) || stack.m_150930_((Item)WitherStormModItems.PHASOMETER.get()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinPlayer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.Gui;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import nonamecrackers2.witherstormmod.client.util.PhasometerRenderHelper;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyArg;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ @Mixin({Gui.class})
/*    */ public class MixinGui
/*    */ {
/*    */   @Unique
/* 25 */   private static final ResourceLocation PHASOMETER_SCOPE_TEXTURE = new ResourceLocation("witherstormmod", "textures/misc/phasometer_scope.png");
/*    */   
/*    */   @Final
/*    */   @Shadow
/*    */   protected Minecraft f_92986_;
/*    */   @Shadow
/*    */   protected int f_92977_;
/*    */   @Shadow
/*    */   protected int f_92978_;
/*    */   @Shadow
/*    */   protected int f_92989_;
/*    */   @Unique
/* 37 */   private String dotDotDot = "";
/*    */ 
/*    */ 
/*    */   
/*    */   @ModifyArg(method = {"renderSpyglassOverlay"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIFFIIII)V"))
/*    */   public ResourceLocation renderSpyglassOverlayInvoke(ResourceLocation original) {
/* 43 */     if (this.f_92986_.f_91074_.m_21211_().m_150930_((Item)WitherStormModItems.PHASOMETER.get()))
/* 44 */       return PHASOMETER_SCOPE_TEXTURE; 
/* 45 */     return original;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"renderSpyglassOverlay"}, at = {@At("TAIL")})
/*    */   public void renderSpyglassOverlayTail(GuiGraphics stack, float partialTicks, CallbackInfo ci) {
/* 54 */     ItemStack item = this.f_92986_.f_91074_.m_21211_();
/* 55 */     if (item.m_150930_((Item)WitherStormModItems.PHASOMETER.get())) {
/* 56 */       PhasometerRenderHelper.renderPhasometerOverlay(item, stack, partialTicks, this.f_92977_, this.f_92978_, this.dotDotDot);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"tick()V"}, at = {@At("TAIL")})
/*    */   public void tickTail(CallbackInfo ci) {
/* 65 */     if (this.f_92989_ % 5 == 0)
/*    */     {
/* 67 */       if (this.dotDotDot.length() >= 3) {
/* 68 */         this.dotDotDot = "";
/*    */       } else {
/* 70 */         this.dotDotDot += ".";
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinGui.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
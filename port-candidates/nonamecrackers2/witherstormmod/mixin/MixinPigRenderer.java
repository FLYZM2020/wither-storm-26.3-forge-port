/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.PigRenderer;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.world.entity.animal.pig.Pig;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({PigRenderer.class})
/*    */ public class MixinPigRenderer
/*    */ {
/*    */   @Unique
/* 18 */   private static final Identifier REUBEN_TEXTURE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/misc/reuben.png");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"getTextureLocation(Lnet/minecraft/world/entity/animal/Pig;)Lnet/minecraft/resources/ResourceLocation;"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void getTextureLocationHead(Pig pig, CallbackInfoReturnable<Identifier> ci) {
/* 27 */     if (pig.hasCustomName() && pig.getName().getString().equals("reuben"))
/* 28 */       ci.setReturnValue(REUBEN_TEXTURE); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinPigRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.BlueFlamingWitherSkullEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.FlamingWitherSkullEntity;
/*    */ 
/*    */ public class BlueFlamingWitherSkullRenderer extends FlamingWitherSkullRenderer<BlueFlamingWitherSkullEntity> {
/* 10 */   private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/flaming_wither_skull/blue_flaming_wither_skull.png");
/* 11 */   private static final ResourceLocation EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/flaming_wither_skull/blue_flaming_wither_skull_emissive.png");
/*    */ 
/*    */   
/*    */   public BlueFlamingWitherSkullRenderer(EntityRendererProvider.Context context) {
/* 15 */     super(context);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(BlueFlamingWitherSkullEntity entity) {
/* 21 */     return TEXTURE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getEmissiveTextureLocation(BlueFlamingWitherSkullEntity entity) {
/* 27 */     return EMISSIVE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\BlueFlamingWitherSkullRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
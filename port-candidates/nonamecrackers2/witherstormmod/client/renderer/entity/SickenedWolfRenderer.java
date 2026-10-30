/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import net.minecraft.client.model.WolfModel;
/*    */ import net.minecraft.client.renderer.rendertype.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.animal.wolf.Wolf;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherSickened;
/*    */ 
/*    */ public class SickenedWolfRenderer extends WolfRenderer {
/* 14 */   public static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_wolf.png");
/* 15 */   public static final Identifier EMISSIVE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_wolf_emissive.png");
/*    */ 
/*    */   
/*    */   public SickenedWolfRenderer(EntityRendererProvider.Context context) {
/* 19 */     super(context);
/* 20 */     addLayer((RenderLayer)new EyesLayer<Wolf, WolfModel<Wolf>>((RenderLayerParent)this)
/*    */         {
/*    */           
/*    */           public RenderType renderType()
/*    */           {
/* 25 */             return RenderType.eyes(SickenedWolfRenderer.EMISSIVE);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Identifier getTextureLocation(Wolf wolf) {
/* 33 */     return TEXTURE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean isShaking(Wolf entity) {
/* 39 */     return (super.isShaking((LivingEntity)entity) || ((WitherSickened)entity).isConverting());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedWolfRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
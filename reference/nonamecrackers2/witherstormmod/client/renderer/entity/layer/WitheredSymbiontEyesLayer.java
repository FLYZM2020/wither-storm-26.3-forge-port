/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.layer;
/*    */ 
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.WitheredSymbiontModel;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*    */ 
/*    */ public class WitheredSymbiontEyesLayer
/*    */   extends EyesLayer<WitheredSymbiontEntity, WitheredSymbiontModel<WitheredSymbiontEntity>>
/*    */ {
/* 13 */   private static final RenderType EYES = RenderType.m_110488_(new ResourceLocation("witherstormmod", "textures/entity/withered_symbiont/withered_symbiont_emissive.png"));
/*    */ 
/*    */   
/*    */   public WitheredSymbiontEyesLayer(RenderLayerParent<WitheredSymbiontEntity, WitheredSymbiontModel<WitheredSymbiontEntity>> renderer) {
/* 17 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RenderType m_5708_() {
/* 23 */     return EYES;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\layer\WitheredSymbiontEyesLayer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
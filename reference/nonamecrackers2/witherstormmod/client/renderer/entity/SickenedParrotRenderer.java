/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import net.minecraft.client.model.ParrotModel;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.animal.Parrot;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherSickened;
/*    */ 
/*    */ public class SickenedParrotRenderer extends ParrotRenderer {
/* 14 */   public static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_parrot.png");
/* 15 */   public static final ResourceLocation EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_parrot_emissive.png");
/*    */ 
/*    */   
/*    */   public SickenedParrotRenderer(EntityRendererProvider.Context context) {
/* 19 */     super(context);
/* 20 */     m_115326_((RenderLayer)new EyesLayer<Parrot, ParrotModel>((RenderLayerParent)this)
/*    */         {
/*    */           
/*    */           public RenderType m_5708_()
/*    */           {
/* 25 */             return RenderType.m_110488_(SickenedParrotRenderer.EMISSIVE);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation m_5478_(Parrot parrot) {
/* 33 */     return TEXTURE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean isShaking(Parrot entity) {
/* 39 */     return (super.m_5936_((LivingEntity)entity) || ((WitherSickened)entity).isConverting());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedParrotRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
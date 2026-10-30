/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.model.EntityModel;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.TaintedSlimeModel;
/*    */ import nonamecrackers2.witherstormmod.common.entity.TaintedSlime;
/*    */ 
/*    */ public class TaintedSlimeRenderer extends MobRenderer<TaintedSlime, TaintedSlimeModel<TaintedSlime>> {
/* 13 */   private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/tainted_slime/tainted_slime.png");
/*    */ 
/*    */   
/*    */   public TaintedSlimeRenderer(EntityRendererProvider.Context context) {
/* 17 */     super(context, (EntityModel)new TaintedSlimeModel(context.m_174023_(WitherStormModRenderers.TAINTED_SLIME)), 0.25F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(TaintedSlime slime) {
/* 23 */     return TEXTURE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\TaintedSlimeRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
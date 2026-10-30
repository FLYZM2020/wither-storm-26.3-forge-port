/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import net.minecraft.client.model.IllagerModel;
/*    */ import net.minecraft.client.renderer.ItemInHandRenderer;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedVindicator;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class null
/*    */   extends ItemInHandLayer<SickenedVindicator, IllagerModel<SickenedVindicator>>
/*    */ {
/*    */   null(RenderLayerParent<SickenedVindicator, IllagerModel<SickenedVindicator>> p_234846_, ItemInHandRenderer p_234847_) {
/* 27 */     super(p_234846_, p_234847_);
/*    */   } public void render(@NotNull PoseStack stack, @NotNull MultiBufferSource source, int i, @NotNull SickenedVindicator sickenedVindicator, float f1, float f2, float f3, float f4, float f5, float f6) {
/* 29 */     if (sickenedVindicator.m_5912_())
/* 30 */       super.m_6494_(stack, source, i, (LivingEntity)sickenedVindicator, f1, f2, f3, f4, f5, f6); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedVindicatorRenderer$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
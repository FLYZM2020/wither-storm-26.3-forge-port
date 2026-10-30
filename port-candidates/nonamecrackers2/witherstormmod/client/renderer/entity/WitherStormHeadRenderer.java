/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import java.awt.Color;
/*    */ import java.time.temporal.ChronoField;
/*    */ import net.minecraft.client.model.EntityModel;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.Mob;
/*    */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.layer.WitherStormHeadEyesLayer;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.WitherStormHeadModel;
/*    */ import nonamecrackers2.witherstormmod.client.resources.WitherStormResourceConfigManager;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormHeadEntity;
/*    */ 
/*    */ public class WitherStormHeadRenderer extends MobRenderer<WitherStormHeadEntity, WitherStormHeadModel> {
/* 23 */   public static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/wither_storm_head/wither_storm_head.png");
/* 24 */   public static final Identifier TEXTURE_HURT = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/wither_storm_head/wither_storm_head_hurt.png");
/* 25 */   public static final Identifier EMISSIVE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/wither_storm_head/wither_storm_head_emissive.png");
/* 26 */   public static final Identifier EMISSIVE_HURT = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/wither_storm_head/wither_storm_head_emissive_hurt.png");
/*    */ 
/*    */   
/*    */   public WitherStormHeadRenderer(EntityRendererProvider.Context context) {
/* 30 */     super(context, (EntityModel)new WitherStormHeadModel(context.bakeLayer(WitherStormModRenderers.WITHER_STORM_HEAD)), 3.5F);
/* 31 */     addLayer((RenderLayer)new WitherStormHeadEyesLayer((RenderLayerParent)this));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getBlockLightLevel(WitherStormHeadEntity entity, BlockPos pos) {
/* 37 */     return Math.max(0, (int)((100.0F - entity.getFadeAnimation()) / 4.0F - 10.0F));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(WitherStormHeadEntity entity, float p_225623_2_, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
/* 43 */     super.render((Mob)entity, p_225623_2_, partialTicks, stack, buffer, packedLight);
/*    */     
/* 45 */     if (((Boolean)WitherStormModConfig.CLIENT.renderTractorBeams.get()).booleanValue())
/*    */     {
/* 47 */       if (!entity.isPlayingDead() && !entity.isHurt() && ((WitherStormHeadModel)getModel()).getHead().shouldRenderTractorBeam((LivingEntity)entity, 0)) {
/*    */         
/* 49 */         int month = WitherStormMod.DATE.get(ChronoField.MONTH_OF_YEAR);
/* 50 */         int day = WitherStormMod.DATE.get(ChronoField.DAY_OF_MONTH);
/* 51 */         boolean flag = (month == 10 && day == 31);
/* 52 */         Color color = WitherStormResourceConfigManager.INSTANCE.getColorSetByPhase(4).tractorBeamColor();
/* 53 */         float r = flag ? 0.5294118F : (color.getRed() / 255.0F);
/* 54 */         float g = flag ? 0.32156864F : (color.getGreen() / 255.0F);
/* 55 */         float b = flag ? 0.10980392F : (color.getBlue() / 255.0F);
/* 56 */         ((WitherStormHeadModel)getModel()).getHead().renderTractorBeam((LivingEntity)entity, stack, buffer, packedLight, r, g, b, 0.5F, partialTicks, -1.0D, 1.0F);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Identifier getTextureLocation(WitherStormHeadEntity entity) {
/* 64 */     return entity.isHurt() ? TEXTURE_HURT : TEXTURE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void scale(WitherStormHeadEntity entity, PoseStack stack, float p_225620_3_) {
/* 70 */     stack.scale(2.0F, 2.0F, 2.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\WitherStormHeadRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
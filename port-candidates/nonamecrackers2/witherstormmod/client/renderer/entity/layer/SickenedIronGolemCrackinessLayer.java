/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.layer;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import java.util.Map;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.animal.golem.IronGolem;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.sickenedentity.SickenedIronGolemModel;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedIronGolem;
/*    */ 
/*    */ 
/*    */ public class SickenedIronGolemCrackinessLayer
/*    */   extends RenderLayer<SickenedIronGolem, SickenedIronGolemModel<SickenedIronGolem>>
/*    */ {
/* 20 */   private static final Map<IronGolem.Crackiness, Identifier> LAYERS = (Map<IronGolem.Crackiness, Identifier>)ImmutableMap.of(IronGolem.Crackiness.LOW, Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_iron_golem_crackiness_low.png"), IronGolem.Crackiness.MEDIUM, Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_iron_golem_crackiness_medium.png"), IronGolem.Crackiness.HIGH, Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_iron_golem_crackiness_high.png"));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SickenedIronGolemCrackinessLayer(RenderLayerParent<SickenedIronGolem, SickenedIronGolemModel<SickenedIronGolem>> parent) {
/* 28 */     super(parent);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(PoseStack stack, MultiBufferSource buffer, int p_117351_, SickenedIronGolem entity, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
/* 34 */     if (!entity.isInvisible()) {
/*    */       
/* 36 */       IronGolem.Crackiness irongolem$crackiness = entity.getCrackiness();
/* 37 */       if (irongolem$crackiness != IronGolem.Crackiness.NONE) {
/*    */         
/* 39 */         Identifier resourcelocation = LAYERS.get(irongolem$crackiness);
/* 40 */         renderColoredCutoutModel(getParentModel(), resourcelocation, stack, buffer, p_117351_, (LivingEntity)entity, 1.0F, 1.0F, 1.0F);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\layer\SickenedIronGolemCrackinessLayer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
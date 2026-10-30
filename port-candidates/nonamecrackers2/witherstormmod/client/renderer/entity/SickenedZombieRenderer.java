/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*    */ import net.minecraft.client.model.ZombieModel;
/*    */ import net.minecraft.client.renderer.rendertype.RenderType;
/*    */ import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*    */ import net.minecraft.client.renderer.entity.RenderLayerParent;
/*    */ import net.minecraft.client.renderer.entity.layers.EyesLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.RenderLayer;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.monster.zombie.Zombie;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedZombie;
/*    */ 
/*    */ public class SickenedZombieRenderer extends AbstractZombieRenderer<SickenedZombie, ZombieModel<SickenedZombie>> {
/* 16 */   private static final Identifier SICKENED_ZOMBIE_LOCATION = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_zombie.png");
/* 17 */   private static final Identifier SICKENED_ZOMBIE_EMISSIVE_LOCATION = Identifier.fromNamespaceAndPath("witherstormmod", "textures/entity/sickened/sickened_zombie_emissive.png");
/*    */ 
/*    */   
/*    */   public SickenedZombieRenderer(EntityRendererProvider.Context context) {
/* 21 */     super(context, new ZombieModel(context.bakeLayer(WitherStormModRenderers.SICKENED_ZOMBIE)), new ZombieModel(context.bakeLayer(WitherStormModRenderers.SICKENED_ZOMBIE_INNER_ARMOR)), new ZombieModel(context.bakeLayer(WitherStormModRenderers.SICKENED_ZOMBIE_OUTER_ARMOR)));
/* 22 */     addLayer((RenderLayer)new EyesLayer<SickenedZombie, ZombieModel<SickenedZombie>>((RenderLayerParent)this)
/*    */         {
/*    */           
/*    */           public RenderType renderType()
/*    */           {
/* 27 */             return RenderType.eyes(SickenedZombieRenderer.SICKENED_ZOMBIE_EMISSIVE_LOCATION);
/*    */           }
/*    */         });
/*    */   }
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Identifier getTextureLocation(Zombie zombie) {
/* 49 */     return SICKENED_ZOMBIE_LOCATION;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean isShaking(SickenedZombie entity) {
/* 55 */     return (super.isShaking((Zombie)entity) || entity.isConverting());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\SickenedZombieRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
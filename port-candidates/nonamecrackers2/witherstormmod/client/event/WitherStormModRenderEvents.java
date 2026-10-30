/*    */ package nonamecrackers2.witherstormmod.client.event;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.PoseStack;
/*    */ import java.util.function.BiConsumer;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.ClientLevel;
/*    */ import net.minecraft.client.renderer.FogRenderer;
/*    */ import net.minecraft.client.renderer.MultiBufferSource;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import net.minecraftforge.client.event.RenderLevelStageEvent;
/*    */ import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.AbstractWitherStormRenderer;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WitherStormModRenderEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void renderWitherStormPost(RenderLevelStageEvent event) {
/* 28 */     if (event.getStage().equals(RenderLevelStageEvent.Stage.AFTER_PARTICLES)) {
/*    */       
/* 30 */       Minecraft mc = Minecraft.getInstance();
/* 31 */       ClientLevel world = mc.level;
/* 32 */       PoseStack stack = event.getPoseStack();
/* 33 */       MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
/* 34 */       Vec3 pos = mc.gameRenderer.getMainCamera().getPosition();
/* 35 */       Vec3 negPos = new Vec3(-pos.x, -pos.y, -pos.z);
/* 36 */       float partialTicks = event.getPartialTick();
/* 37 */       EntityRenderDispatcher manager = mc.getEntityRenderDispatcher();
/* 38 */       boolean flag = (mc.level.effects().isFoggyAt(Mth.floor(pos.x), Mth.floor(pos.y)) || mc.gui.getBossOverlay().shouldCreateWorldFog());
/* 39 */       FogRenderer.setupColor(mc.gameRenderer.getMainCamera(), partialTicks, world, ((Integer)mc.options.renderDistance().get()).intValue(), mc.gameRenderer.getDarkenWorldAmount(partialTicks));
/* 40 */       FogRenderer.levelFogColor();
/* 41 */       if (((Integer)mc.options.renderDistance().get()).intValue() >= 4) {
/* 42 */         FogRenderer.setupFog(mc.gameRenderer.getMainCamera(), FogRenderer.FogMode.FOG_TERRAIN, Math.max(mc.gameRenderer.getRenderDistance() - 16.0F, 32.0F), flag, partialTicks);
/*    */       }
/* 44 */       renderStorms(manager, world, stack, partialTicks, negPos, (MultiBufferSource)buffer, (s, r) -> {
/*    */             if (((Boolean)WitherStormModConfig.CLIENT.renderDebrisRings.get()).booleanValue() && (!((Boolean)WitherStormModConfig.CLIENT.hideDebrisRingsUntilSplit.get()).booleanValue() || s.getPhase() > 5)) {
/*    */               r.renderDebrisRings(s, stack, (MultiBufferSource)buffer, partialTicks, manager.getPackedLightCoords((Entity)s, partialTicks));
/*    */             }
/*    */           });
/* 49 */       buffer.endBatch();
/* 50 */       FogRenderer.setupNoFog();
/* 51 */       renderStorms(manager, world, stack, partialTicks, negPos, (MultiBufferSource)buffer, (s, r) -> {
/*    */             if (((Boolean)WitherStormModConfig.CLIENT.renderTractorBeams.get()).booleanValue() && (!s.onGround() || !s.isPlayingDead())) {
/*    */               int packedLight = manager.getPackedLightCoords((Entity)s, partialTicks);
/*    */               
/*    */               r.prepareHeadAnimsForTractorBeams(s, partialTicks);
/*    */               
/*    */               r.renderTractorBeams(s, stack, (MultiBufferSource)buffer, partialTicks, packedLight);
/*    */             } 
/*    */           });
/* 60 */       buffer.endBatch();
/*    */       
/* 62 */       renderStorms(manager, world, stack, partialTicks, negPos, (MultiBufferSource)buffer, (s, r) -> {
/*    */             if (((Boolean)WitherStormModConfig.CLIENT.renderShine.get()).booleanValue() && s.shouldShine()) {
/*    */               AbstractWitherStormRenderer.renderShine(s, stack, partialTicks, mc.gameRenderer.getMainCamera(), (MultiBufferSource)buffer);
/*    */             }
/*    */           });
/*    */       
/* 68 */       buffer.endBatch();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private static <T extends WitherStormEntity, M extends nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.AbstractWitherStormModel<T>> void renderStorms(EntityRenderDispatcher manager, ClientLevel world, PoseStack stack, float partialTicks, Vec3 negPos, MultiBufferSource buffer, BiConsumer<T, AbstractWitherStormRenderer<T, M>> action) {
/* 74 */     for (Entity entity : world.entitiesForRendering()) {
/*    */       
/* 76 */       if (entity instanceof WitherStormEntity) {
/*    */ 
/*    */         
/* 79 */         WitherStormEntity witherStormEntity = (WitherStormEntity)entity;
/* 80 */         AbstractWitherStormRenderer<T, M> renderer = AbstractWitherStormRenderer.getRenderer(witherStormEntity, manager);
/* 81 */         if (renderer != null) {
/*    */           
/* 83 */           stack.pushPose();
/* 84 */           double x = Mth.lerp(partialTicks, witherStormEntity.xOld, witherStormEntity.getX());
/* 85 */           double y = Mth.lerp(partialTicks, witherStormEntity.yOld, witherStormEntity.getY());
/* 86 */           double z = Mth.lerp(partialTicks, witherStormEntity.zOld, witherStormEntity.getZ());
/* 87 */           stack.translate(negPos.x, negPos.y, negPos.z);
/* 88 */           stack.translate(x, y, z);
/* 89 */           renderer.updateModel(witherStormEntity);
/* 90 */           action.accept((T)witherStormEntity, renderer);
/* 91 */           stack.popPose();
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\event\WitherStormModRenderEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
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
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
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
/* 30 */       Minecraft mc = Minecraft.m_91087_();
/* 31 */       ClientLevel world = mc.f_91073_;
/* 32 */       PoseStack stack = event.getPoseStack();
/* 33 */       MultiBufferSource.BufferSource buffer = mc.m_91269_().m_110104_();
/* 34 */       Vec3 pos = mc.f_91063_.m_109153_().m_90583_();
/* 35 */       Vec3 negPos = new Vec3(-pos.f_82479_, -pos.f_82480_, -pos.f_82481_);
/* 36 */       float partialTicks = event.getPartialTick();
/* 37 */       EntityRenderDispatcher manager = mc.m_91290_();
/* 38 */       boolean flag = (mc.f_91073_.m_104583_().m_5781_(Mth.m_14107_(pos.f_82479_), Mth.m_14107_(pos.f_82480_)) || mc.f_91065_.m_93090_().m_93715_());
/* 39 */       FogRenderer.m_109018_(mc.f_91063_.m_109153_(), partialTicks, world, ((Integer)mc.f_91066_.m_231984_().m_231551_()).intValue(), mc.f_91063_.m_109131_(partialTicks));
/* 40 */       FogRenderer.m_109036_();
/* 41 */       if (((Integer)mc.f_91066_.m_231984_().m_231551_()).intValue() >= 4) {
/* 42 */         FogRenderer.m_234172_(mc.f_91063_.m_109153_(), FogRenderer.FogMode.FOG_TERRAIN, Math.max(mc.f_91063_.m_109152_() - 16.0F, 32.0F), flag, partialTicks);
/*    */       }
/* 44 */       renderStorms(manager, world, stack, partialTicks, negPos, (MultiBufferSource)buffer, (s, r) -> {
/*    */             if (((Boolean)WitherStormModConfig.CLIENT.renderDebrisRings.get()).booleanValue() && (!((Boolean)WitherStormModConfig.CLIENT.hideDebrisRingsUntilSplit.get()).booleanValue() || s.getPhase() > 5)) {
/*    */               r.renderDebrisRings(s, stack, (MultiBufferSource)buffer, partialTicks, manager.m_114394_((Entity)s, partialTicks));
/*    */             }
/*    */           });
/* 49 */       buffer.m_109911_();
/* 50 */       FogRenderer.m_109017_();
/* 51 */       renderStorms(manager, world, stack, partialTicks, negPos, (MultiBufferSource)buffer, (s, r) -> {
/*    */             if (((Boolean)WitherStormModConfig.CLIENT.renderTractorBeams.get()).booleanValue() && (!s.m_20096_() || !s.isPlayingDead())) {
/*    */               int packedLight = manager.m_114394_((Entity)s, partialTicks);
/*    */               
/*    */               r.prepareHeadAnimsForTractorBeams(s, partialTicks);
/*    */               
/*    */               r.renderTractorBeams(s, stack, (MultiBufferSource)buffer, partialTicks, packedLight);
/*    */             } 
/*    */           });
/* 60 */       buffer.m_109911_();
/*    */       
/* 62 */       renderStorms(manager, world, stack, partialTicks, negPos, (MultiBufferSource)buffer, (s, r) -> {
/*    */             if (((Boolean)WitherStormModConfig.CLIENT.renderShine.get()).booleanValue() && s.shouldShine()) {
/*    */               AbstractWitherStormRenderer.renderShine(s, stack, partialTicks, mc.f_91063_.m_109153_(), (MultiBufferSource)buffer);
/*    */             }
/*    */           });
/*    */       
/* 68 */       buffer.m_109911_();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private static <T extends WitherStormEntity, M extends nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.AbstractWitherStormModel<T>> void renderStorms(EntityRenderDispatcher manager, ClientLevel world, PoseStack stack, float partialTicks, Vec3 negPos, MultiBufferSource buffer, BiConsumer<T, AbstractWitherStormRenderer<T, M>> action) {
/* 74 */     for (Entity entity : world.m_104735_()) {
/*    */       
/* 76 */       if (entity instanceof WitherStormEntity) {
/*    */ 
/*    */         
/* 79 */         WitherStormEntity witherStormEntity = (WitherStormEntity)entity;
/* 80 */         AbstractWitherStormRenderer<T, M> renderer = AbstractWitherStormRenderer.getRenderer(witherStormEntity, manager);
/* 81 */         if (renderer != null) {
/*    */           
/* 83 */           stack.m_85836_();
/* 84 */           double x = Mth.m_14139_(partialTicks, witherStormEntity.f_19790_, witherStormEntity.m_20185_());
/* 85 */           double y = Mth.m_14139_(partialTicks, witherStormEntity.f_19791_, witherStormEntity.m_20186_());
/* 86 */           double z = Mth.m_14139_(partialTicks, witherStormEntity.f_19792_, witherStormEntity.m_20189_());
/* 87 */           stack.m_85837_(negPos.f_82479_, negPos.f_82480_, negPos.f_82481_);
/* 88 */           stack.m_85837_(x, y, z);
/* 89 */           renderer.updateModel(witherStormEntity);
/* 90 */           action.accept((T)witherStormEntity, renderer);
/* 91 */           stack.m_85849_();
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\event\WitherStormModRenderEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
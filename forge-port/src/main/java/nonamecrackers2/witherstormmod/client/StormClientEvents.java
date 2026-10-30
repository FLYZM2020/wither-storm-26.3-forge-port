package nonamecrackers2.witherstormmod.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import nonamecrackers2.witherstormmod.client.particle.CommandParticle;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.renderer.entity.TntRenderer;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.client.model.StormEarlyModel;
import nonamecrackers2.witherstormmod.client.model.StormDestroyerModel;
import nonamecrackers2.witherstormmod.client.renderer.StormEarlyRenderer;
import nonamecrackers2.witherstormmod.client.renderer.StormSegmentRenderer;
import nonamecrackers2.witherstormmod.client.renderer.SymbiontRenderer;
import nonamecrackers2.witherstormmod.client.model.SymbiontModel;

/** Early-stage client model registration; later storm stages are not ported. */
@Mod.EventBusSubscriber(modid = WitherStormMod.MOD_ID, value = Dist.CLIENT)
public final class StormClientEvents {
    private StormClientEvents() {}

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(WitherStormMod.COMMAND_PARTICLE.get(), CommandParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(StormEarlyRenderer.LAYER, StormEarlyModel::createLayer);
        event.registerLayerDefinition(StormEarlyRenderer.HUNCHBACK_LAYER, StormEarlyModel::createHunchbackLayer);
        event.registerLayerDefinition(StormEarlyRenderer.GROWING_LAYER, StormEarlyModel::createGrowingLayer);
        event.registerLayerDefinition(StormEarlyRenderer.PREGNANT_LAYER, StormEarlyModel::createPregnantLayer);
        event.registerLayerDefinition(StormEarlyRenderer.HUNCHBACK_ONE_QUARTER, StormEarlyModel::createHunchbackOneQuarterLayer);
        event.registerLayerDefinition(StormEarlyRenderer.HUNCHBACK_HALF, StormEarlyModel::createHunchbackHalfLayer);
        event.registerLayerDefinition(StormEarlyRenderer.GROWING_HALF, StormEarlyModel::createGrowingHalfLayer);
        event.registerLayerDefinition(StormEarlyRenderer.PREGNANT_ONE, StormEarlyModel::createPregnantOneLayer);
        event.registerLayerDefinition(StormEarlyRenderer.PREGNANT_TWO, StormEarlyModel::createPregnantTwoLayer);
        event.registerLayerDefinition(StormEarlyRenderer.DESTROYER_LAYER, StormDestroyerModel::createLayer);
        event.registerLayerDefinition(StormEarlyRenderer.INTERMEDIATE_DESTROYER_LAYER, StormDestroyerModel::createIntermediateLayer);
        event.registerLayerDefinition(StormEarlyRenderer.EVOLVED_DESTROYER_LAYER, StormDestroyerModel::createEvolvedLayer);
        event.registerLayerDefinition(StormEarlyRenderer.INTERMEDIATE_DEVOURER_LAYER, StormDestroyerModel::createIntermediateDevourerLayer);
        event.registerLayerDefinition(StormEarlyRenderer.DEVOURER_LAYER, StormDestroyerModel::createDevourerLayer);
        event.registerLayerDefinition(StormEarlyRenderer.DISMANTLED_LAYER, StormDestroyerModel::createDismantledLayer);
        event.registerLayerDefinition(StormEarlyRenderer.INTERMEDIATE_EVOLVED_DEVOURER_LAYER, StormDestroyerModel::createIntermediateEvolvedDevourerLayer);
        event.registerLayerDefinition(StormEarlyRenderer.EVOLVED_DEVOURER_LAYER, StormDestroyerModel::createEvolvedDevourerLayer);
        event.registerLayerDefinition(StormSegmentRenderer.LAYER, StormDestroyerModel::createSegmentLayer);
        event.registerLayerDefinition(SymbiontRenderer.LAYER, SymbiontModel::createLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(WitherStormMod.WITHER_STORM.get(), StormEarlyRenderer::new);
        event.registerEntityRenderer(WitherStormMod.WITHER_STORM_SEGMENT.get(), StormSegmentRenderer::new);
        event.registerEntityRenderer(WitherStormMod.FORMIDIBOMB.get(), TntRenderer::new);
        event.registerEntityRenderer(WitherStormMod.SUPER_TNT.get(), TntRenderer::new);
        event.registerEntityRenderer(WitherStormMod.WITHERED_SYMBIONT.get(), SymbiontRenderer::new);
    }
}

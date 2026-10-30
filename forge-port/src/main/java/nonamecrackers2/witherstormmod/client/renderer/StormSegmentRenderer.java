package nonamecrackers2.witherstormmod.client.renderer;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WitherBossRenderer;
import net.minecraft.client.renderer.entity.state.WitherRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.phys.AABB;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.client.model.StormDestroyerModel;
import nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity;

/** Base segment geometry; damage overlays remain unported. */
public final class StormSegmentRenderer extends WitherBossRenderer {
    public static final class SegmentState extends WitherRenderState { public boolean mirrored; }
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(WitherStormMod.id("wither_storm_segment"), "main");
    private static final Identifier TEXTURE = WitherStormMod.id("textures/entity/wither_storm/wither_storm.png");
    private static final Identifier INVULNERABLE = WitherStormMod.id("textures/entity/wither_storm/wither_storm_invulnerable.png");
    public StormSegmentRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new StormDestroyerModel(context.bakeLayer(LAYER));
        this.layers.clear();
    }
    @Override public WitherRenderState createRenderState() { return new SegmentState(); }
    @Override public void extractRenderState(WitherBoss entity, WitherRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        ((SegmentState) state).mirrored = entity instanceof WitherStormSegmentEntity segment && segment.getSegmentIndex() == 1;
    }
    @Override public Identifier getTextureLocation(WitherRenderState state) {
        int ticks = Mth.floor(state.invulnerableTicks);
        return ticks > 0 && (ticks > 80 || ticks / 5 % 2 != 1) ? INVULNERABLE : TEXTURE;
    }
    @Override protected AABB getBoundingBoxForCulling(WitherBoss entity, float partialTick) {
        return super.getBoundingBoxForCulling(entity, partialTick).inflate(48.0);
    }
}

package nonamecrackers2.witherstormmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.monster.wither.WitherBossModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WitherBossRenderer;
import net.minecraft.client.renderer.entity.state.WitherRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.client.model.StormEarlyModel;
import nonamecrackers2.witherstormmod.client.model.StormDestroyerModel;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.evolution.StormEvolutionRules;

/** Early-stage model and textures; later phases still use this placeholder. */
public final class StormEarlyRenderer extends WitherBossRenderer {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(WitherStormMod.id("wither_storm_early"), "main");
    public static final ModelLayerLocation HUNCHBACK_LAYER = new ModelLayerLocation(WitherStormMod.id("wither_storm_hunchback"), "main");
    public static final ModelLayerLocation GROWING_LAYER = new ModelLayerLocation(WitherStormMod.id("wither_storm_growing"), "main");
    public static final ModelLayerLocation PREGNANT_LAYER = new ModelLayerLocation(WitherStormMod.id("wither_storm_pregnant"), "main");
    public static final ModelLayerLocation HUNCHBACK_ONE_QUARTER = new ModelLayerLocation(WitherStormMod.id("wither_storm_1_1"), "main");
    public static final ModelLayerLocation HUNCHBACK_HALF = new ModelLayerLocation(WitherStormMod.id("wither_storm_1_2"), "main");
    public static final ModelLayerLocation GROWING_HALF = new ModelLayerLocation(WitherStormMod.id("wither_storm_2_1"), "main");
    public static final ModelLayerLocation PREGNANT_ONE = new ModelLayerLocation(WitherStormMod.id("wither_storm_3_1"), "main");
    public static final ModelLayerLocation PREGNANT_TWO = new ModelLayerLocation(WitherStormMod.id("wither_storm_3_2"), "main");
    public static final ModelLayerLocation DESTROYER_LAYER = new ModelLayerLocation(WitherStormMod.id("wither_storm_4"), "main");
    public static final ModelLayerLocation INTERMEDIATE_DESTROYER_LAYER = new ModelLayerLocation(WitherStormMod.id("wither_storm_4_5"), "main");
    public static final ModelLayerLocation EVOLVED_DESTROYER_LAYER = new ModelLayerLocation(WitherStormMod.id("wither_storm_5"), "main");
    public static final ModelLayerLocation INTERMEDIATE_DEVOURER_LAYER = new ModelLayerLocation(WitherStormMod.id("wither_storm_5_5"), "main");
    public static final ModelLayerLocation DEVOURER_LAYER = new ModelLayerLocation(WitherStormMod.id("wither_storm_devourer"), "main");
    public static final ModelLayerLocation DISMANTLED_LAYER = new ModelLayerLocation(WitherStormMod.id("wither_storm_6"), "main");
    public static final ModelLayerLocation INTERMEDIATE_EVOLVED_DEVOURER_LAYER = new ModelLayerLocation(WitherStormMod.id("wither_storm_6_5"), "main");
    public static final ModelLayerLocation EVOLVED_DEVOURER_LAYER = new ModelLayerLocation(WitherStormMod.id("wither_storm_7"), "main");
    private final WitherBossModel earlyModel;
    private final WitherBossModel hunchbackModel;
    private final WitherBossModel growingModel;
    private final WitherBossModel pregnantModel;
    private final WitherBossModel hunchbackOneQuarter, hunchbackHalf, growingHalf, pregnantOne, pregnantTwo;
    private final WitherBossModel destroyerModel;
    private final WitherBossModel intermediateDestroyerModel, evolvedDestroyerModel;
    private final WitherBossModel intermediateDevourerModel;
    private final WitherBossModel devourerModel, dismantledModel, intermediateEvolvedDevourerModel, evolvedDevourerModel;
    private static final Identifier TEXTURE = WitherStormMod.id("textures/entity/wither_storm/wither_storm.png");
    private static final Identifier INVULNERABLE = WitherStormMod.id("textures/entity/wither_storm/wither_storm_invulnerable.png");

    public StormEarlyRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.earlyModel = new StormEarlyModel(context.bakeLayer(LAYER));
        this.hunchbackModel = new StormEarlyModel(context.bakeLayer(HUNCHBACK_LAYER));
        this.growingModel = new StormEarlyModel(context.bakeLayer(GROWING_LAYER));
        this.pregnantModel = new StormEarlyModel(context.bakeLayer(PREGNANT_LAYER));
        this.hunchbackOneQuarter = new StormEarlyModel(context.bakeLayer(HUNCHBACK_ONE_QUARTER));
        this.hunchbackHalf = new StormEarlyModel(context.bakeLayer(HUNCHBACK_HALF));
        this.growingHalf = new StormEarlyModel(context.bakeLayer(GROWING_HALF));
        this.pregnantOne = new StormEarlyModel(context.bakeLayer(PREGNANT_ONE));
        this.pregnantTwo = new StormEarlyModel(context.bakeLayer(PREGNANT_TWO));
        this.destroyerModel = new StormDestroyerModel(context.bakeLayer(DESTROYER_LAYER));
        this.intermediateDestroyerModel = new StormDestroyerModel(context.bakeLayer(INTERMEDIATE_DESTROYER_LAYER));
        this.evolvedDestroyerModel = new StormDestroyerModel(context.bakeLayer(EVOLVED_DESTROYER_LAYER));
        this.intermediateDevourerModel = new StormDestroyerModel(context.bakeLayer(INTERMEDIATE_DEVOURER_LAYER));
        this.devourerModel = new StormDestroyerModel(context.bakeLayer(DEVOURER_LAYER));
        this.dismantledModel = new StormDestroyerModel(context.bakeLayer(DISMANTLED_LAYER));
        this.intermediateEvolvedDevourerModel = new StormDestroyerModel(context.bakeLayer(INTERMEDIATE_EVOLVED_DEVOURER_LAYER));
        this.evolvedDevourerModel = new StormDestroyerModel(context.bakeLayer(EVOLVED_DEVOURER_LAYER));
        this.model = earlyModel;
        this.layers.clear(); // The original shield layer has not yet been ported.
    }

    private static final class StormState extends WitherRenderState {
        int phase;
        int consumed;
        float evolutionSpeed;
    }

    @Override
    public WitherRenderState createRenderState() {
        return new StormState();
    }

    @Override
    public void extractRenderState(WitherBoss entity, WitherRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        if (entity instanceof WitherStormEntity storm) {
            StormState data = (StormState) state;
            data.phase = storm.getPhase();
            data.consumed = storm.getConsumedEntities();
            data.evolutionSpeed = storm.getVisualEvolutionSpeed();
        }
    }

    @Override
    public void submit(WitherRenderState state, PoseStack poses, SubmitNodeCollector output, CameraRenderState camera) {
        StormState data = (StormState) state;
        this.model = switch (data.phase) {
            case 0 -> earlyModel;
            case 1 -> data.consumed >= (int) (250 * data.evolutionSpeed) ? hunchbackHalf
                    : data.consumed >= (int) (150 * data.evolutionSpeed) ? hunchbackOneQuarter : hunchbackModel;
            case 2 -> data.consumed >= (int) (800 * data.evolutionSpeed) ? growingHalf : growingModel;
            case 3 -> data.consumed >= (int) (3500 * data.evolutionSpeed) ? pregnantTwo
                    : data.consumed >= (int) (2350 * data.evolutionSpeed) ? pregnantOne : pregnantModel;
            case 4 -> data.consumed > StormEvolutionRules.subphaseRequirement(4, data.evolutionSpeed)
                    ? intermediateDestroyerModel : destroyerModel;
            case 5 -> data.consumed > StormEvolutionRules.consumptionRequirement(5, data.evolutionSpeed) ? devourerModel
                    : data.consumed > StormEvolutionRules.subphaseRequirement(5, data.evolutionSpeed)
                    ? intermediateDevourerModel : evolvedDestroyerModel;
            case 6 -> data.consumed > StormEvolutionRules.subphaseRequirement(6, data.evolutionSpeed)
                    ? intermediateEvolvedDevourerModel : dismantledModel;
            default -> evolvedDevourerModel;
        };
        super.submit(state, poses, output, camera);
    }

    @Override
    public Identifier getTextureLocation(WitherRenderState state) {
        int ticks = Mth.floor(state.invulnerableTicks);
        return ticks > 0 && (ticks > 80 || ticks / 5 % 2 != 1) ? INVULNERABLE : TEXTURE;
    }

    @Override
    protected AABB getBoundingBoxForCulling(WitherBoss entity, float partialTick) {
        AABB bounds = super.getBoundingBoxForCulling(entity, partialTick);
        return entity instanceof WitherStormEntity storm && storm.getPhase() >= 4 ? bounds.inflate(48.0) : bounds;
    }
}

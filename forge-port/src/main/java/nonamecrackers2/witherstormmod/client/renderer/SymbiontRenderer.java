package nonamecrackers2.witherstormmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WitherSkeletonRenderer;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.client.model.SymbiontModel;

/** Original base geometry and texture; special overlays and combat animations remain unported. */
public final class SymbiontRenderer extends WitherSkeletonRenderer {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(WitherStormMod.id("withered_symbiont"), "main");
    private static final Identifier TEXTURE = WitherStormMod.id("textures/entity/withered_symbiont/withered_symbiont.png");
    private static final RenderType EYES = RenderTypes.eyes(WitherStormMod.id("textures/entity/withered_symbiont/withered_symbiont_emissive.png"));

    public SymbiontRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new SymbiontModel(context.bakeLayer(LAYER));
        addLayer(new EyesLayer<SkeletonRenderState, SkeletonModel<SkeletonRenderState>>(this) {
            @Override public RenderType renderType() { return EYES; }
        });
    }
    @Override public Identifier getTextureLocation(SkeletonRenderState state) { return TEXTURE; }
    @Override protected void scale(SkeletonRenderState state, PoseStack pose) { pose.scale(1.8F, 1.8F, 1.8F); }
}

package nonamecrackers2.witherstormmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WitherSkeletonRenderer;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.Identifier;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.client.model.SymbiontModel;

/** Original base geometry and texture; special overlays and combat animations remain unported. */
public final class SymbiontRenderer extends WitherSkeletonRenderer {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(WitherStormMod.id("withered_symbiont"), "main");
    private static final Identifier TEXTURE = WitherStormMod.id("textures/entity/withered_symbiont/withered_symbiont.png");

    public SymbiontRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new SymbiontModel(context.bakeLayer(LAYER));
    }
    @Override public Identifier getTextureLocation(SkeletonRenderState state) { return TEXTURE; }
    @Override protected void scale(SkeletonRenderState state, PoseStack pose) { pose.scale(1.8F, 1.8F, 1.8F); }
}

/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.SheetedDecalTextureGenerator;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.mojang.math.Axis;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.EntityModel;
/*     */ import net.minecraft.client.renderer.ItemBlockRenderTypes;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.block.BlockRenderDispatcher;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.client.resources.model.ModelBakery;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.inventory.InventoryMenu;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraftforge.client.model.data.ModelData;
/*     */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.commandblock.RibcageModel;
/*     */ import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
/*     */ import org.joml.Matrix4f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommandBlockRenderer
/*     */   extends EntityRenderer<CommandBlockEntity>
/*     */ {
/*  37 */   private static final ResourceLocation RIBCAGE_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/command_block/ribcage.png");
/*     */   private final RibcageModel ribcage;
/*     */   @Nullable
/*     */   private EntityModel<CommandBlockEntity> model;
/*     */   
/*     */   public CommandBlockRenderer(EntityRendererProvider.Context context) {
/*  43 */     super(context);
/*  44 */     this.ribcage = new RibcageModel(context.m_174023_(WitherStormModRenderers.RIBCAGE));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getBlockLightLevel(CommandBlockEntity entity, BlockPos pos) {
/*  50 */     return (entity.getMode() == CommandBlockEntity.Mode.TENTACLES) ? 15 : super.m_6086_((Entity)entity, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(CommandBlockEntity entity, float p_225623_2_, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
/*  57 */     this.model = getModel(entity.getMode());
/*  58 */     if (this.model != null) {
/*     */       
/*  60 */       stack.m_85836_();
/*  61 */       stack.m_85841_(-1.0F, -1.0F, 1.0F);
/*  62 */       stack.m_252781_(Axis.f_252392_.m_252977_(-Mth.m_14179_(partialTicks, entity.f_20884_, entity.f_20883_) + 90.0F));
/*  63 */       VertexConsumer builder = buffer.m_6299_(this.model.m_103119_(getTextureLocation(entity)));
/*  64 */       this.model.m_6973_((Entity)entity, entity.getModeAnim(partialTicks), partialTicks, 0.0F, entity.m_146908_(), entity.m_146909_());
/*  65 */       this.model.m_7695_(stack, builder, packedLight, OverlayTexture.f_118083_, 1.0F, 1.0F, 1.0F, 1.0F);
/*  66 */       stack.m_85849_();
/*     */     } 
/*     */     
/*  69 */     stack.m_85836_();
/*  70 */     stack.m_85837_(-0.5D, entity.lerpProtectionYOffset(partialTicks), -0.5D);
/*  71 */     Minecraft mc = Minecraft.m_91087_();
/*  72 */     BlockRenderDispatcher blockRenderer = mc.m_91289_();
/*  73 */     BlockState state = entity.getBlockState();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     blockRenderer.renderSingleBlock(state, stack, buffer, packedLight, OverlayTexture.f_118083_, ModelData.EMPTY, ItemBlockRenderTypes.m_109284_(entity.getBlockState(), false));
/*     */     
/* 120 */     int health = Mth.m_14143_((entity.m_21233_() - entity.m_21223_()) / entity.m_21233_() / 4.0F);
/* 121 */     PoseStack.Pose entry = stack.m_85850_();
/* 122 */     if (health < 5 && health > 0) {
/*     */       
/* 124 */       RenderType type = ModelBakery.f_119229_.get(health * 2);
/* 125 */       SheetedDecalTextureGenerator sheetedDecalTextureGenerator = new SheetedDecalTextureGenerator(buffer.m_6299_(type), entry.m_252922_(), entry.m_252943_(), 1.0F);
/* 126 */       blockRenderer.m_110937_().renderModel(entry, (VertexConsumer)sheetedDecalTextureGenerator, state, blockRenderer.m_110910_(state), 1.0F, 1.0F, 1.0F, packedLight, OverlayTexture.f_118083_, ModelData.EMPTY, type);
/*     */     } 
/*     */     
/* 129 */     stack.m_85849_();
/*     */     
/* 131 */     if (entity.getSpecialDeathTime() > 0 || entity.getHitGlareTime() > 0) {
/*     */       
/* 133 */       float f1 = 0.0F;
/* 134 */       float k = 1.0F;
/* 135 */       if (entity.getSpecialDeathTime() > 0) {
/*     */         
/* 137 */         f1 = (entity.getSpecialDeathTime() + partialTicks) / 20.0F;
/* 138 */         k = 1.0F;
/*     */       }
/* 140 */       else if (entity.getHitGlareTime() > 0) {
/*     */         
/* 142 */         f1 = 2.0F + (entity.getHitGlareTime() - partialTicks) / 30.0F;
/* 143 */         k = (entity.getHitGlareTime() - partialTicks) / 60.0F;
/*     */       } 
/* 145 */       Random random = new Random(122L);
/* 146 */       VertexConsumer builder = buffer.m_6299_(RenderType.m_110502_());
/* 147 */       stack.m_85836_();
/* 148 */       stack.m_85837_(0.0D, entity.m_20191_().m_82376_() / 2.0D, 0.0D);
/*     */ 
/*     */       
/* 151 */       for (int i = 0; i < (f1 + f1 * f1) / 2.0F; i++) {
/*     */         
/* 153 */         stack.m_252781_(Axis.f_252529_.m_252977_(random.nextFloat() * 360.0F));
/* 154 */         stack.m_252781_(Axis.f_252436_.m_252977_(random.nextFloat() * 360.0F));
/* 155 */         stack.m_252781_(Axis.f_252403_.m_252977_(random.nextFloat() * 360.0F));
/* 156 */         stack.m_252781_(Axis.f_252529_.m_252977_(random.nextFloat() * 360.0F));
/* 157 */         stack.m_252781_(Axis.f_252436_.m_252977_(random.nextFloat() * 360.0F));
/* 158 */         stack.m_252781_(Axis.f_252403_.m_252977_(random.nextFloat() * 360.0F + f1 * 90.0F));
/* 159 */         float f3 = random.nextFloat() * 0.2F + f1;
/* 160 */         float f4 = (random.nextFloat() + 0.2F) * 0.05F * f1;
/* 161 */         Matrix4f matrix4f = stack.m_85850_().m_252922_();
/* 162 */         float sqrt = (float)(Math.sqrt(3.0D) / 2.0D);
/* 163 */         builder.m_252986_(matrix4f, 0.0F, 0.0F, 0.0F).m_85950_(1.0F, 1.0F, 1.0F, k).m_5752_();
/* 164 */         builder.m_252986_(matrix4f, 0.0F, 0.0F, 0.0F).m_85950_(1.0F, 1.0F, 1.0F, k).m_5752_();
/* 165 */         builder.m_252986_(matrix4f, -sqrt * f4, f3, -0.5F * f4).m_6122_(255, 123, 0, 0).m_5752_();
/* 166 */         builder.m_252986_(matrix4f, sqrt * f4, f3, -0.5F * f4).m_6122_(255, 123, 0, 0).m_5752_();
/* 167 */         builder.m_252986_(matrix4f, 0.0F, 0.0F, 0.0F).m_85950_(1.0F, 1.0F, 1.0F, k).m_5752_();
/* 168 */         builder.m_252986_(matrix4f, 0.0F, 0.0F, 0.0F).m_85950_(1.0F, 1.0F, 1.0F, k).m_5752_();
/* 169 */         builder.m_252986_(matrix4f, sqrt * f4, f3, -0.5F * f4).m_6122_(255, 123, 0, 0).m_5752_();
/* 170 */         builder.m_252986_(matrix4f, 0.0F, f3, 1.0F * f4).m_6122_(255, 123, 0, 0).m_5752_();
/* 171 */         builder.m_252986_(matrix4f, 0.0F, 0.0F, 0.0F).m_85950_(1.0F, 1.0F, 1.0F, k).m_5752_();
/* 172 */         builder.m_252986_(matrix4f, 0.0F, 0.0F, 0.0F).m_85950_(1.0F, 1.0F, 1.0F, k).m_5752_();
/* 173 */         builder.m_252986_(matrix4f, 0.0F, f3, 1.0F * f4).m_6122_(255, 123, 0, 0).m_5752_();
/* 174 */         builder.m_252986_(matrix4f, -sqrt * f4, f3, -0.5F * f4).m_6122_(255, 123, 0, 0).m_5752_();
/*     */       } 
/*     */       
/* 177 */       stack.m_85849_();
/*     */     } 
/*     */     
/* 180 */     super.m_7392_((Entity)entity, p_225623_2_, partialTicks, stack, buffer, packedLight);
/*     */   }
/*     */ 
/*     */   
/*     */   private EntityModel<CommandBlockEntity> getModel(CommandBlockEntity.Mode mode) {
/* 185 */     return (mode == CommandBlockEntity.Mode.RIBS) ? (EntityModel<CommandBlockEntity>)this.ribcage : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getTextureLocation(CommandBlockEntity entity) {
/* 191 */     return (entity.getMode() == CommandBlockEntity.Mode.RIBS) ? RIBCAGE_LOCATION : InventoryMenu.f_39692_;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\CommandBlockRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
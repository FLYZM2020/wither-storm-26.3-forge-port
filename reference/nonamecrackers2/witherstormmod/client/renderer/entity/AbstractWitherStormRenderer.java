/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.mojang.math.Axis;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.awt.Color;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Camera;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.geom.ModelPart;
/*     */ import net.minecraft.client.model.geom.builders.CubeDefinition;
/*     */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*     */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*     */ import net.minecraft.client.renderer.LevelRenderer;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.culling.Frustum;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*     */ import net.minecraft.client.renderer.entity.LivingEntityRenderer;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.animal.Sheep;
/*     */ import net.minecraft.world.item.DyeColor;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.crackerslib.common.compat.CompatHelper;
/*     */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*     */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*     */ import nonamecrackers2.witherstormmod.client.instancing.RenderBufferer;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.SantaHatModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.AbstractWitherStormModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.HeadModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormCommandBlockModel;
/*     */ import nonamecrackers2.witherstormmod.client.rendertype.UtilRenderTypes;
/*     */ import nonamecrackers2.witherstormmod.client.resources.WitherStormResourceConfigManager;
/*     */ import nonamecrackers2.witherstormmod.client.resources.color.ColorSet;
/*     */ import nonamecrackers2.witherstormmod.client.resources.texture.TextureSet;
/*     */ import nonamecrackers2.witherstormmod.client.util.SpecialDay;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
/*     */ import nonamecrackers2.witherstormmod.common.entity.section.Section;
/*     */ import nonamecrackers2.witherstormmod.common.util.DebrisCluster;
/*     */ import nonamecrackers2.witherstormmod.common.util.DebrisRingSettings;
/*     */ import org.joml.Matrix3f;
/*     */ import org.joml.Matrix4f;
/*     */ import org.joml.Vector3f;
/*     */ 
/*     */ public abstract class AbstractWitherStormRenderer<T extends WitherStormEntity, M extends AbstractWitherStormModel<T>>
/*     */   extends EntityRenderer<T> {
/*  67 */   private static final Color MAIN_HEAD_COLOR = new Color(1.0F, 0.6F, 0.0F);
/*  68 */   private static final Color EXTRA_HEAD_COLOR = new Color(1.0F, 1.0F, 0.0F);
/*  69 */   public static final ResourceLocation WITHER_STORM_INVULNERABLE_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/wither_storm/wither_storm_invulnerable.png");
/*  70 */   public static final ResourceLocation WITHER_STORM_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/wither_storm/wither_storm.png");
/*  71 */   public static final ResourceLocation WITHER_STORM_EXPLODING_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/wither_storm/wither_storm_exploding.png");
/*  72 */   public static final ResourceLocation EMISSIVE_DECAL = new ResourceLocation("witherstormmod", "textures/entity/wither_storm/wither_storm_emissive_decal.png");
/*  73 */   public static final ResourceLocation HURT_OVERLAY = new ResourceLocation("witherstormmod", "textures/entity/wither_storm/wither_storm_hurt_overlay.png");
/*     */   
/*  75 */   public static final ResourceLocation PULSE = new ResourceLocation("witherstormmod", "textures/entity/wither_storm/wither_storm_pulse.png");
/*  76 */   public static final ResourceLocation DEBRIS_RING = new ResourceLocation("witherstormmod", "textures/entity/wither_storm/debris.png");
/*  77 */   public static final ResourceLocation SHINE = new ResourceLocation("witherstormmod", "textures/entity/wither_storm/shine.png");
/*  78 */   public static final ResourceLocation PINK_WITHER_STORM_LOCATION = new ResourceLocation("witherstormmod", "textures/misc/pink_wither_storm.png");
/*  79 */   public static final ResourceLocation WITHER_ARMOR_LOCATION = new ResourceLocation("textures/entity/wither/wither_armor.png");
/*     */   protected final SantaHatModel santaHat;
/*     */   @Nullable
/*     */   protected final SpecialDay specialDay;
/*     */   protected M model;
/*     */   protected WitherStormCommandBlockModel<T> swirlModel;
/*     */   
/*     */   public AbstractWitherStormRenderer(EntityRendererProvider.Context context, M model) {
/*  87 */     super(context);
/*  88 */     this.model = model;
/*  89 */     this.swirlModel = new WitherStormCommandBlockModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_ARMOR));
/*  90 */     this.santaHat = new SantaHatModel(context.m_174023_(WitherStormModRenderers.SANTA_HAT));
/*  91 */     this.specialDay = SpecialDay.getForCurrentDate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateModel(T entity) {
/* 101 */     this.model = fetchModel(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public M getModel() {
/* 106 */     return this.model;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getBlockLightLevel(T entity, BlockPos pos) {
/* 114 */     return Math.max(0, (int)((100.0F - entity.getFadeAnimation()) / 4.0F - 10.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(T entity, float yRot, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
/* 120 */     stack.m_85836_();
/*     */     
/* 122 */     updateModel(entity);
/*     */     
/* 124 */     renderDebrisClusters(getTextureLocation(entity), (WitherStormEntity)entity, stack, buffer, partialTicks, packedLight);
/*     */     
/* 126 */     float yBodyRot = Mth.m_14189_(partialTicks, ((WitherStormEntity)entity).f_20884_, ((WitherStormEntity)entity).f_20883_);
/* 127 */     float xBodyRot = Mth.m_14189_(partialTicks, ((WitherStormEntity)entity).xBodyRotO, ((WitherStormEntity)entity).xBodyRot);
/* 128 */     float yHeadRot = Mth.m_14189_(partialTicks, ((WitherStormEntity)entity).f_20886_, ((WitherStormEntity)entity).f_20885_);
/* 129 */     float netRot = yHeadRot - yBodyRot;
/* 130 */     float xRot = Mth.m_14179_(partialTicks, ((WitherStormEntity)entity).f_19860_, entity.m_146909_());
/* 131 */     float bob = ((WitherStormEntity)entity).f_19797_ + partialTicks;
/* 132 */     setupRotations(entity, stack, xBodyRot, yBodyRot, partialTicks);
/* 133 */     stack.m_85841_(-1.0F, -1.0F, 1.0F);
/* 134 */     scale(entity, stack, partialTicks);
/* 135 */     stack.m_252880_(0.0F, -1.501F, 0.0F);
/*     */     
/* 137 */     this.model.setupAnimations((WitherStormEntity)entity, partialTicks, bob, netRot, xRot);
/* 138 */     int i = LivingEntityRenderer.m_115338_((LivingEntity)entity, 0.0F);
/* 139 */     boolean shadersEnabled = CompatHelper.areShadersRunning();
/* 140 */     RenderType emissive = ((Boolean)WitherStormModConfig.CLIENT.renderEmissiveDecalForHeads.get()).booleanValue() ? UtilRenderTypes.emissiveTranslucent(getEmissiveDecalLocation(entity)) : RenderType.m_110452_(getEmissiveDecalLocation(entity));
/* 141 */     RenderType massEmissive = RenderType.m_110488_(getEmissiveDecalLocation(entity));
/* 142 */     RenderType hurtOverlay = RenderType.m_110473_(HURT_OVERLAY);
/*     */     
/* 144 */     if (entity.getDeathTime() > 0) {
/*     */       
/* 146 */       float fade = Math.min(entity.getDeathTime(), 400.0F) / 400.0F;
/* 147 */       RenderType explodingType = shadersEnabled ? RenderType.m_173235_(getExplodingTextureLocation(entity)) : UtilRenderTypes.witherStormDissolve(getExplodingTextureLocation(entity));
/* 148 */       this.model.render((WitherStormEntity)entity, stack, buffer, explodingType, null, null, hurtOverlay, packedLight, OverlayTexture.f_118083_, 1.0F, 1.0F, 1.0F, fade);
/* 149 */       RenderType decalType = shadersEnabled ? RenderType.m_110479_(getTextureLocation(entity)) : UtilRenderTypes.witherStormDecal(getTextureLocation(entity));
/* 150 */       this.model.render((WitherStormEntity)entity, stack, buffer, decalType, emissive, massEmissive, hurtOverlay, packedLight, i, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 154 */       this.model.render((WitherStormEntity)entity, stack, buffer, shadersEnabled ? RenderType.m_110458_(getTextureLocation(entity)) : UtilRenderTypes.witherStorm(getTextureLocation(entity)), emissive, massEmissive, hurtOverlay, packedLight, i, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */     
/* 157 */     if (entity.m_7090_()) {
/* 158 */       renderEnergyShield(entity, stack, buffer, partialTicks, bob, netRot, xRot, packedLight, i);
/*     */     }
/* 160 */     if (this.specialDay == SpecialDay.CHRISTMAS) {
/* 161 */       renderChristmasFestivities(entity, stack, buffer, packedLight, i);
/*     */     }
/* 163 */     if (((Boolean)WitherStormModConfig.CLIENT.renderPulse.get()).booleanValue() && entity.isBeingTornApart() && !entity.m_21224_()) {
/* 164 */       renderPulsing(entity, stack, buffer, partialTicks, packedLight, i);
/*     */     }
/* 166 */     stack.m_85849_();
/*     */     
/* 168 */     super.m_7392_((Entity)entity, yRot, partialTicks, stack, buffer, packedLight);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderEnergyShield(T entity, PoseStack stack, MultiBufferSource bufferSource, float partialTicks, float bob, float yRot, float xRot, int packedLight, int overlayTexture) {
/* 173 */     this.swirlModel.setupAnimations((WitherStormEntity)entity, partialTicks, bob, yRot, xRot);
/* 174 */     float xOffset = Mth.m_14089_(bob * 0.02F) * 3.0F;
/* 175 */     RenderType type = RenderType.m_110436_(WITHER_ARMOR_LOCATION, xOffset % 1.0F, bob * 0.01F % 1.0F);
/* 176 */     this.swirlModel.render((WitherStormEntity)entity, stack, bufferSource, type, null, null, null, packedLight, overlayTexture, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderChristmasFestivities(T entity, PoseStack stack, MultiBufferSource bufferSource, int packedLight, int overlayTexture) {
/* 181 */     for (ObjectIterator<Int2ObjectMap.Entry<HeadModel<T>>> objectIterator = ((AbstractWitherStormModel)getModel()).heads.int2ObjectEntrySet().iterator(); objectIterator.hasNext(); ) { Int2ObjectMap.Entry<HeadModel<T>> entry = objectIterator.next();
/*     */       
/* 183 */       if (!entity.areOtherHeadsDisabled() || entry.getIntKey() == 0) {
/*     */         
/* 185 */         stack.m_85836_();
/* 186 */         HeadModel<T> model = (HeadModel<T>)entry.getValue();
/* 187 */         model.scale(stack);
/* 188 */         model.root().m_104299_(stack);
/* 189 */         this.santaHat.m_7695_(stack, bufferSource.m_6299_(this.santaHat.m_103119_(SantaHatModel.TEXTURE)), packedLight, overlayTexture, 1.0F, 1.0F, 1.0F, 1.0F);
/* 190 */         stack.m_85849_();
/*     */       }  }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderPulsing(T entity, PoseStack stack, MultiBufferSource bufferSource, float partialTicks, int packedLight, int overlayTexture) {
/* 197 */     if (this.model.getMassModel((WitherStormEntity)entity) != null) {
/*     */       
/* 199 */       VertexConsumer builder = bufferSource.m_6299_(RenderType.m_110473_(getPulseTextureLocation(entity)));
/* 200 */       for (int k = 0; k < getPulseAmount(entity); k++) {
/*     */         
/* 202 */         float tick = (((WitherStormEntity)entity).f_19797_ + k) + partialTicks;
/* 203 */         long seed = (long)(tick / 20.0F);
/* 204 */         RandomSource random = RandomSource.m_216335_(seed + (long)(k * Math.PI));
/*     */         
/* 206 */         ModelPart part = this.model.getRandomPart((WitherStormEntity)entity, random);
/* 207 */         Vector3f pos = getRandomPointOnCubeSurface(part.m_233558_(random), random);
/* 208 */         ModelPart mirror = new ModelPart((List)CubeListBuilder.m_171558_().m_171514_(random.m_188503_(28), Math.max(16, random.m_188503_(30))).m_171488_(pos.x(), pos.y(), pos.z(), 1.0F, 1.0F, 1.0F, new CubeDeformation(0.01F)).m_171557_().stream().map(def -> def.m_171455_(32, 32)).collect(Collectors.toList()), Maps.newHashMap());
/* 209 */         ModelPart mass = this.model.getMassModel((WitherStormEntity)entity);
/* 210 */         mirror.f_104200_ = mass.f_104200_;
/* 211 */         mirror.f_104201_ = mass.f_104201_;
/* 212 */         mirror.f_104202_ = mass.f_104202_;
/* 213 */         if (part != mass) {
/*     */           
/* 215 */           mirror.f_104200_ += part.f_104200_;
/* 216 */           mirror.f_104201_ += part.f_104201_;
/* 217 */           mirror.f_104202_ += part.f_104202_;
/*     */         } 
/* 219 */         mirror.f_104203_ = part.f_104203_;
/* 220 */         mirror.f_104204_ = part.f_104204_;
/* 221 */         mirror.f_104205_ = part.f_104205_;
/*     */         
/* 223 */         stack.m_85836_();
/* 224 */         stack.m_85841_(entity.isMirrored() ? -1.0F : 1.0F, 1.0F, 1.0F);
/* 225 */         this.model.scaleMass(stack);
/* 226 */         float fade = 1.0F - tick % 20.0F * 0.1F / 2.0F;
/* 227 */         int overlayCoords = LivingEntityRenderer.m_115338_((LivingEntity)entity, 0.0F);
/* 228 */         mirror.m_104306_(stack, builder, packedLight, overlayCoords, 1.0F, 1.0F, 1.0F, fade);
/* 229 */         stack.m_85849_();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderTractorBeams(T entity, PoseStack stack, MultiBufferSource buffer, float partialTicks, int packedLight) {
/* 236 */     for (ObjectIterator<Int2ObjectMap.Entry<HeadModel<T>>> objectIterator = ((AbstractWitherStormModel)getModel()).heads.int2ObjectEntrySet().iterator(); objectIterator.hasNext(); ) { Int2ObjectMap.Entry<HeadModel<T>> entry = objectIterator.next();
/*     */       
/* 238 */       HeadModel<T> head = (HeadModel<T>)entry.getValue();
/* 239 */       stack.m_85836_();
/* 240 */       if (head.shouldRenderTractorBeam((LivingEntity)entity, entry.getIntKey())) {
/*     */         float r, g, b;
/* 242 */         Minecraft mc = Minecraft.m_91087_();
/* 243 */         Vec3 pos = mc.f_91063_.m_109153_().m_90583_();
/* 244 */         double distance = pos.m_82554_(entity.m_20182_());
/* 245 */         float renderDistance = ((Integer)mc.f_91066_.m_231984_().m_231551_()).intValue() / 16.0F;
/* 246 */         float distLerp = getDistanceLerp(distance, renderDistance);
/* 247 */         float nightLerp = getNightTimeLerp(entity.m_9236_(), partialTicks);
/* 248 */         float alpha = distLerp * (1.0F - nightLerp);
/*     */ 
/*     */ 
/*     */         
/* 252 */         boolean rainbow = (entity.m_8077_() && entity.m_7755_().getString().equals("jeb_"));
/* 253 */         if (!rainbow && entity instanceof WitherStormSegmentEntity) { WitherStormSegmentEntity segment = (WitherStormSegmentEntity)entity;
/*     */           
/* 255 */           WitherStormEntity parent = segment.getParent();
/* 256 */           if (parent != null && parent.m_8077_() && parent.m_7755_().getString().equals("jeb_"))
/* 257 */             rainbow = true;  }
/*     */         
/* 259 */         if (rainbow) {
/*     */           
/* 261 */           float[] rainbowColor = getRainbowColor((WitherStormEntity)entity, entry.getIntKey(), partialTicks);
/* 262 */           r = rainbowColor[0];
/* 263 */           g = rainbowColor[1];
/* 264 */           b = rainbowColor[2];
/*     */ 
/*     */         
/*     */         }
/* 268 */         else if (this.specialDay != null) {
/*     */           
/* 270 */           Color color = this.specialDay.getColor((WitherStormEntity)entity, partialTicks, entry.getIntKey());
/* 271 */           r = color.getRed() / 255.0F;
/* 272 */           g = color.getGreen() / 255.0F;
/* 273 */           b = color.getBlue() / 255.0F;
/*     */         }
/*     */         else {
/*     */           
/* 277 */           ColorSet set = WitherStormResourceConfigManager.INSTANCE.getColorSetByPhase(entity.getPhase());
/* 278 */           Color color = set.tractorBeamColor();
/* 279 */           Color night = set.tractorBeamNightColor();
/* 280 */           r = Mth.m_14179_(nightLerp, color.getRed(), night.getRed()) / 255.0F;
/* 281 */           g = Mth.m_14179_(nightLerp, color.getGreen(), night.getGreen()) / 255.0F;
/* 282 */           b = Mth.m_14179_(nightLerp, color.getBlue(), night.getBlue()) / 255.0F;
/*     */         } 
/*     */         
/* 285 */         if (((Boolean)WitherStormModConfig.CLIENT.distantFog.get()).booleanValue()) {
/*     */           
/* 287 */           float rDelta = 0.3F - r;
/* 288 */           float gDelta = 0.3F - g;
/* 289 */           float bDelta = 0.3F - b;
/* 290 */           r += rDelta * alpha;
/* 291 */           g += gDelta * alpha;
/* 292 */           b += bDelta * alpha;
/*     */         } 
/* 294 */         double cutoff = entity.getTractorBeamCutoffDistance(entry.getIntKey());
/* 295 */         if (cutoff != -1.0D)
/* 296 */           cutoff = cutoff / ((AbstractWitherStormModel)this.model).headScale + 10.0D; 
/* 297 */         head.renderTractorBeam((LivingEntity)entity, stack, buffer, packedLight, r, g, b, 0.5F, partialTicks, cutoff, 0.5F * (1.0F - distLerp));
/*     */       } 
/* 299 */       stack.m_85849_(); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   private static float[] getRainbowColor(WitherStormEntity entity, int offset, float partialTicks) {
/* 305 */     int tickCount = entity.f_19797_ / 25 + entity.m_19879_() + offset;
/* 306 */     int allDyeColors = (DyeColor.values()).length;
/* 307 */     int k = tickCount % allDyeColors;
/* 308 */     int l = (tickCount + 1) % allDyeColors;
/* 309 */     float f3 = ((entity.f_19797_ % 25) + partialTicks) / 25.0F;
/* 310 */     float[] afloat1 = Sheep.m_29829_(DyeColor.m_41053_(k));
/* 311 */     float[] afloat2 = Sheep.m_29829_(DyeColor.m_41053_(l));
/* 312 */     float r = afloat1[0] * (1.0F - f3) + afloat2[0] * f3;
/* 313 */     float g = afloat1[1] * (1.0F - f3) + afloat2[1] * f3;
/* 314 */     float b = afloat1[2] * (1.0F - f3) + afloat2[2] * f3;
/* 315 */     return new float[] { r, g, b };
/*     */   }
/*     */ 
/*     */   
/*     */   public void prepareHeadAnimsForTractorBeams(T entity, float partialTicks) {
/* 320 */     for (ObjectIterator<Int2ObjectMap.Entry<HeadModel<T>>> objectIterator = ((AbstractWitherStormModel)this.model).heads.int2ObjectEntrySet().iterator(); objectIterator.hasNext(); ) { Int2ObjectMap.Entry<HeadModel<T>> entry = objectIterator.next();
/*     */       
/* 322 */       HeadModel<T> head = (HeadModel<T>)entry.getValue();
/* 323 */       float yBodyRot = Mth.m_14189_(partialTicks, ((WitherStormEntity)entity).f_20884_, ((WitherStormEntity)entity).f_20883_);
/* 324 */       float yHeadRot = Mth.m_14189_(partialTicks, ((WitherStormEntity)entity).f_20886_, ((WitherStormEntity)entity).f_20885_);
/* 325 */       float yRot = yHeadRot - yBodyRot;
/* 326 */       float xRot = Mth.m_14179_(partialTicks, ((WitherStormEntity)entity).f_19860_, entity.m_146909_());
/* 327 */       float bob = ((WitherStormEntity)entity).f_19797_ + partialTicks;
/* 328 */       head.setupAnimations((LivingEntity)entity, partialTicks, bob, yRot, xRot, entry.getIntKey()); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender(T entity, Frustum clipping, double x, double y, double z) {
/* 335 */     if (!entity.m_6000_(x, y, z))
/*     */     {
/* 337 */       return false;
/*     */     }
/* 339 */     if (!entity.isOnDistantRenderer() && ((WitherStormEntity)entity).f_19811_)
/*     */     {
/* 341 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 345 */     AABB box = entity.m_6921_().m_82400_(0.5D);
/* 346 */     if (box.m_82392_() || box.m_82309_() == 0.0D) {
/* 347 */       box = new AABB(entity.m_20185_() - 2.0D, entity.m_20186_() - 2.0D, entity.m_20189_() - 2.0D, entity.m_20185_() + 2.0D, entity.m_20186_() + 2.0D, entity.m_20189_() + 2.0D);
/*     */     }
/* 349 */     return clipping.m_113029_(box);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean shouldShowName(T entity) {
/* 356 */     return (super.m_6512_((Entity)entity) && (entity.m_6052_() || (entity.m_8077_() && entity == this.f_114476_.f_114359_)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderDebrisRings(WitherStormEntity entity, PoseStack stack, MultiBufferSource buffer, float partialTicks, int packedLightIn) {
/* 361 */     Minecraft mc = Minecraft.m_91087_();
/* 362 */     Vec3 pos = mc.f_91063_.m_109153_().m_90583_();
/* 363 */     double distance = pos.m_82554_(entity.m_20182_());
/* 364 */     float baseAlpha = Mth.m_14036_((float)(400.0D - distance) * 0.005F, 0.2F, 0.5F);
/* 365 */     ResourceLocation tex = WitherStormResourceConfigManager.INSTANCE.getTextureSetByPhase(entity.getPhase()).debrisRing();
/* 366 */     VertexConsumer debrisBuilder = buffer.m_6299_(RenderType.m_110470_(tex));
/* 367 */     for (DebrisRingSettings settings : entity.getDebrisRings()) {
/*     */       
/* 369 */       if (settings.alpha() > 0.0F)
/*     */       {
/* 371 */         if (entity.getPhase() >= settings.getPhaseRequirement()) {
/*     */           
/* 373 */           stack.m_85836_();
/* 374 */           int segments = settings.getSegments();
/* 375 */           float bottomRadius = settings.getBottomRadius();
/* 376 */           float topRadius = settings.getTopRadius();
/* 377 */           float y = settings.getY();
/* 378 */           float height = settings.getHeight();
/* 379 */           float alpha = settings.alpha() * baseAlpha;
/* 380 */           PoseStack.Pose entry = stack.m_85850_();
/* 381 */           Matrix4f matrix4f = entry.m_252922_();
/* 382 */           Matrix3f matrix3f = entry.m_252943_();
/* 383 */           float u = 0.0F;
/* 384 */           float v = 0.0F;
/* 385 */           float uMax = 1.0F;
/* 386 */           float vMax = 1.0F;
/* 387 */           float tickCount = (entity.f_19797_ + partialTicks) * settings.getSpeedModifier() * (settings.clockwise() ? 1.0F : -1.0F);
/*     */           
/* 389 */           for (int i = 0; i < segments; i++) {
/*     */             
/* 391 */             float theta = (float)(6.283185307179586D / segments);
/* 392 */             float angle = theta * i + tickCount;
/* 393 */             float x = Mth.m_14089_(angle);
/* 394 */             float z = Mth.m_14031_(angle);
/* 395 */             float angle2 = theta * (i + 1) + tickCount;
/* 396 */             float x2 = Mth.m_14089_(angle2);
/* 397 */             float z2 = Mth.m_14031_(angle2);
/* 398 */             debrisBuilder.m_252986_(matrix4f, x * bottomRadius, y, z * bottomRadius).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(u, v).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 399 */             debrisBuilder.m_252986_(matrix4f, x * topRadius, height, z * topRadius).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(u, vMax).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 400 */             debrisBuilder.m_252986_(matrix4f, x2 * topRadius, height, z2 * topRadius).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMax, vMax).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 401 */             debrisBuilder.m_252986_(matrix4f, x2 * bottomRadius, y, z2 * bottomRadius).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMax, v).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/*     */             
/* 403 */             debrisBuilder.m_252986_(matrix4f, x2 * bottomRadius, y, z2 * bottomRadius).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMax, vMax).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 404 */             debrisBuilder.m_252986_(matrix4f, x2 * topRadius, height, z2 * topRadius).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(uMax, v).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 405 */             debrisBuilder.m_252986_(matrix4f, x * topRadius, height, z * topRadius).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(u, v).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 406 */             debrisBuilder.m_252986_(matrix4f, x * bottomRadius, y, z * bottomRadius).m_85950_(1.0F, 1.0F, 1.0F, alpha).m_7421_(u, vMax).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/*     */           } 
/* 408 */           stack.m_85849_();
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderDebrisClusters(ResourceLocation tex, WitherStormEntity entity, PoseStack stack, MultiBufferSource bufferSource, float partialTicks, int packedLight) {
/* 416 */     if (((Boolean)WitherStormModConfig.CLIENT.renderDebrisCloud.get()).booleanValue() && ((!((Boolean)WitherStormModConfig.CLIENT.renderDistantDebris.get()).booleanValue() && !entity.isOnDistantRenderer()) || ((Boolean)WitherStormModConfig.CLIENT.renderDistantDebris.get()).booleanValue()))
/*     */     {
/* 418 */       for (Iterator<DebrisCluster> iterator = entity.getDebrisClusters().iterator(); iterator.hasNext(); ) { DebrisCluster cluster = iterator.next();
/*     */         
/* 420 */         if (!cluster.isDisabled() && cluster.getRenderPhase() <= entity.getPhase()) {
/*     */           
/* 422 */           float u, v, uMax, vMax, orbitalAngle = cluster.getOrbitalAngle(partialTicks);
/* 423 */           stack.m_85836_();
/*     */           
/* 425 */           stack.m_85837_(0.0D, cluster.getVerticalOffset(), 0.0D);
/* 426 */           stack.m_252781_(Axis.f_252436_.m_252977_(orbitalAngle));
/* 427 */           stack.m_85837_(cluster.getRadiusFromCenter(), 0.0D, 0.0D);
/*     */           
/* 429 */           stack.m_252781_(Axis.f_252529_.m_252977_(cluster.getXRot(partialTicks)));
/* 430 */           stack.m_252781_(Axis.f_252436_.m_252977_(cluster.getYRot(partialTicks)));
/*     */           
/* 432 */           boolean flag = ((cluster.isGlowing() && entity.getPhase() > 5) || cluster.isForcedGlowing());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 438 */           if (flag) {
/*     */             
/* 440 */             u = 0.3125F;
/* 441 */             v = 0.3125F;
/* 442 */             uMax = 0.375F;
/* 443 */             vMax = 0.375F;
/*     */           }
/*     */           else {
/*     */             
/* 447 */             u = 0.9F;
/* 448 */             v = 0.8F;
/* 449 */             uMax = 1.0F;
/* 450 */             vMax = 0.9F;
/*     */           } 
/*     */           
/* 453 */           RenderBufferer.pushCullFaces();
/* 454 */           if (flag)
/* 455 */             RenderBufferer.pushNoFog(); 
/* 456 */           RenderBufferer.buildAndOrRender(cluster.toString() + cluster.toString() + flag, RenderType.m_110452_(tex), (bstack, consumer, bpackedLight, boverlayTexture, br, bg, bb, ba) -> renderPieces(cluster, bstack, consumer, br, bg, bb, ba, boverlayTexture, bpackedLight, u, v, uMax, vMax), stack, packedLight, OverlayTexture.f_118083_, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */ 
/*     */ 
/*     */           
/* 460 */           if (flag) {
/*     */             
/* 462 */             RenderBufferer.pushNoFog();
/* 463 */             RenderBufferer.buildAndOrRender(cluster.toString() + ", " + cluster.toString() + ", emissive" + flag, RenderType.m_110488_(tex), (bstack, consumer, bpackedLight, boverlayTexture, br, bg, bb, ba) -> renderPieces(cluster, bstack, consumer, br, bg, bb, ba, boverlayTexture, bpackedLight, u, v, uMax, vMax), stack, packedLight, OverlayTexture.f_118083_, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */           } 
/*     */ 
/*     */           
/* 467 */           RenderBufferer.popCullFaces();
/*     */           
/* 469 */           stack.m_85849_();
/*     */         }  }
/*     */     
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void renderPieces(DebrisCluster cluster, PoseStack bstack, VertexConsumer consumer, float br, float bg, float bb, float ba, int boverlayTexture, int bpackedLight, float u, float v, float uMax, float vMax) {
/* 477 */     for (DebrisCluster.Piece piece : cluster.getPieces()) {
/*     */       
/* 479 */       bstack.m_85836_();
/*     */       
/* 481 */       bstack.m_252880_(piece.x(), piece.y(), piece.z());
/*     */       
/* 483 */       Matrix4f matrix4f = bstack.m_85850_().m_252922_();
/* 484 */       Matrix3f matrix3f = bstack.m_85850_().m_252943_();
/*     */       
/* 486 */       float startSize = piece.size();
/* 487 */       float endSize = -piece.size();
/*     */       
/* 489 */       consumer.m_252986_(matrix4f, startSize, startSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(u, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 490 */       consumer.m_252986_(matrix4f, startSize, endSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(u, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 491 */       consumer.m_252986_(matrix4f, endSize, endSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 492 */       consumer.m_252986_(matrix4f, endSize, startSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/*     */ 
/*     */ 
/*     */       
/* 496 */       consumer.m_252986_(matrix4f, endSize, startSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(u, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 497 */       consumer.m_252986_(matrix4f, endSize, endSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(u, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 498 */       consumer.m_252986_(matrix4f, endSize, endSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 499 */       consumer.m_252986_(matrix4f, endSize, startSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/*     */       
/* 501 */       consumer.m_252986_(matrix4f, startSize, startSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(u, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 502 */       consumer.m_252986_(matrix4f, startSize, endSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(u, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 503 */       consumer.m_252986_(matrix4f, startSize, endSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 504 */       consumer.m_252986_(matrix4f, startSize, startSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/*     */       
/* 506 */       consumer.m_252986_(matrix4f, endSize, startSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(u, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 507 */       consumer.m_252986_(matrix4f, endSize, endSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(u, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 508 */       consumer.m_252986_(matrix4f, startSize, endSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 509 */       consumer.m_252986_(matrix4f, startSize, startSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/*     */       
/* 511 */       consumer.m_252986_(matrix4f, startSize, startSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(u, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 512 */       consumer.m_252986_(matrix4f, startSize, startSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(u, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 513 */       consumer.m_252986_(matrix4f, endSize, startSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 514 */       consumer.m_252986_(matrix4f, endSize, startSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/*     */       
/* 516 */       consumer.m_252986_(matrix4f, startSize, endSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(u, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 517 */       consumer.m_252986_(matrix4f, endSize, endSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(u, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 518 */       consumer.m_252986_(matrix4f, endSize, endSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/* 519 */       consumer.m_252986_(matrix4f, startSize, endSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0F, -1.0F, 0.0F).m_5752_();
/*     */ 
/*     */       
/* 522 */       bstack.m_85849_();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getTextureLocation(T entity) {
/* 529 */     TextureSet set = WitherStormResourceConfigManager.INSTANCE.getTextureSetByPhase(entity.getPhase());
/* 530 */     boolean flag = (WitherStormMod.isAprilFools() && ((Boolean)WitherStormModConfig.CLIENT.aprilFools.get()).booleanValue());
/* 531 */     int i = entity.getInvulnerableTicks();
/* 532 */     return (i > 0 && (i > 80 || i / 5 % 2 != 1)) ? set.invulnerable() : (flag ? PINK_WITHER_STORM_LOCATION : set.main());
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getExplodingTextureLocation(T entity) {
/* 537 */     return WITHER_STORM_EXPLODING_LOCATION;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getPulseTextureLocation(T entity) {
/* 542 */     return PULSE;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getEmissiveDecalLocation(T entity) {
/* 547 */     return WitherStormResourceConfigManager.INSTANCE.getTextureSetByPhase(entity.getPhase()).emissiveDecal();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void scale(T entity, PoseStack stack, float partialTicks) {
/* 557 */     float f = 2.0F;
/* 558 */     int i = entity.getInvulnerableTicks();
/* 559 */     int j = Math.max(0, i - 750);
/* 560 */     if (j > 0) {
/* 561 */       f -= (j - partialTicks) / Math.max(0, entity.getStartingInvulnerableTicks() - 750) * 0.5F;
/*     */     }
/* 563 */     stack.m_85841_(f, f, f);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setupRotations(T entity, PoseStack stack, float xBodyRot, float yBodyRot, float partialTicks) {
/* 568 */     stack.m_252781_(Axis.f_252436_.m_252977_(180.0F - yBodyRot));
/* 569 */     stack.m_252781_(Axis.f_252529_.m_252977_(xBodyRot));
/*     */   }
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
/*     */   public int getPulseAmount(T entity) {
/* 646 */     return (int)(entity.getPhase() * 15.0F / 2.0F * (((Boolean)WitherStormModConfig.CLIENT.lowResModels.get()).booleanValue() ? 3.0F : 1.0F));
/*     */   }
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
/*     */   @Nullable
/*     */   public static <T extends WitherStormEntity, M extends AbstractWitherStormModel<T>> AbstractWitherStormRenderer<T, M> getRenderer(T storm, EntityRenderDispatcher manager) {
/* 676 */     EntityRenderer<? super T> raw = manager.m_114382_((Entity)storm);
/* 677 */     if (raw instanceof AbstractWitherStormRenderer) {
/* 678 */       return (AbstractWitherStormRenderer)raw;
/*     */     }
/* 680 */     return null;
/*     */   }
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
/*     */   public static void renderShine(WitherStormEntity storm, PoseStack stack, float partialTicks, Camera camera, MultiBufferSource buffer) {
/*     */     int[] color;
/* 718 */     Minecraft mc = Minecraft.m_91087_();
/* 719 */     Color configColor = WitherStormResourceConfigManager.INSTANCE.getColorSetByPhase(storm.getPhase()).nightShineColor();
/* 720 */     boolean rainbow = (storm.m_8077_() && storm.m_7755_().getString().equals("jeb_"));
/* 721 */     if (!rainbow && storm instanceof WitherStormSegmentEntity) { WitherStormSegmentEntity segment = (WitherStormSegmentEntity)storm;
/*     */       
/* 723 */       WitherStormEntity parent = segment.getParent();
/* 724 */       if (parent != null && parent.m_8077_() && parent.m_7755_().getString().equals("jeb_")) {
/* 725 */         rainbow = true;
/*     */       } }
/*     */     
/* 728 */     if (!rainbow) {
/*     */       
/* 730 */       color = new int[] { configColor.getRed(), configColor.getGreen(), configColor.getBlue(), configColor.getAlpha() };
/*     */     }
/*     */     else {
/*     */       
/* 734 */       float[] rainbowColor = getRainbowColor(storm, 0, partialTicks);
/* 735 */       color = new int[] { (int)(rainbowColor[0] * 255.0F), (int)(rainbowColor[1] * 255.0F), (int)(rainbowColor[2] * 255.0F), 75 };
/*     */     } 
/* 737 */     float distanceLerp = 1.0F;
/* 738 */     if (storm.getPhase() > 5 || storm.getConsumptionAmountForPhase(5) <= storm.getConsumedEntities())
/* 739 */       distanceLerp = getDistanceLerp(storm.m_146892_().m_82554_(camera.m_90583_()), ((Integer)mc.f_91066_.m_231984_().m_231551_()).intValue() / 8.0F); 
/* 740 */     float f1 = getNightTimeLerp(storm.m_9236_(), partialTicks) * distanceLerp * storm.getShineAlpha(partialTicks);
/* 741 */     color[3] = Mth.m_14143_(color[3] * f1);
/* 742 */     if (color[3] > 0) {
/*     */       
/* 744 */       VertexConsumer consumer = buffer.m_6299_(RenderType.m_110473_(SHINE));
/* 745 */       Matrix4f matrix = stack.m_85850_().m_252922_();
/* 746 */       Vec3 dir = camera.m_90583_().m_82546_(storm.m_20299_(partialTicks)).m_82541_();
/* 747 */       float pitch = (float)Math.asin(dir.f_82480_);
/* 748 */       float yaw = (float)Math.atan2(dir.f_82479_, dir.f_82481_);
/* 749 */       stack.m_252781_(Axis.f_252392_.m_252977_(180.0F));
/* 750 */       stack.m_252781_(Axis.f_252436_.m_252961_(yaw));
/* 751 */       stack.m_252781_(Axis.f_252529_.m_252961_(pitch));
/* 752 */       float scale = storm.getShineScale();
/* 753 */       float xStretch = scale * ((storm.getPhase() > 5) ? 1.5F : 1.0F);
/* 754 */       stack.m_252880_(-xStretch / 2.0F, storm.getUnmodifiedHeight() / 2.0F - scale / 2.0F, scale / 2.0F);
/* 755 */       stack.m_85841_(xStretch, scale, 1.0F);
/* 756 */       consumer.m_252986_(matrix, 0.0F, 0.0F, 0.0F).m_6122_(color[0], color[1], color[2], color[3]).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(15728880).m_5601_(0.0F, 1.0F, 1.0F).m_5752_();
/* 757 */       consumer.m_252986_(matrix, 0.0F, 1.0F, 0.0F).m_6122_(color[0], color[1], color[2], color[3]).m_7421_(0.0F, 1.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(15728880).m_5601_(0.0F, 1.0F, 1.0F).m_5752_();
/* 758 */       consumer.m_252986_(matrix, 1.0F, 1.0F, 0.0F).m_6122_(color[0], color[1], color[2], color[3]).m_7421_(1.0F, 1.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(15728880).m_5601_(0.0F, 1.0F, 1.0F).m_5752_();
/* 759 */       consumer.m_252986_(matrix, 1.0F, 0.0F, 0.0F).m_6122_(color[0], color[1], color[2], color[3]).m_7421_(1.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(15728880).m_5601_(0.0F, 1.0F, 1.0F).m_5752_();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getNightTimeLerp(Level level, float partialTicks) {
/* 765 */     float f1 = Mth.m_14089_(level.m_46942_(partialTicks) * 6.2831855F) * 2.0F + 0.5F;
/* 766 */     return 1.0F - Mth.m_14036_(f1 + 0.5F, 0.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getDistanceLerp(double distance, float renderDistance) {
/* 771 */     return Mth.m_14036_((float)(distance - (200.0F * renderDistance)) * 0.005F, 0.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Vector3f getRandomPointOnCubeSurface(ModelPart.Cube box, RandomSource random) {
/* 776 */     Vector3f pos = new Vector3f(box.f_104335_, box.f_104336_, box.f_104337_);
/* 777 */     float xDif = box.f_104338_ - pos.x();
/* 778 */     float yDif = box.f_104339_ - pos.y();
/* 779 */     float zDif = box.f_104340_ - pos.z();
/* 780 */     boolean flag = (xDif > 0.0F && yDif > 0.0F && zDif > 0.0F);
/* 781 */     if (flag) {
/*     */       
/* 783 */       Direction direction = Direction.m_235672_(random);
/* 784 */       if (direction.m_122434_().equals(Direction.Axis.X)) {
/*     */         
/* 786 */         pos.set(direction.equals(Direction.WEST) ? box.f_104335_ : (box.f_104338_ - 1.0F), pos.y, pos.z);
/* 787 */         pos.set(pos.x, pos.y() + random.m_188503_((int)yDif), pos.z);
/* 788 */         pos.set(pos.x, pos.y, pos.z() + random.m_188503_((int)zDif));
/*     */       }
/* 790 */       else if (direction.m_122434_().equals(Direction.Axis.Y)) {
/*     */         
/* 792 */         pos.set(pos.x() + random.m_188503_((int)xDif), pos.y, pos.z);
/* 793 */         pos.set(pos.x, direction.equals(Direction.DOWN) ? box.f_104336_ : (box.f_104339_ - 1.0F), pos.z);
/* 794 */         pos.set(pos.x, pos.y, pos.z() + random.m_188503_((int)zDif));
/*     */       }
/* 796 */       else if (direction.m_122434_().equals(Direction.Axis.Z)) {
/*     */         
/* 798 */         pos.set(pos.x() + random.m_188503_((int)xDif), pos.y, pos.z);
/* 799 */         pos.set(pos.x, pos.y() + random.m_188503_((int)yDif), pos.z);
/* 800 */         pos.set(pos.x, pos.y, direction.equals(Direction.NORTH) ? box.f_104337_ : (box.f_104340_ - 1.0F));
/*     */       } 
/*     */     } 
/* 803 */     return pos;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderExtraHitboxes(PoseStack stack, VertexConsumer consumer, WitherStormEntity storm, float partialTick) {
/* 808 */     for (WitherStormHead head : storm.getHeadManager().getHeads()) {
/*     */       
/* 810 */       stack.m_85836_();
/* 811 */       stack.m_85837_(-storm.m_20185_(), -storm.m_20186_(), -storm.m_20189_());
/* 812 */       if (head.getBoundingBox() != null) {
/*     */         
/* 814 */         AABB box = head.getBoundingBox();
/* 815 */         Color col = EXTRA_HEAD_COLOR;
/* 816 */         if (head instanceof nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.MainHead)
/* 817 */           col = MAIN_HEAD_COLOR; 
/* 818 */         LevelRenderer.m_109646_(stack, consumer, box, col.getRed() / 255.0F, col.getGreen() / 255.0F, col.getBlue() / 255.0F, 1.0F);
/* 819 */         Vec3 viewVector = storm.getViewVector(head.getHeadXRot(partialTick), head.getHeadYRot(partialTick), (float)box.m_82309_());
/* 820 */         Vec3 eyePos = head.getHeadPos();
/* 821 */         Matrix4f matrix4f = stack.m_85850_().m_252922_();
/* 822 */         Matrix3f matrix3f = stack.m_85850_().m_252943_();
/* 823 */         consumer.m_252986_(matrix4f, (float)eyePos.f_82479_, (float)eyePos.f_82480_, (float)eyePos.f_82481_).m_6122_(0, 0, 255, 255).m_252939_(matrix3f, (float)viewVector.f_82479_, (float)viewVector.f_82480_, (float)viewVector.f_82481_).m_5752_();
/* 824 */         consumer.m_252986_(matrix4f, (float)eyePos.f_82479_ + (float)viewVector.f_82479_, (float)eyePos.f_82480_ + (float)viewVector.f_82480_, (float)eyePos.f_82481_ + (float)viewVector.f_82481_).m_6122_(0, 0, 255, 255).m_252939_(matrix3f, (float)viewVector.f_82479_, (float)viewVector.f_82480_, (float)viewVector.f_82481_).m_5752_();
/*     */       } 
/* 826 */       if (storm.getPhase() > 4 && storm.partsEnabled)
/*     */       {
/* 828 */         for (Section part : storm.getSections()) {
/*     */           
/* 830 */           if (part.isActive())
/* 831 */             LevelRenderer.m_109646_(stack, consumer, part.getBoundingBox(), part.getColor()[0], part.getColor()[1], part.getColor()[2], 1.0F); 
/*     */         } 
/*     */       }
/* 834 */       stack.m_85849_();
/*     */     } 
/*     */   }
/*     */   
/*     */   public abstract M fetchModel(T paramT);
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\AbstractWitherStormRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
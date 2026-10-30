/*     */ package nonamecrackers2.witherstormmod.client.capability;
/*     */ 
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.CrashReport;
/*     */ import net.minecraft.CrashReportCategory;
/*     */ import net.minecraft.ReportedException;
/*     */ import net.minecraft.client.Camera;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraft.client.renderer.FogRenderer;
/*     */ import net.minecraft.client.renderer.GameRenderer;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.culling.Frustum;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.client.event.RenderLevelStageEvent;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import net.minecraftforge.server.timings.TimeTracker;
/*     */ import nonamecrackers2.crackerslib.common.compat.CompatHelper;
/*     */ import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.blockentity.AbstractSuperBeaconRenderer;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.AbstractWitherStormRenderer;
/*     */ import nonamecrackers2.witherstormmod.client.util.SuperBeaconDistantInstance;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.mixin.IMixinGameRenderer;
/*     */ import org.joml.Matrix4f;
/*     */ import org.joml.Matrix4fc;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WitherStormDistantRenderer
/*     */ {
/*  54 */   private final Int2ObjectMap<WitherStormEntity> stormsToRender = (Int2ObjectMap<WitherStormEntity>)new Int2ObjectOpenHashMap();
/*     */   private final Minecraft minecraft;
/*     */   private final EntityRenderDispatcher manager;
/*  57 */   private final Map<BlockPos, SuperBeaconDistantInstance> superBeacons = Maps.newHashMap();
/*     */ 
/*     */   
/*     */   public WitherStormDistantRenderer(Minecraft minecraft) {
/*  61 */     this.minecraft = minecraft;
/*  62 */     this.manager = minecraft.m_91290_();
/*     */   }
/*     */ 
/*     */   
/*     */   public WitherStormDistantRenderer() {
/*  67 */     this.minecraft = null;
/*  68 */     this.manager = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  73 */     ClientLevel world = this.minecraft.f_91073_;
/*  74 */     if (world != null) {
/*     */       
/*  76 */       world.m_46473_().m_6180_("distantWitherStorms");
/*  77 */       this.stormsToRender.forEach((id, entity) -> {
/*     */             WitherStormEntity storm = entity;
/*     */             if (!storm.m_213877_() && !storm.m_20159_()) {
/*     */               guardEntityTick(this::tickEntity, storm);
/*     */             }
/*     */           });
/*  83 */       removeAllPendingEntityRemovals();
/*  84 */       world.m_46473_().m_7238_();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void tickEntity(WitherStormEntity entity) {
/*  90 */     entity.m_146867_();
/*  91 */     entity.f_19797_++;
/*  92 */     this.minecraft.f_91073_.m_46473_().m_6521_(() -> ForgeRegistries.ENTITY_TYPES.getKey(entity.m_6095_()).toString());
/*     */ 
/*     */     
/*  95 */     if (entity.canUpdate())
/*     */     {
/*  97 */       if (entity.m_9236_().m_6815_(entity.m_19879_()) == null)
/*  98 */         entity.m_8119_(); 
/*     */     }
/* 100 */     this.minecraft.f_91073_.m_46473_().m_7238_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void guardEntityTick(Consumer<WitherStormEntity> consumer, WitherStormEntity entity) {
/*     */     try {
/* 107 */       TimeTracker.ENTITY_UPDATE.trackStart(entity);
/* 108 */       consumer.accept(entity);
/*     */     }
/* 110 */     catch (Throwable t) {
/*     */       
/* 112 */       CrashReport report = CrashReport.m_127521_(t, "Distant Ticking WitherStormEntity");
/* 113 */       CrashReportCategory category = report.m_127514_("Trying to tick distant WitherStormEntity on client");
/* 114 */       entity.m_7976_(category);
/* 115 */       throw new ReportedException(report);
/*     */     }
/*     */     finally {
/*     */       
/* 119 */       TimeTracker.ENTITY_UPDATE.trackEnd(entity);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAllPendingEntityRemovals() {
/* 125 */     ObjectIterator<Int2ObjectMap.Entry<WitherStormEntity>> iterator = this.stormsToRender.int2ObjectEntrySet().iterator();
/*     */     
/* 127 */     while (iterator.hasNext()) {
/*     */       
/* 129 */       Int2ObjectMap.Entry<WitherStormEntity> entry = (Int2ObjectMap.Entry<WitherStormEntity>)iterator.next();
/* 130 */       WitherStormEntity entity = (WitherStormEntity)entry.getValue();
/* 131 */       if (entity.m_213877_()) {
/* 132 */         iterator.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void renderTick(PoseStack stack, MultiBufferSource.BufferSource buffer, float partialTicks, Frustum clippinghelper) {
/* 138 */     if (this.minecraft.f_91073_ != null) {
/*     */       
/* 140 */       GameRenderer renderer = this.minecraft.f_91063_;
/* 141 */       Camera camera = renderer.m_109153_();
/* 142 */       boolean distantFog = ((Boolean)WitherStormModConfig.CLIENT.distantFog.get()).booleanValue();
/* 143 */       Vec3 pos = camera.m_90583_();
/* 144 */       if (distantFog) {
/*     */         
/* 146 */         boolean flag = (this.minecraft.f_91073_.m_104583_().m_5781_(Mth.m_14107_(pos.f_82479_), Mth.m_14107_(pos.f_82480_)) || this.minecraft.f_91065_.m_93090_().m_93715_());
/* 147 */         float f = renderer.m_109152_();
/* 148 */         FogRenderer.m_109018_(camera, partialTicks, this.minecraft.f_91073_, ((Integer)this.minecraft.f_91066_.m_231984_().m_231551_()).intValue(), 1.0F);
/* 149 */         FogRenderer.m_109036_();
/* 150 */         FogRenderer.m_234172_(camera, FogRenderer.FogMode.FOG_TERRAIN, Math.max(f - 16.0F, 32.0F), flag, partialTicks);
/*     */       } 
/*     */       
/* 153 */       this.minecraft.f_91073_.m_46473_().m_6182_("distant_wither_storms");
/* 154 */       for (ObjectIterator<Int2ObjectMap.Entry<WitherStormEntity>> objectIterator = this.stormsToRender.int2ObjectEntrySet().iterator(); objectIterator.hasNext(); ) { Int2ObjectMap.Entry<WitherStormEntity> entry = objectIterator.next();
/*     */         
/* 156 */         WitherStormEntity entity = (WitherStormEntity)entry.getValue();
/* 157 */         if (this.manager.m_114397_((Entity)entity, clippinghelper, pos.f_82479_, pos.f_82480_, pos.f_82481_) && this.minecraft.f_91073_.m_6815_(entity.m_19879_()) == null) {
/*     */           
/* 159 */           float f1 = Mth.m_14179_(partialTicks, entity.f_19859_, entity.m_146908_());
/* 160 */           double x = Mth.m_14139_(partialTicks, entity.f_19790_, entity.m_20185_());
/* 161 */           double y = Mth.m_14139_(partialTicks, entity.f_19791_, entity.m_20186_());
/* 162 */           double z = Mth.m_14139_(partialTicks, entity.f_19792_, entity.m_20189_());
/* 163 */           int packedLight = this.manager.m_114394_((Entity)entity, partialTicks);
/* 164 */           this.manager.m_114384_((Entity)entity, x - pos.f_82479_, y - pos.f_82480_, z - pos.f_82481_, f1, partialTicks, stack, (MultiBufferSource)buffer, packedLight);
/*     */         }  }
/*     */       
/* 167 */       renderStorms(clippinghelper, pos, partialTicks, stack, (MultiBufferSource)buffer, (s, r) -> {
/*     */             if (((Boolean)WitherStormModConfig.CLIENT.renderDebrisRings.get()).booleanValue() && (!((Boolean)WitherStormModConfig.CLIENT.hideDebrisRingsUntilSplit.get()).booleanValue() || s.getPhase() > 5)) {
/*     */               r.renderDebrisRings(s, stack, (MultiBufferSource)buffer, partialTicks, this.manager.m_114394_((Entity)s, partialTicks));
/*     */             }
/*     */           });
/* 172 */       buffer.m_109911_();
/* 173 */       if (distantFog) {
/* 174 */         FogRenderer.m_109017_();
/*     */       }
/* 176 */       renderStorms(clippinghelper, pos, partialTicks, stack, (MultiBufferSource)buffer, (s, r) -> {
/*     */             if (((Boolean)WitherStormModConfig.CLIENT.renderTractorBeams.get()).booleanValue() && (!s.m_20096_() || !s.isPlayingDead())) {
/*     */               int packedLight = this.manager.m_114394_((Entity)s, partialTicks);
/*     */               
/*     */               r.prepareHeadAnimsForTractorBeams(s, partialTicks);
/*     */               
/*     */               r.renderTractorBeams(s, stack, (MultiBufferSource)buffer, partialTicks, packedLight);
/*     */             } 
/*     */           });
/* 185 */       renderStorms(clippinghelper, pos, partialTicks, stack, (MultiBufferSource)buffer, (s, r) -> {
/*     */             if (((Boolean)WitherStormModConfig.CLIENT.renderShine.get()).booleanValue() && s.shouldShine()) {
/*     */               AbstractWitherStormRenderer.renderShine(s, stack, partialTicks, this.minecraft.f_91063_.m_109153_(), (MultiBufferSource)buffer);
/*     */             }
/*     */           });
/*     */       
/* 191 */       for (Map.Entry<BlockPos, SuperBeaconDistantInstance> entry : this.superBeacons.entrySet()) {
/*     */         
/* 193 */         SuperBeaconDistantInstance instance = entry.getValue();
/* 194 */         stack.m_85836_();
/* 195 */         stack.m_85837_(instance.pos.m_123341_() - pos.f_82479_, instance.pos.m_123342_() - pos.f_82480_, instance.pos.m_123343_() - pos.f_82481_);
/* 196 */         float camDist = (float)(pos.m_82554_(Vec3.m_82512_((Vec3i)instance.pos)) - (((Integer)this.minecraft.f_91066_.m_231984_().m_231551_()).intValue() * 16.0F));
/* 197 */         float widthMul = (float)Math.max(1.0D, camDist * 0.01D);
/* 198 */         AbstractSuperBeaconRenderer.renderBeam(instance.active, stack, instance.color, (MultiBufferSource)buffer, (camDist > 0.0F) ? 0.0F : partialTicks, (camDist > 0.0F) ? 0L : this.minecraft.f_91073_.m_46467_(), instance.beaconHeight, instance.beamWidth * widthMul, instance.outerBeamWidth * widthMul);
/* 199 */         stack.m_85849_();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private <T extends WitherStormEntity, M extends nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.AbstractWitherStormModel<T>> void renderStorms(Frustum frustum, Vec3 cameraPos, float partialTicks, PoseStack stack, MultiBufferSource buffer, BiConsumer<T, AbstractWitherStormRenderer<T, M>> action) {
/* 206 */     for (ObjectIterator<Int2ObjectMap.Entry<WitherStormEntity>> objectIterator = this.stormsToRender.int2ObjectEntrySet().iterator(); objectIterator.hasNext(); ) { Int2ObjectMap.Entry<WitherStormEntity> entry = objectIterator.next();
/*     */ 
/*     */       
/* 209 */       WitherStormEntity witherStormEntity = (WitherStormEntity)entry.getValue();
/* 210 */       if (this.manager.m_114397_((Entity)witherStormEntity, frustum, cameraPos.f_82479_, cameraPos.f_82480_, cameraPos.f_82481_) && this.minecraft.f_91073_.m_6815_(witherStormEntity.m_19879_()) == null) {
/*     */         
/* 212 */         AbstractWitherStormRenderer<T, M> renderer = AbstractWitherStormRenderer.getRenderer(witherStormEntity, this.manager);
/* 213 */         if (renderer != null) {
/*     */           
/* 215 */           double x = Mth.m_14139_(partialTicks, witherStormEntity.f_19790_, witherStormEntity.m_20185_());
/* 216 */           double y = Mth.m_14139_(partialTicks, witherStormEntity.f_19791_, witherStormEntity.m_20186_());
/* 217 */           double z = Mth.m_14139_(partialTicks, witherStormEntity.f_19792_, witherStormEntity.m_20189_());
/* 218 */           stack.m_85836_();
/* 219 */           stack.m_85837_(x - cameraPos.f_82479_, y - cameraPos.f_82480_, z - cameraPos.f_82481_);
/* 220 */           renderer.updateModel(witherStormEntity);
/* 221 */           action.accept((T)witherStormEntity, renderer);
/* 222 */           stack.m_85849_();
/*     */         } 
/*     */       }  }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public void addWitherStorm(int id, WitherStormEntity entity) {
/* 230 */     this.stormsToRender.put(id, entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public WitherStormEntity get(int id) {
/* 235 */     return (WitherStormEntity)this.stormsToRender.get(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(int id) {
/* 240 */     return this.stormsToRender.containsKey(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterable<WitherStormEntity> getKnown() {
/* 245 */     return Iterables.unmodifiableIterable((Iterable)this.stormsToRender.values());
/*     */   }
/*     */ 
/*     */   
/*     */   public void addAndOrUpdateSuperBeacon(BlockPos pos, int[] color, boolean active, int beaconHeight, float beamWidth, float outerBeamWidth) {
/* 250 */     SuperBeaconDistantInstance instance = this.superBeacons.computeIfAbsent(pos, p -> new SuperBeaconDistantInstance(p, color));
/* 251 */     instance.color = color;
/* 252 */     instance.active = active;
/* 253 */     instance.beaconHeight = beaconHeight;
/* 254 */     instance.beamWidth = beamWidth;
/* 255 */     instance.outerBeamWidth = outerBeamWidth;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeSuperBeacon(BlockPos pos) {
/* 260 */     this.superBeacons.remove(pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<WitherStormEntity> getAllStorms(ClientLevel level) {
/* 265 */     List<WitherStormEntity> storms = Lists.newArrayList();
/* 266 */     level.m_104735_().forEach(entity -> {
/*     */           if (entity instanceof WitherStormEntity) {
/*     */             WitherStormEntity storm = (WitherStormEntity)entity; storms.add(storm);
/*     */           } 
/* 270 */         }); level.getCapability(WitherStormModClientCapabilities.DISTANT_RENDERER).ifPresent(renderer -> {
/*     */           List<Integer> ids = (List<Integer>)storms.stream().collect(Collectors.mapping(Entity::m_19879_, Collectors.toList()));
/*     */ 
/*     */           
/*     */           renderer.getKnown().forEach(());
/*     */         });
/*     */     
/* 277 */     return storms;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void setClipPlanes(Matrix4f mat, float near, float far) {
/* 283 */     mat.set(2, 2, -((far + near) / (far - near))).set(3, 2, -(2.0F * far * near / (far - near)));
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Events
/*     */   {
/*     */     @SubscribeEvent
/*     */     public static void renderTickDistantRenderer(RenderLevelStageEvent event) {
/* 291 */       if (event.getStage().equals(RenderLevelStageEvent.Stage.AFTER_PARTICLES)) {
/* 292 */         render(event.getPoseStack(), event.getPartialTick());
/*     */       }
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void clientTickDistantRenderer(TickEvent.ClientTickEvent event) {
/* 298 */       Minecraft mc = Minecraft.m_91087_();
/* 299 */       if (event.phase == TickEvent.Phase.START) {
/*     */         
/* 301 */         ClientLevel world = mc.f_91073_;
/* 302 */         if (world != null)
/*     */         {
/* 304 */           world.getCapability(WitherStormModClientCapabilities.DISTANT_RENDERER).ifPresent(distantRenderer -> {
/*     */                 if ((!mc.m_91104_() || !mc.m_91091_()) && ((Boolean)WitherStormModConfig.CLIENT.distantRenderer.get()).booleanValue()) {
/*     */                   distantRenderer.tick();
/*     */                 }
/*     */               });
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static void render(PoseStack stack, float partialTicks) {
/* 317 */       if (((Boolean)WitherStormModConfig.CLIENT.distantRenderer.get()).booleanValue()) {
/*     */         
/* 319 */         Minecraft mc = Minecraft.m_91087_();
/* 320 */         ClientLevel world = mc.f_91073_;
/* 321 */         world.getCapability(WitherStormModClientCapabilities.DISTANT_RENDERER).ifPresent(distantRenderer -> {
/*     */               Matrix4f originalMatrix = RenderSystem.getProjectionMatrix();
/*     */               GameRenderer renderer = mc.f_91063_;
/*     */               Camera renderInfo = renderer.m_109153_();
/*     */               Matrix4f projection = null;
/*     */               if (!CompatHelper.isVrActive()) {
/*     */                 double fov = ((IMixinGameRenderer)renderer).callGetFov(renderInfo, partialTicks, true);
/*     */                 projection = (new Matrix4f()).perspective((float)(fov * 0.01745329238474369D), mc.m_91268_().m_85441_() / mc.m_91268_().m_85442_(), 0.05F, renderer.m_109152_() * 180.0F);
/*     */                 Matrix4f defaultProjection = renderer.m_253088_(fov);
/*     */                 Matrix4f invertedDefaultProjection = new Matrix4f((Matrix4fc)defaultProjection);
/*     */                 invertedDefaultProjection.invert();
/*     */                 Matrix4f distortionMatrix = new Matrix4f((Matrix4fc)invertedDefaultProjection);
/*     */                 distortionMatrix.mul((Matrix4fc)originalMatrix);
/*     */                 projection.mul((Matrix4fc)distortionMatrix);
/*     */               } else {
/*     */                 projection = new Matrix4f((Matrix4fc)originalMatrix);
/*     */                 WitherStormDistantRenderer.setClipPlanes(projection, 0.05F, renderer.m_109152_() * 180.0F);
/*     */               } 
/*     */               renderer.m_252879_(projection);
/*     */               Vec3 pos = renderInfo.m_90583_();
/*     */               MultiBufferSource.BufferSource buffer = mc.m_91269_().m_110104_();
/*     */               Frustum clippinghelper = new Frustum(stack.m_85850_().m_252922_(), projection);
/*     */               clippinghelper.m_113002_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_());
/*     */               distantRenderer.renderTick(stack, buffer, partialTicks, clippinghelper);
/*     */               buffer.m_109911_();
/*     */               renderer.m_252879_(originalMatrix);
/*     */             });
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\capability\WitherStormDistantRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
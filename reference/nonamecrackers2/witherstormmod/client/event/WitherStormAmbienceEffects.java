/*     */ package nonamecrackers2.witherstormmod.client.event;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.function.Function;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.client.event.ViewportEvent;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import nonamecrackers2.witherstormmod.client.capability.WitherStormDistantRenderer;
/*     */ import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.AbstractWitherStormRenderer;
/*     */ import nonamecrackers2.witherstormmod.client.resources.WitherStormResourceConfigManager;
/*     */ import nonamecrackers2.witherstormmod.client.resources.color.SkyColorSet;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WitherStormAmbienceEffects
/*     */ {
/*     */   private static final int COLOR_TRANSITION = 40;
/*     */   private final Minecraft mc;
/*  33 */   private float alpha = 1.0F;
/*  34 */   private float alphaO = 1.0F;
/*     */   
/*     */   private int colorTransitionTime;
/*  37 */   private SkyColorSet current = SkyColorSet.DEFAULT_SKY_COLORS;
/*  38 */   private SkyColorSet previous = SkyColorSet.DEFAULT_SKY_COLORS;
/*     */ 
/*     */   
/*     */   public WitherStormAmbienceEffects(Minecraft mc) {
/*  42 */     this.mc = mc;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  47 */     if (((Boolean)WitherStormModConfig.CLIENT.renderSkyAmbienceEffects.get()).booleanValue()) {
/*     */       
/*  49 */       List<WitherStormEntity> storms = getApplicableEntities(this.mc.f_91073_);
/*  50 */       Vec3 cameraPos = this.mc.f_91063_.m_109153_().m_90583_();
/*  51 */       WitherStormEntity storm = storms.stream().sorted(Comparator.<WitherStormEntity>comparingInt(WitherStormEntity::getConsumedEntities).reversed()).findFirst().orElse(null);
/*  52 */       this.alphaO = this.alpha;
/*  53 */       if (storm != null && !storm.isDeadOrPlayingDead()) {
/*     */         
/*  55 */         float alpha = modifyEffectMagnitude(storm, Mth.m_14036_((float)(cameraPos.m_82554_(storm.m_20182_()) - 200.0D) * 0.005F, 0.0F, 1.0F));
/*  56 */         this.alpha += (alpha - this.alpha) / 25.0F;
/*     */         
/*  58 */         SkyColorSet set = WitherStormResourceConfigManager.INSTANCE.getColorSetByPhase(storm.getPhase()).skyColors();
/*  59 */         if (set != null && !set.equals(this.current))
/*     */         {
/*  61 */           this.previous = this.current;
/*  62 */           this.current = set;
/*  63 */           this.colorTransitionTime = 40;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/*  68 */         this.alpha += (1.0F - this.alpha) / 100.0F;
/*     */       } 
/*  70 */       if (this.colorTransitionTime > 0) {
/*  71 */         this.colorTransitionTime--;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public float lerpAlpha(float partialTicks) {
/*  77 */     return Mth.m_14179_(partialTicks, this.alphaO, this.alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   public Color lerpColorsByTransition(Function<SkyColorSet, Color> dayColorGetter, Function<SkyColorSet, Color> nightColorGetter, float partialTicks) {
/*  82 */     float lerp = Mth.m_14036_((this.colorTransitionTime - partialTicks) / 40.0F, 0.0F, 1.0F);
/*  83 */     Color previous = lerpNightColors(dayColorGetter.apply(this.previous), nightColorGetter.apply(this.previous), partialTicks);
/*  84 */     Color current = lerpNightColors(dayColorGetter.apply(this.current), nightColorGetter.apply(this.current), partialTicks);
/*     */     
/*  86 */     int r = Mth.m_14143_(Mth.m_14179_(lerp, current.getRed(), previous.getRed()));
/*  87 */     int g = Mth.m_14143_(Mth.m_14179_(lerp, current.getGreen(), previous.getGreen()));
/*  88 */     int b = Mth.m_14143_(Mth.m_14179_(lerp, current.getBlue(), previous.getBlue()));
/*  89 */     int a = Mth.m_14143_(Mth.m_14179_(lerp, current.getAlpha(), previous.getAlpha()));
/*     */     
/*  91 */     return new Color(r, g, b, a);
/*     */   }
/*     */ 
/*     */   
/*     */   private static Color lerpNightColors(Color day, @Nullable Color night, float partialTicks) {
/*  96 */     if (night != null) {
/*     */       
/*  98 */       float nightLerp = AbstractWitherStormRenderer.getNightTimeLerp((Level)(Minecraft.m_91087_()).f_91073_, partialTicks);
/*  99 */       int r = Mth.m_14143_(Mth.m_14179_(nightLerp, day.getRed(), night.getRed()));
/* 100 */       int g = Mth.m_14143_(Mth.m_14179_(nightLerp, day.getGreen(), night.getGreen()));
/* 101 */       int b = Mth.m_14143_(Mth.m_14179_(nightLerp, day.getBlue(), night.getBlue()));
/* 102 */       int a = Mth.m_14143_(Mth.m_14179_(nightLerp, day.getAlpha(), night.getAlpha()));
/* 103 */       return new Color(r, g, b, a);
/*     */     } 
/*     */ 
/*     */     
/* 107 */     return day;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static List<WitherStormEntity> getApplicableEntities(ClientLevel level) {
/* 113 */     return (List<WitherStormEntity>)WitherStormDistantRenderer.getAllStorms(level).stream().filter(entity -> !(entity instanceof nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity)).collect(Collectors.toList());
/*     */   }
/*     */ 
/*     */   
/*     */   public static float modifyEffectMagnitude(WitherStormEntity storm, float alpha) {
/* 118 */     if (storm.getPhase() < 5) {
/* 119 */       return 1.0F;
/*     */     }
/*     */     
/* 122 */     if (storm.getPhase() == 5) {
/* 123 */       return Math.min(alpha + Mth.m_14036_(1.0F - storm.getPhaseProgress(), 0.15F, 1.0F), 1.0F);
/*     */     }
/* 125 */     return Math.min(alpha + 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vec3 modifySkyColor(Minecraft mc, Vec3 original, Vec3 cameraPos, float partialTicks) {
/* 131 */     return transitionColor(mc, cameraPos, original, SkyColorSet::skyColor, SkyColorSet::nightSkyColor, 200.0D, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float modifySkyDarken(Minecraft mc, Vec3 cameraPos, float original, float partialTicks) {
/* 136 */     WitherStormAmbienceEffects effects = (WitherStormAmbienceEffects)mc.f_91073_.getCapability(WitherStormModClientCapabilities.AMBIENT_EFFECTS).orElse(null);
/* 137 */     if (effects != null) {
/* 138 */       return original * Math.min(effects.lerpAlpha(partialTicks) + 0.4F, 1.0F);
/*     */     }
/* 140 */     return original;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Vec3 modifyCloudColors(Minecraft mc, Vec3 cameraPos, Vec3 original, float partialTicks) {
/* 145 */     return transitionColor(mc, cameraPos, original, SkyColorSet::cloudColor, SkyColorSet::nightCloudColor, 200.0D, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   private static Vec3 transitionColor(Minecraft mc, Vec3 cameraPos, Vec3 original, Function<SkyColorSet, Color> dayColorGetter, Function<SkyColorSet, Color> nightColorGetter, double distance, float partialTicks) {
/* 150 */     WitherStormAmbienceEffects effects = (WitherStormAmbienceEffects)mc.f_91073_.getCapability(WitherStormModClientCapabilities.AMBIENT_EFFECTS).orElse(null);
/* 151 */     if (effects != null) {
/*     */       
/* 153 */       float alpha = effects.lerpAlpha(partialTicks);
/* 154 */       Color col = effects.lerpColorsByTransition(dayColorGetter, nightColorGetter, partialTicks);
/* 155 */       int[] color = { col.getRed(), col.getGreen(), col.getBlue() };
/* 156 */       double rDelta = original.f_82479_ * 255.0D - color[0];
/* 157 */       double gDelta = original.f_82480_ * 255.0D - color[1];
/* 158 */       double bDelta = original.f_82481_ * 255.0D - color[2];
/* 159 */       color[0] = (int)(color[0] + rDelta * alpha);
/* 160 */       color[1] = (int)(color[1] + gDelta * alpha);
/* 161 */       color[2] = (int)(color[2] + bDelta * alpha);
/* 162 */       return new Vec3(color[0] / 255.0D, color[1] / 255.0D, color[2] / 255.0D);
/*     */     } 
/*     */ 
/*     */     
/* 166 */     return original;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Events
/*     */   {
/*     */     @SubscribeEvent
/*     */     public static void onWorldTick(TickEvent.ClientTickEvent event) {
/* 175 */       if (event.phase == TickEvent.Phase.START) {
/*     */         
/* 177 */         Minecraft mc = Minecraft.m_91087_();
/* 178 */         ClientLevel level = mc.f_91073_;
/* 179 */         if (level != null && !mc.m_91104_()) {
/* 180 */           level.getCapability(WitherStormModClientCapabilities.AMBIENT_EFFECTS).ifPresent(WitherStormAmbienceEffects::tick);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void fogColor(ViewportEvent.ComputeFogColor event) {
/* 187 */       if (((Boolean)WitherStormModConfig.CLIENT.renderSkyAmbienceEffects.get()).booleanValue()) {
/*     */         
/* 189 */         Minecraft mc = Minecraft.m_91087_();
/* 190 */         mc.f_91073_.getCapability(WitherStormModClientCapabilities.AMBIENT_EFFECTS).ifPresent(effects -> {
/*     */               float alpha = effects.lerpAlpha((float)event.getPartialTick());
/*     */               Color fog = effects.lerpColorsByTransition(SkyColorSet::fogColor, SkyColorSet::nightFogColor, (float)event.getPartialTick());
/*     */               int[] color = { fog.getRed(), fog.getGreen(), fog.getBlue() };
/*     */               float rDelta = event.getRed() * 255.0F - color[0];
/*     */               float gDelta = event.getGreen() * 255.0F - color[1];
/*     */               float bDelta = event.getBlue() * 255.0F - color[2];
/*     */               color[0] = (int)(color[0] + rDelta * alpha);
/*     */               color[1] = (int)(color[1] + gDelta * alpha);
/*     */               color[2] = (int)(color[2] + bDelta * alpha);
/*     */               event.setRed(color[0] / 255.0F);
/*     */               event.setGreen(color[1] / 255.0F);
/*     */               event.setBlue(color[2] / 255.0F);
/*     */             });
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\event\WitherStormAmbienceEffects.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package nonamecrackers2.witherstormmod.mixin;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.client.event.WitherStormAmbienceEffects;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.gen.Accessor;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.Redirect;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*     */ import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mixin({ClientLevel.class})
/*     */ public abstract class MixinClientLevel
/*     */ {
/*     */   @Redirect(method = {"getSkyColor"}, at = @At(value = "NEW", target = "(DDD)Lnet/minecraft/world/phys/Vec3;"))
/*     */   public Vec3 redirectGetSkyColor(double r, double g, double b, Vec3 cameraPos, float partialTicks) {
/*  28 */     Vec3 color = new Vec3(r, g, b);
/*  29 */     if (((Boolean)WitherStormModConfig.CLIENT.renderSkyAmbienceEffects.get()).booleanValue()) {
/*  30 */       return WitherStormAmbienceEffects.modifySkyColor(getMinecraft(), color, cameraPos, partialTicks);
/*     */     }
/*  32 */     return color;
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
/*     */   @Inject(method = {"getSkyDarken"}, at = {@At("RETURN")}, cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
/*     */   public void redirectGetSkyDarken(float partialTicks, CallbackInfoReturnable<Float> ci, float f, float f1) {
/* 107 */     float toReturn = f1 * 0.8F + 0.2F;
/* 108 */     if (((Boolean)WitherStormModConfig.CLIENT.renderSkyAmbienceEffects.get()).booleanValue()) {
/*     */       
/* 110 */       Minecraft mc = getMinecraft();
/* 111 */       ci.setReturnValue(Float.valueOf(WitherStormAmbienceEffects.modifySkyDarken(mc, mc.f_91063_.m_109153_().m_90583_(), toReturn, 0.0F)));
/*     */     } 
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
/*     */   @Redirect(method = {"getCloudColor"}, at = @At(value = "NEW", target = "(DDD)Lnet/minecraft/world/phys/Vec3;"))
/*     */   public Vec3 redirectGetCloudColor(double r, double g, double b, float partialTicks) {
/* 145 */     Vec3 color = new Vec3(r, g, b);
/* 146 */     if (((Boolean)WitherStormModConfig.CLIENT.renderSkyAmbienceEffects.get()).booleanValue()) {
/*     */       
/* 148 */       Minecraft mc = Minecraft.m_91087_();
/* 149 */       return WitherStormAmbienceEffects.modifyCloudColors(mc, mc.f_91063_.m_109153_().m_90583_(), color, partialTicks);
/*     */     } 
/*     */ 
/*     */     
/* 153 */     return color;
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
/*     */   @Inject(method = {"shouldTickDeath"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void shouldTickDeathHead(Entity entity, CallbackInfoReturnable<Boolean> ci) {
/* 211 */     if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity; if (storm.isOnDistantRenderer())
/* 212 */         ci.setReturnValue(Boolean.valueOf(true));  }
/*     */   
/*     */   }
/*     */   
/*     */   @Accessor
/*     */   public abstract Minecraft getMinecraft();
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinClientLevel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
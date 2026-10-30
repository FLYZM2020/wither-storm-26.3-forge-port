/*    */ package nonamecrackers2.witherstormmod.client.particle;
/*    */ 
/*    */ import net.minecraft.client.multiplayer.ClientLevel;
/*    */ import net.minecraft.client.particle.Particle;
/*    */ import net.minecraft.client.particle.ParticleProvider;
/*    */ import net.minecraft.client.particle.ParticleRenderType;
/*    */ import net.minecraft.client.particle.SimpleAnimatedParticle;
/*    */ import net.minecraft.client.particle.SpriteSet;
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.common.particle.TractorBeamParticleOptions;
/*    */ import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
/*    */ 
/*    */ public class TractorBeamParticle
/*    */   extends SimpleAnimatedParticle
/*    */ {
/*    */   private final int storm;
/*    */   private final int head;
/*    */   private Vec3 pos;
/*    */   
/*    */   public TractorBeamParticle(int storm, int head, ClientLevel world, double x, double y, double z, double dX, double dY, double dZ, SpriteSet sprite) {
/* 24 */     super(world, x, y, z, sprite, 0.0F);
/* 25 */     this.storm = storm;
/* 26 */     this.head = head;
/* 27 */     this.f_107215_ = dX;
/* 28 */     this.f_107216_ = dY;
/* 29 */     this.f_107217_ = dZ;
/* 30 */     this.f_172258_ = 1.0F;
/* 31 */     this.f_107225_ = 20 + this.f_107223_.m_188503_(30);
/* 32 */     this.f_107663_ = 0.1F;
/* 33 */     this.f_107219_ = false;
/* 34 */     this.pos = new Vec3(this.f_107212_, this.f_107213_, this.f_107214_);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ParticleRenderType m_7556_() {
/* 40 */     return ParticleRenderType.f_107432_;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_5989_() {
/* 46 */     super.m_5989_();
/*    */     
/* 48 */     this.pos = new Vec3(this.f_107212_, this.f_107213_, this.f_107214_);
/*    */     
/* 50 */     if (!canExist()) {
/* 51 */       m_107274_();
/*    */     }
/*    */   }
/*    */   
/*    */   public <T extends LivingEntity & nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase> boolean canExist() {
/* 56 */     Entity entity = this.f_107208_.m_6815_(this.storm);
/* 57 */     if (entity instanceof LivingEntity && entity instanceof nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase) {
/*    */ 
/*    */       
/* 60 */       LivingEntity livingEntity = (LivingEntity)entity;
/* 61 */       if (TractorBeamHelper.isInsideTractorBeam(this.pos, livingEntity, 4.0D, this.head))
/* 62 */         return true; 
/*    */     } 
/* 64 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements ParticleProvider<TractorBeamParticleOptions>
/*    */   {
/*    */     private final SpriteSet sprites;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public Factory(SpriteSet sprites) {
/* 85 */       this.sprites = sprites;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public Particle createParticle(TractorBeamParticleOptions options, ClientLevel world, double x, double y, double z, double dX, double dY, double dZ) {
/* 91 */       TractorBeamParticle particle = new TractorBeamParticle(options.storm(), options.head(), world, x, y, z, dX, dY, dZ, this.sprites);
/* 92 */       particle.m_108335_(this.sprites);
/* 93 */       return (Particle)particle;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\particle\TractorBeamParticle.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
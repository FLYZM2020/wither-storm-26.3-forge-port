/*    */ package nonamecrackers2.witherstormmod.client.particle;
/*    */ 
/*    */ import net.minecraft.client.multiplayer.ClientLevel;
/*    */ import net.minecraft.client.particle.Particle;
/*    */ import net.minecraft.client.particle.ParticleProvider;
/*    */ import net.minecraft.client.particle.ParticleRenderType;
/*    */ import net.minecraft.client.particle.SimpleAnimatedParticle;
/*    */ import net.minecraft.client.particle.SpriteSet;
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.SimpleParticleType;
/*    */ 
/*    */ public class PhlegmBlockParticle
/*    */   extends SimpleAnimatedParticle {
/*    */   public PhlegmBlockParticle(ClientLevel world, double x, double y, double z, double dX, double dY, double dZ, SpriteSet sprite) {
/* 15 */     super(world, x, y, z, sprite, 0.0F);
/* 16 */     this.f_107215_ = dX;
/* 17 */     this.f_107216_ = dY;
/* 18 */     this.f_107217_ = dZ;
/* 19 */     this.f_107225_ = 45;
/* 20 */     this.f_107663_ = 0.1F * this.f_107223_.m_188501_();
/* 21 */     this.f_107219_ = false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ParticleRenderType m_7556_() {
/* 27 */     return ParticleRenderType.f_107430_;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements ParticleProvider<SimpleParticleType>
/*    */   {
/*    */     private final SpriteSet sprites;
/*    */     
/*    */     public Factory(SpriteSet sprites) {
/* 36 */       this.sprites = sprites;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double dX, double dY, double dZ) {
/* 42 */       PhlegmBlockParticle particle = new PhlegmBlockParticle(world, x, y, z, dX, dY, dZ, this.sprites);
/* 43 */       particle.m_108335_(this.sprites);
/* 44 */       return (Particle)particle;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\particle\PhlegmBlockParticle.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package nonamecrackers2.witherstormmod.client.particle;
/*    */ 
/*    */ import net.minecraft.client.multiplayer.ClientLevel;
/*    */ import net.minecraft.client.particle.Particle;
/*    */ import net.minecraft.client.particle.ParticleProvider;
/*    */ import net.minecraft.client.particle.SpriteSet;
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.SimpleParticleType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Factory
/*    */   implements ParticleProvider<SimpleParticleType>
/*    */ {
/*    */   private final SpriteSet sprites;
/*    */   
/*    */   public Factory(SpriteSet sprites) {
/* 36 */     this.sprites = sprites;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double dX, double dY, double dZ) {
/* 42 */     PhlegmBlockParticle particle = new PhlegmBlockParticle(world, x, y, z, dX, dY, dZ, this.sprites);
/* 43 */     particle.m_108335_(this.sprites);
/* 44 */     return (Particle)particle;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\particle\PhlegmBlockParticle$Factory.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
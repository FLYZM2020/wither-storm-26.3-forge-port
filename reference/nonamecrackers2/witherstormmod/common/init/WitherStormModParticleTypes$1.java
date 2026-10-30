/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.ParticleType;
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
/*    */ class null
/*    */   extends ParticleType<T>
/*    */ {
/*    */   null(boolean p_123740_, ParticleOptions.Deserializer<T> p_123741_) {
/* 28 */     super(p_123740_, p_123741_);
/*    */   }
/*    */   
/*    */   public Codec<T> m_7652_() {
/* 32 */     return codecGetter.apply(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModParticleTypes$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
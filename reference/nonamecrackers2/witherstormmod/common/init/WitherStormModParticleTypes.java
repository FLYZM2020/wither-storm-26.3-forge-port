/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.ParticleType;
/*    */ import net.minecraft.core.particles.SimpleParticleType;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ import nonamecrackers2.witherstormmod.common.particle.TractorBeamParticleOptions;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WitherStormModParticleTypes
/*    */ {
/* 18 */   public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, "witherstormmod");
/*    */   
/* 20 */   public static final RegistryObject<SimpleParticleType> COMMAND_BLOCK = PARTICLE_TYPES.register("command_block", () -> new SimpleParticleType(true));
/* 21 */   public static final RegistryObject<ParticleType<TractorBeamParticleOptions>> TRACTOR_BEAM = register("tractor_beam", false, TractorBeamParticleOptions.DESERIALIZIER, type -> TractorBeamParticleOptions.CODEC);
/* 22 */   public static final RegistryObject<SimpleParticleType> PHLEGM = PARTICLE_TYPES.register("phlegm", () -> new SimpleParticleType(true));
/*    */ 
/*    */ 
/*    */   
/*    */   public static <T extends ParticleOptions> RegistryObject<ParticleType<T>> register(String name, boolean overrideLimiter, ParticleOptions.Deserializer<T> deserializer, final Function<ParticleType<T>, Codec<T>> codecGetter) {
/* 27 */     return PARTICLE_TYPES.register(name, () -> new ParticleType<T>(overrideLimiter, deserializer)
/*    */         {
/*    */           
/*    */           public Codec<T> m_7652_()
/*    */           {
/* 32 */             return codecGetter.apply(this);
/*    */           }
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModParticleTypes.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
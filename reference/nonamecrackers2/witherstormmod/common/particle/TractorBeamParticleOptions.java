/*    */ package nonamecrackers2.witherstormmod.common.particle;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.ParticleType;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ 
/*    */ public final class TractorBeamParticleOptions extends Record implements ParticleOptions {
/*    */   private final int storm;
/*    */   private final int head;
/*    */   public static final Codec<TractorBeamParticleOptions> CODEC;
/*    */   
/* 14 */   public TractorBeamParticleOptions(int storm, int head) { this.storm = storm; this.head = head; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lnonamecrackers2/witherstormmod/common/particle/TractorBeamParticleOptions;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #14	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 14 */     //   0	7	0	this	Lnonamecrackers2/witherstormmod/common/particle/TractorBeamParticleOptions; } public int storm() { return this.storm; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lnonamecrackers2/witherstormmod/common/particle/TractorBeamParticleOptions;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #14	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lnonamecrackers2/witherstormmod/common/particle/TractorBeamParticleOptions; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lnonamecrackers2/witherstormmod/common/particle/TractorBeamParticleOptions;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #14	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lnonamecrackers2/witherstormmod/common/particle/TractorBeamParticleOptions;
/* 14 */     //   0	8	1	o	Ljava/lang/Object; } public int head() { return this.head; }
/*    */    static {
/* 16 */     CODEC = RecordCodecBuilder.create(instance -> instance.group((App)Codec.INT.fieldOf("storm").forGetter(TractorBeamParticleOptions::storm), (App)Codec.INT.fieldOf("head").forGetter(TractorBeamParticleOptions::head)).apply((Applicative)instance, TractorBeamParticleOptions::new));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 24 */   public static final ParticleOptions.Deserializer<TractorBeamParticleOptions> DESERIALIZIER = new ParticleOptions.Deserializer<TractorBeamParticleOptions>()
/*    */     {
/*    */       
/*    */       public TractorBeamParticleOptions fromCommand(ParticleType<TractorBeamParticleOptions> type, StringReader reader) throws CommandSyntaxException
/*    */       {
/* 29 */         return new TractorBeamParticleOptions(reader.readInt(), reader.readInt());
/*    */       }
/*    */ 
/*    */ 
/*    */       
/*    */       public TractorBeamParticleOptions fromNetwork(ParticleType<TractorBeamParticleOptions> type, FriendlyByteBuf buffer) {
/* 35 */         return new TractorBeamParticleOptions(buffer.m_130242_(), buffer.m_130242_());
/*    */       }
/*    */     };
/*    */ 
/*    */ 
/*    */   
/*    */   public ParticleType<?> m_6012_() {
/* 42 */     return (ParticleType)WitherStormModParticleTypes.TRACTOR_BEAM.get();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_7711_(FriendlyByteBuf buffer) {
/* 48 */     buffer.m_130130_(this.head);
/* 49 */     buffer.m_130130_(this.head);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String m_5942_() {
/* 55 */     return "" + ForgeRegistries.PARTICLE_TYPES.getKey(m_6012_()) + " " + ForgeRegistries.PARTICLE_TYPES.getKey(m_6012_()) + " " + this.storm;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\particle\TractorBeamParticleOptions.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
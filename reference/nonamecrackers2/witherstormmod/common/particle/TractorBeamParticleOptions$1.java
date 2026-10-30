/*    */ package nonamecrackers2.witherstormmod.common.particle;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.ParticleType;
/*    */ import net.minecraft.network.FriendlyByteBuf;
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
/*    */   implements ParticleOptions.Deserializer<TractorBeamParticleOptions>
/*    */ {
/*    */   public TractorBeamParticleOptions fromCommand(ParticleType<TractorBeamParticleOptions> type, StringReader reader) throws CommandSyntaxException {
/* 29 */     return new TractorBeamParticleOptions(reader.readInt(), reader.readInt());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TractorBeamParticleOptions fromNetwork(ParticleType<TractorBeamParticleOptions> type, FriendlyByteBuf buffer) {
/* 35 */     return new TractorBeamParticleOptions(buffer.m_130242_(), buffer.m_130242_());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\particle\TractorBeamParticleOptions$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
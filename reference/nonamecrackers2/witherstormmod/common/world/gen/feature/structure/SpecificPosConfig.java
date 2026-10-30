/*    */ package nonamecrackers2.witherstormmod.common.world.gen.feature.structure;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.world.level.ChunkPos;
/*    */ import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
/*    */ 
/*    */ public class SpecificPosConfig implements FeatureConfiguration {
/*    */   static {
/* 12 */     CODEC = RecordCodecBuilder.create(instance -> instance.group((App)BlockPos.f_121852_.fieldOf("position").forGetter(())).apply((Applicative)instance, SpecificPosConfig::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<SpecificPosConfig> CODEC;
/*    */   
/*    */   private final BlockPos pos;
/*    */ 
/*    */   
/*    */   public SpecificPosConfig(BlockPos pos) {
/* 22 */     this.pos = pos;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isInChunk(ChunkPos pos) {
/* 27 */     return (this.pos.m_123341_() >= pos.m_45604_() && this.pos.m_123341_() <= pos.m_45608_() && this.pos.m_123343_() >= pos.m_45605_() && this.pos.m_123343_() <= pos.m_45609_());
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos getPosition() {
/* 32 */     return this.pos;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\world\gen\feature\structure\SpecificPosConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
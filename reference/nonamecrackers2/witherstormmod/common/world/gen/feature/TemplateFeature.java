/*    */ package nonamecrackers2.witherstormmod.common.world.gen.feature;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.level.WorldGenLevel;
/*    */ import net.minecraft.world.level.chunk.ChunkGenerator;
/*    */ import net.minecraft.world.level.levelgen.feature.Feature;
/*    */ import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
/*    */ import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
/*    */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
/*    */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
/*    */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
/*    */ 
/*    */ 
/*    */ public abstract class TemplateFeature<C extends FeatureConfiguration>
/*    */   extends Feature<C>
/*    */ {
/*    */   public TemplateFeature(Codec<C> codec) {
/* 21 */     super(codec);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean m_142674_(FeaturePlaceContext<C> context) {
/* 27 */     WorldGenLevel level = context.m_159774_();
/* 28 */     if (level instanceof ServerLevel) {
/*    */       
/* 30 */       ServerLevel world = (ServerLevel)level;
/* 31 */       StructureTemplateManager manager = world.m_215082_();
/* 32 */       TemplateFeaturePiece piece = setupTemplate(context.m_159777_(), context.m_225041_(), manager);
/* 33 */       return placeWithTemplate(world, context.m_159775_(), context.m_225041_(), context.m_159777_(), (C)context.m_159778_(), piece);
/*    */     } 
/*    */ 
/*    */     
/* 37 */     return false;
/*    */   }
/*    */   protected abstract TemplateFeaturePiece setupTemplate(BlockPos paramBlockPos, RandomSource paramRandomSource, StructureTemplateManager paramStructureTemplateManager);
/*    */   protected abstract boolean placeWithTemplate(ServerLevel paramServerLevel, ChunkGenerator paramChunkGenerator, RandomSource paramRandomSource, BlockPos paramBlockPos, C paramC, TemplateFeaturePiece paramTemplateFeaturePiece);
/*    */   
/*    */   public static final class TemplateFeaturePiece extends Record { private final StructureTemplate template;
/*    */     private final StructurePlaceSettings settings;
/*    */     
/* 45 */     public TemplateFeaturePiece(StructureTemplate template, StructurePlaceSettings settings) { this.template = template; this.settings = settings; } public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lnonamecrackers2/witherstormmod/common/world/gen/feature/TemplateFeature$TemplateFeaturePiece;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #45	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/* 45 */       //   0	7	0	this	Lnonamecrackers2/witherstormmod/common/world/gen/feature/TemplateFeature$TemplateFeaturePiece; } public StructureTemplate template() { return this.template; } public final int hashCode() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lnonamecrackers2/witherstormmod/common/world/gen/feature/TemplateFeature$TemplateFeaturePiece;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #45	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lnonamecrackers2/witherstormmod/common/world/gen/feature/TemplateFeature$TemplateFeaturePiece; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lnonamecrackers2/witherstormmod/common/world/gen/feature/TemplateFeature$TemplateFeaturePiece;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #45	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lnonamecrackers2/witherstormmod/common/world/gen/feature/TemplateFeature$TemplateFeaturePiece;
/* 45 */       //   0	8	1	o	Ljava/lang/Object; } public StructurePlaceSettings settings() { return this.settings; }
/*    */      }
/*    */ 
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\world\gen\feature\TemplateFeature.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
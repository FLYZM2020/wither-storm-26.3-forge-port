/*    */ package nonamecrackers2.witherstormmod.common.world.gen.feature;
/*    */ 
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.level.WorldGenLevel;
/*    */ import net.minecraft.world.level.chunk.ChunkGenerator;
/*    */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface RemovableTemplateFeature<C extends net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration>
/*    */   extends RemovableFeature<C>
/*    */ {
/*    */   default boolean remove(WorldGenLevel reader, ChunkGenerator generator, RandomSource random, BlockPos pos, C config) {
/* 17 */     if (reader instanceof ServerLevel) {
/*    */       
/* 19 */       ServerLevel world = (ServerLevel)reader;
/* 20 */       StructureTemplateManager manager = world.m_215082_();
/* 21 */       TemplateFeature.TemplateFeaturePiece piece = setupRemovalTemplate(pos, random, manager);
/* 22 */       return placeWithTemplate(world, generator, random, pos, config, piece);
/*    */     } 
/*    */ 
/*    */     
/* 26 */     return false;
/*    */   }
/*    */   
/*    */   TemplateFeature.TemplateFeaturePiece setupRemovalTemplate(BlockPos paramBlockPos, RandomSource paramRandomSource, StructureTemplateManager paramStructureTemplateManager);
/*    */   
/*    */   boolean placeWithTemplate(ServerLevel paramServerLevel, ChunkGenerator paramChunkGenerator, RandomSource paramRandomSource, BlockPos paramBlockPos, C paramC, TemplateFeature.TemplateFeaturePiece paramTemplateFeaturePiece);
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\world\gen\feature\RemovableTemplateFeature.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
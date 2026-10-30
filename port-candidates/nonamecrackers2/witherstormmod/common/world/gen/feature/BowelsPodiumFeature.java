/*    */ package nonamecrackers2.witherstormmod.common.world.gen.feature;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.resources.Identifier;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.level.ServerLevelAccessor;
/*    */ import net.minecraft.world.level.block.Mirror;
/*    */ import net.minecraft.world.level.block.Rotation;
/*    */ import net.minecraft.world.level.chunk.ChunkGenerator;
/*    */ import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
/*    */ import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
/*    */ import net.minecraft.world.level.levelgen.structure.BoundingBox;
/*    */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
/*    */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
/*    */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
/*    */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
/*    */ import nonamecrackers2.witherstormmod.common.world.gen.feature.template.BlockIgnoreIrreplacableProcessor;
/*    */ 
/*    */ public class BowelsPodiumFeature
/*    */   extends TemplateFeature<NoneFeatureConfiguration> {
/*    */   private final Identifier templateId;
/*    */   
/*    */   public BowelsPodiumFeature(Codec<NoneFeatureConfiguration> codec, Identifier templateId) {
/* 26 */     super(codec);
/* 27 */     this.templateId = templateId;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected TemplateFeature.TemplateFeaturePiece setupTemplate(BlockPos pos, RandomSource random, StructureTemplateManager manager) {
/* 33 */     return new TemplateFeature.TemplateFeaturePiece(manager.getOrCreate(this.templateId), (new StructurePlaceSettings()).setRotation(Rotation.getRandom(RandomSource.create(pos.asLong()))).setMirror(Mirror.NONE).setRotationPivot(BlockPos.ZERO).addProcessor((StructureProcessor)BlockIgnoreIrreplacableProcessor.AIR));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean placeWithTemplate(ServerLevel world, ChunkGenerator generator, RandomSource random, BlockPos pos, NoneFeatureConfiguration config, TemplateFeature.TemplateFeaturePiece piece) {
/* 39 */     StructureTemplate template = piece.template();
/* 40 */     BoundingBox box = template.getBoundingBox(piece.settings(), pos);
/* 41 */     BlockPos blockPos1 = box.getCenter();
/* 42 */     int xOffset = pos.getX() - blockPos1.getX();
/* 43 */     int zOffset = pos.getZ() - blockPos1.getZ();
/* 44 */     BlockPos newPos = pos.offset(xOffset, -box.getYSpan(), zOffset);
/* 45 */     template.placeInWorld((ServerLevelAccessor)world, newPos, newPos, piece.settings(), random, 2);
/* 46 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\world\gen\feature\BowelsPodiumFeature.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
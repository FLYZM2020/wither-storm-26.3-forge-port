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
/*    */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
/*    */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
/*    */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
/*    */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
/*    */ import nonamecrackers2.witherstormmod.common.world.gen.feature.template.BlockIgnoreIrreplacableProcessor;
/*    */ 
/*    */ public class CommandBlockPodiumFeature
/*    */   extends TemplateFeature<NoneFeatureConfiguration> implements RemovableTemplateFeature<NoneFeatureConfiguration> {
/*    */   private final Identifier templateId;
/*    */   
/*    */   public CommandBlockPodiumFeature(Codec<NoneFeatureConfiguration> codec, Identifier templateId) {
/* 25 */     super(codec);
/* 26 */     this.templateId = templateId;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected TemplateFeature.TemplateFeaturePiece setupTemplate(BlockPos pos, RandomSource random, StructureTemplateManager manager) {
/* 32 */     StructureTemplate template = manager.getOrCreate(this.templateId);
/* 33 */     RandomSource newRandom = RandomSource.create(pos.asLong());
/* 34 */     StructurePlaceSettings settings = (new StructurePlaceSettings()).setRotation(Rotation.getRandom(newRandom)).setMirror(Mirror.NONE).setRotationPivot(BlockPos.ZERO).addProcessor((StructureProcessor)BlockIgnoreIrreplacableProcessor.AIR);
/* 35 */     return new TemplateFeature.TemplateFeaturePiece(template, settings);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TemplateFeature.TemplateFeaturePiece setupRemovalTemplate(BlockPos pos, RandomSource random, StructureTemplateManager manager) {
/* 41 */     StructureTemplate template = manager.getOrCreate(this.templateId);
/* 42 */     RandomSource newRandom = RandomSource.create(pos.asLong());
/* 43 */     StructurePlaceSettings settings = (new StructurePlaceSettings()).setRotation(Rotation.getRandom(newRandom)).setMirror(Mirror.NONE).setRotationPivot(BlockPos.ZERO).addProcessor((StructureProcessor)BlockIgnoreIrreplacableProcessor.AIR_REMOVE);
/* 44 */     return new TemplateFeature.TemplateFeaturePiece(template, settings);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean placeWithTemplate(ServerLevel world, ChunkGenerator generator, RandomSource random, BlockPos pos, NoneFeatureConfiguration config, TemplateFeature.TemplateFeaturePiece piece) {
/* 50 */     StructureTemplate template = piece.template();
/* 51 */     BlockPos blockPos1 = template.getBoundingBox(piece.settings(), pos).getCenter();
/* 52 */     int xOffset = pos.getX() - blockPos1.getX();
/* 53 */     int yOffset = pos.getY() - blockPos1.getY();
/* 54 */     int zOffset = pos.getZ() - blockPos1.getZ();
/* 55 */     BlockPos newPos = pos.offset(xOffset, yOffset, zOffset);
/* 56 */     template.placeInWorld((ServerLevelAccessor)world, newPos, newPos, piece.settings(), random, 2);
/* 57 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\world\gen\feature\CommandBlockPodiumFeature.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
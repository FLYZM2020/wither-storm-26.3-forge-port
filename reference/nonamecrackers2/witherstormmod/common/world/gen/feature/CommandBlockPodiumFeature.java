/*    */ package nonamecrackers2.witherstormmod.common.world.gen.feature;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.resources.ResourceLocation;
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
/*    */   private final ResourceLocation templateId;
/*    */   
/*    */   public CommandBlockPodiumFeature(Codec<NoneFeatureConfiguration> codec, ResourceLocation templateId) {
/* 25 */     super(codec);
/* 26 */     this.templateId = templateId;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected TemplateFeature.TemplateFeaturePiece setupTemplate(BlockPos pos, RandomSource random, StructureTemplateManager manager) {
/* 32 */     StructureTemplate template = manager.m_230359_(this.templateId);
/* 33 */     RandomSource newRandom = RandomSource.m_216335_(pos.m_121878_());
/* 34 */     StructurePlaceSettings settings = (new StructurePlaceSettings()).m_74379_(Rotation.m_221990_(newRandom)).m_74377_(Mirror.NONE).m_74385_(BlockPos.f_121853_).m_74383_((StructureProcessor)BlockIgnoreIrreplacableProcessor.AIR);
/* 35 */     return new TemplateFeature.TemplateFeaturePiece(template, settings);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TemplateFeature.TemplateFeaturePiece setupRemovalTemplate(BlockPos pos, RandomSource random, StructureTemplateManager manager) {
/* 41 */     StructureTemplate template = manager.m_230359_(this.templateId);
/* 42 */     RandomSource newRandom = RandomSource.m_216335_(pos.m_121878_());
/* 43 */     StructurePlaceSettings settings = (new StructurePlaceSettings()).m_74379_(Rotation.m_221990_(newRandom)).m_74377_(Mirror.NONE).m_74385_(BlockPos.f_121853_).m_74383_((StructureProcessor)BlockIgnoreIrreplacableProcessor.AIR_REMOVE);
/* 44 */     return new TemplateFeature.TemplateFeaturePiece(template, settings);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean placeWithTemplate(ServerLevel world, ChunkGenerator generator, RandomSource random, BlockPos pos, NoneFeatureConfiguration config, TemplateFeature.TemplateFeaturePiece piece) {
/* 50 */     StructureTemplate template = piece.template();
/* 51 */     BlockPos blockPos1 = template.m_74633_(piece.settings(), pos).m_162394_();
/* 52 */     int xOffset = pos.m_123341_() - blockPos1.m_123341_();
/* 53 */     int yOffset = pos.m_123342_() - blockPos1.m_123342_();
/* 54 */     int zOffset = pos.m_123343_() - blockPos1.m_123343_();
/* 55 */     BlockPos newPos = pos.m_7918_(xOffset, yOffset, zOffset);
/* 56 */     template.m_230328_((ServerLevelAccessor)world, newPos, newPos, piece.settings(), random, 2);
/* 57 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\world\gen\feature\CommandBlockPodiumFeature.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package nonamecrackers2.witherstormmod.common.world.gen.feature.structure;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.NbtUtils;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.level.ServerLevelAccessor;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.Mirror;
/*     */ import net.minecraft.world.level.block.Rotation;
/*     */ import net.minecraft.world.level.levelgen.structure.BoundingBox;
/*     */ import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
/*     */ import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
/*     */ import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
/*     */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
/*     */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
/*     */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
/*     */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModStructures;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Piece
/*     */   extends TemplateStructurePiece
/*     */ {
/*     */   @Nullable
/*     */   private BlockPos spawnPos;
/*     */   
/*     */   public Piece(StructureTemplateManager manager, ResourceLocation location, BlockPos pos, Rotation rotation, int offsetY) {
/* 132 */     super(WitherStormModStructures.PLATFORM, 0, manager, location, location.toString(), makeSettings(rotation, pos), pos);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Piece(StructureTemplateManager manager, CompoundTag nbt) {
/* 138 */     super(WitherStormModStructures.PLATFORM, nbt, manager, id -> {
/*     */           StructureTemplate template = manager.m_230359_(id);
/*     */           
/*     */           return makeSettings(Rotation.valueOf(nbt.m_128461_("Rot")), new BlockPos(template.m_163801_().m_123341_() / 2, 0, template.m_163801_().m_123343_() / 2));
/*     */         });
/* 143 */     if (nbt.m_128425_("SpawnPos", 10)) {
/* 144 */       this.spawnPos = NbtUtils.m_129239_(nbt.m_128469_("SpawnPos"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_183620_(StructurePieceSerializationContext context, CompoundTag tag) {
/* 162 */     super.m_183620_(context, tag);
/* 163 */     tag.m_128359_("Rot", this.f_73657_.m_74404_().name());
/* 164 */     if (this.spawnPos != null) {
/* 165 */       tag.m_128365_("SpawnPos", (Tag)NbtUtils.m_129224_(this.spawnPos));
/*     */     }
/*     */   }
/*     */   
/*     */   private static StructurePlaceSettings makeSettings(Rotation rotation, BlockPos pos) {
/* 170 */     return (new StructurePlaceSettings()).m_74379_(rotation).m_74377_(Mirror.NONE).m_74383_((StructureProcessor)BlockIgnoreProcessor.f_74046_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_213704_(String metadata, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) {
/* 176 */     if (metadata.equals("spawn_position")) {
/*     */       
/* 178 */       this.spawnPos = pos;
/* 179 */       level.m_7731_(pos, Blocks.f_50016_.m_49966_(), 3);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BlockPos getSpawnPos() {
/* 185 */     return this.spawnPos;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\world\gen\feature\structure\StormSpawnPlatformStructure$Piece.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import net.minecraft.core.Registry;
/*    */ import net.minecraft.core.registries.BuiltInRegistries;
/*    */ import net.minecraft.core.registries.Registries;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.level.levelgen.structure.StructureType;
/*    */ import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ import nonamecrackers2.witherstormmod.common.world.gen.feature.structure.BowelsStructure;
/*    */ import nonamecrackers2.witherstormmod.common.world.gen.feature.structure.StormSpawnPlatformStructure;
/*    */ 
/*    */ public class WitherStormModStructures
/*    */ {
/* 17 */   public static final StructurePieceType PLATFORM = setTemplatePieceId(nonamecrackers2.witherstormmod.common.world.gen.feature.structure.StormSpawnPlatformStructure.Piece::new);
/*    */ 
/*    */   
/*    */   private static StructurePieceType setTemplatePieceId(StructurePieceType.StructureTemplateType type) {
/* 21 */     return (StructurePieceType)type;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void registerPieceTypes() {
/* 26 */     Registry.m_122965_(BuiltInRegistries.f_257014_, new ResourceLocation("witherstormmod", "platform"), PLATFORM);
/*    */   }
/*    */   
/* 29 */   public static final DeferredRegister<StructureType<?>> STRUCTURE_FEATURES = DeferredRegister.create(Registries.f_256938_, "witherstormmod");
/*    */   
/* 31 */   public static final RegistryObject<StructureType<StormSpawnPlatformStructure>> STORM_SPAWN_PLATFORM = STRUCTURE_FEATURES.register("storm_spawn_platform", () -> ());
/* 32 */   public static final RegistryObject<StructureType<BowelsStructure>> BOWELS = STRUCTURE_FEATURES.register("bowels", () -> ());
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModStructures.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
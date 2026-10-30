/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import net.minecraft.core.Holder;
/*    */ import net.minecraft.core.registries.Registries;
/*    */ import net.minecraft.resources.ResourceKey;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
/*    */ import net.minecraft.world.level.levelgen.feature.Feature;
/*    */ import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ import nonamecrackers2.witherstormmod.common.world.gen.feature.BowelsPodiumFeature;
/*    */ import nonamecrackers2.witherstormmod.common.world.gen.feature.CommandBlockPodiumFeature;
/*    */ 
/*    */ 
/*    */ public class WitherStormModFeatures
/*    */ {
/* 20 */   public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, "witherstormmod");
/*    */   
/* 22 */   public static final RegistryObject<CommandBlockPodiumFeature> COMMAND_BLOCK_PODIUM_FEATURE = FEATURES.register("command_block_podium", () -> new CommandBlockPodiumFeature(NoneFeatureConfiguration.f_67815_, new ResourceLocation("witherstormmod", "command_block_podium")));
/*    */   
/* 24 */   public static final RegistryObject<BowelsPodiumFeature> BOWELS_PODIUM_FEATURE = FEATURES.register("bowels_podium", () -> new BowelsPodiumFeature(NoneFeatureConfiguration.f_67815_, new ResourceLocation("witherstormmod", "bowels_podium")));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Holder<ConfiguredFeature<?, ?>> getConfiguredFeature(ServerLevel level, ResourceLocation id) {
/* 34 */     return (Holder<ConfiguredFeature<?, ?>>)level.m_9598_().m_175515_(Registries.f_256911_).m_246971_(ResourceKey.m_135785_(Registries.f_256911_, id));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModFeatures.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
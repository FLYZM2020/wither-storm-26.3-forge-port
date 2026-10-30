/*    */ package nonamecrackers2.witherstormmod.common.event;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import java.util.Set;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.data.DataGenerator;
/*    */ import net.minecraft.data.DataProvider;
/*    */ import net.minecraft.data.PackOutput;
/*    */ import net.minecraft.data.loot.LootTableProvider;
/*    */ import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
/*    */ import net.minecraftforge.common.data.ExistingFileHelper;
/*    */ import net.minecraftforge.data.event.GatherDataEvent;
/*    */ import net.minecraftforge.event.AddReloadListenerEvent;
/*    */ import nonamecrackers2.witherstormmod.common.data.WitherStormModBlockStatesProvider;
/*    */ import nonamecrackers2.witherstormmod.common.data.WitherStormModBlockTaintingRecipeProvider;
/*    */ import nonamecrackers2.witherstormmod.common.data.WitherStormModItemModelProvider;
/*    */ import nonamecrackers2.witherstormmod.common.data.WitherStormModLangProvider;
/*    */ import nonamecrackers2.witherstormmod.common.data.WitherStormModMobConversionsProvider;
/*    */ import nonamecrackers2.witherstormmod.common.data.WitherStormModRecipeProvider;
/*    */ import nonamecrackers2.witherstormmod.common.world.tainting.WorldTainting;
/*    */ 
/*    */ 
/*    */ public class WitherStormModDataEvents
/*    */ {
/*    */   public static void gatherData(GatherDataEvent event) {
/* 27 */     DataGenerator generator = event.getGenerator();
/* 28 */     PackOutput output = generator.getPackOutput();
/* 29 */     ExistingFileHelper exFileHelper = event.getExistingFileHelper();
/* 30 */     generator.addProvider(event.includeClient(), (DataProvider)new WitherStormModLangProvider(output));
/* 31 */     generator.addProvider(event.includeServer(), (DataProvider)new WitherStormModRecipeProvider(output));
/* 32 */     generator.addProvider(event.includeClient(), (DataProvider)new WitherStormModBlockStatesProvider(output, exFileHelper));
/* 33 */     generator.addProvider(event.includeClient(), (DataProvider)new WitherStormModItemModelProvider(output, exFileHelper));
/* 34 */     generator.addProvider(event.includeServer(), (DataProvider)new LootTableProvider(output, Set.of(), List.of(new LootTableProvider.SubProviderEntry(nonamecrackers2.witherstormmod.common.data.loot.WitherStormModBlockLootProvider::new, LootContextParamSets.f_81421_), new LootTableProvider.SubProviderEntry(nonamecrackers2.witherstormmod.common.data.loot.WitherStormModEntityLootProvider::new, LootContextParamSets.f_81415_))));
/*    */ 
/*    */ 
/*    */     
/* 38 */     generator.addProvider(event.includeServer(), (DataProvider)new WitherStormModBlockTaintingRecipeProvider(output));
/* 39 */     generator.addProvider(event.includeServer(), (DataProvider)new WitherStormModMobConversionsProvider(output));
/*    */   }
/*    */ 
/*    */   
/*    */   public static void addResourceListeners(AddReloadListenerEvent event) {
/* 44 */     Objects.requireNonNull(event); WorldTainting.initializeOrAddListeners(event::addListener);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\event\WitherStormModDataEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package nonamecrackers2.witherstormmod.common.event;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import net.minecraft.commands.CommandSourceStack;
/*    */ import net.minecraftforge.event.RegisterCommandsEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.config.ModConfig;
/*    */ import net.minecraftforge.fml.loading.FMLEnvironment;
/*    */ import nonamecrackers2.crackerslib.common.command.ConfigCommandBuilder;
/*    */ import nonamecrackers2.witherstormmod.common.command.BowelsCommands;
/*    */ import nonamecrackers2.witherstormmod.common.command.ChunkLoaderCommands;
/*    */ import nonamecrackers2.witherstormmod.common.command.ConsumedEntitiesCommands;
/*    */ import nonamecrackers2.witherstormmod.common.command.ConversionCommands;
/*    */ import nonamecrackers2.witherstormmod.common.command.CreateClusterCommand;
/*    */ import nonamecrackers2.witherstormmod.common.command.DebugCommands;
/*    */ import nonamecrackers2.witherstormmod.common.command.DoDeathSequenceCommand;
/*    */ import nonamecrackers2.witherstormmod.common.command.ExplodeStormCommand;
/*    */ import nonamecrackers2.witherstormmod.common.command.PhaseCommands;
/*    */ import nonamecrackers2.witherstormmod.common.command.RandomizeSicknessModifiersCommand;
/*    */ import nonamecrackers2.witherstormmod.common.command.ReviveStormCommand;
/*    */ import nonamecrackers2.witherstormmod.common.command.SetEvolutionAttributeCommand;
/*    */ import nonamecrackers2.witherstormmod.common.command.ShakeScreenCommand;
/*    */ import nonamecrackers2.witherstormmod.common.command.StartCureCommand;
/*    */ import nonamecrackers2.witherstormmod.common.command.StartInfectionCommand;
/*    */ import nonamecrackers2.witherstormmod.common.command.TractorBeamCommands;
/*    */ import nonamecrackers2.witherstormmod.common.command.UltimateTargetCommands;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WitherStormModRegisterCommands
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onRegisterCommandEvent(RegisterCommandsEvent event) {
/* 35 */     CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
/* 36 */     PhaseCommands.register(dispatcher);
/* 37 */     ConsumedEntitiesCommands.register(dispatcher);
/* 38 */     CreateClusterCommand.register(dispatcher);
/* 39 */     SetEvolutionAttributeCommand.register(dispatcher);
/* 40 */     StartInfectionCommand.register(dispatcher);
/* 41 */     StartCureCommand.register(dispatcher);
/* 42 */     ExplodeStormCommand.register(dispatcher);
/* 43 */     ReviveStormCommand.register(dispatcher);
/* 44 */     BowelsCommands.register(dispatcher);
/* 45 */     UltimateTargetCommands.register(dispatcher);
/* 46 */     RandomizeSicknessModifiersCommand.register(dispatcher);
/* 47 */     DoDeathSequenceCommand.register(dispatcher);
/* 48 */     TractorBeamCommands.register(dispatcher);
/* 49 */     ChunkLoaderCommands.register(dispatcher);
/* 50 */     ConversionCommands.register(dispatcher);
/* 51 */     ShakeScreenCommand.register(dispatcher);
/* 52 */     if (!FMLEnvironment.production)
/* 53 */       DebugCommands.register(dispatcher); 
/* 54 */     ConfigCommandBuilder.builder(event.getDispatcher(), "witherstormmod").addSpec(ModConfig.Type.SERVER, WitherStormModConfig.SERVER_SPEC).register();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\event\WitherStormModRegisterCommands.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package nonamecrackers2.witherstormmod.common.command;
/*     */ 
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.DoubleArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import net.minecraft.commands.CommandSourceStack;
/*     */ import net.minecraft.commands.Commands;
/*     */ import net.minecraft.commands.arguments.EntityArgument;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.network.chat.MutableComponent;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.util.EvolutionProfiler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SetPhaseCommand
/*     */ {
/*     */   public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
/*  26 */     LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.m_82127_("witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(
/*  27 */         Commands.m_82127_("phase")
/*  28 */         .then(Commands.m_82127_("set")
/*  29 */           .then(
/*  30 */             Commands.m_82129_("witherstorm", (ArgumentType)EntityArgument.m_91449_())
/*  31 */             .then(
/*  32 */               Commands.m_82129_("phase", (ArgumentType)DoubleArgumentType.doubleArg(0.0D))
/*  33 */               .executes(SetPhaseCommand::setPhase)))));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  38 */     dispatcher.register(setPhaseCommand);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int setPhase(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/*  43 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/*  44 */     Entity entity = EntityArgument.m_91452_(context, "witherstorm");
/*  45 */     double phase = DoubleArgumentType.getDouble(context, "phase");
/*  46 */     if (entity != null)
/*     */     {
/*  48 */       if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*     */         
/*  50 */         boolean setPhase = false;
/*  51 */         if (phase == 4.5D) {
/*     */           
/*  53 */           setPhase = storm.setPhase(4);
/*  54 */           if (setPhase) {
/*  55 */             storm.setConsumedEntities(storm.getSubPhaseRequirement(4) + 1);
/*     */           }
/*  57 */         } else if (phase == 5.25D) {
/*     */           
/*  59 */           setPhase = storm.setPhase(5);
/*  60 */           if (setPhase) {
/*  61 */             storm.setConsumedEntities(storm.getSubPhaseRequirement(5) + 1);
/*     */           }
/*  63 */         } else if (phase == 5.5D) {
/*     */           
/*  65 */           setPhase = storm.setPhase(5);
/*  66 */           if (setPhase) {
/*  67 */             storm.setConsumedEntities(storm.getConsumptionAmountForPhase(5) + 1);
/*     */           }
/*  69 */         } else if (phase == 6.5D) {
/*     */           
/*  71 */           setPhase = storm.setPhase(6);
/*  72 */           if (setPhase) {
/*  73 */             storm.setConsumedEntities(storm.getSubPhaseRequirement(6) + 1);
/*     */           }
/*  75 */         } else if (phase == 7.5D) {
/*     */           
/*  77 */           setPhase = storm.setPhase(7);
/*  78 */           if (setPhase) {
/*  79 */             storm.setConsumedEntities(storm.getConsumptionAmountForPhase(7) + 1);
/*     */           }
/*     */         } else {
/*     */           
/*  83 */           int intPhase = Mth.m_14107_(phase);
/*  84 */           setPhase = storm.setPhase(intPhase);
/*     */         } 
/*  86 */         if (!setPhase) {
/*     */           
/*  88 */           MutableComponent component = Component.m_237110_("commands.witherstormmod.setphase.invalid", new Object[] { Double.valueOf(phase), storm.m_5446_() });
/*  89 */           source.m_81352_((Component)component);
/*     */         }
/*     */         else {
/*     */           
/*  93 */           MutableComponent component = Component.m_237110_("commands.witherstormmod.setphase.success", new Object[] { Double.valueOf(phase), storm.m_5446_() });
/*  94 */           source.m_288197_(() -> component, true);
/*  95 */           EvolutionProfiler profiler = storm.getEvolutionProfiler();
/*  96 */           if (profiler.isProfiling()) {
/*  97 */             profiler.begin();
/*     */           }
/*     */         }  }
/*     */       else
/*     */       
/* 102 */       { MutableComponent component = Component.m_237115_("commands.witherstormmod.entity.arg.invalid");
/* 103 */         source.m_81352_((Component)component); }
/*     */     
/*     */     }
/* 106 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\SetPhaseCommand.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
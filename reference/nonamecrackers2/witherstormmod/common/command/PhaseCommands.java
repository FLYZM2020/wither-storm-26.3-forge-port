/*     */ package nonamecrackers2.witherstormmod.common.command;
/*     */ 
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.DoubleArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
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
/*     */ public class PhaseCommands
/*     */ {
/*     */   public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
/*  26 */     LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.m_82127_("witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then((
/*  27 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_("phase")
/*  28 */         .then(Commands.m_82127_("set")
/*  29 */           .then(Commands.m_82129_("witherstorm", (ArgumentType)EntityArgument.m_91449_())
/*  30 */             .then(Commands.m_82129_("phase", (ArgumentType)DoubleArgumentType.doubleArg(0.0D))
/*  31 */               .executes(PhaseCommands::setPhase)))))
/*     */ 
/*     */ 
/*     */         
/*  35 */         .then(Commands.m_82127_("get")
/*  36 */           .then(Commands.m_82129_("witherstorm", (ArgumentType)EntityArgument.m_91449_())
/*  37 */             .executes(PhaseCommands::getPhase))))
/*     */ 
/*     */         
/*  40 */         .then(Commands.m_82127_("evolve")
/*  41 */           .then(((RequiredArgumentBuilder)Commands.m_82129_("witherstorm", (ArgumentType)EntityArgument.m_91449_())
/*  42 */             .executes(ctx -> evolve(ctx, false)))
/*  43 */             .then(Commands.m_82127_("force")
/*  44 */               .executes(ctx -> evolve(ctx, true))))));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  49 */     dispatcher.register(setPhaseCommand);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int setPhase(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/*  54 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/*  55 */     Entity entity = EntityArgument.m_91452_(context, "witherstorm");
/*  56 */     double phase = DoubleArgumentType.getDouble(context, "phase");
/*  57 */     if (entity != null)
/*     */     {
/*  59 */       if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*     */         
/*  61 */         boolean setPhase = false;
/*  62 */         if (phase >= 1.1D && phase < 1.2D) {
/*  63 */           setPhase = storm.setPhase(1, storm.adjustAmountForEvolutionSpeed(150));
/*  64 */         } else if (phase >= 1.2D && phase < 2.0D) {
/*  65 */           setPhase = storm.setPhase(1, storm.adjustAmountForEvolutionSpeed(250));
/*  66 */         } else if (phase >= 2.1D && phase < 3.0D) {
/*  67 */           setPhase = storm.setPhase(2, storm.adjustAmountForEvolutionSpeed(800));
/*  68 */         } else if (phase >= 3.1D && phase < 3.2D) {
/*  69 */           setPhase = storm.setPhase(3, storm.adjustAmountForEvolutionSpeed(2350));
/*  70 */         } else if (phase >= 3.2D && phase < 4.0D) {
/*  71 */           setPhase = storm.setPhase(3, storm.adjustAmountForEvolutionSpeed(3500));
/*  72 */         } else if (phase >= 4.5D && phase < 5.0D) {
/*  73 */           setPhase = storm.setPhase(4, storm.getSubPhaseRequirement(4) + 1);
/*  74 */         } else if (phase >= 5.25D && phase < 5.5D) {
/*  75 */           setPhase = storm.setPhase(5, storm.getSubPhaseRequirement(5) + 1);
/*  76 */         } else if (phase >= 5.5D && phase < 6.0D) {
/*  77 */           setPhase = storm.setPhase(5, storm.getConsumptionAmountForPhase(5) + 1);
/*  78 */         } else if (phase >= 6.5D && phase < 7.0D) {
/*  79 */           setPhase = storm.setPhase(6, storm.getSubPhaseRequirement(6) + 1);
/*  80 */         } else if (phase >= 7.5D) {
/*  81 */           setPhase = storm.setPhase(7, storm.getConsumptionAmountForPhase(7) + 1);
/*     */         } else {
/*  83 */           setPhase = storm.setPhase(Mth.m_14107_(phase));
/*  84 */         }  if (!setPhase) {
/*     */           
/*  86 */           MutableComponent component = Component.m_237110_("commands.witherstormmod.setphase.invalid", new Object[] { Double.valueOf(phase), storm.m_5446_() });
/*  87 */           source.m_81352_((Component)component);
/*     */         }
/*     */         else {
/*     */           
/*  91 */           source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.setphase.success", new Object[] { Double.valueOf(phase), storm.m_5446_() }), true);
/*  92 */           EvolutionProfiler profiler = storm.getEvolutionProfiler();
/*  93 */           if (profiler.isProfiling()) {
/*  94 */             profiler.begin();
/*     */           }
/*     */         }  }
/*     */       else
/*     */       
/*  99 */       { MutableComponent component = Component.m_237115_("commands.witherstormmod.entity.arg.invalid");
/* 100 */         source.m_81352_((Component)component); }
/*     */     
/*     */     }
/* 103 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int getPhase(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 108 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 109 */     Entity entity = EntityArgument.m_91452_(context, "witherstorm");
/* 110 */     int phase = -1;
/* 111 */     if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*     */       
/* 113 */       phase = storm.getPhase();
/* 114 */       source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.getphase.result", new Object[] { storm.m_5446_(), Integer.valueOf(storm.getPhase()) }), false); }
/*     */     
/*     */     else
/*     */     
/* 118 */     { source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.entity.arg.invalid")); }
/*     */     
/* 120 */     return phase;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int evolve(CommandContext<CommandSourceStack> context, boolean force) throws CommandSyntaxException {
/* 125 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 126 */     Entity entity = EntityArgument.m_91452_(context, "witherstorm");
/* 127 */     int phase = 0;
/* 128 */     if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*     */       
/* 130 */       if (storm.evolve(force)) {
/* 131 */         source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.evolve.success", new Object[] { storm.m_5446_(), Integer.valueOf(storm.getPhase()) }), true);
/*     */       } else {
/* 133 */         source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.evolve.fail"));
/* 134 */       }  phase = storm.getPhase(); }
/*     */     
/*     */     else
/*     */     
/* 138 */     { source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.entity.arg.invalid")); }
/*     */     
/* 140 */     return phase;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\PhaseCommands.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
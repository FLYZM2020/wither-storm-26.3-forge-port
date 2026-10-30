/*     */ package nonamecrackers2.witherstormmod.common.command;
/*     */ 
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import net.minecraft.commands.CommandSourceStack;
/*     */ import net.minecraft.commands.Commands;
/*     */ import net.minecraft.commands.arguments.EntityArgument;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.network.chat.MutableComponent;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConsumedEntitiesCommands
/*     */ {
/*     */   public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
/*  24 */     LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.literal("witherstormmod").requires(commandSource -> commandSource.hasPermission(2))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal("consumedEntities")
/*  25 */         .then(Commands.literal("set")
/*  26 */           .then(((RequiredArgumentBuilder)Commands.argument("witherstorm", (ArgumentType)EntityArgument.entity())
/*  27 */             .then(Commands.argument("amount", (ArgumentType)IntegerArgumentType.integer(0))
/*  28 */               .executes(ctx -> setConsumedEntities(ctx, IntegerArgumentType.getInteger(ctx, "amount")))))
/*     */             
/*  30 */             .then(Commands.literal("blackhole")
/*  31 */               .executes(ctx -> setConsumedEntities(ctx, 16000))))))
/*     */ 
/*     */ 
/*     */         
/*  35 */         .then(Commands.literal("get")
/*  36 */           .then(Commands.argument("witherstorm", (ArgumentType)EntityArgument.entity())
/*  37 */             .executes(ConsumedEntitiesCommands::getConsumedEntities))))
/*     */ 
/*     */         
/*  40 */         .then(Commands.literal("lock")
/*  41 */           .then(Commands.argument("witherstorm", (ArgumentType)EntityArgument.entities())
/*  42 */             .executes(ConsumedEntitiesCommands::lockWitherStorm))))
/*     */ 
/*     */         
/*  45 */         .then(Commands.literal("unlock")
/*  46 */           .then(Commands.argument("witherstorm", (ArgumentType)EntityArgument.entities())
/*  47 */             .executes(ConsumedEntitiesCommands::unlockWitherStorm))));
/*     */ 
/*     */ 
/*     */     
/*  51 */     dispatcher.register(setPhaseCommand);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int setConsumedEntities(CommandContext<CommandSourceStack> context, int consumedAmount) throws CommandSyntaxException {
/*  56 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/*  57 */     Entity entity = EntityArgument.getEntity(context, "witherstorm");
/*  58 */     if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*     */       
/*  60 */       storm.setConsumedEntities(consumedAmount);
/*  61 */       storm.checkConsumptionAmount();
/*  62 */       source.sendSuccess(() -> Component.translatable("commands.witherstormmod.setconsumed.success", new Object[] { Integer.valueOf(consumedAmount), storm.getDisplayName() }), true); }
/*     */     
/*     */     else
/*     */     
/*  66 */     { MutableComponent component = Component.translatable("commands.witherstormmod.entity.arg.invalid");
/*  67 */       source.sendFailure((Component)component); }
/*     */     
/*  69 */     return consumedAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int getConsumedEntities(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/*  74 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/*  75 */     Entity entity = EntityArgument.getEntity(context, "witherstorm");
/*  76 */     int consumedAmount = 0;
/*  77 */     if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*     */       
/*  79 */       consumedAmount = storm.getConsumedEntities();
/*  80 */       source.sendSuccess(() -> Component.translatable("commands.witherstormmod.getconsumed.result", new Object[] { storm.getDisplayName(), Integer.valueOf(storm.getConsumedEntities()), Integer.valueOf(storm.getConsumptionAmountForPhase(storm.getPhase())) }), false); }
/*     */     
/*     */     else
/*     */     
/*  84 */     { source.sendFailure((Component)Component.translatable("commands.witherstormmod.entity.arg.invalid")); }
/*     */     
/*  86 */     return consumedAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int lockWitherStorm(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/*  91 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/*  92 */     Entity entity = EntityArgument.getEntity(context, "witherstorm");
/*  93 */     if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*     */       
/*  95 */       if (!storm.isConsumptionLocked())
/*     */       {
/*  97 */         storm.makeConsumptionLocked(true);
/*  98 */         source.sendSuccess(() -> Component.translatable("commands.witherstormmod.lock.success", new Object[] { storm.getDisplayName() }), true);
/*     */       }
/*     */       else
/*     */       {
/* 102 */         source.sendFailure((Component)Component.translatable("commands.witherstormmod.lock.fail"));
/*     */       }
/*     */        }
/*     */     else
/*     */     
/* 107 */     { source.sendFailure((Component)Component.translatable("commands.witherstormmod.entity.arg.invalid")); }
/*     */     
/* 109 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int unlockWitherStorm(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 114 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 115 */     Entity entity = EntityArgument.getEntity(context, "witherstorm");
/* 116 */     if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*     */       
/* 118 */       if (storm.isConsumptionLocked())
/*     */       {
/* 120 */         storm.makeConsumptionLocked(false);
/* 121 */         source.sendSuccess(() -> Component.translatable("commands.witherstormmod.unlock.success", new Object[] { storm.getDisplayName() }), true);
/*     */       }
/*     */       else
/*     */       {
/* 125 */         source.sendFailure((Component)Component.translatable("commands.witherstormmod.unlock.fail"));
/*     */       }
/*     */        }
/*     */     else
/*     */     
/* 130 */     { source.sendFailure((Component)Component.translatable("commands.witherstormmod.entity.arg.invalid")); }
/*     */     
/* 132 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\ConsumedEntitiesCommands.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
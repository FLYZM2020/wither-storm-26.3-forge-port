/*    */ package nonamecrackers2.witherstormmod.common.command;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.commands.CommandSourceStack;
/*    */ import net.minecraft.commands.Commands;
/*    */ import net.minecraft.commands.arguments.EntityArgument;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.network.chat.MutableComponent;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SetConsumedEntitiesCommand
/*    */ {
/*    */   public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
/* 24 */     LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.literal("witherstormmod").requires(commandSource -> commandSource.hasPermission(2))).then(
/* 25 */         Commands.literal("consumedEntities")
/* 26 */         .then(Commands.literal("set")
/* 27 */           .then(
/* 28 */             Commands.argument("witherstorm", (ArgumentType)EntityArgument.entity())
/* 29 */             .then(
/* 30 */               Commands.argument("amount", (ArgumentType)IntegerArgumentType.integer(0))
/* 31 */               .executes(SetConsumedEntitiesCommand::setConsumedEntities)))));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 36 */     dispatcher.register(setPhaseCommand);
/*    */   }
/*    */ 
/*    */   
/*    */   private static int setConsumedEntities(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 41 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 42 */     Entity entity = EntityArgument.getEntity(context, "witherstorm");
/* 43 */     int consumedAmount = IntegerArgumentType.getInteger(context, "amount");
/* 44 */     if (entity != null)
/*    */     {
/* 46 */       if (entity instanceof WitherStormEntity) {
/*    */         
/* 48 */         WitherStormEntity storm = (WitherStormEntity)entity;
/* 49 */         storm.setConsumedEntities(consumedAmount);
/* 50 */         storm.checkConsumptionAmount();
/* 51 */         MutableComponent component = Component.translatable("commands.witherstormmod.setconsumed.success", new Object[] { Integer.valueOf(consumedAmount), storm.getDisplayName() });
/* 52 */         source.sendSuccess(() -> component, true);
/*    */       }
/*    */       else {
/*    */         
/* 56 */         MutableComponent component = Component.translatable("commands.witherstormmod.entity.arg.invalid");
/* 57 */         source.sendFailure((Component)component);
/*    */       } 
/*    */     }
/* 60 */     return consumedAmount;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\SetConsumedEntitiesCommand.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
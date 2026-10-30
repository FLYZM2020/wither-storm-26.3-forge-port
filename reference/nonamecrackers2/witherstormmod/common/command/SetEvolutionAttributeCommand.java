/*    */ package nonamecrackers2.witherstormmod.common.command;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.DoubleArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.commands.CommandSourceStack;
/*    */ import net.minecraft.commands.Commands;
/*    */ import net.minecraft.commands.arguments.EntityArgument;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.network.chat.MutableComponent;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.ai.attributes.Attribute;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModAttributes;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SetEvolutionAttributeCommand
/*    */ {
/*    */   public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
/* 25 */     LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.m_82127_("witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(
/* 26 */         Commands.m_82127_("evolutionSpeed")
/* 27 */         .then(Commands.m_82127_("set")
/* 28 */           .then(
/* 29 */             Commands.m_82129_("witherstorm", (ArgumentType)EntityArgument.m_91449_())
/* 30 */             .then(
/* 31 */               Commands.m_82129_("value", (ArgumentType)DoubleArgumentType.doubleArg(0.1D, 32.0D))
/* 32 */               .executes(SetEvolutionAttributeCommand::setEvolutionSpeed)))));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 37 */     dispatcher.register(setPhaseCommand);
/*    */   }
/*    */ 
/*    */   
/*    */   private static int setEvolutionSpeed(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 42 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 43 */     Entity entity = EntityArgument.m_91452_(context, "witherstorm");
/* 44 */     double value = DoubleArgumentType.getDouble(context, "value");
/* 45 */     int phase = 0;
/* 46 */     if (entity != null)
/*    */     {
/* 48 */       if (entity instanceof WitherStormEntity) {
/*    */         
/* 50 */         WitherStormEntity storm = (WitherStormEntity)entity;
/* 51 */         phase = storm.getPhase();
/* 52 */         storm.m_21051_((Attribute)WitherStormModAttributes.EVOLUTION_SPEED.get()).m_22100_(value);
/* 53 */         storm.setPhase(storm.getPhase());
/* 54 */         MutableComponent component = Component.m_237110_("commands.witherstormmod.setevolution.success", new Object[] { Double.valueOf(value), storm.m_5446_() });
/* 55 */         source.m_288197_(() -> component, true);
/*    */       }
/*    */       else {
/*    */         
/* 59 */         MutableComponent component = Component.m_237115_("commands.witherstormmod.entity.arg.invalid");
/* 60 */         source.m_81352_((Component)component);
/*    */       } 
/*    */     }
/* 63 */     return phase;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\SetEvolutionAttributeCommand.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
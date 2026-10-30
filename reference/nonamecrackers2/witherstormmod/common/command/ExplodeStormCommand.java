/*    */ package nonamecrackers2.witherstormmod.common.command;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
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
/*    */ public class ExplodeStormCommand
/*    */ {
/*    */   public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
/* 23 */     LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.m_82127_("witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(
/* 24 */         Commands.m_82127_("explode")
/* 25 */         .then(Commands.m_82129_("witherstorm", (ArgumentType)EntityArgument.m_91449_())
/* 26 */           .executes(ExplodeStormCommand::explodeStorm)));
/*    */ 
/*    */     
/* 29 */     dispatcher.register(setPhaseCommand);
/*    */   }
/*    */ 
/*    */   
/*    */   private static int explodeStorm(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 34 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 35 */     Entity entity = EntityArgument.m_91452_(context, "witherstorm");
/* 36 */     int phase = 0;
/* 37 */     if (entity != null)
/*    */     {
/* 39 */       if (entity instanceof WitherStormEntity) {
/*    */         
/* 41 */         WitherStormEntity storm = (WitherStormEntity)entity;
/* 42 */         phase = storm.getPhase();
/* 43 */         if (!storm.getPlayDeadManager().getState().disablesAi())
/*    */         {
/* 45 */           storm.getPlayDeadManager().explode();
/* 46 */           MutableComponent component = Component.m_237110_("commands.witherstormmod.explodeStorm.success", new Object[] { storm.m_5446_() });
/* 47 */           source.m_288197_(() -> component, true);
/*    */         }
/*    */         else
/*    */         {
/* 51 */           MutableComponent component = Component.m_237110_("commands.witherstormmod.explodeStorm.failure", new Object[] { storm.m_5446_() });
/* 52 */           source.m_81352_((Component)component);
/*    */         }
/*    */       
/*    */       } else {
/*    */         
/* 57 */         MutableComponent component = Component.m_237115_("commands.witherstormmod.entity.arg.invalid");
/* 58 */         source.m_81352_((Component)component);
/*    */       } 
/*    */     }
/* 61 */     return phase;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\ExplodeStormCommand.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
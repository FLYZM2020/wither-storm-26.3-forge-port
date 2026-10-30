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
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModDamageTypes;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DoDeathSequenceCommand
/*    */ {
/*    */   public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
/* 24 */     LiteralArgumentBuilder<CommandSourceStack> doDeathSequence = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.m_82127_("witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(
/* 25 */         Commands.m_82127_("kill")
/* 26 */         .then(Commands.m_82129_("witherstorm", (ArgumentType)EntityArgument.m_91449_())
/* 27 */           .executes(DoDeathSequenceCommand::doDeathSequence)));
/*    */ 
/*    */     
/* 30 */     dispatcher.register(doDeathSequence);
/*    */   }
/*    */ 
/*    */   
/*    */   private static int doDeathSequence(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 35 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 36 */     Entity entity = EntityArgument.m_91452_(context, "witherstorm");
/* 37 */     int phase = 0;
/* 38 */     if (entity != null)
/*    */     {
/* 40 */       if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*    */         
/* 42 */         phase = storm.getPhase();
/* 43 */         ServerPlayer player = source.m_230896_();
/* 44 */         if (player != null) {
/* 45 */           storm.m_6469_(WitherStormModDamageTypes.playerAttackWitherStorm((Player)player), Float.MAX_VALUE);
/*    */         } else {
/* 47 */           storm.m_6469_(storm.m_269291_().m_269341_(), Float.MAX_VALUE);
/*    */         }  }
/*    */       else
/*    */       
/* 51 */       { source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.entity.arg.invalid")); }
/*    */     
/*    */     }
/* 54 */     return phase;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\DoDeathSequenceCommand.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
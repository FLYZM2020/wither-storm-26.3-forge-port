/*    */ package nonamecrackers2.witherstormmod.common.command;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.FloatArgumentType;
/*    */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.Collection;
/*    */ import net.minecraft.commands.CommandSourceStack;
/*    */ import net.minecraft.commands.Commands;
/*    */ import net.minecraft.commands.arguments.EntityArgument;
/*    */ import net.minecraft.commands.arguments.TimeArgument;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraftforge.network.PacketDistributor;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*    */ import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ShakeScreenCommand
/*    */ {
/*    */   public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
/* 29 */     LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.m_82127_("witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(
/* 30 */         Commands.m_82127_("screenShake")
/* 31 */         .then(Commands.m_82129_("players", (ArgumentType)EntityArgument.m_91470_())
/* 32 */           .then(Commands.m_82129_("time", (ArgumentType)TimeArgument.m_113037_())
/* 33 */             .then(Commands.m_82129_("strength", (ArgumentType)FloatArgumentType.floatArg(0.0F))
/* 34 */               .executes(ShakeScreenCommand::shakeScreen)))));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 39 */     dispatcher.register(setPhaseCommand);
/*    */   }
/*    */ 
/*    */   
/*    */   private static int shakeScreen(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 44 */     CommandSourceStack stack = (CommandSourceStack)context.getSource();
/* 45 */     Collection<ServerPlayer> players = EntityArgument.m_91477_(context, "players");
/* 46 */     int time = IntegerArgumentType.getInteger(context, "time");
/* 47 */     if (time > 1200) {
/*    */       
/* 49 */       stack.m_81352_((Component)Component.m_237115_("commands.witherstormmod.screenShake.fail"));
/* 50 */       return -1;
/*    */     } 
/* 52 */     float strength = FloatArgumentType.getFloat(context, "strength");
/* 53 */     for (ServerPlayer player : players)
/* 54 */       WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> player), new ShakeScreenMessage(time, strength)); 
/* 55 */     stack.m_288197_(() -> Component.m_237110_("commands.witherstormmod.screenShake.success", new Object[] { Integer.valueOf(players.size()) }), true);
/* 56 */     return players.size();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\ShakeScreenCommand.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
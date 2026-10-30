/*    */ package nonamecrackers2.witherstormmod.common.command;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import net.minecraft.commands.CommandSourceStack;
/*    */ import net.minecraft.commands.Commands;
/*    */ import net.minecraft.commands.arguments.EntityArgument;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TractorBeamCommands
/*    */ {
/*    */   public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
/* 23 */     LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.m_82127_("witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(Commands.m_82127_("tractorBeam")
/* 24 */         .then(Commands.m_82127_("isInBeam")
/* 25 */           .then(Commands.m_82129_("entity", (ArgumentType)EntityArgument.m_91449_())
/* 26 */             .then(Commands.m_82129_("witherstorm", (ArgumentType)EntityArgument.m_91449_())
/* 27 */               .executes(TractorBeamCommands::isInBeam)))));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 32 */     dispatcher.register(setPhaseCommand);
/*    */   }
/*    */ 
/*    */   
/*    */   private static int isInBeam(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 37 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 38 */     Entity target = EntityArgument.m_91452_(context, "entity");
/* 39 */     Entity entity = EntityArgument.m_91452_(context, "witherstorm");
/* 40 */     int head = -1;
/* 41 */     if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*    */       
/* 43 */       Pair<Boolean, Integer> result = TractorBeamHelper.isInsideTractorBeam(target, (LivingEntity)storm, 4.0D);
/* 44 */       if (((Boolean)result.getFirst()).booleanValue())
/*    */       {
/* 46 */         head = ((Integer)result.getSecond()).intValue();
/* 47 */         source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.isInTractorBeam.success", new Object[] { target.m_5446_(), result.getSecond() }), false);
/*    */       }
/*    */       else
/*    */       {
/* 51 */         source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.isInTractorBeam.fail", new Object[] { target.m_5446_() }), false);
/*    */       }
/*    */        }
/*    */     else
/*    */     
/* 56 */     { source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.entity.arg.invalid")); }
/*    */     
/* 58 */     return head;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\TractorBeamCommands.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
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
/* 23 */     LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.literal("witherstormmod").requires(commandSource -> commandSource.hasPermission(2))).then(Commands.literal("tractorBeam")
/* 24 */         .then(Commands.literal("isInBeam")
/* 25 */           .then(Commands.argument("entity", (ArgumentType)EntityArgument.entity())
/* 26 */             .then(Commands.argument("witherstorm", (ArgumentType)EntityArgument.entity())
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
/* 38 */     Entity target = EntityArgument.getEntity(context, "entity");
/* 39 */     Entity entity = EntityArgument.getEntity(context, "witherstorm");
/* 40 */     int head = -1;
/* 41 */     if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*    */       
/* 43 */       Pair<Boolean, Integer> result = TractorBeamHelper.isInsideTractorBeam(target, (LivingEntity)storm, 4.0D);
/* 44 */       if (((Boolean)result.getFirst()).booleanValue())
/*    */       {
/* 46 */         head = ((Integer)result.getSecond()).intValue();
/* 47 */         source.sendSuccess(() -> Component.translatable("commands.witherstormmod.isInTractorBeam.success", new Object[] { target.getDisplayName(), result.getSecond() }), false);
/*    */       }
/*    */       else
/*    */       {
/* 51 */         source.sendSuccess(() -> Component.translatable("commands.witherstormmod.isInTractorBeam.fail", new Object[] { target.getDisplayName() }), false);
/*    */       }
/*    */        }
/*    */     else
/*    */     
/* 56 */     { source.sendFailure((Component)Component.translatable("commands.witherstormmod.entity.arg.invalid")); }
/*    */     
/* 58 */     return head;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\TractorBeamCommands.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
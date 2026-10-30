/*    */ package nonamecrackers2.witherstormmod.common.command;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.commands.CommandSourceStack;
/*    */ import net.minecraft.commands.Commands;
/*    */ import net.minecraft.commands.arguments.EntityArgument;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.common.capability.WitherSicknessTracker;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*    */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModEntityTags;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RandomizeSicknessModifiersCommand
/*    */ {
/*    */   public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
/* 27 */     LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.literal("witherstormmod").requires(commandSource -> commandSource.hasPermission(2))).then(
/* 28 */         Commands.literal("sickness")
/* 29 */         .then(Commands.literal("randomizeModifiers")
/* 30 */           .then(
/* 31 */             Commands.argument("entity", (ArgumentType)EntityArgument.entity())
/* 32 */             .executes(RandomizeSicknessModifiersCommand::randomizeModifiers))));
/*    */ 
/*    */ 
/*    */     
/* 36 */     dispatcher.register(setPhaseCommand);
/*    */   }
/*    */ 
/*    */   
/*    */   private static int randomizeModifiers(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 41 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 42 */     Entity entity = EntityArgument.getEntity(context, "entity");
/* 43 */     if (entity != null)
/*    */     {
/* 45 */       if (entity instanceof LivingEntity) {
/*    */         
/* 47 */         LivingEntity living = (LivingEntity)entity;
/* 48 */         if (!living.getType().is(WitherStormModEntityTags.WITHER_SICKNESS_IMMUNE)) {
/*    */           
/* 50 */           Optional<WitherSicknessTracker> optional = living.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).resolve();
/* 51 */           if (optional.isPresent())
/*    */           {
/* 53 */             WitherSicknessTracker tracker = optional.get();
/* 54 */             tracker.randomizeModifiers();
/* 55 */             source.sendSuccess(() -> Component.translatable("commands.witherstormmod.sickness.randomizeModifiers", new Object[] { living.getDisplayName() }), true);
/*    */           }
/*    */         
/*    */         } else {
/*    */           
/* 60 */           source.sendFailure((Component)Component.translatable("commands.witherstormmod.sickness.immune", new Object[] { entity.getDisplayName() }));
/*    */         }
/*    */       
/*    */       } else {
/*    */         
/* 65 */         source.sendFailure((Component)Component.translatable("commands.witherstormmod.entity.arg.notLiving"));
/*    */       } 
/*    */     }
/* 68 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\RandomizeSicknessModifiersCommand.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
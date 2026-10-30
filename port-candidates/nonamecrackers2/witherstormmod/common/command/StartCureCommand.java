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
/*    */ import net.minecraft.network.chat.MutableComponent;
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
/*    */ public class StartCureCommand
/*    */ {
/*    */   public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
/* 28 */     LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.literal("witherstormmod").requires(commandSource -> commandSource.hasPermission(2))).then(
/* 29 */         Commands.literal("sickness")
/* 30 */         .then(Commands.literal("cure")
/* 31 */           .then(
/* 32 */             Commands.argument("entity", (ArgumentType)EntityArgument.entity())
/* 33 */             .executes(StartCureCommand::startCure))));
/*    */ 
/*    */ 
/*    */     
/* 37 */     dispatcher.register(setPhaseCommand);
/*    */   }
/*    */ 
/*    */   
/*    */   private static int startCure(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 42 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 43 */     Entity entity = EntityArgument.getEntity(context, "entity");
/* 44 */     int timeTillCured = 0;
/* 45 */     if (entity != null)
/*    */     {
/* 47 */       if (entity instanceof LivingEntity) {
/*    */         
/* 49 */         LivingEntity living = (LivingEntity)entity;
/* 50 */         if (!living.getType().is(WitherStormModEntityTags.WITHER_SICKNESS_IMMUNE)) {
/*    */           
/* 52 */           Optional<WitherSicknessTracker> optional = living.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).resolve();
/* 53 */           if (optional.isPresent()) {
/*    */             
/* 55 */             WitherSicknessTracker tracker = optional.get();
/* 56 */             timeTillCured = tracker.getCureDelay();
/* 57 */             if (!tracker.isBeingCured()) {
/*    */               
/* 59 */               if (tracker.isInfected())
/*    */               {
/* 61 */                 MutableComponent component = Component.translatable("commands.witherstormmod.sickness.startCure", new Object[] { living.getDisplayName() });
/* 62 */                 source.sendSuccess(() -> component, true);
/* 63 */                 tracker.beginCure();
/*    */               }
/*    */               else
/*    */               {
/* 67 */                 MutableComponent component = Component.translatable("commands.witherstormmod.sickness.cureNotInfected");
/* 68 */                 source.sendFailure((Component)component);
/*    */               }
/*    */             
/*    */             } else {
/*    */               
/* 73 */               MutableComponent component = Component.translatable("commands.witherstormmod.sickness.alreadyBeingCured", new Object[] { living.getDisplayName() });
/* 74 */               source.sendFailure((Component)component);
/*    */             }
/*    */           
/*    */           } 
/*    */         } else {
/*    */           
/* 80 */           MutableComponent component = Component.translatable("commands.witherstormmod.sickness.immune", new Object[] { entity.getDisplayName() });
/* 81 */           source.sendFailure((Component)component);
/*    */         }
/*    */       
/*    */       } else {
/*    */         
/* 86 */         MutableComponent component = Component.translatable("commands.witherstormmod.entity.arg.notLiving");
/* 87 */         source.sendFailure((Component)component);
/*    */       } 
/*    */     }
/* 90 */     return timeTillCured;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\StartCureCommand.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
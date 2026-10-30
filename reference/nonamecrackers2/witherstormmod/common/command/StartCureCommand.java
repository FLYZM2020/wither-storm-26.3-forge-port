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
/* 28 */     LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.m_82127_("witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(
/* 29 */         Commands.m_82127_("sickness")
/* 30 */         .then(Commands.m_82127_("cure")
/* 31 */           .then(
/* 32 */             Commands.m_82129_("entity", (ArgumentType)EntityArgument.m_91449_())
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
/* 43 */     Entity entity = EntityArgument.m_91452_(context, "entity");
/* 44 */     int timeTillCured = 0;
/* 45 */     if (entity != null)
/*    */     {
/* 47 */       if (entity instanceof LivingEntity) {
/*    */         
/* 49 */         LivingEntity living = (LivingEntity)entity;
/* 50 */         if (!living.m_6095_().m_204039_(WitherStormModEntityTags.WITHER_SICKNESS_IMMUNE)) {
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
/* 61 */                 MutableComponent component = Component.m_237110_("commands.witherstormmod.sickness.startCure", new Object[] { living.m_5446_() });
/* 62 */                 source.m_288197_(() -> component, true);
/* 63 */                 tracker.beginCure();
/*    */               }
/*    */               else
/*    */               {
/* 67 */                 MutableComponent component = Component.m_237115_("commands.witherstormmod.sickness.cureNotInfected");
/* 68 */                 source.m_81352_((Component)component);
/*    */               }
/*    */             
/*    */             } else {
/*    */               
/* 73 */               MutableComponent component = Component.m_237110_("commands.witherstormmod.sickness.alreadyBeingCured", new Object[] { living.m_5446_() });
/* 74 */               source.m_81352_((Component)component);
/*    */             }
/*    */           
/*    */           } 
/*    */         } else {
/*    */           
/* 80 */           MutableComponent component = Component.m_237110_("commands.witherstormmod.sickness.immune", new Object[] { entity.m_5446_() });
/* 81 */           source.m_81352_((Component)component);
/*    */         }
/*    */       
/*    */       } else {
/*    */         
/* 86 */         MutableComponent component = Component.m_237115_("commands.witherstormmod.entity.arg.notLiving");
/* 87 */         source.m_81352_((Component)component);
/*    */       } 
/*    */     }
/* 90 */     return timeTillCured;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\StartCureCommand.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
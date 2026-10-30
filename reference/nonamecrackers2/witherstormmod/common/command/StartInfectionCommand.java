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
/*    */ public class StartInfectionCommand
/*    */ {
/*    */   public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
/* 28 */     LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.m_82127_("witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(
/* 29 */         Commands.m_82127_("sickness")
/* 30 */         .then(Commands.m_82127_("infect")
/* 31 */           .then(
/* 32 */             Commands.m_82129_("entity", (ArgumentType)EntityArgument.m_91449_())
/* 33 */             .executes(StartInfectionCommand::startInfection))));
/*    */ 
/*    */ 
/*    */     
/* 37 */     dispatcher.register(setPhaseCommand);
/*    */   }
/*    */ 
/*    */   
/*    */   private static int startInfection(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 42 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 43 */     Entity entity = EntityArgument.m_91452_(context, "entity");
/* 44 */     int timeTillInfected = 0;
/* 45 */     if (entity != null)
/*    */     {
/* 47 */       if (entity instanceof LivingEntity) {
/*    */         
/* 49 */         LivingEntity living = (LivingEntity)entity;
/* 50 */         if (!entity.m_6095_().m_204039_(WitherStormModEntityTags.WITHER_SICKNESS_IMMUNE)) {
/*    */           
/* 52 */           Optional<WitherSicknessTracker> optional = living.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).resolve();
/* 53 */           if (optional.isPresent()) {
/*    */             
/* 55 */             WitherSicknessTracker tracker = optional.get();
/* 56 */             timeTillInfected = tracker.getDelayTicks();
/* 57 */             if (!tracker.isInfected())
/*    */             {
/* 59 */               MutableComponent component = Component.m_237110_("commands.witherstormmod.sickness.startInfection", new Object[] { living.m_5446_() });
/* 60 */               source.m_288197_(() -> component, true);
/* 61 */               tracker.beginInfection();
/*    */             }
/*    */             else
/*    */             {
/* 65 */               MutableComponent component = Component.m_237110_("commands.witherstormmod.sickness.alreadyInfected", new Object[] { living.m_5446_() });
/* 66 */               source.m_81352_((Component)component);
/*    */             }
/*    */           
/*    */           } 
/*    */         } else {
/*    */           
/* 72 */           MutableComponent component = Component.m_237110_("commands.witherstormmod.sickness.immune", new Object[] { entity.m_5446_() });
/* 73 */           source.m_81352_((Component)component);
/*    */         }
/*    */       
/*    */       } else {
/*    */         
/* 78 */         MutableComponent component = Component.m_237115_("commands.witherstormmod.entity.arg.notLiving");
/* 79 */         source.m_81352_((Component)component);
/*    */       } 
/*    */     }
/* 82 */     return timeTillInfected;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\StartInfectionCommand.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
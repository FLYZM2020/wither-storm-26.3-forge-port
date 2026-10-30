/*     */ package nonamecrackers2.witherstormmod.common.command;
/*     */ 
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import net.minecraft.commands.CommandSourceStack;
/*     */ import net.minecraft.commands.Commands;
/*     */ import net.minecraft.commands.arguments.EntityArgument;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*     */ import nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BowelsCommands
/*     */ {
/*     */   public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
/*  26 */     LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.m_82127_("witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then((
/*  27 */         (LiteralArgumentBuilder)Commands.m_82127_("bowels")
/*  28 */         .then(Commands.m_82129_("entity", (ArgumentType)EntityArgument.m_91449_())
/*  29 */           .then(Commands.m_82129_("witherstorm", (ArgumentType)EntityArgument.m_91449_())
/*  30 */             .executes(BowelsCommands::enterStorm))))
/*     */ 
/*     */         
/*  33 */         .then(Commands.m_82127_("new")
/*  34 */           .then(Commands.m_82129_("witherstorm", (ArgumentType)EntityArgument.m_91449_())
/*  35 */             .executes(BowelsCommands::newBowels))));
/*     */ 
/*     */ 
/*     */     
/*  39 */     dispatcher.register(setPhaseCommand);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int enterStorm(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/*  44 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/*  45 */     Entity enteringEntity = EntityArgument.m_91452_(context, "entity");
/*  46 */     Entity entity = EntityArgument.m_91452_(context, "witherstorm");
/*  47 */     int phase = 0;
/*  48 */     if (entity != null)
/*     */     {
/*  50 */       if (entity instanceof WitherStormEntity) {
/*     */         
/*  52 */         WitherStormEntity storm = (WitherStormEntity)entity;
/*  53 */         phase = storm.getPhase();
/*  54 */         if (!enteringEntity.m_9236_().m_46472_().m_135782_().equals(WitherStormMod.bowelsLocation())) {
/*     */           
/*  56 */           WitherStormBowelsManager.BowelsEnterStatus flag = WitherStormBowelsManager.enter((ServerLevel)storm.m_9236_(), storm, enteringEntity);
/*  57 */           switch (flag) {
/*     */             
/*     */             case ENTITY_CANNOT_CHANGE:
/*  60 */               source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.enterBowels.failure.cannotChangeDim"));
/*     */             case CANT_SETUP_BOWELS:
/*  62 */               source.m_81352_((Component)Component.m_237110_("commands.witherstormmod.enterBowels.failure", new Object[] { storm.m_5446_() }));
/*     */               break;
/*     */           } 
/*     */ 
/*     */         
/*     */         } else {
/*  68 */           source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.enterBowels.dim.invalid"));
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/*  73 */         source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
/*     */       } 
/*     */     }
/*  76 */     return phase;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int newBowels(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/*  81 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/*  82 */     Entity entity = EntityArgument.m_91452_(context, "witherstorm");
/*  83 */     ServerLevel world = source.m_81372_();
/*  84 */     int phase = 0;
/*  85 */     if (entity instanceof WitherStormEntity) {
/*     */       
/*  87 */       WitherStormEntity storm = (WitherStormEntity)entity;
/*  88 */       phase = storm.getPhase();
/*  89 */       ServerLevel bowels = WitherStormMod.bowels(world);
/*  90 */       bowels.getCapability(WitherStormModCapabilities.BOWELS_MANAGER).ifPresent(manager -> {
/*     */             WitherStormBowelsManager.BowelsInstance instance = manager.get(storm.m_20148_());
/*     */ 
/*     */ 
/*     */             
/*     */             if (instance != null && !instance.isCompleted()) {
/*     */               instance.setCompleted(true);
/*     */ 
/*     */ 
/*     */               
/*     */               source.m_288197_((), true);
/*     */             } else {
/*     */               source.m_81352_((Component)Component.m_237110_("commands.witherstormmod.newBowels.failure", new Object[] { storm.m_5446_() }));
/*     */             } 
/*     */           });
/*     */     } else {
/* 106 */       source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
/*     */     } 
/* 108 */     return phase;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\BowelsCommands.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
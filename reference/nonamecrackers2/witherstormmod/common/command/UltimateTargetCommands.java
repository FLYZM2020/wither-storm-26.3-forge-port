/*     */ package nonamecrackers2.witherstormmod.common.command;
/*     */ 
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.commands.CommandSourceStack;
/*     */ import net.minecraft.commands.Commands;
/*     */ import net.minecraft.commands.arguments.EntityArgument;
/*     */ import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.network.chat.ClickEvent;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.network.chat.Style;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UltimateTargetCommands
/*     */ {
/*     */   public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
/*  31 */     LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.m_82127_("witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_("ultimateTarget")
/*  32 */         .then(Commands.m_82127_("set")
/*  33 */           .then(((RequiredArgumentBuilder)Commands.m_82129_("storm", (ArgumentType)EntityArgument.m_91449_())
/*  34 */             .then(Commands.m_82129_("entity", (ArgumentType)EntityArgument.m_91449_())
/*  35 */               .executes(UltimateTargetCommands::setUltimateTarget)))
/*     */             
/*  37 */             .then(Commands.m_82129_("pos", (ArgumentType)BlockPosArgument.m_118239_())
/*  38 */               .executes(UltimateTargetCommands::setPos)))))
/*     */ 
/*     */ 
/*     */         
/*  42 */         .then(((LiteralArgumentBuilder)Commands.m_82127_("get")
/*  43 */           .then(Commands.m_82127_("pos")
/*  44 */             .then(Commands.m_82129_("storm", (ArgumentType)EntityArgument.m_91449_())
/*  45 */               .executes(UltimateTargetCommands::getPos))))
/*     */ 
/*     */           
/*  48 */           .then(Commands.m_82127_("entity")
/*  49 */             .then(Commands.m_82129_("storm", (ArgumentType)EntityArgument.m_91449_())
/*  50 */               .executes(UltimateTargetCommands::getUltimateTarget)))))
/*     */ 
/*     */ 
/*     */         
/*  54 */         .then(Commands.m_82127_("clear")
/*  55 */           .then(((RequiredArgumentBuilder)Commands.m_82129_("storm", (ArgumentType)EntityArgument.m_91449_())
/*  56 */             .then(Commands.m_82127_("entity")
/*  57 */               .executes(UltimateTargetCommands::clearUltimateTarget)))
/*     */             
/*  59 */             .then(Commands.m_82127_("pos")
/*  60 */               .executes(UltimateTargetCommands::clearPos)))))
/*     */ 
/*     */ 
/*     */         
/*  64 */         .then(((LiteralArgumentBuilder)Commands.m_82127_("distractions")
/*  65 */           .then(Commands.m_82127_("makeDistracted")
/*  66 */             .then(Commands.m_82129_("witherstorm", (ArgumentType)EntityArgument.m_91449_())
/*  67 */               .executes(UltimateTargetCommands::makeUltimateTargetDistracted))))
/*     */ 
/*     */           
/*  70 */           .then(Commands.m_82127_("makeFocused")
/*  71 */             .then(Commands.m_82129_("witherstorm", (ArgumentType)EntityArgument.m_91449_())
/*  72 */               .executes(UltimateTargetCommands::makeUltimateTargetFocused)))))
/*     */ 
/*     */ 
/*     */         
/*  76 */         .then(((LiteralArgumentBuilder)Commands.m_82127_("chase")
/*  77 */           .then(Commands.m_82127_("begin")
/*  78 */             .then(Commands.m_82129_("witherstorm", (ArgumentType)EntityArgument.m_91449_())
/*  79 */               .executes(UltimateTargetCommands::beginChase))))
/*     */ 
/*     */           
/*  82 */           .then(Commands.m_82127_("stop")
/*  83 */             .then(Commands.m_82129_("witherstorm", (ArgumentType)EntityArgument.m_91449_())
/*  84 */               .executes(UltimateTargetCommands::stopChase)))));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  89 */     dispatcher.register(setPhaseCommand);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int setUltimateTarget(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/*  94 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/*  95 */     Entity target = EntityArgument.m_91452_(context, "entity");
/*  96 */     Entity entity = EntityArgument.m_91452_(context, "storm");
/*  97 */     if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*     */       
/*  99 */       if (target instanceof LivingEntity && !target.equals(storm) && !(target instanceof nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity)) {
/*     */         
/* 101 */         UltimateTargetManager manager = storm.getUltimateTargetManager().orElse(null);
/* 102 */         if (manager != null) {
/*     */           
/* 104 */           if (target.m_20148_().equals(manager.getTargetOverride()))
/*     */           {
/* 106 */             source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.ultimateTarget.set.duplicate"));
/*     */           }
/*     */           else
/*     */           {
/* 110 */             manager.setTargetOverride(target.m_20148_());
/* 111 */             source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.ultimateTarget.set.success", new Object[] { target.m_5446_() }), true);
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 116 */           source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.ultimateTarget.invalid"));
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 121 */         source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.ultimateTarget.set.entity.invalid"));
/*     */       }
/*     */        }
/*     */     else
/*     */     
/* 126 */     { source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.entity.arg.invalid")); }
/*     */     
/* 128 */     return target.m_19879_();
/*     */   }
/*     */ 
/*     */   
/*     */   private static int setPos(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 133 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 134 */     BlockPos pos = BlockPosArgument.m_118242_(context, "pos");
/* 135 */     Entity entity = EntityArgument.m_91452_(context, "storm");
/* 136 */     if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*     */       
/* 138 */       UltimateTargetManager manager = storm.getUltimateTargetManager().orElse(null);
/* 139 */       if (manager != null) {
/*     */         
/* 141 */         if (pos.equals(manager.getBlockTargetOverride()))
/*     */         {
/* 143 */           source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.ultimateTarget.set.duplicate"));
/*     */         }
/*     */         else
/*     */         {
/* 147 */           manager.setBlockTargetOverride(pos);
/* 148 */           source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.ultimateTarget.set.success", new Object[] { pos }), true);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 153 */         source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.ultimateTarget.invalid"));
/*     */       }
/*     */        }
/*     */     else
/*     */     
/* 158 */     { source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.entity.arg.invalid")); }
/*     */     
/* 160 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int getPos(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 165 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 166 */     Entity entity = EntityArgument.m_91452_(context, "storm");
/* 167 */     if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*     */       
/* 169 */       Vec3 pos = storm.getUltimateTargetPos();
/* 170 */       if (pos != null)
/*     */       {
/* 172 */         source.m_288197_(() -> { double x = Math.round(pos.f_82479_ * 10.0D) / 10.0D; double y = Math.round(pos.f_82480_ * 10.0D) / 10.0D; double z = Math.round(pos.f_82481_ * 10.0D) / 10.0D; String tpCommand = "/tp " + x + " " + y + " " + z; ClickEvent event = new ClickEvent(ClickEvent.Action.RUN_COMMAND, tpCommand); Style style = Style.f_131099_.m_131142_(event).m_131140_(ChatFormatting.BLUE); return (Component)Component.m_237110_("commands.witherstormmod.ultimateTarget.get.pos", new Object[] { storm.m_5446_(), Double.valueOf(x), Double.valueOf(y), Double.valueOf(z), Component.m_237115_("commands.witherstormmod.ultimateTarget.get.pos.click").m_130948_(style) }); }false);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 184 */         source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.ultimateTarget.get.pos.none", new Object[] { storm.m_5446_() }), false);
/*     */       }
/*     */        }
/*     */     else
/*     */     
/* 189 */     { source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.entity.arg.invalid")); }
/*     */     
/* 191 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int getUltimateTarget(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 196 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 197 */     Entity entity = EntityArgument.m_91452_(context, "storm");
/* 198 */     if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*     */       
/* 200 */       LivingEntity target = storm.getUltimateTarget();
/* 201 */       if (target != null)
/*     */       {
/* 203 */         source.m_288197_(() -> { String tpCommand = "/tp " + target.m_20149_(); ClickEvent event = new ClickEvent(ClickEvent.Action.RUN_COMMAND, tpCommand); Style style = Style.f_131099_.m_131142_(event).m_131140_(ChatFormatting.BLUE); return (Component)Component.m_237110_("commands.witherstormmod.ultimateTarget.get.player", new Object[] { storm.m_5446_(), target.m_5446_(), Component.m_237115_("commands.witherstormmod.ultimateTarget.get.player.click").m_130948_(style) }); }false);
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/*     */         
/* 212 */         source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.ultimateTarget.get.player.none", new Object[] { storm.m_5446_() }), false);
/*     */       }
/*     */        }
/*     */     else
/*     */     
/* 217 */     { source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.entity.arg.invalid")); }
/*     */     
/* 219 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int clearUltimateTarget(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 224 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 225 */     Entity entity = EntityArgument.m_91452_(context, "storm");
/* 226 */     if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*     */       
/* 228 */       UltimateTargetManager manager = storm.getUltimateTargetManager().orElse(null);
/* 229 */       if (manager != null)
/*     */       {
/* 231 */         manager.setTargetOverride(null);
/* 232 */         source.m_288197_(() -> Component.m_237115_("commands.witherstormmod.ultimateTarget.clear.success"), true);
/*     */       }
/*     */       else
/*     */       {
/* 236 */         source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.ultimateTarget.invalid"));
/*     */       }
/*     */        }
/*     */     else
/*     */     
/* 241 */     { source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.entity.arg.invalid")); }
/*     */     
/* 243 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int clearPos(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 248 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 249 */     Entity entity = EntityArgument.m_91452_(context, "storm");
/* 250 */     if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*     */       
/* 252 */       UltimateTargetManager manager = storm.getUltimateTargetManager().orElse(null);
/* 253 */       if (manager != null)
/*     */       {
/* 255 */         manager.setBlockTargetOverride(null);
/* 256 */         source.m_288197_(() -> Component.m_237115_("commands.witherstormmod.ultimateTarget.clear.success"), true);
/*     */       }
/*     */       else
/*     */       {
/* 260 */         source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.ultimateTarget.invalid"));
/*     */       }
/*     */        }
/*     */     else
/*     */     
/* 265 */     { source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.entity.arg.invalid")); }
/*     */     
/* 267 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int makeUltimateTargetDistracted(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 272 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 273 */     Entity entity = EntityArgument.m_91452_(context, "witherstorm");
/* 274 */     int phase = 0;
/* 275 */     if (entity != null)
/*     */     {
/* 277 */       if (entity instanceof WitherStormEntity) {
/*     */         
/* 279 */         WitherStormEntity storm = (WitherStormEntity)entity;
/* 280 */         UltimateTargetManager manager = storm.getUltimateTargetManager().orElse(null);
/* 281 */         if (manager != null) {
/*     */           
/* 283 */           if (!manager.isDistracted())
/*     */           {
/* 285 */             manager.makeDistracted(UltimateTargetManager.DistractionReason.FORCED);
/* 286 */             source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.distractions.ultimateTargetDistractions.makeDistracted.success", new Object[] { storm.m_5446_() }), true);
/*     */           }
/*     */           else
/*     */           {
/* 290 */             source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.distractions.ultimateTargetDistractions.makeDistracted.fail"));
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 295 */           source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.ultimateTarget.invalid"));
/*     */         } 
/* 297 */         phase = storm.getPhase();
/*     */       }
/*     */       else {
/*     */         
/* 301 */         source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
/*     */       } 
/*     */     }
/* 304 */     return phase;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int makeUltimateTargetFocused(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 309 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 310 */     Entity entity = EntityArgument.m_91452_(context, "witherstorm");
/* 311 */     int phase = 0;
/* 312 */     if (entity != null)
/*     */     {
/* 314 */       if (entity instanceof WitherStormEntity) {
/*     */         
/* 316 */         WitherStormEntity storm = (WitherStormEntity)entity;
/* 317 */         UltimateTargetManager manager = storm.getUltimateTargetManager().orElse(null);
/* 318 */         if (manager != null) {
/*     */           
/* 320 */           if (manager.isDistracted())
/*     */           {
/* 322 */             manager.makeFocused();
/* 323 */             source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.distractions.ultimateTargetDistractions.makeFocused.success", new Object[] { storm.m_5446_() }), true);
/*     */           }
/*     */           else
/*     */           {
/* 327 */             source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.distractions.ultimateTargetDistractions.makeFocused.fail"));
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 332 */           source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.ultimateTarget.invalid"));
/*     */         } 
/* 334 */         phase = storm.getPhase();
/*     */       }
/*     */       else {
/*     */         
/* 338 */         source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
/*     */       } 
/*     */     }
/* 341 */     return phase;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int beginChase(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 346 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 347 */     Entity entity = EntityArgument.m_91452_(context, "witherstorm");
/* 348 */     if (entity != null)
/*     */     {
/* 350 */       if (entity instanceof WitherStormEntity) {
/*     */         
/* 352 */         WitherStormEntity storm = (WitherStormEntity)entity;
/* 353 */         UltimateTargetManager manager = storm.getUltimateTargetManager().orElse(null);
/* 354 */         if (manager != null) {
/*     */           
/* 356 */           if (!manager.isTargetStationary())
/*     */           {
/* 358 */             manager.accelerate();
/* 359 */             source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.ultimateTarget.beginChase", new Object[] { storm.m_5446_() }), true);
/*     */           }
/*     */           else
/*     */           {
/* 363 */             source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.ultimateTarget.cannotBeginChase"));
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 368 */           source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.ultimateTarget.invalid"));
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 373 */         source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
/*     */       } 
/*     */     }
/* 376 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int stopChase(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 381 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 382 */     Entity entity = EntityArgument.m_91452_(context, "witherstorm");
/* 383 */     if (entity != null)
/*     */     {
/* 385 */       if (entity instanceof WitherStormEntity) {
/*     */         
/* 387 */         WitherStormEntity storm = (WitherStormEntity)entity;
/* 388 */         UltimateTargetManager manager = storm.getUltimateTargetManager().orElse(null);
/* 389 */         if (manager != null) {
/*     */           
/* 391 */           if (manager.isTargetStationary())
/*     */           {
/* 393 */             manager.deaccelerate();
/* 394 */             source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.ultimateTarget.stopChase", new Object[] { storm.m_5446_() }), true);
/*     */           }
/*     */           else
/*     */           {
/* 398 */             source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.ultimateTarget.cannotStopChase"));
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 403 */           source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.ultimateTarget.invalid"));
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 408 */         source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
/*     */       } 
/*     */     }
/* 411 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\UltimateTargetCommands.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
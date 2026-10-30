/*     */ package nonamecrackers2.witherstormmod.common.command;
/*     */ 
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import net.minecraft.commands.CommandSourceStack;
/*     */ import net.minecraft.commands.Commands;
/*     */ import net.minecraft.commands.arguments.EntityArgument;
/*     */ import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.level.GameRules;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.levelgen.structure.BoundingBox;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherSickened;
/*     */ import nonamecrackers2.witherstormmod.common.world.tainting.WorldTainting;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConversionCommands
/*     */ {
/*     */   public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
/*  28 */     LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.m_82127_("witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then((
/*  29 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_("convert")
/*  30 */         .then(
/*  31 */           Commands.m_82127_("canConvert")
/*  32 */           .then((
/*  33 */             (RequiredArgumentBuilder)Commands.m_82129_("mob", (ArgumentType)EntityArgument.m_91449_())
/*  34 */             .executes(ctx -> canConvert(ctx, false)))
/*  35 */             .then(
/*  36 */               Commands.m_82127_("fromWitherSickness")
/*  37 */               .executes(ctx -> canConvert(ctx, true))))))
/*     */ 
/*     */ 
/*     */         
/*  41 */         .then((
/*  42 */           (RequiredArgumentBuilder)Commands.m_82129_("mob", (ArgumentType)EntityArgument.m_91449_())
/*  43 */           .then(
/*  44 */             Commands.m_82127_("toSickened")
/*  45 */             .executes(ConversionCommands::convertMob)))
/*     */           
/*  47 */           .then(
/*  48 */             Commands.m_82127_("toCured")
/*  49 */             .executes(ConversionCommands::convertMobBack))))
/*     */ 
/*     */         
/*  52 */         .then((
/*  53 */           (RequiredArgumentBuilder)Commands.m_82129_("pos", (ArgumentType)BlockPosArgument.m_118239_())
/*  54 */           .then(
/*  55 */             Commands.m_82129_("to", (ArgumentType)BlockPosArgument.m_118239_())
/*  56 */             .executes(ConversionCommands::convertArea)))
/*     */           
/*  58 */           .executes(ConversionCommands::convertBlock)));
/*     */ 
/*     */     
/*  61 */     dispatcher.register(setPhaseCommand);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int canConvert(CommandContext<CommandSourceStack> context, boolean fromWitherSickness) throws CommandSyntaxException {
/*  66 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/*  67 */     Entity entity = EntityArgument.m_91452_(context, "mob");
/*  68 */     if (entity instanceof Mob) {
/*     */       
/*  70 */       if (WorldTainting.getInstance().canConvertMob(entity, fromWitherSickness)) {
/*     */         
/*  72 */         source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.conversion.convert.possible", new Object[] { entity.m_5446_() }), false);
/*  73 */         return 1;
/*     */       } 
/*     */ 
/*     */       
/*  77 */       source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.conversion.convert.impossible", new Object[] { entity.m_5446_() }), false);
/*  78 */       return 0;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  83 */     source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.conversion.invalid"));
/*  84 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int convertMob(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/*  90 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/*  91 */     Entity entity = EntityArgument.m_91452_(context, "mob");
/*  92 */     if (entity instanceof Mob) { Mob mob = (Mob)entity;
/*     */       
/*  94 */       if (WorldTainting.getInstance().convertMob(mob, false)) {
/*     */         
/*  96 */         source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.conversion.success", new Object[] { entity.m_5446_() }), true);
/*  97 */         return 1;
/*     */       } 
/*     */ 
/*     */       
/* 101 */       source.m_288197_(() -> Component.m_237115_("commands.witherstormmod.conversion.fail"), false);
/* 102 */       return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.conversion.invalid"));
/* 108 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int convertMobBack(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 114 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 115 */     Entity entity = EntityArgument.m_91452_(context, "mob");
/* 116 */     if (entity instanceof Mob && entity instanceof WitherSickened) { WitherSickened witherSickened = (WitherSickened)entity;
/*     */       
/* 118 */       if (witherSickened.cure(source.m_81372_())) {
/*     */         
/* 120 */         source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.conversion.success", new Object[] { entity.m_5446_() }), true);
/* 121 */         return 1;
/*     */       } 
/*     */ 
/*     */       
/* 125 */       source.m_288197_(() -> Component.m_237115_("commands.witherstormmod.conversion.fail"), false);
/* 126 */       return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 131 */     source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.conversion.invalid"));
/* 132 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int convertBlock(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 138 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 139 */     BlockPos pos = BlockPosArgument.m_118242_(context, "pos");
/* 140 */     if (WorldTainting.getInstance().convertBlock(pos, (Level)source.m_81372_())) {
/*     */       
/* 142 */       source.m_288197_(() -> Component.m_237115_("commands.witherstormmod.conversion.block.success"), true);
/* 143 */       return 1;
/*     */     } 
/* 145 */     source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.conversion.block.fail"));
/* 146 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int convertArea(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 151 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 152 */     BlockPos start = BlockPosArgument.m_118242_(context, "pos");
/* 153 */     BlockPos end = BlockPosArgument.m_118242_(context, "to");
/* 154 */     BoundingBox box = new BoundingBox(start.m_123341_(), start.m_123342_(), start.m_123343_(), end.m_123341_(), end.m_123342_(), end.m_123343_());
/* 155 */     int area = box.m_71056_() * box.m_71057_() * box.m_71058_();
/* 156 */     int maxAllowed = source.m_81372_().m_46469_().m_46215_(GameRules.f_263760_);
/* 157 */     if (area > maxAllowed) {
/*     */       
/* 159 */       source.m_81352_((Component)Component.m_237110_("commands.witherstormmod.conversion.block.area.excessive", new Object[] { Integer.valueOf(maxAllowed) }));
/* 160 */       return -1;
/*     */     } 
/* 162 */     int count = WorldTainting.getInstance().convertBlocks(box, (Level)source.m_81372_());
/* 163 */     source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.conversion.block.area.success", new Object[] { Integer.valueOf(count) }), true);
/* 164 */     return count;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\ConversionCommands.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
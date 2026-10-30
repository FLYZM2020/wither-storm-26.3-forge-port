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
/*  28 */     LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.literal("witherstormmod").requires(commandSource -> commandSource.hasPermission(2))).then((
/*  29 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal("convert")
/*  30 */         .then(
/*  31 */           Commands.literal("canConvert")
/*  32 */           .then((
/*  33 */             (RequiredArgumentBuilder)Commands.argument("mob", (ArgumentType)EntityArgument.entity())
/*  34 */             .executes(ctx -> canConvert(ctx, false)))
/*  35 */             .then(
/*  36 */               Commands.literal("fromWitherSickness")
/*  37 */               .executes(ctx -> canConvert(ctx, true))))))
/*     */ 
/*     */ 
/*     */         
/*  41 */         .then((
/*  42 */           (RequiredArgumentBuilder)Commands.argument("mob", (ArgumentType)EntityArgument.entity())
/*  43 */           .then(
/*  44 */             Commands.literal("toSickened")
/*  45 */             .executes(ConversionCommands::convertMob)))
/*     */           
/*  47 */           .then(
/*  48 */             Commands.literal("toCured")
/*  49 */             .executes(ConversionCommands::convertMobBack))))
/*     */ 
/*     */         
/*  52 */         .then((
/*  53 */           (RequiredArgumentBuilder)Commands.argument("pos", (ArgumentType)BlockPosArgument.blockPos())
/*  54 */           .then(
/*  55 */             Commands.argument("to", (ArgumentType)BlockPosArgument.blockPos())
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
/*  67 */     Entity entity = EntityArgument.getEntity(context, "mob");
/*  68 */     if (entity instanceof Mob) {
/*     */       
/*  70 */       if (WorldTainting.getInstance().canConvertMob(entity, fromWitherSickness)) {
/*     */         
/*  72 */         source.sendSuccess(() -> Component.translatable("commands.witherstormmod.conversion.convert.possible", new Object[] { entity.getDisplayName() }), false);
/*  73 */         return 1;
/*     */       } 
/*     */ 
/*     */       
/*  77 */       source.sendSuccess(() -> Component.translatable("commands.witherstormmod.conversion.convert.impossible", new Object[] { entity.getDisplayName() }), false);
/*  78 */       return 0;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  83 */     source.sendFailure((Component)Component.translatable("commands.witherstormmod.conversion.invalid"));
/*  84 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int convertMob(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/*  90 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/*  91 */     Entity entity = EntityArgument.getEntity(context, "mob");
/*  92 */     if (entity instanceof Mob) { Mob mob = (Mob)entity;
/*     */       
/*  94 */       if (WorldTainting.getInstance().convertMob(mob, false)) {
/*     */         
/*  96 */         source.sendSuccess(() -> Component.translatable("commands.witherstormmod.conversion.success", new Object[] { entity.getDisplayName() }), true);
/*  97 */         return 1;
/*     */       } 
/*     */ 
/*     */       
/* 101 */       source.sendSuccess(() -> Component.translatable("commands.witherstormmod.conversion.fail"), false);
/* 102 */       return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     source.sendFailure((Component)Component.translatable("commands.witherstormmod.conversion.invalid"));
/* 108 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int convertMobBack(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 114 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 115 */     Entity entity = EntityArgument.getEntity(context, "mob");
/* 116 */     if (entity instanceof Mob && entity instanceof WitherSickened) { WitherSickened witherSickened = (WitherSickened)entity;
/*     */       
/* 118 */       if (witherSickened.cure(source.getLevel())) {
/*     */         
/* 120 */         source.sendSuccess(() -> Component.translatable("commands.witherstormmod.conversion.success", new Object[] { entity.getDisplayName() }), true);
/* 121 */         return 1;
/*     */       } 
/*     */ 
/*     */       
/* 125 */       source.sendSuccess(() -> Component.translatable("commands.witherstormmod.conversion.fail"), false);
/* 126 */       return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 131 */     source.sendFailure((Component)Component.translatable("commands.witherstormmod.conversion.invalid"));
/* 132 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int convertBlock(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 138 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 139 */     BlockPos pos = BlockPosArgument.getLoadedBlockPos(context, "pos");
/* 140 */     if (WorldTainting.getInstance().convertBlock(pos, (Level)source.getLevel())) {
/*     */       
/* 142 */       source.sendSuccess(() -> Component.translatable("commands.witherstormmod.conversion.block.success"), true);
/* 143 */       return 1;
/*     */     } 
/* 145 */     source.sendFailure((Component)Component.translatable("commands.witherstormmod.conversion.block.fail"));
/* 146 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int convertArea(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 151 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 152 */     BlockPos start = BlockPosArgument.getLoadedBlockPos(context, "pos");
/* 153 */     BlockPos end = BlockPosArgument.getLoadedBlockPos(context, "to");
/* 154 */     BoundingBox box = new BoundingBox(start.getX(), start.getY(), start.getZ(), end.getX(), end.getY(), end.getZ());
/* 155 */     int area = box.getXSpan() * box.getYSpan() * box.getZSpan();
/* 156 */     int maxAllowed = source.getLevel().getGameRules().getInt(GameRules.RULE_COMMAND_MODIFICATION_BLOCK_LIMIT);
/* 157 */     if (area > maxAllowed) {
/*     */       
/* 159 */       source.sendFailure((Component)Component.translatable("commands.witherstormmod.conversion.block.area.excessive", new Object[] { Integer.valueOf(maxAllowed) }));
/* 160 */       return -1;
/*     */     } 
/* 162 */     int count = WorldTainting.getInstance().convertBlocks(box, (Level)source.getLevel());
/* 163 */     source.sendSuccess(() -> Component.translatable("commands.witherstormmod.conversion.block.area.success", new Object[] { Integer.valueOf(count) }), true);
/* 164 */     return count;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\ConversionCommands.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
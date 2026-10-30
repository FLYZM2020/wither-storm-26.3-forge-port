/*     */ package nonamecrackers2.witherstormmod.common.command;
/*     */ 
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.commands.CommandSourceStack;
/*     */ import net.minecraft.commands.Commands;
/*     */ import net.minecraft.commands.arguments.CompoundTagArgument;
/*     */ import net.minecraft.commands.arguments.EntityArgument;
/*     */ import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ 
/*     */ 
/*     */ public class CreateClusterCommand
/*     */ {
/*     */   public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
/*  32 */     LiteralArgumentBuilder<CommandSourceStack> createClusterCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.m_82127_("witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(
/*  33 */         Commands.m_82127_("cluster")
/*  34 */         .then(((LiteralArgumentBuilder)Commands.m_82127_("create")
/*  35 */           .then(
/*  36 */             Commands.m_82129_("start", (ArgumentType)BlockPosArgument.m_118239_())
/*  37 */             .then(
/*  38 */               Commands.m_82129_("end", (ArgumentType)BlockPosArgument.m_118239_())
/*  39 */               .then((
/*  40 */                 (RequiredArgumentBuilder)Commands.m_82129_("time", (ArgumentType)IntegerArgumentType.integer(0))
/*  41 */                 .then((
/*  42 */                   (RequiredArgumentBuilder)Commands.m_82129_("data", (ArgumentType)CompoundTagArgument.m_87657_())
/*  43 */                   .then(
/*  44 */                     Commands.m_82129_("storm", (ArgumentType)EntityArgument.m_91449_())
/*  45 */                     .executes(context -> createClusterFill(context, EntityArgument.m_91452_(context, "storm"), CompoundTagArgument.m_87660_(context, "data")))))
/*     */                   
/*  47 */                   .executes(context -> createClusterFill(context, null, CompoundTagArgument.m_87660_(context, "data")))))
/*     */                 
/*  49 */                 .executes(context -> createClusterFill(context, null, null))))))
/*     */ 
/*     */ 
/*     */           
/*  53 */           .then(
/*  54 */             Commands.m_82129_("pos", (ArgumentType)BlockPosArgument.m_118239_())
/*  55 */             .then(
/*  56 */               Commands.m_82129_("radius", (ArgumentType)IntegerArgumentType.integer(0))
/*  57 */               .then((
/*  58 */                 (RequiredArgumentBuilder)Commands.m_82129_("time", (ArgumentType)IntegerArgumentType.integer(0))
/*  59 */                 .then((
/*  60 */                   (RequiredArgumentBuilder)Commands.m_82129_("data", (ArgumentType)CompoundTagArgument.m_87657_())
/*  61 */                   .then(
/*  62 */                     Commands.m_82129_("storm", (ArgumentType)EntityArgument.m_91449_())
/*  63 */                     .executes(context -> createCluster(context, EntityArgument.m_91452_(context, "storm"), CompoundTagArgument.m_87660_(context, "data")))))
/*     */                   
/*  65 */                   .executes(context -> createCluster(context, null, CompoundTagArgument.m_87660_(context, "data")))))
/*     */                 
/*  67 */                 .executes(context -> createCluster(context, null, null)))))));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     dispatcher.register(createClusterCommand);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int createCluster(CommandContext<CommandSourceStack> context, @Nullable Entity entity, @Nullable CompoundTag tag) throws CommandSyntaxException {
/*  92 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/*  93 */     ServerLevel serverLevel = source.m_81372_();
/*  94 */     BlockPos pos = BlockPosArgument.m_118242_(context, "pos");
/*  95 */     int radius = IntegerArgumentType.getInteger(context, "radius");
/*  96 */     int time = IntegerArgumentType.getInteger(context, "time");
/*  97 */     BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_((Level)serverLevel);
/*     */     
/*  99 */     if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*     */       
/* 101 */       cluster.m_20242_(true);
/* 102 */       cluster.setPhysics(false);
/* 103 */       storm.getTrackedEntities().trackEntityToConsume((Entity)cluster); }
/*     */     
/* 105 */     else if (entity != null)
/*     */     
/* 107 */     { source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
/* 108 */       return 0; }
/*     */ 
/*     */     
/* 111 */     if (tag != null)
/* 112 */       cluster.m_20258_(tag); 
/* 113 */     cluster.populateWithRadius(pos, radius, blockstate -> true);
/* 114 */     cluster.setTime(time);
/* 115 */     serverLevel.m_7967_((Entity)cluster);
/*     */     
/* 117 */     return cluster.getSize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int createClusterFill(CommandContext<CommandSourceStack> context, @Nullable Entity entity, @Nullable CompoundTag tag) throws CommandSyntaxException {
/* 136 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 137 */     ServerLevel serverLevel = source.m_81372_();
/* 138 */     BlockPos start = BlockPosArgument.m_118242_(context, "start");
/* 139 */     BlockPos end = BlockPosArgument.m_118242_(context, "end");
/* 140 */     int time = IntegerArgumentType.getInteger(context, "time");
/* 141 */     BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_((Level)serverLevel);
/*     */     
/* 143 */     if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*     */       
/* 145 */       cluster.m_20242_(true);
/* 146 */       cluster.setPhysics(false);
/* 147 */       storm.getTrackedEntities().trackEntityToConsume((Entity)cluster); }
/*     */     
/* 149 */     else if (entity != null)
/*     */     
/* 151 */     { source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
/* 152 */       return 0; }
/*     */ 
/*     */     
/* 155 */     if (tag != null)
/* 156 */       cluster.m_20258_(tag); 
/* 157 */     cluster.populate(start, end, blockstate -> true);
/* 158 */     cluster.setTime(time);
/* 159 */     serverLevel.m_7967_((Entity)cluster);
/* 160 */     return cluster.getSize();
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\CreateClusterCommand.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
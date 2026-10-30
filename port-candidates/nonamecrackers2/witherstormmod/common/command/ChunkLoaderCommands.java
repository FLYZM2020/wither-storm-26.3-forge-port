/*     */ package nonamecrackers2.witherstormmod.common.command;
/*     */ 
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.commands.CommandSourceStack;
/*     */ import net.minecraft.commands.Commands;
/*     */ import net.minecraft.commands.arguments.EntityArgument;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.network.chat.MutableComponent;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.Ticket;
/*     */ import net.minecraft.util.SortedArraySet;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.level.ChunkPos;
/*     */ import nonamecrackers2.witherstormmod.common.capability.WitherStormModChunkLoader;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*     */ import nonamecrackers2.witherstormmod.mixin.MixinDistanceManager;
/*     */ import nonamecrackers2.witherstormmod.mixin.MixinServerChunkCache;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChunkLoaderCommands
/*     */ {
/*     */   public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
/*  36 */     LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.literal("witherstormmod").requires(commandSource -> commandSource.hasPermission(2))).then(((LiteralArgumentBuilder)Commands.literal("chunkLoader")
/*  37 */         .then(((LiteralArgumentBuilder)Commands.literal("get")
/*  38 */           .then(Commands.argument("storm", (ArgumentType)EntityArgument.entity())
/*  39 */             .executes(ctx -> get(ctx, true))))
/*     */           
/*  41 */           .executes(ctx -> get(ctx, false))))
/*     */         
/*  43 */         .then(Commands.literal("refresh")
/*  44 */           .executes(ChunkLoaderCommands::refresh)));
/*     */ 
/*     */     
/*  47 */     dispatcher.register(setPhaseCommand);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int get(CommandContext<CommandSourceStack> context, boolean hasStorm) throws CommandSyntaxException {
/*  52 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/*  53 */     WitherStormModChunkLoader loader = (WitherStormModChunkLoader)source.getLevel().getCapability(WitherStormModCapabilities.CHUNK_LOADER).orElse(null);
/*  54 */     if (loader != null)
/*     */     {
/*  56 */       if (hasStorm) {
/*     */         
/*  58 */         Entity entity = EntityArgument.getEntity(context, "storm");
/*  59 */         if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*     */           
/*  61 */           WitherStormModChunkLoader.Instance instance = loader.getInstance(storm.getUUID());
/*  62 */           if (instance != null) {
/*     */             
/*  64 */             source.sendSuccess(() -> Component.translatable("commands.witherstormmod.chunkloader.get.specific", new Object[] { storm.getDisplayName(), Integer.valueOf(storm.blockPosition().getX()), Integer.valueOf(storm.blockPosition().getZ()), Integer.valueOf(instance.getRadius()) }), false);
/*     */ 
/*     */             
/*  67 */             List<Ticket<?>> tickets = (List<Ticket<?>>)((SortedArraySet)getTickets(source.getLevel()).get(storm.chunkPosition().toLong())).stream().filter(t -> (t.getType() == WitherStormModChunkLoader.WITHER_STORM)).collect(Collectors.toList());
/*  68 */             source.sendSuccess(() -> Component.translatable("commands.witherstormmod.chunkloader.get.specific.ticket", new Object[] { Integer.valueOf(tickets.size()), entity.chunkPosition() }).withStyle(ChatFormatting.DARK_GRAY), hasStorm);
/*  69 */             return instance.getRadius();
/*     */           } 
/*     */ 
/*     */           
/*  73 */           source.sendFailure((Component)Component.translatable("commands.witherstormmod.chunkloader.none"));
/*     */            }
/*     */         
/*     */         else
/*     */         
/*  78 */         { source.sendFailure((Component)Component.translatable("commands.witherstormmod.entity.arg.invalid")); }
/*     */ 
/*     */       
/*     */       } else {
/*     */         
/*  83 */         Map<UUID, WitherStormModChunkLoader.Instance> instances = loader.getInstances();
/*  84 */         int totalChunkLoaders = 0;
/*  85 */         for (Map.Entry<UUID, WitherStormModChunkLoader.Instance> entry : instances.entrySet()) {
/*     */           
/*  87 */           WitherStormModChunkLoader.Instance instance = entry.getValue();
/*  88 */           totalChunkLoaders++;
/*  89 */           ChunkPos pos = instance.getPos();
/*  90 */           source.sendSuccess(() -> Component.translatable("commands.witherstormmod.chunkloader.get.specific", new Object[] { entry.getKey(), Integer.valueOf(pos.getMiddleBlockX()), Integer.valueOf(pos.getMiddleBlockZ()), Integer.valueOf(instance.getRadius()) }).withStyle(ChatFormatting.DARK_GRAY), false);
/*     */         } 
/*  92 */         MutableComponent mutableComponent = Component.translatable("commands.witherstormmod.chunkloader.get", new Object[] { Integer.valueOf(totalChunkLoaders) }).withStyle(ChatFormatting.YELLOW);
/*  93 */         source.sendSuccess(() -> text, false);
/*     */ 
/*     */         
/*  96 */         List<Ticket<?>> tickets = (List<Ticket<?>>)getTickets(source.getLevel()).values().stream().flatMap(Collection::stream).filter(t -> (t.getType() == WitherStormModChunkLoader.WITHER_STORM)).collect(Collectors.toList());
/*  97 */         source.sendSuccess(() -> Component.translatable("commands.witherstormmod.chunkloader.get.tickets", new Object[] { Integer.valueOf(tickets.size()), source.getLevel().dimension().location() }), hasStorm);
/*  98 */         return totalChunkLoaders;
/*     */       } 
/*     */     }
/* 101 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int refresh(CommandContext<CommandSourceStack> context) {
/* 106 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 107 */     WitherStormModChunkLoader loader = (WitherStormModChunkLoader)source.getLevel().getCapability(WitherStormModCapabilities.CHUNK_LOADER).orElse(null);
/* 108 */     if (loader != null) {
/*     */       
/* 110 */       loader.refreshAllLoaders();
/* 111 */       source.sendSuccess(() -> Component.translatable("commands.witherstormmod.chunkloader.refresh"), true);
/*     */     } 
/* 113 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Long2ObjectOpenHashMap<SortedArraySet<Ticket<?>>> getTickets(ServerLevel level) {
/* 118 */     MixinServerChunkCache cache = (MixinServerChunkCache)level.getChunkSource();
/* 119 */     MixinDistanceManager manager = (MixinDistanceManager)cache.getDistanceManager();
/* 120 */     return manager.getTickets();
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\ChunkLoaderCommands.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
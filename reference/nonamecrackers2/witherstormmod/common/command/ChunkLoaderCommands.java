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
/*  36 */     LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.m_82127_("witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(((LiteralArgumentBuilder)Commands.m_82127_("chunkLoader")
/*  37 */         .then(((LiteralArgumentBuilder)Commands.m_82127_("get")
/*  38 */           .then(Commands.m_82129_("storm", (ArgumentType)EntityArgument.m_91449_())
/*  39 */             .executes(ctx -> get(ctx, true))))
/*     */           
/*  41 */           .executes(ctx -> get(ctx, false))))
/*     */         
/*  43 */         .then(Commands.m_82127_("refresh")
/*  44 */           .executes(ChunkLoaderCommands::refresh)));
/*     */ 
/*     */     
/*  47 */     dispatcher.register(setPhaseCommand);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int get(CommandContext<CommandSourceStack> context, boolean hasStorm) throws CommandSyntaxException {
/*  52 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/*  53 */     WitherStormModChunkLoader loader = (WitherStormModChunkLoader)source.m_81372_().getCapability(WitherStormModCapabilities.CHUNK_LOADER).orElse(null);
/*  54 */     if (loader != null)
/*     */     {
/*  56 */       if (hasStorm) {
/*     */         
/*  58 */         Entity entity = EntityArgument.m_91452_(context, "storm");
/*  59 */         if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*     */           
/*  61 */           WitherStormModChunkLoader.Instance instance = loader.getInstance(storm.m_20148_());
/*  62 */           if (instance != null) {
/*     */             
/*  64 */             source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.chunkloader.get.specific", new Object[] { storm.m_5446_(), Integer.valueOf(storm.m_20183_().m_123341_()), Integer.valueOf(storm.m_20183_().m_123343_()), Integer.valueOf(instance.getRadius()) }), false);
/*     */ 
/*     */             
/*  67 */             List<Ticket<?>> tickets = (List<Ticket<?>>)((SortedArraySet)getTickets(source.m_81372_()).get(storm.m_146902_().m_45588_())).stream().filter(t -> (t.m_9428_() == WitherStormModChunkLoader.WITHER_STORM)).collect(Collectors.toList());
/*  68 */             source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.chunkloader.get.specific.ticket", new Object[] { Integer.valueOf(tickets.size()), entity.m_146902_() }).m_130940_(ChatFormatting.DARK_GRAY), hasStorm);
/*  69 */             return instance.getRadius();
/*     */           } 
/*     */ 
/*     */           
/*  73 */           source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.chunkloader.none"));
/*     */            }
/*     */         
/*     */         else
/*     */         
/*  78 */         { source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.entity.arg.invalid")); }
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
/*  90 */           source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.chunkloader.get.specific", new Object[] { entry.getKey(), Integer.valueOf(pos.m_151390_()), Integer.valueOf(pos.m_151393_()), Integer.valueOf(instance.getRadius()) }).m_130940_(ChatFormatting.DARK_GRAY), false);
/*     */         } 
/*  92 */         MutableComponent mutableComponent = Component.m_237110_("commands.witherstormmod.chunkloader.get", new Object[] { Integer.valueOf(totalChunkLoaders) }).m_130940_(ChatFormatting.YELLOW);
/*  93 */         source.m_288197_(() -> text, false);
/*     */ 
/*     */         
/*  96 */         List<Ticket<?>> tickets = (List<Ticket<?>>)getTickets(source.m_81372_()).values().stream().flatMap(Collection::stream).filter(t -> (t.m_9428_() == WitherStormModChunkLoader.WITHER_STORM)).collect(Collectors.toList());
/*  97 */         source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.chunkloader.get.tickets", new Object[] { Integer.valueOf(tickets.size()), source.m_81372_().m_46472_().m_135782_() }), hasStorm);
/*  98 */         return totalChunkLoaders;
/*     */       } 
/*     */     }
/* 101 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int refresh(CommandContext<CommandSourceStack> context) {
/* 106 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 107 */     WitherStormModChunkLoader loader = (WitherStormModChunkLoader)source.m_81372_().getCapability(WitherStormModCapabilities.CHUNK_LOADER).orElse(null);
/* 108 */     if (loader != null) {
/*     */       
/* 110 */       loader.refreshAllLoaders();
/* 111 */       source.m_288197_(() -> Component.m_237115_("commands.witherstormmod.chunkloader.refresh"), true);
/*     */     } 
/* 113 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Long2ObjectOpenHashMap<SortedArraySet<Ticket<?>>> getTickets(ServerLevel level) {
/* 118 */     MixinServerChunkCache cache = (MixinServerChunkCache)level.m_7726_();
/* 119 */     MixinDistanceManager manager = (MixinDistanceManager)cache.getDistanceManager();
/* 120 */     return manager.getTickets();
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\ChunkLoaderCommands.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
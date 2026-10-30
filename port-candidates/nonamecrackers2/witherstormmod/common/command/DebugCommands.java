/*     */ package nonamecrackers2.witherstormmod.common.command;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.DoubleArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.Collection;
/*     */ import net.minecraft.commands.CommandSourceStack;
/*     */ import net.minecraft.commands.Commands;
/*     */ import net.minecraft.commands.arguments.DimensionArgument;
/*     */ import net.minecraft.commands.arguments.EntityArgument;
/*     */ import net.minecraft.commands.arguments.ResourceLocationArgument;
/*     */ import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.resources.ResourceKey;
/*     */ import net.minecraft.resources.Identifier;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.effect.MobEffectInstance;
/*     */ import net.minecraft.world.effect.MobEffects;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.projectile.ThrownPotion;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Items;
/*     */ import net.minecraft.world.item.alchemy.PotionUtils;
/*     */ import net.minecraft.world.item.alchemy.Potions;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.level.WorldGenLevel;
/*     */ import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
/*     */ import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
/*     */ import net.minecraftforge.network.PacketDistributor;
/*     */ import net.minecraftforge.registries.IForgeRegistry;
/*     */ import net.minecraftforge.server.command.EnumArgument;
/*     */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
/*     */ import nonamecrackers2.witherstormmod.api.common.registry.WitherStormModRegistries;
/*     */ import nonamecrackers2.witherstormmod.common.capability.PlayerWitherStormData;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModFeatures;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*     */ import nonamecrackers2.witherstormmod.common.packet.CreateDebrisMessage;
/*     */ import nonamecrackers2.witherstormmod.common.util.EvolutionProfiler;
/*     */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*     */ import nonamecrackers2.witherstormmod.common.world.gen.feature.CommandBlockPodiumFeature;
/*     */ 
/*     */ public class DebugCommands {
/*     */   public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
/*  60 */     LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.literal("witherstormmod").requires(commandSource -> commandSource.hasPermission(2))).then((
/*  61 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal("debug")
/*  62 */         .then((
/*  63 */           (LiteralArgumentBuilder)Commands.literal("podium")
/*  64 */           .then(
/*  65 */             Commands.literal("place")
/*  66 */             .then(
/*  67 */               Commands.argument("dimension", (ArgumentType)DimensionArgument.dimension())
/*  68 */               .then(
/*  69 */                 Commands.argument("pos", (ArgumentType)BlockPosArgument.blockPos())
/*  70 */                 .executes(DebugCommands::placePodium)))))
/*     */ 
/*     */ 
/*     */           
/*  74 */           .then(
/*  75 */             Commands.literal("remove")
/*  76 */             .then(
/*  77 */               Commands.argument("dimension", (ArgumentType)DimensionArgument.dimension())
/*  78 */               .then(
/*  79 */                 Commands.argument("pos", (ArgumentType)BlockPosArgument.blockPos())
/*  80 */                 .executes(DebugCommands::removePodium))))))
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  85 */         .then(
/*  86 */           Commands.literal("debris")
/*  87 */           .then(
/*  88 */             Commands.literal("create")
/*  89 */             .then(
/*  90 */               Commands.argument("witherstorm", (ArgumentType)EntityArgument.entity())
/*  91 */               .executes(DebugCommands::createDebris)))))
/*     */ 
/*     */ 
/*     */         
/*  95 */         .then(
/*  96 */           Commands.literal("deathClusters")
/*  97 */           .then(
/*  98 */             Commands.literal("drop")
/*  99 */             .then(
/* 100 */               Commands.argument("storm", (ArgumentType)EntityArgument.entity())
/* 101 */               .executes(DebugCommands::dropDeathClusters)))))
/*     */ 
/*     */ 
/*     */         
/* 105 */         .then(
/* 106 */           Commands.literal("beacon")
/* 107 */           .then(
/* 108 */             Commands.literal("reset")
/* 109 */             .then(
/* 110 */               Commands.argument("player", (ArgumentType)EntityArgument.player())
/* 111 */               .executes(DebugCommands::resetPlayerBeaconData)))))
/*     */ 
/*     */ 
/*     */         
/* 115 */         .then((
/* 116 */           (LiteralArgumentBuilder)Commands.literal("evolutionProfiler")
/* 117 */           .then(
/* 118 */             Commands.literal("begin")
/* 119 */             .executes(DebugCommands::beginEvolutionProfiler)))
/*     */           
/* 121 */           .then(
/* 122 */             Commands.literal("query")
/* 123 */             .then(
/* 124 */               Commands.argument("witherstorm", (ArgumentType)EntityArgument.entity())
/* 125 */               .executes(DebugCommands::queryEvolutionProfiler)))))
/*     */ 
/*     */ 
/*     */         
/* 129 */         .then(
/* 130 */           Commands.literal("splitCluster")
/* 131 */           .then(
/* 132 */             Commands.argument("blockcluster", (ArgumentType)EntityArgument.entity())
/* 133 */             .then(
/* 134 */               Commands.argument("axis", (ArgumentType)EnumArgument.enumArgument(Direction.Axis.class))
/* 135 */               .executes(DebugCommands::splitBlockCluster)))))
/*     */ 
/*     */ 
/*     */         
/* 139 */         .then(
/* 140 */           Commands.literal("symbiont")
/* 141 */           .then(
/* 142 */             Commands.literal("doSpell")
/* 143 */             .then(
/* 144 */               Commands.argument("symbiont", (ArgumentType)EntityArgument.entity())
/* 145 */               .then(
/* 146 */                 Commands.argument("spell", (ArgumentType)ResourceLocationArgument.id())
/* 147 */                 .executes(DebugCommands::doSymbiontSpell))))))
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 152 */         .then(
/* 153 */           Commands.literal("potionTest")
/* 154 */           .executes(DebugCommands::potionTest)))
/*     */         
/* 156 */         .then(
/* 157 */           Commands.literal("caveRumble")
/* 158 */           .then(
/* 159 */             Commands.argument("players", (ArgumentType)EntityArgument.players())
/* 160 */             .then(
/* 161 */               Commands.argument("intensity", (ArgumentType)DoubleArgumentType.doubleArg(0.0D, 1.0D))
/* 162 */               .executes(DebugCommands::doCaveRumble)))));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     dispatcher.register(setPhaseCommand);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int placePodium(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 172 */     ServerLevel world = DimensionArgument.getDimension(context, "dimension");
/* 173 */     BlockPos pos = BlockPosArgument.getLoadedBlockPos(context, "pos");
/* 174 */     int result = 0;
/*     */     
/*     */     try {
/* 177 */       if (((ConfiguredFeature)WitherStormModFeatures.getConfiguredFeature(world, WitherStormModFeatures.COMMAND_BLOCK_PODIUM_FEATURE.getId()).value()).place((WorldGenLevel)world, world.getChunkSource().getGenerator(), RandomSource.create(), pos)) {
/* 178 */         result = 1;
/*     */       }
/* 180 */     } catch (Exception e) {
/*     */       
/* 182 */       e.printStackTrace();
/*     */     } 
/* 184 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int removePodium(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 189 */     ServerLevel world = DimensionArgument.getDimension(context, "dimension");
/* 190 */     BlockPos pos = BlockPosArgument.getLoadedBlockPos(context, "pos");
/* 191 */     int result = 0;
/*     */ 
/*     */     
/*     */     try {
/* 195 */       ConfiguredFeature<NoneFeatureConfiguration, CommandBlockPodiumFeature> configured = (ConfiguredFeature<NoneFeatureConfiguration, CommandBlockPodiumFeature>)WitherStormModFeatures.getConfiguredFeature(world, WitherStormModFeatures.COMMAND_BLOCK_PODIUM_FEATURE.getId()).value();
/* 196 */       if (((CommandBlockPodiumFeature)configured.feature()).remove((WorldGenLevel)world, world.getChunkSource().getGenerator(), RandomSource.create(), pos, configured.config())) {
/* 197 */         result = 1;
/*     */       }
/* 199 */     } catch (Exception e) {
/*     */       
/* 201 */       e.printStackTrace();
/*     */     } 
/* 203 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int createDebris(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 208 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 209 */     Entity entity = EntityArgument.getEntity(context, "witherstorm");
/* 210 */     int phase = 0;
/* 211 */     if (entity != null)
/*     */     {
/* 213 */       if (entity instanceof WitherStormEntity) {
/*     */         
/* 215 */         WitherStormEntity storm = (WitherStormEntity)entity;
/* 216 */         phase = storm.getPhase();
/* 217 */         WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> storm.level().dimension()), new CreateDebrisMessage(storm, storm.isDeadOrPlayingDead()));
/* 218 */         source.sendSuccess(() -> Component.translatable("commands.witherstormmod.createDebris.success", new Object[] { storm.getDisplayName() }), true);
/*     */       }
/*     */       else {
/*     */         
/* 222 */         source.sendFailure((Component)Component.translatable("commands.witherstormmod.entity.arg.invalid"));
/*     */       } 
/*     */     }
/* 225 */     return phase;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int dropDeathClusters(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 230 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 231 */     Entity entity = EntityArgument.getEntity(context, "storm");
/* 232 */     int phase = 0;
/* 233 */     if (entity != null)
/*     */     {
/* 235 */       if (entity instanceof WitherStormEntity) {
/*     */         
/* 237 */         WitherStormEntity storm = (WitherStormEntity)entity;
/* 238 */         phase = storm.getPhase();
/* 239 */         storm.dropMassCluster(phase);
/*     */       }
/*     */       else {
/*     */         
/* 243 */         source.sendFailure((Component)Component.translatable("commands.witherstormmod.entity.arg.invalid"));
/*     */       } 
/*     */     }
/* 246 */     return phase;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int resetPlayerBeaconData(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 251 */     ServerPlayer player = EntityArgument.getPlayer(context, "player");
/* 252 */     player.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(data -> data.setActivatedSuperBeacon(false));
/* 253 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int beginEvolutionProfiler(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 258 */     ServerPlayer player = ((CommandSourceStack)context.getSource()).getPlayer();
/* 259 */     WitherStormEntity storm = (WitherStormEntity)((EntityType)WitherStormModEntityTypes.WITHER_STORM.get()).create(player.level());
/* 260 */     storm.moveTo(player.position().add(0.0D, 10.0D, 0.0D));
/* 261 */     storm.getEvolutionProfiler().begin();
/* 262 */     player.level().addFreshEntity((Entity)storm);
/* 263 */     WitherStormModConfig.SERVER.ultimateTargetingType.set(UltimateTargetManager.TargetingType.NONE);
/* 264 */     WitherStormModConfig.SERVER.evolutionAttributeModifier.set(WitherStormModConfig.SERVER.evolutionAttributeModifier.getDefault());
/* 265 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int queryEvolutionProfiler(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 270 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 271 */     Entity entity = EntityArgument.getEntity(context, "witherstorm");
/* 272 */     if (entity != null)
/*     */     {
/* 274 */       if (entity instanceof WitherStormEntity) {
/*     */         
/* 276 */         WitherStormEntity storm = (WitherStormEntity)entity;
/* 277 */         EvolutionProfiler profiler = storm.getEvolutionProfiler();
/* 278 */         if (profiler.isProfiling()) {
/* 279 */           source.sendSuccess(() -> Component.literal("Consumed entities per second: " + Math.round(storm.getEvolutionProfiler().getConsumedEntitiesPerSecond() * 10.0D) / 10.0D), false);
/*     */         }
/*     */       } else {
/*     */         
/* 283 */         source.sendFailure((Component)Component.translatable("commands.witherstormmod.entity.arg.invalid"));
/*     */       } 
/*     */     }
/* 286 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int splitBlockCluster(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 291 */     Entity entity = EntityArgument.getEntity(context, "blockcluster");
/* 292 */     Direction.Axis axis = (Direction.Axis)context.getArgument("axis", Direction.Axis.class);
/* 293 */     if (entity instanceof BlockClusterEntity) { BlockClusterEntity cluster = (BlockClusterEntity)entity;
/*     */       
/* 295 */       BlockClusterEntity split = cluster.splitAt(axis);
/* 296 */       if (split != null)
/* 297 */         entity.level().addFreshEntity((Entity)split);  }
/*     */     
/* 299 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int doSymbiontSpell(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 304 */     Entity entity = EntityArgument.getEntity(context, "symbiont");
/* 305 */     Identifier spell = (Identifier)context.getArgument("spell", Identifier.class);
/* 306 */     if (entity instanceof WitheredSymbiontEntity) { WitheredSymbiontEntity symbiont = (WitheredSymbiontEntity)entity;
/*     */       
/* 308 */       SpellType type = (SpellType)((IForgeRegistry)WitherStormModRegistries.SPELL_TYPES.get()).getValue(spell);
/* 309 */       if (type != null) {
/*     */         
/* 311 */         symbiont.setTarget((LivingEntity)((CommandSourceStack)context.getSource()).getPlayer());
/* 312 */         symbiont.setAndCastSpell(type);
/*     */       } 
/* 314 */       return 1; }
/*     */     
/* 316 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int potionTest(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 321 */     ServerPlayer player = ((CommandSourceStack)context.getSource()).getPlayer();
/* 322 */     ThrownPotion potion = new ThrownPotion(player.level(), (LivingEntity)player);
/* 323 */     ItemStack item = new ItemStack((ItemLike)Items.SPLASH_POTION);
/* 324 */     PotionUtils.setPotion(item, Potions.WATER);
/* 325 */     PotionUtils.setCustomEffects(item, Lists.newArrayList((Object[])new MobEffectInstance[] { new MobEffectInstance(MobEffects.WITHER, 60, 2) }));
/* 326 */     potion.setItem(item);
/* 327 */     player.level().addFreshEntity((Entity)potion);
/* 328 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int doCaveRumble(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 333 */     Collection<ServerPlayer> players = EntityArgument.getPlayers(context, "players");
/* 334 */     double intensity = DoubleArgumentType.getDouble(context, "intensity");
/* 335 */     RandomSource random = RandomSource.create();
/* 336 */     for (ServerPlayer player : players)
/* 337 */       WorldUtil.doCaveRumble(((CommandSourceStack)context.getSource()).getLevel(), player, intensity, random); 
/* 338 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\DebugCommands.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
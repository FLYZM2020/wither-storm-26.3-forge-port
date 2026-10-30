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
/*     */ import net.minecraft.resources.ResourceLocation;
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
/*  60 */     LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.m_82127_("witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then((
/*  61 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_("debug")
/*  62 */         .then((
/*  63 */           (LiteralArgumentBuilder)Commands.m_82127_("podium")
/*  64 */           .then(
/*  65 */             Commands.m_82127_("place")
/*  66 */             .then(
/*  67 */               Commands.m_82129_("dimension", (ArgumentType)DimensionArgument.m_88805_())
/*  68 */               .then(
/*  69 */                 Commands.m_82129_("pos", (ArgumentType)BlockPosArgument.m_118239_())
/*  70 */                 .executes(DebugCommands::placePodium)))))
/*     */ 
/*     */ 
/*     */           
/*  74 */           .then(
/*  75 */             Commands.m_82127_("remove")
/*  76 */             .then(
/*  77 */               Commands.m_82129_("dimension", (ArgumentType)DimensionArgument.m_88805_())
/*  78 */               .then(
/*  79 */                 Commands.m_82129_("pos", (ArgumentType)BlockPosArgument.m_118239_())
/*  80 */                 .executes(DebugCommands::removePodium))))))
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  85 */         .then(
/*  86 */           Commands.m_82127_("debris")
/*  87 */           .then(
/*  88 */             Commands.m_82127_("create")
/*  89 */             .then(
/*  90 */               Commands.m_82129_("witherstorm", (ArgumentType)EntityArgument.m_91449_())
/*  91 */               .executes(DebugCommands::createDebris)))))
/*     */ 
/*     */ 
/*     */         
/*  95 */         .then(
/*  96 */           Commands.m_82127_("deathClusters")
/*  97 */           .then(
/*  98 */             Commands.m_82127_("drop")
/*  99 */             .then(
/* 100 */               Commands.m_82129_("storm", (ArgumentType)EntityArgument.m_91449_())
/* 101 */               .executes(DebugCommands::dropDeathClusters)))))
/*     */ 
/*     */ 
/*     */         
/* 105 */         .then(
/* 106 */           Commands.m_82127_("beacon")
/* 107 */           .then(
/* 108 */             Commands.m_82127_("reset")
/* 109 */             .then(
/* 110 */               Commands.m_82129_("player", (ArgumentType)EntityArgument.m_91466_())
/* 111 */               .executes(DebugCommands::resetPlayerBeaconData)))))
/*     */ 
/*     */ 
/*     */         
/* 115 */         .then((
/* 116 */           (LiteralArgumentBuilder)Commands.m_82127_("evolutionProfiler")
/* 117 */           .then(
/* 118 */             Commands.m_82127_("begin")
/* 119 */             .executes(DebugCommands::beginEvolutionProfiler)))
/*     */           
/* 121 */           .then(
/* 122 */             Commands.m_82127_("query")
/* 123 */             .then(
/* 124 */               Commands.m_82129_("witherstorm", (ArgumentType)EntityArgument.m_91449_())
/* 125 */               .executes(DebugCommands::queryEvolutionProfiler)))))
/*     */ 
/*     */ 
/*     */         
/* 129 */         .then(
/* 130 */           Commands.m_82127_("splitCluster")
/* 131 */           .then(
/* 132 */             Commands.m_82129_("blockcluster", (ArgumentType)EntityArgument.m_91449_())
/* 133 */             .then(
/* 134 */               Commands.m_82129_("axis", (ArgumentType)EnumArgument.enumArgument(Direction.Axis.class))
/* 135 */               .executes(DebugCommands::splitBlockCluster)))))
/*     */ 
/*     */ 
/*     */         
/* 139 */         .then(
/* 140 */           Commands.m_82127_("symbiont")
/* 141 */           .then(
/* 142 */             Commands.m_82127_("doSpell")
/* 143 */             .then(
/* 144 */               Commands.m_82129_("symbiont", (ArgumentType)EntityArgument.m_91449_())
/* 145 */               .then(
/* 146 */                 Commands.m_82129_("spell", (ArgumentType)ResourceLocationArgument.m_106984_())
/* 147 */                 .executes(DebugCommands::doSymbiontSpell))))))
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 152 */         .then(
/* 153 */           Commands.m_82127_("potionTest")
/* 154 */           .executes(DebugCommands::potionTest)))
/*     */         
/* 156 */         .then(
/* 157 */           Commands.m_82127_("caveRumble")
/* 158 */           .then(
/* 159 */             Commands.m_82129_("players", (ArgumentType)EntityArgument.m_91470_())
/* 160 */             .then(
/* 161 */               Commands.m_82129_("intensity", (ArgumentType)DoubleArgumentType.doubleArg(0.0D, 1.0D))
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
/* 172 */     ServerLevel world = DimensionArgument.m_88808_(context, "dimension");
/* 173 */     BlockPos pos = BlockPosArgument.m_118242_(context, "pos");
/* 174 */     int result = 0;
/*     */     
/*     */     try {
/* 177 */       if (((ConfiguredFeature)WitherStormModFeatures.getConfiguredFeature(world, WitherStormModFeatures.COMMAND_BLOCK_PODIUM_FEATURE.getId()).m_203334_()).m_224953_((WorldGenLevel)world, world.m_7726_().m_8481_(), RandomSource.m_216327_(), pos)) {
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
/* 189 */     ServerLevel world = DimensionArgument.m_88808_(context, "dimension");
/* 190 */     BlockPos pos = BlockPosArgument.m_118242_(context, "pos");
/* 191 */     int result = 0;
/*     */ 
/*     */     
/*     */     try {
/* 195 */       ConfiguredFeature<NoneFeatureConfiguration, CommandBlockPodiumFeature> configured = (ConfiguredFeature<NoneFeatureConfiguration, CommandBlockPodiumFeature>)WitherStormModFeatures.getConfiguredFeature(world, WitherStormModFeatures.COMMAND_BLOCK_PODIUM_FEATURE.getId()).m_203334_();
/* 196 */       if (((CommandBlockPodiumFeature)configured.f_65377_()).remove((WorldGenLevel)world, world.m_7726_().m_8481_(), RandomSource.m_216327_(), pos, configured.f_65378_())) {
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
/* 209 */     Entity entity = EntityArgument.m_91452_(context, "witherstorm");
/* 210 */     int phase = 0;
/* 211 */     if (entity != null)
/*     */     {
/* 213 */       if (entity instanceof WitherStormEntity) {
/*     */         
/* 215 */         WitherStormEntity storm = (WitherStormEntity)entity;
/* 216 */         phase = storm.getPhase();
/* 217 */         WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> storm.m_9236_().m_46472_()), new CreateDebrisMessage(storm, storm.isDeadOrPlayingDead()));
/* 218 */         source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.createDebris.success", new Object[] { storm.m_5446_() }), true);
/*     */       }
/*     */       else {
/*     */         
/* 222 */         source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
/*     */       } 
/*     */     }
/* 225 */     return phase;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int dropDeathClusters(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 230 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 231 */     Entity entity = EntityArgument.m_91452_(context, "storm");
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
/* 243 */         source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
/*     */       } 
/*     */     }
/* 246 */     return phase;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int resetPlayerBeaconData(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 251 */     ServerPlayer player = EntityArgument.m_91474_(context, "player");
/* 252 */     player.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(data -> data.setActivatedSuperBeacon(false));
/* 253 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int beginEvolutionProfiler(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 258 */     ServerPlayer player = ((CommandSourceStack)context.getSource()).m_230896_();
/* 259 */     WitherStormEntity storm = (WitherStormEntity)((EntityType)WitherStormModEntityTypes.WITHER_STORM.get()).m_20615_(player.m_9236_());
/* 260 */     storm.m_20219_(player.m_20182_().m_82520_(0.0D, 10.0D, 0.0D));
/* 261 */     storm.getEvolutionProfiler().begin();
/* 262 */     player.m_9236_().m_7967_((Entity)storm);
/* 263 */     WitherStormModConfig.SERVER.ultimateTargetingType.set(UltimateTargetManager.TargetingType.NONE);
/* 264 */     WitherStormModConfig.SERVER.evolutionAttributeModifier.set(WitherStormModConfig.SERVER.evolutionAttributeModifier.getDefault());
/* 265 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int queryEvolutionProfiler(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 270 */     CommandSourceStack source = (CommandSourceStack)context.getSource();
/* 271 */     Entity entity = EntityArgument.m_91452_(context, "witherstorm");
/* 272 */     if (entity != null)
/*     */     {
/* 274 */       if (entity instanceof WitherStormEntity) {
/*     */         
/* 276 */         WitherStormEntity storm = (WitherStormEntity)entity;
/* 277 */         EvolutionProfiler profiler = storm.getEvolutionProfiler();
/* 278 */         if (profiler.isProfiling()) {
/* 279 */           source.m_288197_(() -> Component.m_237113_("Consumed entities per second: " + Math.round(storm.getEvolutionProfiler().getConsumedEntitiesPerSecond() * 10.0D) / 10.0D), false);
/*     */         }
/*     */       } else {
/*     */         
/* 283 */         source.m_81352_((Component)Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
/*     */       } 
/*     */     }
/* 286 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int splitBlockCluster(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 291 */     Entity entity = EntityArgument.m_91452_(context, "blockcluster");
/* 292 */     Direction.Axis axis = (Direction.Axis)context.getArgument("axis", Direction.Axis.class);
/* 293 */     if (entity instanceof BlockClusterEntity) { BlockClusterEntity cluster = (BlockClusterEntity)entity;
/*     */       
/* 295 */       BlockClusterEntity split = cluster.splitAt(axis);
/* 296 */       if (split != null)
/* 297 */         entity.m_9236_().m_7967_((Entity)split);  }
/*     */     
/* 299 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int doSymbiontSpell(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 304 */     Entity entity = EntityArgument.m_91452_(context, "symbiont");
/* 305 */     ResourceLocation spell = (ResourceLocation)context.getArgument("spell", ResourceLocation.class);
/* 306 */     if (entity instanceof WitheredSymbiontEntity) { WitheredSymbiontEntity symbiont = (WitheredSymbiontEntity)entity;
/*     */       
/* 308 */       SpellType type = (SpellType)((IForgeRegistry)WitherStormModRegistries.SPELL_TYPES.get()).getValue(spell);
/* 309 */       if (type != null) {
/*     */         
/* 311 */         symbiont.m_6710_((LivingEntity)((CommandSourceStack)context.getSource()).m_230896_());
/* 312 */         symbiont.setAndCastSpell(type);
/*     */       } 
/* 314 */       return 1; }
/*     */     
/* 316 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int potionTest(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 321 */     ServerPlayer player = ((CommandSourceStack)context.getSource()).m_230896_();
/* 322 */     ThrownPotion potion = new ThrownPotion(player.m_9236_(), (LivingEntity)player);
/* 323 */     ItemStack item = new ItemStack((ItemLike)Items.f_42736_);
/* 324 */     PotionUtils.m_43549_(item, Potions.f_43599_);
/* 325 */     PotionUtils.m_43552_(item, Lists.newArrayList((Object[])new MobEffectInstance[] { new MobEffectInstance(MobEffects.f_19615_, 60, 2) }));
/* 326 */     potion.m_37446_(item);
/* 327 */     player.m_9236_().m_7967_((Entity)potion);
/* 328 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int doCaveRumble(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 333 */     Collection<ServerPlayer> players = EntityArgument.m_91477_(context, "players");
/* 334 */     double intensity = DoubleArgumentType.getDouble(context, "intensity");
/* 335 */     RandomSource random = RandomSource.m_216327_();
/* 336 */     for (ServerPlayer player : players)
/* 337 */       WorldUtil.doCaveRumble(((CommandSourceStack)context.getSource()).m_81372_(), player, intensity, random); 
/* 338 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\DebugCommands.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
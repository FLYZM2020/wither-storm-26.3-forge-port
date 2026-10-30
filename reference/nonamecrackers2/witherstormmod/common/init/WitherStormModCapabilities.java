/*     */ package nonamecrackers2.witherstormmod.common.init;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraftforge.common.capabilities.Capability;
/*     */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*     */ import net.minecraftforge.common.capabilities.CapabilityToken;
/*     */ import net.minecraftforge.common.capabilities.ICapabilityProvider;
/*     */ import net.minecraftforge.common.capabilities.ICapabilitySerializable;
/*     */ import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
/*     */ import net.minecraftforge.common.util.LazyOptional;
/*     */ import net.minecraftforge.event.AttachCapabilitiesEvent;
/*     */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*     */ import nonamecrackers2.witherstormmod.common.capability.ChunkLoadingBlockEntities;
/*     */ import nonamecrackers2.witherstormmod.common.capability.EntityCapability;
/*     */ import nonamecrackers2.witherstormmod.common.capability.PlayerWitherStormData;
/*     */ import nonamecrackers2.witherstormmod.common.capability.WitherSicknessTracker;
/*     */ import nonamecrackers2.witherstormmod.common.capability.WitherStormAutoSpawner;
/*     */ import nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager;
/*     */ import nonamecrackers2.witherstormmod.common.capability.WitherStormModChunkLoader;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ 
/*     */ public class WitherStormModCapabilities {
/*  32 */   public static final Capability<WitherStormModChunkLoader> CHUNK_LOADER = CapabilityManager.get(new CapabilityToken<WitherStormModChunkLoader>() {  }
/*  33 */     ); public static final Capability<WitherSicknessTracker> WITHER_SICKNESS_TRACKER = CapabilityManager.get(new CapabilityToken<WitherSicknessTracker>() {  }
/*  34 */     ); public static final Capability<PlayerWitherStormData> PLAYER_WITHER_STORM_DATA = CapabilityManager.get(new CapabilityToken<PlayerWitherStormData>() {  }
/*  35 */     ); public static final Capability<WitherStormBowelsManager> BOWELS_MANAGER = CapabilityManager.get(new CapabilityToken<WitherStormBowelsManager>() {  }
/*  36 */     ); public static final Capability<ChunkLoadingBlockEntities> CHUNK_LOADING_BLOCK_ENTITIES = CapabilityManager.get(new CapabilityToken<ChunkLoadingBlockEntities>() {  }
/*  37 */     ); public static final Capability<WitherStormAutoSpawner> WITHER_STORM_AUTO_SPAWNER = CapabilityManager.get(new CapabilityToken<WitherStormAutoSpawner>() {  }
/*     */     );
/*     */   
/*     */   public static void registerCapabilities(RegisterCapabilitiesEvent event) {
/*  41 */     event.register(WitherSicknessTracker.class);
/*  42 */     event.register(PlayerWitherStormData.class);
/*  43 */     event.register(WitherStormBowelsManager.class);
/*  44 */     event.register(ChunkLoadingBlockEntities.class);
/*  45 */     event.register(WitherStormModChunkLoader.class);
/*  46 */     event.register(WitherStormAutoSpawner.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void attachWorldCapabilities(AttachCapabilitiesEvent<Level> event) {
/*  51 */     Level world = (Level)event.getObject();
/*  52 */     if (!world.f_46443_) {
/*     */       
/*  54 */       final LazyOptional<ChunkLoadingBlockEntities> chunkLoadingBlockEntities = LazyOptional.of(() -> new ChunkLoadingBlockEntities((ServerLevel)world));
/*  55 */       event.addCapability(new ResourceLocation("witherstormmod", "chunk_loading_block_entities"), (ICapabilityProvider)new ICapabilitySerializable<CompoundTag>()
/*     */           {
/*     */             
/*     */             public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
/*     */             {
/*  60 */               return (cap == WitherStormModCapabilities.CHUNK_LOADING_BLOCK_ENTITIES) ? chunkLoadingBlockEntities.cast() : LazyOptional.empty();
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public CompoundTag serializeNBT() {
/*  66 */               return ((ChunkLoadingBlockEntities)chunkLoadingBlockEntities.orElse(null)).write();
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void deserializeNBT(CompoundTag nbt) {
/*  72 */               ((ChunkLoadingBlockEntities)chunkLoadingBlockEntities.orElse(null)).read(nbt);
/*     */             }
/*     */           });
/*  75 */       Objects.requireNonNull(chunkLoadingBlockEntities); event.addListener(chunkLoadingBlockEntities::invalidate);
/*     */       
/*  77 */       final LazyOptional<WitherStormModChunkLoader> chunkLoader = LazyOptional.of(() -> new WitherStormModChunkLoader((ServerLevel)world));
/*  78 */       event.addCapability(new ResourceLocation("witherstormmod", "chunk_loader"), (ICapabilityProvider)new ICapabilitySerializable<Tag>()
/*     */           {
/*     */             
/*     */             public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side)
/*     */             {
/*  83 */               return (capability == WitherStormModCapabilities.CHUNK_LOADER) ? chunkLoader.cast() : LazyOptional.empty();
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public Tag serializeNBT() {
/*  89 */               return (Tag)((WitherStormModChunkLoader)chunkLoader.orElse(null)).write();
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void deserializeNBT(Tag nbt) {
/*  95 */               ((WitherStormModChunkLoader)chunkLoader.orElse(null)).read((CompoundTag)nbt);
/*     */             }
/*     */           });
/*  98 */       Objects.requireNonNull(chunkLoader); event.addListener(chunkLoader::invalidate);
/*     */       
/* 100 */       if (world.m_46472_().m_135782_().equals(WitherStormMod.bowelsLocation())) {
/*     */         
/* 102 */         final LazyOptional<WitherStormBowelsManager> bowelsManager = LazyOptional.of(() -> new WitherStormBowelsManager((ServerLevel)world));
/* 103 */         event.addCapability(new ResourceLocation("witherstormmod", "bowels_manager"), (ICapabilityProvider)new ICapabilitySerializable<Tag>()
/*     */             {
/*     */               
/*     */               public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side)
/*     */               {
/* 108 */                 return (capability == WitherStormModCapabilities.BOWELS_MANAGER) ? bowelsManager.cast() : LazyOptional.empty();
/*     */               }
/*     */ 
/*     */ 
/*     */               
/*     */               public Tag serializeNBT() {
/* 114 */                 return (Tag)((WitherStormBowelsManager)bowelsManager.orElse(null)).write();
/*     */               }
/*     */ 
/*     */ 
/*     */               
/*     */               public void deserializeNBT(Tag nbt) {
/* 120 */                 ((WitherStormBowelsManager)bowelsManager.orElse(null)).read((CompoundTag)nbt);
/*     */               }
/*     */             });
/* 123 */         Objects.requireNonNull(bowelsManager); event.addListener(bowelsManager::invalidate);
/*     */       }
/* 125 */       else if (world.m_46472_().equals(Level.f_46428_)) {
/*     */         
/* 127 */         final LazyOptional<WitherStormAutoSpawner> autoSpawner = LazyOptional.of(() -> new WitherStormAutoSpawner((ServerLevel)world));
/* 128 */         event.addCapability(new ResourceLocation("witherstormmod", "auto_spawner"), (ICapabilityProvider)new ICapabilitySerializable<CompoundTag>()
/*     */             {
/*     */               
/*     */               public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
/*     */               {
/* 133 */                 return (cap == WitherStormModCapabilities.WITHER_STORM_AUTO_SPAWNER) ? autoSpawner.cast() : LazyOptional.empty();
/*     */               }
/*     */ 
/*     */ 
/*     */               
/*     */               public CompoundTag serializeNBT() {
/* 139 */                 return ((WitherStormAutoSpawner)autoSpawner.orElse(null)).write();
/*     */               }
/*     */ 
/*     */ 
/*     */               
/*     */               public void deserializeNBT(CompoundTag nbt) {
/* 145 */                 ((WitherStormAutoSpawner)autoSpawner.orElse(null)).read(nbt);
/*     */               }
/*     */             });
/* 148 */         Objects.requireNonNull(autoSpawner); event.addListener(autoSpawner::invalidate);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
/* 155 */     Entity entity = (Entity)event.getObject();
/*     */     
/* 157 */     if (!(entity instanceof nonamecrackers2.witherstormmod.common.entity.WitherSickened) && entity instanceof LivingEntity)
/*     */     {
/* 159 */       if (((Boolean)WitherStormModConfig.SERVER.witherSicknessEnabled.get()).booleanValue()) {
/*     */         
/* 161 */         LivingEntity living = (LivingEntity)entity;
/* 162 */         LazyOptional<WitherSicknessTracker> tracker = LazyOptional.of(() -> new WitherSicknessTracker(living));
/* 163 */         event.addCapability(new ResourceLocation("witherstormmod", "wither_sickness_tracker"), (ICapabilityProvider)new EntityCapability.Serializable(WITHER_SICKNESS_TRACKER, tracker));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 168 */     if (entity instanceof Player) {
/*     */       
/* 170 */       Player player = (Player)entity;
/* 171 */       LazyOptional<PlayerWitherStormData> data = LazyOptional.of(() -> new PlayerWitherStormData(player));
/* 172 */       event.addCapability(new ResourceLocation("witherstormmod", "wither_storm_data"), (ICapabilityProvider)new EntityCapability.Serializable(PLAYER_WITHER_STORM_DATA, data));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModCapabilities.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
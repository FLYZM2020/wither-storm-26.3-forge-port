/*     */ package nonamecrackers2.witherstormmod.common.capability;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.ibm.icu.impl.locale.XCldrStub;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.TicketType;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.level.ChunkPos;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ChunkLoader;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*     */ import nonamecrackers2.witherstormmod.common.util.WitherStormModNBTUtil;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WitherStormModChunkLoader
/*     */ {
/*  31 */   private static final Logger LOGGER = LogManager.getLogger();
/*  32 */   public static final TicketType<ChunkPos> WITHER_STORM = TicketType.m_9462_("witherstormmod:wither_storm", Comparator.comparingLong(ChunkPos::m_45588_));
/*  33 */   public static final TicketType<ChunkPos> LOAD = TicketType.m_9465_("witherstormmod:load", Comparator.comparingLong(ChunkPos::m_45588_), 100);
/*     */   private final ServerLevel level;
/*  35 */   private final Map<UUID, Instance> instances = Maps.newHashMap();
/*  36 */   private List<ChunkPos> lastKnownPositions = Lists.newArrayList();
/*     */   
/*     */   private boolean loadedLastKnown;
/*     */   
/*     */   public WitherStormModChunkLoader(ServerLevel level) {
/*  41 */     this.level = level;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Instance getInstance(UUID id) {
/*  46 */     return this.instances.get(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<UUID, Instance> getInstances() {
/*  51 */     return XCldrStub.ImmutableMap.copyOf(this.instances);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasChunkLoaders() {
/*  56 */     return !this.instances.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void refreshAllLoaders() {
/*  61 */     for (Instance instance : this.instances.values()) {
/*     */       
/*  63 */       instance.unload();
/*  64 */       instance.load();
/*  65 */       LOGGER.debug("Refreshed chunk loaders");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  71 */     if (hasChunkLoaders()) {
/*  72 */       this.level.m_8886_();
/*     */     }
/*  74 */     if (!this.loadedLastKnown) {
/*     */       
/*  76 */       int count = 0;
/*  77 */       for (ChunkPos pos : this.lastKnownPositions) {
/*     */         
/*  79 */         count++;
/*  80 */         this.level.m_7726_().m_8387_(LOAD, pos, 2, pos);
/*     */       } 
/*  82 */       LOGGER.debug("Loaded {} chunks for initial loading sequence in {}", Integer.valueOf(count), this.level.m_46472_());
/*  83 */       this.loadedLastKnown = true;
/*     */     } 
/*     */     
/*  86 */     for (Iterator<Entity> iterator1 = this.level.m_8583_().iterator(); iterator1.hasNext(); ) { Entity entity = iterator1.next();
/*     */       
/*  88 */       if (entity instanceof ChunkLoader) { ChunkLoader loader = (ChunkLoader)entity; if (loader.shouldLoad()) {
/*     */           
/*  90 */           Instance instance = this.instances.computeIfAbsent(entity.m_20148_(), id -> {
/*     */                 LOGGER.debug("A new chunk loading entity has entered the world: {}", entity);
/*     */                 
/*     */                 ChunkPos pos = entity.m_146902_();
/*     */                 return new Instance(loader, pos, loader.loadRadius());
/*     */               });
/*  96 */           instance.updateCurrentPosition(entity.m_146902_());
/*     */         }  }
/*     */        }
/*     */     
/* 100 */     Iterator<Map.Entry<UUID, Instance>> iterator = this.instances.entrySet().iterator();
/* 101 */     while (iterator.hasNext()) {
/*     */       
/* 103 */       Map.Entry<UUID, Instance> entry = iterator.next();
/* 104 */       Instance instance = entry.getValue();
/* 105 */       if (instance.loader.isStillValidForChunkLoading()) {
/*     */         
/* 107 */         if (((Boolean)WitherStormModConfig.SERVER.shouldChunkLoadWhenNoPlayers.get()).booleanValue() || this.level.m_7654_().m_7416_() > 0) {
/*     */           
/* 109 */           if (instance.checkAndClearNeedsInit()) {
/* 110 */             instance.load();
/*     */           }
/*     */           continue;
/*     */         } 
/* 114 */         if (!instance.needsInit) {
/*     */           
/* 116 */           instance.unload();
/* 117 */           instance.needsInit = true;
/*     */         } 
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 123 */       instance.unload();
/* 124 */       iterator.remove();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundTag write() {
/* 131 */     CompoundTag tag = new CompoundTag();
/* 132 */     if (!this.instances.isEmpty())
/* 133 */       tag.m_128365_("LastKnownPositions", (Tag)WitherStormModNBTUtil.writeChunkPosList(this.instances.values().stream().map(Instance::getPos).toList())); 
/* 134 */     return tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void read(CompoundTag tag) {
/* 139 */     if (tag.m_128441_("LastKnownPositions")) {
/* 140 */       this.lastKnownPositions = WitherStormModNBTUtil.readChunkPosList(tag.m_128469_("LastKnownPositions"));
/*     */     }
/*     */   }
/*     */   
/*     */   public class Instance
/*     */   {
/*     */     private ChunkPos current;
/*     */     private int radius;
/*     */     private boolean needsInit = true;
/*     */     private final ChunkLoader loader;
/*     */     
/*     */     public Instance(ChunkLoader loader, ChunkPos current, int radius) {
/* 152 */       this.loader = loader;
/* 153 */       this.current = current;
/* 154 */       this.radius = radius;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getRadius() {
/* 159 */       return this.radius;
/*     */     }
/*     */ 
/*     */     
/*     */     public ChunkPos getPos() {
/* 164 */       return this.current;
/*     */     }
/*     */ 
/*     */     
/*     */     public void updateCurrentPosition(ChunkPos pos) {
/* 169 */       if (!this.current.equals(pos)) {
/*     */         
/* 171 */         unload();
/* 172 */         this.current = pos;
/* 173 */         load();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void unload() {
/* 179 */       WitherStormModChunkLoader.LOGGER.debug("Unloading chunk {}", this.current);
/* 180 */       WitherStormModChunkLoader.this.level.m_7726_().removeRegionTicket(WitherStormModChunkLoader.WITHER_STORM, this.current, this.radius, this.current, true);
/*     */     }
/*     */ 
/*     */     
/*     */     public void load() {
/* 185 */       WitherStormModChunkLoader.LOGGER.debug("Loading chunk {} with radius {}", this.current, Integer.valueOf(this.radius));
/* 186 */       WitherStormModChunkLoader.this.level.m_7726_().addRegionTicket(WitherStormModChunkLoader.WITHER_STORM, this.current, this.radius, this.current, true);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean checkAndClearNeedsInit() {
/* 191 */       boolean flag = this.needsInit;
/* 192 */       if (flag)
/* 193 */         this.needsInit = false; 
/* 194 */       return flag;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Events
/*     */   {
/*     */     @SubscribeEvent
/*     */     public static void onLevelTick(TickEvent.LevelTickEvent event) {
/* 205 */       if (event.phase == TickEvent.Phase.START)
/* 206 */         event.level.getCapability(WitherStormModCapabilities.CHUNK_LOADER).ifPresent(WitherStormModChunkLoader::tick); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\capability\WitherStormModChunkLoader.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
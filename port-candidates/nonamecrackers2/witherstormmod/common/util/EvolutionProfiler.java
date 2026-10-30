/*     */ package nonamecrackers2.witherstormmod.common.util;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.ListTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.network.chat.MutableComponent;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ 
/*     */ public class EvolutionProfiler {
/*  20 */   private final Int2ObjectMap<Integer> ticksToEvolve = (Int2ObjectMap<Integer>)new Int2ObjectOpenHashMap();
/*  21 */   private final List<Integer> consumedEntitiesPerSeconds = Lists.newArrayList();
/*     */   
/*     */   private double consumedEntitiesPerSecond;
/*     */   private int ticksSinceLastPhase;
/*     */   private int lastConsumedEntities;
/*     */   private boolean isProfiling;
/*     */   
/*     */   public void tick(WitherStormEntity storm) {
/*  29 */     this.ticksSinceLastPhase++;
/*  30 */     MinecraftServer server = storm.level().m_7654_();
/*  31 */     if (this.ticksToEvolve.containsKey(7)) {
/*     */       
/*  33 */       for (ObjectIterator<Int2ObjectMap.Entry<Integer>> objectIterator = this.ticksToEvolve.int2ObjectEntrySet().iterator(); objectIterator.hasNext(); ) { Int2ObjectMap.Entry<Integer> entry = objectIterator.next();
/*     */         
/*  35 */         int phase = entry.getIntKey();
/*  36 */         int ticks = ((Integer)entry.getValue()).intValue();
/*  37 */         MutableComponent mutableComponent = Component.literal("" + ticks + " ticks to evolve from " + ticks + " to " + phase - 1).withStyle(ChatFormatting.GOLD);
/*  38 */         for (ServerPlayer player : server.getPlayerList().getPlayers())
/*  39 */           player.sendSystemMessage((Component)mutableComponent);  }
/*     */       
/*  41 */       this.isProfiling = false;
/*     */     } 
/*  43 */     if (this.ticksSinceLastPhase % 20 == 0) {
/*     */       
/*  45 */       this.consumedEntitiesPerSeconds.add(Integer.valueOf(storm.getConsumedEntities() - this.lastConsumedEntities));
/*  46 */       this.lastConsumedEntities = storm.getConsumedEntities();
/*  47 */       int size = this.consumedEntitiesPerSeconds.size();
/*  48 */       int sum = 0; Iterator<Integer> iterator;
/*  49 */       for (iterator = this.consumedEntitiesPerSeconds.iterator(); iterator.hasNext(); ) { int i = ((Integer)iterator.next()).intValue();
/*  50 */         sum += i; }
/*  51 */        this.consumedEntitiesPerSecond = sum / size;
/*  52 */       if (this.consumedEntitiesPerSeconds.size() > 60) {
/*     */         
/*  54 */         this.consumedEntitiesPerSeconds.clear();
/*  55 */         for (ServerPlayer player : server.getPlayerList().getPlayers()) {
/*  56 */           player.sendSystemMessage((Component)Component.literal("Consumed entities per second for phase " + storm.getPhase() + ": " + this.consumedEntitiesPerSecond).withStyle(ChatFormatting.YELLOW));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onEvolve(WitherStormEntity storm) {
/*  63 */     int phase = storm.getPhase();
/*  64 */     this.ticksToEvolve.put(phase, Integer.valueOf(this.ticksSinceLastPhase));
/*  65 */     MutableComponent mutableComponent = Component.literal("Phase " + phase - 1 + " to " + phase + " took " + this.ticksSinceLastPhase + " ticks").withStyle(ChatFormatting.GOLD);
/*  66 */     for (ServerPlayer player : storm.level().m_7654_().getPlayerList().getPlayers())
/*  67 */       player.sendSystemMessage((Component)mutableComponent); 
/*  68 */     this.ticksSinceLastPhase = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void begin() {
/*  73 */     this.isProfiling = true;
/*  74 */     this.ticksToEvolve.clear();
/*  75 */     this.ticksSinceLastPhase = 0;
/*  76 */     this.consumedEntitiesPerSeconds.clear();
/*  77 */     this.consumedEntitiesPerSecond = 0.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isProfiling() {
/*  82 */     return this.isProfiling;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getConsumedEntitiesPerSecond() {
/*  87 */     return this.consumedEntitiesPerSecond;
/*     */   }
/*     */ 
/*     */   
/*     */   public void save(CompoundTag tag) {
/*  92 */     tag.putBoolean("IsProfiling", this.isProfiling);
/*  93 */     tag.putInt("TicksSinceLastPhase", this.ticksSinceLastPhase);
/*  94 */     ListTag list = new ListTag();
/*  95 */     for (ObjectIterator<Int2ObjectMap.Entry<Integer>> objectIterator = this.ticksToEvolve.int2ObjectEntrySet().iterator(); objectIterator.hasNext(); ) { Int2ObjectMap.Entry<Integer> entry = objectIterator.next();
/*     */       
/*  97 */       CompoundTag entryTag = new CompoundTag();
/*  98 */       entryTag.putInt("Phase", entry.getIntKey());
/*  99 */       entryTag.putInt("Ticks", ((Integer)entry.getValue()).intValue());
/* 100 */       list.add(entryTag); }
/*     */     
/* 102 */     tag.put("TicksToEvolve", (Tag)list);
/*     */   }
/*     */ 
/*     */   
/*     */   public void read(CompoundTag tag) {
/* 107 */     this.isProfiling = tag.getBoolean("IsProfiling");
/* 108 */     this.ticksSinceLastPhase = tag.getInt("TicksSinceLastPhase");
/* 109 */     ListTag list = tag.getList("TicksToEvolve", 10);
/* 110 */     this.ticksToEvolve.clear();
/* 111 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/* 113 */       CompoundTag entryTag = list.getCompound(i);
/* 114 */       this.ticksToEvolve.put(entryTag.getInt("Phase"), Integer.valueOf(entryTag.getInt("Ticks")));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\EvolutionProfiler.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
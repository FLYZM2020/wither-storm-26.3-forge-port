/*     */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.ListTag;
/*     */ import net.minecraft.nbt.NbtUtils;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
/*     */ 
/*     */ public class PersistentTrackedEntities
/*     */ {
/*     */   private static final int TICKS_TILL_REMOVE_SAVED = 80;
/*  19 */   private List<Entity> currentTrackedEntities = Lists.newArrayList();
/*  20 */   private List<UUID> savedTrackedEntities = Lists.newArrayList();
/*     */   
/*     */   private int tickCount;
/*     */   private boolean hasLoadedSaved;
/*     */   
/*     */   public void tick(ServerLevel level) {
/*  26 */     this.tickCount++;
/*     */     
/*  28 */     Iterator<UUID> iterator = this.savedTrackedEntities.iterator();
/*  29 */     while (iterator.hasNext()) {
/*     */       
/*  31 */       UUID id = iterator.next();
/*  32 */       Entity entity = level.m_8791_(id);
/*  33 */       if (entity != null && !entity.m_213877_()) {
/*     */         
/*  35 */         this.currentTrackedEntities.add(entity);
/*  36 */         iterator.remove(); continue;
/*     */       } 
/*  38 */       if (this.tickCount > 80)
/*     */       {
/*  40 */         iterator.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Entity> getCurrentTrackedEntities() {
/*  47 */     return (List<Entity>)ImmutableList.copyOf(this.currentTrackedEntities);
/*     */   }
/*     */ 
/*     */   
/*     */   public void trackEntityToConsume(Entity entity) {
/*  52 */     this.currentTrackedEntities.add(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public void stopTrackingEntity(Entity entity) {
/*  57 */     this.currentTrackedEntities.remove(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(Entity entity) {
/*  62 */     return this.currentTrackedEntities.contains(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearAll() {
/*  67 */     this.currentTrackedEntities.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearAndMakeAllFall() {
/*  72 */     int size = this.currentTrackedEntities.size();
/*  73 */     for (int i = 0; i < size; i++) {
/*     */       
/*  75 */       Entity tracked = this.currentTrackedEntities.get(i);
/*  76 */       tracked.m_20242_(false);
/*  77 */       if (tracked instanceof BlockClusterEntity)
/*  78 */         ((BlockClusterEntity)tracked).setPhysics(true); 
/*     */     } 
/*  80 */     this.currentTrackedEntities.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void destroyAllClusters() {
/*  85 */     Iterator<Entity> iterator = this.currentTrackedEntities.iterator();
/*  86 */     while (iterator.hasNext()) {
/*     */       
/*  88 */       Entity tracked = iterator.next();
/*  89 */       if (tracked instanceof BlockClusterEntity) {
/*     */         
/*  91 */         iterator.remove();
/*  92 */         tracked.m_146870_();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundTag save() {
/*  99 */     CompoundTag tag = new CompoundTag();
/* 100 */     ListTag list = new ListTag();
/* 101 */     List<UUID> toSave = Lists.newArrayList(this.savedTrackedEntities);
/* 102 */     toSave.addAll(this.currentTrackedEntities.stream().map(Entity::m_20148_).toList());
/* 103 */     for (UUID id : toSave)
/* 104 */       list.add(NbtUtils.m_129226_(id)); 
/* 105 */     tag.m_128365_("Entities", (Tag)list);
/* 106 */     return tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void read(CompoundTag tag) {
/* 111 */     if (!this.hasLoadedSaved) {
/*     */       
/* 113 */       this.savedTrackedEntities.clear();
/* 114 */       ListTag list = tag.m_128437_("Entities", 11);
/* 115 */       for (int i = 0; i < list.size(); i++) {
/*     */         
/* 117 */         UUID id = NbtUtils.m_129233_(list.get(i));
/* 118 */         this.savedTrackedEntities.add(id);
/*     */       } 
/* 120 */       this.hasLoadedSaved = true;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\PersistentTrackedEntities.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.ListTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import nonamecrackers2.witherstormmod.common.blockentity.FormidibombBlockEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.item.FormidibombItem;
/*     */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*     */ 
/*     */ public class IgnoredTargetsManager
/*     */ {
/*     */   public static final int DEFAULT_TIME = 80;
/*     */   private final WitherStormEntity storm;
/*  26 */   private final List<Ignored> entities = Lists.newArrayList();
/*  27 */   private final List<AABB> restrictedTargetingRegions = Lists.newArrayList();
/*     */ 
/*     */   
/*     */   public IgnoredTargetsManager(WitherStormEntity storm) {
/*  31 */     this.storm = storm;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getTargetRestrictionSize(Entity entity) {
/*  36 */     if (entity instanceof FormidibombEntity) { FormidibombEntity formidibomb = (FormidibombEntity)entity;
/*     */       
/*  38 */       if (formidibomb.m_6084_() && formidibomb.getStartFuse() > 0) {
/*  39 */         return 20.0D;
/*     */       } }
/*  41 */     else if (entity instanceof Player) { Player player = (Player)entity;
/*     */       
/*  43 */       for (ItemStack stack : (player.m_150109_()).f_35974_) {
/*     */         
/*  45 */         Item item = stack.m_41720_(); if (item instanceof FormidibombItem) { FormidibombItem formidibombItem = (FormidibombItem)item; if (formidibombItem.getStartFuse(stack) > 0)
/*  46 */             return 20.0D;  }
/*     */       
/*     */       }  }
/*  49 */     else if (entity instanceof nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity)
/*     */     
/*  51 */     { return 50.0D; }
/*     */     
/*  53 */     return -1.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  58 */     Iterator<Ignored> iterator = this.entities.iterator();
/*  59 */     while (iterator.hasNext()) {
/*     */       
/*  61 */       Ignored ignored = iterator.next();
/*  62 */       if (ignored.ticks > 0) {
/*     */         
/*  64 */         ignored.ticks--;
/*  65 */         if (ignored.ticks == 0) {
/*  66 */           iterator.remove();
/*     */         }
/*     */       } 
/*     */     } 
/*  70 */     this.restrictedTargetingRegions.clear();
/*  71 */     AABB searchBox = this.storm.getSearchBox();
/*  72 */     for (BlockEntity tile : WorldUtil.getBlockEntitiesInAABB(this.storm.m_9236_(), searchBox)) {
/*     */       
/*  74 */       if (tile instanceof FormidibombBlockEntity) { FormidibombBlockEntity formidibomb = (FormidibombBlockEntity)tile; if (formidibomb.getStartFuse() > 0)
/*  75 */           this.restrictedTargetingRegions.add((new AABB(formidibomb.m_58899_())).m_82400_(20.0D));  }
/*     */     
/*  77 */     }  for (Entity entity : this.storm.m_9236_().m_45976_(Entity.class, searchBox)) {
/*     */       
/*  79 */       double size = getTargetRestrictionSize(entity);
/*  80 */       if (size != -1.0D) {
/*  81 */         this.restrictedTargetingRegions.add((new AABB(entity.m_20183_())).m_82400_(size));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addEntityToIgnore(Entity entity) {
/*  87 */     addEntityToIgnore(entity, 80);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEntityToIgnore(Entity entity, int time) {
/*  92 */     if (!shouldIgnoreEntity(entity)) {
/*  93 */       this.entities.add(new Ignored(entity.m_20148_(), time));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean shouldIgnoreEntity(Entity entity) {
/*  98 */     return (this.entities.stream().anyMatch(e -> e.entity.equals(entity.m_20148_())) || this.restrictedTargetingRegions.stream().anyMatch(aabb -> aabb.m_82390_(entity.m_146892_())));
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
/*     */   public CompoundTag save() {
/* 113 */     CompoundTag tag = new CompoundTag();
/* 114 */     ListTag list = new ListTag();
/* 115 */     for (Ignored ignored : this.entities) {
/*     */       
/* 117 */       CompoundTag entry = new CompoundTag();
/* 118 */       entry.m_128362_("UUID", ignored.entity);
/* 119 */       entry.m_128405_("Ticks", ignored.ticks);
/* 120 */       list.add(entry);
/*     */     } 
/* 122 */     tag.m_128365_("Entities", (Tag)list);
/* 123 */     return tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void read(CompoundTag tag) {
/* 128 */     this.entities.clear();
/* 129 */     ListTag list = tag.m_128437_("Entities", 10);
/* 130 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/* 132 */       CompoundTag entry = list.m_128728_(i);
/* 133 */       UUID id = entry.m_128342_("UUID");
/* 134 */       int ticks = entry.m_128451_("Ticks");
/* 135 */       this.entities.add(new Ignored(id, ticks));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private class Ignored
/*     */   {
/*     */     private final UUID entity;
/*     */     private int ticks;
/*     */     
/*     */     public Ignored(UUID entity, int time) {
/* 146 */       this.entity = entity;
/* 147 */       this.ticks = time;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\IgnoredTargetsManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
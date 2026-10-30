/*     */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Position;
/*     */ import net.minecraft.server.level.FullChunkStatus;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.level.ChunkPos;
/*     */ import net.minecraft.world.level.chunk.ChunkAccess;
/*     */ import net.minecraft.world.level.chunk.ChunkStatus;
/*     */ import net.minecraft.world.level.chunk.LevelChunk;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SegmentsManager
/*     */ {
/*     */   protected final WitherStormEntity owner;
/*     */   protected final WitherStormSegmentEntity[] segments;
/*     */   
/*     */   public SegmentsManager(WitherStormEntity owner) {
/*  27 */     this.owner = owner;
/*  28 */     this.segments = new WitherStormSegmentEntity[2];
/*     */   }
/*     */ 
/*     */   
/*     */   public void createSegments() {
/*  33 */     for (int i = 0; i < this.segments.length; i++) {
/*  34 */       createSegment(i);
/*     */     }
/*     */   }
/*     */   
/*     */   public void createSegment(int index) {
/*  39 */     if (this.owner.m_6084_()) {
/*     */       
/*  41 */       WitherStormSegmentEntity existing = this.segments[index];
/*  42 */       if (existing == null || (existing.m_146911_() != null && existing.m_146911_().m_146965_())) {
/*     */         
/*  44 */         BlockPos lastKnown = BlockPos.m_274561_(this.owner.getDesiredSegmentX(index + 1), this.owner.getDesiredSegmentY(index + 1), this.owner.getDesiredSegmentZ(index + 1));
/*  45 */         if (posLoaded(lastKnown)) {
/*     */           
/*  47 */           WitherStormSegmentEntity segment = new WitherStormSegmentEntity(this.owner);
/*  48 */           segment.m_6863_(this.owner.m_6162_());
/*  49 */           segment.m_21557_(this.owner.m_21525_());
/*  50 */           if (this.owner.m_21532_())
/*  51 */             segment.m_21530_(); 
/*  52 */           segment.m_20331_(this.owner.m_20147_());
/*  53 */           segment.m_20225_(this.owner.m_20067_());
/*  54 */           segment.m_20242_(this.owner.m_20068_());
/*  55 */           segment.setOtherHeadsDisabled(this.owner.areOtherHeadsDisabled());
/*  56 */           segment.setMirrored((index % 2 != 0));
/*  57 */           segment.setPhase(this.owner.getPhase());
/*  58 */           segment.m_5618_(this.owner.f_20883_);
/*  59 */           setSegment(segment, index);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeSegments() {
/*  67 */     for (int i = 0; i < this.segments.length; i++) {
/*     */       
/*  69 */       WitherStormSegmentEntity segment = this.segments[i];
/*  70 */       if (segment != null && !segment.m_213877_()) {
/*     */         
/*  72 */         this.segments[i].getTrackedEntities().clearAndMakeAllFall();
/*  73 */         this.segments[i].m_146870_();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void readdSegments() {
/*  80 */     for (int i = 0; i < this.segments.length; i++) {
/*     */       
/*  82 */       WitherStormSegmentEntity segment = this.segments[i];
/*  83 */       if (segment != null && !segment.isAddedToWorld()) {
/*     */         
/*  85 */         if (segment.m_146911_() != null) {
/*     */           
/*  87 */           Vec3 desiredPos = new Vec3(this.owner.getDesiredSegmentX((segment.isMirrored() == true) ? 1 : 2), this.owner.getDesiredSegmentY((segment.isMirrored() == true) ? 1 : 2), this.owner.getDesiredSegmentZ((segment.isMirrored() == true) ? 1 : 2));
/*  88 */           if (posLoaded(desiredPos))
/*     */           {
/*  90 */             segment.revive();
/*  91 */             segment.regatherCapabilities();
/*  92 */             segment.m_146884_(desiredPos);
/*  93 */             segment.m_6863_(this.owner.m_6162_());
/*  94 */             segment.m_21557_(this.owner.m_21525_());
/*  95 */             if (this.owner.m_21532_())
/*  96 */               segment.m_21530_(); 
/*  97 */             segment.m_20331_(this.owner.m_20147_());
/*  98 */             segment.m_20225_(this.owner.m_20067_());
/*  99 */             segment.m_20242_(this.owner.m_20068_());
/* 100 */             segment.setOtherHeadsDisabled(this.owner.areOtherHeadsDisabled());
/* 101 */             segment.setMirrored((i % 2 != 0));
/* 102 */             segment.setPhase(this.owner.getPhase());
/* 103 */             addSegment(i);
/*     */           }
/*     */         
/*     */         } 
/* 107 */       } else if (segment == null || !segment.m_6084_()) {
/*     */         
/* 109 */         createSegment(i);
/* 110 */         addSegment(i);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addSegments() {
/* 117 */     for (int i = 0; i < this.segments.length; i++) {
/* 118 */       addSegment(i);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addSegment(int index) {
/* 123 */     if (this.owner.isAddedToWorld() && this.owner.m_6084_()) {
/*     */       
/* 125 */       WitherStormSegmentEntity segment = this.segments[index];
/* 126 */       if (segment != null && !segment.isAddedToWorld() && ((segment.m_146911_() != null && !segment.m_146911_().m_146965_()) || segment.m_146911_() == null)) {
/*     */         
/* 128 */         Vec3 desiredPos = new Vec3(this.owner.getDesiredSegmentX((segment.isMirrored() == true) ? 1 : 2), this.owner.getDesiredSegmentY((segment.isMirrored() == true) ? 1 : 2), this.owner.getDesiredSegmentZ((segment.isMirrored() == true) ? 1 : 2));
/* 129 */         if (posLoaded(desiredPos)) {
/*     */           
/* 131 */           segment.m_146884_(desiredPos);
/* 132 */           segment.m_5618_(this.owner.f_20883_);
/* 133 */           this.owner.m_9236_().m_7967_((Entity)segment);
/* 134 */           segment.setPhase(this.owner.getPhase());
/* 135 */           segment.getPlayDeadManager().setStateRaw(this.owner.getPlayDeadManager().getState());
/* 136 */           segment.getPlayDeadManager().setTickAmount(this.owner.getPlayDeadManager().getTicks());
/* 137 */           for (WitherStormHead head : segment.getHeadManager().getHeads()) {
/*     */             
/* 139 */             if (segment.isPlayingDead()) {
/* 140 */               head.setRoar(true); continue;
/*     */             } 
/* 142 */             head.doRoar(false);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void killSegments() {
/* 151 */     for (int i = 0; i < this.segments.length; i++) {
/*     */       
/* 153 */       WitherStormSegmentEntity segment = this.segments[i];
/* 154 */       if (segment != null && segment.m_6084_()) {
/* 155 */         segment.m_6469_(segment.m_269291_().m_269341_(), Float.MAX_VALUE);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public WitherStormSegmentEntity[] getSegments() {
/* 161 */     return this.segments;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSegment(@Nullable WitherStormSegmentEntity entity, int index) {
/* 166 */     this.segments[index] = entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void findSegments(ServerLevel world) {
/* 171 */     for (int i = 0; i < this.segments.length; i++) {
/*     */       
/* 173 */       WitherStormSegmentEntity old = this.segments[i];
/* 174 */       if (old != null && old.m_146911_() != null && !old.m_146911_().m_146965_())
/*     */       {
/* 176 */         for (Entity entity : world.m_8583_()) {
/*     */           
/* 178 */           if (old.m_20148_().equals(entity.m_20148_()) && entity instanceof WitherStormSegmentEntity) { WitherStormSegmentEntity segment = (WitherStormSegmentEntity)entity;
/*     */             
/* 180 */             setSegment(segment, i);
/*     */             break; }
/*     */         
/*     */         } 
/*     */       }
/*     */     } 
/* 186 */     for (Entity entity : world.m_8583_()) {
/*     */       
/* 188 */       if (entity instanceof WitherStormSegmentEntity) { WitherStormSegmentEntity segment = (WitherStormSegmentEntity)entity;
/*     */         
/* 190 */         UUID parent = segment.getParentUUID();
/* 191 */         if (this.owner.m_20148_().equals(parent)) {
/*     */           
/* 193 */           int index = segment.isMirrored() ? 1 : 0;
/* 194 */           WitherStormSegmentEntity existing = this.segments[index];
/* 195 */           if (existing != null) {
/*     */             
/* 197 */             if (!segment.m_20148_().equals(existing.m_20148_())) {
/*     */               
/* 199 */               if (segment.getTimeWithParent() > existing.getTimeWithParent()) {
/*     */                 
/* 201 */                 existing.m_146870_();
/* 202 */                 setSegment(segment, index); continue;
/*     */               } 
/* 204 */               if (existing.getTimeWithParent() > segment.getTimeWithParent()) {
/*     */                 
/* 206 */                 segment.m_146870_();
/* 207 */                 setSegment(existing, index);
/*     */               } 
/*     */             } 
/*     */             
/*     */             continue;
/*     */           } 
/* 213 */           setSegment(segment, index);
/*     */         }  }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean posLoaded(Vec3 pos) {
/* 222 */     return posLoaded(BlockPos.m_274446_((Position)pos));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean posLoaded(BlockPos pos) {
/* 227 */     ChunkPos chunkPos = new ChunkPos(pos);
/* 228 */     ChunkAccess chunk = this.owner.m_9236_().m_6522_(chunkPos.f_45578_, chunkPos.f_45579_, ChunkStatus.f_62326_, false);
/* 229 */     if (!(chunk instanceof LevelChunk))
/* 230 */       return false; 
/* 231 */     FullChunkStatus type = ((LevelChunk)chunk).m_287138_();
/* 232 */     return type.m_287205_(FullChunkStatus.ENTITY_TICKING);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\SegmentsManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
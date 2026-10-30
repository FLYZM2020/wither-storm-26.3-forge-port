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
/*     */ import net.minecraft.world.level.chunk.status.ChunkStatus;
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
/*  39 */     if (this.owner.isAlive()) {
/*     */       
/*  41 */       WitherStormSegmentEntity existing = this.segments[index];
/*  42 */       if (existing == null || (existing.getRemovalReason() != null && existing.getRemovalReason().shouldDestroy())) {
/*     */         
/*  44 */         BlockPos lastKnown = BlockPos.containing(this.owner.getDesiredSegmentX(index + 1), this.owner.getDesiredSegmentY(index + 1), this.owner.getDesiredSegmentZ(index + 1));
/*  45 */         if (posLoaded(lastKnown)) {
/*     */           
/*  47 */           WitherStormSegmentEntity segment = new WitherStormSegmentEntity(this.owner);
/*  48 */           segment.setBaby(this.owner.isBaby());
/*  49 */           segment.setNoAi(this.owner.isNoAi());
/*  50 */           if (this.owner.isPersistenceRequired())
/*  51 */             segment.setPersistenceRequired(); 
/*  52 */           segment.setInvulnerable(this.owner.isInvulnerable());
/*  53 */           segment.setSilent(this.owner.isSilent());
/*  54 */           segment.setNoGravity(this.owner.isNoGravity());
/*  55 */           segment.setOtherHeadsDisabled(this.owner.areOtherHeadsDisabled());
/*  56 */           segment.setMirrored((index % 2 != 0));
/*  57 */           segment.setPhase(this.owner.getPhase());
/*  58 */           segment.setYBodyRot(this.owner.yBodyRot);
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
/*  70 */       if (segment != null && !segment.isRemoved()) {
/*     */         
/*  72 */         this.segments[i].getTrackedEntities().clearAndMakeAllFall();
/*  73 */         this.segments[i].discard();
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
/*  85 */         if (segment.getRemovalReason() != null) {
/*     */           
/*  87 */           Vec3 desiredPos = new Vec3(this.owner.getDesiredSegmentX((segment.isMirrored() == true) ? 1 : 2), this.owner.getDesiredSegmentY((segment.isMirrored() == true) ? 1 : 2), this.owner.getDesiredSegmentZ((segment.isMirrored() == true) ? 1 : 2));
/*  88 */           if (posLoaded(desiredPos))
/*     */           {
/*  90 */             segment.revive();
/*  91 */             segment.regatherCapabilities();
/*  92 */             segment.setPos(desiredPos);
/*  93 */             segment.setBaby(this.owner.isBaby());
/*  94 */             segment.setNoAi(this.owner.isNoAi());
/*  95 */             if (this.owner.isPersistenceRequired())
/*  96 */               segment.setPersistenceRequired(); 
/*  97 */             segment.setInvulnerable(this.owner.isInvulnerable());
/*  98 */             segment.setSilent(this.owner.isSilent());
/*  99 */             segment.setNoGravity(this.owner.isNoGravity());
/* 100 */             segment.setOtherHeadsDisabled(this.owner.areOtherHeadsDisabled());
/* 101 */             segment.setMirrored((i % 2 != 0));
/* 102 */             segment.setPhase(this.owner.getPhase());
/* 103 */             addSegment(i);
/*     */           }
/*     */         
/*     */         } 
/* 107 */       } else if (segment == null || !segment.isAlive()) {
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
/* 123 */     if (this.owner.isAddedToWorld() && this.owner.isAlive()) {
/*     */       
/* 125 */       WitherStormSegmentEntity segment = this.segments[index];
/* 126 */       if (segment != null && !segment.isAddedToWorld() && ((segment.getRemovalReason() != null && !segment.getRemovalReason().shouldDestroy()) || segment.getRemovalReason() == null)) {
/*     */         
/* 128 */         Vec3 desiredPos = new Vec3(this.owner.getDesiredSegmentX((segment.isMirrored() == true) ? 1 : 2), this.owner.getDesiredSegmentY((segment.isMirrored() == true) ? 1 : 2), this.owner.getDesiredSegmentZ((segment.isMirrored() == true) ? 1 : 2));
/* 129 */         if (posLoaded(desiredPos)) {
/*     */           
/* 131 */           segment.setPos(desiredPos);
/* 132 */           segment.setYBodyRot(this.owner.yBodyRot);
/* 133 */           this.owner.level().addFreshEntity((Entity)segment);
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
/* 154 */       if (segment != null && segment.isAlive()) {
/* 155 */         segment.hurt(segment.damageSources().fellOutOfWorld(), Float.MAX_VALUE);
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
/* 174 */       if (old != null && old.getRemovalReason() != null && !old.getRemovalReason().shouldDestroy())
/*     */       {
/* 176 */         for (Entity entity : world.getAllEntities()) {
/*     */           
/* 178 */           if (old.getUUID().equals(entity.getUUID()) && entity instanceof WitherStormSegmentEntity) { WitherStormSegmentEntity segment = (WitherStormSegmentEntity)entity;
/*     */             
/* 180 */             setSegment(segment, i);
/*     */             break; }
/*     */         
/*     */         } 
/*     */       }
/*     */     } 
/* 186 */     for (Entity entity : world.getAllEntities()) {
/*     */       
/* 188 */       if (entity instanceof WitherStormSegmentEntity) { WitherStormSegmentEntity segment = (WitherStormSegmentEntity)entity;
/*     */         
/* 190 */         UUID parent = segment.getParentUUID();
/* 191 */         if (this.owner.getUUID().equals(parent)) {
/*     */           
/* 193 */           int index = segment.isMirrored() ? 1 : 0;
/* 194 */           WitherStormSegmentEntity existing = this.segments[index];
/* 195 */           if (existing != null) {
/*     */             
/* 197 */             if (!segment.getUUID().equals(existing.getUUID())) {
/*     */               
/* 199 */               if (segment.getTimeWithParent() > existing.getTimeWithParent()) {
/*     */                 
/* 201 */                 existing.discard();
/* 202 */                 setSegment(segment, index); continue;
/*     */               } 
/* 204 */               if (existing.getTimeWithParent() > segment.getTimeWithParent()) {
/*     */                 
/* 206 */                 segment.discard();
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
/* 222 */     return posLoaded(BlockPos.containing((Position)pos));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean posLoaded(BlockPos pos) {
/* 227 */     ChunkPos chunkPos = new ChunkPos(pos);
/* 228 */     ChunkAccess chunk = this.owner.level().getChunk(chunkPos.x, chunkPos.z, ChunkStatus.FULL, false);
/* 229 */     if (!(chunk instanceof LevelChunk))
/* 230 */       return false; 
/* 231 */     FullChunkStatus type = ((LevelChunk)chunk).getFullStatus();
/* 232 */     return type.isOrAfter(FullChunkStatus.ENTITY_TICKING);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\SegmentsManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
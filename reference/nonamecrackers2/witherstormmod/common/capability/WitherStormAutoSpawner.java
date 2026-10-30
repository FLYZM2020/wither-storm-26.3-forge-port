/*    */ package nonamecrackers2.witherstormmod.common.capability;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import net.minecraft.advancements.CriteriaTriggers;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.core.Vec3i;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.server.level.TicketType;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.EntitySelector;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.level.ChunkPos;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.levelgen.structure.StructurePiece;
/*    */ import net.minecraft.world.level.levelgen.structure.StructureStart;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*    */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModStructureTags;
/*    */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*    */ import nonamecrackers2.witherstormmod.common.world.gen.feature.structure.StormSpawnPlatformStructure;
/*    */ 
/*    */ public class WitherStormAutoSpawner {
/* 26 */   private static final TicketType<BlockPos> INITIAL_SPAWN = TicketType.m_9465_("initial_spawn", Vec3i::compareTo, 100);
/*    */   
/*    */   private final ServerLevel level;
/*    */   private int tickCount;
/*    */   private boolean hasSpawnedWitherStorm;
/*    */   
/*    */   public WitherStormAutoSpawner(ServerLevel level) {
/* 33 */     this.level = level;
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick() {
/* 38 */     if (((Boolean)WitherStormModConfig.COMMON.autoSpawnWitherStorm.get()).booleanValue()) {
/*    */       
/* 40 */       this.tickCount++;
/*    */       
/* 42 */       int maxTime = Math.max(((Integer)WitherStormModConfig.COMMON.autoSpawnTime.get()).intValue() * 60 * 20, 100);
/* 43 */       if (!this.hasSpawnedWitherStorm && this.tickCount > maxTime) {
/*    */         
/* 45 */         this.hasSpawnedWitherStorm = true;
/*    */         
/* 47 */         Pair<BlockPos, StructureStart> structure = WorldUtil.findNearestMapStructure(this.level, WitherStormModStructureTags.STORM_SPAWN_PLATFORMS, BlockPos.f_121853_, 100, false);
/* 48 */         if (structure != null)
/*    */         {
/* 50 */           BlockPos pos = (BlockPos)structure.getFirst();
/* 51 */           this.level.m_46865_(pos);
/* 52 */           StructureStart start = (StructureStart)structure.getSecond();
/* 53 */           for (StructurePiece piece : start.m_73602_()) {
/*    */             
/* 55 */             if (piece instanceof StormSpawnPlatformStructure.Piece) { StormSpawnPlatformStructure.Piece spawnPiece = (StormSpawnPlatformStructure.Piece)piece;
/*    */               
/* 57 */               if (spawnPiece.getSpawnPos() != null) {
/*    */                 
/* 59 */                 pos = spawnPiece.getSpawnPos();
/*    */                 break;
/*    */               }  }
/*    */           
/*    */           } 
/* 64 */           this.level.m_7726_().addRegionTicket(INITIAL_SPAWN, new ChunkPos(pos), 2, pos, true);
/* 65 */           WitherStormEntity storm = (WitherStormEntity)((EntityType)WitherStormModEntityTypes.WITHER_STORM.get()).m_20615_((Level)this.level);
/* 66 */           storm.m_6027_(pos.m_123341_() + 0.5D, pos.m_123342_(), pos.m_123343_() + 0.5D);
/* 67 */           storm.makeInvulnerable();
/* 68 */           storm.m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_ACTIVATES.get(), 4.0F, 1.0F);
/* 69 */           for (ServerPlayer player : this.level.m_8795_(EntitySelector.f_20408_))
/* 70 */             CriteriaTriggers.f_10580_.m_68256_(player, (Entity)storm); 
/* 71 */           this.level.m_7967_((Entity)storm);
/*    */         }
/*    */       
/*    */       } 
/*    */     } else {
/*    */       
/* 77 */       this.hasSpawnedWitherStorm = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void read(CompoundTag tag) {
/* 83 */     this.tickCount = tag.m_128451_("TickCount");
/* 84 */     this.hasSpawnedWitherStorm = tag.m_128471_("HasSpawnedWitherStorm");
/*    */   }
/*    */ 
/*    */   
/*    */   public CompoundTag write() {
/* 89 */     CompoundTag tag = new CompoundTag();
/* 90 */     tag.m_128405_("TickCount", this.tickCount);
/* 91 */     tag.m_128379_("HasSpawnedWitherStorm", this.hasSpawnedWitherStorm);
/* 92 */     return tag;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\capability\WitherStormAutoSpawner.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
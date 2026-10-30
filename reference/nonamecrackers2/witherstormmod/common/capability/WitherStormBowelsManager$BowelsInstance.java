/*     */ package nonamecrackers2.witherstormmod.common.capability;
/*     */ 
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.Comparator;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.NbtUtils;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.TicketType;
/*     */ import net.minecraft.world.level.ChunkPos;
/*     */ import net.minecraft.world.level.levelgen.structure.StructurePiece;
/*     */ import net.minecraft.world.level.levelgen.structure.StructureStart;
/*     */ import nonamecrackers2.witherstormmod.WitherStormMod;
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
/*     */ public class BowelsInstance
/*     */ {
/* 689 */   private static final TicketType<ChunkPos> BOWELS = TicketType.m_9462_("witherstormmod:bowels", Comparator.comparingLong(ChunkPos::m_45588_));
/*     */   
/*     */   @Nullable
/*     */   private Pair<BlockPos, StructureStart> start;
/*     */   
/*     */   private final UUID witherStorm;
/*     */ 
/*     */   
/*     */   public BowelsInstance(Pair<BlockPos, StructureStart> start, UUID storm) {
/* 698 */     this.start = start;
/* 699 */     this.witherStorm = storm;
/*     */   }
/*     */   @Nullable
/*     */   private UUID commandBlock; private boolean hasPreparedArena; private boolean isCompleted;
/*     */   public BowelsInstance(BlockPos pos, UUID storm) {
/* 704 */     this.start = Pair.of(pos, null);
/* 705 */     this.witherStorm = storm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setup(ServerLevel world) {
/* 712 */     if (!world.m_46472_().m_135782_().equals(WitherStormMod.bowelsLocation())) {
/*     */       
/* 714 */       WitherStormBowelsManager.LOGGER.error("Cannot setup {} in {}", this, world.m_46472_());
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 719 */     if (getStart() == null) {
/*     */       
/* 721 */       StructureStart start = WitherStormBowelsManager.getAvailableStartAt(world, getPos());
/* 722 */       if (start != null) {
/*     */         
/* 724 */         this.start = Pair.of(getPos(), start);
/*     */       }
/*     */       else {
/*     */         
/* 728 */         WitherStormBowelsManager.LOGGER.error("Could not find saved structure start or start is not valid from {}. It is important that these values aren't modified!", getPos());
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 733 */     WitherStormBowelsManager.LOGGER.debug("Successfully setup this bowels instance {}", this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doChunkLoading(ServerLevel level) {
/* 739 */     if (!isCompleted()) {
/* 740 */       forceChunks(level, getCenter(), true);
/*     */     } else {
/* 742 */       forceChunks(level, getCenter(), false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void forceChunks(ServerLevel world, BlockPos pos, boolean force) {
/* 747 */     ChunkPos chunkPos = new ChunkPos(pos);
/* 748 */     if (force) {
/*     */       
/* 750 */       WitherStormBowelsManager.LOGGER.debug("Loaded chunks in arena");
/* 751 */       world.m_7726_().addRegionTicket(BOWELS, chunkPos, 3, chunkPos, true);
/*     */     }
/*     */     else {
/*     */       
/* 755 */       WitherStormBowelsManager.LOGGER.debug("Unloaded chunks in arena");
/* 756 */       world.m_7726_().m_8438_(BOWELS, chunkPos, 3, chunkPos);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundTag write() {
/* 762 */     CompoundTag compound = new CompoundTag();
/* 763 */     compound.m_128365_("Pos", (Tag)NbtUtils.m_129224_(getPos()));
/* 764 */     compound.m_128362_("Storm", this.witherStorm);
/* 765 */     if (this.commandBlock != null)
/* 766 */       compound.m_128362_("CommandBlock", this.commandBlock); 
/* 767 */     compound.m_128379_("HasPreparedArena", this.hasPreparedArena);
/* 768 */     compound.m_128379_("Completed", this.isCompleted);
/* 769 */     return compound;
/*     */   }
/*     */ 
/*     */   
/*     */   public static BowelsInstance read(CompoundTag compound) {
/* 774 */     BlockPos pos = NbtUtils.m_129239_(compound.m_128469_("Pos"));
/* 775 */     UUID storm = compound.m_128342_("Storm");
/* 776 */     UUID commandBlock = null;
/* 777 */     if (compound.m_128441_("CommandBlock"))
/* 778 */       commandBlock = compound.m_128342_("CommandBlock"); 
/* 779 */     BowelsInstance instance = new BowelsInstance(pos, storm);
/* 780 */     instance.commandBlock = commandBlock;
/* 781 */     instance.hasPreparedArena = compound.m_128471_("HasPreparedArena");
/* 782 */     instance.isCompleted = compound.m_128471_("Completed");
/* 783 */     return instance;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCompleted(boolean completed) {
/* 788 */     this.isCompleted = completed;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCompleted() {
/* 793 */     return this.isCompleted;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPreparedArena() {
/* 798 */     this.hasPreparedArena = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPreparedArena() {
/* 803 */     return this.hasPreparedArena;
/*     */   }
/*     */ 
/*     */   
/*     */   public UUID getCommandBlockUUID() {
/* 808 */     return this.commandBlock;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos getPos() {
/* 813 */     return (BlockPos)this.start.getFirst();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BlockPos getCenter() {
/* 818 */     StructureStart start = getStart();
/* 819 */     if (start != null) {
/*     */       
/* 821 */       BlockPos center = ((StructurePiece)start.m_73602_().get(0)).m_73547_().m_162394_();
/* 822 */       return new BlockPos(center.m_123341_(), 0, center.m_123343_());
/*     */     } 
/*     */ 
/*     */     
/* 826 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public StructureStart getStart() {
/* 832 */     return (StructureStart)this.start.getSecond();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 838 */     return "BowelsInstance[start = " + this.start + ", pos = " + 
/* 839 */       getPos() + ", storm = " + this.witherStorm + ", commandBlock = " + this.commandBlock + ", hasPreparedArena = " + this.hasPreparedArena + ", completed = " + this.isCompleted + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\capability\WitherStormBowelsManager$BowelsInstance.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
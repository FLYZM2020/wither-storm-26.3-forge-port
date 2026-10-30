/*    */ package nonamecrackers2.witherstormmod.common.capability;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.nbt.ListTag;
/*    */ import net.minecraft.nbt.NbtUtils;
/*    */ import net.minecraft.nbt.Tag;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.world.level.ChunkPos;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ 
/*    */ public class ChunkLoadingBlockEntities
/*    */ {
/*    */   private final ServerLevel level;
/* 18 */   private List<BlockPos> loadingEntities = Lists.newArrayList();
/*    */ 
/*    */   
/*    */   public ChunkLoadingBlockEntities(ServerLevel level) {
/* 22 */     this.level = level;
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick() {
/* 27 */     Iterator<BlockPos> loadingEntities = this.loadingEntities.iterator();
/* 28 */     while (loadingEntities.hasNext()) {
/*    */       
/* 30 */       BlockPos pos = loadingEntities.next();
/* 31 */       if (this.level.m_46749_(pos)) {
/*    */         
/* 33 */         BlockEntity entity = this.level.m_7702_(pos);
/* 34 */         ChunkPos chunk = new ChunkPos(pos);
/* 35 */         if (entity != null && !entity.m_58901_()) {
/*    */           
/* 37 */           if (!this.level.m_8902_().contains(chunk.m_45588_())) {
/* 38 */             this.level.m_8602_(chunk.f_45578_, chunk.f_45579_, true);
/*    */           }
/*    */           continue;
/*    */         } 
/* 42 */         this.level.m_8602_(chunk.f_45578_, chunk.f_45579_, false);
/* 43 */         loadingEntities.remove();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CompoundTag write() {
/* 51 */     CompoundTag tag = new CompoundTag();
/* 52 */     ListTag list = new ListTag();
/* 53 */     for (BlockPos pos : this.loadingEntities)
/* 54 */       list.add(NbtUtils.m_129224_(pos)); 
/* 55 */     tag.m_128365_("LoadedEntities", (Tag)list);
/* 56 */     return tag;
/*    */   }
/*    */ 
/*    */   
/*    */   public void read(CompoundTag tag) {
/* 61 */     ListTag list = tag.m_128437_("LoadedEntities", 10);
/* 62 */     List<BlockPos> loadingEntities = Lists.newArrayList();
/* 63 */     for (int i = 0; i < list.size(); i++)
/* 64 */       loadingEntities.add(NbtUtils.m_129239_(list.m_128728_(i))); 
/* 65 */     this.loadingEntities = loadingEntities;
/*    */   }
/*    */ 
/*    */   
/*    */   public void add(BlockPos pos) {
/* 70 */     this.loadingEntities.add(pos);
/*    */   }
/*    */ 
/*    */   
/*    */   public void remove(BlockPos pos) {
/* 75 */     this.loadingEntities.remove(pos);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\capability\ChunkLoadingBlockEntities.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
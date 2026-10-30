/*    */ package nonamecrackers2.witherstormmod.common.entity;
/*    */ 
/*    */ 
/*    */ public interface ChunkLoader
/*    */ {
/*    */   int loadRadius();
/*    */   
/*    */   default boolean shouldLoad() {
/*  9 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   default boolean shouldContinueLoading() {
/* 14 */     return (shouldLoad() && isStillValidForChunkLoading());
/*    */   }
/*    */   
/*    */   boolean isStillValidForChunkLoading();
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ChunkLoader.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
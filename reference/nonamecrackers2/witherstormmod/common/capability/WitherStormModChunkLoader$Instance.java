/*     */ package nonamecrackers2.witherstormmod.common.capability;
/*     */ 
/*     */ import net.minecraft.world.level.ChunkPos;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ChunkLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Instance
/*     */ {
/*     */   private ChunkPos current;
/*     */   private int radius;
/*     */   private boolean needsInit = true;
/*     */   private final ChunkLoader loader;
/*     */   
/*     */   public Instance(ChunkLoader loader, ChunkPos current, int radius) {
/* 152 */     this.loader = loader;
/* 153 */     this.current = current;
/* 154 */     this.radius = radius;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRadius() {
/* 159 */     return this.radius;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChunkPos getPos() {
/* 164 */     return this.current;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateCurrentPosition(ChunkPos pos) {
/* 169 */     if (!this.current.equals(pos)) {
/*     */       
/* 171 */       unload();
/* 172 */       this.current = pos;
/* 173 */       load();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unload() {
/* 179 */     WitherStormModChunkLoader.LOGGER.debug("Unloading chunk {}", this.current);
/* 180 */     WitherStormModChunkLoader.this.level.m_7726_().removeRegionTicket(WitherStormModChunkLoader.WITHER_STORM, this.current, this.radius, this.current, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void load() {
/* 185 */     WitherStormModChunkLoader.LOGGER.debug("Loading chunk {} with radius {}", this.current, Integer.valueOf(this.radius));
/* 186 */     WitherStormModChunkLoader.this.level.m_7726_().addRegionTicket(WitherStormModChunkLoader.WITHER_STORM, this.current, this.radius, this.current, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkAndClearNeedsInit() {
/* 191 */     boolean flag = this.needsInit;
/* 192 */     if (flag)
/* 193 */       this.needsInit = false; 
/* 194 */     return flag;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\capability\WitherStormModChunkLoader$Instance.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
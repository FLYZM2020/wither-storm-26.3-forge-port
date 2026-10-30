/*     */ package nonamecrackers2.witherstormmod.client.entity;
/*     */ 
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.world.level.BlockAndTintGetter;
/*     */ import net.minecraft.world.level.ColorResolver;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.LightLayer;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.lighting.LevelLightEngine;
/*     */ import net.minecraft.world.level.material.FluidState;
/*     */ import nonamecrackers2.crackerslib.common.compat.CompatHelper;
/*     */ import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockClusterWorld
/*     */   implements BlockAndTintGetter
/*     */ {
/*     */   private final Level wrapped;
/*     */   private final BlockClusterEntity cluster;
/*     */   
/*     */   public BlockClusterWorld(Level wrapped, BlockClusterEntity cluster) {
/* 150 */     this.wrapped = wrapped;
/* 151 */     this.cluster = cluster;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockEntity m_7702_(BlockPos pos) {
/* 157 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState m_8055_(BlockPos pos) {
/* 163 */     BlockState state = (BlockState)this.cluster.getBlocks().get(pos.m_121996_((Vec3i)this.cluster.getStartPos()));
/* 164 */     if (state == null)
/* 165 */       state = Blocks.f_50016_.m_49966_(); 
/* 166 */     return state;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FluidState m_6425_(BlockPos pos) {
/* 172 */     return m_8055_(pos).m_60819_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int m_141928_() {
/* 178 */     return this.wrapped.m_141928_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int m_141937_() {
/* 184 */     return this.wrapped.m_141937_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float m_7717_(Direction direction, boolean p_45523_) {
/* 190 */     return this.wrapped.m_7717_(direction, p_45523_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LevelLightEngine m_5518_() {
/* 196 */     return this.wrapped.m_5518_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int m_45524_(BlockPos pos, int skyOffset) {
/* 202 */     return CompatHelper.areShadersRunning() ? super.m_45524_(pos, skyOffset) : 15;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int m_45517_(LightLayer layer, BlockPos pos) {
/* 208 */     return CompatHelper.areShadersRunning() ? super.m_45517_(layer, pos) : 15;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int m_6171_(BlockPos pos, ColorResolver resolver) {
/* 214 */     return this.wrapped.m_6171_(pos, resolver);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\entity\ClientBlockClusterEntity$BlockClusterWorld.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
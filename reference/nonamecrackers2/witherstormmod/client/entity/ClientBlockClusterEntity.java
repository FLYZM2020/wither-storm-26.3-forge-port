/*     */ package nonamecrackers2.witherstormmod.client.entity;
/*     */ 
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.block.BlockRenderDispatcher;
/*     */ import net.minecraft.client.resources.model.BakedModel;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.network.syncher.EntityDataAccessor;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.level.BlockAndTintGetter;
/*     */ import net.minecraft.world.level.ColorResolver;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.LightLayer;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.RenderShape;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.lighting.LevelLightEngine;
/*     */ import net.minecraft.world.level.material.FluidState;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.client.ChunkRenderTypeSet;
/*     */ import net.minecraftforge.client.model.data.ModelData;
/*     */ import nonamecrackers2.crackerslib.common.compat.CompatHelper;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
/*     */ 
/*     */ public class ClientBlockClusterEntity extends BlockClusterEntity {
/*     */   private final BlockClusterWorld blockGetter;
/*  36 */   private Map<RenderType, Map<BlockPos, BlockState>> toRender = new LinkedHashMap<>();
/*  37 */   private Map<BlockPos, BlockState> tilesToRender = new LinkedHashMap<>();
/*     */   
/*  39 */   public float fadeAmount = 1.0F; @Nullable
/*  40 */   private String toRenderUniqueId; private float fadeAmountO = 1.0F;
/*     */ 
/*     */   
/*     */   public ClientBlockClusterEntity(EntityType<? extends BlockClusterEntity> entityType, Level world) {
/*  44 */     super(entityType, world);
/*  45 */     this.blockGetter = new BlockClusterWorld(world, this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7350_(EntityDataAccessor<?> parameter) {
/*  51 */     super.m_7350_(parameter);
/*  52 */     if (parameter.equals(BlockClusterEntity.BLOCKS)) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  57 */       this.toRender.clear();
/*  58 */       this.tilesToRender.clear();
/*  59 */       for (Map.Entry<BlockPos, BlockState> entry : (Iterable<Map.Entry<BlockPos, BlockState>>)getBlocks().entrySet()) {
/*     */         
/*  61 */         BlockPos pos = entry.getKey();
/*  62 */         BlockState state = entry.getValue();
/*  63 */         if (state.m_60799_() != RenderShape.ENTITYBLOCK_ANIMATED) {
/*     */           
/*  65 */           if (state.m_60799_() == RenderShape.MODEL) {
/*     */             
/*  67 */             BlockRenderDispatcher dispatcher = Minecraft.m_91087_().m_91289_();
/*  68 */             BakedModel model = dispatcher.m_110910_(state);
/*  69 */             ChunkRenderTypeSet blockRenderTypes = model.getRenderTypes(state, RandomSource.m_216335_(state.m_60726_(getStartPos())), ModelData.EMPTY);
/*  70 */             for (RenderType type : RenderType.m_110506_()) {
/*     */               
/*  72 */               if (blockRenderTypes.contains(type)) {
/*     */                 
/*  74 */                 Map<BlockPos, BlockState> map = this.toRender.computeIfAbsent(type, t -> new LinkedHashMap<>());
/*  75 */                 map.put(pos, state);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/*     */           continue;
/*     */         } 
/*  82 */         this.tilesToRender.put(pos, state);
/*     */       } 
/*     */       
/*  85 */       this.toRenderUniqueId = this.toRender.toString();
/*     */     }
/*  87 */     else if (parameter.equals(BlockClusterEntity.FADE_POINT)) {
/*     */       
/*  89 */       calculateFade();
/*  90 */       this.fadeAmountO = this.fadeAmount;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<RenderType, Map<BlockPos, BlockState>> toRender() {
/*  96 */     return this.toRender;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<BlockPos, BlockState> tilesToRender() {
/* 101 */     return this.tilesToRender;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public String getToRenderUniqueId() {
/* 106 */     return this.toRenderUniqueId;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockAndTintGetter getBlockGetter() {
/* 111 */     return this.blockGetter;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_8119_() {
/* 117 */     super.m_8119_();
/*     */     
/* 119 */     calculateFade();
/*     */   }
/*     */ 
/*     */   
/*     */   private void calculateFade() {
/* 124 */     if (((Boolean)WitherStormModConfig.CLIENT.blockClusterRendering.get()).booleanValue() && getShakeTime() <= 0) {
/*     */       
/* 126 */       this.fadeAmountO = this.fadeAmount;
/* 127 */       BlockPos point = getFadePos();
/* 128 */       if (point != null) {
/*     */         
/* 130 */         double distanceFromCreationToFade = Math.sqrt(getStartPos().m_123331_((Vec3i)point)) - getFadeDistanceOffset();
/* 131 */         double distance = Math.max(0.0D, Vec3.m_82512_((Vec3i)point).m_82554_(m_20182_()) - getFadeDistanceOffset());
/* 132 */         this.fadeAmount = Math.min(1.0F, (float)distance / Math.min((float)distanceFromCreationToFade, getFadeStrength()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float lerpFadeAmount(float partialTicks) {
/* 139 */     return Mth.m_14179_(partialTicks, this.fadeAmountO, this.fadeAmount);
/*     */   }
/*     */ 
/*     */   
/*     */   public static class BlockClusterWorld
/*     */     implements BlockAndTintGetter
/*     */   {
/*     */     private final Level wrapped;
/*     */     private final BlockClusterEntity cluster;
/*     */     
/*     */     public BlockClusterWorld(Level wrapped, BlockClusterEntity cluster) {
/* 150 */       this.wrapped = wrapped;
/* 151 */       this.cluster = cluster;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public BlockEntity m_7702_(BlockPos pos) {
/* 157 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public BlockState m_8055_(BlockPos pos) {
/* 163 */       BlockState state = (BlockState)this.cluster.getBlocks().get(pos.m_121996_((Vec3i)this.cluster.getStartPos()));
/* 164 */       if (state == null)
/* 165 */         state = Blocks.f_50016_.m_49966_(); 
/* 166 */       return state;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public FluidState m_6425_(BlockPos pos) {
/* 172 */       return m_8055_(pos).m_60819_();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int m_141928_() {
/* 178 */       return this.wrapped.m_141928_();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int m_141937_() {
/* 184 */       return this.wrapped.m_141937_();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public float m_7717_(Direction direction, boolean p_45523_) {
/* 190 */       return this.wrapped.m_7717_(direction, p_45523_);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public LevelLightEngine m_5518_() {
/* 196 */       return this.wrapped.m_5518_();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int m_45524_(BlockPos pos, int skyOffset) {
/* 202 */       return CompatHelper.areShadersRunning() ? super.m_45524_(pos, skyOffset) : 15;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int m_45517_(LightLayer layer, BlockPos pos) {
/* 208 */       return CompatHelper.areShadersRunning() ? super.m_45517_(layer, pos) : 15;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int m_6171_(BlockPos pos, ColorResolver resolver) {
/* 214 */       return this.wrapped.m_6171_(pos, resolver);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\entity\ClientBlockClusterEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
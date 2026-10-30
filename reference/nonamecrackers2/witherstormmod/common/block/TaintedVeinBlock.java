/*     */ package nonamecrackers2.witherstormmod.common.block;
/*     */ 
/*     */ import java.util.function.ToIntFunction;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.item.Items;
/*     */ import net.minecraft.world.item.context.BlockPlaceContext;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.LevelAccessor;
/*     */ import net.minecraft.world.level.LevelReader;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.BonemealableBlock;
/*     */ import net.minecraft.world.level.block.MultifaceBlock;
/*     */ import net.minecraft.world.level.block.MultifaceSpreader;
/*     */ import net.minecraft.world.level.block.SimpleWaterloggedBlock;
/*     */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.StateDefinition;
/*     */ import net.minecraft.world.level.block.state.properties.BlockStateProperties;
/*     */ import net.minecraft.world.level.block.state.properties.BooleanProperty;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import net.minecraft.world.level.material.Fluid;
/*     */ import net.minecraft.world.level.material.FluidState;
/*     */ import net.minecraft.world.level.material.Fluids;
/*     */ 
/*     */ public class TaintedVeinBlock extends MultifaceBlock implements BonemealableBlock, SimpleWaterloggedBlock {
/*  30 */   private static final BooleanProperty WATERLOGGED = BlockStateProperties.f_61362_;
/*  31 */   private final MultifaceSpreader spreader = new MultifaceSpreader(this);
/*     */ 
/*     */   
/*     */   public TaintedVeinBlock(BlockBehaviour.Properties properties) {
/*  35 */     super(properties);
/*  36 */     m_49959_((BlockState)m_49966_().m_61124_((Property)WATERLOGGED, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public static ToIntFunction<BlockState> emission(int lightLevel) {
/*  41 */     return state -> MultifaceBlock.m_153960_(state) ? lightLevel : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_7926_(StateDefinition.Builder<Block, BlockState> stateBuilder) {
/*  49 */     super.m_7926_(stateBuilder);
/*  50 */     stateBuilder.m_61104_(new Property[] { (Property)WATERLOGGED });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState m_7417_(BlockState state, Direction direction, BlockState state2, LevelAccessor levelAccessor, BlockPos pos, BlockPos pos2) {
/*  56 */     if (((Boolean)state.m_61143_((Property)WATERLOGGED)).booleanValue()) {
/*  57 */       levelAccessor.m_186469_(pos, (Fluid)Fluids.f_76193_, Fluids.f_76193_.m_6718_((LevelReader)levelAccessor));
/*     */     }
/*  59 */     return super.m_7417_(state, direction, state2, levelAccessor, pos, pos2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6864_(BlockState state, BlockPlaceContext placeContext) {
/*  65 */     return (!placeContext.m_43722_().m_150930_(Items.f_151025_) || super.m_6864_(state, placeContext));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_7370_(LevelReader levelreader, BlockPos pos, BlockState state, boolean yeah) {
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_214167_(Level level, RandomSource randomSource, BlockPos pos, BlockState state) {
/*  77 */     return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_214148_(ServerLevel level, RandomSource randomSource, BlockPos pos, BlockState state) {
/* 106 */     this.spreader.m_221619_(state, (LevelAccessor)level, pos, randomSource);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FluidState m_5888_(BlockState state) {
/* 113 */     return ((Boolean)state.m_61143_((Property)WATERLOGGED)).booleanValue() ? Fluids.f_76193_.m_76068_(false) : super.m_5888_(state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_7420_(BlockState state, BlockGetter getter, BlockPos pos) {
/* 119 */     return state.m_60819_().m_76178_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MultifaceSpreader m_213612_() {
/* 125 */     return this.spreader;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\TaintedVeinBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
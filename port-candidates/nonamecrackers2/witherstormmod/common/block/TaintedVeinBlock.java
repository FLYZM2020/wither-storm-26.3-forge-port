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
/*  30 */   private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
/*  31 */   private final MultifaceSpreader spreader = new MultifaceSpreader(this);
/*     */ 
/*     */   
/*     */   public TaintedVeinBlock(BlockBehaviour.Properties properties) {
/*  35 */     super(properties);
/*  36 */     registerDefaultState((BlockState)defaultBlockState().setValue((Property)WATERLOGGED, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public static ToIntFunction<BlockState> emission(int lightLevel) {
/*  41 */     return state -> MultifaceBlock.hasAnyFace(state) ? lightLevel : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
/*  49 */     super.createBlockStateDefinition(stateBuilder);
/*  50 */     stateBuilder.add(new Property[] { (Property)WATERLOGGED });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState updateShape(BlockState state, Direction direction, BlockState state2, LevelAccessor levelAccessor, BlockPos pos, BlockPos pos2) {
/*  56 */     if (((Boolean)state.getValue((Property)WATERLOGGED)).booleanValue()) {
/*  57 */       levelAccessor.scheduleTick(pos, (Fluid)Fluids.WATER, Fluids.WATER.getTickDelay((LevelReader)levelAccessor));
/*     */     }
/*  59 */     return super.updateShape(state, direction, state2, levelAccessor, pos, pos2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeReplaced(BlockState state, BlockPlaceContext placeContext) {
/*  65 */     return (!placeContext.getItemInHand().is(Items.GLOW_LICHEN) || super.canBeReplaced(state, placeContext));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValidBonemealTarget(LevelReader levelreader, BlockPos pos, BlockState state, boolean yeah) {
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos pos, BlockState state) {
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
/*     */   public void performBonemeal(ServerLevel level, RandomSource randomSource, BlockPos pos, BlockState state) {
/* 106 */     this.spreader.spreadFromRandomFaceTowardRandomDirection(state, (LevelAccessor)level, pos, randomSource);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FluidState getFluidState(BlockState state) {
/* 113 */     return ((Boolean)state.getValue((Property)WATERLOGGED)).booleanValue() ? Fluids.WATER.getSource(false) : super.getFluidState(state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
/* 119 */     return state.getFluidState().isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MultifaceSpreader getSpreader() {
/* 125 */     return this.spreader;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\TaintedVeinBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
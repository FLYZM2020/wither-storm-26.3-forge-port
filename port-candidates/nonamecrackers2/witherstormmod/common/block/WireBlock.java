/*     */ package nonamecrackers2.witherstormmod.common.block;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.core.particles.DustParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.item.context.BlockPlaceContext;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.LevelAccessor;
/*     */ import net.minecraft.world.level.LevelReader;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.Mirror;
/*     */ import net.minecraft.world.level.block.Rotation;
/*     */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.StateDefinition;
/*     */ import net.minecraft.world.level.block.state.properties.BlockStateProperties;
/*     */ import net.minecraft.world.level.block.state.properties.EnumProperty;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import net.minecraft.world.level.block.state.properties.RedstoneSide;
/*     */ import net.minecraft.world.phys.shapes.CollisionContext;
/*     */ import net.minecraft.world.phys.shapes.Shapes;
/*     */ import net.minecraft.world.phys.shapes.VoxelShape;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*     */ import org.joml.Vector3f;
/*     */ 
/*     */ public class WireBlock
/*     */   extends Block {
/*  38 */   public static final EnumProperty<RedstoneSide> NORTH = BlockStateProperties.NORTH_REDSTONE;
/*  39 */   public static final EnumProperty<RedstoneSide> EAST = BlockStateProperties.EAST_REDSTONE;
/*  40 */   public static final EnumProperty<RedstoneSide> SOUTH = BlockStateProperties.SOUTH_REDSTONE;
/*  41 */   public static final EnumProperty<RedstoneSide> WEST = BlockStateProperties.WEST_REDSTONE;
/*  42 */   public static final Map<Direction, EnumProperty<RedstoneSide>> PROPERTY_BY_DIRECTION = Maps.newEnumMap((Map)ImmutableMap.of(Direction.NORTH, NORTH, Direction.EAST, EAST, Direction.SOUTH, SOUTH, Direction.WEST, WEST));
/*  43 */   private static final VoxelShape SHAPE_DOT = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 1.0D, 13.0D);
/*  44 */   private static final Map<Direction, VoxelShape> SHAPES_FLOOR = Maps.newEnumMap((Map)ImmutableMap.of(Direction.NORTH, Block.box(3.0D, 0.0D, 0.0D, 13.0D, 1.0D, 13.0D), Direction.SOUTH, Block.box(3.0D, 0.0D, 3.0D, 13.0D, 1.0D, 16.0D), Direction.EAST, Block.box(3.0D, 0.0D, 3.0D, 16.0D, 1.0D, 13.0D), Direction.WEST, Block.box(0.0D, 0.0D, 3.0D, 13.0D, 1.0D, 13.0D)));
/*  45 */   private static final Map<Direction, VoxelShape> SHAPES_UP = Maps.newEnumMap((Map)ImmutableMap.of(Direction.NORTH, Shapes.or(SHAPES_FLOOR.get(Direction.NORTH), Block.box(3.0D, 0.0D, 0.0D, 13.0D, 16.0D, 1.0D)), Direction.SOUTH, Shapes.or(SHAPES_FLOOR.get(Direction.SOUTH), Block.box(3.0D, 0.0D, 15.0D, 13.0D, 16.0D, 16.0D)), Direction.EAST, Shapes.or(SHAPES_FLOOR.get(Direction.EAST), Block.box(15.0D, 0.0D, 3.0D, 16.0D, 16.0D, 13.0D)), Direction.WEST, Shapes.or(SHAPES_FLOOR.get(Direction.WEST), Block.box(0.0D, 0.0D, 3.0D, 1.0D, 16.0D, 13.0D))));
/*  46 */   private final Map<BlockState, VoxelShape> shapesCache = Maps.newHashMap();
/*     */   
/*     */   protected final Vector3f color;
/*     */   
/*     */   public WireBlock(float r, float g, float b, BlockBehaviour.Properties properties) {
/*  51 */     super(properties);
/*  52 */     registerDefaultState((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue((Property)NORTH, (Comparable)RedstoneSide.NONE)).setValue((Property)EAST, (Comparable)RedstoneSide.NONE)).setValue((Property)SOUTH, (Comparable)RedstoneSide.NONE)).setValue((Property)WEST, (Comparable)RedstoneSide.NONE));
/*     */     
/*  54 */     for (UnmodifiableIterator<BlockState> unmodifiableIterator = getStateDefinition().getPossibleStates().iterator(); unmodifiableIterator.hasNext(); ) { BlockState state = unmodifiableIterator.next();
/*  55 */       this.shapesCache.put(state, calculateShape(state)); }
/*     */     
/*  57 */     this.color = new Vector3f(r, g, b);
/*     */   }
/*     */ 
/*     */   
/*     */   protected VoxelShape calculateShape(BlockState state) {
/*  62 */     VoxelShape voxelshape = SHAPE_DOT;
/*     */     
/*  64 */     for (Direction direction : Direction.Plane.HORIZONTAL) {
/*     */       
/*  66 */       RedstoneSide redstoneside = (RedstoneSide)state.getValue((Property)PROPERTY_BY_DIRECTION.get(direction));
/*  67 */       if (redstoneside == RedstoneSide.SIDE) {
/*  68 */         voxelshape = Shapes.or(voxelshape, SHAPES_FLOOR.get(direction)); continue;
/*  69 */       }  if (redstoneside == RedstoneSide.UP) {
/*  70 */         voxelshape = Shapes.or(voxelshape, SHAPES_UP.get(direction));
/*     */       }
/*     */     } 
/*  73 */     return voxelshape;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
/*  79 */     return this.shapesCache.get(state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState getStateForPlacement(BlockPlaceContext context) {
/*  85 */     return getConnectionState((BlockGetter)context.getLevel(), defaultBlockState(), context.getClickedPos());
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState getConnectionState(BlockGetter reader, BlockState state, BlockPos pos) {
/*  90 */     boolean flag = isDot(state);
/*  91 */     state = getMissingConnections(reader, defaultBlockState(), pos);
/*  92 */     if (flag && isDot(state))
/*     */     {
/*  94 */       return state;
/*     */     }
/*     */ 
/*     */     
/*  98 */     boolean flag1 = ((RedstoneSide)state.getValue((Property)NORTH)).isConnected();
/*  99 */     boolean flag2 = ((RedstoneSide)state.getValue((Property)SOUTH)).isConnected();
/* 100 */     boolean flag3 = ((RedstoneSide)state.getValue((Property)EAST)).isConnected();
/* 101 */     boolean flag4 = ((RedstoneSide)state.getValue((Property)WEST)).isConnected();
/* 102 */     boolean flag5 = (!flag1 && !flag2);
/* 103 */     boolean flag6 = (!flag3 && !flag4);
/* 104 */     if (!flag4 && flag5) {
/* 105 */       state = (BlockState)state.setValue((Property)WEST, (Comparable)RedstoneSide.SIDE);
/*     */     }
/* 107 */     if (!flag3 && flag5) {
/* 108 */       state = (BlockState)state.setValue((Property)EAST, (Comparable)RedstoneSide.SIDE);
/*     */     }
/* 110 */     if (!flag1 && flag6) {
/* 111 */       state = (BlockState)state.setValue((Property)NORTH, (Comparable)RedstoneSide.SIDE);
/*     */     }
/* 113 */     if (!flag2 && flag6) {
/* 114 */       state = (BlockState)state.setValue((Property)SOUTH, (Comparable)RedstoneSide.SIDE);
/*     */     }
/* 116 */     return state;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BlockState getMissingConnections(BlockGetter reader, BlockState state, BlockPos pos) {
/* 122 */     boolean flag = !reader.getBlockState(pos.above()).isRedstoneConductor(reader, pos);
/*     */     
/* 124 */     for (Direction direction : Direction.Plane.HORIZONTAL) {
/*     */       
/* 126 */       if (!((RedstoneSide)state.getValue((Property)PROPERTY_BY_DIRECTION.get(direction))).isConnected()) {
/*     */         
/* 128 */         RedstoneSide redstoneside = getConnectingSide(reader, pos, direction, flag);
/* 129 */         state = (BlockState)state.setValue((Property)PROPERTY_BY_DIRECTION.get(direction), (Comparable)redstoneside);
/*     */       } 
/*     */     } 
/*     */     
/* 133 */     return state;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor world, BlockPos pos, BlockPos pos1) {
/* 139 */     if (direction == Direction.DOWN)
/*     */     {
/* 141 */       return state;
/*     */     }
/* 143 */     if (direction == Direction.UP)
/*     */     {
/* 145 */       return getConnectionState((BlockGetter)world, state, pos);
/*     */     }
/*     */ 
/*     */     
/* 149 */     RedstoneSide redstoneside = getConnectingSide((BlockGetter)world, pos, direction);
/* 150 */     return (redstoneside.isConnected() == ((RedstoneSide)state.getValue((Property)PROPERTY_BY_DIRECTION.get(direction))).isConnected() && !isCross(state)) ? (BlockState)state.setValue((Property)PROPERTY_BY_DIRECTION.get(direction), (Comparable)redstoneside) : getConnectionState((BlockGetter)world, (BlockState)defaultBlockState().setValue((Property)PROPERTY_BY_DIRECTION.get(direction), (Comparable)redstoneside), pos);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean isCross(BlockState state) {
/* 156 */     return (((RedstoneSide)state.getValue((Property)NORTH)).isConnected() && ((RedstoneSide)state.getValue((Property)SOUTH)).isConnected() && ((RedstoneSide)state.getValue((Property)EAST)).isConnected() && ((RedstoneSide)state.getValue((Property)WEST)).isConnected());
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean isDot(BlockState state) {
/* 161 */     return (!((RedstoneSide)state.getValue((Property)NORTH)).isConnected() && !((RedstoneSide)state.getValue((Property)SOUTH)).isConnected() && !((RedstoneSide)state.getValue((Property)EAST)).isConnected() && !((RedstoneSide)state.getValue((Property)WEST)).isConnected());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateIndirectNeighbourShapes(BlockState state, LevelAccessor world, BlockPos pos, int p_196248_4_, int p_196248_5_) {
/* 167 */     BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
/*     */     
/* 169 */     for (Direction direction : Direction.Plane.HORIZONTAL) {
/*     */       
/* 171 */       RedstoneSide redstoneside = (RedstoneSide)state.getValue((Property)PROPERTY_BY_DIRECTION.get(direction));
/* 172 */       if (redstoneside != RedstoneSide.NONE && !world.getBlockState((BlockPos)blockpos$mutable.setWithOffset((Vec3i)pos, direction)).is(this)) {
/*     */         
/* 174 */         blockpos$mutable.move(Direction.DOWN);
/* 175 */         BlockState blockstate = world.getBlockState((BlockPos)blockpos$mutable);
/* 176 */         if (!blockstate.is(Blocks.OBSERVER)) {
/*     */           
/* 178 */           BlockPos blockpos = blockpos$mutable.relative(direction.getOpposite());
/* 179 */           BlockState blockstate1 = blockstate.updateShape(direction.getOpposite(), world.getBlockState(blockpos), world, (BlockPos)blockpos$mutable, blockpos);
/* 180 */           updateOrDestroy(blockstate, blockstate1, world, (BlockPos)blockpos$mutable, p_196248_4_, p_196248_5_);
/*     */         } 
/*     */         
/* 183 */         blockpos$mutable.setWithOffset((Vec3i)pos, direction).move(Direction.UP);
/* 184 */         BlockState blockstate3 = world.getBlockState((BlockPos)blockpos$mutable);
/* 185 */         if (!blockstate3.is(Blocks.OBSERVER)) {
/*     */           
/* 187 */           BlockPos blockpos1 = blockpos$mutable.relative(direction.getOpposite());
/* 188 */           BlockState blockstate2 = blockstate3.updateShape(direction.getOpposite(), world.getBlockState(blockpos1), world, (BlockPos)blockpos$mutable, blockpos1);
/* 189 */           updateOrDestroy(blockstate3, blockstate2, world, (BlockPos)blockpos$mutable, p_196248_4_, p_196248_5_);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected RedstoneSide getConnectingSide(BlockGetter reader, BlockPos pos, Direction direction) {
/* 197 */     return getConnectingSide(reader, pos, direction, !reader.getBlockState(pos.above()).isRedstoneConductor(reader, pos));
/*     */   }
/*     */ 
/*     */   
/*     */   protected RedstoneSide getConnectingSide(BlockGetter reader, BlockPos pos, Direction direction, boolean upCheck) {
/* 202 */     BlockPos blockpos = pos.relative(direction);
/* 203 */     BlockState blockstate = reader.getBlockState(blockpos);
/* 204 */     if (upCheck) {
/*     */       
/* 206 */       boolean flag = canSurviveOn(reader, blockpos, blockstate);
/* 207 */       if (flag && canConnectTo(reader.getBlockState(blockpos.above()), reader, blockpos.above(), (Direction)null)) {
/*     */         
/* 209 */         if (blockstate.isFaceSturdy(reader, blockpos, direction.getOpposite()))
/*     */         {
/* 211 */           return RedstoneSide.UP;
/*     */         }
/*     */         
/* 214 */         return RedstoneSide.SIDE;
/*     */       } 
/*     */     } 
/*     */     
/* 218 */     return (!canConnectTo(blockstate, reader, blockpos, direction) && (blockstate.isRedstoneConductor(reader, blockpos) || !canConnectTo(reader.getBlockState(blockpos.below()), reader, blockpos.below(), (Direction)null))) ? RedstoneSide.NONE : RedstoneSide.SIDE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
/* 224 */     BlockPos blockpos = pos.below();
/* 225 */     BlockState blockstate = reader.getBlockState(blockpos);
/* 226 */     return canSurviveOn((BlockGetter)reader, blockpos, blockstate);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canSurviveOn(BlockGetter reader, BlockPos pos, BlockState state) {
/* 231 */     return (state.isFaceSturdy(reader, pos, Direction.UP) || state.is(Blocks.HOPPER));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void checkCornerChangeAt(Level world, BlockPos pos) {
/* 236 */     if (world.getBlockState(pos).is(this)) {
/*     */       
/* 238 */       world.updateNeighborsAt(pos, this);
/*     */       
/* 240 */       for (Direction direction : Direction.values()) {
/* 241 */         world.updateNeighborsAt(pos.relative(direction), this);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(BlockState state, Level world, BlockPos pos, BlockState state1, boolean flag) {
/* 248 */     if (!state1.is(state.getBlock()) && !world.isClientSide) {
/*     */       
/* 250 */       for (Direction direction : Direction.Plane.VERTICAL) {
/* 251 */         world.updateNeighborsAt(pos.relative(direction), this);
/*     */       }
/* 253 */       updateNeighborsOfNeighboringWires(world, pos);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onRemove(BlockState state, Level world, BlockPos pos, BlockState state1, boolean flag) {
/* 261 */     if (!flag && !state.is(state1.getBlock())) {
/*     */       
/* 263 */       super.onRemove(state, world, pos, state1, flag);
/* 264 */       if (!world.isClientSide) {
/*     */         
/* 266 */         for (Direction direction : Direction.values()) {
/* 267 */           world.updateNeighborsAt(pos.relative(direction), this);
/*     */         }
/* 269 */         updateNeighborsOfNeighboringWires(world, pos);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateNeighborsOfNeighboringWires(Level world, BlockPos pos) {
/* 276 */     for (Direction direction : Direction.Plane.HORIZONTAL) {
/* 277 */       checkCornerChangeAt(world, pos.relative(direction));
/*     */     }
/* 279 */     for (Direction direction1 : Direction.Plane.HORIZONTAL) {
/*     */       
/* 281 */       BlockPos blockpos = pos.relative(direction1);
/* 282 */       if (world.getBlockState(blockpos).isRedstoneConductor((BlockGetter)world, blockpos)) {
/* 283 */         checkCornerChangeAt(world, blockpos.above()); continue;
/*     */       } 
/* 285 */       checkCornerChangeAt(world, blockpos.below());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos pos1, boolean flag) {
/* 292 */     if (!world.isClientSide)
/*     */     {
/* 294 */       if (!state.canSurvive((LevelReader)world, pos)) {
/*     */         
/* 296 */         dropResources(state, world, pos);
/* 297 */         world.removeBlock(pos, false);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean canConnectTo(BlockState state, BlockGetter world, BlockPos pos, @Nullable Direction direction) {
/* 304 */     return state.is((Block)WitherStormModBlocks.TAINTED_DUST.get());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void spawnParticlesAlongLine(Level world, RandomSource random, BlockPos pos, Vector3f colors, Direction direction, Direction direction1, float f3, float f4) {
/* 309 */     float f = f4 - f3;
/* 310 */     if (random.nextFloat() < 0.2F * f) {
/*     */       
/* 312 */       float f1 = 0.4375F;
/* 313 */       float f2 = f3 + f * random.nextFloat();
/* 314 */       double d0 = 0.5D + (f1 * direction.getStepX()) + (f2 * direction1.getStepX());
/* 315 */       double d1 = 0.5D + (f1 * direction.getStepY()) + (f2 * direction1.getStepY());
/* 316 */       double d2 = 0.5D + (f1 * direction.getStepZ()) + (f2 * direction1.getStepZ());
/* 317 */       world.addParticle((ParticleOptions)new DustParticleOptions(new Vector3f(colors.x(), colors.y(), colors.z()), 1.0F), pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2, 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
/* 324 */     for (Direction direction : Direction.Plane.HORIZONTAL) {
/*     */       
/* 326 */       RedstoneSide redstoneside = (RedstoneSide)state.getValue((Property)PROPERTY_BY_DIRECTION.get(direction));
/* 327 */       switch (redstoneside) {
/*     */         
/*     */         case LEFT_RIGHT:
/* 330 */           spawnParticlesAlongLine(world, random, pos, this.color, direction, Direction.UP, -0.5F, 0.5F);
/*     */         case FRONT_BACK:
/* 332 */           spawnParticlesAlongLine(world, random, pos, this.color, Direction.DOWN, direction, 0.0F, 0.5F);
/*     */           continue;
/*     */       } 
/*     */       
/* 336 */       spawnParticlesAlongLine(world, random, pos, this.color, Direction.DOWN, direction, 0.0F, 0.3F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState rotate(BlockState state, Rotation rotation) {
/* 344 */     switch (rotation) {
/*     */       
/*     */       case LEFT_RIGHT:
/* 347 */         return (BlockState)((BlockState)((BlockState)((BlockState)state.setValue((Property)NORTH, state.getValue((Property)SOUTH))).setValue((Property)EAST, state.getValue((Property)WEST))).setValue((Property)SOUTH, state.getValue((Property)NORTH))).setValue((Property)WEST, state.getValue((Property)EAST));
/*     */       case FRONT_BACK:
/* 349 */         return (BlockState)((BlockState)((BlockState)((BlockState)state.setValue((Property)NORTH, state.getValue((Property)EAST))).setValue((Property)EAST, state.getValue((Property)SOUTH))).setValue((Property)SOUTH, state.getValue((Property)WEST))).setValue((Property)WEST, state.getValue((Property)NORTH));
/*     */       case null:
/* 351 */         return (BlockState)((BlockState)((BlockState)((BlockState)state.setValue((Property)NORTH, state.getValue((Property)WEST))).setValue((Property)EAST, state.getValue((Property)NORTH))).setValue((Property)SOUTH, state.getValue((Property)EAST))).setValue((Property)WEST, state.getValue((Property)SOUTH));
/*     */     } 
/* 353 */     return state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState mirror(BlockState state, Mirror mirror) {
/* 361 */     switch (mirror) {
/*     */       
/*     */       case LEFT_RIGHT:
/* 364 */         return (BlockState)((BlockState)state.setValue((Property)NORTH, state.getValue((Property)SOUTH))).setValue((Property)SOUTH, state.getValue((Property)NORTH));
/*     */       case FRONT_BACK:
/* 366 */         return (BlockState)((BlockState)state.setValue((Property)EAST, state.getValue((Property)WEST))).setValue((Property)WEST, state.getValue((Property)EAST));
/*     */     } 
/* 368 */     return super.mirror(state, mirror);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
/* 375 */     builder.add(new Property[] { (Property)NORTH, (Property)EAST, (Property)SOUTH, (Property)WEST });
/*     */   }
/*     */ 
/*     */   
/*     */   public Vector3f getColor() {
/* 380 */     return this.color;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\WireBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
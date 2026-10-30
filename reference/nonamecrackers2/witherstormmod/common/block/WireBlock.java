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
/*  38 */   public static final EnumProperty<RedstoneSide> NORTH = BlockStateProperties.f_61383_;
/*  39 */   public static final EnumProperty<RedstoneSide> EAST = BlockStateProperties.f_61382_;
/*  40 */   public static final EnumProperty<RedstoneSide> SOUTH = BlockStateProperties.f_61384_;
/*  41 */   public static final EnumProperty<RedstoneSide> WEST = BlockStateProperties.f_61385_;
/*  42 */   public static final Map<Direction, EnumProperty<RedstoneSide>> PROPERTY_BY_DIRECTION = Maps.newEnumMap((Map)ImmutableMap.of(Direction.NORTH, NORTH, Direction.EAST, EAST, Direction.SOUTH, SOUTH, Direction.WEST, WEST));
/*  43 */   private static final VoxelShape SHAPE_DOT = Block.m_49796_(3.0D, 0.0D, 3.0D, 13.0D, 1.0D, 13.0D);
/*  44 */   private static final Map<Direction, VoxelShape> SHAPES_FLOOR = Maps.newEnumMap((Map)ImmutableMap.of(Direction.NORTH, Block.m_49796_(3.0D, 0.0D, 0.0D, 13.0D, 1.0D, 13.0D), Direction.SOUTH, Block.m_49796_(3.0D, 0.0D, 3.0D, 13.0D, 1.0D, 16.0D), Direction.EAST, Block.m_49796_(3.0D, 0.0D, 3.0D, 16.0D, 1.0D, 13.0D), Direction.WEST, Block.m_49796_(0.0D, 0.0D, 3.0D, 13.0D, 1.0D, 13.0D)));
/*  45 */   private static final Map<Direction, VoxelShape> SHAPES_UP = Maps.newEnumMap((Map)ImmutableMap.of(Direction.NORTH, Shapes.m_83110_(SHAPES_FLOOR.get(Direction.NORTH), Block.m_49796_(3.0D, 0.0D, 0.0D, 13.0D, 16.0D, 1.0D)), Direction.SOUTH, Shapes.m_83110_(SHAPES_FLOOR.get(Direction.SOUTH), Block.m_49796_(3.0D, 0.0D, 15.0D, 13.0D, 16.0D, 16.0D)), Direction.EAST, Shapes.m_83110_(SHAPES_FLOOR.get(Direction.EAST), Block.m_49796_(15.0D, 0.0D, 3.0D, 16.0D, 16.0D, 13.0D)), Direction.WEST, Shapes.m_83110_(SHAPES_FLOOR.get(Direction.WEST), Block.m_49796_(0.0D, 0.0D, 3.0D, 1.0D, 16.0D, 13.0D))));
/*  46 */   private final Map<BlockState, VoxelShape> shapesCache = Maps.newHashMap();
/*     */   
/*     */   protected final Vector3f color;
/*     */   
/*     */   public WireBlock(float r, float g, float b, BlockBehaviour.Properties properties) {
/*  51 */     super(properties);
/*  52 */     m_49959_((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_((Property)NORTH, (Comparable)RedstoneSide.NONE)).m_61124_((Property)EAST, (Comparable)RedstoneSide.NONE)).m_61124_((Property)SOUTH, (Comparable)RedstoneSide.NONE)).m_61124_((Property)WEST, (Comparable)RedstoneSide.NONE));
/*     */     
/*  54 */     for (UnmodifiableIterator<BlockState> unmodifiableIterator = m_49965_().m_61056_().iterator(); unmodifiableIterator.hasNext(); ) { BlockState state = unmodifiableIterator.next();
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
/*  66 */       RedstoneSide redstoneside = (RedstoneSide)state.m_61143_((Property)PROPERTY_BY_DIRECTION.get(direction));
/*  67 */       if (redstoneside == RedstoneSide.SIDE) {
/*  68 */         voxelshape = Shapes.m_83110_(voxelshape, SHAPES_FLOOR.get(direction)); continue;
/*  69 */       }  if (redstoneside == RedstoneSide.UP) {
/*  70 */         voxelshape = Shapes.m_83110_(voxelshape, SHAPES_UP.get(direction));
/*     */       }
/*     */     } 
/*  73 */     return voxelshape;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape m_5940_(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
/*  79 */     return this.shapesCache.get(state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState m_5573_(BlockPlaceContext context) {
/*  85 */     return getConnectionState((BlockGetter)context.m_43725_(), m_49966_(), context.m_8083_());
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState getConnectionState(BlockGetter reader, BlockState state, BlockPos pos) {
/*  90 */     boolean flag = isDot(state);
/*  91 */     state = getMissingConnections(reader, m_49966_(), pos);
/*  92 */     if (flag && isDot(state))
/*     */     {
/*  94 */       return state;
/*     */     }
/*     */ 
/*     */     
/*  98 */     boolean flag1 = ((RedstoneSide)state.m_61143_((Property)NORTH)).m_61761_();
/*  99 */     boolean flag2 = ((RedstoneSide)state.m_61143_((Property)SOUTH)).m_61761_();
/* 100 */     boolean flag3 = ((RedstoneSide)state.m_61143_((Property)EAST)).m_61761_();
/* 101 */     boolean flag4 = ((RedstoneSide)state.m_61143_((Property)WEST)).m_61761_();
/* 102 */     boolean flag5 = (!flag1 && !flag2);
/* 103 */     boolean flag6 = (!flag3 && !flag4);
/* 104 */     if (!flag4 && flag5) {
/* 105 */       state = (BlockState)state.m_61124_((Property)WEST, (Comparable)RedstoneSide.SIDE);
/*     */     }
/* 107 */     if (!flag3 && flag5) {
/* 108 */       state = (BlockState)state.m_61124_((Property)EAST, (Comparable)RedstoneSide.SIDE);
/*     */     }
/* 110 */     if (!flag1 && flag6) {
/* 111 */       state = (BlockState)state.m_61124_((Property)NORTH, (Comparable)RedstoneSide.SIDE);
/*     */     }
/* 113 */     if (!flag2 && flag6) {
/* 114 */       state = (BlockState)state.m_61124_((Property)SOUTH, (Comparable)RedstoneSide.SIDE);
/*     */     }
/* 116 */     return state;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BlockState getMissingConnections(BlockGetter reader, BlockState state, BlockPos pos) {
/* 122 */     boolean flag = !reader.m_8055_(pos.m_7494_()).m_60796_(reader, pos);
/*     */     
/* 124 */     for (Direction direction : Direction.Plane.HORIZONTAL) {
/*     */       
/* 126 */       if (!((RedstoneSide)state.m_61143_((Property)PROPERTY_BY_DIRECTION.get(direction))).m_61761_()) {
/*     */         
/* 128 */         RedstoneSide redstoneside = getConnectingSide(reader, pos, direction, flag);
/* 129 */         state = (BlockState)state.m_61124_((Property)PROPERTY_BY_DIRECTION.get(direction), (Comparable)redstoneside);
/*     */       } 
/*     */     } 
/*     */     
/* 133 */     return state;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState m_7417_(BlockState state, Direction direction, BlockState state1, LevelAccessor world, BlockPos pos, BlockPos pos1) {
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
/* 150 */     return (redstoneside.m_61761_() == ((RedstoneSide)state.m_61143_((Property)PROPERTY_BY_DIRECTION.get(direction))).m_61761_() && !isCross(state)) ? (BlockState)state.m_61124_((Property)PROPERTY_BY_DIRECTION.get(direction), (Comparable)redstoneside) : getConnectionState((BlockGetter)world, (BlockState)m_49966_().m_61124_((Property)PROPERTY_BY_DIRECTION.get(direction), (Comparable)redstoneside), pos);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean isCross(BlockState state) {
/* 156 */     return (((RedstoneSide)state.m_61143_((Property)NORTH)).m_61761_() && ((RedstoneSide)state.m_61143_((Property)SOUTH)).m_61761_() && ((RedstoneSide)state.m_61143_((Property)EAST)).m_61761_() && ((RedstoneSide)state.m_61143_((Property)WEST)).m_61761_());
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean isDot(BlockState state) {
/* 161 */     return (!((RedstoneSide)state.m_61143_((Property)NORTH)).m_61761_() && !((RedstoneSide)state.m_61143_((Property)SOUTH)).m_61761_() && !((RedstoneSide)state.m_61143_((Property)EAST)).m_61761_() && !((RedstoneSide)state.m_61143_((Property)WEST)).m_61761_());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7742_(BlockState state, LevelAccessor world, BlockPos pos, int p_196248_4_, int p_196248_5_) {
/* 167 */     BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
/*     */     
/* 169 */     for (Direction direction : Direction.Plane.HORIZONTAL) {
/*     */       
/* 171 */       RedstoneSide redstoneside = (RedstoneSide)state.m_61143_((Property)PROPERTY_BY_DIRECTION.get(direction));
/* 172 */       if (redstoneside != RedstoneSide.NONE && !world.m_8055_((BlockPos)blockpos$mutable.m_122159_((Vec3i)pos, direction)).m_60713_(this)) {
/*     */         
/* 174 */         blockpos$mutable.m_122173_(Direction.DOWN);
/* 175 */         BlockState blockstate = world.m_8055_((BlockPos)blockpos$mutable);
/* 176 */         if (!blockstate.m_60713_(Blocks.f_50455_)) {
/*     */           
/* 178 */           BlockPos blockpos = blockpos$mutable.m_121945_(direction.m_122424_());
/* 179 */           BlockState blockstate1 = blockstate.m_60728_(direction.m_122424_(), world.m_8055_(blockpos), world, (BlockPos)blockpos$mutable, blockpos);
/* 180 */           m_49908_(blockstate, blockstate1, world, (BlockPos)blockpos$mutable, p_196248_4_, p_196248_5_);
/*     */         } 
/*     */         
/* 183 */         blockpos$mutable.m_122159_((Vec3i)pos, direction).m_122173_(Direction.UP);
/* 184 */         BlockState blockstate3 = world.m_8055_((BlockPos)blockpos$mutable);
/* 185 */         if (!blockstate3.m_60713_(Blocks.f_50455_)) {
/*     */           
/* 187 */           BlockPos blockpos1 = blockpos$mutable.m_121945_(direction.m_122424_());
/* 188 */           BlockState blockstate2 = blockstate3.m_60728_(direction.m_122424_(), world.m_8055_(blockpos1), world, (BlockPos)blockpos$mutable, blockpos1);
/* 189 */           m_49908_(blockstate3, blockstate2, world, (BlockPos)blockpos$mutable, p_196248_4_, p_196248_5_);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected RedstoneSide getConnectingSide(BlockGetter reader, BlockPos pos, Direction direction) {
/* 197 */     return getConnectingSide(reader, pos, direction, !reader.m_8055_(pos.m_7494_()).m_60796_(reader, pos));
/*     */   }
/*     */ 
/*     */   
/*     */   protected RedstoneSide getConnectingSide(BlockGetter reader, BlockPos pos, Direction direction, boolean upCheck) {
/* 202 */     BlockPos blockpos = pos.m_121945_(direction);
/* 203 */     BlockState blockstate = reader.m_8055_(blockpos);
/* 204 */     if (upCheck) {
/*     */       
/* 206 */       boolean flag = canSurviveOn(reader, blockpos, blockstate);
/* 207 */       if (flag && canConnectTo(reader.m_8055_(blockpos.m_7494_()), reader, blockpos.m_7494_(), (Direction)null)) {
/*     */         
/* 209 */         if (blockstate.m_60783_(reader, blockpos, direction.m_122424_()))
/*     */         {
/* 211 */           return RedstoneSide.UP;
/*     */         }
/*     */         
/* 214 */         return RedstoneSide.SIDE;
/*     */       } 
/*     */     } 
/*     */     
/* 218 */     return (!canConnectTo(blockstate, reader, blockpos, direction) && (blockstate.m_60796_(reader, blockpos) || !canConnectTo(reader.m_8055_(blockpos.m_7495_()), reader, blockpos.m_7495_(), (Direction)null))) ? RedstoneSide.NONE : RedstoneSide.SIDE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_7898_(BlockState state, LevelReader reader, BlockPos pos) {
/* 224 */     BlockPos blockpos = pos.m_7495_();
/* 225 */     BlockState blockstate = reader.m_8055_(blockpos);
/* 226 */     return canSurviveOn((BlockGetter)reader, blockpos, blockstate);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canSurviveOn(BlockGetter reader, BlockPos pos, BlockState state) {
/* 231 */     return (state.m_60783_(reader, pos, Direction.UP) || state.m_60713_(Blocks.f_50332_));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void checkCornerChangeAt(Level world, BlockPos pos) {
/* 236 */     if (world.m_8055_(pos).m_60713_(this)) {
/*     */       
/* 238 */       world.m_46672_(pos, this);
/*     */       
/* 240 */       for (Direction direction : Direction.values()) {
/* 241 */         world.m_46672_(pos.m_121945_(direction), this);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_6807_(BlockState state, Level world, BlockPos pos, BlockState state1, boolean flag) {
/* 248 */     if (!state1.m_60713_(state.m_60734_()) && !world.f_46443_) {
/*     */       
/* 250 */       for (Direction direction : Direction.Plane.VERTICAL) {
/* 251 */         world.m_46672_(pos.m_121945_(direction), this);
/*     */       }
/* 253 */       updateNeighborsOfNeighboringWires(world, pos);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_6810_(BlockState state, Level world, BlockPos pos, BlockState state1, boolean flag) {
/* 261 */     if (!flag && !state.m_60713_(state1.m_60734_())) {
/*     */       
/* 263 */       super.m_6810_(state, world, pos, state1, flag);
/* 264 */       if (!world.f_46443_) {
/*     */         
/* 266 */         for (Direction direction : Direction.values()) {
/* 267 */           world.m_46672_(pos.m_121945_(direction), this);
/*     */         }
/* 269 */         updateNeighborsOfNeighboringWires(world, pos);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateNeighborsOfNeighboringWires(Level world, BlockPos pos) {
/* 276 */     for (Direction direction : Direction.Plane.HORIZONTAL) {
/* 277 */       checkCornerChangeAt(world, pos.m_121945_(direction));
/*     */     }
/* 279 */     for (Direction direction1 : Direction.Plane.HORIZONTAL) {
/*     */       
/* 281 */       BlockPos blockpos = pos.m_121945_(direction1);
/* 282 */       if (world.m_8055_(blockpos).m_60796_((BlockGetter)world, blockpos)) {
/* 283 */         checkCornerChangeAt(world, blockpos.m_7494_()); continue;
/*     */       } 
/* 285 */       checkCornerChangeAt(world, blockpos.m_7495_());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_6861_(BlockState state, Level world, BlockPos pos, Block block, BlockPos pos1, boolean flag) {
/* 292 */     if (!world.f_46443_)
/*     */     {
/* 294 */       if (!state.m_60710_((LevelReader)world, pos)) {
/*     */         
/* 296 */         m_49950_(state, world, pos);
/* 297 */         world.m_7471_(pos, false);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean canConnectTo(BlockState state, BlockGetter world, BlockPos pos, @Nullable Direction direction) {
/* 304 */     return state.m_60713_((Block)WitherStormModBlocks.TAINTED_DUST.get());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void spawnParticlesAlongLine(Level world, RandomSource random, BlockPos pos, Vector3f colors, Direction direction, Direction direction1, float f3, float f4) {
/* 309 */     float f = f4 - f3;
/* 310 */     if (random.m_188501_() < 0.2F * f) {
/*     */       
/* 312 */       float f1 = 0.4375F;
/* 313 */       float f2 = f3 + f * random.m_188501_();
/* 314 */       double d0 = 0.5D + (f1 * direction.m_122429_()) + (f2 * direction1.m_122429_());
/* 315 */       double d1 = 0.5D + (f1 * direction.m_122430_()) + (f2 * direction1.m_122430_());
/* 316 */       double d2 = 0.5D + (f1 * direction.m_122431_()) + (f2 * direction1.m_122431_());
/* 317 */       world.m_7106_((ParticleOptions)new DustParticleOptions(new Vector3f(colors.x(), colors.y(), colors.z()), 1.0F), pos.m_123341_() + d0, pos.m_123342_() + d1, pos.m_123343_() + d2, 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_214162_(BlockState state, Level world, BlockPos pos, RandomSource random) {
/* 324 */     for (Direction direction : Direction.Plane.HORIZONTAL) {
/*     */       
/* 326 */       RedstoneSide redstoneside = (RedstoneSide)state.m_61143_((Property)PROPERTY_BY_DIRECTION.get(direction));
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
/*     */   public BlockState m_6843_(BlockState state, Rotation rotation) {
/* 344 */     switch (rotation) {
/*     */       
/*     */       case LEFT_RIGHT:
/* 347 */         return (BlockState)((BlockState)((BlockState)((BlockState)state.m_61124_((Property)NORTH, state.m_61143_((Property)SOUTH))).m_61124_((Property)EAST, state.m_61143_((Property)WEST))).m_61124_((Property)SOUTH, state.m_61143_((Property)NORTH))).m_61124_((Property)WEST, state.m_61143_((Property)EAST));
/*     */       case FRONT_BACK:
/* 349 */         return (BlockState)((BlockState)((BlockState)((BlockState)state.m_61124_((Property)NORTH, state.m_61143_((Property)EAST))).m_61124_((Property)EAST, state.m_61143_((Property)SOUTH))).m_61124_((Property)SOUTH, state.m_61143_((Property)WEST))).m_61124_((Property)WEST, state.m_61143_((Property)NORTH));
/*     */       case null:
/* 351 */         return (BlockState)((BlockState)((BlockState)((BlockState)state.m_61124_((Property)NORTH, state.m_61143_((Property)WEST))).m_61124_((Property)EAST, state.m_61143_((Property)NORTH))).m_61124_((Property)SOUTH, state.m_61143_((Property)EAST))).m_61124_((Property)WEST, state.m_61143_((Property)SOUTH));
/*     */     } 
/* 353 */     return state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState m_6943_(BlockState state, Mirror mirror) {
/* 361 */     switch (mirror) {
/*     */       
/*     */       case LEFT_RIGHT:
/* 364 */         return (BlockState)((BlockState)state.m_61124_((Property)NORTH, state.m_61143_((Property)SOUTH))).m_61124_((Property)SOUTH, state.m_61143_((Property)NORTH));
/*     */       case FRONT_BACK:
/* 366 */         return (BlockState)((BlockState)state.m_61124_((Property)EAST, state.m_61143_((Property)WEST))).m_61124_((Property)WEST, state.m_61143_((Property)EAST));
/*     */     } 
/* 368 */     return super.m_6943_(state, mirror);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_7926_(StateDefinition.Builder<Block, BlockState> builder) {
/* 375 */     builder.m_61104_(new Property[] { (Property)NORTH, (Property)EAST, (Property)SOUTH, (Property)WEST });
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
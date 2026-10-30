/*     */ package nonamecrackers2.witherstormmod.common.block;
/*     */ 
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.advancements.CriteriaTriggers;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.EquipmentSlot;
/*     */ import net.minecraft.world.item.Equipable;
/*     */ import net.minecraft.world.item.context.BlockPlaceContext;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.LevelReader;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.CarvedPumpkinBlock;
/*     */ import net.minecraft.world.level.block.HorizontalDirectionalBlock;
/*     */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.StateDefinition;
/*     */ import net.minecraft.world.level.block.state.pattern.BlockInWorld;
/*     */ import net.minecraft.world.level.block.state.pattern.BlockPattern;
/*     */ import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
/*     */ import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
/*     */ import net.minecraft.world.level.block.state.properties.DirectionProperty;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import nonamecrackers2.witherstormmod.common.entity.SickenedIronGolem;
/*     */ import nonamecrackers2.witherstormmod.common.entity.SickenedSnowGolem;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ 
/*     */ public class TaintedCarvedPumpkinBlock extends HorizontalDirectionalBlock implements Equipable {
/*  35 */   public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING; @Nullable
/*     */   private BlockPattern taintedSnowGolemBase; @Nullable
/*     */   private BlockPattern taintedSnowGolemFull;
/*     */   
/*     */   static {
/*  40 */     PUMPKINS_PREDICATE = (state -> 
/*  41 */       (state != null && (state.is((Block)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get()) || state.is((Block)WitherStormModBlocks.TAINTED_JACK_O_LANTERN.get()))));
/*     */   } @Nullable
/*     */   private BlockPattern taintedIronGolemBase; @Nullable
/*     */   private BlockPattern taintedIronGolemFull; private static final Predicate<BlockState> PUMPKINS_PREDICATE;
/*     */   public TaintedCarvedPumpkinBlock(BlockBehaviour.Properties properties) {
/*  46 */     super(properties);
/*  47 */     registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue((Property)FACING, (Comparable)Direction.NORTH));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnGolem(LevelReader level, BlockPos pos) {
/*  52 */     return (getOrCreateTaintedIronGolemBase().find(level, pos) != null || getOrCreateTaintedSnowGolemBase().find(level, pos) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   private void trySpawnGolem(Level level, BlockPos pos) {
/*  57 */     BlockPattern.BlockPatternMatch snowmatch = getOrCreateTaintedSnowGolemFull().find((LevelReader)level, pos);
/*  58 */     if (snowmatch != null) {
/*     */       
/*  60 */       SickenedSnowGolem golem = (SickenedSnowGolem)((EntityType)WitherStormModEntityTypes.SICKENED_SNOW_GOLEM.get()).create(level);
/*  61 */       if (golem != null)
/*     */       {
/*  63 */         BlockPos spawnPos = snowmatch.getBlock(0, 2, 0).getPos();
/*  64 */         CarvedPumpkinBlock.clearPatternBlocks(level, snowmatch);
/*  65 */         golem.moveTo(spawnPos.getX() + 0.5D, spawnPos.getY() + 0.05D, spawnPos.getZ() + 0.5D, 0.0F, 0.0F);
/*  66 */         level.addFreshEntity((Entity)golem);
/*  67 */         CarvedPumpkinBlock.updatePatternBlocks(level, snowmatch);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  72 */       BlockPattern.BlockPatternMatch ironmatch = getOrCreateTaintedIronGolemFull().find((LevelReader)level, pos);
/*  73 */       if (ironmatch != null) {
/*     */         
/*  75 */         SickenedIronGolem golem = (SickenedIronGolem)((EntityType)WitherStormModEntityTypes.SICKENED_IRON_GOLEM.get()).create(level);
/*  76 */         if (golem != null) {
/*     */           
/*  78 */           BlockPos spawnPos = ironmatch.getBlock(1, 2, 0).getPos();
/*  79 */           CarvedPumpkinBlock.clearPatternBlocks(level, ironmatch);
/*  80 */           golem.moveTo(spawnPos.getX() + 0.5D, spawnPos.getY() + 0.05D, spawnPos.getZ() + 0.5D, 0.0F, 0.0F);
/*  81 */           level.addFreshEntity((Entity)golem);
/*  82 */           for (ServerPlayer serverplayer : level.getEntitiesOfClass(ServerPlayer.class, golem.getBoundingBox().inflate(5.0D)))
/*  83 */             CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayer, (Entity)golem); 
/*  84 */           CarvedPumpkinBlock.updatePatternBlocks(level, ironmatch);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EquipmentSlot getEquipmentSlot() {
/*  93 */     return EquipmentSlot.HEAD;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPlace(BlockState state, Level level, BlockPos pos, BlockState other, boolean flag) {
/*  99 */     if (!state.is(other.getBlock())) {
/* 100 */       trySpawnGolem(level, pos);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockState getStateForPlacement(BlockPlaceContext context) {
/* 106 */     return (BlockState)defaultBlockState().setValue((Property)FACING, (Comparable)context.getHorizontalDirection().getOpposite());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
/* 112 */     builder.add(new Property[] { (Property)FACING });
/*     */   }
/*     */ 
/*     */   
/*     */   private BlockPattern getOrCreateTaintedSnowGolemBase() {
/* 117 */     if (this.taintedSnowGolemBase == null)
/* 118 */       this.taintedSnowGolemBase = BlockPatternBuilder.start().aisle(new String[] { " ", "#", "#" }).where('#', BlockInWorld.hasState((Predicate)BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK))).build(); 
/* 119 */     return this.taintedSnowGolemBase;
/*     */   }
/*     */ 
/*     */   
/*     */   private BlockPattern getOrCreateTaintedSnowGolemFull() {
/* 124 */     if (this.taintedSnowGolemFull == null)
/* 125 */       this.taintedSnowGolemFull = BlockPatternBuilder.start().aisle(new String[] { "^", "#", "#" }).where('^', BlockInWorld.hasState(PUMPKINS_PREDICATE)).where('#', BlockInWorld.hasState((Predicate)BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK))).build(); 
/* 126 */     return this.taintedSnowGolemFull;
/*     */   }
/*     */ 
/*     */   
/*     */   private BlockPattern getOrCreateTaintedIronGolemBase() {
/* 131 */     if (this.taintedIronGolemBase == null)
/* 132 */       this.taintedIronGolemBase = BlockPatternBuilder.start().aisle(new String[] { "~ ~", "###", "~#~" }).where('#', BlockInWorld.hasState((Predicate)BlockStatePredicate.forBlock(Blocks.IRON_BLOCK))).where('~', BlockInWorld.hasState(BlockBehaviour.BlockStateBase::isAir)).build(); 
/* 133 */     return this.taintedIronGolemBase;
/*     */   }
/*     */ 
/*     */   
/*     */   private BlockPattern getOrCreateTaintedIronGolemFull() {
/* 138 */     if (this.taintedIronGolemFull == null)
/* 139 */       this.taintedIronGolemFull = BlockPatternBuilder.start().aisle(new String[] { "~^~", "###", "~#~" }).where('^', BlockInWorld.hasState(PUMPKINS_PREDICATE)).where('#', BlockInWorld.hasState((Predicate)BlockStatePredicate.forBlock(Blocks.IRON_BLOCK))).where('~', BlockInWorld.hasState(BlockBehaviour.BlockStateBase::isAir)).build(); 
/* 140 */     return this.taintedIronGolemFull;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\TaintedCarvedPumpkinBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package nonamecrackers2.witherstormmod.common.block;
/*     */ 
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.stats.Stats;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.entity.projectile.Projectile;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Items;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Explosion;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.BaseEntityBlock;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.RenderShape;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.entity.BlockEntityTicker;
/*     */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*     */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import nonamecrackers2.witherstormmod.common.blockentity.FireworkBundleBlockEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntityTypes;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ public class FireworkBundleBlock
/*     */   extends BaseEntityBlock
/*     */ {
/*     */   public FireworkBundleBlock(BlockBehaviour.Properties properties) {
/*  35 */     super(properties);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderShape getRenderShape(BlockState state) {
/*  41 */     return RenderShape.MODEL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCaughtFire(BlockState state, Level level, BlockPos pos, @Nullable Direction direction, @Nullable LivingEntity igniter) {
/*  47 */     activateFuse(level, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPlace(BlockState state, Level level, BlockPos pos, BlockState state2, boolean flag) {
/*  53 */     if (state2.is(state.getBlock()))
/*     */     {
/*  55 */       if (level.hasNeighborSignal(pos)) {
/*  56 */         activateFuse(level, pos);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos pos2, boolean flag) {
/*  63 */     if (level.hasNeighborSignal(pos)) {
/*  64 */       activateFuse(level, pos);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
/*  71 */     ItemStack stack = player.getItemInHand(hand);
/*  72 */     if (!stack.is(Items.FLINT_AND_STEEL) && !stack.is(Items.FIRE_CHARGE))
/*     */     {
/*  74 */       return super.use(state, level, pos, player, hand, hitResult);
/*     */     }
/*     */ 
/*     */     
/*  78 */     onCaughtFire(state, level, pos, hitResult.getDirection(), (LivingEntity)player);
/*  79 */     Item item = stack.getItem();
/*  80 */     if (!player.isCreative())
/*     */     {
/*  82 */       if (stack.is(Items.FLINT_AND_STEEL)) {
/*  83 */         stack.hurtAndBreak(1, (LivingEntity)player, p -> p.broadcastBreakEvent(hand));
/*     */       } else {
/*  85 */         stack.shrink(1);
/*     */       }  } 
/*  87 */     player.awardStat(Stats.ITEM_USED.get(item));
/*  88 */     return InteractionResult.sidedSuccess(level.isClientSide);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onProjectileHit(Level p_60453_, BlockState p_60454_, BlockHitResult p_60455_, Projectile p_60456_) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean dropFromExplosion(Explosion explosion) {
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void activateFuse(Level level, BlockPos pos) {
/* 105 */     BlockEntity blockEntity = level.getBlockEntity(pos); if (blockEntity instanceof FireworkBundleBlockEntity) { FireworkBundleBlockEntity entity = (FireworkBundleBlockEntity)blockEntity;
/* 106 */       entity.beginFuse(); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
/* 112 */     BlockEntity blockEntity = level.getBlockEntity(pos); if (blockEntity instanceof FireworkBundleBlockEntity) { FireworkBundleBlockEntity entity = (FireworkBundleBlockEntity)blockEntity; if (entity.isActivated())
/* 113 */         return 4;  }
/* 114 */      return super.getLightEmission(state, level, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
/* 120 */     return (BlockEntity)new FireworkBundleBlockEntity(pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
/* 126 */     return level.isClientSide ? null : createTickerHelper(type, (BlockEntityType)WitherStormModBlockEntityTypes.FIREWORK_BUNDLE.get(), FireworkBundleBlockEntity::serverTick);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\FireworkBundleBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
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
/*     */   public RenderShape m_7514_(BlockState state) {
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
/*     */   public void m_6807_(BlockState state, Level level, BlockPos pos, BlockState state2, boolean flag) {
/*  53 */     if (state2.m_60713_(state.m_60734_()))
/*     */     {
/*  55 */       if (level.m_276867_(pos)) {
/*  56 */         activateFuse(level, pos);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_6861_(BlockState state, Level level, BlockPos pos, Block block, BlockPos pos2, boolean flag) {
/*  63 */     if (level.m_276867_(pos)) {
/*  64 */       activateFuse(level, pos);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InteractionResult m_6227_(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
/*  71 */     ItemStack stack = player.m_21120_(hand);
/*  72 */     if (!stack.m_150930_(Items.f_42409_) && !stack.m_150930_(Items.f_42613_))
/*     */     {
/*  74 */       return super.m_6227_(state, level, pos, player, hand, hitResult);
/*     */     }
/*     */ 
/*     */     
/*  78 */     onCaughtFire(state, level, pos, hitResult.m_82434_(), (LivingEntity)player);
/*  79 */     Item item = stack.m_41720_();
/*  80 */     if (!player.m_7500_())
/*     */     {
/*  82 */       if (stack.m_150930_(Items.f_42409_)) {
/*  83 */         stack.m_41622_(1, (LivingEntity)player, p -> p.m_21190_(hand));
/*     */       } else {
/*  85 */         stack.m_41774_(1);
/*     */       }  } 
/*  87 */     player.m_36246_(Stats.f_12982_.m_12902_(item));
/*  88 */     return InteractionResult.m_19078_(level.f_46443_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_5581_(Level p_60453_, BlockState p_60454_, BlockHitResult p_60455_, Projectile p_60456_) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6903_(Explosion explosion) {
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void activateFuse(Level level, BlockPos pos) {
/* 105 */     BlockEntity blockEntity = level.m_7702_(pos); if (blockEntity instanceof FireworkBundleBlockEntity) { FireworkBundleBlockEntity entity = (FireworkBundleBlockEntity)blockEntity;
/* 106 */       entity.beginFuse(); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
/* 112 */     BlockEntity blockEntity = level.m_7702_(pos); if (blockEntity instanceof FireworkBundleBlockEntity) { FireworkBundleBlockEntity entity = (FireworkBundleBlockEntity)blockEntity; if (entity.isActivated())
/* 113 */         return 4;  }
/* 114 */      return super.getLightEmission(state, level, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockEntity m_142194_(BlockPos pos, BlockState state) {
/* 120 */     return (BlockEntity)new FireworkBundleBlockEntity(pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends BlockEntity> BlockEntityTicker<T> m_142354_(Level level, BlockState state, BlockEntityType<T> type) {
/* 126 */     return level.f_46443_ ? null : m_152132_(type, (BlockEntityType)WitherStormModBlockEntityTypes.FIREWORK_BUNDLE.get(), FireworkBundleBlockEntity::serverTick);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\FireworkBundleBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package nonamecrackers2.witherstormmod.common.block;
/*     */ import java.util.List;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.Container;
/*     */ import net.minecraft.world.Containers;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.MenuProvider;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.TooltipFlag;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.LevelReader;
/*     */ import net.minecraft.world.level.block.BaseEntityBlock;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.RenderShape;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.entity.BlockEntityTicker;
/*     */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*     */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.StateDefinition;
/*     */ import net.minecraft.world.level.block.state.properties.BlockStateProperties;
/*     */ import net.minecraft.world.level.block.state.properties.BooleanProperty;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.common.blockentity.WitheredPhlegmBlockEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
/*     */ 
/*     */ public class WitheredPhlegmBlock extends BaseEntityBlock {
/*  41 */   public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
/*     */ 
/*     */   
/*     */   public WitheredPhlegmBlock(BlockBehaviour.Properties properties) {
/*  45 */     super(properties);
/*  46 */     registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue((Property)POWERED, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
/*  52 */     builder.add(new Property[] { (Property)POWERED });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos pos1, boolean flag) {
/*  58 */     boolean neighborSignal = level.hasNeighborSignal(pos);
/*  59 */     if (neighborSignal != ((Boolean)state.getValue((Property)POWERED)).booleanValue()) {
/*  60 */       level.setBlock(pos, (BlockState)state.setValue((Property)POWERED, Boolean.valueOf(neighborSignal)), 3);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
/*  66 */     if (level.isClientSide)
/*     */     {
/*  68 */       return InteractionResult.SUCCESS;
/*     */     }
/*     */ 
/*     */     
/*  72 */     BlockEntity blockEntity = level.getBlockEntity(pos);
/*  73 */     if (blockEntity instanceof WitheredPhlegmBlockEntity) { WitheredPhlegmBlockEntity phlegmBlock = (WitheredPhlegmBlockEntity)blockEntity;
/*  74 */       player.openMenu((MenuProvider)phlegmBlock); }
/*     */     
/*  76 */     return InteractionResult.CONSUME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state1, boolean flag) {
/*  84 */     if (!state.is(state1.getBlock())) {
/*     */       
/*  86 */       BlockEntity blockEntity = level.getBlockEntity(pos);
/*  87 */       if (blockEntity instanceof Container) { Container container = (Container)blockEntity;
/*     */         
/*  89 */         Containers.dropContents(level, pos, container);
/*  90 */         level.updateNeighbourForOutputSignal(pos, (Block)this); }
/*     */     
/*     */     } 
/*     */     
/*  94 */     super.onRemove(state, level, pos, state1, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderShape getRenderShape(BlockState state) {
/* 100 */     return RenderShape.MODEL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
/* 106 */     return (BlockEntity)new WitheredPhlegmBlockEntity(pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
/* 112 */     return level.isClientSide ? null : createTickerHelper(type, (BlockEntityType)WitherStormModBlockEntityTypes.WITHERED_PHLEGM.get(), WitheredPhlegmBlockEntity::serverTick);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasAnalogOutputSignal(BlockState state) {
/* 119 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
/* 125 */     return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack item) {
/* 131 */     if (item.hasCustomHoverName()) {
/*     */       
/* 133 */       BlockEntity blockEntity = level.getBlockEntity(pos);
/* 134 */       if (blockEntity instanceof WitheredPhlegmBlockEntity) { WitheredPhlegmBlockEntity phlegmBlock = (WitheredPhlegmBlockEntity)blockEntity;
/* 135 */         phlegmBlock.setCustomName(item.getHoverName()); }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource source) {
/* 141 */     if (!((Boolean)state.getValue((Property)BlockStateProperties.POWERED)).booleanValue()) {
/* 142 */       doParticles(source, pos, level, 6, 3.0F, 0.1F);
/*     */     } else {
/* 144 */       doParticles(source, pos, level, 2, 1.0F, -0.01F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void doParticles(RandomSource random, BlockPos pos, Level level, int amount, float radius, float speed) {
/* 149 */     for (int i = 0; i < amount; i++) {
/*     */       
/* 151 */       double x = (random.nextFloat() * 2.0D - 1.0D) * radius;
/* 152 */       double y = (random.nextFloat() * 2.0D - 1.0D) * radius;
/* 153 */       double z = (random.nextFloat() * 2.0D - 1.0D) * radius;
/* 154 */       Vec3 start = Vec3.atCenterOf((Vec3i)pos).add(x, y, z);
/* 155 */       Vec3 delta = (new Vec3(x, y, z)).scale(speed);
/* 156 */       level.addParticle((ParticleOptions)WitherStormModParticleTypes.PHLEGM.get(), start.x, start.y, start.z, -delta.x, -delta.y, -delta.z);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendHoverText(ItemStack stack, BlockGetter level, List<Component> text, TooltipFlag flag) {
/* 163 */     text.add(Component.translatable("description.withered_phlegm.use").withStyle(ChatFormatting.DARK_GRAY));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
/* 169 */     BlockEntity blockEntity = level.getBlockEntity(pos); if (blockEntity instanceof WitheredPhlegmBlockEntity) { WitheredPhlegmBlockEntity entity = (WitheredPhlegmBlockEntity)blockEntity;
/* 170 */       entity.recheckOpen(); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExpDrop(BlockState state, LevelReader level, RandomSource randomSource, BlockPos pos, int fortuneLevel, int silkTouchLevel) {
/* 176 */     BlockEntity blockEntity = level.getBlockEntity(pos); WitheredPhlegmBlockEntity phlegm = (WitheredPhlegmBlockEntity)blockEntity; return (blockEntity instanceof WitheredPhlegmBlockEntity) ? phlegm.getStoredXp() : 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\WitheredPhlegmBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
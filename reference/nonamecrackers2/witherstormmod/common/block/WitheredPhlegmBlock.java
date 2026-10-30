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
/*  41 */   public static final BooleanProperty POWERED = BlockStateProperties.f_61448_;
/*     */ 
/*     */   
/*     */   public WitheredPhlegmBlock(BlockBehaviour.Properties properties) {
/*  45 */     super(properties);
/*  46 */     m_49959_((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_((Property)POWERED, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_7926_(StateDefinition.Builder<Block, BlockState> builder) {
/*  52 */     builder.m_61104_(new Property[] { (Property)POWERED });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_6861_(BlockState state, Level level, BlockPos pos, Block block, BlockPos pos1, boolean flag) {
/*  58 */     boolean neighborSignal = level.m_276867_(pos);
/*  59 */     if (neighborSignal != ((Boolean)state.m_61143_((Property)POWERED)).booleanValue()) {
/*  60 */       level.m_7731_(pos, (BlockState)state.m_61124_((Property)POWERED, Boolean.valueOf(neighborSignal)), 3);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public InteractionResult m_6227_(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
/*  66 */     if (level.f_46443_)
/*     */     {
/*  68 */       return InteractionResult.SUCCESS;
/*     */     }
/*     */ 
/*     */     
/*  72 */     BlockEntity blockEntity = level.m_7702_(pos);
/*  73 */     if (blockEntity instanceof WitheredPhlegmBlockEntity) { WitheredPhlegmBlockEntity phlegmBlock = (WitheredPhlegmBlockEntity)blockEntity;
/*  74 */       player.m_5893_((MenuProvider)phlegmBlock); }
/*     */     
/*  76 */     return InteractionResult.CONSUME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_6810_(BlockState state, Level level, BlockPos pos, BlockState state1, boolean flag) {
/*  84 */     if (!state.m_60713_(state1.m_60734_())) {
/*     */       
/*  86 */       BlockEntity blockEntity = level.m_7702_(pos);
/*  87 */       if (blockEntity instanceof Container) { Container container = (Container)blockEntity;
/*     */         
/*  89 */         Containers.m_19002_(level, pos, container);
/*  90 */         level.m_46717_(pos, (Block)this); }
/*     */     
/*     */     } 
/*     */     
/*  94 */     super.m_6810_(state, level, pos, state1, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderShape m_7514_(BlockState state) {
/* 100 */     return RenderShape.MODEL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockEntity m_142194_(BlockPos pos, BlockState state) {
/* 106 */     return (BlockEntity)new WitheredPhlegmBlockEntity(pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends BlockEntity> BlockEntityTicker<T> m_142354_(Level level, BlockState state, BlockEntityType<T> type) {
/* 112 */     return level.f_46443_ ? null : m_152132_(type, (BlockEntityType)WitherStormModBlockEntityTypes.WITHERED_PHLEGM.get(), WitheredPhlegmBlockEntity::serverTick);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_7278_(BlockState state) {
/* 119 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int m_6782_(BlockState state, Level level, BlockPos pos) {
/* 125 */     return AbstractContainerMenu.m_38918_(level.m_7702_(pos));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_6402_(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack item) {
/* 131 */     if (item.m_41788_()) {
/*     */       
/* 133 */       BlockEntity blockEntity = level.m_7702_(pos);
/* 134 */       if (blockEntity instanceof WitheredPhlegmBlockEntity) { WitheredPhlegmBlockEntity phlegmBlock = (WitheredPhlegmBlockEntity)blockEntity;
/* 135 */         phlegmBlock.m_58638_(item.m_41786_()); }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public void m_214162_(BlockState state, Level level, BlockPos pos, RandomSource source) {
/* 141 */     if (!((Boolean)state.m_61143_((Property)BlockStateProperties.f_61448_)).booleanValue()) {
/* 142 */       doParticles(source, pos, level, 6, 3.0F, 0.1F);
/*     */     } else {
/* 144 */       doParticles(source, pos, level, 2, 1.0F, -0.01F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void doParticles(RandomSource random, BlockPos pos, Level level, int amount, float radius, float speed) {
/* 149 */     for (int i = 0; i < amount; i++) {
/*     */       
/* 151 */       double x = (random.m_188501_() * 2.0D - 1.0D) * radius;
/* 152 */       double y = (random.m_188501_() * 2.0D - 1.0D) * radius;
/* 153 */       double z = (random.m_188501_() * 2.0D - 1.0D) * radius;
/* 154 */       Vec3 start = Vec3.m_82512_((Vec3i)pos).m_82520_(x, y, z);
/* 155 */       Vec3 delta = (new Vec3(x, y, z)).m_82490_(speed);
/* 156 */       level.m_7106_((ParticleOptions)WitherStormModParticleTypes.PHLEGM.get(), start.f_82479_, start.f_82480_, start.f_82481_, -delta.f_82479_, -delta.f_82480_, -delta.f_82481_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_5871_(ItemStack stack, BlockGetter level, List<Component> text, TooltipFlag flag) {
/* 163 */     text.add(Component.m_237115_("description.withered_phlegm.use").m_130940_(ChatFormatting.DARK_GRAY));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_213897_(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
/* 169 */     BlockEntity blockEntity = level.m_7702_(pos); if (blockEntity instanceof WitheredPhlegmBlockEntity) { WitheredPhlegmBlockEntity entity = (WitheredPhlegmBlockEntity)blockEntity;
/* 170 */       entity.recheckOpen(); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExpDrop(BlockState state, LevelReader level, RandomSource randomSource, BlockPos pos, int fortuneLevel, int silkTouchLevel) {
/* 176 */     BlockEntity blockEntity = level.m_7702_(pos); WitheredPhlegmBlockEntity phlegm = (WitheredPhlegmBlockEntity)blockEntity; return (blockEntity instanceof WitheredPhlegmBlockEntity) ? phlegm.getStoredXp() : 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\WitheredPhlegmBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
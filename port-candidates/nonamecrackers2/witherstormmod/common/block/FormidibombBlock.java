/*     */ package nonamecrackers2.witherstormmod.common.block;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.resources.Identifier;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.TooltipFlag;
/*     */ import net.minecraft.world.item.context.BlockPlaceContext;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Explosion;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.EntityBlock;
/*     */ import net.minecraft.world.level.block.HorizontalDirectionalBlock;
/*     */ import net.minecraft.world.level.block.Mirror;
/*     */ import net.minecraft.world.level.block.Rotation;
/*     */ import net.minecraft.world.level.block.TntBlock;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.entity.BlockEntityTicker;
/*     */ import net.minecraft.world.level.block.state.BlockBehaviour;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.StateDefinition;
/*     */ import net.minecraft.world.level.block.state.properties.DirectionProperty;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import net.minecraft.world.level.material.PushReaction;
/*     */ import net.minecraft.world.level.storage.loot.BuiltInLootTables;
/*     */ import net.minecraft.world.level.storage.loot.LootParams;
/*     */ import net.minecraft.world.level.storage.loot.LootTable;
/*     */ import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
/*     */ import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.common.blockentity.FormidibombBlockEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
/*     */ import nonamecrackers2.witherstormmod.common.item.FormidibombItem;
/*     */ import nonamecrackers2.witherstormmod.common.util.IFormidibomb;
/*     */ 
/*     */ public class FormidibombBlock extends TntBlock implements EntityBlock {
/*  51 */   public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
/*     */ 
/*     */   
/*     */   public FormidibombBlock(BlockBehaviour.Properties properties) {
/*  55 */     super(properties);
/*  56 */     registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue((Property)FACING, (Comparable)Direction.NORTH)).setValue((Property)TntBlock.UNSTABLE, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void wasExploded(Level world, BlockPos pos, Explosion explosion) {
/*  62 */     if (!world.isClientSide) {
/*     */       
/*  64 */       BlockEntity tile = world.getBlockEntity(pos);
/*  65 */       IFormidibomb formidibomb = null;
/*  66 */       if (tile instanceof IFormidibomb)
/*  67 */         formidibomb = (IFormidibomb)tile; 
/*  68 */       FormidibombEntity entity = new FormidibombEntity(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, explosion.getIndirectSourceEntity(), formidibomb, world.getBlockState(pos));
/*  69 */       entity.initiateFuse(20);
/*  70 */       world.addFreshEntity((Entity)entity);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCaughtFire(BlockState state, Level world, BlockPos pos, Direction face, LivingEntity igniter) {
/*  77 */     if (!world.isClientSide) {
/*     */       
/*  79 */       BlockEntity tile = world.getBlockEntity(pos);
/*  80 */       IFormidibomb formidibomb = null;
/*  81 */       if (tile instanceof IFormidibomb)
/*  82 */         formidibomb = (IFormidibomb)tile; 
/*  83 */       FormidibombEntity entity = new FormidibombEntity(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, igniter, formidibomb, state);
/*  84 */       int newFuse = ((Integer)WitherStormModConfig.SERVER.catchFireFuseTicks.get()).intValue();
/*  85 */       if (entity.getFuseLife() > newFuse)
/*  86 */         entity.initiateFuse(newFuse); 
/*  87 */       world.addFreshEntity((Entity)entity);
/*  88 */       world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource source) {
/*  94 */     doParticles(source, pos, level, 6, 3.0F, 0.1F);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void doParticles(RandomSource random, BlockPos pos, Level level, int amount, float radius, float speed) {
/*  99 */     for (int i = 0; i < amount; i++) {
/*     */       
/* 101 */       double x = (random.nextFloat() * 2.0D - 1.0D) * radius;
/* 102 */       double y = (random.nextFloat() * 2.0D - 1.0D) * radius;
/* 103 */       double z = (random.nextFloat() * 2.0D - 1.0D) * radius;
/* 104 */       Vec3 start = Vec3.atCenterOf((Vec3i)pos).add(x, y, z);
/* 105 */       Vec3 delta = (new Vec3(x, y, z)).scale(speed);
/* 106 */       level.addParticle((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), start.x, start.y, start.z, -delta.x, -delta.y, -delta.z);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState getStateForPlacement(BlockPlaceContext context) {
/* 113 */     return (BlockState)defaultBlockState().setValue((Property)FACING, (Comparable)context.getHorizontalDirection());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState rotate(BlockState state, Rotation rotation) {
/* 119 */     return (BlockState)state.setValue((Property)FACING, (Comparable)rotation.rotate((Direction)state.getValue((Property)FACING)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState mirror(BlockState state, Mirror mirror) {
/* 126 */     return state.rotate(mirror.getRotation((Direction)state.getValue((Property)FACING)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
/* 132 */     super.createBlockStateDefinition(builder);
/* 133 */     builder.add(new Property[] { (Property)FACING });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
/* 139 */     return (BlockEntity)new FormidibombBlockEntity(pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
/* 145 */     return castTicker(FormidibombBlockEntity::tick);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static <T extends BlockEntity> BlockEntityTicker<T> castTicker(BlockEntityTicker<FormidibombBlockEntity> ticker) {
/* 151 */     return (BlockEntityTicker)ticker;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ItemStack> getDrops(BlockState state, LootParams.Builder loot) {
/* 157 */     Identifier location = getLootTable();
/* 158 */     if (location == BuiltInLootTables.EMPTY)
/*     */     {
/* 160 */       return Collections.emptyList();
/*     */     }
/*     */ 
/*     */     
/* 164 */     LootParams context = loot.withParameter(LootContextParams.BLOCK_STATE, state).create(LootContextParamSets.BLOCK);
/* 165 */     ServerLevel world = context.getLevel();
/* 166 */     LootTable table = world.m_7654_().m_278653_().getLootTable(location);
/* 167 */     ObjectArrayList objectArrayList = table.getRandomItems(context);
/* 168 */     for (ItemStack stack : objectArrayList) {
/*     */       
/* 170 */       if (stack.getItem() instanceof FormidibombItem) {
/*     */         
/* 172 */         BlockEntity tile = (BlockEntity)loot.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
/* 173 */         if (tile instanceof IFormidibomb) {
/*     */           
/* 175 */           IFormidibomb formidibomb = (IFormidibomb)tile;
/* 176 */           CompoundTag compound = stack.getOrCreateTag();
/* 177 */           compound.putInt("Fuse", formidibomb.getFuseLife());
/* 178 */           compound.putInt("StartFuse", formidibomb.getStartFuse());
/*     */         } 
/*     */       } 
/*     */     } 
/* 182 */     return (List<ItemStack>)objectArrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendHoverText(ItemStack stack, BlockGetter world, List<Component> components, TooltipFlag flag) {
/* 189 */     super.appendHoverText(stack, world, components, flag);
/* 190 */     if (stack.getItem() instanceof FormidibombItem) {
/*     */       
/* 192 */       FormidibombItem item = (FormidibombItem)stack.getItem();
/* 193 */       if (item.getStartFuse(stack) > 0 && item.getFuse(stack) < item.getStartFuse(stack)) {
/*     */         
/* 195 */         ChatFormatting style = (item.getFuse(stack) / 10 % 2 == 0) ? ChatFormatting.RED : ChatFormatting.DARK_PURPLE;
/* 196 */         components.add(Component.translatable("description.formidibomb.fuse", new Object[] { Integer.valueOf(item.getFuse(stack) / 20) }).withStyle(style));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PushReaction getPistonPushReaction(BlockState state) {
/* 204 */     return PushReaction.BLOCK;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\FormidibombBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
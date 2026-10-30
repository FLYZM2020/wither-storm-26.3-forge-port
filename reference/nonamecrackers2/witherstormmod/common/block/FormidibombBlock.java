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
/*     */ import net.minecraft.resources.ResourceLocation;
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
/*  51 */   public static final DirectionProperty FACING = HorizontalDirectionalBlock.f_54117_;
/*     */ 
/*     */   
/*     */   public FormidibombBlock(BlockBehaviour.Properties properties) {
/*  55 */     super(properties);
/*  56 */     m_49959_((BlockState)((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_((Property)FACING, (Comparable)Direction.NORTH)).m_61124_((Property)TntBlock.f_57419_, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7592_(Level world, BlockPos pos, Explosion explosion) {
/*  62 */     if (!world.f_46443_) {
/*     */       
/*  64 */       BlockEntity tile = world.m_7702_(pos);
/*  65 */       IFormidibomb formidibomb = null;
/*  66 */       if (tile instanceof IFormidibomb)
/*  67 */         formidibomb = (IFormidibomb)tile; 
/*  68 */       FormidibombEntity entity = new FormidibombEntity(world, pos.m_123341_() + 0.5D, pos.m_123342_(), pos.m_123343_() + 0.5D, explosion.m_252906_(), formidibomb, world.m_8055_(pos));
/*  69 */       entity.initiateFuse(20);
/*  70 */       world.m_7967_((Entity)entity);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCaughtFire(BlockState state, Level world, BlockPos pos, Direction face, LivingEntity igniter) {
/*  77 */     if (!world.f_46443_) {
/*     */       
/*  79 */       BlockEntity tile = world.m_7702_(pos);
/*  80 */       IFormidibomb formidibomb = null;
/*  81 */       if (tile instanceof IFormidibomb)
/*  82 */         formidibomb = (IFormidibomb)tile; 
/*  83 */       FormidibombEntity entity = new FormidibombEntity(world, pos.m_123341_() + 0.5D, pos.m_123342_(), pos.m_123343_() + 0.5D, igniter, formidibomb, state);
/*  84 */       int newFuse = ((Integer)WitherStormModConfig.SERVER.catchFireFuseTicks.get()).intValue();
/*  85 */       if (entity.getFuseLife() > newFuse)
/*  86 */         entity.initiateFuse(newFuse); 
/*  87 */       world.m_7967_((Entity)entity);
/*  88 */       world.m_6263_(null, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), SoundEvents.f_12512_, SoundSource.BLOCKS, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_214162_(BlockState state, Level level, BlockPos pos, RandomSource source) {
/*  94 */     doParticles(source, pos, level, 6, 3.0F, 0.1F);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void doParticles(RandomSource random, BlockPos pos, Level level, int amount, float radius, float speed) {
/*  99 */     for (int i = 0; i < amount; i++) {
/*     */       
/* 101 */       double x = (random.m_188501_() * 2.0D - 1.0D) * radius;
/* 102 */       double y = (random.m_188501_() * 2.0D - 1.0D) * radius;
/* 103 */       double z = (random.m_188501_() * 2.0D - 1.0D) * radius;
/* 104 */       Vec3 start = Vec3.m_82512_((Vec3i)pos).m_82520_(x, y, z);
/* 105 */       Vec3 delta = (new Vec3(x, y, z)).m_82490_(speed);
/* 106 */       level.m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), start.f_82479_, start.f_82480_, start.f_82481_, -delta.f_82479_, -delta.f_82480_, -delta.f_82481_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState m_5573_(BlockPlaceContext context) {
/* 113 */     return (BlockState)m_49966_().m_61124_((Property)FACING, (Comparable)context.m_8125_());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState m_6843_(BlockState state, Rotation rotation) {
/* 119 */     return (BlockState)state.m_61124_((Property)FACING, (Comparable)rotation.m_55954_((Direction)state.m_61143_((Property)FACING)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState m_6943_(BlockState state, Mirror mirror) {
/* 126 */     return state.m_60717_(mirror.m_54846_((Direction)state.m_61143_((Property)FACING)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_7926_(StateDefinition.Builder<Block, BlockState> builder) {
/* 132 */     super.m_7926_(builder);
/* 133 */     builder.m_61104_(new Property[] { (Property)FACING });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockEntity m_142194_(BlockPos pos, BlockState state) {
/* 139 */     return (BlockEntity)new FormidibombBlockEntity(pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends BlockEntity> BlockEntityTicker<T> m_142354_(Level level, BlockState state, BlockEntityType<T> type) {
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
/*     */   public List<ItemStack> m_49635_(BlockState state, LootParams.Builder loot) {
/* 157 */     ResourceLocation location = m_60589_();
/* 158 */     if (location == BuiltInLootTables.f_78712_)
/*     */     {
/* 160 */       return Collections.emptyList();
/*     */     }
/*     */ 
/*     */     
/* 164 */     LootParams context = loot.m_287286_(LootContextParams.f_81461_, state).m_287235_(LootContextParamSets.f_81421_);
/* 165 */     ServerLevel world = context.m_287182_();
/* 166 */     LootTable table = world.m_7654_().m_278653_().m_278676_(location);
/* 167 */     ObjectArrayList objectArrayList = table.m_287195_(context);
/* 168 */     for (ItemStack stack : objectArrayList) {
/*     */       
/* 170 */       if (stack.m_41720_() instanceof FormidibombItem) {
/*     */         
/* 172 */         BlockEntity tile = (BlockEntity)loot.m_287159_(LootContextParams.f_81462_);
/* 173 */         if (tile instanceof IFormidibomb) {
/*     */           
/* 175 */           IFormidibomb formidibomb = (IFormidibomb)tile;
/* 176 */           CompoundTag compound = stack.m_41784_();
/* 177 */           compound.m_128405_("Fuse", formidibomb.getFuseLife());
/* 178 */           compound.m_128405_("StartFuse", formidibomb.getStartFuse());
/*     */         } 
/*     */       } 
/*     */     } 
/* 182 */     return (List<ItemStack>)objectArrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_5871_(ItemStack stack, BlockGetter world, List<Component> components, TooltipFlag flag) {
/* 189 */     super.m_5871_(stack, world, components, flag);
/* 190 */     if (stack.m_41720_() instanceof FormidibombItem) {
/*     */       
/* 192 */       FormidibombItem item = (FormidibombItem)stack.m_41720_();
/* 193 */       if (item.getStartFuse(stack) > 0 && item.getFuse(stack) < item.getStartFuse(stack)) {
/*     */         
/* 195 */         ChatFormatting style = (item.getFuse(stack) / 10 % 2 == 0) ? ChatFormatting.RED : ChatFormatting.DARK_PURPLE;
/* 196 */         components.add(Component.m_237110_("description.formidibomb.fuse", new Object[] { Integer.valueOf(item.getFuse(stack) / 20) }).m_130940_(style));
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
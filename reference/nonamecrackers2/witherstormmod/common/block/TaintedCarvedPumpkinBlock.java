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
/*  35 */   public static final DirectionProperty FACING = HorizontalDirectionalBlock.f_54117_; @Nullable
/*     */   private BlockPattern taintedSnowGolemBase; @Nullable
/*     */   private BlockPattern taintedSnowGolemFull;
/*     */   
/*     */   static {
/*  40 */     PUMPKINS_PREDICATE = (state -> 
/*  41 */       (state != null && (state.m_60713_((Block)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get()) || state.m_60713_((Block)WitherStormModBlocks.TAINTED_JACK_O_LANTERN.get()))));
/*     */   } @Nullable
/*     */   private BlockPattern taintedIronGolemBase; @Nullable
/*     */   private BlockPattern taintedIronGolemFull; private static final Predicate<BlockState> PUMPKINS_PREDICATE;
/*     */   public TaintedCarvedPumpkinBlock(BlockBehaviour.Properties properties) {
/*  46 */     super(properties);
/*  47 */     m_49959_((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_((Property)FACING, (Comparable)Direction.NORTH));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnGolem(LevelReader level, BlockPos pos) {
/*  52 */     return (getOrCreateTaintedIronGolemBase().m_61184_(level, pos) != null || getOrCreateTaintedSnowGolemBase().m_61184_(level, pos) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   private void trySpawnGolem(Level level, BlockPos pos) {
/*  57 */     BlockPattern.BlockPatternMatch snowmatch = getOrCreateTaintedSnowGolemFull().m_61184_((LevelReader)level, pos);
/*  58 */     if (snowmatch != null) {
/*     */       
/*  60 */       SickenedSnowGolem golem = (SickenedSnowGolem)((EntityType)WitherStormModEntityTypes.SICKENED_SNOW_GOLEM.get()).m_20615_(level);
/*  61 */       if (golem != null)
/*     */       {
/*  63 */         BlockPos spawnPos = snowmatch.m_61229_(0, 2, 0).m_61176_();
/*  64 */         CarvedPumpkinBlock.m_245585_(level, snowmatch);
/*  65 */         golem.m_7678_(spawnPos.m_123341_() + 0.5D, spawnPos.m_123342_() + 0.05D, spawnPos.m_123343_() + 0.5D, 0.0F, 0.0F);
/*  66 */         level.m_7967_((Entity)golem);
/*  67 */         CarvedPumpkinBlock.m_246758_(level, snowmatch);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  72 */       BlockPattern.BlockPatternMatch ironmatch = getOrCreateTaintedIronGolemFull().m_61184_((LevelReader)level, pos);
/*  73 */       if (ironmatch != null) {
/*     */         
/*  75 */         SickenedIronGolem golem = (SickenedIronGolem)((EntityType)WitherStormModEntityTypes.SICKENED_IRON_GOLEM.get()).m_20615_(level);
/*  76 */         if (golem != null) {
/*     */           
/*  78 */           BlockPos spawnPos = ironmatch.m_61229_(1, 2, 0).m_61176_();
/*  79 */           CarvedPumpkinBlock.m_245585_(level, ironmatch);
/*  80 */           golem.m_7678_(spawnPos.m_123341_() + 0.5D, spawnPos.m_123342_() + 0.05D, spawnPos.m_123343_() + 0.5D, 0.0F, 0.0F);
/*  81 */           level.m_7967_((Entity)golem);
/*  82 */           for (ServerPlayer serverplayer : level.m_45976_(ServerPlayer.class, golem.m_20191_().m_82400_(5.0D)))
/*  83 */             CriteriaTriggers.f_10580_.m_68256_(serverplayer, (Entity)golem); 
/*  84 */           CarvedPumpkinBlock.m_246758_(level, ironmatch);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EquipmentSlot m_40402_() {
/*  93 */     return EquipmentSlot.HEAD;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_6807_(BlockState state, Level level, BlockPos pos, BlockState other, boolean flag) {
/*  99 */     if (!state.m_60713_(other.m_60734_())) {
/* 100 */       trySpawnGolem(level, pos);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockState m_5573_(BlockPlaceContext context) {
/* 106 */     return (BlockState)m_49966_().m_61124_((Property)FACING, (Comparable)context.m_8125_().m_122424_());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_7926_(StateDefinition.Builder<Block, BlockState> builder) {
/* 112 */     builder.m_61104_(new Property[] { (Property)FACING });
/*     */   }
/*     */ 
/*     */   
/*     */   private BlockPattern getOrCreateTaintedSnowGolemBase() {
/* 117 */     if (this.taintedSnowGolemBase == null)
/* 118 */       this.taintedSnowGolemBase = BlockPatternBuilder.m_61243_().m_61247_(new String[] { " ", "#", "#" }).m_61244_('#', BlockInWorld.m_61169_((Predicate)BlockStatePredicate.m_61287_(Blocks.f_50127_))).m_61249_(); 
/* 119 */     return this.taintedSnowGolemBase;
/*     */   }
/*     */ 
/*     */   
/*     */   private BlockPattern getOrCreateTaintedSnowGolemFull() {
/* 124 */     if (this.taintedSnowGolemFull == null)
/* 125 */       this.taintedSnowGolemFull = BlockPatternBuilder.m_61243_().m_61247_(new String[] { "^", "#", "#" }).m_61244_('^', BlockInWorld.m_61169_(PUMPKINS_PREDICATE)).m_61244_('#', BlockInWorld.m_61169_((Predicate)BlockStatePredicate.m_61287_(Blocks.f_50127_))).m_61249_(); 
/* 126 */     return this.taintedSnowGolemFull;
/*     */   }
/*     */ 
/*     */   
/*     */   private BlockPattern getOrCreateTaintedIronGolemBase() {
/* 131 */     if (this.taintedIronGolemBase == null)
/* 132 */       this.taintedIronGolemBase = BlockPatternBuilder.m_61243_().m_61247_(new String[] { "~ ~", "###", "~#~" }).m_61244_('#', BlockInWorld.m_61169_((Predicate)BlockStatePredicate.m_61287_(Blocks.f_50075_))).m_61244_('~', BlockInWorld.m_61169_(BlockBehaviour.BlockStateBase::m_60795_)).m_61249_(); 
/* 133 */     return this.taintedIronGolemBase;
/*     */   }
/*     */ 
/*     */   
/*     */   private BlockPattern getOrCreateTaintedIronGolemFull() {
/* 138 */     if (this.taintedIronGolemFull == null)
/* 139 */       this.taintedIronGolemFull = BlockPatternBuilder.m_61243_().m_61247_(new String[] { "~^~", "###", "~#~" }).m_61244_('^', BlockInWorld.m_61169_(PUMPKINS_PREDICATE)).m_61244_('#', BlockInWorld.m_61169_((Predicate)BlockStatePredicate.m_61287_(Blocks.f_50075_))).m_61244_('~', BlockInWorld.m_61169_(BlockBehaviour.BlockStateBase::m_60795_)).m_61249_(); 
/* 140 */     return this.taintedIronGolemFull;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\block\TaintedCarvedPumpkinBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
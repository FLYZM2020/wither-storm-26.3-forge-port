/*     */ package nonamecrackers2.witherstormmod.common.item;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.item.ItemEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.BlockItem;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import nonamecrackers2.witherstormmod.common.blockentity.FormidibombBlockEntity;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*     */ 
/*     */ 
/*     */ public class FormidibombItem
/*     */   extends BlockItem
/*     */ {
/*     */   public FormidibombItem(Block block, Item.Properties properties) {
/*  29 */     super(block, properties);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_6883_(ItemStack stack, Level world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
/*  35 */     if (!world.f_46443_) {
/*  36 */       tickFuse(stack, world, entity, entity.m_20183_());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
/*  42 */     tickFuse(stack, entity.m_9236_(), null, entity.m_20183_());
/*  43 */     return super.onEntityItemUpdate(stack, entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tickFuse(ItemStack stack, Level world, @Nullable Entity entity, BlockPos pos) {
/*  48 */     if (!world.f_46443_) {
/*     */       
/*  50 */       if (((Boolean)WitherStormModConfig.SERVER.formidibombFuseEnabled.get()).booleanValue() && getStartFuse(stack) > 0) {
/*  51 */         countFuse(stack, -1);
/*     */       }
/*  53 */       if (getStartFuse(stack) > 0) {
/*     */         
/*  55 */         if (((Boolean)WitherStormModConfig.SERVER.shouldDropFromInventory.get()).booleanValue())
/*     */         {
/*  57 */           if (getFuse(stack) <= getStartFuse(stack) / ((Integer)WitherStormModConfig.SERVER.dropInterval.get()).intValue() && !world.f_46443_) {
/*     */             
/*  59 */             LivingEntity living = null;
/*  60 */             if (entity instanceof LivingEntity)
/*  61 */               living = (LivingEntity)entity; 
/*  62 */             FormidibombEntity formidibomb = new FormidibombEntity(world, pos.m_123341_() + 0.5D, pos.m_123342_(), pos.m_123343_() + 0.5D, living, null, ((Block)WitherStormModBlocks.FORMIDIBOMB.get()).m_49966_());
/*  63 */             formidibomb.m_32085_(getFuse(stack));
/*  64 */             formidibomb.setStartFuse(getStartFuse(stack));
/*  65 */             if (world.m_7967_((Entity)formidibomb)) {
/*     */               
/*  67 */               stack.m_41774_(1);
/*  68 */               world.m_6263_(null, formidibomb.m_20185_(), formidibomb.m_20186_(), formidibomb.m_20189_(), SoundEvents.f_12512_, SoundSource.BLOCKS, 1.0F, 1.0F);
/*     */             } 
/*     */           } 
/*     */         }
/*     */         
/*  73 */         if (getFuse(stack) <= 0) {
/*     */           
/*  75 */           stack.m_41774_(1);
/*  76 */           if (!world.f_46443_) {
/*  77 */             FormidibombEntity.explode(world, entity, 48 + world.f_46441_.m_188503_(9), 3, pos.m_123341_(), pos.m_123342_(), pos.m_123343_());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_7836_(ItemStack stack, Level world, Player entity) {
/*  86 */     setFuse(stack, ((Integer)WitherStormModConfig.SERVER.craftFuseTicks.get()).intValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int m_142158_(ItemStack stack) {
/*  92 */     return Math.round(13.0F - (getStartFuse(stack) - getFuse(stack)) * 13.0F / getStartFuse(stack));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int m_142159_(ItemStack stack) {
/*  98 */     int fuse = getStartFuse(stack) / getFuse(stack);
/*  99 */     int color = (fuse % 2 == 0) ? 12718080 : 10027161;
/* 100 */     return color;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_142522_(ItemStack stack) {
/* 106 */     return (getFuse(stack) > 0 && getFuse(stack) < getStartFuse(stack));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean m_7274_(BlockPos pos, Level world, Player player, ItemStack stack, BlockState state) {
/* 112 */     int fuse = getFuse(stack);
/* 113 */     int startFuse = getStartFuse(stack);
/* 114 */     BlockEntity tile = world.m_7702_(pos);
/* 115 */     if (tile instanceof FormidibombBlockEntity) {
/*     */       
/* 117 */       FormidibombBlockEntity formidibomb = (FormidibombBlockEntity)tile;
/* 118 */       formidibomb.setLifeFuse(fuse);
/* 119 */       formidibomb.setStartFuse(startFuse);
/* 120 */       formidibomb.setFormidibombOwner((LivingEntity)player);
/* 121 */       formidibomb.m_6596_();
/*     */     } 
/* 123 */     return super.m_7274_(pos, world, player, stack, state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
/* 129 */     return slotChanged ? super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFuse(ItemStack stack) {
/* 134 */     return stack.m_41784_().m_128451_("Fuse");
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStartFuse(ItemStack stack) {
/* 139 */     return stack.m_41784_().m_128451_("StartFuse");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void countFuse(ItemStack stack, int amount) {
/* 144 */     CompoundTag compound = stack.m_41784_();
/* 145 */     compound.m_128405_("Fuse", compound.m_128451_("Fuse") + amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFuse(ItemStack stack, int fuse) {
/* 150 */     CompoundTag compound = stack.m_41784_();
/* 151 */     compound.m_128405_("Fuse", fuse);
/* 152 */     compound.m_128405_("StartFuse", fuse);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\FormidibombItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
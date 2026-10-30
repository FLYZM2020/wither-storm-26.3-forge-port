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
/*     */   public void inventoryTick(ItemStack stack, Level world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
/*  35 */     if (!world.isClientSide) {
/*  36 */       tickFuse(stack, world, entity, entity.blockPosition());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
/*  42 */     tickFuse(stack, entity.level(), null, entity.blockPosition());
/*  43 */     return super.onEntityItemUpdate(stack, entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tickFuse(ItemStack stack, Level world, @Nullable Entity entity, BlockPos pos) {
/*  48 */     if (!world.isClientSide) {
/*     */       
/*  50 */       if (((Boolean)WitherStormModConfig.SERVER.formidibombFuseEnabled.get()).booleanValue() && getStartFuse(stack) > 0) {
/*  51 */         countFuse(stack, -1);
/*     */       }
/*  53 */       if (getStartFuse(stack) > 0) {
/*     */         
/*  55 */         if (((Boolean)WitherStormModConfig.SERVER.shouldDropFromInventory.get()).booleanValue())
/*     */         {
/*  57 */           if (getFuse(stack) <= getStartFuse(stack) / ((Integer)WitherStormModConfig.SERVER.dropInterval.get()).intValue() && !world.isClientSide) {
/*     */             
/*  59 */             LivingEntity living = null;
/*  60 */             if (entity instanceof LivingEntity)
/*  61 */               living = (LivingEntity)entity; 
/*  62 */             FormidibombEntity formidibomb = new FormidibombEntity(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, living, null, ((Block)WitherStormModBlocks.FORMIDIBOMB.get()).defaultBlockState());
/*  63 */             formidibomb.setFuse(getFuse(stack));
/*  64 */             formidibomb.setStartFuse(getStartFuse(stack));
/*  65 */             if (world.addFreshEntity((Entity)formidibomb)) {
/*     */               
/*  67 */               stack.shrink(1);
/*  68 */               world.playSound(null, formidibomb.getX(), formidibomb.getY(), formidibomb.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
/*     */             } 
/*     */           } 
/*     */         }
/*     */         
/*  73 */         if (getFuse(stack) <= 0) {
/*     */           
/*  75 */           stack.shrink(1);
/*  76 */           if (!world.isClientSide) {
/*  77 */             FormidibombEntity.explode(world, entity, 48 + world.random.nextInt(9), 3, pos.getX(), pos.getY(), pos.getZ());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onCraftedBy(ItemStack stack, Level world, Player entity) {
/*  86 */     setFuse(stack, ((Integer)WitherStormModConfig.SERVER.craftFuseTicks.get()).intValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBarWidth(ItemStack stack) {
/*  92 */     return Math.round(13.0F - (getStartFuse(stack) - getFuse(stack)) * 13.0F / getStartFuse(stack));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBarColor(ItemStack stack) {
/*  98 */     int fuse = getStartFuse(stack) / getFuse(stack);
/*  99 */     int color = (fuse % 2 == 0) ? 12718080 : 10027161;
/* 100 */     return color;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBarVisible(ItemStack stack) {
/* 106 */     return (getFuse(stack) > 0 && getFuse(stack) < getStartFuse(stack));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean updateCustomBlockEntityTag(BlockPos pos, Level world, Player player, ItemStack stack, BlockState state) {
/* 112 */     int fuse = getFuse(stack);
/* 113 */     int startFuse = getStartFuse(stack);
/* 114 */     BlockEntity tile = world.getBlockEntity(pos);
/* 115 */     if (tile instanceof FormidibombBlockEntity) {
/*     */       
/* 117 */       FormidibombBlockEntity formidibomb = (FormidibombBlockEntity)tile;
/* 118 */       formidibomb.setLifeFuse(fuse);
/* 119 */       formidibomb.setStartFuse(startFuse);
/* 120 */       formidibomb.setFormidibombOwner((LivingEntity)player);
/* 121 */       formidibomb.setChanged();
/*     */     } 
/* 123 */     return super.updateCustomBlockEntityTag(pos, world, player, stack, state);
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
/* 134 */     return stack.getOrCreateTag().getInt("Fuse");
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStartFuse(ItemStack stack) {
/* 139 */     return stack.getOrCreateTag().getInt("StartFuse");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void countFuse(ItemStack stack, int amount) {
/* 144 */     CompoundTag compound = stack.getOrCreateTag();
/* 145 */     compound.putInt("Fuse", compound.getInt("Fuse") + amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFuse(ItemStack stack, int fuse) {
/* 150 */     CompoundTag compound = stack.getOrCreateTag();
/* 151 */     compound.putInt("Fuse", fuse);
/* 152 */     compound.putInt("StartFuse", fuse);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\FormidibombItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
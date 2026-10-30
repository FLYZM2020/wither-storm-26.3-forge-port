/*    */ package nonamecrackers2.witherstormmod.common.entity;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.ParticleTypes;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.sounds.SoundEvents;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.InteractionResult;
/*    */ import net.minecraft.world.effect.MobEffects;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
/*    */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.world.entity.monster.Monster;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.ItemUtils;
/*    */ import net.minecraft.world.item.Items;
/*    */ import net.minecraft.world.item.SuspiciousStewItem;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraftforge.common.IForgeShearable;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class SickenedMushroomCow
/*    */   extends SickenedCow
/*    */   implements IForgeShearable {
/*    */   public SickenedMushroomCow(EntityType<? extends SickenedMushroomCow> type, Level level) {
/* 37 */     super((EntityType)type, level);
/*    */   }
/*    */ 
/*    */   
/*    */   public static AttributeSupplier.Builder createAttributes() {
/* 42 */     return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.3D).add(Attributes.MAX_HEALTH, 26.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public InteractionResult mobInteract(Player player, InteractionHand hand) {
/* 48 */     ItemStack stack = player.getItemInHand(hand);
/* 49 */     if (stack.is(Items.BOWL) && !isBaby()) {
/*    */       
/* 51 */       ItemStack stew = new ItemStack((ItemLike)Items.SUSPICIOUS_STEW);
/* 52 */       SuspiciousStewItem.saveMobEffect(stew, MobEffects.WITHER, 160 + this.random.nextInt(60));
/* 53 */       ItemStack filled = ItemUtils.createFilledResult(stack, player, stew, false);
/* 54 */       player.setItemInHand(hand, filled);
/* 55 */       playSound(SoundEvents.MOOSHROOM_MILK_SUSPICIOUSLY, 1.0F, 1.0F);
/* 56 */       return InteractionResult.sidedSuccess((level()).isClientSide);
/*    */     } 
/*    */ 
/*    */     
/* 60 */     return super.mobInteract(player, hand);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public List<ItemStack> onSheared(@Nullable Player player, @NotNull ItemStack item, Level level, BlockPos pos, int fortune) {
/* 67 */     level().playSound((Player)null, (Entity)this, SoundEvents.MOOSHROOM_SHEAR, (player == null) ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
/* 68 */     if (!(level()).isClientSide) {
/*    */       
/* 70 */       SickenedCow cow = (SickenedCow)((EntityType)WitherStormModEntityTypes.SICKENED_COW.get()).create(level());
/* 71 */       if (cow != null) {
/*    */         
/* 73 */         ((ServerLevel)level()).sendParticles((ParticleOptions)ParticleTypes.EXPLOSION, getX(), getY(0.5D), getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
/* 74 */         discard();
/* 75 */         cow.moveTo(getX(), getY(), getZ(), getYRot(), getXRot());
/* 76 */         cow.setHealth(getHealth());
/* 77 */         cow.yBodyRot = this.yBodyRot;
/* 78 */         if (hasCustomName()) {
/*    */           
/* 80 */           cow.setCustomName(getCustomName());
/* 81 */           cow.setCustomNameVisible(isCustomNameVisible());
/*    */         } 
/* 83 */         if (isPersistenceRequired())
/* 84 */           cow.setPersistenceRequired(); 
/* 85 */         cow.setInvulnerable(isInvulnerable());
/* 86 */         level().addFreshEntity((Entity)cow);
/*    */         
/* 88 */         List<ItemStack> items = Lists.newArrayList();
/* 89 */         for (int i = 0; i < 3; i++) {
/*    */           
/* 91 */           ItemStack drop = new ItemStack((ItemLike)WitherStormModItems.TAINTED_MUSHROOM.get());
/* 92 */           items.add(drop);
/*    */         } 
/* 94 */         return items;
/*    */       } 
/*    */     } 
/* 97 */     return Collections.emptyList();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\SickenedMushroomCow.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
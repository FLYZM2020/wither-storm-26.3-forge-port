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
/*    */   public static AttributeSupplier.Builder m_28307_() {
/* 42 */     return Monster.m_33035_().m_22268_(Attributes.f_22279_, 0.3D).m_22268_(Attributes.f_22276_, 26.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public InteractionResult m_6071_(Player player, InteractionHand hand) {
/* 48 */     ItemStack stack = player.m_21120_(hand);
/* 49 */     if (stack.m_150930_(Items.f_42399_) && !m_6162_()) {
/*    */       
/* 51 */       ItemStack stew = new ItemStack((ItemLike)Items.f_42718_);
/* 52 */       SuspiciousStewItem.m_43258_(stew, MobEffects.f_19615_, 160 + this.f_19796_.m_188503_(60));
/* 53 */       ItemStack filled = ItemUtils.m_41817_(stack, player, stew, false);
/* 54 */       player.m_21008_(hand, filled);
/* 55 */       m_5496_(SoundEvents.f_12074_, 1.0F, 1.0F);
/* 56 */       return InteractionResult.m_19078_((m_9236_()).f_46443_);
/*    */     } 
/*    */ 
/*    */     
/* 60 */     return super.m_6071_(player, hand);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public List<ItemStack> onSheared(@Nullable Player player, @NotNull ItemStack item, Level level, BlockPos pos, int fortune) {
/* 67 */     m_9236_().m_6269_((Player)null, (Entity)this, SoundEvents.f_12075_, (player == null) ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
/* 68 */     if (!(m_9236_()).f_46443_) {
/*    */       
/* 70 */       SickenedCow cow = (SickenedCow)((EntityType)WitherStormModEntityTypes.SICKENED_COW.get()).m_20615_(m_9236_());
/* 71 */       if (cow != null) {
/*    */         
/* 73 */         ((ServerLevel)m_9236_()).m_8767_((ParticleOptions)ParticleTypes.f_123813_, m_20185_(), m_20227_(0.5D), m_20189_(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
/* 74 */         m_146870_();
/* 75 */         cow.m_7678_(m_20185_(), m_20186_(), m_20189_(), m_146908_(), m_146909_());
/* 76 */         cow.m_21153_(m_21223_());
/* 77 */         cow.f_20883_ = this.f_20883_;
/* 78 */         if (m_8077_()) {
/*    */           
/* 80 */           cow.m_6593_(m_7770_());
/* 81 */           cow.m_20340_(m_20151_());
/*    */         } 
/* 83 */         if (m_21532_())
/* 84 */           cow.m_21530_(); 
/* 85 */         cow.m_20331_(m_20147_());
/* 86 */         m_9236_().m_7967_((Entity)cow);
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
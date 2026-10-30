/*    */ package nonamecrackers2.witherstormmod.common.item;
/*    */ 
/*    */ import net.minecraft.sounds.SoundEvents;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.InteractionResult;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.BowlFoodItem;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.Level;
/*    */ import nonamecrackers2.witherstormmod.common.capability.WitherSicknessTracker;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*    */ 
/*    */ public class GoldenAppleStewItem extends BowlFoodItem {
/*    */   public GoldenAppleStewItem(Item.Properties properties) {
/* 18 */     super(properties);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public InteractionResult m_6880_(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
/* 24 */     boolean[] flag = { false };
/* 25 */     entity.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(tracker -> {
/*    */           if (tracker.isInfected() && !tracker.isBeingCured() && !tracker.isActuallyImmune()) {
/*    */             tracker.beginCure();
/*    */             
/*    */             entity.m_9236_().m_6263_(null, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), SoundEvents.f_12644_, SoundSource.NEUTRAL, 1.0F, 1.0F);
/*    */             
/*    */             flag[0] = true;
/*    */           } 
/*    */         });
/* 34 */     if (flag[0]) {
/*    */       
/* 36 */       if (!(player.m_150110_()).f_35937_)
/* 37 */         stack.m_41774_(1); 
/* 38 */       return InteractionResult.SUCCESS;
/*    */     } 
/*    */ 
/*    */     
/* 42 */     return InteractionResult.PASS;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack m_5922_(ItemStack stack, Level world, LivingEntity entity) {
/* 49 */     entity.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(tracker -> {
/*    */           if (tracker.isInfected() && !tracker.isBeingCured() && !tracker.isActuallyImmune()) {
/*    */             tracker.beginCure();
/*    */             
/*    */             entity.m_9236_().m_6263_(null, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), SoundEvents.f_12644_, SoundSource.NEUTRAL, 1.0F, 1.0F);
/*    */           } 
/*    */         });
/*    */     
/* 57 */     return super.m_5922_(stack, world, entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\GoldenAppleStewItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
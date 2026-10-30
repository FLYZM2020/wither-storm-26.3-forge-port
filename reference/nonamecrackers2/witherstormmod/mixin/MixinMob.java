/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.InteractionResult;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.Mob;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.Level;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({Mob.class})
/*    */ public abstract class MixinMob
/*    */   extends LivingEntity
/*    */ {
/*    */   private MixinMob(EntityType<? extends LivingEntity> type, Level level) {
/* 25 */     super(type, level);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"checkAndHandleImportantInteractions"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void checkAndHandleImportantInteractionsHead(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> ci) {
/* 35 */     ItemStack stack = player.m_21120_(hand);
/* 36 */     if (stack.m_41720_() instanceof nonamecrackers2.witherstormmod.common.item.AmuletItem || stack.m_41720_() instanceof nonamecrackers2.witherstormmod.common.item.GoldenAppleStewItem) {
/*    */       
/* 38 */       InteractionResult result = stack.m_41647_(player, this, hand);
/* 39 */       if (result.m_19077_()) {
/* 40 */         ci.setReturnValue(result);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"isSunBurnTick"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void isSunBurnTickHead(CallbackInfoReturnable<Boolean> ci) {
/* 51 */     if (!(m_9236_()).f_46443_) {
/*    */       
/* 53 */       double distance = 200.0D;
/* 54 */       for (WitherStormEntity storm : m_9236_().m_45976_(WitherStormEntity.class, m_20191_().m_82377_(distance, m_9236_().m_141928_(), distance))) {
/*    */         
/* 56 */         if (storm.m_20182_().m_82546_(m_20182_()).m_165924_() <= distance && !storm.isDeadOrPlayingDead() && storm.getPhase() > 5)
/* 57 */           ci.setReturnValue(Boolean.valueOf(false)); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinMob.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
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
/* 35 */     ItemStack stack = player.getItemInHand(hand);
/* 36 */     if (stack.getItem() instanceof nonamecrackers2.witherstormmod.common.item.AmuletItem || stack.getItem() instanceof nonamecrackers2.witherstormmod.common.item.GoldenAppleStewItem) {
/*    */       
/* 38 */       InteractionResult result = stack.interactLivingEntity(player, this, hand);
/* 39 */       if (result.consumesAction()) {
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
/* 51 */     if (!(level()).isClientSide) {
/*    */       
/* 53 */       double distance = 200.0D;
/* 54 */       for (WitherStormEntity storm : level().getEntitiesOfClass(WitherStormEntity.class, getBoundingBox().inflate(distance, level().getHeight(), distance))) {
/*    */         
/* 56 */         if (storm.position().subtract(position()).horizontalDistance() <= distance && !storm.isDeadOrPlayingDead() && storm.getPhase() > 5)
/* 57 */           ci.setReturnValue(Boolean.valueOf(false)); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinMob.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
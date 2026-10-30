/*    */ package nonamecrackers2.witherstormmod.client.audio;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import net.minecraft.client.player.AbstractClientPlayer;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.common.item.FormidiBladeItem;
/*    */ 
/*    */ public class FormidiBladeLoop
/*    */   extends FadingSoundLoop
/*    */   implements IForceStoppableSound {
/*    */   public static final float MIN_POWER = 0.05F;
/*    */   public final AbstractClientPlayer player;
/*    */   
/*    */   public FormidiBladeLoop(AbstractClientPlayer player, SoundEvent event) {
/* 20 */     super(event, SoundSource.AMBIENT);
/* 21 */     this.player = player;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 27 */     super.tick();
/*    */     
/* 29 */     Vec3 view = this.player.getViewVector(1.0F).scale(0.2D);
/* 30 */     this.x = this.player.getX() + view.x;
/* 31 */     this.y = this.player.getEyeY() + view.y;
/* 32 */     this.z = this.player.getZ() + view.z;
/*    */     
/* 34 */     boolean flag = false;
/* 35 */     Optional<Float> power = getPower(this.player);
/* 36 */     if (canPlay(power)) {
/*    */       
/* 38 */       flag = true;
/* 39 */       float powerf = ((Float)power.get()).floatValue();
/* 40 */       this.dampen = (1.0F - powerf) * 50.0F;
/* 41 */       this.pitch = 0.5F + powerf / 2.0F;
/*    */     } 
/* 43 */     if (!this.player.isAlive() || !flag) {
/* 44 */       stopSound();
/*    */     }
/*    */   }
/*    */   
/*    */   public static Optional<Float> getPower(AbstractClientPlayer player) {
/* 49 */     for (InteractionHand hand : InteractionHand.values()) {
/*    */       
/* 51 */       ItemStack stack = player.getItemInHand(hand);
/* 52 */       if (stack.getItem() instanceof FormidiBladeItem) {
/*    */         
/* 54 */         float power = FormidiBladeItem.getPower((LivingEntity)player, stack, false);
/* 55 */         return Optional.of(Float.valueOf(power));
/*    */       } 
/*    */     } 
/* 58 */     return Optional.empty();
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean canPlay(Optional<Float> power) {
/* 63 */     return ((Boolean)power.<Boolean>map(p -> Boolean.valueOf((p.floatValue() > 0.05F))).orElse(Boolean.valueOf(false))).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean canPlay(AbstractClientPlayer player) {
/* 68 */     return canPlay(getPower(player));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void forceStop() {
/* 74 */     stop();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getFadeTime() {
/* 80 */     return 20;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\audio\FormidiBladeLoop.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
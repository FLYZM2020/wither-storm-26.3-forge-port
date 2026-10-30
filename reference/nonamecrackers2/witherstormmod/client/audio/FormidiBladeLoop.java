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
/*    */   public void m_7788_() {
/* 27 */     super.m_7788_();
/*    */     
/* 29 */     Vec3 view = this.player.m_20252_(1.0F).m_82490_(0.2D);
/* 30 */     this.f_119575_ = this.player.m_20185_() + view.f_82479_;
/* 31 */     this.f_119576_ = this.player.m_20188_() + view.f_82480_;
/* 32 */     this.f_119577_ = this.player.m_20189_() + view.f_82481_;
/*    */     
/* 34 */     boolean flag = false;
/* 35 */     Optional<Float> power = getPower(this.player);
/* 36 */     if (canPlay(power)) {
/*    */       
/* 38 */       flag = true;
/* 39 */       float powerf = ((Float)power.get()).floatValue();
/* 40 */       this.dampen = (1.0F - powerf) * 50.0F;
/* 41 */       this.f_119574_ = 0.5F + powerf / 2.0F;
/*    */     } 
/* 43 */     if (!this.player.m_6084_() || !flag) {
/* 44 */       stopSound();
/*    */     }
/*    */   }
/*    */   
/*    */   public static Optional<Float> getPower(AbstractClientPlayer player) {
/* 49 */     for (InteractionHand hand : InteractionHand.values()) {
/*    */       
/* 51 */       ItemStack stack = player.m_21120_(hand);
/* 52 */       if (stack.m_41720_() instanceof FormidiBladeItem) {
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
/* 74 */     m_119609_();
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
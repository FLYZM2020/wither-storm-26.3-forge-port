/*    */ package nonamecrackers2.witherstormmod.client.audio;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.ClientLevel;
/*    */ import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
/*    */ import net.minecraft.client.resources.sounds.SoundInstance;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.entity.BlockEntity;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.FormidibombBlockEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*    */ import nonamecrackers2.witherstormmod.common.util.IFormidibomb;
/*    */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*    */ 
/*    */ public class FormidibombFuseLoop
/*    */   extends AbstractTickableSoundInstance implements IForceStoppableSound {
/*    */   public final IFormidibomb formidibomb;
/*    */   
/*    */   public FormidibombFuseLoop(IFormidibomb formidibomb) {
/* 22 */     super((SoundEvent)WitherStormModSoundEvents.FORMIDIBOMB_PULSE_LOOP.get(), SoundSource.BLOCKS, SoundInstance.m_235150_());
/* 23 */     this.formidibomb = formidibomb;
/* 24 */     this.f_119578_ = true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_7788_() {
/* 30 */     this.f_119573_ = 1.0F + getPercentage() * 2.0F;
/* 31 */     float additionalPitch = (this.formidibomb.getStartFuse() > 0) ? (float)Math.max(0.0D, (120.0D - this.formidibomb.getFuseLife()) / 120.0D) : 0.0F;
/* 32 */     this.f_119574_ = 1.0F + getPercentage() * 0.1F + additionalPitch;
/*    */     
/* 34 */     this.f_119575_ = this.formidibomb.getPosition().m_7096_();
/* 35 */     this.f_119576_ = this.formidibomb.getPosition().m_7098_();
/* 36 */     this.f_119577_ = this.formidibomb.getPosition().m_7094_();
/*    */     
/* 38 */     if (shouldStop()) {
/* 39 */       forceStop();
/*    */     }
/*    */   }
/*    */   
/*    */   private float getPercentage() {
/* 44 */     if (this.formidibomb.getStartFuse() > 0) {
/* 45 */       return (this.formidibomb.getStartFuse() - this.formidibomb.getFuseLife()) / this.formidibomb.getStartFuse();
/*    */     }
/* 47 */     return 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldStop() {
/* 52 */     boolean flag = false;
/* 53 */     if (this.formidibomb instanceof FormidibombBlockEntity) {
/*    */       
/* 55 */       FormidibombBlockEntity tile = (FormidibombBlockEntity)this.formidibomb;
/* 56 */       Minecraft mc = Minecraft.m_91087_();
/* 57 */       ClientLevel world = mc.f_91073_;
/* 58 */       List<BlockEntity> blocks = WorldUtil.getBlockEntitiesInAABB((Level)world, mc.f_91074_.m_20191_().m_82400_(50.0D));
/* 59 */       flag = !blocks.contains(tile);
/*    */     } 
/*    */     
/* 62 */     return (!this.formidibomb.isStillAlive() || flag);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void forceStop() {
/* 68 */     m_119609_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean m_7784_() {
/* 74 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\audio\FormidibombFuseLoop.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
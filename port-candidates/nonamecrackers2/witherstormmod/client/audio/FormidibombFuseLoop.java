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
/* 22 */     super((SoundEvent)WitherStormModSoundEvents.FORMIDIBOMB_PULSE_LOOP.get(), SoundSource.BLOCKS, SoundInstance.createUnseededRandom());
/* 23 */     this.formidibomb = formidibomb;
/* 24 */     this.looping = true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 30 */     this.volume = 1.0F + getPercentage() * 2.0F;
/* 31 */     float additionalPitch = (this.formidibomb.getStartFuse() > 0) ? (float)Math.max(0.0D, (120.0D - this.formidibomb.getFuseLife()) / 120.0D) : 0.0F;
/* 32 */     this.pitch = 1.0F + getPercentage() * 0.1F + additionalPitch;
/*    */     
/* 34 */     this.x = this.formidibomb.getPosition().x();
/* 35 */     this.y = this.formidibomb.getPosition().y();
/* 36 */     this.z = this.formidibomb.getPosition().z();
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
/* 56 */       Minecraft mc = Minecraft.getInstance();
/* 57 */       ClientLevel world = mc.level;
/* 58 */       List<BlockEntity> blocks = WorldUtil.getBlockEntitiesInAABB((Level)world, mc.player.getBoundingBox().inflate(50.0D));
/* 59 */       flag = !blocks.contains(tile);
/*    */     } 
/*    */     
/* 62 */     return (!this.formidibomb.isStillAlive() || flag);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void forceStop() {
/* 68 */     stop();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canStartSilent() {
/* 74 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\audio\FormidibombFuseLoop.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
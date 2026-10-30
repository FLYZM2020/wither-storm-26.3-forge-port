/*    */ package nonamecrackers2.witherstormmod.client.capability;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.player.AbstractClientPlayer;
/*    */ import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.client.audio.EntitySoundManager;
/*    */ import nonamecrackers2.witherstormmod.client.audio.FormidiBladeLoop;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*    */ 
/*    */ public class FormidiBladeLoopManager extends EntitySoundManager<AbstractClientPlayer, FormidiBladeLoop> {
/*    */   public FormidiBladeLoopManager(Minecraft minecraft) {
/* 13 */     super(minecraft, AbstractClientPlayer.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean canPlay(AbstractClientPlayer entity) {
/* 19 */     return (super.canPlay((Entity)entity) && FormidiBladeLoop.canPlay(entity));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean alreadyHasLoop(AbstractClientPlayer entity) {
/* 25 */     return this.loops.stream().anyMatch(l -> (l.player == entity));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected FormidiBladeLoop create(AbstractClientPlayer entity) {
/* 31 */     return new FormidiBladeLoop(entity, (SoundEvent)WitherStormModSoundEvents.FORMIDIBOMB_PULSE_LOOP.get());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected FormidiBladeLoop copyFrom(FormidiBladeLoop loop) {
/* 37 */     return create(loop.player);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\capability\FormidiBladeLoopManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
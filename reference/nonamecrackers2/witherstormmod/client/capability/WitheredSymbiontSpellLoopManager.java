/*    */ package nonamecrackers2.witherstormmod.client.capability;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.client.audio.WitheredSymbiontSpellLoop;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*    */ 
/*    */ public class WitheredSymbiontSpellLoopManager extends EntitySoundManager<WitheredSymbiontEntity, WitheredSymbiontSpellLoop> {
/*    */   public WitheredSymbiontSpellLoopManager(Minecraft minecraft) {
/* 12 */     super(minecraft, WitheredSymbiontEntity.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean canPlay(WitheredSymbiontEntity entity) {
/* 18 */     return (super.canPlay((Entity)entity) && entity.isCastingSpell() && entity.getSpell().spellLoop().isPresent());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean alreadyHasLoop(WitheredSymbiontEntity entity) {
/* 24 */     boolean flag = false;
/* 25 */     for (WitheredSymbiontSpellLoop loop : this.loops) {
/*    */       
/* 27 */       if (loop.entity == entity && loop.spell == entity.getSpell()) {
/*    */         
/* 29 */         flag = true;
/*    */         break;
/*    */       } 
/*    */     } 
/* 33 */     return flag;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected WitheredSymbiontSpellLoop create(WitheredSymbiontEntity entity) {
/* 39 */     return new WitheredSymbiontSpellLoop(entity, ((Supplier<SoundEvent>)entity.getSpell().spellLoop().get()).get());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected WitheredSymbiontSpellLoop copyFrom(WitheredSymbiontSpellLoop loop) {
/* 45 */     return new WitheredSymbiontSpellLoop(loop.entity, ((Supplier<SoundEvent>)loop.spell.spellLoop().get()).get());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\capability\WitheredSymbiontSpellLoopManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
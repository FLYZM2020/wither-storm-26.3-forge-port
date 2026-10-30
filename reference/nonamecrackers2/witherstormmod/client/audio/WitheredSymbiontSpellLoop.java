/*    */ package nonamecrackers2.witherstormmod.client.audio;
/*    */ 
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*    */ 
/*    */ public class WitheredSymbiontSpellLoop
/*    */   extends FadingSoundLoop
/*    */   implements IForceStoppableSound {
/*    */   public final WitheredSymbiontEntity entity;
/*    */   public final SpellType spell;
/*    */   
/*    */   public WitheredSymbiontSpellLoop(WitheredSymbiontEntity entity, SoundEvent event) {
/* 15 */     super(event, SoundSource.AMBIENT);
/* 16 */     this.entity = entity;
/* 17 */     this.spell = entity.getSpell();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_7788_() {
/* 23 */     super.m_7788_();
/*    */     
/* 25 */     this.f_119575_ = this.entity.m_20185_();
/* 26 */     this.f_119576_ = this.entity.m_20186_();
/* 27 */     this.f_119577_ = this.entity.m_20189_();
/*    */     
/* 29 */     if (!this.entity.m_6084_() || !this.entity.isCastingSpell() || this.entity.getSpell() != this.spell) {
/* 30 */       stopSound();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getFadeTime() {
/* 36 */     return 20;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void forceStop() {
/* 42 */     m_119609_();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\audio\WitheredSymbiontSpellLoop.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package nonamecrackers2.witherstormmod.common.blockentity;
/*     */ 
/*     */ import net.minecraft.world.effect.MobEffect;
/*     */ import net.minecraft.world.inventory.ContainerData;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class null
/*     */   implements ContainerData
/*     */ {
/*     */   public void m_8050_(int id, int value) {
/*     */     MobEffect effect;
/*  69 */     switch (id) {
/*     */ 
/*     */       
/*     */       case 0:
/*  73 */         AbstractSuperBeaconBlockEntity.this.beaconLevel = value;
/*     */         break;
/*     */ 
/*     */       
/*     */       case 1:
/*  78 */         effect = MobEffect.m_19453_(value);
/*  79 */         if (effect == null || AbstractSuperBeaconBlockEntity.this.getValidEffects().contains(effect)) {
/*  80 */           AbstractSuperBeaconBlockEntity.this.effect = effect;
/*     */         }
/*     */         break;
/*     */       
/*     */       case 2:
/*  85 */         AbstractSuperBeaconBlockEntity.this.setShowWorkingArea((value == 1));
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/*  90 */         AbstractSuperBeaconBlockEntity.this.effectSetCooldown = value;
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int m_6499_() {
/*  99 */     return 4;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int m_6413_(int id) {
/* 105 */     switch (id) {
/*     */       
/*     */       case 0:
/* 108 */         return AbstractSuperBeaconBlockEntity.this.beaconLevel;
/*     */       case 1:
/* 110 */         return MobEffect.m_19459_(AbstractSuperBeaconBlockEntity.this.effect);
/*     */       case 2:
/* 112 */         return AbstractSuperBeaconBlockEntity.this.showWorkingArea() ? 1 : 0;
/*     */       case 3:
/* 114 */         return AbstractSuperBeaconBlockEntity.this.effectSetCooldown;
/*     */     } 
/* 116 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\blockentity\AbstractSuperBeaconBlockEntity$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
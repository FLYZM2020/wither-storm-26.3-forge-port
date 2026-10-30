/*    */ package nonamecrackers2.witherstormmod.common.blockentity;
/*    */ 
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.inventory.WitheredPhlegmMenu;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class null
/*    */   extends ContainerOpenersCounter
/*    */ {
/*    */   protected void m_142148_(Level level, BlockPos pos, BlockState state, int i, int i2) {}
/*    */   
/*    */   protected void m_142292_(Level level, BlockPos pos, BlockState state) {
/* 49 */     WitheredPhlegmBlockEntity.this.playSound((SoundEvent)WitherStormModSoundEvents.WITHERED_PHLEGM_BLOCK_OPEN.get());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void m_142289_(Level level, BlockPos pos, BlockState state) {
/* 55 */     WitheredPhlegmBlockEntity.this.playSound((SoundEvent)WitherStormModSoundEvents.WITHERED_PHLEGM_BLOCK_CLOSE.get());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean m_142718_(Player player) {
/* 61 */     AbstractContainerMenu abstractContainerMenu = player.f_36096_; if (abstractContainerMenu instanceof WitheredPhlegmMenu) { WitheredPhlegmMenu menu = (WitheredPhlegmMenu)abstractContainerMenu;
/* 62 */       return (menu.getContainer() == WitheredPhlegmBlockEntity.this); }
/*    */     
/* 64 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\blockentity\WitheredPhlegmBlockEntity$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
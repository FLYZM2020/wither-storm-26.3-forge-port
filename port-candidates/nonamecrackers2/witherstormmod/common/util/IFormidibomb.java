/*    */ package nonamecrackers2.witherstormmod.common.util;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ 
/*    */ 
/*    */ public interface IFormidibomb
/*    */ {
/*    */   int getFuseLife();
/*    */   
/*    */   void setLifeFuse(int paramInt);
/*    */   
/*    */   int getStartFuse();
/*    */   
/*    */   void setStartFuse(int paramInt);
/*    */   
/*    */   @Nullable
/*    */   LivingEntity getFormidibombOwner();
/*    */   
/*    */   void setFormidibombOwner(@Nullable LivingEntity paramLivingEntity);
/*    */   
/*    */   Vec3 getPosition();
/*    */   
/*    */   boolean isStillAlive();
/*    */   
/*    */   default void copyFrom(IFormidibomb formidibomb) {
/* 28 */     setLifeFuse(formidibomb.getFuseLife());
/* 29 */     setStartFuse(formidibomb.getStartFuse());
/* 30 */     setFormidibombOwner(formidibomb.getFormidibombOwner());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\IFormidibomb.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
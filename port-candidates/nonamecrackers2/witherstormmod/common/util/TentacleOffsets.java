/*    */ package nonamecrackers2.witherstormmod.common.util;
/*    */ 
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.common.entity.TentacleEntity;
/*    */ 
/*    */ 
/*    */ public class TentacleOffsets
/*    */ {
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   private float xCurl;
/*    */   private float yCurl;
/*    */   private float baseXRot;
/*    */   private float baseYRot;
/*    */   
/*    */   public TentacleOffsets(double x, double y, double z, float xCurl, float yCurl, float baseXRot, float baseYRot) {
/* 20 */     this.x = x;
/* 21 */     this.y = y;
/* 22 */     this.z = z;
/* 23 */     this.xCurl = xCurl;
/* 24 */     this.yCurl = yCurl;
/* 25 */     this.baseXRot = baseXRot;
/* 26 */     this.baseYRot = baseYRot;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getX() {
/* 31 */     return this.x;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getY() {
/* 36 */     return this.y;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getZ() {
/* 41 */     return this.z;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getXCurl() {
/* 46 */     return this.xCurl;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getYCurl() {
/* 51 */     return this.yCurl;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseXRot() {
/* 56 */     return this.baseXRot;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseYRot() {
/* 61 */     return this.baseYRot;
/*    */   }
/*    */ 
/*    */   
/*    */   public void apply(LivingEntity living, TentacleEntity entity) {
/* 66 */     setPos(living, entity);
/* 67 */     entity.setSavedXCurl(getXCurl());
/* 68 */     entity.setSavedYCurl(getYCurl());
/* 69 */     entity.setSavedXOffset(getBaseXRot());
/* 70 */     entity.setSavedYOffset(getBaseYRot() - living.yBodyRot - 90.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPos(LivingEntity living, TentacleEntity entity) {
/* 75 */     float angle = (float)Mth.atan2(getX(), getZ());
/* 76 */     double p = Math.sqrt(getX() * getX() + getZ() * getZ());
/* 77 */     float rot = -living.yBodyRot * 0.017453292F;
/* 78 */     double newXOffset = Mth.cos(rot + angle) * p;
/* 79 */     double newZOffset = Mth.sin(rot + angle) * p;
/* 80 */     Vec3 pos = new Vec3(living.getX() + newXOffset, living.getY() + getY(), living.getZ() + newZOffset);
/* 81 */     entity.setPos(pos.x, pos.y, pos.z);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\TentacleOffsets.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
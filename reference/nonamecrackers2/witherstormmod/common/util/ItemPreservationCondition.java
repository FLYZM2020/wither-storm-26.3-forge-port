/*    */ package nonamecrackers2.witherstormmod.common.util;
/*    */ 
/*    */ import java.util.function.BiFunction;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
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
/*    */ public enum ItemPreservationCondition
/*    */ {
/* 22 */   DISABLED((w, e) -> null, true), CHOMPED((w, e) -> null, true), CHOMPED_OR_KILLED_NEAR_HEAD((w, e) -> null, true), ANY_WITHER_STORM_DEATH((w, e) -> null, true);
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean useDirectEntity;
/*    */ 
/*    */ 
/*    */   
/*    */   ItemPreservationCondition(BiFunction<WitherStormEntity, LivingEntity, Vec3> posGetter, boolean useDirectEntity) {
/* 31 */     this.posGetter = posGetter;
/* 32 */     this.useDirectEntity = useDirectEntity;
/*    */   } private final BiFunction<WitherStormEntity, LivingEntity, Vec3> posGetter; static { ANY_WITHER_STORM_DEATH = new ItemPreservationCondition("ANY_WITHER_STORM_DEATH", 0, (w, e) -> pos(w, e, true), false);
/*    */     CHOMPED_OR_KILLED_NEAR_HEAD = new ItemPreservationCondition("CHOMPED_OR_KILLED_NEAR_HEAD", 1, (w, e) -> pos(w, e, false), false);
/*    */     CHOMPED = new ItemPreservationCondition("CHOMPED", 2, (w, e) -> pos(w, e, false), true); } @Nullable
/*    */   public Vec3 getPos(WitherStormEntity storm, LivingEntity entity) {
/* 37 */     return this.posGetter.apply(storm, entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean useDirectEntity() {
/* 42 */     return this.useDirectEntity;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   private static Vec3 pos(WitherStormEntity storm, LivingEntity entity, boolean entityPosIfTooFarAway) {
/* 47 */     Vec3 closest = null;
/* 48 */     double distance = -1.0D;
/* 49 */     Vec3 eyePos = entity.m_146892_();
/* 50 */     for (int i = 0; i < storm.getTotalHeads(); i++) {
/*    */       
/* 52 */       Vec3 pos = storm.getHeadPos(i);
/* 53 */       double d = pos.m_82554_(eyePos);
/* 54 */       if ((distance == -1.0D || distance > d) && d < 30.0D) {
/*    */         
/* 56 */         distance = d;
/* 57 */         closest = pos;
/*    */       } 
/*    */     } 
/* 60 */     if (entityPosIfTooFarAway && closest == null) {
/* 61 */       return eyePos;
/*    */     }
/* 63 */     return closest;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\ItemPreservationCondition.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
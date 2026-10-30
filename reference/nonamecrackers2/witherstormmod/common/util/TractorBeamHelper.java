/*    */ package nonamecrackers2.witherstormmod.common.util;
/*    */ 
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.level.ClipContext;
/*    */ import net.minecraft.world.phys.BlockHitResult;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;
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
/*    */ public class TractorBeamHelper
/*    */ {
/*    */   public static <T extends LivingEntity & WitherStormBase> Pair<Boolean, Integer> isInsideTractorBeam(Entity target, T entity, double radius) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: invokevirtual m_20182_ : ()Lnet/minecraft/world/phys/Vec3;
/*    */     //   4: aload_1
/*    */     //   5: dload_2
/*    */     //   6: invokestatic isInsideTractorBeam : (Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/entity/LivingEntity;D)Lcom/mojang/datafixers/util/Pair;
/*    */     //   9: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #17	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	10	0	target	Lnet/minecraft/world/entity/Entity;
/*    */     //   0	10	1	entity	Lnet/minecraft/world/entity/LivingEntity;
/*    */     //   0	10	2	radius	D
/*    */     // Local variable type table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	10	1	entity	TT;
/*    */   }
/*    */   
/*    */   public static <T extends LivingEntity & WitherStormBase> Pair<Boolean, Integer> isInsideTractorBeam(Vec3 target, T entity, double radius) {
/*    */     // Byte code:
/*    */     //   0: iconst_0
/*    */     //   1: istore #4
/*    */     //   3: iload #4
/*    */     //   5: aload_1
/*    */     //   6: checkcast nonamecrackers2/witherstormmod/api/common/entity/WitherStormBase
/*    */     //   9: invokeinterface getTotalHeads : ()I
/*    */     //   14: if_icmpge -> 47
/*    */     //   17: aload_0
/*    */     //   18: aload_1
/*    */     //   19: dload_2
/*    */     //   20: iload #4
/*    */     //   22: invokestatic isInsideTractorBeam : (Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/entity/LivingEntity;DI)Z
/*    */     //   25: ifeq -> 41
/*    */     //   28: iconst_1
/*    */     //   29: invokestatic valueOf : (Z)Ljava/lang/Boolean;
/*    */     //   32: iload #4
/*    */     //   34: invokestatic valueOf : (I)Ljava/lang/Integer;
/*    */     //   37: invokestatic of : (Ljava/lang/Object;Ljava/lang/Object;)Lcom/mojang/datafixers/util/Pair;
/*    */     //   40: areturn
/*    */     //   41: iinc #4, 1
/*    */     //   44: goto -> 3
/*    */     //   47: iconst_0
/*    */     //   48: invokestatic valueOf : (Z)Ljava/lang/Boolean;
/*    */     //   51: iconst_m1
/*    */     //   52: invokestatic valueOf : (I)Ljava/lang/Integer;
/*    */     //   55: invokestatic of : (Ljava/lang/Object;Ljava/lang/Object;)Lcom/mojang/datafixers/util/Pair;
/*    */     //   58: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #22	-> 0
/*    */     //   #24	-> 17
/*    */     //   #25	-> 28
/*    */     //   #22	-> 41
/*    */     //   #27	-> 47
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   3	44	4	i	I
/*    */     //   0	59	0	target	Lnet/minecraft/world/phys/Vec3;
/*    */     //   0	59	1	entity	Lnet/minecraft/world/entity/LivingEntity;
/*    */     //   0	59	2	radius	D
/*    */     // Local variable type table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	59	1	entity	TT;
/*    */   }
/*    */   
/*    */   public static <T extends LivingEntity & WitherStormBase> boolean isInsideTractorBeam(Entity target, T entity, double radius, int head) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: invokevirtual m_20182_ : ()Lnet/minecraft/world/phys/Vec3;
/*    */     //   4: aload_1
/*    */     //   5: dload_2
/*    */     //   6: iload #4
/*    */     //   8: invokestatic isInsideTractorBeam : (Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/entity/LivingEntity;DI)Z
/*    */     //   11: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #32	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	12	0	target	Lnet/minecraft/world/entity/Entity;
/*    */     //   0	12	1	entity	Lnet/minecraft/world/entity/LivingEntity;
/*    */     //   0	12	2	radius	D
/*    */     //   0	12	4	head	I
/*    */     // Local variable type table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	12	1	entity	TT;
/*    */   }
/*    */   
/*    */   public static <T extends LivingEntity & WitherStormBase> boolean isInsideTractorBeam(Vec3 target, T entity, double radius, int head) {
/*    */     // Byte code:
/*    */     //   0: aload_1
/*    */     //   1: checkcast nonamecrackers2/witherstormmod/api/common/entity/WitherStormBase
/*    */     //   4: iload #4
/*    */     //   6: invokeinterface tractorBeamActive : (I)Z
/*    */     //   11: ifeq -> 74
/*    */     //   14: aload_0
/*    */     //   15: aload_1
/*    */     //   16: iload #4
/*    */     //   18: invokestatic calculateClosestPoint : (Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/entity/LivingEntity;I)Lnet/minecraft/world/phys/Vec3;
/*    */     //   21: astore #5
/*    */     //   23: aload_0
/*    */     //   24: aload #5
/*    */     //   26: invokevirtual m_82557_ : (Lnet/minecraft/world/phys/Vec3;)D
/*    */     //   29: invokestatic sqrt : (D)D
/*    */     //   32: dstore #6
/*    */     //   34: aload_0
/*    */     //   35: aload_1
/*    */     //   36: checkcast nonamecrackers2/witherstormmod/api/common/entity/WitherStormBase
/*    */     //   39: iload #4
/*    */     //   41: invokeinterface getHeadPos : (I)Lnet/minecraft/world/phys/Vec3;
/*    */     //   46: invokevirtual m_82557_ : (Lnet/minecraft/world/phys/Vec3;)D
/*    */     //   49: invokestatic sqrt : (D)D
/*    */     //   52: dstore #8
/*    */     //   54: dload #6
/*    */     //   56: dload_2
/*    */     //   57: dload #8
/*    */     //   59: ldc2_w 30.0
/*    */     //   62: dadd
/*    */     //   63: ldc2_w 0.014
/*    */     //   66: dmul
/*    */     //   67: dmul
/*    */     //   68: dcmpg
/*    */     //   69: ifgt -> 74
/*    */     //   72: iconst_1
/*    */     //   73: ireturn
/*    */     //   74: iconst_0
/*    */     //   75: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #37	-> 0
/*    */     //   #39	-> 14
/*    */     //   #40	-> 23
/*    */     //   #41	-> 34
/*    */     //   #42	-> 54
/*    */     //   #43	-> 72
/*    */     //   #45	-> 74
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   23	51	5	pos	Lnet/minecraft/world/phys/Vec3;
/*    */     //   34	40	6	distance	D
/*    */     //   54	20	8	distanceFromHead	D
/*    */     //   0	76	0	target	Lnet/minecraft/world/phys/Vec3;
/*    */     //   0	76	1	entity	Lnet/minecraft/world/entity/LivingEntity;
/*    */     //   0	76	2	radius	D
/*    */     //   0	76	4	head	I
/*    */     // Local variable type table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	76	1	entity	TT;
/*    */   }
/*    */   
/*    */   public static <T extends LivingEntity & WitherStormBase> Vec3 calculateClosestPoint(Vec3 target, T entity, int head) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: iload_2
/*    */     //   3: dconst_0
/*    */     //   4: invokestatic calculateClosestPoint : (Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/entity/LivingEntity;ID)Lnet/minecraft/world/phys/Vec3;
/*    */     //   7: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #50	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	target	Lnet/minecraft/world/phys/Vec3;
/*    */     //   0	8	1	entity	Lnet/minecraft/world/entity/LivingEntity;
/*    */     //   0	8	2	head	I
/*    */     // Local variable type table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	8	1	entity	TT;
/*    */   }
/*    */   
/*    */   public static <T extends LivingEntity & WitherStormBase> Vec3 calculateClosestPoint(Vec3 target, T entity, int head, double distanceOffset) {
/* 55 */     float x = ((WitherStormBase)entity).getHeadXRot(head);
/* 56 */     float y = ((WitherStormBase)entity).getHeadYRot(head);
/* 57 */     Vec3 headPos = ((WitherStormBase)entity).getHeadPos(head);
/*    */     
/* 59 */     double cutoff = ((WitherStormBase)entity).getTractorBeamCutoffDistance(head);
/* 60 */     float distanceToHead = (float)(headPos.m_82554_(target) + distanceOffset);
/* 61 */     if (cutoff != -1.0D) {
/* 62 */       distanceToHead = Mth.m_14036_(distanceToHead, 0.0F, (float)cutoff);
/*    */     }
/* 64 */     return headPos.m_82549_(((WitherStormBase)entity).getViewVector(x, y, distanceToHead));
/*    */   }
/*    */ 
/*    */   
/*    */   public static Vec3 calculateClosestPoint(Entity target, LivingEntity entity) {
/* 69 */     Vec3 headPos = entity.m_146892_();
/* 70 */     float distanceToHead = (float)headPos.m_82554_(target.m_20182_());
/* 71 */     Vec3 closest = headPos.m_82549_(getViewVector(entity).m_82490_(distanceToHead));
/* 72 */     BlockHitResult ray = target.m_9236_().m_45547_(new ClipContext(headPos, closest, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, null));
/* 73 */     return ray.m_82450_();
/*    */   }
/*    */ 
/*    */   
/*    */   private static Vec3 getViewVector(LivingEntity entity) {
/* 78 */     float xRot = entity.m_146909_() * 0.017453292F;
/* 79 */     float yRot = -entity.f_20885_ * 0.017453292F;
/* 80 */     float cosY = Mth.m_14089_(yRot);
/* 81 */     float sinY = Mth.m_14031_(yRot);
/* 82 */     float cosX = Mth.m_14089_(xRot);
/* 83 */     float sinX = Mth.m_14031_(xRot);
/* 84 */     return new Vec3((sinY * cosX), -sinX, (cosY * cosX));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\TractorBeamHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
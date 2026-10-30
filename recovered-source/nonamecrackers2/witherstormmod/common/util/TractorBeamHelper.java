package nonamecrackers2.witherstormmod.common.util;

import com.mojang.datafixers.util.Pair;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ClipContext.Block;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;

public class TractorBeamHelper {
   public static <T extends LivingEntity & WitherStormBase> Pair<Boolean, Integer> isInsideTractorBeam(Entity target, T entity, double radius) {
      return isInsideTractorBeam(target.m_20182_(), entity, radius);
   }

   public static <T extends LivingEntity & WitherStormBase> Pair<Boolean, Integer> isInsideTractorBeam(Vec3 target, T entity, double radius) {
      for (int i = 0; i < entity.getTotalHeads(); i++) {
         if (isInsideTractorBeam(target, entity, radius, i)) {
            return Pair.of(true, i);
         }
      }

      return Pair.of(false, -1);
   }

   public static <T extends LivingEntity & WitherStormBase> boolean isInsideTractorBeam(Entity target, T entity, double radius, int head) {
      return isInsideTractorBeam(target.m_20182_(), entity, radius, head);
   }

   public static <T extends LivingEntity & WitherStormBase> boolean isInsideTractorBeam(Vec3 target, T entity, double radius, int head) {
      if (entity.tractorBeamActive(head)) {
         Vec3 pos = calculateClosestPoint(target, entity, head);
         double distance = Math.sqrt(target.m_82557_(pos));
         double distanceFromHead = Math.sqrt(target.m_82557_(entity.getHeadPos(head)));
         if (distance <= radius * ((distanceFromHead + 30.0) * 0.014)) {
            return true;
         }
      }

      return false;
   }

   public static <T extends LivingEntity & WitherStormBase> Vec3 calculateClosestPoint(Vec3 target, T entity, int head) {
      return calculateClosestPoint(target, entity, head, 0.0);
   }

   public static <T extends LivingEntity & WitherStormBase> Vec3 calculateClosestPoint(Vec3 target, T entity, int head, double distanceOffset) {
      float x = entity.getHeadXRot(head);
      float y = entity.getHeadYRot(head);
      Vec3 headPos = entity.getHeadPos(head);
      double cutoff = entity.getTractorBeamCutoffDistance(head);
      float distanceToHead = (float)(headPos.m_82554_(target) + distanceOffset);
      if (cutoff != -1.0) {
         distanceToHead = Mth.m_14036_(distanceToHead, 0.0F, (float)cutoff);
      }

      return headPos.m_82549_(entity.getViewVector(x, y, distanceToHead));
   }

   public static Vec3 calculateClosestPoint(Entity target, LivingEntity entity) {
      Vec3 headPos = entity.m_146892_();
      float distanceToHead = (float)headPos.m_82554_(target.m_20182_());
      Vec3 closest = headPos.m_82549_(getViewVector(entity).m_82490_(distanceToHead));
      BlockHitResult ray = target.m_9236_().m_45547_(new ClipContext(headPos, closest, Block.COLLIDER, Fluid.NONE, null));
      return ray.m_82450_();
   }

   private static Vec3 getViewVector(LivingEntity entity) {
      float xRot = entity.m_146909_() * (float) (Math.PI / 180.0);
      float yRot = -entity.f_20885_ * (float) (Math.PI / 180.0);
      float cosY = Mth.m_14089_(yRot);
      float sinY = Mth.m_14031_(yRot);
      float cosX = Mth.m_14089_(xRot);
      float sinX = Mth.m_14031_(xRot);
      return new Vec3(sinY * cosX, -sinX, cosY * cosX);
   }
}

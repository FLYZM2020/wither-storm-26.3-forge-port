/*    */ package nonamecrackers2.witherstormmod.common.util;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.world.damagesource.DamageSource;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.phys.Vec2;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import net.minecraftforge.event.ForgeEventFactory;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.mixin.MixinLivingEntityAccessor;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PhlegmGravestoneHelper
/*    */ {
/*    */   public static Optional<Vec3> findPotentialPhlegmClusterPos(LivingEntity entity, DamageSource damageSource) {
/* 23 */     if (((Boolean)WitherStormModConfig.SERVER.preserveDropsForAllMobs.get()).booleanValue() || entity instanceof net.minecraft.world.entity.player.Player) {
/*    */       
/* 25 */       ItemPreservationCondition method = (ItemPreservationCondition)WitherStormModConfig.SERVER.itemPreservation.get();
/* 26 */       Entity source = method.useDirectEntity() ? damageSource.getDirectEntity() : damageSource.getEntity();
/* 27 */       if (source instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)source; if (storm.isAlive())
/* 28 */           return Optional.ofNullable(method.getPos(storm, entity));  }
/*    */     
/* 30 */     }  return Optional.empty();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void spawnForEntity(LivingEntity entity, Vec3 pos, List<ItemStack> items) {
/* 35 */     int reward = ForgeEventFactory.getExperienceDrop(entity, ((MixinLivingEntityAccessor)entity).witherstormmod$getLastHurtByPlayer(), entity.getExperienceReward());
/* 36 */     entity.skipDropExperience();
/* 37 */     BlockClusterEntity cluster = ClusterBuilderHelper.buildPhlegmClusterWithItems(entity.level(), entity.getRandom(), items, entity.getDisplayName(), reward);
/* 38 */     if (cluster.getSize() > 0) {
/*    */       
/* 40 */       cluster.setPos(pos);
/* 41 */       cluster.setDeltaMovement(entity.getRandom().nextGaussian() * 0.3D, 0.0D, entity.getRandom().nextGaussian() * 0.3D);
/* 42 */       cluster.setRotationDelta(new Vec2(entity.getRandom().nextInt(20) * 0.3F / 2.0F, entity.getRandom().nextInt(20) * 0.3F / 2.0F));
/* 43 */       entity.level().addFreshEntity((Entity)cluster);
/* 44 */       cluster.setSink(1);
/* 45 */       cluster.setAntiStacking(true);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\PhlegmGravestoneHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
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
/* 26 */       Entity source = method.useDirectEntity() ? damageSource.m_7640_() : damageSource.m_7639_();
/* 27 */       if (source instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)source; if (storm.m_6084_())
/* 28 */           return Optional.ofNullable(method.getPos(storm, entity));  }
/*    */     
/* 30 */     }  return Optional.empty();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void spawnForEntity(LivingEntity entity, Vec3 pos, List<ItemStack> items) {
/* 35 */     int reward = ForgeEventFactory.getExperienceDrop(entity, ((MixinLivingEntityAccessor)entity).witherstormmod$getLastHurtByPlayer(), entity.m_213860_());
/* 36 */     entity.m_217045_();
/* 37 */     BlockClusterEntity cluster = ClusterBuilderHelper.buildPhlegmClusterWithItems(entity.m_9236_(), entity.m_217043_(), items, entity.m_5446_(), reward);
/* 38 */     if (cluster.getSize() > 0) {
/*    */       
/* 40 */       cluster.m_146884_(pos);
/* 41 */       cluster.m_20334_(entity.m_217043_().m_188583_() * 0.3D, 0.0D, entity.m_217043_().m_188583_() * 0.3D);
/* 42 */       cluster.setRotationDelta(new Vec2(entity.m_217043_().m_188503_(20) * 0.3F / 2.0F, entity.m_217043_().m_188503_(20) * 0.3F / 2.0F));
/* 43 */       entity.m_9236_().m_7967_((Entity)cluster);
/* 44 */       cluster.setSink(1);
/* 45 */       cluster.setAntiStacking(true);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\PhlegmGravestoneHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
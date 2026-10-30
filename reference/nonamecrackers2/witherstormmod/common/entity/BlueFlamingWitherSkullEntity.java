/*    */ package nonamecrackers2.witherstormmod.common.entity;
/*    */ 
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.ParticleTypes;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.level.BlockGetter;
/*    */ import net.minecraft.world.level.Explosion;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.level.material.FluidState;
/*    */ import net.minecraftforge.event.ForgeEventFactory;
/*    */ import net.minecraftforge.network.PacketDistributor;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*    */ import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
/*    */ 
/*    */ public class BlueFlamingWitherSkullEntity extends FlamingWitherSkullEntity {
/*    */   public BlueFlamingWitherSkullEntity(EntityType<? extends BlueFlamingWitherSkullEntity> type, Level world) {
/* 25 */     super((EntityType)type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlueFlamingWitherSkullEntity(EntityType<? extends BlueFlamingWitherSkullEntity> type, Level world, LivingEntity owner, double x, double y, double z) {
/* 30 */     super((EntityType)type, world, owner, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlueFlamingWitherSkullEntity(Level world, LivingEntity owner, double x, double y, double z) {
/* 35 */     this((EntityType<? extends BlueFlamingWitherSkullEntity>)WitherStormModEntityTypes.BLUE_FLAMING_WITHER_SKULL.get(), world, owner, x, y, z);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected float m_6884_() {
/* 41 */     return 0.85F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ParticleOptions getParticle() {
/* 47 */     return (ParticleOptions)ParticleTypes.f_123745_;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void explodeAndDiscard() {
/* 53 */     boolean flag = ForgeEventFactory.getMobGriefingEvent(m_9236_(), m_19749_());
/* 54 */     m_5496_((SoundEvent)WitherStormModSoundEvents.FLAMING_SKULL_IMPACT.get(), 6.0F, (this.f_19796_.m_188501_() - this.f_19796_.m_188501_()) * -0.2F + 0.8F);
/* 55 */     WitherStormModPacketHandlers.MAIN.send(PacketDistributor.NEAR.with(PacketDistributor.TargetPoint.p((m_20182_()).f_82479_, (m_20182_()).f_82480_, (m_20182_()).f_82481_, 60.0D, m_9236_().m_46472_())), new ShakeScreenMessage(20.0F, 6.0F));
/* 56 */     m_9236_().m_255391_((Entity)this, m_20185_(), m_20186_(), m_20189_(), (float)(((Double)WitherStormModConfig.SERVER.flamingSkullExplosionSize.get()).doubleValue() + 4.0D), flag, Level.ExplosionInteraction.MOB);
/* 57 */     m_146870_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float m_7077_(Explosion explosion, BlockGetter blockGetter, BlockPos pos, BlockState state, FluidState fluidState, float resistance) {
/* 63 */     float blastResistance = state.getExplosionResistance(blockGetter, pos, explosion);
/* 64 */     if (blastResistance <= 1200.0F)
/* 65 */       return state.canEntityDestroy(blockGetter, pos, (Entity)this) ? Math.min(0.8F, resistance) : resistance; 
/* 66 */     return resistance;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\BlueFlamingWitherSkullEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
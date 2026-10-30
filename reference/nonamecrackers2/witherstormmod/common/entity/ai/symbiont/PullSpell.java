/*    */ package nonamecrackers2.witherstormmod.common.entity.ai.symbiont;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import net.minecraftforge.network.PacketDistributor;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
/*    */ import nonamecrackers2.witherstormmod.common.packet.PlayerMotionMessage;
/*    */ 
/*    */ public class PullSpell
/*    */   extends SymbiontSpell {
/*    */   public PullSpell(WitheredSymbiontEntity symbiont, SpellType type) {
/* 23 */     super(symbiont, type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void cast(LivingEntity target) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void doCasting(LivingEntity target) {
/* 34 */     List<LivingEntity> nearbyTargets = this.entity.getNearbyTargets(WitheredSymbiontEntity.MOB_TARGET_PREDICATE).toList();
/* 35 */     for (LivingEntity entity : nearbyTargets) {
/*    */       
/* 37 */       if (entity.m_6084_() && entity != this.entity) {
/*    */         
/* 39 */         Vec3 delta = this.entity.m_20182_().m_82546_(entity.m_20182_()).m_82541_().m_82490_(0.1D);
/* 40 */         if (entity.m_20270_((Entity)this.entity) < 3.0D) {
/*    */           
/* 42 */           this.entity.breakSpell();
/*    */           
/*    */           return;
/*    */         } 
/* 46 */         delta = new Vec3(delta.m_7096_(), entity.m_20184_().m_7098_(), delta.m_7094_());
/* 47 */         entity.m_20256_(delta);
/* 48 */         if (entity instanceof ServerPlayer) {
/*    */           
/* 50 */           PlayerMotionMessage message = new PlayerMotionMessage(delta);
/* 51 */           WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)entity), message);
/*    */         } 
/*    */         
/* 54 */         double x = entity.m_20185_() + entity.m_217043_().m_188583_() * entity.m_20191_().m_82362_() * 0.4D;
/* 55 */         double y = (entity.m_20191_().m_82399_()).f_82480_ + entity.m_217043_().m_188583_() * entity.m_20191_().m_82376_() * 0.4D;
/* 56 */         double z = entity.m_20189_() + entity.m_217043_().m_188583_() * entity.m_20191_().m_82385_() * 0.4D;
/* 57 */         Vec3 particleDelta = this.entity.m_20299_(1.0F).m_82492_(x, y, z).m_82541_().m_82490_(0.1D);
/* 58 */         ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, 0, particleDelta.m_7096_(), particleDelta.m_7098_(), particleDelta.m_7094_(), 1.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDelay(RandomSource random, float modifier) {
/* 66 */     return Math.max(340, random.m_188503_(420)) - Mth.m_14143_(modifier) * 10;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\symbiont\PullSpell.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
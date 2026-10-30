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
/* 37 */       if (entity.isAlive() && entity != this.entity) {
/*    */         
/* 39 */         Vec3 delta = this.entity.position().subtract(entity.position()).normalize().scale(0.1D);
/* 40 */         if (entity.distanceTo((Entity)this.entity) < 3.0D) {
/*    */           
/* 42 */           this.entity.breakSpell();
/*    */           
/*    */           return;
/*    */         } 
/* 46 */         delta = new Vec3(delta.x(), entity.getDeltaMovement().y(), delta.z());
/* 47 */         entity.setDeltaMovement(delta);
/* 48 */         if (entity instanceof ServerPlayer) {
/*    */           
/* 50 */           PlayerMotionMessage message = new PlayerMotionMessage(delta);
/* 51 */           WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)entity), message);
/*    */         } 
/*    */         
/* 54 */         double x = entity.getX() + entity.getRandom().nextGaussian() * entity.getBoundingBox().getXsize() * 0.4D;
/* 55 */         double y = (entity.getBoundingBox().getCenter()).y + entity.getRandom().nextGaussian() * entity.getBoundingBox().getYsize() * 0.4D;
/* 56 */         double z = entity.getZ() + entity.getRandom().nextGaussian() * entity.getBoundingBox().getZsize() * 0.4D;
/* 57 */         Vec3 particleDelta = this.entity.getEyePosition(1.0F).subtract(x, y, z).normalize().scale(0.1D);
/* 58 */         ((ServerLevel)this.entity.level()).sendParticles((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, 0, particleDelta.x(), particleDelta.y(), particleDelta.z(), 1.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDelay(RandomSource random, float modifier) {
/* 66 */     return Math.max(340, random.nextInt(420)) - Mth.floor(modifier) * 10;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\symbiont\PullSpell.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
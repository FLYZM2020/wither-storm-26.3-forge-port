/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.projectile.FishingHook;
/*    */ import net.minecraft.world.entity.projectile.Projectile;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Redirect;
/*    */ 
/*    */ @Mixin({FishingHook.class})
/*    */ public abstract class MixinFishingHook
/*    */   extends Projectile {
/*    */   private MixinFishingHook(EntityType<? extends Projectile> type, Level level) {
/* 21 */     super(type, level);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Redirect(method = {"pullEntity"}, at = @At(value = "NEW", target = "(DDD)Lnet/minecraft/world/phys/Vec3;"))
/*    */   public Vec3 getPullEntityDelta(double x, double y, double z, Entity toPull) {
/* 30 */     Vec3 vec = new Vec3(x, y, z);
/* 31 */     for (WitherStormEntity storm : m_9236_().m_45976_(WitherStormEntity.class, m_20191_().m_82377_(100.0D, 200.0D, 100.0D))) {
/*    */       
/* 33 */       Pair<Boolean, Integer> result = TractorBeamHelper.isInsideTractorBeam(toPull, (LivingEntity)storm, 4.0D);
/* 34 */       if (!storm.isDeadOrPlayingDead() && ((Boolean)result.getFirst()).booleanValue())
/* 35 */         storm.getIgnoredTargets().addEntityToIgnore(toPull); 
/*    */     } 
/* 37 */     return vec;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinFishingHook.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
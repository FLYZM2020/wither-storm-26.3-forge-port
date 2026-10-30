/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import net.minecraft.sounds.SoundEvents;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.monster.AbstractSkeleton;
/*    */ import net.minecraft.world.entity.monster.Monster;
/*    */ import net.minecraft.world.entity.projectile.AbstractArrow;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
/*    */ 
/*    */ 
/*    */ @Mixin({AbstractSkeleton.class})
/*    */ public abstract class MixinAbstractSkeleton
/*    */   extends Monster
/*    */ {
/*    */   private MixinAbstractSkeleton(EntityType<? extends Monster> type, Level level) {
/* 28 */     super(type, level);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"performRangedAttack"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getX()D", ordinal = 0)}, cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
/*    */   public void performRangedAttackInvoke(LivingEntity entity, float f, CallbackInfo ci, ItemStack stack, AbstractArrow arrow) {
/* 39 */     if (entity instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)entity;
/*    */       
/* 41 */       Pair<Boolean, Integer> pair = TractorBeamHelper.isInsideTractorBeam((Entity)this, (LivingEntity)storm, 4.0D);
/* 42 */       if (((Boolean)pair.getFirst()).booleanValue()) {
/*    */         
/* 44 */         Vec3 pos = storm.getHeadPos(((Integer)pair.getSecond()).intValue());
/* 45 */         double x = pos.m_7096_() - m_20185_();
/* 46 */         double y = pos.m_7098_() - arrow.m_20186_();
/* 47 */         double z = pos.m_7094_() - m_20189_();
/* 48 */         double dist = Math.sqrt(x * x + z * z);
/* 49 */         arrow.m_6686_(x, y + dist * 0.20000000298023224D, z, 1.6F, (14 - m_9236_().m_46791_().m_19028_() * 4));
/* 50 */         m_5496_(SoundEvents.f_12382_, 1.0F, 1.0F / (m_217043_().m_188501_() * 0.4F + 0.8F));
/* 51 */         m_9236_().m_7967_((Entity)arrow);
/* 52 */         ci.cancel();
/*    */       }  }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinAbstractSkeleton.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
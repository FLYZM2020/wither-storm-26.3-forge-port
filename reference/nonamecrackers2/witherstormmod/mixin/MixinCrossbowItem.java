/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.sounds.SoundEvents;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.world.InteractionHand;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.monster.CrossbowAttackMob;
/*    */ import net.minecraft.world.entity.projectile.Projectile;
/*    */ import net.minecraft.world.entity.projectile.ThrownEnderpearl;
/*    */ import net.minecraft.world.item.CrossbowItem;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.item.Items;
/*    */ import net.minecraft.world.item.ProjectileWeaponItem;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ import org.joml.Quaternionf;
/*    */ import org.joml.Quaternionfc;
/*    */ import org.joml.Vector3f;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ @Mixin({CrossbowItem.class})
/*    */ public class MixinCrossbowItem
/*    */ {
/*    */   static {
/* 33 */     HELD = (stack -> (stack.m_41720_() == Items.f_42584_));
/*    */   }
/*    */ 
/*    */   
/*    */   @Unique
/*    */   private static final Predicate<ItemStack> HELD;
/*    */   
/*    */   @Inject(method = {"getSupportedHeldProjectiles"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void getSupportedHeldProjectilesHead(CallbackInfoReturnable<Predicate<ItemStack>> callback) {
/* 42 */     if (((Boolean)WitherStormModConfig.SERVER.crossbowsSupportEnderPearls.get()).booleanValue()) {
/* 43 */       callback.setReturnValue(ProjectileWeaponItem.f_43006_.or(HELD));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"shootProjectile"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private static void shootProjectile(Level world, LivingEntity entity, InteractionHand hand, ItemStack stack, ItemStack projectile, float shotPitch, boolean instabuild, float power, float f, float offset, CallbackInfo callback) {
/* 53 */     if (!world.f_46443_ && ((Boolean)WitherStormModConfig.SERVER.crossbowsSupportEnderPearls.get()).booleanValue())
/*    */     {
/* 55 */       if (projectile.m_41720_() == Items.f_42584_) {
/*    */         
/* 57 */         ThrownEnderpearl thrownEnderpearl = new ThrownEnderpearl(world, entity);
/* 58 */         if (entity instanceof CrossbowAttackMob) { CrossbowAttackMob crossbowUser = (CrossbowAttackMob)entity;
/*    */           
/* 60 */           crossbowUser.m_5811_(crossbowUser.m_5448_(), stack, (Projectile)thrownEnderpearl, offset); }
/*    */         
/*    */         else
/*    */         
/* 64 */         { Vec3 upVector = entity.m_20289_(1.0F);
/* 65 */           Quaternionf quaternion = (new Quaternionf()).setAngleAxis((offset * 0.017453292F), upVector.f_82479_, upVector.f_82480_, upVector.f_82481_);
/* 66 */           Vec3 viewVector = entity.m_20252_(1.0F);
/* 67 */           Vector3f vector3f = viewVector.m_252839_().rotate((Quaternionfc)quaternion);
/* 68 */           thrownEnderpearl.m_6686_(vector3f.x(), vector3f.y(), vector3f.z(), power, f); }
/*    */ 
/*    */         
/* 71 */         stack.m_41622_(3, entity, livingEntity -> livingEntity.m_21190_(hand));
/*    */ 
/*    */         
/* 74 */         world.m_7967_((Entity)thrownEnderpearl);
/* 75 */         world.m_6263_(null, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), SoundEvents.f_11847_, SoundSource.PLAYERS, 1.0F, shotPitch);
/*    */         
/* 77 */         callback.cancel();
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinCrossbowItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
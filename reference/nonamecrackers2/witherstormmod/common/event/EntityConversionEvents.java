/*    */ package nonamecrackers2.witherstormmod.common.event;
/*    */ 
/*    */ import net.minecraft.world.damagesource.DamageSource;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.Mob;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModDamageTypes;
/*    */ import nonamecrackers2.witherstormmod.common.world.tainting.WorldTainting;
/*    */ 
/*    */ 
/*    */ public class EntityConversionEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onLivingDeath(LivingDeathEvent event) {
/* 18 */     Level world = event.getEntity().m_9236_();
/* 19 */     if (!world.f_46443_)
/*    */     {
/* 21 */       if (((Boolean)WitherStormModConfig.SERVER.sickenedMobConversions.get()).booleanValue()) {
/*    */         
/* 23 */         DamageSource source = event.getSource();
/* 24 */         if (source.m_276093_(WitherStormModDamageTypes.WITHER_SICKNESS)) {
/*    */           
/* 26 */           LivingEntity living = event.getEntity();
/* 27 */           if (living instanceof Mob) { Mob mob = (Mob)living;
/*    */             
/* 29 */             if (WorldTainting.getInstance().convertMob(mob, true))
/* 30 */               event.setCanceled(true);  }
/*    */         
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\event\EntityConversionEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
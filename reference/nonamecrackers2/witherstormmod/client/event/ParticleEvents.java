/*    */ package nonamecrackers2.witherstormmod.client.event;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.ClientLevel;
/*    */ import net.minecraft.client.particle.ParticleEngine;
/*    */ import net.minecraft.core.particles.ParticleOptions;
/*    */ import net.minecraft.core.particles.ParticleType;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.item.ItemEntity;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
/*    */ import net.minecraftforge.event.TickEvent;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
/*    */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModItemTags;
/*    */ 
/*    */ public class ParticleEvents
/*    */ {
/*    */   public static void registerFactories(RegisterParticleProvidersEvent event) {
/* 21 */     event.registerSpriteSet((ParticleType)WitherStormModParticleTypes.COMMAND_BLOCK.get(), nonamecrackers2.witherstormmod.client.particle.CommandBlockParticle.Factory::new);
/* 22 */     event.registerSpriteSet((ParticleType)WitherStormModParticleTypes.TRACTOR_BEAM.get(), nonamecrackers2.witherstormmod.client.particle.TractorBeamParticle.Factory::new);
/* 23 */     event.registerSpriteSet((ParticleType)WitherStormModParticleTypes.PHLEGM.get(), nonamecrackers2.witherstormmod.client.particle.PhlegmBlockParticle.Factory::new);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void onClientTick(TickEvent.ClientTickEvent event) {
/* 28 */     Minecraft mc = Minecraft.m_91087_();
/* 29 */     if (event.phase == TickEvent.Phase.START)
/*    */     {
/* 31 */       if (!mc.m_91104_() && mc.f_91073_ != null) {
/*    */         
/* 33 */         ClientLevel world = mc.f_91073_;
/* 34 */         for (Entity entity : world.m_104735_()) {
/*    */           
/* 36 */           if (entity instanceof ItemEntity) {
/*    */             
/* 38 */             ItemEntity item = (ItemEntity)entity;
/* 39 */             if (item.m_32055_().m_150930_((Item)WitherStormModItems.COMMAND_BLOCK_BOOK.get()) || item.m_32055_().m_204117_(WitherStormModItemTags.COMMAND_BLOCK_TOOLS))
/*    */             {
/* 41 */               for (int i = 0; i < 2; i++) {
/*    */                 
/* 43 */                 double x = item.m_20185_() + world.m_213780_().m_188583_() * 0.4D;
/* 44 */                 double y = item.m_20188_() + world.m_213780_().m_188583_() * 0.4D;
/* 45 */                 double z = item.m_20189_() + world.m_213780_().m_188583_() * 0.4D;
/* 46 */                 Vec3 delta = item.m_20299_(1.0F).m_82492_(x, y, z).m_82541_().m_82542_(0.05D, 0.05D, 0.05D);
/* 47 */                 world.m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, delta.m_7096_(), delta.m_7098_(), delta.m_7094_());
/*    */               } 
/*    */             }
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\event\ParticleEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
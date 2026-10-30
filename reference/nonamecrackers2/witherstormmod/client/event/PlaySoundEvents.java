/*    */ package nonamecrackers2.witherstormmod.client.event;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.core.Holder;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraftforge.event.PlayLevelSoundEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*    */ 
/*    */ public class PlaySoundEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onPlaySound(PlayLevelSoundEvent event) {
/* 19 */     if ((event.getLevel()).f_46443_ && ((Boolean)WitherStormModConfig.SERVER.occludeSoundsUnderground.get()).booleanValue()) {
/*    */       
/* 21 */       Holder<SoundEvent> sound = event.getSound();
/* 22 */       LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/* 23 */       if (!WorldUtil.isInAnOpenArea((Entity)player)) {
/*    */         
/* 25 */         float original = event.getOriginalVolume();
/* 26 */         float volume = original * (20.0F - Mth.m_14036_((float)-player.m_20186_() + 40.0F, 0.0F, 20.0F)) / 20.0F * 0.5F;
/* 27 */         if (WitherStormEntity.isOccludedSound((SoundEvent)sound.get()))
/* 28 */           event.setNewVolume(volume); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\event\PlaySoundEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
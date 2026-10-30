/*     */ package nonamecrackers2.witherstormmod.client.capability;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.client.renderer.DimensionSpecialEffects;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*     */ import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
/*     */ import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ import nonamecrackers2.witherstormmod.common.util.BowelsSpecialEffects;
/*     */ 
/*     */ public class BowelsEffectsManager {
/*     */   private final Minecraft minecraft;
/*  22 */   private final Random random = new Random();
/*  23 */   private int nextTremble = 300 + this.random.nextInt(600);
/*  24 */   private int nextScream = 100 + this.random.nextInt(240);
/*     */ 
/*     */   
/*     */   public BowelsEffectsManager(Minecraft minecraft) {
/*  28 */     this.minecraft = minecraft;
/*     */   }
/*     */ 
/*     */   
/*     */   public BowelsEffectsManager() {
/*  33 */     this.minecraft = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  38 */     LocalPlayer player = this.minecraft.f_91074_;
/*  39 */     float timeReduction = 1.0F;
/*     */     
/*  41 */     CommandBlockEntity lowest = null;
/*  42 */     float lowestHealth = -1.0F;
/*  43 */     for (Entity entity : this.minecraft.f_91073_.m_104735_()) {
/*     */       
/*  45 */       if (entity instanceof CommandBlockEntity) {
/*     */         
/*  47 */         CommandBlockEntity commandBlock = (CommandBlockEntity)entity;
/*  48 */         if (lowestHealth == -1.0F || commandBlock.m_21223_() < lowestHealth) {
/*     */           
/*  50 */           lowest = commandBlock;
/*  51 */           lowestHealth = commandBlock.m_21223_();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  56 */     if (lowest != null)
/*     */     {
/*  58 */       if (lowest.m_21223_() < lowest.m_21233_()) {
/*     */         
/*  60 */         timeReduction = Math.max(0.05F, lowest.m_21223_() / lowest.m_21233_() * 0.3F);
/*     */         
/*  62 */         if (this.nextScream > 0) {
/*     */           
/*  64 */           this.nextScream--;
/*  65 */           if (this.nextScream == 0) {
/*     */             
/*  67 */             player.m_6330_((SoundEvent)WitherStormModSoundEvents.BOWELS_LOUD_HURT.get(), SoundSource.AMBIENT, 1.0F, 1.0F);
/*  68 */             this.nextScream = 120 + this.random.nextInt(120);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*  74 */     if (this.nextTremble > 0) {
/*     */       
/*  76 */       this.nextTremble--;
/*  77 */       if (this.nextTremble == 0) {
/*     */         
/*  79 */         float extraShakeStrength = 0.0F;
/*  80 */         if (lowest != null && lowest.m_21223_() < lowest.m_21233_())
/*  81 */           extraShakeStrength = 4.0F; 
/*  82 */         PlayerCameraShaker shaker = (PlayerCameraShaker)player.getCapability(WitherStormModClientCapabilities.CAMERA_SHAKER).orElse(null);
/*  83 */         if (shaker != null)
/*  84 */           shaker.shake(60.0F, 2.0F + extraShakeStrength); 
/*  85 */         player.m_6330_((SoundEvent)WitherStormModSoundEvents.BOWELS_TREMBLE.get(), SoundSource.AMBIENT, 1.0F, 1.0F);
/*  86 */         this.nextTremble = (int)((240 + this.random.nextInt(720)) * timeReduction);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Events
/*     */   {
/*     */     @SubscribeEvent
/*     */     public static void tickAmbience(TickEvent.ClientTickEvent event) {
/*  96 */       Minecraft mc = Minecraft.m_91087_();
/*  97 */       if (event.phase == TickEvent.Phase.END) {
/*     */         
/*  99 */         ClientLevel world = mc.f_91073_;
/* 100 */         if (world != null && !mc.m_91104_())
/*     */         {
/* 102 */           world.getCapability(WitherStormModClientCapabilities.BOWELS_EFFECTS_MANAGER).ifPresent(manager -> manager.tick());
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static void registerSpecialEffects(RegisterDimensionSpecialEffectsEvent event) {
/* 111 */       event.register(WitherStormMod.id("bowels"), (DimensionSpecialEffects)new BowelsSpecialEffects());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\capability\BowelsEffectsManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
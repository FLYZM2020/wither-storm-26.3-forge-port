/*     */ package nonamecrackers2.witherstormmod.client.capability;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.resources.sounds.TickableSoundInstance;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import nonamecrackers2.witherstormmod.client.audio.ISoundManager;
/*     */ import nonamecrackers2.witherstormmod.client.audio.WitherStormSoundLoop;
/*     */ import nonamecrackers2.witherstormmod.client.audio.WitherStormTractorBeamLoop;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ 
/*     */ public class WitherStormLoopingSoundManager implements ISoundManager {
/*     */   private final Minecraft minecraft;
/*  17 */   private final Int2ObjectMap<WitherStormSoundLoop> sounds = (Int2ObjectMap<WitherStormSoundLoop>)new Int2ObjectOpenHashMap();
/*  18 */   private final Int2ObjectMap<WitherStormSoundLoop> pendingReplacements = (Int2ObjectMap<WitherStormSoundLoop>)new Int2ObjectOpenHashMap();
/*  19 */   private final Int2ObjectMap<WitherStormSoundLoop> additional = (Int2ObjectMap<WitherStormSoundLoop>)new Int2ObjectOpenHashMap();
/*  20 */   private final Int2ObjectMap<WitherStormTractorBeamLoop[]> beamLoops = (Int2ObjectMap<WitherStormTractorBeamLoop[]>)new Int2ObjectOpenHashMap();
/*     */ 
/*     */   
/*     */   public WitherStormLoopingSoundManager(Minecraft mc) {
/*  24 */     this.minecraft = mc;
/*     */   }
/*     */ 
/*     */   
/*     */   public WitherStormLoopingSoundManager() {
/*  29 */     this.minecraft = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  35 */     applyPendingReplacements();
/*  36 */     removeAllPendingRemovals();
/*     */   }
/*     */ 
/*     */   
/*     */   private void applyPendingReplacements() {
/*  41 */     ObjectIterator<Int2ObjectMap.Entry<WitherStormSoundLoop>> iterator = this.pendingReplacements.int2ObjectEntrySet().iterator();
/*  42 */     while (iterator.hasNext()) {
/*     */       
/*  44 */       Int2ObjectMap.Entry<WitherStormSoundLoop> entry = (Int2ObjectMap.Entry<WitherStormSoundLoop>)iterator.next();
/*  45 */       ((WitherStormSoundLoop)this.sounds.get(entry.getIntKey())).stopSound();
/*  46 */       WitherStormSoundLoop replacement = (WitherStormSoundLoop)entry.getValue();
/*  47 */       this.sounds.replace(entry.getIntKey(), replacement);
/*  48 */       this.pendingReplacements.remove(entry.getIntKey());
/*  49 */       this.minecraft.m_91106_().m_120372_((TickableSoundInstance)replacement);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void removeAllPendingRemovals() {
/*  55 */     ObjectIterator<Int2ObjectMap.Entry<WitherStormSoundLoop>> iterator = this.sounds.int2ObjectEntrySet().iterator();
/*  56 */     while (iterator.hasNext()) {
/*     */       
/*  58 */       Int2ObjectMap.Entry<WitherStormSoundLoop> entry = (Int2ObjectMap.Entry<WitherStormSoundLoop>)iterator.next();
/*  59 */       WitherStormSoundLoop sound = (WitherStormSoundLoop)entry.getValue();
/*  60 */       if (sound.m_7801_()) {
/*  61 */         iterator.remove();
/*     */       }
/*     */     } 
/*  64 */     ObjectIterator<Int2ObjectMap.Entry<WitherStormTractorBeamLoop[]>> iterator1 = this.beamLoops.int2ObjectEntrySet().iterator();
/*  65 */     while (iterator1.hasNext()) {
/*     */       
/*  67 */       Int2ObjectMap.Entry<WitherStormTractorBeamLoop[]> entry = (Int2ObjectMap.Entry<WitherStormTractorBeamLoop[]>)iterator1.next();
/*  68 */       WitherStormTractorBeamLoop[] loops = (WitherStormTractorBeamLoop[])entry.getValue();
/*  69 */       for (int i = 0; i < loops.length; i++) {
/*     */         
/*  71 */         if (loops[i] != null && loops[i].m_7801_()) {
/*  72 */           loops[i] = null;
/*     */         }
/*     */       } 
/*     */     } 
/*  76 */     ObjectIterator<Int2ObjectMap.Entry<WitherStormSoundLoop>> iterator2 = this.additional.int2ObjectEntrySet().iterator();
/*  77 */     while (iterator2.hasNext()) {
/*     */       
/*  79 */       Int2ObjectMap.Entry<WitherStormSoundLoop> entry = (Int2ObjectMap.Entry<WitherStormSoundLoop>)iterator2.next();
/*  80 */       WitherStormSoundLoop sound = (WitherStormSoundLoop)entry.getValue();
/*  81 */       if (sound.m_7801_()) {
/*  82 */         iterator2.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void putSound(int id, WitherStormSoundLoop sound) {
/*  88 */     if (!this.sounds.containsKey(id)) {
/*     */       
/*  90 */       this.minecraft.m_91106_().m_120372_((TickableSoundInstance)sound);
/*  91 */       this.sounds.put(id, sound);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void putAdditionalSound(int id, WitherStormSoundLoop sound) {
/*  97 */     if (!this.additional.containsKey(id)) {
/*     */       
/*  99 */       this.minecraft.m_91106_().m_120372_((TickableSoundInstance)sound);
/* 100 */       this.additional.put(id, sound);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void refresh() {
/* 106 */     ObjectIterator<Int2ObjectMap.Entry<WitherStormSoundLoop>> iterator = this.sounds.int2ObjectEntrySet().iterator();
/* 107 */     while (iterator.hasNext()) {
/*     */       
/* 109 */       Int2ObjectMap.Entry<WitherStormSoundLoop> entry = (Int2ObjectMap.Entry<WitherStormSoundLoop>)iterator.next();
/* 110 */       WitherStormSoundLoop sound = (WitherStormSoundLoop)entry.getValue();
/* 111 */       sound.forceStop();
/* 112 */       int key = entry.getIntKey();
/* 113 */       this.sounds.remove(entry.getIntKey());
/* 114 */       if (key != 0) {
/* 115 */         putSound(key, new WitherStormSoundLoop(sound.getPos(), sound.getSoundEvent()));
/*     */       }
/*     */     } 
/* 118 */     ObjectIterator<Int2ObjectMap.Entry<WitherStormTractorBeamLoop[]>> iterator1 = this.beamLoops.int2ObjectEntrySet().iterator();
/* 119 */     while (iterator1.hasNext()) {
/*     */       
/* 121 */       Int2ObjectMap.Entry<WitherStormTractorBeamLoop[]> entry = (Int2ObjectMap.Entry<WitherStormTractorBeamLoop[]>)iterator1.next();
/* 122 */       WitherStormTractorBeamLoop[] loops = (WitherStormTractorBeamLoop[])entry.getValue();
/* 123 */       for (int i = 0; i < loops.length; i++) {
/*     */         
/* 125 */         if (loops[i] != null) {
/*     */           
/* 127 */           loops[i].forceStop();
/* 128 */           WitherStormTractorBeamLoop newLoop = new WitherStormTractorBeamLoop((WitherStormEntity)loops[i].getEntity(), loops[i].getHead());
/* 129 */           loops[i] = null;
/* 130 */           putBeamSound(((WitherStormEntity)newLoop.getEntity()).m_19879_(), newLoop.getHead(), newLoop);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 135 */     ObjectIterator<Int2ObjectMap.Entry<WitherStormSoundLoop>> iterator2 = this.additional.int2ObjectEntrySet().iterator();
/* 136 */     while (iterator2.hasNext()) {
/*     */       
/* 138 */       Int2ObjectMap.Entry<WitherStormSoundLoop> entry = (Int2ObjectMap.Entry<WitherStormSoundLoop>)iterator2.next();
/* 139 */       WitherStormSoundLoop sound = (WitherStormSoundLoop)entry.getValue();
/* 140 */       sound.forceStop();
/* 141 */       int key = entry.getIntKey();
/* 142 */       this.additional.remove(entry.getIntKey());
/* 143 */       if (key != 0) {
/* 144 */         putSound(key, new WitherStormSoundLoop(sound.getPos(), sound.getSoundEvent()));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public WitherStormSoundLoop getSound(int id) {
/* 150 */     return (WitherStormSoundLoop)this.sounds.get(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public WitherStormSoundLoop getAdditional(int id) {
/* 155 */     return (WitherStormSoundLoop)this.additional.get(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public WitherStormTractorBeamLoop[] getBeamSound(int id) {
/* 160 */     return (WitherStormTractorBeamLoop[])this.beamLoops.get(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public void putBeamSound(int id, int head, WitherStormTractorBeamLoop loop) {
/* 165 */     WitherStormTractorBeamLoop[] loops = new WitherStormTractorBeamLoop[3];
/* 166 */     if (this.beamLoops.containsKey(id))
/* 167 */       loops = (WitherStormTractorBeamLoop[])this.beamLoops.get(id); 
/* 168 */     loops[head] = loop;
/* 169 */     this.beamLoops.put(id, loops);
/* 170 */     this.minecraft.m_91106_().m_120372_((TickableSoundInstance)loop);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean alreadyHasLoop(int id, int head) {
/* 175 */     if (this.beamLoops.containsKey(id)) {
/*     */       
/* 177 */       WitherStormTractorBeamLoop[] loop = (WitherStormTractorBeamLoop[])this.beamLoops.get(id);
/* 178 */       return (loop[head] != null && !loop[head].m_7801_());
/*     */     } 
/*     */ 
/*     */     
/* 182 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopSound(int id) {
/* 188 */     if (this.sounds.containsKey(id)) {
/* 189 */       ((WitherStormSoundLoop)this.sounds.get(id)).stopSound();
/*     */     }
/*     */   }
/*     */   
/*     */   public void stopAdditional(int id) {
/* 194 */     if (this.additional.containsKey(id)) {
/* 195 */       ((WitherStormSoundLoop)this.additional.get(id)).stopSound();
/*     */     }
/*     */   }
/*     */   
/*     */   public void replace(int id, WitherStormSoundLoop sound) {
/* 200 */     this.pendingReplacements.putIfAbsent(id, sound);
/*     */   }
/*     */ 
/*     */   
/*     */   public WitherStormSoundLoop getReplacement(int id) {
/* 205 */     return (WitherStormSoundLoop)this.pendingReplacements.get(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean alreadyHasReplacement(int id) {
/* 210 */     return this.pendingReplacements.containsKey(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public static SoundEvent getSoundBasedOnDistance(float distance) {
/* 215 */     SoundEvent event = (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_CLOSE_LOOP.get();
/* 216 */     if (distance > 3.0F && distance < 6.0F) {
/* 217 */       event = (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_DISTANT_LOOP.get();
/* 218 */     } else if (distance > 6.0F) {
/* 219 */       event = (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_FAR_LOOP.get();
/* 220 */     }  return event;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\capability\WitherStormLoopingSoundManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
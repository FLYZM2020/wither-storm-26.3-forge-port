/*     */ package nonamecrackers2.witherstormmod.client.capability;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraft.client.resources.sounds.TickableSoundInstance;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import nonamecrackers2.witherstormmod.client.audio.FormidibombFuseLoop;
/*     */ import nonamecrackers2.witherstormmod.client.audio.ISoundManager;
/*     */ import nonamecrackers2.witherstormmod.common.util.IFormidibomb;
/*     */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*     */ 
/*     */ public class FormidibombEffectsManager
/*     */   implements ISoundManager {
/*     */   private final Minecraft minecraft;
/*     */   @Nullable
/*     */   private IFormidibomb formidibomb;
/*  23 */   private final List<FormidibombFuseLoop> loops = Lists.newArrayList();
/*     */ 
/*     */   
/*     */   public FormidibombEffectsManager(Minecraft minecraft) {
/*  27 */     this.minecraft = minecraft;
/*     */   }
/*     */ 
/*     */   
/*     */   public FormidibombEffectsManager() {
/*  32 */     this.minecraft = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  38 */     ClientLevel world = this.minecraft.f_91073_;
/*     */     
/*  40 */     IFormidibomb formidibomb = null;
/*     */     
/*  42 */     for (Entity entity : world.m_104735_()) {
/*     */       
/*  44 */       if (entity instanceof IFormidibomb) {
/*     */         
/*  46 */         IFormidibomb current = (IFormidibomb)entity;
/*     */         
/*  48 */         formidibomb = compare(formidibomb, current);
/*     */         
/*  50 */         if (!alreadyHasLoop(current)) {
/*  51 */           putLoop(new FormidibombFuseLoop(current));
/*     */         }
/*     */       } 
/*     */     } 
/*  55 */     for (BlockEntity tile : WorldUtil.getBlockEntitiesInAABB((Level)world, this.minecraft.f_91074_.m_20191_().m_82400_(50.0D))) {
/*     */       
/*  57 */       if (tile instanceof IFormidibomb) {
/*     */         
/*  59 */         IFormidibomb current = (IFormidibomb)tile;
/*     */         
/*  61 */         formidibomb = compare(formidibomb, current);
/*     */         
/*  63 */         if (!alreadyHasLoop(current)) {
/*  64 */           putLoop(new FormidibombFuseLoop(current));
/*     */         }
/*     */       } 
/*     */     } 
/*  68 */     this.formidibomb = formidibomb;
/*     */     
/*  70 */     for (int i = 0; i < this.loops.size(); i++) {
/*     */       
/*  72 */       FormidibombFuseLoop loop = this.loops.get(i);
/*  73 */       if (loop.m_7801_() || loop.shouldStop()) {
/*  74 */         this.loops.remove(i);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public IFormidibomb compare(@Nullable IFormidibomb previous, IFormidibomb current) {
/*  80 */     if (previous != null) {
/*     */       
/*  82 */       if (current.getStartFuse() > 0) {
/*     */         
/*  84 */         int percentage = (current.getStartFuse() - current.getFuseLife()) / current.getStartFuse();
/*  85 */         if (previous.getStartFuse() > 0) {
/*     */           
/*  87 */           if (percentage > (previous.getStartFuse() - previous.getFuseLife()) / previous.getStartFuse()) {
/*  88 */             return current;
/*     */           }
/*  90 */           return previous;
/*     */         } 
/*     */ 
/*     */         
/*  94 */         return current;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  99 */       return previous;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 104 */     return current;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean alreadyHasLoop(IFormidibomb formidibomb) {
/* 110 */     boolean flag = false;
/* 111 */     for (FormidibombFuseLoop loop : this.loops) {
/*     */       
/* 113 */       if (loop.formidibomb == formidibomb) {
/*     */         
/* 115 */         flag = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 119 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   private void putLoop(FormidibombFuseLoop loop) {
/* 124 */     if (!this.loops.contains(loop)) {
/*     */       
/* 126 */       this.loops.add(loop);
/* 127 */       this.minecraft.m_91106_().m_120372_((TickableSoundInstance)loop);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLife() {
/* 133 */     return (this.formidibomb != null) ? this.formidibomb.getFuseLife() : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStartFuse() {
/* 138 */     return (this.formidibomb != null) ? this.formidibomb.getStartFuse() : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void refresh() {
/* 143 */     List<FormidibombFuseLoop> soundsToAdd = new ArrayList<>();
/* 144 */     for (int i = 0; i < this.loops.size(); i++) {
/*     */       
/* 146 */       FormidibombFuseLoop loop = this.loops.get(i);
/* 147 */       FormidibombFuseLoop newLoop = new FormidibombFuseLoop(loop.formidibomb);
/* 148 */       loop.forceStop();
/* 149 */       soundsToAdd.add(newLoop);
/* 150 */       this.minecraft.m_91106_().m_120372_((TickableSoundInstance)newLoop);
/*     */     } 
/* 152 */     this.loops.clear();
/* 153 */     this.loops.addAll(soundsToAdd);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\capability\FormidibombEffectsManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
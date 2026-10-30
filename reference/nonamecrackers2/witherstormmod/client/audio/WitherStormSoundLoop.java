/*     */ package nonamecrackers2.witherstormmod.client.audio;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.level.ClipContext;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*     */ 
/*     */ public class WitherStormSoundLoop
/*     */   extends FadingSoundLoop
/*     */   implements IForceStoppableSound
/*     */ {
/*     */   public Vec3 pos;
/*     */   public Vec3 prevPos;
/*     */   public final SoundEvent soundevent;
/*     */   protected Optional<WitherStormEntity> entity;
/*     */   
/*     */   public WitherStormSoundLoop(@Nullable WitherStormEntity entity, Vec3 pos, SoundEvent event) {
/*  30 */     super(event, SoundSource.AMBIENT);
/*  31 */     this.pos = pos;
/*  32 */     this.prevPos = pos;
/*  33 */     this.f_119575_ = pos.f_82479_;
/*  34 */     this.f_119576_ = pos.f_82480_;
/*  35 */     this.f_119577_ = pos.f_82481_;
/*  36 */     this.soundevent = event;
/*  37 */     this.entity = Optional.ofNullable(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public WitherStormSoundLoop(@Nonnull WitherStormEntity entity, SoundEvent event) {
/*  42 */     this(entity, entity.m_20182_(), event);
/*     */   }
/*     */ 
/*     */   
/*     */   public WitherStormSoundLoop(Vec3 pos, SoundEvent event) {
/*  47 */     this((WitherStormEntity)null, pos, event);
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3 getPos() {
/*  52 */     return this.pos;
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundEvent getSoundEvent() {
/*  57 */     return this.soundevent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7788_() {
/*  63 */     LocalPlayer player = (Minecraft.m_91087_()).f_91074_;
/*     */     
/*  65 */     double distance = Math.sqrt(player.m_20238_(this.pos));
/*  66 */     if (distance < 1000.0D) {
/*     */       float dampenAmount;
/*     */       
/*  69 */       if (((Boolean)WitherStormModConfig.SERVER.occludeSoundsUnderground.get()).booleanValue()) {
/*     */         
/*  71 */         boolean cave = !WorldUtil.isInAnOpenArea((Entity)player);
/*  72 */         dampenAmount = cave ? (30.0F + Mth.m_14036_((float)-player.m_20186_() + 40.0F, 0.0F, 20.0F)) : 15.0F;
/*     */       }
/*     */       else {
/*     */         
/*  76 */         dampenAmount = 15.0F;
/*     */       } 
/*  78 */       BlockHitResult ray = player.m_9236_().m_45547_(new ClipContext(this.pos, player.m_20182_(), ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, null));
/*  79 */       if (ray.m_6662_() == HitResult.Type.BLOCK && this.dampen < dampenAmount) {
/*  80 */         this.dampen++;
/*  81 */       } else if (ray.m_6662_() == HitResult.Type.BLOCK && this.dampen > dampenAmount) {
/*  82 */         this.dampen--;
/*  83 */       } else if (ray.m_6662_() == HitResult.Type.MISS && this.dampen > 0.0F) {
/*  84 */         this.dampen--;
/*     */       } 
/*     */     } else {
/*     */       
/*  88 */       this.dampen = 0.0F;
/*     */     } 
/*     */     
/*  91 */     this.f_119575_ = this.pos.f_82479_;
/*  92 */     this.f_119576_ = this.pos.f_82480_;
/*  93 */     this.f_119577_ = this.pos.f_82481_;
/*     */     
/*  95 */     this.entity.ifPresent(entity -> {
/*     */           if (entity.m_21224_() || !entity.isAddedToWorld()) {
/*     */             stopSound();
/*     */           }
/*     */         });
/* 100 */     super.m_7788_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void forceStop() {
/* 106 */     m_119609_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getFadeTime() {
/* 112 */     return 40;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\audio\WitherStormSoundLoop.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package nonamecrackers2.witherstormmod.client.audio;
/*     */ 
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
/*     */ import net.minecraft.client.resources.sounds.SoundInstance;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
/*     */ 
/*     */ public class TractorBeamLoop<T extends LivingEntity>
/*     */   extends AbstractTickableSoundInstance
/*     */   implements IForceStoppableSound {
/*  21 */   public static double DISTANCE_REQUIRED = 30.0D;
/*     */   protected final T entity;
/*     */   protected final LocalPlayer player;
/*     */   private double prevX;
/*     */   private double prevY;
/*     */   private double prevZ;
/*     */   @Nullable
/*     */   private final Predicate<T> shouldStop;
/*     */   
/*     */   public TractorBeamLoop(T entity, Predicate<T> shouldStop) {
/*  31 */     super((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_TRACTOR_BEAM.get(), SoundSource.AMBIENT, SoundInstance.m_235150_());
/*  32 */     this.entity = entity;
/*  33 */     Minecraft mc = Minecraft.m_91087_();
/*  34 */     this.player = mc.f_91074_;
/*  35 */     this.f_119578_ = true;
/*  36 */     this.f_119579_ = 0;
/*  37 */     this.f_119573_ = 0.0F;
/*  38 */     this.shouldStop = shouldStop;
/*     */   }
/*     */ 
/*     */   
/*     */   public TractorBeamLoop(T entity) {
/*  43 */     this(entity, (Predicate<T>)null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7788_() {
/*  49 */     this.prevX = this.f_119575_;
/*  50 */     this.prevY = this.f_119576_;
/*  51 */     this.prevZ = this.f_119577_;
/*  52 */     Vec3 closest = calculateClosestPoint();
/*     */     
/*  54 */     if (closest != null) {
/*     */       
/*  56 */       this.f_119575_ = Mth.m_14139_(0.1D, this.prevX, closest.f_82479_);
/*  57 */       this.f_119576_ = Mth.m_14139_(0.1D, this.prevY, closest.f_82480_);
/*  58 */       this.f_119577_ = Mth.m_14139_(0.1D, this.prevZ, closest.f_82481_);
/*     */       
/*  60 */       calculateVolume();
/*     */     } 
/*     */     
/*  63 */     double distance = Math.sqrt(this.player.m_20238_(closest));
/*  64 */     if (shouldStop(distance)) {
/*  65 */       m_119609_();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setPos(Vec3 vec) {
/*  70 */     this.prevX = vec.f_82479_;
/*  71 */     this.prevY = vec.f_82480_;
/*  72 */     this.prevZ = vec.f_82481_;
/*  73 */     this.f_119575_ = vec.f_82479_;
/*  74 */     this.f_119576_ = vec.f_82480_;
/*  75 */     this.f_119577_ = vec.f_82481_;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Vec3 calculateClosestPoint() {
/*  80 */     return TractorBeamHelper.calculateClosestPoint((Entity)this.player, (LivingEntity)this.entity);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getDistance(Vec3 origin) {
/*  85 */     float x = (float)(origin.f_82479_ - this.player.m_20185_());
/*  86 */     float y = (float)(origin.f_82480_ - this.player.m_20186_());
/*  87 */     float z = (float)(origin.f_82481_ - this.player.m_20189_());
/*     */     
/*  89 */     return Mth.m_14116_(x * x + y * y + z * z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void forceStop() {
/*  95 */     m_119609_();
/*     */   }
/*     */ 
/*     */   
/*     */   public T getEntity() {
/* 100 */     return this.entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldStop(double distance) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield entity : Lnet/minecraft/world/entity/LivingEntity;
/*     */     //   4: astore #4
/*     */     //   6: aload #4
/*     */     //   8: instanceof nonamecrackers2/witherstormmod/api/common/entity/WitherStormBase
/*     */     //   11: ifeq -> 32
/*     */     //   14: aload #4
/*     */     //   16: checkcast nonamecrackers2/witherstormmod/api/common/entity/WitherStormBase
/*     */     //   19: astore_3
/*     */     //   20: aload_3
/*     */     //   21: invokeinterface isDeadOrPlayingDead : ()Z
/*     */     //   26: ifeq -> 42
/*     */     //   29: goto -> 83
/*     */     //   32: aload_0
/*     */     //   33: getfield entity : Lnet/minecraft/world/entity/LivingEntity;
/*     */     //   36: invokevirtual m_21224_ : ()Z
/*     */     //   39: ifne -> 83
/*     */     //   42: aload_0
/*     */     //   43: getfield entity : Lnet/minecraft/world/entity/LivingEntity;
/*     */     //   46: invokevirtual m_6084_ : ()Z
/*     */     //   49: ifeq -> 83
/*     */     //   52: dload_1
/*     */     //   53: getstatic nonamecrackers2/witherstormmod/client/audio/TractorBeamLoop.DISTANCE_REQUIRED : D
/*     */     //   56: dcmpl
/*     */     //   57: ifgt -> 83
/*     */     //   60: aload_0
/*     */     //   61: getfield shouldStop : Ljava/util/function/Predicate;
/*     */     //   64: ifnull -> 87
/*     */     //   67: aload_0
/*     */     //   68: getfield shouldStop : Ljava/util/function/Predicate;
/*     */     //   71: aload_0
/*     */     //   72: getfield entity : Lnet/minecraft/world/entity/LivingEntity;
/*     */     //   75: invokeinterface test : (Ljava/lang/Object;)Z
/*     */     //   80: ifeq -> 87
/*     */     //   83: iconst_1
/*     */     //   84: goto -> 88
/*     */     //   87: iconst_0
/*     */     //   88: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #105	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   20	12	3	storm	Lnonamecrackers2/witherstormmod/api/common/entity/WitherStormBase;
/*     */     //   0	89	0	this	Lnonamecrackers2/witherstormmod/client/audio/TractorBeamLoop;
/*     */     //   0	89	1	distance	D
/*     */     // Local variable type table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	89	0	this	Lnonamecrackers2/witherstormmod/client/audio/TractorBeamLoop<TT;>;
/*     */   }
/*     */ 
/*     */   
/*     */   public void calculateVolume() {
/* 110 */     this.f_119573_ = Math.max(0.0F, 0.3F - getDistance(new Vec3(this.f_119575_, this.f_119576_, this.f_119577_)) / 60.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_7784_() {
/* 116 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\audio\TractorBeamLoop.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
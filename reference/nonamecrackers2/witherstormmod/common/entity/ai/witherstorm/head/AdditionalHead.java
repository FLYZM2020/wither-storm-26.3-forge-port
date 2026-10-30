/*     */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.util.HeadConfiguration;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AdditionalHead
/*     */   extends WitherStormHead
/*     */ {
/*     */   @Nullable
/*     */   protected LivingEntity target;
/*     */   public float xRot;
/*     */   public float yRot;
/*     */   public float xRotO;
/*     */   public float yRotO;
/*     */   
/*     */   public AdditionalHead(WitherStormEntity storm, int headIndex) {
/*  25 */     super(storm, headIndex, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundTag save() {
/*  31 */     CompoundTag tag = super.save();
/*  32 */     tag.m_128350_("xRot", this.xRot);
/*  33 */     tag.m_128350_("yRot", this.yRot);
/*  34 */     return tag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void read(CompoundTag tag) {
/*  40 */     super.read(tag);
/*  41 */     if (tag.m_128441_("xRot"))
/*  42 */       this.xRot = tag.m_128457_("xRot"); 
/*  43 */     if (tag.m_128441_("yRot")) {
/*  44 */       this.yRot = tag.m_128457_("yRot");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLookPos(@Nullable Vec3 pos, int steps) {
/*  50 */     setLookSteps(steps);
/*  51 */     this.storm.m_20088_().m_135381_(HeadManager.TARGETS.get(this.headIndex), Optional.ofNullable(pos));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Vec3 getLookPos() {
/*  56 */     return ((Optional<Vec3>)this.storm.m_20088_().m_135370_(HeadManager.TARGETS.get(this.headIndex))).orElse(null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setLookSteps(int steps) {
/*  61 */     this.storm.m_20088_().m_135381_(HeadManager.LOOK_STEPS.get(this.headIndex), Integer.valueOf(steps));
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getLookSteps() {
/*  66 */     return ((Integer)this.storm.m_20088_().m_135370_(HeadManager.LOOK_STEPS.get(this.headIndex))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void baseTick(HeadConfiguration config) {
/*  72 */     super.baseTick(config);
/*  73 */     this.xRotO = this.xRot;
/*  74 */     this.yRotO = this.yRot;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doHeadLookLogic() {
/*  80 */     Vec3 pos = getLookPos();
/*     */     
/*  82 */     if (pos != null) {
/*     */       
/*  84 */       Vec3 headPos = getHeadPos();
/*  85 */       double deltaX = pos.m_7096_() - headPos.f_82479_;
/*  86 */       double deltaY = pos.m_7098_() - headPos.f_82480_;
/*  87 */       double deltaZ = pos.m_7094_() - headPos.f_82481_;
/*  88 */       double d7 = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
/*  89 */       float f = (float)(Mth.m_14136_(deltaZ, deltaX) * 57.2957763671875D) - 90.0F;
/*  90 */       float f1 = (float)-(Mth.m_14136_(deltaY, d7) * 57.2957763671875D);
/*  91 */       lerpHeadTo(f1, f, getLookSteps());
/*     */     }
/*  93 */     else if (!this.storm.isOnDistantRenderer() && !this.storm.isDeadOrPlayingDead()) {
/*     */       
/*  95 */       this.yRot = WitherStormEntity.m_21376_(this.yRot, this.storm.f_20883_, 10.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeadXRot() {
/* 102 */     return this.xRot;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeadXRotO() {
/* 108 */     return this.xRotO;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeadYRot() {
/* 114 */     return this.yRot;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeadYRotO() {
/* 120 */     return this.yRotO;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHeadXRot(float rot) {
/* 126 */     this.xRot = rot;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHeadYRot(float rot) {
/* 132 */     this.yRot = rot;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LivingEntity getTarget() {
/* 138 */     return this.target;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTarget(@Nullable LivingEntity target) {
/* 144 */     this.target = target;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\head\AdditionalHead.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
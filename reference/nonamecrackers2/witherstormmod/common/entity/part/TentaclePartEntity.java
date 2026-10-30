/*     */ package nonamecrackers2.witherstormmod.common.entity.part;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.entity.EntityDimensions;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.Pose;
/*     */ import nonamecrackers2.witherstormmod.common.entity.IMultipartHurtable;
/*     */ 
/*     */ 
/*     */ public class TentaclePartEntity<T extends LivingEntity & IMultipartHurtable<TentaclePartEntity<T>>>
/*     */   extends LinkedPartEntity<T, TentaclePartEntity<T>>
/*     */ {
/*     */   public float xCurl;
/*     */   public float yCurl;
/*     */   public double xOffset;
/*     */   public double yOffset;
/*     */   public double zOffset;
/*     */   public float xRotOffset;
/*     */   public float yRotOffset;
/*     */   private float newWidth;
/*     */   private float newHeight;
/*     */   
/*     */   public TentaclePartEntity(T parent, float width, float height, int segment, float xCurl, float yCurl) {
/*  26 */     super(parent, width, height, segment);
/*  27 */     this.xCurl = xCurl;
/*  28 */     this.yCurl = yCurl;
/*  29 */     this.pushEntities = true;
/*  30 */     this.newWidth = width;
/*  31 */     this.newHeight = height;
/*     */   }
/*     */ 
/*     */   
/*     */   public TentaclePartEntity(T parent, @Nullable TentaclePartEntity<T> linkedChild, float width, float height, int segment, float xCurl, float yCurl) {
/*  36 */     super(parent, linkedChild, width, height, segment);
/*  37 */     this.xCurl = xCurl;
/*  38 */     this.yCurl = yCurl;
/*  39 */     this.pushEntities = true;
/*  40 */     this.newWidth = width;
/*  41 */     this.newHeight = height;
/*     */   }
/*     */ 
/*     */   
/*     */   public TentaclePartEntity<T> setOffset(double x, double y, double z, float xRot, float yRot) {
/*  46 */     this.xOffset = x;
/*  47 */     this.yOffset = y;
/*  48 */     this.zOffset = z;
/*  49 */     this.xRotOffset = xRot;
/*  50 */     this.yRotOffset = yRot;
/*  51 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_8119_() {
/*  57 */     super.m_8119_();
/*     */     
/*  59 */     if (!this.isBase) {
/*     */       
/*  61 */       float rot = m_146909_() * this.xCurl;
/*  62 */       this.newWidth = Math.abs(Mth.m_14031_(rot * 0.017453292F)) * (this.size.f_20377_ - this.size.f_20378_) + this.size.f_20378_;
/*  63 */       this.newHeight = Math.abs(Mth.m_14031_(rot * 0.017453292F)) * (this.size.f_20378_ - this.size.f_20377_) + this.size.f_20377_;
/*     */     } 
/*  65 */     m_6210_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void tickChild() {
/*  71 */     super.tickChild();
/*  72 */     this.linkedChild.m_146926_(m_146909_() * this.xCurl);
/*  73 */     this.linkedChild.m_146922_((m_146908_() - this.yRotOffset) * this.yCurl + this.yRotOffset);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityDimensions m_6972_(Pose pose) {
/*  79 */     return EntityDimensions.m_20395_(this.newWidth, this.newHeight);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6469_(DamageSource source, float damage) {
/*  85 */     if (!m_6673_(source)) {
/*  86 */       return ((IMultipartHurtable)getParent()).hurt(this, source, damage);
/*     */     }
/*  88 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6087_() {
/*  94 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_142391_() {
/* 100 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\part\TentaclePartEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
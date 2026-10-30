/*     */ package nonamecrackers2.witherstormmod.common.entity.part;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityDimensions;
/*     */ import net.minecraft.world.entity.EntitySelector;
/*     */ import net.minecraft.world.entity.Pose;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.entity.PartEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LinkedPartEntity<T extends Entity, P extends LinkedPartEntity<T, P>>
/*     */   extends PartEntity<T>
/*     */ {
/*     */   @Nullable
/*     */   protected final P linkedChild;
/*     */   public final int segment;
/*     */   protected boolean isBase = true;
/*     */   protected final EntityDimensions size;
/*     */   protected boolean pushEntities;
/*     */   
/*     */   public LinkedPartEntity(T parent, @Nullable P linkedChild, float width, float height, int segment) {
/*  27 */     super((Entity)parent);
/*  28 */     this.linkedChild = linkedChild;
/*  29 */     if (this.linkedChild != null)
/*  30 */       ((LinkedPartEntity)this.linkedChild).isBase = false; 
/*  31 */     this.size = EntityDimensions.scalable(width, height);
/*  32 */     refreshDimensions();
/*  33 */     this.segment = segment;
/*     */   }
/*     */ 
/*     */   
/*     */   public LinkedPartEntity(T parent, float width, float height, int segment) {
/*  38 */     this(parent, (P)null, width, height, segment);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  44 */     super.tick();
/*     */     
/*  46 */     if (this.pushEntities) {
/*     */       
/*  48 */       List<Entity> list = level().getEntities(getParent(), getBoundingBox(), EntitySelector.pushableBy(getParent()));
/*  49 */       if (!list.isEmpty())
/*     */       {
/*  51 */         for (Entity entity : list) {
/*     */           
/*  53 */           if (!(entity instanceof PartEntity)) {
/*  54 */             entity.push((Entity)this);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*  59 */     if (this.linkedChild != null) {
/*  60 */       tickChild();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void tickChild() {
/*  65 */     Vec3 look = getDeltaLinkPos();
/*  66 */     this.linkedChild.tickAndO();
/*  67 */     this.linkedChild.setPos(look.x + getX(), look.y + getY(), look.z + getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   protected Vec3 getDeltaLinkPos() {
/*  72 */     return getLookAngle().multiply(getBbWidth(), getBbHeight(), getBbWidth());
/*     */   }
/*     */ 
/*     */   
/*     */   public void tickAndO() {
/*  77 */     setOldPosAndRot();
/*  78 */     this.tickCount++;
/*  79 */     tick();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void defineSynchedData() {}
/*     */ 
/*     */   
/*     */   protected void readAdditionalSaveData(CompoundTag compound) {}
/*     */ 
/*     */   
/*     */   protected void addAdditionalSaveData(CompoundTag compound) {}
/*     */   
/*     */   @Nullable
/*     */   public P getLinkedChild() {
/*  93 */     return this.linkedChild;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<P> getChained() {
/*  98 */     P current = this.linkedChild;
/*  99 */     List<P> chained = Lists.newArrayList();
/* 100 */     while (current != null) {
/*     */       
/* 102 */       chained.add(current);
/* 103 */       current = (P)current.getLinkedChild();
/*     */     } 
/* 105 */     return chained;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public P getSegment(int segment) {
/* 110 */     for (LinkedPartEntity linkedPartEntity : getChained()) {
/*     */       
/* 112 */       if (linkedPartEntity.segment == segment)
/* 113 */         return (P)linkedPartEntity; 
/*     */     } 
/* 115 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public P getLast() {
/* 120 */     List<P> chained = getChained();
/* 121 */     return chained.get(chained.size() - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean is(Entity entity) {
/* 127 */     return (this == entity || getParent() == entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityDimensions getDimensions(Pose pose) {
/* 133 */     return this.size;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\part\LinkedPartEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
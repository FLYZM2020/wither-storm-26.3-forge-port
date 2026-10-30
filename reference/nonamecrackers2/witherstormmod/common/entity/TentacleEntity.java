/*      */ package nonamecrackers2.witherstormmod.common.entity;
/*      */ 
/*      */ import com.google.common.collect.Lists;
/*      */ import java.util.Collections;
/*      */ import java.util.EnumSet;
/*      */ import java.util.List;
/*      */ import java.util.Optional;
/*      */ import javax.annotation.Nullable;
/*      */ import net.minecraft.core.BlockPos;
/*      */ import net.minecraft.core.particles.ParticleOptions;
/*      */ import net.minecraft.core.particles.ParticleTypes;
/*      */ import net.minecraft.nbt.CompoundTag;
/*      */ import net.minecraft.network.FriendlyByteBuf;
/*      */ import net.minecraft.network.protocol.Packet;
/*      */ import net.minecraft.network.protocol.game.ClientGamePacketListener;
/*      */ import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
/*      */ import net.minecraft.network.syncher.EntityDataAccessor;
/*      */ import net.minecraft.network.syncher.EntityDataSerializers;
/*      */ import net.minecraft.network.syncher.SynchedEntityData;
/*      */ import net.minecraft.server.level.ServerLevel;
/*      */ import net.minecraft.sounds.SoundEvent;
/*      */ import net.minecraft.tags.DamageTypeTags;
/*      */ import net.minecraft.util.Mth;
/*      */ import net.minecraft.world.DifficultyInstance;
/*      */ import net.minecraft.world.damagesource.DamageSource;
/*      */ import net.minecraft.world.effect.MobEffectInstance;
/*      */ import net.minecraft.world.entity.Entity;
/*      */ import net.minecraft.world.entity.EntityDimensions;
/*      */ import net.minecraft.world.entity.EntityType;
/*      */ import net.minecraft.world.entity.LivingEntity;
/*      */ import net.minecraft.world.entity.Mob;
/*      */ import net.minecraft.world.entity.MobSpawnType;
/*      */ import net.minecraft.world.entity.MobType;
/*      */ import net.minecraft.world.entity.Pose;
/*      */ import net.minecraft.world.entity.SpawnGroupData;
/*      */ import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
/*      */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*      */ import net.minecraft.world.entity.ai.control.BodyRotationControl;
/*      */ import net.minecraft.world.entity.ai.control.LookControl;
/*      */ import net.minecraft.world.entity.ai.goal.Goal;
/*      */ import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
/*      */ import net.minecraft.world.entity.animal.Animal;
/*      */ import net.minecraft.world.entity.monster.Monster;
/*      */ import net.minecraft.world.entity.player.Player;
/*      */ import net.minecraft.world.item.Items;
/*      */ import net.minecraft.world.level.Level;
/*      */ import net.minecraft.world.level.ServerLevelAccessor;
/*      */ import net.minecraft.world.level.material.PushReaction;
/*      */ import net.minecraft.world.phys.AABB;
/*      */ import net.minecraft.world.phys.Vec3;
/*      */ import net.minecraftforge.api.distmarker.Dist;
/*      */ import net.minecraftforge.common.util.LogicalSidedProvider;
/*      */ import net.minecraftforge.entity.PartEntity;
/*      */ import net.minecraftforge.fml.DistExecutor;
/*      */ import net.minecraftforge.network.NetworkEvent;
/*      */ import net.minecraftforge.network.PacketDistributor;
/*      */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*      */ import nonamecrackers2.witherstormmod.common.entity.part.TentaclePartEntity;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*      */ import nonamecrackers2.witherstormmod.common.util.ConditionalLookController;
/*      */ import nonamecrackers2.witherstormmod.common.util.EmptyBodyController;
/*      */ import nonamecrackers2.witherstormmod.common.world.tainting.WorldTainting;
/*      */ import org.jetbrains.annotations.NotNull;
/*      */ 
/*      */ public class TentacleEntity
/*      */   extends Monster
/*      */   implements IMultipartHurtable<TentaclePartEntity<TentacleEntity>>
/*      */ {
/*   71 */   private static final EntityDataAccessor<Boolean> DORMANT = SynchedEntityData.m_135353_(TentacleEntity.class, EntityDataSerializers.f_135035_);
/*   72 */   private static final EntityDataAccessor<Integer> ANIMATION_OFFSET = SynchedEntityData.m_135353_(TentacleEntity.class, EntityDataSerializers.f_135028_);
/*   73 */   private static final EntityDataAccessor<Float> XOFFSET = SynchedEntityData.m_135353_(TentacleEntity.class, EntityDataSerializers.f_135029_);
/*   74 */   private static final EntityDataAccessor<Float> YOFFSET = SynchedEntityData.m_135353_(TentacleEntity.class, EntityDataSerializers.f_135029_);
/*   75 */   private static final EntityDataAccessor<Integer> OFFSETSTEPS = SynchedEntityData.m_135353_(TentacleEntity.class, EntityDataSerializers.f_135028_);
/*   76 */   private static final EntityDataAccessor<Boolean> SHOULDWRAPY = SynchedEntityData.m_135353_(TentacleEntity.class, EntityDataSerializers.f_135035_);
/*   77 */   private static final EntityDataAccessor<Float> XOFFSETANIM = SynchedEntityData.m_135353_(TentacleEntity.class, EntityDataSerializers.f_135029_);
/*   78 */   private static final EntityDataAccessor<Float> YOFFSETANIM = SynchedEntityData.m_135353_(TentacleEntity.class, EntityDataSerializers.f_135029_);
/*   79 */   private static final EntityDataAccessor<Float> XCURL = SynchedEntityData.m_135353_(TentacleEntity.class, EntityDataSerializers.f_135029_);
/*   80 */   private static final EntityDataAccessor<Float> YCURL = SynchedEntityData.m_135353_(TentacleEntity.class, EntityDataSerializers.f_135029_);
/*   81 */   private static final EntityDataAccessor<Integer> CURLSTEPS = SynchedEntityData.m_135353_(TentacleEntity.class, EntityDataSerializers.f_135028_);
/*   82 */   private static final EntityDataAccessor<Float> XCURLANIM = SynchedEntityData.m_135353_(TentacleEntity.class, EntityDataSerializers.f_135029_);
/*   83 */   private static final EntityDataAccessor<Float> YCURLANIM = SynchedEntityData.m_135353_(TentacleEntity.class, EntityDataSerializers.f_135029_);
/*   84 */   private static final EntityDataAccessor<Float> LASTXCURLANIM = SynchedEntityData.m_135353_(TentacleEntity.class, EntityDataSerializers.f_135029_);
/*      */   private float xCurlAnim;
/*      */   private float yCurlAnim;
/*      */   private final TentaclePartEntity<TentacleEntity> tentacle;
/*      */   private final TentaclePartEntity<TentacleEntity> last;
/*      */   private final TentaclePartEntity<TentacleEntity> secondLast;
/*      */   private int tentacleAnim;
/*      */   private float xRotOffsetAnim;
/*      */   private float yRotOffsetAnim;
/*      */   private int strangleTime;
/*   94 */   private float tentacleAnimSpeed = 1.0F;
/*   95 */   private float tentacleAnimReach = 1.0F;
/*      */   
/*      */   private int tentacleSwingTime;
/*      */   private int nextSwing;
/*      */   private int knockbackWait;
/*      */   private int awakeTime;
/*      */   private boolean canStrangle = true;
/*      */   private boolean canSwing = true;
/*      */   private StrangleGoal strangleGoal;
/*      */   private SwingGoal swingGoal;
/*      */   
/*      */   public TentacleEntity(EntityType<? extends TentacleEntity> type, Level world) {
/*  107 */     super(type, world);
/*  108 */     this.tentacle = new TentaclePartEntity((LivingEntity)this, new TentaclePartEntity((LivingEntity)this, new TentaclePartEntity((LivingEntity)this, new TentaclePartEntity((LivingEntity)this, new TentaclePartEntity((LivingEntity)this, 1.5F, 3.0F, 4, 1.5F, 1.0F), 1.5F, 3.0F, 3, 1.5F, 1.0F), 1.5F, 2.5F, 2, 1.5F, 1.0F), 1.5F, 2.0F, 1, 1.5F, 1.0F), 1.5F, 1.5F, 0, 1.5F, 1.0F);
/*  109 */     this.secondLast = (TentaclePartEntity<TentacleEntity>)this.tentacle.getSegment(3);
/*  110 */     this.last = (TentaclePartEntity<TentacleEntity>)this.tentacle.getLast();
/*  111 */     this.f_21365_ = (LookControl)new ConditionalLookController((Mob)this, entity -> false);
/*  112 */     this.f_19850_ = false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected BodyRotationControl m_7560_() {
/*  118 */     return (BodyRotationControl)new EmptyBodyController((Mob)this);
/*      */   }
/*      */ 
/*      */   
/*      */   public static AttributeSupplier.Builder createAttributes() {
/*  123 */     return Monster.m_33035_().m_22268_(Attributes.f_22276_, 80.0D).m_22268_(Attributes.f_22277_, 8.0D).m_22268_(Attributes.f_22279_, 0.0D).m_22268_(Attributes.f_22284_, 12.0D).m_22268_(Attributes.f_22282_, 1.5D);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void m_8099_() {
/*  129 */     this.f_21345_.m_25352_(0, new DoNothingGoal(this));
/*  130 */     this.swingGoal = new SwingGoal(this);
/*  131 */     this.f_21345_.m_25352_(1, this.swingGoal);
/*  132 */     this.strangleGoal = new StrangleGoal(this);
/*  133 */     this.f_21345_.m_25352_(2, this.strangleGoal);
/*  134 */     this.f_21346_.m_25352_(0, (Goal)new TargetGoal<>((Mob)this, Player.class, true, true));
/*  135 */     this.f_21346_.m_25352_(1, (Goal)new TargetGoal<>((Mob)this, Animal.class, true, true));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void m_8097_() {
/*  141 */     super.m_8097_();
/*  142 */     this.f_19804_.m_135372_(DORMANT, Boolean.valueOf(false));
/*  143 */     this.f_19804_.m_135372_(ANIMATION_OFFSET, Integer.valueOf(0));
/*  144 */     this.f_19804_.m_135372_(XOFFSET, Float.valueOf(20.0F));
/*  145 */     this.f_19804_.m_135372_(YOFFSET, Float.valueOf(0.0F));
/*  146 */     this.f_19804_.m_135372_(OFFSETSTEPS, Integer.valueOf(0));
/*  147 */     this.f_19804_.m_135372_(SHOULDWRAPY, Boolean.valueOf(true));
/*  148 */     this.f_19804_.m_135372_(XOFFSETANIM, Float.valueOf(0.0F));
/*  149 */     this.f_19804_.m_135372_(YOFFSETANIM, Float.valueOf(0.0F));
/*  150 */     this.f_19804_.m_135372_(XCURL, Float.valueOf(1.3F));
/*  151 */     this.f_19804_.m_135372_(YCURL, Float.valueOf(1.0F));
/*  152 */     this.f_19804_.m_135372_(CURLSTEPS, Integer.valueOf(0));
/*  153 */     this.f_19804_.m_135372_(XCURLANIM, Float.valueOf(0.0F));
/*  154 */     this.f_19804_.m_135372_(YCURLANIM, Float.valueOf(0.0F));
/*  155 */     this.f_19804_.m_135372_(LASTXCURLANIM, Float.valueOf(0.0F));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_7378_(CompoundTag compound) {
/*  161 */     super.m_7378_(compound);
/*  162 */     if (compound.m_128441_("Dormant"))
/*  163 */       setDormant(compound.m_128471_("Dormant")); 
/*  164 */     if (compound.m_128441_("AnimOffset"))
/*  165 */       this.f_19804_.m_135381_(ANIMATION_OFFSET, Integer.valueOf(compound.m_128451_("AnimOffset"))); 
/*  166 */     if (compound.m_128441_("XOffset")) {
/*      */       
/*  168 */       float offset = compound.m_128457_("XOffset");
/*  169 */       this.f_19804_.m_135381_(XOFFSET, Float.valueOf(offset));
/*  170 */       setXOffset(offset);
/*      */     } 
/*  172 */     if (compound.m_128441_("YOffset")) {
/*      */       
/*  174 */       float offset = compound.m_128457_("YOffset");
/*  175 */       this.f_19804_.m_135381_(YOFFSET, Float.valueOf(offset));
/*  176 */       setYOffset(offset);
/*      */     } 
/*  178 */     if (compound.m_128441_("XCurl")) {
/*      */       
/*  180 */       float xCurl = compound.m_128457_("XCurl");
/*  181 */       this.f_19804_.m_135381_(XCURL, Float.valueOf(xCurl));
/*  182 */       setXCurl(xCurl);
/*      */     } 
/*  184 */     if (compound.m_128441_("YCurl")) {
/*      */       
/*  186 */       float yCurl = compound.m_128457_("YCurl");
/*  187 */       this.f_19804_.m_135381_(YCURL, Float.valueOf(yCurl));
/*  188 */       setYCurl(yCurl);
/*      */     } 
/*  190 */     this.nextSwing = compound.m_128451_("NextSwing");
/*  191 */     if (compound.m_128441_("CanStrangle"))
/*  192 */       setCanStrangle(compound.m_128471_("CanStrangle")); 
/*  193 */     if (compound.m_128441_("CanSwing"))
/*  194 */       setCanSwing(compound.m_128471_("CanSwing")); 
/*  195 */     if (compound.m_128441_("XCurlAnim"))
/*  196 */       lerpCurlXTo(compound.m_128457_("XCurlAnim"), 1); 
/*  197 */     if (compound.m_128441_("YCurlAnim"))
/*  198 */       lerpCurlYTo(compound.m_128457_("YCurlAnim"), 1); 
/*  199 */     if (compound.m_128441_("XOffsetAnim"))
/*  200 */       lerpBaseXTo(compound.m_128457_("XOffsetAnim"), 1); 
/*  201 */     if (compound.m_128441_("YOffsetAnim")) {
/*  202 */       lerpBaseYTo(compound.m_128457_("YOffsetAnim"), 1, true);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void m_7380_(CompoundTag compound) {
/*  208 */     super.m_7380_(compound);
/*  209 */     compound.m_128379_("Dormant", isDormant());
/*  210 */     compound.m_128405_("AnimOffset", ((Integer)this.f_19804_.m_135370_(ANIMATION_OFFSET)).intValue());
/*  211 */     compound.m_128350_("XOffset", ((Float)this.f_19804_.m_135370_(XOFFSET)).floatValue());
/*  212 */     compound.m_128350_("YOffset", ((Float)this.f_19804_.m_135370_(YOFFSET)).floatValue());
/*  213 */     compound.m_128350_("XCurl", ((Float)this.f_19804_.m_135370_(XCURL)).floatValue());
/*  214 */     compound.m_128350_("YCurl", ((Float)this.f_19804_.m_135370_(YCURL)).floatValue());
/*  215 */     compound.m_128405_("NextSwing", this.nextSwing);
/*  216 */     compound.m_128379_("CanStrangle", this.canStrangle);
/*  217 */     compound.m_128379_("CanSwing", this.canSwing);
/*  218 */     compound.m_128350_("XCurlAnim", ((Float)this.f_19804_.m_135370_(XCURLANIM)).floatValue());
/*  219 */     compound.m_128350_("YCurlAnim", ((Float)this.f_19804_.m_135370_(YCURLANIM)).floatValue());
/*  220 */     compound.m_128350_("XOffsetAnim", ((Float)this.f_19804_.m_135370_(XOFFSETANIM)).floatValue());
/*  221 */     compound.m_128350_("YOffsetAnim", ((Float)this.f_19804_.m_135370_(YOFFSETANIM)).floatValue());
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDormant(boolean dormant) {
/*  226 */     this.f_19804_.m_135381_(DORMANT, Boolean.valueOf(dormant));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isDormant() {
/*  231 */     return ((Boolean)this.f_19804_.m_135370_(DORMANT)).booleanValue();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_8107_() {
/*  237 */     super.m_8107_();
/*  238 */     int offsetSteps = ((Integer)this.f_19804_.m_135370_(OFFSETSTEPS)).intValue();
/*  239 */     if (offsetSteps > 0) {
/*      */       
/*  241 */       this.xRotOffsetAnim += (float)Mth.m_14175_(((Float)this.f_19804_.m_135370_(XOFFSETANIM)).floatValue() - this.xRotOffsetAnim) / offsetSteps;
/*  242 */       this.yRotOffsetAnim += (float)(((Float)this.f_19804_.m_135370_(YOFFSETANIM)).floatValue() - this.yRotOffsetAnim) / offsetSteps;
/*      */     } 
/*  244 */     setXOffset(this.xRotOffsetAnim + getXOffset());
/*  245 */     setYOffset(this.yRotOffsetAnim + Mth.m_14177_(getYOffset()));
/*      */     
/*  247 */     if (this.strangleTime > 0) {
/*      */       
/*  249 */       this.strangleTime--;
/*  250 */       if (this.strangleTime == 0) {
/*  251 */         this.tentacleAnimSpeed = 1.0F;
/*      */       }
/*      */     } 
/*  254 */     if (!isDormant())
/*  255 */       this.tentacleAnim++; 
/*  256 */     float vanillaAnim = this.f_267362_.m_267756_() - this.f_267362_.m_267731_() * 2.0F;
/*  257 */     m_146926_((float)Math.toDegrees((Mth.m_14089_((this.tentacleAnim + getAnimationOffset()) * 0.05F * this.tentacleAnimSpeed) + Mth.m_14089_(vanillaAnim))) * 0.05F * this.tentacleAnimReach - 90.0F + this.tentacle.xRotOffset);
/*  258 */     m_146922_((float)Math.toDegrees((Mth.m_14031_((this.tentacleAnim + getAnimationOffset()) * 0.06F * this.tentacleAnimSpeed) + Mth.m_14031_(vanillaAnim))) * 0.14F * this.tentacleAnimReach - 270.0F + this.tentacle.yRotOffset);
/*      */     
/*  260 */     int curlSteps = ((Integer)this.f_19804_.m_135370_(CURLSTEPS)).intValue();
/*  261 */     if (curlSteps > 0) {
/*      */       
/*  263 */       this.xCurlAnim += (float)(((Float)this.f_19804_.m_135370_(XCURLANIM)).floatValue() - this.xCurlAnim) / curlSteps;
/*  264 */       this.yCurlAnim += (float)wrap(((Float)this.f_19804_.m_135370_(YCURLANIM)).floatValue() - this.yCurlAnim, ((Boolean)this.f_19804_.m_135370_(SHOULDWRAPY)).booleanValue()) / curlSteps;
/*      */     } 
/*  266 */     setXCurl(this.xCurlAnim + getXCurl());
/*  267 */     this.tentacle.xCurl += ((Float)this.f_19804_.m_135370_(LASTXCURLANIM)).floatValue();
/*  268 */     setYCurl(this.yCurlAnim + getYCurl());
/*      */     
/*  270 */     this.tentacle.tickAndO();
/*  271 */     this.tentacle.m_6034_(m_20185_(), m_20186_(), m_20189_());
/*  272 */     this.tentacle.m_146926_(m_146909_());
/*  273 */     this.tentacle.m_146922_(m_146908_());
/*      */     
/*  275 */     if (!(m_9236_()).f_46443_ && this.f_19797_ % 120 == 0) {
/*  276 */       WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new UpdateAnimationMessage(m_19879_(), this.tentacleAnim));
/*      */     }
/*  278 */     if (!m_21224_()) {
/*      */       
/*  280 */       if (canSwing()) {
/*      */         
/*  282 */         if (this.tentacleSwingTime > 0) {
/*      */           
/*  284 */           this.tentacleSwingTime--;
/*  285 */           if (this.tentacleSwingTime == 25) {
/*      */             
/*  287 */             lerpBaseYTo(80.0F, 4, false);
/*  288 */             lerpCurlYTo(-0.1F, 4);
/*      */           } 
/*  290 */           if (this.tentacleSwingTime == 15 && m_5448_() != null)
/*      */           {
/*  292 */             doSwingAnimation(m_5448_().m_20182_(), 20.0F, 2);
/*      */           }
/*  294 */           if (this.tentacleSwingTime == 0)
/*      */           {
/*  296 */             stopSwingAnimation(false);
/*      */           }
/*      */         } 
/*      */         
/*  300 */         if (this.nextSwing > 0) {
/*  301 */           this.nextSwing--;
/*      */         }
/*  303 */         if (this.tentacleSwingTime > 0) {
/*      */           
/*  305 */           this.knockbackWait++;
/*  306 */           LivingEntity target = m_5448_();
/*  307 */           if (this.knockbackWait >= 35 && target != null) {
/*      */             
/*  309 */             if (target instanceof Player) { Player player = (Player)target;
/*      */               
/*  311 */               if (!player.m_21211_().m_41619_() && player.m_21211_().m_41720_() instanceof net.minecraft.world.item.ShieldItem && !player.m_36335_().m_41519_(Items.f_42740_)) {
/*      */                 
/*  313 */                 player.m_36335_().m_41524_(Items.f_42740_, 100);
/*  314 */                 m_9236_().m_7605_((Entity)player, (byte)30);
/*      */               }  }
/*      */             
/*  317 */             boolean flag = target.m_6469_(m_269291_().m_269333_((LivingEntity)this), (float)m_21133_(Attributes.f_22281_) * 3.5F);
/*  318 */             if (flag)
/*      */             {
/*  320 */               target.m_147240_((float)m_21133_(Attributes.f_22282_), m_20185_() - target.m_20185_(), m_20189_() - target.m_20189_());
/*  321 */               m_19970_((LivingEntity)this, (Entity)target);
/*  322 */               m_6703_(target);
/*      */             }
/*      */           
/*      */           } 
/*      */         } else {
/*      */           
/*  328 */           this.knockbackWait = 0;
/*      */         } 
/*      */       } 
/*      */       
/*  332 */       if (this.awakeTime > 0) {
/*      */         
/*  334 */         this.awakeTime--;
/*  335 */         if (this.awakeTime == 0) {
/*  336 */           stopAwakeAnimation();
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private static double wrap(double f, boolean shouldWrap) {
/*  343 */     return shouldWrap ? Mth.m_14175_(f) : f;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_5997_(double deltaX, double deltaY, double deltaZ) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hurt(TentaclePartEntity<TentacleEntity> part, DamageSource source, float amount) {
/*  354 */     if (isDormant()) {
/*      */       
/*  356 */       if (source.m_269533_(DamageTypeTags.f_268738_)) {
/*  357 */         return reallyHurt(source, amount);
/*      */       }
/*  359 */       return false;
/*      */     } 
/*      */ 
/*      */     
/*  363 */     return reallyHurt(source, amount);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean reallyHurt(DamageSource source, float amount) {
/*  369 */     return super.m_6469_(source, amount);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_6469_(DamageSource source, float amount) {
/*  375 */     if (source.m_269533_(DamageTypeTags.f_268738_))
/*  376 */       return super.m_6469_(source, amount); 
/*  377 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_6043_() {
/*  383 */     this.f_20891_ = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_147207_(MobEffectInstance effect, @Nullable Entity entity) {
/*  389 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_214076_(ServerLevel level, LivingEntity entity) {
/*  395 */     if (entity instanceof Mob) { Mob mob = (Mob)entity; if (WorldTainting.getInstance().convertMob(mob, false)); }  return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_7301_(MobEffectInstance effect) {
/*  401 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public MobType m_6336_() {
/*  407 */     return WitherStormModMobTypes.SICKENED;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected float m_6431_(Pose pose, EntityDimensions size) {
/*  413 */     return size.f_20378_ / 2.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_5802_(BlockPos pos) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_6573_(Player player) {
/*  423 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_6094_() {
/*  429 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_6063_() {
/*  435 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_147240_(double strength, double deltaX, double deltaZ) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isMultipartEntity() {
/*  446 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public PartEntity<?>[] getParts() {
/*  452 */     List<TentaclePartEntity<TentacleEntity>> parts = Lists.newArrayList();
/*  453 */     parts.add(this.tentacle);
/*  454 */     parts.addAll(this.tentacle.getChained());
/*  455 */     Collections.reverse(parts);
/*  456 */     return (PartEntity<?>[])parts.<PartEntity>toArray(new PartEntity[parts.size()]);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getAnimationOffset() {
/*  461 */     return ((Integer)this.f_19804_.m_135370_(ANIMATION_OFFSET)).intValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAnimationOffset(int offset) {
/*  466 */     this.f_19804_.m_135381_(ANIMATION_OFFSET, Integer.valueOf(offset));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Packet<ClientGamePacketListener> m_5654_() {
/*  472 */     WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new UpdateAnimationMessage(m_19879_(), this.tentacleAnim));
/*  473 */     return super.m_5654_();
/*      */   }
/*      */ 
/*      */   
/*      */   public TentaclePartEntity<TentacleEntity> getTentacle() {
/*  478 */     return this.tentacle;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_19956_(Entity entity, Entity.MoveFunction moveFunction) {
/*  484 */     if (entity.m_20270_((Entity)this.last) < 20.0D) {
/*  485 */       entity.m_6034_(this.last.m_20185_(), this.last.m_20186_(), this.last.m_20189_());
/*      */     } else {
/*  487 */       super.m_19956_(entity, moveFunction);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Vec3 m_7688_(LivingEntity entity) {
/*  493 */     return this.last.m_20182_();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_7350_(EntityDataAccessor<?> parameter) {
/*  499 */     super.m_7350_(parameter);
/*  500 */     if (parameter.equals(XOFFSET))
/*  501 */       setXOffset(getXOffset()); 
/*  502 */     if (parameter.equals(YOFFSET))
/*  503 */       setYOffset(getYOffset()); 
/*  504 */     if (parameter.equals(XCURL))
/*  505 */       setXCurl(getXCurl()); 
/*  506 */     if (parameter.equals(YCURL))
/*  507 */       setYCurl(getYCurl()); 
/*  508 */     if (parameter.equals(LASTXCURLANIM)) {
/*  509 */       this.secondLast.xCurl = getXCurl() + ((Float)this.f_19804_.m_135370_(LASTXCURLANIM)).floatValue();
/*      */     }
/*      */   }
/*      */   
/*      */   private void setXCurl(float curl) {
/*  514 */     this.tentacle.xCurl = curl;
/*  515 */     for (TentaclePartEntity<TentacleEntity> part : (Iterable<TentaclePartEntity<TentacleEntity>>)this.tentacle.getChained()) {
/*  516 */       part.xCurl = this.tentacle.xCurl;
/*      */     }
/*      */   }
/*      */   
/*      */   private void setYCurl(float curl) {
/*  521 */     this.tentacle.yCurl = curl;
/*  522 */     for (TentaclePartEntity<TentacleEntity> part : (Iterable<TentaclePartEntity<TentacleEntity>>)this.tentacle.getChained()) {
/*  523 */       part.yCurl = this.tentacle.yCurl;
/*      */     }
/*      */   }
/*      */   
/*      */   private void setXOffset(float rot) {
/*  528 */     this.tentacle.xRotOffset = rot;
/*  529 */     for (TentaclePartEntity<TentacleEntity> part : (Iterable<TentaclePartEntity<TentacleEntity>>)this.tentacle.getChained()) {
/*  530 */       part.xRotOffset = this.tentacle.xRotOffset;
/*      */     }
/*      */   }
/*      */   
/*      */   private void setYOffset(float rot) {
/*  535 */     this.tentacle.yRotOffset = rot;
/*  536 */     for (TentaclePartEntity<TentacleEntity> part : (Iterable<TentaclePartEntity<TentacleEntity>>)this.tentacle.getChained()) {
/*  537 */       part.yRotOffset = this.tentacle.yRotOffset;
/*      */     }
/*      */   }
/*      */   
/*      */   public void setSavedXCurl(float rot) {
/*  542 */     this.f_19804_.m_135381_(XCURL, Float.valueOf(rot));
/*  543 */     setXCurl(rot);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSavedYCurl(float rot) {
/*  548 */     this.f_19804_.m_135381_(YCURL, Float.valueOf(rot));
/*  549 */     setYCurl(rot);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSavedXOffset(float rot) {
/*  554 */     this.f_19804_.m_135381_(XOFFSET, Float.valueOf(rot));
/*  555 */     setXOffset(rot);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSavedYOffset(float rot) {
/*  560 */     this.f_19804_.m_135381_(YOFFSET, Float.valueOf(rot));
/*  561 */     setYOffset(rot);
/*      */   }
/*      */ 
/*      */   
/*      */   public float getXOffset() {
/*  566 */     return ((Float)this.f_19804_.m_135370_(XOFFSET)).floatValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public float getYOffset() {
/*  571 */     return ((Float)this.f_19804_.m_135370_(YOFFSET)).floatValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public float getXCurl() {
/*  576 */     return ((Float)this.f_19804_.m_135370_(XCURL)).floatValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public float getYCurl() {
/*  581 */     return ((Float)this.f_19804_.m_135370_(YCURL)).floatValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public void doSwingAttack(LivingEntity target) {
/*  586 */     this.tentacleSwingTime = 40;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isDoingSwingAttack() {
/*  591 */     return (this.tentacleSwingTime > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canDoSwingAttack() {
/*  596 */     return (this.nextSwing <= 0 && !isDoingSwingAttack());
/*      */   }
/*      */ 
/*      */   
/*      */   public void doSwingAnimation(Vec3 target, float overReach, int steps) {
/*  601 */     Vec3 delta = target.m_82546_(m_20182_());
/*  602 */     float atan2 = (float)Mth.m_14136_(delta.f_82479_, delta.f_82481_);
/*  603 */     float angle = atan2 * 57.295776F;
/*  604 */     lerpBaseYTo(-(angle + 360.0F - 90.0F + overReach + Mth.m_14177_(getYOffset())) % 360.0F, steps, false);
/*  605 */     lerpCurlTo(0.1F, 0.1F, 4);
/*  606 */     m_5496_((SoundEvent)WitherStormModSoundEvents.WHOOSH.get(), 3.0F, 1.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public void stopSwingAnimation(boolean setBaseY) {
/*  611 */     if (setBaseY) {
/*      */       
/*  613 */       setYOffset(this.tentacle.yRotOffset);
/*  614 */       this.f_19804_.m_135381_(YOFFSET, Float.valueOf(this.tentacle.yRotOffset));
/*      */     } 
/*  616 */     lerpBaseYTo(0.0F, 12, true);
/*  617 */     lerpCurlTo(0.0F, 0.0F, 4);
/*      */   }
/*      */ 
/*      */   
/*      */   public void doDeathAnimation() {
/*  622 */     lerpCurlTo(0.3F, 0.1F, 3);
/*  623 */     lerpBaseXTo(70.0F, 8);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_6667_(DamageSource source) {
/*  629 */     super.m_6667_(source);
/*  630 */     doDeathAnimation();
/*  631 */     m_20153_();
/*      */   }
/*      */ 
/*      */   
/*      */   public void doAwakeAnimation() {
/*  636 */     this.tentacleAnimSpeed = 6.0F;
/*  637 */     this.tentacleAnimReach = 4.0F;
/*  638 */     lerpCurlYTo(0.05F * (float)this.f_19796_.m_188583_(), 8);
/*  639 */     this.awakeTime = 40;
/*  640 */     m_9236_().m_7605_((Entity)this, (byte)13);
/*  641 */     m_5496_((SoundEvent)WitherStormModSoundEvents.WHOOSH.get(), 3.0F, 1.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public void doIndefiniteAwakeAnimation() {
/*  646 */     if (!(m_9236_()).f_46443_)
/*  647 */       doAwakeAnimation(); 
/*  648 */     this.awakeTime = 0;
/*  649 */     m_9236_().m_7605_((Entity)this, (byte)14);
/*      */   }
/*      */ 
/*      */   
/*      */   public void stopAwakeAnimation() {
/*  654 */     this.tentacleAnimSpeed = 1.0F;
/*  655 */     this.tentacleAnimReach = 1.0F;
/*  656 */     lerpCurlYTo(0.0F, 4);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void m_6153_() {
/*  662 */     this.f_20919_++;
/*  663 */     if (this.f_20919_ >= 40) {
/*      */       
/*  665 */       m_142687_(Entity.RemovalReason.KILLED);
/*      */       
/*  667 */       for (int i = 0; i < 40; i++) {
/*      */         
/*  669 */         double d0 = this.f_19796_.m_188583_() * 0.02D;
/*  670 */         double d1 = this.f_19796_.m_188583_() * 0.02D;
/*  671 */         double d2 = this.f_19796_.m_188583_() * 0.02D;
/*  672 */         m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123759_, m_20208_(0.5D), m_20187_(), m_20262_(0.5D), d0, d1, d2);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void doStrangle() {
/*  679 */     this.strangleTime = 20;
/*  680 */     this.tentacleAnimSpeed = 15.0F;
/*  681 */     m_9236_().m_7605_((Entity)this, (byte)11);
/*      */   }
/*      */ 
/*      */   
/*      */   public void stopStrangle() {
/*  686 */     this.strangleTime = 0;
/*  687 */     this.tentacleAnimSpeed = 1.0F;
/*  688 */     m_9236_().m_7605_((Entity)this, (byte)12);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_7822_(byte event) {
/*  694 */     if (event == 11) {
/*  695 */       doStrangle();
/*  696 */     } else if (event == 12) {
/*  697 */       stopStrangle();
/*  698 */     } else if (event == 13) {
/*  699 */       doAwakeAnimation();
/*  700 */     } else if (event == 14) {
/*  701 */       doIndefiniteAwakeAnimation();
/*      */     } else {
/*  703 */       super.m_7822_(event);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void lerpBaseOffsetTo(float x, float y, int steps) {
/*  708 */     this.f_19804_.m_135381_(XOFFSETANIM, Float.valueOf(x));
/*  709 */     this.f_19804_.m_135381_(YOFFSETANIM, Float.valueOf(y));
/*  710 */     this.f_19804_.m_135381_(OFFSETSTEPS, Integer.valueOf(steps));
/*      */   }
/*      */ 
/*      */   
/*      */   public void lerpCurlTo(float x, float y, int steps) {
/*  715 */     this.f_19804_.m_135381_(XCURLANIM, Float.valueOf(x));
/*  716 */     this.f_19804_.m_135381_(YCURLANIM, Float.valueOf(y));
/*  717 */     this.f_19804_.m_135381_(CURLSTEPS, Integer.valueOf(steps));
/*      */   }
/*      */ 
/*      */   
/*      */   public void lerpBaseXTo(float x, int steps) {
/*  722 */     this.f_19804_.m_135381_(XOFFSETANIM, Float.valueOf(x));
/*  723 */     this.f_19804_.m_135381_(OFFSETSTEPS, Integer.valueOf(steps));
/*      */   }
/*      */ 
/*      */   
/*      */   public void lerpBaseYTo(float y, int steps, boolean wrap) {
/*  728 */     this.f_19804_.m_135381_(YOFFSETANIM, Float.valueOf(y));
/*  729 */     this.f_19804_.m_135381_(OFFSETSTEPS, Integer.valueOf(steps));
/*  730 */     this.f_19804_.m_135381_(SHOULDWRAPY, Boolean.valueOf(wrap));
/*      */   }
/*      */ 
/*      */   
/*      */   public void lerpCurlXTo(float x, int steps) {
/*  735 */     this.f_19804_.m_135381_(XCURLANIM, Float.valueOf(x));
/*  736 */     this.f_19804_.m_135381_(CURLSTEPS, Integer.valueOf(steps));
/*      */   }
/*      */ 
/*      */   
/*      */   public void lerpCurlYTo(float y, int steps) {
/*  741 */     this.f_19804_.m_135381_(YCURLANIM, Float.valueOf(y));
/*  742 */     this.f_19804_.m_135381_(CURLSTEPS, Integer.valueOf(steps));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public AABB m_6921_() {
/*  748 */     return m_20191_().m_82400_(4.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_6087_() {
/*  754 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void m_6138_() {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SpawnGroupData m_6518_(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, SpawnGroupData existing, CompoundTag data) {
/*  766 */     setSavedYOffset(this.f_19796_.m_188503_(360));
/*  767 */     setSavedXOffset(15.0F + this.f_19796_.m_188501_() * 5.0F);
/*  768 */     setSavedXCurl(1.25F + this.f_19796_.m_188501_() * 0.1F);
/*  769 */     setAnimationOffset(this.f_19796_.m_188503_(35) * 10000);
/*  770 */     return super.m_6518_(world, difficulty, reason, existing, data);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCanStrangle(boolean canStrangle) {
/*  775 */     this.canStrangle = canStrangle;
/*  776 */     if (!canStrangle) {
/*      */       
/*  778 */       this.f_21345_.m_25363_(this.strangleGoal);
/*      */     }
/*      */     else {
/*      */       
/*  782 */       this.f_21345_.m_25363_(this.strangleGoal);
/*  783 */       this.f_21345_.m_25352_(2, this.strangleGoal);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canStrangle() {
/*  789 */     return this.canStrangle;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCanSwing(boolean canSwing) {
/*  794 */     this.canSwing = canSwing;
/*  795 */     if (!canSwing) {
/*      */       
/*  797 */       this.f_21345_.m_25363_(this.swingGoal);
/*      */     }
/*      */     else {
/*      */       
/*  801 */       this.f_21345_.m_25363_(this.swingGoal);
/*  802 */       this.f_21345_.m_25352_(1, this.swingGoal);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canSwing() {
/*  808 */     return this.canSwing;
/*      */   }
/*      */ 
/*      */   
/*      */   public void curlAround(Vec3 pos) {
/*  813 */     Vec3 delta = pos.m_82546_(m_20182_());
/*  814 */     float atan2 = (float)Mth.m_14136_(delta.f_82479_, delta.f_82481_);
/*  815 */     float angle = atan2 * 57.295776F;
/*  816 */     lerpBaseYTo(-(angle + 180.0F + getYOffset()), 8, false);
/*  817 */     lerpCurlTo(0.1F, 0.1F, 4);
/*      */   }
/*      */ 
/*      */   
/*      */   public void stopCurlingAround() {
/*  822 */     lerpBaseYTo(0.0F, 8, true);
/*  823 */     lerpCurlTo(0.0F, 0.0F, 4);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void m_141965_(ClientboundAddEntityPacket packet) {
/*  829 */     super.m_141965_(packet);
/*      */     
/*  831 */     PartEntity[] arrayOfPartEntity = (PartEntity[])getParts();
/*      */     
/*  833 */     for (int i = 0; i < arrayOfPartEntity.length; i++) {
/*  834 */       arrayOfPartEntity[i].m_20234_(1 + i + packet.m_131496_());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public PushReaction m_7752_() {
/*  840 */     return PushReaction.IGNORE;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean m_6779_(LivingEntity entity) {
/*  846 */     return (super.m_6779_(entity) && !(entity instanceof WitherSickened));
/*      */   }
/*      */   
/*      */   public static class UpdateAnimationMessage
/*      */     extends Packet
/*      */   {
/*      */     private int id;
/*      */     private int anim;
/*      */     
/*      */     public UpdateAnimationMessage(int entityId, int anim) {
/*  856 */       super(true);
/*  857 */       this.id = entityId;
/*  858 */       this.anim = anim;
/*      */     }
/*      */ 
/*      */     
/*      */     public UpdateAnimationMessage() {
/*  863 */       super(false);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void encode(FriendlyByteBuf buffer) {
/*  869 */       buffer.m_130130_(this.id);
/*  870 */       buffer.writeInt(this.anim);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void decode(FriendlyByteBuf buffer) {
/*  876 */       this.id = buffer.m_130242_();
/*  877 */       this.anim = buffer.readInt();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public Runnable getProcessor(NetworkEvent.Context context) {
/*  883 */       return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class StrangleGoal
/*      */     extends Goal
/*      */   {
/*      */     private final TentacleEntity entity;
/*      */ 
/*      */     
/*      */     private int grabWait;
/*      */ 
/*      */     
/*      */     private int nextStrangle;
/*      */ 
/*      */ 
/*      */     
/*      */     public StrangleGoal(TentacleEntity entity) {
/*  904 */       this.entity = entity;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean m_8036_() {
/*  910 */       LivingEntity target = this.entity.m_5448_();
/*  911 */       return (!this.entity.isDormant() && target != null && target.m_6084_() && this.entity.m_6084_() && !this.entity.isDoingSwingAttack());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void m_8037_() {
/*  917 */       LivingEntity target = this.entity.m_5448_();
/*  918 */       if (!this.entity.m_142582_((Entity)target)) {
/*      */         
/*  920 */         target.m_8127_();
/*  921 */         this.entity.m_6710_(null);
/*      */       }
/*      */       else {
/*      */         
/*  925 */         this.grabWait++;
/*  926 */         if (this.grabWait > 5)
/*  927 */           target.m_20329_((Entity)this.entity); 
/*  928 */         if (this.nextStrangle > 0) {
/*      */           
/*  930 */           this.nextStrangle--;
/*  931 */           if (this.nextStrangle <= 0) {
/*      */             
/*  933 */             this.entity.doStrangle();
/*  934 */             this.nextStrangle = 20 + this.entity.m_217043_().m_188503_(40);
/*  935 */             this.entity.m_7327_((Entity)target);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void m_8056_() {
/*  944 */       LivingEntity target = this.entity.m_5448_();
/*  945 */       this.entity.doSwingAnimation(target.m_20182_(), 0.0F, 4);
/*  946 */       this.entity.f_19804_.m_135381_(TentacleEntity.LASTXCURLANIM, Float.valueOf(0.3F));
/*  947 */       this.nextStrangle = 20 + this.entity.m_217043_().m_188503_(40);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void m_8041_() {
/*  953 */       this.entity.m_20153_();
/*  954 */       this.grabWait = 0;
/*  955 */       this.entity.stopSwingAnimation(true);
/*  956 */       this.entity.stopStrangle();
/*  957 */       this.entity.f_19804_.m_135381_(TentacleEntity.LASTXCURLANIM, Float.valueOf(0.0F));
/*      */     }
/*      */   }
/*      */   
/*      */   private static class SwingGoal
/*      */     extends Goal
/*      */   {
/*      */     private final TentacleEntity entity;
/*      */     
/*      */     public SwingGoal(TentacleEntity entity) {
/*  967 */       this.entity = entity;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean m_8036_() {
/*  973 */       LivingEntity target = this.entity.m_5448_();
/*  974 */       return (!this.entity.isDormant() && target != null && target.m_6084_() && this.entity.m_6084_() && (this.entity.m_21223_() < this.entity.m_21233_() || !this.entity.canStrangle) && this.entity.canDoSwingAttack());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean m_8045_() {
/*  980 */       LivingEntity target = this.entity.m_5448_();
/*  981 */       return (target != null && target.m_6084_() && this.entity.m_6084_() && this.entity.isDoingSwingAttack());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void m_8056_() {
/*  987 */       LivingEntity target = this.entity.m_5448_();
/*  988 */       this.entity.doSwingAttack(target);
/*  989 */       this.entity.nextSwing = 120 + this.entity.m_217043_().m_188503_(120);
/*      */     }
/*      */   }
/*      */   
/*      */   private static class DoNothingGoal
/*      */     extends Goal
/*      */   {
/*      */     private final TentacleEntity entity;
/*      */     
/*      */     public DoNothingGoal(TentacleEntity entity) {
/*  999 */       this.entity = entity;
/* 1000 */       m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP, Goal.Flag.TARGET));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean m_8036_() {
/* 1006 */       return this.entity.isDormant();
/*      */     }
/*      */   }
/*      */   
/*      */   public static class TargetGoal<T extends LivingEntity>
/*      */     extends NearestAttackableTargetGoal<T>
/*      */   {
/*      */     public TargetGoal(Mob entity, Class<T> targetType, boolean mustSee, boolean mustReach) {
/* 1014 */       super(entity, targetType, 10, mustSee, mustReach, e -> !(e instanceof net.minecraft.world.entity.monster.Enemy));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected AABB m_7255_(double range) {
/* 1020 */       return this.f_26135_.m_20191_().m_82400_(range);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected void m_26073_() {
/* 1026 */       super.m_26073_();
/* 1027 */       for (Entity entity : this.f_26135_.m_9236_().m_45976_(TentacleEntity.class, m_7255_(m_7623_() + 10.0D))) {
/*      */         
/* 1029 */         TentacleEntity tentacle = (TentacleEntity)entity;
/* 1030 */         if (tentacle.m_6084_() && tentacle.m_5448_() == this.f_26050_) {
/*      */           
/* 1032 */           this.f_26050_ = null;
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\TentacleEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
package nonamecrackers2.witherstormmod.common.entity;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.Entity.MoveFunction;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.util.LogicalSidedProvider;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent.Context;
import nonamecrackers2.witherstormmod.common.entity.part.TentaclePartEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.util.ConditionalLookController;
import nonamecrackers2.witherstormmod.common.util.EmptyBodyController;
import nonamecrackers2.witherstormmod.common.world.tainting.WorldTainting;
import org.jetbrains.annotations.NotNull;

public class TentacleEntity extends Monster implements IMultipartHurtable<TentaclePartEntity<nonamecrackers2.witherstormmod.common.entity.TentacleEntity>> {
   private static final EntityDataAccessor<Boolean> DORMANT = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.TentacleEntity.class, EntityDataSerializers.f_135035_
   );
   private static final EntityDataAccessor<Integer> ANIMATION_OFFSET = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.TentacleEntity.class, EntityDataSerializers.f_135028_
   );
   private static final EntityDataAccessor<Float> XOFFSET = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.TentacleEntity.class, EntityDataSerializers.f_135029_
   );
   private static final EntityDataAccessor<Float> YOFFSET = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.TentacleEntity.class, EntityDataSerializers.f_135029_
   );
   private static final EntityDataAccessor<Integer> OFFSETSTEPS = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.TentacleEntity.class, EntityDataSerializers.f_135028_
   );
   private static final EntityDataAccessor<Boolean> SHOULDWRAPY = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.TentacleEntity.class, EntityDataSerializers.f_135035_
   );
   private static final EntityDataAccessor<Float> XOFFSETANIM = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.TentacleEntity.class, EntityDataSerializers.f_135029_
   );
   private static final EntityDataAccessor<Float> YOFFSETANIM = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.TentacleEntity.class, EntityDataSerializers.f_135029_
   );
   private static final EntityDataAccessor<Float> XCURL = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.TentacleEntity.class, EntityDataSerializers.f_135029_
   );
   private static final EntityDataAccessor<Float> YCURL = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.TentacleEntity.class, EntityDataSerializers.f_135029_
   );
   private static final EntityDataAccessor<Integer> CURLSTEPS = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.TentacleEntity.class, EntityDataSerializers.f_135028_
   );
   private static final EntityDataAccessor<Float> XCURLANIM = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.TentacleEntity.class, EntityDataSerializers.f_135029_
   );
   private static final EntityDataAccessor<Float> YCURLANIM = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.TentacleEntity.class, EntityDataSerializers.f_135029_
   );
   private static final EntityDataAccessor<Float> LASTXCURLANIM = SynchedEntityData.m_135353_(
      nonamecrackers2.witherstormmod.common.entity.TentacleEntity.class, EntityDataSerializers.f_135029_
   );
   private float xCurlAnim;
   private float yCurlAnim;
   private final TentaclePartEntity<nonamecrackers2.witherstormmod.common.entity.TentacleEntity> tentacle;
   private final TentaclePartEntity<nonamecrackers2.witherstormmod.common.entity.TentacleEntity> last;
   private final TentaclePartEntity<nonamecrackers2.witherstormmod.common.entity.TentacleEntity> secondLast;
   private int tentacleAnim;
   private float xRotOffsetAnim;
   private float yRotOffsetAnim;
   private int strangleTime;
   private float tentacleAnimSpeed = 1.0F;
   private float tentacleAnimReach = 1.0F;
   private int tentacleSwingTime;
   private int nextSwing;
   private int knockbackWait;
   private int awakeTime;
   private boolean canStrangle = true;
   private boolean canSwing = true;
   private nonamecrackers2.witherstormmod.common.entity.TentacleEntity.StrangleGoal strangleGoal;
   private nonamecrackers2.witherstormmod.common.entity.TentacleEntity.SwingGoal swingGoal;

   public TentacleEntity(EntityType<? extends nonamecrackers2.witherstormmod.common.entity.TentacleEntity> type, Level world) {
      super(type, world);
      this.tentacle = new TentaclePartEntity(
         this,
         new TentaclePartEntity(
            this,
            new TentaclePartEntity(
               this,
               new TentaclePartEntity(this, new TentaclePartEntity(this, 1.5F, 3.0F, 4, 1.5F, 1.0F), 1.5F, 3.0F, 3, 1.5F, 1.0F),
               1.5F,
               2.5F,
               2,
               1.5F,
               1.0F
            ),
            1.5F,
            2.0F,
            1,
            1.5F,
            1.0F
         ),
         1.5F,
         1.5F,
         0,
         1.5F,
         1.0F
      );
      this.secondLast = (TentaclePartEntity<nonamecrackers2.witherstormmod.common.entity.TentacleEntity>)this.tentacle.getSegment(3);
      this.last = (TentaclePartEntity<nonamecrackers2.witherstormmod.common.entity.TentacleEntity>)this.tentacle.getLast();
      this.f_21365_ = new ConditionalLookController(this, entity -> false);
      this.f_19850_ = false;
   }

   protected BodyRotationControl m_7560_() {
      return new EmptyBodyController(this);
   }

   public static Builder createAttributes() {
      return Monster.m_33035_()
         .m_22268_(Attributes.f_22276_, 80.0)
         .m_22268_(Attributes.f_22277_, 8.0)
         .m_22268_(Attributes.f_22279_, 0.0)
         .m_22268_(Attributes.f_22284_, 12.0)
         .m_22268_(Attributes.f_22282_, 1.5);
   }

   protected void m_8099_() {
      this.f_21345_.m_25352_(0, new nonamecrackers2.witherstormmod.common.entity.TentacleEntity.DoNothingGoal(this));
      this.swingGoal = new nonamecrackers2.witherstormmod.common.entity.TentacleEntity.SwingGoal(this);
      this.f_21345_.m_25352_(1, this.swingGoal);
      this.strangleGoal = new nonamecrackers2.witherstormmod.common.entity.TentacleEntity.StrangleGoal(this);
      this.f_21345_.m_25352_(2, this.strangleGoal);
      this.f_21346_.m_25352_(0, new nonamecrackers2.witherstormmod.common.entity.TentacleEntity.TargetGoal(this, Player.class, true, true));
      this.f_21346_.m_25352_(1, new nonamecrackers2.witherstormmod.common.entity.TentacleEntity.TargetGoal(this, Animal.class, true, true));
   }

   protected void m_8097_() {
      super.m_8097_();
      this.f_19804_.m_135372_(DORMANT, false);
      this.f_19804_.m_135372_(ANIMATION_OFFSET, 0);
      this.f_19804_.m_135372_(XOFFSET, 20.0F);
      this.f_19804_.m_135372_(YOFFSET, 0.0F);
      this.f_19804_.m_135372_(OFFSETSTEPS, 0);
      this.f_19804_.m_135372_(SHOULDWRAPY, true);
      this.f_19804_.m_135372_(XOFFSETANIM, 0.0F);
      this.f_19804_.m_135372_(YOFFSETANIM, 0.0F);
      this.f_19804_.m_135372_(XCURL, 1.3F);
      this.f_19804_.m_135372_(YCURL, 1.0F);
      this.f_19804_.m_135372_(CURLSTEPS, 0);
      this.f_19804_.m_135372_(XCURLANIM, 0.0F);
      this.f_19804_.m_135372_(YCURLANIM, 0.0F);
      this.f_19804_.m_135372_(LASTXCURLANIM, 0.0F);
   }

   public void m_7378_(CompoundTag compound) {
      super.m_7378_(compound);
      if (compound.m_128441_("Dormant")) {
         this.setDormant(compound.m_128471_("Dormant"));
      }

      if (compound.m_128441_("AnimOffset")) {
         this.f_19804_.m_135381_(ANIMATION_OFFSET, compound.m_128451_("AnimOffset"));
      }

      if (compound.m_128441_("XOffset")) {
         float offset = compound.m_128457_("XOffset");
         this.f_19804_.m_135381_(XOFFSET, offset);
         this.setXOffset(offset);
      }

      if (compound.m_128441_("YOffset")) {
         float offset = compound.m_128457_("YOffset");
         this.f_19804_.m_135381_(YOFFSET, offset);
         this.setYOffset(offset);
      }

      if (compound.m_128441_("XCurl")) {
         float xCurl = compound.m_128457_("XCurl");
         this.f_19804_.m_135381_(XCURL, xCurl);
         this.setXCurl(xCurl);
      }

      if (compound.m_128441_("YCurl")) {
         float yCurl = compound.m_128457_("YCurl");
         this.f_19804_.m_135381_(YCURL, yCurl);
         this.setYCurl(yCurl);
      }

      this.nextSwing = compound.m_128451_("NextSwing");
      if (compound.m_128441_("CanStrangle")) {
         this.setCanStrangle(compound.m_128471_("CanStrangle"));
      }

      if (compound.m_128441_("CanSwing")) {
         this.setCanSwing(compound.m_128471_("CanSwing"));
      }

      if (compound.m_128441_("XCurlAnim")) {
         this.lerpCurlXTo(compound.m_128457_("XCurlAnim"), 1);
      }

      if (compound.m_128441_("YCurlAnim")) {
         this.lerpCurlYTo(compound.m_128457_("YCurlAnim"), 1);
      }

      if (compound.m_128441_("XOffsetAnim")) {
         this.lerpBaseXTo(compound.m_128457_("XOffsetAnim"), 1);
      }

      if (compound.m_128441_("YOffsetAnim")) {
         this.lerpBaseYTo(compound.m_128457_("YOffsetAnim"), 1, true);
      }
   }

   public void m_7380_(CompoundTag compound) {
      super.m_7380_(compound);
      compound.m_128379_("Dormant", this.isDormant());
      compound.m_128405_("AnimOffset", (Integer)this.f_19804_.m_135370_(ANIMATION_OFFSET));
      compound.m_128350_("XOffset", (Float)this.f_19804_.m_135370_(XOFFSET));
      compound.m_128350_("YOffset", (Float)this.f_19804_.m_135370_(YOFFSET));
      compound.m_128350_("XCurl", (Float)this.f_19804_.m_135370_(XCURL));
      compound.m_128350_("YCurl", (Float)this.f_19804_.m_135370_(YCURL));
      compound.m_128405_("NextSwing", this.nextSwing);
      compound.m_128379_("CanStrangle", this.canStrangle);
      compound.m_128379_("CanSwing", this.canSwing);
      compound.m_128350_("XCurlAnim", (Float)this.f_19804_.m_135370_(XCURLANIM));
      compound.m_128350_("YCurlAnim", (Float)this.f_19804_.m_135370_(YCURLANIM));
      compound.m_128350_("XOffsetAnim", (Float)this.f_19804_.m_135370_(XOFFSETANIM));
      compound.m_128350_("YOffsetAnim", (Float)this.f_19804_.m_135370_(YOFFSETANIM));
   }

   public void setDormant(boolean dormant) {
      this.f_19804_.m_135381_(DORMANT, dormant);
   }

   public boolean isDormant() {
      return (Boolean)this.f_19804_.m_135370_(DORMANT);
   }

   public void m_8107_() {
      super.m_8107_();
      int offsetSteps = (Integer)this.f_19804_.m_135370_(OFFSETSTEPS);
      if (offsetSteps > 0) {
         this.xRotOffsetAnim = this.xRotOffsetAnim
            + (float)Mth.m_14175_((double)((Float)this.f_19804_.m_135370_(XOFFSETANIM)).floatValue() - this.xRotOffsetAnim) / offsetSteps;
         this.yRotOffsetAnim = this.yRotOffsetAnim
            + (float)((double)((Float)this.f_19804_.m_135370_(YOFFSETANIM)).floatValue() - this.yRotOffsetAnim) / offsetSteps;
      }

      this.setXOffset(this.xRotOffsetAnim + this.getXOffset());
      this.setYOffset(this.yRotOffsetAnim + Mth.m_14177_(this.getYOffset()));
      if (this.strangleTime > 0) {
         this.strangleTime--;
         if (this.strangleTime == 0) {
            this.tentacleAnimSpeed = 1.0F;
         }
      }

      if (!this.isDormant()) {
         this.tentacleAnim++;
      }

      float vanillaAnim = this.f_267362_.m_267756_() - this.f_267362_.m_267731_() * 2.0F;
      this.m_146926_(
         (float)Math.toDegrees(Mth.m_14089_((this.tentacleAnim + this.getAnimationOffset()) * 0.05F * this.tentacleAnimSpeed) + Mth.m_14089_(vanillaAnim))
               * 0.05F
               * this.tentacleAnimReach
            - 90.0F
            + this.tentacle.xRotOffset
      );
      this.m_146922_(
         (float)Math.toDegrees(Mth.m_14031_((this.tentacleAnim + this.getAnimationOffset()) * 0.06F * this.tentacleAnimSpeed) + Mth.m_14031_(vanillaAnim))
               * 0.14F
               * this.tentacleAnimReach
            - 270.0F
            + this.tentacle.yRotOffset
      );
      int curlSteps = (Integer)this.f_19804_.m_135370_(CURLSTEPS);
      if (curlSteps > 0) {
         this.xCurlAnim = this.xCurlAnim + (float)((double)((Float)this.f_19804_.m_135370_(XCURLANIM)).floatValue() - this.xCurlAnim) / curlSteps;
         this.yCurlAnim = this.yCurlAnim
            + (float)wrap((double)((Float)this.f_19804_.m_135370_(YCURLANIM)).floatValue() - this.yCurlAnim, (Boolean)this.f_19804_.m_135370_(SHOULDWRAPY))
               / curlSteps;
      }

      this.setXCurl(this.xCurlAnim + this.getXCurl());
      this.secondLast.xCurl = this.tentacle.xCurl + (Float)this.f_19804_.m_135370_(LASTXCURLANIM);
      this.setYCurl(this.yCurlAnim + this.getYCurl());
      this.tentacle.tickAndO();
      this.tentacle.m_6034_(this.m_20185_(), this.m_20186_(), this.m_20189_());
      this.tentacle.m_146926_(this.m_146909_());
      this.tentacle.m_146922_(this.m_146908_());
      if (!this.m_9236_().f_46443_ && this.f_19797_ % 120 == 0) {
         WitherStormModPacketHandlers.MAIN
            .send(
               PacketDistributor.TRACKING_ENTITY.with(() -> this),
               new nonamecrackers2.witherstormmod.common.entity.TentacleEntity.UpdateAnimationMessage(this.m_19879_(), this.tentacleAnim)
            );
      }

      if (!this.m_21224_()) {
         if (this.canSwing()) {
            if (this.tentacleSwingTime > 0) {
               this.tentacleSwingTime--;
               if (this.tentacleSwingTime == 25) {
                  this.lerpBaseYTo(80.0F, 4, false);
                  this.lerpCurlYTo(-0.1F, 4);
               }

               if (this.tentacleSwingTime == 15 && this.m_5448_() != null) {
                  this.doSwingAnimation(this.m_5448_().m_20182_(), 20.0F, 2);
               }

               if (this.tentacleSwingTime == 0) {
                  this.stopSwingAnimation(false);
               }
            }

            if (this.nextSwing > 0) {
               this.nextSwing--;
            }

            if (this.tentacleSwingTime > 0) {
               this.knockbackWait++;
               LivingEntity target = this.m_5448_();
               if (this.knockbackWait >= 35 && target != null) {
                  if (target instanceof Player player
                     && !player.m_21211_().m_41619_()
                     && player.m_21211_().m_41720_() instanceof ShieldItem
                     && !player.m_36335_().m_41519_(Items.f_42740_)) {
                     player.m_36335_().m_41524_(Items.f_42740_, 100);
                     this.m_9236_().m_7605_(player, (byte)30);
                  }

                  boolean flag = target.m_6469_(this.m_269291_().m_269333_(this), (float)this.m_21133_(Attributes.f_22281_) * 3.5F);
                  if (flag) {
                     target.m_147240_((float)this.m_21133_(Attributes.f_22282_), this.m_20185_() - target.m_20185_(), this.m_20189_() - target.m_20189_());
                     this.m_19970_(this, target);
                     this.m_6703_(target);
                  }
               }
            } else {
               this.knockbackWait = 0;
            }
         }

         if (this.awakeTime > 0) {
            this.awakeTime--;
            if (this.awakeTime == 0) {
               this.stopAwakeAnimation();
            }
         }
      }
   }

   private static double wrap(double f, boolean shouldWrap) {
      return shouldWrap ? Mth.m_14175_(f) : f;
   }

   public void m_5997_(double deltaX, double deltaY, double deltaZ) {
   }

   public boolean hurt(TentaclePartEntity<nonamecrackers2.witherstormmod.common.entity.TentacleEntity> part, DamageSource source, float amount) {
      if (this.isDormant()) {
         return source.m_269533_(DamageTypeTags.f_268738_) ? this.reallyHurt(source, amount) : false;
      } else {
         return this.reallyHurt(source, amount);
      }
   }

   private boolean reallyHurt(DamageSource source, float amount) {
      return super.m_6469_(source, amount);
   }

   public boolean m_6469_(DamageSource source, float amount) {
      return source.m_269533_(DamageTypeTags.f_268738_) ? super.m_6469_(source, amount) : false;
   }

   public void m_6043_() {
      this.f_20891_ = 0;
   }

   public boolean m_147207_(MobEffectInstance effect, @Nullable Entity entity) {
      return false;
   }

   public boolean m_214076_(ServerLevel level, LivingEntity entity) {
      return entity instanceof Mob mob && WorldTainting.getInstance().convertMob(mob, false);
   }

   public boolean m_7301_(MobEffectInstance effect) {
      return false;
   }

   @NotNull
   public MobType m_6336_() {
      return WitherStormModMobTypes.SICKENED;
   }

   protected float m_6431_(Pose pose, EntityDimensions size) {
      return size.f_20378_ / 2.0F;
   }

   public void m_5802_(BlockPos pos) {
   }

   public boolean m_6573_(Player player) {
      return false;
   }

   public boolean m_6094_() {
      return false;
   }

   public boolean m_6063_() {
      return false;
   }

   public void m_147240_(double strength, double deltaX, double deltaZ) {
   }

   public boolean isMultipartEntity() {
      return true;
   }

   public PartEntity<?>[] getParts() {
      List<TentaclePartEntity<nonamecrackers2.witherstormmod.common.entity.TentacleEntity>> parts = Lists.newArrayList();
      parts.add(this.tentacle);
      parts.addAll(this.tentacle.getChained());
      Collections.reverse(parts);
      return parts.toArray(new PartEntity[parts.size()]);
   }

   public int getAnimationOffset() {
      return (Integer)this.f_19804_.m_135370_(ANIMATION_OFFSET);
   }

   public void setAnimationOffset(int offset) {
      this.f_19804_.m_135381_(ANIMATION_OFFSET, offset);
   }

   public Packet<ClientGamePacketListener> m_5654_() {
      WitherStormModPacketHandlers.MAIN
         .send(
            PacketDistributor.TRACKING_ENTITY.with(() -> this),
            new nonamecrackers2.witherstormmod.common.entity.TentacleEntity.UpdateAnimationMessage(this.m_19879_(), this.tentacleAnim)
         );
      return super.m_5654_();
   }

   public TentaclePartEntity<nonamecrackers2.witherstormmod.common.entity.TentacleEntity> getTentacle() {
      return this.tentacle;
   }

   public void m_19956_(Entity entity, MoveFunction moveFunction) {
      if (entity.m_20270_(this.last) < 20.0) {
         entity.m_6034_(this.last.m_20185_(), this.last.m_20186_(), this.last.m_20189_());
      } else {
         super.m_19956_(entity, moveFunction);
      }
   }

   public Vec3 m_7688_(LivingEntity entity) {
      return this.last.m_20182_();
   }

   public void m_7350_(EntityDataAccessor<?> parameter) {
      super.m_7350_(parameter);
      if (parameter.equals(XOFFSET)) {
         this.setXOffset(this.getXOffset());
      }

      if (parameter.equals(YOFFSET)) {
         this.setYOffset(this.getYOffset());
      }

      if (parameter.equals(XCURL)) {
         this.setXCurl(this.getXCurl());
      }

      if (parameter.equals(YCURL)) {
         this.setYCurl(this.getYCurl());
      }

      if (parameter.equals(LASTXCURLANIM)) {
         this.secondLast.xCurl = this.getXCurl() + (Float)this.f_19804_.m_135370_(LASTXCURLANIM);
      }
   }

   private void setXCurl(float curl) {
      this.tentacle.xCurl = curl;

      for (TentaclePartEntity<nonamecrackers2.witherstormmod.common.entity.TentacleEntity> part : this.tentacle.getChained()) {
         part.xCurl = this.tentacle.xCurl;
      }
   }

   private void setYCurl(float curl) {
      this.tentacle.yCurl = curl;

      for (TentaclePartEntity<nonamecrackers2.witherstormmod.common.entity.TentacleEntity> part : this.tentacle.getChained()) {
         part.yCurl = this.tentacle.yCurl;
      }
   }

   private void setXOffset(float rot) {
      this.tentacle.xRotOffset = rot;

      for (TentaclePartEntity<nonamecrackers2.witherstormmod.common.entity.TentacleEntity> part : this.tentacle.getChained()) {
         part.xRotOffset = this.tentacle.xRotOffset;
      }
   }

   private void setYOffset(float rot) {
      this.tentacle.yRotOffset = rot;

      for (TentaclePartEntity<nonamecrackers2.witherstormmod.common.entity.TentacleEntity> part : this.tentacle.getChained()) {
         part.yRotOffset = this.tentacle.yRotOffset;
      }
   }

   public void setSavedXCurl(float rot) {
      this.f_19804_.m_135381_(XCURL, rot);
      this.setXCurl(rot);
   }

   public void setSavedYCurl(float rot) {
      this.f_19804_.m_135381_(YCURL, rot);
      this.setYCurl(rot);
   }

   public void setSavedXOffset(float rot) {
      this.f_19804_.m_135381_(XOFFSET, rot);
      this.setXOffset(rot);
   }

   public void setSavedYOffset(float rot) {
      this.f_19804_.m_135381_(YOFFSET, rot);
      this.setYOffset(rot);
   }

   public float getXOffset() {
      return (Float)this.f_19804_.m_135370_(XOFFSET);
   }

   public float getYOffset() {
      return (Float)this.f_19804_.m_135370_(YOFFSET);
   }

   public float getXCurl() {
      return (Float)this.f_19804_.m_135370_(XCURL);
   }

   public float getYCurl() {
      return (Float)this.f_19804_.m_135370_(YCURL);
   }

   public void doSwingAttack(LivingEntity target) {
      this.tentacleSwingTime = 40;
   }

   public boolean isDoingSwingAttack() {
      return this.tentacleSwingTime > 0;
   }

   public boolean canDoSwingAttack() {
      return this.nextSwing <= 0 && !this.isDoingSwingAttack();
   }

   public void doSwingAnimation(Vec3 target, float overReach, int steps) {
      Vec3 delta = target.m_82546_(this.m_20182_());
      float atan2 = (float)Mth.m_14136_(delta.f_82479_, delta.f_82481_);
      float angle = atan2 * (180.0F / (float)Math.PI);
      this.lerpBaseYTo(-(angle + 360.0F - 90.0F + overReach + Mth.m_14177_(this.getYOffset())) % 360.0F, steps, false);
      this.lerpCurlTo(0.1F, 0.1F, 4);
      this.m_5496_((SoundEvent)WitherStormModSoundEvents.WHOOSH.get(), 3.0F, 1.0F);
   }

   public void stopSwingAnimation(boolean setBaseY) {
      if (setBaseY) {
         this.setYOffset(this.tentacle.yRotOffset);
         this.f_19804_.m_135381_(YOFFSET, this.tentacle.yRotOffset);
      }

      this.lerpBaseYTo(0.0F, 12, true);
      this.lerpCurlTo(0.0F, 0.0F, 4);
   }

   public void doDeathAnimation() {
      this.lerpCurlTo(0.3F, 0.1F, 3);
      this.lerpBaseXTo(70.0F, 8);
   }

   public void m_6667_(DamageSource source) {
      super.m_6667_(source);
      this.doDeathAnimation();
      this.m_20153_();
   }

   public void doAwakeAnimation() {
      this.tentacleAnimSpeed = 6.0F;
      this.tentacleAnimReach = 4.0F;
      this.lerpCurlYTo(0.05F * (float)this.f_19796_.m_188583_(), 8);
      this.awakeTime = 40;
      this.m_9236_().m_7605_(this, (byte)13);
      this.m_5496_((SoundEvent)WitherStormModSoundEvents.WHOOSH.get(), 3.0F, 1.0F);
   }

   public void doIndefiniteAwakeAnimation() {
      if (!this.m_9236_().f_46443_) {
         this.doAwakeAnimation();
      }

      this.awakeTime = 0;
      this.m_9236_().m_7605_(this, (byte)14);
   }

   public void stopAwakeAnimation() {
      this.tentacleAnimSpeed = 1.0F;
      this.tentacleAnimReach = 1.0F;
      this.lerpCurlYTo(0.0F, 4);
   }

   protected void m_6153_() {
      this.f_20919_++;
      if (this.f_20919_ >= 40) {
         this.m_142687_(RemovalReason.KILLED);

         for (int i = 0; i < 40; i++) {
            double d0 = this.f_19796_.m_188583_() * 0.02;
            double d1 = this.f_19796_.m_188583_() * 0.02;
            double d2 = this.f_19796_.m_188583_() * 0.02;
            this.m_9236_().m_7106_(ParticleTypes.f_123759_, this.m_20208_(0.5), this.m_20187_(), this.m_20262_(0.5), d0, d1, d2);
         }
      }
   }

   public void doStrangle() {
      this.strangleTime = 20;
      this.tentacleAnimSpeed = 15.0F;
      this.m_9236_().m_7605_(this, (byte)11);
   }

   public void stopStrangle() {
      this.strangleTime = 0;
      this.tentacleAnimSpeed = 1.0F;
      this.m_9236_().m_7605_(this, (byte)12);
   }

   public void m_7822_(byte event) {
      if (event == 11) {
         this.doStrangle();
      } else if (event == 12) {
         this.stopStrangle();
      } else if (event == 13) {
         this.doAwakeAnimation();
      } else if (event == 14) {
         this.doIndefiniteAwakeAnimation();
      } else {
         super.m_7822_(event);
      }
   }

   public void lerpBaseOffsetTo(float x, float y, int steps) {
      this.f_19804_.m_135381_(XOFFSETANIM, x);
      this.f_19804_.m_135381_(YOFFSETANIM, y);
      this.f_19804_.m_135381_(OFFSETSTEPS, steps);
   }

   public void lerpCurlTo(float x, float y, int steps) {
      this.f_19804_.m_135381_(XCURLANIM, x);
      this.f_19804_.m_135381_(YCURLANIM, y);
      this.f_19804_.m_135381_(CURLSTEPS, steps);
   }

   public void lerpBaseXTo(float x, int steps) {
      this.f_19804_.m_135381_(XOFFSETANIM, x);
      this.f_19804_.m_135381_(OFFSETSTEPS, steps);
   }

   public void lerpBaseYTo(float y, int steps, boolean wrap) {
      this.f_19804_.m_135381_(YOFFSETANIM, y);
      this.f_19804_.m_135381_(OFFSETSTEPS, steps);
      this.f_19804_.m_135381_(SHOULDWRAPY, wrap);
   }

   public void lerpCurlXTo(float x, int steps) {
      this.f_19804_.m_135381_(XCURLANIM, x);
      this.f_19804_.m_135381_(CURLSTEPS, steps);
   }

   public void lerpCurlYTo(float y, int steps) {
      this.f_19804_.m_135381_(YCURLANIM, y);
      this.f_19804_.m_135381_(CURLSTEPS, steps);
   }

   public AABB m_6921_() {
      return this.m_20191_().m_82400_(4.0);
   }

   public boolean m_6087_() {
      return false;
   }

   protected void m_6138_() {
   }

   public SpawnGroupData m_6518_(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, SpawnGroupData existing, CompoundTag data) {
      this.setSavedYOffset(this.f_19796_.m_188503_(360));
      this.setSavedXOffset(15.0F + this.f_19796_.m_188501_() * 5.0F);
      this.setSavedXCurl(1.25F + this.f_19796_.m_188501_() * 0.1F);
      this.setAnimationOffset(this.f_19796_.m_188503_(35) * 10000);
      return super.m_6518_(world, difficulty, reason, existing, data);
   }

   public void setCanStrangle(boolean canStrangle) {
      this.canStrangle = canStrangle;
      if (!canStrangle) {
         this.f_21345_.m_25363_(this.strangleGoal);
      } else {
         this.f_21345_.m_25363_(this.strangleGoal);
         this.f_21345_.m_25352_(2, this.strangleGoal);
      }
   }

   public boolean canStrangle() {
      return this.canStrangle;
   }

   public void setCanSwing(boolean canSwing) {
      this.canSwing = canSwing;
      if (!canSwing) {
         this.f_21345_.m_25363_(this.swingGoal);
      } else {
         this.f_21345_.m_25363_(this.swingGoal);
         this.f_21345_.m_25352_(1, this.swingGoal);
      }
   }

   public boolean canSwing() {
      return this.canSwing;
   }

   public void curlAround(Vec3 pos) {
      Vec3 delta = pos.m_82546_(this.m_20182_());
      float atan2 = (float)Mth.m_14136_(delta.f_82479_, delta.f_82481_);
      float angle = atan2 * (180.0F / (float)Math.PI);
      this.lerpBaseYTo(-(angle + 180.0F + this.getYOffset()), 8, false);
      this.lerpCurlTo(0.1F, 0.1F, 4);
   }

   public void stopCurlingAround() {
      this.lerpBaseYTo(0.0F, 8, true);
      this.lerpCurlTo(0.0F, 0.0F, 4);
   }

   public void m_141965_(ClientboundAddEntityPacket packet) {
      super.m_141965_(packet);
      PartEntity<?>[] parts = this.getParts();

      for (int i = 0; i < parts.length; i++) {
         parts[i].m_20234_(1 + i + packet.m_131496_());
      }
   }

   public PushReaction m_7752_() {
      return PushReaction.IGNORE;
   }

   public boolean m_6779_(LivingEntity entity) {
      return super.m_6779_(entity) && !(entity instanceof WitherSickened);
   }

   private static class DoNothingGoal extends Goal {
      private final nonamecrackers2.witherstormmod.common.entity.TentacleEntity entity;

      public DoNothingGoal(nonamecrackers2.witherstormmod.common.entity.TentacleEntity entity) {
         this.entity = entity;
         this.m_7021_(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP, Flag.TARGET));
      }

      public boolean m_8036_() {
         return this.entity.isDormant();
      }
   }

   private static class StrangleGoal extends Goal {
      private final nonamecrackers2.witherstormmod.common.entity.TentacleEntity entity;
      private int grabWait;
      private int nextStrangle;

      public StrangleGoal(nonamecrackers2.witherstormmod.common.entity.TentacleEntity entity) {
         this.entity = entity;
      }

      public boolean m_8036_() {
         LivingEntity target = this.entity.m_5448_();
         return !this.entity.isDormant() && target != null && target.m_6084_() && this.entity.m_6084_() && !this.entity.isDoingSwingAttack();
      }

      public void m_8037_() {
         LivingEntity target = this.entity.m_5448_();
         if (!this.entity.m_142582_(target)) {
            target.m_8127_();
            this.entity.m_6710_(null);
         } else {
            this.grabWait++;
            if (this.grabWait > 5) {
               target.m_20329_(this.entity);
            }

            if (this.nextStrangle > 0) {
               this.nextStrangle--;
               if (this.nextStrangle <= 0) {
                  this.entity.doStrangle();
                  this.nextStrangle = 20 + this.entity.m_217043_().m_188503_(40);
                  this.entity.m_7327_(target);
               }
            }
         }
      }

      public void m_8056_() {
         LivingEntity target = this.entity.m_5448_();
         this.entity.doSwingAnimation(target.m_20182_(), 0.0F, 4);
         this.entity.f_19804_.m_135381_(nonamecrackers2.witherstormmod.common.entity.TentacleEntity.LASTXCURLANIM, 0.3F);
         this.nextStrangle = 20 + this.entity.m_217043_().m_188503_(40);
      }

      public void m_8041_() {
         this.entity.m_20153_();
         this.grabWait = 0;
         this.entity.stopSwingAnimation(true);
         this.entity.stopStrangle();
         this.entity.f_19804_.m_135381_(nonamecrackers2.witherstormmod.common.entity.TentacleEntity.LASTXCURLANIM, 0.0F);
      }
   }

   private static class SwingGoal extends Goal {
      private final nonamecrackers2.witherstormmod.common.entity.TentacleEntity entity;

      public SwingGoal(nonamecrackers2.witherstormmod.common.entity.TentacleEntity entity) {
         this.entity = entity;
      }

      public boolean m_8036_() {
         LivingEntity target = this.entity.m_5448_();
         return !this.entity.isDormant()
            && target != null
            && target.m_6084_()
            && this.entity.m_6084_()
            && (this.entity.m_21223_() < this.entity.m_21233_() || !this.entity.canStrangle)
            && this.entity.canDoSwingAttack();
      }

      public boolean m_8045_() {
         LivingEntity target = this.entity.m_5448_();
         return target != null && target.m_6084_() && this.entity.m_6084_() && this.entity.isDoingSwingAttack();
      }

      public void m_8056_() {
         LivingEntity target = this.entity.m_5448_();
         this.entity.doSwingAttack(target);
         this.entity.nextSwing = 120 + this.entity.m_217043_().m_188503_(120);
      }
   }

   public static class TargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
      public TargetGoal(Mob entity, Class<T> targetType, boolean mustSee, boolean mustReach) {
         super(entity, targetType, 10, mustSee, mustReach, e -> !(e instanceof Enemy));
      }

      protected AABB m_7255_(double range) {
         return this.f_26135_.m_20191_().m_82400_(range);
      }

      protected void m_26073_() {
         super.m_26073_();

         for (Entity entity : this.f_26135_
            .m_9236_()
            .m_45976_(nonamecrackers2.witherstormmod.common.entity.TentacleEntity.class, this.m_7255_(this.m_7623_() + 10.0))) {
            nonamecrackers2.witherstormmod.common.entity.TentacleEntity tentacle = (nonamecrackers2.witherstormmod.common.entity.TentacleEntity)entity;
            if (tentacle.m_6084_() && tentacle.m_5448_() == this.f_26050_) {
               this.f_26050_ = null;
               break;
            }
         }
      }
   }

   public static class UpdateAnimationMessage extends nonamecrackers2.crackerslib.common.packet.Packet {
      private int id;
      private int anim;

      public UpdateAnimationMessage(int entityId, int anim) {
         super(true);
         this.id = entityId;
         this.anim = anim;
      }

      public UpdateAnimationMessage() {
         super(false);
      }

      public void encode(FriendlyByteBuf buffer) {
         buffer.m_130130_(this.id);
         buffer.writeInt(this.anim);
      }

      public void decode(FriendlyByteBuf buffer) {
         this.id = buffer.m_130242_();
         this.anim = buffer.readInt();
      }

      public Runnable getProcessor(Context context) {
         return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            Optional<Level> optional = (Optional<Level>)LogicalSidedProvider.CLIENTWORLD.get(context.getDirection().getReceptionSide());
            optional.ifPresent(world -> {
               if (world.m_6815_(this.id) instanceof nonamecrackers2.witherstormmod.common.entity.TentacleEntity tentacle) {
                  tentacle.tentacleAnim = this.anim;
               }
            });
         });
      }
   }
}

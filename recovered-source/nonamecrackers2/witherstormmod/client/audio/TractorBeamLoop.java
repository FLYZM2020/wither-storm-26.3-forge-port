package nonamecrackers2.witherstormmod.client.audio;

import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;

public class TractorBeamLoop<T extends LivingEntity> extends AbstractTickableSoundInstance implements IForceStoppableSound {
   public static double DISTANCE_REQUIRED = 30.0;
   protected final T entity;
   protected final LocalPlayer player;
   private double prevX;
   private double prevY;
   private double prevZ;
   @Nullable
   private final Predicate<T> shouldStop;

   public TractorBeamLoop(T entity, Predicate<T> shouldStop) {
      super((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_TRACTOR_BEAM.get(), SoundSource.AMBIENT, SoundInstance.m_235150_());
      this.entity = entity;
      Minecraft mc = Minecraft.m_91087_();
      this.player = mc.f_91074_;
      this.f_119578_ = true;
      this.f_119579_ = 0;
      this.f_119573_ = 0.0F;
      this.shouldStop = shouldStop;
   }

   public TractorBeamLoop(T entity) {
      this(entity, null);
   }

   public void m_7788_() {
      this.prevX = this.f_119575_;
      this.prevY = this.f_119576_;
      this.prevZ = this.f_119577_;
      Vec3 closest = this.calculateClosestPoint();
      if (closest != null) {
         this.f_119575_ = Mth.m_14139_(0.1, this.prevX, closest.f_82479_);
         this.f_119576_ = Mth.m_14139_(0.1, this.prevY, closest.f_82480_);
         this.f_119577_ = Mth.m_14139_(0.1, this.prevZ, closest.f_82481_);
         this.calculateVolume();
      }

      double distance = Math.sqrt(this.player.m_20238_(closest));
      if (this.shouldStop(distance)) {
         this.m_119609_();
      }
   }

   public void setPos(Vec3 vec) {
      this.prevX = vec.f_82479_;
      this.prevY = vec.f_82480_;
      this.prevZ = vec.f_82481_;
      this.f_119575_ = vec.f_82479_;
      this.f_119576_ = vec.f_82480_;
      this.f_119577_ = vec.f_82481_;
   }

   protected Vec3 calculateClosestPoint() {
      return TractorBeamHelper.calculateClosestPoint(this.player, this.entity);
   }

   protected float getDistance(Vec3 origin) {
      float x = (float)(origin.f_82479_ - this.player.m_20185_());
      float y = (float)(origin.f_82480_ - this.player.m_20186_());
      float z = (float)(origin.f_82481_ - this.player.m_20189_());
      return Mth.m_14116_(x * x + y * y + z * z);
   }

   public void forceStop() {
      this.m_119609_();
   }

   public T getEntity() {
      return this.entity;
   }

   public boolean shouldStop(double distance) {
      return (this.entity instanceof WitherStormBase storm ? storm.isDeadOrPlayingDead() : this.entity.m_21224_())
         || !this.entity.m_6084_()
         || distance > DISTANCE_REQUIRED
         || this.shouldStop != null && this.shouldStop.test(this.entity);
   }

   public void calculateVolume() {
      this.f_119573_ = Math.max(0.0F, 0.3F - this.getDistance(new Vec3(this.f_119575_, this.f_119576_, this.f_119577_)) / 60.0F);
   }

   public boolean m_7784_() {
      return true;
   }
}

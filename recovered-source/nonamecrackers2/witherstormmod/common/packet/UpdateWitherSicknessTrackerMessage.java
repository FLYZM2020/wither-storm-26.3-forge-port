package nonamecrackers2.witherstormmod.common.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent.Context;
import nonamecrackers2.crackerslib.common.packet.Packet;
import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;

public class UpdateWitherSicknessTrackerMessage extends Packet {
   private int id;
   private int requiredProximityTicks;
   private int applicationDelay;
   private int cureDelay;
   private int proximityTicksModifier;
   private int applicationDelayModifier;
   private int cureDelayModifier;
   private int proximityTicks;
   private int delayTicks;
   private int contacts;
   private int totalInfections;
   private int multiplierDecreaseTicks;
   private int multiplier;
   private int contactsDecreaseTicks;
   private int cureDelayTicks;
   private int totalCures;
   private boolean isBeingCured;
   private boolean isInfected;
   private boolean isNearStorm;
   private boolean isActuallyImmune;

   public UpdateWitherSicknessTrackerMessage(Entity entity) {
      super(true);
      this.id = entity.m_19879_();
      entity.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(tracker -> {
         this.requiredProximityTicks = tracker.getRawRequiredProximityTicks();
         this.applicationDelay = tracker.getRawApplicationDelay();
         this.cureDelay = tracker.getRawCureDelay();
         this.proximityTicksModifier = tracker.getRequiredProximityTicksModifier();
         this.applicationDelayModifier = tracker.getApplicationDelayModifier();
         this.proximityTicks = tracker.getProximityTicks();
         this.delayTicks = tracker.getDelayTicks();
         this.contacts = tracker.getContacts();
         this.totalInfections = tracker.getTotalInfections();
         this.multiplierDecreaseTicks = tracker.getAmplifierDecreaseTicks();
         this.multiplier = tracker.getBaseMultiplier();
         this.contactsDecreaseTicks = tracker.getContactsDecreaseTicks();
         this.isInfected = tracker.isInfected();
         this.isNearStorm = tracker.isNearStorm();
         this.cureDelayModifier = tracker.getCureDelayModifier();
         this.cureDelayTicks = tracker.getCureDelayTicks();
         this.totalCures = tracker.getTotalCures();
         this.isBeingCured = tracker.isBeingCured();
         this.isActuallyImmune = tracker.isActuallyImmune();
      });
   }

   public UpdateWitherSicknessTrackerMessage() {
      super(false);
   }

   public int getId() {
      return this.id;
   }

   public int getProximityTicksModifier() {
      return this.proximityTicksModifier;
   }

   public int getApplicationDelayModifier() {
      return this.applicationDelayModifier;
   }

   public int getProximityTicks() {
      return this.proximityTicks;
   }

   public int getDelayTicks() {
      return this.delayTicks;
   }

   public int getContacts() {
      return this.contacts;
   }

   public int getTotalInfections() {
      return this.totalInfections;
   }

   public int getMultiplierDecreaseTicks() {
      return this.multiplierDecreaseTicks;
   }

   public int getMultiplier() {
      return this.multiplier;
   }

   public int getContactsDecreaseTicks() {
      return this.contactsDecreaseTicks;
   }

   public boolean isInfected() {
      return this.isInfected;
   }

   public boolean isNearStorm() {
      return this.isNearStorm;
   }

   public int getCureDelayModifier() {
      return this.cureDelayModifier;
   }

   public int getCureDelayTicks() {
      return this.cureDelayTicks;
   }

   public int getTotalCures() {
      return this.totalCures;
   }

   public boolean isBeingCured() {
      return this.isBeingCured;
   }

   public int getRequiredProximityTicks() {
      return this.requiredProximityTicks;
   }

   public int getApplicationDelay() {
      return this.applicationDelay;
   }

   public int getCureDelay() {
      return this.cureDelay;
   }

   public boolean isActuallyImmune() {
      return this.isActuallyImmune;
   }

   public void decode(FriendlyByteBuf buffer) {
      this.id = buffer.m_130242_();
      this.requiredProximityTicks = buffer.m_130242_();
      this.applicationDelay = buffer.m_130242_();
      this.cureDelay = buffer.m_130242_();
      this.proximityTicksModifier = buffer.m_130242_();
      this.applicationDelayModifier = buffer.m_130242_();
      this.proximityTicks = buffer.m_130242_();
      this.delayTicks = buffer.m_130242_();
      this.contacts = buffer.m_130242_();
      this.totalInfections = buffer.m_130242_();
      this.multiplierDecreaseTicks = buffer.m_130242_();
      this.multiplier = buffer.m_130242_();
      this.contactsDecreaseTicks = buffer.m_130242_();
      this.isInfected = buffer.readBoolean();
      this.isNearStorm = buffer.readBoolean();
      this.cureDelayModifier = buffer.m_130242_();
      this.cureDelayTicks = buffer.m_130242_();
      this.totalCures = buffer.m_130242_();
      this.isBeingCured = buffer.readBoolean();
      this.isActuallyImmune = buffer.readBoolean();
   }

   public void encode(FriendlyByteBuf buffer) {
      buffer.m_130130_(this.id);
      buffer.m_130130_(this.requiredProximityTicks);
      buffer.m_130130_(this.applicationDelay);
      buffer.m_130130_(this.cureDelay);
      buffer.m_130130_(this.proximityTicksModifier);
      buffer.m_130130_(this.applicationDelayModifier);
      buffer.m_130130_(this.proximityTicks);
      buffer.m_130130_(this.delayTicks);
      buffer.m_130130_(this.contacts);
      buffer.m_130130_(this.totalInfections);
      buffer.m_130130_(this.multiplierDecreaseTicks);
      buffer.m_130130_(this.multiplier);
      buffer.m_130130_(this.contactsDecreaseTicks);
      buffer.writeBoolean(this.isInfected);
      buffer.writeBoolean(this.isNearStorm);
      buffer.m_130130_(this.cureDelayModifier);
      buffer.m_130130_(this.cureDelayTicks);
      buffer.m_130130_(this.totalCures);
      buffer.writeBoolean(this.isBeingCured);
      buffer.writeBoolean(this.isActuallyImmune);
   }

   public Runnable getProcessor(Context context) {
      return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> WitherStormModMessageHandlerClient.processUpdateWitherSicknessTrackerMessage(this));
   }

   public String toString() {
      return "UpdateWitherSicknessTrackerMessage[id="
         + this.id
         + ", requiredProximityTicks"
         + this.requiredProximityTicks
         + ", applicationDelay"
         + this.applicationDelay
         + ", cureDelay"
         + this.cureDelay
         + ", proximityTicksModifier="
         + this.proximityTicksModifier
         + ", applicationDelayModifier="
         + this.applicationDelayModifier
         + ", proximityTicks="
         + this.proximityTicks
         + ", delayTicks="
         + this.delayTicks
         + ", contacts= "
         + this.contacts
         + ", totalInfections= "
         + this.totalInfections
         + ", multiplierDecreaseTicks= "
         + this.multiplierDecreaseTicks
         + ", multiplier= "
         + this.multiplier
         + ", contactsDecreaseTicks= "
         + this.contactsDecreaseTicks
         + ", isInfected="
         + this.isInfected
         + ", isNearStorm="
         + this.isNearStorm
         + ", cureDelayModifier="
         + this.cureDelayModifier
         + ", cureDelayTicks="
         + this.cureDelayTicks
         + ", totalCures="
         + this.totalCures
         + ", isBeingCured="
         + this.isBeingCured
         + ", isActuallyImmune"
         + this.isActuallyImmune
         + "]";
   }
}

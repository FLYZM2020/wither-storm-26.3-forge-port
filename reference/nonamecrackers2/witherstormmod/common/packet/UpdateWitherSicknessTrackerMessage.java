/*     */ package nonamecrackers2.witherstormmod.common.packet;
/*     */ 
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.fml.DistExecutor;
/*     */ import net.minecraftforge.network.NetworkEvent;
/*     */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*     */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*     */ import nonamecrackers2.witherstormmod.common.capability.WitherSicknessTracker;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*     */ 
/*     */ 
/*     */ public class UpdateWitherSicknessTrackerMessage
/*     */   extends Packet
/*     */ {
/*     */   private int id;
/*     */   private int requiredProximityTicks;
/*     */   private int applicationDelay;
/*     */   private int cureDelay;
/*     */   private int proximityTicksModifier;
/*     */   private int applicationDelayModifier;
/*     */   private int cureDelayModifier;
/*     */   private int proximityTicks;
/*     */   private int delayTicks;
/*     */   private int contacts;
/*     */   private int totalInfections;
/*     */   private int multiplierDecreaseTicks;
/*     */   private int multiplier;
/*     */   private int contactsDecreaseTicks;
/*     */   private int cureDelayTicks;
/*     */   private int totalCures;
/*     */   private boolean isBeingCured;
/*     */   private boolean isInfected;
/*     */   private boolean isNearStorm;
/*     */   private boolean isActuallyImmune;
/*     */   
/*     */   public UpdateWitherSicknessTrackerMessage(Entity entity) {
/*  39 */     super(true);
/*  40 */     this.id = entity.m_19879_();
/*  41 */     entity.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(tracker -> {
/*     */           this.requiredProximityTicks = tracker.getRawRequiredProximityTicks();
/*     */           this.applicationDelay = tracker.getRawApplicationDelay();
/*     */           this.cureDelay = tracker.getRawCureDelay();
/*     */           this.proximityTicksModifier = tracker.getRequiredProximityTicksModifier();
/*     */           this.applicationDelayModifier = tracker.getApplicationDelayModifier();
/*     */           this.proximityTicks = tracker.getProximityTicks();
/*     */           this.delayTicks = tracker.getDelayTicks();
/*     */           this.contacts = tracker.getContacts();
/*     */           this.totalInfections = tracker.getTotalInfections();
/*     */           this.multiplierDecreaseTicks = tracker.getAmplifierDecreaseTicks();
/*     */           this.multiplier = tracker.getBaseMultiplier();
/*     */           this.contactsDecreaseTicks = tracker.getContactsDecreaseTicks();
/*     */           this.isInfected = tracker.isInfected();
/*     */           this.isNearStorm = tracker.isNearStorm();
/*     */           this.cureDelayModifier = tracker.getCureDelayModifier();
/*     */           this.cureDelayTicks = tracker.getCureDelayTicks();
/*     */           this.totalCures = tracker.getTotalCures();
/*     */           this.isBeingCured = tracker.isBeingCured();
/*     */           this.isActuallyImmune = tracker.isActuallyImmune();
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public UpdateWitherSicknessTrackerMessage() {
/*  67 */     super(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  72 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getProximityTicksModifier() {
/*  77 */     return this.proximityTicksModifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getApplicationDelayModifier() {
/*  82 */     return this.applicationDelayModifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getProximityTicks() {
/*  87 */     return this.proximityTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDelayTicks() {
/*  92 */     return this.delayTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getContacts() {
/*  97 */     return this.contacts;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTotalInfections() {
/* 102 */     return this.totalInfections;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMultiplierDecreaseTicks() {
/* 107 */     return this.multiplierDecreaseTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMultiplier() {
/* 112 */     return this.multiplier;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getContactsDecreaseTicks() {
/* 117 */     return this.contactsDecreaseTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInfected() {
/* 122 */     return this.isInfected;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNearStorm() {
/* 127 */     return this.isNearStorm;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCureDelayModifier() {
/* 132 */     return this.cureDelayModifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCureDelayTicks() {
/* 137 */     return this.cureDelayTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTotalCures() {
/* 142 */     return this.totalCures;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBeingCured() {
/* 147 */     return this.isBeingCured;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRequiredProximityTicks() {
/* 152 */     return this.requiredProximityTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getApplicationDelay() {
/* 157 */     return this.applicationDelay;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCureDelay() {
/* 162 */     return this.cureDelay;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isActuallyImmune() {
/* 167 */     return this.isActuallyImmune;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void decode(FriendlyByteBuf buffer) {
/* 173 */     this.id = buffer.m_130242_();
/* 174 */     this.requiredProximityTicks = buffer.m_130242_();
/* 175 */     this.applicationDelay = buffer.m_130242_();
/* 176 */     this.cureDelay = buffer.m_130242_();
/* 177 */     this.proximityTicksModifier = buffer.m_130242_();
/* 178 */     this.applicationDelayModifier = buffer.m_130242_();
/* 179 */     this.proximityTicks = buffer.m_130242_();
/* 180 */     this.delayTicks = buffer.m_130242_();
/* 181 */     this.contacts = buffer.m_130242_();
/* 182 */     this.totalInfections = buffer.m_130242_();
/* 183 */     this.multiplierDecreaseTicks = buffer.m_130242_();
/* 184 */     this.multiplier = buffer.m_130242_();
/* 185 */     this.contactsDecreaseTicks = buffer.m_130242_();
/* 186 */     this.isInfected = buffer.readBoolean();
/* 187 */     this.isNearStorm = buffer.readBoolean();
/* 188 */     this.cureDelayModifier = buffer.m_130242_();
/* 189 */     this.cureDelayTicks = buffer.m_130242_();
/* 190 */     this.totalCures = buffer.m_130242_();
/* 191 */     this.isBeingCured = buffer.readBoolean();
/* 192 */     this.isActuallyImmune = buffer.readBoolean();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void encode(FriendlyByteBuf buffer) {
/* 198 */     buffer.m_130130_(this.id);
/* 199 */     buffer.m_130130_(this.requiredProximityTicks);
/* 200 */     buffer.m_130130_(this.applicationDelay);
/* 201 */     buffer.m_130130_(this.cureDelay);
/* 202 */     buffer.m_130130_(this.proximityTicksModifier);
/* 203 */     buffer.m_130130_(this.applicationDelayModifier);
/* 204 */     buffer.m_130130_(this.proximityTicks);
/* 205 */     buffer.m_130130_(this.delayTicks);
/* 206 */     buffer.m_130130_(this.contacts);
/* 207 */     buffer.m_130130_(this.totalInfections);
/* 208 */     buffer.m_130130_(this.multiplierDecreaseTicks);
/* 209 */     buffer.m_130130_(this.multiplier);
/* 210 */     buffer.m_130130_(this.contactsDecreaseTicks);
/* 211 */     buffer.writeBoolean(this.isInfected);
/* 212 */     buffer.writeBoolean(this.isNearStorm);
/* 213 */     buffer.m_130130_(this.cureDelayModifier);
/* 214 */     buffer.m_130130_(this.cureDelayTicks);
/* 215 */     buffer.m_130130_(this.totalCures);
/* 216 */     buffer.writeBoolean(this.isBeingCured);
/* 217 */     buffer.writeBoolean(this.isActuallyImmune);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 223 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 229 */     return "UpdateWitherSicknessTrackerMessage[id=" + this.id + ", requiredProximityTicks" + this.requiredProximityTicks + ", applicationDelay" + this.applicationDelay + ", cureDelay" + this.cureDelay + ", proximityTicksModifier=" + 
/*     */ 
/*     */ 
/*     */       
/* 233 */       String.valueOf(this.proximityTicksModifier) + ", applicationDelayModifier=" + 
/* 234 */       String.valueOf(this.applicationDelayModifier) + ", proximityTicks=" + 
/* 235 */       String.valueOf(this.proximityTicks) + ", delayTicks=" + 
/* 236 */       String.valueOf(this.delayTicks) + ", contacts= " + 
/* 237 */       String.valueOf(this.contacts) + ", totalInfections= " + 
/* 238 */       String.valueOf(this.totalInfections) + ", multiplierDecreaseTicks= " + 
/* 239 */       String.valueOf(this.multiplierDecreaseTicks) + ", multiplier= " + 
/* 240 */       String.valueOf(this.multiplier) + ", contactsDecreaseTicks= " + 
/* 241 */       String.valueOf(this.contactsDecreaseTicks) + ", isInfected=" + 
/* 242 */       String.valueOf(this.isInfected) + ", isNearStorm=" + 
/* 243 */       String.valueOf(this.isNearStorm) + ", cureDelayModifier=" + 
/* 244 */       String.valueOf(this.cureDelayModifier) + ", cureDelayTicks=" + 
/* 245 */       String.valueOf(this.cureDelayTicks) + ", totalCures=" + 
/* 246 */       String.valueOf(this.totalCures) + ", isBeingCured=" + 
/* 247 */       String.valueOf(this.isBeingCured) + ", isActuallyImmune" + 
/* 248 */       String.valueOf(this.isActuallyImmune) + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\UpdateWitherSicknessTrackerMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package nonamecrackers2.witherstormmod.common.packet;
/*     */ 
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.fml.DistExecutor;
/*     */ import net.minecraftforge.network.NetworkEvent;
/*     */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*     */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager;
/*     */ 
/*     */ public class UpdatePlayDeadManagerMessage
/*     */   extends Packet {
/*     */   private int entityId;
/*  14 */   private PlayDeadManager.State state = PlayDeadManager.State.NORMAL_BEHAVIOR;
/*     */   
/*     */   private int ticks;
/*     */   private int revivalTicks;
/*     */   private boolean hasRecentlyBeenRevived;
/*     */   private boolean updateTick;
/*     */   private int revivalPlayerProtection;
/*     */   
/*     */   public UpdatePlayDeadManagerMessage(int id, PlayDeadManager manager, boolean updateTick) {
/*  23 */     super(true);
/*  24 */     this.entityId = id;
/*  25 */     this.state = manager.getState();
/*  26 */     this.ticks = manager.getTicks();
/*  27 */     this.revivalTicks = manager.getTicksSinceRevival();
/*  28 */     this.hasRecentlyBeenRevived = manager.hasRecentlyBeenRevived();
/*  29 */     this.updateTick = updateTick;
/*  30 */     this.revivalPlayerProtection = manager.getRevivalPlayerProtectionTime();
/*     */   }
/*     */ 
/*     */   
/*     */   public UpdatePlayDeadManagerMessage() {
/*  35 */     super(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEntityID() {
/*  40 */     return this.entityId;
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayDeadManager.State getState() {
/*  45 */     return this.state;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTicks() {
/*  50 */     return this.ticks;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTicksSinceRevival() {
/*  55 */     return this.revivalTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasRecentlyBeenRevived() {
/*  60 */     return this.hasRecentlyBeenRevived;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldUpdateTick() {
/*  65 */     return this.updateTick;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRevivalPlayerProtectionTime() {
/*  70 */     return this.revivalPlayerProtection;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void decode(FriendlyByteBuf buffer) {
/*  76 */     this.entityId = buffer.m_130242_();
/*  77 */     this.state = (PlayDeadManager.State)buffer.m_130066_(PlayDeadManager.State.class);
/*  78 */     this.ticks = buffer.readInt();
/*  79 */     this.revivalTicks = buffer.readInt();
/*  80 */     this.hasRecentlyBeenRevived = buffer.readBoolean();
/*  81 */     this.updateTick = buffer.readBoolean();
/*  82 */     this.revivalPlayerProtection = buffer.readInt();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void encode(FriendlyByteBuf buffer) {
/*  88 */     buffer.m_130130_(this.entityId);
/*  89 */     buffer.m_130068_((Enum)this.state);
/*  90 */     buffer.writeInt(this.ticks);
/*  91 */     buffer.writeInt(this.revivalTicks);
/*  92 */     buffer.writeBoolean(this.hasRecentlyBeenRevived);
/*  93 */     buffer.writeBoolean(this.updateTick);
/*  94 */     buffer.writeInt(this.revivalPlayerProtection);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 100 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 106 */     return "UpdatePlayDeadManagerMessage[id=" + this.entityId + ", state=" + this.state
/* 107 */       .toString() + ", ticks=" + this.ticks + ", updateTick=" + this.updateTick + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\UpdatePlayDeadManagerMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
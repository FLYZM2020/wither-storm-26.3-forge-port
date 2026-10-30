/*    */ package nonamecrackers2.witherstormmod.common.packet;
/*    */ 
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.world.effect.MobEffect;
/*    */ import net.minecraft.world.effect.MobEffectInstance;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.fml.DistExecutor;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*    */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*    */ 
/*    */ public class UpdateEffectInstanceMessage
/*    */   extends Packet
/*    */ {
/*    */   private int entityId;
/*    */   private byte effectId;
/*    */   private byte amplifier;
/*    */   private int duration;
/*    */   private boolean showDuration;
/*    */   
/*    */   public UpdateEffectInstanceMessage(int entityId, MobEffectInstance effect, boolean showDuration) {
/* 22 */     super(true);
/* 23 */     this.entityId = entityId;
/* 24 */     this.effectId = (byte)(MobEffect.m_19459_(effect.m_19544_()) & 0xFF);
/* 25 */     this.amplifier = (byte)(effect.m_19564_() & 0xFF);
/* 26 */     if (effect.m_19557_() > 32767) {
/* 27 */       this.duration = 32767;
/*    */     } else {
/* 29 */       this.duration = effect.m_19557_();
/* 30 */     }  this.showDuration = showDuration;
/*    */   }
/*    */ 
/*    */   
/*    */   public UpdateEffectInstanceMessage() {
/* 35 */     super(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getEntityID() {
/* 40 */     return this.entityId;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte getEffectID() {
/* 45 */     return this.effectId;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte getAmplifier() {
/* 50 */     return this.amplifier;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDuration() {
/* 55 */     return this.duration;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldShowDuration() {
/* 60 */     return this.showDuration;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 66 */     buffer.m_130130_(this.entityId);
/* 67 */     buffer.writeByte(this.effectId);
/* 68 */     buffer.writeByte(this.amplifier);
/* 69 */     buffer.m_130130_(this.duration);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) {
/* 75 */     this.entityId = buffer.m_130242_();
/* 76 */     this.effectId = buffer.readByte();
/* 77 */     this.amplifier = buffer.readByte();
/* 78 */     this.duration = buffer.m_130242_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 84 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 90 */     return "UpdateEffectInstanceMessage[id=" + this.entityId + ", effectId=" + this.effectId + ", amplifer=" + this.amplifier + ", duration=" + this.duration + ", showDuration=" + this.showDuration + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\UpdateEffectInstanceMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
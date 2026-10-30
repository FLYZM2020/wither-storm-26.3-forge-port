/*    */ package nonamecrackers2.witherstormmod.common.packet;
/*    */ 
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.fml.DistExecutor;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*    */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*    */ 
/*    */ public class GlobalSoundMessage
/*    */   extends Packet
/*    */ {
/*    */   private SoundEvent event;
/*    */   private float pitch;
/*    */   private float volume;
/*    */   
/*    */   public GlobalSoundMessage(SoundEvent event, float volume, float pitch) {
/* 21 */     super(true);
/* 22 */     this.event = event;
/* 23 */     this.pitch = pitch;
/* 24 */     this.volume = volume;
/*    */   }
/*    */ 
/*    */   
/*    */   public GlobalSoundMessage() {
/* 29 */     super(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public SoundEvent getSoundEvent() {
/* 34 */     return this.event;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPitch() {
/* 39 */     return this.pitch;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getVolume() {
/* 44 */     return this.volume;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 50 */     buffer.m_130070_(ForgeRegistries.SOUND_EVENTS.getKey(this.event).toString());
/* 51 */     buffer.writeFloat(this.pitch);
/* 52 */     buffer.writeFloat(this.volume);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) {
/* 58 */     this.event = (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(buffer.m_130277_()));
/* 59 */     this.pitch = buffer.readFloat();
/* 60 */     this.volume = buffer.readFloat();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 66 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 72 */     return "GlobalSoundMessage[sound_event=" + ForgeRegistries.SOUND_EVENTS.getKey(this.event).toString() + ", pitch=" + String.valueOf(this.pitch) + ", volume=" + String.valueOf(this.volume) + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\GlobalSoundMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
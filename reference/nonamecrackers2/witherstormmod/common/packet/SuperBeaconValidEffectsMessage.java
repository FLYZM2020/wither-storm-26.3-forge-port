/*    */ package nonamecrackers2.witherstormmod.common.packet;
/*    */ 
/*    */ import java.util.Set;
/*    */ import java.util.function.IntFunction;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.world.effect.MobEffect;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.fml.DistExecutor;
/*    */ import net.minecraftforge.network.NetworkEvent;
/*    */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*    */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*    */ 
/*    */ 
/*    */ public class SuperBeaconValidEffectsMessage
/*    */   extends Packet
/*    */ {
/*    */   private Set<MobEffect> effects;
/*    */   
/*    */   public SuperBeaconValidEffectsMessage(Set<MobEffect> effects) {
/* 20 */     super(true);
/* 21 */     this.effects = effects;
/*    */   }
/*    */ 
/*    */   
/*    */   public SuperBeaconValidEffectsMessage() {
/* 26 */     super(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MobEffect> getEffects() {
/* 31 */     return this.effects;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) {
/* 37 */     this.effects = (Set<MobEffect>)buffer.m_236838_(java.util.HashSet::new, buf -> MobEffect.m_19453_(buf.m_130242_()));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 43 */     buffer.m_236828_(this.effects, (buf, effect) -> buf.m_130130_(MobEffect.m_19459_(effect)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 49 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\SuperBeaconValidEffectsMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
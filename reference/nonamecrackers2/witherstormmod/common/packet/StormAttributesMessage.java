/*     */ package nonamecrackers2.witherstormmod.common.packet;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeInstance;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.fml.DistExecutor;
/*     */ import net.minecraftforge.network.NetworkEvent;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*     */ 
/*     */ 
/*     */ public class StormAttributesMessage
/*     */   extends DistantRendererMessage
/*     */ {
/*     */   private int entityId;
/*  23 */   private List<ClientboundUpdateAttributesPacket.AttributeSnapshot> attributes = Lists.newArrayList();
/*     */ 
/*     */   
/*     */   public StormAttributesMessage(List<Integer> applicable, int id, Collection<AttributeInstance> attributes) {
/*  27 */     super(true, applicable);
/*  28 */     this.entityId = id;
/*  29 */     for (AttributeInstance attribute : attributes) {
/*  30 */       this.attributes.add(new ClientboundUpdateAttributesPacket.AttributeSnapshot(attribute.m_22099_(), attribute.m_22115_(), attribute.m_22122_()));
/*     */     }
/*     */   }
/*     */   
/*     */   public StormAttributesMessage(List<Integer> applicable, int id, List<ClientboundUpdateAttributesPacket.AttributeSnapshot> attributes) {
/*  35 */     super(true, applicable);
/*  36 */     this.entityId = id;
/*  37 */     this.attributes = attributes;
/*     */   }
/*     */ 
/*     */   
/*     */   public StormAttributesMessage() {
/*  42 */     super(false, Lists.newArrayList());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEntityID() {
/*  47 */     return this.entityId;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<ClientboundUpdateAttributesPacket.AttributeSnapshot> getAttributes() {
/*  52 */     return this.attributes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void encode(FriendlyByteBuf buffer) {
/*  58 */     super.encode(buffer);
/*  59 */     buffer.m_130130_(this.entityId);
/*  60 */     buffer.m_236828_(this.attributes, (buffer1, snapshot) -> {
/*     */           buffer1.m_130085_(ForgeRegistries.ATTRIBUTES.getKey(snapshot.m_133601_()));
/*     */           buffer1.writeDouble(snapshot.m_133602_());
/*     */           buffer1.m_236828_(snapshot.m_133603_(), ());
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void decode(FriendlyByteBuf buffer) {
/*  76 */     super.decode(buffer);
/*  77 */     this.entityId = buffer.m_130242_();
/*  78 */     this.attributes = buffer.m_236845_(buffer1 -> {
/*     */           ResourceLocation location = buffer1.m_130281_();
/*     */           Attribute attribute = (Attribute)ForgeRegistries.ATTRIBUTES.getValue(location);
/*     */           double base = buffer1.readDouble();
/*     */           List<AttributeModifier> list = buffer1.m_236845_(());
/*     */           return new ClientboundUpdateAttributesPacket.AttributeSnapshot(attribute, base, list);
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Runnable getProcessor(NetworkEvent.Context context) {
/*  94 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 100 */     return "StormAttributesMessage[id=" + this.entityId + ", properties=" + this.attributes.toString() + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\StormAttributesMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
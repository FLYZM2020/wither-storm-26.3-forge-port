/*     */ package nonamecrackers2.witherstormmod.common.packet;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
/*     */ import net.minecraft.network.syncher.SynchedEntityData;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeInstance;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.entity.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.fml.DistExecutor;
/*     */ import net.minecraftforge.network.NetworkEvent;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.HeadManager;
/*     */ 
/*     */ public class WitherStormToDistantRendererMessage
/*     */   extends DistantRendererMessage
/*     */ {
/*     */   private final WitherStormEntity entity;
/*     */   private int id;
/*     */   private UUID uuid;
/*     */   private ResourceLocation type;
/*     */   private Vec3 pos;
/*     */   private Vec3i delta;
/*     */   private byte yRot;
/*     */   private byte xRot;
/*     */   private byte yHeadRot;
/*     */   private HeadManager.PackedHeadRots rots;
/*     */   private List<SynchedEntityData.DataValue<?>> packedItems;
/*  41 */   private final List<ClientboundUpdateAttributesPacket.AttributeSnapshot> attributes = Lists.newArrayList();
/*     */   
/*     */   private FriendlyByteBuf buffer;
/*     */   
/*     */   public WitherStormToDistantRendererMessage(List<Integer> applicable, WitherStormEntity entity) {
/*  46 */     super(true, applicable);
/*  47 */     this.entity = entity;
/*  48 */     this.id = entity.m_19879_();
/*  49 */     this.uuid = entity.m_20148_();
/*  50 */     this.type = ForgeRegistries.ENTITY_TYPES.getKey(entity.m_6095_());
/*  51 */     this.pos = new Vec3(entity.m_20185_(), entity.m_20186_(), entity.m_20189_());
/*  52 */     this.yRot = (byte)(int)(entity.m_146908_() * 256.0F / 360.0F);
/*  53 */     this.xRot = (byte)(int)(entity.m_146909_() * 256.0F / 360.0F);
/*  54 */     this.yHeadRot = (byte)(int)(entity.f_20885_ * 256.0F / 360.0F);
/*  55 */     this.rots = entity.getHeadManager().packHeadRotations();
/*  56 */     Vec3 vector3d = entity.m_20184_();
/*     */ 
/*     */ 
/*     */     
/*  60 */     Vec3i delta = new Vec3i(Mth.m_14107_(Mth.m_14008_(vector3d.f_82479_, -3.9D, 3.9D)), Mth.m_14107_(Mth.m_14008_(vector3d.f_82480_, -3.9D, 3.9D)), Mth.m_14107_(Mth.m_14008_(vector3d.f_82481_, -3.9D, 3.9D)));
/*     */     
/*  62 */     this.delta = delta;
/*  63 */     SynchedEntityData data = entity.m_20088_();
/*  64 */     this.packedItems = getPackedData(data);
/*  65 */     for (AttributeInstance attribute : entity.m_21204_().m_22170_())
/*  66 */       this.attributes.add(new ClientboundUpdateAttributesPacket.AttributeSnapshot(attribute.m_22099_(), attribute.m_22115_(), attribute.m_22122_())); 
/*  67 */     this.buffer = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public WitherStormToDistantRendererMessage() {
/*  72 */     super(false, Lists.newArrayList());
/*  73 */     this.entity = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  78 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public UUID getUUID() {
/*  83 */     return this.uuid;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getType() {
/*  88 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3 getPos() {
/*  93 */     return this.pos;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3i getDeltaMovement() {
/*  98 */     return this.delta;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getYRot() {
/* 103 */     return this.yRot;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getXRot() {
/* 108 */     return this.xRot;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getHeadYRot() {
/* 113 */     return this.yHeadRot;
/*     */   }
/*     */ 
/*     */   
/*     */   public HeadManager.PackedHeadRots getRots() {
/* 118 */     return this.rots;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<SynchedEntityData.DataValue<?>> getUnpackedData() {
/* 123 */     return this.packedItems;
/*     */   }
/*     */ 
/*     */   
/*     */   public FriendlyByteBuf getBuffer() {
/* 128 */     return this.buffer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void decode(FriendlyByteBuf buffer) {
/* 134 */     super.decode(buffer);
/* 135 */     this.id = buffer.m_130242_();
/* 136 */     this.uuid = buffer.m_130259_();
/* 137 */     this.type = buffer.m_130281_();
/* 138 */     double x = buffer.readDouble();
/* 139 */     double y = buffer.readDouble();
/* 140 */     double z = buffer.readDouble();
/* 141 */     this.pos = new Vec3(x, y, z);
/* 142 */     this.yRot = buffer.readByte();
/* 143 */     this.xRot = buffer.readByte();
/* 144 */     this.yHeadRot = buffer.readByte();
/* 145 */     this.rots = HeadManager.PackedHeadRots.fromPacket(buffer);
/* 146 */     int xd = buffer.readShort();
/* 147 */     int yd = buffer.readShort();
/* 148 */     int zd = buffer.readShort();
/* 149 */     this.delta = new Vec3i(xd, yd, zd);
/* 150 */     List<SynchedEntityData.DataValue<?>> packedData = Lists.newArrayList();
/*     */     int j;
/* 152 */     while ((j = buffer.readUnsignedByte()) != 255)
/* 153 */       packedData.add(SynchedEntityData.DataValue.m_252860_(buffer, j)); 
/* 154 */     this.packedItems = packedData;
/* 155 */     int attributeSize = buffer.readInt();
/* 156 */     for (int i = 0; i < attributeSize; i++) {
/*     */       
/* 158 */       ResourceLocation location = buffer.m_130281_();
/* 159 */       Attribute attribute = (Attribute)ForgeRegistries.ATTRIBUTES.getValue(location);
/* 160 */       double base = buffer.readDouble();
/* 161 */       List<AttributeModifier> list = Lists.newArrayList();
/* 162 */       int modifierSize = buffer.m_130242_();
/* 163 */       for (int l = 0; l < modifierSize; l++) {
/*     */         
/* 165 */         UUID uuid = buffer.m_130259_();
/* 166 */         list.add(new AttributeModifier(uuid, "Unknown synced attribute modifier", buffer.readDouble(), AttributeModifier.Operation.m_22236_(buffer.readByte())));
/*     */       } 
/*     */       
/* 169 */       this.attributes.add(new ClientboundUpdateAttributesPacket.AttributeSnapshot(attribute, base, list));
/*     */     } 
/* 171 */     int count = buffer.m_130242_();
/* 172 */     if (count > 0) {
/*     */       
/* 174 */       FriendlyByteBuf extra = new FriendlyByteBuf(Unpooled.buffer());
/* 175 */       extra.writeBytes((ByteBuf)buffer, count);
/* 176 */       this.buffer = extra;
/*     */     }
/*     */     else {
/*     */       
/* 180 */       this.buffer = new FriendlyByteBuf(Unpooled.buffer());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void encode(FriendlyByteBuf buffer) {
/* 187 */     super.encode(buffer);
/* 188 */     buffer.m_130130_(this.id);
/* 189 */     buffer.m_130077_(this.uuid);
/* 190 */     buffer.m_130085_(this.type);
/* 191 */     buffer.writeDouble(this.pos.f_82479_);
/* 192 */     buffer.writeDouble(this.pos.f_82480_);
/* 193 */     buffer.writeDouble(this.pos.f_82481_);
/* 194 */     buffer.writeByte(this.yRot);
/* 195 */     buffer.writeByte(this.xRot);
/* 196 */     buffer.writeByte(this.yHeadRot);
/* 197 */     this.rots.toPacket(buffer);
/* 198 */     buffer.writeShort(this.delta.m_123341_());
/* 199 */     buffer.writeShort(this.delta.m_123342_());
/* 200 */     buffer.writeShort(this.delta.m_123343_());
/* 201 */     for (SynchedEntityData.DataValue<?> value : this.packedItems)
/* 202 */       value.m_252897_(buffer); 
/* 203 */     buffer.writeByte(255);
/* 204 */     buffer.writeInt(this.attributes.size());
/* 205 */     for (ClientboundUpdateAttributesPacket.AttributeSnapshot snapshot : this.attributes) {
/*     */       
/* 207 */       buffer.m_130085_(ForgeRegistries.ATTRIBUTES.getKey(snapshot.m_133601_()));
/* 208 */       buffer.writeDouble(snapshot.m_133602_());
/* 209 */       buffer.m_130130_(snapshot.m_133603_().size());
/* 210 */       for (AttributeModifier modifier : snapshot.m_133603_()) {
/*     */         
/* 212 */         buffer.m_130077_(modifier.m_22209_());
/* 213 */         buffer.writeDouble(modifier.m_22218_());
/* 214 */         buffer.writeByte(modifier.m_22217_().m_22235_());
/*     */       } 
/*     */     } 
/* 217 */     FriendlyByteBuf extra = new FriendlyByteBuf(Unpooled.buffer());
/* 218 */     WitherStormEntity witherStormEntity = this.entity; if (witherStormEntity instanceof IEntityAdditionalSpawnData) { IEntityAdditionalSpawnData e = (IEntityAdditionalSpawnData)witherStormEntity;
/* 219 */       e.writeSpawnData(extra); }
/* 220 */      this.entity.writeData(extra);
/* 221 */     buffer.m_130130_(extra.readableBytes());
/* 222 */     buffer.writeBytes((ByteBuf)extra);
/* 223 */     extra.release();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Runnable getProcessor(NetworkEvent.Context context) {
/* 229 */     return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 235 */     return "WitherStormToDistantRendererMessage[id=" + this.id + ", uuid=" + 
/* 236 */       String.valueOf(this.uuid) + ", type=" + 
/* 237 */       String.valueOf(this.type) + ", x=" + 
/* 238 */       String.valueOf(this.pos.f_82479_) + ", y=" + 
/* 239 */       String.valueOf(this.pos.f_82480_) + ", z=" + 
/* 240 */       String.valueOf(this.pos.f_82481_) + ", yRot= " + 
/* 241 */       String.valueOf(this.yRot) + ", xRot= " + 
/* 242 */       String.valueOf(this.xRot) + ", yHeadRot= " + 
/* 243 */       String.valueOf(this.yHeadRot) + ", headRots= " + this.rots
/* 244 */       .toString() + ", xd=" + 
/* 245 */       String.valueOf(this.delta.m_123341_()) + ", yd=" + 
/* 246 */       String.valueOf(this.delta.m_123342_()) + ", zd=" + 
/* 247 */       String.valueOf(this.delta.m_123343_()) + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\WitherStormToDistantRendererMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package nonamecrackers2.witherstormmod.common.serializer;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.network.syncher.EntityDataSerializer;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.Vec2;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.registries.DeferredRegister;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
/*     */ import nonamecrackers2.witherstormmod.api.common.registry.WitherStormModRegistries;
/*     */ import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*     */ import nonamecrackers2.witherstormmod.common.util.WitherStormModNBTUtil;
/*     */ 
/*     */ 
/*     */ public class WitherStormModDataSerializers
/*     */ {
/*  28 */   public static final DeferredRegister<EntityDataSerializer<?>> DATA_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.ENTITY_DATA_SERIALIZERS, "witherstormmod");
/*     */   
/*  30 */   public static final EntityDataSerializer<Map<BlockPos, BlockState>> BLOCK_STATE_POS_MAP = new EntityDataSerializer<Map<BlockPos, BlockState>>()
/*     */     {
/*     */       
/*     */       public void write(FriendlyByteBuf buffer, Map<BlockPos, BlockState> map)
/*     */       {
/*  35 */         buffer.m_236831_(map, (buf, pos) -> buf.m_130064_(pos), (buf, state) -> buf.m_130130_(Block.m_49956_(state)));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public Map<BlockPos, BlockState> read(FriendlyByteBuf buffer) {
/*  41 */         return buffer.m_236847_(buf -> buf.m_130135_(), buf -> Block.m_49803_(buf.m_130242_()));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public Map<BlockPos, BlockState> copy(Map<BlockPos, BlockState> map) {
/*  47 */         return new HashMap<>(map);
/*     */       }
/*     */     };
/*     */   
/*  51 */   public static final EntityDataSerializer<List<CompoundTag>> COMPOUND_LIST = new EntityDataSerializer<List<CompoundTag>>()
/*     */     {
/*     */ 
/*     */       
/*     */       public void write(FriendlyByteBuf buffer, List<CompoundTag> list)
/*     */       {
/*  57 */         CompoundTag compound = new CompoundTag();
/*  58 */         compound.m_128365_("List", (Tag)WitherStormModNBTUtil.writeCompoundList(list));
/*  59 */         buffer.m_130079_(compound);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public List<CompoundTag> read(FriendlyByteBuf buffer) {
/*  65 */         CompoundTag compound = buffer.m_130260_();
/*  66 */         return WitherStormModNBTUtil.readCompoundList(compound.m_128437_("List", 10));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public List<CompoundTag> copy(List<CompoundTag> list) {
/*  72 */         return new ArrayList<>(list);
/*     */       }
/*     */     };
/*     */   
/*  76 */   public static final EntityDataSerializer<Vec2> VECTOR_2F = new EntityDataSerializer<Vec2>()
/*     */     {
/*     */       
/*     */       public void write(FriendlyByteBuf buffer, Vec2 vector)
/*     */       {
/*  81 */         buffer.writeFloat(vector.f_82470_);
/*  82 */         buffer.writeFloat(vector.f_82471_);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public Vec2 read(FriendlyByteBuf buffer) {
/*  88 */         return new Vec2(buffer.readFloat(), buffer.readFloat());
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public Vec2 copy(Vec2 vector) {
/*  94 */         return new Vec2(vector.f_82470_, vector.f_82471_);
/*     */       }
/*     */     };
/*     */   
/*  98 */   public static final EntityDataSerializer<Optional<Vec3>> OPTIONAL_VECTOR_3D = new EntityDataSerializer<Optional<Vec3>>()
/*     */     {
/*     */       
/*     */       public void write(FriendlyByteBuf buffer, Optional<Vec3> vector)
/*     */       {
/* 103 */         buffer.writeBoolean(vector.isPresent());
/* 104 */         vector.ifPresent(pos -> {
/*     */               buffer.writeDouble(pos.m_7096_());
/*     */               buffer.writeDouble(pos.m_7098_());
/*     */               buffer.writeDouble(pos.m_7094_());
/*     */             });
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       public Optional<Vec3> read(FriendlyByteBuf buffer) {
/* 115 */         return buffer.readBoolean() ? Optional.<Vec3>of(new Vec3(buffer.readDouble(), buffer.readDouble(), buffer.readDouble())) : Optional.<Vec3>empty();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public Optional<Vec3> copy(Optional<Vec3> vector) {
/* 121 */         return vector;
/*     */       }
/*     */     };
/*     */   
/* 125 */   public static final EntityDataSerializer<SpellType> SPELL_TYPE = new EntityDataSerializer<SpellType>()
/*     */     {
/*     */       
/*     */       public void write(FriendlyByteBuf buffer, SpellType type)
/*     */       {
/* 130 */         buffer.writeRegistryId(WitherStormModRegistries.SPELL_TYPES.get(), type);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public SpellType read(FriendlyByteBuf buffer) {
/* 136 */         return (SpellType)buffer.readRegistryId();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public SpellType copy(SpellType type) {
/* 142 */         return type;
/*     */       }
/*     */     };
/*     */   
/* 146 */   public static final EnumDataSerializer<CommandBlockEntity.Mode> MODE_ENUM = new EnumDataSerializer<>(CommandBlockEntity.Mode.class);
/* 147 */   public static final EnumDataSerializer<CommandBlockEntity.State> STATE_ENUM = new EnumDataSerializer<>(CommandBlockEntity.State.class);
/* 148 */   public static final EnumDataSerializer<WitheredSymbiontEntity.BossfightStage> BOSSFIGHT_STAGE_ENUM = new EnumDataSerializer<>(WitheredSymbiontEntity.BossfightStage.class);
/*     */ 
/*     */   
/*     */   static {
/* 152 */     DATA_SERIALIZERS.register("block_state_pos_map", () -> BLOCK_STATE_POS_MAP);
/* 153 */     DATA_SERIALIZERS.register("compound_list", () -> COMPOUND_LIST);
/* 154 */     DATA_SERIALIZERS.register("vector2f", () -> VECTOR_2F);
/* 155 */     DATA_SERIALIZERS.register("mode_enum", () -> MODE_ENUM);
/* 156 */     DATA_SERIALIZERS.register("state_enum", () -> STATE_ENUM);
/* 157 */     DATA_SERIALIZERS.register("bossfight_state_enum", () -> BOSSFIGHT_STAGE_ENUM);
/* 158 */     DATA_SERIALIZERS.register("vector3d", () -> OPTIONAL_VECTOR_3D);
/* 159 */     DATA_SERIALIZERS.register("spell_type", () -> SPELL_TYPE);
/*     */   }
/*     */   
/*     */   public static class EnumDataSerializer<T extends Enum<T>>
/*     */     implements EntityDataSerializer<T>
/*     */   {
/*     */     private final Class<T> enumClass;
/*     */     
/*     */     private EnumDataSerializer(Class<T> enumClass) {
/* 168 */       this.enumClass = enumClass;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void write(FriendlyByteBuf buffer, T enub) {
/* 174 */       buffer.m_130068_((Enum)enub);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public T read(FriendlyByteBuf buffer) {
/* 180 */       return (T)buffer.m_130066_(this.enumClass);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public T copy(T enub) {
/* 186 */       return enub;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\serializer\WitherStormModDataSerializers.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
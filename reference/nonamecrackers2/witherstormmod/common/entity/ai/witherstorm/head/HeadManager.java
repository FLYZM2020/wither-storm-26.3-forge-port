/*     */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.network.syncher.EntityDataAccessor;
/*     */ import net.minecraft.network.syncher.EntityDataSerializers;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.SegmentsManager;
/*     */ import nonamecrackers2.witherstormmod.common.serializer.WitherStormModDataSerializers;
/*     */ import nonamecrackers2.witherstormmod.common.util.HeadConfiguration;
/*     */ 
/*     */ 
/*     */ public class HeadManager
/*     */ {
/*     */   public static final int TOTAL_HEADS = 3;
/*  26 */   public static final List<EntityDataAccessor<Boolean>> HEAD_ROARS = createDataAccessors(() -> WitherStormEntity.registerDataAccessor(EntityDataSerializers.f_135035_, ()));
/*  27 */   public static final List<EntityDataAccessor<Boolean>> HEADS_BITING = createDataAccessors(() -> WitherStormEntity.registerDataAccessor(EntityDataSerializers.f_135035_, ()));
/*  28 */   public static final List<EntityDataAccessor<Optional<Vec3>>> TARGETS = createDataAccessors(() -> WitherStormEntity.registerDataAccessor(WitherStormModDataSerializers.OPTIONAL_VECTOR_3D, Optional::empty));
/*  29 */   public static final List<EntityDataAccessor<Integer>> HURT_HEAD_TIME = createDataAccessors(() -> WitherStormEntity.registerDataAccessor(EntityDataSerializers.f_135028_, ()));
/*  30 */   public static final List<EntityDataAccessor<Integer>> INJURE_ATTEMPT_COOLDOWN = createDataAccessors(() -> WitherStormEntity.registerDataAccessor(EntityDataSerializers.f_135028_, ()));
/*  31 */   public static final List<EntityDataAccessor<Integer>> LOOK_STEPS = createDataAccessors(() -> WitherStormEntity.registerDataAccessor(EntityDataSerializers.f_135028_, ()));
/*  32 */   public static final EntityDataAccessor<Integer> INJURY_TIME = WitherStormEntity.registerDataAccessor(EntityDataSerializers.f_135028_, () -> Integer.valueOf(320));
/*  33 */   private static final EntityDataAccessor<Boolean> OTHER_HEADS_DISABLED = WitherStormEntity.registerDataAccessor(EntityDataSerializers.f_135035_, () -> Boolean.valueOf(false));
/*     */   private final List<HeadConfiguration> configurations;
/*     */   private final WitherStormEntity storm;
/*  36 */   private final Int2ObjectMap<WitherStormHead> heads = (Int2ObjectMap<WitherStormHead>)new Int2ObjectOpenHashMap();
/*     */ 
/*     */   
/*     */   public HeadManager(WitherStormEntity storm, List<HeadConfiguration> configurations) {
/*  40 */     this.storm = storm;
/*  41 */     if (configurations.isEmpty())
/*  42 */       throw new IllegalArgumentException("Head configurations cannot be empty!"); 
/*  43 */     this.configurations = configurations;
/*  44 */     this.heads.put(0, new MainHead(storm, 0));
/*  45 */     for (int i = 1; i < storm.getTotalHeads(); i++) {
/*  46 */       this.heads.put(i, new AdditionalHead(storm, i));
/*     */     }
/*     */   }
/*     */   
/*     */   private static <T> List<EntityDataAccessor<T>> createDataAccessors(Supplier<EntityDataAccessor<T>> registrar) {
/*  51 */     ImmutableList.Builder<EntityDataAccessor<T>> list = ImmutableList.builderWithExpectedSize(3);
/*  52 */     for (int i = 0; i < 3; i++)
/*  53 */       list.add(registrar.get()); 
/*  54 */     return (List<EntityDataAccessor<T>>)list.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(int phase) {
/*  59 */     if (phase > 3) {
/*  60 */       setHeadInjuryTime(720);
/*     */     } else {
/*  62 */       setHeadInjuryTime(180);
/*  63 */     }  this.heads.forEach((i, head) -> head.update(phase));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void baseTick() {
/*  70 */     this.heads.forEach((i, head) -> head.baseTick(findCurrentConfig()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  77 */     this.heads.forEach((i, head) -> head.tick());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void aiStep() {
/*  84 */     this.heads.forEach((i, head) -> head.doAi());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void customServerAiStep() {
/*  91 */     this.heads.forEach((i, head) -> head.doServerAi());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HeadConfiguration findCurrentConfig() {
/*  98 */     for (HeadConfiguration configuration : this.configurations) {
/*     */       
/* 100 */       if (configuration.predicate().test(this.storm))
/* 101 */         return configuration; 
/*     */     } 
/* 103 */     return this.configurations.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public WitherStormHead getHead(int index) {
/* 108 */     return Objects.<WitherStormHead>requireNonNull((WitherStormHead)this.heads.get(index), "No head exists with index " + index);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<WitherStormHead> getHeads() {
/* 113 */     return (List<WitherStormHead>)ImmutableList.copyOf((Collection)this.heads.values());
/*     */   }
/*     */ 
/*     */   
/*     */   public List<AdditionalHead> getOtherHeads() {
/* 118 */     return this.heads.values().stream().filter(h -> h instanceof AdditionalHead).map(h -> (AdditionalHead)h).toList();
/*     */   }
/*     */ 
/*     */   
/*     */   public PackedHeadRots packHeadRotations() {
/* 123 */     byte[] xHeadsRot = new byte[this.heads.size()];
/* 124 */     byte[] yHeadsRot = new byte[this.heads.size()];
/* 125 */     for (WitherStormHead head : getHeads()) {
/*     */       
/* 127 */       if (head.syncHeadRotations()) {
/*     */         
/* 129 */         xHeadsRot[head.getIndex()] = (byte)Mth.m_14143_(head.getHeadXRot() * 256.0F / 360.0F);
/* 130 */         yHeadsRot[head.getIndex()] = (byte)Mth.m_14143_(head.getHeadYRot() * 256.0F / 360.0F);
/*     */       } 
/*     */     } 
/* 133 */     return new PackedHeadRots(xHeadsRot, yHeadsRot);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateHeadsFromPacked(PackedHeadRots rots) {
/* 138 */     for (int i = 0; i < this.heads.size(); i++) {
/*     */       
/* 140 */       WitherStormHead head = (WitherStormHead)this.heads.get(i);
/* 141 */       if (head.syncHeadRotations()) {
/*     */         
/* 143 */         float xRot = PackedHeadRots.unpack(rots.xRots[i]);
/* 144 */         float yRot = PackedHeadRots.unpack(rots.yRots[i]);
/* 145 */         head.setHeadXRot(xRot);
/* 146 */         head.setHeadYRot(yRot);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean areOtherHeadsDisabled() {
/* 153 */     return ((Boolean)this.storm.m_20088_().m_135370_(OTHER_HEADS_DISABLED)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOtherHeadsDisabled(boolean value) {
/* 158 */     this.storm.m_20088_().m_135381_(OTHER_HEADS_DISABLED, Boolean.valueOf(value));
/* 159 */     this.storm.getSegmentsManager().ifPresent(manager -> {
/*     */           WitherStormSegmentEntity[] segments = manager.getSegments();
/*     */           for (int i = 0; i < segments.length; i++) {
/*     */             if (segments[i] != null) {
/*     */               segments[i].setOtherHeadsDisabled(value);
/*     */             }
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHeadInjuryTime(int time) {
/* 172 */     this.storm.m_20088_().m_135381_(INJURY_TIME, Integer.valueOf(time));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeadInjuryTime() {
/* 177 */     return ((Integer)this.storm.m_20088_().m_135370_(INJURY_TIME)).intValue();
/*     */   }
/*     */   public static void bootstrap() {}
/*     */   public static final class PackedHeadRots extends Record { private final byte[] xRots; private final byte[] yRots;
/*     */     
/* 182 */     public PackedHeadRots(byte[] xRots, byte[] yRots) { this.xRots = xRots; this.yRots = yRots; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lnonamecrackers2/witherstormmod/common/entity/ai/witherstorm/head/HeadManager$PackedHeadRots;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #182	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 182 */       //   0	7	0	this	Lnonamecrackers2/witherstormmod/common/entity/ai/witherstorm/head/HeadManager$PackedHeadRots; } public byte[] xRots() { return this.xRots; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lnonamecrackers2/witherstormmod/common/entity/ai/witherstorm/head/HeadManager$PackedHeadRots;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #182	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lnonamecrackers2/witherstormmod/common/entity/ai/witherstorm/head/HeadManager$PackedHeadRots; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lnonamecrackers2/witherstormmod/common/entity/ai/witherstorm/head/HeadManager$PackedHeadRots;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #182	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lnonamecrackers2/witherstormmod/common/entity/ai/witherstorm/head/HeadManager$PackedHeadRots;
/* 182 */       //   0	8	1	o	Ljava/lang/Object; } public byte[] yRots() { return this.yRots; }
/*     */ 
/*     */     
/*     */     public void toPacket(FriendlyByteBuf buffer) {
/* 186 */       buffer.m_130087_(this.xRots);
/* 187 */       buffer.m_130087_(this.yRots);
/*     */     }
/*     */ 
/*     */     
/*     */     public static PackedHeadRots fromPacket(FriendlyByteBuf buffer) {
/* 192 */       byte[] xRots = buffer.m_130052_();
/* 193 */       byte[] yRots = buffer.m_130052_();
/* 194 */       return new PackedHeadRots(xRots, yRots);
/*     */     }
/*     */ 
/*     */     
/*     */     public static float unpack(byte b) {
/* 199 */       return (b * 360) / 256.0F;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\head\HeadManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
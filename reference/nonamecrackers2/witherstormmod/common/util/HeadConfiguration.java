/*    */ package nonamecrackers2.witherstormmod.common.util;
/*    */ public final class HeadConfiguration extends Record {
/*    */   private final Predicate<WitherStormEntity> predicate;
/*    */   private final Int2ObjectMap<Vec3> offsetsByHead;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lnonamecrackers2/witherstormmod/common/util/HeadConfiguration;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #14	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lnonamecrackers2/witherstormmod/common/util/HeadConfiguration;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lnonamecrackers2/witherstormmod/common/util/HeadConfiguration;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #14	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lnonamecrackers2/witherstormmod/common/util/HeadConfiguration;
/*    */   }
/*    */   
/* 14 */   public HeadConfiguration(Predicate<WitherStormEntity> predicate, Int2ObjectMap<Vec3> offsetsByHead) { this.predicate = predicate; this.offsetsByHead = offsetsByHead; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lnonamecrackers2/witherstormmod/common/util/HeadConfiguration;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #14	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lnonamecrackers2/witherstormmod/common/util/HeadConfiguration;
/* 14 */     //   0	8	1	o	Ljava/lang/Object; } public Predicate<WitherStormEntity> predicate() { return this.predicate; } public Int2ObjectMap<Vec3> offsetsByHead() { return this.offsetsByHead; }
/*    */ 
/*    */   
/*    */   public static Builder builder(Predicate<WitherStormEntity> predicate) {
/* 18 */     return new Builder(predicate);
/*    */   }
/*    */ 
/*    */   
/*    */   public static Builder forPhase(int phase) {
/* 23 */     return builder(storm -> (storm.getPhase() == phase));
/*    */   }
/*    */ 
/*    */   
/*    */   public Vec3 getOffsetForHead(int head) {
/* 28 */     return (Vec3)offsetsByHead().getOrDefault(head, Vec3.f_82478_);
/*    */   }
/*    */ 
/*    */   
/*    */   public static List<HeadConfiguration> makeSameFor(Consumer<Builder> consumer, int... phases) {
/* 33 */     ImmutableList.Builder<HeadConfiguration> configurations = ImmutableList.builder();
/* 34 */     for (int phase : phases) {
/*    */       
/* 36 */       Builder builder = forPhase(phase);
/* 37 */       consumer.accept(builder);
/* 38 */       configurations.add(builder.build());
/*    */     } 
/* 40 */     return (List<HeadConfiguration>)configurations.build();
/*    */   }
/*    */   
/*    */   public static class Builder
/*    */   {
/*    */     private final Predicate<WitherStormEntity> predicate;
/* 46 */     private final Int2ObjectMap<Vec3> offsetsByHead = (Int2ObjectMap<Vec3>)new Int2ObjectOpenHashMap();
/*    */ 
/*    */     
/*    */     private Builder(Predicate<WitherStormEntity> predicate) {
/* 50 */       this.predicate = predicate;
/*    */     }
/*    */ 
/*    */     
/*    */     public Builder addOffset(int head, double x, double y, double z) {
/* 55 */       this.offsetsByHead.put(head, new Vec3(x, y, z));
/* 56 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public HeadConfiguration build() {
/* 61 */       return new HeadConfiguration(this.predicate, this.offsetsByHead);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\HeadConfiguration.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
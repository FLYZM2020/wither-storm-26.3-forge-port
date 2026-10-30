/*   */ package nonamecrackers2.witherstormmod.common.resources.taint;public final class MobConversion extends Record { private final EntityType<?> from;
/*   */   private final EntityType<?> to;
/*   */   private final boolean canBeConvertedFromWitherSickness;
/*   */   
/* 5 */   public MobConversion(EntityType<?> from, EntityType<?> to, boolean canBeConvertedFromWitherSickness) { this.from = from; this.to = to; this.canBeConvertedFromWitherSickness = canBeConvertedFromWitherSickness; } public final String toString() { // Byte code:
/*   */     //   0: aload_0
/*   */     //   1: <illegal opcode> toString : (Lnonamecrackers2/witherstormmod/common/resources/taint/MobConversion;)Ljava/lang/String;
/*   */     //   6: areturn
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #5	-> 0
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/* 5 */     //   0	7	0	this	Lnonamecrackers2/witherstormmod/common/resources/taint/MobConversion; } public EntityType<?> from() { return this.from; } public final int hashCode() { // Byte code:
/*   */     //   0: aload_0
/*   */     //   1: <illegal opcode> hashCode : (Lnonamecrackers2/witherstormmod/common/resources/taint/MobConversion;)I
/*   */     //   6: ireturn
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #5	-> 0
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/*   */     //   0	7	0	this	Lnonamecrackers2/witherstormmod/common/resources/taint/MobConversion; } public final boolean equals(Object o) { // Byte code:
/*   */     //   0: aload_0
/*   */     //   1: aload_1
/*   */     //   2: <illegal opcode> equals : (Lnonamecrackers2/witherstormmod/common/resources/taint/MobConversion;Ljava/lang/Object;)Z
/*   */     //   7: ireturn
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #5	-> 0
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/*   */     //   0	8	0	this	Lnonamecrackers2/witherstormmod/common/resources/taint/MobConversion;
/* 5 */     //   0	8	1	o	Ljava/lang/Object; } public EntityType<?> to() { return this.to; } public boolean canBeConvertedFromWitherSickness() { return this.canBeConvertedFromWitherSickness; }
/*   */    }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\resources\taint\MobConversion.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
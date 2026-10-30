/*    */ package nonamecrackers2.witherstormmod.api.common.ai.symbiont;
/*    */ public final class SpellType extends Record {
/*    */   private final BiFunction<WitheredSymbiontEntity, SpellType, SymbiontSpell> spellFactory;
/*    */   private final int spellTime;
/*    */   private final Optional<Supplier<SoundEvent>> spellLoop;
/*    */   private final boolean doProtection;
/*    */   private final double protectionRadius;
/*    */   private final double protectionThrowStrength;
/*    */   
/* 10 */   public SpellType(BiFunction<WitheredSymbiontEntity, SpellType, SymbiontSpell> spellFactory, int spellTime, Optional<Supplier<SoundEvent>> spellLoop, boolean doProtection, double protectionRadius, double protectionThrowStrength) { this.spellFactory = spellFactory; this.spellTime = spellTime; this.spellLoop = spellLoop; this.doProtection = doProtection; this.protectionRadius = protectionRadius; this.protectionThrowStrength = protectionThrowStrength; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lnonamecrackers2/witherstormmod/api/common/ai/symbiont/SpellType;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #10	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 10 */     //   0	7	0	this	Lnonamecrackers2/witherstormmod/api/common/ai/symbiont/SpellType; } public BiFunction<WitheredSymbiontEntity, SpellType, SymbiontSpell> spellFactory() { return this.spellFactory; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lnonamecrackers2/witherstormmod/api/common/ai/symbiont/SpellType;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #10	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lnonamecrackers2/witherstormmod/api/common/ai/symbiont/SpellType; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lnonamecrackers2/witherstormmod/api/common/ai/symbiont/SpellType;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #10	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lnonamecrackers2/witherstormmod/api/common/ai/symbiont/SpellType;
/* 10 */     //   0	8	1	o	Ljava/lang/Object; } public int spellTime() { return this.spellTime; } public Optional<Supplier<SoundEvent>> spellLoop() { return this.spellLoop; } public boolean doProtection() { return this.doProtection; } public double protectionRadius() { return this.protectionRadius; } public double protectionThrowStrength() { return this.protectionThrowStrength; }
/*    */ 
/*    */   
/*    */   public SpellType(BiFunction<WitheredSymbiontEntity, SpellType, SymbiontSpell> spellFactory, int spellTime, Optional<Supplier<SoundEvent>> spellLoop, boolean doProtection) {
/* 14 */     this(spellFactory, spellTime, spellLoop, doProtection, 3.0D, 1.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public SpellType(BiFunction<WitheredSymbiontEntity, SpellType, SymbiontSpell> spellFactory, int spellTime, Optional<Supplier<SoundEvent>> spellLoop) {
/* 19 */     this(spellFactory, spellTime, spellLoop, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public SymbiontSpell makeSpell(WitheredSymbiontEntity symbiont) {
/* 24 */     return this.spellFactory.apply(symbiont, this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\api\common\ai\symbiont\SpellType.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
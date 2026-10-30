/*    */ package nonamecrackers2.witherstormmod.common.world.gen.feature;
/*    */ 
/*    */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
/*    */ import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class TemplateFeaturePiece
/*    */   extends Record
/*    */ {
/*    */   private final StructureTemplate template;
/*    */   private final StructurePlaceSettings settings;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lnonamecrackers2/witherstormmod/common/world/gen/feature/TemplateFeature$TemplateFeaturePiece;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #45	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lnonamecrackers2/witherstormmod/common/world/gen/feature/TemplateFeature$TemplateFeaturePiece;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lnonamecrackers2/witherstormmod/common/world/gen/feature/TemplateFeature$TemplateFeaturePiece;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #45	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lnonamecrackers2/witherstormmod/common/world/gen/feature/TemplateFeature$TemplateFeaturePiece;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lnonamecrackers2/witherstormmod/common/world/gen/feature/TemplateFeature$TemplateFeaturePiece;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #45	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lnonamecrackers2/witherstormmod/common/world/gen/feature/TemplateFeature$TemplateFeaturePiece;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public TemplateFeaturePiece(StructureTemplate template, StructurePlaceSettings settings) {
/* 45 */     this.template = template; this.settings = settings; } public StructureTemplate template() { return this.template; } public StructurePlaceSettings settings() { return this.settings; }
/*    */ 
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\world\gen\feature\TemplateFeature$TemplateFeaturePiece.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
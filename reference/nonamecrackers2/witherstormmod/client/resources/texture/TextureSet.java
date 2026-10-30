/*    */ package nonamecrackers2.witherstormmod.client.resources.texture;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ public final class TextureSet extends Record {
/*    */   private final ResourceLocation invulnerable;
/*    */   private final ResourceLocation main;
/*    */   private final ResourceLocation emissiveDecal;
/*    */   private final ResourceLocation debrisRing;
/*    */   
/* 12 */   public TextureSet(ResourceLocation invulnerable, ResourceLocation main, ResourceLocation emissiveDecal, ResourceLocation debrisRing) { this.invulnerable = invulnerable; this.main = main; this.emissiveDecal = emissiveDecal; this.debrisRing = debrisRing; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lnonamecrackers2/witherstormmod/client/resources/texture/TextureSet;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #12	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 12 */     //   0	7	0	this	Lnonamecrackers2/witherstormmod/client/resources/texture/TextureSet; } public ResourceLocation invulnerable() { return this.invulnerable; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lnonamecrackers2/witherstormmod/client/resources/texture/TextureSet;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #12	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lnonamecrackers2/witherstormmod/client/resources/texture/TextureSet; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lnonamecrackers2/witherstormmod/client/resources/texture/TextureSet;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #12	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lnonamecrackers2/witherstormmod/client/resources/texture/TextureSet;
/* 12 */     //   0	8	1	o	Ljava/lang/Object; } public ResourceLocation main() { return this.main; } public ResourceLocation emissiveDecal() { return this.emissiveDecal; } public ResourceLocation debrisRing() { return this.debrisRing; }
/*    */   
/* 14 */   public static final TextureSet DEFAULT = builder().build();
/*    */ 
/*    */   
/*    */   public static Builder builder() {
/* 18 */     return new Builder();
/*    */   }
/*    */ 
/*    */   
/*    */   public static TextureSet fromJson(JsonObject object) throws JsonSyntaxException {
/* 23 */     Builder builder = builder();
/* 24 */     Objects.requireNonNull(builder); applyTexture(builder::setInvulnerable, object, "invulnerable");
/* 25 */     Objects.requireNonNull(builder); applyTexture(builder::setMain, object, "main");
/* 26 */     Objects.requireNonNull(builder); applyTexture(builder::setEmissiveDecal, object, "emissive_decal");
/* 27 */     Objects.requireNonNull(builder); applyTexture(builder::setDebrisRing, object, "debris_ring");
/* 28 */     return builder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   private static void applyTexture(Consumer<ResourceLocation> consumer, JsonObject object, String path) throws JsonSyntaxException {
/* 33 */     if (object.has(path)) {
/*    */       
/* 35 */       String rawId = GsonHelper.m_13906_(object, path);
/* 36 */       ResourceLocation id = ResourceLocation.m_135820_(rawId);
/* 37 */       if (id != null) {
/* 38 */         consumer.accept(id);
/*    */       } else {
/* 40 */         throw new JsonSyntaxException("Not a valid id: '" + rawId + "'");
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class Builder {
/* 46 */     private ResourceLocation invulnerable = AbstractWitherStormRenderer.WITHER_STORM_INVULNERABLE_LOCATION;
/* 47 */     private ResourceLocation main = AbstractWitherStormRenderer.WITHER_STORM_LOCATION;
/* 48 */     private ResourceLocation emissiveDecal = AbstractWitherStormRenderer.EMISSIVE_DECAL;
/* 49 */     private ResourceLocation debrisRing = AbstractWitherStormRenderer.DEBRIS_RING;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public Builder setInvulnerable(ResourceLocation invulnerable) {
/* 55 */       this.invulnerable = invulnerable;
/* 56 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public Builder setMain(ResourceLocation main) {
/* 61 */       this.main = main;
/* 62 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public Builder setEmissiveDecal(ResourceLocation emissiveDecal) {
/* 67 */       this.emissiveDecal = emissiveDecal;
/* 68 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public Builder setDebrisRing(ResourceLocation debrisRing) {
/* 73 */       this.debrisRing = debrisRing;
/* 74 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public TextureSet build() {
/* 79 */       return new TextureSet(this.invulnerable, this.main, this.emissiveDecal, this.debrisRing);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\resources\texture\TextureSet.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
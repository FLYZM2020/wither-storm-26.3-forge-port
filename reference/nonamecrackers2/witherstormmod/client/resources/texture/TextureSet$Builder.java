/*    */ package nonamecrackers2.witherstormmod.client.resources.texture;
/*    */ 
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import nonamecrackers2.witherstormmod.client.renderer.entity.AbstractWitherStormRenderer;
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
/*    */ public class Builder
/*    */ {
/* 46 */   private ResourceLocation invulnerable = AbstractWitherStormRenderer.WITHER_STORM_INVULNERABLE_LOCATION;
/* 47 */   private ResourceLocation main = AbstractWitherStormRenderer.WITHER_STORM_LOCATION;
/* 48 */   private ResourceLocation emissiveDecal = AbstractWitherStormRenderer.EMISSIVE_DECAL;
/* 49 */   private ResourceLocation debrisRing = AbstractWitherStormRenderer.DEBRIS_RING;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Builder setInvulnerable(ResourceLocation invulnerable) {
/* 55 */     this.invulnerable = invulnerable;
/* 56 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public Builder setMain(ResourceLocation main) {
/* 61 */     this.main = main;
/* 62 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public Builder setEmissiveDecal(ResourceLocation emissiveDecal) {
/* 67 */     this.emissiveDecal = emissiveDecal;
/* 68 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public Builder setDebrisRing(ResourceLocation debrisRing) {
/* 73 */     this.debrisRing = debrisRing;
/* 74 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public TextureSet build() {
/* 79 */     return new TextureSet(this.invulnerable, this.main, this.emissiveDecal, this.debrisRing);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\resources\texture\TextureSet$Builder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
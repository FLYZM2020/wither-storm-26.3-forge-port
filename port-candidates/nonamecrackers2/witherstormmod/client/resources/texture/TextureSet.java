package nonamecrackers2.witherstormmod.client.resources.texture;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.function.Consumer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.GsonHelper;
import nonamecrackers2.witherstormmod.client.renderer.entity.AbstractWitherStormRenderer;

public record TextureSet(Identifier invulnerable, Identifier main, Identifier emissiveDecal, Identifier debrisRing) {
   public static final nonamecrackers2.witherstormmod.client.resources.texture.TextureSet DEFAULT = builder().build();

   public static nonamecrackers2.witherstormmod.client.resources.texture.TextureSet.Builder builder() {
      return new nonamecrackers2.witherstormmod.client.resources.texture.TextureSet.Builder();
   }

   public static nonamecrackers2.witherstormmod.client.resources.texture.TextureSet fromJson(JsonObject object) throws JsonSyntaxException {
      nonamecrackers2.witherstormmod.client.resources.texture.TextureSet.Builder builder = builder();
      applyTexture(builder::setInvulnerable, object, "invulnerable");
      applyTexture(builder::setMain, object, "main");
      applyTexture(builder::setEmissiveDecal, object, "emissive_decal");
      applyTexture(builder::setDebrisRing, object, "debris_ring");
      return builder.build();
   }

   private static void applyTexture(Consumer<Identifier> consumer, JsonObject object, String path) throws JsonSyntaxException {
      if (object.has(path)) {
         String rawId = GsonHelper.getAsString(object, path);
         Identifier id = Identifier.tryParse(rawId);
         if (id == null) {
            throw new JsonSyntaxException("Not a valid id: '" + rawId + "'");
         }

         consumer.accept(id);
      }
   }

   public static class Builder {
      private Identifier invulnerable = AbstractWitherStormRenderer.WITHER_STORM_INVULNERABLE_LOCATION;
      private Identifier main = AbstractWitherStormRenderer.WITHER_STORM_LOCATION;
      private Identifier emissiveDecal = AbstractWitherStormRenderer.EMISSIVE_DECAL;
      private Identifier debrisRing = AbstractWitherStormRenderer.DEBRIS_RING;

      private Builder() {
      }

      public nonamecrackers2.witherstormmod.client.resources.texture.TextureSet.Builder setInvulnerable(Identifier invulnerable) {
         this.invulnerable = invulnerable;
         return this;
      }

      public nonamecrackers2.witherstormmod.client.resources.texture.TextureSet.Builder setMain(Identifier main) {
         this.main = main;
         return this;
      }

      public nonamecrackers2.witherstormmod.client.resources.texture.TextureSet.Builder setEmissiveDecal(Identifier emissiveDecal) {
         this.emissiveDecal = emissiveDecal;
         return this;
      }

      public nonamecrackers2.witherstormmod.client.resources.texture.TextureSet.Builder setDebrisRing(Identifier debrisRing) {
         this.debrisRing = debrisRing;
         return this;
      }

      public nonamecrackers2.witherstormmod.client.resources.texture.TextureSet build() {
         return new nonamecrackers2.witherstormmod.client.resources.texture.TextureSet(this.invulnerable, this.main, this.emissiveDecal, this.debrisRing);
      }
   }
}

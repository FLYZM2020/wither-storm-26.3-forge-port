package nonamecrackers2.witherstormmod.client.resources.color;

import java.awt.Color;
import javax.annotation.Nullable;

public record SkyColorSet(
   Color skyColor, Color cloudColor, Color fogColor, @Nullable Color nightSkyColor, @Nullable Color nightCloudColor, @Nullable Color nightFogColor
) {
   public static final nonamecrackers2.witherstormmod.client.resources.color.SkyColorSet DEFAULT_SKY_COLORS = new nonamecrackers2.witherstormmod.client.resources.color.SkyColorSet(
      new Color(20, 0, 19), new Color(28, 10, 27), new Color(133, 69, 62)
   );

   public SkyColorSet(Color skyColor, Color cloudColor, Color fogColor) {
      this(skyColor, cloudColor, fogColor, null, null, null);
   }

   public static nonamecrackers2.witherstormmod.client.resources.color.SkyColorSet.Builder builder() {
      return new nonamecrackers2.witherstormmod.client.resources.color.SkyColorSet.Builder();
   }

   public static class Builder {
      private Color skyDarkenColor = nonamecrackers2.witherstormmod.client.resources.color.SkyColorSet.DEFAULT_SKY_COLORS.skyColor();
      private Color skyDarkenCloudColor = nonamecrackers2.witherstormmod.client.resources.color.SkyColorSet.DEFAULT_SKY_COLORS.cloudColor();
      private Color skyDarkenFogColor = nonamecrackers2.witherstormmod.client.resources.color.SkyColorSet.DEFAULT_SKY_COLORS.fogColor();
      @Nullable
      private Color nightSkyDarkenColor;
      @Nullable
      private Color nightSkyDarkenCloudColor;
      @Nullable
      private Color nightSkyDarkenFogColor;

      private Builder() {
      }

      public nonamecrackers2.witherstormmod.client.resources.color.SkyColorSet.Builder setSkyColor(Color color) {
         this.skyDarkenColor = color;
         return this;
      }

      public nonamecrackers2.witherstormmod.client.resources.color.SkyColorSet.Builder setCloudColor(Color color) {
         this.skyDarkenCloudColor = color;
         return this;
      }

      public nonamecrackers2.witherstormmod.client.resources.color.SkyColorSet.Builder setFogColor(Color color) {
         this.skyDarkenFogColor = color;
         return this;
      }

      public nonamecrackers2.witherstormmod.client.resources.color.SkyColorSet.Builder setNightSkyColor(Color color) {
         this.nightSkyDarkenColor = color;
         return this;
      }

      public nonamecrackers2.witherstormmod.client.resources.color.SkyColorSet.Builder setNightCloudColor(Color color) {
         this.nightSkyDarkenCloudColor = color;
         return this;
      }

      public nonamecrackers2.witherstormmod.client.resources.color.SkyColorSet.Builder setNightFogColor(Color color) {
         this.nightSkyDarkenFogColor = color;
         return this;
      }

      public nonamecrackers2.witherstormmod.client.resources.color.SkyColorSet build() {
         return new nonamecrackers2.witherstormmod.client.resources.color.SkyColorSet(
            this.skyDarkenColor,
            this.skyDarkenCloudColor,
            this.skyDarkenFogColor,
            this.nightSkyDarkenColor,
            this.nightSkyDarkenCloudColor,
            this.nightSkyDarkenFogColor
         );
      }
   }
}

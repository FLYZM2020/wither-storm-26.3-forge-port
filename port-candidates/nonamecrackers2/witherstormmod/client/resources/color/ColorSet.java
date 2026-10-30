package nonamecrackers2.witherstormmod.client.resources.color;

import java.awt.Color;

public record ColorSet(Color tractorBeamColor, Color tractorBeamNightColor, Color nightShineColor, SkyColorSet skyColors) {
   public static final nonamecrackers2.witherstormmod.client.resources.color.ColorSet DEFAULT = new nonamecrackers2.witherstormmod.client.resources.color.ColorSet(
      new Color(128, 77, 204), new Color(128, 77, 204), new Color(150, 59, 255, 75), SkyColorSet.DEFAULT_SKY_COLORS
   );

   public static nonamecrackers2.witherstormmod.client.resources.color.ColorSet.Builder builder() {
      return new nonamecrackers2.witherstormmod.client.resources.color.ColorSet.Builder();
   }

   public static class Builder {
      private Color tractorBeamColor;
      private Color tractorBeamNightColor;
      private Color nightShineColor;
      private SkyColorSet skyColors;

      private Builder() {
         this.tractorBeamColor = nonamecrackers2.witherstormmod.client.resources.color.ColorSet.DEFAULT.tractorBeamColor;
         this.tractorBeamNightColor = nonamecrackers2.witherstormmod.client.resources.color.ColorSet.DEFAULT.tractorBeamNightColor;
         this.nightShineColor = nonamecrackers2.witherstormmod.client.resources.color.ColorSet.DEFAULT.nightShineColor;
         this.skyColors = nonamecrackers2.witherstormmod.client.resources.color.ColorSet.DEFAULT.skyColors;
      }

      public Color getTractorBeamColor() {
         return this.tractorBeamColor;
      }

      public Color getTractorBeamNightColor() {
         return this.tractorBeamNightColor;
      }

      public Color getNightShineColor() {
         return this.nightShineColor;
      }

      public SkyColorSet getSkyColors() {
         return this.skyColors;
      }

      public nonamecrackers2.witherstormmod.client.resources.color.ColorSet.Builder setTractorBeamColor(Color color) {
         this.tractorBeamColor = color;
         this.tractorBeamNightColor = color;
         return this;
      }

      public nonamecrackers2.witherstormmod.client.resources.color.ColorSet.Builder setTractorBeamNightColor(Color color) {
         this.tractorBeamNightColor = color;
         return this;
      }

      public nonamecrackers2.witherstormmod.client.resources.color.ColorSet.Builder setNightShineColor(Color color) {
         this.nightShineColor = color;
         return this;
      }

      public nonamecrackers2.witherstormmod.client.resources.color.ColorSet.Builder setSkyColors(SkyColorSet colors) {
         this.skyColors = colors;
         return this;
      }

      public nonamecrackers2.witherstormmod.client.resources.color.ColorSet.Builder copy() {
         return new nonamecrackers2.witherstormmod.client.resources.color.ColorSet.Builder()
            .setTractorBeamColor(this.tractorBeamColor)
            .setNightShineColor(this.nightShineColor)
            .setTractorBeamNightColor(this.tractorBeamNightColor)
            .setSkyColors(this.skyColors);
      }

      public nonamecrackers2.witherstormmod.client.resources.color.ColorSet build() {
         return new nonamecrackers2.witherstormmod.client.resources.color.ColorSet(
            this.tractorBeamColor, this.tractorBeamNightColor, this.nightShineColor, this.skyColors
         );
      }
   }
}

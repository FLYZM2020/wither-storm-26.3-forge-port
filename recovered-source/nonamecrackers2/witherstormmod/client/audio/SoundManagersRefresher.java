package nonamecrackers2.witherstormmod.client.audio;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;

public class SoundManagersRefresher implements ResourceManagerReloadListener {
   public static final nonamecrackers2.witherstormmod.client.audio.SoundManagersRefresher INSTANCE = new nonamecrackers2.witherstormmod.client.audio.SoundManagersRefresher(
      Minecraft.m_91087_()
   );
   private final Minecraft minecraft;

   private SoundManagersRefresher(Minecraft mc) {
      this.minecraft = mc;
   }

   public void refresh() {
      if (this.minecraft.f_91073_ != null) {
         ClientLevel world = this.minecraft.f_91073_;
         world.getCapability(WitherStormModClientCapabilities.SOUND_MANAGERS).ifPresent(holder -> holder.getManagers().forEach(manager -> manager.refresh()));
      }
   }

   public void m_6213_(ResourceManager manager) {
      this.refresh();
   }
}

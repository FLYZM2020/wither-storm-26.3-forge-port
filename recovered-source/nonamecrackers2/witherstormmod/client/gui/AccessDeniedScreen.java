package nonamecrackers2.witherstormmod.client.gui;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import nonamecrackers2.witherstormmod.client.util.Contributors;
import nonamecrackers2.witherstormmod.client.util.Contributors.Result;

@Deprecated
public class AccessDeniedScreen extends Screen {
   private static final Component TITLE = Component.m_237113_("Whoops!").m_130948_(Style.f_131099_.m_131136_(true));
   private static final Component DESCRIPTION = Component.m_237113_(
      "Looks like you do not have access to this build of Cracker's Wither Storm Mod. This is an in-development build exclusive for BETA testers, patrons, etc. The content in this in-development build will be made available freely to the public when it is deemed to be finished."
   );
   private static final Component PATREON = Component.m_237113_(
      "If you're a nonamecrackers2 patron, open the link below and sign in with your Patreon account to gain access."
   );
   private static final Component NOTE = Component.m_237113_(
         "NOTE: The Minecraft account used to launch this game will be associated with your Patreon account."
      )
      .m_130940_(ChatFormatting.DARK_GRAY);
   private static final Component ERROR = Component.m_237113_(
      "Something went wrong while authenticating! Please check your internet connection. If the problem persists, please notify nonamecrackers2 and provide the latest.log file."
   );
   private Button openLink;
   private Button refresh;
   private Result access;
   @Nullable
   private CompletableFuture<Result> resultGetter;

   public AccessDeniedScreen(Result access) {
      super(Component.m_237119_());
      this.access = access;
   }

   protected void m_7856_() {
      this.openLink = Button.m_253074_(Component.m_237113_("Open Link"), button -> {
         String url = "https://patronauthenticator-sp4uwbgqwa-uc.a.run.app/login?mc_uuid=" + this.f_96541_.m_91094_().m_92545_();
         this.f_96541_.m_91152_(new ConfirmLinkScreen(b -> {
            if (b) {
               Util.m_137581_().m_137646_(url);
            }

            this.f_96541_.m_91152_(this);
         }, url, true));
      }).m_252780_(100).m_252794_(this.f_96543_ / 2 - 120, 210).m_253136_();
      this.refresh = Button.m_253074_(Component.m_237113_("Refresh"), button -> this.refresh())
         .m_252780_(100)
         .m_252794_(this.f_96543_ / 2 + 20, 210)
         .m_253136_();
      if (this.access != Result.ERROR) {
         this.m_142416_(this.openLink);
      } else {
         this.refresh.m_252865_(this.f_96543_ / 2 - this.refresh.m_5711_() / 2);
      }

      this.m_142416_(this.refresh);
   }

   public void m_88315_(GuiGraphics stack, int mouseX, int mouseY, float partialTicks) {
      this.m_280273_(stack);
      int y = this.f_96544_ / 8;
      stack.m_280653_(this.f_96547_, TITLE, this.f_96543_ / 2, y, -1);
      if (this.access != Result.ERROR) {
         int var6 = drawSplitText(stack, this.f_96547_, DESCRIPTION, this.f_96543_ - 50, this.f_96543_ / 2, y + 30, -1);
         int var7 = drawSplitText(stack, this.f_96547_, PATREON, this.f_96543_ - 50, this.f_96543_ / 2, var6 + 20, -1);
         y = drawSplitText(stack, this.f_96547_, NOTE, this.f_96543_ - 50, this.f_96543_ / 2, var7 + 20, -1);
         this.openLink.m_253211_(y + this.openLink.m_93694_() + 10);
      } else {
         y = drawSplitText(stack, this.f_96547_, ERROR, this.f_96543_ - 50, this.f_96543_ / 2, y + 30, -1);
      }

      this.refresh.m_253211_(y + this.openLink.m_93694_() + 10);
      super.m_88315_(stack, mouseX, mouseY, partialTicks);
   }

   private static int drawSplitText(GuiGraphics stack, Font font, Component text, int width, int x, int y, int color) {
      List<FormattedCharSequence> desc = font.m_92923_(text, width);
      int lastY = y;

      for (int i = 0; i < desc.size(); i++) {
         FormattedCharSequence line = desc.get(i);
         lastY = y + i * (9 + 2);
         stack.m_280364_(font, line, x, lastY, color);
      }

      return lastY;
   }

   public void m_86600_() {
      this.f_96541_.m_91397_().m_120183_();
      this.f_96541_.m_91106_().m_120389_(false);
      if (this.resultGetter != null && this.resultGetter.isDone()) {
         try {
            this.access = this.resultGetter.get();
            if (!this.access.canLaunchGame()) {
               this.m_267719_();
            } else {
               this.m_7379_();
            }
         } catch (InterruptedException | ExecutionException var2) {
         }

         this.resultGetter = null;
      }
   }

   public boolean m_6913_() {
      return false;
   }

   public void refresh() {
      this.refresh.f_93623_ = false;
      this.openLink.f_93623_ = false;
      if (this.resultGetter == null) {
         this.resultGetter = CompletableFuture.supplyAsync(() -> Contributors.getAccess(this.f_96541_.m_91094_().m_92545_()));
      }
   }
}

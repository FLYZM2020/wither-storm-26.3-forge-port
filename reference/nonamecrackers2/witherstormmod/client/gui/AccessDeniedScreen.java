/*     */ package nonamecrackers2.witherstormmod.client.gui;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.Util;
/*     */ import net.minecraft.client.gui.Font;
/*     */ import net.minecraft.client.gui.GuiGraphics;
/*     */ import net.minecraft.client.gui.components.Button;
/*     */ import net.minecraft.client.gui.components.events.GuiEventListener;
/*     */ import net.minecraft.client.gui.screens.ConfirmLinkScreen;
/*     */ import net.minecraft.client.gui.screens.Screen;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.network.chat.FormattedText;
/*     */ import net.minecraft.network.chat.Style;
/*     */ import net.minecraft.util.FormattedCharSequence;
/*     */ import nonamecrackers2.witherstormmod.client.util.Contributors;
/*     */ 
/*     */ @Deprecated
/*     */ public class AccessDeniedScreen
/*     */   extends Screen {
/*  24 */   private static final Component TITLE = (Component)Component.m_237113_("Whoops!").m_130948_(Style.f_131099_.m_131136_(Boolean.valueOf(true)));
/*  25 */   private static final Component DESCRIPTION = (Component)Component.m_237113_("Looks like you do not have access to this build of Cracker's Wither Storm Mod. This is an in-development build exclusive for BETA testers, patrons, etc. The content in this in-development build will be made available freely to the public when it is deemed to be finished.");
/*  26 */   private static final Component PATREON = (Component)Component.m_237113_("If you're a nonamecrackers2 patron, open the link below and sign in with your Patreon account to gain access.");
/*  27 */   private static final Component NOTE = (Component)Component.m_237113_("NOTE: The Minecraft account used to launch this game will be associated with your Patreon account.").m_130940_(ChatFormatting.DARK_GRAY);
/*  28 */   private static final Component ERROR = (Component)Component.m_237113_("Something went wrong while authenticating! Please check your internet connection. If the problem persists, please notify nonamecrackers2 and provide the latest.log file.");
/*     */   private Button openLink;
/*     */   private Button refresh;
/*     */   private Contributors.Result access;
/*     */   @Nullable
/*     */   private CompletableFuture<Contributors.Result> resultGetter;
/*     */   
/*     */   public AccessDeniedScreen(Contributors.Result access) {
/*  36 */     super((Component)Component.m_237119_());
/*  37 */     this.access = access;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_7856_() {
/*  43 */     this
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  50 */       .openLink = Button.m_253074_((Component)Component.m_237113_("Open Link"), button -> { String url = "https://patronauthenticator-sp4uwbgqwa-uc.a.run.app/login?mc_uuid=" + this.f_96541_.m_91094_().m_92545_(); this.f_96541_.m_91152_((Screen)new ConfirmLinkScreen((), url, true)); }).m_252780_(100).m_252794_(this.f_96543_ / 2 - 120, 210).m_253136_();
/*  51 */     this
/*     */       
/*  53 */       .refresh = Button.m_253074_((Component)Component.m_237113_("Refresh"), button -> refresh()).m_252780_(100).m_252794_(this.f_96543_ / 2 + 20, 210).m_253136_();
/*  54 */     if (this.access != Contributors.Result.ERROR) {
/*  55 */       m_142416_((GuiEventListener)this.openLink);
/*     */     } else {
/*  57 */       this.refresh.m_252865_(this.f_96543_ / 2 - this.refresh.m_5711_() / 2);
/*  58 */     }  m_142416_((GuiEventListener)this.refresh);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_88315_(GuiGraphics stack, int mouseX, int mouseY, float partialTicks) {
/*  64 */     m_280273_(stack);
/*     */     
/*  66 */     int y = this.f_96544_ / 8;
/*  67 */     stack.m_280653_(this.f_96547_, TITLE, this.f_96543_ / 2, y, -1);
/*     */     
/*  69 */     if (this.access != Contributors.Result.ERROR) {
/*     */       
/*  71 */       y = drawSplitText(stack, this.f_96547_, DESCRIPTION, this.f_96543_ - 50, this.f_96543_ / 2, y + 30, -1);
/*  72 */       y = drawSplitText(stack, this.f_96547_, PATREON, this.f_96543_ - 50, this.f_96543_ / 2, y + 20, -1);
/*  73 */       y = drawSplitText(stack, this.f_96547_, NOTE, this.f_96543_ - 50, this.f_96543_ / 2, y + 20, -1);
/*  74 */       this.openLink.m_253211_(y + this.openLink.m_93694_() + 10);
/*     */     }
/*     */     else {
/*     */       
/*  78 */       y = drawSplitText(stack, this.f_96547_, ERROR, this.f_96543_ - 50, this.f_96543_ / 2, y + 30, -1);
/*     */     } 
/*     */     
/*  81 */     this.refresh.m_253211_(y + this.openLink.m_93694_() + 10);
/*     */     
/*  83 */     super.m_88315_(stack, mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int drawSplitText(GuiGraphics stack, Font font, Component text, int width, int x, int y, int color) {
/*  88 */     List<FormattedCharSequence> desc = font.m_92923_((FormattedText)text, width);
/*  89 */     int lastY = y;
/*  90 */     for (int i = 0; i < desc.size(); i++) {
/*     */       
/*  92 */       FormattedCharSequence line = desc.get(i);
/*  93 */       Objects.requireNonNull(font); lastY = y + i * (9 + 2);
/*  94 */       stack.m_280364_(font, line, x, lastY, color);
/*     */     } 
/*  96 */     return lastY;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_86600_() {
/* 102 */     this.f_96541_.m_91397_().m_120183_();
/* 103 */     this.f_96541_.m_91106_().m_120389_(false);
/*     */     
/* 105 */     if (this.resultGetter != null && this.resultGetter.isDone()) {
/*     */ 
/*     */       
/*     */       try {
/* 109 */         this.access = this.resultGetter.get();
/* 110 */         if (!this.access.canLaunchGame()) {
/* 111 */           m_267719_();
/*     */         } else {
/* 113 */           m_7379_();
/*     */         } 
/* 115 */       } catch (InterruptedException|java.util.concurrent.ExecutionException interruptedException) {}
/*     */ 
/*     */       
/* 118 */       this.resultGetter = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6913_() {
/* 125 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void refresh() {
/* 130 */     this.refresh.f_93623_ = false;
/* 131 */     this.openLink.f_93623_ = false;
/* 132 */     if (this.resultGetter == null)
/*     */     {
/* 134 */       this.resultGetter = CompletableFuture.supplyAsync(() -> Contributors.getAccess(this.f_96541_.m_91094_().m_92545_()));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\gui\AccessDeniedScreen.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
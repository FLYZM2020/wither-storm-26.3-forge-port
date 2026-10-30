/*    */ package nonamecrackers2.witherstormmod.client.audio;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.ClientLevel;
/*    */ import net.minecraft.server.packs.resources.ResourceManager;
/*    */ import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
/*    */ import nonamecrackers2.witherstormmod.client.capability.SoundManagersHolder;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
/*    */ 
/*    */ public class SoundManagersRefresher implements ResourceManagerReloadListener {
/* 11 */   public static final SoundManagersRefresher INSTANCE = new SoundManagersRefresher(Minecraft.m_91087_());
/*    */   
/*    */   private final Minecraft minecraft;
/*    */ 
/*    */   
/*    */   private SoundManagersRefresher(Minecraft mc) {
/* 17 */     this.minecraft = mc;
/*    */   }
/*    */ 
/*    */   
/*    */   public void refresh() {
/* 22 */     if (this.minecraft.f_91073_ != null) {
/*    */       
/* 24 */       ClientLevel world = this.minecraft.f_91073_;
/* 25 */       world.getCapability(WitherStormModClientCapabilities.SOUND_MANAGERS).ifPresent(holder -> holder.getManagers().forEach(()));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void m_6213_(ResourceManager manager) {
/* 37 */     refresh();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\audio\SoundManagersRefresher.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
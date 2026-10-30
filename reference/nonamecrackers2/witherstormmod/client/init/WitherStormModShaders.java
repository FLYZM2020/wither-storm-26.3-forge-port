/*    */ package nonamecrackers2.witherstormmod.client.init;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.DefaultVertexFormat;
/*    */ import java.io.IOException;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.client.renderer.ShaderInstance;
/*    */ import net.minecraftforge.client.event.RegisterShadersEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WitherStormModShaders
/*    */ {
/*    */   private static ShaderInstance witherStormShader;
/*    */   private static ShaderInstance witherStormDissolveShader;
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void registerShaders(RegisterShadersEvent event) throws IOException {
/* 21 */     event.registerShader(new ShaderInstance(event.getResourceProvider(), WitherStormMod.id("wither_storm"), DefaultVertexFormat.f_85812_), s -> witherStormShader = s);
/*    */ 
/*    */     
/* 24 */     event.registerShader(new ShaderInstance(event.getResourceProvider(), WitherStormMod.id("wither_storm_dissolve"), DefaultVertexFormat.f_85812_), s -> witherStormDissolveShader = s);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ShaderInstance getWitherStormShader() {
/* 31 */     return Objects.<ShaderInstance>requireNonNull(witherStormShader, "The wither storm shader has not been created yet");
/*    */   }
/*    */ 
/*    */   
/*    */   public static ShaderInstance getWitherStormDissolveShader() {
/* 36 */     return Objects.<ShaderInstance>requireNonNull(witherStormDissolveShader, "The wither storm dissolve shader has not been created yet");
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\init\WitherStormModShaders.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.client.resources.SplashManager;
/*    */ import net.minecraft.server.packs.resources.ResourceManager;
/*    */ import net.minecraft.util.profiling.ProfilerFiller;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({SplashManager.class})
/*    */ public class MixinSplashManager
/*    */ {
/*    */   @Shadow
/*    */   private List<String> splashes;
/*    */   
/*    */   @Inject(method = {"apply"}, at = {@At("TAIL")})
/*    */   public void witherstormmod$addCustomSplashes$apply(List<String> splashes, ResourceManager manager, ProfilerFiller filler, CallbackInfo ci) {
/* 24 */     this.splashes.add("Waga Baga Bobo!");
/* 25 */     this.splashes.add("Also try Explorer's Eve!");
/* 26 */     this.splashes.add("Also try CWSM Plus!");
/* 27 */     this.splashes.add("Also try Command Tool Expansion!");
/* 28 */     this.splashes.add("Also try Bloxxify's Lightbringer!");
/* 29 */     this.splashes.add("It's symbiont, not 'symbiote'!");
/* 30 */     this.splashes.add("Useless tentacles included!");
/* 31 */     this.splashes.add("Elixir not included!");
/* 32 */     this.splashes.add("Slightly optimized Wither Storm included!");
/* 33 */     this.splashes.add("Shout out to Nazaru!");
/* 34 */     this.splashes.add("World destruction included!");
/* 35 */     this.splashes.add("Also try Story Mod!");
/* 36 */     this.splashes.add("Includes zombies doing flips!");
/* 37 */     this.splashes.add("Rest in peace Sickened Frog and Rabbit!");
/* 38 */     this.splashes.add("IT SHOULD HAVE BEEN ME!!");
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinSplashManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
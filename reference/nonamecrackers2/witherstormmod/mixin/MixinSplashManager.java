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
/*    */   private List<String> f_118862_;
/*    */   
/*    */   @Inject(method = {"apply"}, at = {@At("TAIL")})
/*    */   public void witherstormmod$addCustomSplashes$apply(List<String> splashes, ResourceManager manager, ProfilerFiller filler, CallbackInfo ci) {
/* 24 */     this.f_118862_.add("Waga Baga Bobo!");
/* 25 */     this.f_118862_.add("Also try Explorer's Eve!");
/* 26 */     this.f_118862_.add("Also try CWSM Plus!");
/* 27 */     this.f_118862_.add("Also try Command Tool Expansion!");
/* 28 */     this.f_118862_.add("Also try Bloxxify's Lightbringer!");
/* 29 */     this.f_118862_.add("It's symbiont, not 'symbiote'!");
/* 30 */     this.f_118862_.add("Useless tentacles included!");
/* 31 */     this.f_118862_.add("Elixir not included!");
/* 32 */     this.f_118862_.add("Slightly optimized Wither Storm included!");
/* 33 */     this.f_118862_.add("Shout out to Nazaru!");
/* 34 */     this.f_118862_.add("World destruction included!");
/* 35 */     this.f_118862_.add("Also try Story Mod!");
/* 36 */     this.f_118862_.add("Includes zombies doing flips!");
/* 37 */     this.f_118862_.add("Rest in peace Sickened Frog and Rabbit!");
/* 38 */     this.f_118862_.add("IT SHOULD HAVE BEEN ME!!");
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinSplashManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
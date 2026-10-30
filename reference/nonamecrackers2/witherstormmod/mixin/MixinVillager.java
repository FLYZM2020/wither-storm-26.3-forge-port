/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.ai.Brain;
/*    */ import net.minecraft.world.entity.npc.Villager;
/*    */ import nonamecrackers2.witherstormmod.common.util.BrainInjectionHelper;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({Villager.class})
/*    */ public abstract class MixinVillager
/*    */ {
/*    */   @Inject(method = {"registerBrainGoals"}, at = {@At("TAIL")})
/*    */   public void registerBrainGoalsTail(Brain<Villager> brain, CallbackInfo ci) {
/* 21 */     BrainInjectionHelper.inject((LivingEntity)this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinVillager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import net.minecraft.world.effect.MobEffectInstance;
/*    */ import net.minecraft.world.effect.MobEffects;
/*    */ import net.minecraft.world.item.alchemy.Potion;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ 
/*    */ public class WitherStormModPotions
/*    */ {
/* 13 */   public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, "witherstormmod");
/*    */   
/* 15 */   public static final RegistryObject<Potion> WITHER = POTIONS.register("wither", () -> new Potion(new MobEffectInstance[] { new MobEffectInstance(MobEffects.f_19615_, 900) }));
/* 16 */   public static final RegistryObject<Potion> LONG_WITHER = POTIONS.register("long_wither", () -> new Potion(new MobEffectInstance[] { new MobEffectInstance(MobEffects.f_19615_, 1800) }));
/* 17 */   public static final RegistryObject<Potion> STRONG_WITHER = POTIONS.register("strong_wither", () -> new Potion(new MobEffectInstance[] { new MobEffectInstance(MobEffects.f_19615_, 432, 1) }));
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModPotions.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
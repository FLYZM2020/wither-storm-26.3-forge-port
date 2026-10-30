/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import net.minecraft.world.effect.MobEffect;
/*    */ import net.minecraft.world.effect.MobEffectCategory;
/*    */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ import nonamecrackers2.witherstormmod.common.potion.WitherSicknessEffect;
/*    */ 
/*    */ 
/*    */ public class WitherStormModEffects
/*    */ {
/* 15 */   public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "witherstormmod");
/*    */   
/* 17 */   public static final RegistryObject<MobEffect> WITHER_SICKNESS = EFFECTS.register("wither_sickness", () -> (new WitherSicknessEffect(MobEffectCategory.HARMFUL, 8192505)).m_19472_(Attributes.f_22276_, "08BA7AB9-0056-4B4F-AA13-7103B4B9D127", 0.0D, AttributeModifier.Operation.ADDITION));
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModEffects.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
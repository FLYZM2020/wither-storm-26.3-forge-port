/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import net.minecraft.world.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.world.entity.ai.attributes.RangedAttribute;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ 
/*    */ public class WitherStormModAttributes
/*    */ {
/* 12 */   public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, "witherstormmod");
/*    */   
/* 14 */   public static final RegistryObject<Attribute> TARGET_STATIONARY_FLYING_SPEED = ATTRIBUTES.register("target_stationary_flying_speed", () -> (new RangedAttribute("attribute.witherstormmod.name.target_stationary_flying_speed", 0.4D, 0.01D, 1.0D)).m_22084_(true));
/* 15 */   public static final RegistryObject<Attribute> SLOW_FLYING_SPEED = ATTRIBUTES.register("slow_flying_speed", () -> (new RangedAttribute("attribute.witherstormmod.name.slow_flying_speed", 0.02D, 0.01D, 1.0D)).m_22084_(true));
/* 16 */   public static final RegistryObject<Attribute> EVOLUTION_SPEED = ATTRIBUTES.register("evolution_speed", () -> (new RangedAttribute("attribute.witherstormmod.name.evolution_speed", 1.0D, 0.0D, 1024.0D)).m_22084_(true));
/* 17 */   public static final RegistryObject<Attribute> HUNCHBACK_FOLLOW_RANGE = ATTRIBUTES.register("hunchback_follow_range", () -> (new RangedAttribute("attribute.witherstormmod.name.hunchback_follow_range", 32.0D, 0.0D, 2048.0D)).m_22084_(true));
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModAttributes.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.world.entity.ai.sensing.SensorType;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ import nonamecrackers2.witherstormmod.common.entity.ai.sensing.WitherStormSensor;
/*    */ 
/*    */ public class WitherStormModSensorTypes
/*    */ {
/* 12 */   public static final DeferredRegister<SensorType<?>> SENSOR_TYPES = DeferredRegister.create(ForgeRegistries.SENSOR_TYPES, "witherstormmod");
/*    */   
/* 14 */   public static final RegistryObject<SensorType<WitherStormSensor>> WITHER_STORM_SENSOR = SENSOR_TYPES.register("wither_storm", () -> new SensorType(WitherStormSensor::new));
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModSensorTypes.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
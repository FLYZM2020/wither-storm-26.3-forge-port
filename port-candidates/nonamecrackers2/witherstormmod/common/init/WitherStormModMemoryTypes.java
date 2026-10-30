/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import net.minecraft.world.entity.ai.memory.MemoryModuleType;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WitherStormModMemoryTypes
/*    */ {
/* 14 */   public static final DeferredRegister<MemoryModuleType<?>> MEMORY_MODULE_TYPES = DeferredRegister.create(ForgeRegistries.MEMORY_MODULE_TYPES, "witherstormmod");
/*    */   
/* 16 */   public static final RegistryObject<MemoryModuleType<WitherStormEntity>> NEAREST_WITHER_STORM = MEMORY_MODULE_TYPES.register("nearest_wither_storm", () -> new MemoryModuleType(Optional.empty()));
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModMemoryTypes.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
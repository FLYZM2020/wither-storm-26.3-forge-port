/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import net.minecraft.world.entity.decoration.PaintingVariant;
/*    */ import net.minecraftforge.eventbus.api.IEventBus;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WitherStormModPaintingTypes
/*    */ {
/* 15 */   public static final DeferredRegister<PaintingVariant> PAINTING_MOTIVES = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, "witherstormmod");
/*    */   
/* 17 */   public static final RegistryObject<PaintingVariant> AMULET = PAINTING_MOTIVES.register("amulet", () -> new PaintingVariant(16, 32));
/*    */ 
/*    */   
/*    */   public static void register(IEventBus eventBus) {
/* 21 */     PAINTING_MOTIVES.register(eventBus);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModPaintingTypes.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
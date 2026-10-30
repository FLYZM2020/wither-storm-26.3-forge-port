/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.FireworkBundleBlockEntity;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.FormidibombBlockEntity;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.SuperBeaconBlockEntity;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.SuperSupportBeaconBlockEntity;
/*    */ import nonamecrackers2.witherstormmod.common.blockentity.WitheredPhlegmBlockEntity;
/*    */ 
/*    */ public class WitherStormModBlockEntityTypes
/*    */ {
/* 16 */   public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, "witherstormmod");
/*    */   
/* 18 */   public static final RegistryObject<BlockEntityType<FormidibombBlockEntity>> FORMIDIBOMB = BLOCK_ENTITIES.register("formidibomb", () -> BlockEntityType.Builder.m_155273_(FormidibombBlockEntity::new, new Block[] { (Block)WitherStormModBlocks.FORMIDIBOMB.get() }).m_58966_(null));
/* 19 */   public static final RegistryObject<BlockEntityType<SuperBeaconBlockEntity>> SUPER_BEACON = BLOCK_ENTITIES.register("super_beacon", () -> BlockEntityType.Builder.m_155273_(SuperBeaconBlockEntity::new, new Block[] { (Block)WitherStormModBlocks.SUPER_BEACON.get() }).m_58966_(null));
/* 20 */   public static final RegistryObject<BlockEntityType<SuperSupportBeaconBlockEntity>> SUPER_SUPPORT_BEACON = BLOCK_ENTITIES.register("super_support_beacon", () -> BlockEntityType.Builder.m_155273_(SuperSupportBeaconBlockEntity::new, new Block[] { (Block)WitherStormModBlocks.SUPER_SUPPORT_BEACON.get() }).m_58966_(null));
/* 21 */   public static final RegistryObject<BlockEntityType<FireworkBundleBlockEntity>> FIREWORK_BUNDLE = BLOCK_ENTITIES.register("firework_bundle", () -> BlockEntityType.Builder.m_155273_(FireworkBundleBlockEntity::new, new Block[] { (Block)WitherStormModBlocks.FIREWORK_BUNDLE.get() }).m_58966_(null));
/* 22 */   public static final RegistryObject<BlockEntityType<WitheredPhlegmBlockEntity>> WITHERED_PHLEGM = BLOCK_ENTITIES.register("withered_phlegm", () -> BlockEntityType.Builder.m_155273_(WitheredPhlegmBlockEntity::new, new Block[] { (Block)WitherStormModBlocks.WITHERED_PHLEGM_BLOCK.get() }).m_58966_(null));
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModBlockEntityTypes.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
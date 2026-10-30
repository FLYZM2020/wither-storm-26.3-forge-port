/*    */ package nonamecrackers2.witherstormmod.common.data;
/*    */ 
/*    */ import net.minecraft.data.PackOutput;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import nonamecrackers2.witherstormmod.api.common.data.MobConversionProvider;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*    */ 
/*    */ 
/*    */ public class WitherStormModMobConversionsProvider
/*    */   extends MobConversionProvider
/*    */ {
/*    */   public WitherStormModMobConversionsProvider(PackOutput output) {
/* 13 */     super(output, "witherstormmod");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void addConversions() {
/* 19 */     add(EntityType.f_20501_, (EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get());
/* 20 */     add(EntityType.f_20524_, (EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get());
/* 21 */     add(EntityType.f_20479_, (EntityType)WitherStormModEntityTypes.SICKENED_SPIDER.get());
/* 22 */     add(EntityType.f_20558_, (EntityType)WitherStormModEntityTypes.SICKENED_CREEPER.get());
/* 23 */     add(EntityType.f_20492_, (EntityType)WitherStormModEntityTypes.SICKENED_VILLAGER.get());
/* 24 */     add(EntityType.f_20530_, (EntityType)WitherStormModEntityTypes.SICKENED_VILLAGER.get());
/* 25 */     add(EntityType.f_20509_, (EntityType)WitherStormModEntityTypes.SICKENED_PHANTOM.get());
/* 26 */     add(EntityType.f_20555_, (EntityType)WitherStormModEntityTypes.SICKENED_CHICKEN.get());
/* 27 */     add(EntityType.f_20508_, (EntityType)WitherStormModEntityTypes.SICKENED_PARROT.get());
/* 28 */     add(EntityType.f_20557_, (EntityType)WitherStormModEntityTypes.SICKENED_COW.get());
/* 29 */     add(EntityType.f_20510_, (EntityType)WitherStormModEntityTypes.SICKENED_PIG.get());
/* 30 */     add(EntityType.f_20504_, (EntityType)WitherStormModEntityTypes.SICKENED_MUSHROOM_COW.get());
/* 31 */     add(EntityType.f_20550_, (EntityType)WitherStormModEntityTypes.SICKENED_BEE.get());
/* 32 */     add(EntityType.f_20513_, (EntityType)WitherStormModEntityTypes.SICKENED_PILLAGER.get());
/* 33 */     add(EntityType.f_20493_, (EntityType)WitherStormModEntityTypes.SICKENED_VINDICATOR.get());
/* 34 */     add(EntityType.f_20481_, (EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get());
/* 35 */     add(EntityType.f_20458_, (EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get());
/* 36 */     add(EntityType.f_20562_, (EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get());
/* 37 */     add(EntityType.f_20460_, (EntityType)WitherStormModEntityTypes.SICKENED_IRON_GOLEM.get(), false);
/* 38 */     add(EntityType.f_20528_, (EntityType)WitherStormModEntityTypes.SICKENED_SNOW_GOLEM.get(), false);
/* 39 */     add(EntityType.f_20499_, (EntityType)WitherStormModEntityTypes.SICKENED_WOLF.get());
/* 40 */     add(EntityType.f_20553_, (EntityType)WitherStormModEntityTypes.SICKENED_CAT.get());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\data\WitherStormModMobConversionsProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
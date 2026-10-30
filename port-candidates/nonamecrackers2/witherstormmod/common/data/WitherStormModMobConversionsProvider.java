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
/* 19 */     add(EntityType.ZOMBIE, (EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get());
/* 20 */     add(EntityType.SKELETON, (EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get());
/* 21 */     add(EntityType.SPIDER, (EntityType)WitherStormModEntityTypes.SICKENED_SPIDER.get());
/* 22 */     add(EntityType.CREEPER, (EntityType)WitherStormModEntityTypes.SICKENED_CREEPER.get());
/* 23 */     add(EntityType.VILLAGER, (EntityType)WitherStormModEntityTypes.SICKENED_VILLAGER.get());
/* 24 */     add(EntityType.ZOMBIE_VILLAGER, (EntityType)WitherStormModEntityTypes.SICKENED_VILLAGER.get());
/* 25 */     add(EntityType.PHANTOM, (EntityType)WitherStormModEntityTypes.SICKENED_PHANTOM.get());
/* 26 */     add(EntityType.CHICKEN, (EntityType)WitherStormModEntityTypes.SICKENED_CHICKEN.get());
/* 27 */     add(EntityType.PARROT, (EntityType)WitherStormModEntityTypes.SICKENED_PARROT.get());
/* 28 */     add(EntityType.COW, (EntityType)WitherStormModEntityTypes.SICKENED_COW.get());
/* 29 */     add(EntityType.PIG, (EntityType)WitherStormModEntityTypes.SICKENED_PIG.get());
/* 30 */     add(EntityType.MOOSHROOM, (EntityType)WitherStormModEntityTypes.SICKENED_MUSHROOM_COW.get());
/* 31 */     add(EntityType.BEE, (EntityType)WitherStormModEntityTypes.SICKENED_BEE.get());
/* 32 */     add(EntityType.PILLAGER, (EntityType)WitherStormModEntityTypes.SICKENED_PILLAGER.get());
/* 33 */     add(EntityType.VINDICATOR, (EntityType)WitherStormModEntityTypes.SICKENED_VINDICATOR.get());
/* 34 */     add(EntityType.STRAY, (EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get());
/* 35 */     add(EntityType.HUSK, (EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get());
/* 36 */     add(EntityType.DROWNED, (EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get());
/* 37 */     add(EntityType.IRON_GOLEM, (EntityType)WitherStormModEntityTypes.SICKENED_IRON_GOLEM.get(), false);
/* 38 */     add(EntityType.SNOW_GOLEM, (EntityType)WitherStormModEntityTypes.SICKENED_SNOW_GOLEM.get(), false);
/* 39 */     add(EntityType.WOLF, (EntityType)WitherStormModEntityTypes.SICKENED_WOLF.get());
/* 40 */     add(EntityType.CAT, (EntityType)WitherStormModEntityTypes.SICKENED_CAT.get());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\data\WitherStormModMobConversionsProvider.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
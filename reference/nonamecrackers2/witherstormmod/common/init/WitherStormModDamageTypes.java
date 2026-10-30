/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.core.Holder;
/*    */ import net.minecraft.core.RegistryAccess;
/*    */ import net.minecraft.core.registries.Registries;
/*    */ import net.minecraft.resources.ResourceKey;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.world.damagesource.DamageSource;
/*    */ import net.minecraft.world.damagesource.DamageType;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.level.Level;
/*    */ 
/*    */ 
/*    */ public class WitherStormModDamageTypes
/*    */ {
/* 19 */   public static final ResourceKey<DamageType> FLAMING_WITHER_SKULL = create("flaming_wither_skull");
/* 20 */   public static final ResourceKey<DamageType> WITHER_SICKNESS = create("wither_sickness");
/* 21 */   public static final ResourceKey<DamageType> PLAYER_FORMIDIBOMB = create("player_formidibomb");
/*    */   
/* 23 */   public static final ResourceKey<DamageType> FORMIDIBOMB = create("formidibomb");
/* 24 */   public static final ResourceKey<DamageType> WITHER_STORM_ATTACK_MOB = create("wither_storm_attack_mob");
/* 25 */   public static final ResourceKey<DamageType> WITHER_STORM_ATTACK = create("wither_storm_attack");
/* 26 */   public static final ResourceKey<DamageType> PLAYER_ATTACK_WITHER_STORM = create("player_attack_wither_storm");
/* 27 */   public static final ResourceKey<DamageType> MOB_ATTACK_WITHER_STORM = create("mob_attack_wither_storm");
/* 28 */   public static final ResourceKey<DamageType> SUPER_TNT_EXPLOSION = create("super_tnt_explosion");
/* 29 */   public static final ResourceKey<DamageType> IRON_PIERCING = create("iron_pierce");
/*    */ 
/*    */   
/*    */   private static ResourceKey<DamageType> create(String id) {
/* 33 */     return ResourceKey.m_135785_(Registries.f_268580_, new ResourceLocation("witherstormmod", id));
/*    */   }
/*    */ 
/*    */   
/*    */   public static DamageSource source(RegistryAccess access, ResourceKey<DamageType> key) {
/* 38 */     return new DamageSource((Holder)access.m_175515_(Registries.f_268580_).m_246971_(key));
/*    */   }
/*    */ 
/*    */   
/*    */   public static DamageSource source(RegistryAccess access, ResourceKey<DamageType> key, @Nullable Entity entity) {
/* 43 */     return new DamageSource((Holder)access.m_175515_(Registries.f_268580_).m_246971_(key), entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public static DamageSource source(RegistryAccess access, ResourceKey<DamageType> key, @Nullable Entity entity, @Nullable Entity entity2) {
/* 48 */     return new DamageSource((Holder)access.m_175515_(Registries.f_268580_).m_246971_(key), entity, entity2);
/*    */   }
/*    */ 
/*    */   
/*    */   public static DamageSource witherStormAttack(LivingEntity entity) {
/* 53 */     return source(entity.m_9236_().m_9598_(), WITHER_STORM_ATTACK, (Entity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public static DamageSource witherStormAttackMob(LivingEntity entity) {
/* 58 */     return source(entity.m_9236_().m_9598_(), WITHER_STORM_ATTACK_MOB, (Entity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public static DamageSource playerAttackWitherStorm(Player entity) {
/* 63 */     return source(entity.m_9236_().m_9598_(), PLAYER_ATTACK_WITHER_STORM, (Entity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public static DamageSource mobAttackWitherStorm(LivingEntity entity) {
/* 68 */     return source(entity.m_9236_().m_9598_(), MOB_ATTACK_WITHER_STORM, (Entity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public static DamageSource superTntExplosion(Level level) {
/* 73 */     return source(level.m_9598_(), SUPER_TNT_EXPLOSION);
/*    */   }
/*    */ 
/*    */   
/*    */   public static DamageSource ironPierced(LivingEntity entity) {
/* 78 */     return source(entity.m_9236_().m_9598_(), IRON_PIERCING, (Entity)entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModDamageTypes.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
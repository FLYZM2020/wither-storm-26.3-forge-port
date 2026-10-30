/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import java.util.function.BiFunction;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
/*    */ import net.minecraft.world.entity.projectile.DragonFireball;
/*    */ import net.minecraft.world.entity.projectile.LargeFireball;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraftforge.eventbus.api.IEventBus;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
/*    */ import nonamecrackers2.witherstormmod.api.common.registry.WitherStormModRegistries;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.ai.symbiont.FireballSpell;
/*    */ import nonamecrackers2.witherstormmod.common.util.DragonFireballAccessor;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WitherStormModSymbiontSpellTypes
/*    */ {
/* 30 */   private static final DeferredRegister<SpellType> SPELL_TYPES = DeferredRegister.create(WitherStormModRegistries.SPELL_TYPES_NAME, "witherstormmod");
/*    */   
/* 32 */   public static final RegistryObject<SpellType> EMPTY = SPELL_TYPES.register("empty", () -> new SpellType(nonamecrackers2.witherstormmod.common.entity.ai.symbiont.EmptySpell::new, 0, Optional.empty(), false));
/* 33 */   public static final RegistryObject<SpellType> EVOKER_FANGS = SPELL_TYPES.register("evoker_fangs", () -> new SpellType(nonamecrackers2.witherstormmod.common.entity.ai.symbiont.EvokerFangsSpell::new, 20, Optional.empty()));
/* 34 */   public static final RegistryObject<SpellType> SHULKER_BULLETS = SPELL_TYPES.register("shulker_bullets", () -> new SpellType(nonamecrackers2.witherstormmod.common.entity.ai.symbiont.ShulkerBulletsSpell::new, 80, Optional.empty()));
/* 35 */   public static final RegistryObject<SpellType> ARROWS = SPELL_TYPES.register("arrows", () -> new SpellType(nonamecrackers2.witherstormmod.common.entity.ai.symbiont.ArrowsSpell::new, 120, Optional.empty()));
/* 36 */   public static final RegistryObject<SpellType> SMASH = SPELL_TYPES.register("smash", () -> new SpellType(nonamecrackers2.witherstormmod.common.entity.ai.symbiont.SmashSpell::new, 40, Optional.empty()));
/* 37 */   public static final RegistryObject<SpellType> FIRE_BALLS = SPELL_TYPES.register("fire_balls", () -> new SpellType((), 160, Optional.empty()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 61 */   public static final RegistryObject<SpellType> WITHER_SKULLS = SPELL_TYPES.register("wither_skulls", () -> new SpellType(nonamecrackers2.witherstormmod.common.entity.ai.symbiont.WitherSkullsSpell::new, 220, Optional.empty()));
/* 62 */   public static final RegistryObject<SpellType> PULL = SPELL_TYPES.register("pull", () -> new SpellType(nonamecrackers2.witherstormmod.common.entity.ai.symbiont.PullSpell::new, 240, Optional.of(WitherStormModSoundEvents.WITHERED_SYMBIONT_PULL), false));
/* 63 */   public static final RegistryObject<SpellType> THROWING = SPELL_TYPES.register("throwing", () -> new SpellType(nonamecrackers2.witherstormmod.common.entity.ai.symbiont.ThrowingSpell::new, 240, Optional.empty()));
/* 64 */   public static final RegistryObject<SpellType> BOMBING = SPELL_TYPES.register("bombing", () -> new SpellType(nonamecrackers2.witherstormmod.common.entity.ai.symbiont.BombingSpell::new, 60, Optional.empty()));
/* 65 */   public static final RegistryObject<SpellType> PULSE = SPELL_TYPES.register("pulse", () -> new SpellType(nonamecrackers2.witherstormmod.common.entity.ai.symbiont.PulseSpell::new, 160, Optional.empty(), true, 6.0D, 2.0D));
/*    */ 
/*    */   
/*    */   public static void register(IEventBus modBus) {
/* 69 */     SPELL_TYPES.register(modBus);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModSymbiontSpellTypes.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
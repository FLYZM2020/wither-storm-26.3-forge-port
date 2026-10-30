/*     */ package nonamecrackers2.witherstormmod.common.event;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.PathfinderMob;
/*     */ import net.minecraft.world.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.entity.animal.Animal;
/*     */ import net.minecraft.world.entity.animal.bee.Bee;
/*     */ import net.minecraft.world.entity.animal.wolf.Wolf;
/*     */ import net.minecraft.world.entity.animal.horse.Llama;
/*     */ import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
/*     */ import net.minecraft.world.entity.monster.Blaze;
/*     */ import net.minecraft.world.entity.monster.Creeper;
/*     */ import net.minecraft.world.entity.monster.Evoker;
/*     */ import net.minecraft.world.entity.monster.Phantom;
/*     */ import net.minecraft.world.entity.monster.illager.Pillager;
/*     */ import net.minecraft.world.entity.monster.Ravager;
/*     */ import net.minecraft.world.entity.monster.Shulker;
/*     */ import net.minecraft.world.entity.monster.Silverfish;
/*     */ import net.minecraft.world.entity.monster.spider.Spider;
/*     */ import net.minecraft.world.entity.monster.Vex;
/*     */ import net.minecraft.world.entity.monster.illager.Vindicator;
/*     */ import net.minecraft.world.entity.monster.Witch;
/*     */ import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
/*     */ import net.minecraft.world.entity.monster.zombie.Zombie;
/*     */ import net.minecraft.world.entity.monster.ZombifiedPiglin;
/*     */ import net.minecraft.world.entity.monster.hoglin.Hoglin;
/*     */ import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraftforge.event.entity.EntityJoinLevelEvent;
/*     */ import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherSickened;
/*     */ import nonamecrackers2.witherstormmod.common.entity.goal.AvoidWitherStormGoal;
/*     */ import nonamecrackers2.witherstormmod.common.entity.goal.FightSickenedMobsGoal;
/*     */ import nonamecrackers2.witherstormmod.common.entity.goal.NearestAttackingWitherStormGoal;
/*     */ import nonamecrackers2.witherstormmod.common.entity.goal.PhantomOrbitWitherStormGoal;
/*     */ import nonamecrackers2.witherstormmod.common.predicate.EntityPredicateBuilder;
/*     */ import nonamecrackers2.witherstormmod.mixin.IMixinBrain;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InjectCustomGoalsEvents
/*     */ {
/*     */   public static final Predicate<LivingEntity> CAN_RUN_AWAY_FROM_WITHER_STORM;
/*     */   
/*     */   static {
/*  58 */     CAN_RUN_AWAY_FROM_WITHER_STORM = ((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.and().isNotInstanceOf(WitherSickened.class)).isNotInstanceOf(WitherSkeleton.class)).addTest(((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.or().isInstanceOf(Zombie.class)).isInstanceOf(Spider.class)).isInstanceOf(AbstractSkeleton.class)).isInstanceOf(Creeper.class)).isInstanceOf(AbstractPiglin.class)).isInstanceOf(Pillager.class)).isInstanceOf(Animal.class)).build())).addTest(e -> ((IMixinBrain)e.getBrain()).getCoreActivities().isEmpty())).build();
/*     */   }
/*  60 */   public static final Predicate<LivingEntity> CAN_ATTACK_WITHER_STORM_BACK = ((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.and()
/*  61 */     .isNotInstanceOf(WitherSickened.class))
/*  62 */     .isNotInstanceOf(WitherSkeleton.class))
/*  63 */     .addTest(((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.or()
/*  64 */       .isInstanceOf(Creeper.class))
/*  65 */       .isInstanceOf(AbstractSkeleton.class))
/*  66 */       .build()))
/*  67 */     .build();
/*     */   
/*  69 */   public static final Predicate<LivingEntity> CAN_ATTACK_SICKENED_MOBS = ((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.and()
/*  70 */     .isNotInstanceOf(WitherSickened.class))
/*  71 */     .isNotInstanceOf(WitherSkeleton.class))
/*  72 */     .addTest(((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.or()
/*  73 */       .isInstanceOf(Shulker.class))
/*  74 */       .isInstanceOf(Evoker.class))
/*  75 */       .isInstanceOf(Vex.class))
/*  76 */       .isInstanceOf(Witch.class))
/*  77 */       .isInstanceOf(Ravager.class))
/*  78 */       .isInstanceOf(Llama.class))
/*  79 */       .isInstanceOf(Vindicator.class))
/*  80 */       .isInstanceOf(ZombifiedPiglin.class))
/*  81 */       .isInstanceOf(AbstractPiglin.class))
/*  82 */       .isInstanceOf(Hoglin.class))
/*  83 */       .isInstanceOf(Silverfish.class))
/*  84 */       .isInstanceOf(Blaze.class))
/*  85 */       .isInstanceOf(Wolf.class))
/*  86 */       .isInstanceOf(Bee.class))
/*  87 */       .build()))
/*  88 */     .build();
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
/*  94 */     Level world = event.getLevel();
/*  95 */     if (world.isClientSide || !((Boolean)WitherStormModConfig.COMMON.injectCustomAiBehavior.get()).booleanValue()) {
/*     */       return;
/*     */     }
/*  98 */     Entity entity = event.getEntity();
/*  99 */     if (((List)WitherStormModConfig.COMMON.injectAiMobBlacklist.get()).contains(entity.getEncodeId())) {
/*     */       return;
/*     */     }
/* 102 */     if (entity.getType().equals(EntityType.PHANTOM) && entity instanceof Phantom) { Phantom phantom = (Phantom)entity; if (((Boolean)WitherStormModConfig.COMMON.phantomsOrbitWitherStorm.get()).booleanValue()) {
/*     */         
/* 104 */         phantom.goalSelector.removeAllGoals(g -> g instanceof PhantomOrbitWitherStormGoal);
/* 105 */         phantom.goalSelector.addGoal(1, (Goal)new PhantomOrbitWitherStormGoal(phantom));
/*     */       }  }
/*     */     
/* 108 */     if (entity instanceof PathfinderMob) { PathfinderMob mob = (PathfinderMob)entity;
/*     */       
/* 110 */       if (CAN_RUN_AWAY_FROM_WITHER_STORM.test(mob)) {
/*     */         
/* 112 */         mob.goalSelector.removeAllGoals(g -> g instanceof AvoidWitherStormGoal);
/* 113 */         mob.goalSelector.addGoal(0, (Goal)new AvoidWitherStormGoal(mob, 300.0F, 1.55D, 1.55D));
/*     */       } 
/* 115 */       if (CAN_ATTACK_WITHER_STORM_BACK.test(mob)) {
/*     */         
/* 117 */         mob.targetSelector.removeAllGoals(g -> g instanceof NearestAttackingWitherStormGoal);
/* 118 */         mob.targetSelector.addGoal(0, (Goal)new NearestAttackingWitherStormGoal((Mob)mob, 10));
/*     */       } 
/* 120 */       if (CAN_ATTACK_SICKENED_MOBS.test(mob)) {
/*     */         
/* 122 */         mob.targetSelector.removeAllGoals(g -> g instanceof FightSickenedMobsGoal);
/* 123 */         mob.targetSelector.addGoal(-1, (Goal)new FightSickenedMobsGoal((Mob)mob));
/*     */       }  }
/*     */   
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\event\InjectCustomGoalsEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
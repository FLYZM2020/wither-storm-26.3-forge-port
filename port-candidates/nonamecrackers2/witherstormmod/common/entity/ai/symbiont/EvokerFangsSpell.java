/*     */ package nonamecrackers2.witherstormmod.common.entity.ai.symbiont;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nonnull;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.util.random.SimpleWeightedRandomList;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeInstance;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.world.entity.projectile.EvokerFangs;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.shapes.VoxelShape;
/*     */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
/*     */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*     */ 
/*     */ public class EvokerFangsSpell extends SymbiontSpell {
/*  29 */   private static final SimpleWeightedRandomList<EntityType<? extends Mob>> PILLAGERS = SimpleWeightedRandomList.builder()
/*  30 */     .add(WitherStormModEntityTypes.SICKENED_PILLAGER.get(), 3)
/*  31 */     .add(WitherStormModEntityTypes.SICKENED_VINDICATOR.get(), 3)
/*  32 */     .build();
/*     */ 
/*     */   
/*     */   public EvokerFangsSpell(WitheredSymbiontEntity symbiont, SpellType type) {
/*  36 */     super(symbiont, type);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void cast(@Nonnull LivingEntity target) {
/*  42 */     int chance = this.entity.shouldIncreaseDifficulty() ? 6 : 3;
/*  43 */     if (this.entity.getRandom().nextInt(chance) == 1)
/*     */     {
/*  45 */       for (int j = 0; j < 3; j++) {
/*     */         
/*  47 */         Mob mob = WorldUtil.summonRandomMob((ServerLevel)this.entity.level(), this.entity.blockPosition(), this.entity.getRandom(), 6, PILLAGERS, this.entity.shouldIncreaseDifficulty());
/*  48 */         if (mob != null) {
/*  49 */           addAttributes(mob);
/*     */         }
/*     */       } 
/*     */     }
/*  53 */     LivingEntity entity = this.entity.getRandomNearbyTargetOrFallback(target, WitheredSymbiontEntity.TARGET_PREDICATE);
/*  54 */     double minHeight = Math.min(entity.getY(), this.entity.getY()) - 2.0D;
/*  55 */     double maxHeight = Math.max(entity.getY(), this.entity.getY()) + 2.0D;
/*     */     
/*  57 */     int ringCount = 16;
/*  58 */     int fangsPerRing = 8;
/*     */     
/*  60 */     double initialRadius = 1.5D;
/*  61 */     double expansionRate = 3.0D + this.entity.getRandom().nextDouble();
/*     */     
/*  63 */     for (int i = 0; i < ringCount; i++) {
/*     */       
/*  65 */       double radius = initialRadius + i * expansionRate;
/*  66 */       fangsPerRing += 4;
/*  67 */       double angleIncrement = 6.283185307179586D / fangsPerRing;
/*  68 */       for (int i1 = 0; i1 < fangsPerRing; i1++) {
/*     */         
/*  70 */         double angle = i1 * angleIncrement;
/*  71 */         double x = this.entity.getX() + Math.cos(angle) * radius;
/*  72 */         double z = this.entity.getZ() + Math.sin(angle) * radius;
/*  73 */         createFang(x, z, minHeight, maxHeight, (float)angle, i1 + 2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void createFang(double x, double z, double minHeight, double maxHeight, float yRot, int delay) {
/*  80 */     BlockPos blockPos = BlockPos.containing(x, maxHeight, z);
/*  81 */     boolean flag = false;
/*  82 */     double d0 = 0.0D;
/*     */ 
/*     */     
/*     */     do {
/*  86 */       BlockPos below = blockPos.below();
/*  87 */       BlockState state = this.entity.level().getBlockState(below);
/*  88 */       if (state.isFaceSturdy((BlockGetter)this.entity.level(), below, Direction.UP)) {
/*     */         
/*  90 */         if (!this.entity.level().isEmptyBlock(blockPos)) {
/*     */           
/*  92 */           BlockState state1 = this.entity.level().getBlockState(blockPos);
/*  93 */           VoxelShape shape = state1.getCollisionShape((BlockGetter)this.entity.level(), blockPos);
/*  94 */           if (!shape.isEmpty()) {
/*  95 */             d0 = shape.max(Direction.Axis.Y);
/*     */           }
/*     */         } 
/*  98 */         flag = true;
/*     */         
/*     */         break;
/*     */       } 
/* 102 */       blockPos = blockPos.below();
/*     */     }
/* 104 */     while (blockPos.getY() >= Mth.floor(minHeight) - 1);
/*     */     
/* 106 */     if (flag) {
/* 107 */       this.entity.level().addFreshEntity((Entity)new EvokerFangs(this.entity.level(), x, blockPos.getY() + d0, z, yRot, delay, (LivingEntity)this.entity));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDelay(RandomSource random, float modifier) {
/* 113 */     return Math.max(60, random.nextInt(100)) - Mth.floor(modifier) * 10;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void addAttributes(Mob mob) {
/* 118 */     ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(mob.getAttribute(Attributes.MAX_HEALTH))).addPermanentModifier(new AttributeModifier("194fec31-b36e-41fc-ad72-02a5cb891def", -((mob.getRandom().nextDouble() + 0.5D) * 2.0D), AttributeModifier.Operation.ADDITION));
/* 119 */     ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(mob.getAttribute(Attributes.MOVEMENT_SPEED))).addPermanentModifier(new AttributeModifier("5965c24d-8ac1-4f04-92ee-3d2724f976e8", -0.08D, AttributeModifier.Operation.ADDITION));
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\symbiont\EvokerFangsSpell.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
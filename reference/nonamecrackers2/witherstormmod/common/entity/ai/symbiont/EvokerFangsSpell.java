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
/*  29 */   private static final SimpleWeightedRandomList<EntityType<? extends Mob>> PILLAGERS = SimpleWeightedRandomList.m_146263_()
/*  30 */     .m_146271_(WitherStormModEntityTypes.SICKENED_PILLAGER.get(), 3)
/*  31 */     .m_146271_(WitherStormModEntityTypes.SICKENED_VINDICATOR.get(), 3)
/*  32 */     .m_146270_();
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
/*  43 */     if (this.entity.m_217043_().m_188503_(chance) == 1)
/*     */     {
/*  45 */       for (int j = 0; j < 3; j++) {
/*     */         
/*  47 */         Mob mob = WorldUtil.summonRandomMob((ServerLevel)this.entity.m_9236_(), this.entity.m_20183_(), this.entity.m_217043_(), 6, PILLAGERS, this.entity.shouldIncreaseDifficulty());
/*  48 */         if (mob != null) {
/*  49 */           addAttributes(mob);
/*     */         }
/*     */       } 
/*     */     }
/*  53 */     LivingEntity entity = this.entity.getRandomNearbyTargetOrFallback(target, WitheredSymbiontEntity.TARGET_PREDICATE);
/*  54 */     double minHeight = Math.min(entity.m_20186_(), this.entity.m_20186_()) - 2.0D;
/*  55 */     double maxHeight = Math.max(entity.m_20186_(), this.entity.m_20186_()) + 2.0D;
/*     */     
/*  57 */     int ringCount = 16;
/*  58 */     int fangsPerRing = 8;
/*     */     
/*  60 */     double initialRadius = 1.5D;
/*  61 */     double expansionRate = 3.0D + this.entity.m_217043_().m_188500_();
/*     */     
/*  63 */     for (int i = 0; i < ringCount; i++) {
/*     */       
/*  65 */       double radius = initialRadius + i * expansionRate;
/*  66 */       fangsPerRing += 4;
/*  67 */       double angleIncrement = 6.283185307179586D / fangsPerRing;
/*  68 */       for (int i1 = 0; i1 < fangsPerRing; i1++) {
/*     */         
/*  70 */         double angle = i1 * angleIncrement;
/*  71 */         double x = this.entity.m_20185_() + Math.cos(angle) * radius;
/*  72 */         double z = this.entity.m_20189_() + Math.sin(angle) * radius;
/*  73 */         createFang(x, z, minHeight, maxHeight, (float)angle, i1 + 2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void createFang(double x, double z, double minHeight, double maxHeight, float yRot, int delay) {
/*  80 */     BlockPos blockPos = BlockPos.m_274561_(x, maxHeight, z);
/*  81 */     boolean flag = false;
/*  82 */     double d0 = 0.0D;
/*     */ 
/*     */     
/*     */     do {
/*  86 */       BlockPos below = blockPos.m_7495_();
/*  87 */       BlockState state = this.entity.m_9236_().m_8055_(below);
/*  88 */       if (state.m_60783_((BlockGetter)this.entity.m_9236_(), below, Direction.UP)) {
/*     */         
/*  90 */         if (!this.entity.m_9236_().m_46859_(blockPos)) {
/*     */           
/*  92 */           BlockState state1 = this.entity.m_9236_().m_8055_(blockPos);
/*  93 */           VoxelShape shape = state1.m_60812_((BlockGetter)this.entity.m_9236_(), blockPos);
/*  94 */           if (!shape.m_83281_()) {
/*  95 */             d0 = shape.m_83297_(Direction.Axis.Y);
/*     */           }
/*     */         } 
/*  98 */         flag = true;
/*     */         
/*     */         break;
/*     */       } 
/* 102 */       blockPos = blockPos.m_7495_();
/*     */     }
/* 104 */     while (blockPos.m_123342_() >= Mth.m_14107_(minHeight) - 1);
/*     */     
/* 106 */     if (flag) {
/* 107 */       this.entity.m_9236_().m_7967_((Entity)new EvokerFangs(this.entity.m_9236_(), x, blockPos.m_123342_() + d0, z, yRot, delay, (LivingEntity)this.entity));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDelay(RandomSource random, float modifier) {
/* 113 */     return Math.max(60, random.m_188503_(100)) - Mth.m_14143_(modifier) * 10;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void addAttributes(Mob mob) {
/* 118 */     ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(mob.m_21051_(Attributes.f_22276_))).m_22125_(new AttributeModifier("194fec31-b36e-41fc-ad72-02a5cb891def", -((mob.m_217043_().m_188500_() + 0.5D) * 2.0D), AttributeModifier.Operation.ADDITION));
/* 119 */     ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(mob.m_21051_(Attributes.f_22279_))).m_22125_(new AttributeModifier("5965c24d-8ac1-4f04-92ee-3d2724f976e8", -0.08D, AttributeModifier.Operation.ADDITION));
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\symbiont\EvokerFangsSpell.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
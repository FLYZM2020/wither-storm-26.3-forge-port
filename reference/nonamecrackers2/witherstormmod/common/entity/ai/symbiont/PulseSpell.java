/*     */ package nonamecrackers2.witherstormmod.common.entity.ai.symbiont;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.tags.BlockTags;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.Vec2;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.network.PacketDistributor;
/*     */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
/*     */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
/*     */ import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ import nonamecrackers2.witherstormmod.common.packet.PlayerMotionMessage;
/*     */ import nonamecrackers2.witherstormmod.common.predicate.BlockPredicateBuilder;
/*     */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
/*     */ 
/*     */ public class PulseSpell extends SymbiontSpell {
/*  33 */   public static final Predicate<BlockState> CAN_BE_THROWN = BlockPredicateBuilder.and()
/*  34 */     .isNotAir()
/*  35 */     .isNotAFluid()
/*  36 */     .isNotTag(WitherStormModBlockTags.TAINTED_BLOCKS)
/*  37 */     .isNotTag(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST)
/*  38 */     .isNotTag(WitherStormModBlockTags.SMALL_CLUSTER_BLACKLIST)
/*  39 */     .isNotTag(BlockTags.f_278394_)
/*  40 */     .isNotTag(BlockTags.f_13079_)
/*  41 */     .isNotTag(WitherStormModBlockTags.RED_SUPPORT_BASE)
/*  42 */     .isNotTag(WitherStormModBlockTags.GREEN_SUPPORT_BASE)
/*  43 */     .isNotTag(WitherStormModBlockTags.AQUA_SUPPORT_BASE)
/*  44 */     .isNotTag(WitherStormModBlockTags.GRAY_SUPPORT_BASE)
/*  45 */     .isNotTag(WitherStormModBlockTags.WITHERED_BEACON_BASE).build();
/*     */ 
/*     */   
/*     */   public PulseSpell(WitheredSymbiontEntity symbiont, SpellType type) {
/*  49 */     super(symbiont, type);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void start(LivingEntity target) {}
/*     */ 
/*     */   
/*     */   public void cast(LivingEntity target) {
/*  58 */     List<LivingEntity> nearbyEntities = this.entity.getNearbyTargets(WitheredSymbiontEntity.PULSE_PREDICATE).toList();
/*  59 */     List<BlockPos> nearbyBlocks = getNearbyBlocks();
/*  60 */     this.entity.m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_ACTIVATES.get(), 4.0F, 2.0F);
/*  61 */     for (BlockPos pos : nearbyBlocks) {
/*     */       
/*  63 */       BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(this.entity.m_9236_());
/*  64 */       if (cluster != null) {
/*     */         
/*  66 */         cluster.populateWithRadius(pos, 1.0F, blockState -> (!blockState.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST) && !blockState.m_204336_(WitherStormModBlockTags.SMALL_CLUSTER_BLACKLIST) && !blockState.m_204336_(WitherStormModBlockTags.TAINTED_BLOCKS)));
/*  67 */         cluster.setTime(100);
/*  68 */         cluster.setShouldCrumble(false);
/*  69 */         int rotationDelta = this.entity.m_217043_().m_188503_(129) - 64;
/*  70 */         cluster.setRotationDelta(new Vec2(rotationDelta * 0.0625F, rotationDelta * 0.0625F));
/*  71 */         cluster.m_20242_(false);
/*  72 */         cluster.setPhysics(true);
/*  73 */         double deltaX = (pos.m_123341_() + this.entity.m_217043_().m_188503_(4)) - this.entity.m_20185_();
/*  74 */         double deltaY = (pos.m_123342_() + this.entity.m_217043_().m_188503_(4)) - this.entity.m_20186_();
/*  75 */         double deltaZ = (pos.m_123343_() + this.entity.m_217043_().m_188503_(4)) - this.entity.m_20189_();
/*  76 */         Vec3 deltaMovement = (new Vec3(deltaX, deltaY, deltaZ)).m_82541_().m_82490_(2.0D);
/*  77 */         cluster.m_20256_(deltaMovement);
/*  78 */         this.entity.m_9236_().m_7967_((Entity)cluster);
/*     */       } 
/*     */     } 
/*  81 */     for (Entity entity : nearbyEntities) {
/*     */       
/*  83 */       if (entity.m_6084_() && entity != this.entity) {
/*     */         
/*  85 */         double deltaX = entity.m_20185_() - this.entity.m_20185_();
/*  86 */         double deltaY = entity.m_20186_() + 1.0D - this.entity.m_20186_();
/*  87 */         double deltaZ = entity.m_20189_() - this.entity.m_20189_();
/*  88 */         Vec3 deltaMovement = (new Vec3(deltaX, deltaY, deltaZ)).m_82541_().m_82490_(3.0D);
/*  89 */         entity.m_20256_(deltaMovement);
/*  90 */         if (entity instanceof ServerPlayer) {
/*     */           
/*  92 */           PlayerMotionMessage message = new PlayerMotionMessage(deltaMovement);
/*  93 */           WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)entity), message);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doCasting(LivingEntity target) {
/* 102 */     List<LivingEntity> nearbyEntities = this.entity.getNearbyTargets(WitheredSymbiontEntity.PULSE_PREDICATE).toList();
/* 103 */     Vec3 entityDelta = this.entity.m_20299_(1.0F).m_82520_(this.entity.m_20185_(), this.entity.m_20188_(), this.entity.m_20189_()).m_82541_().m_82542_(0.1D, 0.1D, 0.1D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), this.entity.m_20185_(), this.entity.m_20188_(), this.entity.m_20189_(), 3, entityDelta.m_7096_(), entityDelta.m_7098_(), entityDelta.m_7094_(), 1.0D);
/* 128 */     for (Entity entity : nearbyEntities) {
/*     */       
/* 130 */       double x = entity.m_20185_() + this.entity.m_217043_().m_188583_() * 1.0D;
/* 131 */       double y = entity.m_20188_() + this.entity.m_217043_().m_188583_() * 1.0D;
/* 132 */       double z = entity.m_20189_() + this.entity.m_217043_().m_188583_() * 1.0D;
/* 133 */       Vec3 delta = entity.m_20299_(1.0F).m_82492_(x, y, z).m_82541_().m_82542_(0.1D, 0.1D, 0.1D);
/* 134 */       ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, 0, delta.m_7096_(), delta.m_7098_(), delta.m_7094_(), 1.0D);
/*     */     } 
/* 136 */     if (this.entity.f_19797_ % Math.max(2, 16) == 0) {
/* 137 */       this.entity.m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_ACTIVATES.get(), 4.0F, 0.75F);
/*     */     }
/*     */   }
/*     */   
/*     */   private List<BlockPos> getNearbyBlocks() {
/* 142 */     List<BlockPos> nearbyBlocks = new ArrayList<>();
/* 143 */     BlockPos entityPos = this.entity.m_20183_();
/* 144 */     int horizontalRange = 16;
/* 145 */     int verticalRange = 8;
/* 146 */     int maxCount = 1024;
/* 147 */     int blockCount = 0;
/* 148 */     for (int x = -horizontalRange; x <= horizontalRange; x++) {
/*     */       
/* 150 */       for (int y = -verticalRange; y <= verticalRange; y++) {
/*     */         
/* 152 */         for (int z = -horizontalRange; z <= horizontalRange; z++) {
/*     */           
/* 154 */           BlockPos searchPos = entityPos.m_7918_(x, y, z);
/* 155 */           if (blockCount >= maxCount) {
/*     */             break;
/*     */           }
/*     */           
/* 159 */           if (CAN_BE_THROWN.test(this.entity.m_9236_().m_8055_(searchPos))) {
/*     */             
/* 161 */             nearbyBlocks.add(searchPos);
/* 162 */             blockCount++;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 167 */     return nearbyBlocks;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDelay(RandomSource random, float modifier) {
/* 173 */     return Math.max(480, random.m_188503_(600)) - Mth.m_14143_(modifier) * 10;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\symbiont\PulseSpell.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
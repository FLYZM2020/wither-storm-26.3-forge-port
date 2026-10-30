/*     */ package nonamecrackers2.witherstormmod.common.entity;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.HolderGetter;
/*     */ import net.minecraft.core.RegistryAccess;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.core.registries.Registries;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.NbtUtils;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.ClientGamePacketListener;
/*     */ import net.minecraft.network.syncher.EntityDataAccessor;
/*     */ import net.minecraft.network.syncher.EntityDataSerializers;
/*     */ import net.minecraft.network.syncher.SynchedEntityData;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.damagesource.DamageSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.item.ItemEntity;
/*     */ import net.minecraft.world.entity.item.PrimedTnt;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.enchantment.ProtectionEnchantment;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.EntityBasedExplosionDamageCalculator;
/*     */ import net.minecraft.world.level.Explosion;
/*     */ import net.minecraft.world.level.ExplosionDamageCalculator;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.BaseFireBlock;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.material.FluidState;
/*     */ import net.minecraft.world.level.storage.loot.LootParams;
/*     */ import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.event.ForgeEventFactory;
/*     */ import net.minecraftforge.network.NetworkHooks;
/*     */ import net.minecraftforge.network.PacketDistributor;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModDamageTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ import nonamecrackers2.witherstormmod.common.packet.FormidibombExplosionMessage;
/*     */ import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
/*     */ import nonamecrackers2.witherstormmod.common.util.IFormidibomb;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class FormidibombEntity extends PrimedTnt implements IFormidibomb {
/*  68 */   private static final EntityDataAccessor<Optional<BlockState>> BLOCK_STATE = SynchedEntityData.defineId(FormidibombEntity.class, EntityDataSerializers.OPTIONAL_BLOCK_STATE);
/*  69 */   private static final EntityDataAccessor<Integer> START_FUSE = SynchedEntityData.defineId(FormidibombEntity.class, EntityDataSerializers.INT);
/*     */   @Nullable
/*     */   private LivingEntity owner;
/*     */   private int airTime;
/*     */   
/*     */   public FormidibombEntity(EntityType<? extends FormidibombEntity> type, Level world) {
/*  75 */     super(type, world);
/*  76 */     initiateFuse(1200);
/*     */   }
/*     */ 
/*     */   
/*     */   public FormidibombEntity(Level world, double x, double y, double z, @Nullable LivingEntity owner, @Nullable IFormidibomb previous, @Nullable BlockState state) {
/*  81 */     this((EntityType<? extends FormidibombEntity>)WitherStormModEntityTypes.FORMIDIBOMB.get(), world);
/*  82 */     setPos(x, y, z);
/*  83 */     double d0 = world.random.nextDouble() * 6.2831854820251465D;
/*  84 */     setDeltaMovement(-Math.sin(d0) * 0.02D, 0.2D, -Math.cos(d0) * 0.02D);
/*  85 */     initiateFuse(1200);
/*  86 */     this.xo = x;
/*  87 */     this.yo = y;
/*  88 */     this.zo = z;
/*  89 */     this.owner = owner;
/*  90 */     if (previous != null && previous.getStartFuse() > 0)
/*  91 */       copyFrom(previous); 
/*  92 */     setBlockState(state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void defineSynchedData() {
/*  98 */     super.defineSynchedData();
/*  99 */     this.entityData.define(BLOCK_STATE, Optional.empty());
/* 100 */     this.entityData.define(START_FUSE, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readAdditionalSaveData(@NotNull CompoundTag compound) {
/* 106 */     super.readAdditionalSaveData(compound);
/* 107 */     if (compound.contains("StartFuse"))
/* 108 */       setStartFuse(compound.getInt("StartFuse")); 
/* 109 */     if (compound.contains("State")) {
/* 110 */       setBlockState(NbtUtils.readBlockState((HolderGetter)level().holderLookup(Registries.BLOCK), compound.getCompound("State")));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addAdditionalSaveData(@NotNull CompoundTag compound) {
/* 116 */     super.addAdditionalSaveData(compound);
/* 117 */     compound.putInt("StartFuse", getStartFuse());
/* 118 */     compound.put("State", (Tag)NbtUtils.writeBlockState(getBlockState()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*     */     float radius, speed;
/* 124 */     super.tick();
/*     */     
/* 126 */     int startFuse = getStartFuse();
/* 127 */     int currentFuse = getFuse();
/* 128 */     float fuseProgress = 1.0F - currentFuse / startFuse;
/*     */     
/* 130 */     float initalRadius = 3.0F;
/* 131 */     float initalSpeed = 0.1F;
/*     */ 
/*     */ 
/*     */     
/* 135 */     if (fuseProgress < 0.5F) {
/*     */       
/* 137 */       speed = initalSpeed * (1.0F - 2.0F * fuseProgress);
/* 138 */       radius = initalRadius * (1.0F - fuseProgress);
/*     */     }
/*     */     else {
/*     */       
/* 142 */       speed = initalSpeed * 8.0F * (fuseProgress - 0.5F);
/* 143 */       radius = 1.5F;
/*     */     } 
/*     */     
/* 146 */     for (int i = 0; i < 6; i++) {
/*     */       
/* 148 */       double x = (this.random.nextFloat() * 2.0D - 1.0D) * radius;
/* 149 */       double y = (this.random.nextFloat() * 2.0D - 1.0D) * radius;
/* 150 */       double z = (this.random.nextFloat() * 2.0D - 1.0D) * radius;
/* 151 */       Vec3 start = position().add(x, y, z);
/* 152 */       Vec3 delta = (new Vec3(x, y, z)).scale(speed);
/*     */       
/* 154 */       if (fuseProgress > 0.5F)
/*     */       {
/* 156 */         delta = delta.reverse();
/*     */       }
/*     */       
/* 159 */       level().addParticle((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), start.x, start.y + 0.5D, start.z, -delta.x, -delta.y, -delta.z);
/*     */     } 
/*     */     
/* 162 */     if (onGround()) {
/* 163 */       this.airTime = 0;
/*     */     } else {
/* 165 */       this.airTime++;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFuseLife() {
/* 171 */     return getFuse();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLifeFuse(int fuse) {
/* 177 */     setFuse(fuse);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initiateFuse(int fuse) {
/* 182 */     setLifeFuse(fuse);
/* 183 */     setStartFuse(fuse);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStartFuse() {
/* 189 */     return ((Integer)this.entityData.get(START_FUSE)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartFuse(int fuse) {
/* 195 */     this.entityData.set(START_FUSE, Integer.valueOf(fuse));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LivingEntity getFormidibombOwner() {
/* 201 */     return this.owner;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormidibombOwner(LivingEntity entity) {
/* 207 */     this.owner = entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockState getBlockState() {
/* 212 */     return ((Optional<BlockState>)this.entityData.get(BLOCK_STATE)).orElse(((Block)WitherStormModBlocks.FORMIDIBOMB.get()).defaultBlockState());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockState(@Nullable BlockState state) {
/* 217 */     this.entityData.set(BLOCK_STATE, Optional.ofNullable(state));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getPickedResult(HitResult target) {
/* 223 */     ItemStack stack = new ItemStack((ItemLike)WitherStormModItems.FORMIDIBOMB.get());
/* 224 */     CompoundTag compound = stack.getOrCreateTag();
/* 225 */     compound.putInt("Fuse", getFuse());
/* 226 */     compound.putInt("StartFuse", getStartFuse());
/* 227 */     return stack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void explode() {
/* 233 */     explode(level(), (Entity)getOwner(), 48 + (level()).random.nextInt(9), 3, getX(), getY(), getZ());
/* 234 */     WitherStormModPacketHandlers.MAIN.send(PacketDistributor.NEAR.with(PacketDistributor.TargetPoint.p(getX(), getY(), getZ(), 100.0D, level().dimension())), new ShakeScreenMessage(480.0F, 24.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getPosition() {
/* 240 */     return position();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStillAlive() {
/* 246 */     return isAlive();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAirTime() {
/* 251 */     return this.airTime;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Packet<ClientGamePacketListener> getAddEntityPacket() {
/* 257 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void explode(Level world, @Nullable Entity entity, int radius, int squish, double x, double y, double z) {
/* 262 */     world.getProfiler().push("formidibomb_explosion");
/*     */     
/* 264 */     ExplosionDamageCalculator explosionContext = (entity == null) ? new ExplosionDamageCalculator() : (ExplosionDamageCalculator)new EntityBasedExplosionDamageCalculator(entity);
/* 265 */     Explosion explosion = new Explosion(world, entity, formidibomb(world.registryAccess(), entity), explosionContext, x, y, z, radius, true, Explosion.BlockInteraction.DESTROY);
/*     */     
/* 267 */     if (world.isClientSide) {
/*     */       
/* 269 */       int poofCount = Math.max(4500, world.random.nextInt(5001));
/* 270 */       for (int i = 0; i < poofCount; i++) {
/*     */         
/* 272 */         double speedX = world.random.nextGaussian() * 0.5D;
/* 273 */         double speedY = world.random.nextGaussian() * 0.5D;
/* 274 */         double speedZ = world.random.nextGaussian() * 0.5D;
/* 275 */         double deltaX = world.random.nextGaussian() * 4.0D;
/* 276 */         double deltaY = world.random.nextGaussian() * 4.0D;
/* 277 */         double deltaZ = world.random.nextGaussian() * 4.0D;
/* 278 */         world.addParticle((ParticleOptions)ParticleTypes.POOF, x + deltaX, y + deltaY, z + deltaZ, speedX, speedY, speedZ);
/*     */       } 
/*     */       
/* 281 */       int explosionCount = Math.max(50, world.random.nextInt(76));
/* 282 */       for (int j = 0; j < explosionCount; j++) {
/*     */         
/* 284 */         double deltaX = world.random.nextGaussian() * 12.0D;
/* 285 */         double deltaY = world.random.nextGaussian() * 12.0D;
/* 286 */         double deltaZ = world.random.nextGaussian() * 12.0D;
/* 287 */         world.addParticle((ParticleOptions)ParticleTypes.EXPLOSION_EMITTER, x + deltaX, y + deltaY, z + deltaZ, 0.0D, 0.0D, 0.0D);
/*     */       } 
/*     */     } 
/*     */     
/* 291 */     if (world.isClientSide) {
/*     */       
/* 293 */       SoundEvent event = (SoundEvent)WitherStormModSoundEvents.FORMIDIBOMB_EXPLOSION.get();
/* 294 */       if (!((Boolean)WitherStormModConfig.CLIENT.earRingingEffects.get()).booleanValue())
/* 295 */         event = (SoundEvent)WitherStormModSoundEvents.FORMIDIBOMB_EXPLOSION_QUIET.get(); 
/* 296 */       world.playLocalSound(x, y, z, event, SoundSource.BLOCKS, 16.0F, 1.0F, false);
/*     */     } 
/* 298 */     world.playSound(null, x, y, z, (SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), SoundSource.BLOCKS, 32.0F, 1.0F);
/*     */     
/* 300 */     if (!world.isClientSide) {
/*     */       
/* 302 */       FormidibombExplosionMessage message = new FormidibombExplosionMessage(entity, x, y, z, radius, squish);
/* 303 */       Objects.requireNonNull(world); WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(world::dimension), message);
/*     */       
/* 305 */       float diameter = radius * 2.0F;
/* 306 */       int minX = Mth.floor(x - diameter - 1.0D);
/* 307 */       int maxX = Mth.floor(x + diameter + 1.0D);
/* 308 */       int minY = Mth.floor(y - diameter - 1.0D);
/* 309 */       int maxY = Mth.floor(y + diameter + 1.0D);
/* 310 */       int minZ = Mth.floor(z - diameter - 1.0D);
/* 311 */       int maxZ = Mth.floor(z + diameter + 1.0D);
/* 312 */       AABB explosionArea = new AABB(minX, minY, minZ, maxX, maxY, maxZ);
/*     */       
/* 314 */       List<WitherStormEntity> entities = world.getEntitiesOfClass(WitherStormEntity.class, explosionArea.inflate(200.0D));
/* 315 */       for (WitherStormEntity storm : entities) {
/*     */         
/* 317 */         if (storm.canBeFormidibombed(true)) {
/* 318 */           storm.explode();
/*     */         }
/*     */       } 
/* 321 */       ObjectArrayList<Pair<ItemStack, BlockPos>> items = new ObjectArrayList();
/* 322 */       for (int i = -radius; i < radius; i++) {
/*     */         
/* 324 */         for (int j = -radius; j < radius; j++) {
/*     */           
/* 326 */           for (int k = -radius; k < radius; k++) {
/*     */             
/* 328 */             if (Mth.sqrt((i * i + j * j * squish + k * k)) < radius) {
/*     */               
/* 330 */               int random = world.random.nextInt(2);
/*     */               
/* 332 */               for (int l = -random; l <= random; l++) {
/*     */                 
/* 334 */                 BlockPos pos = BlockPos.containing(i + x, j + y - l, k + z);
/*     */                 
/* 336 */                 BlockState state = world.getBlockState(pos);
/* 337 */                 FluidState fluid = world.getFluidState(pos);
/* 338 */                 if (!state.is(Blocks.AIR)) {
/*     */                   
/* 340 */                   BlockPos imutable = pos.immutable();
/* 341 */                   if (state.canDropFromExplosion((BlockGetter)world, pos, explosion) && world instanceof ServerLevel) {
/*     */                     
/* 343 */                     BlockEntity tile = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
/* 344 */                     LootParams.Builder context = (new LootParams.Builder((ServerLevel)world)).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf((Vec3i)pos)).withParameter(LootContextParams.TOOL, ItemStack.EMPTY).withOptionalParameter(LootContextParams.BLOCK_ENTITY, tile).withOptionalParameter(LootContextParams.THIS_ENTITY, entity).withParameter(LootContextParams.EXPLOSION_RADIUS, Float.valueOf(radius));
/* 345 */                     state.getDrops(context).forEach(item -> addBlockDrops(items, item, imutable));
/*     */                   } 
/*     */ 
/*     */ 
/*     */                   
/* 350 */                   float resistance = radius * (0.7F + world.random.nextFloat() * 0.6F);
/* 351 */                   Optional<Float> blockResistance = explosionContext.getBlockExplosionResistance(explosion, (BlockGetter)world, pos, state, fluid);
/* 352 */                   if (blockResistance.isPresent()) {
/* 353 */                     resistance -= (((Float)blockResistance.get()).floatValue() + 0.3F) * (((Boolean)WitherStormModConfig.SERVER.lowerBlockResistance.get()).booleanValue() ? 0.01F : 0.3F);
/*     */                   }
/* 355 */                   if (resistance > 0.0F && explosionContext.shouldBlockExplode(explosion, (BlockGetter)world, pos, state, resistance)) {
/*     */                     
/* 357 */                     state.onBlockExploded(world, pos, explosion);
/* 358 */                     if (world.random.nextInt(3) == 0 && world.getBlockState(pos).is(Blocks.AIR) && world.getBlockState(pos.below()).isSolidRender((BlockGetter)world, pos.below())) {
/* 359 */                       world.setBlockAndUpdate(pos, BaseFireBlock.getState((BlockGetter)world, pos));
/*     */                     }
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 368 */       List<Entity> entitiesToExplode = world.getEntities(null, explosionArea);
/* 369 */       ForgeEventFactory.onExplosionDetonate(world, explosion, entitiesToExplode, diameter);
/* 370 */       Vec3 vector = new Vec3(x, y, z);
/*     */       
/* 372 */       for (Entity toExplode : entitiesToExplode) {
/*     */         
/* 374 */         if (!toExplode.ignoreExplosion()) {
/*     */           
/* 376 */           double distance = Math.sqrt(toExplode.distanceToSqr(vector)) / diameter;
/* 377 */           if (distance <= 1.0D) {
/* 378 */             double relativeX = toExplode.getX() - x;
/* 379 */             double relativeY = ((toExplode instanceof PrimedTnt) ? toExplode.getY() : toExplode.getEyeY()) - y;
/* 380 */             double relativeZ = toExplode.getZ() - z;
/*     */             
/* 382 */             double sqrtPos = Math.sqrt(relativeX * relativeX + relativeY * relativeY + relativeZ * relativeZ);
/* 383 */             if (sqrtPos != 0.0D) {
/* 384 */               relativeX /= sqrtPos;
/* 385 */               relativeY /= sqrtPos;
/* 386 */               relativeZ /= sqrtPos;
/* 387 */               double seenPercent = Explosion.getSeenPercent(vector, toExplode);
/* 388 */               double explosionPower = (1.0D - distance) * seenPercent;
/* 389 */               toExplode.hurt(formidibomb(world.registryAccess(), entity), (int)((explosionPower * explosionPower + explosionPower) / 2.0D * 7.0D * diameter + 1.0D));
/*     */               
/* 391 */               double explosionPowerModifiable = explosionPower;
/* 392 */               if (toExplode instanceof LivingEntity) {
/* 393 */                 explosionPowerModifiable = ProtectionEnchantment.getExplosionKnockbackAfterDampener((LivingEntity)toExplode, explosionPower);
/*     */               }
/* 395 */               toExplode.setDeltaMovement(toExplode.getDeltaMovement().add(relativeX * (explosionPowerModifiable + radius), relativeY * (explosionPowerModifiable + radius), relativeZ * (explosionPowerModifiable + radius)));
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 401 */       for (ObjectListIterator<Pair<ItemStack, BlockPos>> objectListIterator = items.iterator(); objectListIterator.hasNext(); ) { Pair<ItemStack, BlockPos> pair = objectListIterator.next();
/* 402 */         Block.popResource(world, (BlockPos)pair.getSecond(), (ItemStack)pair.getFirst()); }
/*     */     
/*     */     } 
/* 405 */     world.getProfiler().pop();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void addBlockDrops(ObjectArrayList<Pair<ItemStack, BlockPos>> items, ItemStack stack, BlockPos pos) {
/* 411 */     int i = items.size();
/* 412 */     for (int j = 0; j < i; j++) {
/*     */       
/* 414 */       Pair<ItemStack, BlockPos> pair = (Pair<ItemStack, BlockPos>)items.get(j);
/* 415 */       ItemStack pairStack = (ItemStack)pair.getFirst();
/* 416 */       if (ItemEntity.areMergable(pairStack, stack)) {
/*     */         
/* 418 */         ItemStack merged = ItemEntity.merge(pairStack, stack, 16);
/* 419 */         items.set(j, Pair.of(merged, pair.getSecond()));
/* 420 */         if (stack.isEmpty()) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */     } 
/* 425 */     items.add(Pair.of(stack, pos));
/*     */   }
/*     */ 
/*     */   
/*     */   public static DamageSource formidibomb(RegistryAccess access, @Nullable Entity entity) {
/* 430 */     return (entity != null) ? WitherStormModDamageTypes.source(access, WitherStormModDamageTypes.PLAYER_FORMIDIBOMB, entity) : WitherStormModDamageTypes.source(access, WitherStormModDamageTypes.FORMIDIBOMB);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\FormidibombEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
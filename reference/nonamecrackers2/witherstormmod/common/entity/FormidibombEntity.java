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
/*  68 */   private static final EntityDataAccessor<Optional<BlockState>> BLOCK_STATE = SynchedEntityData.m_135353_(FormidibombEntity.class, EntityDataSerializers.f_268618_);
/*  69 */   private static final EntityDataAccessor<Integer> START_FUSE = SynchedEntityData.m_135353_(FormidibombEntity.class, EntityDataSerializers.f_135028_);
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
/*  82 */     m_6034_(x, y, z);
/*  83 */     double d0 = world.f_46441_.m_188500_() * 6.2831854820251465D;
/*  84 */     m_20334_(-Math.sin(d0) * 0.02D, 0.2D, -Math.cos(d0) * 0.02D);
/*  85 */     initiateFuse(1200);
/*  86 */     this.f_19854_ = x;
/*  87 */     this.f_19855_ = y;
/*  88 */     this.f_19856_ = z;
/*  89 */     this.owner = owner;
/*  90 */     if (previous != null && previous.getStartFuse() > 0)
/*  91 */       copyFrom(previous); 
/*  92 */     setBlockState(state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_8097_() {
/*  98 */     super.m_8097_();
/*  99 */     this.f_19804_.m_135372_(BLOCK_STATE, Optional.empty());
/* 100 */     this.f_19804_.m_135372_(START_FUSE, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_7378_(@NotNull CompoundTag compound) {
/* 106 */     super.m_7378_(compound);
/* 107 */     if (compound.m_128441_("StartFuse"))
/* 108 */       setStartFuse(compound.m_128451_("StartFuse")); 
/* 109 */     if (compound.m_128441_("State")) {
/* 110 */       setBlockState(NbtUtils.m_247651_((HolderGetter)m_9236_().m_246945_(Registries.f_256747_), compound.m_128469_("State")));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_7380_(@NotNull CompoundTag compound) {
/* 116 */     super.m_7380_(compound);
/* 117 */     compound.m_128405_("StartFuse", getStartFuse());
/* 118 */     compound.m_128365_("State", (Tag)NbtUtils.m_129202_(getBlockState()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_8119_() {
/*     */     float radius, speed;
/* 124 */     super.m_8119_();
/*     */     
/* 126 */     int startFuse = getStartFuse();
/* 127 */     int currentFuse = m_32100_();
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
/* 148 */       double x = (this.f_19796_.m_188501_() * 2.0D - 1.0D) * radius;
/* 149 */       double y = (this.f_19796_.m_188501_() * 2.0D - 1.0D) * radius;
/* 150 */       double z = (this.f_19796_.m_188501_() * 2.0D - 1.0D) * radius;
/* 151 */       Vec3 start = m_20182_().m_82520_(x, y, z);
/* 152 */       Vec3 delta = (new Vec3(x, y, z)).m_82490_(speed);
/*     */       
/* 154 */       if (fuseProgress > 0.5F)
/*     */       {
/* 156 */         delta = delta.m_82548_();
/*     */       }
/*     */       
/* 159 */       m_9236_().m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), start.f_82479_, start.f_82480_ + 0.5D, start.f_82481_, -delta.f_82479_, -delta.f_82480_, -delta.f_82481_);
/*     */     } 
/*     */     
/* 162 */     if (m_20096_()) {
/* 163 */       this.airTime = 0;
/*     */     } else {
/* 165 */       this.airTime++;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFuseLife() {
/* 171 */     return m_32100_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLifeFuse(int fuse) {
/* 177 */     m_32085_(fuse);
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
/* 189 */     return ((Integer)this.f_19804_.m_135370_(START_FUSE)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartFuse(int fuse) {
/* 195 */     this.f_19804_.m_135381_(START_FUSE, Integer.valueOf(fuse));
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
/* 212 */     return ((Optional<BlockState>)this.f_19804_.m_135370_(BLOCK_STATE)).orElse(((Block)WitherStormModBlocks.FORMIDIBOMB.get()).m_49966_());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockState(@Nullable BlockState state) {
/* 217 */     this.f_19804_.m_135381_(BLOCK_STATE, Optional.ofNullable(state));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getPickedResult(HitResult target) {
/* 223 */     ItemStack stack = new ItemStack((ItemLike)WitherStormModItems.FORMIDIBOMB.get());
/* 224 */     CompoundTag compound = stack.m_41784_();
/* 225 */     compound.m_128405_("Fuse", m_32100_());
/* 226 */     compound.m_128405_("StartFuse", getStartFuse());
/* 227 */     return stack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_32103_() {
/* 233 */     explode(m_9236_(), (Entity)m_19749_(), 48 + (m_9236_()).f_46441_.m_188503_(9), 3, m_20185_(), m_20186_(), m_20189_());
/* 234 */     WitherStormModPacketHandlers.MAIN.send(PacketDistributor.NEAR.with(PacketDistributor.TargetPoint.p(m_20185_(), m_20186_(), m_20189_(), 100.0D, m_9236_().m_46472_())), new ShakeScreenMessage(480.0F, 24.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getPosition() {
/* 240 */     return m_20182_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStillAlive() {
/* 246 */     return m_6084_();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAirTime() {
/* 251 */     return this.airTime;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Packet<ClientGamePacketListener> m_5654_() {
/* 257 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void explode(Level world, @Nullable Entity entity, int radius, int squish, double x, double y, double z) {
/* 262 */     world.m_46473_().m_6180_("formidibomb_explosion");
/*     */     
/* 264 */     ExplosionDamageCalculator explosionContext = (entity == null) ? new ExplosionDamageCalculator() : (ExplosionDamageCalculator)new EntityBasedExplosionDamageCalculator(entity);
/* 265 */     Explosion explosion = new Explosion(world, entity, formidibomb(world.m_9598_(), entity), explosionContext, x, y, z, radius, true, Explosion.BlockInteraction.DESTROY);
/*     */     
/* 267 */     if (world.f_46443_) {
/*     */       
/* 269 */       int poofCount = Math.max(4500, world.f_46441_.m_188503_(5001));
/* 270 */       for (int i = 0; i < poofCount; i++) {
/*     */         
/* 272 */         double speedX = world.f_46441_.m_188583_() * 0.5D;
/* 273 */         double speedY = world.f_46441_.m_188583_() * 0.5D;
/* 274 */         double speedZ = world.f_46441_.m_188583_() * 0.5D;
/* 275 */         double deltaX = world.f_46441_.m_188583_() * 4.0D;
/* 276 */         double deltaY = world.f_46441_.m_188583_() * 4.0D;
/* 277 */         double deltaZ = world.f_46441_.m_188583_() * 4.0D;
/* 278 */         world.m_7106_((ParticleOptions)ParticleTypes.f_123759_, x + deltaX, y + deltaY, z + deltaZ, speedX, speedY, speedZ);
/*     */       } 
/*     */       
/* 281 */       int explosionCount = Math.max(50, world.f_46441_.m_188503_(76));
/* 282 */       for (int j = 0; j < explosionCount; j++) {
/*     */         
/* 284 */         double deltaX = world.f_46441_.m_188583_() * 12.0D;
/* 285 */         double deltaY = world.f_46441_.m_188583_() * 12.0D;
/* 286 */         double deltaZ = world.f_46441_.m_188583_() * 12.0D;
/* 287 */         world.m_7106_((ParticleOptions)ParticleTypes.f_123812_, x + deltaX, y + deltaY, z + deltaZ, 0.0D, 0.0D, 0.0D);
/*     */       } 
/*     */     } 
/*     */     
/* 291 */     if (world.f_46443_) {
/*     */       
/* 293 */       SoundEvent event = (SoundEvent)WitherStormModSoundEvents.FORMIDIBOMB_EXPLOSION.get();
/* 294 */       if (!((Boolean)WitherStormModConfig.CLIENT.earRingingEffects.get()).booleanValue())
/* 295 */         event = (SoundEvent)WitherStormModSoundEvents.FORMIDIBOMB_EXPLOSION_QUIET.get(); 
/* 296 */       world.m_7785_(x, y, z, event, SoundSource.BLOCKS, 16.0F, 1.0F, false);
/*     */     } 
/* 298 */     world.m_6263_(null, x, y, z, (SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), SoundSource.BLOCKS, 32.0F, 1.0F);
/*     */     
/* 300 */     if (!world.f_46443_) {
/*     */       
/* 302 */       FormidibombExplosionMessage message = new FormidibombExplosionMessage(entity, x, y, z, radius, squish);
/* 303 */       Objects.requireNonNull(world); WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(world::m_46472_), message);
/*     */       
/* 305 */       float diameter = radius * 2.0F;
/* 306 */       int minX = Mth.m_14107_(x - diameter - 1.0D);
/* 307 */       int maxX = Mth.m_14107_(x + diameter + 1.0D);
/* 308 */       int minY = Mth.m_14107_(y - diameter - 1.0D);
/* 309 */       int maxY = Mth.m_14107_(y + diameter + 1.0D);
/* 310 */       int minZ = Mth.m_14107_(z - diameter - 1.0D);
/* 311 */       int maxZ = Mth.m_14107_(z + diameter + 1.0D);
/* 312 */       AABB explosionArea = new AABB(minX, minY, minZ, maxX, maxY, maxZ);
/*     */       
/* 314 */       List<WitherStormEntity> entities = world.m_45976_(WitherStormEntity.class, explosionArea.m_82400_(200.0D));
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
/* 328 */             if (Mth.m_14116_((i * i + j * j * squish + k * k)) < radius) {
/*     */               
/* 330 */               int random = world.f_46441_.m_188503_(2);
/*     */               
/* 332 */               for (int l = -random; l <= random; l++) {
/*     */                 
/* 334 */                 BlockPos pos = BlockPos.m_274561_(i + x, j + y - l, k + z);
/*     */                 
/* 336 */                 BlockState state = world.m_8055_(pos);
/* 337 */                 FluidState fluid = world.m_6425_(pos);
/* 338 */                 if (!state.m_60713_(Blocks.f_50016_)) {
/*     */                   
/* 340 */                   BlockPos imutable = pos.m_7949_();
/* 341 */                   if (state.canDropFromExplosion((BlockGetter)world, pos, explosion) && world instanceof ServerLevel) {
/*     */                     
/* 343 */                     BlockEntity tile = state.m_155947_() ? world.m_7702_(pos) : null;
/* 344 */                     LootParams.Builder context = (new LootParams.Builder((ServerLevel)world)).m_287286_(LootContextParams.f_81460_, Vec3.m_82512_((Vec3i)pos)).m_287286_(LootContextParams.f_81463_, ItemStack.f_41583_).m_287289_(LootContextParams.f_81462_, tile).m_287289_(LootContextParams.f_81455_, entity).m_287286_(LootContextParams.f_81464_, Float.valueOf(radius));
/* 345 */                     state.m_287290_(context).forEach(item -> addBlockDrops(items, item, imutable));
/*     */                   } 
/*     */ 
/*     */ 
/*     */                   
/* 350 */                   float resistance = radius * (0.7F + world.f_46441_.m_188501_() * 0.6F);
/* 351 */                   Optional<Float> blockResistance = explosionContext.m_6617_(explosion, (BlockGetter)world, pos, state, fluid);
/* 352 */                   if (blockResistance.isPresent()) {
/* 353 */                     resistance -= (((Float)blockResistance.get()).floatValue() + 0.3F) * (((Boolean)WitherStormModConfig.SERVER.lowerBlockResistance.get()).booleanValue() ? 0.01F : 0.3F);
/*     */                   }
/* 355 */                   if (resistance > 0.0F && explosionContext.m_6714_(explosion, (BlockGetter)world, pos, state, resistance)) {
/*     */                     
/* 357 */                     state.onBlockExploded(world, pos, explosion);
/* 358 */                     if (world.f_46441_.m_188503_(3) == 0 && world.m_8055_(pos).m_60713_(Blocks.f_50016_) && world.m_8055_(pos.m_7495_()).m_60804_((BlockGetter)world, pos.m_7495_())) {
/* 359 */                       world.m_46597_(pos, BaseFireBlock.m_49245_((BlockGetter)world, pos));
/*     */                     }
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 368 */       List<Entity> entitiesToExplode = world.m_45933_(null, explosionArea);
/* 369 */       ForgeEventFactory.onExplosionDetonate(world, explosion, entitiesToExplode, diameter);
/* 370 */       Vec3 vector = new Vec3(x, y, z);
/*     */       
/* 372 */       for (Entity toExplode : entitiesToExplode) {
/*     */         
/* 374 */         if (!toExplode.m_6128_()) {
/*     */           
/* 376 */           double distance = Math.sqrt(toExplode.m_20238_(vector)) / diameter;
/* 377 */           if (distance <= 1.0D) {
/* 378 */             double relativeX = toExplode.m_20185_() - x;
/* 379 */             double relativeY = ((toExplode instanceof PrimedTnt) ? toExplode.m_20186_() : toExplode.m_20188_()) - y;
/* 380 */             double relativeZ = toExplode.m_20189_() - z;
/*     */             
/* 382 */             double sqrtPos = Math.sqrt(relativeX * relativeX + relativeY * relativeY + relativeZ * relativeZ);
/* 383 */             if (sqrtPos != 0.0D) {
/* 384 */               relativeX /= sqrtPos;
/* 385 */               relativeY /= sqrtPos;
/* 386 */               relativeZ /= sqrtPos;
/* 387 */               double seenPercent = Explosion.m_46064_(vector, toExplode);
/* 388 */               double explosionPower = (1.0D - distance) * seenPercent;
/* 389 */               toExplode.m_6469_(formidibomb(world.m_9598_(), entity), (int)((explosionPower * explosionPower + explosionPower) / 2.0D * 7.0D * diameter + 1.0D));
/*     */               
/* 391 */               double explosionPowerModifiable = explosionPower;
/* 392 */               if (toExplode instanceof LivingEntity) {
/* 393 */                 explosionPowerModifiable = ProtectionEnchantment.m_45135_((LivingEntity)toExplode, explosionPower);
/*     */               }
/* 395 */               toExplode.m_20256_(toExplode.m_20184_().m_82520_(relativeX * (explosionPowerModifiable + radius), relativeY * (explosionPowerModifiable + radius), relativeZ * (explosionPowerModifiable + radius)));
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 401 */       for (ObjectListIterator<Pair<ItemStack, BlockPos>> objectListIterator = items.iterator(); objectListIterator.hasNext(); ) { Pair<ItemStack, BlockPos> pair = objectListIterator.next();
/* 402 */         Block.m_49840_(world, (BlockPos)pair.getSecond(), (ItemStack)pair.getFirst()); }
/*     */     
/*     */     } 
/* 405 */     world.m_46473_().m_7238_();
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
/* 416 */       if (ItemEntity.m_32026_(pairStack, stack)) {
/*     */         
/* 418 */         ItemStack merged = ItemEntity.m_32029_(pairStack, stack, 16);
/* 419 */         items.set(j, Pair.of(merged, pair.getSecond()));
/* 420 */         if (stack.m_41619_()) {
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
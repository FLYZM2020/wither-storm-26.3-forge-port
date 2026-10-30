/*     */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import net.minecraft.advancements.CriteriaTriggers;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntitySelector;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.world.entity.ai.targeting.TargetingConditions;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.LevelReader;
/*     */ import net.minecraft.world.level.NaturalSpawner;
/*     */ import net.minecraft.world.level.ServerLevelAccessor;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.levelgen.Heightmap;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.shapes.VoxelShape;
/*     */ import net.minecraftforge.common.capabilities.ForgeCapabilities;
/*     */ import net.minecraftforge.event.ForgeEventFactory;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*     */ import nonamecrackers2.witherstormmod.common.capability.PlayerWitherStormData;
/*     */ import nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ import nonamecrackers2.witherstormmod.common.item.FormidibombItem;
/*     */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModItemTags;
/*     */ 
/*     */ public class SymbiontSummoningManager {
/*     */   protected final WitherStormEntity entity;
/*     */   
/*     */   public SymbiontSummoningManager(WitherStormEntity entity) {
/*  53 */     this.entity = entity;
/*     */   }
/*     */   protected int timeTillCanSummonSymbiont;
/*     */   
/*     */   public void tick() {
/*  58 */     if (this.timeTillCanSummonSymbiont > 0) {
/*  59 */       this.timeTillCanSummonSymbiont--;
/*     */     }
/*  61 */     if (((Boolean)WitherStormModConfig.SERVER.canSummonSymbiont.get()).booleanValue()) {
/*     */       
/*  63 */       int delay = Mth.clamp(((Integer)WitherStormModConfig.SERVER.minimumSpawnCheckInterval.get()).intValue(), 1, 240) * 20;
/*  64 */       if (this.entity.tickCount % delay * (this.entity.getRandom().nextInt(3) + 1) == 0) {
/*     */         
/*  66 */         List<Player> players = this.entity.level().getNearbyPlayers(TargetingConditions.DEFAULT, null, this.entity.getSearchBox());
/*  67 */         Collections.sort(players, (entity, entity1) -> Mth.floor(entity.distanceTo((Entity)this.entity) - entity1.distanceTo((Entity)this.entity)));
/*     */ 
/*     */ 
/*     */         
/*  71 */         Player player = null;
/*  72 */         for (Player playerToCheck : players) {
/*     */           
/*  74 */           if (playerApplicable(playerToCheck)) {
/*     */             
/*  76 */             player = playerToCheck;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*  81 */         if (canSummonSymbiont() && player != null) {
/*  82 */           summonSymbiont(player);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean canSummonSymbiont() {
/*  89 */     boolean flag = true;
/*     */     
/*  91 */     if (this.entity.isDeadOrPlayingDead() || !this.entity.isAlive()) {
/*  92 */       flag = false;
/*     */     }
/*  94 */     if (this.entity.getPhase() < 5 || this.entity.getConsumedEntities() < this.entity.getConsumptionAmountForPhase(5)) {
/*  95 */       flag = false;
/*     */     }
/*  97 */     AABB searchBox = this.entity.getSearchBox().inflate(50.0D);
/*  98 */     List<Entity> entities = WorldUtil.getPerformantEntitiesOfClass((ServerLevel)this.entity.level(), Entity.class, searchBox);
/*  99 */     for (Entity entity : entities) {
/*     */       
/* 101 */       if (entity.isAlive()) {
/*     */         
/* 103 */         if (entity instanceof FormidibombEntity) {
/*     */           
/* 105 */           FormidibombEntity formidibomb = (FormidibombEntity)entity;
/* 106 */           if (formidibomb.getStartFuse() > 0) {
/*     */             
/* 108 */             flag = false; break;
/*     */           } 
/*     */           continue;
/*     */         } 
/* 112 */         if (entity instanceof WitheredSymbiontEntity) {
/*     */           
/* 114 */           flag = false;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 120 */     if (this.entity.isAttractingFormidibomb()) {
/* 121 */       flag = false;
/*     */     }
/* 123 */     if (this.timeTillCanSummonSymbiont > 0) {
/* 124 */       flag = false;
/*     */     }
/* 126 */     if (this.entity.hasRecentlyBeenRevived()) {
/* 127 */       flag = false;
/*     */     }
/*     */     
/* 130 */     CommandBlockEntity commandBlock = this.entity.getBowelsCommandBlock();
/* 131 */     if (commandBlock != null)
/*     */     {
/* 133 */       if (commandBlock.getHealth() < commandBlock.getMaxHealth()) {
/* 134 */         flag = false;
/*     */       }
/*     */     }
/* 137 */     ServerLevel bowels = WitherStormMod.bowels((ServerLevel)this.entity.level());
/* 138 */     WitherStormBowelsManager manager = (WitherStormBowelsManager)bowels.getCapability(WitherStormModCapabilities.BOWELS_MANAGER).orElse(null);
/* 139 */     if (manager != null) {
/*     */       
/* 141 */       WitherStormBowelsManager.BowelsInstance instance = this.entity.getBowelsInstance();
/* 142 */       if (instance != null) {
/*     */         
/* 144 */         AABB box = (new AABB(instance.getPos())).inflate(50.0D);
/* 145 */         for (Player entity : bowels.getEntitiesOfClass(Player.class, box)) {
/*     */           
/* 147 */           if (manager.getInstanceFromEntity((Entity)entity) == instance) {
/*     */             
/* 149 */             flag = false;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 156 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean playerApplicable(Player player) {
/* 161 */     if (!EntitySelector.NO_SPECTATORS.test(player) || !EntitySelector.LIVING_ENTITY_STILL_ALIVE.test(player)) {
/* 162 */       return false;
/*     */     }
/* 164 */     if (player.position().subtract(this.entity.position()).horizontalDistance() > this.entity.getAttributeValue(Attributes.FOLLOW_RANGE)) {
/* 165 */       return false;
/*     */     }
/* 167 */     IItemHandler handler = (IItemHandler)player.getCapability(ForgeCapabilities.ITEM_HANDLER).orElse(null);
/* 168 */     if (handler != null)
/*     */     {
/* 170 */       for (int i = 0; i < handler.getSlots(); i++) {
/*     */         
/* 172 */         ItemStack stack = handler.getStackInSlot(i);
/* 173 */         if (stack.is((Item)WitherStormModItems.COMMAND_BLOCK_BOOK.get()))
/*     */         {
/* 175 */           return false;
/*     */         }
/* 177 */         if (stack.getItem() instanceof FormidibombItem) {
/*     */           
/* 179 */           FormidibombItem item = (FormidibombItem)stack.getItem();
/* 180 */           if (item.getStartFuse(stack) > 0) {
/* 181 */             return false;
/*     */           }
/* 183 */         } else if (this.entity.getPhase() > 5 && stack.is(WitherStormModItemTags.COMMAND_BLOCK_TOOLS)) {
/*     */           
/* 185 */           return false;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 190 */     PlayerWitherStormData data = (PlayerWitherStormData)player.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).orElse(null);
/* 191 */     if (data != null) {
/*     */       
/* 193 */       if (data.hasKilledSymbiontRecently()) {
/* 194 */         return false;
/*     */       }
/* 196 */       if (!data.hasChangedPhase(this.entity) && data.hasRecentlySummonedSymbiont(this.entity)) {
/* 197 */         return false;
/*     */       }
/*     */     } 
/* 200 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void summonSymbiont(Player player) {
/* 205 */     float angle = -((float)Math.atan2(player.getX() - this.entity.getX(), player.getZ() - this.entity.getZ()));
/* 206 */     float spawnX = Mth.cos(angle) * 30.0F + (float)this.entity.getX();
/* 207 */     float spawnZ = Mth.sin(angle) * 30.0F + (float)this.entity.getZ();
/* 208 */     double y = this.entity.getEyeY();
/* 209 */     for (int i = 0; i < 10; i++) {
/*     */       
/* 211 */       int randomX = Mth.floor(spawnX) + (int)(10.0D * this.entity.getRandom().nextGaussian()) + 5;
/* 212 */       int randomZ = Mth.floor(spawnZ) + (int)(10.0D * this.entity.getRandom().nextGaussian()) + 5;
/* 213 */       int radius = 5;
/* 214 */       Integer highest = null;
/* 215 */       BlockPos pos = null;
/* 216 */       for (int ax = -radius; ax <= radius; ax++) {
/*     */         
/* 218 */         for (int az = -radius; az <= radius; az++) {
/*     */           
/* 220 */           int x = ax + randomX;
/* 221 */           int z = az + randomZ;
/* 222 */           int height = player.level().getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z) - 1;
/* 223 */           if (highest == null || height > highest.intValue()) {
/*     */             
/* 225 */             highest = Integer.valueOf(height);
/* 226 */             pos = new BlockPos(x, highest.intValue(), z);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 231 */       BlockState state = this.entity.level().getBlockState(pos);
/* 232 */       if (state.isFaceSturdy((BlockGetter)this.entity.level(), pos, Direction.UP) && state.isValidSpawn((BlockGetter)this.entity.level(), pos, this.entity.getType())) {
/*     */         
/* 234 */         VoxelShape shape = state.getCollisionShape((BlockGetter)this.entity.level(), pos);
/* 235 */         if (!shape.isEmpty()) {
/* 236 */           y = shape.max(Direction.Axis.Y) + pos.getY();
/*     */         }
/* 238 */         if (NaturalSpawner.isSpawnPositionOk(SpawnPlacements.Type.ON_GROUND, (LevelReader)this.entity.level(), BlockPos.containing(pos.getX() + 0.5D, y, pos.getZ() + 0.5D), (EntityType)WitherStormModEntityTypes.WITHERED_SYMBIONT.get())) {
/*     */           
/* 240 */           WitheredSymbiontEntity entity = (WitheredSymbiontEntity)((EntityType)WitherStormModEntityTypes.WITHERED_SYMBIONT.get()).create(this.entity.level());
/* 241 */           entity.setPos(pos.getX() + 0.5D, y, pos.getZ() + 0.5D);
/* 242 */           double deltaX = player.getX() - entity.getX();
/* 243 */           double deltaY = player.getEyeY() - entity.getEyeY();
/* 244 */           double deltaZ = player.getZ() - entity.getZ();
/* 245 */           double sqrt = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
/* 246 */           float yRot = (float)(Mth.atan2(deltaZ, deltaX) * 57.2957763671875D) - 90.0F;
/* 247 */           float xRot = (float)-(Mth.atan2(deltaY, sqrt) * 57.2957763671875D);
/* 248 */           entity.yBodyRot = yRot;
/* 249 */           entity.setYRot(yRot);
/* 250 */           entity.setXRot(xRot);
/* 251 */           entity.setOwner(this.entity);
/* 252 */           this.entity.level().addFreshEntity((Entity)entity);
/* 253 */           ServerLevel world = (ServerLevel)this.entity.level();
/* 254 */           for (Player nearbyPlayers : world.getNearbyPlayers(TargetingConditions.DEFAULT, null, this.entity.getSearchBox()))
/* 255 */             CriteriaTriggers.SUMMONED_ENTITY.trigger((ServerPlayer)nearbyPlayers, (Entity)entity); 
/* 256 */           ForgeEventFactory.onFinalizeSpawn((Mob)entity, (ServerLevelAccessor)world, world.getCurrentDifficultyAt(entity.blockPosition()), MobSpawnType.TRIGGERED, null, null);
/* 257 */           entity.spawnAnim();
/* 258 */           world.sendParticles((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), entity.getX(), entity.getEyeY(), entity.getZ(), 20, this.entity.getRandom().nextGaussian(), this.entity.getRandom().nextGaussian(), this.entity.getRandom().nextGaussian(), 0.2D);
/* 259 */           world.sendParticles((ParticleOptions)ParticleTypes.LARGE_SMOKE, entity.getX(), entity.getEyeY(), entity.getZ(), 20, this.entity.getRandom().nextGaussian(), this.entity.getRandom().nextGaussian(), this.entity.getRandom().nextGaussian(), 0.01D);
/* 260 */           this.timeTillCanSummonSymbiont = Mth.clamp(((Integer)WitherStormModConfig.SERVER.witherStormSummoningDelay.get()).intValue(), 1, 20) * 1200 + this.entity.getRandom().nextInt(12000);
/* 261 */           player.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(data -> data.markSummonedSymbiont(this.entity));
/*     */ 
/*     */           
/* 264 */           this.entity.playSound((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_SUMMON.get(), 15.0F, 1.0F);
/* 265 */           entity.playSound((SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_SPAWN.get(), 12.0F, 1.0F);
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSummoningDelay() {
/* 274 */     return this.timeTillCanSummonSymbiont;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSummoningDelay(int delay) {
/* 279 */     this.timeTillCanSummonSymbiont = delay;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\SymbiontSummoningManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
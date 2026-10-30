/*     */ package nonamecrackers2.witherstormmod.common.world.tainting;
/*     */ 
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.resources.Identifier;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.packs.resources.PreparableReloadListener;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.world.effect.MobEffect;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.item.alchemy.Potion;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import net.minecraft.world.level.levelgen.structure.BoundingBox;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherSickened;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ import nonamecrackers2.witherstormmod.common.resources.BlockTainting;
/*     */ import nonamecrackers2.witherstormmod.common.resources.MobConversions;
/*     */ import nonamecrackers2.witherstormmod.common.resources.taint.MobConversion;
/*     */ import nonamecrackers2.witherstormmod.common.resources.taint.TaintRecipe;
/*     */ import org.apache.commons.lang3.mutable.MutableBoolean;
/*     */ import org.apache.commons.lang3.mutable.MutableInt;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldTainting
/*     */ {
/*  42 */   private static final Logger LOGGER = LogManager.getLogger("witherstormmod/WorldTainting");
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static WorldTainting instance;
/*     */ 
/*     */   
/*  49 */   private final BlockTainting blockTainting = new BlockTainting();
/*  50 */   private final MobConversions mobConversions = new MobConversions();
/*     */ 
/*     */ 
/*     */   
/*     */   private void addReloadListeners(Consumer<PreparableReloadListener> addListener) {
/*  55 */     addListener.accept(this.blockTainting);
/*  56 */     addListener.accept(this.mobConversions);
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Identifier, TaintRecipe> getBlockTaintingRecipes() {
/*  61 */     return this.blockTainting.getRecipes();
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Identifier, MobConversion> getMobConversions() {
/*  66 */     return this.mobConversions.getConversions();
/*     */   }
/*     */ 
/*     */   
/*     */   public Optional<MobConversion> getMobConversionFor(EntityType<?> type) {
/*  71 */     return getMobConversions().values().stream().filter(c -> c.from().equals(type)).findFirst();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EntityType<?> getOriginalTypeFromConvertedType(EntityType<?> converted) {
/*  76 */     return ((MobConversion)getMobConversions().values().stream().filter(c -> c.to().equals(converted)).findFirst().orElseThrow()).from();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canConvertBlock(BlockPos pos, Level level) {
/*  81 */     for (TaintRecipe recipe : getBlockTaintingRecipes().values()) {
/*     */       
/*  83 */       if (recipe.canConvertBlock(level.getBlockState(pos)))
/*  84 */         return true; 
/*     */     } 
/*  86 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int convertBlocks(BoundingBox box, Level level) {
/*  91 */     return convertBlocks(box, level, r -> true);
/*     */   }
/*     */ 
/*     */   
/*     */   public int convertBlocks(BoundingBox box, Level level, MobEffect effect) {
/*  96 */     return convertBlocks(box, level, r -> (r.effect() == effect));
/*     */   }
/*     */ 
/*     */   
/*     */   public int convertBlocks(BoundingBox box, Level level, Potion potion) {
/* 101 */     return convertBlocks(box, level, r -> r.canConvertWithPotion(potion));
/*     */   }
/*     */ 
/*     */   
/*     */   public int convertBlocks(BoundingBox box, Level level, Predicate<TaintRecipe> canConvert) {
/* 106 */     MutableInt count = new MutableInt();
/* 107 */     BlockPos.betweenClosedStream(box).forEach(pos -> {
/*     */           for (TaintRecipe recipe : getInstance().getBlockTaintingRecipes().values()) {
/*     */             BlockState state = level.getBlockState(pos);
/*     */             
/*     */             if (canConvert.test(recipe) && recipe.canConvertBlock(state)) {
/*     */               BlockState replacement = recipe.replacement();
/*     */               
/*     */               for (Property<?> property : (Iterable<Property<?>>)recipe.propertiesToCopy()) {
/*     */                 replacement = copyProperty(property, state, replacement);
/*     */               }
/*     */               
/*     */               if (level.setBlock(pos, replacement, 3, 0)) {
/*     */                 count.increment();
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         });
/* 125 */     return count.getValue().intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean convertBlock(BlockPos pos, Level level) {
/* 130 */     for (TaintRecipe recipe : getInstance().getBlockTaintingRecipes().values()) {
/*     */       
/* 132 */       BlockState state = level.getBlockState(pos);
/* 133 */       if (recipe.canConvertBlock(state)) {
/*     */         
/* 135 */         BlockState replacement = recipe.replacement();
/* 136 */         for (Property<?> property : (Iterable<Property<?>>)recipe.propertiesToCopy())
/* 137 */           replacement = copyProperty(property, state, replacement); 
/* 138 */         return level.setBlock(pos, replacement, 3);
/*     */       } 
/*     */     } 
/* 141 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T extends Comparable<T>> BlockState copyProperty(Property<T> property, BlockState from, BlockState to) {
/* 146 */     if (from.hasProperty(property)) {
/* 147 */       return (BlockState)to.setValue(property, from.getValue(property));
/*     */     }
/* 149 */     return to;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canConvertMob(Entity entity, boolean fromWitherSickness) {
/* 154 */     if (entity instanceof Mob) {
/*     */       
/* 156 */       MutableBoolean result = new MutableBoolean();
/* 157 */       getMobConversionFor(entity.getType()).ifPresent(conversion -> result.setValue(
/* 158 */             (conversion.canBeConvertedFromWitherSickness() || !fromWitherSickness)));
/*     */       
/* 160 */       return result.getValue().booleanValue();
/*     */     } 
/*     */ 
/*     */     
/* 164 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean convertMob(Mob mob, boolean fromWitherSickness) {
/* 170 */     EntityType<?> type = mob.getType();
/* 171 */     MutableBoolean result = new MutableBoolean();
/* 172 */     getMobConversionFor(type).ifPresent(conversion -> {
/*     */           if (conversion.canBeConvertedFromWitherSickness() || !fromWitherSickness) {
/*     */             CompoundTag data = mob.serializeNBT();
/*     */             
/*     */             try {
/*     */               Mob converted = mob.convertTo(conversion.to(), true);
/*     */               
/*     */               if (converted != null) {
/*     */                 copyExtraData(mob, converted);
/*     */                 
/*     */                 if (converted instanceof WitherSickened) {
/*     */                   WitherSickened sickened = (WitherSickened)converted;
/*     */                   
/*     */                   sickened.getData().setOriginal(type, data);
/*     */                   
/*     */                   sickened.convertFrom(mob);
/*     */                 } 
/*     */                 
/*     */                 converted.playSound((SoundEvent)WitherStormModSoundEvents.MOB_INFECTED.get());
/*     */                 ServerLevel level = (ServerLevel)converted.level();
/*     */                 AABB box = converted.getBoundingBox();
/*     */                 level.sendParticles((ParticleOptions)WitherStormModParticleTypes.PHLEGM.get(), (box.getCenter()).x, (box.getCenter()).y, (box.getCenter()).z, 10, box.getXsize() / 2.0D, box.getYsize() / 2.0D, box.getZsize() / 2.0D, 0.05D);
/*     */                 result.setTrue();
/*     */               } 
/* 196 */             } catch (ClassCastException e) {
/*     */               LOGGER.warn("Invalid mob conversion! Cannot convert to {} since it is not a valid mob. Please fix your mob conversion: {}", conversion.to(), conversion);
/*     */             } 
/*     */           } 
/*     */         });
/*     */     
/* 202 */     return result.getValue().booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void copyExtraData(Mob from, Mob to) {
/* 208 */     to.yBodyRot = from.yBodyRot;
/* 209 */     to.yHeadRot = from.yHeadRot;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void initializeOrAddListeners(Consumer<PreparableReloadListener> addListener) {
/* 214 */     if (instance == null)
/* 215 */       instance = new WorldTainting(); 
/* 216 */     instance.addReloadListeners(addListener);
/*     */   }
/*     */ 
/*     */   
/*     */   public static WorldTainting getInstance() {
/* 221 */     return Objects.<WorldTainting>requireNonNull(instance, "WorldTainting has not been initialized yet");
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\world\tainting\WorldTainting.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package nonamecrackers2.witherstormmod.common.util;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.ListTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.resources.Identifier;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.util.random.SimpleWeightedRandomList;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ 
/*     */ public class ClusterBuilderHelper
/*     */ {
/*  36 */   private static final Identifier BOWELS_LOOT = Identifier.fromNamespaceAndPath("witherstormmod", "chests/bowels_general");
/*  37 */   private static final SimpleWeightedRandomList<Block> OUTSIDE = SimpleWeightedRandomList.builder()
/*  38 */     .add(WitherStormModBlocks.TAINTED_FLESH_BLOCK.get(), 20)
/*  39 */     .add(WitherStormModBlocks.INFECTED_FLESH_BLOCK.get(), 3)
/*  40 */     .add(WitherStormModBlocks.TAINTED_PLANKS.get(), 1)
/*  41 */     .add(WitherStormModBlocks.TAINTED_COBBLESTONE.get(), 1)
/*  42 */     .build();
/*  43 */   private static final SimpleWeightedRandomList<Block> BLOCKS = SimpleWeightedRandomList.builder()
/*  44 */     .add(WitherStormModBlocks.TAINTED_FLESH_BLOCK.get(), 20)
/*  45 */     .add(WitherStormModBlocks.TAINTED_SAND.get(), 15)
/*  46 */     .add(WitherStormModBlocks.TAINTED_DIRT.get(), 10)
/*  47 */     .add(WitherStormModBlocks.TAINTED_COBBLESTONE.get(), 10)
/*  48 */     .add(WitherStormModBlocks.TAINTED_STONE.get(), 10)
/*  49 */     .add(WitherStormModBlocks.TAINTED_PLANKS.get(), 8)
/*  50 */     .add(WitherStormModBlocks.TAINTED_LOG.get(), 5)
/*  51 */     .add(WitherStormModBlocks.TAINTED_LEAVES.get(), 2)
/*  52 */     .add(WitherStormModBlocks.INFECTED_FLESH_BLOCK.get(), 5)
/*  53 */     .build();
/*     */   
/*  55 */   private static final SimpleWeightedRandomList<Block> JUNK = SimpleWeightedRandomList.builder()
/*  56 */     .add(WitherStormModBlocks.TAINTED_FLESH_BLOCK.get(), 8)
/*  57 */     .add(WitherStormModBlocks.TAINTED_SAND.get(), 5)
/*  58 */     .add(WitherStormModBlocks.TAINTED_SANDSTONE.get(), 3)
/*  59 */     .add(WitherStormModBlocks.TAINTED_DIRT.get(), 4)
/*  60 */     .add(WitherStormModBlocks.TAINTED_COBBLESTONE.get(), 6)
/*  61 */     .add(WitherStormModBlocks.TAINTED_STONE.get(), 6)
/*  62 */     .add(WitherStormModBlocks.TAINTED_DUST_BLOCK.get(), 2)
/*  63 */     .add(WitherStormModBlocks.TAINTED_GLASS.get(), 2)
/*  64 */     .add(WitherStormModBlocks.TAINTED_PUMPKIN.get(), 1)
/*  65 */     .add(WitherStormModBlocks.TAINTED_ZOMBIE_LYING.get(), 1)
/*  66 */     .add(WitherStormModBlocks.TAINTED_BONE_PILE.get(), 1)
/*  67 */     .add(WitherStormModBlocks.TAINTED_WOOD.get(), 4)
/*  68 */     .add(WitherStormModBlocks.TAINTED_PLANKS.get(), 6)
/*  69 */     .add(WitherStormModBlocks.TAINTED_LOG.get(), 6)
/*  70 */     .add(WitherStormModBlocks.TAINTED_LEAVES.get(), 4)
/*  71 */     .build();
/*  72 */   private static final SimpleWeightedRandomList<Block> DECORATION = SimpleWeightedRandomList.builder()
/*  73 */     .add(WitherStormModBlocks.TAINTED_DUST.get(), 10)
/*  74 */     .add(WitherStormModBlocks.TAINTED_MUSHROOM.get(), 5)
/*  75 */     .add(WitherStormModBlocks.TAINTED_BONE_PILE.get(), 1)
/*  76 */     .add(WitherStormModBlocks.TAINTED_ZOMBIE_SITTING.get(), 1)
/*  77 */     .build();
/*  78 */   private static final SimpleWeightedRandomList<Block> CHEWED = SimpleWeightedRandomList.builder()
/*  79 */     .add(WitherStormModBlocks.TAINTED_FLESH_BLOCK.get(), 10)
/*  80 */     .add(WitherStormModBlocks.INFECTED_FLESH_BLOCK.get(), 10)
/*  81 */     .add(WitherStormModBlocks.TAINTED_ZOMBIE_LYING.get(), 2)
/*  82 */     .add(WitherStormModBlocks.TAINTED_BONE_PILE.get(), 2)
/*  83 */     .build();
/*     */ 
/*     */   
/*     */   public static BlockClusterEntity buildSmallRandomDeathCluster(Level level, RandomSource random, int radius) {
/*  87 */     Map<BlockPos, BlockState> states = Maps.newLinkedHashMap();
/*     */     
/*  89 */     for (int i = 0; i <= 3; i++) {
/*     */       
/*  91 */       BlockPos offset = new BlockPos(random.nextInt(radius) - radius, random.nextInt(radius) - radius, random.nextInt(radius) - radius);
/*     */       
/*  93 */       for (int x = -radius; x < radius; x++) {
/*     */         
/*  95 */         for (int y = -radius; y < radius; y++) {
/*     */           
/*  97 */           for (int z = -radius; z < radius; z++) {
/*     */             
/*  99 */             int sqrt = Mth.floor(Mth.sqrt((x * x) + 1.0F + (y * y) + 1.0F + (z * z) + 1.0F));
/* 100 */             if (sqrt <= radius) {
/*     */               
/* 102 */               BlockPos pos = (new BlockPos(x, y, z)).offset((Vec3i)offset);
/* 103 */               if (!states.containsKey(pos)) {
/*     */                 BlockState state;
/*     */                 
/* 106 */                 if (random.nextFloat() < 0.975F) {
/* 107 */                   state = ((Block)JUNK.getRandomValue(random).orElse(Blocks.AIR)).defaultBlockState();
/*     */                 } else {
/* 109 */                   state = ((Block)WitherStormModBlocks.WITHERED_PHLEGM_BLOCK.get()).defaultBlockState();
/* 110 */                 }  states.put(pos, state);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 118 */     BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).create(level);
/* 119 */     assert cluster != null;
/* 120 */     cluster.populate(states);
/*     */     
/* 122 */     for (Map.Entry<BlockPos, BlockState> entry : states.entrySet()) {
/*     */       
/* 124 */       BlockState state = entry.getValue();
/* 125 */       if (state.is((Block)WitherStormModBlocks.WITHERED_PHLEGM_BLOCK.get())) {
/*     */         
/* 127 */         BlockPos pos = ((BlockPos)entry.getKey()).offset((Vec3i)cluster.getStartPos());
/* 128 */         CompoundTag tileData = new CompoundTag();
/* 129 */         BlockEntity.addEntityType(tileData, (BlockEntityType)WitherStormModBlockEntityTypes.WITHERED_PHLEGM.get());
/* 130 */         tileData.putInt("x", pos.getX());
/* 131 */         tileData.putInt("y", pos.getY());
/* 132 */         tileData.putInt("z", pos.getZ());
/* 133 */         tileData.putString("LootTable", BOWELS_LOOT.toString());
/* 134 */         cluster.addTileData(tileData);
/*     */       } 
/*     */     } 
/*     */     
/* 138 */     return cluster;
/*     */   }
/*     */ 
/*     */   
/*     */   public static BlockClusterEntity buildRandomDeathCluster(Level level, RandomSource random, int radius) {
/* 143 */     Map<BlockPos, BlockState> states = Maps.newLinkedHashMap();
/* 144 */     radius += random.nextInt(2);
/* 145 */     float stretch = 3.0F;
/* 146 */     for (int i = 0; i <= 3; i++) {
/*     */       
/* 148 */       float xStretch = stretch * Math.max(0.5F, random.nextFloat());
/* 149 */       float yStretch = stretch;
/* 150 */       float zStretch = stretch * Math.max(0.5F, random.nextFloat());
/* 151 */       int offsetR = radius - 1;
/* 152 */       BlockPos offset = new BlockPos(random.nextInt(offsetR * 2) - offsetR, random.nextInt(radius * 2) - radius, random.nextInt(radius * 2) - radius);
/* 153 */       for (int x = -radius; x < radius; x++) {
/*     */         
/* 155 */         for (int y = -radius; y < radius; y++) {
/*     */           
/* 157 */           for (int z = -radius; z < radius; z++) {
/*     */             
/* 159 */             int sqrt = Mth.floor(Mth.sqrt((x * x) * xStretch + 1.0F + (y * y) * yStretch + 1.0F + (z * z) * zStretch + 1.0F));
/* 160 */             if (sqrt <= radius) {
/*     */               
/* 162 */               SimpleWeightedRandomList<Block> list = OUTSIDE;
/* 163 */               if (sqrt < radius - 1)
/* 164 */                 list = BLOCKS; 
/* 165 */               int randomY = random.nextInt(2);
/* 166 */               for (int l = -randomY; l <= randomY; l++) {
/*     */                 
/* 168 */                 BlockPos pos = (new BlockPos(x, y - l, z)).offset((Vec3i)offset);
/* 169 */                 if (!states.containsKey(pos)) {
/*     */                   BlockState state;
/*     */                   
/* 172 */                   if (random.nextFloat() < 0.999F) {
/* 173 */                     state = ((Block)list.getRandomValue(random).orElse(Blocks.AIR)).defaultBlockState();
/*     */                   } else {
/* 175 */                     state = ((Block)WitherStormModBlocks.WITHERED_PHLEGM_BLOCK.get()).defaultBlockState();
/* 176 */                   }  states.put(pos, state);
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 184 */     Map<BlockPos, BlockState> toAdd = Maps.newHashMap();
/* 185 */     for (Map.Entry<BlockPos, BlockState> entry : states.entrySet()) {
/*     */       
/* 187 */       BlockPos above = ((BlockPos)entry.getKey()).above();
/* 188 */       if (!states.containsKey(above) && random.nextInt(3) == 0) {
/*     */         
/* 190 */         BlockState decoration = ((Block)DECORATION.getRandomValue(random).orElse(Blocks.AIR)).defaultBlockState();
/* 191 */         toAdd.put(above, decoration);
/*     */       } 
/*     */     } 
/* 194 */     BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).create(level);
/* 195 */     Objects.requireNonNull(states); toAdd.forEach(states::put);
/* 196 */     cluster.populate(states);
/* 197 */     for (Map.Entry<BlockPos, BlockState> entry : states.entrySet()) {
/*     */       
/* 199 */       BlockState state = entry.getValue();
/* 200 */       if (state.is((Block)WitherStormModBlocks.WITHERED_PHLEGM_BLOCK.get())) {
/*     */         
/* 202 */         BlockPos pos = ((BlockPos)entry.getKey()).offset((Vec3i)cluster.getStartPos());
/* 203 */         CompoundTag tileData = new CompoundTag();
/* 204 */         BlockEntity.addEntityType(tileData, (BlockEntityType)WitherStormModBlockEntityTypes.WITHERED_PHLEGM.get());
/* 205 */         tileData.putInt("x", pos.getX());
/* 206 */         tileData.putInt("y", pos.getY());
/* 207 */         tileData.putInt("z", pos.getZ());
/* 208 */         tileData.putString("LootTable", BOWELS_LOOT.toString());
/* 209 */         cluster.addTileData(tileData);
/*     */       } 
/*     */     } 
/* 212 */     return cluster;
/*     */   }
/*     */ 
/*     */   
/*     */   public static BlockClusterEntity buildPhlegmClusterWithItems(Level level, RandomSource random, List<ItemStack> items, @Nullable Component name, int experience) {
/* 217 */     int size = items.size();
/*     */     
/* 219 */     List<ListTag> seperatedItemTags = Lists.newArrayList();
/* 220 */     int currentSlot = 0;
/* 221 */     ListTag currentListTag = new ListTag();
/* 222 */     for (int i = 0; i < size; i++) {
/*     */       
/* 224 */       ItemStack stack = items.get(i);
/* 225 */       if (!stack.isEmpty()) {
/*     */         
/* 227 */         if (currentSlot >= 25) {
/*     */           
/* 229 */           currentSlot -= 25;
/* 230 */           seperatedItemTags.add(currentListTag);
/* 231 */           currentListTag = new ListTag();
/*     */         } 
/* 233 */         CompoundTag tag = new CompoundTag();
/* 234 */         tag.putByte("Slot", (byte)currentSlot);
/* 235 */         stack.copy().save(tag);
/* 236 */         currentListTag.add(tag);
/* 237 */         currentSlot += random.nextIntBetweenInclusive(1, 3);
/*     */       } 
/*     */     } 
/* 240 */     seperatedItemTags.add(currentListTag);
/*     */     
/* 242 */     int experiencePerPhlegm = experience / seperatedItemTags.size();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 249 */     List<CompoundTag> tagsPerPhlegmBlock = seperatedItemTags.stream().filter(Predicate.not(ListTag::isEmpty)).map(l -> { CompoundTag tag = new CompoundTag(); tag.put("Items", (Tag)l); tag.putInt("StoredXp", experiencePerPhlegm); return tag; }).toList();
/*     */     
/* 251 */     BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).create(level);
/*     */     
/* 253 */     List<BlockPos> availablePositions = Lists.newArrayList();
/* 254 */     int radius = 1;
/* 255 */     for (int x = -radius; x <= radius; x++) {
/*     */       
/* 257 */       for (int y = -radius; y <= radius; y++) {
/*     */         
/* 259 */         for (int z = -radius; z <= radius; z++) {
/* 260 */           availablePositions.add(new BlockPos(x, y, z));
/*     */         }
/*     */       } 
/*     */     } 
/* 264 */     Map<BlockPos, CompoundTag> phlegmBlocks = Maps.newHashMap();
/*     */     
/* 266 */     for (int j = 0; j < tagsPerPhlegmBlock.size(); j++) {
/*     */       
/* 268 */       if (j < availablePositions.size()) {
/*     */         
/* 270 */         int index = 0;
/* 271 */         if (availablePositions.size() > 0)
/* 272 */           index = random.nextInt(availablePositions.size()); 
/* 273 */         BlockPos pos = availablePositions.get(index);
/* 274 */         availablePositions.remove(index);
/* 275 */         CompoundTag tag = tagsPerPhlegmBlock.get(j);
/* 276 */         phlegmBlocks.put(pos, tag);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 282 */     Map<BlockPos, BlockState> blocks = (Map<BlockPos, BlockState>)phlegmBlocks.entrySet().stream().map(e -> Map.entry(e.getKey(), ((Block)WitherStormModBlocks.WITHERED_PHLEGM_BLOCK.get()).defaultBlockState())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
/*     */     
/* 284 */     for (BlockPos leftOver : availablePositions) {
/*     */       
/* 286 */       if (random.nextFloat() > 0.6F) {
/* 287 */         blocks.put(leftOver, ((Block)CHEWED.getRandomValue(random).get()).defaultBlockState());
/*     */       }
/*     */     } 
/* 290 */     cluster.populate(blocks);
/*     */     
/* 292 */     for (Map.Entry<BlockPos, CompoundTag> entry : phlegmBlocks.entrySet()) {
/*     */       
/* 294 */       CompoundTag tag = entry.getValue();
/* 295 */       BlockPos pos = ((BlockPos)entry.getKey()).offset((Vec3i)cluster.getStartPos());
/* 296 */       BlockEntity.addEntityType(tag, (BlockEntityType)WitherStormModBlockEntityTypes.WITHERED_PHLEGM.get());
/* 297 */       tag.putInt("x", pos.getX());
/* 298 */       tag.putInt("y", pos.getY());
/* 299 */       tag.putInt("z", pos.getZ());
/* 300 */       if (name != null)
/* 301 */         tag.putString("CustomName", Component.Serializer.toJson(name)); 
/* 302 */       cluster.addTileData(tag);
/*     */     } 
/*     */     
/* 305 */     return cluster;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\ClusterBuilderHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
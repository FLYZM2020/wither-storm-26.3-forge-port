/*     */ package nonamecrackers2.witherstormmod.common.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.HolderGetter;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.ListTag;
/*     */ import net.minecraft.nbt.NbtUtils;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.world.level.ChunkPos;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.Vec2;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WitherStormModNBTUtil
/*     */ {
/*     */   public static ListTag writeBlockStatePosMap(Map<BlockPos, BlockState> map) {
/*  24 */     ListTag list = new ListTag();
/*  25 */     for (Map.Entry<BlockPos, BlockState> entry : map.entrySet()) {
/*     */       
/*  27 */       CompoundTag stateCompound = NbtUtils.writeBlockState(entry.getValue());
/*  28 */       stateCompound.put("RelativePos", (Tag)NbtUtils.writeBlockPos(entry.getKey()));
/*  29 */       list.add(stateCompound);
/*     */     } 
/*     */     
/*  32 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ListTag writeCompoundList(List<CompoundTag> compounds) {
/*  37 */     ListTag list = new ListTag();
/*  38 */     for (int i = 0; i < compounds.size(); i++)
/*  39 */       list.add(compounds.get(i)); 
/*  40 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public static CompoundTag writeChunkPosList(List<ChunkPos> chunks) {
/*  45 */     CompoundTag compound = new CompoundTag();
/*  46 */     for (int i = 0; i < chunks.size(); i++) {
/*     */       
/*  48 */       CompoundTag chunkCompound = new CompoundTag();
/*  49 */       ChunkPos pos = chunks.get(i);
/*  50 */       chunkCompound.putInt("x", pos.x());
/*  51 */       chunkCompound.putInt("z", pos.z());
/*  52 */       compound.put("Chunk" + i, (Tag)chunkCompound);
/*     */     } 
/*  54 */     return compound;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Map<BlockPos, BlockState> readBlockStatePosMap(HolderGetter<Block> getter, ListTag list) {
/*  59 */     Map<BlockPos, BlockState> blocks = new HashMap<>();
/*  60 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/*  62 */       CompoundTag stateCompound = list.getCompoundOrEmpty(i);
/*  63 */       BlockState state = NbtUtils.readBlockState(getter, stateCompound);
/*  64 */       BlockPos pos = NbtUtils.readBlockPos(stateCompound.getCompound("RelativePos"));
/*  65 */       blocks.put(pos, state);
/*     */     } 
/*  67 */     return blocks;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<CompoundTag> readCompoundList(ListTag list) {
/*  72 */     List<CompoundTag> compounds = new ArrayList<>();
/*  73 */     for (int i = 0; i < list.size(); i++)
/*  74 */       compounds.add(list.getCompoundOrEmpty(i)); 
/*  75 */     return compounds;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<ChunkPos> readChunkPosList(CompoundTag compound) {
/*  80 */     List<ChunkPos> list = new ArrayList<>();
/*  81 */     for (int i = 0; i < compound.getAllKeys().size(); i++) {
/*     */       
/*  83 */       if (compound.contains("Chunk" + i)) {
/*     */         
/*  85 */         CompoundTag chunkCompound = compound.getCompoundOrEmpty("Chunk" + i);
/*  86 */         ChunkPos pos = new ChunkPos(chunkCompound.getIntOr("x", 0), chunkCompound.getIntOr("z", 0));
/*  87 */         list.add(pos);
/*     */       } 
/*     */     } 
/*  90 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public static CompoundTag writeVector2f(Vec2 vector) {
/*  95 */     CompoundTag compound = new CompoundTag();
/*  96 */     compound.putFloat("x", vector.x);
/*  97 */     compound.putFloat("y", vector.y);
/*  98 */     return compound;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Vec2 readVector2f(CompoundTag compound) {
/* 103 */     return new Vec2(compound.getFloatOr("x", 0.0F), compound.getFloatOr("y", 0.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public static CompoundTag writeChunkPos(ChunkPos pos) {
/* 108 */     CompoundTag compound = new CompoundTag();
/* 109 */     compound.putInt("x", pos.x());
/* 110 */     compound.putInt("z", pos.z());
/* 111 */     return compound;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ChunkPos readChunkPos(CompoundTag compound) {
/* 116 */     return new ChunkPos(compound.getIntOr("x", 0), compound.getIntOr("z", 0));
/*     */   }
/*     */ 
/*     */   
/*     */   public static CompoundTag writeVector3d(Vec3 vector) {
/* 121 */     CompoundTag compound = new CompoundTag();
/* 122 */     compound.putDouble("X", vector.x());
/* 123 */     compound.putDouble("Y", vector.y());
/* 124 */     compound.putDouble("Z", vector.z());
/* 125 */     return compound;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Vec3 readVector3d(CompoundTag compound) {
/* 130 */     return new Vec3(compound.getDoubleOr("X", 0.0D), compound.getDoubleOr("Y", 0.0D), compound.getDoubleOr("Z", 0.0D));
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\WitherStormModNBTUtil.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
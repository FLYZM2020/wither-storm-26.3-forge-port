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
/*  27 */       CompoundTag stateCompound = NbtUtils.m_129202_(entry.getValue());
/*  28 */       stateCompound.m_128365_("RelativePos", (Tag)NbtUtils.m_129224_(entry.getKey()));
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
/*  50 */       chunkCompound.m_128405_("x", pos.f_45578_);
/*  51 */       chunkCompound.m_128405_("z", pos.f_45579_);
/*  52 */       compound.m_128365_("Chunk" + i, (Tag)chunkCompound);
/*     */     } 
/*  54 */     return compound;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Map<BlockPos, BlockState> readBlockStatePosMap(HolderGetter<Block> getter, ListTag list) {
/*  59 */     Map<BlockPos, BlockState> blocks = new HashMap<>();
/*  60 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/*  62 */       CompoundTag stateCompound = list.m_128728_(i);
/*  63 */       BlockState state = NbtUtils.m_247651_(getter, stateCompound);
/*  64 */       BlockPos pos = NbtUtils.m_129239_(stateCompound.m_128469_("RelativePos"));
/*  65 */       blocks.put(pos, state);
/*     */     } 
/*  67 */     return blocks;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<CompoundTag> readCompoundList(ListTag list) {
/*  72 */     List<CompoundTag> compounds = new ArrayList<>();
/*  73 */     for (int i = 0; i < list.size(); i++)
/*  74 */       compounds.add(list.m_128728_(i)); 
/*  75 */     return compounds;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<ChunkPos> readChunkPosList(CompoundTag compound) {
/*  80 */     List<ChunkPos> list = new ArrayList<>();
/*  81 */     for (int i = 0; i < compound.m_128431_().size(); i++) {
/*     */       
/*  83 */       if (compound.m_128441_("Chunk" + i)) {
/*     */         
/*  85 */         CompoundTag chunkCompound = compound.m_128469_("Chunk" + i);
/*  86 */         ChunkPos pos = new ChunkPos(chunkCompound.m_128451_("x"), chunkCompound.m_128451_("z"));
/*  87 */         list.add(pos);
/*     */       } 
/*     */     } 
/*  90 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public static CompoundTag writeVector2f(Vec2 vector) {
/*  95 */     CompoundTag compound = new CompoundTag();
/*  96 */     compound.m_128350_("x", vector.f_82470_);
/*  97 */     compound.m_128350_("y", vector.f_82471_);
/*  98 */     return compound;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Vec2 readVector2f(CompoundTag compound) {
/* 103 */     return new Vec2(compound.m_128457_("x"), compound.m_128457_("y"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static CompoundTag writeChunkPos(ChunkPos pos) {
/* 108 */     CompoundTag compound = new CompoundTag();
/* 109 */     compound.m_128405_("x", pos.f_45578_);
/* 110 */     compound.m_128405_("z", pos.f_45579_);
/* 111 */     return compound;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ChunkPos readChunkPos(CompoundTag compound) {
/* 116 */     return new ChunkPos(compound.m_128451_("x"), compound.m_128451_("z"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static CompoundTag writeVector3d(Vec3 vector) {
/* 121 */     CompoundTag compound = new CompoundTag();
/* 122 */     compound.m_128347_("X", vector.m_7096_());
/* 123 */     compound.m_128347_("Y", vector.m_7098_());
/* 124 */     compound.m_128347_("Z", vector.m_7094_());
/* 125 */     return compound;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Vec3 readVector3d(CompoundTag compound) {
/* 130 */     return new Vec3(compound.m_128459_("X"), compound.m_128459_("Y"), compound.m_128459_("Z"));
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\WitherStormModNBTUtil.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
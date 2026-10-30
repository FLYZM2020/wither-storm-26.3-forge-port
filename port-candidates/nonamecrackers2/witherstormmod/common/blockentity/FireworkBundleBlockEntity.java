/*     */ package nonamecrackers2.witherstormmod.common.blockentity;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.core.particles.ParticleTypes;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.ListTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.projectile.FireworkRocketEntity;
/*     */ import net.minecraft.world.item.DyeColor;
/*     */ import net.minecraft.world.item.FireworkRocketItem;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Items;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntityTypes;
/*     */ 
/*     */ public class FireworkBundleBlockEntity extends BlockEntity {
/*  31 */   private final RandomSource random = RandomSource.create(); private static final int FUSE_TIME = 100;
/*     */   private static final int LAUNCH_TIME = 500;
/*     */   private int fuse;
/*     */   private int launchDuration;
/*     */   
/*     */   public FireworkBundleBlockEntity(BlockPos pos, BlockState state) {
/*  37 */     super((BlockEntityType)WitherStormModBlockEntityTypes.FIREWORK_BUNDLE.get(), pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void saveAdditional(CompoundTag tag) {
/*  43 */     super.saveAdditional(tag);
/*  44 */     tag.putInt("Fuse", this.fuse);
/*  45 */     tag.putInt("LaunchDuration", this.launchDuration);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void load(CompoundTag tag) {
/*  51 */     super.load(tag);
/*  52 */     this.fuse = tag.getInt("Fuse");
/*  53 */     this.launchDuration = tag.getInt("LaunchDuration");
/*     */   }
/*     */ 
/*     */   
/*     */   public void beginFuse() {
/*  58 */     if (this.fuse == 0 && this.launchDuration == 0) {
/*     */       
/*  60 */       this.fuse = 100;
/*  61 */       this.level.playSound(null, getBlockPos(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isActivated() {
/*  67 */     return (this.fuse > 0 || this.launchDuration > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void serverTick(Level level, BlockPos pos, BlockState state, FireworkBundleBlockEntity entity) {
/*  72 */     if (entity.fuse > 0) {
/*     */       
/*  74 */       if (level instanceof ServerLevel) { ServerLevel serverLevel = (ServerLevel)level;
/*     */         
/*  76 */         Vec3 vec = Vec3.atCenterOf((Vec3i)pos);
/*  77 */         serverLevel.sendParticles((ParticleOptions)ParticleTypes.SMOKE, vec.x, vec.y, vec.z, 1, 0.0D, 0.0D, 0.0D, 0.0D); }
/*     */ 
/*     */       
/*  80 */       entity.fuse--;
/*  81 */       if (entity.fuse == 0) {
/*  82 */         entity.launchDuration = 500;
/*     */       }
/*     */     } 
/*  85 */     if (entity.launchDuration > 0) {
/*     */       
/*  87 */       entity.launchDuration--;
/*     */       
/*  89 */       if (entity.launchDuration == 0) {
/*     */         
/*  91 */         level.removeBlock(pos, false);
/*     */ 
/*     */       
/*     */       }
/*  95 */       else if (entity.random.nextInt(3) == 0) {
/*     */         
/*  97 */         Vec3 vec = Vec3.atCenterOf((Vec3i)pos).add(entity.random.nextDouble() - 0.5D, 0.6D, entity.random.nextDouble() - 0.5D);
/*  98 */         ItemStack stack = createRandomFireworkItem(entity.random);
/*  99 */         FireworkRocketEntity rocket = new FireworkRocketEntity(level, vec.x, vec.y, vec.z, stack);
/* 100 */         Vec3 delta = rocket.getDeltaMovement().add((entity.random.nextDouble() - 0.5D) * 0.05D, 0.0D, (entity.random.nextDouble() - 0.5D) * 0.05D);
/* 101 */         rocket.setDeltaMovement(delta);
/* 102 */         level.addFreshEntity((Entity)rocket);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static ItemStack createRandomFireworkItem(RandomSource random) {
/* 110 */     ItemStack stack = new ItemStack((ItemLike)Items.FIREWORK_ROCKET);
/*     */     
/* 112 */     ListTag list = new ListTag();
/* 113 */     CompoundTag tag = new CompoundTag();
/*     */     
/* 115 */     tag.putBoolean("Flicker", random.nextBoolean());
/* 116 */     tag.putBoolean("Trail", random.nextBoolean());
/*     */     
/* 118 */     List<Integer> colors = Lists.newArrayList();
/*     */     
/* 120 */     int size = random.nextInt(5) + 1;
/* 121 */     for (int i = 0; i < size; i++) {
/*     */       
/* 123 */       DyeColor color = (DyeColor)Util.getRandom((Object[])DyeColor.values(), random);
/* 124 */       colors.add(Integer.valueOf(color.getFireworkColor()));
/*     */     } 
/*     */     
/* 127 */     tag.putIntArray("Colors", colors);
/* 128 */     tag.putByte("Type", (byte)((FireworkRocketItem.Shape)Util.getRandom((Object[])FireworkRocketItem.Shape.values(), random)).getId());
/*     */     
/* 130 */     list.add(tag);
/* 131 */     CompoundTag fireworks = stack.getOrCreateTagElement("Fireworks");
/* 132 */     fireworks.putByte("Flight", (byte)(random.nextInt(1) + 2));
/* 133 */     fireworks.put("Explosions", (Tag)list);
/*     */     
/* 135 */     return stack;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\blockentity\FireworkBundleBlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
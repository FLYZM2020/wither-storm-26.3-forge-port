/*     */ package nonamecrackers2.witherstormmod.common.blockentity;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import net.minecraft.Util;
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
/*  31 */   private final RandomSource random = RandomSource.m_216327_(); private static final int FUSE_TIME = 100;
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
/*     */   protected void m_183515_(CompoundTag tag) {
/*  43 */     super.m_183515_(tag);
/*  44 */     tag.m_128405_("Fuse", this.fuse);
/*  45 */     tag.m_128405_("LaunchDuration", this.launchDuration);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_142466_(CompoundTag tag) {
/*  51 */     super.m_142466_(tag);
/*  52 */     this.fuse = tag.m_128451_("Fuse");
/*  53 */     this.launchDuration = tag.m_128451_("LaunchDuration");
/*     */   }
/*     */ 
/*     */   
/*     */   public void beginFuse() {
/*  58 */     if (this.fuse == 0 && this.launchDuration == 0) {
/*     */       
/*  60 */       this.fuse = 100;
/*  61 */       this.f_58857_.m_247517_(null, m_58899_(), SoundEvents.f_12512_, SoundSource.BLOCKS);
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
/*  76 */         Vec3 vec = Vec3.m_82512_((Vec3i)pos);
/*  77 */         serverLevel.m_8767_((ParticleOptions)ParticleTypes.f_123762_, vec.f_82479_, vec.f_82480_, vec.f_82481_, 1, 0.0D, 0.0D, 0.0D, 0.0D); }
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
/*  91 */         level.m_7471_(pos, false);
/*     */ 
/*     */       
/*     */       }
/*  95 */       else if (entity.random.m_188503_(3) == 0) {
/*     */         
/*  97 */         Vec3 vec = Vec3.m_82512_((Vec3i)pos).m_82520_(entity.random.m_188500_() - 0.5D, 0.6D, entity.random.m_188500_() - 0.5D);
/*  98 */         ItemStack stack = createRandomFireworkItem(entity.random);
/*  99 */         FireworkRocketEntity rocket = new FireworkRocketEntity(level, vec.f_82479_, vec.f_82480_, vec.f_82481_, stack);
/* 100 */         Vec3 delta = rocket.m_20184_().m_82520_((entity.random.m_188500_() - 0.5D) * 0.05D, 0.0D, (entity.random.m_188500_() - 0.5D) * 0.05D);
/* 101 */         rocket.m_20256_(delta);
/* 102 */         level.m_7967_((Entity)rocket);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static ItemStack createRandomFireworkItem(RandomSource random) {
/* 110 */     ItemStack stack = new ItemStack((ItemLike)Items.f_42688_);
/*     */     
/* 112 */     ListTag list = new ListTag();
/* 113 */     CompoundTag tag = new CompoundTag();
/*     */     
/* 115 */     tag.m_128379_("Flicker", random.m_188499_());
/* 116 */     tag.m_128379_("Trail", random.m_188499_());
/*     */     
/* 118 */     List<Integer> colors = Lists.newArrayList();
/*     */     
/* 120 */     int size = random.m_188503_(5) + 1;
/* 121 */     for (int i = 0; i < size; i++) {
/*     */       
/* 123 */       DyeColor color = (DyeColor)Util.m_214670_((Object[])DyeColor.values(), random);
/* 124 */       colors.add(Integer.valueOf(color.m_41070_()));
/*     */     } 
/*     */     
/* 127 */     tag.m_128408_("Colors", colors);
/* 128 */     tag.m_128344_("Type", (byte)((FireworkRocketItem.Shape)Util.m_214670_((Object[])FireworkRocketItem.Shape.values(), random)).m_41236_());
/*     */     
/* 130 */     list.add(tag);
/* 131 */     CompoundTag fireworks = stack.m_41698_("Fireworks");
/* 132 */     fireworks.m_128344_("Flight", (byte)(random.m_188503_(1) + 2));
/* 133 */     fireworks.m_128365_("Explosions", (Tag)list);
/*     */     
/* 135 */     return stack;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\blockentity\FireworkBundleBlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
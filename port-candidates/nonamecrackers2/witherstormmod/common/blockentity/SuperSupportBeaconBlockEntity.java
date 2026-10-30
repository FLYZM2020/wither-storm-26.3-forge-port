/*     */ package nonamecrackers2.witherstormmod.common.blockentity;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.NbtUtils;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.Container;
/*     */ import net.minecraft.world.effect.MobEffect;
/*     */ import net.minecraft.world.effect.MobEffectInstance;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*     */ import net.minecraft.world.inventory.ContainerLevelAccess;
/*     */ import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.entity.BlockEntityType;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.network.PacketDistributor;
/*     */ import nonamecrackers2.witherstormmod.common.blockentity.inventory.SuperSupportBeaconMenu;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
/*     */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class SuperSupportBeaconBlockEntity
/*     */   extends AbstractSuperBeaconBlockEntity {
/*     */   public static final int EFFECT_AREA_ARC = 90;
/*  44 */   private static final int[] FALLBACK_COLOR = new int[] { 255, 255, 255 };
/*  45 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   @Nullable
/*     */   private AbstractSuperBeaconBlockEntity.Color color;
/*     */   @Nullable
/*     */   private BlockPos connectedBeacon;
/*     */   
/*     */   public SuperSupportBeaconBlockEntity(BlockPos pos, BlockState state) {
/*  52 */     super((BlockEntityType<? extends AbstractSuperBeaconBlockEntity>)WitherStormModBlockEntityTypes.SUPER_SUPPORT_BEACON.get(), pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  58 */     super.tick();
/*     */     
/*  60 */     if (!this.level.isClientSide) {
/*     */       
/*  62 */       BlockPos searchStart = this.worldPosition.below();
/*  63 */       int radius = 1;
/*  64 */       List<BlockState> blocks = Lists.newArrayList();
/*  65 */       for (int x = -radius; x <= radius; x++) {
/*     */         
/*  67 */         for (int z = -radius; z <= radius; z++)
/*  68 */           blocks.add(this.level.getBlockState(searchStart.offset(x, 0, z))); 
/*     */       } 
/*  70 */       AbstractSuperBeaconBlockEntity.Color matching = null;
/*  71 */       for (AbstractSuperBeaconBlockEntity.Color color : AbstractSuperBeaconBlockEntity.Color.values()) {
/*     */         
/*  73 */         if (blocks.stream().allMatch(block -> color.isValidBaseBlock(block))) {
/*     */           
/*  75 */           matching = color;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*  80 */       if (matching != this.color) {
/*     */         
/*  82 */         this.color = matching;
/*  83 */         markUpdated();
/*     */       } 
/*     */       
/*  86 */       SuperBeaconBlockEntity superBeaconBlockEntity = getNearbyValidBeacon();
/*     */       
/*  88 */       if (superBeaconBlockEntity != null) {
/*     */         
/*  90 */         if (this.connectedBeacon != superBeaconBlockEntity.getBlockPos())
/*  91 */           markUpdated(); 
/*  92 */         this.connectedBeacon = superBeaconBlockEntity.getBlockPos();
/*     */       } 
/*     */       
/*  95 */       boolean flag = (this.color != null && superBeaconBlockEntity != null);
/*  96 */       if (flag != this.isActive) {
/*     */         
/*  98 */         if (flag) {
/*     */           
/* 100 */           this.isActive = true;
/* 101 */           activate();
/*     */         }
/*     */         else {
/*     */           
/* 105 */           this.isActive = false;
/* 106 */           deactivate();
/*     */         } 
/* 108 */         markUpdated();
/*     */       } 
/*     */     } 
/*     */     
/* 112 */     SuperBeaconBlockEntity beacon = getConnectedBeaconEntity();
/* 113 */     if (beacon != null) {
/*     */       
/* 115 */       this.beaconLevel = beacon.beaconLevel;
/* 116 */       this.showWorkingArea = beacon.showWorkingArea();
/*     */       
/* 118 */       if (!this.level.isClientSide && this.color != null && beacon.getResummonTicks() == getResummonThreshold()) {
/*     */         
/* 120 */         playSound((SoundEvent)WitherStormModSoundEvents.WITHERED_BEACON_ACTIVATE.get(), 1.0F, 1.0F);
/* 121 */         playSound((SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), 10.0F, 1.0F);
/* 122 */         Vec3 pos = Vec3.atCenterOf((Vec3i)getConnectedBeacon());
/* 123 */         WitherStormModPacketHandlers.MAIN.send(PacketDistributor.NEAR.with(PacketDistributor.TargetPoint.p(pos.x, pos.y, pos.z, 20.0D, this.level.dimension())), new ShakeScreenMessage(80.0F, 10.0F));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void applyEffect(ServerLevel level) {
/* 131 */     if (getConnectedBeacon() != null) {
/*     */       
/* 133 */       BlockPos target = getConnectedBeacon();
/* 134 */       Vec3 targetVec = Vec3.atCenterOf((Vec3i)target);
/* 135 */       float angleWithMainBeacon = getAngleBetween(getBlockPos(), getConnectedBeacon());
/* 136 */       for (ServerPlayer player : level.players()) {
/*     */         
/* 138 */         float angle = (float)(Mth.atan2(player.getX() - targetVec.x, player.getZ() - targetVec.z) * 57.29577951308232D);
/* 139 */         float diff = (angleWithMainBeacon - angle + 180.0F + 360.0F) % 360.0F - 180.0F;
/* 140 */         if (diff <= 45.0F && diff >= -45.0F) {
/* 141 */           player.addEffect(new MobEffectInstance(this.effect, 205, this.beaconLevel - 1, true, true));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static float getAngleBetween(BlockPos blockPos, BlockPos blockTarget) {
/* 148 */     Vec3 pos = Vec3.atCenterOf((Vec3i)blockPos);
/* 149 */     Vec3 target = Vec3.atCenterOf((Vec3i)blockTarget);
/* 150 */     return (float)(Mth.atan2(pos.x - target.x, pos.z - target.z) * 57.29577951308232D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getThickness() {
/* 156 */     return 0.15F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getOuterThickness() {
/* 162 */     return 0.2F;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractSuperBeaconBlockEntity.Color getColor() {
/* 167 */     return this.color;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getBeamColor() {
/* 173 */     AbstractSuperBeaconBlockEntity.Color color = getColor();
/* 174 */     if (color != null) {
/* 175 */       return new int[] { color.getRed(), color.getGreen(), color.getBlue() };
/*     */     }
/* 177 */     return FALLBACK_COLOR;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private SuperBeaconBlockEntity getNearbyValidBeacon() {
/* 182 */     AABB box = (new AABB(getBlockPos())).inflate(5.0D);
/* 183 */     List<BlockEntity> entities = WorldUtil.getBlockEntitiesInAABB(this.level, box);
/* 184 */     for (BlockEntity entity : entities) {
/*     */       
/* 186 */       if (entity instanceof SuperBeaconBlockEntity) { SuperBeaconBlockEntity beacon = (SuperBeaconBlockEntity)entity; if (beacon.isConnected(getBlockPos()))
/* 187 */           return beacon;  }
/*     */     
/* 189 */     }  return null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BlockPos getConnectedBeacon() {
/* 194 */     return this.connectedBeacon;
/*     */   }
/*     */ 
/*     */   
/*     */   public SuperBeaconBlockEntity getConnectedBeaconEntity() {
/* 199 */     if (getConnectedBeacon() != null) {
/*     */       
/* 201 */       BlockEntity entity = this.level.getBlockEntity(getConnectedBeacon());
/* 202 */       if (entity instanceof SuperBeaconBlockEntity) { SuperBeaconBlockEntity superBeacon = (SuperBeaconBlockEntity)entity;
/* 203 */         return superBeacon; }
/*     */     
/* 205 */     }  return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Component getDisplayName() {
/* 211 */     return (this.name != null) ? this.name : (Component)Component.translatable("container.witherstormmod.withered_support_beacon");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void load(CompoundTag tag) {
/* 217 */     super.load(tag);
/* 218 */     int colorIndex = tag.getInt("Color");
/* 219 */     if (colorIndex >= 0 && colorIndex < (AbstractSuperBeaconBlockEntity.Color.values()).length) {
/* 220 */       this.color = AbstractSuperBeaconBlockEntity.Color.values()[colorIndex];
/* 221 */     } else if (colorIndex == -1) {
/* 222 */       this.color = null;
/*     */     } else {
/* 224 */       LOGGER.warn("Read incorrect color index value {}", Integer.valueOf(colorIndex));
/* 225 */     }  if (tag.contains("Connected")) {
/* 226 */       this.connectedBeacon = NbtUtils.readBlockPos(tag.getCompound("Connected"));
/*     */     } else {
/* 228 */       this.connectedBeacon = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void saveAdditional(CompoundTag tag) {
/* 234 */     super.saveAdditional(tag);
/* 235 */     if (this.color != null) {
/* 236 */       tag.putInt("Color", this.color.ordinal());
/*     */     } else {
/* 238 */       tag.putInt("Color", -1);
/* 239 */     }  if (this.connectedBeacon != null) {
/* 240 */       tag.put("Connected", (Tag)NbtUtils.writeBlockPos(this.connectedBeacon));
/*     */     } else {
/* 242 */       tag.remove("Connected");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<MobEffect> getValidEffects() {
/* 248 */     if (this.color != null) {
/* 249 */       return this.color.getValidEffects();
/*     */     }
/* 251 */     return (Set<MobEffect>)ImmutableSet.of();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShowWorkingArea(boolean flag) {
/* 257 */     SuperBeaconBlockEntity entity = getConnectedBeaconEntity();
/* 258 */     if (entity != null)
/* 259 */       entity.setShowWorkingArea(flag); 
/* 260 */     super.setShowWorkingArea(flag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
/* 266 */     return BaseContainerBlockEntity.canUnlock(player, this.lockKey, getDisplayName()) ? (AbstractContainerMenu)new SuperSupportBeaconMenu(id, (Container)inventory, this.data, ContainerLevelAccess.create(this.level, getBlockPos()), this::doPowerUp, getValidEffects()) : null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BlockPos getBeamPos() {
/* 271 */     SuperBeaconBlockEntity connected = getConnectedBeaconEntity();
/* 272 */     boolean flag = false;
/* 273 */     if (connected != null && this.color != null && connected.getResummonTicks() > getResummonThreshold())
/* 274 */       flag = true; 
/* 275 */     BlockPos pos = getConnectedBeacon();
/* 276 */     if (pos != null && flag) {
/* 277 */       return pos.above(3);
/*     */     }
/* 279 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean shouldDoActivatedAnim() {
/* 285 */     SuperBeaconBlockEntity connected = getConnectedBeaconEntity();
/* 286 */     boolean flag = false;
/* 287 */     if (connected != null && this.color != null)
/*     */     {
/* 289 */       if (connected.getResummonTicks() > getResummonThreshold())
/* 290 */         flag = true; 
/*     */     }
/* 292 */     return (super.shouldDoActivatedAnim() || flag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getResummonThreshold() {
/* 298 */     return 60 + this.color.ordinal() * 40;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\blockentity\SuperSupportBeaconBlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
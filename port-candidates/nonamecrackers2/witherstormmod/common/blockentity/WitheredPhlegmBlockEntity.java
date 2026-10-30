/*     */ package nonamecrackers2.witherstormmod.common.blockentity;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.NonNullList;
/*     */ import net.minecraft.core.Vec3i;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.ClientGamePacketListener;
/*     */ import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
/*     */ import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
/*     */ import net.minecraft.server.level.ServerChunkCache;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundEvents;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.Container;
/*     */ import net.minecraft.world.ContainerHelper;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.item.ItemEntity;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*     */ import net.minecraft.world.inventory.ContainerData;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.entity.BlockEntity;
/*     */ import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
/*     */ import net.minecraft.world.level.block.entity.HopperBlockEntity;
/*     */ import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.common.block.WitheredPhlegmBlock;
/*     */ import nonamecrackers2.witherstormmod.common.blockentity.inventory.WitheredPhlegmMenu;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ 
/*     */ public class WitheredPhlegmBlockEntity extends RandomizableContainerBlockEntity {
/*  40 */   private NonNullList<ItemStack> items = NonNullList.withSize(25, ItemStack.EMPTY); public static final int CONTAINER_SIZE = 25;
/*  41 */   private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter()
/*     */     {
/*     */       protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int i, int i2) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       protected void onOpen(Level level, BlockPos pos, BlockState state) {
/*  49 */         WitheredPhlegmBlockEntity.this.playSound((SoundEvent)WitherStormModSoundEvents.WITHERED_PHLEGM_BLOCK_OPEN.get());
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       protected void onClose(Level level, BlockPos pos, BlockState state) {
/*  55 */         WitheredPhlegmBlockEntity.this.playSound((SoundEvent)WitherStormModSoundEvents.WITHERED_PHLEGM_BLOCK_CLOSE.get());
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       protected boolean isOwnContainer(Player player) {
/*  61 */         AbstractContainerMenu abstractContainerMenu = player.containerMenu; if (abstractContainerMenu instanceof WitheredPhlegmMenu) { WitheredPhlegmMenu menu = (WitheredPhlegmMenu)abstractContainerMenu;
/*  62 */           return (menu.getContainer() == WitheredPhlegmBlockEntity.this); }
/*     */         
/*  64 */         return false;
/*     */       }
/*     */     };
/*  67 */   private final ContainerData dataAccess = new ContainerData()
/*     */     {
/*     */       
/*     */       public void set(int slot, int data)
/*     */       {
/*  72 */         if (slot == 0) {
/*  73 */           WitheredPhlegmBlockEntity.this.storedExperience = data;
/*     */         }
/*     */       }
/*     */ 
/*     */       
/*     */       public int getCount() {
/*  79 */         return 1;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public int get(int slot) {
/*  85 */         return (slot == 0) ? WitheredPhlegmBlockEntity.this.storedExperience : 0;
/*     */       }
/*     */     };
/*     */   
/*     */   private int storedExperience;
/*     */   
/*     */   public WitheredPhlegmBlockEntity(BlockPos pos, BlockState state) {
/*  92 */     super((BlockEntityType)WitherStormModBlockEntityTypes.WITHERED_PHLEGM.get(), pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getContainerSize() {
/*  98 */     return 25;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected NonNullList<ItemStack> getItems() {
/* 104 */     return this.items;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setItems(NonNullList<ItemStack> items) {
/* 110 */     this.items = items;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Component getDefaultName() {
/* 116 */     return (Component)Component.translatable("container.witherstormmod.phlegm_block");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
/* 122 */     return (AbstractContainerMenu)new WitheredPhlegmMenu(id, inventory, (Container)this, this.dataAccess);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void serverTick(Level level, BlockPos pos, BlockState state, WitheredPhlegmBlockEntity entity) {
/* 127 */     if (!((Boolean)state.getValue((Property)WitheredPhlegmBlock.POWERED)).booleanValue() && !entity.getItems().stream().allMatch(Predicate.not(ItemStack::isEmpty))) {
/*     */       
/* 129 */       AABB box = new AABB(pos, pos.offset(1, 1, 1));
/* 130 */       List<ItemEntity> items = level.getEntitiesOfClass(ItemEntity.class, box.inflate(8.0D));
/* 131 */       for (ItemEntity item : items) {
/*     */         
/* 133 */         Vec3 vec3 = Vec3.atCenterOf((Vec3i)pos);
/* 134 */         double d = item.position().distanceTo(vec3);
/* 135 */         Vec3 delta = vec3.subtract(item.position()).normalize().scale(Math.max(1.0D - d / 8.0D, 0.1D));
/* 136 */         delta = item.getDeltaMovement().multiply(0.8D, 1.0D, 0.8D).add(delta.multiply(1.0D, 0.2D, 1.0D));
/* 137 */         item.setDeltaMovement(delta);
/* 138 */         ((ServerChunkCache)item.getCommandSenderWorld().getChunkSource()).broadcast((Entity)item, (Packet)new ClientboundSetEntityMotionPacket((Entity)item));
/* 139 */         if (item.getBoundingBox().intersects(box.inflate(0.5D)))
/*     */         {
/* 141 */           if (HopperBlockEntity.addItem((Container)entity, item)) {
/* 142 */             level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundTag tag) {
/* 151 */     super.load(tag);
/* 152 */     this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
/* 153 */     if (!tryLoadLootTable(tag))
/* 154 */       ContainerHelper.loadAllItems(tag, this.items); 
/* 155 */     this.storedExperience = tag.getInt("StoredXp");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void saveAdditional(CompoundTag tag) {
/* 161 */     super.saveAdditional(tag);
/* 162 */     if (!trySaveLootTable(tag))
/* 163 */       ContainerHelper.saveAllItems(tag, this.items); 
/* 164 */     tag.putInt("StoredXp", this.storedExperience);
/*     */   }
/*     */ 
/*     */   
/*     */   private void playSound(SoundEvent event) {
/* 169 */     this.level.playSound(null, getBlockPos(), event, SoundSource.BLOCKS, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startOpen(Player player) {
/* 175 */     if (!this.remove && !player.isSpectator()) {
/* 176 */       this.openersCounter.incrementOpeners(player, getLevel(), getBlockPos(), getBlockState());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void stopOpen(Player player) {
/* 182 */     if (!this.remove && !player.isSpectator()) {
/* 183 */       this.openersCounter.decrementOpeners(player, getLevel(), getBlockPos(), getBlockState());
/*     */     }
/*     */   }
/*     */   
/*     */   public void recheckOpen() {
/* 188 */     if (!this.remove) {
/* 189 */       this.openersCounter.recheckOpeners(getLevel(), getBlockPos(), getBlockState());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet<ClientGamePacketListener> getUpdatePacket() {
/* 195 */     return (Packet<ClientGamePacketListener>)ClientboundBlockEntityDataPacket.create((BlockEntity)this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundTag getUpdateTag() {
/* 201 */     CompoundTag tag = new CompoundTag();
/* 202 */     ContainerHelper.saveAllItems(tag, this.items);
/* 203 */     return tag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleUpdateTag(CompoundTag tag) {
/* 209 */     ContainerHelper.loadAllItems(tag, this.items);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChanged() {
/* 215 */     super.setChanged();
/* 216 */     if (this.level != null) {
/* 217 */       this.level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
/*     */     }
/*     */   }
/*     */   
/*     */   public int getStoredXp() {
/* 222 */     return this.storedExperience;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\blockentity\WitheredPhlegmBlockEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
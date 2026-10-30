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
/*  40 */   private NonNullList<ItemStack> items = NonNullList.m_122780_(25, ItemStack.f_41583_); public static final int CONTAINER_SIZE = 25;
/*  41 */   private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter()
/*     */     {
/*     */       protected void m_142148_(Level level, BlockPos pos, BlockState state, int i, int i2) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       protected void m_142292_(Level level, BlockPos pos, BlockState state) {
/*  49 */         WitheredPhlegmBlockEntity.this.playSound((SoundEvent)WitherStormModSoundEvents.WITHERED_PHLEGM_BLOCK_OPEN.get());
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       protected void m_142289_(Level level, BlockPos pos, BlockState state) {
/*  55 */         WitheredPhlegmBlockEntity.this.playSound((SoundEvent)WitherStormModSoundEvents.WITHERED_PHLEGM_BLOCK_CLOSE.get());
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       protected boolean m_142718_(Player player) {
/*  61 */         AbstractContainerMenu abstractContainerMenu = player.f_36096_; if (abstractContainerMenu instanceof WitheredPhlegmMenu) { WitheredPhlegmMenu menu = (WitheredPhlegmMenu)abstractContainerMenu;
/*  62 */           return (menu.getContainer() == WitheredPhlegmBlockEntity.this); }
/*     */         
/*  64 */         return false;
/*     */       }
/*     */     };
/*  67 */   private final ContainerData dataAccess = new ContainerData()
/*     */     {
/*     */       
/*     */       public void m_8050_(int slot, int data)
/*     */       {
/*  72 */         if (slot == 0) {
/*  73 */           WitheredPhlegmBlockEntity.this.storedExperience = data;
/*     */         }
/*     */       }
/*     */ 
/*     */       
/*     */       public int m_6499_() {
/*  79 */         return 1;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public int m_6413_(int slot) {
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
/*     */   public int m_6643_() {
/*  98 */     return 25;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected NonNullList<ItemStack> m_7086_() {
/* 104 */     return this.items;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_6520_(NonNullList<ItemStack> items) {
/* 110 */     this.items = items;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Component m_6820_() {
/* 116 */     return (Component)Component.m_237115_("container.witherstormmod.phlegm_block");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractContainerMenu m_6555_(int id, Inventory inventory) {
/* 122 */     return (AbstractContainerMenu)new WitheredPhlegmMenu(id, inventory, (Container)this, this.dataAccess);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void serverTick(Level level, BlockPos pos, BlockState state, WitheredPhlegmBlockEntity entity) {
/* 127 */     if (!((Boolean)state.m_61143_((Property)WitheredPhlegmBlock.POWERED)).booleanValue() && !entity.m_7086_().stream().allMatch(Predicate.not(ItemStack::m_41619_))) {
/*     */       
/* 129 */       AABB box = new AABB(pos, pos.m_7918_(1, 1, 1));
/* 130 */       List<ItemEntity> items = level.m_45976_(ItemEntity.class, box.m_82400_(8.0D));
/* 131 */       for (ItemEntity item : items) {
/*     */         
/* 133 */         Vec3 vec3 = Vec3.m_82512_((Vec3i)pos);
/* 134 */         double d = item.m_20182_().m_82554_(vec3);
/* 135 */         Vec3 delta = vec3.m_82546_(item.m_20182_()).m_82541_().m_82490_(Math.max(1.0D - d / 8.0D, 0.1D));
/* 136 */         delta = item.m_20184_().m_82542_(0.8D, 1.0D, 0.8D).m_82549_(delta.m_82542_(1.0D, 0.2D, 1.0D));
/* 137 */         item.m_20256_(delta);
/* 138 */         ((ServerChunkCache)item.m_20193_().m_7726_()).m_8445_((Entity)item, (Packet)new ClientboundSetEntityMotionPacket((Entity)item));
/* 139 */         if (item.m_20191_().m_82381_(box.m_82400_(0.5D)))
/*     */         {
/* 141 */           if (HopperBlockEntity.m_59331_((Container)entity, item)) {
/* 142 */             level.m_5594_(null, pos, SoundEvents.f_12019_, SoundSource.BLOCKS, 1.0F, 1.0F);
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_142466_(CompoundTag tag) {
/* 151 */     super.m_142466_(tag);
/* 152 */     this.items = NonNullList.m_122780_(m_6643_(), ItemStack.f_41583_);
/* 153 */     if (!m_59631_(tag))
/* 154 */       ContainerHelper.m_18980_(tag, this.items); 
/* 155 */     this.storedExperience = tag.m_128451_("StoredXp");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void m_183515_(CompoundTag tag) {
/* 161 */     super.m_183515_(tag);
/* 162 */     if (!m_59634_(tag))
/* 163 */       ContainerHelper.m_18973_(tag, this.items); 
/* 164 */     tag.m_128405_("StoredXp", this.storedExperience);
/*     */   }
/*     */ 
/*     */   
/*     */   private void playSound(SoundEvent event) {
/* 169 */     this.f_58857_.m_5594_(null, m_58899_(), event, SoundSource.BLOCKS, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_5856_(Player player) {
/* 175 */     if (!this.f_58859_ && !player.m_5833_()) {
/* 176 */       this.openersCounter.m_155452_(player, m_58904_(), m_58899_(), m_58900_());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_5785_(Player player) {
/* 182 */     if (!this.f_58859_ && !player.m_5833_()) {
/* 183 */       this.openersCounter.m_155468_(player, m_58904_(), m_58899_(), m_58900_());
/*     */     }
/*     */   }
/*     */   
/*     */   public void recheckOpen() {
/* 188 */     if (!this.f_58859_) {
/* 189 */       this.openersCounter.m_155476_(m_58904_(), m_58899_(), m_58900_());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet<ClientGamePacketListener> m_58483_() {
/* 195 */     return (Packet<ClientGamePacketListener>)ClientboundBlockEntityDataPacket.m_195640_((BlockEntity)this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundTag m_5995_() {
/* 201 */     CompoundTag tag = new CompoundTag();
/* 202 */     ContainerHelper.m_18973_(tag, this.items);
/* 203 */     return tag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleUpdateTag(CompoundTag tag) {
/* 209 */     ContainerHelper.m_18980_(tag, this.items);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_6596_() {
/* 215 */     super.m_6596_();
/* 216 */     if (this.f_58857_ != null) {
/* 217 */       this.f_58857_.m_7260_(m_58899_(), m_58900_(), m_58900_(), 3);
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
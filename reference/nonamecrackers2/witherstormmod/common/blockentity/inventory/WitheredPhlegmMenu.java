/*     */ package nonamecrackers2.witherstormmod.common.blockentity.inventory;
/*     */ 
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.Container;
/*     */ import net.minecraft.world.SimpleContainer;
/*     */ import net.minecraft.world.entity.player.Inventory;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*     */ import net.minecraft.world.inventory.ContainerData;
/*     */ import net.minecraft.world.inventory.MenuType;
/*     */ import net.minecraft.world.inventory.SimpleContainerData;
/*     */ import net.minecraft.world.inventory.Slot;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMenuTypes;
/*     */ 
/*     */ public class WitheredPhlegmMenu
/*     */   extends AbstractContainerMenu
/*     */ {
/*     */   private final Container container;
/*     */   private final ContainerData data;
/*     */   
/*     */   public WitheredPhlegmMenu(int id, Inventory inventory) {
/*  23 */     this(id, inventory, (Container)new SimpleContainer(25), (ContainerData)new SimpleContainerData(1));
/*     */   }
/*     */ 
/*     */   
/*     */   public Container getContainer() {
/*  28 */     return this.container;
/*     */   }
/*     */ 
/*     */   
/*     */   public WitheredPhlegmMenu(int id, Inventory playerInventory, Container container, ContainerData data) {
/*  33 */     super((MenuType)WitherStormModMenuTypes.WITHERED_PHLEGM.get(), id);
/*  34 */     this.container = container;
/*  35 */     this.data = data;
/*  36 */     container.m_5856_(playerInventory.f_35978_);
/*  37 */     int rowsColumns = Mth.m_14165_(Math.sqrt(container.m_6643_()));
/*  38 */     int totalSlots = 0;
/*     */     int y;
/*  40 */     label33: for (y = 0; y < rowsColumns; y++) {
/*     */       
/*  42 */       for (int x = 0; x < rowsColumns; x++) {
/*     */         
/*  44 */         if (totalSlots >= container.m_6643_())
/*     */           break label33; 
/*  46 */         m_38897_(new Slot(container, totalSlots, 44 + x * 18, 18 + y * 18));
/*  47 */         totalSlots++;
/*     */       } 
/*     */     } 
/*     */     
/*  51 */     int yOffset = 22;
/*     */     
/*  53 */     for (int l = 0; l < 3; l++) {
/*     */       
/*  55 */       for (int j1 = 0; j1 < 9; j1++) {
/*  56 */         m_38897_(new Slot((Container)playerInventory, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + yOffset));
/*     */       }
/*     */     } 
/*  59 */     for (int i1 = 0; i1 < 9; i1++) {
/*  60 */       m_38897_(new Slot((Container)playerInventory, i1, 8 + i1 * 18, 161 + yOffset));
/*     */     }
/*  62 */     m_38884_(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6875_(Player player) {
/*  68 */     return this.container.m_6542_(player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_6877_(Player player) {
/*  74 */     super.m_6877_(player);
/*  75 */     this.container.m_5785_(player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack m_7648_(Player player, int slotId) {
/*  81 */     ItemStack stack = ItemStack.f_41583_;
/*  82 */     Slot slot = (Slot)this.f_38839_.get(slotId);
/*  83 */     if (slot != null && slot.m_6657_()) {
/*     */       
/*  85 */       ItemStack inSlot = slot.m_7993_();
/*  86 */       stack = inSlot.m_41777_();
/*  87 */       if (slotId < this.container.m_6643_()) {
/*     */         
/*  89 */         if (!m_38903_(inSlot, this.container.m_6643_(), this.f_38839_.size(), true)) {
/*  90 */           return ItemStack.f_41583_;
/*     */         }
/*  92 */       } else if (!m_38903_(inSlot, 0, this.container.m_6643_(), false)) {
/*     */         
/*  94 */         return ItemStack.f_41583_;
/*     */       } 
/*     */       
/*  97 */       if (inSlot.m_41619_()) {
/*  98 */         slot.m_269060_(ItemStack.f_41583_);
/*     */       } else {
/* 100 */         slot.m_6654_();
/*     */       } 
/* 102 */     }  return stack;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getXp() {
/* 107 */     return this.data.m_6413_(0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\blockentity\inventory\WitheredPhlegmMenu.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
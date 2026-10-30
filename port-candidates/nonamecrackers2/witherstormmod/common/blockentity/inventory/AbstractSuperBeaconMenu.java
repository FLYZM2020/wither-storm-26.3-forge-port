/*     */ package nonamecrackers2.witherstormmod.common.blockentity.inventory;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.util.Set;
/*     */ import java.util.function.Consumer;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.world.Container;
/*     */ import net.minecraft.world.effect.MobEffect;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.inventory.AbstractContainerMenu;
/*     */ import net.minecraft.world.inventory.ContainerData;
/*     */ import net.minecraft.world.inventory.ContainerLevelAccess;
/*     */ import net.minecraft.world.inventory.MenuType;
/*     */ import net.minecraft.world.inventory.SimpleContainerData;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractSuperBeaconMenu
/*     */   extends AbstractContainerMenu
/*     */ {
/*     */   protected final ContainerLevelAccess access;
/*     */   private final ContainerData data;
/*     */   @Nullable
/*     */   private final Consumer<ServerPlayer> powerUp;
/*     */   private final Set<MobEffect> validEffects;
/*     */   
/*     */   public AbstractSuperBeaconMenu(MenuType<? extends AbstractSuperBeaconMenu> type, int id, Container container) {
/*  31 */     this(type, id, container, (ContainerData)new SimpleContainerData(4), ContainerLevelAccess.NULL, (Consumer<ServerPlayer>)null, (Set<MobEffect>)ImmutableSet.of());
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractSuperBeaconMenu(MenuType<? extends AbstractSuperBeaconMenu> type, int id, Container container, ContainerData data, ContainerLevelAccess access, @Nullable Consumer<ServerPlayer> powerUp, Set<MobEffect> validEffects) {
/*  36 */     super(type, id);
/*  37 */     this.access = access;
/*  38 */     this.data = data;
/*  39 */     addDataSlots(data);
/*  40 */     this.powerUp = powerUp;
/*  41 */     this.validEffects = validEffects;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setData(int slot, int value) {
/*  47 */     super.setData(slot, value);
/*  48 */     broadcastChanges();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLevel() {
/*  53 */     return this.data.get(0);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public MobEffect getPrimaryEffect() {
/*  58 */     return MobEffect.byId(this.data.get(1));
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateEffects(int effectId) {
/*  63 */     this.data.set(1, effectId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void activateCooldown(int time) {
/*  68 */     this.data.set(3, time);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCooldown() {
/*  73 */     return this.data.get(3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShowArea(boolean flag) {
/*  78 */     this.data.set(2, flag ? 1 : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldShowArea() {
/*  83 */     return (this.data.get(2) == 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doPowerUp(ServerPlayer player) {
/*  88 */     if (this.powerUp != null) {
/*  89 */       this.powerUp.accept(player);
/*     */     }
/*     */   }
/*     */   
/*     */   public Set<MobEffect> getValidEffects() {
/*  94 */     return this.validEffects;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack quickMoveStack(Player player, int slot) {
/* 100 */     return ItemStack.EMPTY;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\blockentity\inventory\AbstractSuperBeaconMenu.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
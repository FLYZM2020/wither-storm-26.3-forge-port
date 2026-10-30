/*    */ package nonamecrackers2.witherstormmod.common.blockentity.inventory;
/*    */ 
/*    */ import java.util.Set;
/*    */ import java.util.function.Consumer;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.Container;
/*    */ import net.minecraft.world.effect.MobEffect;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.inventory.ContainerData;
/*    */ import net.minecraft.world.inventory.ContainerLevelAccess;
/*    */ import net.minecraft.world.inventory.MenuType;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModMenuTypes;
/*    */ 
/*    */ public class SuperSupportBeaconMenu
/*    */   extends AbstractSuperBeaconMenu
/*    */ {
/*    */   public SuperSupportBeaconMenu(int id, Container container) {
/* 21 */     super((MenuType<? extends AbstractSuperBeaconMenu>)WitherStormModMenuTypes.SUPER_SUPPORT_BEACON.get(), id, container);
/*    */   }
/*    */ 
/*    */   
/*    */   public SuperSupportBeaconMenu(int id, Container container, ContainerData data, ContainerLevelAccess access, @Nullable Consumer<ServerPlayer> powerUp, Set<MobEffect> validEffects) {
/* 26 */     super((MenuType<? extends AbstractSuperBeaconMenu>)WitherStormModMenuTypes.SUPER_SUPPORT_BEACON.get(), id, container, data, access, powerUp, validEffects);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean m_6875_(Player player) {
/* 32 */     return m_38889_(this.access, player, (Block)WitherStormModBlocks.SUPER_SUPPORT_BEACON.get());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\blockentity\inventory\SuperSupportBeaconMenu.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
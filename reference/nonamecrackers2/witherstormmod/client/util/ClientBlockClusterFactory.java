/*    */ package nonamecrackers2.witherstormmod.client.util;
/*    */ 
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraftforge.network.PlayMessages;
/*    */ import nonamecrackers2.witherstormmod.client.entity.ClientBlockClusterEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*    */ 
/*    */ public class ClientBlockClusterFactory
/*    */ {
/*    */   public static ClientBlockClusterEntity make(PlayMessages.SpawnEntity packet, Level level) {
/* 12 */     return new ClientBlockClusterEntity((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get(), level);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\clien\\util\ClientBlockClusterFactory.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
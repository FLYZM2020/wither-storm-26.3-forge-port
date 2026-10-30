/*    */ package nonamecrackers2.witherstormmod.client.util;
/*    */ 
/*    */ import net.minecraft.core.BlockPos;
/*    */ 
/*    */ public class SuperBeaconDistantInstance
/*    */ {
/*    */   public final BlockPos pos;
/*    */   public int[] color;
/*    */   public boolean active;
/*    */   public int beaconHeight;
/* 11 */   public float beamWidth = 0.25F;
/* 12 */   public float outerBeamWidth = 0.45F;
/*    */ 
/*    */   
/*    */   public SuperBeaconDistantInstance(BlockPos pos, int[] color) {
/* 16 */     this.pos = pos;
/* 17 */     this.color = color;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\clien\\util\SuperBeaconDistantInstance.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
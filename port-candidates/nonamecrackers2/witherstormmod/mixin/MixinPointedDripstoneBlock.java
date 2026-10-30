/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.world.level.block.PointedDripstoneBlock;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.gen.Invoker;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({PointedDripstoneBlock.class})
/*    */ public interface MixinPointedDripstoneBlock
/*    */ {
/*    */   @Invoker
/*    */   static boolean callIsStalagmite(BlockState state) {
/* 17 */     throw new AssertionError();
/*    */   }
/*    */ 
/*    */   
/*    */   @Invoker
/*    */   static void callSpawnFallingStalactite(BlockState state, ServerLevel level, BlockPos pos) {
/* 23 */     throw new AssertionError();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinPointedDripstoneBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
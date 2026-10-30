/*    */ package nonamecrackers2.witherstormmod.client.event;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.util.Mth;
/*    */ import net.minecraft.world.level.BlockAndTintGetter;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraftforge.client.event.RegisterColorHandlersEvent;
/*    */ import nonamecrackers2.witherstormmod.common.block.WireBlock;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*    */ import org.joml.Vector3f;
/*    */ 
/*    */ public class WitherStormModRegisterBlockColors {
/*    */   public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
/* 14 */     event.register((state, world, pos, i) -> { Vector3f color = ((WireBlock)WitherStormModBlocks.TAINTED_DUST.get()).getColor(); return Mth.m_14159_(color.x(), color.y(), color.z()); }new Block[] { (Block)WitherStormModBlocks.TAINTED_DUST
/*    */ 
/*    */           
/* 17 */           .get() });
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\event\WitherStormModRegisterBlockColors.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
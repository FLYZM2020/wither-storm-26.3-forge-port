/*    */ package nonamecrackers2.witherstormmod.common.serializer;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.network.syncher.EntityDataSerializer;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class null
/*    */   implements EntityDataSerializer<Map<BlockPos, BlockState>>
/*    */ {
/*    */   public void write(FriendlyByteBuf buffer, Map<BlockPos, BlockState> map) {
/* 35 */     buffer.m_236831_(map, (buf, pos) -> buf.m_130064_(pos), (buf, state) -> buf.m_130130_(Block.m_49956_(state)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<BlockPos, BlockState> read(FriendlyByteBuf buffer) {
/* 41 */     return buffer.m_236847_(buf -> buf.m_130135_(), buf -> Block.m_49803_(buf.m_130242_()));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<BlockPos, BlockState> copy(Map<BlockPos, BlockState> map) {
/* 47 */     return new HashMap<>(map);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\serializer\WitherStormModDataSerializers$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
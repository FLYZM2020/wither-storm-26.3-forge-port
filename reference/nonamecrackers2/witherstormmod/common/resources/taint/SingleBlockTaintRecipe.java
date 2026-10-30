/*    */ package nonamecrackers2.witherstormmod.common.resources.taint;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.List;
/*    */ import net.minecraft.world.effect.MobEffect;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.level.block.state.properties.Property;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SingleBlockTaintRecipe
/*    */   extends TaintRecipe
/*    */ {
/*    */   private final Block block;
/*    */   
/*    */   public SingleBlockTaintRecipe(Block block, MobEffect effect, BlockState replacement, List<Property<?>> propertiesToCopy) {
/* 19 */     super(effect, replacement, propertiesToCopy);
/* 20 */     this.block = block;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canConvertBlock(BlockState state) {
/* 26 */     return state.m_60713_(this.block);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 32 */     return ForgeRegistries.BLOCKS.getKey(this.block).m_135815_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serializeFrom(JsonObject object) {
/* 38 */     object.addProperty("block", ForgeRegistries.BLOCKS.getKey(this.block).toString());
/*    */   }
/*    */ 
/*    */   
/*    */   public Block getBlock() {
/* 43 */     return this.block;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int compareTo(TaintRecipe o) {
/* 49 */     return (o instanceof TagBasedTaintRecipe) ? 1 : 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\resources\taint\SingleBlockTaintRecipe.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package nonamecrackers2.witherstormmod.common.resources.taint;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.List;
/*    */ import net.minecraft.tags.TagKey;
/*    */ import net.minecraft.world.effect.MobEffect;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.level.block.state.properties.Property;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TagBasedTaintRecipe
/*    */   extends TaintRecipe
/*    */ {
/*    */   private final TagKey<Block> tag;
/*    */   
/*    */   public TagBasedTaintRecipe(TagKey<Block> tag, MobEffect effect, BlockState replacement, List<Property<?>> propertiesToCopy) {
/* 19 */     super(effect, replacement, propertiesToCopy);
/* 20 */     this.tag = tag;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canConvertBlock(BlockState state) {
/* 26 */     return state.m_204336_(this.tag);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 32 */     return this.tag.f_203868_().m_135815_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serializeFrom(JsonObject object) {
/* 38 */     object.addProperty("block", "#" + this.tag.f_203868_().toString());
/*    */   }
/*    */ 
/*    */   
/*    */   public TagKey<Block> getTag() {
/* 43 */     return this.tag;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int compareTo(TaintRecipe o) {
/* 49 */     return (o instanceof SingleBlockTaintRecipe) ? -1 : 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\resources\taint\TagBasedTaintRecipe.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
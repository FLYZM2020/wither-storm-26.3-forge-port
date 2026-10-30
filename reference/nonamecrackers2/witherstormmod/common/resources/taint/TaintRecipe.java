/*    */ package nonamecrackers2.witherstormmod.common.resources.taint;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.world.effect.MobEffect;
/*    */ import net.minecraft.world.effect.MobEffectInstance;
/*    */ import net.minecraft.world.item.alchemy.Potion;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.level.block.state.properties.Property;
/*    */ 
/*    */ 
/*    */ public abstract class TaintRecipe
/*    */   implements Comparable<TaintRecipe>
/*    */ {
/*    */   @Nullable
/*    */   protected final MobEffect effect;
/*    */   protected final BlockState replacement;
/*    */   protected final List<Property<?>> propertiesToCopy;
/*    */   
/*    */   public TaintRecipe(@Nullable MobEffect effect, BlockState replacement, List<Property<?>> propertiesToCopy) {
/* 22 */     this.effect = effect;
/* 23 */     this.replacement = replacement;
/* 24 */     this.propertiesToCopy = propertiesToCopy;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public MobEffect effect() {
/* 29 */     return this.effect;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockState replacement() {
/* 34 */     return this.replacement;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Property<?>> propertiesToCopy() {
/* 39 */     return this.propertiesToCopy;
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract boolean canConvertBlock(BlockState paramBlockState);
/*    */   
/*    */   public abstract void serializeFrom(JsonObject paramJsonObject);
/*    */   
/*    */   public abstract String getName();
/*    */   
/*    */   public boolean canConvertWithPotion(Potion potion) {
/* 50 */     if (this.effect == null) {
/* 51 */       return false;
/*    */     }
/* 53 */     return potion.m_43488_().stream().anyMatch(e -> (e.m_19544_() == this.effect));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\resources\taint\TaintRecipe.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
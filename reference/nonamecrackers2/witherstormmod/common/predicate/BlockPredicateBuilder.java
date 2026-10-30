/*    */ package nonamecrackers2.witherstormmod.common.predicate;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import net.minecraft.tags.TagKey;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ 
/*    */ 
/*    */ public class BlockPredicateBuilder
/*    */   extends PredicateBuilder<BlockState, BlockPredicateBuilder>
/*    */ {
/*    */   private BlockPredicateBuilder(PredicateBuilder.Comparison comparison) {
/* 13 */     super(comparison);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static BlockPredicateBuilder or() {
/* 24 */     return new BlockPredicateBuilder(PredicateBuilder.Comparison.OR);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static BlockPredicateBuilder and() {
/* 35 */     return new BlockPredicateBuilder(PredicateBuilder.Comparison.AND);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPredicateBuilder isExactly(Block block) {
/* 40 */     return addTest(b -> b.m_60713_(block));
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPredicateBuilder isNotExactly(Block block) {
/* 45 */     return addTest(b -> !b.m_60713_(block));
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPredicateBuilder isExactly(BlockState state) {
/* 50 */     return addTest(b -> Objects.equals(state, b));
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPredicateBuilder isNotExactly(BlockState state) {
/* 55 */     return addTest(b -> !Objects.equals(state, b));
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPredicateBuilder isTag(TagKey<Block> tag) {
/* 60 */     return addTest(b -> b.m_204336_(tag));
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPredicateBuilder isNotTag(TagKey<Block> tag) {
/* 65 */     return addTest(b -> !b.m_204336_(tag));
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPredicateBuilder isAir() {
/* 70 */     return addTest(b -> b.m_60795_());
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPredicateBuilder isNotAir() {
/* 75 */     return addTest(b -> !b.m_60795_());
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPredicateBuilder isNotAFluid() {
/* 80 */     return addTest(b -> b.m_60819_().m_76178_());
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPredicateBuilder isAFluid() {
/* 85 */     return addTest(b -> !b.m_60819_().m_76178_());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\predicate\BlockPredicateBuilder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
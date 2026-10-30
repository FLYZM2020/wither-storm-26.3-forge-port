/*    */ package nonamecrackers2.witherstormmod.common.util;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.util.List;
/*    */ import net.minecraft.Util;
/*    */ 
/*    */ public class StormHeadOffsets {
/*    */   public static final List<HeadConfiguration> MAIN;
/*    */   
/*    */   static {
/* 11 */     MAIN = (List<HeadConfiguration>)Util.m_137537_(() -> {
/*    */           ImmutableList.Builder<HeadConfiguration> builder = ImmutableList.builder();
/*    */           builder.add(HeadConfiguration.forPhase(0).addOffset(0, 0.0D, 3.0D, 0.0D).addOffset(1, -1.3D, 2.2D, 0.0D).addOffset(2, 1.3D, 2.2D, 0.0D).build());
/*    */           builder.add(HeadConfiguration.forPhase(1).addOffset(0, 0.0D, 3.0D, 0.0D).addOffset(1, -1.3D, 2.2D, 0.0D).addOffset(2, 1.3D, 2.2D, 0.0D).build());
/*    */           builder.add(HeadConfiguration.forPhase(2).addOffset(0, 0.0D, 2.75D, 0.5D).addOffset(1, -1.3D, 2.2D, 0.0D).addOffset(2, 1.3D, 2.2D, 0.0D).build());
/*    */           builder.add(HeadConfiguration.forPhase(3).addOffset(0, 0.0D, 2.75D, 0.5D).addOffset(1, -1.3D, 2.2D, 0.0D).addOffset(2, 1.3D, 2.2D, 0.0D).build());
/*    */           builder.addAll(HeadConfiguration.makeSameFor((), new int[] { 4, 5, 6, 7 }));
/*    */           return builder.build();
/*    */         });
/*    */   }
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
/* 42 */   public static final List<HeadConfiguration> SEGMENT = (List<HeadConfiguration>)Util.m_137537_(() -> HeadConfiguration.makeSameFor((), new int[] { 0, 1, 2, 3, 4, 5, 6, 7 }));
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\StormHeadOffsets.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
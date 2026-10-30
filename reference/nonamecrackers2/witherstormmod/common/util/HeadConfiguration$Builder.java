/*    */ package nonamecrackers2.witherstormmod.common.util;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
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
/*    */ public class Builder
/*    */ {
/*    */   private final Predicate<WitherStormEntity> predicate;
/* 46 */   private final Int2ObjectMap<Vec3> offsetsByHead = (Int2ObjectMap<Vec3>)new Int2ObjectOpenHashMap();
/*    */ 
/*    */   
/*    */   private Builder(Predicate<WitherStormEntity> predicate) {
/* 50 */     this.predicate = predicate;
/*    */   }
/*    */ 
/*    */   
/*    */   public Builder addOffset(int head, double x, double y, double z) {
/* 55 */     this.offsetsByHead.put(head, new Vec3(x, y, z));
/* 56 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public HeadConfiguration build() {
/* 61 */     return new HeadConfiguration(this.predicate, this.offsetsByHead);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\HeadConfiguration$Builder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package nonamecrackers2.witherstormmod.common.entity.section;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.function.Consumer;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ 
/*    */ 
/*    */ public class CollisionActionSection
/*    */   extends Section
/*    */ {
/*    */   private final Consumer<Entity> action;
/*    */   
/*    */   public CollisionActionSection(WitherStormEntity entity, float height, float width, double x, double y, double z, int phase, Consumer<Entity> action) {
/* 16 */     super(entity, height, width, x, y, z, phase);
/* 17 */     this.action = action;
/*    */   }
/*    */ 
/*    */   
/*    */   public CollisionActionSection(WitherStormEntity entity, float height, float width, double x, double y, double z, Predicate<WitherStormEntity> isActive, Consumer<Entity> action) {
/* 22 */     super(entity, height, width, x, y, z, isActive);
/* 23 */     this.action = action;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void collideWithNearbyEntities() {
/* 29 */     super.collideWithNearbyEntities();
/* 30 */     List<Entity> list = this.owner.m_9236_().m_45976_(Entity.class, this.boundingBox);
/* 31 */     for (Entity entity : list)
/* 32 */       this.action.accept(entity); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\section\CollisionActionSection.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
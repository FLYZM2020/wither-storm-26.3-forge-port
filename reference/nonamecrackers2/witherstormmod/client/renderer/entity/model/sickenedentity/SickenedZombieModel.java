/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.sickenedentity;
/*    */ 
/*    */ import net.minecraft.client.model.AbstractZombieModel;
/*    */ import net.minecraft.client.model.geom.ModelPart;
/*    */ import net.minecraft.world.entity.monster.Monster;
/*    */ import nonamecrackers2.witherstormmod.common.entity.SickenedZombie;
/*    */ 
/*    */ @Deprecated
/*    */ public class SickenedZombieModel<T extends SickenedZombie>
/*    */   extends AbstractZombieModel<T> {
/*    */   public SickenedZombieModel(ModelPart part) {
/* 12 */     super(part);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAggressive(T entity) {
/* 18 */     return entity.m_5912_();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\sickenedentity\SickenedZombieModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
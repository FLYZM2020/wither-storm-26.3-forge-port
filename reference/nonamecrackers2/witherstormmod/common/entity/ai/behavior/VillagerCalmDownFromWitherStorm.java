/*    */ package nonamecrackers2.witherstormmod.common.entity.ai.behavior;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.ai.behavior.Behavior;
/*    */ import net.minecraft.world.entity.ai.memory.MemoryModuleType;
/*    */ import net.minecraft.world.entity.npc.Villager;
/*    */ 
/*    */ public class VillagerCalmDownFromWitherStorm
/*    */   extends Behavior<Villager> {
/*    */   public VillagerCalmDownFromWitherStorm() {
/* 14 */     super((Map)ImmutableMap.of());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void start(ServerLevel level, Villager villager, long seed) {
/* 20 */     if (!WitherStormPanicTrigger.isNearWitherStorm((LivingEntity)villager)) {
/*    */       
/* 22 */       villager.m_6274_().m_21936_(MemoryModuleType.f_26381_);
/* 23 */       villager.m_6274_().m_21936_(MemoryModuleType.f_26382_);
/* 24 */       villager.m_6274_().m_21862_(level.m_46468_(), level.m_46467_());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\behavior\VillagerCalmDownFromWitherStorm.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
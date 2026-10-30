/*    */ package nonamecrackers2.witherstormmod.common.event;
/*    */ 
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.RegisterWorldInteractionsEvent;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.clustersource.BlockClusterSource;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.pullbehavior.WitherStormPullBehavior;
/*    */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.clustersource.DefaultClusterSource;
/*    */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.clustersource.HunchbackClusterSource;
/*    */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.clustersource.NatureClusterSource;
/*    */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.clustersource.SmallClusterSource;
/*    */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.pullbehavior.BlockClusterPullBehavior;
/*    */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.pullbehavior.ItemPullBehavior;
/*    */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.pullbehavior.SlimePullBehavior;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*    */ 
/*    */ public class WitherStormModClusterInteractionEvents {
/*    */   public static void registerClusterInteractions(RegisterWorldInteractionsEvent event) {
/* 18 */     event.registerPullBehavior(EntityType.f_20526_, (WitherStormPullBehavior)new SlimePullBehavior());
/* 19 */     event.registerPullBehavior(EntityType.f_20461_, (WitherStormPullBehavior)new ItemPullBehavior());
/* 20 */     event.registerPullBehavior((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get(), (WitherStormPullBehavior)new BlockClusterPullBehavior());
/* 21 */     event.registerBlockClusterSource((BlockClusterSource)new DefaultClusterSource());
/* 22 */     event.registerBlockClusterSource((BlockClusterSource)new HunchbackClusterSource());
/* 23 */     event.registerBlockClusterSource((BlockClusterSource)new SmallClusterSource());
/* 24 */     event.registerBlockClusterSource((BlockClusterSource)new NatureClusterSource());
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\event\WitherStormModClusterInteractionEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
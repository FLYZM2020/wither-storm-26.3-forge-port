/*    */ package nonamecrackers2.witherstormmod.api.common.ai.witherstorm;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import net.minecraftforge.fml.event.IModBusEvent;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.clustersource.BlockClusterSource;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.pullbehavior.WitherStormPullBehavior;
/*    */ 
/*    */ public class RegisterWorldInteractionsEvent
/*    */   extends Event
/*    */   implements IModBusEvent
/*    */ {
/* 17 */   protected final Map<EntityType<?>, WitherStormPullBehavior<?>> pullBehaviors = Maps.newHashMap();
/* 18 */   protected final List<BlockClusterSource> sources = Lists.newArrayList();
/*    */ 
/*    */   
/*    */   public void registerPullBehavior(EntityType<?> type, WitherStormPullBehavior<?> behavior) {
/* 22 */     if (this.pullBehaviors.containsKey(type))
/* 23 */       throw new IllegalArgumentException("Type '" + type + "' is already registered"); 
/* 24 */     this.pullBehaviors.put(type, behavior);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerBlockClusterSource(BlockClusterSource source) {
/* 29 */     this.sources.add(source);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\api\common\ai\witherstorm\RegisterWorldInteractionsEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
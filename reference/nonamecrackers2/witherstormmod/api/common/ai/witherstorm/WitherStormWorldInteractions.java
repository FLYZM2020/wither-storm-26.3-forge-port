/*    */ package nonamecrackers2.witherstormmod.api.common.ai.witherstorm;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraftforge.fml.ModContainer;
/*    */ import net.minecraftforge.fml.ModLoader;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.clustersource.BlockClusterSource;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.pullbehavior.WitherStormPullBehavior;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WitherStormWorldInteractions
/*    */ {
/*    */   @Nullable
/*    */   private static WitherStormWorldInteractions instance;
/*    */   private final Map<EntityType<?>, WitherStormPullBehavior<?>> pullBehaviors;
/*    */   private final List<BlockClusterSource> sources;
/*    */   
/*    */   private WitherStormWorldInteractions(Map<EntityType<?>, WitherStormPullBehavior<?>> pullBehaviors, List<BlockClusterSource> sources) {
/* 28 */     this.pullBehaviors = pullBehaviors;
/* 29 */     this.sources = sources;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <T extends net.minecraft.world.entity.Entity> WitherStormPullBehavior<T> getPullBehavior(EntityType<?> type) {
/* 35 */     return Objects.<WitherStormPullBehavior<T>>requireNonNull((WitherStormPullBehavior<T>)this.pullBehaviors.get(type), "Pull behavior does not exist for entity!");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasPullBehavior(EntityType<?> type) {
/* 40 */     return this.pullBehaviors.containsKey(type);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<BlockClusterSource> getClusterSources() {
/* 45 */     return this.sources;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void initialize() {
/* 50 */     if (instance != null)
/* 51 */       throw new IllegalStateException("Cluster interactions have already been initialized!"); 
/* 52 */     List<RegisterWorldInteractionsEvent> postedEvents = Lists.newArrayList();
/* 53 */     ImmutableMap.Builder<EntityType<?>, WitherStormPullBehavior<?>> pullBehaviors = ImmutableMap.builder();
/* 54 */     ImmutableList.Builder<BlockClusterSource> sources = ImmutableList.builder();
/* 55 */     ModLoader.get().runEventGenerator(mod -> {
/*    */           RegisterWorldInteractionsEvent event = new RegisterWorldInteractionsEvent();
/*    */           postedEvents.add(event);
/*    */           return event;
/*    */         });
/* 60 */     for (RegisterWorldInteractionsEvent event : postedEvents) {
/*    */       
/* 62 */       pullBehaviors.putAll(event.pullBehaviors);
/* 63 */       sources.addAll(event.sources);
/*    */     } 
/* 65 */     instance = new WitherStormWorldInteractions((Map<EntityType<?>, WitherStormPullBehavior<?>>)pullBehaviors.buildKeepingLast(), (List<BlockClusterSource>)sources.build());
/*    */   }
/*    */ 
/*    */   
/*    */   public static WitherStormWorldInteractions getInstance() {
/* 70 */     return Objects.<WitherStormWorldInteractions>requireNonNull(instance, "Cluster interactions have not been initialized yet");
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\api\common\ai\witherstorm\WitherStormWorldInteractions.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
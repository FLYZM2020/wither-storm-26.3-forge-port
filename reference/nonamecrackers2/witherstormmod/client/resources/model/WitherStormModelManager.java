/*    */ package nonamecrackers2.witherstormmod.client.resources.model;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import com.google.gson.JsonElement;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.model.geom.ModelLayerLocation;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.server.packs.resources.ResourceManager;
/*    */ import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
/*    */ import net.minecraft.util.profiling.ProfilerFiller;
/*    */ import net.minecraftforge.client.model.obj.ObjModel;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WitherStormModelManager
/*    */   extends SimpleJsonResourceReloadListener
/*    */ {
/* 22 */   private static final Gson GSON = (new GsonBuilder()).create();
/* 23 */   public static final WitherStormModelManager INSTANCE = new WitherStormModelManager();
/*    */   
/* 25 */   private Map<ModelLayerLocation, ObjModel> models = Maps.newHashMap();
/*    */ 
/*    */   
/*    */   public WitherStormModelManager() {
/* 29 */     super(GSON, "models/wither_storm_mass");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void apply(Map<ResourceLocation, JsonElement> files, ResourceManager manager, ProfilerFiller profiler) {
/* 35 */     this.models.clear();
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
/*    */   public boolean hasObjModel(ModelLayerLocation location) {
/* 48 */     return this.models.containsKey(location);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public ObjModel getModel(ModelLayerLocation location) {
/* 53 */     return this.models.get(location);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<ModelLayerLocation, ObjModel> getModels() {
/* 58 */     return (Map<ModelLayerLocation, ObjModel>)ImmutableMap.copyOf(this.models);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\resources\model\WitherStormModelManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
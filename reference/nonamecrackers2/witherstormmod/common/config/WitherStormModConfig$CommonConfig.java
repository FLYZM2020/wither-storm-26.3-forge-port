/*     */ package nonamecrackers2.witherstormmod.common.config;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraftforge.common.ForgeConfigSpec;
/*     */ import nonamecrackers2.crackerslib.common.config.ConfigHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommonConfig
/*     */   extends ConfigHelper
/*     */ {
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> blockClustersDropItems;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> shouldPickUpVehicles;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> phantomsOrbitWitherStorm;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> playerCannotDismountTentacles;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> injectCustomAiBehavior;
/*     */   public final ForgeConfigSpec.ConfigValue<List<? extends String>> injectAiMobBlacklist;
/*     */   public final ForgeConfigSpec.ConfigValue<Boolean> autoSpawnWitherStorm;
/*     */   public final ForgeConfigSpec.ConfigValue<Integer> autoSpawnTime;
/*     */   
/*     */   private CommonConfig(ForgeConfigSpec.Builder builder) {
/* 294 */     super(builder, "witherstormmod");
/*     */     
/* 296 */     builder.comment("Common options").push("common");
/*     */ 
/*     */ 
/*     */     
/* 300 */     this.blockClustersDropItems = createValue(Boolean.valueOf(false), "blockClustersDropItems", true, "Toggle to enable/disable drops from Block Clusters. NOTE: Enabling this feature can cause world lag");
/*     */     
/* 302 */     this.shouldPickUpVehicles = createValue(Boolean.valueOf(true), "shouldPickUpVehicles", false, "Specifies if the current entity the Wither Storm is picking up has a vehicle, that it should pick it up as well. Ex: Should pick up a player riding a boat");
/*     */     
/* 304 */     this.phantomsOrbitWitherStorm = createValue(Boolean.valueOf(true), "phantomsOrbitWitherStorm", true, "Specifies if phantoms AI should be overriden to allow circling above any nearby Wither Storms. Disable if you wish for default behaviour and/or if issues arise");
/*     */     
/* 306 */     this.playerCannotDismountTentacles = createValue(Boolean.valueOf(true), "playerCannotDismountTentacles", false, "Specifies if players should not be able to dismount a tentacle entity. Disable if facing compatibility issues");
/*     */     
/* 308 */     this.injectCustomAiBehavior = createValue(Boolean.valueOf(true), "injectCustomAiBehavior", true, "Specifies if custom AI behavior should be injected into certain entities (such as mobs running away from the Wither Storm). Disable if facing compatibility issues");
/*     */     
/* 310 */     this.injectAiMobBlacklist = createListValue(String.class, () -> { List<String> list = Lists.newArrayList(); list.add("witherstormmod:example"); return list; }val -> ResourceLocation.m_135830_(val), "injectAiMobBlacklist", true, "A list of mobs that should not have custom AI injected into them");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 318 */     this.autoSpawnWitherStorm = createValue(Boolean.valueOf(false), "autoSpawnWitherStorm", false, "Specifies if the Wither Storm should automatically be spawned upon world creation");
/*     */     
/* 320 */     this.autoSpawnTime = createRangedIntValue(1, 0, 120, "autoSpawnTime", false, "Specifies the amount of time (in minutes) required for the Wither Storm to automatically spawn, if enabled");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 325 */     builder.pop();
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\config\WitherStormModConfig$CommonConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
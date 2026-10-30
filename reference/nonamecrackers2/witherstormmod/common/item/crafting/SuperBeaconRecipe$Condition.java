/*     */ package nonamecrackers2.witherstormmod.common.item.crafting;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.util.GsonHelper;
/*     */ import net.minecraft.util.StringRepresentable;
/*     */ import nonamecrackers2.witherstormmod.common.blockentity.SuperBeaconBlockEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum Condition
/*     */   implements StringRepresentable
/*     */ {
/*     */   FULLY_COMLETED,
/*     */   FULL_SUPPORTS,
/*     */   MAIN_ACTIVATED,
/* 108 */   NONE("none", null, e -> true); static {
/* 109 */     MAIN_ACTIVATED = new Condition("MAIN_ACTIVATED", 1, "main_activated", "witherstormmod.jei.super_beacon.requiresMainActivated", e -> e.isActive());
/*     */ 
/*     */     
/* 112 */     FULL_SUPPORTS = new Condition("FULL_SUPPORTS", 2, "all_supports", "witherstormmod.jei.super_beacon.requiresAllSupports", e -> 
/* 113 */         (e.isActive() && e.getConnected().size() == 4));
/*     */     
/* 115 */     FULLY_COMLETED = new Condition("FULLY_COMLETED", 3, "fully_completed", "witherstormmod.jei.super_beacon.requiresFullBeacon", e -> 
/* 116 */         (e.isActive() && e.getConnected().size() == 4 && e.beaconLevel == 4));
/*     */   }
/*     */   
/*     */   private final Predicate<SuperBeaconBlockEntity> predicate;
/*     */   @Nullable
/*     */   private final String description;
/*     */   private final String name;
/*     */   
/*     */   Condition(String name, String description, Predicate<SuperBeaconBlockEntity> predicate) {
/* 125 */     this.name = name;
/* 126 */     this.description = description;
/* 127 */     this.predicate = predicate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String m_7912_() {
/* 133 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canCraft(SuperBeaconBlockEntity entity) {
/* 138 */     return this.predicate.test(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 143 */     return this.description;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Condition fromJson(JsonObject object, String name) {
/* 148 */     if (object.has(name)) {
/*     */       
/* 150 */       String id = GsonHelper.m_13906_(object, name);
/* 151 */       for (Condition value : values()) {
/*     */         
/* 153 */         if (value.name.equals(id))
/* 154 */           return value; 
/*     */       } 
/*     */     } 
/* 157 */     return NONE;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\crafting\SuperBeaconRecipe$Condition.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
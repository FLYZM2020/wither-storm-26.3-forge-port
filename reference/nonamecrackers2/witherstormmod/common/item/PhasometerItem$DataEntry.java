/*     */ package nonamecrackers2.witherstormmod.common.item;
/*     */ 
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum DataEntry
/*     */ {
/* 140 */   OBSTRUCTED("IsObstructed", false, false)
/*     */   {
/*     */     
/*     */     public Component getDisplayText(CompoundTag tag)
/*     */     {
/* 145 */       return (Component)Component.m_237115_("description.phasometer.obstructed").m_130940_(ChatFormatting.RED);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasData(CompoundTag tag) {
/* 151 */       return tag.m_128441_(this.tagName);
/*     */     }
/*     */   },
/* 154 */   PHASE("LookingAtPhase", false)
/*     */   {
/*     */     
/*     */     protected void apply(CompoundTag tag, WitherStormEntity storm)
/*     */     {
/* 159 */       tag.m_128405_(this.tagName, storm.getPhase());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Component getDisplayText(CompoundTag tag) {
/* 165 */       return (Component)Component.m_237110_("description.phasometer.phase", new Object[] { Integer.valueOf(tag.m_128451_(this.tagName)) }).m_130940_(ChatFormatting.GREEN);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasData(CompoundTag tag) {
/* 171 */       return tag.m_128441_(this.tagName);
/*     */     }
/*     */   },
/* 174 */   FORMIDIBOMBABLE("IsFormidibombable", false)
/*     */   {
/*     */     
/*     */     protected void apply(CompoundTag tag, WitherStormEntity storm)
/*     */     {
/* 179 */       tag.m_128379_(this.tagName, (storm.getPhase() == 5 && storm.getConsumedEntities() >= storm.getConsumptionAmountForPhase(5)));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Component getDisplayText(CompoundTag tag) {
/* 185 */       return (Component)Component.m_237115_("description.phasometer.formidibombable").m_130940_(ChatFormatting.GOLD);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasData(CompoundTag tag) {
/* 191 */       return tag.m_128471_(this.tagName);
/*     */     }
/*     */   },
/* 194 */   BOWELS_ACCESSIBLE("BowelsAccessible", false)
/*     */   {
/*     */     
/*     */     protected void apply(CompoundTag tag, WitherStormEntity storm)
/*     */     {
/* 199 */       tag.m_128379_(this.tagName, storm.isBeingTornApart());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Component getDisplayText(CompoundTag tag) {
/* 205 */       return (Component)Component.m_237115_("description.phasometer.bowelsAccessible").m_130940_(ChatFormatting.GOLD);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasData(CompoundTag tag) {
/* 211 */       return tag.m_128471_(this.tagName);
/*     */     }
/*     */   },
/* 214 */   DISTRACTED("IsDistracted", false)
/*     */   {
/*     */     
/*     */     protected void apply(CompoundTag tag, WitherStormEntity storm)
/*     */     {
/* 219 */       UltimateTargetManager manager = storm.getUltimateTargetManager().orElse(null);
/* 220 */       if (manager != null) {
/* 221 */         tag.m_128379_(this.tagName, manager.isDistracted());
/*     */       } else {
/* 223 */         tag.m_128379_(this.tagName, false);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public Component getDisplayText(CompoundTag tag) {
/* 229 */       return (Component)Component.m_237115_("description.phasometer.distracted").m_130940_(ChatFormatting.GOLD);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasData(CompoundTag tag) {
/* 235 */       return tag.m_128471_(this.tagName);
/*     */     }
/*     */   },
/* 238 */   CHASING("IsChasing", false)
/*     */   {
/*     */     
/*     */     protected void apply(CompoundTag tag, WitherStormEntity storm)
/*     */     {
/* 243 */       UltimateTargetManager manager = storm.getUltimateTargetManager().orElse(null);
/* 244 */       if (manager != null) {
/* 245 */         tag.m_128379_(this.tagName, manager.isTargetStationary());
/*     */       } else {
/* 247 */         tag.m_128379_(this.tagName, false);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public Component getDisplayText(CompoundTag tag) {
/* 253 */       return (Component)Component.m_237115_("description.phasometer.chasing").m_130940_(ChatFormatting.GOLD);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasData(CompoundTag tag) {
/* 259 */       return tag.m_128471_(this.tagName);
/*     */     }
/*     */   },
/* 262 */   ULTIMATE_TARGET("UltimateTarget", true)
/*     */   {
/*     */     
/*     */     protected void apply(CompoundTag tag, WitherStormEntity storm)
/*     */     {
/* 267 */       LivingEntity target = storm.getUltimateTarget();
/* 268 */       if (target != null) {
/* 269 */         tag.m_128359_(this.tagName, target.m_5446_().getString());
/*     */       } else {
/* 271 */         tag.m_128473_(this.tagName);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public Component getDisplayText(CompoundTag tag) {
/* 277 */       return (Component)Component.m_237110_("description.phasometer.ultimateTarget", new Object[] { tag.m_128461_(this.tagName) }).m_130940_(ChatFormatting.LIGHT_PURPLE);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasData(CompoundTag tag) {
/* 283 */       return tag.m_128441_(this.tagName);
/*     */     }
/*     */   },
/* 286 */   ULTIMATE_TARGET_DIRECTION("UltimateTargetDirection", true)
/*     */   {
/*     */     
/*     */     protected void apply(CompoundTag tag, WitherStormEntity storm)
/*     */     {
/* 291 */       Vec3 pos = storm.getUltimateTargetPos();
/* 292 */       if (pos != null) {
/*     */         
/* 294 */         Vec3 normal = (new Vec3(pos.f_82479_, storm.m_20186_(), pos.f_82481_)).m_82546_(storm.m_20182_()).m_82541_();
/* 295 */         tag.m_128359_(this.tagName, Direction.m_122366_(normal.f_82479_, normal.f_82480_, normal.f_82481_).m_122433_());
/*     */       }
/*     */       else {
/*     */         
/* 299 */         tag.m_128473_(this.tagName);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Component getDisplayText(CompoundTag tag) {
/* 306 */       return (Component)Component.m_237110_("description.phasometer.ultimateTargetDirection", new Object[] { tag.m_128461_(this.tagName) }).m_130940_(ChatFormatting.LIGHT_PURPLE);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasData(CompoundTag tag) {
/* 312 */       return tag.m_128441_(this.tagName);
/*     */     }
/*     */   },
/* 315 */   PHASE_PROGRESS("PhaseProgress", true)
/*     */   {
/*     */     
/*     */     protected void apply(CompoundTag tag, WitherStormEntity storm)
/*     */     {
/* 320 */       if (storm.canEvolve(true)) {
/*     */         
/* 322 */         int progress = Math.round(storm.getPhaseProgress() * 100.0F);
/* 323 */         tag.m_128405_(this.tagName, progress);
/*     */       }
/*     */       else {
/*     */         
/* 327 */         tag.m_128405_(this.tagName, 100);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Component getDisplayText(CompoundTag tag) {
/* 334 */       return (Component)Component.m_237110_("description.phasometer.phaseProgress", new Object[] { "" + tag.m_128451_(this.tagName) + "%" }).m_130940_(ChatFormatting.LIGHT_PURPLE);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasData(CompoundTag tag) {
/* 340 */       return tag.m_128441_(this.tagName);
/*     */     }
/*     */   };
/*     */ 
/*     */   
/*     */   public final String tagName;
/*     */   public final boolean requiresUpgraded;
/*     */   public final boolean isInformational;
/*     */   
/*     */   DataEntry(String tagName, boolean requiresUpgraded, boolean isInformational) {
/* 350 */     this.tagName = tagName;
/* 351 */     this.requiresUpgraded = requiresUpgraded;
/* 352 */     this.isInformational = isInformational;
/*     */   }
/*     */   
/*     */   protected void apply(CompoundTag tag, WitherStormEntity storm) {}
/*     */   
/*     */   public abstract Component getDisplayText(CompoundTag paramCompoundTag);
/*     */   
/*     */   public abstract boolean hasData(CompoundTag paramCompoundTag);
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\PhasometerItem$DataEntry.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
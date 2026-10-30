/*     */ package nonamecrackers2.witherstormmod.common.capability;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Snapshot
/*     */ {
/*     */   @Nullable
/*     */   private UUID lastStorm;
/*     */   private int lastPhase;
/*     */   private int symbiontSummonCooldown;
/*     */   public boolean shouldRemove;
/*     */   
/*     */   public Snapshot(@Nullable UUID lastStorm, int lastPhase, int cooldown) {
/* 195 */     this.lastStorm = lastStorm;
/* 196 */     this.lastPhase = lastPhase;
/* 197 */     this.symbiontSummonCooldown = cooldown;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public UUID getLastStorm() {
/* 202 */     return this.lastStorm;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLastPhase() {
/* 207 */     return this.lastPhase;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSymbiontSummonCooldown() {
/* 212 */     return this.symbiontSummonCooldown;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/* 217 */     if (this.symbiontSummonCooldown > 0) {
/* 218 */       this.symbiontSummonCooldown--;
/*     */     } else {
/* 220 */       this.shouldRemove = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundTag write() {
/* 228 */     CompoundTag data = new CompoundTag();
/* 229 */     if (this.lastStorm != null)
/* 230 */       data.m_128362_("Summoner", this.lastStorm); 
/* 231 */     data.m_128405_("SummonerPhase", this.lastPhase);
/* 232 */     data.m_128405_("SummoningCooldown", this.symbiontSummonCooldown);
/* 233 */     return data;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Snapshot read(CompoundTag compound) {
/* 238 */     UUID lastStorm = null;
/* 239 */     if (compound.m_128441_("Summoner"))
/* 240 */       lastStorm = compound.m_128342_("Summoner"); 
/* 241 */     int lastPhase = compound.m_128451_("SummonerPhase");
/* 242 */     int summoningCooldown = compound.m_128451_("SummoningCooldown");
/* 243 */     return new Snapshot(lastStorm, lastPhase, summoningCooldown);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 249 */     return "Snapshot[UUID=" + this.lastStorm + ", lastPhase" + this.lastPhase + ", summoningCooldown" + this.symbiontSummonCooldown + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\capability\PlayerWitherStormData$Snapshot.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
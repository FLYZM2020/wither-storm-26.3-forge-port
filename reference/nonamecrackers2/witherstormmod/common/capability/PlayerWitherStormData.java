/*     */ package nonamecrackers2.witherstormmod.common.capability;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.ListTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*     */ 
/*     */ public class PlayerWitherStormData
/*     */   extends EntityCapability<PlayerWitherStormData, Player> {
/*     */   private static final int AREA_CHECK_INTERVAL = 80;
/*  20 */   private List<Snapshot> data = new ArrayList<>();
/*     */   
/*     */   private int invulnerableTime;
/*     */   
/*     */   private boolean hasActivatedSuperBeacon;
/*     */   private UUID killedByStorm;
/*     */   private int nextAreaCheck;
/*     */   private boolean isInAnOpenArea;
/*     */   
/*     */   public PlayerWitherStormData(Player player) {
/*  30 */     super(player);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  41 */     for (int i = 0; i < this.data.size(); i++) {
/*     */       
/*  43 */       Snapshot snapshot = this.data.get(i);
/*  44 */       snapshot.tick();
/*  45 */       if (snapshot.shouldRemove) {
/*  46 */         this.data.remove(i);
/*     */       }
/*     */     } 
/*  49 */     if (this.invulnerableTime > 0) {
/*  50 */       this.invulnerableTime--;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  57 */     if (this.nextAreaCheck > 0)
/*  58 */       this.nextAreaCheck--; 
/*  59 */     if (this.nextAreaCheck == 0) {
/*     */       
/*  61 */       this.isInAnOpenArea = WorldUtil.isInAnOpenArea((Entity)this.entity);
/*  62 */       this.nextAreaCheck = 80;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundTag write() {
/*  69 */     CompoundTag compound = new CompoundTag();
/*  70 */     ListTag list = new ListTag();
/*  71 */     for (Snapshot snapshot : this.data)
/*  72 */       list.add(snapshot.write()); 
/*  73 */     compound.m_128365_("Data", (Tag)list);
/*  74 */     compound.m_128405_("InvulnerableTime", this.invulnerableTime);
/*  75 */     compound.m_128379_("HasActivatedSuperBeacon", this.hasActivatedSuperBeacon);
/*  76 */     if (this.killedByStorm != null)
/*  77 */       compound.m_128362_("KilledByStorm", this.killedByStorm); 
/*  78 */     return compound;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void read(CompoundTag compound) {
/*  84 */     ListTag list = compound.m_128437_("Data", 10);
/*  85 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/*  87 */       CompoundTag snapshot = list.m_128728_(i);
/*  88 */       this.data.add(Snapshot.read(snapshot));
/*     */     } 
/*  90 */     this.invulnerableTime = compound.m_128451_("InvulnerableTime");
/*  91 */     this.hasActivatedSuperBeacon = compound.m_128471_("HasActivatedSuperBeacon");
/*  92 */     if (compound.m_128403_("KilledByStorm")) {
/*  93 */       this.killedByStorm = compound.m_128342_("KilledByStorm");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void copyFrom(PlayerWitherStormData instance) {
/*  99 */     this.data = instance.data;
/* 100 */     this.invulnerableTime = instance.invulnerableTime;
/* 101 */     this.killedByStorm = instance.killedByStorm;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasRecentlySummonedSymbiont(WitherStormEntity entity) {
/* 106 */     Snapshot snapshot = getFor(entity);
/* 107 */     return (snapshot != null) ? ((snapshot.getSymbiontSummonCooldown() > 0)) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasKilledSymbiontRecently() {
/* 112 */     return (this.invulnerableTime > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasChangedPhase(WitherStormEntity entity) {
/* 117 */     Snapshot snapshot = getFor(entity);
/* 118 */     return (snapshot != null) ? ((snapshot.getLastPhase() != entity.getPhase())) : true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void markSummonedSymbiont(WitherStormEntity entity) {
/* 123 */     Snapshot snapshot = getFor(entity);
/* 124 */     int symbiontSummonCooldown = Mth.m_14045_(((Integer)WitherStormModConfig.SERVER.playerSummoningDelay.get()).intValue(), 1, 60) * 1200 + this.entity.m_217043_().m_188503_(2400);
/* 125 */     if (snapshot != null) {
/*     */       
/* 127 */       snapshot.symbiontSummonCooldown = symbiontSummonCooldown;
/* 128 */       snapshot.lastPhase = entity.getPhase();
/*     */     }
/*     */     else {
/*     */       
/* 132 */       this.data.add(new Snapshot(entity.m_20148_(), entity.getPhase(), symbiontSummonCooldown));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void markKilledSymbiont(WitherStormEntity entity) {
/* 138 */     int symbiontSummonCooldown = Mth.m_14045_(((Integer)WitherStormModConfig.SERVER.playerSummoningDelayOnKill.get()).intValue(), 1, 60) * 1200 + this.entity.m_217043_().m_188503_(24000);
/* 139 */     Snapshot snapshot = getFor(entity);
/* 140 */     if (snapshot != null) {
/* 141 */       snapshot.symbiontSummonCooldown = symbiontSummonCooldown;
/*     */     } else {
/* 143 */       this.data.add(new Snapshot(entity.m_20148_(), entity.getPhase(), symbiontSummonCooldown));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void makeInvulnerable(int time) {
/* 148 */     this.invulnerableTime = time;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Snapshot getFor(WitherStormEntity entity) {
/* 153 */     for (Snapshot snapshot : this.data) {
/*     */       
/* 155 */       if (snapshot.lastStorm != null && snapshot.lastStorm.equals(entity.m_20148_()))
/* 156 */         return snapshot; 
/*     */     } 
/* 158 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasActivatedSuperBeacon() {
/* 163 */     return this.hasActivatedSuperBeacon;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActivatedSuperBeacon(boolean flag) {
/* 168 */     this.hasActivatedSuperBeacon = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public UUID getKilledByStorm() {
/* 173 */     return this.killedByStorm;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setKilledByStorm(@Nullable UUID id) {
/* 178 */     this.killedByStorm = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInAnOpenArea() {
/* 183 */     return this.isInAnOpenArea;
/*     */   }
/*     */   
/*     */   public PlayerWitherStormData() {}
/*     */   
/*     */   public static class Snapshot
/*     */   {
/*     */     @Nullable
/*     */     private UUID lastStorm;
/*     */     private int lastPhase;
/*     */     
/*     */     public Snapshot(@Nullable UUID lastStorm, int lastPhase, int cooldown) {
/* 195 */       this.lastStorm = lastStorm;
/* 196 */       this.lastPhase = lastPhase;
/* 197 */       this.symbiontSummonCooldown = cooldown;
/*     */     }
/*     */     private int symbiontSummonCooldown; public boolean shouldRemove;
/*     */     @Nullable
/*     */     public UUID getLastStorm() {
/* 202 */       return this.lastStorm;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getLastPhase() {
/* 207 */       return this.lastPhase;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getSymbiontSummonCooldown() {
/* 212 */       return this.symbiontSummonCooldown;
/*     */     }
/*     */ 
/*     */     
/*     */     public void tick() {
/* 217 */       if (this.symbiontSummonCooldown > 0) {
/* 218 */         this.symbiontSummonCooldown--;
/*     */       } else {
/* 220 */         this.shouldRemove = true;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public CompoundTag write() {
/* 228 */       CompoundTag data = new CompoundTag();
/* 229 */       if (this.lastStorm != null)
/* 230 */         data.m_128362_("Summoner", this.lastStorm); 
/* 231 */       data.m_128405_("SummonerPhase", this.lastPhase);
/* 232 */       data.m_128405_("SummoningCooldown", this.symbiontSummonCooldown);
/* 233 */       return data;
/*     */     }
/*     */ 
/*     */     
/*     */     public static Snapshot read(CompoundTag compound) {
/* 238 */       UUID lastStorm = null;
/* 239 */       if (compound.m_128441_("Summoner"))
/* 240 */         lastStorm = compound.m_128342_("Summoner"); 
/* 241 */       int lastPhase = compound.m_128451_("SummonerPhase");
/* 242 */       int summoningCooldown = compound.m_128451_("SummoningCooldown");
/* 243 */       return new Snapshot(lastStorm, lastPhase, summoningCooldown);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 249 */       return "Snapshot[UUID=" + this.lastStorm + ", lastPhase" + this.lastPhase + ", summoningCooldown" + this.symbiontSummonCooldown + "]";
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\capability\PlayerWitherStormData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
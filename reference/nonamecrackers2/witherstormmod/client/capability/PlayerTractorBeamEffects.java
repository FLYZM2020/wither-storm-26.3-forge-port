/*    */ package nonamecrackers2.witherstormmod.client.capability;
/*    */ 
/*    */ import net.minecraft.client.player.LocalPlayer;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.EntitySelector;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraftforge.event.TickEvent;
/*    */ import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;
/*    */ import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
/*    */ import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
/*    */ 
/*    */ 
/*    */ public class PlayerTractorBeamEffects
/*    */ {
/*    */   private final LocalPlayer player;
/*    */   private int ticksInTractorBeam;
/*    */   
/*    */   public PlayerTractorBeamEffects(LocalPlayer player) {
/* 19 */     this.player = player;
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerTractorBeamEffects() {
/* 24 */     this.player = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public <T extends LivingEntity & WitherStormBase> void tick() {
/* 29 */     boolean flag = false;
/* 30 */     if (EntitySelector.f_20408_.test(this.player))
/*    */     {
/* 32 */       for (Entity entity : this.player.f_108545_.m_104735_()) {
/*    */         
/* 34 */         if (entity instanceof LivingEntity) { LivingEntity living = (LivingEntity)entity; if (living instanceof WitherStormBase) { WitherStormBase storm = (WitherStormBase)living;
/*    */ 
/*    */             
/* 37 */             LivingEntity livingEntity = (LivingEntity)storm;
/* 38 */             if (((Boolean)TractorBeamHelper.isInsideTractorBeam((Entity)this.player, livingEntity, 4.0D).getFirst()).booleanValue()) {
/*    */               
/* 40 */               flag = true;
/*    */               break;
/*    */             }  }
/*    */            }
/*    */       
/*    */       }  } 
/* 46 */     if (flag) {
/*    */       
/* 48 */       if (this.ticksInTractorBeam < 240) {
/* 49 */         this.ticksInTractorBeam++;
/*    */       
/*    */       }
/*    */     }
/* 53 */     else if (this.ticksInTractorBeam > 0) {
/* 54 */       this.ticksInTractorBeam--;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTicksInTractorBeam() {
/* 60 */     return this.ticksInTractorBeam;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPercent() {
/* 65 */     return Math.min(getTicksInTractorBeam(), 120) / 240.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
/* 70 */     if (event.phase == TickEvent.Phase.END)
/* 71 */       event.player.getCapability(WitherStormModClientCapabilities.TRACTOR_BEAM_EFFECTS).ifPresent(PlayerTractorBeamEffects::tick); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\capability\PlayerTractorBeamEffects.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
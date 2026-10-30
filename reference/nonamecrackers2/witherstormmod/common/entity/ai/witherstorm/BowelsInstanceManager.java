/*    */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*    */ import nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager;
/*    */ import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*    */ 
/*    */ 
/*    */ public class BowelsInstanceManager
/*    */ {
/*    */   private final WitherStormEntity storm;
/*    */   @Nullable
/*    */   private WitherStormBowelsManager.BowelsInstance instance;
/*    */   @Nullable
/*    */   private CommandBlockEntity commandBlock;
/*    */   
/*    */   public BowelsInstanceManager(WitherStormEntity storm) {
/* 23 */     this.storm = storm;
/*    */   }
/*    */ 
/*    */   
/*    */   public void loadInstance() {
/* 28 */     ServerLevel bowels = WitherStormMod.bowels((ServerLevel)this.storm.m_9236_());
/* 29 */     bowels.getCapability(WitherStormModCapabilities.BOWELS_MANAGER).ifPresent(manager -> {
/*    */           WitherStormBowelsManager.BowelsInstance instance = manager.getOrCreateInstanceFor(this.storm);
/*    */           if (instance != null) {
/*    */             manager.add(instance);
/*    */             instance.setup(bowels);
/*    */             instance.doChunkLoading(bowels);
/*    */             this.instance = instance;
/*    */           } 
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 44 */     if (this.instance != null) {
/*    */       
/* 46 */       if (this.instance.isCompleted()) {
/*    */         
/* 48 */         this.commandBlock = null;
/* 49 */         this.instance = null;
/* 50 */         loadInstance();
/*    */       } 
/*    */       
/* 53 */       if (this.commandBlock == null && this.instance.hasPreparedArena()) {
/*    */         
/* 55 */         ServerLevel bowels = WitherStormMod.bowels((ServerLevel)this.storm.m_9236_());
/* 56 */         UUID uuid = this.instance.getCommandBlockUUID();
/* 57 */         if (uuid != null) {
/*    */           
/* 59 */           Entity entity = bowels.m_8791_(this.instance.getCommandBlockUUID());
/* 60 */           if (entity instanceof CommandBlockEntity)
/* 61 */             this.commandBlock = (CommandBlockEntity)entity; 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public CommandBlockEntity getCommandBlock() {
/* 69 */     return this.commandBlock;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public WitherStormBowelsManager.BowelsInstance getBowelsInstance() {
/* 74 */     return this.instance;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\BowelsInstanceManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
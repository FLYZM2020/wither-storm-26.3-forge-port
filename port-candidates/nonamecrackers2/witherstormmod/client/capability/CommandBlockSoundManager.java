/*    */ package nonamecrackers2.witherstormmod.client.capability;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import nonamecrackers2.witherstormmod.client.audio.CommandBlockEntityLoop;
/*    */ import nonamecrackers2.witherstormmod.client.audio.EntitySoundManager;
/*    */ import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
/*    */ 
/*    */ public class CommandBlockSoundManager extends EntitySoundManager<CommandBlockEntity, CommandBlockEntityLoop> {
/*    */   public CommandBlockSoundManager(Minecraft minecraft) {
/* 12 */     super(minecraft, CommandBlockEntity.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean alreadyHasLoop(CommandBlockEntity entity) {
/* 18 */     boolean flag = false;
/* 19 */     for (CommandBlockEntityLoop loop : this.loops) {
/*    */       
/* 21 */       if (loop.entity == entity) {
/*    */         
/* 23 */         flag = true;
/*    */         break;
/*    */       } 
/*    */     } 
/* 27 */     return flag;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected CommandBlockEntityLoop create(CommandBlockEntity entity) {
/* 33 */     return new CommandBlockEntityLoop(entity);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected CommandBlockEntityLoop copyFrom(CommandBlockEntityLoop loop) {
/* 39 */     return new CommandBlockEntityLoop(loop.entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\capability\CommandBlockSoundManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
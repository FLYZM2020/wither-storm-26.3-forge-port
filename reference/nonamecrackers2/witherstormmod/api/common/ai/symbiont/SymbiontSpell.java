/*    */ package nonamecrackers2.witherstormmod.api.common.ai.symbiont;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nonnull;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class SymbiontSpell
/*    */ {
/*    */   protected final WitheredSymbiontEntity entity;
/*    */   protected final SpellType type;
/* 18 */   protected final List<Entity> projectiles = Lists.newArrayList();
/*    */ 
/*    */   
/*    */   public SymbiontSpell(WitheredSymbiontEntity symbiont, SpellType type) {
/* 22 */     this.entity = symbiont;
/* 23 */     this.type = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public void start(@Nonnull LivingEntity target) {}
/*    */   
/*    */   public abstract void cast(@Nonnull LivingEntity paramLivingEntity);
/*    */   
/*    */   public void finish() {
/* 32 */     for (Entity projectile : this.projectiles)
/* 33 */       projectile.m_20242_(false); 
/* 34 */     this.projectiles.clear();
/*    */   }
/*    */   
/*    */   public void doCasting(@Nonnull LivingEntity target) {}
/*    */   
/*    */   public abstract int getDelay(RandomSource paramRandomSource, float paramFloat);
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\api\common\ai\symbiont\SymbiontSpell.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
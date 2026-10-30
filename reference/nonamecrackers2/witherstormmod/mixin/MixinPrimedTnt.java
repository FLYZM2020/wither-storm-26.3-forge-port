/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import java.util.List;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.item.PrimedTnt;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
/*    */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyVariable;
/*    */ 
/*    */ 
/*    */ @Mixin({PrimedTnt.class})
/*    */ public abstract class MixinPrimedTnt
/*    */   extends Entity
/*    */ {
/*    */   private MixinPrimedTnt(EntityType<?> type, Level level) {
/* 25 */     super(type, level);
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
/*    */   
/*    */   @ModifyVariable(method = {"tick"}, at = @At("STORE"), ordinal = 0)
/*    */   public int tickModifyFuse(int i) {
/* 40 */     if (!((PrimedTnt)this instanceof nonamecrackers2.witherstormmod.common.entity.FormidibombEntity)) {
/*    */       
/* 42 */       List<WitherStormEntity> storms = m_9236_().m_45976_(WitherStormEntity.class, m_20191_().m_82377_(100.0D, 200.0D, 100.0D));
/* 43 */       WitherStormEntity storm = (WitherStormEntity)WorldUtil.getNearest(storms, m_20182_(), Entity::m_20182_);
/* 44 */       if (storm != null) {
/*    */         
/* 46 */         Pair<Boolean, Integer> pair = TractorBeamHelper.isInsideTractorBeam(this, (LivingEntity)storm, 4.0D);
/* 47 */         if (((Boolean)pair.getFirst()).booleanValue()) {
/*    */           
/* 49 */           Vec3 pos = storm.getHeadPos(((Integer)pair.getSecond()).intValue());
/* 50 */           if (pos.m_82554_(m_20182_()) > 12.0D) {
/*    */             
/* 52 */             if (i == 20) {
/* 53 */               return 80;
/*    */             }
/*    */           } else {
/*    */             
/* 57 */             return 0;
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/* 62 */     return i;
/*    */   }
/*    */   
/*    */   @Shadow
/*    */   public abstract void m_32085_(int paramInt);
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinPrimedTnt.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
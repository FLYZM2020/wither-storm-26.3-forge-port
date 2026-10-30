/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import net.minecraft.world.entity.item.ItemEntity;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModItemTags;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Redirect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin(value = {ItemEntity.class}, priority = 999)
/*    */ public abstract class MixinItemEntity
/*    */ {
/*    */   @Redirect(method = {"tick"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/item/ItemEntity;setDeltaMovement(Lnet/minecraft/world/phys/Vec3;)V", ordinal = 0))
/*    */   public void tick_makeItemFloatInVoid(ItemEntity entity, Vec3 delta) {
/* 23 */     if (entity.m_20186_() < entity.m_9236_().m_141937_() && m_32055_().m_204117_(WitherStormModItemTags.CANNOT_FALL_IN_VOID)) {
/* 24 */       entity.m_20256_(entity.m_20184_().m_82520_(0.0D, 0.04D, 0.0D));
/*    */     } else {
/* 26 */       entity.m_20256_(delta);
/*    */     } 
/*    */   }
/*    */   
/*    */   @Shadow
/*    */   public abstract ItemStack m_32055_();
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinItemEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
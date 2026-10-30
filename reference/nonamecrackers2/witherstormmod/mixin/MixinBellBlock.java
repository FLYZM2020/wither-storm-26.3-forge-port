/*    */ package nonamecrackers2.witherstormmod.mixin;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.BellBlock;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.phys.AABB;
/*    */ import net.minecraft.world.phys.BlockHitResult;
/*    */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCriteriaTriggers;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({BellBlock.class})
/*    */ public class MixinBellBlock
/*    */ {
/*    */   @Inject(method = {"onHit"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;awardStat(Lnet/minecraft/resources/ResourceLocation;)V")})
/*    */   public void onHitInvoke(Level level, BlockState state, BlockHitResult hitResult, @Nullable Player player, boolean flag, CallbackInfoReturnable<Boolean> ci) {
/* 32 */     if (!level.f_46443_) {
/*    */       
/* 34 */       AABB box = player.m_20191_().m_82400_(300.0D);
/* 35 */       List<WitherStormEntity> storms = level.m_45976_(WitherStormEntity.class, box);
/* 36 */       if (!storms.isEmpty()) {
/*    */         
/* 38 */         Objects.requireNonNull(player); storms.sort(Comparator.comparingDouble(player::m_20280_));
/* 39 */         WitherStormEntity storm = storms.get(0);
/* 40 */         WitherStormModCriteriaTriggers.RING_BELL_NEAR_STORM.trigger((ServerPlayer)player, storm);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinBellBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
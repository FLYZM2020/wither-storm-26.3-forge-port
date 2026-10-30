/*    */ package nonamecrackers2.witherstormmod.common.util;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.core.Vec3i;
/*    */ import net.minecraft.network.protocol.Packet;
/*    */ import net.minecraft.network.protocol.game.ClientboundSoundPacket;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.sounds.SoundSource;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.level.portal.PortalInfo;
/*    */ import net.minecraft.world.phys.Vec3;
/*    */ import net.minecraftforge.common.util.ITeleporter;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*    */ 
/*    */ public class BowelsTeleporter
/*    */   implements ITeleporter {
/*    */   private final BlockPos pos;
/*    */   
/*    */   public BowelsTeleporter(BlockPos pos) {
/* 23 */     this.pos = pos;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public PortalInfo getPortalInfo(Entity entity, ServerLevel destWorld, Function<ServerLevel, PortalInfo> defaultPortalInfo) {
/* 29 */     return new PortalInfo(Vec3.atBottomCenterOf((Vec3i)this.pos), Vec3.ZERO, entity.getYRot(), entity.getXRot());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean playTeleportSound(ServerPlayer player, ServerLevel sourceWorld, ServerLevel destWorld) {
/* 35 */     player.connection.send((Packet)new ClientboundSoundPacket(ForgeRegistries.SOUND_EVENTS.getHolder(WitherStormModSoundEvents.BOWELS_TRANSPORT.get()).get(), SoundSource.AMBIENT, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextLong()));
/* 36 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\commo\\util\BowelsTeleporter.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
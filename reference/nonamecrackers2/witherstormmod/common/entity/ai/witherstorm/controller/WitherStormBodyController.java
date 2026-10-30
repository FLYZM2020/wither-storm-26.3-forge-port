/*     */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.controller;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.resources.ResourceKey;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.ai.control.BodyRotationControl;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.fml.DistExecutor;
/*     */ import net.minecraftforge.network.NetworkEvent;
/*     */ import net.minecraftforge.network.PacketDistributor;
/*     */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*     */ import nonamecrackers2.witherstormmod.client.capability.WitherStormDistantRenderer;
/*     */ import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.AdditionalHead;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*     */ 
/*     */ 
/*     */ public class WitherStormBodyController
/*     */   extends BodyRotationControl
/*     */ {
/*     */   protected final WitherStormEntity storm;
/*     */   protected float maxRotSpeed;
/*     */   protected boolean hasWanted;
/*     */   
/*     */   public WitherStormBodyController(WitherStormEntity storm) {
/*  36 */     super((Mob)storm);
/*  37 */     this.storm = storm;
/*     */   }
/*     */   protected double wantedX; protected double wantedZ; protected int headStableTime;
/*     */   protected float lastStableYHeadRot;
/*     */   
/*     */   public void m_8121_() {
/*  43 */     if (!(this.storm.m_9236_()).f_46443_) {
/*     */       
/*  45 */       if (!this.storm.m_21525_() && !this.storm.isDeadOrPlayingDead()) {
/*     */         
/*  47 */         boolean ultimateTargetInUseBySegment = (this.storm.getUltimateTarget() == null) ? false : this.storm.targetInUseBySegment((Entity)this.storm.getUltimateTarget());
/*  48 */         boolean ultimateTargetInUseByHeads = (this.storm.getUltimateTarget() == null) ? false : this.storm.alreadyATarget((Entity)this.storm.getUltimateTarget(), false);
/*     */         
/*  50 */         FormidibombEntity formidibomb = this.storm.getFormidibomb();
/*  51 */         boolean formidibombNearby = this.storm.isNearbyTickingFormidibomb();
/*     */         
/*  53 */         if (this.storm.getPhase() > 3) {
/*     */           
/*  55 */           if (!ultimateTargetInUseBySegment && !ultimateTargetInUseByHeads && !formidibombNearby && this.storm.shouldRotateTowardsUltimateTarget()) {
/*     */             
/*  57 */             Vec3 pos = this.storm.getUltimateTargetPos();
/*  58 */             if (pos != null) {
/*  59 */               setBodyAt(pos.m_7096_(), pos.m_7094_(), ((Double)WitherStormModConfig.SERVER.rotationSpeed.get()).floatValue());
/*     */             }
/*  61 */             if (this.hasWanted)
/*     */             {
/*  63 */               this.storm.f_20883_ = rotateTowards(this.storm.f_20883_, getYRotD(), this.maxRotSpeed);
/*  64 */               m_24881_();
/*     */             }
/*     */           
/*  67 */           } else if (formidibombNearby) {
/*     */             
/*  69 */             setBodyAt(formidibomb.m_20185_(), formidibomb.m_20189_(), 0.1F);
/*  70 */             if (this.hasWanted)
/*     */             {
/*  72 */               this.storm.f_20883_ = rotateTowards(this.storm.f_20883_, getYRotD(), this.maxRotSpeed);
/*  73 */               m_24881_();
/*     */             }
/*     */           
/*     */           } 
/*     */         } else {
/*     */           
/*  79 */           boolean flag = true;
/*  80 */           for (AdditionalHead head : this.storm.getHeadManager().getOtherHeads()) {
/*     */             
/*  82 */             if (head.getTarget() != null) {
/*     */               
/*  84 */               flag = false;
/*     */               break;
/*     */             } 
/*     */           } 
/*  88 */           if (flag && this.storm.m_5448_() == null && m_24884_()) {
/*     */             
/*  90 */             Vec3 pos = this.storm.getUltimateTargetPos();
/*  91 */             if (pos != null) {
/*  92 */               setBodyAt(pos.m_7096_(), pos.m_7094_(), 5.0F);
/*     */             }
/*  94 */             if (this.hasWanted)
/*     */             {
/*  96 */               this.storm.f_20883_ = rotateTowards(this.storm.f_20883_, getYRotD(), this.maxRotSpeed);
/*  97 */               m_24881_();
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 102 */             super.m_8121_();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 107 */       if (this.storm.canFallOnBack())
/*     */       {
/* 109 */         if (this.storm.getPlayDeadManager().getState() == PlayDeadManager.State.PLAYING_DEAD) {
/*     */           
/* 111 */           if (this.storm.xBodyRot < 90.0F)
/* 112 */             this.storm.xBodyRot += this.storm.xBodyRot * 0.04F + 0.05F; 
/* 113 */           if (this.storm.xBodyRot > 90.0F)
/*     */           {
/* 115 */             this.storm.xBodyRot = 90.0F;
/* 116 */             this.storm.onFallOnBack();
/*     */           }
/*     */         
/* 119 */         } else if (!this.storm.isPlayingDead()) {
/*     */           
/* 121 */           WitherStormEntity witherStormEntity = this.storm; witherStormEntity.xBodyRot += -this.storm.xBodyRot * 0.015F - 0.02F;
/* 122 */           if (this.storm.xBodyRot < 0.0F) {
/* 123 */             this.storm.xBodyRot = 0.0F;
/*     */           }
/*     */         } 
/*     */       }
/* 127 */       UpdateBodyRotMessage message = new UpdateBodyRotMessage(this.storm);
/* 128 */       ResourceKey<Level> dimension = this.storm.m_9236_().m_46472_();
/* 129 */       WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> dimension), message);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setBodyAt(double x, double z, float speed) {
/* 135 */     this.wantedX = x;
/* 136 */     this.wantedZ = z;
/* 137 */     this.maxRotSpeed = speed;
/* 138 */     this.hasWanted = true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_24881_() {
/* 143 */     this.storm.f_20885_ = Mth.m_14094_(this.storm.f_20885_, this.storm.f_20883_, this.storm.m_8085_());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float rotateTowards(float yRot, float newYRot, float speed) {
/* 149 */     float f = Mth.m_14118_(yRot, newYRot);
/* 150 */     float f1 = Mth.m_14036_(f, -speed, speed);
/* 151 */     return yRot + f1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getYRotD() {
/* 156 */     double d0 = this.wantedX - this.storm.m_20185_();
/* 157 */     double d1 = this.wantedZ - this.storm.m_20189_();
/* 158 */     return (float)(Mth.m_14136_(d1, d0) * 57.2957763671875D) - 90.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean m_24884_() {
/* 163 */     double d0 = this.storm.m_20185_() - this.storm.f_19854_;
/* 164 */     double d1 = this.storm.m_20189_() - this.storm.f_19856_;
/* 165 */     return (d0 * d0 + d1 * d1 > 2.500000277905201E-7D);
/*     */   }
/*     */   
/*     */   public static class UpdateBodyRotMessage
/*     */     extends Packet
/*     */   {
/*     */     private float yBodyRot;
/*     */     private float xBodyRot;
/*     */     private int entityId;
/*     */     
/*     */     private UpdateBodyRotMessage(WitherStormEntity entity) {
/* 176 */       super(true);
/* 177 */       this.yBodyRot = entity.f_20883_;
/* 178 */       this.xBodyRot = entity.xBodyRot;
/* 179 */       this.entityId = entity.m_19879_();
/*     */     }
/*     */ 
/*     */     
/*     */     public UpdateBodyRotMessage() {
/* 184 */       super(false);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void decode(FriendlyByteBuf buffer) {
/* 190 */       this.yBodyRot = buffer.readFloat();
/* 191 */       this.xBodyRot = buffer.readFloat();
/* 192 */       this.entityId = buffer.m_130242_();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void encode(FriendlyByteBuf buffer) {
/* 198 */       buffer.writeFloat(this.yBodyRot);
/* 199 */       buffer.writeFloat(this.xBodyRot);
/* 200 */       buffer.m_130130_(this.entityId);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Runnable getProcessor(NetworkEvent.Context context) {
/* 206 */       return () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 212 */       return "UpdateBodyRotMessage[yBodyRot=" + this.yBodyRot + ", xBodyRot=" + this.xBodyRot + "]";
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public static void processMessage(UpdateBodyRotMessage message) {
/* 218 */       Minecraft mc = Minecraft.m_91087_();
/* 219 */       ClientLevel world = mc.f_91073_;
/* 220 */       WitherStormEntity entity = (WitherStormEntity)mc.f_91073_.m_6815_(message.entityId);
/* 221 */       if (entity != null)
/* 222 */         entity.lerpBodyRotationTo(message.xBodyRot, message.yBodyRot, 3); 
/* 223 */       world.getCapability(WitherStormModClientCapabilities.DISTANT_RENDERER).ifPresent(distantRenderer -> {
/*     */             WitherStormEntity distantEntity = distantRenderer.get(message.entityId);
/*     */             if (distantEntity != null)
/*     */               distantEntity.lerpBodyRotationTo(message.xBodyRot, message.yBodyRot, 3); 
/*     */           });
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstorm\controller\WitherStormBodyController.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
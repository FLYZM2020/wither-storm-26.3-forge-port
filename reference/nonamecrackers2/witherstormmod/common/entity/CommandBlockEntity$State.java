/*      */ package nonamecrackers2.witherstormmod.common.entity;
/*      */ 
/*      */ import net.minecraft.core.particles.ParticleOptions;
/*      */ import net.minecraft.server.level.ServerBossEvent;
/*      */ import net.minecraft.server.level.ServerPlayer;
/*      */ import net.minecraft.sounds.SoundEvent;
/*      */ import net.minecraft.sounds.SoundSource;
/*      */ import net.minecraft.world.entity.Entity;
/*      */ import net.minecraft.world.entity.LivingEntity;
/*      */ import net.minecraft.world.entity.player.Player;
/*      */ import net.minecraft.world.level.Level;
/*      */ import net.minecraft.world.phys.Vec3;
/*      */ import net.minecraftforge.network.PacketDistributor;
/*      */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*      */ import nonamecrackers2.witherstormmod.common.entity.ai.commandblock.BowelsBossFightStages;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
/*      */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*      */ import nonamecrackers2.witherstormmod.common.packet.PlayerMotionMessage;
/*      */ import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public enum State
/*      */ {
/* 1241 */   IDLE
/*      */   {
/*      */     
/*      */     public void tick(CommandBlockEntity entity)
/*      */     {
/* 1246 */       super.tick(entity);
/*      */       
/* 1248 */       if (modeTickDelay() < entity.getStateTicks())
/* 1249 */         entity.getMode().idleTick(entity); 
/*      */     }
/*      */   },
/* 1252 */   PLAYING_DEAD
/*      */   {
/*      */     
/*      */     public void tick(CommandBlockEntity entity)
/*      */     {
/* 1257 */       super.tick(entity);
/*      */       
/* 1259 */       if (modeTickDelay() < entity.getStateTicks()) {
/* 1260 */         entity.getMode().playingDeadTick(entity);
/*      */       }
/* 1262 */       if (!(entity.m_9236_()).f_46443_) {
/*      */         
/* 1264 */         Level world = entity.m_9236_();
/* 1265 */         Player player = world.m_45946_(entity.searchablePlayersPredicate(), entity);
/* 1266 */         if (player != null) {
/*      */           
/* 1268 */           entity.setLuringPlayer(player);
/* 1269 */           entity.nextState();
/*      */         } 
/*      */         
/* 1272 */         int time = ((Integer)WitherStormModConfig.SERVER.revivalTimeMinutes.get()).intValue();
/* 1273 */         if (((Boolean)WitherStormModConfig.SERVER.revivalTimer.get()).booleanValue() && time > 0 && entity.getStateTicks() > time * 1200)
/* 1274 */           entity.setState(State.REACTIVATING); 
/*      */       } 
/*      */     }
/*      */   },
/* 1278 */   LURING
/*      */   {
/*      */     
/*      */     public void tick(CommandBlockEntity entity)
/*      */     {
/* 1283 */       super.tick(entity);
/*      */       
/* 1285 */       if (modeTickDelay() < entity.getStateTicks()) {
/* 1286 */         entity.getMode().idleTick(entity);
/*      */       }
/* 1288 */       if (!(entity.m_9236_()).f_46443_) {
/*      */         
/* 1290 */         Player player = entity.getLuringPlayer();
/* 1291 */         if (player != null && entity.searchablePlayersPredicate().m_26883_(12.0D).m_26885_(entity, (LivingEntity)player) && entity.getStateTicks() < 240) {
/*      */           
/* 1293 */           double speed = 0.025D;
/* 1294 */           Vec3 motion = entity.m_20182_().m_82546_(player.m_20182_()).m_82541_().m_82542_(speed, speed, speed);
/* 1295 */           player.m_20334_(motion.f_82479_, (player.m_20184_()).f_82480_, motion.f_82481_);
/* 1296 */           WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)player), new PlayerMotionMessage(new Vec3(motion.f_82479_, (player.m_20184_()).f_82480_, motion.f_82481_)));
/* 1297 */           if (player.m_20270_((Entity)entity) < 3.0D) {
/*      */             
/* 1299 */             entity.setLuringPlayer((Player)null);
/* 1300 */             entity.nextState();
/*      */           } 
/* 1302 */           for (int i = 0; i < 4; i++)
/*      */           {
/* 1304 */             double x = player.m_20185_() + CommandBlockEntity.access$200(entity).m_188583_() * player.m_20191_().m_82362_() * 0.4D;
/* 1305 */             double y = player.m_20191_().m_82399_().m_7098_() + CommandBlockEntity.access$300(entity).m_188583_() * player.m_20191_().m_82376_() * 0.4D;
/* 1306 */             double z = player.m_20189_() + CommandBlockEntity.access$400(entity).m_188583_() * player.m_20191_().m_82385_() * 0.4D;
/* 1307 */             Vec3 delta = entity.m_20299_(1.0F).m_82492_(x, y, z).m_82541_().m_82490_(0.1D);
/* 1308 */             entity.m_9236_().m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, delta.m_7096_(), delta.m_7098_(), delta.m_7094_());
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 1313 */           entity.nextState();
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void init(CommandBlockEntity entity) {
/* 1321 */       super.init(entity);
/* 1322 */       entity.m_9236_().m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_ACTIVATES.get(), SoundSource.HOSTILE, 5.0F, 1.0F);
/* 1323 */       entity.m_9236_().m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), SoundSource.AMBIENT, 10.0F, 1.0F);
/* 1324 */       if (!(entity.m_9236_()).f_46443_) {
/*      */         
/* 1326 */         ShakeScreenMessage message = new ShakeScreenMessage(40.0F, 5.0F);
/* 1327 */         WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
/*      */       } 
/* 1329 */       for (int i = 0; i < 10; i++) {
/* 1330 */         entity.m_9236_().m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), entity.m_20185_(), entity.m_20188_(), entity.m_20189_(), CommandBlockEntity.access$500(entity).m_188583_() * 0.5D, CommandBlockEntity.access$600(entity).m_188583_() * 0.5D, CommandBlockEntity.access$700(entity).m_188583_() * 0.5D);
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean shouldShowOwnerBossBar() {
/* 1336 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int modeTickDelay() {
/* 1342 */       return 40;
/*      */     }
/*      */   },
/* 1345 */   REACTIVATING
/*      */   {
/*      */     
/*      */     public void tick(CommandBlockEntity entity)
/*      */     {
/* 1350 */       super.tick(entity);
/*      */       
/* 1352 */       if (modeTickDelay() < entity.getStateTicks()) {
/* 1353 */         entity.getMode().protectTick(entity);
/*      */       }
/* 1355 */       if (!(entity.m_9236_()).f_46443_)
/*      */       {
/* 1357 */         if (entity.getOwner() != null && entity.getStateTicks() > 60) {
/*      */           
/* 1359 */           WitherStormEntity owner = entity.getOwner();
/* 1360 */           if (!owner.isReviving()) {
/* 1361 */             owner.reviveFromPlayingDead();
/*      */           }
/*      */         } 
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public void init(CommandBlockEntity entity) {
/* 1369 */       super.init(entity);
/*      */       
/* 1371 */       if (!(entity.m_9236_()).f_46443_) {
/*      */         
/* 1373 */         ShakeScreenMessage message = new ShakeScreenMessage(120.0F, 5.0F);
/* 1374 */         WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
/*      */       } 
/* 1376 */       entity.m_9236_().m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), SoundSource.AMBIENT, 10.0F, 1.0F);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean shouldShowOwnerBossBar() {
/* 1382 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int modeTickDelay() {
/* 1388 */       return 20;
/*      */     }
/*      */   },
/* 1391 */   BOSSFIGHT
/*      */   {
/*      */     
/*      */     public void tick(CommandBlockEntity entity)
/*      */     {
/* 1396 */       super.tick(entity);
/*      */       
/* 1398 */       if (!(entity.m_9236_()).f_46443_) {
/*      */         
/* 1400 */         entity.getBossfightManager().tick();
/* 1401 */         if (!entity.getCurrentPhase().equals(BowelsBossFightStages.IDLE) || entity.m_21223_() < entity.m_21233_()) {
/* 1402 */           entity.bossInfo.m_8321_(true);
/*      */         }
/*      */       } 
/* 1405 */       entity.getMode().idleTick(entity);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void init(CommandBlockEntity entity) {
/* 1411 */       super.init(entity);
/*      */     }
/*      */   };
/*      */ 
/*      */   
/*      */   public void tick(CommandBlockEntity entity) {
/* 1417 */     entity.getMode().tick(entity, entity.getState());
/* 1418 */     entity.stateTicks++;
/* 1419 */     if (this != PLAYING_DEAD)
/*      */     {
/* 1421 */       for (int i = 0; i < 5; i++) {
/*      */         
/* 1423 */         double x = entity.m_20185_() + CommandBlockEntity.access$800(entity).m_188583_();
/* 1424 */         double y = entity.m_20188_() + CommandBlockEntity.access$900(entity).m_188583_();
/* 1425 */         double z = entity.m_20189_() + CommandBlockEntity.access$1000(entity).m_188583_();
/* 1426 */         float particleSpeed = (entity.m_21233_() - entity.m_21223_()) / entity.m_21233_() + 1.0F;
/* 1427 */         Vec3 delta = entity.m_20299_(1.0F).m_82492_(x, y, z).m_82541_().m_82490_(0.1D * particleSpeed);
/* 1428 */         entity.m_9236_().m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, delta.m_7096_(), delta.m_7098_(), delta.m_7094_());
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void init(CommandBlockEntity entity) {
/* 1435 */     entity.stateTicks = 0;
/* 1436 */     entity.getMode().init(entity, entity.getState());
/* 1437 */     entity.setProtectionYOffset(0.0F);
/* 1438 */     if (entity.getOwner() != null) {
/* 1439 */       initWithOwner(entity.getOwner(), entity);
/*      */     }
/* 1441 */     entity.bossInfo.m_8321_(false);
/*      */   }
/*      */ 
/*      */   
/*      */   public int modeTickDelay() {
/* 1446 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean shouldShowOwnerBossBar() {
/* 1451 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void initWithOwner(WitherStormEntity owner, CommandBlockEntity entity) {
/* 1456 */     if (owner.m_6084_())
/*      */     {
/* 1458 */       if (shouldShowOwnerBossBar()) {
/* 1459 */         owner.getBossInfo().ifPresent(info -> info.m_8321_(true));
/*      */       } else {
/* 1461 */         owner.getBossInfo().ifPresent(info -> info.m_8321_(!owner.isPlayingDead()));
/*      */       } 
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\CommandBlockEntity$State.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
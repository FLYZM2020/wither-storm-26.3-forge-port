/*     */ package nonamecrackers2.witherstormmod.common.event;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import net.minecraft.commands.arguments.EntityAnchorArgument;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Position;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.item.ItemEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Items;
/*     */ import net.minecraft.world.level.ItemLike;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.LevelReader;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.levelgen.Heightmap;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.event.ForgeEventFactory;
/*     */ import net.minecraftforge.event.entity.EntityMountEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.event.level.ExplosionEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;
/*     */ import nonamecrackers2.witherstormmod.common.capability.PlayerWitherStormData;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WitherStormEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onLivingDeath(LivingDeathEvent event) {
/*  44 */     LivingEntity entity = event.getEntity();
/*  45 */     LivingEntity attacker = entity.m_21232_();
/*  46 */     if (!(entity.m_9236_()).f_46443_) {
/*     */       
/*  48 */       boolean flag = false;
/*  49 */       if (attacker instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)attacker;
/*     */         
/*  51 */         if (ForgeEventFactory.getMobGriefingEvent(entity.m_9236_(), (Entity)entity)) {
/*     */           
/*  53 */           BlockPos pos = BlockPos.m_274446_((Position)entity.m_20182_());
/*  54 */           BlockState state = Blocks.f_50070_.m_49966_();
/*  55 */           if (entity.m_9236_().m_46859_(pos) && state.m_60710_((LevelReader)entity.m_9236_(), pos)) {
/*     */             
/*  57 */             entity.m_9236_().m_7731_(pos, state, 3);
/*  58 */             flag = true;
/*     */           } 
/*     */         } 
/*     */         
/*  62 */         if (!flag) {
/*     */           
/*  64 */           ItemEntity item = new ItemEntity(entity.m_9236_(), entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), new ItemStack((ItemLike)Items.f_41951_));
/*  65 */           entity.m_9236_().m_7967_((Entity)item);
/*     */         } 
/*     */ 
/*     */         
/*  69 */         if (entity instanceof ServerPlayer) { ServerPlayer player = (ServerPlayer)entity;
/*     */           
/*  71 */           player.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(data -> data.setKilledByStorm(storm.m_20148_())); }
/*     */          }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
/*  82 */     Player player = event.getEntity(); if (player instanceof ServerPlayer) { ServerPlayer serverPlayer = (ServerPlayer)player; if (!event.isEndConquered())
/*     */       {
/*  84 */         serverPlayer.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(data -> {
/*     */               UUID id = data.getKilledByStorm();
/*     */               if (((Boolean)WitherStormModConfig.SERVER.preventWitherStormCamping.get()).booleanValue() && id != null) {
/*     */                 Entity patt3648$temp = player.m_284548_().m_8791_(id);
/*     */                 if (patt3648$temp instanceof WitherStormEntity) {
/*     */                   WitherStormEntity storm = (WitherStormEntity)patt3648$temp;
/*     */                   if (storm.getPhase() > 3) {
/*     */                     if (storm.m_20270_((Entity)player) < 300.0D) {
/*     */                       ServerLevel level = player.m_284548_();
/*     */                       for (int i = 0; i < 10; i++) {
/*     */                         float angle = storm.m_217043_().m_188501_() * 6.2831855F;
/*     */                         int x = (int)(Mth.m_14089_(angle) * (storm.m_217043_().m_188503_(200) + 300.0F)) + storm.m_146903_();
/*     */                         int z = (int)(Mth.m_14031_(angle) * (storm.m_217043_().m_188503_(200) + 300.0F)) + storm.m_146907_();
/*     */                         level.m_46745_(new BlockPos(x, 0, z));
/*     */                         int y = level.m_6924_(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);
/*     */                         BlockPos pos = new BlockPos(x, y, z);
/*     */                         if (Level.m_46741_(pos)) {
/*     */                           Vec3 vec = Player.m_36130_(level, pos, 0.0F, true, true).orElse(null);
/*     */                           if (vec != null) {
/*     */                             player.m_7618_(EntityAnchorArgument.Anchor.EYES, vec);
/*     */                             player.m_6027_(vec.f_82479_, vec.f_82480_, vec.f_82481_);
/*     */                             break;
/*     */                           } 
/*     */                         } 
/*     */                       } 
/*     */                     } 
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */               data.setKilledByStorm(null);
/*     */             });
/*     */       } }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onExplosionDetonate(ExplosionEvent.Detonate event) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual getLevel : ()Lnet/minecraft/world/level/Level;
/*     */     //   4: astore_1
/*     */     //   5: aload_1
/*     */     //   6: getfield f_46443_ : Z
/*     */     //   9: ifne -> 363
/*     */     //   12: aload_0
/*     */     //   13: invokevirtual getExplosion : ()Lnet/minecraft/world/level/Explosion;
/*     */     //   16: invokevirtual getExploder : ()Lnet/minecraft/world/entity/Entity;
/*     */     //   19: astore_2
/*     */     //   20: aload_2
/*     */     //   21: ifnull -> 363
/*     */     //   24: aload_2
/*     */     //   25: instanceof nonamecrackers2/witherstormmod/common/entity/WitherStormEntity
/*     */     //   28: ifne -> 363
/*     */     //   31: aload_1
/*     */     //   32: ldc nonamecrackers2/witherstormmod/common/entity/WitherStormEntity
/*     */     //   34: getstatic net/minecraft/world/entity/ai/targeting/TargetingConditions.f_26872_ : Lnet/minecraft/world/entity/ai/targeting/TargetingConditions;
/*     */     //   37: aconst_null
/*     */     //   38: aload_2
/*     */     //   39: invokevirtual m_20191_ : ()Lnet/minecraft/world/phys/AABB;
/*     */     //   42: ldc2_w 100.0
/*     */     //   45: invokevirtual m_82400_ : (D)Lnet/minecraft/world/phys/AABB;
/*     */     //   48: invokevirtual m_45971_ : (Ljava/lang/Class;Lnet/minecraft/world/entity/ai/targeting/TargetingConditions;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/phys/AABB;)Ljava/util/List;
/*     */     //   51: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   56: astore_3
/*     */     //   57: aload_3
/*     */     //   58: invokeinterface hasNext : ()Z
/*     */     //   63: ifeq -> 363
/*     */     //   66: aload_3
/*     */     //   67: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   72: checkcast net/minecraft/world/entity/Entity
/*     */     //   75: astore #4
/*     */     //   77: aload #4
/*     */     //   79: instanceof nonamecrackers2/witherstormmod/common/entity/WitherStormEntity
/*     */     //   82: ifeq -> 360
/*     */     //   85: aload #4
/*     */     //   87: checkcast nonamecrackers2/witherstormmod/common/entity/WitherStormEntity
/*     */     //   90: astore #5
/*     */     //   92: aload #5
/*     */     //   94: invokevirtual getHeadManager : ()Lnonamecrackers2/witherstormmod/common/entity/ai/witherstorm/head/HeadManager;
/*     */     //   97: invokevirtual getHeads : ()Ljava/util/List;
/*     */     //   100: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   105: astore #6
/*     */     //   107: aload #6
/*     */     //   109: invokeinterface hasNext : ()Z
/*     */     //   114: ifeq -> 360
/*     */     //   117: aload #6
/*     */     //   119: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   124: checkcast nonamecrackers2/witherstormmod/common/entity/ai/witherstorm/head/WitherStormHead
/*     */     //   127: astore #7
/*     */     //   129: aload #5
/*     */     //   131: aload #7
/*     */     //   133: invokevirtual getIndex : ()I
/*     */     //   136: invokevirtual tractorBeamActive : (I)Z
/*     */     //   139: ifeq -> 357
/*     */     //   142: aload_2
/*     */     //   143: instanceof net/minecraft/world/entity/item/PrimedTnt
/*     */     //   146: ifeq -> 239
/*     */     //   149: aload_2
/*     */     //   150: instanceof nonamecrackers2/witherstormmod/common/entity/FormidibombEntity
/*     */     //   153: ifne -> 239
/*     */     //   156: aload #5
/*     */     //   158: aload #7
/*     */     //   160: invokevirtual getIndex : ()I
/*     */     //   163: getstatic nonamecrackers2/witherstormmod/api/common/entity/WitherStormBase$DistractionType.ENTITY_BASED : Lnonamecrackers2/witherstormmod/api/common/entity/WitherStormBase$DistractionType;
/*     */     //   166: invokevirtual canBeDistracted : (ILnonamecrackers2/witherstormmod/api/common/entity/WitherStormBase$DistractionType;)Z
/*     */     //   169: ifeq -> 239
/*     */     //   172: iconst_1
/*     */     //   173: aload #5
/*     */     //   175: aload_2
/*     */     //   176: invokevirtual m_20270_ : (Lnet/minecraft/world/entity/Entity;)F
/*     */     //   179: ldc_w 30.0
/*     */     //   182: fdiv
/*     */     //   183: invokestatic m_14143_ : (F)I
/*     */     //   186: invokestatic max : (II)I
/*     */     //   189: istore #8
/*     */     //   191: aload #7
/*     */     //   193: aload_2
/*     */     //   194: invokevirtual canSee : (Lnet/minecraft/world/entity/Entity;)Z
/*     */     //   197: ifeq -> 239
/*     */     //   200: aload #5
/*     */     //   202: invokevirtual m_217043_ : ()Lnet/minecraft/util/RandomSource;
/*     */     //   205: iload #8
/*     */     //   207: invokeinterface m_188503_ : (I)I
/*     */     //   212: ifne -> 239
/*     */     //   215: aload #7
/*     */     //   217: aload_2
/*     */     //   218: invokevirtual m_20182_ : ()Lnet/minecraft/world/phys/Vec3;
/*     */     //   221: aload #5
/*     */     //   223: invokevirtual m_217043_ : ()Lnet/minecraft/util/RandomSource;
/*     */     //   226: bipush #60
/*     */     //   228: invokeinterface m_188503_ : (I)I
/*     */     //   233: bipush #120
/*     */     //   235: iadd
/*     */     //   236: invokevirtual makeDistracted : (Lnet/minecraft/world/phys/Vec3;I)V
/*     */     //   239: aload_0
/*     */     //   240: invokevirtual getExplosion : ()Lnet/minecraft/world/level/Explosion;
/*     */     //   243: invokevirtual getExploder : ()Lnet/minecraft/world/entity/Entity;
/*     */     //   246: astore #8
/*     */     //   248: aload #8
/*     */     //   250: ifnull -> 278
/*     */     //   253: aload #8
/*     */     //   255: instanceof net/minecraft/world/entity/projectile/Projectile
/*     */     //   258: ifeq -> 278
/*     */     //   261: aload #8
/*     */     //   263: checkcast net/minecraft/world/entity/projectile/Projectile
/*     */     //   266: astore #9
/*     */     //   268: aload #9
/*     */     //   270: invokevirtual m_19749_ : ()Lnet/minecraft/world/entity/Entity;
/*     */     //   273: aload #5
/*     */     //   275: if_acmpeq -> 357
/*     */     //   278: aload #7
/*     */     //   280: invokevirtual getHeadPos : ()Lnet/minecraft/world/phys/Vec3;
/*     */     //   283: astore #10
/*     */     //   285: aload_0
/*     */     //   286: invokevirtual getExplosion : ()Lnet/minecraft/world/level/Explosion;
/*     */     //   289: invokevirtual getPosition : ()Lnet/minecraft/world/phys/Vec3;
/*     */     //   292: aload #10
/*     */     //   294: invokevirtual m_82554_ : (Lnet/minecraft/world/phys/Vec3;)D
/*     */     //   297: aload #5
/*     */     //   299: invokevirtual getPhase : ()I
/*     */     //   302: iconst_4
/*     */     //   303: if_icmpge -> 312
/*     */     //   306: ldc2_w 5.0
/*     */     //   309: goto -> 315
/*     */     //   312: ldc2_w 12.0
/*     */     //   315: dcmpg
/*     */     //   316: ifge -> 357
/*     */     //   319: aload #5
/*     */     //   321: invokevirtual isDeadOrPlayingDead : ()Z
/*     */     //   324: ifne -> 357
/*     */     //   327: aload #7
/*     */     //   329: invokevirtual isHeadInjured : ()Z
/*     */     //   332: ifne -> 357
/*     */     //   335: aload #7
/*     */     //   337: invokevirtual checkAndCountAttack : ()Z
/*     */     //   340: ifeq -> 357
/*     */     //   343: aload #7
/*     */     //   345: aload_2
/*     */     //   346: aload #5
/*     */     //   348: invokevirtual getHeadManager : ()Lnonamecrackers2/witherstormmod/common/entity/ai/witherstorm/head/HeadManager;
/*     */     //   351: invokevirtual getHeadInjuryTime : ()I
/*     */     //   354: invokevirtual hurt : (Lnet/minecraft/world/entity/Entity;I)V
/*     */     //   357: goto -> 107
/*     */     //   360: goto -> 57
/*     */     //   363: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #121	-> 0
/*     */     //   #122	-> 5
/*     */     //   #124	-> 12
/*     */     //   #125	-> 20
/*     */     //   #127	-> 31
/*     */     //   #129	-> 77
/*     */     //   #131	-> 85
/*     */     //   #132	-> 92
/*     */     //   #134	-> 129
/*     */     //   #136	-> 142
/*     */     //   #138	-> 156
/*     */     //   #140	-> 172
/*     */     //   #141	-> 191
/*     */     //   #142	-> 215
/*     */     //   #145	-> 239
/*     */     //   #146	-> 248
/*     */     //   #148	-> 278
/*     */     //   #149	-> 285
/*     */     //   #151	-> 319
/*     */     //   #152	-> 343
/*     */     //   #156	-> 357
/*     */     //   #158	-> 360
/*     */     //   #161	-> 363
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   191	48	8	chance	I
/*     */     //   268	10	9	projectile	Lnet/minecraft/world/entity/projectile/Projectile;
/*     */     //   285	72	10	headPos	Lnet/minecraft/world/phys/Vec3;
/*     */     //   248	109	8	exploder	Lnet/minecraft/world/entity/Entity;
/*     */     //   129	228	7	head	Lnonamecrackers2/witherstormmod/common/entity/ai/witherstorm/head/WitherStormHead;
/*     */     //   92	268	5	storm	Lnonamecrackers2/witherstormmod/common/entity/WitherStormEntity;
/*     */     //   77	283	4	entity	Lnet/minecraft/world/entity/Entity;
/*     */     //   20	343	2	source	Lnet/minecraft/world/entity/Entity;
/*     */     //   0	364	0	event	Lnet/minecraftforge/event/level/ExplosionEvent$Detonate;
/*     */     //   5	359	1	world	Lnet/minecraft/world/level/Level;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityMount(EntityMountEvent event) {
/* 166 */     if (event.getEntityMounting() instanceof WitherStormEntity) {
/* 167 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityUseItem(LivingEntityUseItemEvent.Start event) {
/* 173 */     ItemStack item = event.getItem();
/* 174 */     if (item.m_150930_(Items.f_220219_))
/*     */     {
/* 176 */       for (WitherStormEntity storm : event.getEntity().m_9236_().m_45976_(WitherStormEntity.class, event.getEntity().m_20191_().m_82377_(128.0D, 256.0D, 128.0D))) {
/*     */         
/* 178 */         if (!storm.isDeadOrPlayingDead())
/*     */         {
/* 180 */           for (WitherStormHead head : storm.getHeadManager().getHeads()) {
/*     */             
/* 182 */             if (storm.tractorBeamActive(head.getIndex()) && storm.canBeDistracted(head.getIndex(), WitherStormBase.DistractionType.ENTITY_BASED))
/*     */             {
/* 184 */               if (storm.m_217043_().m_188503_(3) == 0)
/* 185 */                 head.makeDistracted(event.getEntity().m_146892_(), 80); 
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\event\WitherStormEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
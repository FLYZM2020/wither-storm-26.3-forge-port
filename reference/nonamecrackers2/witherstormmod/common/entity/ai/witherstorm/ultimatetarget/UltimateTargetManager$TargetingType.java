/*      */ package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget;
/*      */ 
/*      */ import java.util.Arrays;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import java.util.function.Predicate;
/*      */ import javax.annotation.Nullable;
/*      */ import net.minecraft.server.level.ServerPlayer;
/*      */ import net.minecraft.world.entity.Entity;
/*      */ import net.minecraft.world.entity.EntitySelector;
/*      */ import net.minecraft.world.phys.AABB;
/*      */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*      */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public enum TargetingType
/*      */ {
/*  869 */   NEAREST
/*      */   {
/*      */     @Nullable
/*      */     public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate)
/*      */     {
/*  874 */       double d0 = -1.0D;
/*  875 */       ServerPlayer closest = null;
/*  876 */       for (ServerPlayer player : players) {
/*      */         
/*  878 */         if (predicate.test(player)) {
/*      */           
/*  880 */           double d1 = player.m_20280_((Entity)storm);
/*  881 */           if (d1 < d0 || d0 == -1.0D) {
/*      */             
/*  883 */             d0 = player.m_20280_((Entity)storm);
/*  884 */             closest = player;
/*      */           } 
/*      */         } 
/*      */       } 
/*  888 */       return closest;
/*      */     }
/*      */   },
/*  891 */   FARTHEST
/*      */   {
/*      */     @Nullable
/*      */     public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate)
/*      */     {
/*  896 */       long currentTime = System.currentTimeMillis();
/*  897 */       long timeThreshold = (((Integer)WitherStormModConfig.SERVER.farthestTargetingTime.get()).intValue() * 60 * 1000);
/*  898 */       ServerPlayer farthestPlayer = (manager.farthestPlayer != null) ? (ServerPlayer)storm.m_9236_().m_46003_(manager.farthestPlayer) : null;
/*  899 */       if (currentTime - manager.farthestLastSwitchTime >= timeThreshold || farthestPlayer == null) {
/*      */         
/*  901 */         double d0 = -1.0D;
/*      */         
/*  903 */         for (ServerPlayer player : players) {
/*      */           
/*  905 */           if (predicate.test(player)) {
/*      */             
/*  907 */             double d1 = player.m_20280_((Entity)storm);
/*  908 */             if (d1 > d0 || d0 == -1.0D) {
/*      */               
/*  910 */               d0 = player.m_20280_((Entity)storm);
/*  911 */               manager.farthestPlayer = player.m_20148_();
/*  912 */               farthestPlayer = player;
/*  913 */               UltimateTargetManager.LOGGER.info("FARTHEST: Farthest Player was " + player + ", Going to them for " + WitherStormModConfig.SERVER.farthestTargetingTime.get() + " minute(s)");
/*      */             } 
/*      */           } 
/*      */         } 
/*  917 */         manager.farthestLastSwitchTime = currentTime;
/*      */       } 
/*  919 */       return farthestPlayer;
/*      */     }
/*      */   },
/*  922 */   GROUP
/*      */   {
/*      */     @Nullable
/*      */     public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate)
/*      */     {
/*  927 */       double size = -1.0D;
/*  928 */       ServerPlayer toTarget = null;
/*  929 */       for (ServerPlayer player : players) {
/*      */         
/*  931 */         if (predicate.test(player)) {
/*      */           
/*  933 */           AABB box = player.m_20191_().m_82400_(20.0D);
/*  934 */           List<ServerPlayer> nearby = storm.m_9236_().m_6443_(ServerPlayer.class, box, EntitySelector.f_20403_.and(EntitySelector.f_20408_));
/*  935 */           if (nearby.size() > size) {
/*      */             
/*  937 */             size = nearby.size();
/*  938 */             toTarget = player;
/*      */           } 
/*      */         } 
/*      */       } 
/*  942 */       return toTarget;
/*      */     }
/*      */   },
/*  945 */   NONE
/*      */   {
/*      */     @Nullable
/*      */     public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate)
/*      */     {
/*  950 */       return null;
/*      */     }
/*      */   },
/*  953 */   RANDOM_STROLL
/*      */   {
/*      */     @Nullable
/*      */     public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate)
/*      */     {
/*  958 */       return null;
/*      */     }
/*      */   },
/*      */   
/*  962 */   RANDOM_PLAYER
/*      */   {
/*      */     @Nullable
/*      */     public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate)
/*      */     {
/*  967 */       long currentTime = System.currentTimeMillis();
/*  968 */       long timeThreshold = 300000L;
/*  969 */       ServerPlayer randomPlayer = (manager.randomPlayer != null) ? (ServerPlayer)storm.m_9236_().m_46003_(manager.randomPlayer) : null;
/*  970 */       if (currentTime - manager.randomPlayerLastSwitchTime >= timeThreshold || randomPlayer == null) {
/*      */         
/*  972 */         Random random = new Random();
/*      */ 
/*      */ 
/*      */         
/*  976 */         List<ServerPlayer> survivalPlayers = players.stream().filter(player -> player.f_8941_.m_9294_()).filter(predicate).toList();
/*  977 */         if (!survivalPlayers.isEmpty()) {
/*      */           
/*  979 */           int randomizer = random.nextInt(survivalPlayers.size());
/*  980 */           randomPlayer = survivalPlayers.get(randomizer);
/*  981 */           manager.randomPlayer = randomPlayer.m_20148_();
/*  982 */           UltimateTargetManager.LOGGER.info("RANDOM_PLAYER: Chose a player: " + randomPlayer + ", Going to them for 5 minutes");
/*      */         }
/*      */         else {
/*      */           
/*  986 */           ServerPlayer nearestPlayer = NEAREST.getPlayer(manager, storm, players, predicate);
/*  987 */           if (nearestPlayer != null) {
/*      */             
/*  989 */             manager.randomPlayer = nearestPlayer.m_20148_();
/*  990 */             randomPlayer = nearestPlayer;
/*  991 */             UltimateTargetManager.LOGGER.info("RANDOM_PLAYER: Couldn't find a valid player in Survival, changing to NEAREST for 5 minutes");
/*      */           } 
/*      */         } 
/*  994 */         manager.randomPlayerLastSwitchTime = currentTime;
/*      */       } 
/*  996 */       return randomPlayer;
/*      */     }
/*      */   },
/*  999 */   RANDOMIZED
/*      */   {
/*      */     @Nullable
/*      */     public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate)
/*      */     {
/* 1004 */       long currentTime = System.currentTimeMillis();
/* 1005 */       if (currentTime - manager.randomizedLastSwitchTime >= (((Integer)WitherStormModConfig.SERVER.randomizedTargetingTime.get()).intValue() * 60 * 1000)) {
/*      */         List<TargetingType> availableTypes;
/*      */         
/* 1008 */         if (players.size() <= 1) {
/* 1009 */           availableTypes = Arrays.asList(new TargetingType[] { NEAREST, RANDOM_STROLL, RANDOM_STROLL_NEAR_PLAYER });
/*      */         } else {
/* 1011 */           availableTypes = Arrays.asList(new TargetingType[] { NEAREST, FARTHEST, GROUP, RANDOM_STROLL, RANDOM_PLAYER });
/* 1012 */         }  TargetingType lastType = manager.randomizedType;
/*      */         
/*      */         do {
/* 1015 */           manager.randomizedType = availableTypes.get(storm.m_217043_().m_188503_(availableTypes.size()));
/*      */         }
/* 1017 */         while (manager.randomizedType == lastType);
/*      */         
/* 1019 */         manager.randomizedLastSwitchTime = currentTime;
/* 1020 */         UltimateTargetManager.LOGGER.info("RANDOMIZED: Targeting Type has been set to: " + manager.randomizedType);
/* 1021 */         if (storm.m_217043_().m_216339_(1, 11) == 1 && storm.getPhase() >= 4 && ((Boolean)WitherStormModConfig.SERVER.randomlySpeedUpWithTargetChange.get()).booleanValue()) {
/*      */           
/* 1023 */           manager.accelerate();
/* 1024 */           UltimateTargetManager.LOGGER.info("RANDOMIZED: The Wither Storm will be speeding up!!!");
/*      */         } 
/*      */       } 
/* 1027 */       assert manager.randomizedType != null;
/* 1028 */       return manager.randomizedType.getPlayer(manager, storm, players, predicate);
/*      */     }
/*      */   },
/* 1031 */   RANDOM_STROLL_NEAR_PLAYER
/*      */   {
/*      */     @Nullable
/*      */     public ServerPlayer getPlayer(UltimateTargetManager manager, WitherStormEntity storm, List<ServerPlayer> players, Predicate<Entity> predicate)
/*      */     {
/* 1036 */       return null;
/*      */     }
/*      */   };
/*      */   
/*      */   @Nullable
/*      */   public abstract ServerPlayer getPlayer(UltimateTargetManager paramUltimateTargetManager, WitherStormEntity paramWitherStormEntity, List<ServerPlayer> paramList, Predicate<Entity> paramPredicate);
/*      */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\entity\ai\witherstor\\ultimatetarget\UltimateTargetManager$TargetingType.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package nonamecrackers2.witherstormmod.common.item;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.NbtUtils;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResult;
/*     */ import net.minecraft.world.InteractionResultHolder;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.TooltipFlag;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCriteriaTriggers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*     */ 
/*     */ public class AmuletItem extends Item {
/*     */   private static final Predicate<Entity> BINDABLE;
/*     */   
/*     */   static {
/*  39 */     BINDABLE = (entity -> (entity instanceof net.minecraft.world.entity.Mob || entity instanceof Player));
/*     */   }
/*     */   public static final String TRACKING_BLUE = "TrackingBlue"; public static final String TRACKING_AQUA = "TrackingAqua";
/*     */   public static final String TRACKING_GREEN = "TrackingGreen";
/*     */   public static final String TRACKING_GRAY = "TrackingGray";
/*     */   public static final String TRACKING_RED = "TrackingRed";
/*  45 */   public static final String[] TRACKING = new String[] { "TrackingBlue", "TrackingAqua", "TrackingGreen", "TrackingGray", "TrackingRed" };
/*     */   
/*     */   public static final String SELECTED_INDEX = "SelectedIndex";
/*     */   public static final String LOCKED = "Locked";
/*     */   public static final String TRACK_ENTITY_TYPES = "TrackEntityTypes";
/*     */   public static final int DEFAULT_SCAN_DISTANCE = 1000;
/*     */   
/*     */   public AmuletItem(Item.Properties properties) {
/*  53 */     super(properties);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_6883_(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
/*  59 */     if (level instanceof ServerLevel) { ServerLevel serverLevel = (ServerLevel)level; if (entity instanceof Player) { Player player = (Player)entity;
/*     */         
/*  61 */         CompoundTag tag = stack.m_41784_();
/*  62 */         if (tag.m_128451_("SelectedIndex") <= 0)
/*  63 */           tag.m_128405_("SelectedIndex", 1); 
/*  64 */         for (String id : TRACKING) {
/*     */           
/*  66 */           if (id.equals("TrackingBlue")) {
/*     */             
/*  68 */             List<WitherStormEntity> storms = Lists.newArrayList();
/*  69 */             for (Entity e : serverLevel.m_8583_()) {
/*     */               
/*  71 */               if (e instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)e; if (!(e instanceof nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity))
/*  72 */                   storms.add(storm);  }
/*     */             
/*  74 */             }  WitherStormEntity nearest = (WitherStormEntity)WorldUtil.getNearest(storms, player.m_20182_(), Entity::m_20182_);
/*  75 */             if (nearest != null)
/*     */             {
/*  77 */               tag.m_128359_(id + "Type", ForgeRegistries.ENTITY_TYPES.getKey(nearest.m_6095_()).toString());
/*  78 */               tag.m_128362_(id, nearest.m_20148_());
/*  79 */               tag.m_128405_(id + "Dist", (int)player.m_20270_((Entity)nearest));
/*  80 */               tag.m_128359_(id + "Name", nearest.m_5446_().getString());
/*  81 */               tag.m_128365_(id + "Pos", (Tag)NbtUtils.m_129224_(nearest.m_20183_()));
/*     */             }
/*     */             else
/*     */             {
/*  85 */               tag.m_128405_(id + "Dist", -1);
/*     */             
/*     */             }
/*     */           
/*     */           }
/*  90 */           else if (tag.m_128441_(id)) {
/*  91 */             saveDistFor(serverLevel, tag, player, tag.m_128342_(id), id);
/*     */           } else {
/*  93 */             tag.m_128405_(id + "Dist", -1);
/*     */           } 
/*     */         }  }
/*     */        }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public InteractionResultHolder<ItemStack> m_7203_(Level level, Player player, InteractionHand hand) {
/* 102 */     ItemStack stack = player.m_21120_(hand);
/* 103 */     if (!level.f_46443_ && player.m_6144_())
/*     */     {
/* 105 */       if (stack.m_41720_() instanceof AmuletItem) {
/*     */         
/* 107 */         CompoundTag tag = stack.m_41784_();
/* 108 */         int index = tag.m_128451_("SelectedIndex");
/* 109 */         index++;
/* 110 */         if (index < TRACKING.length) {
/* 111 */           tag.m_128405_("SelectedIndex", index);
/*     */         } else {
/* 113 */           tag.m_128405_("SelectedIndex", 1);
/* 114 */         }  player.m_6330_((SoundEvent)WitherStormModSoundEvents.AMULET_SWAPS.get(), SoundSource.PLAYERS, 1.0F, 2.0F);
/*     */       } 
/*     */     }
/* 117 */     return InteractionResultHolder.m_19098_(stack);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InteractionResult m_6880_(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
/* 123 */     if (BINDABLE.test(entity) && !(entity instanceof WitherStormEntity) && !player.m_6144_()) {
/*     */       
/* 125 */       ItemStack item = player.m_21120_(hand);
/* 126 */       if (item.m_41720_() instanceof AmuletItem) {
/*     */         
/* 128 */         CompoundTag tag = item.m_41784_();
/* 129 */         if (!tag.m_128471_("Locked")) {
/*     */           
/* 131 */           int index = tag.m_128451_("SelectedIndex");
/* 132 */           if (index >= 1 && index < TRACKING.length) {
/*     */             
/* 134 */             if (!(player.m_9236_()).f_46443_) {
/*     */               
/* 136 */               String id = TRACKING[index];
/* 137 */               if (!tag.m_128441_(id) || !tag.m_128342_(id).equals(entity.m_20148_())) {
/*     */                 
/* 139 */                 tag.m_128359_(id + "Type", ForgeRegistries.ENTITY_TYPES.getKey(entity.m_6095_()).toString());
/* 140 */                 tag.m_128362_(id, entity.m_20148_());
/* 141 */                 player.m_6330_((SoundEvent)WitherStormModSoundEvents.AMULET_BIND.get(), SoundSource.PLAYERS, 1.0F, 0.0F);
/* 142 */                 if (player instanceof ServerPlayer) { ServerPlayer serverPlayer = (ServerPlayer)player;
/* 143 */                   WitherStormModCriteriaTriggers.LINK_AMULET.trigger(serverPlayer, (Entity)entity, getTotalUniqueLinked(stack)); }
/*     */               
/*     */               } else {
/*     */                 
/* 147 */                 tag.m_128473_(id);
/* 148 */                 tag.m_128473_(id + "Type");
/* 149 */                 tag.m_128473_(id + "Name");
/* 150 */                 tag.m_128473_(id + "Pos");
/* 151 */                 tag.m_128405_(id + "Dist", -1);
/* 152 */                 player.m_6330_((SoundEvent)WitherStormModSoundEvents.AMULET_UNBIND.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/*     */               } 
/*     */             } 
/* 155 */             return InteractionResult.m_19078_((player.m_9236_()).f_46443_);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 160 */     return InteractionResult.PASS;
/*     */   }
/*     */ 
/*     */   
/*     */   private void saveDistFor(ServerLevel level, CompoundTag tag, Player player, UUID uuid, String id) {
/* 165 */     Entity tracking = null;
/* 166 */     if (tag.m_128471_("TrackEntityTypes")) {
/*     */       
/* 168 */       EntityType<?> type = (EntityType)ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(tag.m_128461_(id + "Type")));
/* 169 */       List<Entity> entities = level.m_6443_(Entity.class, player.m_20191_().m_82400_(500.0D), e -> (e.m_6095_().equals(type) && e != player));
/* 170 */       tracking = (Entity)WorldUtil.getNearest(entities, player.m_20182_(), Entity::m_20182_);
/*     */     }
/* 172 */     else if (tag.m_128441_(id)) {
/*     */       
/* 174 */       tracking = level.m_8791_(tag.m_128342_(id));
/*     */     } 
/*     */     
/* 177 */     if (tracking != null) {
/*     */       
/* 179 */       tag.m_128405_(id + "Dist", (int)player.m_20270_(tracking));
/* 180 */       tag.m_128359_(id + "Name", tracking.m_5446_().getString());
/* 181 */       tag.m_128365_(id + "Pos", (Tag)NbtUtils.m_129224_(tracking.m_20183_()));
/*     */     }
/*     */     else {
/*     */       
/* 185 */       tag.m_128405_(id + "Dist", -1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7373_(ItemStack stack, Level level, List<Component> text, TooltipFlag flag) {
/* 192 */     super.m_7373_(stack, level, text, flag);
/* 193 */     CompoundTag tag = stack.m_41784_();
/* 194 */     boolean locked = tag.m_128471_("Locked");
/* 195 */     if (((Boolean)WitherStormModConfig.SERVER.amuletOverride.get()).booleanValue())
/* 196 */       text.add(Component.m_237115_("description.amulet.mainUse").m_130940_(ChatFormatting.DARK_GRAY)); 
/* 197 */     text.add(Component.m_237115_("description.amulet.trackingDesc").m_130940_(ChatFormatting.DARK_GRAY));
/* 198 */     text.add(Component.m_237115_("description.amulet.swap").m_130940_(ChatFormatting.DARK_GRAY));
/* 199 */     if (!locked)
/* 200 */       text.add(Component.m_237115_("description.amulet.bind").m_130940_(ChatFormatting.DARK_GRAY)); 
/* 201 */     text.add(Component.m_237110_("description.amulet.tracking", new Object[] { getTrackingName("TrackingBlue", tag), getDistString("TrackingBlue", tag) }).m_130940_(ChatFormatting.BLUE));
/* 202 */     text.add(Component.m_237110_("description.amulet.tracking", new Object[] { getTrackingName("TrackingAqua", tag), getDistString("TrackingAqua", tag) }).m_130940_(ChatFormatting.AQUA));
/* 203 */     text.add(Component.m_237110_("description.amulet.tracking", new Object[] { getTrackingName("TrackingGreen", tag), getDistString("TrackingGreen", tag) }).m_130940_(ChatFormatting.GREEN));
/* 204 */     text.add(Component.m_237110_("description.amulet.tracking", new Object[] { getTrackingName("TrackingGray", tag), getDistString("TrackingGray", tag) }).m_130940_(ChatFormatting.GRAY));
/* 205 */     text.add(Component.m_237110_("description.amulet.tracking", new Object[] { getTrackingName("TrackingRed", tag), getDistString("TrackingRed", tag) }).m_130940_(ChatFormatting.RED));
/* 206 */     if (locked)
/* 207 */       text.add(Component.m_237115_("description.amulet.locked").m_130940_(ChatFormatting.YELLOW)); 
/* 208 */     if (tag.m_128471_("TrackEntityTypes")) {
/* 209 */       text.add(Component.m_237115_("description.amulet.tracksEntityTypes").m_130940_(ChatFormatting.GOLD));
/*     */     }
/*     */   }
/*     */   
/*     */   private static String getDistString(String id, CompoundTag tag) {
/* 214 */     int dist = tag.m_128451_(id + "Dist");
/* 215 */     if (dist >= 0)
/* 216 */       return String.valueOf(dist); 
/* 217 */     if (tag.m_128441_(id + "Name")) {
/* 218 */       return "Could not find nearby";
/*     */     }
/* 220 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   private static String getTrackingName(String id, CompoundTag tag) {
/* 225 */     if (!tag.m_128441_(id + "Name")) {
/*     */       
/* 227 */       if (id == "TrackingBlue") {
/* 228 */         return "No Nearby Wither Storm";
/*     */       }
/* 230 */       return "Empty";
/*     */     } 
/*     */ 
/*     */     
/* 234 */     return tag.m_128461_(id + "Name");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
/* 241 */     return slotChanged ? super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTotalUniqueLinked(ItemStack stack) {
/* 246 */     CompoundTag tag = stack.m_41784_();
/* 247 */     List<UUID> ids = Lists.newArrayList();
/* 248 */     for (int i = 0; i < TRACKING.length; i++) {
/*     */       
/* 250 */       String id = TRACKING[i];
/* 251 */       if (!id.equals("TrackingBlue") && tag.m_128441_(id)) {
/*     */         
/* 253 */         UUID uuid = tag.m_128342_(id);
/* 254 */         if (!ids.contains(uuid))
/* 255 */           ids.add(uuid); 
/*     */       } 
/*     */     } 
/* 258 */     return ids.size();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldCauseBlockBreakReset(ItemStack oldStack, ItemStack newStack) {
/* 264 */     if (!newStack.m_150930_(oldStack.m_41720_())) {
/* 265 */       return true;
/*     */     }
/* 267 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\AmuletItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
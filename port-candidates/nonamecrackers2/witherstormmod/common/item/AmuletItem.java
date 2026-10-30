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
/*     */ import net.minecraft.resources.Identifier;
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
/*     */   public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
/*  59 */     if (level instanceof ServerLevel) { ServerLevel serverLevel = (ServerLevel)level; if (entity instanceof Player) { Player player = (Player)entity;
/*     */         
/*  61 */         CompoundTag tag = stack.getOrCreateTag();
/*  62 */         if (tag.getInt("SelectedIndex") <= 0)
/*  63 */           tag.putInt("SelectedIndex", 1); 
/*  64 */         for (String id : TRACKING) {
/*     */           
/*  66 */           if (id.equals("TrackingBlue")) {
/*     */             
/*  68 */             List<WitherStormEntity> storms = Lists.newArrayList();
/*  69 */             for (Entity e : serverLevel.getAllEntities()) {
/*     */               
/*  71 */               if (e instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)e; if (!(e instanceof nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity))
/*  72 */                   storms.add(storm);  }
/*     */             
/*  74 */             }  WitherStormEntity nearest = (WitherStormEntity)WorldUtil.getNearest(storms, player.position(), Entity::position);
/*  75 */             if (nearest != null)
/*     */             {
/*  77 */               tag.putString(id + "Type", ForgeRegistries.ENTITY_TYPES.getKey(nearest.getType()).toString());
/*  78 */               tag.putUUID(id, nearest.getUUID());
/*  79 */               tag.putInt(id + "Dist", (int)player.distanceTo((Entity)nearest));
/*  80 */               tag.putString(id + "Name", nearest.getDisplayName().getString());
/*  81 */               tag.put(id + "Pos", (Tag)NbtUtils.writeBlockPos(nearest.blockPosition()));
/*     */             }
/*     */             else
/*     */             {
/*  85 */               tag.putInt(id + "Dist", -1);
/*     */             
/*     */             }
/*     */           
/*     */           }
/*  90 */           else if (tag.contains(id)) {
/*  91 */             saveDistFor(serverLevel, tag, player, tag.getUUID(id), id);
/*     */           } else {
/*  93 */             tag.putInt(id + "Dist", -1);
/*     */           } 
/*     */         }  }
/*     */        }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
/* 102 */     ItemStack stack = player.getItemInHand(hand);
/* 103 */     if (!level.isClientSide && player.isShiftKeyDown())
/*     */     {
/* 105 */       if (stack.getItem() instanceof AmuletItem) {
/*     */         
/* 107 */         CompoundTag tag = stack.getOrCreateTag();
/* 108 */         int index = tag.getInt("SelectedIndex");
/* 109 */         index++;
/* 110 */         if (index < TRACKING.length) {
/* 111 */           tag.putInt("SelectedIndex", index);
/*     */         } else {
/* 113 */           tag.putInt("SelectedIndex", 1);
/* 114 */         }  player.playNotifySound((SoundEvent)WitherStormModSoundEvents.AMULET_SWAPS.get(), SoundSource.PLAYERS, 1.0F, 2.0F);
/*     */       } 
/*     */     }
/* 117 */     return InteractionResultHolder.pass(stack);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
/* 123 */     if (BINDABLE.test(entity) && !(entity instanceof WitherStormEntity) && !player.isShiftKeyDown()) {
/*     */       
/* 125 */       ItemStack item = player.getItemInHand(hand);
/* 126 */       if (item.getItem() instanceof AmuletItem) {
/*     */         
/* 128 */         CompoundTag tag = item.getOrCreateTag();
/* 129 */         if (!tag.getBoolean("Locked")) {
/*     */           
/* 131 */           int index = tag.getInt("SelectedIndex");
/* 132 */           if (index >= 1 && index < TRACKING.length) {
/*     */             
/* 134 */             if (!(player.level()).isClientSide) {
/*     */               
/* 136 */               String id = TRACKING[index];
/* 137 */               if (!tag.contains(id) || !tag.getUUID(id).equals(entity.getUUID())) {
/*     */                 
/* 139 */                 tag.putString(id + "Type", ForgeRegistries.ENTITY_TYPES.getKey(entity.getType()).toString());
/* 140 */                 tag.putUUID(id, entity.getUUID());
/* 141 */                 player.playNotifySound((SoundEvent)WitherStormModSoundEvents.AMULET_BIND.get(), SoundSource.PLAYERS, 1.0F, 0.0F);
/* 142 */                 if (player instanceof ServerPlayer) { ServerPlayer serverPlayer = (ServerPlayer)player;
/* 143 */                   WitherStormModCriteriaTriggers.LINK_AMULET.trigger(serverPlayer, (Entity)entity, getTotalUniqueLinked(stack)); }
/*     */               
/*     */               } else {
/*     */                 
/* 147 */                 tag.remove(id);
/* 148 */                 tag.remove(id + "Type");
/* 149 */                 tag.remove(id + "Name");
/* 150 */                 tag.remove(id + "Pos");
/* 151 */                 tag.putInt(id + "Dist", -1);
/* 152 */                 player.playNotifySound((SoundEvent)WitherStormModSoundEvents.AMULET_UNBIND.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/*     */               } 
/*     */             } 
/* 155 */             return InteractionResult.sidedSuccess((player.level()).isClientSide);
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
/* 166 */     if (tag.getBoolean("TrackEntityTypes")) {
/*     */       
/* 168 */       EntityType<?> type = (EntityType)ForgeRegistries.ENTITY_TYPES.getValue(Identifier.parse(tag.getString(id + "Type")));
/* 169 */       List<Entity> entities = level.getEntitiesOfClass(Entity.class, player.getBoundingBox().inflate(500.0D), e -> (e.getType().equals(type) && e != player));
/* 170 */       tracking = (Entity)WorldUtil.getNearest(entities, player.position(), Entity::position);
/*     */     }
/* 172 */     else if (tag.contains(id)) {
/*     */       
/* 174 */       tracking = level.getEntity(tag.getUUID(id));
/*     */     } 
/*     */     
/* 177 */     if (tracking != null) {
/*     */       
/* 179 */       tag.putInt(id + "Dist", (int)player.distanceTo(tracking));
/* 180 */       tag.putString(id + "Name", tracking.getDisplayName().getString());
/* 181 */       tag.put(id + "Pos", (Tag)NbtUtils.writeBlockPos(tracking.blockPosition()));
/*     */     }
/*     */     else {
/*     */       
/* 185 */       tag.putInt(id + "Dist", -1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendHoverText(ItemStack stack, Level level, List<Component> text, TooltipFlag flag) {
/* 192 */     super.appendHoverText(stack, level, text, flag);
/* 193 */     CompoundTag tag = stack.getOrCreateTag();
/* 194 */     boolean locked = tag.getBoolean("Locked");
/* 195 */     if (((Boolean)WitherStormModConfig.SERVER.amuletOverride.get()).booleanValue())
/* 196 */       text.add(Component.translatable("description.amulet.mainUse").withStyle(ChatFormatting.DARK_GRAY)); 
/* 197 */     text.add(Component.translatable("description.amulet.trackingDesc").withStyle(ChatFormatting.DARK_GRAY));
/* 198 */     text.add(Component.translatable("description.amulet.swap").withStyle(ChatFormatting.DARK_GRAY));
/* 199 */     if (!locked)
/* 200 */       text.add(Component.translatable("description.amulet.bind").withStyle(ChatFormatting.DARK_GRAY)); 
/* 201 */     text.add(Component.translatable("description.amulet.tracking", new Object[] { getTrackingName("TrackingBlue", tag), getDistString("TrackingBlue", tag) }).withStyle(ChatFormatting.BLUE));
/* 202 */     text.add(Component.translatable("description.amulet.tracking", new Object[] { getTrackingName("TrackingAqua", tag), getDistString("TrackingAqua", tag) }).withStyle(ChatFormatting.AQUA));
/* 203 */     text.add(Component.translatable("description.amulet.tracking", new Object[] { getTrackingName("TrackingGreen", tag), getDistString("TrackingGreen", tag) }).withStyle(ChatFormatting.GREEN));
/* 204 */     text.add(Component.translatable("description.amulet.tracking", new Object[] { getTrackingName("TrackingGray", tag), getDistString("TrackingGray", tag) }).withStyle(ChatFormatting.GRAY));
/* 205 */     text.add(Component.translatable("description.amulet.tracking", new Object[] { getTrackingName("TrackingRed", tag), getDistString("TrackingRed", tag) }).withStyle(ChatFormatting.RED));
/* 206 */     if (locked)
/* 207 */       text.add(Component.translatable("description.amulet.locked").withStyle(ChatFormatting.YELLOW)); 
/* 208 */     if (tag.getBoolean("TrackEntityTypes")) {
/* 209 */       text.add(Component.translatable("description.amulet.tracksEntityTypes").withStyle(ChatFormatting.GOLD));
/*     */     }
/*     */   }
/*     */   
/*     */   private static String getDistString(String id, CompoundTag tag) {
/* 214 */     int dist = tag.getInt(id + "Dist");
/* 215 */     if (dist >= 0)
/* 216 */       return String.valueOf(dist); 
/* 217 */     if (tag.contains(id + "Name")) {
/* 218 */       return "Could not find nearby";
/*     */     }
/* 220 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   private static String getTrackingName(String id, CompoundTag tag) {
/* 225 */     if (!tag.contains(id + "Name")) {
/*     */       
/* 227 */       if (id == "TrackingBlue") {
/* 228 */         return "No Nearby Wither Storm";
/*     */       }
/* 230 */       return "Empty";
/*     */     } 
/*     */ 
/*     */     
/* 234 */     return tag.getString(id + "Name");
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
/* 246 */     CompoundTag tag = stack.getOrCreateTag();
/* 247 */     List<UUID> ids = Lists.newArrayList();
/* 248 */     for (int i = 0; i < TRACKING.length; i++) {
/*     */       
/* 250 */       String id = TRACKING[i];
/* 251 */       if (!id.equals("TrackingBlue") && tag.contains(id)) {
/*     */         
/* 253 */         UUID uuid = tag.getUUID(id);
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
/* 264 */     if (!newStack.is(oldStack.getItem())) {
/* 265 */       return true;
/*     */     }
/* 267 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\AmuletItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
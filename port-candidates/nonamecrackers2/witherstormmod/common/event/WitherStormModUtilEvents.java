/*     */ package nonamecrackers2.witherstormmod.common.event;
/*     */ 
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.sounds.SoundSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EquipmentSlot;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.entity.projectile.Projectile;
/*     */ import net.minecraft.world.entity.projectile.ThrownPotion;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.alchemy.Potion;
/*     */ import net.minecraft.world.item.alchemy.PotionUtils;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.levelgen.structure.BoundingBox;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraft.world.phys.EntityHitResult;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import net.minecraftforge.event.ItemAttributeModifierEvent;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.event.entity.ProjectileImpactEvent;
/*     */ import net.minecraftforge.event.entity.player.AttackEntityEvent;
/*     */ import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
/*     */ import net.minecraftforge.network.PacketDistributor;
/*     */ import nonamecrackers2.witherstormmod.common.capability.WitherStormAutoSpawner;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
/*     */ import nonamecrackers2.witherstormmod.common.item.EyeOfTheStormItem;
/*     */ import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
/*     */ import nonamecrackers2.witherstormmod.common.world.tainting.WorldTainting;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WitherStormModUtilEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onProjectileImpact(ProjectileImpactEvent event) {
/*  44 */     if (event.getProjectile() instanceof net.minecraft.world.entity.projectile.FishingHook)
/*     */     
/*  46 */     { HitResult hitResult = event.getRayTraceResult(); if (hitResult instanceof EntityHitResult) { EntityHitResult hit = (EntityHitResult)hitResult; if (hit.getEntity() instanceof nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity || hit.getEntity() instanceof nonamecrackers2.witherstormmod.common.entity.WitherStormEntity)
/*  47 */           event.setImpactResult(ProjectileImpactEvent.ImpactResult.SKIP_ENTITY);  }
/*     */        }
/*  49 */     else if (!event.getProjectile().level().isClientSide()) { Projectile projectile = event.getProjectile(); if (projectile instanceof ThrownPotion) { ThrownPotion thrownPotion = (ThrownPotion)projectile; HitResult hitResult = event.getRayTraceResult(); if (hitResult instanceof BlockHitResult) { BlockHitResult hit = (BlockHitResult)hitResult;
/*     */           
/*  51 */           Potion potion = PotionUtils.getPotion(thrownPotion.getItem());
/*  52 */           BoundingBox box = (new BoundingBox(hit.getBlockPos())).inflatedBy(1);
/*  53 */           WorldTainting.getInstance().convertBlocks(box, thrownPotion.level(), potion); }
/*     */          }
/*     */        }
/*     */   
/*     */   }
/*     */   @SubscribeEvent
/*     */   public static void modifyItemAttributes(ItemAttributeModifierEvent event) {
/*  60 */     if (event.getSlotType() == EquipmentSlot.MAINHAND) {
/*     */       
/*  62 */       ItemStack stack = event.getItemStack();
/*  63 */       if (stack.is((Item)WitherStormModItems.EYE_OF_THE_STORM.get()) && stack.hasTag()) {
/*     */         
/*  65 */         CompoundTag tag = stack.getTag();
/*  66 */         float ratio = tag.getFloat("EntityHealthRatio");
/*  67 */         if (ratio > 0.0F) {
/*  68 */           event.addModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(EyeOfTheStormItem.DAMAGE_MODIFIER_ID, "Health damage modifier", (-ratio * 5.0F), AttributeModifier.Operation.ADDITION));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onLevelTick(TickEvent.LevelTickEvent event) {
/*  76 */     if (event.phase == TickEvent.Phase.END) {
/*  77 */       event.level.getCapability(WitherStormModCapabilities.WITHER_STORM_AUTO_SPAWNER).ifPresent(WitherStormAutoSpawner::tick);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerAttack(AttackEntityEvent event) {
/*  83 */     Player player = event.getEntity();
/*  84 */     if (!player.level().isClientSide()) {
/*     */       
/*  86 */       ItemStack stack = player.getMainHandItem();
/*  87 */       if (stack.is((Item)WitherStormModItems.FORMIDI_BLADE.get()) && !player.getCooldowns().isOnCooldown(stack.getItem())) {
/*     */         
/*  89 */         Entity hit = event.getTarget();
/*  90 */         CompoundTag tag = stack.getOrCreateTag();
/*  91 */         float power = Math.min(1.0F, tag.getFloat("Power"));
/*  92 */         if (power > 0.0F) {
/*     */           
/*  94 */           tag.putBoolean("IsCharged", false);
/*  95 */           tag.putFloat("Power", 2.0F);
/*  96 */           player.level().playSound(null, player.getX(), player.getY(), player.getZ(), (SoundEvent)WitherStormModSoundEvents.FORMIDI_BLADE_DECHARGE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/*  97 */           player.getCooldowns().addCooldown(stack.getItem(), 100);
/*  98 */           player.level().explode((Entity)player, hit.getX(), hit.getY(), hit.getZ(), 4.0F * power, Level.ExplosionInteraction.NONE);
/*  99 */           for (ServerPlayer nearby : player.level().getEntitiesOfClass(ServerPlayer.class, player.getBoundingBox().inflate(32.0D)))
/* 100 */             WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> nearby), new ShakeScreenMessage(40.0F, 2.5F)); 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\event\WitherStormModUtilEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
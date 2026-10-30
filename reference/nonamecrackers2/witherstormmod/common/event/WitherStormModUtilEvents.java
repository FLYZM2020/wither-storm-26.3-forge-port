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
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
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
/*  46 */     { HitResult hitResult = event.getRayTraceResult(); if (hitResult instanceof EntityHitResult) { EntityHitResult hit = (EntityHitResult)hitResult; if (hit.m_82443_() instanceof nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity || hit.m_82443_() instanceof nonamecrackers2.witherstormmod.common.entity.WitherStormEntity)
/*  47 */           event.setImpactResult(ProjectileImpactEvent.ImpactResult.SKIP_ENTITY);  }
/*     */        }
/*  49 */     else if (!event.getProjectile().m_9236_().m_5776_()) { Projectile projectile = event.getProjectile(); if (projectile instanceof ThrownPotion) { ThrownPotion thrownPotion = (ThrownPotion)projectile; HitResult hitResult = event.getRayTraceResult(); if (hitResult instanceof BlockHitResult) { BlockHitResult hit = (BlockHitResult)hitResult;
/*     */           
/*  51 */           Potion potion = PotionUtils.m_43579_(thrownPotion.m_7846_());
/*  52 */           BoundingBox box = (new BoundingBox(hit.m_82425_())).m_191961_(1);
/*  53 */           WorldTainting.getInstance().convertBlocks(box, thrownPotion.m_9236_(), potion); }
/*     */          }
/*     */        }
/*     */   
/*     */   }
/*     */   @SubscribeEvent
/*     */   public static void modifyItemAttributes(ItemAttributeModifierEvent event) {
/*  60 */     if (event.getSlotType() == EquipmentSlot.MAINHAND) {
/*     */       
/*  62 */       ItemStack stack = event.getItemStack();
/*  63 */       if (stack.m_150930_((Item)WitherStormModItems.EYE_OF_THE_STORM.get()) && stack.m_41782_()) {
/*     */         
/*  65 */         CompoundTag tag = stack.m_41783_();
/*  66 */         float ratio = tag.m_128457_("EntityHealthRatio");
/*  67 */         if (ratio > 0.0F) {
/*  68 */           event.addModifier(Attributes.f_22281_, new AttributeModifier(EyeOfTheStormItem.DAMAGE_MODIFIER_ID, "Health damage modifier", (-ratio * 5.0F), AttributeModifier.Operation.ADDITION));
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
/*  84 */     if (!player.m_9236_().m_5776_()) {
/*     */       
/*  86 */       ItemStack stack = player.m_21205_();
/*  87 */       if (stack.m_150930_((Item)WitherStormModItems.FORMIDI_BLADE.get()) && !player.m_36335_().m_41519_(stack.m_41720_())) {
/*     */         
/*  89 */         Entity hit = event.getTarget();
/*  90 */         CompoundTag tag = stack.m_41784_();
/*  91 */         float power = Math.min(1.0F, tag.m_128457_("Power"));
/*  92 */         if (power > 0.0F) {
/*     */           
/*  94 */           tag.m_128379_("IsCharged", false);
/*  95 */           tag.m_128350_("Power", 2.0F);
/*  96 */           player.m_9236_().m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)WitherStormModSoundEvents.FORMIDI_BLADE_DECHARGE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
/*  97 */           player.m_36335_().m_41524_(stack.m_41720_(), 100);
/*  98 */           player.m_9236_().m_254849_((Entity)player, hit.m_20185_(), hit.m_20186_(), hit.m_20189_(), 4.0F * power, Level.ExplosionInteraction.NONE);
/*  99 */           for (ServerPlayer nearby : player.m_9236_().m_45976_(ServerPlayer.class, player.m_20191_().m_82400_(32.0D)))
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
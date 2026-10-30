/*     */ package nonamecrackers2.witherstormmod.common.potion;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.world.effect.MobEffect;
/*     */ import net.minecraft.world.effect.MobEffectCategory;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeInstance;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeMap;
/*     */ import net.minecraft.world.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.common.util.LazyOptional;
/*     */ import nonamecrackers2.witherstormmod.common.capability.WitherSicknessTracker;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModDamageTypes;
/*     */ import nonamecrackers2.witherstormmod.common.tags.WitherStormModEntityTags;
/*     */ 
/*     */ 
/*     */ public class WitherSicknessEffect
/*     */   extends MobEffect
/*     */ {
/*     */   public WitherSicknessEffect(MobEffectCategory type, int color) {
/*  27 */     super(type, color);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void applyEffectTick(LivingEntity entity, int amplifier) {
/*  33 */     LazyOptional<WitherSicknessTracker> trackerOptional = entity.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER);
/*  34 */     trackerOptional.ifPresent(tracker -> {
/*     */           if (!tracker.isBeingCured()) {
/*     */             float damage = entity.getType().is(WitherStormModEntityTags.HIGH_IMMUNITY) ? 1.0F : 2.0F;
/*     */             
/*     */             entity.hurt(WitherStormModDamageTypes.source(entity.level().registryAccess(), WitherStormModDamageTypes.WITHER_SICKNESS), damage);
/*     */             
/*     */             addToMaxHealthModifier(entity, -0.5D, amplifier);
/*     */           } 
/*     */         });
/*  43 */     if (!trackerOptional.isPresent()) {
/*     */       
/*  45 */       float damage = entity.getType().is(WitherStormModEntityTags.HIGH_IMMUNITY) ? 1.0F : 2.0F;
/*  46 */       entity.hurt(WitherStormModDamageTypes.source(entity.level().registryAccess(), WitherStormModDamageTypes.WITHER_SICKNESS), damage);
/*  47 */       addToMaxHealthModifier(entity, -0.5D, amplifier);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDurationEffectTick(int p_76397_1_, int p_76397_2_) {
/*  54 */     int i = 7200 >> p_76397_2_;
/*  55 */     if (i > 0) {
/*  56 */       return (p_76397_1_ % i == 0);
/*     */     }
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addToMaxHealthModifier(LivingEntity entity, double amount, int amplifier) {
/*  64 */     Attribute attribute = Attributes.MAX_HEALTH;
/*  65 */     if (getAttributeModifiers().containsKey(attribute)) {
/*     */       
/*  67 */       UUID id = ((AttributeModifier)getAttributeModifiers().get(attribute)).getId();
/*  68 */       AttributeModifier modifier = entity.getAttribute(attribute).getModifier(id);
/*  69 */       if (modifier != null) {
/*     */         
/*  71 */         double value = modifier.getAmount();
/*  72 */         value = Math.max(entity.getAttributeBaseValue(attribute) * -1.0D + 1.0D, value + amount);
/*  73 */         updateAttributeModifier(entity, Attributes.MAX_HEALTH, value + amount, amplifier);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAttributeModifier(LivingEntity entity, Attribute attribute, double amount, int amplifier) {
/*  80 */     UUID id = ((AttributeModifier)getAttributeModifiers().get(attribute)).getId();
/*  81 */     AttributeModifier modifier = entity.getAttribute(attribute).getModifier(id);
/*  82 */     AttributeInstance instance = entity.getAttributes().getInstance(attribute);
/*  83 */     if (instance != null) {
/*     */       
/*  85 */       instance.removeModifier(modifier);
/*  86 */       AttributeModifier newModifier = new AttributeModifier(modifier.getId(), getDescriptionId() + " " + getDescriptionId(), amount, modifier.getOperation());
/*  87 */       instance.addPermanentModifier(newModifier);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAttributeModifiers(LivingEntity entity, AttributeMap manager, int amplifier) {
/*  94 */     for (Map.Entry<Attribute, AttributeModifier> entry : (Iterable<Map.Entry<Attribute, AttributeModifier>>)getAttributeModifiers().entrySet()) {
/*     */       
/*  96 */       AttributeInstance instance = manager.getInstance(entry.getKey());
/*  97 */       if (instance != null) {
/*     */         
/*  99 */         AttributeModifier modifier = entry.getValue();
/* 100 */         if (!instance.hasModifier(modifier)) {
/*     */           
/* 102 */           instance.addPermanentModifier(new AttributeModifier(modifier.getId(), getDescriptionId() + " " + getDescriptionId(), getAttributeModifierValue(amplifier, modifier), modifier.getOperation()));
/*     */           
/*     */           continue;
/*     */         } 
/* 106 */         AttributeModifier original = instance.getModifier(modifier.getId());
/* 107 */         instance.removeModifier(modifier);
/* 108 */         instance.addPermanentModifier(new AttributeModifier(original.getId(), getDescriptionId() + " " + getDescriptionId(), getAttributeModifierValue(amplifier, original), original.getOperation()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ItemStack> getCurativeItems() {
/* 117 */     return new ArrayList<>();
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\potion\WitherSicknessEffect.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
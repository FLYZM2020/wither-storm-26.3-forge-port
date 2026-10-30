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
/*     */   public void m_6742_(LivingEntity entity, int amplifier) {
/*  33 */     LazyOptional<WitherSicknessTracker> trackerOptional = entity.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER);
/*  34 */     trackerOptional.ifPresent(tracker -> {
/*     */           if (!tracker.isBeingCured()) {
/*     */             float damage = entity.m_6095_().m_204039_(WitherStormModEntityTags.HIGH_IMMUNITY) ? 1.0F : 2.0F;
/*     */             
/*     */             entity.m_6469_(WitherStormModDamageTypes.source(entity.m_9236_().m_9598_(), WitherStormModDamageTypes.WITHER_SICKNESS), damage);
/*     */             
/*     */             addToMaxHealthModifier(entity, -0.5D, amplifier);
/*     */           } 
/*     */         });
/*  43 */     if (!trackerOptional.isPresent()) {
/*     */       
/*  45 */       float damage = entity.m_6095_().m_204039_(WitherStormModEntityTags.HIGH_IMMUNITY) ? 1.0F : 2.0F;
/*  46 */       entity.m_6469_(WitherStormModDamageTypes.source(entity.m_9236_().m_9598_(), WitherStormModDamageTypes.WITHER_SICKNESS), damage);
/*  47 */       addToMaxHealthModifier(entity, -0.5D, amplifier);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_6584_(int p_76397_1_, int p_76397_2_) {
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
/*  64 */     Attribute attribute = Attributes.f_22276_;
/*  65 */     if (m_19485_().containsKey(attribute)) {
/*     */       
/*  67 */       UUID id = ((AttributeModifier)m_19485_().get(attribute)).m_22209_();
/*  68 */       AttributeModifier modifier = entity.m_21051_(attribute).m_22111_(id);
/*  69 */       if (modifier != null) {
/*     */         
/*  71 */         double value = modifier.m_22218_();
/*  72 */         value = Math.max(entity.m_21172_(attribute) * -1.0D + 1.0D, value + amount);
/*  73 */         updateAttributeModifier(entity, Attributes.f_22276_, value + amount, amplifier);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAttributeModifier(LivingEntity entity, Attribute attribute, double amount, int amplifier) {
/*  80 */     UUID id = ((AttributeModifier)m_19485_().get(attribute)).m_22209_();
/*  81 */     AttributeModifier modifier = entity.m_21051_(attribute).m_22111_(id);
/*  82 */     AttributeInstance instance = entity.m_21204_().m_22146_(attribute);
/*  83 */     if (instance != null) {
/*     */       
/*  85 */       instance.m_22130_(modifier);
/*  86 */       AttributeModifier newModifier = new AttributeModifier(modifier.m_22209_(), m_19481_() + " " + m_19481_(), amount, modifier.m_22217_());
/*  87 */       instance.m_22125_(newModifier);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_6385_(LivingEntity entity, AttributeMap manager, int amplifier) {
/*  94 */     for (Map.Entry<Attribute, AttributeModifier> entry : (Iterable<Map.Entry<Attribute, AttributeModifier>>)m_19485_().entrySet()) {
/*     */       
/*  96 */       AttributeInstance instance = manager.m_22146_(entry.getKey());
/*  97 */       if (instance != null) {
/*     */         
/*  99 */         AttributeModifier modifier = entry.getValue();
/* 100 */         if (!instance.m_22109_(modifier)) {
/*     */           
/* 102 */           instance.m_22125_(new AttributeModifier(modifier.m_22209_(), m_19481_() + " " + m_19481_(), m_7048_(amplifier, modifier), modifier.m_22217_()));
/*     */           
/*     */           continue;
/*     */         } 
/* 106 */         AttributeModifier original = instance.m_22111_(modifier.m_22209_());
/* 107 */         instance.m_22130_(modifier);
/* 108 */         instance.m_22125_(new AttributeModifier(original.m_22209_(), m_19481_() + " " + m_19481_(), m_7048_(amplifier, original), original.m_22217_()));
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
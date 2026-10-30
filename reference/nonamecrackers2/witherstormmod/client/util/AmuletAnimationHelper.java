/*     */ package nonamecrackers2.witherstormmod.client.util;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.mojang.math.Axis;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraft.client.player.AbstractClientPlayer;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.NbtUtils;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.entity.HumanoidArm;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
/*     */ import nonamecrackers2.witherstormmod.common.item.AmuletItem;
/*     */ import org.joml.Matrix4f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AmuletAnimationHelper
/*     */ {
/*  34 */   public static final ResourceLocation GLARE = new ResourceLocation("witherstormmod", "textures/misc/glare.png");
/*  35 */   private static final Map<InteractionHand, AnimationHolder> ANIMATIONS = (Map<InteractionHand, AnimationHolder>)ImmutableMap.of(InteractionHand.MAIN_HAND, new AnimationHolder(), InteractionHand.OFF_HAND, new AnimationHolder());
/*     */ 
/*     */   
/*     */   public static void onRenderItemInHand(ItemStack item, PoseStack stack, InteractionHand hand, float partialTicks, MultiBufferSource buffer) {
/*  39 */     if (item.m_41720_() instanceof AmuletItem) {
/*     */       
/*  41 */       AnimationHolder holder = ANIMATIONS.get(hand);
/*     */       
/*  43 */       VertexConsumer consumer = buffer.m_6299_(RenderType.m_110473_(GLARE));
/*     */       
/*  45 */       stack.m_85837_(0.0D, 0.2D, 0.07D);
/*  46 */       stack.m_252781_(Axis.f_252529_.m_252977_(Mth.m_14179_(partialTicks, holder.animO, holder.anim)));
/*     */ 
/*     */       
/*  49 */       drawGlare(stack, hand, 1.0F, -0.53D, 0.089D, -0.53D, 0.0F, 0.24705882F, 1.0F, 0.0F, holder.tickCount + 360, partialTicks, consumer, Mth.m_14179_(partialTicks, holder.pulseIntensityO[0], holder.pulseIntensity[0]));
/*     */       
/*  51 */       drawGlare(stack, hand, 0.4F, -0.2D, 0.09D, -0.02D, 0.007843138F, 0.8980392F, 0.7019608F, 0.0F, holder.tickCount, partialTicks, consumer, Mth.m_14179_(partialTicks, holder.pulseIntensityO[1], holder.pulseIntensity[1]));
/*     */       
/*  53 */       drawGlare(stack, hand, 0.4F, -0.2D, 0.09D, -0.02D, 0.9411765F, 0.15294118F, 0.02745098F, (hand == InteractionHand.MAIN_HAND) ? 270.0F : 90.0F, holder.tickCount, partialTicks, consumer, Mth.m_14179_(partialTicks, holder.pulseIntensityO[4], holder.pulseIntensity[4]));
/*     */       
/*  55 */       drawGlare(stack, hand, 0.4F, -0.2D, 0.09D, -0.02D, 0.85882354F, 0.85882354F, 0.85882354F, 180.0F, holder.tickCount, partialTicks, consumer, Mth.m_14179_(partialTicks, holder.pulseIntensityO[3], holder.pulseIntensity[3]));
/*     */       
/*  57 */       drawGlare(stack, hand, 0.4F, -0.2D, 0.09D, -0.02D, 0.25882354F, 0.8666667F, 0.023529412F, (hand == InteractionHand.MAIN_HAND) ? 90.0F : 270.0F, holder.tickCount, partialTicks, consumer, Mth.m_14179_(partialTicks, holder.pulseIntensityO[2], holder.pulseIntensity[2]));
/*     */       
/*  59 */       stack.m_85837_(0.0D, -0.2D, -0.07D);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawGlare(PoseStack stack, InteractionHand hand, float scale, double xOffset, double zOffset, double radius, float r, float g, float b, float degreeOffset, int ticks, float partialTicks, VertexConsumer consumer, float pulseIntensity) {
/*  65 */     stack.m_85836_();
/*  66 */     stack.m_252781_(Axis.f_252529_.m_252977_(degreeOffset - 30.0F));
/*  67 */     stack.m_252781_(Axis.f_252392_.m_252977_(90.0F));
/*  68 */     Minecraft mc = Minecraft.m_91087_();
/*  69 */     HumanoidArm arm = (HumanoidArm)mc.f_91066_.m_232107_().m_231551_();
/*  70 */     boolean swap = (arm == HumanoidArm.RIGHT) ? ((hand == InteractionHand.MAIN_HAND)) : ((hand == InteractionHand.OFF_HAND));
/*  71 */     stack.m_85837_(xOffset, radius, swap ? -zOffset : zOffset);
/*  72 */     stack.m_85841_(scale, scale, scale);
/*  73 */     Matrix4f matrix = stack.m_85850_().m_252922_();
/*  74 */     float alpha = Mth.m_14036_(Mth.m_14089_((ticks + degreeOffset + partialTicks) * 0.2F) * pulseIntensity, 0.0F, 1.0F);
/*  75 */     if (alpha > 0.0F) {
/*     */       
/*  77 */       consumer.m_252986_(matrix, 0.0F, 0.0F, 0.0F).m_85950_(r, g, b, alpha).m_7421_(0.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(15728880).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/*  78 */       consumer.m_252986_(matrix, 0.0F, 1.0F, 0.0F).m_85950_(r, g, b, alpha).m_7421_(0.0F, 1.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(15728880).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/*  79 */       consumer.m_252986_(matrix, 1.0F, 1.0F, 0.0F).m_85950_(r, g, b, alpha).m_7421_(1.0F, 1.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(15728880).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/*  80 */       consumer.m_252986_(matrix, 1.0F, 0.0F, 0.0F).m_85950_(r, g, b, alpha).m_7421_(1.0F, 0.0F).m_86008_(OverlayTexture.f_118083_).m_85969_(15728880).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
/*     */     } 
/*  82 */     stack.m_85849_();
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getPulseIntensity(AbstractClientPlayer player, ClientLevel level, ItemStack stack, String id, int distance) {
/*  87 */     CompoundTag tag = stack.m_41784_();
/*  88 */     if (tag.m_128441_(id + "Pos")) {
/*     */       
/*  90 */       BlockPos pos = NbtUtils.m_129239_(tag.m_128469_(id + "Pos"));
/*  91 */       float angle = (float)(Mth.m_14136_(pos.m_123341_() - player.m_20185_(), pos.m_123343_() - player.m_20189_()) * 57.29577951308232D);
/*  92 */       float angleDiff = (Mth.m_14177_(-player.f_20885_) - angle + 180.0F + 360.0F) % 360.0F - 180.0F;
/*  93 */       float value = 1.0F - Mth.m_14036_(Mth.m_14154_(angleDiff * 0.03F), 0.0F, 0.8F);
/*  94 */       int dist = tag.m_128451_(id + "Dist");
/*  95 */       if (dist >= 0) {
/*  96 */         return value * Mth.m_14036_((distance - dist) * 0.05F, 0.0F, 1.0F);
/*     */       }
/*  98 */       return 0.0F;
/*     */     } 
/* 100 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void onClientTick(TickEvent.ClientTickEvent event) {
/* 105 */     Minecraft mc = Minecraft.m_91087_();
/* 106 */     if (!mc.m_91104_() && mc.f_91074_ != null)
/*     */     {
/* 108 */       for (InteractionHand hand : InteractionHand.values()) {
/*     */         
/* 110 */         ItemStack item = mc.f_91074_.m_21120_(hand);
/* 111 */         if (item.m_150930_((Item)WitherStormModItems.AMULET.get())) {
/*     */           
/* 113 */           AnimationHolder holder = ANIMATIONS.get(hand);
/* 114 */           if (holder != null) {
/*     */             
/* 116 */             holder.tickCount++;
/* 117 */             CompoundTag tag = item.m_41784_();
/* 118 */             int index = tag.m_128451_("SelectedIndex");
/* 119 */             float target = getSwapDegrees(hand, index);
/* 120 */             if (target != holder.targetO)
/* 121 */               holder.steps = 16; 
/* 122 */             holder.animO = holder.anim;
/* 123 */             if (holder.steps > 0) {
/*     */               
/* 125 */               holder.anim += Mth.m_14177_(target - holder.anim) / holder.steps;
/* 126 */               holder.steps--;
/*     */             } 
/* 128 */             holder.targetO = target;
/* 129 */             for (int i = 0; i < holder.pulseIntensity.length; i++) {
/*     */               
/* 131 */               holder.pulseIntensityO[i] = holder.pulseIntensity[i];
/* 132 */               holder.pulseIntensity[i] = getPulseIntensity((AbstractClientPlayer)mc.f_91074_, mc.f_91073_, item, AmuletItem.TRACKING[i], 1000);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getSwapDegrees(InteractionHand hand, int index) {
/* 142 */     if (hand == InteractionHand.OFF_HAND) {
/*     */       
/* 144 */       switch (index) {
/*     */         
/*     */         case 1:
/* 147 */           return 0.0F;
/*     */         case 2:
/* 149 */           return 90.0F;
/*     */         case 3:
/* 151 */           return 180.0F;
/*     */         case 4:
/* 153 */           return 270.0F;
/*     */       } 
/* 155 */       return 0.0F;
/*     */     } 
/*     */     
/* 158 */     if (hand == InteractionHand.MAIN_HAND) {
/*     */       
/* 160 */       switch (index) {
/*     */         
/*     */         case 1:
/* 163 */           return 0.0F;
/*     */         case 2:
/* 165 */           return 270.0F;
/*     */         case 3:
/* 167 */           return 180.0F;
/*     */         case 4:
/* 169 */           return 90.0F;
/*     */       } 
/* 171 */       return 0.0F;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 176 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   private static class AnimationHolder
/*     */   {
/*     */     private float anim;
/*     */     private float animO;
/*     */     private float targetO;
/*     */     private int steps;
/*     */     private int tickCount;
/* 187 */     private float[] pulseIntensity = new float[5];
/* 188 */     private float[] pulseIntensityO = new float[5];
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\clien\\util\AmuletAnimationHelper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
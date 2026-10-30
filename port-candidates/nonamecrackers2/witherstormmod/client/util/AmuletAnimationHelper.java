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
/*     */ import net.minecraft.client.renderer.rendertype.RenderType;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.NbtUtils;
/*     */ import net.minecraft.resources.Identifier;
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
/*  34 */   public static final Identifier GLARE = Identifier.fromNamespaceAndPath("witherstormmod", "textures/misc/glare.png");
/*  35 */   private static final Map<InteractionHand, AnimationHolder> ANIMATIONS = (Map<InteractionHand, AnimationHolder>)ImmutableMap.of(InteractionHand.MAIN_HAND, new AnimationHolder(), InteractionHand.OFF_HAND, new AnimationHolder());
/*     */ 
/*     */   
/*     */   public static void onRenderItemInHand(ItemStack item, PoseStack stack, InteractionHand hand, float partialTicks, MultiBufferSource buffer) {
/*  39 */     if (item.getItem() instanceof AmuletItem) {
/*     */       
/*  41 */       AnimationHolder holder = ANIMATIONS.get(hand);
/*     */       
/*  43 */       VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucent(GLARE));
/*     */       
/*  45 */       stack.translate(0.0D, 0.2D, 0.07D);
/*  46 */       stack.mulPose(Axis.XP.rotationDegrees(Mth.lerp(partialTicks, holder.animO, holder.anim)));
/*     */ 
/*     */       
/*  49 */       drawGlare(stack, hand, 1.0F, -0.53D, 0.089D, -0.53D, 0.0F, 0.24705882F, 1.0F, 0.0F, holder.tickCount + 360, partialTicks, consumer, Mth.lerp(partialTicks, holder.pulseIntensityO[0], holder.pulseIntensity[0]));
/*     */       
/*  51 */       drawGlare(stack, hand, 0.4F, -0.2D, 0.09D, -0.02D, 0.007843138F, 0.8980392F, 0.7019608F, 0.0F, holder.tickCount, partialTicks, consumer, Mth.lerp(partialTicks, holder.pulseIntensityO[1], holder.pulseIntensity[1]));
/*     */       
/*  53 */       drawGlare(stack, hand, 0.4F, -0.2D, 0.09D, -0.02D, 0.9411765F, 0.15294118F, 0.02745098F, (hand == InteractionHand.MAIN_HAND) ? 270.0F : 90.0F, holder.tickCount, partialTicks, consumer, Mth.lerp(partialTicks, holder.pulseIntensityO[4], holder.pulseIntensity[4]));
/*     */       
/*  55 */       drawGlare(stack, hand, 0.4F, -0.2D, 0.09D, -0.02D, 0.85882354F, 0.85882354F, 0.85882354F, 180.0F, holder.tickCount, partialTicks, consumer, Mth.lerp(partialTicks, holder.pulseIntensityO[3], holder.pulseIntensity[3]));
/*     */       
/*  57 */       drawGlare(stack, hand, 0.4F, -0.2D, 0.09D, -0.02D, 0.25882354F, 0.8666667F, 0.023529412F, (hand == InteractionHand.MAIN_HAND) ? 90.0F : 270.0F, holder.tickCount, partialTicks, consumer, Mth.lerp(partialTicks, holder.pulseIntensityO[2], holder.pulseIntensity[2]));
/*     */       
/*  59 */       stack.translate(0.0D, -0.2D, -0.07D);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawGlare(PoseStack stack, InteractionHand hand, float scale, double xOffset, double zOffset, double radius, float r, float g, float b, float degreeOffset, int ticks, float partialTicks, VertexConsumer consumer, float pulseIntensity) {
/*  65 */     stack.pushPose();
/*  66 */     stack.mulPose(Axis.XP.rotationDegrees(degreeOffset - 30.0F));
/*  67 */     stack.mulPose(Axis.YN.rotationDegrees(90.0F));
/*  68 */     Minecraft mc = Minecraft.getInstance();
/*  69 */     HumanoidArm arm = (HumanoidArm)mc.options.mainHand().get();
/*  70 */     boolean swap = (arm == HumanoidArm.RIGHT) ? ((hand == InteractionHand.MAIN_HAND)) : ((hand == InteractionHand.OFF_HAND));
/*  71 */     stack.translate(xOffset, radius, swap ? -zOffset : zOffset);
/*  72 */     stack.scale(scale, scale, scale);
/*  73 */     Matrix4f matrix = stack.last().pose();
/*  74 */     float alpha = Mth.clamp(Mth.cos((ticks + degreeOffset + partialTicks) * 0.2F) * pulseIntensity, 0.0F, 1.0F);
/*  75 */     if (alpha > 0.0F) {
/*     */       
/*  77 */       consumer.vertex(matrix, 0.0F, 0.0F, 0.0F).color(r, g, b, alpha).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(0.0F, 1.0F, 0.0F).endVertex();
/*  78 */       consumer.vertex(matrix, 0.0F, 1.0F, 0.0F).color(r, g, b, alpha).uv(0.0F, 1.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(0.0F, 1.0F, 0.0F).endVertex();
/*  79 */       consumer.vertex(matrix, 1.0F, 1.0F, 0.0F).color(r, g, b, alpha).uv(1.0F, 1.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(0.0F, 1.0F, 0.0F).endVertex();
/*  80 */       consumer.vertex(matrix, 1.0F, 0.0F, 0.0F).color(r, g, b, alpha).uv(1.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(0.0F, 1.0F, 0.0F).endVertex();
/*     */     } 
/*  82 */     stack.popPose();
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getPulseIntensity(AbstractClientPlayer player, ClientLevel level, ItemStack stack, String id, int distance) {
/*  87 */     CompoundTag tag = stack.getOrCreateTag();
/*  88 */     if (tag.contains(id + "Pos")) {
/*     */       
/*  90 */       BlockPos pos = NbtUtils.readBlockPos(tag.getCompound(id + "Pos"));
/*  91 */       float angle = (float)(Mth.atan2(pos.getX() - player.getX(), pos.getZ() - player.getZ()) * 57.29577951308232D);
/*  92 */       float angleDiff = (Mth.wrapDegrees(-player.yHeadRot) - angle + 180.0F + 360.0F) % 360.0F - 180.0F;
/*  93 */       float value = 1.0F - Mth.clamp(Mth.abs(angleDiff * 0.03F), 0.0F, 0.8F);
/*  94 */       int dist = tag.getInt(id + "Dist");
/*  95 */       if (dist >= 0) {
/*  96 */         return value * Mth.clamp((distance - dist) * 0.05F, 0.0F, 1.0F);
/*     */       }
/*  98 */       return 0.0F;
/*     */     } 
/* 100 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void onClientTick(TickEvent.ClientTickEvent event) {
/* 105 */     Minecraft mc = Minecraft.getInstance();
/* 106 */     if (!mc.isPaused() && mc.player != null)
/*     */     {
/* 108 */       for (InteractionHand hand : InteractionHand.values()) {
/*     */         
/* 110 */         ItemStack item = mc.player.getItemInHand(hand);
/* 111 */         if (item.is((Item)WitherStormModItems.AMULET.get())) {
/*     */           
/* 113 */           AnimationHolder holder = ANIMATIONS.get(hand);
/* 114 */           if (holder != null) {
/*     */             
/* 116 */             holder.tickCount++;
/* 117 */             CompoundTag tag = item.getOrCreateTag();
/* 118 */             int index = tag.getInt("SelectedIndex");
/* 119 */             float target = getSwapDegrees(hand, index);
/* 120 */             if (target != holder.targetO)
/* 121 */               holder.steps = 16; 
/* 122 */             holder.animO = holder.anim;
/* 123 */             if (holder.steps > 0) {
/*     */               
/* 125 */               holder.anim += Mth.wrapDegrees(target - holder.anim) / holder.steps;
/* 126 */               holder.steps--;
/*     */             } 
/* 128 */             holder.targetO = target;
/* 129 */             for (int i = 0; i < holder.pulseIntensity.length; i++) {
/*     */               
/* 131 */               holder.pulseIntensityO[i] = holder.pulseIntensity[i];
/* 132 */               holder.pulseIntensity[i] = getPulseIntensity((AbstractClientPlayer)mc.player, mc.level, item, AmuletItem.TRACKING[i], 1000);
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
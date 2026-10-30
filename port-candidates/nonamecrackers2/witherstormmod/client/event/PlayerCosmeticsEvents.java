/*     */ package nonamecrackers2.witherstormmod.client.event;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.math.Axis;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraftforge.client.event.RenderPlayerEvent;
/*     */ import net.minecraftforge.client.model.data.ModelData;
/*     */ import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
/*     */ import nonamecrackers2.witherstormmod.client.util.Contributors;
/*     */ import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerCosmeticsEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onRenderPlayer(RenderPlayerEvent.Post event) {
/*  25 */     Player player = event.getEntity();
/*  26 */     if ((((Boolean)WitherStormModConfig.CLIENT.patronCosmetic.get()).booleanValue() || player != (Minecraft.getInstance()).player) && !player.isInvisible()) {
/*     */       
/*  28 */       String name = player.getGameProfile().getName();
/*  29 */       BlockState state = null;
/*  30 */       BlockState devState = null;
/*  31 */       BlockState kofiState = null;
/*  32 */       if (Contributors.isDeveloper(name))
/*  33 */         devState = Blocks.COMMAND_BLOCK.defaultBlockState(); 
/*  34 */       if (Contributors.isPatron(name))
/*  35 */         state = ((Block)WitherStormModBlocks.FORMIDIBOMB.get()).defaultBlockState(); 
/*  36 */       if (Contributors.isKofi(name))
/*  37 */         kofiState = ((Block)WitherStormModBlocks.TAINTED_ZOMBIE_LYING.get()).defaultBlockState(); 
/*  38 */       Minecraft mc = Minecraft.getInstance();
/*  39 */       if (state != null) {
/*     */         
/*  41 */         PoseStack stack = event.getPoseStack();
/*  42 */         stack.pushPose();
/*     */         
/*  44 */         float tickCount = player.tickCount + event.getPartialTick();
/*     */         
/*  46 */         stack.mulPose(Axis.YP.rotationDegrees(tickCount));
/*  47 */         stack.translate(0.6D, (player.getBbHeight() + 0.2F), 0.0D);
/*     */         
/*  49 */         stack.pushPose();
/*  50 */         float scale = (Mth.sin(tickCount * 0.1F) + 10.0F) * 0.025F;
/*     */         
/*  52 */         stack.mulPose(Axis.XP.rotationDegrees(tickCount));
/*  53 */         stack.mulPose(Axis.ZN.rotationDegrees(tickCount * 4.0F));
/*  54 */         stack.scale(scale, scale, scale);
/*  55 */         stack.translate(-0.5D, -0.5D, -0.5D);
/*     */         
/*  57 */         int overlay = OverlayTexture.NO_OVERLAY;
/*  58 */         if (player.tickCount / 20 % 5 == 0 && Contributors.isPatron(name)) {
/*  59 */           overlay = OverlayTexture.pack(OverlayTexture.u(1.0F), 10);
/*     */         }
/*  61 */         mc.getBlockRenderer().renderSingleBlock(state, stack, event.getMultiBufferSource(), 15728880, overlay, ModelData.EMPTY, null);
/*     */         
/*  63 */         stack.popPose();
/*  64 */         stack.popPose();
/*     */       } 
/*  66 */       if (devState != null) {
/*     */         
/*  68 */         PoseStack devStack = event.getPoseStack();
/*  69 */         devStack.pushPose();
/*     */         
/*  71 */         float tickCount = player.tickCount + event.getPartialTick();
/*     */         
/*  73 */         devStack.mulPose(Axis.YP.rotationDegrees(tickCount));
/*  74 */         devStack.translate(-0.6D, (player.getBbHeight() + 0.2F), 0.0D);
/*     */         
/*  76 */         devStack.pushPose();
/*  77 */         float scale = (Mth.sin(tickCount * 0.1F) + 10.0F) * 0.025F;
/*     */         
/*  79 */         devStack.mulPose(Axis.XP.rotationDegrees(tickCount));
/*  80 */         devStack.mulPose(Axis.ZN.rotationDegrees(tickCount * 4.0F));
/*  81 */         devStack.scale(scale, scale, scale);
/*  82 */         devStack.translate(-0.5D, -0.5D, -0.5D);
/*     */         
/*  84 */         int overlay = OverlayTexture.NO_OVERLAY;
/*  85 */         if (player.tickCount / 20 % 5 == 0 && Contributors.isPatron(name)) {
/*  86 */           overlay = OverlayTexture.pack(OverlayTexture.u(1.0F), 10);
/*     */         }
/*  88 */         mc.getBlockRenderer().renderSingleBlock(devState, devStack, event.getMultiBufferSource(), 15728880, overlay, ModelData.EMPTY, null);
/*     */         
/*  90 */         devStack.popPose();
/*  91 */         devStack.popPose();
/*     */       } 
/*  93 */       if (kofiState != null) {
/*     */ 
/*     */         
/*  96 */         PoseStack kofiStack = event.getPoseStack();
/*  97 */         kofiStack.pushPose();
/*     */         
/*  99 */         float tickCount = player.tickCount + event.getPartialTick();
/*     */         
/* 101 */         kofiStack.mulPose(Axis.YP.rotationDegrees(-tickCount));
/* 102 */         kofiStack.translate(-0.6D, (player.getBbHeight() + 0.2F), 1.0D);
/*     */         
/* 104 */         kofiStack.pushPose();
/* 105 */         float scale = 0.2727F;
/*     */         
/* 107 */         kofiStack.mulPose(Axis.XP.rotationDegrees(-tickCount * 6.0F));
/* 108 */         kofiStack.mulPose(Axis.ZN.rotationDegrees(-tickCount * 6.0F));
/* 109 */         kofiStack.scale(scale, scale, scale);
/* 110 */         kofiStack.translate(-0.5D, -0.5D, -0.5D);
/* 111 */         int overlay = OverlayTexture.NO_OVERLAY;
/*     */         
/* 113 */         mc.getBlockRenderer().renderSingleBlock(kofiState, kofiStack, event.getMultiBufferSource(), 15728880, overlay, ModelData.EMPTY, null);
/* 114 */         kofiStack.popPose();
/* 115 */         kofiStack.popPose();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\event\PlayerCosmeticsEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
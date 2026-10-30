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
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
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
/*  26 */     if ((((Boolean)WitherStormModConfig.CLIENT.patronCosmetic.get()).booleanValue() || player != (Minecraft.m_91087_()).f_91074_) && !player.m_20145_()) {
/*     */       
/*  28 */       String name = player.m_36316_().getName();
/*  29 */       BlockState state = null;
/*  30 */       BlockState devState = null;
/*  31 */       BlockState kofiState = null;
/*  32 */       if (Contributors.isDeveloper(name))
/*  33 */         devState = Blocks.f_50272_.m_49966_(); 
/*  34 */       if (Contributors.isPatron(name))
/*  35 */         state = ((Block)WitherStormModBlocks.FORMIDIBOMB.get()).m_49966_(); 
/*  36 */       if (Contributors.isKofi(name))
/*  37 */         kofiState = ((Block)WitherStormModBlocks.TAINTED_ZOMBIE_LYING.get()).m_49966_(); 
/*  38 */       Minecraft mc = Minecraft.m_91087_();
/*  39 */       if (state != null) {
/*     */         
/*  41 */         PoseStack stack = event.getPoseStack();
/*  42 */         stack.m_85836_();
/*     */         
/*  44 */         float tickCount = player.f_19797_ + event.getPartialTick();
/*     */         
/*  46 */         stack.m_252781_(Axis.f_252436_.m_252977_(tickCount));
/*  47 */         stack.m_85837_(0.6D, (player.m_20206_() + 0.2F), 0.0D);
/*     */         
/*  49 */         stack.m_85836_();
/*  50 */         float scale = (Mth.m_14031_(tickCount * 0.1F) + 10.0F) * 0.025F;
/*     */         
/*  52 */         stack.m_252781_(Axis.f_252529_.m_252977_(tickCount));
/*  53 */         stack.m_252781_(Axis.f_252393_.m_252977_(tickCount * 4.0F));
/*  54 */         stack.m_85841_(scale, scale, scale);
/*  55 */         stack.m_85837_(-0.5D, -0.5D, -0.5D);
/*     */         
/*  57 */         int overlay = OverlayTexture.f_118083_;
/*  58 */         if (player.f_19797_ / 20 % 5 == 0 && Contributors.isPatron(name)) {
/*  59 */           overlay = OverlayTexture.m_118093_(OverlayTexture.m_118088_(1.0F), 10);
/*     */         }
/*  61 */         mc.m_91289_().renderSingleBlock(state, stack, event.getMultiBufferSource(), 15728880, overlay, ModelData.EMPTY, null);
/*     */         
/*  63 */         stack.m_85849_();
/*  64 */         stack.m_85849_();
/*     */       } 
/*  66 */       if (devState != null) {
/*     */         
/*  68 */         PoseStack devStack = event.getPoseStack();
/*  69 */         devStack.m_85836_();
/*     */         
/*  71 */         float tickCount = player.f_19797_ + event.getPartialTick();
/*     */         
/*  73 */         devStack.m_252781_(Axis.f_252436_.m_252977_(tickCount));
/*  74 */         devStack.m_85837_(-0.6D, (player.m_20206_() + 0.2F), 0.0D);
/*     */         
/*  76 */         devStack.m_85836_();
/*  77 */         float scale = (Mth.m_14031_(tickCount * 0.1F) + 10.0F) * 0.025F;
/*     */         
/*  79 */         devStack.m_252781_(Axis.f_252529_.m_252977_(tickCount));
/*  80 */         devStack.m_252781_(Axis.f_252393_.m_252977_(tickCount * 4.0F));
/*  81 */         devStack.m_85841_(scale, scale, scale);
/*  82 */         devStack.m_85837_(-0.5D, -0.5D, -0.5D);
/*     */         
/*  84 */         int overlay = OverlayTexture.f_118083_;
/*  85 */         if (player.f_19797_ / 20 % 5 == 0 && Contributors.isPatron(name)) {
/*  86 */           overlay = OverlayTexture.m_118093_(OverlayTexture.m_118088_(1.0F), 10);
/*     */         }
/*  88 */         mc.m_91289_().renderSingleBlock(devState, devStack, event.getMultiBufferSource(), 15728880, overlay, ModelData.EMPTY, null);
/*     */         
/*  90 */         devStack.m_85849_();
/*  91 */         devStack.m_85849_();
/*     */       } 
/*  93 */       if (kofiState != null) {
/*     */ 
/*     */         
/*  96 */         PoseStack kofiStack = event.getPoseStack();
/*  97 */         kofiStack.m_85836_();
/*     */         
/*  99 */         float tickCount = player.f_19797_ + event.getPartialTick();
/*     */         
/* 101 */         kofiStack.m_252781_(Axis.f_252436_.m_252977_(-tickCount));
/* 102 */         kofiStack.m_85837_(-0.6D, (player.m_20206_() + 0.2F), 1.0D);
/*     */         
/* 104 */         kofiStack.m_85836_();
/* 105 */         float scale = 0.2727F;
/*     */         
/* 107 */         kofiStack.m_252781_(Axis.f_252529_.m_252977_(-tickCount * 6.0F));
/* 108 */         kofiStack.m_252781_(Axis.f_252393_.m_252977_(-tickCount * 6.0F));
/* 109 */         kofiStack.m_85841_(scale, scale, scale);
/* 110 */         kofiStack.m_85837_(-0.5D, -0.5D, -0.5D);
/* 111 */         int overlay = OverlayTexture.f_118083_;
/*     */         
/* 113 */         mc.m_91289_().renderSingleBlock(kofiState, kofiStack, event.getMultiBufferSource(), 15728880, overlay, ModelData.EMPTY, null);
/* 114 */         kofiStack.m_85849_();
/* 115 */         kofiStack.m_85849_();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\event\PlayerCosmeticsEvents.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
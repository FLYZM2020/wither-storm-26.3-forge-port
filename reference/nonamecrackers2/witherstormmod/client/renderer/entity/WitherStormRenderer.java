/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.PoseStack;
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.mojang.math.Axis;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.MultiBufferSource;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.AbstractWitherStormModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormCommandBlockModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormDestroyerModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormDevourerModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormDismantledModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormEvolvedDestroyerModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormEvolvedDevourerModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormGrowingHunchbackModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchback1_1Model;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchback1_2Model;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchback2_1Model;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchback3_1Model;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchback3_2Model;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchbackModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormIntermediateDevourerModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormIntermediateEvolvedDestroyerModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormIntermediateEvolvedDevourerModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormPregnantHunchbackModel;
/*     */ import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormTornEvolvedDevourerModel;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import org.joml.Matrix4f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WitherStormRenderer
/*     */   extends AbstractWitherStormRenderer<WitherStormEntity, AbstractWitherStormModel<WitherStormEntity>>
/*     */ {
/*     */   private final WitherStormCommandBlockModel<WitherStormEntity> commandBlockModel;
/*     */   private final WitherStormDestroyerModel<WitherStormEntity> destroyerModel;
/*     */   private final WitherStormIntermediateEvolvedDestroyerModel<WitherStormEntity> intermediateEvolvedDestroyer;
/*     */   private final WitherStormEvolvedDestroyerModel<WitherStormEntity> evolvedDestroyerModel;
/*     */   private final WitherStormIntermediateDevourerModel<WitherStormEntity> intermediateDevourerModel;
/*     */   private final WitherStormDevourerModel<WitherStormEntity> devourerModel;
/*     */   private final WitherStormIntermediateEvolvedDevourerModel<WitherStormEntity> intermediateEvolvedDevourerModel;
/*     */   private final WitherStormEvolvedDevourerModel<WitherStormEntity> evolvedDevourerModel;
/*     */   private final WitherStormDismantledModel<WitherStormEntity> dismantledModel;
/*     */   private final WitherStormTornEvolvedDevourerModel<WitherStormEntity> tornModel;
/*     */   private final WitherStormHunchbackModel<WitherStormEntity> hunchbackP1;
/*     */   private final WitherStormHunchback1_1Model<WitherStormEntity> hunchbackP125;
/*     */   private final WitherStormHunchback1_2Model<WitherStormEntity> hunchbackP15;
/*     */   private final WitherStormGrowingHunchbackModel<WitherStormEntity> hunchbackP2;
/*     */   private final WitherStormHunchback2_1Model<WitherStormEntity> hunchbackP25;
/*     */   private final WitherStormPregnantHunchbackModel<WitherStormEntity> hunchbackP3;
/*     */   private final WitherStormHunchback3_1Model<WitherStormEntity> hunchbackP31;
/*     */   private final WitherStormHunchback3_2Model<WitherStormEntity> hunchbackP32;
/*     */   
/*     */   public WitherStormRenderer(EntityRendererProvider.Context context) {
/*  61 */     this(context, new WitherStormCommandBlockModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_0)));
/*     */   }
/*     */ 
/*     */   
/*     */   private WitherStormRenderer(EntityRendererProvider.Context context, WitherStormCommandBlockModel<WitherStormEntity> base) {
/*  66 */     super(context, base);
/*     */     
/*  68 */     this.commandBlockModel = base;
/*  69 */     this.hunchbackP1 = new WitherStormHunchbackModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_1));
/*  70 */     this.hunchbackP125 = new WitherStormHunchback1_1Model(context.m_174023_(WitherStormModRenderers.WITHER_STORM_1_1));
/*  71 */     this.hunchbackP15 = new WitherStormHunchback1_2Model(context.m_174023_(WitherStormModRenderers.WITHER_STORM_1_2));
/*  72 */     this.hunchbackP2 = new WitherStormGrowingHunchbackModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_2));
/*  73 */     this.hunchbackP25 = new WitherStormHunchback2_1Model(context.m_174023_(WitherStormModRenderers.WITHER_STORM_2_1));
/*  74 */     this.hunchbackP3 = new WitherStormPregnantHunchbackModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_3));
/*  75 */     this.hunchbackP31 = new WitherStormHunchback3_1Model(context.m_174023_(WitherStormModRenderers.WITHER_STORM_3_1));
/*  76 */     this.hunchbackP32 = new WitherStormHunchback3_2Model(context.m_174023_(WitherStormModRenderers.WITHER_STORM_3_2));
/*  77 */     this.destroyerModel = new WitherStormDestroyerModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_4));
/*  78 */     this.intermediateEvolvedDestroyer = new WitherStormIntermediateEvolvedDestroyerModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_4_5));
/*  79 */     this.evolvedDestroyerModel = new WitherStormEvolvedDestroyerModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_5));
/*  80 */     this.intermediateDevourerModel = new WitherStormIntermediateDevourerModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_5_5));
/*  81 */     this.devourerModel = new WitherStormDevourerModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_6));
/*  82 */     this.intermediateEvolvedDevourerModel = new WitherStormIntermediateEvolvedDevourerModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_6_5));
/*  83 */     this.evolvedDevourerModel = new WitherStormEvolvedDevourerModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_7));
/*  84 */     this.dismantledModel = new WitherStormDismantledModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_DISMANTLED));
/*  85 */     this.tornModel = new WitherStormTornEvolvedDevourerModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_TORN));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(WitherStormEntity entity, float yRot, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
/*  91 */     super.render(entity, yRot, partialTicks, stack, buffer, packedLight);
/*  92 */     if (entity.getDeathTime() > 0) {
/*     */       
/*  94 */       float f1 = (entity.getDeathTime() + partialTicks) / 200.0F;
/*  95 */       float f2 = Math.min((f1 > 1.6F) ? ((f1 - 1.6F) / 0.2F) : 0.0F, 1.0F);
/*  96 */       Random random = new Random(382L);
/*  97 */       VertexConsumer builder = buffer.m_6299_(RenderType.m_110502_());
/*  98 */       stack.m_85836_();
/*  99 */       stack.m_85837_(0.0D, entity.getUnmodifiedHeight() / 2.0D, 0.0D);
/*     */       
/* 101 */       for (int i = 0; i < (f1 + f1 * f1) / 2.0F * 60.0F; i++) {
/*     */         
/* 103 */         stack.m_252781_(Axis.f_252529_.m_252977_(random.nextFloat() * 360.0F));
/* 104 */         stack.m_252781_(Axis.f_252436_.m_252977_(random.nextFloat() * 360.0F));
/* 105 */         stack.m_252781_(Axis.f_252403_.m_252977_(random.nextFloat() * 360.0F));
/* 106 */         stack.m_252781_(Axis.f_252529_.m_252977_(random.nextFloat() * 360.0F));
/* 107 */         stack.m_252781_(Axis.f_252436_.m_252977_(random.nextFloat() * 360.0F));
/* 108 */         stack.m_252781_(Axis.f_252403_.m_252977_(random.nextFloat() * 360.0F + f1 * 90.0F));
/* 109 */         float f3 = random.nextFloat() * entity.getUnmodifiedSize() / 1.5F * 2.5F + 5.0F + f2 * 10.0F;
/* 110 */         float f4 = random.nextFloat() * 10.0F + 1.0F + f2 * 2.0F;
/* 111 */         Matrix4f matrix4f = stack.m_85850_().m_252922_();
/* 112 */         int k = (int)(255.0F * (1.0F - f2));
/* 113 */         builder.m_252986_(matrix4f, 0.0F, 0.0F, 0.0F).m_6122_(255, 255, 255, k).m_5752_();
/* 114 */         builder.m_252986_(matrix4f, 0.0F, 0.0F, 0.0F).m_6122_(255, 255, 255, k).m_5752_();
/* 115 */         builder.m_252986_(matrix4f, -((float)(Math.sqrt(3.0D) / 2.0D)) * f4, f3, -0.5F * f4).m_6122_(255, 0, 255, 0).m_5752_();
/* 116 */         builder.m_252986_(matrix4f, (float)(Math.sqrt(3.0D) / 2.0D) * f4, f3, -0.5F * f4).m_6122_(255, 0, 255, 0).m_5752_();
/* 117 */         builder.m_252986_(matrix4f, 0.0F, 0.0F, 0.0F).m_6122_(255, 255, 255, k).m_5752_();
/* 118 */         builder.m_252986_(matrix4f, 0.0F, 0.0F, 0.0F).m_6122_(255, 255, 255, k).m_5752_();
/* 119 */         builder.m_252986_(matrix4f, (float)(Math.sqrt(3.0D) / 2.0D) * f4, f3, -0.5F * f4).m_6122_(255, 0, 255, 0).m_5752_();
/* 120 */         builder.m_252986_(matrix4f, 0.0F, f3, f4).m_6122_(255, 0, 255, 0).m_5752_();
/* 121 */         builder.m_252986_(matrix4f, 0.0F, 0.0F, 0.0F).m_6122_(255, 255, 255, k).m_5752_();
/* 122 */         builder.m_252986_(matrix4f, 0.0F, 0.0F, 0.0F).m_6122_(255, 255, 255, k).m_5752_();
/* 123 */         builder.m_252986_(matrix4f, 0.0F, f3, f4).m_6122_(255, 0, 255, 0).m_5752_();
/* 124 */         builder.m_252986_(matrix4f, -((float)(Math.sqrt(3.0D) / 2.0D)) * f4, f3, -0.5F * f4).m_6122_(255, 0, 255, 0).m_5752_();
/*     */       } 
/*     */       
/* 127 */       stack.m_85849_();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractWitherStormModel<WitherStormEntity> fetchModel(WitherStormEntity entity) {
/* 134 */     int phase = entity.getPhase();
/* 135 */     int consumptionAmountForPhase = entity.getConsumptionAmountForPhase(phase);
/* 136 */     int consumedEntities = entity.getConsumedEntities();
/* 137 */     if (phase == 1) {
/*     */       
/* 139 */       if (consumedEntities >= entity.adjustAmountForEvolutionSpeed(250))
/* 140 */         return (AbstractWitherStormModel<WitherStormEntity>)this.hunchbackP15; 
/* 141 */       if (consumedEntities >= entity.adjustAmountForEvolutionSpeed(150))
/* 142 */         return (AbstractWitherStormModel<WitherStormEntity>)this.hunchbackP125; 
/* 143 */       if (consumptionAmountForPhase >= consumedEntities)
/* 144 */         return (AbstractWitherStormModel<WitherStormEntity>)this.hunchbackP1; 
/*     */     } else {
/* 146 */       if (phase == 2) {
/*     */         
/* 148 */         if (consumedEntities >= entity.adjustAmountForEvolutionSpeed(800)) {
/* 149 */           return (AbstractWitherStormModel<WitherStormEntity>)this.hunchbackP25;
/*     */         }
/* 151 */         return (AbstractWitherStormModel<WitherStormEntity>)this.hunchbackP2;
/*     */       } 
/* 153 */       if (phase == 3) {
/*     */         
/* 155 */         if (consumedEntities >= entity.adjustAmountForEvolutionSpeed(3500))
/* 156 */           return (AbstractWitherStormModel<WitherStormEntity>)this.hunchbackP32; 
/* 157 */         if (consumedEntities >= entity.adjustAmountForEvolutionSpeed(2350)) {
/* 158 */           return (AbstractWitherStormModel<WitherStormEntity>)this.hunchbackP31;
/*     */         }
/* 160 */         return (AbstractWitherStormModel<WitherStormEntity>)this.hunchbackP3;
/*     */       } 
/* 162 */       if (phase == 4) {
/*     */         
/* 164 */         if (consumedEntities <= entity.getSubPhaseRequirement(phase)) {
/* 165 */           return (AbstractWitherStormModel<WitherStormEntity>)this.destroyerModel;
/*     */         }
/* 167 */         return (AbstractWitherStormModel<WitherStormEntity>)this.intermediateEvolvedDestroyer;
/*     */       } 
/* 169 */       if (phase == 5) {
/*     */         
/* 171 */         if (consumptionAmountForPhase < consumedEntities)
/*     */         {
/* 173 */           return (AbstractWitherStormModel<WitherStormEntity>)this.devourerModel;
/*     */         }
/*     */ 
/*     */         
/* 177 */         if (consumedEntities <= entity.getSubPhaseRequirement(phase)) {
/* 178 */           return (AbstractWitherStormModel<WitherStormEntity>)this.evolvedDestroyerModel;
/*     */         }
/* 180 */         return (AbstractWitherStormModel<WitherStormEntity>)this.intermediateDevourerModel;
/*     */       } 
/*     */       
/* 183 */       if (phase == 6) {
/*     */         
/* 185 */         if (consumedEntities <= entity.getSubPhaseRequirement(phase)) {
/* 186 */           return (AbstractWitherStormModel<WitherStormEntity>)this.dismantledModel;
/*     */         }
/* 188 */         return (AbstractWitherStormModel<WitherStormEntity>)this.intermediateEvolvedDevourerModel;
/*     */       } 
/* 190 */       if (phase == 7) {
/*     */         
/* 192 */         if (!entity.isBeingTornApart()) {
/* 193 */           return (AbstractWitherStormModel<WitherStormEntity>)this.evolvedDevourerModel;
/*     */         }
/* 195 */         return (AbstractWitherStormModel<WitherStormEntity>)this.tornModel;
/*     */       } 
/*     */ 
/*     */       
/* 199 */       return (AbstractWitherStormModel<WitherStormEntity>)this.commandBlockModel;
/*     */     } 
/* 201 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\WitherStormRenderer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
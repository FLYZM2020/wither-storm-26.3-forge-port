/*     */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass;
/*     */ 
/*     */ import net.minecraft.client.model.geom.PartPose;
/*     */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*     */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*     */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LowResEvolvedDestroyerBodyModel
/*     */ {
/*     */   public static PartDefinition createBodyModel(PartDefinition root, float texScale) {
/*  13 */     PartDefinition body = root.addOrReplaceChild("lowResMass", CubeListBuilder.create().texOffs(0, 24).addBox(-3.0F, -6.0F, -3.0F, 5.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  14 */         .texOffs(0, 24).addBox(-7.0F, -29.0F, -8.0F, 13.0F, 13.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  15 */         .texOffs(0, 24).addBox(6.0F, -29.0F, -8.0F, 1.0F, 13.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  16 */         .texOffs(0, 24).addBox(-19.0F, -25.0F, -4.0F, 1.0F, 15.0F, 7.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  17 */         .texOffs(0, 24).mirror().addBox(18.0F, -25.0F, -4.0F, 1.0F, 15.0F, 7.0F, new CubeDeformation(0.0F), texScale, texScale).mirror(false)
/*  18 */         .texOffs(0, 24).addBox(-7.0F, -24.0F, 3.0F, 13.0F, 14.0F, 10.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  19 */         .texOffs(0, 24).addBox(-7.0F, -10.0F, 3.0F, 13.0F, 2.0F, 10.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  20 */         .texOffs(0, 24).addBox(-3.0F, 12.0F, -3.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  21 */         .texOffs(0, 24).addBox(-2.0F, 16.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  22 */         .texOffs(0, 24).addBox(0.0F, 15.0F, 0.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  23 */         .texOffs(0, 24).addBox(-1.0F, 16.0F, -2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  24 */         .texOffs(0, 24).addBox(-1.0F, 16.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  25 */         .texOffs(0, 24).addBox(-1.0F, 15.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  26 */         .texOffs(0, 24).addBox(-2.0F, 15.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  27 */         .texOffs(0, 24).addBox(-3.0F, 15.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  28 */         .texOffs(0, 24).addBox(-2.0F, 15.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  29 */         .texOffs(0, 24).addBox(1.0F, 15.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  30 */         .texOffs(0, 24).addBox(0.0F, 15.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  31 */         .texOffs(0, 24).addBox(1.0F, 16.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  32 */         .texOffs(0, 24).addBox(-1.0F, 15.0F, 2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  33 */         .texOffs(0, 24).addBox(0.0F, 15.0F, 1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.offset(0.0F, -15.0F, 0.0F));
/*     */     
/*  35 */     body.addOrReplaceChild("part1", CubeListBuilder.create().texOffs(0, 24).addBox(-1.5F, -3.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.offsetAndRotation(-0.5F, 4.0F, -6.0F, 0.1309F, 0.0F, 0.0F));
/*     */     
/*  37 */     body.addOrReplaceChild("part2", CubeListBuilder.create().texOffs(0, 24).addBox(-0.5F, -3.0F, -2.0F, 5.0F, 5.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  38 */         .texOffs(0, 24).addBox(-0.5F, -21.0F, -2.0F, 5.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.offsetAndRotation(-0.5F, 9.0F, -1.0F, 0.0F, 0.0F, 0.3927F));
/*     */     
/*  40 */     body.addOrReplaceChild("part3", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(-2.4965F, -8.2953F, -1.0F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F), texScale, texScale).mirror(false), PartPose.offsetAndRotation(4.5F, -2.0F, -1.0F, 1.2519F, -0.2368F, 0.8109F));
/*     */     
/*  42 */     body.addOrReplaceChild("part4", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(-3.4965F, -4.2953F, -2.0F, 11.0F, 4.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale).mirror(false), PartPose.offsetAndRotation(4.5F, -2.0F, -1.0F, 0.0F, 0.0F, -1.1345F));
/*     */     
/*  44 */     body.addOrReplaceChild("part5", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(-3.0341F, -3.1877F, -4.5034F, 6.0F, 7.0F, 12.0F, new CubeDeformation(0.0F), texScale, texScale).mirror(false), PartPose.offsetAndRotation(0.5F, 0.0F, -3.0F, 1.8785F, -0.318F, 0.8876F));
/*     */     
/*  46 */     body.addOrReplaceChild("part6", CubeListBuilder.create().texOffs(0, 24).addBox(-3.4217F, -10.2414F, -9.7983F, 6.0F, 7.0F, 13.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.offsetAndRotation(-7.5F, -5.0F, -1.0F, 2.2689F, 0.0F, -1.0036F));
/*     */     
/*  48 */     body.addOrReplaceChild("part7", CubeListBuilder.create().texOffs(0, 24).addBox(-3.4217F, -10.2414F, -5.7983F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.offsetAndRotation(-7.5F, -5.0F, -1.0F, 1.4399F, 0.0F, -1.0036F));
/*     */     
/*  50 */     body.addOrReplaceChild("part8", CubeListBuilder.create().texOffs(0, 24).addBox(-1.5035F, -7.2953F, -2.0F, 5.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.offsetAndRotation(-4.5F, -2.0F, -1.0F, 0.0F, 0.0F, -0.5672F));
/*     */     
/*  52 */     body.addOrReplaceChild("part9", CubeListBuilder.create().texOffs(0, 24).addBox(-1.5035F, -9.2953F, -2.0F, 5.0F, 10.0F, 9.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.offsetAndRotation(-4.5F, -2.0F, -1.0F, -0.7144F, 0.1674F, -1.3419F));
/*     */     
/*  54 */     body.addOrReplaceChild("part10", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(-3.4965F, -9.2953F, -2.0F, 5.0F, 10.0F, 9.0F, new CubeDeformation(0.0F), texScale, texScale).mirror(false), PartPose.offsetAndRotation(4.5F, -2.0F, -1.0F, -0.7144F, -0.1674F, 1.3419F));
/*     */     
/*  56 */     body.addOrReplaceChild("part11", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(-3.4965F, -16.2953F, -7.0F, 6.0F, 13.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale).mirror(false), PartPose.offsetAndRotation(4.5F, -2.0F, -1.0F, 0.0F, 0.0F, 0.9599F));
/*     */     
/*  58 */     body.addOrReplaceChild("part12", CubeListBuilder.create().texOffs(0, 24).addBox(-2.5035F, -16.2953F, -7.0F, 6.0F, 13.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.offsetAndRotation(-4.5F, -2.0F, -1.0F, 0.0F, 0.0F, -0.9599F));
/*     */     
/*  60 */     body.addOrReplaceChild("part13", CubeListBuilder.create().texOffs(0, 24).addBox(-4.5F, 1.0F, -2.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  61 */         .texOffs(0, 24).addBox(-4.5F, -17.0F, -2.0F, 5.0F, 18.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.offsetAndRotation(-0.5F, 9.0F, -1.0F, 0.0F, 0.0F, -0.4363F));
/*     */     
/*  63 */     body.addOrReplaceChild("part14", CubeListBuilder.create().texOffs(0, 24).addBox(-0.5F, -8.8659F, -2.4041F, 3.0F, 9.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.offsetAndRotation(-2.5F, 6.5F, -3.0F, 0.3295F, 0.1172F, -0.3295F));
/*     */     
/*  65 */     body.addOrReplaceChild("part15", CubeListBuilder.create().texOffs(0, 24).addBox(-2.5F, -18.0F, -3.0F, 5.0F, 17.0F, 6.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.offsetAndRotation(-0.5F, 13.0F, 0.0F, 0.3491F, 0.0F, 0.0F));
/*     */     
/*  67 */     body.addOrReplaceChild("part16", CubeListBuilder.create().texOffs(0, 24).addBox(-2.5F, -2.0F, -2.0F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.offsetAndRotation(-0.5F, 15.0F, 0.0F, -0.2182F, 0.0F, 0.0F));
/*     */     
/*  69 */     body.addOrReplaceChild("part17", CubeListBuilder.create().texOffs(0, 24).addBox(-2.5F, -21.6493F, 0.9289F, 5.0F, 19.0F, 5.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.offsetAndRotation(-0.5F, 15.0F, -3.0F, -0.2182F, 0.0F, 0.0F));
/*     */     
/*  71 */     body.addOrReplaceChild("part18", CubeListBuilder.create().texOffs(0, 24).addBox(10.5F, -10.0495F, -7.867F, 1.0F, 9.0F, 3.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  72 */         .texOffs(0, 24).addBox(-10.5F, -10.0495F, -7.867F, 21.0F, 9.0F, 3.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.offsetAndRotation(-0.5F, -19.0F, 0.0F, 0.1309F, 0.0F, 0.0F));
/*     */     
/*  74 */     body.addOrReplaceChild("part19", CubeListBuilder.create().texOffs(0, 24).addBox(-10.5F, -14.0F, -9.0F, 22.0F, 18.0F, 2.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.offsetAndRotation(-0.5F, -6.0F, 0.0F, -0.0873F, 0.0F, 0.0F));
/*     */     
/*  76 */     body.addOrReplaceChild("part20", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(-1.0F, 1.0F, 1.0F, 2.0F, 5.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale).mirror(false)
/*  77 */         .texOffs(0, 24).mirror().addBox(-1.0F, -11.0F, 1.0F, 2.0F, 12.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale).mirror(false)
/*  78 */         .texOffs(0, 24).mirror().addBox(-14.0F, 2.0F, 1.0F, 13.0F, 4.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale).mirror(false)
/*  79 */         .texOffs(0, 24).mirror().addBox(-14.0F, -11.0F, 1.0F, 13.0F, 13.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale).mirror(false), PartPose.offsetAndRotation(0.0F, -14.0F, 2.0F, 0.0F, -0.6109F, 0.0F));
/*     */     
/*  81 */     body.addOrReplaceChild("part21", CubeListBuilder.create().texOffs(0, 24).addBox(-1.0F, 2.0F, 1.0F, 2.0F, 4.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  82 */         .texOffs(0, 24).addBox(1.0F, 2.0F, 1.0F, 13.0F, 4.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  83 */         .texOffs(0, 24).addBox(-1.0F, -11.0F, 1.0F, 2.0F, 13.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  84 */         .texOffs(0, 24).addBox(1.0F, -11.0F, 1.0F, 13.0F, 13.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.offsetAndRotation(0.0F, -14.0F, 2.0F, 0.0F, 0.6109F, 0.0F));
/*     */     
/*  86 */     body.addOrReplaceChild("part22", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(0.5F, 0.0F, -2.5F, 13.0F, 5.0F, 7.0F, new CubeDeformation(0.0F), texScale, texScale).mirror(false), PartPose.offsetAndRotation(6.5F, -29.0F, -1.5F, 0.0F, 0.0F, 0.2618F));
/*     */     
/*  88 */     body.addOrReplaceChild("part23", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(0.5F, 0.0F, 0.5F, 13.0F, 5.0F, 4.0F, new CubeDeformation(0.0F), texScale, texScale).mirror(false), PartPose.offsetAndRotation(6.5F, -29.0F, -8.5F, 0.0F, -0.2182F, 0.2618F));
/*     */     
/*  90 */     body.addOrReplaceChild("part24", CubeListBuilder.create().texOffs(0, 24).addBox(-13.5F, 0.0F, 0.5F, 13.0F, 5.0F, 4.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.offsetAndRotation(-6.5F, -29.0F, -8.5F, 0.0F, 0.2182F, -0.2618F));
/*     */     
/*  92 */     body.addOrReplaceChild("part25", CubeListBuilder.create().texOffs(0, 24).addBox(-13.5F, 0.0F, -2.5F, 13.0F, 5.0F, 7.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.offsetAndRotation(-6.5F, -29.0F, -1.5F, 0.0F, 0.0F, -0.2618F));
/*     */     
/*  94 */     body.addOrReplaceChild("part26", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(5.5F, -18.0F, -12.0F, 9.0F, 13.0F, 10.0F, new CubeDeformation(0.0F), texScale, texScale).mirror(false), PartPose.offsetAndRotation(0.5F, -6.0F, 0.0F, 0.0F, -0.4363F, 0.0F));
/*     */     
/*  96 */     body.addOrReplaceChild("part27", CubeListBuilder.create().texOffs(0, 24).addBox(-14.5F, -18.0F, -12.0F, 9.0F, 13.0F, 10.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.offsetAndRotation(-0.5F, -6.0F, 0.0F, 0.0F, 0.4363F, 0.0F));
/*     */     
/*  98 */     body.addOrReplaceChild("part28", CubeListBuilder.create().texOffs(0, 24).addBox(-6.5F, -10.0F, -8.0F, 13.0F, 13.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale)
/*  99 */         .texOffs(0, 24).addBox(-6.5F, -22.0F, -8.0F, 13.0F, 12.0F, 11.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.offsetAndRotation(-0.5F, -6.0F, 0.0F, -0.48F, 0.0F, 0.0F));
/*     */     
/* 101 */     body.addOrReplaceChild("part29", CubeListBuilder.create().texOffs(0, 24).addBox(-6.5F, 14.0F, -8.0F, 13.0F, 2.0F, 8.0F, new CubeDeformation(0.0F), texScale, texScale)
/* 102 */         .texOffs(0, 24).addBox(-6.5F, 0.0F, -10.0F, 13.0F, 14.0F, 10.0F, new CubeDeformation(0.0F), texScale, texScale), PartPose.offsetAndRotation(-0.5F, -8.0F, 13.0F, -0.6109F, 0.0F, 0.0F));
/*     */     
/* 104 */     return body;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\mass\LowResEvolvedDestroyerBodyModel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
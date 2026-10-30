/*    */ package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass;
/*    */ 
/*    */ import net.minecraft.client.model.geom.PartPose;
/*    */ import net.minecraft.client.model.geom.builders.CubeDeformation;
/*    */ import net.minecraft.client.model.geom.builders.CubeListBuilder;
/*    */ import net.minecraft.client.model.geom.builders.PartDefinition;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WSHunchback2_1
/*    */ {
/*    */   public static PartDefinition createBodyModel(PartDefinition root, float texScale) {
/* 13 */     return root.addOrReplaceChild("mass", CubeListBuilder.create().texOffs(0, 140).addBox(-4.0F, -27.0F, 1.0F, 10.0F, 10.0F, 10.0F, CubeDeformation.NONE)
/* 14 */         .texOffs(0, 144).addBox(2.0F, -22.0F, 1.0F, 8.0F, 8.0F, 8.0F, CubeDeformation.NONE)
/* 15 */         .texOffs(0, 144).addBox(-6.0F, -22.0F, 0.0F, 8.0F, 8.0F, 8.0F, CubeDeformation.NONE)
/* 16 */         .texOffs(0, 144).addBox(4.0F, -29.0F, 2.0F, 8.0F, 8.0F, 8.0F, CubeDeformation.NONE)
/* 17 */         .texOffs(0, 148).addBox(10.0F, -27.0F, 2.0F, 6.0F, 6.0F, 6.0F, CubeDeformation.NONE)
/* 18 */         .texOffs(0, 148).addBox(-1.0F, -30.0F, 3.0F, 6.0F, 6.0F, 6.0F, CubeDeformation.NONE)
/* 19 */         .texOffs(0, 146).addBox(-11.0F, -26.0F, 2.0F, 7.0F, 7.0F, 7.0F, CubeDeformation.NONE)
/* 20 */         .texOffs(0, 148).addBox(-1.0F, -26.0F, 6.0F, 6.0F, 6.0F, 6.0F, CubeDeformation.NONE)
/* 21 */         .texOffs(0, 148).addBox(-2.0F, -12.0F, -1.0F, 6.0F, 6.0F, 6.0F, CubeDeformation.NONE)
/* 22 */         .texOffs(0, 148).addBox(0.0F, -15.0F, 0.0F, 6.0F, 6.0F, 6.0F, CubeDeformation.NONE)
/* 23 */         .texOffs(0, 148).addBox(-4.0F, -14.0F, 1.0F, 6.0F, 6.0F, 6.0F, CubeDeformation.NONE)
/* 24 */         .texOffs(0, 148).addBox(-6.0F, -15.0F, -2.0F, 6.0F, 6.0F, 6.0F, CubeDeformation.NONE)
/* 25 */         .texOffs(0, 148).addBox(-9.0F, -19.0F, -3.0F, 6.0F, 6.0F, 6.0F, CubeDeformation.NONE)
/* 26 */         .texOffs(0, 148).addBox(-4.0F, -22.0F, -1.0F, 6.0F, 6.0F, 6.0F, CubeDeformation.NONE)
/* 27 */         .texOffs(0, 148).addBox(-14.0F, -24.0F, 1.0F, 6.0F, 6.0F, 6.0F, CubeDeformation.NONE)
/* 28 */         .texOffs(0, 148).addBox(-10.0F, -22.0F, 0.0F, 6.0F, 6.0F, 6.0F, CubeDeformation.NONE)
/* 29 */         .texOffs(0, 148).addBox(-1.0F, -24.0F, -2.0F, 6.0F, 6.0F, 6.0F, CubeDeformation.NONE)
/* 30 */         .texOffs(0, 152).addBox(10.0F, -22.0F, 2.0F, 4.0F, 4.0F, 4.0F, CubeDeformation.NONE)
/* 31 */         .texOffs(0, 152).addBox(7.0F, -22.0F, 8.0F, 4.0F, 4.0F, 4.0F, CubeDeformation.NONE)
/* 32 */         .texOffs(0, 152).addBox(5.0F, -15.0F, 1.0F, 4.0F, 4.0F, 4.0F, CubeDeformation.NONE)
/* 33 */         .texOffs(0, 152).addBox(8.0F, -20.0F, 1.0F, 4.0F, 4.0F, 4.0F, CubeDeformation.NONE)
/* 34 */         .texOffs(0, 152).addBox(11.0F, -28.0F, 3.0F, 4.0F, 4.0F, 4.0F, CubeDeformation.NONE)
/* 35 */         .texOffs(0, 152).addBox(5.0F, -31.0F, 3.0F, 4.0F, 4.0F, 4.0F, CubeDeformation.NONE)
/* 36 */         .texOffs(0, 152).addBox(7.0F, -28.0F, 7.0F, 4.0F, 4.0F, 4.0F, CubeDeformation.NONE)
/* 37 */         .texOffs(0, 152).addBox(10.0F, -26.0F, 5.0F, 4.0F, 4.0F, 4.0F, CubeDeformation.NONE)
/* 38 */         .texOffs(0, 152).addBox(7.0F, -18.0F, 3.0F, 4.0F, 4.0F, 4.0F, CubeDeformation.NONE)
/* 39 */         .texOffs(0, 152).addBox(-6.0F, -19.0F, 8.0F, 4.0F, 4.0F, 4.0F, CubeDeformation.NONE)
/* 40 */         .texOffs(0, 152).addBox(-3.0F, -9.0F, -1.0F, 4.0F, 4.0F, 4.0F, CubeDeformation.NONE)
/* 41 */         .texOffs(0, 152).addBox(-1.0F, -8.0F, 2.0F, 4.0F, 4.0F, 4.0F, CubeDeformation.NONE)
/* 42 */         .texOffs(0, 152).addBox(-6.0F, -29.0F, 4.0F, 4.0F, 4.0F, 4.0F, CubeDeformation.NONE)
/* 43 */         .texOffs(0, 152).addBox(-10.0F, -27.0F, 3.0F, 4.0F, 4.0F, 4.0F, CubeDeformation.NONE)
/* 44 */         .texOffs(0, 152).addBox(-12.0F, -28.0F, 3.0F, 4.0F, 4.0F, 4.0F, CubeDeformation.NONE)
/* 45 */         .texOffs(0, 152).addBox(-15.0F, -25.0F, 2.0F, 4.0F, 4.0F, 4.0F, CubeDeformation.NONE)
/* 46 */         .texOffs(0, 152).addBox(15.0F, -26.0F, 3.0F, 4.0F, 4.0F, 4.0F, CubeDeformation.NONE)
/* 47 */         .texOffs(0, 152).addBox(-6.0F, -20.0F, -2.0F, 11.0F, 4.0F, 4.0F, CubeDeformation.NONE)
/* 48 */         .texOffs(0, 151).addBox(-2.0F, -8.0F, 0.0F, 4.0F, 5.0F, 4.0F, CubeDeformation.NONE)
/* 49 */         .texOffs(76, 50).addBox(4.0F, -13.0F, -2.0F, 3.0F, 3.0F, 3.0F, CubeDeformation.NONE)
/* 50 */         .texOffs(76, 50).addBox(2.0F, -10.0F, -2.0F, 3.0F, 3.0F, 3.0F, CubeDeformation.NONE)
/* 51 */         .texOffs(0, 152).addBox(6.0F, -19.0F, -2.0F, 4.0F, 4.0F, 4.0F, CubeDeformation.NONE)
/* 52 */         .texOffs(0, 152).addBox(2.0F, -21.0F, -2.0F, 4.0F, 4.0F, 4.0F, CubeDeformation.NONE)
/* 53 */         .texOffs(0, 154).addBox(-1.0F, -3.0F, 1.0F, 2.0F, 3.0F, 2.0F, CubeDeformation.NONE)
/* 54 */         .texOffs(0, 155).addBox(0.0F, 0.0F, 2.0F, 1.0F, 4.0F, 1.0F, CubeDeformation.NONE)
/* 55 */         .texOffs(0, 155).addBox(-1.0F, 0.0F, 1.0F, 1.0F, 2.0F, 1.0F, CubeDeformation.NONE)
/* 56 */         .texOffs(70, 52).addBox(2.0F, -18.0F, -4.0F, 4.0F, 4.0F, 4.0F, CubeDeformation.NONE)
/* 57 */         .texOffs(70, 52).addBox(-6.0F, -17.0F, -4.0F, 4.0F, 4.0F, 4.0F, CubeDeformation.NONE)
/* 58 */         .texOffs(70, 52).addBox(-4.0F, -11.0F, -2.0F, 4.0F, 4.0F, 4.0F, CubeDeformation.NONE), PartPose.offset(0.0F, 24.0F, 0.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\renderer\entity\model\witherstorm\mass\WSHunchback2_1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
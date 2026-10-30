/*     */ package nonamecrackers2.witherstormmod.common.item;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.Tier;
/*     */ import net.minecraft.world.item.TooltipFlag;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.shapes.VoxelShape;
/*     */ import nonamecrackers2.witherstormmod.common.entity.TentacleSpike;
/*     */ 
/*     */ public class EyeOfTheStormItem
/*     */   extends CommandBlockSwordItem {
/*  26 */   public static final UUID DAMAGE_MODIFIER_ID = UUID.fromString("823350e7-4c91-4a1f-8c01-8735113f066e");
/*     */   
/*     */   public static final String ENTITY_HEALTH_RATIO = "EntityHealthRatio";
/*     */   
/*     */   public EyeOfTheStormItem(Tier tier, int damage, float attackSpeed, Item.Properties properties) {
/*  31 */     super(tier, damage, attackSpeed, properties);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_6883_(ItemStack stack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
/*  37 */     CompoundTag tag = stack.m_41784_();
/*  38 */     if (entity instanceof LivingEntity) { LivingEntity living = (LivingEntity)entity; if (!(living instanceof Player) || !(((Player)living).m_150110_()).f_35937_) {
/*  39 */         tag.m_128350_("EntityHealthRatio", living.m_21223_() / living.m_21233_()); return;
/*     */       }  }
/*  41 */      tag.m_128473_("EntityHealthRatio");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7373_(ItemStack stack, Level level, List<Component> text, TooltipFlag flag) {
/*  47 */     text.add(Component.m_237115_("item.witherstormmod.eye_of_the_storm.author").m_130940_(ChatFormatting.DARK_GRAY));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_7579_(ItemStack stack, LivingEntity hit, LivingEntity living) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: aload_2
/*     */     //   3: aload_3
/*     */     //   4: invokespecial m_7579_ : (Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/LivingEntity;)Z
/*     */     //   7: ifeq -> 269
/*     */     //   10: aload_3
/*     */     //   11: invokevirtual m_217043_ : ()Lnet/minecraft/util/RandomSource;
/*     */     //   14: invokeinterface m_188501_ : ()F
/*     */     //   19: aload_3
/*     */     //   20: invokevirtual m_21223_ : ()F
/*     */     //   23: aload_3
/*     */     //   24: invokevirtual m_21233_ : ()F
/*     */     //   27: fdiv
/*     */     //   28: fcmpl
/*     */     //   29: ifgt -> 56
/*     */     //   32: aload_3
/*     */     //   33: instanceof net/minecraft/world/entity/player/Player
/*     */     //   36: ifeq -> 267
/*     */     //   39: aload_3
/*     */     //   40: checkcast net/minecraft/world/entity/player/Player
/*     */     //   43: astore #4
/*     */     //   45: aload #4
/*     */     //   47: invokevirtual m_150110_ : ()Lnet/minecraft/world/entity/player/Abilities;
/*     */     //   50: getfield f_35937_ : Z
/*     */     //   53: ifeq -> 267
/*     */     //   56: aload_2
/*     */     //   57: invokevirtual m_20186_ : ()D
/*     */     //   60: aload_3
/*     */     //   61: invokevirtual m_20186_ : ()D
/*     */     //   64: invokestatic min : (DD)D
/*     */     //   67: dstore #5
/*     */     //   69: aload_2
/*     */     //   70: invokevirtual m_20186_ : ()D
/*     */     //   73: aload_3
/*     */     //   74: invokevirtual m_20186_ : ()D
/*     */     //   77: invokestatic max : (DD)D
/*     */     //   80: dconst_1
/*     */     //   81: dadd
/*     */     //   82: dstore #7
/*     */     //   84: iconst_5
/*     */     //   85: istore #9
/*     */     //   87: iconst_2
/*     */     //   88: istore #10
/*     */     //   90: aload_2
/*     */     //   91: invokevirtual m_20189_ : ()D
/*     */     //   94: aload_3
/*     */     //   95: invokevirtual m_20189_ : ()D
/*     */     //   98: dsub
/*     */     //   99: aload_2
/*     */     //   100: invokevirtual m_20185_ : ()D
/*     */     //   103: aload_3
/*     */     //   104: invokevirtual m_20185_ : ()D
/*     */     //   107: dsub
/*     */     //   108: invokestatic m_14136_ : (DD)D
/*     */     //   111: d2f
/*     */     //   112: fstore #11
/*     */     //   114: aload_1
/*     */     //   115: aload_2
/*     */     //   116: invokevirtual m_6336_ : ()Lnet/minecraft/world/entity/MobType;
/*     */     //   119: invokestatic m_44833_ : (Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/MobType;)F
/*     */     //   122: fstore #12
/*     */     //   124: aload_3
/*     */     //   125: aload_2
/*     */     //   126: invokevirtual m_20185_ : ()D
/*     */     //   129: aload_2
/*     */     //   130: invokevirtual m_20189_ : ()D
/*     */     //   133: dload #5
/*     */     //   135: dload #7
/*     */     //   137: fload #11
/*     */     //   139: iconst_0
/*     */     //   140: fload #12
/*     */     //   142: invokestatic createSpike : (Lnet/minecraft/world/entity/LivingEntity;DDDDFIF)V
/*     */     //   145: iconst_0
/*     */     //   146: istore #13
/*     */     //   148: iload #13
/*     */     //   150: iload #9
/*     */     //   152: if_icmpge -> 267
/*     */     //   155: iload #13
/*     */     //   157: i2f
/*     */     //   158: iload #9
/*     */     //   160: i2f
/*     */     //   161: fdiv
/*     */     //   162: ldc 3.1415927
/*     */     //   164: fmul
/*     */     //   165: fconst_2
/*     */     //   166: fmul
/*     */     //   167: fload #11
/*     */     //   169: fadd
/*     */     //   170: fstore #14
/*     */     //   172: iconst_0
/*     */     //   173: istore #15
/*     */     //   175: iload #15
/*     */     //   177: iload #10
/*     */     //   179: if_icmpge -> 261
/*     */     //   182: aload_2
/*     */     //   183: invokevirtual m_20185_ : ()D
/*     */     //   186: fload #14
/*     */     //   188: invokestatic m_14089_ : (F)F
/*     */     //   191: f2d
/*     */     //   192: iload #15
/*     */     //   194: iconst_1
/*     */     //   195: iadd
/*     */     //   196: i2d
/*     */     //   197: dmul
/*     */     //   198: dadd
/*     */     //   199: dstore #16
/*     */     //   201: aload_2
/*     */     //   202: invokevirtual m_20189_ : ()D
/*     */     //   205: fload #14
/*     */     //   207: invokestatic m_14031_ : (F)F
/*     */     //   210: f2d
/*     */     //   211: iload #15
/*     */     //   213: iconst_1
/*     */     //   214: iadd
/*     */     //   215: i2d
/*     */     //   216: dmul
/*     */     //   217: dadd
/*     */     //   218: dstore #18
/*     */     //   220: aload_3
/*     */     //   221: dload #16
/*     */     //   223: dload #18
/*     */     //   225: dload #5
/*     */     //   227: dload #7
/*     */     //   229: fload #14
/*     */     //   231: iload #15
/*     */     //   233: iconst_1
/*     */     //   234: iadd
/*     */     //   235: iconst_5
/*     */     //   236: imul
/*     */     //   237: aload_3
/*     */     //   238: invokevirtual m_217043_ : ()Lnet/minecraft/util/RandomSource;
/*     */     //   241: iconst_4
/*     */     //   242: invokeinterface m_188503_ : (I)I
/*     */     //   247: iadd
/*     */     //   248: iconst_2
/*     */     //   249: isub
/*     */     //   250: fload #12
/*     */     //   252: invokestatic createSpike : (Lnet/minecraft/world/entity/LivingEntity;DDDDFIF)V
/*     */     //   255: iinc #15, 1
/*     */     //   258: goto -> 175
/*     */     //   261: iinc #13, 1
/*     */     //   264: goto -> 148
/*     */     //   267: iconst_1
/*     */     //   268: ireturn
/*     */     //   269: iconst_0
/*     */     //   270: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #53	-> 0
/*     */     //   #55	-> 10
/*     */     //   #57	-> 56
/*     */     //   #58	-> 69
/*     */     //   #59	-> 84
/*     */     //   #60	-> 87
/*     */     //   #61	-> 90
/*     */     //   #62	-> 114
/*     */     //   #63	-> 124
/*     */     //   #64	-> 145
/*     */     //   #66	-> 155
/*     */     //   #67	-> 172
/*     */     //   #69	-> 182
/*     */     //   #70	-> 201
/*     */     //   #71	-> 220
/*     */     //   #67	-> 255
/*     */     //   #64	-> 261
/*     */     //   #75	-> 267
/*     */     //   #79	-> 269
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   45	11	4	player	Lnet/minecraft/world/entity/player/Player;
/*     */     //   201	54	16	x	D
/*     */     //   220	35	18	z	D
/*     */     //   175	86	15	j	I
/*     */     //   172	89	14	angle	F
/*     */     //   148	119	13	i	I
/*     */     //   69	198	5	minHeight	D
/*     */     //   84	183	7	maxHeight	D
/*     */     //   87	180	9	total	I
/*     */     //   90	177	10	spread	I
/*     */     //   114	153	11	hitAngle	F
/*     */     //   124	143	12	damageModifier	F
/*     */     //   0	271	0	this	Lnonamecrackers2/witherstormmod/common/item/EyeOfTheStormItem;
/*     */     //   0	271	1	stack	Lnet/minecraft/world/item/ItemStack;
/*     */     //   0	271	2	hit	Lnet/minecraft/world/entity/LivingEntity;
/*     */     //   0	271	3	living	Lnet/minecraft/world/entity/LivingEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void createSpike(LivingEntity owner, double x, double z, double minHeight, double maxHeight, float yRot, int delay, float damageModifier) {
/*  85 */     BlockPos blockPos = BlockPos.m_274561_(x, maxHeight, z);
/*  86 */     boolean flag = false;
/*  87 */     double d0 = 0.0D;
/*     */ 
/*     */     
/*     */     do {
/*  91 */       BlockPos below = blockPos.m_7495_();
/*  92 */       BlockState state = owner.m_9236_().m_8055_(below);
/*  93 */       if (state.m_60783_((BlockGetter)owner.m_9236_(), below, Direction.UP)) {
/*     */         
/*  95 */         if (!owner.m_9236_().m_46859_(blockPos)) {
/*     */           
/*  97 */           BlockState state1 = owner.m_9236_().m_8055_(blockPos);
/*  98 */           VoxelShape shape = state1.m_60812_((BlockGetter)owner.m_9236_(), blockPos);
/*  99 */           if (!shape.m_83281_()) {
/* 100 */             d0 = shape.m_83297_(Direction.Axis.Y);
/*     */           }
/*     */         } 
/* 103 */         flag = true;
/*     */         
/*     */         break;
/*     */       } 
/* 107 */       blockPos = blockPos.m_7495_();
/*     */     }
/* 109 */     while (blockPos.m_123342_() >= Mth.m_14107_(minHeight) - 1);
/*     */     
/* 111 */     if (flag)
/* 112 */       owner.m_9236_().m_7967_((Entity)new TentacleSpike(owner.m_9236_(), x, blockPos.m_123342_() + d0, z, yRot, delay, owner, damageModifier)); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\EyeOfTheStormItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
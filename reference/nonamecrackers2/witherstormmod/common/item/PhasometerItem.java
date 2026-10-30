/*     */ package nonamecrackers2.witherstormmod.common.item;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.world.InteractionHand;
/*     */ import net.minecraft.world.InteractionResultHolder;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.entity.projectile.ProjectileUtil;
/*     */ import net.minecraft.world.item.Item;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.item.SpyglassItem;
/*     */ import net.minecraft.world.item.TooltipFlag;
/*     */ import net.minecraft.world.level.BlockGetter;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.AABB;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraft.world.phys.EntityHitResult;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
/*     */ import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager;
/*     */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*     */ import nonamecrackers2.witherstormmod.common.util.WorldUtil;
/*     */ 
/*     */ 
/*     */ public class PhasometerItem
/*     */   extends SpyglassItem
/*     */ {
/*     */   public static final String UPGRADED = "IsUpgraded";
/*     */   
/*     */   public PhasometerItem(Item.Properties properties) {
/*  39 */     super(properties);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InteractionResultHolder<ItemStack> m_7203_(Level level, Player player, InteractionHand hand) {
/*  45 */     clearDataTags(player.m_21120_(hand).m_41784_(), new DataEntry[0]);
/*  46 */     return super.m_7203_(level, player, hand);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_5929_(Level level, LivingEntity entity, ItemStack item, int count) {
/*  52 */     if (!level.f_46443_) {
/*     */       
/*  54 */       Vec3 pos = entity.m_146892_();
/*  55 */       Vec3 view = entity.m_20252_(1.0F);
/*  56 */       Vec3 end = pos.m_82549_(view.m_82490_(10000.0D));
/*  57 */       EntityHitResult result = ProjectileUtil.m_150175_(entity.m_9236_(), (Entity)entity, pos, end, (new AABB(pos, end)).m_82400_(1.0D), e -> !e.m_5833_(), 0.0F);
/*  58 */       CompoundTag tag = item.m_41784_();
/*  59 */       if (result != null && result.m_6662_() == HitResult.Type.ENTITY) {
/*     */         
/*  61 */         Entity hit = result.m_82443_();
/*  62 */         if (hit instanceof WitherStormEntity) { WitherStormEntity storm = (WitherStormEntity)hit; if (storm.m_6095_() == WitherStormModEntityTypes.WITHER_STORM.get()) {
/*     */             
/*  64 */             BlockHitResult obstruction = WorldUtil.raycast((Entity)entity, (Entity)storm, 150.0D);
/*  65 */             if (obstruction.m_6662_() == HitResult.Type.MISS) {
/*     */               
/*  67 */               for (DataEntry entry : DataEntry.values()) {
/*     */                 
/*  69 */                 if (!entry.requiresUpgraded || isUpgraded(item))
/*  70 */                   entry.apply(tag, storm); 
/*     */               } 
/*  72 */               tag.m_128379_(DataEntry.OBSTRUCTED.tagName, false);
/*     */               
/*     */               return;
/*     */             } 
/*     */             
/*  77 */             BlockState state = level.m_8055_(obstruction.m_82425_());
/*  78 */             if (!state.m_60838_((BlockGetter)level, obstruction.m_82425_())) {
/*     */               
/*  80 */               clearDataTags(tag, new DataEntry[] { DataEntry.OBSTRUCTED });
/*  81 */               tag.m_128379_(DataEntry.OBSTRUCTED.tagName, true);
/*     */               return;
/*     */             } 
/*     */           }  }
/*     */       
/*     */       } 
/*  87 */       clearDataTags(tag, new DataEntry[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void clearDataTags(CompoundTag tag, DataEntry... excluding) {
/*  94 */     for (DataEntry entry : DataEntry.values()) {
/*     */       
/*  96 */       DataEntry[] arrayOfDataEntry = excluding; int i = arrayOfDataEntry.length; byte b = 0; while (true) { if (b < i) { DataEntry toExclude = arrayOfDataEntry[b];
/*     */           
/*  98 */           if (entry == toExclude)
/*     */             break;  b++; continue; }
/*     */         
/* 101 */         tag.m_128473_(entry.tagName);
/*     */         break; }
/*     */     
/*     */     } 
/*     */   }
/*     */   public static List<DataEntry> getEntries(CompoundTag tag) {
/* 107 */     List<DataEntry> entries = Lists.newArrayList();
/* 108 */     for (DataEntry entry : DataEntry.values()) {
/*     */       
/* 110 */       if (entry.hasData(tag) && entry.isInformational)
/* 111 */         entries.add(entry); 
/*     */     } 
/* 113 */     return entries;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack m_5922_(ItemStack item, Level level, LivingEntity entity) {
/* 119 */     clearDataTags(item.m_41784_(), new DataEntry[0]);
/* 120 */     return super.m_5922_(item, level, entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m_7373_(ItemStack stack, Level level, List<Component> text, TooltipFlag flag) {
/* 126 */     CompoundTag tag = stack.m_41784_();
/* 127 */     text.add(Component.m_237115_("description.phasometer.use").m_130940_(ChatFormatting.DARK_GRAY));
/* 128 */     if (tag.m_128471_("IsUpgraded")) {
/* 129 */       text.add(Component.m_237115_("description.phasometer.use.upgraded").m_130940_(ChatFormatting.GOLD));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUpgraded(ItemStack stack) {
/* 135 */     return stack.m_41784_().m_128471_("IsUpgraded");
/*     */   }
/*     */   
/*     */   public enum DataEntry
/*     */   {
/* 140 */     OBSTRUCTED("IsObstructed", false, false)
/*     */     {
/*     */       
/*     */       public Component getDisplayText(CompoundTag tag)
/*     */       {
/* 145 */         return (Component)Component.m_237115_("description.phasometer.obstructed").m_130940_(ChatFormatting.RED);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean hasData(CompoundTag tag) {
/* 151 */         return tag.m_128441_(this.tagName);
/*     */       }
/*     */     },
/* 154 */     PHASE("LookingAtPhase", false)
/*     */     {
/*     */       
/*     */       protected void apply(CompoundTag tag, WitherStormEntity storm)
/*     */       {
/* 159 */         tag.m_128405_(this.tagName, storm.getPhase());
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public Component getDisplayText(CompoundTag tag) {
/* 165 */         return (Component)Component.m_237110_("description.phasometer.phase", new Object[] { Integer.valueOf(tag.m_128451_(this.tagName)) }).m_130940_(ChatFormatting.GREEN);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean hasData(CompoundTag tag) {
/* 171 */         return tag.m_128441_(this.tagName);
/*     */       }
/*     */     },
/* 174 */     FORMIDIBOMBABLE("IsFormidibombable", false)
/*     */     {
/*     */       
/*     */       protected void apply(CompoundTag tag, WitherStormEntity storm)
/*     */       {
/* 179 */         tag.m_128379_(this.tagName, (storm.getPhase() == 5 && storm.getConsumedEntities() >= storm.getConsumptionAmountForPhase(5)));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public Component getDisplayText(CompoundTag tag) {
/* 185 */         return (Component)Component.m_237115_("description.phasometer.formidibombable").m_130940_(ChatFormatting.GOLD);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean hasData(CompoundTag tag) {
/* 191 */         return tag.m_128471_(this.tagName);
/*     */       }
/*     */     },
/* 194 */     BOWELS_ACCESSIBLE("BowelsAccessible", false)
/*     */     {
/*     */       
/*     */       protected void apply(CompoundTag tag, WitherStormEntity storm)
/*     */       {
/* 199 */         tag.m_128379_(this.tagName, storm.isBeingTornApart());
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public Component getDisplayText(CompoundTag tag) {
/* 205 */         return (Component)Component.m_237115_("description.phasometer.bowelsAccessible").m_130940_(ChatFormatting.GOLD);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean hasData(CompoundTag tag) {
/* 211 */         return tag.m_128471_(this.tagName);
/*     */       }
/*     */     },
/* 214 */     DISTRACTED("IsDistracted", false)
/*     */     {
/*     */       
/*     */       protected void apply(CompoundTag tag, WitherStormEntity storm)
/*     */       {
/* 219 */         UltimateTargetManager manager = storm.getUltimateTargetManager().orElse(null);
/* 220 */         if (manager != null) {
/* 221 */           tag.m_128379_(this.tagName, manager.isDistracted());
/*     */         } else {
/* 223 */           tag.m_128379_(this.tagName, false);
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/*     */       public Component getDisplayText(CompoundTag tag) {
/* 229 */         return (Component)Component.m_237115_("description.phasometer.distracted").m_130940_(ChatFormatting.GOLD);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean hasData(CompoundTag tag) {
/* 235 */         return tag.m_128471_(this.tagName);
/*     */       }
/*     */     },
/* 238 */     CHASING("IsChasing", false)
/*     */     {
/*     */       
/*     */       protected void apply(CompoundTag tag, WitherStormEntity storm)
/*     */       {
/* 243 */         UltimateTargetManager manager = storm.getUltimateTargetManager().orElse(null);
/* 244 */         if (manager != null) {
/* 245 */           tag.m_128379_(this.tagName, manager.isTargetStationary());
/*     */         } else {
/* 247 */           tag.m_128379_(this.tagName, false);
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/*     */       public Component getDisplayText(CompoundTag tag) {
/* 253 */         return (Component)Component.m_237115_("description.phasometer.chasing").m_130940_(ChatFormatting.GOLD);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean hasData(CompoundTag tag) {
/* 259 */         return tag.m_128471_(this.tagName);
/*     */       }
/*     */     },
/* 262 */     ULTIMATE_TARGET("UltimateTarget", true)
/*     */     {
/*     */       
/*     */       protected void apply(CompoundTag tag, WitherStormEntity storm)
/*     */       {
/* 267 */         LivingEntity target = storm.getUltimateTarget();
/* 268 */         if (target != null) {
/* 269 */           tag.m_128359_(this.tagName, target.m_5446_().getString());
/*     */         } else {
/* 271 */           tag.m_128473_(this.tagName);
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/*     */       public Component getDisplayText(CompoundTag tag) {
/* 277 */         return (Component)Component.m_237110_("description.phasometer.ultimateTarget", new Object[] { tag.m_128461_(this.tagName) }).m_130940_(ChatFormatting.LIGHT_PURPLE);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean hasData(CompoundTag tag) {
/* 283 */         return tag.m_128441_(this.tagName);
/*     */       }
/*     */     },
/* 286 */     ULTIMATE_TARGET_DIRECTION("UltimateTargetDirection", true)
/*     */     {
/*     */       
/*     */       protected void apply(CompoundTag tag, WitherStormEntity storm)
/*     */       {
/* 291 */         Vec3 pos = storm.getUltimateTargetPos();
/* 292 */         if (pos != null) {
/*     */           
/* 294 */           Vec3 normal = (new Vec3(pos.f_82479_, storm.m_20186_(), pos.f_82481_)).m_82546_(storm.m_20182_()).m_82541_();
/* 295 */           tag.m_128359_(this.tagName, Direction.m_122366_(normal.f_82479_, normal.f_82480_, normal.f_82481_).m_122433_());
/*     */         }
/*     */         else {
/*     */           
/* 299 */           tag.m_128473_(this.tagName);
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public Component getDisplayText(CompoundTag tag) {
/* 306 */         return (Component)Component.m_237110_("description.phasometer.ultimateTargetDirection", new Object[] { tag.m_128461_(this.tagName) }).m_130940_(ChatFormatting.LIGHT_PURPLE);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean hasData(CompoundTag tag) {
/* 312 */         return tag.m_128441_(this.tagName);
/*     */       }
/*     */     },
/* 315 */     PHASE_PROGRESS("PhaseProgress", true)
/*     */     {
/*     */       
/*     */       protected void apply(CompoundTag tag, WitherStormEntity storm)
/*     */       {
/* 320 */         if (storm.canEvolve(true)) {
/*     */           
/* 322 */           int progress = Math.round(storm.getPhaseProgress() * 100.0F);
/* 323 */           tag.m_128405_(this.tagName, progress);
/*     */         }
/*     */         else {
/*     */           
/* 327 */           tag.m_128405_(this.tagName, 100);
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public Component getDisplayText(CompoundTag tag) {
/* 334 */         return (Component)Component.m_237110_("description.phasometer.phaseProgress", new Object[] { "" + tag.m_128451_(this.tagName) + "%" }).m_130940_(ChatFormatting.LIGHT_PURPLE);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean hasData(CompoundTag tag) {
/* 340 */         return tag.m_128441_(this.tagName);
/*     */       }
/*     */     };
/*     */ 
/*     */     
/*     */     public final String tagName;
/*     */     public final boolean requiresUpgraded;
/*     */     public final boolean isInformational;
/*     */     
/*     */     DataEntry(String tagName, boolean requiresUpgraded, boolean isInformational) {
/* 350 */       this.tagName = tagName;
/* 351 */       this.requiresUpgraded = requiresUpgraded;
/* 352 */       this.isInformational = isInformational;
/*     */     }
/*     */     
/*     */     protected void apply(CompoundTag tag, WitherStormEntity storm) {}
/*     */     
/*     */     public abstract Component getDisplayText(CompoundTag param1CompoundTag);
/*     */     
/*     */     public abstract boolean hasData(CompoundTag param1CompoundTag);
/*     */   }
/*     */   
/*     */   enum null {
/*     */     public Component getDisplayText(CompoundTag tag) {
/*     */       return (Component)Component.m_237115_("description.phasometer.obstructed").m_130940_(ChatFormatting.RED);
/*     */     }
/*     */     
/*     */     public boolean hasData(CompoundTag tag) {
/*     */       return tag.m_128441_(this.tagName);
/*     */     }
/*     */   }
/*     */   
/*     */   enum null {
/*     */     protected void apply(CompoundTag tag, WitherStormEntity storm) {
/*     */       tag.m_128405_(this.tagName, storm.getPhase());
/*     */     }
/*     */     
/*     */     public Component getDisplayText(CompoundTag tag) {
/*     */       return (Component)Component.m_237110_("description.phasometer.phase", new Object[] { Integer.valueOf(tag.m_128451_(this.tagName)) }).m_130940_(ChatFormatting.GREEN);
/*     */     }
/*     */     
/*     */     public boolean hasData(CompoundTag tag) {
/*     */       return tag.m_128441_(this.tagName);
/*     */     }
/*     */   }
/*     */   
/*     */   enum null {
/*     */     protected void apply(CompoundTag tag, WitherStormEntity storm) {
/*     */       tag.m_128379_(this.tagName, (storm.getPhase() == 5 && storm.getConsumedEntities() >= storm.getConsumptionAmountForPhase(5)));
/*     */     }
/*     */     
/*     */     public Component getDisplayText(CompoundTag tag) {
/*     */       return (Component)Component.m_237115_("description.phasometer.formidibombable").m_130940_(ChatFormatting.GOLD);
/*     */     }
/*     */     
/*     */     public boolean hasData(CompoundTag tag) {
/*     */       return tag.m_128471_(this.tagName);
/*     */     }
/*     */   }
/*     */   
/*     */   enum null {
/*     */     protected void apply(CompoundTag tag, WitherStormEntity storm) {
/*     */       tag.m_128379_(this.tagName, storm.isBeingTornApart());
/*     */     }
/*     */     
/*     */     public Component getDisplayText(CompoundTag tag) {
/*     */       return (Component)Component.m_237115_("description.phasometer.bowelsAccessible").m_130940_(ChatFormatting.GOLD);
/*     */     }
/*     */     
/*     */     public boolean hasData(CompoundTag tag) {
/*     */       return tag.m_128471_(this.tagName);
/*     */     }
/*     */   }
/*     */   
/*     */   enum null {
/*     */     protected void apply(CompoundTag tag, WitherStormEntity storm) {
/*     */       UltimateTargetManager manager = storm.getUltimateTargetManager().orElse(null);
/*     */       if (manager != null) {
/*     */         tag.m_128379_(this.tagName, manager.isDistracted());
/*     */       } else {
/*     */         tag.m_128379_(this.tagName, false);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Component getDisplayText(CompoundTag tag) {
/*     */       return (Component)Component.m_237115_("description.phasometer.distracted").m_130940_(ChatFormatting.GOLD);
/*     */     }
/*     */     
/*     */     public boolean hasData(CompoundTag tag) {
/*     */       return tag.m_128471_(this.tagName);
/*     */     }
/*     */   }
/*     */   
/*     */   enum null {
/*     */     protected void apply(CompoundTag tag, WitherStormEntity storm) {
/*     */       UltimateTargetManager manager = storm.getUltimateTargetManager().orElse(null);
/*     */       if (manager != null) {
/*     */         tag.m_128379_(this.tagName, manager.isTargetStationary());
/*     */       } else {
/*     */         tag.m_128379_(this.tagName, false);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Component getDisplayText(CompoundTag tag) {
/*     */       return (Component)Component.m_237115_("description.phasometer.chasing").m_130940_(ChatFormatting.GOLD);
/*     */     }
/*     */     
/*     */     public boolean hasData(CompoundTag tag) {
/*     */       return tag.m_128471_(this.tagName);
/*     */     }
/*     */   }
/*     */   
/*     */   enum null {
/*     */     protected void apply(CompoundTag tag, WitherStormEntity storm) {
/*     */       LivingEntity target = storm.getUltimateTarget();
/*     */       if (target != null) {
/*     */         tag.m_128359_(this.tagName, target.m_5446_().getString());
/*     */       } else {
/*     */         tag.m_128473_(this.tagName);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Component getDisplayText(CompoundTag tag) {
/*     */       return (Component)Component.m_237110_("description.phasometer.ultimateTarget", new Object[] { tag.m_128461_(this.tagName) }).m_130940_(ChatFormatting.LIGHT_PURPLE);
/*     */     }
/*     */     
/*     */     public boolean hasData(CompoundTag tag) {
/*     */       return tag.m_128441_(this.tagName);
/*     */     }
/*     */   }
/*     */   
/*     */   enum null {
/*     */     protected void apply(CompoundTag tag, WitherStormEntity storm) {
/*     */       Vec3 pos = storm.getUltimateTargetPos();
/*     */       if (pos != null) {
/*     */         Vec3 normal = (new Vec3(pos.f_82479_, storm.m_20186_(), pos.f_82481_)).m_82546_(storm.m_20182_()).m_82541_();
/*     */         tag.m_128359_(this.tagName, Direction.m_122366_(normal.f_82479_, normal.f_82480_, normal.f_82481_).m_122433_());
/*     */       } else {
/*     */         tag.m_128473_(this.tagName);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Component getDisplayText(CompoundTag tag) {
/*     */       return (Component)Component.m_237110_("description.phasometer.ultimateTargetDirection", new Object[] { tag.m_128461_(this.tagName) }).m_130940_(ChatFormatting.LIGHT_PURPLE);
/*     */     }
/*     */     
/*     */     public boolean hasData(CompoundTag tag) {
/*     */       return tag.m_128441_(this.tagName);
/*     */     }
/*     */   }
/*     */   
/*     */   enum null {
/*     */     protected void apply(CompoundTag tag, WitherStormEntity storm) {
/*     */       if (storm.canEvolve(true)) {
/*     */         int progress = Math.round(storm.getPhaseProgress() * 100.0F);
/*     */         tag.m_128405_(this.tagName, progress);
/*     */       } else {
/*     */         tag.m_128405_(this.tagName, 100);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Component getDisplayText(CompoundTag tag) {
/*     */       return (Component)Component.m_237110_("description.phasometer.phaseProgress", new Object[] { "" + tag.m_128451_(this.tagName) + "%" }).m_130940_(ChatFormatting.LIGHT_PURPLE);
/*     */     }
/*     */     
/*     */     public boolean hasData(CompoundTag tag) {
/*     */       return tag.m_128441_(this.tagName);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\item\PhasometerItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
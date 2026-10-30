package nonamecrackers2.witherstormmod.common.item;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpyglassItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult.Type;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;

public class PhasometerItem extends SpyglassItem {
   public static final String UPGRADED = "IsUpgraded";

   public PhasometerItem(Properties properties) {
      super(properties);
   }

   public InteractionResultHolder<ItemStack> m_7203_(Level level, Player player, InteractionHand hand) {
      clearDataTags(player.m_21120_(hand).m_41784_());
      return super.m_7203_(level, player, hand);
   }

   public void m_5929_(Level level, LivingEntity entity, ItemStack item, int count) {
      if (!level.f_46443_) {
         Vec3 pos = entity.m_146892_();
         Vec3 view = entity.m_20252_(1.0F);
         Vec3 end = pos.m_82549_(view.m_82490_(10000.0));
         EntityHitResult result = ProjectileUtil.m_150175_(entity.m_9236_(), entity, pos, end, new AABB(pos, end).m_82400_(1.0), e -> !e.m_5833_(), 0.0F);
         CompoundTag tag = item.m_41784_();
         if (result != null
            && result.m_6662_() == Type.ENTITY
            && result.m_82443_() instanceof WitherStormEntity storm
            && storm.m_6095_() == WitherStormModEntityTypes.WITHER_STORM.get()) {
            BlockHitResult obstruction = WorldUtil.raycast(entity, storm, 150.0);
            if (obstruction.m_6662_() == Type.MISS) {
               for (nonamecrackers2.witherstormmod.common.item.PhasometerItem.DataEntry entry : nonamecrackers2.witherstormmod.common.item.PhasometerItem.DataEntry.values()) {
                  if (!entry.requiresUpgraded || this.isUpgraded(item)) {
                     entry.apply(tag, storm);
                  }
               }

               tag.m_128379_(nonamecrackers2.witherstormmod.common.item.PhasometerItem.DataEntry.OBSTRUCTED.tagName, false);
               return;
            }

            BlockState state = level.m_8055_(obstruction.m_82425_());
            if (!state.m_60838_(level, obstruction.m_82425_())) {
               clearDataTags(tag, nonamecrackers2.witherstormmod.common.item.PhasometerItem.DataEntry.OBSTRUCTED);
               tag.m_128379_(nonamecrackers2.witherstormmod.common.item.PhasometerItem.DataEntry.OBSTRUCTED.tagName, true);
               return;
            }
         }

         clearDataTags(tag);
      }
   }

   private static void clearDataTags(CompoundTag tag, nonamecrackers2.witherstormmod.common.item.PhasometerItem.DataEntry... excluding) {
      label24:
      for (nonamecrackers2.witherstormmod.common.item.PhasometerItem.DataEntry entry : nonamecrackers2.witherstormmod.common.item.PhasometerItem.DataEntry.values()) {
         for (nonamecrackers2.witherstormmod.common.item.PhasometerItem.DataEntry toExclude : excluding) {
            if (entry == toExclude) {
               continue label24;
            }
         }

         tag.m_128473_(entry.tagName);
      }
   }

   public static List<nonamecrackers2.witherstormmod.common.item.PhasometerItem.DataEntry> getEntries(CompoundTag tag) {
      List<nonamecrackers2.witherstormmod.common.item.PhasometerItem.DataEntry> entries = Lists.newArrayList();

      for (nonamecrackers2.witherstormmod.common.item.PhasometerItem.DataEntry entry : nonamecrackers2.witherstormmod.common.item.PhasometerItem.DataEntry.values()) {
         if (entry.hasData(tag) && entry.isInformational) {
            entries.add(entry);
         }
      }

      return entries;
   }

   public ItemStack m_5922_(ItemStack item, Level level, LivingEntity entity) {
      clearDataTags(item.m_41784_());
      return super.m_5922_(item, level, entity);
   }

   public void m_7373_(ItemStack stack, Level level, List<Component> text, TooltipFlag flag) {
      CompoundTag tag = stack.m_41784_();
      text.add(Component.m_237115_("description.phasometer.use").m_130940_(ChatFormatting.DARK_GRAY));
      if (tag.m_128471_("IsUpgraded")) {
         text.add(Component.m_237115_("description.phasometer.use.upgraded").m_130940_(ChatFormatting.GOLD));
      }
   }

   public boolean isUpgraded(ItemStack stack) {
      return stack.m_41784_().m_128471_("IsUpgraded");
   }

   public enum DataEntry {
      OBSTRUCTED("IsObstructed", false, false) {
         @Override
         public Component getDisplayText(CompoundTag tag) {
            return Component.m_237115_("description.phasometer.obstructed").m_130940_(ChatFormatting.RED);
         }

         @Override
         public boolean hasData(CompoundTag tag) {
            return tag.m_128441_(this.tagName);
         }
      },
      PHASE("LookingAtPhase", false) {
         @Override
         protected void apply(CompoundTag tag, WitherStormEntity storm) {
            tag.m_128405_(this.tagName, storm.getPhase());
         }

         @Override
         public Component getDisplayText(CompoundTag tag) {
            return Component.m_237110_("description.phasometer.phase", new Object[]{tag.m_128451_(this.tagName)}).m_130940_(ChatFormatting.GREEN);
         }

         @Override
         public boolean hasData(CompoundTag tag) {
            return tag.m_128441_(this.tagName);
         }
      },
      FORMIDIBOMBABLE("IsFormidibombable", false) {
         @Override
         protected void apply(CompoundTag tag, WitherStormEntity storm) {
            tag.m_128379_(this.tagName, storm.getPhase() == 5 && storm.getConsumedEntities() >= storm.getConsumptionAmountForPhase(5));
         }

         @Override
         public Component getDisplayText(CompoundTag tag) {
            return Component.m_237115_("description.phasometer.formidibombable").m_130940_(ChatFormatting.GOLD);
         }

         @Override
         public boolean hasData(CompoundTag tag) {
            return tag.m_128471_(this.tagName);
         }
      },
      BOWELS_ACCESSIBLE("BowelsAccessible", false) {
         @Override
         protected void apply(CompoundTag tag, WitherStormEntity storm) {
            tag.m_128379_(this.tagName, storm.isBeingTornApart());
         }

         @Override
         public Component getDisplayText(CompoundTag tag) {
            return Component.m_237115_("description.phasometer.bowelsAccessible").m_130940_(ChatFormatting.GOLD);
         }

         @Override
         public boolean hasData(CompoundTag tag) {
            return tag.m_128471_(this.tagName);
         }
      },
      DISTRACTED("IsDistracted", false) {
         @Override
         protected void apply(CompoundTag tag, WitherStormEntity storm) {
            UltimateTargetManager manager = (UltimateTargetManager)storm.getUltimateTargetManager().orElse(null);
            if (manager != null) {
               tag.m_128379_(this.tagName, manager.isDistracted());
            } else {
               tag.m_128379_(this.tagName, false);
            }
         }

         @Override
         public Component getDisplayText(CompoundTag tag) {
            return Component.m_237115_("description.phasometer.distracted").m_130940_(ChatFormatting.GOLD);
         }

         @Override
         public boolean hasData(CompoundTag tag) {
            return tag.m_128471_(this.tagName);
         }
      },
      CHASING("IsChasing", false) {
         @Override
         protected void apply(CompoundTag tag, WitherStormEntity storm) {
            UltimateTargetManager manager = (UltimateTargetManager)storm.getUltimateTargetManager().orElse(null);
            if (manager != null) {
               tag.m_128379_(this.tagName, manager.isTargetStationary());
            } else {
               tag.m_128379_(this.tagName, false);
            }
         }

         @Override
         public Component getDisplayText(CompoundTag tag) {
            return Component.m_237115_("description.phasometer.chasing").m_130940_(ChatFormatting.GOLD);
         }

         @Override
         public boolean hasData(CompoundTag tag) {
            return tag.m_128471_(this.tagName);
         }
      },
      ULTIMATE_TARGET("UltimateTarget", true) {
         @Override
         protected void apply(CompoundTag tag, WitherStormEntity storm) {
            LivingEntity target = storm.getUltimateTarget();
            if (target != null) {
               tag.m_128359_(this.tagName, target.m_5446_().getString());
            } else {
               tag.m_128473_(this.tagName);
            }
         }

         @Override
         public Component getDisplayText(CompoundTag tag) {
            return Component.m_237110_("description.phasometer.ultimateTarget", new Object[]{tag.m_128461_(this.tagName)})
               .m_130940_(ChatFormatting.LIGHT_PURPLE);
         }

         @Override
         public boolean hasData(CompoundTag tag) {
            return tag.m_128441_(this.tagName);
         }
      },
      ULTIMATE_TARGET_DIRECTION("UltimateTargetDirection", true) {
         @Override
         protected void apply(CompoundTag tag, WitherStormEntity storm) {
            Vec3 pos = storm.getUltimateTargetPos();
            if (pos != null) {
               Vec3 normal = new Vec3(pos.f_82479_, storm.m_20186_(), pos.f_82481_).m_82546_(storm.m_20182_()).m_82541_();
               tag.m_128359_(this.tagName, Direction.m_122366_(normal.f_82479_, normal.f_82480_, normal.f_82481_).m_122433_());
            } else {
               tag.m_128473_(this.tagName);
            }
         }

         @Override
         public Component getDisplayText(CompoundTag tag) {
            return Component.m_237110_("description.phasometer.ultimateTargetDirection", new Object[]{tag.m_128461_(this.tagName)})
               .m_130940_(ChatFormatting.LIGHT_PURPLE);
         }

         @Override
         public boolean hasData(CompoundTag tag) {
            return tag.m_128441_(this.tagName);
         }
      },
      PHASE_PROGRESS("PhaseProgress", true) {
         @Override
         protected void apply(CompoundTag tag, WitherStormEntity storm) {
            if (storm.canEvolve(true)) {
               int progress = Math.round(storm.getPhaseProgress() * 100.0F);
               tag.m_128405_(this.tagName, progress);
            } else {
               tag.m_128405_(this.tagName, 100);
            }
         }

         @Override
         public Component getDisplayText(CompoundTag tag) {
            return Component.m_237110_("description.phasometer.phaseProgress", new Object[]{tag.m_128451_(this.tagName) + "%"})
               .m_130940_(ChatFormatting.LIGHT_PURPLE);
         }

         @Override
         public boolean hasData(CompoundTag tag) {
            return tag.m_128441_(this.tagName);
         }
      };

      public final String tagName;
      public final boolean requiresUpgraded;
      public final boolean isInformational;

      DataEntry(String tagName, boolean requiresUpgraded, boolean isInformational) {
         this.tagName = tagName;
         this.requiresUpgraded = requiresUpgraded;
         this.isInformational = isInformational;
      }

      DataEntry(String tagName, boolean requiresUpgraded) {
         this(tagName, requiresUpgraded, true);
      }

      protected void apply(CompoundTag tag, WitherStormEntity storm) {
      }

      public abstract Component getDisplayText(CompoundTag var1);

      public abstract boolean hasData(CompoundTag var1);
   }
}

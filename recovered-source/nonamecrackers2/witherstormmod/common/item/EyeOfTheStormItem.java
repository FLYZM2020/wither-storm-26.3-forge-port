package nonamecrackers2.witherstormmod.common.item;

import java.util.List;
import java.util.UUID;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import nonamecrackers2.witherstormmod.common.entity.TentacleSpike;

public class EyeOfTheStormItem extends CommandBlockSwordItem {
   public static final UUID DAMAGE_MODIFIER_ID = UUID.fromString("823350e7-4c91-4a1f-8c01-8735113f066e");
   public static final String ENTITY_HEALTH_RATIO = "EntityHealthRatio";

   public EyeOfTheStormItem(Tier tier, int damage, float attackSpeed, Properties properties) {
      super(tier, damage, attackSpeed, properties);
   }

   public void m_6883_(ItemStack stack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
      CompoundTag tag = stack.m_41784_();
      if (!(entity instanceof LivingEntity living && (!(living instanceof Player) || !((Player)living).m_150110_().f_35937_))) {
         tag.m_128473_("EntityHealthRatio");
      } else {
         tag.m_128350_("EntityHealthRatio", living.m_21223_() / living.m_21233_());
      }
   }

   public void m_7373_(ItemStack stack, Level level, List<Component> text, TooltipFlag flag) {
      text.add(Component.m_237115_("item.witherstormmod.eye_of_the_storm.author").m_130940_(ChatFormatting.DARK_GRAY));
   }

   public boolean m_7579_(ItemStack stack, LivingEntity hit, LivingEntity living) {
      if (!super.m_7579_(stack, hit, living)) {
         return false;
      }

      if (living.m_217043_().m_188501_() > living.m_21223_() / living.m_21233_() || living instanceof Player player && player.m_150110_().f_35937_) {
         double minHeight = Math.min(hit.m_20186_(), living.m_20186_());
         double maxHeight = Math.max(hit.m_20186_(), living.m_20186_()) + 1.0;
         int total = 5;
         int spread = 2;
         float hitAngle = (float)Mth.m_14136_(hit.m_20189_() - living.m_20189_(), hit.m_20185_() - living.m_20185_());
         float damageModifier = EnchantmentHelper.m_44833_(stack, hit.m_6336_());
         createSpike(living, hit.m_20185_(), hit.m_20189_(), minHeight, maxHeight, hitAngle, 0, damageModifier);

         for (int i = 0; i < total; i++) {
            float angle = (float)i / total * (float) Math.PI * 2.0F + hitAngle;

            for (int j = 0; j < spread; j++) {
               double x = hit.m_20185_() + (double)Mth.m_14089_(angle) * (j + 1);
               double z = hit.m_20189_() + (double)Mth.m_14031_(angle) * (j + 1);
               createSpike(living, x, z, minHeight, maxHeight, angle, (j + 1) * 5 + living.m_217043_().m_188503_(4) - 2, damageModifier);
            }
         }
      }

      return true;
   }

   private static void createSpike(LivingEntity owner, double x, double z, double minHeight, double maxHeight, float yRot, int delay, float damageModifier) {
      BlockPos blockPos = BlockPos.m_274561_(x, maxHeight, z);
      boolean flag = false;
      double d0 = 0.0;

      do {
         BlockPos below = blockPos.m_7495_();
         BlockState state = owner.m_9236_().m_8055_(below);
         if (state.m_60783_(owner.m_9236_(), below, Direction.UP)) {
            if (!owner.m_9236_().m_46859_(blockPos)) {
               BlockState state1 = owner.m_9236_().m_8055_(blockPos);
               VoxelShape shape = state1.m_60812_(owner.m_9236_(), blockPos);
               if (!shape.m_83281_()) {
                  d0 = shape.m_83297_(Axis.Y);
               }
            }

            flag = true;
            break;
         }

         blockPos = blockPos.m_7495_();
      } while (blockPos.m_123342_() >= Mth.m_14107_(minHeight) - 1);

      if (flag) {
         owner.m_9236_().m_7967_(new TentacleSpike(owner.m_9236_(), x, blockPos.m_123342_() + d0, z, yRot, delay, owner, damageModifier));
      }
   }
}

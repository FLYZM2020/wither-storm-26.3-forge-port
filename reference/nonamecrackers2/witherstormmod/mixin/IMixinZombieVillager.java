package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.monster.ZombieVillager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({ZombieVillager.class})
public interface IMixinZombieVillager {
  @Accessor
  Tag getGossips();
  
  @Accessor
  CompoundTag getTradeOffers();
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\IMixinZombieVillager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
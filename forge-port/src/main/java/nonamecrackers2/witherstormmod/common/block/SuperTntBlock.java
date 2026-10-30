package nonamecrackers2.witherstormmod.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gamerules.GameRules;
import nonamecrackers2.witherstormmod.common.entity.SuperTntEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSounds;
import org.jspecify.annotations.Nullable;

public final class SuperTntBlock extends TntBlock {
    public SuperTntBlock(BlockBehaviour.Properties properties) { super(properties); }

    @Override
    public boolean onCaughtFire(BlockState state, Level level, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
        if (!(level instanceof ServerLevel server) || !server.getGameRules().get(GameRules.TNT_EXPLODES)) return false;
        SuperTntEntity tnt = SuperTntEntity.create(server, pos, state, igniter, 320);
        if (!server.addFreshEntity(tnt)) return false;
        server.playSound(null, tnt.getX(), tnt.getY(), tnt.getZ(), WitherStormModSounds.SUPER_TNT_FUSE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
        server.gameEvent(igniter, GameEvent.PRIME_FUSE, pos);
        return true;
    }

    @Override
    public void wasExploded(ServerLevel level, BlockPos pos, Explosion explosion) {
        if (!level.getGameRules().get(GameRules.TNT_EXPLODES)) return;
        int fuse = PrimedTnt.getRandomShortFuse(320, level.getRandom());
        level.addFreshEntity(SuperTntEntity.create(level, pos, defaultBlockState(), explosion.getIndirectSourceEntity(), fuse));
    }
}

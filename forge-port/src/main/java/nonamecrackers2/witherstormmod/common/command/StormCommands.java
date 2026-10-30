package nonamecrackers2.witherstormmod.common.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.RegisterCommandsEvent;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.evolution.StormEvolutionRules;
import nonamecrackers2.witherstormmod.common.sickness.WitherSicknessTracker;

/** Phase, subphase and consumed-count subset of the original commands. */
public final class StormCommands {
    private static final SimpleCommandExceptionType NOT_STORM =
            new SimpleCommandExceptionType(Component.translatable("commands.witherstormmod.entity.arg.invalid"));
    private static final SimpleCommandExceptionType CANNOT_EVOLVE =
            new SimpleCommandExceptionType(Component.translatable("commands.witherstormmod.setphase.invalid"));
    private static final SimpleCommandExceptionType INVALID_COLLAPSE =
            new SimpleCommandExceptionType(Component.translatable("commands.witherstormmod.collapse.invalid"));
    private static final SimpleCommandExceptionType INVALID_REVIVAL =
            new SimpleCommandExceptionType(Component.translatable("commands.witherstormmod.revive.invalid"));

    private StormCommands() {}

    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("witherstormmod")
            .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
            .then(Commands.literal("phase")
                .then(Commands.literal("get")
                    .then(Commands.argument("witherstorm", EntityArgument.entity()).executes(StormCommands::getPhase)))
                .then(Commands.literal("set")
                    .then(Commands.argument("witherstorm", EntityArgument.entity())
                        .then(Commands.argument("phase", DoubleArgumentType.doubleArg(0.0)).executes(StormCommands::setPhase))))
                .then(Commands.literal("evolve")
                    .then(Commands.argument("witherstorm", EntityArgument.entity())
                        .executes(ctx -> evolve(ctx, false))
                        .then(Commands.literal("force").executes(ctx -> evolve(ctx, true))))))
            .then(Commands.literal("explode")
                .then(Commands.argument("witherstorm", EntityArgument.entity()).executes(StormCommands::collapse)))
            .then(Commands.literal("revive")
                .then(Commands.argument("witherstorm", EntityArgument.entity()).executes(StormCommands::revive)))
            .then(Commands.literal("sickness")
                .then(Commands.literal("infect")
                    .then(Commands.argument("entity", EntityArgument.entity()).executes(ctx -> sickness(ctx, false))))
                .then(Commands.literal("cure")
                    .then(Commands.argument("entity", EntityArgument.entity()).executes(ctx -> sickness(ctx, true)))))
            .then(Commands.literal("consumedEntities")
                .then(Commands.literal("get")
                    .then(Commands.argument("witherstorm", EntityArgument.entity()).executes(StormCommands::getConsumed)))
                .then(Commands.literal("set")
                    .then(Commands.argument("witherstorm", EntityArgument.entity())
                        .then(Commands.argument("amount", IntegerArgumentType.integer(0)).executes(StormCommands::setConsumed))))));
    }

    private static WitherStormEntity getStorm(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Entity entity = EntityArgument.getEntity(context, "witherstorm");
        if (entity instanceof WitherStormEntity storm) return storm;
        throw NOT_STORM.create();
    }

    private static int getPhase(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        WitherStormEntity storm = getStorm(context);
        context.getSource().sendSuccess(() -> Component.translatable("commands.witherstormmod.phase.current", storm.getPhase()), false);
        return storm.getPhase();
    }

    private static int setPhase(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        WitherStormEntity storm = getStorm(context);
        double phase = DoubleArgumentType.getDouble(context, "phase");
        double speed = storm.getEvolutionSpeedModifier();
        boolean changed;
        if (phase >= 1.1 && phase < 1.2) changed = storm.setPhase(1, (int) (150 * speed));
        else if (phase >= 1.2 && phase < 2.0) changed = storm.setPhase(1, (int) (250 * speed));
        else if (phase >= 2.1 && phase < 3.0) changed = storm.setPhase(2, (int) (800 * speed));
        else if (phase >= 3.1 && phase < 3.2) changed = storm.setPhase(3, (int) (2350 * speed));
        else if (phase >= 3.2 && phase < 4.0) changed = storm.setPhase(3, (int) (3500 * speed));
        else if (phase >= 4.5 && phase < 5.0) changed = storm.setPhase(4, plusOne(StormEvolutionRules.subphaseRequirement(4, speed)));
        else if (phase >= 5.25 && phase < 5.5) changed = storm.setPhase(5, plusOne(StormEvolutionRules.subphaseRequirement(5, speed)));
        else if (phase >= 5.5 && phase < 6.0) changed = storm.setPhase(5, plusOne(StormEvolutionRules.consumptionRequirement(5, speed)));
        else if (phase >= 6.5 && phase < 7.0) changed = storm.setPhase(6, plusOne(StormEvolutionRules.subphaseRequirement(6, speed)));
        else if (phase >= 7.5) changed = storm.setPhase(7, plusOne(StormEvolutionRules.consumptionRequirement(7, speed)));
        else changed = storm.setPhase((int) phase);
        if (!changed) throw CANNOT_EVOLVE.create();
        context.getSource().sendSuccess(() -> Component.translatable("commands.witherstormmod.setphase.success", phase, storm.getDisplayName()), true);
        return 1;
    }

    private static int plusOne(int value) { return value == Integer.MAX_VALUE ? value : value + 1; }

    private static int sickness(CommandContext<CommandSourceStack> context, boolean cure) throws CommandSyntaxException {
        Entity entity = EntityArgument.getEntity(context, "entity");
        if (!(entity instanceof LivingEntity living)) {
            context.getSource().sendFailure(Component.translatable("commands.witherstormmod.sickness.not_living"));
            return 0;
        }
        boolean started = cure ? WitherSicknessTracker.beginCure(living) : WitherSicknessTracker.beginInfection(living);
        if (!started) {
            context.getSource().sendFailure(Component.translatable(cure ? "commands.witherstormmod.sickness.cure_failed" : "commands.witherstormmod.sickness.infect_failed", living.getDisplayName()));
            return 0;
        }
        context.getSource().sendSuccess(() -> Component.translatable(cure ? "commands.witherstormmod.sickness.cure_started" : "commands.witherstormmod.sickness.infect_started", living.getDisplayName()), true);
        return 1;
    }

    private static int collapse(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        WitherStormEntity storm = getStorm(context);
        if (!storm.beginCollapse()) throw INVALID_COLLAPSE.create();
        context.getSource().sendSuccess(() -> Component.translatable("commands.witherstormmod.collapse.success", storm.getDisplayName()), true);
        return 1;
    }

    private static int revive(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        WitherStormEntity storm = getStorm(context);
        if (!storm.beginRevival()) throw INVALID_REVIVAL.create();
        context.getSource().sendSuccess(() -> Component.translatable("commands.witherstormmod.revive.success", storm.getDisplayName()), true);
        return 1;
    }

    private static int evolve(CommandContext<CommandSourceStack> context, boolean force) throws CommandSyntaxException {
        WitherStormEntity storm = getStorm(context);
        if (!storm.evolve(force)) throw CANNOT_EVOLVE.create();
        context.getSource().sendSuccess(() -> Component.translatable("commands.witherstormmod.setphase.success", storm.getPhase(), storm.getDisplayName()), true);
        return 1;
    }

    private static int getConsumed(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        WitherStormEntity storm = getStorm(context);
        context.getSource().sendSuccess(() -> Component.translatable("commands.witherstormmod.consumed.current", storm.getConsumedEntities()), false);
        return storm.getConsumedEntities();
    }

    private static int setConsumed(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        WitherStormEntity storm = getStorm(context);
        int amount = IntegerArgumentType.getInteger(context, "amount");
        storm.setConsumedEntities(amount);
        context.getSource().sendSuccess(() -> Component.translatable("commands.witherstormmod.setconsumed.success", amount, storm.getDisplayName()), true);
        return 1;
    }
}

package nonamecrackers2.witherstormmod.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager;
import nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsEnterStatus;
import nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager.BowelsInstance;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;

public class BowelsCommands {
   public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
      LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.m_82127_(
               "witherstormmod"
            )
            .requires(commandSource -> commandSource.m_6761_(2)))
         .then(
            ((LiteralArgumentBuilder)Commands.m_82127_("bowels")
                  .then(
                     Commands.m_82129_("entity", EntityArgument.m_91449_())
                        .then(
                           Commands.m_82129_("witherstorm", EntityArgument.m_91449_())
                              .executes(nonamecrackers2.witherstormmod.common.command.BowelsCommands::enterStorm)
                        )
                  ))
               .then(
                  Commands.m_82127_("new")
                     .then(
                        Commands.m_82129_("witherstorm", EntityArgument.m_91449_())
                           .executes(nonamecrackers2.witherstormmod.common.command.BowelsCommands::newBowels)
                     )
               )
         );
      dispatcher.register(setPhaseCommand);
   }

   private static int enterStorm(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
      CommandSourceStack source = (CommandSourceStack)context.getSource();
      Entity enteringEntity = EntityArgument.m_91452_(context, "entity");
      Entity entity = EntityArgument.m_91452_(context, "witherstorm");
      int phase = 0;
      if (entity != null) {
         if (entity instanceof WitherStormEntity storm) {
            phase = storm.getPhase();
            if (!enteringEntity.m_9236_().m_46472_().m_135782_().equals(WitherStormMod.bowelsLocation())) {
               BowelsEnterStatus flag = WitherStormBowelsManager.enter((ServerLevel)storm.m_9236_(), storm, enteringEntity);
               switch (flag) {
                  case ENTITY_CANNOT_CHANGE:
                     source.m_81352_(Component.m_237115_("commands.witherstormmod.enterBowels.failure.cannotChangeDim"));
                  case CANT_SETUP_BOWELS:
                     source.m_81352_(Component.m_237110_("commands.witherstormmod.enterBowels.failure", new Object[]{storm.m_5446_()}));
               }
            } else {
               source.m_81352_(Component.m_237115_("commands.witherstormmod.enterBowels.dim.invalid"));
            }
         } else {
            source.m_81352_(Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
         }
      }

      return phase;
   }

   private static int newBowels(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
      CommandSourceStack source = (CommandSourceStack)context.getSource();
      Entity entity = EntityArgument.m_91452_(context, "witherstorm");
      ServerLevel world = source.m_81372_();
      int phase = 0;
      if (entity instanceof WitherStormEntity storm) {
         phase = storm.getPhase();
         ServerLevel bowels = WitherStormMod.bowels(world);
         bowels.getCapability(WitherStormModCapabilities.BOWELS_MANAGER).ifPresent(manager -> {
            BowelsInstance instance = manager.get(storm.m_20148_());
            if (instance != null && !instance.isCompleted()) {
               instance.setCompleted(true);
               source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.newBowels.success", new Object[]{storm.m_5446_()}), true);
            } else {
               source.m_81352_(Component.m_237110_("commands.witherstormmod.newBowels.failure", new Object[]{storm.m_5446_()}));
            }
         });
      } else {
         source.m_81352_(Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
      }

      return phase;
   }
}

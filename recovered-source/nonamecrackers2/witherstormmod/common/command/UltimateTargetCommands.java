package nonamecrackers2.witherstormmod.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.ClickEvent.Action;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager.DistractionReason;

public class UltimateTargetCommands {
   public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
      LiteralArgumentBuilder<CommandSourceStack> setPhaseCommand = (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.m_82127_(
               "witherstormmod"
            )
            .requires(commandSource -> commandSource.m_6761_(2)))
         .then(
            ((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_("ultimateTarget")
                           .then(
                              Commands.m_82127_("set")
                                 .then(
                                    ((RequiredArgumentBuilder)Commands.m_82129_("storm", EntityArgument.m_91449_())
                                          .then(
                                             Commands.m_82129_("entity", EntityArgument.m_91449_())
                                                .executes(nonamecrackers2.witherstormmod.common.command.UltimateTargetCommands::setUltimateTarget)
                                          ))
                                       .then(
                                          Commands.m_82129_("pos", BlockPosArgument.m_118239_())
                                             .executes(nonamecrackers2.witherstormmod.common.command.UltimateTargetCommands::setPos)
                                       )
                                 )
                           ))
                        .then(
                           ((LiteralArgumentBuilder)Commands.m_82127_("get")
                                 .then(
                                    Commands.m_82127_("pos")
                                       .then(
                                          Commands.m_82129_("storm", EntityArgument.m_91449_())
                                             .executes(nonamecrackers2.witherstormmod.common.command.UltimateTargetCommands::getPos)
                                       )
                                 ))
                              .then(
                                 Commands.m_82127_("entity")
                                    .then(
                                       Commands.m_82129_("storm", EntityArgument.m_91449_())
                                          .executes(nonamecrackers2.witherstormmod.common.command.UltimateTargetCommands::getUltimateTarget)
                                    )
                              )
                        ))
                     .then(
                        Commands.m_82127_("clear")
                           .then(
                              ((RequiredArgumentBuilder)Commands.m_82129_("storm", EntityArgument.m_91449_())
                                    .then(
                                       Commands.m_82127_("entity")
                                          .executes(nonamecrackers2.witherstormmod.common.command.UltimateTargetCommands::clearUltimateTarget)
                                    ))
                                 .then(Commands.m_82127_("pos").executes(nonamecrackers2.witherstormmod.common.command.UltimateTargetCommands::clearPos))
                           )
                     ))
                  .then(
                     ((LiteralArgumentBuilder)Commands.m_82127_("distractions")
                           .then(
                              Commands.m_82127_("makeDistracted")
                                 .then(
                                    Commands.m_82129_("witherstorm", EntityArgument.m_91449_())
                                       .executes(nonamecrackers2.witherstormmod.common.command.UltimateTargetCommands::makeUltimateTargetDistracted)
                                 )
                           ))
                        .then(
                           Commands.m_82127_("makeFocused")
                              .then(
                                 Commands.m_82129_("witherstorm", EntityArgument.m_91449_())
                                    .executes(nonamecrackers2.witherstormmod.common.command.UltimateTargetCommands::makeUltimateTargetFocused)
                              )
                        )
                  ))
               .then(
                  ((LiteralArgumentBuilder)Commands.m_82127_("chase")
                        .then(
                           Commands.m_82127_("begin")
                              .then(
                                 Commands.m_82129_("witherstorm", EntityArgument.m_91449_())
                                    .executes(nonamecrackers2.witherstormmod.common.command.UltimateTargetCommands::beginChase)
                              )
                        ))
                     .then(
                        Commands.m_82127_("stop")
                           .then(
                              Commands.m_82129_("witherstorm", EntityArgument.m_91449_())
                                 .executes(nonamecrackers2.witherstormmod.common.command.UltimateTargetCommands::stopChase)
                           )
                     )
               )
         );
      dispatcher.register(setPhaseCommand);
   }

   private static int setUltimateTarget(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
      CommandSourceStack source = (CommandSourceStack)context.getSource();
      Entity target = EntityArgument.m_91452_(context, "entity");
      if (EntityArgument.m_91452_(context, "storm") instanceof WitherStormEntity storm) {
         if (target instanceof LivingEntity && !target.equals(storm) && !(target instanceof WitherStormSegmentEntity)) {
            UltimateTargetManager manager = (UltimateTargetManager)storm.getUltimateTargetManager().orElse(null);
            if (manager != null) {
               if (target.m_20148_().equals(manager.getTargetOverride())) {
                  source.m_81352_(Component.m_237115_("commands.witherstormmod.ultimateTarget.set.duplicate"));
               } else {
                  manager.setTargetOverride(target.m_20148_());
                  source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.ultimateTarget.set.success", new Object[]{target.m_5446_()}), true);
               }
            } else {
               source.m_81352_(Component.m_237115_("commands.witherstormmod.ultimateTarget.invalid"));
            }
         } else {
            source.m_81352_(Component.m_237115_("commands.witherstormmod.ultimateTarget.set.entity.invalid"));
         }
      } else {
         source.m_81352_(Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
      }

      return target.m_19879_();
   }

   private static int setPos(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
      CommandSourceStack source = (CommandSourceStack)context.getSource();
      BlockPos pos = BlockPosArgument.m_118242_(context, "pos");
      if (EntityArgument.m_91452_(context, "storm") instanceof WitherStormEntity storm) {
         UltimateTargetManager manager = (UltimateTargetManager)storm.getUltimateTargetManager().orElse(null);
         if (manager != null) {
            if (pos.equals(manager.getBlockTargetOverride())) {
               source.m_81352_(Component.m_237115_("commands.witherstormmod.ultimateTarget.set.duplicate"));
            } else {
               manager.setBlockTargetOverride(pos);
               source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.ultimateTarget.set.success", new Object[]{pos}), true);
            }
         } else {
            source.m_81352_(Component.m_237115_("commands.witherstormmod.ultimateTarget.invalid"));
         }
      } else {
         source.m_81352_(Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
      }

      return 0;
   }

   private static int getPos(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
      CommandSourceStack source = (CommandSourceStack)context.getSource();
      if (EntityArgument.m_91452_(context, "storm") instanceof WitherStormEntity storm) {
         Vec3 pos = storm.getUltimateTargetPos();
         if (pos != null) {
            source.m_288197_(
               () -> {
                  double x = Math.round(pos.f_82479_ * 10.0) / 10.0;
                  double y = Math.round(pos.f_82480_ * 10.0) / 10.0;
                  double z = Math.round(pos.f_82481_ * 10.0) / 10.0;
                  String tpCommand = "/tp " + x + " " + y + " " + z;
                  ClickEvent event = new ClickEvent(Action.RUN_COMMAND, tpCommand);
                  Style style = Style.f_131099_.m_131142_(event).m_131140_(ChatFormatting.BLUE);
                  return Component.m_237110_(
                     "commands.witherstormmod.ultimateTarget.get.pos",
                     new Object[]{storm.m_5446_(), x, y, z, Component.m_237115_("commands.witherstormmod.ultimateTarget.get.pos.click").m_130948_(style)}
                  );
               },
               false
            );
         } else {
            source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.ultimateTarget.get.pos.none", new Object[]{storm.m_5446_()}), false);
         }
      } else {
         source.m_81352_(Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
      }

      return 0;
   }

   private static int getUltimateTarget(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
      CommandSourceStack source = (CommandSourceStack)context.getSource();
      if (EntityArgument.m_91452_(context, "storm") instanceof WitherStormEntity storm) {
         LivingEntity target = storm.getUltimateTarget();
         if (target != null) {
            source.m_288197_(
               () -> {
                  String tpCommand = "/tp " + target.m_20149_();
                  ClickEvent event = new ClickEvent(Action.RUN_COMMAND, tpCommand);
                  Style style = Style.f_131099_.m_131142_(event).m_131140_(ChatFormatting.BLUE);
                  return Component.m_237110_(
                     "commands.witherstormmod.ultimateTarget.get.player",
                     new Object[]{
                        storm.m_5446_(), target.m_5446_(), Component.m_237115_("commands.witherstormmod.ultimateTarget.get.player.click").m_130948_(style)
                     }
                  );
               },
               false
            );
         } else {
            source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.ultimateTarget.get.player.none", new Object[]{storm.m_5446_()}), false);
         }
      } else {
         source.m_81352_(Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
      }

      return 0;
   }

   private static int clearUltimateTarget(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
      CommandSourceStack source = (CommandSourceStack)context.getSource();
      if (EntityArgument.m_91452_(context, "storm") instanceof WitherStormEntity storm) {
         UltimateTargetManager manager = (UltimateTargetManager)storm.getUltimateTargetManager().orElse(null);
         if (manager != null) {
            manager.setTargetOverride(null);
            source.m_288197_(() -> Component.m_237115_("commands.witherstormmod.ultimateTarget.clear.success"), true);
         } else {
            source.m_81352_(Component.m_237115_("commands.witherstormmod.ultimateTarget.invalid"));
         }
      } else {
         source.m_81352_(Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
      }

      return 0;
   }

   private static int clearPos(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
      CommandSourceStack source = (CommandSourceStack)context.getSource();
      if (EntityArgument.m_91452_(context, "storm") instanceof WitherStormEntity storm) {
         UltimateTargetManager manager = (UltimateTargetManager)storm.getUltimateTargetManager().orElse(null);
         if (manager != null) {
            manager.setBlockTargetOverride(null);
            source.m_288197_(() -> Component.m_237115_("commands.witherstormmod.ultimateTarget.clear.success"), true);
         } else {
            source.m_81352_(Component.m_237115_("commands.witherstormmod.ultimateTarget.invalid"));
         }
      } else {
         source.m_81352_(Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
      }

      return 0;
   }

   private static int makeUltimateTargetDistracted(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
      CommandSourceStack source = (CommandSourceStack)context.getSource();
      Entity entity = EntityArgument.m_91452_(context, "witherstorm");
      int phase = 0;
      if (entity != null) {
         if (entity instanceof WitherStormEntity storm) {
            UltimateTargetManager manager = (UltimateTargetManager)storm.getUltimateTargetManager().orElse(null);
            if (manager != null) {
               if (!manager.isDistracted()) {
                  manager.makeDistracted(DistractionReason.FORCED);
                  source.m_288197_(
                     () -> Component.m_237110_(
                        "commands.witherstormmod.distractions.ultimateTargetDistractions.makeDistracted.success", new Object[]{storm.m_5446_()}
                     ),
                     true
                  );
               } else {
                  source.m_81352_(Component.m_237115_("commands.witherstormmod.distractions.ultimateTargetDistractions.makeDistracted.fail"));
               }
            } else {
               source.m_81352_(Component.m_237115_("commands.witherstormmod.ultimateTarget.invalid"));
            }

            phase = storm.getPhase();
         } else {
            source.m_81352_(Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
         }
      }

      return phase;
   }

   private static int makeUltimateTargetFocused(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
      CommandSourceStack source = (CommandSourceStack)context.getSource();
      Entity entity = EntityArgument.m_91452_(context, "witherstorm");
      int phase = 0;
      if (entity != null) {
         if (entity instanceof WitherStormEntity storm) {
            UltimateTargetManager manager = (UltimateTargetManager)storm.getUltimateTargetManager().orElse(null);
            if (manager != null) {
               if (manager.isDistracted()) {
                  manager.makeFocused();
                  source.m_288197_(
                     () -> Component.m_237110_(
                        "commands.witherstormmod.distractions.ultimateTargetDistractions.makeFocused.success", new Object[]{storm.m_5446_()}
                     ),
                     true
                  );
               } else {
                  source.m_81352_(Component.m_237115_("commands.witherstormmod.distractions.ultimateTargetDistractions.makeFocused.fail"));
               }
            } else {
               source.m_81352_(Component.m_237115_("commands.witherstormmod.ultimateTarget.invalid"));
            }

            phase = storm.getPhase();
         } else {
            source.m_81352_(Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
         }
      }

      return phase;
   }

   private static int beginChase(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
      CommandSourceStack source = (CommandSourceStack)context.getSource();
      Entity entity = EntityArgument.m_91452_(context, "witherstorm");
      if (entity != null) {
         if (entity instanceof WitherStormEntity storm) {
            UltimateTargetManager manager = (UltimateTargetManager)storm.getUltimateTargetManager().orElse(null);
            if (manager != null) {
               if (!manager.isTargetStationary()) {
                  manager.accelerate();
                  source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.ultimateTarget.beginChase", new Object[]{storm.m_5446_()}), true);
               } else {
                  source.m_81352_(Component.m_237115_("commands.witherstormmod.ultimateTarget.cannotBeginChase"));
               }
            } else {
               source.m_81352_(Component.m_237115_("commands.witherstormmod.ultimateTarget.invalid"));
            }
         } else {
            source.m_81352_(Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
         }
      }

      return 0;
   }

   private static int stopChase(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
      CommandSourceStack source = (CommandSourceStack)context.getSource();
      Entity entity = EntityArgument.m_91452_(context, "witherstorm");
      if (entity != null) {
         if (entity instanceof WitherStormEntity storm) {
            UltimateTargetManager manager = (UltimateTargetManager)storm.getUltimateTargetManager().orElse(null);
            if (manager != null) {
               if (manager.isTargetStationary()) {
                  manager.deaccelerate();
                  source.m_288197_(() -> Component.m_237110_("commands.witherstormmod.ultimateTarget.stopChase", new Object[]{storm.m_5446_()}), true);
               } else {
                  source.m_81352_(Component.m_237115_("commands.witherstormmod.ultimateTarget.cannotStopChase"));
               }
            } else {
               source.m_81352_(Component.m_237115_("commands.witherstormmod.ultimateTarget.invalid"));
            }
         } else {
            source.m_81352_(Component.m_237115_("commands.witherstormmod.entity.arg.invalid"));
         }
      }

      return 0;
   }
}

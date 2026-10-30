package nonamecrackers2.witherstormmod.api.common.event;

import net.minecraftforge.eventbus.api.bus.EventBus;
import net.minecraftforge.eventbus.api.event.MutableEvent;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

/** Allows listeners to adjust the evolution requirement multiplier. */
public final class WitherStormModifyEvolutionSpeedEvent extends MutableEvent implements WitherStormEvent {
    public static final EventBus<WitherStormModifyEvolutionSpeedEvent> BUS = EventBus.create(WitherStormModifyEvolutionSpeedEvent.class);
    private final WitherStormEntity storm;
    private double modifier;

    public WitherStormModifyEvolutionSpeedEvent(WitherStormEntity storm, double modifier) {
        this.storm = storm;
        this.modifier = modifier;
    }

    @Override public WitherStormEntity getEntity() { return storm; }
    public double getOriginalEvolutionSpeedModifier() { return modifier; }
    public void setEvolutionSpeedModifier(double modifier) { this.modifier = modifier; }
}

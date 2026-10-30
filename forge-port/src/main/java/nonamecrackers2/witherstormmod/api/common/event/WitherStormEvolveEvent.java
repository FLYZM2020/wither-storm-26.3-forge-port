package nonamecrackers2.witherstormmod.api.common.event;

import net.minecraftforge.eventbus.api.bus.EventBus;
import net.minecraftforge.eventbus.api.event.MutableEvent;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

/** Fired on the server immediately before the phase changes. */
public final class WitherStormEvolveEvent extends MutableEvent implements WitherStormEvent {
    public static final EventBus<WitherStormEvolveEvent> BUS = EventBus.create(WitherStormEvolveEvent.class);
    private final WitherStormEntity storm;
    private final int toPhase;

    public WitherStormEvolveEvent(WitherStormEntity storm, int toPhase) {
        this.storm = storm;
        this.toPhase = toPhase;
    }

    @Override public WitherStormEntity getEntity() { return storm; }
    public int getToPhase() { return toPhase; }
}

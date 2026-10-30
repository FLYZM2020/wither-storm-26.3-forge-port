package nonamecrackers2.witherstormmod.api.common.event;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.eventbus.api.bus.CancellableEventBus;
import net.minecraftforge.eventbus.api.event.MutableEvent;
import net.minecraftforge.eventbus.api.event.characteristic.Cancellable;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

/** Fired on the server before an entity contributes to the consumed count. */
public final class WitherStormConsumeEvent extends MutableEvent implements Cancellable, WitherStormEvent {
    public static final CancellableEventBus<WitherStormConsumeEvent> BUS = CancellableEventBus.create(WitherStormConsumeEvent.class);
    private final WitherStormEntity storm;
    private final Entity consumedEntity;
    private int consumedAmount;

    public WitherStormConsumeEvent(WitherStormEntity storm, Entity consumedEntity, int consumedAmount) {
        this.storm = storm;
        this.consumedEntity = consumedEntity;
        this.consumedAmount = consumedAmount;
    }

    @Override public WitherStormEntity getEntity() { return storm; }
    public Entity getConsumedEntity() { return consumedEntity; }
    public int getConsumedAmount() { return consumedAmount; }
    public void setConsumedAmount(int amount) { this.consumedAmount = amount; }
}

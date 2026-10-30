package nonamecrackers2.witherstormmod.api.common.event;

import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

/** Common access to the storm associated with a Forge 26.3 event. */
public interface WitherStormEvent {
    WitherStormEntity getEntity();
}

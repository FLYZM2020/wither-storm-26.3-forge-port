package nonamecrackers2.witherstormmod.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.TicketType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import nonamecrackers2.witherstormmod.WitherStormMod;

/** Temporary, non-persistent storm tickets. They expire if the entity stops refreshing. */
public final class WitherStormModTickets {
    public static final DeferredRegister<TicketType> TYPES = DeferredRegister.create(Registries.TICKET_TYPE, WitherStormMod.MOD_ID);
    public static final RegistryObject<TicketType> STORM = TYPES.register("wither_storm", () -> new TicketType(200L,
            TicketType.FLAG_LOADING | TicketType.FLAG_SIMULATION | TicketType.FLAG_KEEP_DIMENSION_ACTIVE
                    | TicketType.FLAG_CAN_EXPIRE_IF_UNLOADED));
    private WitherStormModTickets() {}
}

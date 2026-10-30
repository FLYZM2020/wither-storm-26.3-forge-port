package nonamecrackers2.witherstormmod.common.evolution;

/** Version-independent phase rules adapted from the 1.20.1 storm entity.
 *
 * The entity supplies the effective per-world or attribute-based speed modifier.
 */
public final class StormEvolutionRules {
    private StormEvolutionRules() {}

    public static int consumptionRequirement(int phase, double evolutionSpeedModifier) {
        int amount = switch (phase) {
            case 0 -> 100;
            case 1 -> 400;
            case 2 -> 1200;
            case 3 -> 18800;
            case 4 -> 195000;
            case 5 -> 351400;
            case 6 -> 580800;
            case 7 -> 2125000;
            default -> 0;
        };
        return (int) (amount * evolutionSpeedModifier);
    }

    public static int subphaseRequirement(int phase, double evolutionSpeedModifier) {
        int previous = consumptionRequirement(phase - 1, evolutionSpeedModifier);
        return previous + (consumptionRequirement(phase, evolutionSpeedModifier) - previous) / 2;
    }

    public static boolean canEvolve(int phase, boolean force, boolean profiling) {
        return force ? phase < 7 : profiling || phase < 5 || phase > 5 && phase < 7;
    }

    public static boolean shouldCheckEvolution(boolean deadOrPlayingDead, int consumed,
            int lastConsumed, int phase, double evolutionSpeedModifier) {
        return !deadOrPlayingDead && consumed > consumptionRequirement(phase, evolutionSpeedModifier)
                && consumed != lastConsumed;
    }
}

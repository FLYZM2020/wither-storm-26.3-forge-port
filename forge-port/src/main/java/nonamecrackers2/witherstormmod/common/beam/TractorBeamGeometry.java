package nonamecrackers2.witherstormmod.common.beam;

import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

/** Geometry extracted from the 1.20.1 tractor-beam helper.
 * The beam entity, collision and networking have not yet been connected.
 */
public final class TractorBeamGeometry {
    private TractorBeamGeometry() {}

    /** Direction must be the unit head-facing vector used by the caller. */
    public static Vec3 closestPoint(Vec3 target, Vec3 headPosition, Vec3 direction,
            double cutoff, double distanceOffset) {
        float distance = (float) (headPosition.distanceTo(target) + distanceOffset);
        if (cutoff != -1.0) {
            distance = Mth.clamp(distance, 0.0F, (float) cutoff);
        }
        return headPosition.add(direction.scale(distance));
    }

    public static boolean contains(Vec3 target, Vec3 headPosition, Vec3 direction,
            double cutoff, double radius, boolean active) {
        if (!active) return false;
        Vec3 point = closestPoint(target, headPosition, direction, cutoff, 0.0);
        double distanceFromHead = Math.sqrt(target.distanceToSqr(headPosition));
        return Math.sqrt(target.distanceToSqr(point)) <= radius * ((distanceFromHead + 30.0) * 0.014);
    }
    /** Reproduces the old beam's attraction direction and near-target slowdown. */
    public static Vec3 pullVelocity(Vec3 target, Vec3 headPosition, Vec3 direction,
            double cutoff, double speed, boolean player) {
        Vec3 pullPosition = !player && target.distanceTo(headPosition) >= 25.0
                ? closestPoint(target, headPosition, direction, cutoff, -5.0)
                : headPosition;
        double distance = target.distanceTo(pullPosition);
        return pullPosition.subtract(target).normalize().scale(speed * Mth.clamp(distance, 0.1, 1.0));
    }

}

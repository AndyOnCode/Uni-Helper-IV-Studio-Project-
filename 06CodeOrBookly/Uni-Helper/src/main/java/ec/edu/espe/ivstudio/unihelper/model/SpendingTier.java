package ec.edu.espe.ivstudio.unihelper.model;
/**
 *
 * @author Tomas Tapia, IV Studio, @ESPE
 */
public enum SpendingTier {
    SAVE(1.0, 500L),
    RECOMMENDED(1.5, 800L),
    SOCIAL(2.2, 1400L);

    private final double multiplier;
    private final long baseAllowanceCents;

    SpendingTier(double multiplier, long baseAllowanceCents) {
        this.multiplier = multiplier;
        this.baseAllowanceCents = baseAllowanceCents;
    }

    public double getMultiplier() { return multiplier; }
    public long getBaseAllowanceCents() { return baseAllowanceCents; }
}

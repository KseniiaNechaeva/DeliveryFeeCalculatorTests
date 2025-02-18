package deliveryFee.constants;

public enum DeliveryLoad {
    EXTRA_HIGH(1.6),
    HIGH(1.4),
    INCREASED(1.2),
    NORMAL(1.0);

    private final double feeCoefficient;

    DeliveryLoad(double feeCoefficient) {
        this.feeCoefficient = feeCoefficient;
    }

    public double getFeeCoefficient() {
        return feeCoefficient;
    }
}

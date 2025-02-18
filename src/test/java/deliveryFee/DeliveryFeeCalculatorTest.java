package deliveryFee;

import deliveryFee.constants.DeliveryLoad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static deliveryFee.DeliveryFeeCalculator.calculateDeliveryFee;
import static deliveryFee.constants.DeliveryLoad.HIGH;
import static deliveryFee.constants.DeliveryLoad.NORMAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeliveryFeeCalculatorTest {

    @ParameterizedTest(name = "(distance={0}, bigPackage={1}, fragile={2}, deliveryLoad={3})")
    @DisplayName("Delivery fee should be calculated for valid parameters combinations")
    @Tag("Positive")
    @CsvSource({
            "150, false, false, NORMAL, 400",
            "30.01, true, false, EXTRA_HIGH, 800",
            "30, true, true, EXTRA_HIGH, 1120",
            "10.1, false, false, HIGH, 420",
            "10, false, true, NORMAL, 500",
            "2.001, false, true, EXTRA_HIGH, 800",
            "2, true, true, HIGH, 770",
            "0.1, true, true, NORMAL, 550",
            "0.0001, false, false, INCREASED, 400",
    })
    void shouldCalculateDeliveryFee(
            double distance,
            boolean bigPackage,
            boolean fragile,
            DeliveryLoad deliveryLoad,
            double expectedDeliveryFee
    ) {
        double deliveryFee = calculateDeliveryFee(distance, bigPackage, fragile, deliveryLoad);

        assertEquals(expectedDeliveryFee, deliveryFee);
    }

    @Test
    @Tag("Negative")
    @DisplayName("Exception should be thrown if a fragile package is delivered over 30 km")
    void shouldThrowExceptionIfFragilePackageIsDeliveredOver30Km() {
        Exception thrown = assertThrows(IllegalArgumentException.class,
                () -> calculateDeliveryFee(30.0001, false, true, HIGH)
        );
        assertEquals("Fragile package can't be shipped over 30 km", thrown.getMessage());
    }

    @ParameterizedTest(name = "(distance = {0})")
    @Tag("Negative")
    @DisplayName("Exception should be thrown if distance is not a positive number")
    @ValueSource(doubles = {0, -1})
    void shouldThrowExceptionIfDistanceIsInvalid(double distance) {
        Exception thrown = assertThrows(IllegalArgumentException.class,
                () -> calculateDeliveryFee(distance, false, false, NORMAL)
        );
        assertEquals("Package cannot be shipped at a distance of less than 0 km", thrown.getMessage());
    }

    @Test
    @Tag("Negative")
    @DisplayName("Exception should be thrown if delivery load is null")
    void shouldThrowExceptionIfDeliveryLoadIsInvalid() {
        Exception thrown = assertThrows(IllegalArgumentException.class,
                () -> calculateDeliveryFee(15, false, false, null)
        );
        assertEquals("Delivery load cannot be null", thrown.getMessage());
    }
}

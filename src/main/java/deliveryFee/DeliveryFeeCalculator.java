package deliveryFee;

import deliveryFee.constants.DeliveryLoad;

import static deliveryFee.constants.DeliveryFeeConstants.BIG_PACKAGE_FEE;
import static deliveryFee.constants.DeliveryFeeConstants.DISTANCE_10KM;
import static deliveryFee.constants.DeliveryFeeConstants.DISTANCE_2KM;
import static deliveryFee.constants.DeliveryFeeConstants.DISTANCE_30KM;
import static deliveryFee.constants.DeliveryFeeConstants.DISTANCE_FEE_0_2KM;
import static deliveryFee.constants.DeliveryFeeConstants.DISTANCE_FEE_10_30KM;
import static deliveryFee.constants.DeliveryFeeConstants.DISTANCE_FEE_2_10KM;
import static deliveryFee.constants.DeliveryFeeConstants.DISTANCE_FEE_OVER_30KM;
import static deliveryFee.constants.DeliveryFeeConstants.FRAGILE_PACKAGE_FEE;
import static deliveryFee.constants.DeliveryFeeConstants.MAX_DISTANCE_FOR_FRAGILE_PACKAGE;
import static deliveryFee.constants.DeliveryFeeConstants.MIN_DELIVERY_FEE;
import static deliveryFee.constants.DeliveryFeeConstants.NOT_FRAGILE_PACKAGE_FEE;
import static deliveryFee.constants.DeliveryFeeConstants.SMALL_PACKAGE_FEE;
import static deliveryFee.constants.ErrorMessages.FRAGILE_PACKAGE_ERROR;
import static deliveryFee.constants.ErrorMessages.INVALID_DELIVERY_LOAD_ERROR;
import static deliveryFee.constants.ErrorMessages.INVALID_DISTANCE_ERROR;

public class DeliveryFeeCalculator {

    public static double calculateDeliveryFee(double distance, boolean isBigPackage, boolean isFragile, DeliveryLoad deliveryLoad) {
        validateFragilityCondition(isFragile, distance);
        double baseDeliveryFee = distanceFee(distance) + bigPackageFee(isBigPackage) + fragileFee(isFragile);

        return Math.max(baseDeliveryFee * deliveryLoadCoefficient(deliveryLoad), MIN_DELIVERY_FEE);
    }

    private static double distanceFee(double distance) {
        validateDistance(distance);

        if (distance > DISTANCE_30KM) return DISTANCE_FEE_OVER_30KM;
        else if (distance > DISTANCE_10KM) return DISTANCE_FEE_10_30KM;
        else if (distance > DISTANCE_2KM) return DISTANCE_FEE_2_10KM;
        else return DISTANCE_FEE_0_2KM;
    }

    private static double bigPackageFee(boolean isBigPackage) {
        return isBigPackage ? BIG_PACKAGE_FEE : SMALL_PACKAGE_FEE;
    }

    private static double fragileFee(boolean isFragile) {
        return isFragile ? FRAGILE_PACKAGE_FEE : NOT_FRAGILE_PACKAGE_FEE;
    }

    private static double deliveryLoadCoefficient(DeliveryLoad deliveryLoad) {
        validateDeliveryLoad(deliveryLoad);
        return deliveryLoad.getFeeCoefficient();
    }

    private static void validateFragilityCondition(boolean isFragile, double distance) {
        if (isFragile && distance > MAX_DISTANCE_FOR_FRAGILE_PACKAGE) {
            throw new IllegalArgumentException(FRAGILE_PACKAGE_ERROR);
        }
    }

    private static void validateDistance(double distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException(INVALID_DISTANCE_ERROR);
        }
    }

    private static void validateDeliveryLoad(DeliveryLoad deliveryLoad) {
        if (deliveryLoad == null) {
            throw new IllegalArgumentException(INVALID_DELIVERY_LOAD_ERROR);
        }
    }
}

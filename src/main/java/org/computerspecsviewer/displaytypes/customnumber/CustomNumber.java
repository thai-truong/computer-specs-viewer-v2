package org.computerspecsviewer.displaytypes.customnumber;

import oshi.util.tuples.Pair;

import java.util.HashMap;
import java.util.Map;

public class CustomNumber {
    private static final Integer[] exponents = {3, 6, 9, 12};
    private static final Integer BIGGEST_EXPONENT_SUPPORTED = exponents[exponents.length - 1];
    private static final Integer SMALLEST_EXPONENT_SUPPORTED = exponents[0];

    private static final Map<Integer, CustomNumberUnits> expToCustomNumberUnit;

    static {
        expToCustomNumberUnit = new HashMap<>();
        expToCustomNumberUnit.put(0, CustomNumberUnits.NONE);
        expToCustomNumberUnit.put(3, CustomNumberUnits.K);
        expToCustomNumberUnit.put(6, CustomNumberUnits.M);
        expToCustomNumberUnit.put(9, CustomNumberUnits.B);
        expToCustomNumberUnit.put(12, CustomNumberUnits.T);
    }

    private Number value;
    private double convValue;
    private CustomNumberUnits convUnit;
    private String valueName;

    public CustomNumber(Number value, String valueName) {
        this.value = value;
        this.valueName = valueName;

        Pair<Double, CustomNumberUnits> customNumberValues = getCustomNumberValues(value);
        convValue = customNumberValues.getA();
        convUnit = customNumberValues.getB();
    }

    @Override
    public String toString() {
        return String.format("%.2f%s %s", convValue, convUnit.getCustomNumberUnit(), valueName);
    }

    public static Pair<Double, CustomNumberUnits> getCustomNumberValues(Number value) {
        int exponent = 0;
        double targetValue = value.doubleValue();

        // If target value < 10^3 (1000), exponent = 0
        if(targetValue >= Math.pow(10, BIGGEST_EXPONENT_SUPPORTED)) {
            exponent = BIGGEST_EXPONENT_SUPPORTED;
        } else if(targetValue >= Math.pow(10, SMALLEST_EXPONENT_SUPPORTED)) {
            for(int i = exponents.length - 1; i > 0; i--) {
                int lowerBound = exponents[i - 1];
                int upperBound = exponents[i];

                if(targetValue >= Math.pow(10, lowerBound) && targetValue < Math.pow(10, upperBound)) {
                    exponent = lowerBound;
                    break;
                }
            }
        }

        double convertedValue = targetValue / Math.pow(10, exponent);
        CustomNumberUnits convertedUnit = expToCustomNumberUnit.get(exponent);

        return new Pair<>(convertedValue, convertedUnit);
    }
}
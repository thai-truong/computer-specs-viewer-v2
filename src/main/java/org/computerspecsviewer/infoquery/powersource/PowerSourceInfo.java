package org.computerspecsviewer.infoquery.powersource;

import org.computerspecsviewer.displaytypes.customboolean.CustomBoolean;
import org.computerspecsviewer.displaytypes.customduration.CustomDuration;
import org.computerspecsviewer.displaytypes.customduration.DurationLimit;
import org.computerspecsviewer.displaytypes.customfield.CustomField;
import org.computerspecsviewer.displaytypes.customnumber.CustomNumber;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import org.computerspecsviewer.infoquery.utils.PrintHelpers;
import oshi.hardware.PowerSource;

public class PowerSourceInfo extends BaseInfoQuery {
    public String name;
    public String deviceName;
    public String serialNumber;
    public String manufacturer;
    public String batteryChemistry;
    public CustomBoolean isCharging;
    public CustomBoolean isPluggedIntoExternalSource;
    public String estimatedRemainingCapacityPercent;
    public CustomDuration estimatedTimeRemaining;
    public CustomField<Double> temperature;
    public CustomField<Double> amperage;
    public CustomField<Double> voltage;
    public CustomField<Double> powerUsageRate;
    public CustomField<Integer> currentCapacity;
    public CustomField<Integer> maximumCapacity;
    public CustomField<Integer> designCapacity;
    public CustomNumber cycleCount;

    public PowerSourceInfo(PowerSource powerSource) {
        String capacityUnit = powerSource.getCapacityUnits().toString();
        double capacityPercent = powerSource.getRemainingCapacityPercent();

        name = powerSource.getName();
        deviceName = powerSource.getDeviceName();
        serialNumber = powerSource.getSerialNumber();
        manufacturer = powerSource.getManufacturer();
        batteryChemistry = powerSource.getChemistry();
        isCharging = new CustomBoolean(powerSource.isCharging());
        isPluggedIntoExternalSource = new CustomBoolean(powerSource.isPowerOnLine());
        estimatedRemainingCapacityPercent = PrintHelpers.getPercentString(capacityPercent, 1);
        temperature = new CustomField<>(powerSource.getTemperature(), "degrees Celsius");
        amperage = new CustomField<>(powerSource.getAmperage(), "mA");
        voltage = new CustomField<>(powerSource.getVoltage(), "volts");
        powerUsageRate = new CustomField<>(powerSource.getPowerUsageRate(), "mW");
        currentCapacity = new CustomField<>(powerSource.getCurrentCapacity(), capacityUnit);
        maximumCapacity = new CustomField<>(powerSource.getMaxCapacity(), capacityUnit);
        designCapacity = new CustomField<>(powerSource.getDesignCapacity(), capacityUnit);
        cycleCount = new CustomNumber(powerSource.getCycleCount(), "cycles");

        long estRemTime = (long)powerSource.getTimeRemainingEstimated();
        estimatedTimeRemaining = new CustomDuration(estRemTime, DurationLimit.UP_TO_HOURS);
    }
}

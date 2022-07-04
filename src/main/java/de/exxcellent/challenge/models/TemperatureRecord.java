package de.exxcellent.challenge.models;

import de.exxcellent.challenge.interfaces.Record;

/**
 * Temperature record to store day and daily min and max Temperature
 * @author Andreas Krauss <am.krauss@web.de>
 */
public class TemperatureRecord implements Record {
    private final int day;
    private final int minTemperature;
    private final int maxTemperature;

    private final static int lowerTemperatureLimit = -148;
    private final static int upperTemperatureLimit =  153;

    public TemperatureRecord(int day, int minTemperature, int maxTemperature) {
        if (!validateTemperatureLimits(minTemperature, maxTemperature))
            throw new IllegalArgumentException("Temperature exceeds limits");
        if (!validateTemperatureValues(minTemperature, maxTemperature))
            throw new IllegalArgumentException("maxTemperature lower than minTemperature");
        this.day = day;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    private boolean validateTemperatureLimits(int minTemperature, int maxTemperature){
        if (minTemperature < lowerTemperatureLimit){ return false; }
        if (minTemperature > upperTemperatureLimit){ return false; }
        if (maxTemperature < lowerTemperatureLimit){ return false; }
        if (maxTemperature > upperTemperatureLimit){ return false; }
        return true;
    }

    private boolean validateTemperatureValues(int minTemperature, int maxTemperature){
        return minTemperature <= maxTemperature;
    }

    public String getId() {
        return Integer.toString(this.day);
    }

    public int getMinTemperature() {
        return this.minTemperature;
    }

    public int getMaxTemperature() {
        return this.maxTemperature;
    }

    @Override
    public int getDifference() {
        return Math.abs(this.maxTemperature-this.minTemperature);
    }
}

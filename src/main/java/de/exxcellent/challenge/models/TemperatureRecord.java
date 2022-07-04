package de.exxcellent.challenge.models;

import de.exxcellent.challenge.interfaces.Record;

public class TemperatureRecord implements Record {
    private int id;
    private int minTemperature;
    private int maxTemperature;

    private static int lowerTemperatureLimit = -148;
    private static int upperTemperatureLimit =  153;

    public TemperatureRecord(int id, int minTemperature, int maxTemperature) {
        if (!validateTemperatureLimits(minTemperature, maxTemperature))
            throw new IllegalArgumentException("Temperature exceeds limits");
        if (!validateTemperatureValues(minTemperature, maxTemperature))
            throw new IllegalArgumentException("maxTemperature lower than minTemperature");
        this.id = id;
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

    public int getId() {
        return this.id;
    }

    public int getMinTemperature() {
        return this.minTemperature;
    }

    public int getMaxTemperature() {
        return this.maxTemperature;
    }

    public int getDifference() {
        return Math.abs(this.maxTemperature-this.minTemperature);
    }
}

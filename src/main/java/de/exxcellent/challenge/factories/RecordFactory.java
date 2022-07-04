package de.exxcellent.challenge.factories;

import de.exxcellent.challenge.interfaces.Record;
import de.exxcellent.challenge.models.FootballRecord;
import de.exxcellent.challenge.models.TemperatureRecord;

public class RecordFactory {

    /**
     * Creates a TemperatureRecord based on the given attributes.
     *
     * @param id                The day in the month as int
     * @param minTemperature    The minimum temperature of the day as int
     * @param maxTemperature    The maximum temperature of the day as int
     * @return                  new TemperatureRecord
     */
    public Record createRecord(int id, int minTemperature, int maxTemperature){
        return new TemperatureRecord(id, minTemperature, maxTemperature);
    }

    /**
     * Creates a FootballRecord based on the given attributes.
     *
     * @param id                The team Identifier (i.e., its name) as String
     * @param goals             The amount of goals achieved as int
     * @param goalsAllowed      The amount of goals received as int
     * @return                  new FootballRecord
     */
    public Record createRecord(String id, int goals, int goalsAllowed){
        return new FootballRecord(id, goals, goalsAllowed);
    }
}

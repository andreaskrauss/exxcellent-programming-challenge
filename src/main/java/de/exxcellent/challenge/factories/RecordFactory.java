package de.exxcellent.challenge.factories;

import de.exxcellent.challenge.interfaces.Record;
import de.exxcellent.challenge.models.TemperatureRecord;

public class RecordFactory {

    public Record createRecord(int id, int minTemperature, int maxTemperature){
        return new TemperatureRecord(id, minTemperature, maxTemperature);
    }

}

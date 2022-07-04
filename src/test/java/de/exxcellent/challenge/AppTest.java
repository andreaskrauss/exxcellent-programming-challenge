package de.exxcellent.challenge;

import de.exxcellent.challenge.factories.RecordFactory;
import de.exxcellent.challenge.interfaces.Record;
import de.exxcellent.challenge.models.TemperatureRecord;
import de.exxcellent.challenge.readers.CSVRecordReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Example JUnit 5 test case.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {

    private String successLabel = "not successful";

    @BeforeEach
    void setUp() {
        successLabel = "successful";
    }
        RecordFactory factory = new RecordFactory();

    @Test
    void createTemperatureRecord_validTemperature_validObject(){
        int id = 1;
        int minTemperature = 60;
        int maxTemperature = 80;
        TemperatureRecord record = (TemperatureRecord) factory.createRecord(id, minTemperature, maxTemperature);
        assertEquals(id, record.getId());
        assertEquals(minTemperature, record.getMinTemperature());
        assertEquals(maxTemperature, record.getMaxTemperature());
    }

    @Test
    void createTemperatureRecord_invalidLowMinTemperature_raiseException(){
        int id = 1;
        int minTemperature = -1200;
        int maxTemperature = 80;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TemperatureRecord record = (TemperatureRecord) factory.createRecord(id, minTemperature, maxTemperature);
        });

        String expectedMessage = "Temperature exceeds limits";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createTemperatureRecord_invalidLowMaxTemperature_raiseException(){
        int id = 1;
        int minTemperature = 70;
        int maxTemperature = -1200;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TemperatureRecord record = (TemperatureRecord) factory.createRecord(id, minTemperature, maxTemperature);
        });

        String expectedMessage = "Temperature exceeds limits";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createTemperatureRecord_invalidHighMinTemperature_raiseException(){
        int id = 1;
        int minTemperature = 1200;
        int maxTemperature = 80;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TemperatureRecord record = (TemperatureRecord) factory.createRecord(id, minTemperature, maxTemperature);
        });

        String expectedMessage = "Temperature exceeds limits";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createTemperatureRecord_invalidHighMaxTemperature_raiseException(){
        int id = 1;
        int minTemperature = 70;
        int maxTemperature = 1200;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TemperatureRecord record = (TemperatureRecord) factory.createRecord(id, minTemperature, maxTemperature);
        });

        String expectedMessage = "Temperature exceeds limits";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createTemperatureRecord_MaxTemperatureLowerThanMinTemperature_raiseException(){
        int id = 1;
        int minTemperature = 90;
        int maxTemperature = 70;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TemperatureRecord record = (TemperatureRecord) factory.createRecord(id, minTemperature, maxTemperature);
        });

        String expectedMessage = "maxTemperature lower than minTemperature";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getDifference_positiveTemperatures_absoluteDistance(){
        int id = 1;
        int minTemperature = 60;
        int maxTemperature = 80;
        TemperatureRecord record = (TemperatureRecord) factory.createRecord(id, minTemperature, maxTemperature);

        int expectedResult = Math.abs(maxTemperature-minTemperature);
        assertEquals(expectedResult, record.getDifference());
    }

    @Test
    void getDifference_differentSigns_absoluteDistance(){
        int id = 1;
        int minTemperature = -60;
        int maxTemperature = 80;
        TemperatureRecord record = (TemperatureRecord) factory.createRecord(id, minTemperature, maxTemperature);

        int expectedResult = Math.abs(maxTemperature-minTemperature);
        assertEquals(expectedResult, record.getDifference());
    }

    @Test
    void getDifference_negativeTemperatures_absoluteDistance(){
        int id = 1;
        int minTemperature = -60;
        int maxTemperature = -40;
        TemperatureRecord record = (TemperatureRecord) factory.createRecord(id, minTemperature, maxTemperature);

        int expectedResult = Math.abs(maxTemperature-minTemperature);
        assertEquals(expectedResult, record.getDifference());
    }

    @Test
    void readRecord_csv_listOfTemperatureRecords(){
        int expectedRows = 2;
        String path = "weather_sample.csv";
        List<TemperatureRecord> testRecords = new ArrayList<TemperatureRecord>();
        testRecords.add((TemperatureRecord) factory.createRecord(1,59, 88));
        testRecords.add((TemperatureRecord) factory.createRecord(2,63, 79));

        CSVRecordReader reader = new CSVRecordReader(path);
        List<TemperatureRecord> records = (List<TemperatureRecord>) reader.readRecords();

        assertEquals(expectedRows, records.size());

        for(int i = 0; i < records.size(); i++){
            assertEquals(testRecords.get(i).getId(), records.get(i).getId());
            assertEquals(testRecords.get(i).getMinTemperature(), records.get(i).getMinTemperature());
            assertEquals(testRecords.get(i).getMaxTemperature(), records.get(i).getMaxTemperature());
        }
    }




//    @Test
//    void aPointlessTest() {
//        assertEquals("successful", successLabel, "My expectations were not met");
//    }

//    @Test
//    void runFootball() {
//        App.main("--football", "football.csv");
//    }

}
package de.exxcellent.challenge;

import de.exxcellent.challenge.collections.RecordCollection;
import de.exxcellent.challenge.factories.RecordFactory;
import de.exxcellent.challenge.interfaces.Record;
import de.exxcellent.challenge.models.FootballRecord;
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
    void createTemperatureRecord_validTemperature_validRecord(){
        int id = 1;
        int minTemperature = 60;
        int maxTemperature = 80;
        TemperatureRecord record = (TemperatureRecord) factory.createRecord(id, minTemperature, maxTemperature);
        String expectedId = Integer.toString(id);
        assertEquals(expectedId, record.getId());
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
    void readRecords_csv_listOfTemperatureRecords(){
        int expectedRows = 2;
        String path = "de/exxcellent/challenge/weather_sample.csv";
        List<TemperatureRecord> testRecords = new ArrayList<>();
        testRecords.add((TemperatureRecord) factory.createRecord(1,59, 88));
        testRecords.add((TemperatureRecord) factory.createRecord(2,63, 79));

        CSVRecordReader reader = new CSVRecordReader();
        List<Record> records = reader.readRecords(path, "--weather");

        assertEquals(expectedRows, records.size());

        for(int i = 0; i < records.size(); i++){
            TemperatureRecord temp = (TemperatureRecord) records.get(i);
            assertEquals(testRecords.get(i).getId(), temp.getId());
            assertEquals(testRecords.get(i).getMinTemperature(), temp.getMinTemperature());
            assertEquals(testRecords.get(i).getMaxTemperature(), temp.getMaxTemperature());
        }
    }

    @Test
    void getRecordWithMinimalDifference_validInput_record(){
        List<Record> temperatureRecords = new ArrayList<>();
        temperatureRecords.add(factory.createRecord(1,59, 88));
        temperatureRecords.add(factory.createRecord(2,63, 79));
        temperatureRecords.add(factory.createRecord(3,64, 85));

        RecordCollection collection = new RecordCollection(temperatureRecords);
        String id = collection.getRecordWithMinimalDifference();

        assertEquals("2", id);
    }

    @Test
    void runWeather() {
        App.main("--weather", "de/exxcellent/challenge/weather_sample.csv");
    }


    @Test
    void createFootballRecord_validGoals_validRecord(){
        String team = "Sampleteam";
        int goals = 9;
        int goalsAllowed = 1;

        FootballRecord record = (FootballRecord) factory.createRecord(team, goals, goalsAllowed);
        assertEquals(team, record.getId());
        assertEquals(goals, record.getGoals());
        assertEquals(goalsAllowed, record.getGoalsAllowed());
    }

    @Test
    void createFootballRecord_invalidGoals_raiseException(){
        String team = "Sampleteam";
        int validGoals = 9;
        int invalidGoals = -1;
        int validGoalsAllowed = 3;
        int invalidGoalsAllowed = -2;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createRecord(team, invalidGoals, validGoalsAllowed);
        });

        String expectedMessage = "Goal counts cannot be lower than 0";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            factory.createRecord(team, validGoals, invalidGoalsAllowed);
        });

        String actualMessage2 = exception2.getMessage();

        assertTrue(actualMessage2.contains(expectedMessage));
    }

    @Test
    void readRecords_csv_listOfFootballRecords(){
        int expectedRows = 2;
        String path = "de/exxcellent/challenge/football_sample.csv";
        List<FootballRecord> testRecords = new ArrayList<>();
        testRecords.add((FootballRecord) factory.createRecord("Arsenal",79, 36));
        testRecords.add((FootballRecord) factory.createRecord("Liverpool",67, 30));

        CSVRecordReader reader = new CSVRecordReader();
        List<Record> records = reader.readRecords(path, "--football");

        assertEquals(expectedRows, records.size());

        for(int i = 0; i < records.size(); i++){
            FootballRecord temp = (FootballRecord) records.get(i);
            assertEquals(testRecords.get(i).getId(), temp.getId());
            assertEquals(testRecords.get(i).getGoals(), temp.getGoals());
            assertEquals(testRecords.get(i).getGoalsAllowed(), temp.getGoalsAllowed());
        }
    }

    @Test
    void getDifference_validGoalCounts_absoluteDistance(){
        String team = "European";
        int goals = 20;
        int goalsAllowed = 12;
        FootballRecord record = (FootballRecord) factory.createRecord(team, goals, goalsAllowed);

        int expectedResult = Math.abs(goals-goalsAllowed);
        assertEquals(expectedResult, record.getDifference());
    }

//    @Test
//    void runFootball() {
//        App.main("--weather", "football.csv", "--football", "football.csv");
//    }

}
package de.exxcellent.challenge;

import de.exxcellent.challenge.models.TemperatureRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    void createTemperatureRecord_validTemperature_validObject(){
        int id = 1;
        int minTemperature = 60;
        int maxTemperature = 80;
        TemperatureRecord record = new TemperatureRecord(id, minTemperature, maxTemperature);
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
            TemperatureRecord record = new TemperatureRecord(id, minTemperature, maxTemperature);;
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
            TemperatureRecord record = new TemperatureRecord(id, minTemperature, maxTemperature);;
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
            TemperatureRecord record = new TemperatureRecord(id, minTemperature, maxTemperature);;
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
            TemperatureRecord record = new TemperatureRecord(id, minTemperature, maxTemperature);;
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
            TemperatureRecord record = new TemperatureRecord(id, minTemperature, maxTemperature);;
        });

        String expectedMessage = "maxTemperature lower than minTemperature";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
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
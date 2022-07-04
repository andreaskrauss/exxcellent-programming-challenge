package de.exxcellent.challenge;

import de.exxcellent.challenge.collections.RecordCollection;
import de.exxcellent.challenge.interfaces.Record;
import de.exxcellent.challenge.readers.CSVRecordReader;

import java.util.List;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        CSVRecordReader temperatureReader = new CSVRecordReader();
        List<Record> temperatureRecords = temperatureReader.readRecords(args[1], args[0]);
        RecordCollection temperaturesCollection = new RecordCollection(temperatureRecords);

        String dayWithSmallestTempSpread = temperaturesCollection.getRecordWithMinimalDifference();
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}

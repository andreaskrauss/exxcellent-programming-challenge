package de.exxcellent.challenge.readers;

import de.exxcellent.challenge.factories.RecordFactory;
import de.exxcellent.challenge.interfaces.RecordReader;
import de.exxcellent.challenge.interfaces.Record;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVRecordReader implements RecordReader {
    public CSVRecordReader(){ }
    @Override
    public List<Record> readRecords(String filepath, String type) {
        filepath = "de/exxcellent/challenge/" + filepath;
        InputStream is = getClass().getClassLoader().getResourceAsStream(filepath);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            List<Record> records = new ArrayList<>();
            RecordFactory recordFactory = new RecordFactory();


            String[] header = br.readLine().split(",");
            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split(",");
                if (type.contains("weather"))
                {
                    int id              = Integer.parseInt(attributes[Arrays.asList(header).indexOf("Day")]);
                    int minTemperature  = Integer.parseInt(attributes[Arrays.asList(header).indexOf("MnT")]);
                    int maxTemperature  = Integer.parseInt(attributes[Arrays.asList(header).indexOf("MxT")]);
                    records.add(recordFactory.createRecord(id, minTemperature, maxTemperature));
                }
                if (type.contains("football"))
                {
                    String team        = attributes[Arrays.asList(header).indexOf("Team")];
                    int goals         = Integer.parseInt(attributes[Arrays.asList(header).indexOf("Goals")]);
                    int goalsAllowed  = Integer.parseInt(attributes[Arrays.asList(header).indexOf("Goals Allowed")]);
                    records.add(recordFactory.createRecord(team, goals, goalsAllowed));
                }



                line = br.readLine();
            }

            return records;

        }catch (IOException | IllegalArgumentException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }
}

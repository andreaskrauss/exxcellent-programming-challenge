package de.exxcellent.challenge.readers;

import de.exxcellent.challenge.factories.RecordFactory;
import de.exxcellent.challenge.interfaces.RecordReader;
import de.exxcellent.challenge.interfaces.Record;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVRecordReader implements RecordReader {
    public CSVRecordReader(){ }
    @Override
    public List<Record> readRecords(String filepath, boolean hasHeader) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(filepath);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            List<Record> records = new ArrayList<>();
            RecordFactory recordFactory = new RecordFactory();


            String line = br.readLine();

            if (hasHeader){ line = br.readLine(); }

            while (line != null) {

                String[] attributes = line.split(",");
                int id              = Integer.parseInt(attributes[0]);
                int minTemperature  = Integer.parseInt(attributes[2]);
                int maxTemperature  = Integer.parseInt(attributes[1]);
                records.add(recordFactory.createRecord(id, minTemperature, maxTemperature));

                line = br.readLine();
            }

            return records;

        }catch (IOException | IllegalArgumentException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }
}

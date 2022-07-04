package de.exxcellent.challenge.readers;

import de.exxcellent.challenge.factories.RecordFactory;
import de.exxcellent.challenge.interfaces.RecordReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CSVRecordReader<T> implements RecordReader<T> {
    public CSVRecordReader(){ }
    @Override
    public List<T> readRecords(String filepath, boolean hasHeader) {
        try (BufferedReader br = Files.newBufferedReader(Path.of(filepath),
                StandardCharsets.US_ASCII)) {

            List<T> records = new ArrayList<>();
            RecordFactory recordFactory = new RecordFactory();


            String line = br.readLine();

            if (hasHeader){ line = br.readLine(); }

            while (line != null) {

                String[] attributes = line.split(",");
                int id              = Integer.parseInt(attributes[0]);
                int minTemperature  = Integer.parseInt(attributes[2]);
                int maxTemperature  = Integer.parseInt(attributes[1]);
                records.add((T) recordFactory.createRecord(id, minTemperature, maxTemperature));

                line = br.readLine();
            }

            return records;

        }catch (IOException | IllegalArgumentException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }
}

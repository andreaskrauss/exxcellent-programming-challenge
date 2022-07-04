package de.exxcellent.challenge.readers;

import de.exxcellent.challenge.interfaces.Record;
import de.exxcellent.challenge.interfaces.RecordReader;

public class CSVRecordReader implements RecordReader {
    public CSVRecordReader(String filepath){
        this.filepath = filepath;
    }

    private String filepath;
    @Override
    public Record readRecords() {
        return null;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}

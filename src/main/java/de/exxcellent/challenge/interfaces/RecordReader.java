package de.exxcellent.challenge.interfaces;

import java.util.List;

public interface RecordReader{
    List<Record> readRecords(String filepath, String type);
}

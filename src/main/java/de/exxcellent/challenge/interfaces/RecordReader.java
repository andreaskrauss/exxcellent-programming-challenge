package de.exxcellent.challenge.interfaces;

import java.util.List;

public interface RecordReader <T>{
    List<T> readRecords(String filepath, boolean hasHeaderline);
}

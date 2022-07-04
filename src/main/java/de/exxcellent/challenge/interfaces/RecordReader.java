package de.exxcellent.challenge.interfaces;

import java.util.List;

/**
 * Football record to store team and goal counts
 * @author Andreas Krauss <am.krauss@web.de>
 */
public interface RecordReader{
    List<Record> readRecords(String filepath, String type);
}

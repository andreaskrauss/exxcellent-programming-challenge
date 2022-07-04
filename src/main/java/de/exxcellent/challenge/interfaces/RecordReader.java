package de.exxcellent.challenge.interfaces;

import java.util.List;

/**
 * Football record to store team and goal counts
 *
 * @author Andreas Krauss <am.krauss@web.de>
 */
public interface RecordReader {
    /**
     * Read CSV file and create records depending on their type.
     *
     * @param filepath  The path to the file based on the `resource` folder as String
     * @param type      A String determining the type of the objects stored in the file
     * @return          List of records
     */
    List<Record> readRecords(String filepath, String type);
}

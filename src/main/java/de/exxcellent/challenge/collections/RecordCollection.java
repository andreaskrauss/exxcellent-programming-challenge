package de.exxcellent.challenge.collections;

import java.util.List;
import de.exxcellent.challenge.interfaces.Record;

public class RecordCollection {

    private final List<Record> records;

    public RecordCollection (List<Record> records){
        this.records = records;
    }

    public String getRecordWithMinimalDifference(){
        int minDifference = Integer.MAX_VALUE;
        Record identifiedRecord = null;

        if (this.records == null || this.records.size() == 0){
            throw new IllegalArgumentException("Records are null or empty");
        }

        for (Record record : this.records){
            if (record.getDifference() < minDifference){
                minDifference = record.getDifference();
                identifiedRecord = record;
            }
        }
        // Should nether produce null, since there should always be record
        return Integer.toString(identifiedRecord.getId());
    }



}
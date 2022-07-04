package de.exxcellent.challenge.interfaces;

/**
 *
 * @author Andreas Krauss <am.krauss@web.de>
 */
public interface Record {

    /**
     * @return identifier of a record
     */
    String getId();

    /**
     * @return the absolute difference between a records attributes
     */
    int getDifference();
}

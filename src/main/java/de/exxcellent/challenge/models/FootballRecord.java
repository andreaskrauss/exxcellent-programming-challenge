package de.exxcellent.challenge.models;

import de.exxcellent.challenge.interfaces.Record;

/**
 * Football record to store team and goal counts
 *
 * @author Andreas Krauss <am.krauss@web.de>
 */
public class FootballRecord implements Record {
    private final String name;
    private final int goals;
    private final int goalsAllowed;

    public FootballRecord(String name, int goals, int goalsAllowed) {
        this.name = name;
        if (!validateGoals(goals, goalsAllowed)) {
            throw new IllegalArgumentException("Goal counts cannot be lower than 0");
        }
        this.goals = goals;
        this.goalsAllowed = goalsAllowed;

    }

    /**
     * Checks whether the amount of goals is in the acceptance range
     *
     * @param goals        An integer representing the amount of goals achieved
     * @param goalsAllowed An integer representing the amount of goals received
     * @return A boolean representing validity state
     */
    private boolean validateGoals(int goals, int goalsAllowed) {
        return goals >= 0 && goalsAllowed >= 0;
    }

    public int getGoals() {
        return this.goals;
    }

    public int getGoalsAllowed() {
        return this.goalsAllowed;
    }

    /**
     * @return String representing the team name
     */
    @Override
    public String getId() {
        return this.name;
    }

    /**
     * @return the absolute difference representing the goal difference of a team
     */
    @Override
    public int getDifference() {
        return Math.abs(this.goals - this.goalsAllowed);
    }
}

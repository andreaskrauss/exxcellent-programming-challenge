package de.exxcellent.challenge.models;

import de.exxcellent.challenge.interfaces.Record;

/**
 * Football record to store team and goal counts
 * @author Andreas Krauss <am.krauss@web.de>
 */
public class FootballRecord implements Record {
    private final String name;
    private final int goals;
    private final int goalsAllowed;

    public FootballRecord(String name, int goals, int goalsAllowed){
        this.name = name;
        if (!validateGoals(goals, goalsAllowed)) {
            throw new IllegalArgumentException("Goal counts cannot be lower than 0");
        }
        this.goals = goals;
        this.goalsAllowed = goalsAllowed;

    }

    private boolean validateGoals(int goals, int goalsAllowed) {
        return goals >= 0 && goalsAllowed >= 0;
    }

    public int getGoals(){
        return this.goals;
    }

    public int getGoalsAllowed(){
        return this.goalsAllowed;
    }

    @Override
    public String getId() {
        return this.name;
    }

    @Override
    public int getDifference() {
        return Math.abs(this.goals-this.goalsAllowed);
    }
}

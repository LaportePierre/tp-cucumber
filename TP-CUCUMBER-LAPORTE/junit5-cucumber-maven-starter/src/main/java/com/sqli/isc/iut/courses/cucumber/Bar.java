package com.sqli.isc.iut.courses.cucumber;

import java.util.ArrayList;

public class Bar {
    private String name;
    private String type;
    private int maxSeats;
    private ArrayList<People> peopleInBar;

    public Bar(String name, String type, int maxSeats) {
        this.name = name;
        this.type = type;
        this.maxSeats = maxSeats;
        peopleInBar = new ArrayList<>();
    }

    public int getNumberOfPeopleInBar(){
        return this.peopleInBar.size();
    }

    public int getMaxSeats(){
        return this.maxSeats;
    }

    public void addPeopleInBar(People people){
        this.peopleInBar.add(people);
        people.setInBar(true);
    }

    public void makePeoplesEnterTheBar(ArrayList<People> peoples) {
        if (peoples.size() + this.getNumberOfPeopleInBar() <= getMaxSeats()) {
            for (People people : peoples) {
                this.addPeopleInBar(people);
            }
        } else {
            System.out.println("Cannot enter because the bar is full");
        }
    }

    public void setMaxSeats(int maxSeats){
        this.maxSeats = maxSeats;
    }

    public void setRandomPeopleInBar(int numberOfPeople) {
        this.peopleInBar = new ArrayList<>();
        for(int i = 0; i < numberOfPeople; i++){
            this.peopleInBar.add(new People(String.valueOf(i), 0, false, true));
        }
    }

    public boolean isPeopleInTheBar(People people){
        for (People p : peopleInBar) {
            if (p.equals(people)) {
                return true;
            }
        }
        return false;
    }
}


package Locations;

import People.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public abstract class Location {
    protected List<Person> peopleHere = new ArrayList<>();

    public abstract List<String> description();
    public abstract String name();
    public abstract List<String> nextMessage();

    public List<Location> getSublocations() {
        return new ArrayList<>();
    }

    public List<Person> getPeopleRecursive() {
        if (getSublocations().isEmpty()) {
            return peopleHere;
        }

        List<Person> people = new ArrayList<>();

        for (Location subLocation : getSublocations()) {
            people.addAll(subLocation.getPeopleRecursive());
        }

        return people;
    }
}

package Locations;

import People.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public abstract class Location {
    protected List<Person> allPeople;
    protected final List<Location> sublocations;

    public abstract List<String> description();
    public abstract String name();
    public abstract List<String> nextMessage();

    public final List<Location> getSublocations() {
        return sublocations;
    }

    public List<Person> getPeopleRecursive() {
        List<Person> peopleHere = new ArrayList<>();

        if (getSublocations().isEmpty()) {
            for (Person person : allPeople) {
                if (person.currentLocation().equals(this)) {
                    peopleHere.add(person);
                }
            }

            return peopleHere;
        }

        for (Location subLocation : getSublocations()) {
            peopleHere.addAll(subLocation.getPeopleRecursive());
        }

        return peopleHere;
    }

    public Location(List<Person> allPeople, List<Location> sublocations) {
        this.allPeople = allPeople;
        this.sublocations = sublocations;
    }
}

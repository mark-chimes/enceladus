package People;

import Locations.Location;

/**
 * Created by Mark Chimes on 2017/11/08.
 */
public abstract class Person {
    private final String name;
    private Location location;

    public Person(String name, Location startingLocation) {

        this.name = name;
        location = startingLocation;
    }

    public String name() {
        return name;
    }

    public Location currentLocation() {
        return location;
    }
}

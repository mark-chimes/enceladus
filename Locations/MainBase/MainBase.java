package Locations.MainBase;

import Locations.Location;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/08.
 */
public class MainBase implements Location {
    @Override
    public List<String> description() {
        return Arrays.asList("The main base.", "Description forthcoming.");
    }
}

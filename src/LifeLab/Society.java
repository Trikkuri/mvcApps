package LifeLab;


import CALab.Grid;

import java.util.HashSet;
import java.util.Set;

public class Society extends Grid {

    // Factory method to create instances of Cell


    public static Set<Integer> rebirth = new HashSet<Integer>();
    public static Set<Integer> death = new HashSet<Integer>();
    public static int percentAlive = 50;

    @Override
    public Agent makeCell(boolean uniform) {
        // You can use the factory method here if needed
        return null;
    }
}

package LifeLab;

import CALab.*;
import java.util.HashSet;
import java.util.Set;

public class Society extends Grid {
    public static Set<Integer> rebirth = new HashSet<>();
    public static Set<Integer> death = new HashSet<>();
    public static int percentAlive = 50;

    static {
        rebirth.add(3); // Conditions for an agent to become alive
        death.add(0);   // Conditions for an agent to die
        death.add(1);
        death.add(4);
        death.add(5);
        death.add(6);
        death.add(7);
        death.add(8);
    }

    public Society(int dim) {
        super(dim);
    }

    @Override
    public Cell makeCell(int row, int col, boolean uniform) {
        boolean isAlive = Math.random() < percentAlive / 100.0;
        return new Agent(row, col, this, isAlive ? 1 : 0);
    }

}

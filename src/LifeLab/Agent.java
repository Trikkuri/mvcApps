package LifeLab;

import CALab.*;
import java.awt.Color;

public class Agent extends Cell {
    private int ambience; // Number of living neighbors

    public Agent(int row, int col, Grid grid, int status) {
        super(row, col, grid);
        this.status = status;
    }

    @Override
    public void observe() {
        ambience = 0;
        for (Cell neighbor : neighbors) {
            if (neighbor.getStatus() == 1) {
                ambience++;
            }
        }
    }

    @Override
    public void interact() {
        // No interaction phase for Life Lab
    }

    @Override
    public void update() {
        if (status == 0 && Society.rebirth.contains(ambience)) {
            status = 1;
        } else if (status == 1 && Society.death.contains(ambience)) {
            status = 0;
        }
    }

    @Override
    public Color getColor() {
        return status == 1 ? Color.GREEN : Color.RED;
    }

    @Override
    public void nextState() {
        status = (status + 1) % 2; // Toggle between alive and dead
    }

    @Override
    public void reset(boolean randomly) {
        if (randomly) {
            status = Math.random() < Society.percentAlive / 100.0 ? 1 : 0;
        } else {
            status = 0;
        }
    }
}

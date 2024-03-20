package CALab;

import java.awt.*;
import java.util.*;
import java.io.*;
import mvc.*;

public abstract class Grid extends Model {
    static private int time = 0;
    protected int dim = 20;
    protected Cell[][] cells;

    public int getDim() { return dim; }
    public int getTime() { return time; }
    public Cell getCell(int row, int col) { return cells[row][col]; }
    public abstract Cell makeCell(boolean uniform);


    public Grid(int dim) {
        this.dim = dim;
        cells = new Cell[dim][dim];
        populate();
    }
    public Grid() { this(20); }

    protected void populate() {
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                cells[row][col] = makeCell(false); // Replace with your actual implementation
                cells[row][col].setNeighbors(getNeighbors(cells[row][col], 1));
            }
        }
    }

    // called when Populate and clear buttons are clicked
    public void repopulate(boolean randomly) {
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                if (randomly) {
                    cells[row][col].reset(true); // Reset cell with random state
                } else {
                    cells[row][col].reset(false); // Reset cell to initial state
                }
            }
        }
        notifySubscribers();
    }


    public Set<Cell> getNeighbors(Cell asker, int radius) {
        Set<Cell> neighbors = new HashSet<>();
        for (int i = asker.getRow() - radius; i <= asker.getRow() + radius; i++) {
            for (int j = asker.getCol() - radius; j <= asker.getCol() + radius; j++) {
                if (i >= 0 && i < dim && j >= 0 && j < dim && (i != asker.getRow() || j != asker.getCol())) {
                    neighbors.add(cells[i][j]);
                }
            }
        }
        return neighbors;
    }


    // cell phases:

    public void observe() {
        // call each cell's observe method and notify subscribers
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.observe();
            }
        }
        notifySubscribers();
    }

    public void interact() {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.interact();
            }
        }
    }

    public void update() {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.update();
            }
        }
    }

    public void updateLoop(int cycles) {
        observe();
        for(int cycle = 0; cycle < cycles; cycle++) {
            interact();
            update();
            observe();
            time++;
            System.out.println("time = " + time);
        }
    }
}


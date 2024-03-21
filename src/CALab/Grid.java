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
    public abstract Cell makeCell(int row, int col, boolean uniform);


    public Grid(int dim) {
        this.dim = dim;
        cells = new Cell[dim][dim];
        populate();
    }
    public Grid() { this(20); }

    protected void populate() {
        boolean uniform = false; // Or some logic to decide this
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                Cell cell = makeCell(row, col, uniform);
                if (cell == null) {
                    throw new IllegalStateException("Cell creation failed at position: " + row + ", " + col);
                }
                cells[row][col] = cell;
            }
        }

        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                Cell cell = cells[row][col];
                if (cell == null) {
                    System.out.println("Cell is null at position: " + row + ", " + col);
                    continue;
                }
                Set<Cell> neighborCells = getNeighbors(cell, 1); // Example radius
                cell.setNeighbors(neighborCells);
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
        int askerRow = asker.getRow();
        int askerCol = asker.getCol();

        for (int i = Math.max(askerRow - radius, 0); i <= Math.min(askerRow + radius, dim - 1); i++) {
            for (int j = Math.max(askerCol - radius, 0); j <= Math.min(askerCol + radius, dim - 1); j++) {
                if (i == askerRow && j == askerCol) continue; // Skip the asker cell itself
                neighbors.add(cells[i][j]);
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



package CALab;

import java.util.*;
import java.io.*;
import java.awt.Color;
import mvc.*;

abstract class Cell extends Publisher implements Serializable {

    protected int row = 0, col = 0;
    protected Set<Cell> neighbors = new HashSet<Cell>();
    protected Grid myGrid = null;
    protected Cell partner = null;
    protected int status;

    public Cell(int row, int col, Grid grid) {
        this.row = row;
        this.col = col;
        this.myGrid = grid;
        this.status = 0;
    }

    public Color getColor() {
        switch (status) {
            case 1:
                return Color.GREEN; // Example: living cell
            case 0:
            default:
                return Color.RED; // Example: dead cell
        }
    }

    // Method to set neighbors
    public void setNeighbors(Set<Cell> neighbors) {
        this.neighbors = neighbors;
    }

    // Getters for row and column
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // choose a random neighbor as a partner
    public void choosePartner() {
        if (partner == null && neighbors != null) {
			/*
			Set partner to null
			Convert neighbors set to a local array
			Starting at a random position in the array search for a neighbor without a partner
			Make the first such neighbor (if any) the partner and set its partner field to this
			*/
            partner = null; // Ensure the partner is null to start

            // Convert the neighbors Set to an array for random access
            Cell[] neighborsArray = neighbors.toArray(new Cell[0]);

            // Start at a random position in the array and search for a neighbor without a partner
            int startIndex = new Random().nextInt(neighborsArray.length);
            for (int i = 0; i < neighborsArray.length; i++) {
                int index = (startIndex + i) % neighborsArray.length; // Wrap around the array
                Cell neighbor = neighborsArray[index];

                // Check if this neighbor has no partner
                if (neighbor.partner == null) {
                    // Make this neighbor the partner and set its partner field to this cell
                    partner = neighbor;
                    neighbor.partner = this;
                    break;
                }
            }
        }

    }

    public void unpartner() {
        if (partner != null) {
            if (partner.partner != null) {
                partner.partner = null;
            }
            partner = null;
        }
    }

    // observer neighbors' states
    public abstract void observe();
    // interact with a random neighbor
    public abstract void interact();
    // update my state
    public abstract void update();
    // set status to status + 1 mod whatever
    public abstract void nextState();
    // set status to a random or initial value
    public abstract void reset(boolean randomly);

}


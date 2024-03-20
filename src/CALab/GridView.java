package CALab;

import javax.swing.*;

import mvc.*;
import java.awt.*;

public class GridView  extends View {

    private CellView cellViews[][];

    public GridView(Model model) {
        /*
        Cell cell = new CellView(((Grid)model).getCell(row, col)
        cellViews[row][col] = cell
        set cell.row and cell.col here
        */
        super(model);
        this.setLayout(new GridLayout(((Grid) model).getDim(), ((Grid) model).getDim()));
        cellViews = new CellView[((Grid) model).getDim()][((Grid) model).getDim()];
        for (int row = 0; row < ((Grid) model).getDim(); row++) {
            for (int col = 0; col < ((Grid) model).getDim(); col++) {
                Cell cell = ((Grid) model).getCell(row, col);
                CellView cellView = new CellView(cell);
                this.add(cellView);
                cellViews[row][col] = cellView;
            }
        }
    }

    public void update() {
        // call update method of each CellView
        for (CellView[] row : cellViews) {
            for (CellView cellView : row) {
                cellView.update();
            }
        }
    }

}

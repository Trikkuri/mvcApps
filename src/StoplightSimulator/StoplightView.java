package StoplightSimulator;

import java.awt.*;

public class StoplightView {

    public StoplightView(Stoplight light) {
        super(light);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Color oldColor = gc.getColor();
        Stoplight light = (Stoplight)model;
        StoplightShape shape = new StoplightShape(light);
        shape.draw((Graphics2D) gc);
        gc.setColor(oldColor);
    }
}

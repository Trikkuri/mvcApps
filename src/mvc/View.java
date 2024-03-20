package mvc;

import javax.swing.*;
import java.awt.*;

public abstract class View extends JPanel implements Subscriber {
    protected Model model;

    public View(Model model) {
        this.model = model;
        this.model.subscribe(this);
    }

    public void setModel(Model model) {
        this.model = model;
        update();
    }

    @Override
    public void update() {
        repaint();
    }
}

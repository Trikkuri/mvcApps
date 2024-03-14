package mvc;

import java.awt.Graphics;

public interface View extends Subscriber {
    void paintComponent(Graphics gc);
}

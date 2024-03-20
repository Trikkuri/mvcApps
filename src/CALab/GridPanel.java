package CALab;

import mvc.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GridPanel extends AppPanel {
    public GridPanel(AppFactory factory) {
        super(factory);
        JButton populateButton = new JButton("POPULATE");
        JButton clearButton = new JButton("CLEAR");
        JButton run1Button = new JButton("RUN1");
        JButton run50Button = new JButton("RUN50");

        populateButton.addActionListener(this);
        clearButton.addActionListener(this);
        run1Button.addActionListener(this);
        run50Button.addActionListener(this);

        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(run1Button);
        controlPanel.add(run50Button);
        controlPanel.add(populateButton);
        controlPanel.add(clearButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e); // Call superclass method to handle common actions
        Command command = null;
        if ("POPULATE".equals(e.getActionCommand())) {
            command = new PopulateCommand(model);
        } else if ("CLEAR".equals(e.getActionCommand())) {
            command = new ClearCommand(model);
        } else if ("RUN1".equals(e.getActionCommand())) {
            command = new RunCommand(model, 1);
        } else if ("RUN50".equals(e.getActionCommand())) {
            command = new RunCommand(model, 50);
        }
        if (command != null) {
            command.execute();
        }
    }
}

package mvc;

import StoplightSimulator.StoplightFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppPanel extends JPanel implements Subscriber {
    protected AppFactory factory;
    private Publisher publisher;
    private View view;

    // Nested ControlPanel class
    public class ControlPanel extends JPanel implements ActionListener {
        private JButton changeButton;

        public ControlPanel() {
            changeButton = new JButton("Change");
            changeButton.addActionListener(this);
            add(changeButton);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == changeButton) {
                Command command = factory.makeEditCommand(null, "Change", this);
                if (command != null) {
                    command.execute();
                }
            }
        }
    }

    public AppPanel(AppFactory factory) {
        this.factory = factory;
        this.publisher = new Publisher();
        initComponents();
    }

    private void initComponents() {
        ControlPanel controlPanel = new ControlPanel();
        add(controlPanel);

        // Create and add the view (stoplight) to the panel
        Model model = factory.makeModel();
        view = factory.makeView(model);
        publisher.addSubscriber(view);
        add((Component) view);
    }

    public Publisher getPublisher() {
        return publisher;
    }

    @Override
    public void update() {
        // Implement update logic as needed
    }

    public static void main(String[] args) {
        // For testing purpose
        JFrame frame = new JFrame("MVC App");
        AppFactory factory = new StoplightFactory();
        AppPanel panel = new AppPanel(factory);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}

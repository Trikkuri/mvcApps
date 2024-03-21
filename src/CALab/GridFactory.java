package CALab;

import mvc.*;
import LifeLab.*;

public class GridFactory implements AppFactory {

    public Model makeModel() {
        // Ensure you have a 'dim' value or use a default value
        int dim = 20; // or whatever default dimension you want
        return new Society(dim);
    }

    public View makeView(Model m) { return new GridView(m); }

    public String[] getEditCommands() {
        return new String[] {"Clear", "Populate", "Run1", "Run50"};
    }

    public Command makeEditCommand(Model model, String type, Object source) {
        switch (type) {
            case "Clear":
                return new ClearCommand(model);
            case "Populate":
                return new PopulateCommand(model);
            case "Run1":
                return new RunCommand(model, 1);
            case "Run50":
                return new RunCommand(model, 50);
            default:
                return null;
        }
    }

    public String getTitle() { return "Cellular Automaton Lab"; }

    public String[] getHelp() {
        return new String[] {"Help message..."}; // Provide relevant help text
    }

    public String about() {
        return "About Cellular Automaton Lab..."; // Provide about text
    }
}


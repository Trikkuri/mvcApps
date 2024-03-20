package CALab;

import mvc.*;

public class ClearCommand extends Command {

    public ClearCommand(Model model) {
        super(model);
    }

    public void execute() {
        ((Grid)model).repopulate(false); // Clear the grid
    }
}

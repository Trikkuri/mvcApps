package CALab;

import mvc.*;

public class RunCommand extends Command {
    private final int cycles;

    public RunCommand(Model model, int cycles) {
        super(model);
        this.cycles = cycles;
    }

    public void execute() {
        ((Grid)model).updateLoop(cycles);
    }
}

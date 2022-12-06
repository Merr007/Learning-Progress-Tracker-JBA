package tracker;

public class ExitCommand implements Command{

    @Override
    public void execute() {
        CommandHandler.exit();
    }
}

package tracker;

public class ListCommand implements Command {
    @Override
    public void execute() {
        CommandHandler.list();
    }
}

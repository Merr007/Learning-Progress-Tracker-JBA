package tracker;

public class NotifyCommand implements Command{
    @Override
    public void execute() {
        CommandHandler.notifyCompleted();
    }
}

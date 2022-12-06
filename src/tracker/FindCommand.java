package tracker;

public class FindCommand implements Command {

    private Integer id;

    FindCommand(Integer id) {
        this.id = id;
    }
    @Override
    public void execute() {
        CommandHandler.find(id);
    }
}

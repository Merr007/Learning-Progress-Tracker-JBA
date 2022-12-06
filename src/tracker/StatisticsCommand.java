package tracker;

public class StatisticsCommand implements Command {

    private String courseName;


    StatisticsCommand(String courseName) {
        this.courseName = courseName;
    }
    @Override
    public void execute() {
        CommandHandler.showStatistics(courseName);
    }
}

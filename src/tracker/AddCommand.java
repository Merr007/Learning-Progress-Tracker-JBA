package tracker;

public class AddCommand implements Command{
    private String firstName;
    private String lastName;
    private String email;
    private static int id = 10000;

    public AddCommand(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }
    @Override
    public void execute() {
        CommandHandler.add(id, firstName, lastName, email);
        ++id;
    }
}

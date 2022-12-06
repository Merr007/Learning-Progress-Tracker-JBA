package tracker;

public class StudentInfoParser {

    private boolean parseFirstName(String firstName) {
        String firstRegex = "[A-Za-z].*[A-Za-z]";
        String secondRegex = "(?!.*[-'][-'])[A-Za-z-']+";
        if (firstName.matches(firstRegex) && firstName.matches(secondRegex)) {
            return true;
        }
        System.out.println("Incorrect first name");
        return false;
    }

    private boolean parseLastName(String lastName) {
        String firstRegex = "[A-Za-z].*[A-Za-z]";
        String secondRegex = "(?!.*[-'][-'])[A-Za-z-']+";
        if (lastName.matches(firstRegex) && lastName.matches(secondRegex)) {
            return true;
        }
        System.out.println("Incorrect last name.");
        return false;
    }

    private boolean parseEmail(String email) {
        String regex = "[\\w-\\.]+@\\w+\\.\\w+";
        if (email.matches(regex)) {
            if (UserDatabase.isTaken(email)) {
                System.out.println("This email is already taken.");
                return false;
            }
            return true;
        }

        System.out.println("Incorrect email.");
        return false;

    }

    public boolean parse(String studentInfo) {
        String[] info = studentInfo.split("\\s+");
        String firstName = info[0];
        String email = info[info.length - 1];
        StringBuilder lastName = new StringBuilder();
        for (int i = 1; i < info.length - 1; i++) {
            lastName.append(info[i]);
        }
        if (!parseFirstName(firstName) || !parseLastName(lastName.toString()) || !parseEmail(email)) {
            return false;
        }
        CommandExecutor.executeCommand(new AddCommand(firstName, lastName.toString(), email));
        return true;
    }
}

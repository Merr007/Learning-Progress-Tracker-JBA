package tracker;

public class CommandHandler {
    public static void exit() {
        System.out.println("Bye!");
    }

    public static void add(Integer id, String firstName, String lastName, String email) {
        UserDatabase.addUser(id, new User(firstName, lastName, email, id));
        System.out.println("The student has been added.");
    }

    public static void addPoints(Integer id, String javaPoints, String dsaPoints, String databasesPoints, String springPoints) {
        User user = UserDatabase.getUser(id);
        user.setJavaPoints(Integer.parseInt(javaPoints));
        user.setDsaPoints(Integer.parseInt(dsaPoints));
        user.setDatabasesPoints(Integer.parseInt(databasesPoints));
        user.setSpringPoints(Integer.parseInt(springPoints));
        System.out.println("Points updated.");
    }

    public static void find(Integer id) {
        User user = UserDatabase.getUser(id);
        System.out.printf("%s points: Java=%d; DSA=%d; Databases=%d; Spring=%d%n", id, user.getJavaPoints(), user.getDsaPoints(), user.getDatabasesPoints(), user.getSpringPoints());
    }

    public static void list() {
        if (UserDatabase.isEmpty()) {
            System.out.println("No students found.");
        } else {
            UserDatabase.printStudents();
        }
    }

    public static void showStatistics(String courseName) {
        UserDatabase.printCourseInfo(courseName);
    }

    public static void notifyCompleted() {
        UserDatabase.notifyCompleted();
    }
}

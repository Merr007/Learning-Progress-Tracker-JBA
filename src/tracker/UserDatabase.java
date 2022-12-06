package tracker;

import java.util.*;

public class UserDatabase {
    private static final Map<Integer, User> database = new LinkedHashMap<>();
    private static final Set<String> emails = new HashSet<>();

    private static final List<String> usedCourses = new ArrayList<>();

    private static final List<Course> coursesList = new ArrayList<>();

    static {
        coursesList.add(new Course("Java", 600));
        coursesList.add(new Course("DSA", 400));
        coursesList.add(new Course("Databases", 480));
        coursesList.add(new Course("Spring", 550));
    }

    public static void notifyCompleted() {
        Set<Integer> studentsNotified = new HashSet<>();
        for (var c : coursesList) {
            for (int id : c.checkCompleted()) {
                System.out.printf("To: %s%n", database.get(id).getEmail());
                System.out.println("Re: Your Learning Progress");
                System.out.printf("Hello, %s! You have accomplished our %s course!%n", database.get(id).getUserName(), c.getName());
                studentsNotified.add(id);
            }
        }
        System.out.printf("Total %d students have been notified.%n", studentsNotified.size());
    }



    public static void addUser(int id, User user) {
        database.put(id, user);
        emails.add(user.getEmail());
    }

    public static boolean isTaken(String email) {
        return emails.contains(email);
    }

    public static boolean containsId(int id) {
        return database.containsKey(id);
    }

    public static User getUser(int id) {
        return database.get(id);
    }

    public static boolean isEmpty() {
        return database.isEmpty();
    }

    public static void printStudents() {
        System.out.println("Students:");
        for (var map : database.entrySet()) {
            System.out.println(map.getKey());
        }
    }

    public static boolean isCourseExist(String courseName) {
        return coursesList.stream().anyMatch(c -> c.getName().equalsIgnoreCase(courseName));
    }

    public static Course getCourse(String nameOfCourse) {
        Course currentCourse = null;
        for (var course : coursesList) {
            if (course.getName().equalsIgnoreCase(nameOfCourse)) {
                currentCourse = course;
            }
        }
        return currentCourse;
    }

    public static void printCourseInfo(String courseName) {
        Course course = getCourse(courseName);
        course.printCourseInfo();
    }

    public static void printStatistics() {
        if (!isAnyEnrolled()) {
            System.out.println("Most popular: n/a");
            System.out.println("Least popular: n/a");
            System.out.println("Highest activity: n/a");
            System.out.println("Lowest activity: n/a");
            System.out.println("Easiest course: n/a");
            System.out.println("Hardest course: n/a");
            return;
        }
        Comparator<Course> popularity = (o1, o2) -> o2.getNumberOfStudents() - o1.getNumberOfStudents();
        Comparator<Course> activity = (o1, o2) -> o2.getSubmissions() - o1.getSubmissions();
        Comparator<Course> simplicity = Comparator.comparingDouble(Course::getAverageScore);
        coursesList.sort(popularity);
        System.out.printf("Most popular: %s%n", getStatistics("popularity", "max"));
        System.out.printf("Least popular: %s%n", getStatistics("popularity","min"));
        coursesList.sort(activity);
        System.out.printf("Highest activity: %s%n", getStatistics("activity", "max"));
        System.out.printf("Lowest activity: %s%n", getStatistics("activity", "min"));
        coursesList.sort(simplicity);
        System.out.printf("Easiest course: %s%n", getStatistics("simplicity", "min"));
        System.out.printf("Hardest course: %s%n", getStatistics("simplicity", "max"));

    }
    private static boolean isAnyEnrolled() {
        int counter = 0;
        for (var c : coursesList) {
            if (c.getNumberOfStudents() != 0) {
                counter++;
            }
        }

        return counter != 0;
    }


    private static String getStatistics(String type, String maxOrMin) {
        StringBuilder builder = new StringBuilder();
        if (type.equalsIgnoreCase("popularity")) {
            if (maxOrMin.equalsIgnoreCase("max")) {
                int max = coursesList.get(0).getNumberOfStudents();
                for (var c : coursesList) {
                    if (c.getNumberOfStudents() == max) {
                        builder.append(c.getName()).append(" ");
                        usedCourses.add(c.getName());
                    }
                }
            } else {
                int min = coursesList.get(3).getNumberOfStudents();
                for (var c : coursesList) {
                    if (!usedCourses.contains(c.getName()) && c.getNumberOfStudents() == min) {
                        builder.append(c.getName()).append(" ");
                    }
                }
                usedCourses.clear();
            }
        } else if (type.equalsIgnoreCase("activity")) {
            if (maxOrMin.equalsIgnoreCase("max")) {
                int max = coursesList.get(0).getSubmissions();
                for (var c : coursesList) {
                    if (c.getSubmissions() == max) {
                        builder.append(c.getName()).append(" ");
                        usedCourses.add(c.getName());
                    }
                }
            } else {
                int min = coursesList.get(3).getSubmissions();
                for (var c : coursesList) {
                    if (!usedCourses.contains(c.getName()) && c.getSubmissions() == min) {
                        builder.append(c.getName()).append(" ");
                    }
                }
                usedCourses.clear();
            }
        } else if (type.equalsIgnoreCase("simplicity")){
            if (maxOrMin.equalsIgnoreCase("max")) {
                double max = coursesList.get(0).getAverageScore();
                for (var c : coursesList) {
                    if (c.getAverageScore() == max) {
                        builder.append(c.getName()).append(" ");
                        usedCourses.add(c.getName());
                    }
                }
            } else {
                double min = coursesList.get(3).getAverageScore();
                for (var c : coursesList) {
                    if (!usedCourses.contains(c.getName()) && c.getAverageScore() == min) {
                        builder.append(c.getName()).append(" ");
                    }
                }
                usedCourses.clear();
            }
        }


        return builder.isEmpty() ? "n/a" : builder.toString().trim().replaceAll(" ", ", ");
    }


}

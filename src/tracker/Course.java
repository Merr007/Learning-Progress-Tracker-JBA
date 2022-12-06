package tracker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Course {
    private final String name;
    private int numberOfStudents;
    private int submissions;
    private int points;
    private double averageScore;
    private final int pointsToComplete;

    private Map<Integer, Integer> enrolledStudents = new TreeMap<>();
    private List<Integer> sortedIds = new ArrayList<>();

    private Set<Integer> notifiedStudents = new HashSet<>();

    Course(String name, int pointsToComplete) {
        this.name = name;
        this.pointsToComplete = pointsToComplete;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public int getSubmissions() {
        return submissions;
    }

    public double getAverageScore() {
        return averageScore;
    }

    private void addStudent() {
        numberOfStudents++;
    }

    private void addSubmission() {
        submissions++;
    }

    public List<Integer> checkCompleted() {
        List<Integer> completedIds = new ArrayList<>();
        for (var entry : enrolledStudents.entrySet()) {
            if (entry.getValue() >= pointsToComplete && !notifiedStudents.contains(entry.getKey())) {
                completedIds.add(entry.getKey());
                notifiedStudents.add(entry.getKey());
            }
        }
        return completedIds;
    }

    public void printCourseInfo() {
        Comparator<Integer> comparator = (o1, o2) -> enrolledStudents.get(o2) - enrolledStudents.get(o1);
        sortedIds.sort(comparator.thenComparingInt(o -> o));
        System.out.println(name);
        System.out.println("id    points    completed");
        RoundingMode rm = RoundingMode.HALF_UP;
        for (var s : sortedIds) {
            System.out.printf(Locale.US, "%d %-10d%.1f%%%n",
                    s, enrolledStudents.get(s), BigDecimal.valueOf((double) enrolledStudents.get(s) / (double) pointsToComplete * 100).setScale(1, rm));
        }
    }
    public void setPoints(int id, int points) {
        this.points = this.points + points;
        addSubmission();
        if (!enrolledStudents.containsKey(id)) {
            addStudent();
            enrolledStudents.put(id, points);
            sortedIds.add(id);
        } else {
            enrolledStudents.compute(id, (k, v) -> v == null ? 0 : v + points);
        }
        updateAverageScore();
    }

    private void updateAverageScore() {
        this.averageScore = (double) points / (double) submissions;
    }
}

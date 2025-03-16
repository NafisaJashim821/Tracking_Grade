import java.util.ArrayList;
import java.util.List;

public class GradeTracker {
    private List<Course> courses;

    public GradeTracker() {
        courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public double calculateGPA() {
        double totalGradePoints = 0.0;
        int totalCredits = 0;

        for (Course course : courses) {
            totalGradePoints += course.getGrade() * course.getCredits();
            totalCredits += course.getCredits();
        }

        return (totalCredits == 0) ? 0.0 : totalGradePoints / totalCredits;
    }
}
/**
 * Represents a course with a code, name, grade, and credits.
 */
public class Course {
    private final String courseCode;
    private final String courseName;
    private final double grade;
    private final int credits;

    public Course(String courseCode, String courseName, double grade, int credits) {
        if (grade < 2.50 || grade > 4.00) {
            throw new IllegalArgumentException("Grade must be between 2.50 and 4.00");
        }
        if (credits <= 0) {
            throw new IllegalArgumentException("Credits must be positive");
        }
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.grade = grade;
        this.credits = credits;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getGrade() {
        return grade;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return String.format("%s - %s: %.2f (%d credits)", courseCode, courseName, grade, credits);
    }
}

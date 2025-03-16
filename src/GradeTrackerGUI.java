import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GradeTrackerGUI extends JFrame {
    private JTextField studentNameField, studentIdField, numCoursesField;
    private JTextArea displayArea;
    private List<Course> courses;
    private int numberOfCourses;
    private JButton setCoursesButton, addCourseButton, calculateGPAButton, resetButton;
    private JPanel coursePanel;
    private JTextField courseCodeField, courseNameField, gradeField, creditsField;
    private FileHandler fileHandler;  // Add this line to declare the FileHandler instance

    public GradeTrackerGUI() {
        setTitle("Grade Tracker System");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Initialize the FileHandler with the file path
        fileHandler = new FileHandler("D:\\Course Grading\\gpa_results.txt");

        // Create components
        JLabel titleLabel = new JLabel("Grade Tracker", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel);

        // Student Name
        add(new JLabel("Student Name:"));
        studentNameField = new JTextField(15);
        add(studentNameField);

        // Student ID
        add(new JLabel("Student ID:"));
        studentIdField = new JTextField(15);
        add(studentIdField);

        // Number of Courses
        add(new JLabel("Number of Courses:"));
        numCoursesField = new JTextField(5);
        add(numCoursesField);
        setCoursesButton = new JButton("Set Courses");
        add(setCoursesButton);

        // Display Area
        displayArea = new JTextArea(15, 50);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane);

        // Prepare course input panel
        coursePanel = new JPanel(new GridLayout(4, 2));
        coursePanel.setVisible(false);
        initializeCourseFields();
        add(coursePanel);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        addCourseButton = new JButton("Add Next Course");
        addCourseButton.setEnabled(false); // Initially disabled
        buttonPanel.add(addCourseButton);
        calculateGPAButton = new JButton("Calculate GPA");
        buttonPanel.add(calculateGPAButton);
        resetButton = new JButton("Reset");
        buttonPanel.add(resetButton);
        add(buttonPanel);

        // Initialize list
        courses = new ArrayList<>();

        // Button Actions
        setCoursesButton.addActionListener(e -> setNumberOfCourses());
        addCourseButton.addActionListener(e -> addCourse());
        calculateGPAButton.addActionListener(e -> calculateGPA());
        resetButton.addActionListener(e -> resetFields());

        setVisible(true);
    }

    private void initializeCourseFields() {
        coursePanel.add(new JLabel("Course Code:"));
        courseCodeField = new JTextField();
        coursePanel.add(courseCodeField);

        coursePanel.add(new JLabel("Course Name:"));
        courseNameField = new JTextField();
        coursePanel.add(courseNameField);

        coursePanel.add(new JLabel("Grade (2.50 - 4.00):"));
        gradeField = new JTextField();
        coursePanel.add(gradeField);

        coursePanel.add(new JLabel("Credits:"));
        creditsField = new JTextField();
        coursePanel.add(creditsField);
    }

    private void setNumberOfCourses() {
        try {
            numberOfCourses = Integer.parseInt(numCoursesField.getText());
            if (numberOfCourses <= 0) {
                throw new NumberFormatException();
            }
            displayArea.setText("Please enter details for " + numberOfCourses + " course(s):\n");
            coursePanel.setVisible(true);
            numCoursesField.setEditable(false);
            setCoursesButton.setEnabled(false);
            addCourseButton.setEnabled(true);

            // Clear previous input
            clearCourseFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid positive number for courses.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addCourse() {
        if (courses.size() < numberOfCourses) {
            try {
                String courseCode = courseCodeField.getText();
                String courseName = courseNameField.getText();
                double grade = Double.parseDouble(gradeField.getText());
                int credits = Integer.parseInt(creditsField.getText());

                if (grade < 2.50 || grade > 4.00) {
                    JOptionPane.showMessageDialog(this, "Grade must be between 2.50 and 4.00", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Course newCourse = new Course(courseCode, courseName, grade, credits);
                courses.add(newCourse);
                displayArea.append("Course Added: " + courseName + " [" + courseCode + "]\n");

                // Clear the fields for next input
                clearCourseFields();

                if (courses.size() == numberOfCourses) {
                    addCourseButton.setEnabled(false);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers for grade and credits.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "All courses have been added.", "Info", JOptionPane.INFORMATION_MESSAGE);
            addCourseButton.setEnabled(false);
        }
    }

    private void clearCourseFields() {
        courseCodeField.setText("");
        courseNameField.setText("");
        gradeField.setText("");
        creditsField.setText("");
    }

    private void calculateGPA() {
        if (courses.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No courses entered.", "GPA Calculation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double totalPoints = 0.0;
        int totalCredits = 0;

        for (Course course : courses) {
            totalPoints += course.getGrade() * course.getCredits();
            totalCredits += course.getCredits();
        }

        double gpa = totalPoints / totalCredits;

        // Display the result
        JOptionPane.showMessageDialog(this, "Your GPA is: " + String.format("%.2f", gpa), "GPA Result", JOptionPane.INFORMATION_MESSAGE);

        // Save the GPA to the file
        fileHandler.saveGPAToFile(gpa, studentNameField.getText(), studentIdField.getText());
    }

    private void resetFields() {
        studentNameField.setText("");
        studentIdField.setText("");
        numCoursesField.setText("");
        courses.clear();
        displayArea.setText("");
        coursePanel.setVisible(false);
        numCoursesField.setEditable(true);
        setCoursesButton.setEnabled(true);
        addCourseButton.setEnabled(false);
    }

    public static void main(String[] args) {
        new GradeTrackerGUI();
    }
}

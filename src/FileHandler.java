import java.io.*;

public class FileHandler {
    private String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    public void saveGPAToFile(double gpa, String studentName, String studentId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("Student Name: " + studentName + "\n");
            writer.write("Student ID: " + studentId + "\n");
            writer.write("GPA: " + gpa + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
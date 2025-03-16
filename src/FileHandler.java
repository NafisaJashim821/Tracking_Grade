import java.io.*;

public class FileHandler {
    private String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
        createFileIfNotExists();
    }

    // Ensure the file and directory exist before writing
    private void createFileIfNotExists() {
        // Get the parent directory of the file path
        File directory = new File(filePath).getParentFile();
        if (!directory.exists()) {
            // Create the directory if it doesn't exist
            boolean created = directory.mkdirs();
            if (!created) {
                System.out.println("Could not create the directory.");
            }
        }

        // Create the file if it doesn't exist
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                boolean created = file.createNewFile();
                if (created) {
                    System.out.println("File created: " + filePath);
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
            }
        }
    }

    public void saveGPAToFile(double gpa, String studentName, String studentId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("Student Name: " + studentName + "\n");
            writer.write("Student ID: " + studentId + "\n");
            writer.write("GPA: " + gpa + "\n\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}

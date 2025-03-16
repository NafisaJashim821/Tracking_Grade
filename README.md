# Grade Tracker System

## Overview
The **Grade Tracker System** is a Java-based application that helps students track their grades, calculate their GPA, and store the results in a text file. It provides a simple graphical user interface (GUI) built with Java Swing, which allows students to input their courses, grades, and credits. After entering the course details, the application calculates the GPA and saves the result into a text file for future reference.

## Features
- **Course Management**: Allows students to add courses, including the course code, name, grade, and credits.
- **GPA Calculation**: Computes the GPA based on the entered courses.
- **Text File Output**: Saves the GPA calculation result in a text file (`gpa_results.txt`) for reference.
- **Reset Functionality**: Clears all fields and resets the state for a new session.

## Prerequisites
Before running the Grade Tracker System, ensure you have the following:

- **Java JDK** (Java Development Kit) installed on your machine.
- **IDE** (like IntelliJ IDEA, Eclipse, or NetBeans) or a simple text editor to compile and run the Java code.

## Installation

1. **Clone the repository** (or download the files):
   - Download or clone the `Grade Tracker System` repository to your local machine.

2. **Ensure Java is installed**:
   - You must have Java installed. If it's not installed, download it from the official [Java website](https://www.oracle.com/java/technologies/javase-downloads.html).
   - You can verify if Java is installed by running the following command in your terminal:
     ```bash
     java -version
     ```

3. **Setting up the File Path**:
   - The program saves the GPA results in a file located at `filep path\gpa_results.txt`.
   - Ensure that this directory exists or modify the `fileHandler` object in the `GradeTrackerGUI` class to point to a valid directory where you want the results to be stored.

4. **Compile and Run**:
   - Open the project in your preferred IDE or compile via the terminal using:
     ```bash
     javac *.java
     java GradeTrackerGUI
     ```

## Usage

1. **Launch the Application**:
   - Run the `GradeTrackerGUI` class to launch the application window.
   - The application will display a simple interface where you can enter student details, course information, and grades.

2. **Entering Student Details**:
   - **Student Name**: Enter the student's name.
   - **Student ID**: Enter the student's unique ID.
   - **Number of Courses**: Enter how many courses the student is tracking.

3. **Entering Course Details**:
   - For each course, you'll be prompted to enter:
     - **Course Code**
     - **Course Name**
     - **Grade** (must be between 2.50 and 4.00)
     - **Credits** (must be a positive integer)

4. **Calculating GPA**:
   - Once all courses are added, click the "Calculate GPA" button.
   - The GPA will be displayed in a dialog box and saved to the `gpa_results.txt` file.

5. **Reset**:
   - Click the "Reset" button to clear all fields and reset the application for a new session.

## Example of the Output File (`gpa_results.txt`):
The GPA results are saved in the following format:


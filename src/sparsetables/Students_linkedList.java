package sparsetables;

import javafx.scene.control.Alert;

public class Students_linkedList {

    // Linked list of Students
    private Student firstStudent;

    // Employee class representing each Student node
    public class Student {

        private final String StudentId; // ID of the Student
        private final String StudentName; // Name of the Student
        private Student nextStudent; // Reference to the next Student in the list
        private Student left_Student; // Reference to the previous Student in the list
        private Cell_data firstdCourseCellData; // Reference to the first cell data containing Course information

        // Constructor to initialize Student
        public Student(String StudentId, String StudentName) {
            this.StudentId = StudentId;
            this.StudentName = StudentName;
        }

        // Getter method for the Student ID
        public String getStudentId() {
            return this.StudentId;
        }

        // Getter method for the first cell data of the Student
        public Cell_data getFirstdCourseCellData() {
            return this.firstdCourseCellData;
        }

        // Setter method for the first cell data of the Student
        public void setFirstdCourseCellData(Cell_data firstdCourseCellData) {
            this.firstdCourseCellData = firstdCourseCellData;
        }

        // Getter method for the Student name
        public String getStudentName() {
            return this.StudentName;
        }

        public Student getNextStudentRight() {
            return nextStudent;
        }

        @Override
        public String toString() {
            return StudentId + " - " + StudentName;
        }

        public Student getLeft_Student() {
            return left_Student;
        }

        public void setLeft_Student(Student left_Student) {
            this.left_Student = left_Student;
        }

        public void setNextStudent(Student nextStudent) {
            this.nextStudent = nextStudent;
        }

    }

    public Student getFirstStudent() {
        return firstStudent;
    }

    public void setFirstStudent(Student firstStudent) {
        this.firstStudent = firstStudent;
    }

    // Method to add a new employee to the list
    public Student addNewStudent(String StudentId, String StudentName, boolean ch) {
        Student newStudent = new Student(StudentId, StudentName);
        if (firstStudent == null) {
            // If the list is empty, set the new Student as the first Student
            firstStudent = newStudent;
            firstStudent.nextStudent = null;
            if (ch) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INFORMATION");
                alert.setHeaderText("Added Successfully");
                alert.showAndWait();
            }
        } else {
            // Traverse the list to find the last Student and link the new Student
            try {
                Student currentStudent = firstStudent;
                while (currentStudent.nextStudent != null || currentStudent.getStudentId().equals(StudentId)) {
                    currentStudent = currentStudent.nextStudent;
                }
                if (!currentStudent.getStudentId().equals(StudentId)) {
                    currentStudent.nextStudent = newStudent;
                    newStudent.left_Student = currentStudent;
                    if (ch) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("INFORMATION");
                        alert.setHeaderText("Added Successfully");
                        alert.showAndWait();
                    }
                }
            } catch (Exception e) {
                if (ch) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFORMATION");
                    alert.setHeaderText("Its already added");
                    alert.showAndWait();
                }
            }

        }
        return newStudent;
    }

}

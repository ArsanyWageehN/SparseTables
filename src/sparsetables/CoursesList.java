package sparsetables;

import javafx.scene.control.Alert;

public class CoursesList {
    // Linked list of Courses

    private Course firstCourse;

    // Course class representing each Course node
    public class Course {

        private String CourseId; // ID of the Course
        private String CourseName; // Name of the Course
        private Course down_Course; // Reference to the next Course in the list
        private Course up_Course; // Reference to the previous Course in the list
        private Cell_data firstStudentCellData; // Reference to the first cell data containing Student information

        // Constructor to initialize Course
        public Course(String CourseId, String CourseName) {
            this.CourseId = CourseId;
            this.CourseName = CourseName;
        }

        // Getter method for the first cell data of the Course
        public Cell_data getFirstStudentCellData() {
            return this.firstStudentCellData;
        }

        // Setter method for the first cell data of the Course
        public void setFirstStudentCellData(Cell_data newCellData) {
            this.firstStudentCellData = newCellData;
        }

        // Getter method for the Course ID
        public String getCourseId() {
            return this.CourseId;
        }

        // Getter method for the Course name
        public String getCourseName() {
            return this.CourseName;
        }

        public Course getNextCoursedown() {
            return down_Course;
        }

        @Override
        public String toString() {
            return CourseName + " - " + CourseId;
        }

        public void setDown_Course(Course down_Course) {
            this.down_Course = down_Course;
        }

        public void setUp_Course(Course up_Course) {
            this.up_Course = up_Course;
        }

        public Course getDown_Course() {
            return down_Course;
        }

        public Course getUp_Course() {
            return up_Course;
        }

    }

    public void setFirstCourse(Course course) {
        this.firstCourse = course;
    }

    public Course getFirstCourse() {
        return firstCourse;
    }

    // Method to add a new Course to the list
    public Course addNewCourse(String CourseId, String CourseName, boolean ch) {
        Course newCourse = new Course(CourseId, CourseName);
        if (firstCourse == null) {
            // If the list is empty, set the new Course as the first Course
            firstCourse = newCourse;
            firstCourse.down_Course = null;
            if (ch) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INFORMATION");
                alert.setHeaderText("Added Successfully");
                alert.showAndWait();
            }
        } else {
            // Traverse the list to find the last Course and link the new Course
            Course currentCourse = firstCourse;
            try {
                while (currentCourse.down_Course != null || currentCourse.getCourseId().equals(CourseId)) {
                    currentCourse = currentCourse.down_Course;
                }
                if (!currentCourse.getCourseId().equals(CourseId)) {
                    currentCourse.down_Course = newCourse;
                    newCourse.up_Course = currentCourse;

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
        return newCourse;
    }
}

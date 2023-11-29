package sparsetables;

import GUI_CLASSES.FXMLTableStdController2;
import GUI_CLASSES.FXMLTablecrsController2;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import sparsetables.CoursesList.Course;
import sparsetables.Students_linkedList.Student;

public class SparseTables extends Application {

    public static Students_linkedList studentList = new Students_linkedList();
    public static CoursesList coursesList = new CoursesList();
    // Creating an instance of SparseTable
    public static SparseTables sparseTable = new SparseTables();

    @Override
    public void start(Stage primaryStage) {

        FXMLLoader fxmlLoader2 = new FXMLLoader(SparseTables.class.getResource("/sparsetablesGUI_FXML/MainFX.fxml"));
        Scene scene2 = null;
        try {
            scene2 = new Scene(fxmlLoader2.load());
        } catch (IOException ex) {
            Logger.getLogger(SparseTables.class.getName()).log(Level.SEVERE, null, ex);
        }
        primaryStage.setTitle("Alexandria University");
        primaryStage.setScene(scene2);
        primaryStage.show();

        primaryStage.show();

        primaryStage.setOnCloseRequest((event) -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Are you sure you want to close this app?");
            alert.setContentText("Close App?");
            alert.setHeaderText("");
            if (alert.showAndWait().get() == ButtonType.OK) {
                System.exit(0);
            }
            event.consume();
        });

        add_C("Linear Algebra", "02-24-00101", false);
        add_C("Calculus", "02-24-00102", false);
        add_C("Introduction to Computer System", "02-24-00103", false);
        add_C("Introduction to Data Sciences", "02-24-00104", false);
        add_C("Programming I", "02-24-00105", false);
        add_C("Critical Thinking", "0200000XX", false);
        add_C("Probability and Statistics I", "02-24-00106", false);
        add_C("Discrete Structures", "02-24-00107", false);
        add_C("Data Structures and Algorithms", "02-24-00108", false);
        add_C("Introduction to Artificial Intelligence", "02-24-00109", false);

        add_S("Arsany Wageeh", "22011985", false);
        add_S("Ahmed Ibrahim", "22011500", false);
        add_S("Mohamed Ramadan", "22010980", false);
        add_S("Mina Magdy", "22011561", false);
        add_S("Ali Hagag", "22011511", false);

    }

    public static void add_C(String CourseName, String courseID, boolean ch) {
        coursesList.addNewCourse(courseID, CourseName, ch);
    }

    public static void add_S(String StudentName, String StudentID, boolean ch) {
        studentList.addNewStudent(StudentID, StudentName, ch);
    }

    // Method to add an Student to a Course
    public void addStudentToCourse(Student student, Course course) {

        // Creating a new cell data with the extracted IDs
        Cell_data newCellData = new Cell_data(student, course);

        // Handling the case where the course's first Student cell data is null
        if (course.getFirstStudentCellData() == null) {
            course.setFirstStudentCellData(newCellData);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION");
            alert.setHeaderText("Registered successfully");
            alert.showAndWait();
        } else {
            // Traverse to the end of the course's cell data and link the new cell data
            Cell_data current = course.getFirstStudentCellData();

            while (current.getNextCellRight() != null && !current.getMy_student().getStudentId().equals(newCellData.getMy_student().getStudentId())) {
                current = current.getNextCellRight();
            }
            if (!current.getMy_student().getStudentId().equals(newCellData.getMy_student().getStudentId())) {
                current.setNextCellRight(newCellData);
                newCellData.setNextCellleft(current);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INFORMATION");
                alert.setHeaderText("Registered successfully");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INFORMATION");
                alert.setHeaderText("it's already registered");
                alert.showAndWait();
            }
        }

        // Handling the case where the Student's first course cell data is null
        if (student.getFirstdCourseCellData() == null) {
            student.setFirstdCourseCellData(newCellData);

        } else {
            // Traverse to the end of the Student's cell data and link the new cell data
            Cell_data current = student.getFirstdCourseCellData();
            while (current.getNextCellDown() != null && !current.getMy_course().getCourseId().equals(newCellData.getMy_course().getCourseId())) {
                current = current.getNextCellDown();
            }
            if (!current.getMy_course().getCourseId().equals(newCellData.getMy_course().getCourseId())) {
                current.setNextCellDown(newCellData);
                newCellData.setNextCellup(current);
            }
        }
    }

    // Method to display the Courses associated with an Student
    public void displaystudentCourses(Student student) {
        // Checking if the student has any associated Courses
        if (student.getFirstdCourseCellData() == null) {
            System.out.println("No Courses for student " + student.getStudentId());
        } else {
            // Traverse the student's cell data and print associated Course IDs
            Cell_data curCellData = student.getFirstdCourseCellData();
            System.out.println(student.getStudentName() + " is associated with the following Courses:");
            while (curCellData != null) {
                System.out.println(curCellData.getMy_course().getCourseId());
                FXMLTableStdController2.data_controller_data2.add(curCellData.getMy_course());
                curCellData = curCellData.getNextCellDown();
            }
        }
    }

    // Method to display the Students in a Course
    public void displayCourseStudents(Course Course) {
        // Checking if the Course has any associated Students
        if (Course.getFirstStudentCellData() == null) {
            System.out.println("No Students in Course " + Course.getCourseId());
        } else {
            // Traverse the Course's cell data and print associated Student IDs
            Cell_data curCellData = Course.getFirstStudentCellData();
            System.out.println("Students in Course " + Course.getCourseId() + ":");
            while (curCellData != null) {
                String StudentId = curCellData.getMy_student().getStudentId();
                FXMLTablecrsController2.data_controller_data.add(curCellData.getMy_student());
                curCellData = curCellData.getNextCellRight();
            }
        }
    }

    public static void unroll(Student student, Course course) {

        if (course == null || student == null) {
            System.out.println("its is already empty");
        } else {
            Cell_data current = course.getFirstStudentCellData();

            if (current.getMy_student().getStudentId().equals(student.getStudentId())) {
                if (current.getNextCellRight() != null) {
                    current.getNextCellRight().setNextCellleft(null);
                }
                course.setFirstStudentCellData(current.getNextCellRight());

            } else {
                while (current != null && !current.getMy_student().getStudentId().equals(student.getStudentId())) {
                    current = current.getNextCellRight();
                }

                if (current.getNextCellRight() != null) {
                    current.getNextCellRight().setNextCellleft(current.getNextCellleft());
                }

                if (current.getNextCellleft() != null) {
                    current.getNextCellleft().setNextCellRight(current.getNextCellRight());
                }
            }
            current.setNextCellleft(null);
            current.setNextCellRight(null);

            Cell_data current2 = student.getFirstdCourseCellData();

            if (current2.getMy_course().getCourseId().equals(course.getCourseId())) {
                if (current2.getNextCellDown() != null) {
                    current2.getNextCellDown().setNextCellup(null);
                }
                student.setFirstdCourseCellData(current.getNextCellDown());

            } else {
                while (current2 != null && !current2.getMy_course().getCourseId().equals(course.getCourseId())) {
                    current2 = current2.getNextCellDown();
                }

                if (current2.getNextCellDown() != null) {
                    current2.getNextCellDown().setNextCellup(current.getNextCellup());
                }

                if (current2.getNextCellup() != null) {
                    current2.getNextCellup().setNextCellDown(current.getNextCellDown());
                }
            }
            current2.setNextCellDown(null);
            current2.setNextCellup(null);
        }

    }

    public static void deleteStudent(String IDStudent, Students_linkedList listSTD, CoursesList listCR) {

        Course current2 = listCR.getFirstCourse();
        if (current2 != null) {
            Cell_data currentCell2;

            while (current2 != null) {
                currentCell2 = current2.getFirstStudentCellData();
                if (currentCell2 != null && currentCell2.getMy_student().getStudentId().equals(IDStudent)) {
                    current2.setFirstStudentCellData(currentCell2.getNextCellRight());
                }
                current2 = current2.getNextCoursedown();
            }
        }

        Student current = listSTD.getFirstStudent();

        if (current != null) {
            while (current.getNextStudentRight() != null && !current.getStudentId().equals(IDStudent)) {
                current = current.getNextStudentRight();
            }

            if (current.getStudentId().equals(IDStudent)) {

                Cell_data currentCell = current.getFirstdCourseCellData();

                while (currentCell != null) {

                    if (currentCell.getNextCellRight() != null) {
                        currentCell.getNextCellRight().setNextCellleft(currentCell.getNextCellleft());
                    } else {

                        currentCell.setNextCellRight(null);
                    }

                    if (currentCell.getNextCellleft() != null) {
                        currentCell.getNextCellleft().setNextCellRight(currentCell.getNextCellRight());
                    } else {
                        currentCell.setNextCellleft(null);
                    }

                    if (currentCell.getNextCellDown() != null) {
                        currentCell = currentCell.getNextCellDown();
                    } else {
                        while (currentCell.getNextCellup() != null) {
                            currentCell.setNextCellDown(null);
                            if (currentCell.getNextCellDown() != null) {
                                currentCell.getNextCellDown().setNextCellup(null);
                            }
                        }
                        break;
                    }

                    currentCell = currentCell.getNextCellRight();
                }

                if (current.getNextStudentRight() != null) {
                    current.getNextStudentRight().setLeft_Student(current.getLeft_Student());
                }

                if (current.getLeft_Student() != null) {
                    current.getLeft_Student().setNextStudent(current.getNextStudentRight());
                }

                if (listSTD.getFirstStudent().getStudentId().equals(IDStudent)) {
                    listSTD.setFirstStudent(listSTD.getFirstStudent().getNextStudentRight());
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("deleted Successfully");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("This isnt exist");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("No courses here");
            alert.showAndWait();
        }
    }

    public static void deleteCourse(String NameCourse, CoursesList list, Students_linkedList listSTD) {

        Student current2 = listSTD.getFirstStudent();
        if (current2 != null) {
            Cell_data currentCell2;
            while (current2 != null) {
                currentCell2 = current2.getFirstdCourseCellData();
                if (currentCell2 != null && currentCell2.getMy_course().getCourseName().equals(NameCourse)) {
                    current2.setFirstdCourseCellData(currentCell2.getNextCellDown());
                }
                System.out.println(current2.getStudentName() + "");
                current2 = current2.getNextStudentRight();
            }
        }
        Course current = list.getFirstCourse();
        if (current != null) {

            while (current.getNextCoursedown() != null && !current.getCourseName().equals(NameCourse)) {
                current = current.getNextCoursedown();
            }

            if (current.getCourseName().equals(NameCourse)) {

                Cell_data currentCell = current.getFirstStudentCellData();

                while (currentCell != null) {

                    if (currentCell.getNextCellDown() != null) {
                        currentCell.getNextCellDown().setNextCellup(currentCell.getNextCellup());
                    } else {
                        currentCell.setNextCellDown(null);
                    }

                    if (currentCell.getNextCellup() != null) {
                        currentCell.getNextCellup().setNextCellDown(currentCell.getNextCellDown());
                    } else {

                        currentCell.setNextCellup(null);
                    }

                    if (currentCell.getNextCellRight() != null) {
                        currentCell = currentCell.getNextCellRight();
                    } else {
                        while (currentCell.getNextCellleft() != null) {
                            currentCell.setNextCellRight(null);
                            if (currentCell.getNextCellRight() != null) {
                                currentCell.getNextCellRight().setNextCellleft(null);
                            }
                            currentCell = currentCell.getNextCellleft();
                        }
                        break;
                    }
                }

                if (current.getNextCoursedown() != null) {
                    current.getNextCoursedown().setUp_Course(current.getUp_Course());
                }

                if (current.getUp_Course() != null) {
                    current.getUp_Course().setDown_Course(current.getNextCoursedown());
                }

                if (list.getFirstCourse().getCourseName().equals(NameCourse)) {
                    list.setFirstCourse(list.getFirstCourse().getNextCoursedown());
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("deleted Successfully");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("This isnt exist");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("No Courses here");
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {

        launch(args);
    }

}

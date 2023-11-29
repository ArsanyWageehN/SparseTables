package GUI_CLASSES;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sparsetables.SparseTables;
import static sparsetables.SparseTables.studentList;
import sparsetables.Students_linkedList.Student;

public class BasicFXMLController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField CRS_NAME, CODE_CRS, ID_STD, NameSTD, ID_STD_OF_DELETE, ID_CRS_OF_DELETE;
    private TableView<Student> Table_Students;
    private TableColumn<String, Student> col_id, col_name;
    @FXML
    private MenuButton list_std;
    @FXML
    private MenuButton list_crs;

    public void switchToStudent(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sparsetablesGUI_FXML/Add_StudentFX.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Add Student");
        stage.show();
    }

    public void switchToCourse(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sparsetablesGUI_FXML/Add_courseFX.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Add Courese");
        stage.show();
    }

    public void switchToMyStudents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sparsetablesGUI_FXML/FXTableStudents.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("My students");
        stage.show();
    }

    public void switchToMycourses(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sparsetablesGUI_FXML/FXTableCRS.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("My courses");
        stage.show();
    }

    public void switchToDeleteStudent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sparsetablesGUI_FXML/delete_student.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Delete Student");
        stage.show();
    }

    public void switchToDeleteCourse(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sparsetablesGUI_FXML/deleteCourse.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Delete course");
        stage.show();
    }

    public void switchToRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sparsetablesGUI_FXML/Register.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Registertraion Courses");
        stage.setScene(scene);
        stage.show();

    }

    public void switchToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sparsetablesGUI_FXML/MainFX.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Alexandria University");
        stage.show();

    }

    public void SaveStudent(ActionEvent event) throws IOException {
        String id = ID_STD.getText();
        String name = NameSTD.getText();
        if (id.isEmpty() || name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid data");
            alert.setHeaderText("Enter name of the student and his ID");
            alert.showAndWait();
        } else {
            SparseTables.add_S(name, id, true);
        }
    }

    public void SaveCRS(ActionEvent event) throws IOException {
        String id = CODE_CRS.getText();
        String name = CRS_NAME.getText();
        if (id.isEmpty() || name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid data");
            alert.setHeaderText("Enter name of the Course and its code");
            alert.showAndWait();
        } else {
            SparseTables.add_C(name, id, true);
        }
    }

    public void deleteStudent(ActionEvent event) throws IOException {
        String id = ID_STD_OF_DELETE.getText();
        if (id.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid data");
            alert.setHeaderText("Enter ID of the student");
            alert.showAndWait();
        } else {
            SparseTables.sparseTable.deleteStudent(id, studentList, SparseTables.coursesList);
        }
    }

    public void deleteCourse(ActionEvent event) throws IOException {
        String id = ID_CRS_OF_DELETE.getText();
        if (id.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid data");
            alert.setHeaderText("Enter Name of the course");
            alert.showAndWait();
        } else {
            SparseTables.sparseTable.deleteCourse(id, SparseTables.coursesList, studentList);
        }
    }

}

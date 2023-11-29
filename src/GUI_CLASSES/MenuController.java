package GUI_CLASSES;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import sparsetables.CoursesList;
import sparsetables.SparseTables;
import sparsetables.Students_linkedList;

public class MenuController implements Initializable {

    @FXML
    private ChoiceBox<CoursesList.Course> list_crs;
    @FXML
    private ChoiceBox<Students_linkedList.Student> list_std;
    private Stage stage;
    private Scene scene;
    String st, crs;

    ObservableList<CoursesList.Course> data;
    ObservableList<Students_linkedList.Student> data2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        data = FXCollections.observableArrayList();
        list_crs.setItems(data);

        CoursesList.Course firstCourse = SparseTables.coursesList.getFirstCourse();
        if (firstCourse != null) {
            while (firstCourse != null) {
                data.add(firstCourse);
                firstCourse = firstCourse.getNextCoursedown();
            }
        }

        data2 = FXCollections.observableArrayList();
        Students_linkedList.Student firstStudent = SparseTables.studentList.getFirstStudent();
        if (firstStudent != null) {
            while (firstStudent != null) {
                data2.add(firstStudent);
                firstStudent = firstStudent.getNextStudentRight();
            }
        }
        list_std.setItems(data2);
 
    }

    public void Save(ActionEvent event) throws IOException {
        if (list_std.getSelectionModel().getSelectedItem() == null
                || list_crs.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Choose first");
            alert.showAndWait();
        } else {
            SparseTables.sparseTable.addStudentToCourse(list_std.getSelectionModel().getSelectedItem(), list_crs.getSelectionModel().getSelectedItem());
        }
    }

    public void switchToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sparsetablesGUI_FXML/MainFX.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Alexandria University");
        stage.show();
    }

}

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sparsetables.CoursesList.Course;
import sparsetables.SparseTables;

public class ShowCRScontroller implements Initializable {

    @FXML
    TableView<Course> tableview2;

    @FXML
    private Scene scene;
    private Stage stage;

    ObservableList<Course> data;
    public static Course crs_selected;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        crs_selected = null;
        TableColumn IDCol = new TableColumn("Course Code");
        TableColumn NameCol = new TableColumn("Course Name");

        tableview2.getColumns().addAll(NameCol, IDCol);

        data = FXCollections.observableArrayList();

        IDCol.setCellValueFactory(
                new PropertyValueFactory<Course, String>("CourseId")
        );

        NameCol.setCellValueFactory(
                new PropertyValueFactory<Course, String>("CourseName")
        );

        tableview2.setItems(data);

        Course firstCourse = SparseTables.coursesList.getFirstCourse();
        if (firstCourse != null) {
            while (firstCourse != null) {
                data.add(firstCourse);
                firstCourse = firstCourse.getNextCoursedown();
            }
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

    @FXML
    void getSelectedItem(javafx.scene.input.MouseEvent event) {
        Course crsdata = tableview2.getSelectionModel().getSelectedItem();
        if (crsdata != null) {
            crs_selected = crsdata;
        }
    }

    public void Show(ActionEvent event) throws IOException {
        if (crs_selected != null) {
            Parent root = FXMLLoader.load(getClass().getResource("/sparsetablesGUI_FXML/Students_of_Courses.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Alexandria University");
            stage.show();
        } else { 
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Select first");
            alert.showAndWait();
        }
    }

}

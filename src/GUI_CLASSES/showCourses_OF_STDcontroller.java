package GUI_CLASSES;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sparsetables.CoursesList.Course;
import sparsetables.SparseTables;  
 
public class showCourses_OF_STDcontroller implements Initializable {

    @FXML
    TableView<Course> tableview4;

    @FXML
    private Scene scene;
    private Stage stage;

    Course crsDelete;
    int pos_delete;
    public static ObservableList<Course> data_controller_data2;
    
    @FXML
    Label label_student_of_courses;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        label_student_of_courses.setText("Courses recorded by the student \""+ShowStdController.std_selected.getStudentName()+"\""); 

        TableColumn IDCol = new TableColumn("Course code");
        TableColumn NameCol = new TableColumn("Course Name");

        tableview4.getColumns().addAll(IDCol, NameCol);

        data_controller_data2 = FXCollections.observableArrayList();

        IDCol.setCellValueFactory(
                new PropertyValueFactory<Course, String>("CourseId")
        );

        NameCol.setCellValueFactory(
                new PropertyValueFactory<Course, String>("CourseName")
        );

        tableview4.setItems(data_controller_data2);

        if (ShowStdController.std_selected!=null) {
            SparseTables.sparseTable.displaystudentCourses(ShowStdController.std_selected);
        }

        tableview4.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                if (tableview4.getSelectionModel().getSelectedItem() != null) { 
                    crsDelete=tableview4.getSelectionModel().getSelectedItem();
                }
            }

        });

    }

    public void switchToMain(ActionEvent event) throws IOException {
        data_controller_data2.clear();
        Parent root = FXMLLoader.load(getClass().getResource("/sparsetablesGUI_FXML/FXTableStudents.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Alexandria University");
        stage.show();
    }

    public void unrollGUI(ActionEvent event) throws IOException {
        data_controller_data2.clear(); 
        sparsetables.SparseTables.unroll(ShowStdController.std_selected,crsDelete);
        Parent root = FXMLLoader.load(getClass().getResource("/sparsetablesGUI_FXML/FXTableStudents.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Alexandria University");
        stage.show();
    }

}

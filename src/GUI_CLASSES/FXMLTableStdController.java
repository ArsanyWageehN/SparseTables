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
import sparsetables.SparseTables;
import sparsetables.Students_linkedList.Student;

public class FXMLTableStdController implements Initializable {

    @FXML
    TableView<Student> tableview;

    @FXML
    private Scene scene;
    private Stage stage;

    ObservableList<Student> data;

    public static Student std_selected;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        std_selected=null;

        TableColumn IDCol = new TableColumn("ID");
        TableColumn NameCol = new TableColumn("Name");

        tableview.getColumns().addAll(IDCol, NameCol);

        data = FXCollections.observableArrayList();

        IDCol.setCellValueFactory(
                new PropertyValueFactory<Student, String>("StudentId")
        );

        NameCol.setCellValueFactory(
                new PropertyValueFactory<Student, String>("StudentName")
        );

        tableview.setItems(data);

        Student firstStudent = SparseTables.studentList.getFirstStudent();
        if (firstStudent != null) {
            while (firstStudent != null) {
                data.add(firstStudent);
                firstStudent = firstStudent.getNextStudentRight();
            }
        }

    }

    @FXML
    void getSelectedItem(javafx.scene.input.MouseEvent event) {
        Student stdData = tableview.getSelectionModel().getSelectedItem();
        if (stdData != null) {
            std_selected = stdData;
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

    public void Show(ActionEvent event) throws IOException {
        if (std_selected != null) {
            Parent root = FXMLLoader.load(getClass().getResource("/sparsetablesGUI_FXML/Courses_OF_Students.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Alexandria University");
            stage.show();
        }else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Select first");
            alert.showAndWait();
        }
    }

}

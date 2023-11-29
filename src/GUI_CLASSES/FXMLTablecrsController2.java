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
import sparsetables.SparseTables; 
import sparsetables.Students_linkedList.Student; 

public class FXMLTablecrsController2 implements Initializable {

    @FXML
    TableView<Student> tableview3;

    @FXML
    private Scene scene;
    private Stage stage;
    Student stdDelete;
    
    @FXML
    Label label_courses_of_student;


    public static ObservableList<Student> data_controller_data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TableColumn IDCol = new TableColumn("Student ID");
        TableColumn NameCol = new TableColumn("Student Name");
         
        label_courses_of_student.setText("Students enrolled in a course \""+FXMLTablecrsController.crs_selected.getCourseName()+"\""); 

        tableview3.getColumns().addAll(NameCol,IDCol);

        data_controller_data = FXCollections.observableArrayList();

        IDCol.setCellValueFactory(
                new PropertyValueFactory<Student, String>("StudentId")
        );

        NameCol.setCellValueFactory(
                new PropertyValueFactory<Student, String>("StudentName")
        );

        tableview3.setItems(data_controller_data);

        if (FXMLTablecrsController.crs_selected!=null) {
           SparseTables.sparseTable.displayCourseStudents(FXMLTablecrsController.crs_selected); 
        }
        
        
        tableview3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                if (tableview3.getSelectionModel().getSelectedItem() != null) {
 
                    stdDelete =  tableview3.getSelectionModel().getSelectedItem();
                }
            }

        });

    }

    public void switchToMain(ActionEvent event) throws IOException {
        data_controller_data.clear();
        Parent root = FXMLLoader.load(getClass().getResource("/sparsetablesGUI_FXML/FXTableCRS.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Alexandria University");
        stage.show();
    }
    
      public void unrollGUI(ActionEvent event) throws IOException {
        data_controller_data.clear();
        SparseTables.unroll(stdDelete,FXMLTablecrsController.crs_selected);
        Parent root = FXMLLoader.load(getClass().getResource("/sparsetablesGUI_FXML/FXTableCRS.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Alexandria University");
        stage.show();
    }

}

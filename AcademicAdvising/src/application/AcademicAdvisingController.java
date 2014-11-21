package application;

/**
 * Sample Skeleton for 'AcademicAdvising.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class AcademicAdvisingController {
	
	

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="newStudentMenu"
    private MenuItem newStudentMenu; // Value injected by FXMLLoader
    
    @FXML
    private HBox headerBox;
    
    public void setHeaderBox(Node myBox){
    	headerBox = (HBox) myBox;
    }
    
    @FXML
    private TableView<account.Student> advisingTable;
    
    @FXML
    private TableColumn<account.Student, String> ColFName;
    
    @FXML
    private TableColumn<account.Student, String> colLName;
    
    @FXML
    private TableColumn<account.Student, String> colVID;
    
    @FXML
    private TableColumn<account.Student, String> colGrade;
    
    @FXML
    private TableColumn<account.Student, String> colAA;
    
    @FXML
    private TableColumn<account.Student, String> colDate;



    
    
    
    
    
    @FXML
    private ObservableList<account.Student> sList;
    
    
    
    
    
    

    @FXML
    void newStudentButtonHandler(ActionEvent event) {
    	try {
    		
			headerBox.getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("NewStudent.fxml")));
			HBox.setHgrow(headerBox.getScene().lookup("#newStudentPane"), Priority.ALWAYS);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert newStudentMenu != null : "fx:id=\"newStudentMenu\" was not injected: check your FXML file 'AcademicAdvising.fxml'.";
        
        sList = new account.StudentList("C:\\Users\\smaybush.BIZPORTBIZ\\Eclipse Workspace\\AcademicAdvising\\src\\account\\Academic.txt").getStudentList();
        
        ColFName.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
        colLName.setCellValueFactory(cellData-> cellData.getValue().getLastNameProperty());
        colVID.setCellValueFactory(cellData -> cellData.getValue().getStudentIdProperty());
        colGrade.setCellValueFactory(cellData -> cellData.getValue().getGradeProperty());
        colAA.setCellValueFactory(cellData -> cellData.getValue().getAdvisingProperty());
        colDate.setCellValueFactory(cellData-> cellData.getValue().getAdvisingDateProperty());
        
        advisingTable.setItems(sList);

    }
}


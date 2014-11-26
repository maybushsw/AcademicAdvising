package application;

/**
 * Sample Skeleton for 'AcademicAdvising.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Callback;
import account.Student;
import account.StudentList;


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
    private TableView<Student> advisingTable;
    
    @FXML
    private TableColumn<Student, String> ColFName;
    
    @FXML
    private TableColumn<Student, String> colLName;
    
    @FXML
    private TableColumn<Student, String> colVID;
    
    @FXML
    private TableColumn<Student, String> colGrade;
    
    @FXML
    private TableColumn<Student, Boolean> colAA;
    
    @FXML
    private TableColumn<Student, String> colDate;




    
    
    
    
    
   
    
    
    @FXML
    private StudentList sList;
    
    public StudentList getData(){
    	
    	return sList;
    }
    
    
    

    @FXML
    void newStudentMenuItemHandler(ActionEvent event) {
    	try {
    		
    		FXMLLoader nloader = new FXMLLoader(Main.class.getResource("NewStudent.fxml"));
    		
    		AnchorPane newStudent = (AnchorPane)nloader.load();
    		NewStudentPaneController nController = nloader.getController();
    		nController.setStudentList(sList);
    		nController.setPreviousChildren(headerBox.getChildren());
			headerBox.getChildren().setAll(newStudent);
			HBox.setHgrow(headerBox.getScene().lookup("#newStudentPane"), Priority.ALWAYS);
			
		nController.setHeaderBox(headerBox);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert newStudentMenu != null : "fx:id=\"newStudentMenu\" was not injected: check your FXML file 'AcademicAdvising.fxml'.";
        
		Callback<TableColumn<Student,String>, TableCell<Student,String>> cellFactory = new Callback<TableColumn<Student, String>, TableCell<Student,String>>() {
            @Override
            public TableCell<Student,String> call(TableColumn<Student,String> p) {
                return new EditingCell();
            }
        };
        sList = new StudentList("Academic.txt");
        
        
        ColFName.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
        ColFName.setCellFactory(cellFactory);
        colLName.setCellValueFactory(cellData-> cellData.getValue().getLastNameProperty());
        colLName.setCellFactory(cellFactory);
        colVID.setCellValueFactory(cellData -> cellData.getValue().getStudentIdProperty());
        colVID.setCellFactory(cellFactory);
        colGrade.setCellValueFactory(cellData -> cellData.getValue().getGradeProperty());
        colGrade.setCellFactory(cellFactory);
        colAA.setCellValueFactory(cellData -> cellData.getValue().getAdvisingProperty());
        colAA.setCellFactory(cellData -> new CheckBoxTableCell<>());
        colDate.setCellValueFactory(cellData-> cellData.getValue().getAdvisingDateProperty());
        
        advisingTable.setItems(sList.getStudentList());
        
       advisingTable.setEditable(true);
       
       
       
       
     
       
       ColFName.setOnEditCommit(new EventHandler<CellEditEvent<Student,String>>(){
    	   @Override
    	   public void handle(CellEditEvent<Student, String> t){
    		   ((Student) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFirstName(t.getNewValue());
    		   sList.PrintList();
    	   }
    	   
    	  
    	   
       });
       
       colLName.setOnEditCommit(new EventHandler<CellEditEvent<Student,String>>(){
    	   @Override
    	   public void handle(CellEditEvent<Student, String> t){
    		   ((Student) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLastName(t.getNewValue());
    	   }
    	   
       });
       
       colVID.setOnEditCommit(new EventHandler<CellEditEvent<Student,String>>(){
    	   @Override
    	   public void handle(CellEditEvent<Student, String> t){
    		   ((Student) t.getTableView().getItems().get(t.getTablePosition().getRow())).setStudentId(t.getNewValue());
    	   }
    	   
       });
       
       colGrade.setOnEditCommit(new EventHandler<CellEditEvent<Student,String>>(){
    	   @Override
    	   public void handle(CellEditEvent<Student, String> t){
    		   ((Student) t.getTableView().getItems().get(t.getTablePosition().getRow())).setGrade(t.getNewValue());
    	   }
    	   
       });
       
       
       
       
       
    }
    
    
}


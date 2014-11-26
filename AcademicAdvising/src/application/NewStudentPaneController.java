package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import account.Student;
import account.StudentList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;


public class NewStudentPaneController {
	
	
		@FXML
		private ObservableList<Node> pChildren;
		
	    @FXML
	    private HBox headerBox;
	   
	
	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private TextField newSVID;

	    @FXML
	    private TextField newSFirstName;

	    @FXML
	    private ChoiceBox<String> newSGrade;

	    @FXML
	    private TextField newSLastName;

	    @FXML
	    private AnchorPane newStudentPane;
	    

	    @FXML
	    private Button newStudentSave;
	    
	    @FXML 
	    
	    private StudentList sList;
	    
	    public void setStudentList(StudentList mList){
	    	
	    	sList = mList;
	    }
	    
	    public void setHeaderBox(HBox mHeaderBox){
	    	
	    	headerBox = mHeaderBox;
	    }
	    
	    public void setPreviousChildren(ObservableList<Node> mPChildren){
	    	
	    	pChildren = FXCollections.observableArrayList(mPChildren);
	    }
	    
	 
	    
	    @FXML
	    void newStudentButtonHandler(ActionEvent event) {
	    	
	    	Student nStudent = new Student(newSFirstName.getText(),"",newSLastName.getText(),newSVID.getText(),newSGrade.getValue().toString(),false,"");
	    	
	    	sList.Add(nStudent);
	    	
	    	headerBox.getChildren().clear();
	    	headerBox.getChildren().setAll(pChildren);
	    	
	   
	    }

	    @FXML
	    void initialize() {
	        assert newSVID != null : "fx:id=\"newSVID\" was not injected: check your FXML file 'NewStudent.fxml'.";
	        assert newSFirstName != null : "fx:id=\"newSFirstName\" was not injected: check your FXML file 'NewStudent.fxml'.";
	        assert newSGrade != null : "fx:id=\"newSGrade\" was not injected: check your FXML file 'NewStudent.fxml'.";
	        assert newSLastName != null : "fx:id=\"newSLastName\" was not injected: check your FXML file 'NewStudent.fxml'.";
	        assert newStudentPane != null : "fx:id=\"newStudentPane\" was not injected: check your FXML file 'NewStudent.fxml'.";

	        newSGrade.setItems(FXCollections.observableArrayList(
        	    "Freshman", "Sophmore", "Junior", "Senior"));
	        
	       newSGrade.setValue("Freshman");
	    }

}

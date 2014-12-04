package application;
import java.net.URL;
import java.util.ResourceBundle;

import account.Student;
import account.StudentList;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;


public class GraduationTableConstroller {
	
	@FXML
	private AcademicAdvisingController pController;
	
	  @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private TableColumn<Student,Integer> colGUpperCredits;

	    @FXML
	    private TableColumn<Student, Integer> colGTotalCredits;

	    @FXML
	    private TableColumn<Student, String> colGStudentID;

	    @FXML
	    private TableColumn<Student, Double> colGMajorGPA;

	    @FXML
	    private TableColumn<Student, String> colGSubDate;

	    @FXML
	    private TableView<Student> gradTable;

	    @FXML
	    private TableColumn<Student, String> colGFirstName;

	    @FXML
	    private TableColumn<Student, String> colGLastNmae;

	    @FXML
	    private TableColumn<Student, Double> colGTotalGPA;
	    
	    @FXML
	    private TableColumn<Student, Boolean> colAppSubmit;
	    
	    @FXML
	    private StudentList sList;
	    
	    @FXML
	    private TableColumn<Student, Boolean> colGDelete;
	    
	    
	    //Public method for data exchange and access.
	    public void setStudentList(StudentList mList){
	    	
	    	sList = mList;
	    	gradTable.setItems(sList.getStudentList());
	    	
	    }
	    
	 
	    
	 
	    
	   
	  

	    @FXML
	    void initialize() {
	    	
	    	
	        assert colGUpperCredits != null : "fx:id=\"colGUpperCredits\" was not injected: check your FXML file 'GraduationTable.fxml'.";
	        assert colGTotalCredits != null : "fx:id=\"colGTotalCredits\" was not injected: check your FXML file 'GraduationTable.fxml'.";
	        assert colGStudentID != null : "fx:id=\"colGStudentID\" was not injected: check your FXML file 'GraduationTable.fxml'.";
	        assert colGMajorGPA != null : "fx:id=\"colGMajorGPA\" was not injected: check your FXML file 'GraduationTable.fxml'.";
	        assert colGSubDate != null : "fx:id=\"colGSubDate\" was not injected: check your FXML file 'GraduationTable.fxml'.";
	        assert gradTable != null : "fx:id=\"gradTable\" was not injected: check your FXML file 'GraduationTable.fxml'.";
	        assert colGFirstName != null : "fx:id=\"colGFirstName\" was not injected: check your FXML file 'GraduationTable.fxml'.";
	        assert colGLastNmae != null : "fx:id=\"colGLastNmae\" was not injected: check your FXML file 'GraduationTable.fxml'.";
	        assert colGTotalGPA != null : "fx:id=\"colGTotalGPA\" was not injected: check your FXML file 'GraduationTable.fxml'.";
	        
	        
	        //call backs for columns
	        Callback<TableColumn<Student,String>, TableCell<Student,String>> cellFactory = new Callback<TableColumn<Student, String>, TableCell<Student,String>>() {
	            @Override
	            public TableCell<Student,String> call(TableColumn<Student,String> p) {
	                return new EditingCell();
	            }
	        };
	        
	        Callback<TableColumn<Student,Integer>, TableCell<Student,Integer>> intCellFactory = new Callback<TableColumn<Student, Integer>, TableCell<Student,Integer>>() {
	            @Override
	            public TableCell<Student,Integer> call(TableColumn<Student,Integer> p) {
	                return new IntEditingCell();
	            }
	        };
	        
	        Callback<TableColumn<Student,Double>, TableCell<Student,Double>> doubleCellFactory = new Callback<TableColumn<Student, Double>, TableCell<Student,Double>>() {
	            @Override
	            public TableCell<Student,Double> call(TableColumn<Student,Double> p) {
	                return new DoubleEditingCell();
	            }
	        };
	        
	        
	        //add data and callbacks to columns
	        colGFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
	        colGFirstName.setCellFactory(cellFactory);
	        colGLastNmae.setCellValueFactory(cellData-> cellData.getValue().getLastNameProperty());
	        colGLastNmae.setCellFactory(cellFactory);
	        colGStudentID.setCellValueFactory(cellData -> cellData.getValue().getStudentIdProperty());
	        colGStudentID.setCellFactory(cellFactory);
	        colGUpperCredits.setCellValueFactory(cellData -> cellData.getValue().getUpperLevelCreditsProperty());
	        colGUpperCredits.setCellFactory(intCellFactory);
	        colGTotalCredits.setCellValueFactory(cellData -> cellData.getValue().getTotalCreditsProperty());
	        colGTotalCredits.setCellFactory(intCellFactory);
	        colGMajorGPA.setCellValueFactory(cellData -> cellData.getValue().getMajorGPAProperty());
	        colGMajorGPA.setCellFactory(doubleCellFactory);
	        colAppSubmit.setCellValueFactory(cellData -> cellData.getValue().getGradSubmitProperty());
	        colAppSubmit.setCellFactory(cellData -> new CheckBoxTableCell<>());
	        colGSubDate.setCellValueFactory(cellData -> cellData.getValue().getGradSubmissionDateProperty());
	        colGTotalGPA.setCellValueFactory(cellData -> cellData.getValue().getTotalGPAProperty());
	        colGTotalGPA.setCellFactory(doubleCellFactory);
	        
	        
	        //Delete button column
	        colGDelete.setCellValueFactory(
	        		new Callback<TableColumn.CellDataFeatures<Student, Boolean>,
	        		ObservableValue<Boolean>>() {
	        		 
	        		@Override
	        		public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Student, Boolean> p) {
	        		return new SimpleBooleanProperty(p.getValue() != null);
	        		}
	        		}); 
	        colGDelete.setCellFactory(
	        		new Callback<TableColumn<Student, Boolean>, TableCell<Student, Boolean>>() {
	        		 
	        		@Override
	        		public TableCell<Student, Boolean> call(TableColumn<Student, Boolean> p) {
	        		return new ButtonCell(sList);
	        		}
	        		});
	        
	        
	        
	        gradTable.setEditable(true);
	        
	        
	        //Actions events for editing calls of columns
	        
	        colGFirstName.setOnEditCommit(new EventHandler<CellEditEvent<Student,String>>(){
	     	   @Override
	     	   public void handle(CellEditEvent<Student, String> t){
	     		   ((Student) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFirstName(t.getNewValue());
	     		   sList.PrintList();
	     	   }  
	        });
	        
	        colGLastNmae.setOnEditCommit(new EventHandler<CellEditEvent<Student,String>>(){
	     	   @Override
	     	   public void handle(CellEditEvent<Student, String> t){
	     		   ((Student) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLastName(t.getNewValue());
	     		   sList.PrintList();
	     	   }  
	        });
	        
	        colGStudentID.setOnEditCommit(new EventHandler<CellEditEvent<Student,String>>(){
	     	   @Override
	     	   public void handle(CellEditEvent<Student, String> t){
	     		   ((Student) t.getTableView().getItems().get(t.getTablePosition().getRow())).setStudentId(t.getNewValue());
	     		   sList.PrintList();
	     	   }  
	        });
	        
	        colGUpperCredits.setOnEditCommit(new EventHandler<CellEditEvent<Student,Integer>>(){
	     	   @Override
	     	   public void handle(CellEditEvent<Student, Integer> t){
	     		   ((Student) t.getTableView().getItems().get(t.getTablePosition().getRow())).setUpperLevelCredits(t.getNewValue());
	     		   sList.PrintList();
	     	   }  
	        });
	        
	        colGTotalCredits.setOnEditCommit(new EventHandler<CellEditEvent<Student,Integer>>(){
		     	   @Override
		     	   public void handle(CellEditEvent<Student, Integer> t){
		     		   ((Student) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTotalCredits(t.getNewValue());
		     		   sList.PrintList();
		     	   }  
		        });
	        
	        colGSubDate.setOnEditCommit(new EventHandler<CellEditEvent<Student,String>>(){
		     	   @Override
		     	   public void handle(CellEditEvent<Student, String> t){
		     		   ((Student) t.getTableView().getItems().get(t.getTablePosition().getRow())).setGradSubmissionDate(t.getNewValue());
		     		   sList.PrintList();
		     	   }  
		        });
	        
	        colGMajorGPA.setOnEditCommit(new EventHandler<CellEditEvent<Student,Double>>(){
		     	   @Override
		     	   public void handle(CellEditEvent<Student, Double> t){
		     		   ((Student) t.getTableView().getItems().get(t.getTablePosition().getRow())).setMajorGPA(t.getNewValue());
		     		   sList.PrintList();
		     	   }  
		        });
	        
	        colGTotalGPA.setOnEditCommit(new EventHandler<CellEditEvent<Student,Double>>(){
		     	   @Override
		     	   public void handle(CellEditEvent<Student, Double> t){
		     		   ((Student) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTotalGPA(t.getNewValue());
		     		   sList.PrintList();
		     	   }  
		        });
	        
	        
	       
	    }

}

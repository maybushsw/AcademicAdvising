package application;

/**
 * Sample Skeleton for 'AcademicAdvising.fxml' Controller Class
 */

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
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
    
   
    
    
    @FXML
    private MenuItem advisingMenuButon;

    @FXML
    private MenuItem graduationMenuButton;
    
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
    private TableColumn<Student, Boolean> colDelete;

    @FXML
    private PieChart advisingChart;
    
    @FXML
    private HBox tableBox;
    
       
    @FXML
    private StudentList sList;
    
    @FXML
    private AnchorPane mainWindow;
    
    @FXML
    private MenuItem importMenuItem;
    
    
    
    //public functions for data exchange
    
    public void setHeaderBox(Node myBox){
    	headerBox = (HBox) myBox;
    }
    
   
    
    public void setStudentList(StudentList mList){
    	
    	sList = mList;
    }
    
    public StudentList getData(){
    	
    	return sList;
    }
    
    
    //Action event for Import

    @FXML
    void importMenuItemHandler(ActionEvent event) {
    	
    	final FileChooser fChooser = new FileChooser();
    	File iFile = fChooser.showOpenDialog(tableBox.getScene().getWindow());
    	
    	if(iFile != null){
    		
    		StudentList myStudentList = new StudentList(iFile.getPath());
    		sList.getStudentList().addAll(myStudentList.getStudentList());
    	}
    	
    	

    }
    
    //Action event for advising view
    
    @FXML
    void advisingMenuButtonHandler(ActionEvent event) {
    	
   
    tableBox.getChildren().clear();
    tableBox.getChildren().setAll(advisingTable);
    headerBox.getChildren().clear();
    headerBox.getChildren().setAll(advisingChart);
    
   

    }

    
    //Action Event for graduation view
    @FXML
    void graduationMenuButtonHandler(ActionEvent event) {
    	
    	
    	
    	FXMLLoader nloader = new FXMLLoader(Main.class.getResource("GraduationTable.fxml"));
    	FXMLLoader mloader = new FXMLLoader(Main.class.getResource("GraduationPieChart.fxml"));
    	try {
    		AnchorPane gradPieChart = (AnchorPane)mloader.load();
    		GraduationChartController mController = mloader.getController();
    		mController.setStudentList(sList);
    		headerBox.getChildren().setAll(gradPieChart);
			AnchorPane m_gradTable = (AnchorPane)nloader.load();
			GraduationTableConstroller nController = nloader.getController();
	    	nController.setStudentList(sList);
	    	tableBox.getChildren().setAll(m_gradTable);
	    	HBox.setHgrow(tableBox.getScene().lookup("#gradPane"), Priority.ALWAYS);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    }
    

    //Action event for new Student menue
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

    
    // initialise
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert newStudentMenu != null : "fx:id=\"newStudentMenu\" was not injected: check your FXML file 'AcademicAdvising.fxml'.";
        
        //Get Data
        sList = new StudentList("Academic.txt");
 
        		
        		
        
     //Set call back for editing cells
		Callback<TableColumn<Student,String>, TableCell<Student,String>> cellFactory = new Callback<TableColumn<Student, String>, TableCell<Student,String>>() {
            @Override
            public TableCell<Student,String> call(TableColumn<Student,String> p) {
                return new EditingCell();
            }
        };
        
        
       //Assign callbacks and data to columns
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
        
        
        
       
        
        colDelete.setCellValueFactory(
        		new Callback<TableColumn.CellDataFeatures<Student, Boolean>,
        		ObservableValue<Boolean>>() {
        		 
        		@Override
        		public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Student, Boolean> p) {
        		return new SimpleBooleanProperty(p.getValue() != null);
        		}
        		}); 
        colDelete.setCellFactory(
        		new Callback<TableColumn<Student, Boolean>, TableCell<Student, Boolean>>() {
        		 
        		@Override
        		public TableCell<Student, Boolean> call(TableColumn<Student, Boolean> p) {
        		return new ButtonCell(sList);
        		}
        		}); 
        
        advisingTable.setItems(sList.getStudentList());
        
       advisingTable.setEditable(true);
           
       
   
     
       //Action Events for Column cells
       ColFName.setOnEditCommit(new EventHandler<CellEditEvent<Student,String>>(){
    	   @Override
    	   public void handle(CellEditEvent<Student, String> t){
    		   ((Student) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFirstName(t.getNewValue());
    		  
    		   
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
       
       //Add data to chart
       setPieChart(sList);
       
       
       //Add Change listener to data for piecharts
       sList.getStudentList().addListener(new ListChangeListener<Student>(){
    	   
    	   @Override
    	   public void onChanged(ListChangeListener.Change<? extends Student> change){
    		   
    		  
    		   
    		   while(change.next()){
    			   
    			   if(change.wasUpdated()){
    				   
    				   Student stu = sList.getStudentList().get(change.getFrom());
    				   if(stu.getAdvising()){
    				   DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
    				   
    				   
    				   sList.getStudentList().get(change.getFrom()).setAdvisingDate(dateFormat.format(new Date()));
    				   setPieChart(sList);
    				   }
    				   else{
    					   sList.getStudentList().get(change.getFrom()).setAdvisingDate(""); 
    					   setPieChart(sList);
    				   }
    				   
    				   if(stu.getGradSubmit()){
    					   
    					   DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
    					   sList.getStudentList().get(change.getFrom()).setGradSubmissionDate(dateFormat.format(new Date()));
    					   
    				   }
    				   
    				   else{
    					   
    					   sList.getStudentList().get(change.getFrom()).setGradSubmissionDate("");
    				   }
    			   }
    		   }
    		   
    	   
    	   }
       });

       
       
    }
    
    //Piechart data set up
    public void setPieChart(StudentList sList){
        
        int advised = 0;
        int total = sList.getStudentList().size();
        
        for(Student n: sList.getStudentList()){
     	   
     	  if(n.getAdvising()){
     		  advised++;
     	  }
        }
        
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Advised", advised),
                new PieChart.Data("Not Advisied", total-advised));
                
        advisingChart.setData(pieChartData);
       
     }
}


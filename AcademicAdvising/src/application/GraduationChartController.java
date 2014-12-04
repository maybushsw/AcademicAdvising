package application;
import java.net.URL;
import java.util.ResourceBundle;
import account.Student;
import account.StudentList;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class GraduationChartController {
	
	   @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private PieChart graduationChart;
	    
	    @FXML
	    private StudentList sList;
	    
	    //public Method for data exchange
	    
	    public void setStudentList(StudentList mList){
	    	
	    	sList = mList;
	        sList.getStudentList().addListener(new ListChangeListener<Student>(){
		     	   
		     	   @Override
		     	   public void onChanged(ListChangeListener.Change<? extends Student> change){
		     		   
		     		  
		     		   
		     		   while(change.next()){
		     			   
		     			   if(change.wasUpdated()){
		     				   
		     				   setPieChart(sList);
		     				   
		     				
		     			   }
		     		   }
		     		   
		     	   
		     	   }
		        });
	    	setPieChart(sList);
	    }

	    @FXML
	    void initialize() {
	        assert graduationChart != null : "fx:id=\"graduationChart\" was not injected: check your FXML file 'GraduationPieChart.fxml'.";
	       
	    
	        
	        
	    }
	    
	    
	    //Set pie chart data
	    public void setPieChart(StudentList sList){
	        
	        int submitted = 0;
	        int total = sList.getStudentList().size();
	        
	        for(Student n: sList.getStudentList()){
	     	   
	     	  if(n.getGradSubmit()){
	     		  submitted++;
	     	  }
	        }
	        
	        ObservableList<PieChart.Data> pieChartData =
	                FXCollections.observableArrayList(
	                new PieChart.Data("Submitted", submitted),
	                new PieChart.Data("Not Submitted", total-submitted));
	                
	        graduationChart.setData(pieChartData);
	       
	     }

}

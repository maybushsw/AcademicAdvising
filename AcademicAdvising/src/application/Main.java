package application;
	


import java.util.logging.Level;
import java.util.logging.Logger;






import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

	private AcademicAdvisingController mController;
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
    	
    	
    	
    
    	
    	
    	
        Application.launch(Main.class, (java.lang.String[])null);
        
       
    }

    @Override
    public void start(Stage primaryStage) {
        try {
        	
        	//Load the initial Window
        	setUserAgentStylesheet(STYLESHEET_CASPIAN);
        	FXMLLoader loader = new FXMLLoader(Main.class.getResource("AcademicAdvising.fxml"));
            AnchorPane page = (AnchorPane) loader.load(); 
            Scene scene = new Scene(page);
            mController = loader.getController();
            mController.setHeaderBox(scene.lookup("#headerBox"));
            primaryStage.setMinWidth(999.0);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Academic Advising");
            primaryStage.show();
            
            
            //On close action event to save data
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            	
            	@Override
            	public void handle (WindowEvent event){
            		
            		mController.getData().writeStudentListToFile("Academic.txt");
            		
            		
            	}
            }
            		
            		);
            
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    	
        
    
  
}

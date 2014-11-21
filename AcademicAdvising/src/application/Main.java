package application;
	


import java.util.logging.Level;
import java.util.logging.Logger;




import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
    	
    	
    	
    
    	
    	
    	
        Application.launch(Main.class, (java.lang.String[])null);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
        	
        	
        	setUserAgentStylesheet(STYLESHEET_CASPIAN);
        	FXMLLoader loader = new FXMLLoader(Main.class.getResource("AcademicAdvising.fxml"));
            AnchorPane page = (AnchorPane) loader.load(); 
            Scene scene = new Scene(page);
            AcademicAdvisingController mController = loader.getController();
            mController.setHeaderBox(scene.lookup("#headerBox"));
            primaryStage.setMinWidth(999.0);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Academic Advising");
            primaryStage.show();
            
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    	
        
    
  
}

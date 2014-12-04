package application;

import account.Student;
import account.StudentList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

public class ButtonCell extends TableCell<Student,Boolean> {

	 
		 final Button cellButton = new Button("Delete");
		 ButtonCell(StudentList sList){
		 //Action when the button is pressed
		 cellButton.setOnAction(new EventHandler<ActionEvent>(){
		  
		 @Override
		 public void handle(ActionEvent t) {
		 // get Selected Item
		 Student currentStudent= (Student) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
		 //remove selected item from the table list
		 sList.getStudentList().remove(currentStudent);
		 
		
		 }
		 });
		 }
		  
		 //Display button if the row is not empty
		 @Override
		 protected void updateItem(Boolean t, boolean empty) {
		 super.updateItem(t, empty);
		 if(!empty){
		 setGraphic(cellButton);
		 }
		 else{
			 setGraphic(null);
		 }
		 }
}
		 


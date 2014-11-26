package account;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private StringProperty sFirstName;
    private StringProperty sLastName;
    private StringProperty sMiddleName;
    private StringProperty sStudentId;
    private StringProperty sGrade;  
    private BooleanProperty sAdvising;
    private StringProperty sAdvisingDate;
    
    // constructor
    public Student(String a_sFirstName, String a_sMiddleName, String a_sLastName, String a_sId, String a_sGrade, Boolean a_sAdvising, String a_sDate) {
        sFirstName = new SimpleStringProperty(a_sFirstName);
        sLastName = new SimpleStringProperty(a_sLastName);
        sMiddleName = new SimpleStringProperty(a_sMiddleName);
        sStudentId = new SimpleStringProperty(a_sId);
        sGrade = new SimpleStringProperty(a_sGrade);
        sAdvising = new SimpleBooleanProperty(a_sAdvising);
        sAdvisingDate = new SimpleStringProperty(a_sDate);
    }
    
    // Setter methods
    public void setFirstName (String a_sFirstName) {
       this.sFirstName.set(a_sFirstName);
    }
   
    public void setLastName (String a_sLastName) {
        this.sLastName.set(a_sLastName);
    }
    
    public void setMiddleName (String a_sMiddleName) {
        this.sMiddleName.set(a_sMiddleName);
    }
    
    public void setGrade (String a_sGrade) {
        this.sGrade.set(a_sGrade);
    }
 
    public void setAdvising (Boolean a_sAdvising) {
        this.sAdvising.set(a_sAdvising);
    }
    
    public void setAdvisingDate (String a_sDate) {
        this.sAdvisingDate.set(a_sDate);
    }
    
    public void setStudentId (String a_VID){
    	
    	this.sStudentId.set(a_VID);
    }
    
    // Getter methods
    public String getFirstName () {
        return sFirstName.get();
    }
    
    public StringProperty getFirstNameProperty(){
    	return sFirstName;
    }

    public String getLastName () {
        return sLastName.get();
    }
    
    public StringProperty getLastNameProperty(){
    	return sLastName;
    }
    public String getMiddleName () {
        return sMiddleName.get();
    }
    
    public StringProperty getMiddleNameProperty(){
    	return sMiddleName;
    }
    
    public String getStudentId () {
        return sStudentId.get();
    }
    
    public StringProperty getStudentIdProperty () {
        return sStudentId;
    }
    
    public String getGrade () {
        return sGrade.get();
    }
    
    public StringProperty getGradeProperty () {
        return sGrade;
    }
    
    public Boolean getAdvising () {
        return sAdvising.get();
    }
    
    public BooleanProperty getAdvisingProperty () {
        return sAdvising;
    }
    
    public String getAdvisingDate () {
        return sAdvisingDate.get();
    }
    
    public StringProperty getAdvisingDateProperty () {
        return sAdvisingDate;
    }
    
    @Override
    public boolean equals (Object otherStudent) {
    
        if (this == otherStudent)
        {
            System.err.println("Student.equals() ERROR: the argument is a reference to itself");
            return true;
        }
        
        if (otherStudent instanceof Student)
            return sStudentId.equals(((Student)otherStudent).getStudentId()); 
        else
            return false;
    }
    
    @Override
    public String toString() {
        return sFirstName.get() + " " + sMiddleName.get() + " " + sLastName.get() + ", " + sStudentId.get() + ", " + sGrade.get() + ", " + sAdvising.get() + ", " + sAdvisingDate.get();
    }  
}

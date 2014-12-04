
package account;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;







import javafx.beans.Observable;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.util.Callback;


public class StudentList {
    
    private ObservableList<Student> lList = null;
    private String sDbFileName = "Academic.txt";
   
    public StudentList () {
        lList = FXCollections.<Student>observableArrayList(new Callback<Student, Observable[]>() {
            @Override
            public Observable[] call(Student eStudent) {
              return new Observable[]{eStudent.getAdvisingProperty(),eStudent.getGradSubmitProperty()};
            }
          });
        
      
        
        try
        {
            getStudentListFromFile(sDbFileName);
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
        
     
    }
    
    public StudentList (String a_sFileName) {
        lList = FXCollections.<Student>observableArrayList(new Callback<Student, Observable[]>() {
            @Override
            public Observable[] call(Student eStudent) {
            	return new Observable[]{eStudent.getAdvisingProperty(),eStudent.getGradSubmitProperty()};
            }
          });
        sDbFileName = a_sFileName;
        
        try
        {
            getStudentListFromFile(sDbFileName);
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    
    public ObservableList<Student> getStudentList(){
    	
    	return lList;
    }
    
    private void getStudentListFromFile(String a_sFileName) throws IOException {
        File inFile = new File(a_sFileName);
        if (inFile.exists()) {

            Scanner scanFile = new Scanner(inFile);
            String sLine;
                        
            while (scanFile.hasNextLine()) {
                sLine = scanFile.nextLine(); 
                
                System.out.println(sLine); 
                
                String[] studentInfo = sLine.split(",");
                
                String sFName  = "";
                String sMName  = "";
                String sLName  = ""; 
                String sId     = "";             
                String sGrade  = "";
                Boolean sAdvice = false;
                String sDate   = "";
                Integer sUpperLevelCredits = 0;
                Integer sTotalCredits = 0;
                String sGradSubmissionDate = "";
                Double sMajorGPA = 0.0;
                Double sTotalGPA = 0.0;
                Boolean sGradSubmit = false;
                
                for (int i = 0; i < studentInfo.length; i++) {
                    switch (i) {
                        case 0: sFName  = studentInfo[0].substring(0, studentInfo[0].length()); break;
                        case 1: sMName  = studentInfo[1].substring(0, studentInfo[1].length()); break;
                        case 2: sLName  = studentInfo[2].substring(0, studentInfo[2].length()); break;
                        case 3: sId     = studentInfo[3].substring(0, studentInfo[3].length()); break;
                        case 4: sGrade  = studentInfo[4].substring(0, studentInfo[4].length()); break;
                        case 5: sAdvice = Boolean.valueOf(studentInfo[5].substring(0, studentInfo[5].length())); break;
                        case 6: sDate   = studentInfo[6].substring(0, studentInfo[6].length()); break;
                        case 7: sUpperLevelCredits =Integer.valueOf(studentInfo[7].substring(0, studentInfo[7].length())); break;
                        case 8: sTotalCredits = Integer.valueOf(studentInfo[8].substring(0,studentInfo[8].length())); break;
                        case 9: sGradSubmissionDate = studentInfo[9].substring(0,studentInfo[9].length()); break;
                        case 10: sMajorGPA = Double.valueOf(studentInfo[10].substring(0, studentInfo[10].length())); break;
                        case 11: sTotalGPA = Double.valueOf(studentInfo[11].substring(0, studentInfo[11].length())); break;
                        case 12: sGradSubmit = Boolean.valueOf(studentInfo[12].substring(0, studentInfo[12].length())); break;
                        default: break;
                    }
                }
                Student st = new Student(sFName, sMName, sLName, sId, sGrade, sAdvice, sDate, sUpperLevelCredits,
                		sTotalCredits, sGradSubmissionDate, sMajorGPA,sTotalGPA, sGradSubmit);
                
                lList.add(st);
            }

            scanFile.close();  
        }  
        else
        {
            System.out.println("File doesn't exist");
        }
    }

    private void writeStudentToFile(Student a_student, String a_sFileName) {
        
        try
        {           
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(a_sFileName, true)));
            out.println(a_student.getFirstName() + "," + a_student.getMiddleName() + "," + a_student.getLastName() + "," +
                        a_student.getStudentId() + "," + a_student.getGrade() + "," + a_student.getAdvising().toString() + "," + a_student.getAdvisingDate() + "," +
                        a_student.getUpperLevelCredits().toString() + "," + a_student.getTotalCredits().toString() + "," +
                        a_student.getGradSubmissionDate() + "," + a_student.getMajorGPA().toString() + "," + a_student.getTotalGPA().toString() + "," + a_student.getGradSubmit());
            out.close();
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    
    public void writeStudentListToFile(String a_sFileName) {
        
        try
        {           
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(a_sFileName, false)));
            
            Iterator<Student> itr = lList.iterator();
            while(itr.hasNext()) {
                Student s = (Student)itr.next(); 
                out.println(s.getFirstName() + "," + s.getMiddleName() + "," + s.getLastName() + "," +
                            s.getStudentId() + "," + s.getGrade() + "," + s.getAdvising() + "," + s.getAdvisingDate() + "," +
                			s.getUpperLevelCredits() + "," + s.getTotalCredits() + "," + s.getGradSubmissionDate() + "," +
                            s.getMajorGPA() + "," + s.getTotalGPA() + "," + s.getGradSubmit());
            }  
        
            out.close();
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    
    public void Add(Student a_student) {
        lList.add(a_student);
        writeStudentToFile(a_student, sDbFileName) ;
    }
    
    public boolean UpdateStudent(String a_sStudentId, String a_sNewValue, int a_iField) {
        
        boolean bRet = false;
        int i = 0;
        Iterator<Student> itr = lList.iterator();
        
        while(itr.hasNext()) {
            Student s = (Student)itr.next();
            
            if (s.getStudentId().equals(a_sStudentId)) {
                switch (a_iField) {
                    case 1: s.setFirstName(a_sNewValue); break;
                    case 2: s.setMiddleName(a_sNewValue); break;
                    case 3: s.setLastName(a_sNewValue); break;
                    case 4: s.setGrade(a_sNewValue); break;
                    case 5: s.setAdvising(Boolean.valueOf(a_sNewValue)); break;
                    case 6: s.setAdvisingDate(a_sNewValue); break;
                    default: break;
                } 
                
                lList.set(i, s);
                bRet = true;
                break;
            }
            
            i++;
        } 
        
        writeStudentListToFile(sDbFileName);
        
        return bRet ;
    }
    
    public void PrintList() {
        Iterator<Student> itr = lList.iterator();
        
        System.out.println("Student list: " + lList.size()); 
        while(itr.hasNext()) {
            System.out.println(itr.next().toString());            
        }        
    }
}

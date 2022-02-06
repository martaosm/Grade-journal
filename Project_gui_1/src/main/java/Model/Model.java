/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author marta
 */
public class Model extends Exception{
    
    private String subjectName;
    
    private ArrayList<Integer> grades;
 
    interface Function{
        double operation(double a,double b);
    }
    double operate(double a,double b,Function x){
        return x.operation(a, b);
    }
    
    public Model(){}
    
    Model(String _name, ArrayList<Integer> _grades){
        this.subjectName = _name;
        this.grades = _grades;
    }
    
    /**
     * returns subject's name
     * @return name of a subject
     */
    public String getSubjectName(){
        return this.subjectName;
    }
    
    public String ret(String val){
        return val;
    }
    /**
     * sets given argument as name of a subject
     * @param name name if a subject
     * @throws Model.WrongValueException
     */
    public void setSubjectName(String name)throws WrongValueException{
        if(name == null || name.length()==0){
            throw new WrongValueException("Wrong value!"); 
            
        }
        if(Character.isUpperCase(name.charAt(0)) == false){
            throw new WrongValueException("Wrong value!");
        }
        else{
            this.subjectName = name;
        }      
    }
    
    /**
     * returns arary list of grades
     * @return array list of grades on a subject
     */
    public ArrayList getGrades(){
        return this.grades;
    }
    
    /**
     * sets array list of grades
     * @param grades array list of grades on a subject
     */
    public void setGrades(ArrayList grades){
        this.grades = new ArrayList<Integer>();
        this.grades = grades;   
    }
    
    /**
     * returns size of array list
     * @return array list size
     */
    public int getGradesSize(){
        return this.grades.size();
    }
    
    /**
     * returns single grade from an array list
     * @param i place in an array list of a single grade
     * @return single grade
     * @throws Model.WrongValueException
     */
    public int getGrade(int i) throws WrongValueException, IOException{
        try{
            FileWriter writer = new FileWriter("C:\\exceptions.txt");
        }catch (IOException ex){
            ex.printStackTrace();
        }
        /**
         * holds string value of grade argument
         */
        if(i>=0 && i<this.grades.size()){
            String j;
            j = String.valueOf(this.grades.get(i));
        /**
         * variable j1 holds casted to int variable j
         */
        int j1 = Integer.parseInt(j);
        return j1;
        }else if(i>this.grades.size()){
            throw new WrongValueException("Wrong value!");
        }
        else{
            throw new WrongValueException("Wrong value!");
        }
    }
    
    /**
     * adds grade to an array list, if caught throws exception
     * @param x new grade 
     * @throws Model.WrongValueException wrong value exception
     */
    public void addGrade(int x)throws WrongValueException{
        if(x<=0 || x>6){
            
            throw new WrongValueException("Ocena nie może być większa od 6 i mniejsza lub równa zero");
        }
        else{
            this.grades.add(x);
        }
    }
    /**
     * returns average grade in set format
     * @param avg
     * @return average grade on a subject
     */
    public String formatAvg(double avg){
        String formatter = "#.##";
        DecimalFormat df = new DecimalFormat(formatter);
        String form = df.format(avg);
        return form;
    }
    
    /**
     * calculates and sets average grade
     * @return average grade
     */
    public double calculateAvg(){
        Model object = new Model();
        Function divide = (a,b) -> (a/b);
        /**
         * holds string value of grade argument
         */
        String j;
        /**
         * variable j1 holds casted to int variable j
         */
        int j1; 
        double avg1=0;
        for(int num=0; num<this.grades.size(); num++){
            j = String.valueOf(this.grades.get(num));
            j1 = Integer.parseInt(j);
            avg1 = avg1+ j1;
        }
        double size = (double)this.grades.size(); 
        double avg;
        avg = object.operate(avg1, size, divide);
        return avg;
    }
}

package UnitTest;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import Model.Model;
import Model.WrongValueException;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Arrays;
//import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author marta
 */
public class ModelTest {
    
    private Model testObject;
    
    /**
     * interface for Lambda expression
     */
    /*interface Function{
        double operation(double a,double b);
    }
    double operate(double a,double b,Function x){
        return x.operation(a, b);
    }*/
    
    @BeforeEach
    public void setUp() {
        testObject = new Model();
    }
    
    /**
     * Parameterized test for checking if given name of subject is correct
     * @param name 
     */
    @ParameterizedTest
    @ValueSource(strings={"Nazwa","nazwa","","fizyka","Chemia","biologia"})
    public void testSetSubjectName(String name){
        if(name.length()>0 && Character.isUpperCase(name.charAt(0)) == true){
            try{
                testObject.setSubjectName(name);   
            } catch(WrongValueException e){
                fail("Given value is correct!!!");
            }
        }
        else{
            try{
                testObject.setSubjectName(name); 
                fail("Wrong value!!! First letter must be in uppercase!!!");  
            } catch(WrongValueException e){            
            }
        }
        if(name == null || name.length()==0)
        {
            try{
                testObject.setSubjectName(name); 
                fail("Wrong value!!! Name can't be a null!!!");  
            } catch(WrongValueException e){            
            }
        }
    }
    
    /**
     * Parameterized Test for checking if method returning single grade works fine
     * @param place
     * @throws WrongValueException 
     */
    @ParameterizedTest
    @ValueSource(ints={2,3,4,-1,2,-4,-5,-2,3})
    public void testGetGrade(int place) throws WrongValueException, IOException{
        ArrayList<Integer> testGrades1 = new ArrayList<Integer>();
        Stream.of(4,5,4,3,2,5)
                .forEach(n -> testGrades1.add(n));
        testObject.setGrades(testGrades1);
        if(place<0)
        {
            try{
                testObject.getGrade(place);
                fail("An exception should be thrown when the number is negative");
            }catch(WrongValueException e){
            }
        }else if(place>testGrades1.size()){
            try{
                testObject.getGrade(place);
                fail("Error");
            }catch(WrongValueException e){
            }
        }else{
            try{
                testObject.getGrade(place);
            }catch(WrongValueException e){
                fail("Given value is correct!!!");
            }
        }
    }
    
    /**
     * Parameterized test for checking if given grade to add is a correct value
     * @param number
     * @throws WrongValueException 
     */
    @ParameterizedTest
    @ValueSource(ints={4,8,9,10,-2,-10,5,6,2,-7})
    public void testAddGrade(int number) throws WrongValueException{
        ArrayList<Integer> testGrades1 = new ArrayList<>();
        Stream.of(3,4,4)
                .forEach(n -> testGrades1.add(n));
        testObject.setGrades(testGrades1);
        if(number<=0 || number>6)
        {
            try{
                testObject.addGrade(-4);
                fail("Wrong value!!!");
            }catch(WrongValueException e){
        }
        }else if(number>0 && number<=6)
        {
            try{
                testObject.addGrade(4);
            }catch(WrongValueException e){
                fail("Given value is correct!!!");
            }
        }
    }
    
    /**
     * Test for checking if method responsible for calcultong average grade is returning a correct value
     * @throws WrongValueException 
     */
    @Test
    public void testCalculateAvg() throws WrongValueException{
        //Model object = new Model();
        //Function divide1 = (a,b) -> (a/b);
        int[] tab = new int[]{4,5,5,4,2};
        ArrayList<Integer> testGrades1 = new ArrayList<>();
        Stream.of(3,4,4)
                .forEach(n -> testGrades1.add(n));
        testObject.setGrades(testGrades1);
        for (int x : tab) {
            testObject.addGrade(x);
        }
        int sum=0;
        for(int i=0;i<5;i++){
            sum += tab[i];
        }
        sum = sum+11;
        double wantedResult;
        double s=(double)sum;
        //wantedResult = object.operate(s,8.0, divide1);
        wantedResult = s/8;
        double result=testObject.calculateAvg();
        assertEquals(wantedResult, result,"Values are not the same");
    }
    
    /**
     * Test for checking a method which returns an average grade in a correct format 
     * @param number 
     */
    @ParameterizedTest
    @ValueSource(doubles={3.45})
    public void testFormatAvg(double number){
        String result = testObject.formatAvg(number);
        String wantedResult = "3,45";
        assertEquals(wantedResult, result,"Values are not the same");
    }
}

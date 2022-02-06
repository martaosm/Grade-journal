/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author marta
 */
public class Subjects {
    private final List<Model> data;
    ArrayList<Integer> polski = new ArrayList<Integer>(Arrays.asList(3,3,3,3,4,4,2));
    ArrayList<Integer> matematyka = new ArrayList<Integer>(Arrays.asList(4,4,5,5,5,4,3));
    ArrayList<Integer> angielski = new ArrayList<Integer>(Arrays.asList(5,5,5,5,5,5));
    ArrayList<Integer> chemia = new ArrayList<Integer>(Arrays.asList(4,4,4,3,3,4,5,2,6));
    ArrayList<Integer> fizyka = new ArrayList<Integer>(Arrays.asList(5,4,4,5,4,3,3));
    public Subjects(){
        
        data = new ArrayList<Model>();
        data.add(new Model("Polski", polski));
        data.add(new Model("Matematyka", matematyka));
        data.add(new Model("Angielski", angielski));
        data.add(new Model("Chemia", chemia));
        data.add(new Model("Fizyka", fizyka));
    }
    
    public List<Model> getData(){
        return data;
    }
}

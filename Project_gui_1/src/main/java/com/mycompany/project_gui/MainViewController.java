/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.project_gui;

import Model.Model;
import Model.Subjects;
import Model.WrongValueException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marta
 */
public class MainViewController {

    @FXML
    private AnchorPane background;
    @FXML
    private TableView<Model> table;
    @FXML
    private TableColumn<Model,String> subjectsName;
    //@FXML
    //private TableColumn<Model,String> Grades;
    @FXML
    private Label gradesDisplay;
    @FXML
    private Label avgDisplay;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Button showButton;
    @FXML 
    private Button addButton;
    @FXML
    private TextField addField;
    
    String exception;

    Model model;
    
    private final ObservableList<Model> data;
    private final Subjects subjects;
    
    
    /*ViewController(Model model){
        this.model = model;
    } */  
    
    public MainViewController(Subjects subjects){
        this.subjects = subjects;
        data = FXCollections.observableArrayList(subjects.getData());
        
    }
    
    @FXML
    private void showGrades(ActionEvent event) throws FileNotFoundException{
        String display="";
        String j;
        int j1;
        int index = table.getSelectionModel().getSelectedIndex();
        for(int num=0; num<data.get(index).getGradesSize(); num++){
            j = String.valueOf(data.get(index).getGrades().get(num));
            j1 = Integer.parseInt(j);
            display = display + j1 + " ";
        }
        double avg;
        avg = data.get(index).calculateAvg();
        String form;
        form = data.get(index).formatAvg(avg);
        avgDisplay.setText(form);
        gradesDisplay.setText(display);
        //PrintWriter writer = new PrintWriter("C:\\exceptions.txt");
            //writer.write(avgDisplay.getText());
            //writer.close();
    }
    
    @FXML
    public void initialize() {
        background.setStyle("-fx-background-color: #b4d3b2;");
        addField.setStyle("-fx-background-color:#a1c89e; -fx-text-fill:white");
        table.setStyle("-fx-background-color: #a1c89e;");
        //subjectsName.setStyle("-fx-background-color: #a1c89e; -fx-text-fill:white");
        addButton.setStyle("-fx-background-color:#a1c89e; -fx-text-fill:white");
        showButton.setStyle("-fx-background-color:#a1c89e; -fx-text-fill:white");
        label1.setStyle("-fx-text-fill:white");
        label2.setStyle("-fx-text-fill:white");
        label3.setStyle("-fx-text-fill:white");
        gradesDisplay.setStyle("-fx-text-fill:white");
        avgDisplay.setStyle("-fx-text-fill:white");
        table.setItems(data);
        subjectsName.setCellValueFactory(new PropertyValueFactory<Model, String>("subjectName"));
        //Grades.setCellValueFactory(new PropertyValueFactory<Model, String>("Grades"));
        //table.setEditable(true);
        subjectsName.setCellFactory(TextFieldTableCell.forTableColumn());
        subjectsName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Model, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Model,String> t) {
                try {
                    ((Model) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSubjectName(t.getNewValue());
                } catch (WrongValueException ex) {
                    ex.printStackTrace();
                    gradesDisplay.setText("Click Exception button to see what went wrong");
                    avgDisplay.setText("Click Exception button to see what went wrong");
                }
            }
        });
    }
    
    @FXML
    public void add(ActionEvent event) throws IOException, WrongValueException{
        String x = addField.getText();
        int _x= Integer.parseInt(x);
        int index = table.getSelectionModel().getSelectedIndex();
        try{
            data.get(index).addGrade(_x);
        }catch(WrongValueException e){
            gradesDisplay.setText("Click Exception button");
            avgDisplay.setText("Click Exception button");
            exception = e.toString();
        }
        addField.clear();
        
    }
    
    @FXML
    public void exceptionWindow(ActionEvent event) throws IOException{
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.setTitle("Program exception");
        final DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK);
        dialogPane.getButtonTypes().addAll(ButtonType.CLOSE);
        dialogPane.setContentText("Exceptions");
        dialog.initModality(Modality.APPLICATION_MODAL);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        
        /*try(Stream<String> stream = Files.lines(Paths.get("C:\\exceptions.txt"))){
            stream.forEach(System.out::println);
        }catch(IOException e){
            e.printStackTrace(pw);
            pw.close();
        }*/
        
        Label label = new Label("Exceptions: ");
        TextArea textArea = new TextArea(exception + "\n");
        textArea.setEditable(false);
        textArea.setWrapText(true);
        
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);
        
        GridPane root = new GridPane();
        root.setVisible(false);
        root.setMaxWidth(Double.MAX_VALUE);
        root.add(label,0,0);
        root.add(textArea,0,1);
        dialogPane.setExpandableContent(root);
        Optional<ButtonType> showAndWait = dialog.showAndWait();
    }
}
package com.mycompany.project_gui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import Model.Model;
import Model.Subjects;
import Model.WrongValueException;
//import View.View;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javafx.scene.paint.Color;
//import org.json.simple.*;
//import org.json.simple.parser.*;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Subjects subjects;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("mainView"), 640, 480);
        //scene.setFill(Color.web("#81c483"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setControllerFactory( p -> { return new MainViewController(subjects);} );
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        
        subjects = new Subjects();
        launch();
        subjects.getData().forEach(x -> {
            System.out.println(x.getSubjectName());
        });
    }

}
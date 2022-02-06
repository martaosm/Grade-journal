module com.mycompany.project_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.project_gui to javafx.fxml;
    exports com.mycompany.project_gui;
    exports Model;
}

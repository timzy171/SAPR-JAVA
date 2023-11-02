module com.example.saprbar {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires com.almasb.fxgl.all;

    opens com.example.saprbar to javafx.fxml;
    exports com.example.saprbar;
}
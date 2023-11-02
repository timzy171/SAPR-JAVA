package com.example.saprbar;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {
    public static void openAnotherScene(Node node, String path){
        node.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloController.class.getResource(path));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
}

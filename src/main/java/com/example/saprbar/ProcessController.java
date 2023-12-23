package com.example.saprbar;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.List;

public class ProcessController {
    @FXML
    private TextField fileNameField;

    @FXML
    private Button saveButton;

    @FXML
    private Button backButton;

    private static List<List<Double>> nodeInfo;
    @FXML
    void initialize(){
        backButton.setOnAction(actionEvent -> {
            SceneSwitcher.openAnotherScene(backButton,"hello-view.fxml");
        });
        saveButton.setOnAction(actionEvent -> {
            File constructionFile = new File("src/main/resources/" + fileNameField.getText() + ".cn");
            File loadsFile = new File("src/main/resources/" + fileNameField.getText() + ".ld");
            File resultFile = new File("src/main/resources/" + fileNameField.getText() + ".md");
            try {
                constructionFile.createNewFile();
                loadsFile.createNewFile();
                resultFile.createNewFile();

                FileWriterController.writeToFile(constructionFile);
                FileWriterController.writeToFile(loadsFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }
}

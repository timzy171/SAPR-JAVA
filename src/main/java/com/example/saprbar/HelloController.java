package com.example.saprbar;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelloController {

    @FXML
    private Button preProcessButton;

    @FXML
    private Button processButton;

    @FXML
    void initialize(){
        preProcessButton.setOnAction(actionEvent -> SceneSwitcher.openAnotherScene(preProcessButton,"preProcess.fxml"));
        processButton.setOnAction(actionEvent -> {SceneSwitcher.openAnotherScene(processButton,"process.fxml");});
    }

}
package com.example.saprbar;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelloController {

    @FXML
    private Button preProcessButton;

    @FXML
    void initialize(){
        preProcessButton.setOnAction(actionEvent -> SceneSwitcher.openAnotherScene(preProcessButton,"preProcess.fxml"));
    }

}
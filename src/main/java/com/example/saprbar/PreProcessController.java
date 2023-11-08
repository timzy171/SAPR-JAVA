package com.example.saprbar;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class PreProcessController {

    @FXML
    private TextArea nodeCoordinatesArea;

    @FXML
    private TextArea forceArea;

    @FXML
    private TextField nodeTextField;

    @FXML
    private Button saveButton;

    @FXML
    private CheckBox firstNodeBlock;

    @FXML
    private CheckBox lastNodeBlock;

    @FXML
    private Button backButton;
    @FXML
    void initialize(){
        backButton.setOnAction(actionEvent -> {
            SceneSwitcher.openAnotherScene(backButton,"hello-view.fxml");
        });
        saveButton.setOnAction(actionEvent -> {
            ImageController.setNodeCounter(Integer.parseInt(nodeTextField.getText()));
            ImageController.setNodeInfo(getParsedNodeInfoList());
            ImageController.setForces(getParsedForceList());
            ImageController.blocks.add(firstNodeBlock.isSelected());
            ImageController.blocks.add(lastNodeBlock.isSelected());
            SceneSwitcher.openAnotherScene(saveButton,"image.fxml");
        });

    }

    private List<List<Double>> getParsedNodeInfoList(){
        List<List<Double>> coordinates = new ArrayList<>();
        String[] split = nodeCoordinatesArea.getText().split("\n");
        for (String coord : split) {
            char[] buffer = coord.toCharArray();
            List<Double> xy = new ArrayList<>();
            StringBuilder value = new StringBuilder();
            for (int i = 0; i < buffer.length; ++i) {
                if (buffer[i] != ' ') {
                    value.append(buffer[i]);
                    if(i == buffer.length - 1){
                        xy.add(Double.parseDouble(value.toString()));
                    }
                }
                else{
                    if(!value.isEmpty()){
                        xy.add(Double.parseDouble(value.toString()));
                        value = new StringBuilder();
                    }
                }
            }
            coordinates.add(new ArrayList<>(List.of(xy.get(0),xy.get(1))));
        }
        return coordinates;
    }

    private List<List<Double>> getParsedForceList(){
        List<List<Double>> forces = new ArrayList<>();
        String[] split = forceArea.getText().split("\n");
        for (String force : split) {
            char[] buffer = force.toCharArray();
            List<Double> currentForces = new ArrayList<>();
            StringBuilder value = new StringBuilder();
            for (int i = 0; i < buffer.length; ++i) {
                if (buffer[i] != ' ') {
                    value.append(buffer[i]);
                    if(i == buffer.length - 1){
                        currentForces.add(Double.parseDouble(value.toString()));
                    }
                }
                else{
                    if(!value.isEmpty()){
                        currentForces.add(Double.parseDouble(value.toString()));
                        value = new StringBuilder();
                    }
                }
            }
            try {
                forces.add(new ArrayList<>(List.of(currentForces.get(0),
                        currentForces.get(1), currentForces.get(2))));
            }
            catch (Exception ignored){
            }
        }
        return forces;
    }
}

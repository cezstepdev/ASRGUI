package sample;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javax.sound.sampled.AudioSystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
    @FXML
    private static Pane list;
    @FXML
    private TextField post;
    @FXML
    private TextField serwer;
    @FXML
    private TextField port;

    private static ComboBox<String> inputDevices;
    private static ComboBox<String> outputDevices;

    public void writeToFile(int inputDeviceIndex, int outputDeviceIndex) {
        String fpost = post.getText();
        String fport = port.getText();
        String fserver = serwer.getText();

        //default values
        if(fpost.equals(""))
            fpost = "1";
        if(fport.equals(""))
            fport = "1";
        if(fserver.equals(""))
            fserver = "console.mysocialseller.com/";

        SaveToFile saveToFile = new SaveToFile(fserver,fport,fpost,inputDeviceIndex,outputDeviceIndex);
        saveToFile.modify();
        System.out.println("saved!");
    }

    //Submit Button onClick function
    public void click() {
        String inputChoose;
        String outputChoose;
        int inputDeviceIndex;
        int outputDeviceIndex;

        if(!(inputChoose = inputDevices.getValue()).equals("") && !(outputChoose = outputDevices.getValue()).equals(""))
        {
            inputDeviceIndex = getDeviceIndex(inputChoose);
            outputDeviceIndex = getDeviceIndex(outputChoose);
            writeToFile(inputDeviceIndex,outputDeviceIndex);
        }
    }

    public int getDeviceIndex(String name) {
        for(int i=0;i<AudioSystem.getMixerInfo().length;i++)
            if(AudioSystem.getMixerInfo()[i].getName().contains(name))
                return i;
        return -1;
    }

    private void addToList(ComboBox<String> devicesList, List<String> devices) {
        for(String device : devices)
            devicesList.getItems().add(device);
    }

    void prepareList(Parent root) {
        //combobox
        inputDevices = new ComboBox<>();
        outputDevices = new ComboBox<>();

        //adding devices to combobox
        List<String> listOfInput = getInputDevices();
        List<String> listOfOutput = getOutputDevices();
        addToList(inputDevices,listOfInput);
        addToList(outputDevices,listOfOutput);

        //adding combobox to pane
        list = new Pane();
        list.getChildren().add(inputDevices);
        list.getChildren().add(outputDevices);
        ((Pane) root).getChildren().add(list);
    }

    public void setComboBox() {
        inputDevices.setValue(inputDevices.getItems().get(0));
        inputDevices.setLayoutX(20);
        inputDevices.setLayoutY(40);
        outputDevices.setValue(outputDevices.getItems().get(0));
        outputDevices.setLayoutX(20);
        outputDevices.setLayoutY(inputDevices.getHeight() + 50);
    }

    private List<String> getInputDevices() {
        List<String> devices = new ArrayList<>();
        for(int i=0;i<AudioSystem.getMixerInfo().length;i++) {
            String device = AudioSystem.getMixerInfo()[i].getName();
            if(device.toLowerCase().contains("input") || device.toLowerCase().contains("mikrofon")) {
                devices.add(device);
            }
        }
        return devices;
    }

    private List<String> getOutputDevices() {
        List<String> devices = new ArrayList<>();
        for(int i=0;i<AudioSystem.getMixerInfo().length;i++) {
            String device = AudioSystem.getMixerInfo()[i].getName();
            if(device.toLowerCase().contains("output") || device.toLowerCase().contains("głośnik")) {
                devices.add(device);
            }
        }
        return devices;
    }
}

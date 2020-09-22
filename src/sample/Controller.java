package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javax.sound.sampled.AudioSystem;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField post;
    @FXML
    private TextField server;
    @FXML
    private TextField port;
    @FXML
    private VBox textInput;


    @FXML
    private ComboBox<String> inputDevices;
    @FXML
    private ComboBox<String> outputDevices;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(textInput != null)
        {
            if(inputDevices != null && outputDevices != null)
            {
                prepareList();
                setComboBox();
            }
        }
    }

    public void writeToFile(int inputDeviceIndex, int outputDeviceIndex) {
        String fpost = post.getText();
        String fport = port.getText();
        String fserver = server.getText();

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

    void prepareList() {
        //adding devices to combobox list
        List<String> listOfInput = getInputDevices();
        List<String> listOfOutput = getOutputDevices();
        addToList(inputDevices,listOfInput);
        addToList(outputDevices,listOfOutput);
    }

    public void setComboBox() {
        inputDevices.setValue(inputDevices.getItems().get(0));
        outputDevices.setValue(outputDevices.getItems().get(0));
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

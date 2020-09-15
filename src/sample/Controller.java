package sample;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javax.sound.sampled.AudioSystem;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private static Pane list;
    @FXML
    private TextField stanowisko;
    @FXML
    private TextField serwer;
    @FXML
    private TextField scenariusz;

    private static ComboBox<String> inputDevices;
    private static ComboBox<String> outputDevices;

    public void writeToFile(String inputChoose, String outputChoose) {
        String fstanowisko = stanowisko.getText();
        String fscenariusz = scenariusz.getText();
        String fserwer = serwer.getText();

        if(fstanowisko.equals(""))
            fstanowisko = "1";
        if(fscenariusz.equals(""))
            fscenariusz = "1";
        if(fserwer.equals(""))
            fserwer = "console.mysocialseller.com/";

        SaveToFile saveToFile = new SaveToFile();
        //saveToFile.saveConfig(fstanowisko + "\n" + fserwer + "\n" + fscenariusz + "\n" + inputChoose + "\n" + outputChoose);
        saveToFile.modify(fserwer,fscenariusz,fstanowisko);
        System.out.println("saved!");
    }

    public void click() {
        String inputChoose;
        String outputChoose;

        if(!(inputChoose = inputDevices.getValue()).equals("") && !(outputChoose = outputDevices.getValue()).equals(""))
            writeToFile(inputChoose,outputChoose);
    }

    void addList(Parent root) {
        inputDevices = new ComboBox<>();
        outputDevices = new ComboBox<>();

        addToList(inputDevices,getInputDevices());
        addToList(outputDevices,getOutputDevices());

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

    private void addToList(ComboBox<String> devicesList, List<String> devices) {
        for(String device : devices)
            devicesList.getItems().add(device);
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

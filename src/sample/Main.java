package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Controller controller = new Controller();
        controller.prepareList(root);

        primaryStage.setTitle("ASR Configuration");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        controller.setComboBox();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        System.out.println("loader: " + loader);
//        loader.setController(new Controller());
        Parent root = loader.load();
        System.out.println("parent: " + root);
        System.out.println("");
        primaryStage.setTitle("Shape Dragging");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
        
    }


    public static void main(String[] args) {
        launch(args);
    }
}

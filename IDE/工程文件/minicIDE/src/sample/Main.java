package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application {

    Controller myController;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader myfxmlLoader=new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root =myfxmlLoader.load();
        Controller fxmlcontroller =myfxmlLoader.getController();
        myController=fxmlcontroller;
        primaryStage.setTitle("英英英IDE");
        Scene myScene=new Scene(root,900,600);
        primaryStage.setScene(myScene);
        myScene.getStylesheets().add(getClass().getResource("font.css").toExternalForm());
        primaryStage.getIcons().add(new Image("file:d:\\minicIDE\\1.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

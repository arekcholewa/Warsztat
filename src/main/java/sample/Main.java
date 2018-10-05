package sample;

import databse.AutoService;
import databse.PartCategoryService;
import databse.PartService;
import databse.WarsztatService;
import entity.Auto;
import entity.Part;
import entity.PartCategory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();


        System.out.println( new WarsztatService().getAllWarsztat().get(0).getMiejscowosc());

    }


    public static void main(String[] args) {
        launch(args);
    }
}

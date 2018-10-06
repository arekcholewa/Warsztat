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
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main extends Application {

    private Rectangle2D rect;
    private  String firstName;

    @Override
    public void init() {
//
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Warsztat Manager");
        Rectangle2D rect = Screen.getPrimary().getVisualBounds();
        primaryStage.setScene(new Scene(root));
        primaryStage.setX((rect.getWidth() - 100) / 2.0);
        primaryStage.setY(rect.getHeight() - 100 / 2.0);
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.requestFocus();
        System.out.println(Screen.getPrimary().getVisualBounds());
        System.out.println(Screen.getPrimary().getDpi());
        System.out.println(primaryStage.hasProperties());

        // new PartService().getAllParts();
        // new AutoService().getAllCars();
    }

    @Override
    public void stop(){



    }

    public static void main(String[] args) {
        launch(args);
    }
}

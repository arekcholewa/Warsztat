package sample;


import databse.DatabseConnection;
import databse.WarsztatService;
import entity.Warsztat;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javafx.geometry.Rectangle2D;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Interface {

    Main main = new Main();
    private Connection connection;
    private Statement statement;
    private ResultSet result;
    private  String firstName;

    @FXML
    private AnchorPane anchor;

    @FXML //combobox wyboru itemu
    private ComboBox<String> cSelectItem; //bedzie sparametryzowany inaczej

    @FXML //combobox wyboru lokalizacji
    private ComboBox<String> cSelectLocation;

    @FXML //przycik zaloguj
    private Button bLogin;

    @FXML //przycik OK w oknie dodawania itemu
    private Button bAddOK;

    @FXML //przycisk dodaj do bazy
    private Button bAdd;

    @FXML //przycisk usun z bazy
    private Button bRemove;

    @FXML //przycisk szukaj w bazie
    private Button bSearch;

    @FXML //pole wpisania loginu
    private TextField tUsername;

    @FXML //pole wpisania hasla
    private PasswordField tPassword;

    @FXML //etykieta wyswietlajaca status polaczenia
    private Label lconnectionStatus;

    @FXML //checkbox ustalajacy haslo i login na default
    private CheckBox checkDefault;


    @FXML //logowanie sie do bazy - JESZCZE NIE DZIALA - LOGOWANIE W KLASIE
    public void loginToDatabase() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        if(!tUsername.getText().isEmpty() && !tPassword.getText().isEmpty()) {


            Class.forName(DatabseConnection.getDBDRIVER()).newInstance();
            connection = DriverManager.getConnection(DatabseConnection.getURL(),
                    DatabseConnection.getUSER(),
                    DatabseConnection.getPASSWORD());
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("Select * FROM Warsztat");



//            while (result.next()){
//                warsztats.add( new Warsztat(result.getLong(ID),
//                        result.getString(ULICA),
//                        result.getString(MIEJSCOWOSC)
//                ));
//            }

            bAdd.setDisable(false);
            bRemove.setDisable(false);
            bSearch.setDisable(false);

            lconnectionStatus.setTextFill(Color.GREEN);
            lconnectionStatus.setText("Database status: Connected");
        }
    }


    @FXML //sprawdzenie stanu checkboxa - logowanie domyslne
    public void isDefaultSelected(){
        if(checkDefault.isSelected()){
            tUsername.setText("user1");
            tPassword.setText("user1");
        }
    }

    @FXML //zamkniecie okna glownego - przycisk cancel
    public void closeWindow(){
        Stage stage = (Stage) anchor.getScene().getWindow();
        stage.close();
        if (result != null) {
            try {
                result.close();
            } catch (SQLException e) {System.out.println("rozlaczono");}
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) { System.out.println("rozlaczono");}
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) { System.out.println("rozlaczono");}
        }
    }

    @FXML  // dodawanie do bazy / przycik add
    public void addToDatabase(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/add.fxml"));
            Stage addingStage = new Stage();
            Rectangle2D rect = Screen.getPrimary().getVisualBounds();
            addingStage.setTitle("Add an entity to database");
            addingStage.setScene(new Scene(root));
            addingStage.setX(rect.getWidth() / 2 +(rect.getWidth() / 8));
            addingStage.setY(rect.getHeight() / 2);
            addingStage.requestFocus();
            addingStage.show();



        } catch (IOException exc){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window");
            exc.printStackTrace();
        }
    }

    @FXML  //co sie dzieje po wybraniu checkboxa select item
    public void selectItem(){



    }

    @FXML //dzilanie przycisku OK w panelu dodawanie itemu
    public void confirmAdding(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/addCar.fxml"));
            Stage carStage = new Stage();
            Rectangle2D rect = Screen.getPrimary().getVisualBounds();
            carStage.setTitle("Add car to database");
            carStage.setScene(new Scene(root));
            carStage.setX(rect.getWidth() / 2 +(rect.getWidth() / 4));
            carStage.setY(rect.getHeight() / 2 - (rect.getHeight() / 8));
            carStage.requestFocus();
            carStage.show();



        } catch (IOException exc){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window");
            exc.printStackTrace();
        }
    }

    @FXML  //co sie dzieje po wybraniu checkboxa select location
    public void selectLocation(){

    }

    @FXML //przycisk usun z bazy
    public void removeFromDatabase(){

    }

    @FXML //przycisk szukaj w bazie
    public void searchInDatabase(){

    }


}

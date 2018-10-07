package sample;


import databse.DatabseConnection;
import databse.PartService;
import databse.WarsztatService;
import entity.Auto;
import entity.Part;
import entity.Warsztat;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

;

public class Interface {

    Main main = new Main();
    DatabseConnection db = new DatabseConnection();
    private Connection connection;
    private Statement statement;
    private ResultSet result;
    private String firstName;
    private String user, password;
    private ObservableList<Auto> listaAut;
    private ObservableSet<CheckBox> selectedCheckBoxes = FXCollections.observableSet();
    private ObservableSet<CheckBox> unselectedCheckBoxes = FXCollections.observableSet();
    private IntegerBinding numCheckBoxesSelected = Bindings.size(selectedCheckBoxes);
    private final int maxNumSelected =  1;



    @FXML
    private AnchorPane anchor;

    @FXML //combobox wyboru itemu
    private ComboBox<String> cSelectItem; //bedzie sparametryzowany inaczej

    @FXML //combobox wyboru lokalizacji
    private ComboBox<String> cSelectLocation;

    @FXML //przycik zaloguj
    private Button bLogin;

    @FXML
    private Button bDodaj;

    @FXML
    private Button bUsun;

    @FXML
    private Button bSzukaj;

    @FXML
    private CheckBox chCars;

    @FXML
    private CheckBox chParts;

    @FXML
    private CheckBox chWarsztats;

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

    @FXML
    private TableView<Part> tableContent;

    @FXML
    private ComboBox<String> cbWybierzWarsztat;

//    public void checkNumberOfCheckBoxes(){
//
//        numCheckBoxesSelected.addListener((obs, oldSelectedCount, newSelectedCount) -> {
//            if (newSelectedCount.intValue() >= maxNumSelected) {
//                unselectedCheckBoxes.forEach(cb -> cb.setDisable(true));
//                bDodaj.setDisable(true);
//            } else {
//                unselectedCheckBoxes.forEach(cb -> cb.setDisable(false));
//                bDodaj.setDisable(true);
//            }
//        });
//    }
//
//    private void configureCheckBox(CheckBox checkBox) {
//
//        if (checkBox.isSelected()) {
//            selectedCheckBoxes.add(checkBox);
//        } else {
//            unselectedCheckBoxes.add(checkBox);
//        }
//
//        checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
//            if (isNowSelected) {
//                unselectedCheckBoxes.remove(checkBox);
//                selectedCheckBoxes.add(checkBox);
//            } else {
//                selectedCheckBoxes.remove(checkBox);
//                unselectedCheckBoxes.add(checkBox);
//            }
//
//        });
//
//    }

    @FXML //logowanie sie do bazy - JESZCZE NIE DZIALA - LOGOWANIE W KLASIE
    public void loginToDatabase() {

        if (!tUsername.getText().isEmpty() && !tPassword.getText().isEmpty()) {

            user = tUsername.getText();
            password = tPassword.getText();

            db.connectToDatabase();

//            if (db.isDatabaseConnected())
//                lconnectionStatus.setTextFill(Color.GREEN);
//                lconnectionStatus.setText("Database status: Connected");
//            } else {
//                lconnectionStatus.setTextFill(Color.RED);
//                lconnectionStatus.setText("Database status: Disconnected");
//            }

        }



        try {
            Parent root = FXMLLoader.load(getClass().getResource("/content.fxml"));
            Stage contentStage = new Stage();
            Rectangle2D rect = Screen.getPrimary().getVisualBounds();
            contentStage.setTitle("Content Pane");
            contentStage.setScene(new Scene(root));
            contentStage.setX(rect.getWidth() / 2 - ((rect.getWidth()) / 3));
            contentStage.setY(rect.getHeight() / 2 - (rect.getHeight() / 4));
            contentStage.requestFocus();
            contentStage.show();

        } catch (IOException exc) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window");
            exc.printStackTrace();
        }

    }


    @FXML
    public void addToDatabase() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/addCar.fxml"));
            Stage carStage = new Stage();
            Rectangle2D rect = Screen.getPrimary().getVisualBounds();
            carStage.setTitle("Dodaj samochod do bazy");
            carStage.setScene(new Scene(root));
            carStage.setX(rect.getWidth() / 2);
            carStage.setY(rect.getHeight() / 2 - (rect.getHeight() / 4));
            carStage.requestFocus();
            carStage.show();

        } catch (IOException exc) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window");
            exc.printStackTrace();
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

    ///////////////////////////////////////////////////////////////////////

    // okno contentowe

    ///////////////////////////////////////////////////////////////////////

    @FXML
    public void isCarsSelected(){
        if(chCars.isSelected()) {
            // AutoService autoService = new AutoService();
//            ObservableList<Auto> listaSamochodow = new AutoService().getAllCars();
//
//            tableContent.setItems(listaSamochodow);
//
//            TableColumn<Auto, Long> id = new TableColumn<Auto, Long>("ID");
//            id.setCellValueFactory(new PropertyValueFactory<Auto, Long>("id"));
//
//            TableColumn<Auto, Long> warsztatId = new TableColumn<Auto, Long>("ID Warsztatu");
//            warsztatId.setCellValueFactory(new PropertyValueFactory<Auto, Long>("warsztatId"));
//
//            TableColumn<Auto, String> marka = new TableColumn<Auto, String>("Marka");
//            marka.setCellValueFactory(new PropertyValueFactory<Auto, String>("marka"));
//
//            TableColumn<Auto, String> model = new TableColumn<Auto, String>("Model");
//            model.setCellValueFactory(new PropertyValueFactory<Auto, String>("model"));
//
//            TableColumn<Auto, String> nrRej = new TableColumn<Auto, String>("Nr tablicy");
//            nrRej.setCellValueFactory(new PropertyValueFactory<Auto, String>("nrRej"));
//
//            tableContent.getColumns().setAll(id, warsztatId, marka, model, nrRej);

        }
    }

    @FXML
    public void isPartsSelected(){
        if(chParts.isSelected()){
            ObservableList<Part> listaCzesci = (ObservableList<Part>) new PartService().getAllParts();

            tableContent.setItems(listaCzesci);

            TableColumn<Part, Long> id = new TableColumn<Part, Long>("ID");
            id.setCellValueFactory(new PropertyValueFactory<Part, Long>("id"));

            TableColumn<Part, Long> categoryID = new TableColumn<Part, Long>("Category ID");
            categoryID.setCellValueFactory(new PropertyValueFactory<Part, Long>("categoryID"));

            TableColumn<Part, Long> warsztatId = new TableColumn<Part, Long>("warsztat ID");
            warsztatId.setCellValueFactory(new PropertyValueFactory<Part, Long>("warsztatId"));

            TableColumn<Part, String> producent = new TableColumn<Part, String>("Producent");
            producent.setCellValueFactory(new PropertyValueFactory<Part, String>("producent"));

            TableColumn<Part, String> model = new TableColumn<Part, String>("Model");
            model.setCellValueFactory(new PropertyValueFactory<Part, String>("model"));

            TableColumn<Part, Integer> półka = new TableColumn<Part, Integer>("Półka");
            półka.setCellValueFactory(new PropertyValueFactory<Part, Integer>("pólka"));

            TableColumn<Part, Integer> miejsce = new TableColumn<Part, Integer>("Miejsce");
            miejsce.setCellValueFactory(new PropertyValueFactory<Part, Integer>("miejsce"));

            TableColumn<Part, Integer> rząd = new TableColumn<Part, Integer>("Rząd");
            rząd.setCellValueFactory(new PropertyValueFactory<Part, Integer>("rząd"));

            TableColumn<Part, String> DOT = new TableColumn<Part, String>("DOT");
            DOT.setCellValueFactory(new PropertyValueFactory<Part, String>("DOT"));

            TableColumn<Part, String> bieznik = new TableColumn<Part, String>("Bieznik");
            bieznik.setCellValueFactory(new PropertyValueFactory<Part, String>("bieznik"));

            tableContent.getColumns().setAll(id, categoryID, warsztatId, producent, model, półka, miejsce, rząd, DOT, bieznik);


        }
    }

    @FXML
    public void isWarsztatsSelected(){
        if(chWarsztats.isSelected()) {
            //  tableContent.setItems((Warsztat) listaWarsztatow);
//            TableColumn<Warsztat, Long> id = new TableColumn<Warsztat, Long>("ID");
//            id.setCellValueFactory(new PropertyValueFactory<Warsztat, Long>("id"));
//
//            TableColumn<Warsztat, String> miejscowosc = new TableColumn<Warsztat, String>("Miejscowosc");
//            miejscowosc.setCellValueFactory(new PropertyValueFactory<Warsztat, String>("miejscowosc"));
//
//            TableColumn<Warsztat, String> ulica = new TableColumn<Warsztat, String>("Ulica");
//            ulica.setCellValueFactory(new PropertyValueFactory<Warsztat, String>("ulica"));
//
//
//            tableContent.getColumns().setAll(id, ulica, miejscowosc);
        }
    }


    @FXML //przycisk usuwajacy z bazy
    public void deleteFromDatabase(){


        // tableContent.getItems().remove(tableContent.getSelectionModel().getSelectedCells());

    }

    @FXML //dane do comboboxa o warsztatach
    public void selectWarsztat(){
//        WarsztatService warsztatService = new WarsztatService();
//        ObservableList<Warsztat> listaWarsztatow = warsztatService.getAllWarsztat();
//
//        for(int i = 0; i < listaWarsztatow.size(); i++) {
//           Warsztat warsztat = listaWarsztatow.get(i);
//
//            System.out.println(warsztat.getId());
//            System.out.println(warsztat.getMiejscowosc());
//            System.out.println(warsztat.getUlica());
//
//        }


    }



    ////////////////////////////////////////////////////////////////////////


    @FXML  //co sie dzieje po wybraniu checkboxa select item
    public void selectItem(){
        WarsztatService warsztatService = new WarsztatService();
        ObservableList<Warsztat> listaWarsztatow = (ObservableList<Warsztat>) warsztatService.getAllWarsztat();
        for(int i = 0; i < listaWarsztatow.size(); i++) {
            Warsztat warsztat = listaWarsztatow.get(i);
            System.out.println(warsztat.getId());
            System.out.println(warsztat.getMiejscowosc());
            System.out.println(warsztat.getUlica());

        }

//            System.out.println("warsztaty: " +listaWarsztatow.size());
//
//            AutoService autoService = new AutoService();
//            List<Auto> cars  = autoService.getAllCars();
//            System.out.println(cars.size());
//            for(int i = 0; i < cars.size(); i++){
//                Auto auto = cars.get(i);
//                System.out.println(auto.getId());
//                System.out.println(auto.getWarsztatId());
//                System.out.println(auto.getMarka());
//                System.out.println(auto.getModel());
//                System.out.println(auto.getNrRej());
//
//            }
//
//            System.out.println("auta: " +cars.size());
//
//            PartService partService = new PartService();
//            List<Part> listaCzesci = partService.getAllParts();
//            for(int i=0; i < listaCzesci.size(); i++){
//                Part part = listaCzesci.get(i);
//                System.out.println(part.getId());
//                System.out.println(part.getWarsztatId());
//                System.out.println(part.getProducent());
//                System.out.println(part.getModel());
//                System.out.println(part.getBieznik());
//                System.out.println(part.getDOT());
//                System.out.println(part.getRząd());
//                System.out.println(part.getPólka());
//                System.out.println(part.getMiejsce());
//            }
//
//            System.out.println("czesci: " +listaCzesci.size());


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

    public String getUser(){
        return this.user;
    }

    public String getPassword(){
        return this.password;
    }


}

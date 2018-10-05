package databse;

import entity.Auto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AutoService {

    private Connection connection;
    private Statement statement;
    private final String ID = "autoId";
    private final String WARSZTAT_ID = "warsztatId";
    private final String MARKA = "auto_marka";
    private final String MODEL = "auto_model";
    private final String REJESTRACJA = "nr_rejestracyjny";

    public List<Auto> getAllCars(){
        List<Auto> cars = new ArrayList();
        try {
            Class.forName(DatabseConnection.getDBDRIVER()).newInstance();
            connection = DriverManager.getConnection(DatabseConnection.getURL(),
                                                    DatabseConnection.getUSER(),
                                                    DatabseConnection.getPASSWORD());
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("Select * FROM Auto");


            while (result.next()){
                cars.add( new Auto(result.getLong(ID),
                                   result.getLong(WARSZTAT_ID),
                                    result.getString(MARKA),
                                    result.getString(MODEL),
                                    result.getString(REJESTRACJA)));
            }

            System.out.println(cars.get(0).getNrRej());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cars;

    }

    public Auto getAutoByNrRej(String nrRej){
        Auto auto = new Auto();
        try {
            Class.forName(DatabseConnection.getDBDRIVER()).newInstance();
            connection = DriverManager.getConnection(DatabseConnection.getURL(),
                    DatabseConnection.getUSER(),
                    DatabseConnection.getPASSWORD());
            statement = connection.createStatement();
            String query = String.format("Select * From Auto Where nr_rejestracyjny = '%s' ", nrRej);
            ResultSet result = statement.executeQuery(query);


            while (result.next()){
                auto.setId( result.getLong(ID));
                auto.setWarsztatId( result.getLong(WARSZTAT_ID));
                auto.setMarka(result.getString(MARKA));
                auto.setModel(result.getString(MODEL));
                auto.setNrRej(result.getString(REJESTRACJA));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(auto.getMarka());
        return auto;
    }

    public void addAuto(Auto auto){
        try {
            Class.forName(DatabseConnection.getDBDRIVER()).newInstance();
            connection = DriverManager.getConnection(DatabseConnection.getURL(),
                    DatabseConnection.getUSER(),
                    DatabseConnection.getPASSWORD());
            statement = connection.createStatement();


            String insert = String.format("INSERT INTO Auto (warsztatId, auto_marka, auto_model, nr_rejestracyjny)" +
                                            "VALUES (%d, '%s', '%s', '%s')",
                                            auto.getWarsztatId(),
                                            auto.getMarka(),
                                            auto.getModel(),
                                            auto.getNrRej());
            statement.execute(insert);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

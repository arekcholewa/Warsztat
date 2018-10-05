package databse;

import entity.Auto;
import entity.Warsztat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WarsztatService {

    private Connection connection;
    private Statement statement;

    private final String ID = "warsztatId";
    private final String ULICA = "ulica";
    private  final String MIEJSCOWOSC = "miejscowosc";

    public List<Warsztat> getAllWarsztat(){
        List<Warsztat> warsztats = new ArrayList();
        try {
            Class.forName(DatabseConnection.getDBDRIVER()).newInstance();
            connection = DriverManager.getConnection(DatabseConnection.getURL(),
                    DatabseConnection.getUSER(),
                    DatabseConnection.getPASSWORD());
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("Select * FROM Warsztat");


            while (result.next()){
                warsztats.add( new Warsztat(result.getLong(ID),
                        result.getString(ULICA),
                        result.getString(MIEJSCOWOSC)
                        ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return warsztats;

    }
}

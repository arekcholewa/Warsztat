package databse;

import entity.Auto;
import entity.Part;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PartService {


    private Connection connection;
    private Statement statement;

    public List<Part> getAllParts(){
        List<Part> parts = new ArrayList();
        try {
            Class.forName(DatabseConnection.getDBDRIVER()).newInstance();
            connection = DriverManager.getConnection(DatabseConnection.getURL(),
                    DatabseConnection.getUSER(),
                    DatabseConnection.getPASSWORD());
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("Select * FROM Part");


            while (result.next()){
                parts.add( new Part(result.getLong("partId"),
                        result.getLong("categoryId"),
                        result.getLong("warsztatId"),
                        result.getString("producent"),
                        result.getString("model"),
                        result.getInt("półka"),
                        result.getInt("miejsce"),
                        result.getInt("rząd"),
                        result.getString("DOT"),
                        result.getString("bieznik")));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return parts;

    }
}

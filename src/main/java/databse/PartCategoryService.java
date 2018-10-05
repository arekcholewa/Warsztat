package databse;

import entity.Auto;
import entity.PartCategory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PartCategoryService {

    private Connection connection;
    private Statement statement;

    private final String CATEGORY_ID = "categoryId";
    private final String NAZWA = "nazwa";

    public List<PartCategory> getAllCategories(){

        List<PartCategory> categroies = new ArrayList();
        try {
            Class.forName(DatabseConnection.getDBDRIVER()).newInstance();
            connection = DriverManager.getConnection(DatabseConnection.getURL(),
                    DatabseConnection.getUSER(),
                    DatabseConnection.getPASSWORD());
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("Select * FROM PartCategory");


            while (result.next()){
                categroies.add( new PartCategory(result.getLong(CATEGORY_ID),
                                                result.getString(NAZWA)));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
            return  categroies;
    }



}

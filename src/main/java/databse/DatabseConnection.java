package databse;

public class DatabseConnection {

    private static final String URL = "jdbc:mysql://51.38.131.17:3306/Warsztat?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "user1";
    private static final String PASSWORD = "user1";
    private final static String DBDRIVER = "com.mysql.cj.jdbc.Driver";

    public static String getURL() {
        return URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getDBDRIVER() {
        return DBDRIVER;
    }
}

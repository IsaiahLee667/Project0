package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {
    public static Connection createConnection()
    {
        try{
            Connection conn = DriverManager.getConnection("jdbc:postgresql://revature-awsdb.cww3rzwfr5hv.us-east-2.rds.amazonaws.com/bank?user=postgres&password=Saintanger12!");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }



    }
}

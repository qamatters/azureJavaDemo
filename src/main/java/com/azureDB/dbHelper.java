package com.azureDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.azureDB.KeyDontAddMeInGit.DB_USER_PASSWORD;
import static com.constants.ProjectConstants.*;

public class dbHelper {

    public static void readDBWithUsernameAndPassword() {
        String connectionString = "jdbc:sqlserver://"+SERVER_NAME+".database.windows.net:1433;database="+DB_NAME+";user="+DB_USER_NAME+";password="+DB_USER_PASSWORD+";encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        try {
            List<Object> arrayList = new ArrayList<>();
            Connection conn = DriverManager.getConnection(connectionString);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM [SalesLT].[Address]");
            while (resultSet.next()){
                String AddressID = resultSet.getString("AddressID");
                String AddressLine1 = resultSet.getString("AddressLine1");
                String City = resultSet.getString("City");
                String StateProvince = resultSet.getString("StateProvince");
                String CountryRegion = resultSet.getString("CountryRegion");
                arrayList.add(AddressID);
                arrayList.add(AddressLine1);
                arrayList.add(City);
                arrayList.add(StateProvince);
                arrayList.add(CountryRegion);
                System.out.println(AddressID + ", " + AddressLine1 + ", " + City +
                        ", " + StateProvince + ", " + CountryRegion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}

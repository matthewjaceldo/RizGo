package database;

import java.sql.Connection;

public class DatabaseTest {

    public static void main(String[] args) {

        System.out.println("Testing RizGo database...");

        Connection connection =
                Database.connect();

        if (connection != null) {

            System.out.println(
                    "SUCCESS: RizGo database is working!"
            );

            try {
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            System.out.println(
                    "FAILED: Could not connect to RizGo database."
            );
        }
    }

}

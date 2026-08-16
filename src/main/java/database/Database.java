package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    // =========================================================
    // DATABASE
    // =========================================================

    private static final String DATABASE_NAME =
            "rizgo.db";

    private static final String URL =
            "jdbc:sqlite:" + DATABASE_NAME;


    // =========================================================
    // CONNECT
    // =========================================================

    public static Connection connect() {

        try {

            Connection connection =
                    DriverManager.getConnection(URL);

            createUsersTable(connection);

            System.out.println(
                    "Connected to RizGo database successfully."
            );

            System.out.println(
                    "DATABASE PATH: "
                            + new File(DATABASE_NAME)
                            .getAbsolutePath()
            );

            return connection;

        } catch (SQLException e) {

            System.err.println(
                    "Database connection failed."
            );

            e.printStackTrace();

            return null;
        }
    }


    // =========================================================
    // CREATE USERS TABLE
    // =========================================================

    private static void createUsersTable(
            Connection connection)
            throws SQLException {

        String sql = """
                CREATE TABLE IF NOT EXISTS users (

                    id INTEGER PRIMARY KEY AUTOINCREMENT,

                    fullname TEXT NOT NULL,

                    username TEXT NOT NULL UNIQUE,

                    email TEXT NOT NULL UNIQUE,

                    password TEXT NOT NULL

                )
                """;

        try (
                Statement statement =
                        connection.createStatement()
        ) {

            statement.execute(sql);
        }
    }


    // =========================================================
    // REGISTER USER
    // =========================================================

    public static boolean registerUser(
            String fullname,
            String username,
            String email,
            String password) {

        String sql = """
                INSERT INTO users
                (fullname, username, email, password)
                VALUES (?, ?, ?, ?)
                """;

        Connection connection =
                connect();

        if (connection == null) {

            System.err.println(
                    "Registration failed because database connection is null."
            );

            return false;
        }


        try (
                connection;

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    fullname
            );

            statement.setString(
                    2,
                    username
            );

            statement.setString(
                    3,
                    email
            );

            statement.setString(
                    4,
                    password
            );


            int rowsInserted =
                    statement.executeUpdate();


            System.out.println(
                    "Rows inserted: "
                            + rowsInserted
            );


            if (rowsInserted > 0) {

                System.out.println(
                        "USER SAVED SUCCESSFULLY:"
                );

                System.out.println(
                        "Full Name: "
                                + fullname
                );

                System.out.println(
                        "Username: "
                                + username
                );

                System.out.println(
                        "Email: "
                                + email
                );

                return true;
            }


            return false;


        } catch (SQLException e) {

            System.err.println(
                    "Registration failed:"
            );

            System.err.println(
                    e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // CHECK EMAIL
    // =========================================================

    public static boolean emailExists(
            String email) {

        String sql = """
                SELECT id
                FROM users
                WHERE LOWER(email) = LOWER(?)
                """;


        Connection connection =
                connect();


        if (connection == null) {
            return false;
        }


        try (
                connection;

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    email
            );


            try (
                    ResultSet result =
                            statement.executeQuery()
            ) {

                return result.next();
            }


        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // CHECK USERNAME
    // =========================================================

    public static boolean usernameExists(
            String username) {

        String sql = """
                SELECT id
                FROM users
                WHERE LOWER(username) = LOWER(?)
                """;


        Connection connection =
                connect();


        if (connection == null) {
            return false;
        }


        try (
                connection;

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    username
            );


            try (
                    ResultSet result =
                            statement.executeQuery()
            ) {

                return result.next();
            }


        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // LOGIN USER
    // =========================================================

    public static boolean loginUser(
            String email,
            String password) {

        String sql = """
                SELECT id
                FROM users
                WHERE LOWER(email) = LOWER(?)
                AND password = ?
                """;


        Connection connection =
                connect();


        if (connection == null) {
            return false;
        }


        try (
                connection;

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    email
            );

            statement.setString(
                    2,
                    password
            );


            try (
                    ResultSet result =
                            statement.executeQuery()
            ) {

                return result.next();
            }


        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // COUNT USERS
    // =========================================================

    public static int getUserCount() {

        String sql =
                "SELECT COUNT(*) FROM users";


        Connection connection =
                connect();


        if (connection == null) {
            return 0;
        }


        try (
                connection;

                Statement statement =
                        connection.createStatement();

                ResultSet result =
                        statement.executeQuery(sql)
        ) {

            if (result.next()) {

                return result.getInt(1);
            }


        } catch (SQLException e) {

            e.printStackTrace();
        }


        return 0;
    }
}
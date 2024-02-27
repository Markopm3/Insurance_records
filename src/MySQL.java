import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQL {

        // JDBC URL, username, and password of MySQL server
        private static final String JDBC_URL = "jdbc:mysql://localhost:3306/basic_insured";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "";

        // JDBC variables for opening and managing connection
        private static Connection connection;

        // Method to establish a connection to the database
        private static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        }

        // Method to save a person into the database
        public static void saveInsured(Insured insured) {
            try {
                // Open a connection
                connection = getConnection();

                // Prepare SQL statement
                String sql = "INSERT INTO pojistenci (first_name, surname, age, telephone_number) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, insured.getFirstName());
                statement.setString(2, insured.getLastName());
                statement.setInt(3, insured.getAge());
                statement.setString(4, insured.getTelephoneNumber());

                // Execute the statement
                statement.executeUpdate();
                System.out.println("Pojištěnec byl úspěšně uložen do MySQL databáze.");

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Close connection
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void deletePerson(int insured_ID) throws SQLException {
            try {

                connection = getConnection();
                String sql = "DELETE FROM pojistenci WHERE insured_id = ?";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1, insured_ID);
                stmt.executeUpdate();
                System.out.println("Pojištěnec byl z MySQL databáze úspěšně smazán.");
                stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {

                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
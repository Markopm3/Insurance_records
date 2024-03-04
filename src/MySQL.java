import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQL {

        // JDBC URL, přezdívka a heslo k mému MySQL serveru
        private static final String JDBC_URL = "jdbc:mysql://localhost:3306/basic_insured";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "";

        // JDBC proměnná k navázání a správu připojení
        private static Connection connection;

        // Metoda k navázání připojení k mé MySQL databázi
        private static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        }

        // Metoda pro přidání uživatele do databáze
        public static void saveInsured(Insured insured) {
            try {
                // Navázání připojení
                connection = getConnection();

                // SQL příkaz
                String sql = "INSERT INTO pojistenci (first_name, surname, age, telephone_number) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, insured.getFirstName());
                statement.setString(2, insured.getLastName());
                statement.setInt(3, insured.getAge());
                statement.setString(4, insured.getTelephoneNumber());

                // Provedení SQL příkazu
                statement.executeUpdate();
                System.out.println("Pojištěnec byl úspěšně uložen do MySQL databáze.");

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Přerušení připojení
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Metoda ke smazání uloženého uživatele v databázi
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

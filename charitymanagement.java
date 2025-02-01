import java.sql.*;

public class CharityManagement {
    private static final String URL = "jdbc:postgresql://localhost:5432/charity_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            createTables(conn);
            insertData(conn);
            readData(conn);
            updateData(conn);
            deleteData(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTables(Connection conn) throws SQLException {
        String createDonorTable = """
            CREATE TABLE IF NOT EXISTS Donor (
                id SERIAL PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                email VARCHAR(100) UNIQUE NOT NULL
            )""";

        String createCharityTable = """
            CREATE TABLE IF NOT EXISTS Charity (
                id SERIAL PRIMARY KEY,
                name VARCHAR(150) NOT NULL,
                description TEXT
            )""";

        String createDonationTable = """
            CREATE TABLE IF NOT EXISTS Donation (
                id SERIAL PRIMARY KEY,
                donor_id INT REFERENCES Donor(id),
                charity_id INT REFERENCES Charity(id),
                amount DECIMAL(10,2) NOT NULL,
                donation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )""";
        
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createDonorTable);
            stmt.execute(createCharityTable);
            stmt.execute(createDonationTable);
        }
    }

    private static void insertData(Connection conn) throws SQLException {
        String insertDonor = "INSERT INTO Donor (name, email) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertDonor)) {
            pstmt.setString(1, "John Doe");
            pstmt.setString(2, "john.doe@example.com");
            pstmt.executeUpdate();
        }
    }

    private static void readData(Connection conn) throws SQLException {
        String query = "SELECT * FROM Donor";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }
        }
    }

    private static void updateData(Connection conn) throws SQLException {
        String updateQuery = "UPDATE Donor SET name = ? WHERE email = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
            pstmt.setString(1, "Johnathan Doe");
            pstmt.setString(2, "john.doe@example.com");
            pstmt.executeUpdate();
        }
    }

    private static void deleteData(Connection conn) throws SQLException {
        String deleteQuery = "DELETE FROM Donor WHERE email = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
            pstmt.setString(1, "john.doe@example.com");
            pstmt.executeUpdate();
        }
    }
}

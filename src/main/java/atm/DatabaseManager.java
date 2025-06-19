package atm;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseManager {
    private final String DB_URL;
    private final String DB_USER;
    private final String DB_PASSWORD;

    public DatabaseManager() {
        this.DB_URL = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : "jdbc:postgresql://localhost:5432/atm_db";
        this.DB_USER = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "postgres";
        this.DB_PASSWORD = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "111111";
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public AccountHolder getAccountHolderByPin(String pin) {
        String query = "SELECT * FROM account_holders WHERE pin = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, pin);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new AccountHolder(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("pin"),
                            rs.getDouble("balance")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Transaction> getTransactions(int accountHolderId) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions WHERE account_holder_id = ? ORDER BY date DESC";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, accountHolderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    transactions.add(new Transaction(
                            rs.getString("type"),
                            rs.getDouble("amount"),
                            rs.getString("date")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
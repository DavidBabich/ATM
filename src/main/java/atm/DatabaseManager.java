package atm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/atm_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public AccountHolder getAccountHolderByPin(String pin) {
        String query = "SELECT * FROM account_holders WHERE pin = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, pin);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new AccountHolder(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("pin"),
                        rs.getDouble("balance")
                );
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
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                transactions.add(new Transaction(
                        rs.getString("type"),
                        rs.getDouble("amount"),
                        rs.getString("date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
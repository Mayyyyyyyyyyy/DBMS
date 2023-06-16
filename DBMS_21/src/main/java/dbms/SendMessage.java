package dbms;

import java.sql.*;

public class SendMessage {
    private Connection connection;
    private UserManager um;

    public SendMessage(Connection connection) {
        this.connection = connection;
        um = new UserManager(connection);
    }

    public boolean sendMessage(int receiverID, String message) {
    	if (!um.isLoggedIn()) {
            System.out.println("請先登入");
            return false;
        }
    	
        String insertQuery = "INSERT INTO sendMessage (sID, rID, sendTime, context) VALUES (?, ?, CURRENT_TIMESTAMP, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setInt(1, um.getUserId());
            statement.setInt(2, receiverID);
            statement.setString(3, message);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("傳送訊息失敗：" + e.getMessage());
            return false;
        }

        System.out.println("訊息傳送成功");
        return true;
    }
    
    public boolean receive(int senderID, String message) {
    	if (!um.isLoggedIn()) {
            System.out.println("請先登入");
            return false;
        }
    	
        String insertQuery = "INSERT INTO sendMessage (sID, rID, sendTime, context) VALUES (?, ?, CURRENT_TIMESTAMP, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setInt(1, senderID);
            statement.setInt(2, um.getUserId());
            statement.setString(3, message);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("接收訊息失敗：" + e.getMessage());
            return false;
        }

        System.out.println("訊息接收成功");
        return true;
    }
}

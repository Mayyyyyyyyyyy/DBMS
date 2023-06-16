package dbms;

import java.sql.*;
import java.time.LocalDateTime;

public class ResumeManager {
    private Connection connection;
    private UserManager um;
    private UserType ut;

    public ResumeManager(Connection connection) {
        this.connection = connection;
        um = new UserManager(connection);
        ut = new UserType(connection);
    }

    public boolean addResume(String rContent) {
        String insertQuery = "INSERT INTO RESUME (rContent, updateTime, candidateID) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, rContent);
            statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            statement.setInt(3, ut.getCandidateId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("新增失敗：" + e.getMessage());
            return false;
        }
    }

    public boolean updateResume(int resumeID, String rContent) {
    	if (!um.isLoggedIn()) {
            System.out.println("請先登入");
            return false;
        }
    	
        String updateQuery = "UPDATE RESUME SET rContent = ?, updateTime = ? WHERE resumeID = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, rContent);
            statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            statement.setInt(3, resumeID);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("更新失敗：" + e.getMessage());
            return false;
        }
    }

    public boolean deleteResume(int resumeID) {
    	if (!um.isLoggedIn()) {
            System.out.println("請先登入");
            return false;
        }
    	
        String deleteQuery = "DELETE FROM RESUME WHERE resumeID = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, resumeID);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("刪除失敗：" + e.getMessage());
            return false;
        }
    }
}
package dbms;

import java.sql.*;

public class CollectJob {
    private Connection connection;
    private UserManager um;
    private UserType ut;

    public CollectJob(Connection connection) {
        this.connection = connection;
        um = new UserManager(connection);
        ut = new UserType(connection);
    }

    public boolean collectJob(int jobID) {
    	if (!um.isLoggedIn()) {
            System.out.println("請先登入");
            return false;
        }
    	
        // 檢查是否已經收藏該工作
        if (isJobCollected(jobID)) {
            System.out.println("此工作已被新增");
            return false;
        }

        // 新增收藏工作資料
        String insertQuery = "INSERT INTO collect (candidateID, jID, collectTime) VALUES (?, ?, CURRENT_TIMESTAMP)";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setInt(1, ut.getCandidateId());
            statement.setInt(2, jobID);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("新增收藏工作失敗：" + e.getMessage());
            return false;
        }

        System.out.println("工作已成功收藏");
        return true;
    }

    public boolean cancelCollectJob(int candidateID, int jobID) {
        // 刪除收藏工作資料
    	if (!um.isLoggedIn()) {
            System.out.println("請先登入");
            return false;
        }
    	
        String deleteQuery = "DELETE FROM collect WHERE candidateID = ? AND jID = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, candidateID);
            statement.setInt(2, jobID);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("工作已成功取消收藏");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("取消收藏工作失敗：" + e.getMessage());
        }

        System.out.println("取消收藏工作失敗：找不到相應的收藏紀錄");
        return false;
    }

    private boolean isJobCollected(int jobID) {
        String query = "SELECT COUNT(*) FROM collect WHERE candidateID = ? AND jID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ut.getCandidateId());
            statement.setInt(2, jobID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.out.println("查詢收藏工作失敗：" + e.getMessage());
        }
        return false;
    }
}

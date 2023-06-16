package dbms;

import java.sql.*;

public class JobManager {
    private Connection connection;

    public JobManager(Connection connection) {
        this.connection = connection;
    }

    public boolean createJob(int companyID, String title, String content) {
        // 新增工作
        String jobInsertQuery = "INSERT INTO JOB (title, jContent, cID) VALUES (?, ?, ?)";
        try (PreparedStatement jobInsertStatement = connection.prepareStatement(jobInsertQuery, Statement.RETURN_GENERATED_KEYS)) {
            jobInsertStatement.setString(1, title);
            jobInsertStatement.setString(2, content);
            jobInsertStatement.setInt(3, companyID);
            jobInsertStatement.executeUpdate();

            // 取得新增工作的 jID
            ResultSet generatedKeys = jobInsertStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int jobID = generatedKeys.getInt(1);
                createOffer(companyID, jobID);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("新增工作失敗：" + e.getMessage());
        }

        return false;
    }

    public boolean updateJob(int jobID, String title, String content) {
        // 更新工作
        String jobUpdateQuery = "UPDATE JOB SET title = ?, jContent = ? WHERE jID = ?";
        try (PreparedStatement jobUpdateStatement = connection.prepareStatement(jobUpdateQuery)) {
            jobUpdateStatement.setString(1, title);
            jobUpdateStatement.setString(2, content);
            jobUpdateStatement.setInt(3, jobID);
            int rowsAffected = jobUpdateStatement.executeUpdate();

            if (rowsAffected > 0) {
                updateOffer(jobID);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("更新工作失敗：" + e.getMessage());
        }

        return false;
    }

    public boolean deleteJob(int jobID) {
        // 刪除工作
        String jobDeleteQuery = "DELETE FROM JOB WHERE jID = ?";
        try (PreparedStatement jobDeleteStatement = connection.prepareStatement(jobDeleteQuery)) {
            jobDeleteStatement.setInt(1, jobID);
            int rowsAffected = jobDeleteStatement.executeUpdate();

            if (rowsAffected > 0) {
                deleteOffer(jobID);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("刪除工作失敗：" + e.getMessage());
        }

        return false;
    }

    private void createOffer(int companyID, int jobID) {
        // 新增 offer 資料
        String offerInsertQuery = "INSERT INTO offer (cID, jID, offerTime) VALUES (?, ?, CURRENT_TIMESTAMP)";
        try (PreparedStatement offerInsertStatement = connection.prepareStatement(offerInsertQuery)) {
            offerInsertStatement.setInt(1, companyID);
            offerInsertStatement.setInt(2, jobID);
            offerInsertStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("新增 offer 資料失敗：" + e.getMessage());
        }
    }

    private void updateOffer(int jobID) {
        // 更新 offer 資料
        String offerUpdateQuery = "UPDATE offer SET offerTime = CURRENT_TIMESTAMP WHERE jID = ?";
        try (PreparedStatement offerUpdateStatement = connection.prepareStatement(offerUpdateQuery)) {
            offerUpdateStatement.setInt(1, jobID);
            offerUpdateStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("更新 offer 資料失敗：" + e.getMessage());
        }
    }

    private void deleteOffer(int jobID) {
        // 刪除 offer 資料
        String offerDeleteQuery = "DELETE FROM offer WHERE jID = ?";
        try (PreparedStatement offerDeleteStatement = connection.prepareStatement(offerDeleteQuery)) {
            offerDeleteStatement.setInt(1, jobID);
            offerDeleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("刪除 offer 資料失敗：" + e.getMessage());
        }
    }
}

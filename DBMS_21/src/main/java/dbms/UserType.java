package dbms;

import java.sql.*;

public class UserType {
    private Connection connection;
    private UserManager um;

    public UserType(Connection connection) {
        this.connection = connection;
        um = new UserManager(connection);
    }

    public void addCandidateData(String gender, int age, String eduLevel) {
        if (!um.isLoggedIn()) {
            System.out.println("請先登入");
            return;
        }

        if (!um.getUserType().equals("candidate")) {
            System.out.println("權限不足");
            return;
        }

        String insertQuery = "INSERT INTO CANDIDATE (gender, age, eduLevel, uID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, gender);
            statement.setInt(2, age);
            statement.setString(3, eduLevel);
            statement.setInt(4, um.getUserId());
            statement.executeUpdate();
            System.out.println("資料新增成功");
        } catch (SQLException e) {
            System.out.println("資料新增失敗：" + e.getMessage());
        }
    }
    
    public void addEmployerData() {
        if (!um.isLoggedIn()) {
            System.out.println("請先登入");
            return;
        }

        if (!um.getUserType().equals("employer")) {
            System.out.println("權限不足");
            return;
        }

        String insertQuery = "INSERT INTO EMPLOYER (uID) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setInt(1, um.getUserId());
            statement.executeUpdate();
            System.out.println("資料新增成功");
        } catch (SQLException e) {
            System.out.println("資料新增失敗：" + e.getMessage());
        }
    }
    
    public int getCandidateId() {
    	if (!um.getUserType().equals("candidate")) {
            System.out.println("權限不足");
            return -1;
        }
    	
        String query = "SELECT candidateID FROM CANDIDATE WHERE uID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, um.getUserId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("candidateID");
            }
        } catch (SQLException e) {
            System.out.println("查詢失敗：" + e.getMessage());
        }
        return -1;
    }

}

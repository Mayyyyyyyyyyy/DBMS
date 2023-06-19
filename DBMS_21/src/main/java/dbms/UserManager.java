package dbms;

import java.sql.*;

public class UserManager {
	private Connection connection;
    private boolean isLoggedIn;
    private String loggedInUser;
    private String checkString = "";
    private String checkString2 = "";
    private String registerUser;

    public UserManager(Connection connection) {
    	this.connection = connection;
        isLoggedIn = false;
        loggedInUser = null;
    }

    public void register(String userName, String account, String password, String email, String userType) {
        if (isAccountExists(account)) {
            System.out.println("帳號已存在");
            checkString2 = "l";
            return;
        }
        String userInsertQuery = "INSERT INTO USER (userName, account, password, email, userType) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement userInsertStatement = connection.prepareStatement(userInsertQuery)) {
        	userInsertStatement.setString(1, userName);
        	userInsertStatement.setString(2, account);
            userInsertStatement.setString(3, password);
            userInsertStatement.setString(4, email);
            userInsertStatement.setString(5, userType);
            userInsertStatement.executeUpdate();
            registerUser = account;
            checkString2 = "r";
        } catch (SQLException e) {
        	e.printStackTrace();
        }

    }

    private boolean isAccountExists(String account) {
        String query = "SELECT COUNT(*) FROM USER WHERE account = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, account);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return false;
    }
    
    public void login(String account, String password, String userType) {
        if (!isAccountExists(account)) {
            System.out.println("請先註冊");
            checkString = "r";
            return;
        }
        
        String query = "SELECT COUNT(*) FROM USER WHERE account = ? AND password = ? AND userType = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, account);
            statement.setString(2, password);
            statement.setString(3, userType);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    isLoggedIn = true;
                    loggedInUser = account;
                    System.out.println("登入成功");
                    checkString = "l";
                } else {
                	System.out.println("登入失敗");
                	checkString = "!l";
                }
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }
    
    public String getUserType() {
        String query = "SELECT userType FROM USER WHERE account = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, loggedInUser);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("userType");
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return null;
    }

    public int getUserId(String account) throws SQLException {
        String query = "SELECT uID FROM USER WHERE account = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, account);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("uID");
            } else {
                throw new SQLException("User not found");
            }
        } catch (SQLException e) {
        	e.printStackTrace();
            throw e;
        }
    }
    
    public String getCheckString() {
    	return checkString;
		
	}
    
    public String getRegisterUser() {
    	return registerUser;
	}
    
    public String getCheckString2() {
    	return checkString2;
		
	}
}

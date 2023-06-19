package dbms;

import java.sql.*;

public class UserManager {
    // 假設您已經建立了與資料庫的連接
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
        // 檢查帳號是否已存在
        if (isAccountExists(account)) {
            System.out.println("帳號已存在");
            checkString2 = "l";
            return;
        }
        // 建立新的使用者
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
            System.out.println("註冊失敗：" + e.getMessage());
        }

//        System.out.println("帳號註冊成功");
//        return true;
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
            System.out.println("查詢失敗：" + e.getMessage());
        }
        return false;
    }
    
    public void login(String account, String password, String userType) {
    	// 檢查帳號是否已存在
        if (!isAccountExists(account)) {
            System.out.println("請先註冊");
            checkString = "r";
            return;
        }
        
        // 登入帳號
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
            System.out.println("驗證失敗：" + e.getMessage());
        }
//        System.out.println("登入失敗");
//        checkString = "!l";
    }
        
    public void logout() {
        isLoggedIn = false;
        loggedInUser = null;
        System.out.println("已登出");
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
            System.out.println("查詢失敗：" + e.getMessage());
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
            System.out.println("查詢失敗：" + e.getMessage());
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

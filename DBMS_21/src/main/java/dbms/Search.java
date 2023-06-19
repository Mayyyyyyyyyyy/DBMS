package dbms;

import java.sql.*;
import java.util.ArrayList;

import jnr.ffi.Struct.int16_t;

public class Search {
//    private Connection connection;
//    private UserManager userManager = LoginServlet.userManager;

//    public Search(Connection connection) {
//        this.connection = connection;
//        um = new UserManager(connection);
//    }

    public static ArrayList<Job> searchJobsByKeyword(Connection conn, String keyword, String account) {
    	ArrayList<Job> jobList = new ArrayList<>();

    	try {
    		if(keyword != null) {
    			String sql = "SELECT * FROM JOB WHERE jobName LIKE ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, "%" + keyword + "%");

                ResultSet rs = statement.executeQuery();
                saveSearchData(conn, keyword, account);

                while (rs.next()) {
                    // 從結果集中讀取資料並建立 Product 物件
                	int jID = rs.getInt("jID");
                    String jobName = rs.getString("jobName");
                    String jContent = rs.getString("jContent");
                    int cID = rs.getInt("cID");
                    System.out.println(cID);

                    Job job = new Job(jID, jobName, jContent, cID);
                    jobList.add(job);
                }
                rs.close();
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobList;
    }

    public static void saveSearchData(Connection conn, String keyword, String account) {
        String insertQuery = "INSERT INTO SEARCH (keyword, uID) VALUES (?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(insertQuery)) {
            statement.setString(1, keyword);
            statement.setInt(2, LoginServlet.userManager.getUserId(account));
            statement.executeUpdate();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }
    
    public static int getJobID(Connection conn, String jobName) throws SQLException {
        String query = "SELECT jID FROM JOB WHERE jobName = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, jobName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("jID");
            } else {
                throw new SQLException("Job not found");
            }
        } catch (SQLException e) {
            System.out.println("查詢失敗：" + e.getMessage());
            throw e;
        }
    }

}

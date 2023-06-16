package dbms;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class Main {
//    public static void main(String[] args) {
//        String instanceConnectionName = "organic-phoenix-387005:asia-east1:ryuuyo39";
//        String databaseName = "jobsearchplatform";
//        String username = "root";
//        String password = "Umi8491lulu";
//
//        String jdbcUrl = String.format(
//                "jdbc:mysql://google/%s?cloudSqlInstance=%s&socketFactory=com.google.cloud.sql.mysql.SocketFactory&user=%s&password=%s",
//                databaseName, instanceConnectionName, username, password);
//
//        try {
//            // 載入 JDBC 驅動程式
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            // 建立連線
//            Connection connection = DriverManager.getConnection(jdbcUrl);
//
//            // 建立 Statement 物件
//            Statement statement = connection.createStatement();
//
//            // 執行 SQL 查詢
//            String sql = "SELECT * FROM your-table";
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            // 處理查詢結果
//            while (resultSet.next()) {
//                // 取得欄位值
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                // 其他欄位...
//
//                // 做你想要的處理
//                System.out.println("ID: " + id + ", Name: " + name);
//            }
//
//            // 關閉資源
//            resultSet.close();
//            statement.close();
//            connection.close();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.sql.core.CoreSocketFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try {
            
        	//C:\Users\ryuuy\OneDrive\文件\DBMS_team21\organic-phoenix-387005-45309f4d7fba.json
        	GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:/Users/ryuuy/eclipse-workspace/DBMS_21/organic-phoenix-387005-45309f4d7fba.json"));

            // 建立資料庫連線
            String instanceConnectionName = "organic-phoenix-387005:asia-east1:ryuuyo39";
            String databaseName = "jobsearchplatform";
//            String jdbcUrl = String.format("jdbc:mysql://google/%s?cloudSqlInstance=%s&socketFactory=%s",
//                    databaseName, instanceConnectionName, CoreSocketFactory.class.getName());
            String jdbcUrl = String.format(
                    "jdbc:mysql://google/%s?cloudSqlInstance=%s&"
                        + "socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8",
                    databaseName, instanceConnectionName);
            
            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "Umi8491lulu");
            
            properties.setProperty("credentials", credentials.toString());
            
            Connection connection = DriverManager.getConnection(jdbcUrl, properties);
            System.out.println("connected");
            
    	    Statement statement = connection.createStatement();
    	    //This line has the issue
//    	    statement.executeUpdate("INSERT INTO USER\r\n"
//    	    		+ "VALUES (01, 'aaaa', '0000', 'qqqqq', 'c');");
//    	    System.out.println("Inserted");
    	    UserManager u = new UserManager(connection);
    	    
    	    
    	    
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}



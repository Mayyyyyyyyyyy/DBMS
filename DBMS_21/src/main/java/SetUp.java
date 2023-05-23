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

import com.google.auth.oauth2.GoogleCredentials;

public class SetUp {
	public Connection conn;

	public SetUp() {
		Database();
	}

	public void Database() {
		try {

			// C:\Users\ryuuy\OneDrive\文件\DBMS_team21\organic-phoenix-387005-45309f4d7fba.json
			GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(
					"C:/Users/user/eclipse-workspace/DBMS_21/organic-phoenix-387005-45309f4d7fba.json"));

			// 建立資料庫連線
			String instanceConnectionName = "organic-phoenix-387005:asia-east1:ryuuyo39";
			String databaseName = "jobsearchplatform";
//            String jdbcUrl = String.format("jdbc:mysql://google/%s?cloudSqlInstance=%s&socketFactory=%s",
//                    databaseName, instanceConnectionName, CoreSocketFactory.class.getName());
			String jdbcUrl = String.format("jdbc:mysql://google/%s?cloudSqlInstance=%s&"
					+ "socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8",
					databaseName, instanceConnectionName);

			Properties properties = new Properties();
			properties.setProperty("user", "root");
			properties.setProperty("password", "Umi8491lulu");

			properties.setProperty("credentials", credentials.toString());

			conn = DriverManager.getConnection(jdbcUrl, properties);
			System.out.println("connected");

			Statement statement = conn.createStatement();
			// This line has the issue
//    	    statement.executeUpdate("INSERT INTO USER\r\n"
//    	    		+ "VALUES (01, 'aaaa', '0000', 'qqqqq', 'c');");
//    	    System.out.println("Inserted");
//    	    UserManager u = new UserManager(connection);

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConn() {
		return conn;
	}
}

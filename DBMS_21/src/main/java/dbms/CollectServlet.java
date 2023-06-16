package dbms;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.auth.oauth2.GoogleCredentials;

import jnr.ffi.Struct.int16_t;

/**
 * Servlet implementation class CollectServlet
 */
@WebServlet("/CollectServlet")
public class CollectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static UserManager userManager = LoginServlet.userManager;
	public static Connection conn;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(
				"C:/Users/ryuuy/eclipse-workspace/DBMSteam21/organic-phoenix-387005-45309f4d7fba.json"));

		// 建立資料庫連線
		String instanceConnectionName = "organic-phoenix-387005:asia-east1:ryuuyo39";
		String databaseName = "jobsearchplatform";
		String jdbcUrl = String.format("jdbc:mysql://google/%s?cloudSqlInstance=%s&"
				+ "socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8",
				databaseName, instanceConnectionName);

		Properties properties = new Properties();
		properties.setProperty("user", "root");
		properties.setProperty("password", "Umi8491lulu");

		properties.setProperty("credentials", credentials.toString());

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, properties);
			System.out.println(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		request.setAttribute("jobCollectedList", getCollectedJob(conn));
		request.getRequestDispatcher("Love.jsp").forward(request, response);
        
        String action = request.getParameter("action");
        if (action != null && action.equals("delete")) {
            String sql = "DELETE FROM COLLECT WHERE uID = ? AND jID = ?";

    	    try (PreparedStatement statement = conn.prepareStatement(sql)) {
    	    	int uID = userManager.getUserId();
    	        int jID = Integer.parseInt(request.getParameter("jID"));
    	        statement.setInt(1, uID);
    	        statement.setInt(2, jID);
    	        System.out.println(jID);
    	        // 执行插入收藏条目的SQL语句
    	        statement.executeUpdate();
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
//            System.out.println("Job collected successfully.");
        }
	}
	
	private ArrayList<Job> getCollectedJob(Connection conn) {
	    ArrayList<Job> jobCollectedList = new ArrayList<>();

	    try {
	        String sql = "SELECT jID FROM COLLECT WHERE uID = ?";
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setInt(1, userManager.getUserId());
	        ResultSet rs = statement.executeQuery();

	        while (rs.next()) {
	            int jID = rs.getInt("jID");

	            String sql2 = "SELECT * FROM JOB WHERE jID = ?";
	            PreparedStatement statement2 = conn.prepareStatement(sql2);
	            statement2.setInt(1, jID);
	            ResultSet rs2 = statement2.executeQuery();

	            if (rs2.next()) {
	                String jobName = rs2.getString("jobName");
	                String jContent = rs2.getString("jContent");
	                int cID = rs2.getInt("cID");

	                Job job = new Job(jID, jobName, jContent, cID);
	                jobCollectedList.add(job);
	            }

	            rs2.close();
	            statement2.close();
	        }

	        rs.close();
	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return jobCollectedList;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

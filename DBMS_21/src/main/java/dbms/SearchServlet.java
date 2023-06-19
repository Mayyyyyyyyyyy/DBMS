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

/**
 * Servlet implementation class SerachServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static UserManager userManager = LoginServlet.userManager;
	public static Connection conn;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(
				"C:/apache-tomcat-9.0.75/webapps/DBMS_21/organic-phoenix-387005-45309f4d7fba.json"));

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
		
//		request.getRequestDispatcher("Homepage.jsp").forward(request, response);
		String keyword = request.getParameter("search");
		
		ArrayList<Job> jobList = Search.searchJobsByKeyword(conn, keyword, (String) request.getSession().getAttribute("loggedInUser"));
		
		request.setAttribute("jobList", jobList);
        request.getRequestDispatcher("Homepage.jsp").forward(request, response);
        
        String action = request.getParameter("action");
        if (action != null && action.equals("collect")) {
//            String jobName = request.getParameter("jobName");
//            System.out.println(jobName);
            String sql = "INSERT INTO COLLECT (uID, jID) VALUES (?, ?)";

    	    try (PreparedStatement statement = conn.prepareStatement(sql)) {
    	        int uID = userManager.getUserId((String) request.getSession().getAttribute("loggedInUser"));
    	        System.out.println(request.getParameter("jID"));
    	        int jID = Integer.parseInt(request.getParameter("jID"));
    	        System.out.println(jID);
    	        
    	        // 检查uID和jID是否已存在
    	        if (isEntryExist(uID, jID)) {
    	            System.out.println("Entry already exists.");
    	            return; // 中止执行
    	        }

    	        statement.setInt(1, uID);
    	        statement.setInt(2, jID);
    	        System.out.println(jID);
    	        // 执行插入收藏条目的SQL语句
    	        statement.executeUpdate();
    	        System.out.println("Job collected successfully.");
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	        // 处理插入操作期间发生的任何错误
    	    }
//            System.out.println("Job collected successfully.");
        }
        
        try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 检查uID和jID是否已存在
	private boolean isEntryExist(int uID, int jID) {
	    String sql = "SELECT COUNT(*) FROM COLLECT WHERE uID = ? AND jID = ?";
	    try (PreparedStatement statement = conn.prepareStatement(sql)) {
	        statement.setInt(1, uID);
	        statement.setInt(2, jID);
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


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

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

import javax.print.attribute.standard.JobName;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.auth.oauth2.GoogleCredentials;

/**
 * Servlet implementation class CollectServlet
 */
@WebServlet("/AddResumeServlet")
public class AddResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static UserManager userManager = ResumeServlet.userManager;
	public static Connection conn;
	private String resumeName;
	public String rContent;
	private int o = 0;
       
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
		
		if (request.getParameter("resumeTitle") == null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("AddResume.jsp").forward(request, response);
//			System.out.println("vvvvvvvvvvvv");
			return;
		}
//		request.getRequestDispatcher("Supply.jsp").forward(request, response);
		resumeName = request.getParameter("resumeTitle");
		rContent = request.getParameter("description");
		
		String query = "INSERT INTO RESUME (resumeName, rContent, uID) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
        	statement.setString(1, resumeName);
        	statement.setString(2, rContent);
        	statement.setInt(3, userManager.getUserId());
        	statement.executeUpdate();
        	o = 1;
        } catch (SQLException e) {
            System.out.println("新增失敗：" + e.getMessage());
        }
		System.out.println("o");
        if(o == 1) {
        	response.sendRedirect("ResumeServlet");
    	    return;
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

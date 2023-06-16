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

import jnr.ffi.Struct.int16_t;

/**
 * Servlet implementation class CollectServlet
 */
@WebServlet("/InputServlet")
public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static UserManager userManager = LoginServlet.userManager;
	public static Connection conn;
	public String content;
	public int eID;
       
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
		
		if (request.getParameter("content") == null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Input.jsp").forward(request, response);
//			System.out.println("vvvvvvvvvvvv");
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		content = request.getParameter("content");
		
		String qString = "SELECT * FROM COMPANY WHERE cID = ?;";
		try (PreparedStatement stat = conn.prepareStatement(qString)) {
			stat.setInt(1, ChatServlet.cID);
			ResultSet resultSet = stat.executeQuery();
			while(resultSet.next()) {
				eID = resultSet.getInt("uID");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String query = "INSERT INTO sendMessage (sID, rID, content) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
        	statement.setInt(1, userManager.getUserId());
        	statement.setInt(2, eID);
        	statement.setString(3, content);	
        	statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("新增失敗：" + e.getMessage());
        }
        
        response.sendRedirect("InputServlet");
	}

}

package dbms;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.auth.oauth2.GoogleCredentials;

/**
 * Servlet implementation class IntroServlet
 */
@WebServlet("/AllMessageServlet")
public class AllMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Connection conn;
	private UserManager userManager;
	private int uID;
	private String gender;
	private int age;
	private String level;
	private String department;
	private String email;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
				
				if (request.getParameter("age") == null) {
					String requestUri = request.getRequestURI();
					request.setAttribute("requestUri", requestUri);
					request.getRequestDispatcher("Intro.jsp").forward(request, response);
//					System.out.println("vvvvvvvvvvvv");
					return;
				}
				
				gender = request.getParameter("gender");
				age = Integer.parseInt(request.getParameter("age"));
				level = request.getParameter("grade");
				department = request.getParameter("major");
				email = request.getParameter("email");
				
				userManager = RegisterServletC.userManager;
//				System.out.println(userManager.getRegisterUser());
				
				String query = "UPDATE USER SET email = ? WHERE account = ?;";
		        try (PreparedStatement stat = conn.prepareStatement(query)) {
		        	stat.setString(1, email);
		        	stat.setString(2, userManager.getRegisterUser());
		        	stat.executeUpdate();
		        } catch (SQLException e) {
		        	// TODO Auto-generated catch block
		        	e.printStackTrace();
		        }
		        
		        String query2 = "SELECT uID FROM USER WHERE account = ?;";
		        try(PreparedStatement stat2 = conn.prepareStatement(query2)) {
		        	stat2.setString(1, userManager.getRegisterUser());
		        	ResultSet resultSet = stat2.executeQuery();
		        	if (resultSet.next()) {
		        		uID = resultSet.getInt("uID");
		        	}
		        } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        String query3 = "INSERT INTO CANDIDATE (uID, gender, age, level, department) VALUES (?, ?, ?, ?, ?)";
		        try (PreparedStatement stat3 = conn.prepareStatement(query3)) {
		        	stat3.setInt(1, uID);
		        	stat3.setString(2, gender);
		        	stat3.setInt(3, age);
		        	stat3.setString(4, level);
		        	stat3.setString(5, department);
		        	stat3.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        response.sendRedirect("LoginServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

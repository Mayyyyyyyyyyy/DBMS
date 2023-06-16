package dbms;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.auth.oauth2.GoogleCredentials;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Connection conn;
	private String account;
	private String password;
	private String userType;
	
	public static UserManager userManager;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

//		login
		if (request.getParameter("loginAccount") == null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Login.jsp").forward(request, response);
//			System.out.println("vvvvvvvvvvvv");
			return;
		}
		
		account = request.getParameter("loginAccount");
		System.out.println(account);
		password = request.getParameter("loginPassword");
		if(request.getParameter("role").equals("employee")) {
			userType = "c";
		} else if (request.getParameter("role").equals("employer")) {
			userType = "e";
		}

		userManager = new UserManager(conn);
		userManager.login(account, password, userType);
		if(userManager.getCheckString().equals("r")) {
			System.out.println("r");
			response.sendRedirect("Register0Servlet");
			return;
		} else if(userManager.getCheckString().equals("l")) {
			System.out.println("l");
			if(userType.equals("c")) {
				response.sendRedirect("SearchServlet");
				return;
			} else if(userType.equals("e")) {
				response.sendRedirect("CompanyServlet");
				return;
			}
		} else if(userManager.getCheckString().equals("!l")) {
			System.out.println("!l");
			response.sendRedirect("LoginServlet");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

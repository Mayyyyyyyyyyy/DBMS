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
import com.google.protobuf.TextFormat.Printer;

@WebServlet("/Server")
public class Server extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Connection conn;
	private String account;
	private String password;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(
				"C:/Users/user/eclipse-workspace/DBMS_21/organic-phoenix-387005-45309f4d7fba.json"));

		// 建立資料庫連線
		String instanceConnectionName = "organic-phoenix-387005:asia-east1:ryuuyo39";
		String databaseName = "jobsearchplatform";
//        String jdbcUrl = String.format("jdbc:mysql://google/%s?cloudSqlInstance=%s&socketFactory=%s",
//                databaseName, instanceConnectionName, CoreSocketFactory.class.getName());
		String jdbcUrl = String.format("jdbc:mysql://google/%s?cloudSqlInstance=%s&"
				+ "socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8",
				databaseName, instanceConnectionName);

		Properties properties = new Properties();
		properties.setProperty("user", "root");
		properties.setProperty("password", "Umi8491lulu");

		properties.setProperty("credentials", credentials.toString());

		try {
			conn = DriverManager.getConnection(jdbcUrl, properties);
			System.out.println(conn);
		} catch (SQLException e) {
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
			System.out.println("vvvvvvvvvvvv");
			return;
		}

		
		account = request.getParameter("loginAccount");
		password = request.getParameter("loginPassword");
		System.out.println(account + " " + password);

		PreparedStatement stat;
		try {
			stat = conn.prepareStatement("SELECT COUNT(*) FROM USER WHERE account = ? AND password = ?");

			stat.setString(1, account);
			stat.setString(2, password);

			ResultSet resultSet = stat.executeQuery();
			System.out.println("xxxxxxxxxxxxxxxxxxx");
			if (resultSet.next()) {
				System.out.println("zzzzzzzzzzzzzzzzzz");
				int count = resultSet.getInt(1);
				if (count > 0) {
					System.out.println("vvvvvvvvvvvvvvvv");
					System.out.println("登入成功");
					request.getRequestDispatcher("Homepage.jsp").forward(request, response);
				}
			}

			stat.clearParameters();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

//		GoogleCaller google = new GoogleCaller(input);
//
//		ArrayList<Website> list = google.getResult();
//		String[][] s = new String[25][2];
//		int num = 0;
//		try {
//			for (Website entry : list) {
//				String name = entry.name;
//				String url = entry.url;
//
//				s[num][0] = name;
//				s[num][1] = url;
//				num++;
//			}
//		}catch (IndexOutOfBoundsException e) {
//			// TODO: handle exception
//			request.setAttribute("query", s);
//			request.getRequestDispatcher("SongResult.jsp").forward(request, response);
//		}
//		request.setAttribute("query", s);
//		request.getRequestDispatcher("SongResult.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

package dbms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String url_STRING = "jdbc:mysql://google/jobsearchplatform?cloudSqlInstance=organic-phoenix-387005:asia-east1:ryuuyo39&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=root&password=Umi8491lulu";
	static Connection connection = null;
	UserManager userManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url_STRING);
			userManager = new UserManager(connection);
			
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USER");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				String nameString = resultSet.getString("account");
				response.getWriter().append(nameString);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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

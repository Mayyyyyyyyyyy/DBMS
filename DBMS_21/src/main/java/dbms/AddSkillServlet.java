package dbms;

import java.awt.print.PrinterException;
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
@WebServlet("/AddSkillServlet")
public class AddSkillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static UserManager userManager = ResumeServlet.userManager;
	public static Connection conn;
	private String skillName;
	private int o = 0;
       
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
		
		if (request.getParameter("skillName") == null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("AddSkill.jsp").forward(request, response);
			return;
		}
		skillName = request.getParameter("skillName");
		
		String query = "SELECT COUNT(*) FROM MAJOR WHERE majorName = ?";
		boolean shouldInsert = true;

		try (PreparedStatement statement = conn.prepareStatement(query)) {
		    statement.setString(1, skillName);
		    ResultSet rs = statement.executeQuery();
		    
		    if (rs.next()) {
		        int count = rs.getInt(1);
		        if (count > 0) {
		            shouldInsert = false;
		        }
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (shouldInsert) {
		    String insertQuery = "INSERT INTO MAJOR (majorName) VALUES (?)";
		    try (PreparedStatement insertStatement = conn.prepareStatement(insertQuery)) {
		        insertStatement.setString(1, skillName);
		        insertStatement.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
        
        String query2 = "SELECT * FROM MAJOR WHERE majorName = ?";
        try (PreparedStatement statement = conn.prepareStatement(query2)) {
        	statement.setString(1, skillName);
        	ResultSet rs = statement.executeQuery();
        	while (rs.next()) {
	            int mID = rs.getInt("mID");
	            String query3 = "INSERT INTO has (uID, mID) VALUES (?, ?)";
	            try (PreparedStatement statement2 = conn.prepareStatement(query3)) {
	            	statement2.setInt(1, userManager.getUserId((String) request.getSession().getAttribute("loggedInUser")));
	            	statement2.setInt(2, mID);
	            	statement2.executeUpdate();
	            	o = 1;
	            } catch (SQLException e) {
	            	e.printStackTrace();
	            }
        	}
	            
        } catch (SQLException e) {
        	e.printStackTrace();
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

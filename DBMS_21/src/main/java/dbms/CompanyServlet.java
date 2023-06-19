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
@WebServlet("/CompanyServlet")
public class CompanyServlet extends HttpServlet {
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
		
		request.setAttribute("jobList", getJob(conn, (String) request.getSession().getAttribute("loggedInUser")));
		request.getRequestDispatcher("HomepageForCompany.jsp").forward(request, response);
	}
	
	private ArrayList<Job> getJob(Connection conn, String account) {
    	ArrayList<Job> jobList = new ArrayList<>();

    	try {
    		String sql = "SELECT cID FROM COMPANY WHERE uID = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userManager.getUserId(account));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
            	 int cID = rs.getInt("cID");
            	    String sql2 = "SELECT jobName, jContent FROM JOB WHERE cID = ?;";
            	    PreparedStatement statement2 = conn.prepareStatement(sql2);
            	    statement2.setInt(1, cID);
            	    ResultSet rs2 = statement2.executeQuery();

            	    while (rs2.next()) {
            	        String jobName = rs2.getString("jobName");
            	        String jContent = rs2.getString("jContent");

            	        Job job = new Job(cID, jobName, jContent, cID);
                        jobList.add(job);
            	    }
                }
                rs.close();
                statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobList;
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

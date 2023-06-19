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
 * Servlet implementation class ResumeServlet
 */
@WebServlet("/ResumeServlet")
public class ResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static UserManager userManager = LoginServlet.userManager;
	public static Connection conn;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
        
		int uID = 0;
		try {
			uID = userManager.getUserId((String) request.getSession().getAttribute("loggedInUser"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    if (uID > 0) {
	        // 查询数据库获取数据
	        String query = "SELECT USER.userName, USER.email, CANDIDATE.gender, CANDIDATE.age, CANDIDATE.level, CANDIDATE.department FROM USER, CANDIDATE WHERE USER.uID = CANDIDATE.uID AND USER.uID = ?";
	        try {
	            PreparedStatement stmt = conn.prepareStatement(query);
	            stmt.setInt(1, uID);
	            ResultSet rs = stmt.executeQuery();

	            // 将查询结果设置到请求属性中
	            if (rs.next()) {
	                request.setAttribute("userName", rs.getString("userName"));
	                request.setAttribute("email", rs.getString("email"));
	                request.setAttribute("gender", rs.getString("gender"));
	                request.setAttribute("age", rs.getInt("age"));
	                request.setAttribute("level", rs.getString("level"));
	                request.setAttribute("department", rs.getString("department"));
	            }

	            rs.close();
	            stmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // 转发到 Resume.jsp 页面
	    request.setAttribute("skillList", getSkill(conn, (String) request.getSession().getAttribute("loggedInUser")));
	    request.setAttribute("resumeList", getResume(conn, (String) request.getSession().getAttribute("loggedInUser")));
	    request.getRequestDispatcher("Resume.jsp").forward(request, response);
	    
	    String action2 = request.getParameter("action2");
        if (action2 != null && action2.equals("deleteSkill")) {
            String sql = "DELETE FROM has WHERE uID = ? AND mID = ?";

    	    try (PreparedStatement statement = conn.prepareStatement(sql)) {
    	        int mID = Integer.parseInt(request.getParameter("mID"));
    	        statement.setInt(1, uID);
    	        statement.setInt(2, mID);
//    	        System.out.println(mID);
    	        // 执行插入收藏条目的SQL语句
    	        statement.executeUpdate();
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
//            System.out.println("Job collected successfully.");
        }
	    
	    String action = request.getParameter("action");
        if (action != null && action.equals("delete")) {
            String sql = "DELETE FROM RESUME WHERE resumeID = ?";

    	    try (PreparedStatement statement = conn.prepareStatement(sql)) {
    	        int resumeID = Integer.parseInt(request.getParameter("resumeID"));
    	        statement.setInt(1, resumeID);
    	        System.out.println(resumeID);
    	        // 执行插入收藏条目的SQL语句
    	        statement.executeUpdate();
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
//            System.out.println("Job collected successfully.");
        }
	}
	
	private ArrayList<Resume> getResume(Connection conn, String account) {
    	ArrayList<Resume> resumeList = new ArrayList<>();

    	try {
    		String sql = "SELECT * FROM RESUME WHERE uID = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userManager.getUserId(account));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
            	int resumeID = rs.getInt("resumeID");
            	String resumeName = rs.getString("resumeName");
            	String rContent = rs.getString("rContent");
            	 
            	Resume resume = new Resume(resumeID, resumeName, rContent);
            	resumeList.add(resume);
               }
               rs.close();
               statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resumeList;
    }
	
	private ArrayList<Skill> getSkill(Connection conn, String account) {
    	ArrayList<Skill> skillList = new ArrayList<>();

    	try {
    		String sql = "SELECT * FROM has WHERE uID = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userManager.getUserId(account));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
	            int mID = rs.getInt("mID");

	            String sql2 = "SELECT * FROM MAJOR WHERE mID = ?";
	            PreparedStatement statement2 = conn.prepareStatement(sql2);
	            statement2.setInt(1, mID);
	            ResultSet rs2 = statement2.executeQuery();
	            
	            if (rs2.next()) {
	                String skillName = rs2.getString("majorName");

	                Skill skill = new Skill(mID, skillName);
	                skillList.add(skill);
	            }

	            rs2.close();
	            statement2.close();
	        }

	        rs.close();
	        statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return skillList;
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 获取当前用户的 ID
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
	    int uID = 0;
		try {
			uID = userManager.getUserId((String) request.getSession().getAttribute("loggedInUser"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	    // 从请求参数中获取更新的字段值
	    String userName = request.getParameter("userName");
	    String email = request.getParameter("email");
	    String gender = request.getParameter("gender");
	    int age = Integer.parseInt(request.getParameter("age"));
	    String level = request.getParameter("level");
	    String department = request.getParameter("department");

	    // 更新数据库中的数据
	    String updateQuery = "UPDATE USER SET userName = ?, email = ? WHERE uID = ?";
	    String candidateUpdateQuery = "UPDATE CANDIDATE SET gender = ?, age = ?, level = ?, department = ? WHERE uID = ?";
	    try {
	        PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
	        updateStmt.setString(1, userName);
	        updateStmt.setString(2, email);
	        updateStmt.setInt(3, uID);
	        updateStmt.executeUpdate();
	        updateStmt.close();

	        PreparedStatement candidateUpdateStmt = conn.prepareStatement(candidateUpdateQuery);
	        candidateUpdateStmt.setString(1, gender);
	        candidateUpdateStmt.setInt(2, age);
	        candidateUpdateStmt.setString(3, level);
	        candidateUpdateStmt.setString(4, department);
	        candidateUpdateStmt.setInt(5, uID);
	        candidateUpdateStmt.executeUpdate();
	        candidateUpdateStmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    // 重定向到 ResumeServlet，重新加载数据并显示在 Resume.jsp 页面上
	    response.sendRedirect("ResumeServlet");
	}

}

package dbms;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.auth.oauth2.GoogleCredentials;

/**
 * Servlet implementation class RecommendServlet
 */
@WebServlet("/RecommendServlet")
public class RecommendServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static UserManager userManager = LoginServlet.userManager;
    public static Connection conn;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        ArrayList<Job> jobRecommendList = getRecommendJob(conn, (String) request.getSession().getAttribute("loggedInUser"));
        saveRecommendations(jobRecommendList, conn, (String) request.getSession().getAttribute("loggedInUser"));

        request.setAttribute("jobRecommendList", jobRecommendList);
        request.getRequestDispatcher("Recommend.jsp").forward(request, response);
    }

    private ArrayList<Job> getRecommendJob(Connection conn, String account) {
        ArrayList<Job> jobRecommendList = new ArrayList<>();

        try {
            ArrayList<String> userSkills = getUserSkills(conn, account);
            String userDepartment = getUserDepartment(conn, account);

            Set<String> keywords = new HashSet<>();
            keywords.addAll(userSkills);
            keywords.add(userDepartment);

            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("SELECT jID, jobName, jContent ");
            sqlBuilder.append("FROM JOB ");
            sqlBuilder.append("WHERE ");

            for (int i = 0; i < keywords.size(); i++) {
                if (i != 0) {
                    sqlBuilder.append("OR ");
                }
                sqlBuilder.append("jobName LIKE ? OR jContent LIKE ? ");
            }

            PreparedStatement statement = conn.prepareStatement(sqlBuilder.toString());

            int parameterIndex = 1;
            for (String keyword : keywords) {
                String keywordPattern = "%" + keyword + "%";
                statement.setString(parameterIndex++, keywordPattern);
                statement.setString(parameterIndex++, keywordPattern);
            }

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int jID = rs.getInt("jID");
                String jobName = rs.getString("jobName");
                String jContent = rs.getString("jContent");
                int cID = 0;
                String qString = "SELECT * FROM JOB WHERE jID = ?;";
                PreparedStatement stat = conn.prepareStatement(qString);
                stat.setInt(1, jID);
                ResultSet r = stat.executeQuery();
                while (r.next()) {
                    cID = r.getInt("cID");
                }
                

                Job job = new Job(jID, jobName, jContent, cID);
                jobRecommendList.add(job);
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobRecommendList;
    }

    private ArrayList<String> getUserSkills(Connection conn, String account) {
        ArrayList<String> userSkills = new ArrayList<>();

        try {
            String sql = "SELECT MAJOR.majorName FROM MAJOR " + "INNER JOIN has ON MAJOR.mID = has.mID "
                    + "WHERE has.uID = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userManager.getUserId(account));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String skillName = rs.getString("majorName");
                userSkills.add(skillName);
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userSkills;
    }

    private String getUserDepartment(Connection conn, String account) {
        String userDepartment = "";

        try {
            String sql = "SELECT department FROM CANDIDATE " + "WHERE uID = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userManager.getUserId(account));
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                userDepartment = rs.getString("department");
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userDepartment;
    }

    private void saveRecommendations(ArrayList<Job> jobRecommendList, Connection conn, String account) {
        try {
            Set<Integer> existingRecommendations = getExistingRecommendations(userManager.getUserId(account), conn);

            String deleteSql = "DELETE FROM RECOMMEND WHERE uID = ? AND jID = ?";
            PreparedStatement deleteStatement = conn.prepareStatement(deleteSql);

            for (Integer jID : existingRecommendations) {
                if (!containsJobID(jobRecommendList, jID)) {
                    deleteStatement.setInt(1, userManager.getUserId(account));
                    deleteStatement.setInt(2, jID);
                    deleteStatement.executeUpdate();
                }
            }

            deleteStatement.close();

            String insertSql = "INSERT INTO RECOMMEND (uID, jID) VALUES (?, ?)";
            PreparedStatement insertStatement = conn.prepareStatement(insertSql);

            for (Job job : jobRecommendList) {
                int jID = job.getjID();
                if (!existingRecommendations.contains(jID)) {
                    insertStatement.setInt(1, userManager.getUserId(account));
                    insertStatement.setInt(2, jID);
                    insertStatement.executeUpdate();
                }
            }

            insertStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Set<Integer> getExistingRecommendations(int userId, Connection conn) {
        Set<Integer> existingRecommendations = new HashSet<>();

        try {
            String selectSql = "SELECT jID FROM RECOMMEND WHERE uID = ?";
            PreparedStatement selectStatement = conn.prepareStatement(selectSql);
            selectStatement.setInt(1, userId);

            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {
                int jID = rs.getInt("jID");
                existingRecommendations.add(jID);
            }

            rs.close();
            selectStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return existingRecommendations;
    }

    private boolean containsJobID(ArrayList<Job> jobRecommendList, int jID) {
        for (Job job : jobRecommendList) {
            if (job.getjID() == jID) {
                return true;
            }
        }
        return false;
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}

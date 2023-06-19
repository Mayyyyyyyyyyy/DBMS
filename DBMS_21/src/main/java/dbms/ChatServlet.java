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

@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static UserManager userManager;
    public static Connection conn;
    private int sID;
    private int rID;
    public static int cID;
    
    public void init() throws ServletException {
        GoogleCredentials credentials;
        try {
        	credentials = GoogleCredentials.fromStream(new FileInputStream(
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        userManager = LoginServlet.userManager;
        rID = 0;
        sID = 0;
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        
        if (request.getAttribute("firstTime") == null) {
            try {
                sID = userManager.getUserId((String) request.getSession().getAttribute("loggedInUser"));
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            
            cID = MessageServlet.cID;
            System.out.println(cID);
            try {
                String qString = "SELECT * FROM COMPANY WHERE cID = ?;";
                PreparedStatement s = conn.prepareStatement(qString);
                s.setInt(1, cID);
                ResultSet r = s.executeQuery();

                while (r.next()) {
                    rID = r.getInt("uID");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ArrayList<Message> messages = getMessages(sID, rID);

        request.setAttribute("messages", messages);
        request.getRequestDispatcher("Chat1.jsp").forward(request, response);
    }

    private ArrayList<Message> getMessages(int sID, int rID) {
        ArrayList<Message> messages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM sendMessage WHERE (sID = ? AND rID = ?) OR (rID = ? AND sID = ?) ORDER BY sendTime ASC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, sID);
            stmt.setInt(2, rID);
            stmt.setInt(3, sID);
            stmt.setInt(4, rID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int senderID = rs.getInt("sID");
                int receiverID = rs.getInt("rID");
                String content = rs.getString("content");

                Message message = new Message(senderID, receiverID, content);
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

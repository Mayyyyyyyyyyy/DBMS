import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.protobuf.TextFormat.Printer;

@WebServlet("/Server")
public class Server extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Connection conn;
	private String account;
	private String password;

	public Server() {
		super();
		SetUp setUp = new SetUp();
		conn = setUp.getConn();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

//		login
		if (request.getParameter("loginAccount") == null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			System.out.println("vvvvvvvvvvvv");
			System.out.println(conn);
			return;
		}

		System.out.println("rrrrrrrrrrrrrrrr");
		account = request.getParameter("loginAccount");
		password = request.getParameter("loginPassword");
		System.out.println(account + " " + password);

		PreparedStatement stat;
		try {
			stat = conn.prepareStatement("SELECT COUNT(*) FROM USER WHERE account = ? AND password = ?");

			stat.setString(1, account);
			stat.setString(2, password);
			stat.executeQuery();
			stat.clearParameters();

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

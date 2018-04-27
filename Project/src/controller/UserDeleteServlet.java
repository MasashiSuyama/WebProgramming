package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインセッションがない場合、ログイン画面にリダイレクトさせる
		HttpSession session = request.getSession();
		User u =(User)session.getAttribute("userInfo");
		if(u == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		// URLからGETパラメータとしてIDを受け取る
		String id_str = request.getParameter("id");
		int id = Integer.parseInt(id_str);

		//idを引数にして、idに紐づくユーザ情報を出力する
		UserDao userDao = new UserDao();
		User user = userDao.findUserInfo(id);

		//ユーザ情報をリクエストスコープにセット
		request.setAttribute("userDelete", user);

		// ユーザ詳細のjspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userDelete.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// URLからGETパラメータとしてIDを受け取る
		String id_str = request.getParameter("id");
		int id = Integer.parseInt(id_str);

		//idを引数にして、idに紐づくユーザ情報を削除する
		UserDao userDao = new UserDao();
		userDao.DeleteUser(id);

		// ユーザ一覧のサーブレットにリダイレクト
		response.sendRedirect("UserListServlet");
	}

}

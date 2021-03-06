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
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
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
		request.setAttribute("userUpdate", user);

		// ユーザ更新のjspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
		String password = request.getParameter("password");
		String passwordCheck = request.getParameter("passwordCheck");
		String userName = request.getParameter("userName");
		String birthday = request.getParameter("birthday");

		// URLからGETパラメータとしてIDを受け取る
		String id_str = request.getParameter("id");
		int id = Integer.parseInt(id_str);

		/** 登録できない場合 **/
		if ( !(password.equals(passwordCheck)) || userName.equals("") || birthday.equals("") ) {
			// リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "入力された内容は正しくありません");

			//idを引数にして、idに紐づくユーザ情報を出力する
			UserDao userDao = new UserDao();
			User user = userDao.findUserInfo(id);

			//ユーザ情報をリクエストスコープにセット
			request.setAttribute("userUpdate", user);

			// ユーザ更新jspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
			dispatcher.forward(request, response);
			return;
		}

		//idを引数にして、idに紐づくユーザ情報を更新する
		UserDao userDao = new UserDao();
		userDao.UserUpdate(id, userName, birthday, password);

		// ユーザ一覧のサーブレットにリダイレクト
		response.sendRedirect("UserListServlet");
	}

}

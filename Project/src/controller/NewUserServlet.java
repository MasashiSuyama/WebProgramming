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
 * Servlet implementation class NewUserServlet
 */
@WebServlet("/NewUserServlet")
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUserServlet() {
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

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUser.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String passwordCheck = request.getParameter("passwordCheck");
		String userName = request.getParameter("userName");
		String birthday = request.getParameter("birthday");

		// loginIdが既に登録されているか(false:登録済み)
		UserDao userDao = new UserDao();
		boolean loginIdCheck = userDao.newUser(loginId);

		/** 登録できない場合 **/
		if ( !(password.equals(passwordCheck)) || loginId.equals("") || password.equals("") || userName.equals("")
				|| birthday.equals("") || (loginIdCheck == false) ) {
			// リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "入力された内容は正しくありません");

			// ユーザ登録jspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUser.jsp");
			dispatcher.forward(request, response);
			return;
		}

		/** 登録できる場合 **/
		// ユーザ情報を登録
		userDao.newUser(loginId, userName, birthday, password);

		// ユーザ一覧のサーブレットにリダイレクト
		response.sendRedirect("UserListServlet");
	}

}

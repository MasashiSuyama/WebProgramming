package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.User;

/**
 * ユーザテーブル用のDao
 * @author takano
 *
 */
public class UserDao {

    /**
     * ログインIDとパスワードに紐づくユーザ情報を返す
     * @param loginId
     * @param password
     * @return
     */
    public User findByLoginInfo(String loginId, String password) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);
            pStmt.setString(2, password);
            ResultSet rs = pStmt.executeQuery();

             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
            if (!rs.next()) {
                return null;
            }

            String loginIdData = rs.getString("login_id");
            String nameData = rs.getString("name");
            return new User(loginIdData, nameData);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

    /**
     * idが一致するユーザー情報を全て返す
     */
    public User findUserInfo(int id) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE id = ?";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, id);
            ResultSet rs = pStmt.executeQuery();

             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
            if (!rs.next()) {
                return null;
            }

            String loginIdData = rs.getString("login_id");
            String nameData = rs.getString("name");
            Date brithdayData = rs.getDate("birth_date");
            String createDateData = rs.getString("create_date");
            String updateDateData = rs.getString("update_date");

            return new User(id,loginIdData, nameData, brithdayData, createDateData, updateDateData);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

    /**
     * ユーザ情報を登録する
     */
    public void newUser(String loginId, String userName, String birthday, String password) {
    	Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();


            //INSERT文を準備
            String sql = "insert into user(login_id,name,birth_date,password,create_date,update_date) values(?, ?, ?, ?, now(), now())";

            // INSERTを実行
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);
            pStmt.setString(2, userName);
            pStmt.setString(3, birthday);
            pStmt.setString(4, password);
            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * ログインIDがすでに登録されているかをbooleanで返す
     */
    public boolean newUser(String loginId) {
    	 Connection conn = null;
         try {
             // データベースへ接続
             conn = DBManager.getConnection();

             // SELECT文を準備
             String sql = "SELECT * FROM user WHERE login_id = ?";

              // SELECTを実行し、結果表を取得
             PreparedStatement pStmt = conn.prepareStatement(sql);
             pStmt.setString(1, loginId);
             ResultSet rs = pStmt.executeQuery();

              // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
             if (!rs.next()) {
                 return true;
             }

             return false;

         } catch (SQLException e) {
             e.printStackTrace();
             return false;
         } finally {
             // データベース切断
             if (conn != null) {
                 try {
                     conn.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                     return false;
                 }
             }
         }

    }

    /**
     * ユーザ情報を更新する
     */
    public void UserUpdate(int id, String userName, String birthday, String password) {
    	Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

          //現在時間の取得
    		java.util.Date date = new java.util.Date();
            SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String now = dateForm.format(date);

            if(password.equals("")) {
	            //INSERT文を準備
	            String sql = "UPDATE user SET name = ? , birth_date = ? , update_date = now() WHERE id = ?";
	            // INSERTを実行
	            PreparedStatement pStmt = conn.prepareStatement(sql);
	            pStmt.setString(1, userName);
	            pStmt.setString(2, birthday);
	            pStmt.setInt(3,id);
	            pStmt.executeUpdate();
            } else {
            	//INSERT文を準備
	            String sql = "UPDATE user SET password = ? , name = ? , birth_date = ?  WHERE id = ?";
	            // INSERTを実行
	            PreparedStatement pStmt = conn.prepareStatement(sql);
	            pStmt.setString(1, password);
	            pStmt.setString(2, userName);
	            pStmt.setString(3, birthday);
	            pStmt.setInt(4,id);
	            pStmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * idが一致するユーザー情報を削除する
     */
    public void DeleteUser(int id) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "DELETE FROM user WHERE id = ?";

             // SELECTを実行し、ユーザ情報を削除
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, id);
            pStmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 全てのユーザ情報を取得する
     * @return
     */
    public List<User> findAll() {
        Connection conn = null;
        List<User> userList = new ArrayList<User>();

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            // TODO: 未実装：管理者以外を取得するようSQLを変更する
            String sql = "SELECT * FROM user";

             // SELECTを実行し、結果表を取得
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 結果表に格納されたレコードの内容を
            // Userインスタンスに設定し、ArrayListインスタンスに追加
            while (rs.next()) {
                int id = rs.getInt("id");
                String loginId = rs.getString("login_id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");
                User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return userList;
    }
}
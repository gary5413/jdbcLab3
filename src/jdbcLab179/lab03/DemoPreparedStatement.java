package jdbcLab179.lab03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbcLab179.utils.JDBCutil;



public class DemoPreparedStatement {
	
//	CRUD
//	新增
	public void insert() {
//		preparedStatement需要使用符文字串?來取代要輸入的值
		String sql="INSERT INTO users(name,password,email) VALUES(?,?,?)";
//		使用JDBCutil 來創建連線
		Connection connection = JDBCutil.getConnection();
		PreparedStatement preparedStatement=null;
		try {
//			創建preparedStatement 並預載sql語句
			preparedStatement = connection.prepareStatement(sql);
//			各個符文字串? 相對應值要設定
			preparedStatement.setString(1, "preparedStatement");
			preparedStatement.setString(2,"123456");
			preparedStatement.setString(3,"prepared@mail.com");
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, preparedStatement);
		}
	}
	
//	刪除
	public void deletById(Integer id) {
		String sql="DELETE FROM users WHERE id = ?";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement preparedStatment=null;
		try {
			 preparedStatment = connection.prepareStatement(sql);
			preparedStatment.setInt(1, id);
			int row = preparedStatment.executeUpdate();
			System.out.println("刪除"+row+"筆");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, preparedStatment);
		}
	}
	
//	更新
	/*
	 * 依據姓名更改密碼
	 */
	public void updatePasswordByName(String name,String password) {
		String sql="UPDATE users SET password =? WHERE name=?";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement preparedStatement =null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(2, name);
			preparedStatement.setString(1, password);
			int row = preparedStatement.executeUpdate();
			System.out.println("更新"+row+"筆");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, preparedStatement);
		}
		
	}
	
//	查詢
	public void findAllUser() {
		String sql="SELECT * FROM users";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement preparedStatment=null;
		ResultSet rs=null;
		try {
			 preparedStatment = connection.prepareStatement(sql);
//			executeQuery
			 rs = preparedStatment.executeQuery();
//			next();
			while (rs.next()) {
				System.out.println(rs.getInt("id")+","+rs.getString("name")+","+rs.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, preparedStatment, rs);
		}
	}
	
//	由id去查詢單筆資料
	public void findUserById(Integer id) {
		String sql="SELECT * FROM users WHERE id =?";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			rs = preparedStatement.executeQuery();
			rs.next();
			System.out.println(rs.getInt("id")+","+rs.getString("name")+","+rs.getString("password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, preparedStatement, rs);
		}
	}
	
	
	public static void main(String[] args) {
		DemoPreparedStatement demoPreparedStatement = new DemoPreparedStatement();
//		demoPreparedStatement.insert();
//		demoPreparedStatement.deletById(1);
//		demoPreparedStatement.updatePasswordByName("Ben", "Banana");
//		demoPreparedStatement.findAllUser();
		demoPreparedStatement.findUserById(4);
	}
}

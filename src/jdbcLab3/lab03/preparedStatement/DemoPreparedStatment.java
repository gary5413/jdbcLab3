package jdbcLab3.lab03.preparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbcLab3.util.JDBCutil;

public class DemoPreparedStatment {
	/*
	 * 這邊帶同學寫JDBCutil
	 */
//	新增
	public void insert() {
		String sql="INSERT INTO users(name,password,email)VALUES(?,?,?)";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement prepareStatement=null;
		try {
//			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1,"preparedStatment");
			prepareStatement.setString(2,"123456");
			prepareStatement.setString(3, "test@mail.com");
			int row = prepareStatement.executeUpdate();
			System.out.println("新增"+row+"筆");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, prepareStatement);			
		}
	}
	
//	刪除
	public void deleteById(Integer id) {
		String sql="DELETE FROM users WHERE　id =?";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement prepareStatement=null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			int row = prepareStatement.executeUpdate();
			System.out.println("刪除"+row+"筆");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, prepareStatement);
		}
	
	}
	
//	更新
	/*
	 * 給同學實作
	 * 依據姓名更新密碼
	 */
	public void updateUserPasswordByName(String name,String passwrod) {
		String sql="UPDATE users SET password=? WHERE name=?";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement prepareStatement=null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(2,name);
			prepareStatement.setString(1, passwrod);
			int row = prepareStatement.executeUpdate();
			System.out.println("更新"+row+"筆");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, prepareStatement);
		}
	}
	
//	查詢全部
	public void findAllUsers() {
		String sql="SELECT * FROM users";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement prepareStatement=null;
		ResultSet rs=null;
		try {
			 prepareStatement = connection.prepareStatement(sql);
			 rs = prepareStatement.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getString("password")+","+rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, prepareStatement, rs);
		}
	}
	
	public static void main(String[] args) {
		DemoPreparedStatment demoPreparedStatment = new DemoPreparedStatment();
//		demoPreparedStatment.insert();
//		demoPreparedStatment.deleteById(1);
//		demoPreparedStatment.updateUserPasswordByName("Ben", "45678");
		demoPreparedStatment.findAllUsers();
	}

}

package jdbcLab183.lab08;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbcLab183.utils.JDBCutil;

public class DemoTransaction {
	
	public void updateBalance(Connection connection,String name,Integer balance) {
		String sql="UPDATE account SET account_balance =? WHERE account_name = ?";
		PreparedStatement preparedStatement=null;
		try {
			 preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(2, name);
			preparedStatement.setInt(1, balance);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(null, preparedStatement);
		}
	}
	
	public static void main(String[] args) {
		DemoTransaction demoTransaction = new DemoTransaction();
		Connection connection = JDBCutil.getConnection();
		/*
		 * B>A 1000
		 * A 1000>2000
		 * B 2000>1000
		 */
		try {
//			無交易模式狀況
//			demoTransaction.updateBalance(connection, "AA", 2000);
//			模擬出錯
//			System.out.println(10/0);
//			demoTransaction.updateBalance(connection, "BB", 1000);
//=================================================================
//			交易模式
//			setAutoCommit(false)自動認可交易關閉
			connection.setAutoCommit(false);
			connection.setAutoCommit(false);
			demoTransaction.updateBalance(connection, "AA", 2000);
//			模擬出錯
			System.out.println(10/0);
			demoTransaction.updateBalance(connection, "BB", 1000);
//			提交交易
			connection.commit();
			System.out.println("轉帳成功");
		} catch (Exception e) {
			try {
				connection.rollback();
				System.out.println("轉帳失敗");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			/*
			 * connection沒有被關閉前 還可以被重複使用
			 * 則需要恢復自動認可交易狀態setAutoCommit(true)
			 * 建議恢復自動認可交易
			 */
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JDBCutil.closeResource(connection);
		}
		
	}

}

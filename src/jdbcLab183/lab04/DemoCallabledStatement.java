package jdbcLab183.lab04;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbcLab179.utils.JDBCutil;



public class DemoCallabledStatement {
	/*
	 * 非主流
	 * 寫入最慢MySQL
    	MySQL執行效能不錯
		寫入最快Oracle

		以前電腦效能不好
		以前的工程師會使用callablestatement
		但現在電腦都變好了
		所以都使用電腦效能來處理

		沒有out參數 才可以使用executeQuery()
		只有輸入的參數
		回傳就是ResultSet
	 */
	public void callProcedure(int userId) {
		Connection connection = JDBCutil.getConnection();
		CallableStatement callabledStatement=null;
		ResultSet rs=null;
		try {
//		prepareCall()方法 會創建callabledStatement
			callabledStatement = connection.prepareCall("{call ProcUserById(?)}");
			callabledStatement.setInt(1, userId);
			rs = callabledStatement.executeQuery();
			rs.next();
			System.out.println(rs.getString("name")+","+rs.getString("password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, callabledStatement,rs);
		}
	}
	
	public void callProcedureOut(Integer id) {
		Connection connection = JDBCutil.getConnection();
		CallableStatement callabledStatement=null;
		try {
			callabledStatement = connection.prepareCall("{call ProcUserNameById(?,?)}");
			callabledStatement.setInt(1, id);
			callabledStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
			callabledStatement.execute();
//			取出第二個問號的值
			String userName = callabledStatement.getString(2);
			System.out.println(userName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, callabledStatement);
		}
	}
	
	
	public static void main(String[] args) {
		DemoCallabledStatement demoCallabledStatement = new DemoCallabledStatement();
//		demoCallabledStatement.callProcedure();
		demoCallabledStatement.callProcedureOut(2);
	}

}

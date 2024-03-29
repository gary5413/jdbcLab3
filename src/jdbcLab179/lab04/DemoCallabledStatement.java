package jdbcLab179.lab04;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbcLab179.utils.JDBCutil;



public class DemoCallabledStatement {
	/*
	 * 非主流
	 */
	public void callProcedure() {
		Connection connection = JDBCutil.getConnection();
		CallableStatement callabledStatement=null;
		ResultSet rs=null;
		try {
//		prepareCall()方法 會創建callabledStatement
			callabledStatement = connection.prepareCall("{call ProcUserById(?)}");
			callabledStatement.setInt(1, 2);
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

package jdbcLab3.lab03.callableStatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbcLab3.util.JDBCutil;

public class DemoCallableStatement {

	public void callPrcedure() {
		Connection connection = JDBCutil.getConnection();
		CallableStatement callableStatement=null;
		try {
			callableStatement = connection.prepareCall("{call SelectUsersById(?)}");
			callableStatement.setInt(1, 1);
			ResultSet rs = callableStatement.executeQuery();
			rs.next();
			System.out.println(rs.getString("name"));
			System.out.println(rs.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection,callableStatement);
		}
		
	}
	
	public void callPrcedureOut(int userId) {
		Connection connection = JDBCutil.getConnection();
		CallableStatement callableStatement=null;
		try {
			callableStatement = connection.prepareCall("{call SelectUsersById2(?,?)}");
			callableStatement.setInt(1, userId);
			callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
			callableStatement.execute();
			String userName = callableStatement.getString(2); //第二個問號
			System.out.println(userName);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection,callableStatement);
		}
		
	}
	
	
	public static void main(String[] args) {
		DemoCallableStatement demoCallableStatement = new DemoCallableStatement();
//		demoCallableStatement.callPrcedure();
		demoCallableStatement.callPrcedureOut(2);
	}

}

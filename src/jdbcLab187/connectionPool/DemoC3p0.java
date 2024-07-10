package jdbcLab187.connectionpool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DemoC3p0 {
	
	Connection connection=null;
	
	C3p0util c3p0util = new C3p0util();
	public void createConnection() throws SQLException {
		ComboPooledDataSource ds = c3p0util.openDataSource();
		connection = ds.getConnection();
		boolean status = !connection.isClosed();
		System.out.println(status);
	}
	
	public void closeConnection() throws SQLException {
		if(connection!=null) {
			connection.close();
			c3p0util.closeDataSource();
		}
	}
	
	public void insertUser() throws SQLException {
		String sql ="INSERT INTO users(name,password,email) VALUES('pool','123','pool@mail.com')";
		Statement createStatement = connection.createStatement();
		createStatement.execute(sql);
	}
	
	public static void main(String[] args) {
		System.out.println("123");
		DemoC3p0 demoC3p0 = new DemoC3p0();
		try {
			demoC3p0.createConnection();
			demoC3p0.insertUser();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

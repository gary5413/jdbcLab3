package jdbcLab3.lab05;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import jdbcLab3.util.JDBCutil;

public class DemoMetaData {
	
	public void testDatabaseMetaDate() {
		Connection connection = JDBCutil.getConnection();
		DatabaseMetaData metaData = connection.getMetaData();
	}
	
	public static void main(String[] args) {

	}

}

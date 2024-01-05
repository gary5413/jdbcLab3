package jdbcLab3.lab05;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import jdbcLab3.util.JDBCutil;

public class DemoMetaData {
	
	public void testDatabaseMetaDate() {
		Connection connection = JDBCutil.getConnection();
		DatabaseMetaData metaData = connection.getMetaData();
		System.out.println("DatabaseProductName:"+metaData.getDatabaseProductName());
		System.out.println("DatabaseProductName:"+metaData.getDatabaseProductVersion());
		System.out.println("DriverName:"+metaData.getDriverName());
		System.out.println("DriverVersion:"+metaData.getDriverVersion());
		System.out.println("UserName"+metaData.getUserName());

	}
	
	public void testResultMetaData() throws SQLException {
		String sql="SELECT * FROM user";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		ResultSet rs = prepareStatement.executeQuery();
		ResultSetMetaData metaData= rs.getMetaData();
//		回傳結果集的欄位數量
		System.out.println("ColumnCount:"+metaData.getColumnCount());
//		回傳欄位名稱/別名
		System.out.println("ColumnLabel(1):"+metaData.getColumnLabel(1));
		System.out.println("ColumnLabel(2):"+metaData.getColumnLabel(2));
//		回傳SQL資料型別
		System.out.println("ColumnTypeName(1):"+metaData.getColumnTypeName(1));
		System.out.println("ColumnTypeName(2):"+metaData.getColumnTypeName(2));
//		回傳欄位最大儲存尺寸
		System.out.println("ColumnDisplaySize(1):"+metaData.getColumnDisplaySize(1));
		System.out.println("ColumnDisplaySize(2):"+metaData.getColumnDisplaySize(2));
/*
 * 		傳回值: 
		0 代表不允許NULL 
		1 代表允許NULL 
		2 代表無法判斷的情形 
 */
		System.out.println("isNullable(1):"+metaData.isNullable(1));
		System.out.println("isNullable(5):"+metaData.isNullable(5));
		System.out.println("isNullable(7):"+metaData.isNullable(7));
		rs.close();
		prepareStatement.close();
	}
	
	public void queryAllUser() throws SQLException {
		String sql="SELECT * FROM user";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		ResultSet rs = prepareStatement.executeQuery();
		ResultSetMetaData metaData =rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		while(rs.next()) {
			for(int i=0;i<columnCount;i++) {
				Object objectValue = rs.getObject(i+1);
				String columnLabel = metaData.getColumnLabel(i+1);
				System.out.println(columnLabel+":"+objectValue);
			}
			System.out.println("=====================");
		}
		
		rs.close();
		prepareStatement.close();
	}

	
	public static void main(String[] args) {

	}

}

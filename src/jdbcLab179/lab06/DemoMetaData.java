package jdbcLab179.lab06;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import jdbcLab179.utils.JDBCutil;



public class DemoMetaData {

	public void testDatabaseMetaData() {
		Connection connection = JDBCutil.getConnection();
		try {
			DatabaseMetaData metaData= connection.getMetaData();
			System.out.println("DatabaseProductName:"+metaData.getDatabaseProductName());
			System.out.println("DatabaseProductVersion:"+metaData.getDatabaseProductVersion());
			System.out.println(metaData.getDriverName());
			System.out.println(metaData.getDriverVersion());
			System.out.println(metaData.getUserName());
			System.out.println(metaData.getURL());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection);
		}
	}
	
	public void testResultSetMetaData() {
		String sql="SELECT * FROM users";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement preparedStament=null;
		ResultSet rs=null;
		try {
			 preparedStament = connection.prepareStatement(sql);
			 rs = preparedStament.executeQuery();
			ResultSetMetaData metaData= rs.getMetaData();
//			回傳欄位數量
			System.out.println("ColumnCount:"+metaData.getColumnCount());
			System.out.println("=================================");
//			回傳欄位名稱/別名
			System.out.println(metaData.getColumnLabel(1));
			System.out.println(metaData.getColumnLabel(2));
			System.out.println("=================================");
//			回傳資料型別
			System.out.println(metaData.getColumnTypeName(1));
			System.out.println(metaData.getColumnTypeName(2));
			System.out.println("=================================");
//			回傳欄位最大儲存尺寸
			System.out.println(metaData.getColumnDisplaySize(1));
			System.out.println(metaData.getColumnDisplaySize(2));
			System.out.println("=================================");
//			回傳 null 
//			0代表不允許null
//			1代表允許null
			System.out.println(metaData.isNullable(1));
			System.out.println(metaData.isNullable(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, preparedStament, rs);
		}
	}
	
	public void queryAllUser() {
		String sql="SELECT * FROM users";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		try {
			 preparedStatement = connection.prepareStatement(sql);
			 rs = preparedStatement.executeQuery();
			ResultSetMetaData metaData= rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				for(int i=0;i<columnCount;i++) {
					Object objectValue = rs.getObject(i+1);
					String columnLabel = metaData.getColumnLabel(i+1);
					System.out.println(columnLabel+":"+objectValue);
				}
				System.out.println("============================");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, preparedStatement, rs);
		}
		
	}
	
	public static void main(String[] args) {
		DemoMetaData demoMetaData = new DemoMetaData();
//		demoMetaData.testDatabaseMetaData();
//		demoMetaData.testResultSetMetaData();
		demoMetaData.queryAllUser();
	}

}

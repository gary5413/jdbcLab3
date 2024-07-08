package jdbcLab183.lab07;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;

import jdbcLab183.utils.JDBCutil;

public class DemoBatch {
	/*
	 * 插入goods表格 for迴圈跑10000筆資料
	 */
	
	public void testInset() {
		String sql="INSERT INTO goods(goods_name) VALUES(?)";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement preparedStatement=null;
//		開計時器
		long start=System.currentTimeMillis();
		try {
			 preparedStatement = connection.prepareStatement(sql);
			for(int i=1;i<=10000;i++) {
				preparedStatement.setString(1, "goods_name"+i);
				preparedStatement.execute();
			}
//			結束的時間
			long end=System.currentTimeMillis();
			System.out.println("所花費時間"+(end-start)+"ms");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, preparedStatement);
		}
	}
	
	public void insertBatch() {
		String sql="INSERT INTO goods(goods_name) VALUES(?)";
		Connection connection = JDBCutil.getConnection();
//		long start=System.currentTimeMillis();
		long start=Instant.now().toEpochMilli();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			for(int i=1;i<=10000;i++) {
				preparedStatement.setString(1, "goods_name"+i);
//				累積sql
				preparedStatement.addBatch();
				if(i %1000 ==0) {
//					執行累積的sql語句 執行batch
					preparedStatement.executeBatch();
//					清空batch
					preparedStatement.clearBatch();
				}
			}
//			long end=System.currentTimeMillis();
			long end=Instant.now().toEpochMilli();
			System.out.println("所花費時間"+(end-start)+"ms");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, preparedStatement);
		}
	}
	public static void main(String[] args) {
		DemoBatch demoBatch = new DemoBatch();
//		demoBatch.testInset();//4753ms
		demoBatch.insertBatch();//4019ms
	}

}

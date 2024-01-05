package jdbcLab3.lab06;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbcLab3.util.JDBCutil;

public class DemoBatchUpdate {
	
//	批次處理 先示範preparedStatement
	public void testInsert() {
//		開啟計時器
		long start = System.currentTimeMillis();
		String sql="INSERT INTO goods(goods_name) VALUES (?)";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement prepareStatement=null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			for(int i=1;i<=10000;i++) {
				prepareStatement.setString(1, "name_"+i);
				prepareStatement.executeUpdate();
			}
			long end =System.currentTimeMillis();
			System.out.println("所花費時間:"+(end-start)+"ms");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, prepareStatement);
		}
	}
	
	public void insertBatch() {
		long start = System.currentTimeMillis();
		String sql="INSERT INTO goods(goods_name) VALUES (?)";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement prepareStatement=null;
		int[] rows=null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			for(int i=1;i<=10000;i++) {
				prepareStatement.setString(1, "name_"+i);
//			累積sql
				prepareStatement.addBatch();
				if(i % 1000 ==0) {
//				執行batch
					rows = prepareStatement.executeBatch();
//				清空batch
					prepareStatement.clearBatch();
				}
			}
			long end =System.currentTimeMillis();
			System.out.println("所花費時間:"+(end-start)+"ms");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, prepareStatement);
		}
	}
//	batch 示範list
	public void insertBatch2() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("name_1");
		list.add("name_2");
		list.add("name_3");
		String sql="INSERT INTO goods(goods_name) VALUES(?)";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement prepareStatement=null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			for (String name : list) {
				prepareStatement.setString(1,name);
//				累積sql
				prepareStatement.addBatch();
			}
//			批次執行
			int[] rows = prepareStatement.executeBatch();
			System.out.println("sql add rows:"+rows.length);
			prepareStatement.clearBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, prepareStatement);
		}
	}
	
	public static void main(String[] args) {
		DemoBatchUpdate demoBatchUpdate = new DemoBatchUpdate();
//		demoBatchUpdate.testInsert();//2179ms
//		demoBatchUpdate.insertBatch();//1392ms
		demoBatchUpdate.insertBatch2();
		
	}

}

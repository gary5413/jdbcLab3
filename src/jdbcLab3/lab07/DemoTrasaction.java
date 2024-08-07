package jdbcLab3.lab07;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbcLab3.util.JDBCutil;

public class DemoTrasaction {
	/*
	 * 1. 什麼是資料庫交易(事務)
	 * 		交易(事務) 一組邏輯操作單元 使資料從一種狀態轉到另一種狀態
	 *  		> 一組邏輯操作單元 一個或多DML
	 *  							資料操作語言：DML(Data Manipulation Language)用來處理資料表裡的資料。常見的指令UPDATE 更改資料表中的資料DELETE 刪除資料表中的資料
	 * 2. 事務處理的原則 要所有事務都完成 就commit
	 * 		如果過程中有出現異常 就rollback
	 * 
	 * 3. 資料一但commit 就不可rollback
	 * 
	 * 4. 哪些操作會導致資料自動提交
	 * 		DDL 資料定義語言：DDL(Data Definition Language)
	 * 			操作一但執行 都會自動提交
	 * 			我們可以通過 set autocommit =false 方式取消 DML
	 * 			CREATE 建立資料庫的物件ALTER 變更資料庫的物件的結構DROP 刪除資料庫的物件TRUNCATE 清除資料庫表格內的資料
	 * 		DML 資料操作語言：DML(Data Manipulation Language) 
	 * 			默認情況下 一但執行 就會自動提交
	 * 			我們可以通過 set autocommit =false 方式取消 DML
	 * 		默認在關閉連接時,會自動提交
	 * 
	 *  事務的ACID
	 *  	
		1. 原子性(Atomicity) 原子性是指事务是一个不可分割的工作单位，事务中的操作要么都发生，要么都不发 生。
		2. 一致性(Consistency) 事务必须使数据库从一个一致性状态变换到另外一个一致性状态。
		3. 隔离性(Isolation) 事务的隔离性是指一个事务的执行不能被其他事务干扰，即一个事务内部的操作及使用的
	  			数据对并发的其他事务是隔离的，并发执行的各个事务之间不能互相干扰。
		4. 持久性(Durability) 持久性是指一个事务一旦被提交，它对数据库中数据的改变就是永久性的，接下来的其
	  			他操作和数据库故障不应该对其有任何影响。
	  			
	  	交易的幾個問題
	  	 髒讀
	  	 	T1讀取已被T2更新但未commit資料 之後若T2 rollback T1內容就是臨時且無效的
	  	 不可重複讀
	  	 	T1 T2讀取同一個資料 然後T2更新資料後 T1在讀取直就不同了
	  	 幻讀
	  	 	T1 T2讀取同一個資料 然後T2插入新的資料後 如果T1在讀取同一資料就會多出幾行
	 */
	
	public void updateBlance(Connection connection,String name,Integer balance) {
		String sql="UPDATE account SET account_balance = ? WHERE account_name =?";
		PreparedStatement prepareStatement=null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(2, name);
			prepareStatement.setInt(1, balance);
			prepareStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(null, prepareStatement);
		}
	}

	
	public static void main(String[] args) {
		DemoTrasaction demoTrasaction = new DemoTrasaction();
		Connection connection = JDBCutil.getConnection();
//		示範交易失敗
//		try {
//			demoTrasaction.updateBlance(connection, "AA", 1500);
//			System.out.println(10/0);
//			demoTrasaction.updateBlance(connection, "BB", 1500);
//		} catch (Exception e2) {
//			e2.printStackTrace();
//		}finally {
//			JDBCutil.closeResource(connection);			
//		}
		
		
//		交易模式
		try {
//			開啟隱含交易
			connection.setAutoCommit(false);
			demoTrasaction.updateBlance(connection, "AA", 500);
			System.out.println(10/0);
			demoTrasaction.updateBlance(connection, "BB", 1500);
//			提交
			connection.commit();
			System.out.println("轉帳成功");
		} catch (Exception e) {
			e.printStackTrace();
			try {
//				交易回滾
				connection.rollback();
				System.out.println("轉帳失敗");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				/*
				 * 若此时 Connection 没有被关闭，还可能被重复使用，
				 * 则需要恢复其自动提交状态 setAutoCommit(true)。
				 * 尤其是在使用数据库连接池技术时，执行close()方法前，建议恢复自动提交状态。
				 */
				connection.setAutoCommit(true);
				JDBCutil.closeResource(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}

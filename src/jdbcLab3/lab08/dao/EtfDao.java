package jdbcLab3.lab08.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbcLab3.lab08.model.Etf;
import jdbcLab3.util.JDBCutil;

public class EtfDao {
	public void saveEtf(Etf etf) {
		String sql="INSERT INTO ETF_V(stock_id,stock_name,month_quantity) VALUES (?,?,?)";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement prepareStatement=null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, etf.getStockId());
			prepareStatement.setString(2, etf.getStockName());
			prepareStatement.setInt(3, etf.getMonthQuantity());
			prepareStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, prepareStatement);
		}
	}
	
	public void saveEtf(String stockId,String stockName,Integer stockQuantity) {
		String sql="INSERT INTO ETF_V(stock_id,stock_name,month_quantity) VALUES (?,?,?)";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement prepareStatement=null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, stockId);
			prepareStatement.setString(2, stockName);
			prepareStatement.setInt(3, stockQuantity);
			prepareStatement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, prepareStatement);
		}
	}
}

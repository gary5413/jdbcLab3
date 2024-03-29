package jdbcLab179.lab09.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbcLab179.lab09.model.Etf;
import jdbcLab179.utils.JDBCutil;



public class EtfDao {
//	新增Etf功能
	public void saveEtf(Etf etf) {
		String sql="INSERT INTO ETF_V(stock_id,stock_name,month_quantity) VALUES(?,?,?)";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			 preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, etf.getStockId());
			preparedStatement.setString(2, etf.getStockName());
			preparedStatement.setInt(3, etf.getMonthQuantity());
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, preparedStatement);
		}
	}
}

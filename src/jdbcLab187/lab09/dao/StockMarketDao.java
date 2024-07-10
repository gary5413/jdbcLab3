package jdbcLab187.lab09.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbcLab183.lab09.model.Etf;
import jdbcLab183.utils.JDBCutil;
import jdbcLab187.lab09.model.StockMarket;

public class StockMarketDao {
//	新增功能
	public void saveStockMarket(StockMarket stockMarket) {
		String sql="INSERT INTO STOCK_V(year,stock_company_OriginalValue,stock_company_growthRate,stock_marketCap_OriginalValue,stock_marketCap_growthRate) VALUES(?,?,?,?,?)";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			 preparedStatement = connection.prepareStatement(sql);
			 preparedStatement.setString(1,stockMarket.getYear());
			preparedStatement.setString(2, stockMarket.getStockCompanyOriginalValue());
			preparedStatement.setString(3, stockMarket.getStockCompanyGrowthRate());
			preparedStatement.setString(4, stockMarket.getStockMarketCapOriginalValue());
			preparedStatement.setString(5, stockMarket.getStockMarketCapGrowthRate());
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, preparedStatement);
		}
	}
}

package jdbcLab187.lab09;

import java.io.IOException;
import java.util.List;

import jdbcLab183.utils.GetDataUtil;
import jdbcLab187.lab09.dao.StockMarketDao;
import jdbcLab187.lab09.model.StockMarket;
import jdbcLab187.lab09.service.StockMarketService;

public class DemoStockMarket {
	public static void main(String[] args) {
//		GetDataUtil.getData("/Users/garylee/Downloads/EG27Y01.csv");
		List<String> datalist = GetDataUtil.getUrl("https://www.cbc.gov.tw/public/data/OpenData/%E7%B6%93%E7%A0%94%E8%99%95/EG27Y01.csv");
		StockMarketService stockMarketService = new StockMarketService();
		List<StockMarket> stockMarketData = stockMarketService.getStockMarketData(datalist);
//		for (StockMarket stockMarket : stockMarketData) {
//			System.out.println(stockMarket.getStockCompanyOriginalValue());
//		}
				StockMarketDao stockMarketDao = new StockMarketDao();
		for (StockMarket stockMarket : stockMarketData) {
			stockMarketDao.saveStockMarket(stockMarket);
		}
		
	}
}

package jdbcLab187.lab09.service;

import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.x.ContinuousOutputStream;

import jdbcLab183.lab09.model.Etf;
import jdbcLab187.lab09.model.StockMarket;

public class StockMarketService {
	public List<StockMarket> getStockMarketData(List<String> dataList) {
		List<StockMarket> list = new ArrayList<StockMarket>();
		for(int i=0;i<dataList.size();i++) {
			String[] tokens= dataList.get(i).split(",");
			System.out.println(tokens[0]+","+tokens[1]);
//			01256
			StockMarket stockMarket = new StockMarket();
			stockMarket.setYear(tokens[0]);
			String newStockCompanyOriginalValue=tokens[1].replaceAll("\"", "");
			stockMarket.setStockCompanyOriginalValue(newStockCompanyOriginalValue);
//			stockMarket.setStockCompanyOriginalValue(tokens[1]);
			stockMarket.setStockCompanyGrowthRate(tokens[2]);
			stockMarket.setStockMarketCapOriginalValue(tokens[5]);
			stockMarket.setStockMarketCapGrowthRate(tokens[6]);
			list.add(stockMarket);
		}
		return list;
	}
}

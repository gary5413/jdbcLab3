package jdbcLab187.lab09.model;

import java.io.Serializable;

public class StockMarket implements Serializable{
	private Integer id;
	private String year;
	private String stockCompanyOriginalValue;
	private String stockCompanyGrowthRate;
	private String stockMarketCapOriginalValue;
	private String stockMarketCapGrowthRate;
	
	public StockMarket() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StockMarket(Integer id, String year, String stockCompanyOriginalValue, String stockCompanyGrowthRate,
			String stockMarketCapOriginalValue, String stockMarketCapGrowthRate) {
		super();
		this.id = id;
		this.year = year;
		this.stockCompanyOriginalValue = stockCompanyOriginalValue;
		this.stockCompanyGrowthRate = stockCompanyGrowthRate;
		this.stockMarketCapOriginalValue = stockMarketCapOriginalValue;
		this.stockMarketCapGrowthRate = stockMarketCapGrowthRate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getStockCompanyOriginalValue() {
		return stockCompanyOriginalValue;
	}
	public void setStockCompanyOriginalValue(String stockCompanyOriginalValue) {
		this.stockCompanyOriginalValue = stockCompanyOriginalValue;
	}
	public String getStockCompanyGrowthRate() {
		return stockCompanyGrowthRate;
	}
	public void setStockCompanyGrowthRate(String stockCompanyGrowthRate) {
		this.stockCompanyGrowthRate = stockCompanyGrowthRate;
	}
	public String getStockMarketCapOriginalValue() {
		return stockMarketCapOriginalValue;
	}
	public void setStockMarketCapOriginalValue(String stockMarketCapOriginalValue) {
		this.stockMarketCapOriginalValue = stockMarketCapOriginalValue;
	}
	public String getStockMarketCapGrowthRate() {
		return stockMarketCapGrowthRate;
	}
	public void setStockMarketCapGrowthRate(String stockMarketCapGrowthRate) {
		this.stockMarketCapGrowthRate = stockMarketCapGrowthRate;
	}
	
}

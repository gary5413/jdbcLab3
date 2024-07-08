package jdbcLab183.lab09.model;

public class Etf {
	private Integer id;
	private String stockId;
	private String stockName;
	private String etfId;
	private String etfName;
	public Etf() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getEtfId() {
		return etfId;
	}
	public void setEtfId(String etfId) {
		this.etfId = etfId;
	}
	public String getEtfName() {
		return etfName;
	}
	public void setEtfName(String etfName) {
		this.etfName = etfName;
	}
	
}

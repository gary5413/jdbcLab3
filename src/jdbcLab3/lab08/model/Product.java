package jdbcLab3.lab08.model;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
// implements Serializable
	//	private static final long serialVersionUID = 1L;
	/*
	 * 序列化：把對象轉換爲字節序列的過程稱爲對象的序列化。
	   反序列化：把字節序列恢復爲對象的過程稱爲對象的反序列化。
	 * 
	 * serialVersionUID 是一个版本号，
	 * 用于标识序列化对象的版本。当你在反序列化时，
	 * 系统会检查序列化数据中的版本号是否与当前类的版本号匹配，
	 * 以确保反序列化的正确性。如果你对类做了修改，一般会更新版本号。
	 */
	private Integer productId;
	private String productName;
	private String category;
	private String imageUrl;
	private Integer price;
	private Integer stock;
	private String description;
	private Date createDate;
	private Date lastModifiedDate;
	
	
	
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Product(Integer productId, String productName, String category, String imageUrl, Integer price,
			Integer stock, String description) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.category = category;
		this.imageUrl = imageUrl;
		this.price = price;
		this.stock = stock;
		this.description = description;
	}



	public Product(String productName, String category, String imageUrl, Integer price, Integer stock,
			String description) {
		super();
		this.productName = productName;
		this.category = category;
		this.imageUrl = imageUrl;
		this.price = price;
		this.stock = stock;
		this.description = description;
	}
	
	


	public Product(Integer productId, String productName, String category, String imageUrl, Integer price,
			Integer stock, String description, Date createDate, Date lastModifiedDate) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.category = category;
		this.imageUrl = imageUrl;
		this.price = price;
		this.stock = stock;
		this.description = description;
		this.createDate = createDate;
		this.lastModifiedDate = lastModifiedDate;
	}



	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}


	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", category=" + category
				+ ", imageUrl=" + imageUrl + ", price=" + price + ", stock=" + stock + ", description=" + description
				+ ", createDate=" + createDate + ", lastModifiedDate=" + lastModifiedDate + "]";
	}
	
	
	
	
}

package jdbcLab187.lab09;

import java.util.List;

import jdbcLab183.lab09.model.Product;
import jdbcLab187.lab09.dao.ProductDao;

public class DemoProduct {
	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
//		Product product1 = new Product("JDBC講義","BOOK","url",0,1,"JDBC講義好棒");
//		productDao.saveProduct(product1);
//		productDao.deleteProductById(1);
//		Product product2 = productDao.findProductById(2);
//		product2.setProductName("更新後商品");
//		productDao.updateProduct(product2);
		List<Product> allProducts = productDao.findAllProducts();
		for(Product product:allProducts) {
			System.out.println(product.toString());
		}
	}
}

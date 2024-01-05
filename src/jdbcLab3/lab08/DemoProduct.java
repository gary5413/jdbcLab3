package jdbcLab3.lab08;

import java.util.List;

import jdbcLab3.lab08.dao.ProductDao;
import jdbcLab3.lab08.model.Product;

public class DemoProduct {
	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
//		查詢
//		List<Product> productList = productDao.findAllProducts();
//		for (Product product : productList) {
//			System.out.println(product.getProductName()+","+product.getCategory()+","+product.getImageUrl()+","+product.getPrice()+","+product.getStock()+","+product.getDescription());
//		}
		Product product = productDao.findProductById(1);
//		System.out.println(product.getProductName()+","+product.getCategory()+","+product.getImageUrl()+","+product.getPrice()+","+product.getStock()+","+product.getDescription());
//		新增
//		Product product = new Product("jdbc講義","BOOK","jdbc.jpg",1,1,"我愛JDBC");
//		productDao.saveProduct(product);
//		更新
//		Product product = productDao.findProductById(2);
//		product.setProductName("更新後商品");
//		productDao.updateProduct(2, product);
//		刪除
		productDao.deleteProductById(7);
		
		
	}
}

package jdbcLab183.lab09.dao;

import java.util.List;

import jdbcLab183.lab09.model.Product;

public interface ProductDao {

	//	新增
	void saveProduct(Product product);

	//	刪除
	//	根據指定id刪除商品 參數id
	void deleteProductById(Integer productId);

	//	更新
	void updateProduct(Product product);

	//	查詢
	//	利用id查詢單筆商品
	Product findProductById(Integer productId);

	//	查詢全部
	List<Product> findAllProducts();

}
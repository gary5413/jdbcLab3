package jdbcLab179.lab09.dao;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbcLab179.lab09.model.Product;
import jdbcLab179.utils.JDBCutil;



public class ProductDao {
//	新增
	public void saveProduct(Product product) {
		String sql = "INSERT INTO product (product_name,category,image_url,price,stock,description,created_date,last_modified_date)"
				+ " VALUES (?,?,?,?,?,?,?,?)";
		Connection connection = JDBCutil.getConnection();
//		new xXXX()
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setString(2, product.getCategory());
			preparedStatement.setString(3, product.getImageUrl());
			preparedStatement.setInt(4, product.getPrice());
			preparedStatement.setInt(5, product.getStock());
			preparedStatement.setString(6, product.getDescription());
//			產生現在時間
			LocalDate nowDate = LocalDate.now();
//			從java型態時間轉乘sql時間型態
			preparedStatement.setDate(7, java.sql.Date.valueOf(nowDate));
			preparedStatement.setDate(8, java.sql.Date.valueOf(nowDate));
			preparedStatement.execute();
			System.out.println("新增商品完成");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCutil.closeResource(connection, preparedStatement);
		}
	}

//	刪除
//	根據指定id刪除商品 參數id
	public void deleteProductById(Integer productId) {
		String sql = "DELETE FROM product WHERE product_id =?";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, productId);
			preparedStatement.execute();
			System.out.println("刪除的id商品為" + productId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCutil.closeResource(connection, preparedStatement);
		}
	}

//	更新
	public void updateProduct(Product product) {
		String sql = "UPDATE product SET product_name=?,category=?,image_url=?,price=?,stock=?,description=? ,last_modified_date=? "
				+ "WHERE product_id =?";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(8, product.getProductId());
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setString(2, product.getCategory());
			preparedStatement.setString(3, product.getImageUrl());
			preparedStatement.setInt(4, product.getPrice());
			preparedStatement.setInt(5, product.getStock());
			preparedStatement.setString(6, product.getDescription());
//			現在更新時間
			LocalDate nowDate = LocalDate.now();
			preparedStatement.setDate(7, java.sql.Date.valueOf(nowDate));
			preparedStatement.execute();
			System.out.println("更新的商品id為" + product.getProductId());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCutil.closeResource(connection, preparedStatement);
		}
	}

//	查詢
//	利用id查詢單筆商品
	public Product findProductById(Integer productId) {
		String sql = "SELECT * FROM product WHERE product_id=?";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Product product = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, productId);
			rs = preparedStatement.executeQuery();
			rs.next();
			String productName = rs.getString("product_name");
			String category = rs.getString("category");
			String imageUrl = rs.getString("image_url");
			Integer price = rs.getInt("price");
			Integer stock = rs.getInt("stock");
			String description = rs.getString("description");
			Date createDate = rs.getDate("created_date");
			Date lastModifiedDate = rs.getDate("last_modified_date");
			product = new Product(productId, productName, category, imageUrl, price, stock, description, createDate,
					lastModifiedDate);
			System.out.println(product.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCutil.closeResource(connection, preparedStatement, rs);
		}
		return product;
	}

//	查詢全部
	public List<Product> findAllProducts() {
		String sql = "SELECT * FROM product";
		Connection connection = JDBCutil.getConnection();
		ArrayList<Product> productList = new ArrayList<Product>();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Integer productId = rs.getInt("product_id");
				String productName = rs.getString("product_name");
				String category = rs.getString("category");
				String imageUrl = rs.getString("image_url");
				Integer price = rs.getInt("price");
				Integer stock = rs.getInt("stock");
				String description = rs.getString("description");
				Date createDate = rs.getDate("created_date");
				Date lastModifiedDate = rs.getDate("last_modified_date");
				Product product = new Product(productId, productName, category, imageUrl, price, stock, description,
						createDate, lastModifiedDate);
				productList.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCutil.closeResource(connection, preparedStatement, rs);
		}
		return productList;
	}

}

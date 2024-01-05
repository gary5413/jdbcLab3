package jdbcLab3.lab08.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;import java.util.Date;
import java.util.List;

import jdbcLab3.lab08.model.Product;
import jdbcLab3.util.JDBCutil;

public class ProductDao {
//	新增商品
	public void saveProduct(Product product) {
		String sql="INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date)"
				+ "VALUES (?,?,?,?,?,?,?,?)";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement prepareStatement=null;
		try {
			prepareStatement= connection.prepareStatement(sql);
			prepareStatement.setString(1, product.getProductName());
			prepareStatement.setString(2, product.getCategory());
			prepareStatement.setString(3, product.getImageUrl());
			prepareStatement.setInt(4, product.getPrice());
			prepareStatement.setInt(5, product.getStock());
			prepareStatement.setString(6, product.getDescription());
			LocalDate now = LocalDate.now();
			prepareStatement.setDate(7, java.sql.Date.valueOf(now));
			prepareStatement.setDate(8, java.sql.Date.valueOf(now));
			int row = prepareStatement.executeUpdate();
			System.out.println("新增"+row+"筆");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, prepareStatement);
		}
	}
//	刪除商品
	public void deleteProductById(Integer productId) {
		String sql="DELETE FROM product WHERE product_id=?";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement prepareStatement=null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, productId);
			int row = prepareStatement.executeUpdate();
			System.out.println("刪除id為"+productId+"的商品");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, prepareStatement);
		}
	}
//	更新商品
	public void updateProduct(Integer productId,Product product) {
		String sql="UPDATE product SET product_name=?, category=?, image_url=?, price=?, stock=?, description=?, last_modified_date=? "
				+ " WHERE product_id =?";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement prepareStatement=null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, product.getProductName());
			prepareStatement.setString(2, product.getCategory());
			prepareStatement.setString(3, product.getImageUrl());
			prepareStatement.setInt(4, product.getPrice());
			prepareStatement.setInt(5, product.getStock());
			prepareStatement.setString(6, product.getDescription());
			LocalDate now = LocalDate.now();
			prepareStatement.setDate(7, java.sql.Date.valueOf(now));
			prepareStatement.setInt(8, productId);
			prepareStatement.execute();
			System.out.println("更新id為"+productId+"的商品");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, prepareStatement);
		}
	}
	
//	查詢商品
	public Product findProductById(Integer productId) {
		String sql="SELECT * FROM product WHERE product_id=?";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement prepareStatement=null;
		ResultSet rs=null;
		Product product=null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, productId);
			rs = prepareStatement.executeQuery();
			rs.next();
			String productName=rs.getString("product_name");
			String category=rs.getString("category");
			String imageUrl=rs.getString("image_url");
			Integer price=rs.getInt("price");
			Integer stock=rs.getInt("stock");
			String description=rs.getString("description");
			Date createDate=rs.getDate("created_date");
			Date lastModifiedDate=rs.getDate("last_modified_date");
			product = new Product(productId,productName,category,imageUrl,price,stock,description,createDate,lastModifiedDate);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, prepareStatement, rs);
		}
		return product;
	}
	public List<Product> findAllProducts(){
		String sql="SELECT * FROM product";
		ArrayList<Product> productList = new ArrayList<Product>();
		Connection connection = JDBCutil.getConnection();
		PreparedStatement prepareStatement=null;
		ResultSet rs=null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			rs = prepareStatement.executeQuery();
			while (rs.next()) {
				Integer productId=rs.getInt("product_id");
				String productName=rs.getString("product_name");
				String category=rs.getString("category");
				String imageUrl=rs.getString("image_url");
				Integer price=rs.getInt("price");
				Integer stock=rs.getInt("stock");
				String description=rs.getString("description");
				Date createDate=rs.getDate("created_date");
				Date lastModifiedDate=rs.getDate("last_modified_date");
				Product product = new Product(productId,productName,category,imageUrl,price,stock,description,createDate,lastModifiedDate);
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, prepareStatement, rs);
		}
		return productList;
	}
	
}

package jdbcLab3.lab04;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbcLab3.util.JDBCutil;

public class DemoBlobImage {
	
	
	public void saveImage() {
		String sql="INSERT INTO users(name,password,photo) VALUES(?,?,?)";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement prepareStatement=null;
		FileInputStream fileInputStream=null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1,"catprogramer");
			prepareStatement.setString(2,"meow");
			fileInputStream = new FileInputStream(new File("resource/catprogramer.jpg"));
			prepareStatement.setBinaryStream(3, fileInputStream);
			int row = prepareStatement.executeUpdate();
			System.out.println("新增"+row+"筆");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, prepareStatement);
			try {
				fileInputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void getImageById(Integer id) {
		String sql="SELECT photo FROM users WHERE id=?";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement prepareStatement=null;
		ResultSet rs=null;
		FileOutputStream fileOutputStream=null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1,id);
			rs = prepareStatement.executeQuery();
//		注意是sql.blob
			rs.next();
			Blob blob = rs.getBlob(1);
			fileOutputStream = new FileOutputStream("resource/test3.jpg");
//			fileOutputStream.write(blob.getBytes(1, (int) blob.length()));
			fileOutputStream.write(blob.getBytes(1,5000));
			System.out.println("圖片輸出完成");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, prepareStatement, rs);
			try {
				fileOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void getImageById2(Integer id) {
		String sql="SELECT photo FROM users WHERE id=?";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement prepareStatement=null;
		ResultSet rs=null;
		FileOutputStream fileOutputStream=null;
		BufferedOutputStream bufferedOutputStream=null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1,id);
			rs = prepareStatement.executeQuery();
//		注意是sql.blob
			rs.next();
			Blob blob = rs.getBlob(1);
			fileOutputStream = new FileOutputStream("resource/test2.jpg");
			bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
			bufferedOutputStream.write(blob.getBytes(1,(int) blob.length()));
			bufferedOutputStream.flush();
			System.out.println("圖片輸出完成");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, prepareStatement, rs);
			try {
				bufferedOutputStream.close();
				fileOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		DemoBlobImage demoBlobImage = new DemoBlobImage();
//		demoBlobImage.saveImage();
//		demoBlobImage.getImageById(7);
		demoBlobImage.getImageById2(7);
	}

}

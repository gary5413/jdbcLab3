package jdbcLab183.lab05;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jdbcLab183.utils.JDBCutil;



public class DemoBlobImage {
	
//	貓咪是Java程式工程師在電腦前面寫程式,coding,卡通風格
	public void saveImage() {
		String sql="INSERT INTO users(name,password,photo) VALUES(?,?,?)";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement preparedStatment=null;
		FileInputStream fileInputStream=null;
		try {
			preparedStatment = connection.prepareStatement(sql);
			preparedStatment.setString(1, "catProgrammer");
			preparedStatment.setString(2, "meow");
			fileInputStream = new FileInputStream(new File("resource/catprogramer.jpg"));
			preparedStatment.setBinaryStream(3, fileInputStream);
			int row = preparedStatment.executeUpdate();
			System.out.println("新增"+row+"筆資料");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, preparedStatment);
			try {
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void getImage(Integer id) {
		String sql="SELECT photo FROM users WHERE id =?";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement preparedStament=null;
		ResultSet rs =null;
		FileOutputStream fileOutputStream=null;
		try {
			preparedStament = connection.prepareStatement(sql);
			preparedStament.setInt(1, id);
			 rs = preparedStament.executeQuery();
			rs.next();
//			java.sql
			Blob blob= rs.getBlob("photo");
			 fileOutputStream = new FileOutputStream("resource/test.jpg");
			fileOutputStream.write(blob.getBytes(1,(int) blob.length()));
			System.out.println("圖片輸出完成");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, preparedStament, rs);
			try {
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
		demoBlobImage.getImage(8);
	}

}

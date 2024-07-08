package jdbcLab187.lab01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DemoConnection31 {

	public static void main(String[] args) {
		
		
		try(FileInputStream  fileInputStream = new FileInputStream(new File("src/jdbc183.properties"))) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			try(Connection connection = DriverManager.getConnection(url,user,password);){
				boolean status = !connection.isClosed();
				System.out.println("連線狀態："+status);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}

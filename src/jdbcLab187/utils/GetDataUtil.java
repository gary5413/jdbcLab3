package jdbcLab187.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetDataUtil {
	public static List<String> getData(String dataPath){
		ArrayList<String> list = new ArrayList<String>();
		try (FileInputStream fileInputStream = new FileInputStream(new File(dataPath));
				InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);){
			String content="";
			while (bufferedReader.ready()) {
				content= bufferedReader.readLine();
				System.out.println(content);
				list.add(content);
			}
//			刪除標頭
			list.remove(0);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String> getUrl(String urlString) throws IOException{
		ArrayList<String> list = new ArrayList<String>();
		URL url = new URL(urlString);
		InputStream inputStream = url.openStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		try {
			String content="";
			while (bufferedReader.ready()) {
				content = bufferedReader.readLine();
//				System.out.println(content);
//				加到list裏面
				list.add(content);
			}
//			刪除第一筆
			list.remove(0);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			bufferedReader.close();
			inputStreamReader.close();
		}
		return list;
	}
}

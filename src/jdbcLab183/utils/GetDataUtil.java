package jdbcLab183.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetDataUtil {
	public static List<String> getData(String dataPath) {
		ArrayList<String> list = new ArrayList<String>();
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileInputStream = new FileInputStream(new File(dataPath));
			inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String content = "";
			while (bufferedReader.ready()) {
				content = bufferedReader.readLine();
//				System.out.println(content);
				list.add(content);
			}
//			刪除標頭
			list.remove(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
				inputStreamReader.close();
				fileInputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public static List<String> getUrl(String urlString){
		ArrayList<String> list = new ArrayList<String>();
		URL url;
		try {
			url = new URL(urlString);
			
			try (InputStream inputStream = url.openStream();
					InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
					){
				String content="";
				while (bufferedReader.ready()) {
					content = bufferedReader.readLine();
//					System.out.println(content);
//					加到list裏面
					list.add(content);
				}
//				刪除第一筆
				list.remove(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return list;
	}
}

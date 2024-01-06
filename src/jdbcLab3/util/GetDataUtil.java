package jdbcLab3.util;

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

/*
 * 
 */
public class GetDataUtil {

	public List<String> getDataContent(String dataPath) {
		ArrayList<String> list = new ArrayList<String>();
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		FileInputStream fileInputStream = null;
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
//		刪除第一筆標頭
			list.remove(0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
				inputStreamReader.close();
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public List<String> getUrlDataContent(String dataUrl) {
		ArrayList<String> list = new ArrayList<String>();
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			URL url = new URL(dataUrl);
			InputStream inputStream = url.openStream();
			inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String content = "";
			while (bufferedReader.ready()) {
				content = bufferedReader.readLine();
//				System.out.println(content);
				list.add(content);
			}
//			刪除第一筆標頭
			list.remove(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStreamReader.close();
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}

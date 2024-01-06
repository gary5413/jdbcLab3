package jdbcLab3.lab08.server;

import java.util.ArrayList;
import java.util.List;

import jdbcLab3.lab08.model.Etf;

public class EtfServer {
	
	public List<Etf> getEtfData2(List<String> etfDataList) {
		ArrayList<Etf> list = new ArrayList<Etf>();
//		String[] tokens = etfDataList.get(0).split(",");
//		System.out.println(tokens[0]);
//		System.out.println(tokens[1]);
//		System.out.println(tokens[2]);
		for(int i=0;i<etfDataList.size();i++) {
			String[] tokens = etfDataList.get(i).split(",");
			System.out.println("正要輸入:"+tokens[1]+","+tokens[2]+","+tokens[3]);
			Etf etf = new Etf();
			etf.setStockId(tokens[1]);
			etf.setStockName(tokens[2]);
			etf.setMonthQuantity(Integer.parseInt(tokens[3]));
			list.add(etf);
		}
		return list;
	}
	
	public List<String> getEtfData(List<String> etfDataList) {
		ArrayList<String> list = new ArrayList<String>();
//		String[] tokens = etfDataList.get(0).split(",");
//		System.out.println(tokens[0]);
//		System.out.println(tokens[1]);
//		System.out.println(tokens[2]);
		for(int i=0;i<etfDataList.size();i++) {
			String[] tokens = etfDataList.get(i).split(",");
			System.out.println("正要輸入:"+tokens[1]+","+tokens[2]+","+tokens[3]);
			for(int j=1;j<4;j++) {
//				System.out.println(tokens[j]);
				list.add(tokens[j]);
			}
		}
		return list;
	}
	
}

package jdbcLab179.lab09.service;

import java.util.ArrayList;
import java.util.List;

import jdbcLab179.lab09.model.Etf;



public class EtfService {
	public List<Etf> getEtfData(List<String> dataList) {
		ArrayList<Etf> list = new ArrayList<Etf>();
		for(int i=0;i<dataList.size();i++) {
			String[] tokens= dataList.get(i).split(",");
			System.out.println(tokens[0]+","+tokens[1]);
			Etf etf = new Etf();
			etf.setStockId(tokens[1]);
			etf.setStockName(tokens[2]);
			etf.setMonthQuantity(Integer.parseInt( tokens[3]));
			list.add(etf);
		}
		return list;
	}
}

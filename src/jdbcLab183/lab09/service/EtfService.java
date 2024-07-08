package jdbcLab183.lab09.service;

import java.util.ArrayList;
import java.util.List;



import jdbcLab183.lab09.model.Etf;

public class EtfService {
	public List<Etf> getEtfData(List<String> dataList) {
		ArrayList<Etf> list = new ArrayList<Etf>();
		for(int i=0;i<dataList.size();i++) {
			String[] tokens= dataList.get(i).split(",");
//			System.out.println(tokens[0]+","+tokens[1]);
			Etf etf = new Etf();
			String newStockId=tokens[1].replaceAll("\"", "");
			etf.setStockId(newStockId);
			etf.setStockName(tokens[2]);
			etf.setEtfId((tokens[4]));
			etf.setEtfName(tokens[5]);
			list.add(etf);
		}
		return list;
	}
}

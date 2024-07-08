package jdbcLab183.lab09;

import java.util.List;

import jdbcLab183.lab09.dao.EtfDao;
import jdbcLab183.lab09.model.Etf;
import jdbcLab183.lab09.service.EtfService;
import jdbcLab183.utils.GetDataUtil;



public class DemoEtf {

	public static void main(String[] args) {
		String dataPath="/Users/garylee/Downloads/ETFRank_202403.csv";
//		String dataUrl="https://www.twse.com.tw/zh/ETFReport/ETFRank?response=open_data";
		List<String> datalist = GetDataUtil.getData(dataPath);
//		List<String> datalist = GetDataUtil.getData(dataUrl);
//		EtfService etfService = new EtfService();
//		List<Etf> etfData = etfService.getEtfData(datalist);
//		EtfDao etfDao= new EtfDao();
//		for(Etf etf:etfData) {
//			etfDao.saveEtf(etf);
//		}
	}

}

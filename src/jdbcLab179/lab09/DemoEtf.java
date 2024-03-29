package jdbcLab179.lab09;

import java.util.List;

import jdbcLab179.lab09.dao.EtfDao;
import jdbcLab179.lab09.model.Etf;
import jdbcLab179.lab09.service.EtfService;
import jdbcLab179.utils.GetDataUtil;



public class DemoEtf {
	public static void main(String[] args) {
		
		GetDataUtil getDataUtil = new GetDataUtil();
		String dataPath="C:/Shared/JDBC/etfData.csv";
		List<String> datalist = getDataUtil.getData(dataPath);
		
		EtfService etfService = new EtfService();
		List<Etf> etfData = etfService.getEtfData(datalist);
		
		EtfDao etfDao = new EtfDao();
		for (Etf etf : etfData) {
			etfDao.saveEtf(etf);
		}
	}
}

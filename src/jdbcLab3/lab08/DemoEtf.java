package jdbcLab3.lab08;

import java.util.List;

import jdbcLab3.lab08.dao.EtfDao;
import jdbcLab3.lab08.model.Etf;
import jdbcLab3.lab08.server.EtfServer;
import jdbcLab3.util.GetDataUtil;

public class DemoEtf {

	public static void main(String[] args) {
		GetDataUtil getDataUtil = new GetDataUtil();
//		String etf_url="https://opendata.tdcc.com.tw/getOD.ashx?id=2-41";
		String etf_path="D:/datasource/JDBC_jar/etf.csv";
		List<String> datalist = getDataUtil.getDataContent(etf_path);
		EtfServer etfServer = new EtfServer();
		List<Etf> etfData2 = etfServer.getEtfData2(datalist);
		EtfDao etfDao = new EtfDao();
		for (Etf etf : etfData2) {
			etfDao.saveEtf(etf);
		}
	}

}

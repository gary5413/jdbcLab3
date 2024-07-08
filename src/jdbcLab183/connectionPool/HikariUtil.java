package jdbcLab183.connectionPool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariUtil {
private HikariDataSource ds;
	
	public HikariDataSource openDataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/jdbcLabEEIT183");
		config.setUsername("root");
		config.setPassword("5413gary");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		ds = new HikariDataSource(config);
		return ds;
	}
	
	public void closeDataSource() {
		if(ds!=null) {
			ds.close();
		}
	}
}

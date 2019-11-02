package com.core.provider;

import org.apache.commons.dbcp.BasicDataSource;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author moubin.mo
 * @date: 2019/9/20 20:59
 */

@Component
public class DbProviderImpl implements DbProvider {

	@Value("${mysql.url}")
	private String defaultDbUrl;

	@Override
	public DefaultConfiguration getDbConfiguration() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUrl(defaultDbUrl);
		DefaultConfiguration configuration = new DefaultConfiguration();
		configuration.set(basicDataSource);
		configuration.setSQLDialect(SQLDialect.MYSQL);
		return configuration;
	}
}

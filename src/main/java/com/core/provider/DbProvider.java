package com.core.provider;

import org.jooq.impl.DefaultConfiguration;

/**
 * @author moubin.mo
 * @date: 2019/9/20 20:59
 */

public interface DbProvider {

	/**
	 * 获取数据库连接配置
	 */
	DefaultConfiguration getDbConfiguration();
}

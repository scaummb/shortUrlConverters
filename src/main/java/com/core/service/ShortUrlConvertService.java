package com.core.service;

import com.core.pojo.command.LongUrlCommand;
import com.core.pojo.command.ShortUrlCommand;
import com.core.pojo.dto.LongUrlDTO;
import com.core.pojo.dto.ShortUrlDTO;

/**
 * @author moubin.mo
 * @date: 2019/8/31 15:04
 */

public interface ShortUrlConvertService {

	/**
	 * 长链接转短链接
	 * @param LongUrlCommand
	 * @return ShortUrlDTO
	 */
	ShortUrlDTO convertLongUrlToShortUrl(LongUrlCommand command);

	/**
	 * 短链接转长链接
	 * @param ShortUrlCommand
	 * @return LongUrlDTO
	 */
	LongUrlDTO reconvertToShortUrl(ShortUrlCommand cmd);

}

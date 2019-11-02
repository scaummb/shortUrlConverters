package com.core.service;

import com.core.kafka.KafkaMessage;
import com.core.kafka.KafkaSender;
import com.core.pojo.command.LongUrlCommand;
import com.core.pojo.command.ShortUrlCommand;
import com.core.pojo.dto.LongUrlDTO;
import com.core.pojo.dto.ShortUrlDTO;
import com.core.provider.ShortUrlConvertProvider;
import com.core.utils.converter.ConverterUtils;
import com.core.utils.converter.ShortUrlGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author moubin.mo
 * @date: 2019/8/31 15:06
 */

@Component
public class ShortUrlConvertServiceImpl implements ShortUrlConvertService {

	@Autowired
	private ShortUrlConvertProvider shortUrlConvertProvider;

	@Autowired
	private KafkaSender kafkaSender;

	@Override
	public ShortUrlDTO convertLongUrlToShortUrl(LongUrlCommand command) {
		if (StringUtils.isEmpty(command.getLongUrl())){
			throw new RuntimeException("Long url is null, please recheck your command!!");
		}

		// 长链接转短链接
		Long urlId = shortUrlConvertProvider.getUrlSequnceId();
		String shortUrl = ShortUrlGenerator.generateShortUrl(command.getLongUrl(), urlId);

		//TODO 使用kafka解耦数据库操作,topic=(shortUrlConvertEventPersist),partition=(urlId%200),groupid='application-name'-group-id,只有一份消费者
		kafkaSender.sendTestMessage(new KafkaMessage());

		// 长链接存于redis/mysql
		shortUrlConvertProvider.saveSeqIdAndUrls(urlId, shortUrl, command.getLongUrl());

		ShortUrlDTO shortUrlDTO = new ShortUrlDTO();
		shortUrlDTO.setUrlId(urlId);
		shortUrlDTO.setShortUrl(shortUrl);
		return shortUrlDTO;
	}

	@Override
	public LongUrlDTO reconvertToShortUrl(ShortUrlCommand command) {
		if (StringUtils.isEmpty(command.getShortUrl())){
			throw new RuntimeException("Long url is null, please recheck your command!!");
		}
		String[] split = command.getShortUrl().split(":");
		String shortUrl = split[0];
		String urlId = split[1];

		// 短链接转长链接
		String longUrl = shortUrlConvertProvider.getLongUrl(shortUrl, urlId);
		String redirectUrl = ConverterUtils.buildRedirectUrl(longUrl);

		LongUrlDTO longUrlDTO = new LongUrlDTO();
		longUrlDTO.setLongUrl(redirectUrl);
		longUrlDTO.setUrlId(Long.valueOf(urlId));
		return longUrlDTO;
	}



}

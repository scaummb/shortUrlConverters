package com.core.provider;

import com.core.pojo.constants.UrlMappingStatus;
import com.core.pojo.entity.UrlMapping;
import com.core.utils.DateHelper;
import com.db.pojo.tables.daos.EhUrlMappingDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @author moubin.mo
 * @date: 2019/8/31 15:44
 */

@Component
public class ShortUrlConvertProviderImpl implements ShortUrlConvertProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShortUrlConvertProviderImpl.class);

	@Autowired
	private RedisProvider redisProvider;

	@Autowired
	private SequenceProvider sequenceProvider;

	@Autowired
	private DbProvider dbProvider;

	@Override
	public Long getUrlSequnceId() {
		return redisProvider.getUrlSequnceId();
	}

	@Override
	public Long saveSeqIdAndUrls(Long seqId, String shortUrl, String longUrl) {
		redisProvider.saveSeqIdAndUrls(seqId, shortUrl, longUrl);
		saveShortUrl(seqId, shortUrl, longUrl);
		return seqId;

	}

	@Override
	public String getLongUrl(String shortUrl, String urlId) {
		return redisProvider.getLongUrl(shortUrl, urlId);
	}

	@Override
	public Long saveShortUrl(Long seqId, String shortUrl, String longUrl) {

		Long id = this.sequenceProvider.getNextSequence("");
		UrlMapping urlMapping = new UrlMapping();
		urlMapping.setId(id);
		urlMapping.setCreateTime(new Timestamp(DateHelper.currentGMTTime().getTime()));
		urlMapping.setLongUrl(longUrl);
		urlMapping.setShortUrl(shortUrl);
		urlMapping.setStatus(UrlMappingStatus.VALID.getStatus());
		urlMapping.setUrlId(seqId);
		EhUrlMappingDao dao = new EhUrlMappingDao(dbProvider.getDbConfiguration());
		dao.insert(urlMapping);

		LOGGER.debug("Create urlMapping success, urlMapping = {}", urlMapping.toString());
		return seqId;
	}


}

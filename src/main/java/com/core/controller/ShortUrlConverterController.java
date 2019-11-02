package com.core.controller;

import com.core.pojo.command.LongUrlCommand;
import com.core.pojo.command.ShortUrlCommand;
import com.core.pojo.dto.LongUrlDTO;
import com.core.pojo.dto.ShortUrlDTO;
import com.core.service.ShortUrlConvertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author moubin.mo
 * @date: 2019/8/31 11:49
 */

@RestController
@RequestMapping("/evh/shortUrl")
public class ShortUrlConverterController {

	private final Logger LOGGER = LoggerFactory.getLogger(ShortUrlConverterController.class);

	@Autowired
	private ShortUrlConvertService shortUrlConvertService;

	@RequestMapping("convertToShortUrl")
	@ResponseBody
	public ShortUrlDTO convertToShortUrl(LongUrlCommand cmd){
		LOGGER.info("Test long url convert to short url, cmd = {}", cmd.getLongUrl());
		ShortUrlDTO shortUrlDTO = shortUrlConvertService.convertLongUrlToShortUrl(cmd);
		LOGGER.info("Test end, url id = {}, short url = {}", shortUrlDTO.getUrlId(), shortUrlDTO.getShortUrl());
		return shortUrlDTO;
	}

	@RequestMapping("reconvertToLongUrl")
	@ResponseBody
	public LongUrlDTO reconvertToLongUrl(ShortUrlCommand cmd){
		LOGGER.info("Test short url reconvert to long url, cmd = {}", cmd.getShortUrl());
		LongUrlDTO longUrlDTO = shortUrlConvertService.reconvertToShortUrl(cmd);
		LOGGER.info("Test end, url id = {}, short url = {}", longUrlDTO.getUrlId(), longUrlDTO.getLongUrl());
		return longUrlDTO;
	}

	@RequestMapping("redirectToLongUrl")
	@ResponseBody
	public void redirectToLongUrl(HttpServletRequest request, HttpServletResponse response, ShortUrlCommand cmd) throws IOException {
		LOGGER.info("Test short url redirect to long url, cmd = {}", cmd);
		try{
            LongUrlDTO longUrlDTO = shortUrlConvertService.reconvertToShortUrl(cmd);
            if (!StringUtils.isEmpty(longUrlDTO.getLongUrl())){
                response.sendRedirect(longUrlDTO.getLongUrl());
                LOGGER.debug("Test short url redirect to long url, redirectUrl = {}, longUrlDTO = {}", longUrlDTO.getLongUrl(), longUrlDTO);
            } else {
                LOGGER.error("Test short url redirect to long url, can not find long url mapping to short url, shortUrl = {}, longUrlDTO = {}", cmd.getShortUrl(), longUrlDTO);
            }
        } catch (Exception e) {
		    throw new RuntimeException("Test short url redirect to long url exception!!");
        }
	}
}


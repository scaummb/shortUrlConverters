package com.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * short url application
 * @author moubin.mo
 * @date: 2019/8/28 21:39
 */
@Controller
@EnableAutoConfiguration
@ComponentScan(value = {"com.core.*"})
public class ShortUrlConverterApplication {

	final static Logger LOGGER = LoggerFactory.getLogger(ShortUrlConverterApplication.class);

	@RequestMapping("/")
	@ResponseBody
	String home(){
		return "Hello World!";
	}

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		try {
			SpringApplication.run(ShortUrlConverterApplication.class, args);
		} catch(Throwable e) {
			long endTime = System.currentTimeMillis();
			LOGGER.error("App startup error, elapse={}"+ (endTime - startTime), e);
			System.exit(-1);
		}

		long endTime = System.currentTimeMillis();
		LOGGER.info("App end, elapse={}", (endTime - startTime));
	}
}


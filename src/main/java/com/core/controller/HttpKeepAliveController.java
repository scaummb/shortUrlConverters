//package com.core.controller;
//
//import com.core.pojo.web_message.FetchWebMessagesCommand;
//import com.core.pojo.web_message.WebMessageDTO;
//import com.core.pojo.web_message.WebMessageLongPollingCommand;
//import com.core.pojo.web_message.WebMessageLongPollingDTO;
//import com.core.service.web_message.WebMsgLongPoolingContainer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.async.DeferredResult;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
///**
// * @author moubin.mo
// * @date: 2019/11/7 11:04
// */
//@RestController
//@RequestMapping("/evh/http")
//public class HttpKeepAliveController {
//
//	private final Logger LOGGER = LoggerFactory.getLogger(HttpKeepAliveController.class);
//
//	@Autowired
//	private WebMsgLongPoolingContainer container;
//
//	@RequestMapping("testKeepAlive")
//	@ResponseBody
//	public DeferredResult<WebMessageLongPollingDTO> testKeepAlive(WebMessageLongPollingCommand command , HttpServletRequest request, HttpServletResponse response){
//		LOGGER.info("command = {}, request = {}, response = {}", command, request, response);
//		return container.watch(command, System.currentTimeMillis());
//	}
//
//	@RequestMapping("wewebMessagelongPolling")
//	@ResponseBody
//	public DeferredResult<WebMessageLongPollingDTO> webMessagelongPolling(WebMessageLongPollingCommand command){
//		return null;
//	}
//
//	@RequestMapping("fetchWebMessage")
//	@ResponseBody
//	public List<WebMessageDTO> fetchWebMessage(FetchWebMessagesCommand command){
//		return null;
//	}
//
//}

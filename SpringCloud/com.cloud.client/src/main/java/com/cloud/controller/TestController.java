package com.cloud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cloud.config.MyConfig;
import com.cloud.feign.MathClient;

@RestController
@RefreshScope		// 开启自动刷新配置
public class TestController {

	@Value("${spring.config.demo}")
	private String str;
	
	@Value("${my.data.date}")
	private String date;
	
	@Autowired
	MathClient mathClient;
	
	@RequestMapping("/config.json")
	public Object config(HttpServletRequest req,HttpServletResponse res)
	{
		String[] g = {str,date};
		return JSONObject.toJSON(g);
	}
	
	@ResponseBody
	@RequestMapping(value="/math.do")
	public String math(@RequestParam Integer number) {
		System.out.println("number" + number);
		return mathClient.math(number);
	}
	
	@RequestMapping("/table.json")
	public Object table(HttpServletRequest req,HttpServletResponse res)
	{
		return mathClient.table();
	}
	
	@Autowired
	MyConfig myConfig;
	
	@RequestMapping("/myConfig.json")
	public Object myconfig(HttpServletRequest req,HttpServletResponse res)
	{
		return JSONObject.toJSON(myConfig);
	}
	
	
}

package com.kgc.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	Logger logger = Logger.getLogger(IndexController.class);
	
	@RequestMapping({"/","/index"})
	public String index() {
		logger.debug("IndexController 请求成功!");
		//视图的名称
		//视图解析器依据我的返回字符串生成的地址,请求对象转发这个新的地址.
		return "index";
	}
	
	/**
	 * 参数传递 : view  => controller
	 */
	@RequestMapping("/index1")
	//定义一个请求参数参数
	public String index1(@RequestParam("userName") String param) {
		logger.debug(param);
		return "index";
	}

	@RequestMapping("/index2")
	//指定参数不是必须的
	public String index2(@RequestParam(name="userName",required=false) String param) {
		logger.debug(param);
		return "index";
	}
	
	@RequestMapping("/index3")
	//直接用方法的参数,来接收请求的参数,方法参数名和请求的参数名需要一致
	public String index3(String userName, String password) {
		logger.debug(userName);
		logger.debug(password);
		return "index";
	}
	
	/**
	 * 参数传递：controller => view  (ModelAndView)
	 */
	@RequestMapping("/index4")
	public ModelAndView index4(String userName, String password) {
		//模拟登陆
		logger.debug(userName);
		logger.debug(password);
		//success页面显示用户名
		ModelAndView mav = new ModelAndView();
		//添加数据至请求对象
		mav.addObject("userName", userName);
		//设置视图名称
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/index5")
	public String index5(String userName, String password, Model model) {
		//模拟登陆
		logger.debug(userName);
		logger.debug(password);
		//官方推荐的实现方案
		//接收一个Model参数,add至这个Model内容会被植入请求对象
		model.addAttribute("userName", userName);
		//model.addAllAttributes(map);
		return "index";
	}
}

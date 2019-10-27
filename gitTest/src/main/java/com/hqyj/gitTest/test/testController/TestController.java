package com.hqyj.gitTest.test.testController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping("/test/config")
	public String test(){
		return "hello world";
	}
}

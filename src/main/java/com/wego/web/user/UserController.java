package com.wego.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wego.web.pxy.Box;

import lombok.extern.log4j.Log4j;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
@RestController
@RequestMapping("/users")
@Log4j
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired UserMapper userMapper;
	@Autowired Box<Integer> box;
	public int rowCount(){
		
		int rowCount = userMapper.rowCount();
		System.out.println("로우카운트"+rowCount);
		box.put("rowCount", rowCount);
		return box.get("rowCount");
	}

		 
	 

}
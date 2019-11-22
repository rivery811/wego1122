package com.wego.web.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wego.web.pxy.Proxy;

@Component
public class UserService extends Proxy {
	@Autowired UserMapper userMapper;

	public  String test() {
	/*	List<Integer> list = Arrays.asList(1,2,3,4,5);
		Stream<Integer> intStream = list.stream();
		=> Stream<Integer> intStream = Arrays.asList(1,2,3,4,5).stream();
		=>Arrays.asList(1,2,3,4,5).stream().forEach(System.out::print)
		*/
		Arrays.asList(1,2,3,4,5).forEach(System.out::println);
		Arrays.asList(1,2,3,4,5).stream().forEach(System.out::println);
		IntStream.rangeClosed(101, 105).forEach(System.out::println);//포를 메소드로 찍는걸 메소드 참조로 쓴것
		new Random().ints().limit(5).forEach(System.out::println);
		
			
		return string("");
	}
	public String selectAll() {
		//Arrays.asList(userMapper.selectAll().get(5))
		//userMapper.selectAll().forEach(System.out::println);
		 List<User> ls = userMapper.selectAll();
		 List<String> ls2 = new ArrayList<>();
		 for(int i = 0; i<ls.size(); i++) {
			 ls2.add(ls.get(i).getUid());
		 }
		 
		 Stream.of(ls2)
		 .sorted()
		 .forEach(System.out::println);
		return "5";
	}

}

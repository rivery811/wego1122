package com.wego.web.pxy;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.stereotype.Component;



@Component("pxy")
public class Proxy {
	public String string (Object param) {
		
		Function<Object, String> f = String :: valueOf;
		return f.apply(param);
	}
	public int integer(String param) {
		Function<String,Integer> f = Integer ::parseInt;
		return f.apply(param);
	}
	public boolean equal(String a, String b) {
		BiPredicate<String, String> f = String::equals;
		return f.test(a, b);
	}
	
	public void printer(String t) {
		Consumer<String> c = System.out::print; 
		// 타입: 컨슈머 타입을 갖는것은  객체 객체니까 파라미터로 전달 가능 	
		//inventory.get().forEach(System.out::println);<- 포이치니까 안을 도는데 파라미터 시스아웃이인벤토리 겟의 안의 돌면서 찍힘 
		// 객체인지 메소드인지는 ()로 판단  () 있으면 기능 없으면 속성 없으면 곧 객체가 됨 
		c.accept(t);
	}

}

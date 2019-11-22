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
		// Ÿ��: ������ Ÿ���� ���°���  ��ü ��ü�ϱ� �Ķ���ͷ� ���� ���� 	
		//inventory.get().forEach(System.out::println);<- ����ġ�ϱ� ���� ���µ� �Ķ���� �ý��ƿ����κ��丮 ���� ���� ���鼭 ���� 
		// ��ü���� �޼ҵ������� ()�� �Ǵ�  () ������ ��� ������ �Ӽ� ������ �� ��ü�� �� 
		c.accept(t);
	}

}

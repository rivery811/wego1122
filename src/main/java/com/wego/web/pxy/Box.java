package com.wego.web.pxy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
@Component @Lazy
public class Box<T> {
	private HashMap<String,T> box;
	public Box() {
		box = new HashMap<>();
	}
	
	public void put(String s, T t) {box.put(s, t);}
	

	public void put(List<String>x, List<T>y) {
	
		for(int i =0 ; i <x.size() ;i++) {
			box.put(x.get(i), y.get(i));
		}
		box.forEach((k,v)-> System.out.print(String.format("%s : %s", k,v)));
	}
	public T get(String k) {
		Function<String, T> f = p -> box.get(p);
		return f.apply(k);
	}
	public HashMap<String, T> get(){
		return box; 
	}
	public int size() {return box.size();}


}

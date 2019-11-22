package com.wego.web.hdl;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import com.wego.web.user.User;
@Repository
public interface UserHandler {
	@Insert("insert into user (uid,pwd,uname,birth,gender,tel,pettype) values (\n" + 
			"  #{uid}, #{pwd},#{uname},#{birth},#{gender},#{tel},#{pettype}) ")
	public void insertUser(User u);

}

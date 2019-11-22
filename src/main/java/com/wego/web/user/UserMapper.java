package com.wego.web.user;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
public int rowCount();
public List<User> selectAll();
}

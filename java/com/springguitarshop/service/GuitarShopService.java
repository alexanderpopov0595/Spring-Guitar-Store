package com.springguitarshop.service;

import java.util.List;
import java.util.Map;

import com.springguitarshop.domain.Guitar;
import com.springguitarshop.domain.GuitarParams;
import com.springguitarshop.domain.User;

public interface GuitarShopService {
	//methods for users
	void addUser(User user);
	void saveUser(User user);
	User getUser(long l);
	User getUser(String login);
	void deleteUser(long id);
	//methods for guitars
	long addGuitar(Guitar guitar);
	void saveGuitar(Guitar guitar);
	void deleteGuitar(int id);	
	Guitar getGuitar(long id);	
	List<Guitar> selectGuitars(Map<String, String> params, int pageid, int perpage);
	int selectCount(Map<String, String> params);	
	List<Guitar> getAllGuitarsForUser(User user);
	
	
	
	
	
	
}

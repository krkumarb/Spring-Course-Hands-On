package com.springcourse.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static int userCount = 0;
	
	private static List<User> usersList = new ArrayList<>();
	
	static {
		usersList.add(new User(++userCount, "user 1", LocalDate.now().minusYears(26)));
		usersList.add(new User(++userCount, "user 2", LocalDate.now().minusYears(27)));
		usersList.add(new User(++userCount, "user 3", LocalDate.now().minusYears(28)));
	}
	
	public List<User> findAll(){
		return usersList;
	}
	
	public User save(User user) {
		user.setId(++ userCount);
		usersList.add(user);
		return user;
	}
	
	public User findOne(int id) {
		Predicate<User> predicate = user -> user.getId().equals(id);
		return usersList.stream().filter(predicate).findFirst().orElse(null);
		
	}
	
	public void deleteById(int id) {
		
		Predicate<User> predicate = user -> user.getId().equals(id);
		usersList.removeIf(predicate);
		
	}
	

}

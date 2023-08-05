package com.madhavasoft.rest.webservices.restfulwebservices.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.madhavasoft.rest.webservices.restfulwebservices.user.User;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<User>();
	static int sno=0;
	static {
		users.add(new User(++sno, "Mahendhar", LocalDate.now().minusYears(11)));
		users.add(new User(++sno, "Githa", LocalDate.now().minusYears(15)));
		users.add(new User(++sno, "Madhava", LocalDate.now().minusYears(40)));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		user.setId(++sno);
		users.add(user);
		System.out.println("The user has been added");
		return user;
	}
	
	
	public User findOne(int id) {
		Predicate<? super User> p =user -> user.getId().equals(id);
		//return users.stream().filter(p).findFirst().get().;
		//if we want to Return user defined 404 exception like - There was an unexpected error (type=Not Found, status=404).
		return users.stream().filter(p).findFirst().orElse(null);
	}

	public void removeById(int id) {
		Predicate<? super User> u=user->user.getId().equals(id);
		users.removeIf(u);
	}
	
	

	
}

package com.madhavasoft.rest.webservices.restfulwebservices.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhavasoft.rest.webservices.restfulwebservices.user.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

}

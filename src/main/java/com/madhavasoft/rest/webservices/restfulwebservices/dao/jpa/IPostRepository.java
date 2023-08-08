package com.madhavasoft.rest.webservices.restfulwebservices.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhavasoft.rest.webservices.restfulwebservices.user.Post;

public interface IPostRepository extends JpaRepository<Post, Integer> {

}

package com.test.resttemplate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.resttemplate.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByMobileNumber(String mobileNumber);

}

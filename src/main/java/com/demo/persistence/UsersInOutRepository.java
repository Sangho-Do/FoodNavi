package com.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.domain.Users;

public interface UsersInOutRepository extends JpaRepository<Users, Integer> {
	
	@Query("SELECT users FROM Users users WHERE users.userid=:userid ")
	public Users getUsersByUserid(@Param("userid") String userid);
	
	@Query("SELECT COUNT(users) FROM Users users ")
	public int getTotalUsersCount();
	
	public Users findFirstByOrderByUseqDesc();
	
	public List<Users> findAll();
	
}

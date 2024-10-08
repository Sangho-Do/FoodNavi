package com.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.domain.Food;
import com.demo.domain.History;
import com.demo.domain.Users;

public interface HistoryRepository extends JpaRepository<History, Integer> {
	@Query("SELECT history FROM History history "
			+ "WHERE history.user = :user "
			+ "AND history.servedDate IS NULL ")
	public List<History> getHistoryListNotConfirmedYet(@Param("user") Users user);
	
	@Query("SELECT history FROM History history "
			+ "WHERE history.user = :user "
			+ "AND history.servedDate IS NOT NULL ")
	public List<History> getHistoryListConfirmed(@Param("user") Users user);
	
	@Query("SELECT history FROM History history "
			+ "WHERE history.user = :user "
			+ "AND history.servedDate IS NULL "
			+ "AND history.food = :food ")
	public History getHistoryNotConfirmedYetByFood(@Param("user") Users user, @Param("food") Food food);
	
	@Query("SELECT history FROM History history "
			+ "WHERE history.user = :user "
			+ "AND history.food = :food ")
	public History getHistoryConfirmedByUserAndFood(@Param("user") Users user, @Param("food") Food food);
	
	@Query("SELECT history FROM History history "
			+ "WHERE history.servedDate IS NOT NULL ")
	public List<History> getHistoryListConfirmed();
	
	@Query("SELECT history FROM History history "
			+ "WHERE history.user = :user ")
	public List<History> getHistoryListByUser(@Param("user") Users user);
}

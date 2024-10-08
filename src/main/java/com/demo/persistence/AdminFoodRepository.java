package com.demo.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.domain.Food;


public interface AdminFoodRepository extends JpaRepository<Food, Integer> {

	@Query("SELECT food FROM Food food "
			+ "WHERE food.name LIKE %:searchWord% ")
	public Page<Food> getFoodList(@Param("searchWord") String searchWord, Pageable pageable);
	
	@Query(value="SELECT f FROM Food f "
			+ "INNER JOIN FoodDetail fd ON fd.fdseq = f.foodDetail.fdseq "
			+ "WHERE fd.fdseq = ?1 ")
	public List<Food> getFoodDetail(int fseq);
	
	public Food findFirstByOrderByFseqDesc();
}

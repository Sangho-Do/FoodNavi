package com.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.domain.FoodIngredient;

public interface FoodIngredientRepository extends JpaRepository<FoodIngredient, Integer> {
    @Query("SELECT fi FROM FoodIngredient fi WHERE fi.food.fseq = :fseq")
    public List<FoodIngredient> getFoodIngredientListByFood(@Param("fseq") int fseq);
}
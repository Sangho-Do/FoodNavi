package com.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.domain.FoodDetail;

public interface FoodDetailScanRepository extends JpaRepository<FoodDetail, Integer> {
    @Query("SELECT fd FROM FoodDetail fd WHERE fd.food.fseq = :fseq")
    public FoodDetail findByFseq(@Param("fseq") int fseq);
}

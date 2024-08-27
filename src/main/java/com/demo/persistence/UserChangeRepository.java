package com.demo.persistence;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.domain.UserChange;
import com.demo.domain.Users;

public interface UserChangeRepository extends JpaRepository<UserChange, Integer> {

        @Query(value = "SELECT uc FROM UserChange uc " +
                "WHERE uc.user = :user " +
                "ORDER BY CAST(uc.createdAt AS TIMESTAMP) desc ")
        List<UserChange> findRecentChanges(@Param("user") Users user);

        UserChange findByUserAndCreatedAtBetween(Users user, Date startDate, Date endDate);

}

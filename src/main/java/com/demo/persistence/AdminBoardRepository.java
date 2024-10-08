package com.demo.persistence;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.domain.Board;

public interface AdminBoardRepository extends JpaRepository<Board, Integer> {

	@Query("SELECT board FROM Board board "
			+ "WHERE board.title LIKE %:title% "
			+ "AND board.content LIKE %:content% ")
	public Page<Board> getBoardList(@Param("title") String title, @Param("content") String content, Pageable pageable);
	
}

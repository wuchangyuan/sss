package com.sss.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sss.pojo.Rank;

public interface RankDao extends JpaRepository<Rank, Integer> {

	Page<Rank> findAll(Pageable pageable);

	@Modifying
	@Query("update Rank set score =:score where groupNo=:groupNo")
	public int updateRank(int score, int groupNo);

	List<Rank> findByGroupNo(Integer groupNo);

	List<Rank> findAll(Specification<Rank> specification);
}

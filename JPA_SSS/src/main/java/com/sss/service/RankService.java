package com.sss.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sss.pojo.Rank;

public interface RankService {

	public boolean saveRank(Rank rank);

	public boolean updateRank(Rank rank);

	public boolean deleteRank(Integer id);

	Page<Rank> getRankPageList(int pageNum, int pageSize);

	List<Rank> findByGroupNo(Integer groupNo) throws Exception;

	Rank getRankById(Integer id);

	List<Rank> findAllById(Integer id);
}

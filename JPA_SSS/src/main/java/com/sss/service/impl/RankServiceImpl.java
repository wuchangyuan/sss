package com.sss.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sss.dao.RankDao;
import com.sss.pojo.Rank;
import com.sss.service.RankService;

@Service
@Transactional
public class RankServiceImpl implements RankService {

	@Autowired
	private RankDao dao;

	@Override
	public boolean saveRank(Rank rank) {
		// TODO Auto-generated method stub
		return dao.save(rank) != null;
	}

	@Override
	public boolean updateRank(Rank rank) {
		// TODO Auto-generated method stub
		return dao.updateRank(rank.getScore(), rank.getGroupNo()) > 0;
	}

	@Override
	public boolean deleteRank(Integer id) {
		// TODO Auto-generated method stub
		try {
			dao.delete(id);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Page<Rank> getRankPageList(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC, "id");
		Pageable pageable = new PageRequest(pageNum - 1, pageSize, sort);
		return dao.findAll(pageable);
	}

	@Override
	public List<Rank> findByGroupNo(Integer groupNo) throws Exception {
		// TODO Auto-generated method stub
		List<Rank> list = dao.findByGroupNo(groupNo);
		if (list != null && list.size() > 0) {
			throw new Exception("组号已经存在");
		}
		return list;
	}

	@Override
	public Rank getRankById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findOne(id);
	}

	@Override
	public List<Rank> findAllById(Integer id) {
		// TODO Auto-generated method stub
		List<Rank> list = dao.findAll(new Specification<Rank>() {

			@Override
			public Predicate toPredicate(Root<Rank> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return cb.gt(root.get("id").as(Integer.class), id);
			}
		});
		return list;
	}
}

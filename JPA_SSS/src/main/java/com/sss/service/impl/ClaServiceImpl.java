package com.sss.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sss.dao.ClaDao;
import com.sss.pojo.Cla;
import com.sss.service.ClaService;

@Service
@Transactional
public class ClaServiceImpl implements ClaService {
	@Autowired
	private ClaDao dao;

	@Override
	public List<Cla> getClaList() {
		// TODO Auto-generated method stub
		return dao.findAll(new Sort(Direction.ASC, "id"));
	}

	@Override
	public Cla getCla(Integer id) {
		// TODO Auto-generated method stub
		return dao.findOne(id);
	}

	@Override
	public List<Cla> getClaByName(String name) throws Exception {
		// TODO Auto-generated method stub
		List<Cla> list = dao.findByName(name);
		if (list != null && list.size() > 0) {
			throw new Exception("班级名字已存在");
		}
		return list;
	}

	@Override
	public void add(Cla cla) {
		// TODO Auto-generated method stub
		dao.save(cla);
	}

	@Override
	public List<Cla> getClaById(Integer id) {
		// TODO Auto-generated method stub
		List<Cla> list = dao.findAll(new Specification<Cla>() {

			@Override
			public Predicate toPredicate(Root<Cla> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Path<Object> path = root.get("id");
				Predicate predicate = cb.gt(path.as(Integer.class), id);
				return predicate;
			}
		});
		return list;
	}

}

package com.sss.dao;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sss.pojo.Cla;

public interface ClaDao extends JpaRepository<Cla, Integer> {
	List<Cla> findByName(String name);

	List<Cla> findAll(Specification<Cla> specification);
}

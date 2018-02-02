package com.sss.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sss.pojo.Student;

public interface StudentDao extends JpaRepository<Student, Integer> {
	Page<Student> findAll(Specification<Student> specification, Pageable pageable);

	List<Student> findByAgeAfter(Integer age);

	List<Student> findByIdNotIn(Integer[] ids);

}

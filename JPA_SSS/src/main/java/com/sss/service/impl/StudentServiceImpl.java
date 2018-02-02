package com.sss.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
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

import com.sss.dao.StudentDao;
import com.sss.pojo.Cla;
import com.sss.pojo.Student;
import com.sss.service.StudentService;

@Transactional
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao dao;
	@Autowired
	private EntityManager manager;

	@Override
	public void edit(Student student) {
		// TODO Auto-generated method stub
		// manager.persist(student);
		dao.save(student);
	}

	@Override
	public Page<Student> list(int pageNum, int pageSize, Student student) {
		// TODO Auto-generated method stub
		// List<Student> list = dao.findAll();
		// Query query = manager.createQuery("select s from Student s order by s.id");
		// List<Student> list = query.getResultList();
		Sort sort = new Sort(Direction.DESC, "id");
		Pageable pageable = new PageRequest(pageNum, pageSize, sort);
		Page<Student> page = dao.findAll(new Specification<Student>() {

			@Override
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				List<Predicate> predicates = new ArrayList<>();
				Path<String> namepath = root.get("name");
				Path<String> sexpath = root.get("sex");
				Path<Cla> claidpath = root.get("cla");
				if (student != null) {
					if (student.getName() != null && student.getName() != "") {
						predicates.add(cb.like(namepath, "%" + student.getName() + "%"));
					}
					if (student.getSex() != null && student.getSex() != "") {
						predicates.add(cb.equal(sexpath, student.getSex()));
					}
					if (student.getCla() != null && student.getCla().getId() != null) {
						predicates.add(cb.equal(claidpath, student.getCla()));
					}
				}
				Predicate[] p = new Predicate[predicates.size()];
				return cb.and(predicates.toArray(p));
			}
		}, pageable);
		return page;
	}

	@Override
	public Student get(Integer id) {
		// TODO Auto-generated method stub
		Student student = dao.findOne(id);
		// Query query = manager.createQuery("select s from Student s where s.id =:id");
		// query.setParameter("id", id);
		// Student student = (Student) query.getSingleResult();
		return student;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		/*
		 * Query query = manager.createQuery("delete from Student where id = ?1");
		 * query.setParameter(1, id); query.executeUpdate();
		 */
		dao.delete(id);
	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub
		manager.merge(student);
	}

	@Override
	public void remove() {
		Cla cla = manager.find(Cla.class, 1);
		manager.remove(cla);
	}
}

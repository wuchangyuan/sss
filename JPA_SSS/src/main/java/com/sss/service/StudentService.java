package com.sss.service;

import org.springframework.data.domain.Page;

import com.sss.pojo.Student;

public interface StudentService {

	void edit(Student student);

	Page<Student> list(int pageNum, int pageSize, Student student);

	Student get(Integer id);

	void delete(Integer id);

	void update(Student student);

	public void remove();
}

package com.sss.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sss.dao.ClaDao;
import com.sss.dao.StudentDao;
import com.sss.pojo.Cla;
import com.sss.pojo.Student;

public class JPA_Test01 {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
		test04(context);
	}

	public static void test01(ApplicationContext context) {
		StudentDao dao = context.getBean(StudentDao.class);
		System.out.println(dao.count());
		List<Student> findByIdAfter = dao.findByAgeAfter(30);
		System.out.println(findByIdAfter);
		Integer[] ids = { 1, 4, 5 };
		System.out.println(dao.findByIdNotIn(ids));
	}

	private static void test02(ApplicationContext context) {
		// TODO Auto-generated method stub
		EntityManager manager = context.getBean(EntityManager.class);
		String jpql = "select s from Student s join s.cla c where c.id =:id";
		Query query = manager.createQuery(jpql);
		query.setParameter("id", 1);
		List list = query.getResultList();
		System.out.println(list);
	}

	private static void test03(ApplicationContext context) {
		EntityManager manager = context.getBean(EntityManager.class);
		Student student = manager.find(Student.class, 4);
		String jpql = "select c from Cla c join c.students as s where s=:student";
		Query query = manager.createQuery(jpql);
		query.setParameter("student", student);
		Cla cla = (Cla) query.getSingleResult();
		System.out.println(cla);
	}

	private static void test04(ApplicationContext context) {
		ClaDao dao = context.getBean(ClaDao.class);
		dao.delete(2);
	}
}

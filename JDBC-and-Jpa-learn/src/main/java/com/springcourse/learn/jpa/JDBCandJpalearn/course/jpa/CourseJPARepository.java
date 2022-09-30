package com.springcourse.learn.jpa.JDBCandJpalearn.course.jpa;

import org.springframework.stereotype.Repository;

import com.springcourse.learn.jpa.JDBCandJpalearn.course.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CourseJPARepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void insert(Course course) {
		entityManager.merge(course);
	}
	public void delete(long id) {
		entityManager.remove(this.findById(id));
	}
	public Course findById(long id) {
		return entityManager.find(Course.class, id);
	}

}

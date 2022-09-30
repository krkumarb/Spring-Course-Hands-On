package com.springcourse.learn.jpa.JDBCandJpalearn.course.springdatajpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springcourse.learn.jpa.JDBCandJpalearn.course.Course;

public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long>{

	List<Course> findByAuthor(String author);
	List<Course> findByName(String name);
}

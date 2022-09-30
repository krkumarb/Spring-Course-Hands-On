package com.springcourse.learn.jpa.JDBCandJpalearn.course.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.springcourse.learn.jpa.JDBCandJpalearn.course.Course;
import com.springcourse.learn.jpa.JDBCandJpalearn.course.jpa.CourseJPARepository;
import com.springcourse.learn.jpa.JDBCandJpalearn.course.springdatajpa.CourseSpringDataJpaRepository;

@Component
public class CourseCommandLineRunner implements CommandLineRunner{
	
//	@Autowired
//	private CourseJDBCRepository repository;
	
//	@Autowired
//	private CourseJPARepository repository;
	
	@Autowired
	private CourseSpringDataJpaRepository repository;

	@Override
	public void run(String... args) throws Exception {
		
		repository.save(new Course(1,"Learn Spring","dfbasd"));
		repository.save(new Course(2,"Learn AWS","ghjfv sdgr"));
		repository.save(new Course(3,"Learn JPA","nkjnsd sds "));
		
		repository.deleteById(2l);
		
		System.out.println(repository.findById(1l));
		System.out.println(repository.findById(3l));
		
		System.out.println(repository.findAll());
		System.out.println(repository.count());
		
		System.out.println(repository.findByAuthor("dfbasd"));
		System.out.println(repository.findByName("Learn JPA"));
	}

}

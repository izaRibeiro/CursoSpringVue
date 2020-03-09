package com.iza.microservicecoursemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.iza.microservicecoursemanagement.model.Course;
import com.iza.microservicecoursemanagement.model.Transaction;
import com.iza.microservicecoursemanagement.repository.CourseRepository;
import com.iza.microservicecoursemanagement.repository.TransactionRepository;

public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	private TransactionRepository transactionRepository;
	
	public List<Course> allCourses(){
		return courseRepository.findAll();
	}
	
	public Course findCourseById(Long courseId) {
		return courseRepository.findById(courseId).orElse(null);
	}
	
	public List<Transaction> findTransactionsOfUser(Long userId){
		return transactionRepository.findAllByUserId(userId);
	}
	
	public List<Transaction> findTransactionOfCourse(Long courseId){
		return transactionRepository.findAllByCourseId(courseId);
	}
	
	public Transaction saveTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}
}

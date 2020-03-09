package com.iza.microservicecoursemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iza.microservicecoursemanagement.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}

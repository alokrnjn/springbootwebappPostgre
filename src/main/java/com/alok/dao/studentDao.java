package com.alok.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.alok.model.Student;
import com.alok.model.StudentHelper;

public interface studentDao extends JpaRepository<Student,Integer>{

	public List<Student> findByAddress(String address);
	public List<Student> findByNameOrAddress(String name,String address);
	@Query("select s.name from Student s where s.roll=:roll")
	public String getStudentNameByRoll(@Param("roll")int roll);
	@Query("select s.name from Student s where s.roll=?1")
	public String getStudentByAddress(@Param("roll") int roll);
	@Query("select s.name,s.marks from Student s where s.roll=?1")
	public String getNameAndMarksfromStudent(int roll);
	@Query("select s.name,s.marks from Student s where s.address=?1")
	public List<String> getNameAndMarksfromStudentbyAddress(String address);
	@Query("select new com.alok.model.StudentHelper(s.name,s.address,s.marks) from Student s where s.roll=?1")
	public StudentHelper getNameAddressMarks(int roll);
}

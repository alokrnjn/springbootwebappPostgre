package com.alok.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alok.dao.studentDao;
import com.alok.exception.StudentNotFoundException;
import com.alok.model.Student;
import com.alok.model.StudentHelper;

@RestController
@RequestMapping("/jpaApp")
public class StudentController {

	@Autowired
	studentDao dao;
	
	@PostMapping(value = "/savestudent")
	public Student saveStudent(@RequestBody Student student) {
		
		Student inst = dao.save(student);
		return inst;
	}
	
	@GetMapping("/getStudent/{roll}")
	public Student getStudentByRoll(@PathVariable int roll) {
		
		Optional<Student> opt =dao.findById(roll);
		if(opt.isPresent())
			return  opt.get();
		else 
			throw new StudentNotFoundException("Student not found");
	}
/**/	@GetMapping("/getAllStudents")
	public List<Student> getAllStudents(){
		List<Student> students = dao.findAll();
		return students;
	}
/**/	@DeleteMapping("/deletestudent/{roll}")
	public Student deleteStudentByRoll(@PathVariable int roll) {
		
		Student st = dao.findById(roll).orElseThrow(()-> new StudentNotFoundException("Student doesn't Exist"));
		dao.delete(st);
		return st;
	}
	@PutMapping("/updateStudent")
	public Student updateStudentDetails(@RequestBody Student student) {
		Optional<Student> opt = dao.findById(student.getRoll());
		if(opt.isPresent()) {
		//	Student eso = opt.get();
			dao.save(student);
		}
		else
			throw new StudentNotFoundException("Student not Found");
		return student;
	}
	@PutMapping("/updateMarks/{roll}/{gmarks}")
	public Student updateMarksByRoll(@PathVariable int roll,@PathVariable int gmarks) {
		
		Student st = dao.findById(roll).orElseThrow(()-> new StudentNotFoundException("Student doesn't Exist"));
         st.setMarks(st.getMarks()+50);
		return dao.save(st);
     }
@GetMapping("/getStudentByAddress/{address}")
	public List<Student> getStudentByAddress(@PathVariable String address) {
		return dao.findByAddress(address);
	}
    @GetMapping("/getStudentNameByRoll/{roll}")
     public String getStudentNameByRoll(@PathVariable int roll) {
	return dao.getStudentNameByRoll(roll);
    }
    @GetMapping("/getStudentNameByRol/{roll}")
public String getStudentByAddress(@PathVariable int roll) {
				
		String name=dao.getStudentNameByRoll(roll);
		
		if(name == null) {
			throw new StudentNotFoundException("Student does not exist with the Roll "+roll);
		}
		else
			return name;
			
	} 
   @GetMapping("/getNameAndMarksByRoll/{roll}")
    public String getStudentNameAndMarksByRoll(@PathVariable int roll) {
        String result= dao.getNameAndMarksfromStudent(roll);
          //String[] sr = result.split(",");
        //System.out.println("Name is "+sr[0]);
		//System.out.println("Marks is "+sr[1]);
        if(result == null) {
			throw new StudentNotFoundException("Student does not exist with the Roll "+roll);
        }
        else
		return result;
    }
   @GetMapping("/getNameAndMarksByAddress/{address}")
	public List<String> getStudentNameAndMarksByRoll(@PathVariable String address) {
		
		
		List<String> result=dao.getNameAndMarksfromStudentbyAddress(address);
		
		
	
	if(result.size() == 0) {
		throw new StudentNotFoundException("Student does not exist with the address "+address);
	}
		else
			return result;	
	}
   @GetMapping("/getNameAddressMarks/{roll}")
	public StudentHelper getStudentNameAddressMarksByRoll(@PathVariable int roll) {
				
		StudentHelper sh= dao.getNameAddressMarks(roll);
		
		if(sh == null) {
			throw new StudentNotFoundException("Student does not exist with the Roll "+roll);
		}
		else
			return sh;		
	}
}

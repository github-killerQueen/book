package com.guo.service;

import com.guo.bean.Student;
import com.guo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentMapper studentMapper;

    public Student selectById(Integer stuId) {
        Student student = studentMapper.selectById(stuId);
        return student;
    }

    public List<Student> selectAll() {
        List<Student> students = studentMapper.selectAll();
        return students;
    }

    public void insertStudent(Student student) {
        studentMapper.insertStudent(student);
    }

    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    public void deleteStudent(Integer stuId) {
        studentMapper.deleteStudent(stuId);
    }

    public void updateStudentCountAdd(Integer stuId) {
        studentMapper.updateStudentCountAdd(stuId);
    }

    public void updateStudentCountReduce(Integer stuId) {
        studentMapper.updateStudentCountReduce(stuId);
    }
}

package com.guo.mapper;

import com.guo.bean.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    public Student selectById(Integer stuId);

    public List<Student> selectAll();

    public void insertStudent(Student student);

    public void updateStudent(Student student);

    public void deleteStudent(Integer stuId);

    public void updateStudentCountAdd(Integer stuId);

    public void updateStudentCountReduce(Integer stuId);
}

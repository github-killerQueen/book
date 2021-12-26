package com.guo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guo.bean.Book;
import com.guo.bean.Student;
import com.guo.service.BookService;
import com.guo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/")
    public String toLogin(HttpSession session) {
        session.removeAttribute("stuId");
        session.removeAttribute("stuName");
        return "stuLogin";
    }

    @PostMapping("/stuLogin")
    public String login(@RequestParam("stuId") Integer stuId,
                     @RequestParam("stuPassword") String stuPassword,
                     Model model, HttpSession session) {
        Student student = studentService.selectById(stuId);
        if(student == null) {
            model.addAttribute("info","此学号不存在!");
            return "stuLogin";
        }
        else if(!student.getStuPassword().equals(stuPassword)) {
            model.addAttribute("info","学号或密码错误!");
            return "stuLogin";
        }
        else {
            session.setAttribute("stuId", student.getStuId());
            session.setAttribute("stuName", student.getStuName());
            return "redirect:/stuBook";
        }
    }

    //重定向表单重复提交
    @GetMapping("/stuBook")
    public String toMain() {
        return "stuBook";
    }

    @GetMapping("/stuBorrow")
    public String toBorrow() {
        return "stuBorrow";
    }


    @GetMapping("/selectStudent/{stuId}")
    @ResponseBody
    public Student getStudent(@PathVariable("stuId") Integer stuId) {
        Student student = studentService.selectById(stuId);
        return student;
    }

    @GetMapping("/selectStudents")
    @ResponseBody
    public PageInfo getStudentList(@RequestParam(value = "pn",defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 8);
        List<Student> students = studentService.selectAll();
        PageInfo pi = new PageInfo(students);
        return pi;
    }

    @PostMapping("/addStudent")
    @ResponseBody
    public String insertStudent(Student student) {
        studentService.insertStudent(student);
        return null;
    }

    @PutMapping("updateStudent/{stuId}")
    @ResponseBody
    public String updateStudent(Student student) {
        studentService.updateStudent(student);
        return null;
    }

    @DeleteMapping("/deleteStudent/{stuId}")
    @ResponseBody
    public String deleteStudent(@PathVariable("stuId") Integer stuId) {
        studentService.deleteStudent(stuId);
        return null;
    }
}

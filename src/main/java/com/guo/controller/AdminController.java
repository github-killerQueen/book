package com.guo.controller;

import com.guo.bean.Admin;
import com.guo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/adminPage")
    public String toLogin(HttpSession session) {
        session.removeAttribute("adminName");
        return "adminLogin";
    }

    @PostMapping("/adminLogin")
    public String login(@RequestParam("adminId") String adminId,
                        @RequestParam("adminPassword") String adminPassword,
                        Model model, HttpSession session) {
        Admin admin = adminService.selectById(adminId);
        if(admin == null) {
            model.addAttribute("info","此管理员账号不存在!");
            return "adminLogin";
        }
        else if(!admin.getAdminPassword().equals(adminPassword)) {
            model.addAttribute("info","管理员账号或密码错误!");
            return "adminLogin";
        }
        else {
            session.setAttribute("adminName",admin.getAdminName());
            return "redirect:/adminBook";
        }
    }

    @GetMapping("/adminBook")
    public String toBook() {
        return "adminBook";
    }


    @GetMapping("/adminStudent")
    public String toStudent() {
        return "adminStudent";
    }

    @GetMapping("/adminBorrow")
    public String toBorrow() {
        return "adminBorrow";
    }
}

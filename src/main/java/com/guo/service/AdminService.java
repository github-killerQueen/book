package com.guo.service;

import com.guo.bean.Admin;
import com.guo.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminMapper adminMapper;

    public Admin selectById(String adminId) {
        Admin admin = adminMapper.selectById(adminId);
        return admin;
    }
}

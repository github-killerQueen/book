package com.guo.mapper;

import com.guo.bean.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    public Admin selectById(String adminId);
}

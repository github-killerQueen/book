package com.guo.mapper;

import com.guo.bean.Borrow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BorrowMapper {

    public Borrow selectByBookId(Integer bookId);

    public Borrow selectByStudentId(Integer stuId);

    public List<Borrow> selectAll();

    public void updateBorrow(Borrow borrow);

    public void insertBorrow(Borrow borrow);

    public void deleteBorrow(Integer bookId);
}

package com.silentteller.service;

import com.silentteller.entity.Book;
import com.silentteller.entity.Borrow;

import java.util.List;

public interface BookService {
    public List<Book> findAll(int page);
    public int getPages();
    public void addBorrow(Integer bookid, Integer readerid);
    public int getBorrowPages(Integer readerid);
    public List<Borrow> findAllBorrowByReaderId(Integer id, Integer page);
    public List<Borrow> findAllBorrowByState(Integer state,Integer page);
    public int getBorrowPagesByState(Integer state);
    public void handleBorrow(Integer borrowId,Integer state,Integer adminId);
}

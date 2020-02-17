package com.silentteller.repository;

import com.silentteller.entity.Book;

import java.util.List;

public interface BookRepository {
    public List<Book> findAll(int index, int limit);
    public int count();
}

package com.silentteller.repository.impl;

import com.silentteller.entity.Book;
import com.silentteller.entity.BookCase;
import com.silentteller.repository.BookRepository;
import com.silentteller.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {


    @Override
    public List<Book> findAll(int index, int limit) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from book,bookcase where book.bookcaseid = bookcase.id limit ?, ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Book> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, index);
            statement.setInt(2, limit);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                list.add(new Book(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getDouble(6),
                        new BookCase(resultSet.getInt(9),
                                      resultSet.getString(10)))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public int count() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from book,bookcase where book.bookcaseid = bookcase.id";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, statement, resultSet);
        }
        return count;
    }
}
